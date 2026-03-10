## ЧТО ТАКОЕ SOLID И КАКУЮ ПРОБЛЕМУ РЕШАЕТ?
---
Пять принципов объектно-ориентированного проектирования для создания поддерживаемого, расширяемого и гибкого кода.

- S — Single Responsibility Principle (Принцип единственной ответственности)
- O — Open-Closed Principle (Принцип открытости/закрытости)
- L — Liskov Substitution Principle (Принцип подстановки Лисков)
- I — Interface Segregation Principle (Принцип разделения интерфейсов)
- D — Dependency Inversion Principle (Принцип инверсии зависимостей)

Почему именно эти 5: вместе решают основные проблемы плохого дизайна:
1. Жёсткость (Rigidity) — сложно изменить код, одно изменение тянет за собой много других (решает OCP, SRP)
2. Хрупкость (Fragility) — изменения в одном месте ломают код в другом (решает SRP, LSP)
3. Неподвижность (Immobility) — невозможно переиспользовать код в другом контексте, всё слишком связано (решает DIP, ISP)

SOLID — не догма, а ориентир. Не каждый класс обязан следовать всем пяти принципам. Цель — писать код, который легко читать, менять и тестировать.
!!!
## ЧТО ТАКОЕ SRP И КАКУЮ ПРОБЛЕМУ РЕШАЕТ?
---
Single Responsibility Principle — класс должен иметь только одну причину для изменения.

"Причина для изменения" = один актор (человек/система), чьи требования влияют на класс. Если два разных отдела могут потребовать изменить один класс — у него две ответственности.

Какую проблему решает:
- God objects — классы, которые делают всё
- Высокая связанность кода
- Сложность тестирования и отладки
- Каскадные изменения при модификации

Почему важен:
- Упрощает понимание кода
- Облегчает тестирование (меньше зависимостей)
- Снижает риск поломки при изменениях

ПРИМЕР:
- UserManager — только управление пользователями
- UserValidator — только валидация данных
- Изменение правил валидации не влияет на основные операции с пользователями
!!!
## ПРИМЕРЫ SRP И АНТИПАТТЕРНЫ
---
```kotlin
// НАРУШЕНИЕ — множественная ответственность
class User(var name: String, var email: String) {
    fun updateEmail(newEmail: String) { email = newEmail }
    fun isValidEmail(): Boolean = email.contains("@")
    fun saveToDatabase() { /* SQL логика */ }
    fun sendWelcomeEmail() { /* SMTP логика */ }
}
// Проблема: изменение формата email, логики БД или шаблона письма —
// три разные причины менять ОДИН класс

// ПРАВИЛЬНО — разделение ответственностей
data class User(val name: String, val email: String)

class UserValidator {
    fun isValidEmail(email: String): Boolean {
        return email.contains("@") && email.contains(".")
    }

    fun isValidUser(user: User): Boolean {
        return user.name.isNotBlank() && isValidEmail(user.email)
    }
}

class UserRepository {
    fun save(user: User) { /* только работа с БД */ }
    fun findById(id: String): User? { /* только работа с БД */ return null }
}

class EmailService {
    fun sendWelcome(user: User) { /* только отправка */ }
}
```

Антипаттерн: God object — класс, который знает и делает слишком много. Признак: название класса содержит "Manager", "Handler", "Processor", "Helper" и при этом файл больше 200-300 строк.
!!!
## ЧТО ТАКОЕ OCP И КАКУЮ ПРОБЛЕМУ РЕШАЕТ?
---
Open-Closed Principle — классы должны быть открыты для расширения, но закрыты для модификации.

Новый функционал добавляется через создание новых классов (наследование, реализация интерфейсов), а не через изменение существующего кода.

Какую проблему решает:
- Риск поломки существующего кода при добавлении функций
- Необходимость изменять и перетестировать старый код
- Нарушение обратной совместимости

Почему важен:
- Новый функционал без риска сломать старый
- Защита проверенного кода от изменений
- Упрощение добавления новых возможностей

Реализация в Kotlin:
- Абстрактные классы и интерфейсы
- Полиморфизм и наследование
- Стратегия через функциональные типы

ПРИМЕР:
- Shape с абстрактным area()
- Circle, Rectangle, Triangle расширяют Shape
- Новые фигуры добавляются без изменения существующего кода
!!!
## ПРИМЕРЫ OCP И АНТИПАТТЕРНЫ
---
```kotlin
// НАРУШЕНИЕ — нужно модифицировать класс для новых фигур
class AreaCalculator {
    fun calculate(shape: Any): Double {
        return when (shape) {
            is Circle -> Math.PI * shape.radius * shape.radius
            is Rectangle -> shape.width * shape.height
            // Каждая новая фигура = изменение ЭТОГО класса
            else -> throw IllegalArgumentException("Неизвестная фигура")
        }
    }
}

// ПРАВИЛЬНО — расширение без модификации
interface Shape {
    fun area(): Double
}

class Circle(val radius: Double) : Shape {
    override fun area() = Math.PI * radius * radius
}

class Rectangle(val width: Double, val height: Double) : Shape {
    override fun area() = width * height
}

// Новая фигура — НОВЫЙ класс, старый код не трогаем
class Triangle(val base: Double, val height: Double) : Shape {
    override fun area() = 0.5 * base * height
}

class AreaCalculator {
    fun calculate(shape: Shape) = shape.area()
}
```

Подход через функциональный тип (стратегия):

```kotlin
// Стратегия скидки — легко добавить новую без изменения существующих
fun interface DiscountStrategy {
    fun apply(price: Double): Double
}

val noDiscount = DiscountStrategy { it }
val tenPercent = DiscountStrategy { it * 0.9 }
val fixedDiscount = DiscountStrategy { maxOf(it - 500.0, 0.0) }

class PriceCalculator(private val strategy: DiscountStrategy) {
    fun finalPrice(price: Double) = strategy.apply(price)
}
```

Антипаттерн: when/if по типам объектов в нескольких местах кода. Если добавление нового типа требует правок в 5 файлах — нарушен OCP.
!!!
## ЧТО ТАКОЕ LSP И КАКУЮ ПРОБЛЕМУ РЕШАЕТ?
---
Liskov Substitution Principle — объекты-наследники должны корректно заменять родителей. Подкласс не должен нарушать контракт (ожидаемое поведение) базового класса.

Формально: если код работает с типом Base, он должен работать с ЛЮБЫМ наследником Base без сюрпризов — без исключений, без изменённой семантики, без нарушенных инвариантов.

Какую проблему решает:
- Неожиданное поведение при использовании наследования
- Нарушение контракта базового класса
- Невозможность полиморфной замены

Почему важен:
- Гарантирует корректность полиморфизма
- Предсказуемое поведение наследников
- Безопасная замена реализаций

Проверка: если метод базового класса что-то обещает (возвращает результат, не бросает исключение), наследник должен соблюдать эти обещания.

ПРИМЕР:
- Bird с методом fly()
- Правильно: Sparrow и Eagle реализуют fly()
- Неправильно: Penguin наследует fly() и бросает исключение
- Решение: Интерфейс Flyable только для летающих птиц
!!!
## ПРИМЕРЫ LSP И АНТИПАТТЕРНЫ
---
```kotlin
// НАРУШЕНИЕ LSP — Penguin не может летать, но наследует fly()
open class Bird {
    open fun fly(): String = "Летит"
}

class Sparrow : Bird() {
    override fun fly() = "Воробей летит"
}

class Penguin : Bird() {
    override fun fly(): String {
        throw UnsupportedOperationException("Пингвины не летают!")
    }
}

fun makeFly(bird: Bird) {
    println(bird.fly()) // для Penguin — ИСКЛЮЧЕНИЕ. Нарушен контракт.
}

// ПРАВИЛЬНО — разделение иерархии
open class Bird(val name: String) {
    open fun eat() = "$name ест"
}

interface Flyable {
    fun fly(): String
}

class Sparrow : Bird("Воробей"), Flyable {
    override fun fly() = "$name летит"
}

class Penguin : Bird("Пингвин") {
    fun swim() = "$name плывёт"
}

// Теперь безопасно — только летающие птицы
fun makeFly(bird: Flyable) {
    println(bird.fly())
}
```

Классический пример — Rectangle/Square:

```kotlin
// НАРУШЕНИЕ — Square меняет поведение Rectangle
open class Rectangle(open var width: Double, open var height: Double) {
    open fun area() = width * height
}

class Square(side: Double) : Rectangle(side, side) {
    override var width: Double = side
        set(value) { field = value; height = value }
    override var height: Double = side
        set(value) { field = value; width = value }
}

fun doubleWidth(rect: Rectangle) {
    rect.width = rect.width * 2
    // Ожидание: area = width * 2 * height
    // Для Square: area = (width * 2) * (width * 2) — СЮРПРИЗ!
}
```

Антипаттерн: наследование "is-a" без проверки поведения. Квадрат IS-A прямоугольник математически, но не программно — потому что изменение ширины меняет и высоту.
!!!
## ЧТО ТАКОЕ ISP И КАКУЮ ПРОБЛЕМУ РЕШАЕТ?
---
Interface Segregation Principle — клиенты не должны зависеть от интерфейсов, которые они не используют. Много маленьких интерфейсов лучше одного большого.

Какую проблему решает:
- "Жирные" интерфейсы с множеством методов
- Вынужденная реализация ненужных методов (пустые заглушки, UnsupportedOperationException)
- Высокая связанность — изменение одного метода в интерфейсе затрагивает все классы

Почему важен:
- Минимизирует зависимости
- Упрощает реализацию и тестирование
- Повышает гибкость системы

ПРИМЕР:
- Вместо большого Machine (print, scan, fax, staple)
- Отдельные Printer, Scanner, Faxer
- SimplePrinter реализует только Printer
- MultifunctionDevice реализует все три
!!!
## ПРИМЕРЫ ISP И АНТИПАТТЕРНЫ
---
```kotlin
// НАРУШЕНИЕ — жирный интерфейс
interface Machine {
    fun print(document: String)
    fun scan(): String
    fun fax(document: String, number: String)
    fun staple()
}

class SimplePrinter : Machine {
    override fun print(document: String) = println("Печать: $document")
    override fun scan(): String = throw UnsupportedOperationException()  // не умеет
    override fun fax(document: String, number: String) = throw UnsupportedOperationException()
    override fun staple() = throw UnsupportedOperationException()
}
// SimplePrinter вынужден реализовать 4 метода, хотя использует 1

// ПРАВИЛЬНО — разделённые интерфейсы
interface Printer {
    fun print(document: String)
}

interface Scanner {
    fun scan(): String
}

interface Faxer {
    fun fax(document: String, number: String)
}

class SimplePrinter : Printer {
    override fun print(document: String) = println("Печать: $document")
}

class MultifunctionDevice : Printer, Scanner, Faxer {
    override fun print(document: String) = println("Печать: $document")
    override fun scan(): String = "Скан документа"
    override fun fax(document: String, number: String) = println("Факс на $number")
}
```

Когда интерфейс слишком большой — признаки:
- Классы реализуют интерфейс, но бросают UnsupportedOperationException в некоторых методах
- Название интерфейса слишком общее (Worker, Handler, Manager)
- При добавлении метода в интерфейс приходится менять 10+ классов, хотя метод нужен только двум

Антипаттерн: интерфейсы со множеством методов, где каждый клиент использует только часть.
!!!
## ЧТО ТАКОЕ DIP И КАКУЮ ПРОБЛЕМУ РЕШАЕТ?
---
Dependency Inversion Principle — модули высокого уровня не должны зависеть от модулей низкого уровня. Оба должны зависеть от абстракций.

"Высокий уровень" — бизнес-логика (что делать). "Низкий уровень" — детали реализации (как делать: БД, API, файлы).

Какую проблему решает:
- Жёсткая связь с конкретными реализациями
- Сложность тестирования (невозможно подменить зависимости)
- Невозможность изменить реализацию без изменения бизнес-логики

Почему важен:
- Упрощает тестирование через моки
- Позволяет менять реализации (PostgreSQL -> MongoDB) без изменения бизнес-кода
- Снижает связанность модулей

Реализация в Kotlin:
- Зависимость от интерфейсов, а не от классов
- Dependency Injection через конструктор
- Factory pattern

ПРИМЕР:
- NotificationService зависит от MessageSender (абстракция)
- EmailSender, SmsSender, PushSender реализуют MessageSender
- Легко добавить новый способ отправки без изменения сервиса
!!!
## ПРИМЕРЫ DIP И АНТИПАТТЕРНЫ
---
```kotlin
// НАРУШЕНИЕ — прямая зависимость от конкретной реализации
class NotificationService {
    private val sender = EmailSender() // жёстко привязан к email

    fun notify(message: String) {
        sender.send(message)
    }
}
// Проблема: нельзя отправить SMS, нельзя подменить на мок для теста

// ПРАВИЛЬНО — зависимость от абстракции
interface MessageSender {
    fun send(message: String)
}

class EmailSender : MessageSender {
    override fun send(message: String) = println("Email: $message")
}

class SmsSender : MessageSender {
    override fun send(message: String) = println("SMS: $message")
}

class PushSender : MessageSender {
    override fun send(message: String) = println("Push: $message")
}

// Зависимость передаётся через конструктор (DI)
class NotificationService(private val sender: MessageSender) {
    fun notify(message: String) {
        sender.send(message)
    }
}

fun main() {
    val emailNotifier = NotificationService(EmailSender())
    val smsNotifier = NotificationService(SmsSender())

    emailNotifier.notify("Привет через email")
    smsNotifier.notify("Привет через SMS")
}
```

DI через конструктор — идиоматический подход в Kotlin и Spring:

```kotlin
// Слой абстракций
interface UserRepository {
    fun findById(id: String): User?
    fun save(user: User)
}

interface EmailService {
    fun send(to: String, body: String)
}

// Бизнес-логика зависит ТОЛЬКО от абстракций
class UserService(
    private val repo: UserRepository,
    private val email: EmailService
) {
    fun register(name: String, emailAddr: String) {
        val user = User(name, emailAddr)
        repo.save(user)
        email.send(emailAddr, "Добро пожаловать, $name!")
    }
}

// В тестах — подставляем моки:
// val service = UserService(FakeUserRepository(), FakeEmailService())

// В проде — реальные реализации:
// val service = UserService(PostgresUserRepository(), SmtpEmailService())
```

Антипаттерн: создание зависимостей через конструктор внутри класса (new EmailSender()) вместо инъекции извне.
!!!
## КАК ПРИНЦИПЫ SOLID СВЯЗАНЫ МЕЖДУ СОБОЙ?
---
Принципы не изолированы — они усиливают друг друга:

SRP + OCP: если у класса одна ответственность, его проще расширять, не ломая другие.

OCP + LSP: расширение через наследование работает безопасно, только если наследники соблюдают контракт.

ISP + DIP: маленькие интерфейсы упрощают инверсию зависимостей — проще создать абстракцию для узкого контракта.

LSP + ISP: если интерфейс маленький, меньше шансов что реализация нарушит контракт (не нужны заглушки).

Порядок применения на практике:
1. SRP — раздели ответственности
2. ISP — выдели узкие интерфейсы
3. DIP — зависи от абстракций
4. OCP — расширяй через новые классы
5. LSP — проверяй что наследники не ломают контракт

Не надо применять SOLID фанатично. Для простого CRUD с 3 эндпоинтами — избыточно. Для растущего проекта с командой — необходимо.
