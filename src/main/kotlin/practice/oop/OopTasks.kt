package practice.oop

/*
 * Практические задачи по ООП в Kotlin
 *
 * Темы:
 * - Классы, свойства, конструкторы
 * - Init блок, порядок инициализации
 * - Инкапсуляция и модификаторы видимости
 * - Наследование (open, override, super)
 * - Полиморфизм
 * - Абстрактные классы и интерфейсы
 * - Композиция vs наследование
 *
 * Как запускать: нажми зелёную стрелку рядом с fun main() внизу файла
 */

// ============================================================================
// 1. КЛАССЫ И КОНСТРУКТОРЫ
// ============================================================================

/*
 * Задача 1 — Создание классов
 *
 * 1. Создай класс Product с:
 *    - val name: String
 *    - var price: Double
 *    - var quantity: Int (по умолчанию = 0)
 *    - метод totalCost(): Double — возвращает price * quantity
 *    - метод describe(): String — "Product(name=<name>, price=<price>, qty=<quantity>)"
 *
 * 2. Создай класс Order с secondary constructor:
 *    - primary: (val id: String, val products: List<Product>)
 *    - secondary: (val id: String) — создаёт заказ с пустым списком
 *    - метод total(): Double — сумма totalCost() всех продуктов
 */
fun task1() {
    // TODO: создай классы Product и Order

    // После реализации раскомментируй:
    // val apple = Product("Яблоко", 1.5, 10)
    // val bread = Product("Хлеб", 2.0)
    // println(apple.describe())        // Product(name=Яблоко, price=1.5, qty=10)
    // println(apple.totalCost())       // 15.0
    // println(bread.describe())        // Product(name=Хлеб, price=2.0, qty=0)
    //
    // val order = Order("ORD-001", listOf(apple, bread))
    // println("Total: ${order.total()}") // Total: 15.0
    //
    // val emptyOrder = Order("ORD-002")
    // println("Empty total: ${emptyOrder.total()}") // Empty total: 0.0
}

// ============================================================================
// 2. INIT БЛОК И ПОРЯДОК ИНИЦИАЛИЗАЦИИ
// ============================================================================

/*
 * Задача 2 — Предскажи порядок вывода
 *
 * Не запуская код, определи что будет выведено.
 * Потом запусти и проверь.
 *
 * Подсказка: вспомни порядок — init-блоки и свойства сверху вниз,
 * secondary constructor — последний.
 */
fun task2() {
    // Раскомментируй и предскажи вывод:

    // class Example(val label: String) {
    //     val a = println("1. Свойство a")
    //
    //     init {
    //         println("2. Init блок 1, label=$label")
    //     }
    //
    //     val b = println("3. Свойство b")
    //
    //     init {
    //         println("4. Init блок 2")
    //     }
    //
    //     constructor(label: String, extra: Int) : this(label) {
    //         println("5. Secondary constructor, extra=$extra")
    //     }
    // }
    //
    // println("=== Primary constructor ===")
    // Example("Test")
    // println()
    // println("=== Secondary constructor ===")
    // Example("Test", 42)

    // Запиши свой прогноз здесь (потом сверь):
    // Primary:
    //   ???
    //
    // Secondary:
    //   ???
}

// ============================================================================
// 3. ИНКАПСУЛЯЦИЯ
// ============================================================================

/*
 * Задача 3 — Банковский счёт с инкапсуляцией
 *
 * Создай класс BankAccount:
 * - private var _balance: Double (начальное значение через конструктор)
 * - val balance: Double (публичный getter, private setter)
 * - val holder: String
 * - private val _history: MutableList<String> — лог операций
 * - val history: List<String> — неизменяемая копия лога
 *
 * Методы:
 * - deposit(amount: Double) — пополнение. Сумма > 0, иначе ошибка
 * - withdraw(amount: Double) — снятие. Сумма > 0 и <= balance, иначе ошибка
 * - Каждая операция добавляет запись в _history:
 *   "Deposit: +<amount>, balance: <new_balance>"
 *   "Withdrawal: -<amount>, balance: <new_balance>"
 */
fun task3() {
    // TODO: создай класс BankAccount

    // После реализации раскомментируй:
    // val account = BankAccount("Иван", 100.0)
    // account.deposit(50.0)
    // account.withdraw(30.0)
    // println("Баланс: ${account.balance}")  // 120.0
    // println("История:")
    // account.history.forEach { println("  $it") }
    // Deposit: +50.0, balance: 150.0
    // Withdrawal: -30.0, balance: 120.0

    // Эти строки должны вызвать ошибку компиляции или runtime exception:
    // account._balance = 1000000.0  // ошибка компиляции — private
    // account.deposit(-10.0)        // runtime exception
    // account.withdraw(999.0)       // runtime exception
}

// ============================================================================
// 4. НАСЛЕДОВАНИЕ
// ============================================================================

/*
 * Задача 4 — Иерархия сотрудников
 *
 * 1. Создай open class Employee(val name: String, val baseSalary: Double) с:
 *    - open fun calculateSalary(): Double = baseSalary
 *    - open fun role(): String = "Сотрудник"
 *    - fun info(): String = "${role()}: $name, зарплата: ${calculateSalary()}"
 *
 * 2. Создай класс Manager(name, baseSalary, val bonus: Double) : Employee
 *    - calculateSalary() = baseSalary + bonus
 *    - role() = "Менеджер"
 *
 * 3. Создай класс Developer(name, baseSalary, val language: String) : Employee
 *    - role() = "Разработчик ($language)"
 *
 * 4. Создай класс SeniorDeveloper(name, baseSalary, language, val leadBonus: Double) : Developer
 *    - calculateSalary() = baseSalary + leadBonus
 *    - role() = "Старший разработчик ($language)" — используй super.role()
 *      но замени "Разработчик" на "Старший разработчик"
 */
fun task4() {
    // TODO: создай классы Employee, Manager, Developer, SeniorDeveloper

    // После реализации раскомментируй:
    // val team = listOf(
    //     Manager("Анна", 5000.0, 1500.0),
    //     Developer("Борис", 4000.0, "Kotlin"),
    //     SeniorDeveloper("Вера", 4500.0, "Kotlin", 1000.0)
    // )
    //
    // team.forEach { println(it.info()) }
    // Менеджер: Анна, зарплата: 6500.0
    // Разработчик (Kotlin): Борис, зарплата: 4000.0
    // Старший разработчик (Kotlin): Вера, зарплата: 5500.0
}

// ============================================================================
// 5. ПОЛИМОРФИЗМ
// ============================================================================

/*
 * Задача 5 — Система уведомлений
 *
 * 1. Создай abstract class Notification(val recipient: String, val message: String) с:
 *    - abstract fun send(): String
 *    - fun preview(): String = "To: $recipient | ${send()}"
 *
 * 2. Создай три наследника:
 *    - EmailNotification(recipient, message, val subject: String)
 *      send() -> "Email [$subject]: $message"
 *    - SmsNotification(recipient, message)
 *      send() -> "SMS: $message"
 *    - PushNotification(recipient, message, val appName: String)
 *      send() -> "Push ($appName): $message"
 *
 * 3. Напиши функцию sendAll(notifications: List<Notification>)
 *    которая вызывает preview() для каждого — продемонстрируй полиморфизм.
 */
fun task5() {
    // TODO: создай классы и функцию sendAll

    // После реализации раскомментируй:
    // val notifications = listOf(
    //     EmailNotification("user@mail.com", "Ваш заказ отправлен", "Заказ #123"),
    //     SmsNotification("+7999123456", "Код: 4521"),
    //     PushNotification("user42", "Новое сообщение", "Telegram")
    // )
    //
    // sendAll(notifications)
    // To: user@mail.com | Email [Заказ #123]: Ваш заказ отправлен
    // To: +7999123456 | SMS: Код: 4521
    // To: user42 | Push (Telegram): Новое сообщение
}

// ============================================================================
// 6. ИНТЕРФЕЙСЫ
// ============================================================================

/*
 * Задача 6 — Интерфейсы с default implementations
 *
 * 1. Создай interface Printable с:
 *    - fun format(): String (абстрактный)
 *    - fun print() = println(format()) (default)
 *
 * 2. Создай interface Exportable с:
 *    - fun toJson(): String (абстрактный)
 *    - fun export() = println("Export: ${toJson()}") (default)
 *
 * 3. Создай data class Report(val title: String, val data: List<String>)
 *    который реализует ОБА интерфейса:
 *    - format() -> "=== $title ===\n" + data через \n
 *    - toJson() -> """{"title":"$title","items":${data.size}}"""
 *
 * 4. Вызови print() и export() — покажи что default implementations работают.
 */
fun task6() {
    // TODO: создай интерфейсы и класс Report

    // После реализации раскомментируй:
    // val report = Report("Продажи", listOf("Январь: 100", "Февраль: 150", "Март: 200"))
    // report.print()
    // === Продажи ===
    // Январь: 100
    // Февраль: 150
    // Март: 200

    // report.export()
    // Export: {"title":"Продажи","items":3}
}

// ============================================================================
// 7. ABSTRACT CLASS + INTERFACE ВМЕСТЕ
// ============================================================================

/*
 * Задача 7 — Платёжная система
 *
 * 1. Создай interface Refundable с:
 *    - fun refund(amount: Double): String
 *
 * 2. Создай abstract class PaymentMethod(val name: String) с:
 *    - fun authorize(amount: Double) = "Авторизация $amount через $name"
 *    - abstract fun processPayment(amount: Double): String
 *
 * 3. Создай класс CreditCard(name: String, val cardNumber: String)
 *    : PaymentMethod(name), Refundable
 *    - processPayment -> "Оплата $amount с карты $cardNumber"
 *    - refund -> "Возврат $amount на карту $cardNumber"
 *
 * 4. Создай класс Cash(name: String) : PaymentMethod(name)
 *    (без Refundable — наличные не возвращаются автоматически)
 *    - processPayment -> "Оплата $amount наличными"
 *
 * 5. Напиши функцию processOrder(method: PaymentMethod, amount: Double)
 *    - Вызвать authorize и processPayment
 *    - Если method is Refundable — вывести "Возврат доступен"
 */
fun task7() {
    // TODO: создай интерфейс, классы и функцию

    // После реализации раскомментируй:
    // val visa = CreditCard("Visa", "4111-XXXX-XXXX-1234")
    // val cash = Cash("Наличные")
    //
    // processOrder(visa, 100.0)
    // Авторизация 100.0 через Visa
    // Оплата 100.0 с карты 4111-XXXX-XXXX-1234
    // Возврат доступен
    //
    // println()
    // processOrder(cash, 50.0)
    // Авторизация 50.0 через Наличные
    // Оплата 50.0 наличными
}

// ============================================================================
// 8. КОМПОЗИЦИЯ VS НАСЛЕДОВАНИЕ
// ============================================================================

/*
 * Задача 8 — Рефакторинг: наследование -> композиция
 *
 * Дан код с неправильным использованием наследования.
 * Перепиши его, используя композицию.
 *
 * Проблема: Robot НЕ является ArrayList. Он ИСПОЛЬЗУЕТ список деталей.
 */
fun task8() {
    // ПЛОХО — наследование:
    // class Robot(val name: String) : ArrayList<String>() {
    //     fun addPart(part: String) {
    //         add(part)
    //         println("$name получил деталь: $part")
    //     }
    //
    //     fun showParts() {
    //         println("Детали $name: ${joinToString()}")
    //     }
    //
    //     fun removePart(part: String) {
    //         remove(part)
    //         println("$name потерял деталь: $part")
    //     }
    // }

    // TODO: перепиши Robot через композицию (private val parts = mutableListOf<String>())
    //       Robot НЕ наследует ArrayList, а содержит список внутри

    // После рефакторинга раскомментируй:
    // val robot = Robot("R2D2")
    // robot.addPart("Рука")
    // robot.addPart("Нога")
    // robot.addPart("Голова")
    // robot.showParts()         // Детали R2D2: Рука, Нога, Голова
    // robot.removePart("Нога")
    // robot.showParts()         // Детали R2D2: Рука, Голова

    // Эти строки НЕ должны компилироваться (в отличие от наследования):
    // robot.add("Хак")       // ошибка — нет публичного add()
    // robot.clear()           // ошибка — нет публичного clear()
    // robot.size              // ошибка — нет публичного size
}

// ============================================================================
// 9. РАЗРЕШЕНИЕ КОНФЛИКТОВ ИНТЕРФЕЙСОВ
// ============================================================================

/*
 * Задача 9 — Множественные интерфейсы с конфликтом
 *
 * 1. Создай interface Flyer с:
 *    - fun move(): String = "Летит по воздуху"
 *    - fun speed(): Int
 *
 * 2. Создай interface Swimmer с:
 *    - fun move(): String = "Плывёт по воде"
 *    - fun speed(): Int
 *
 * 3. Создай класс Duck(val name: String) : Flyer, Swimmer
 *    - move() должен комбинировать обе реализации через super<Flyer> и super<Swimmer>
 *      -> "$name: ${super<Flyer>.move()} и ${super<Swimmer>.move()}"
 *    - speed() -> 20
 *
 * 4. Создай класс Penguin(val name: String) : Swimmer
 *    - speed() -> 15
 *    (Penguin НЕ реализует Flyer — не летает)
 */
fun task9() {
    // TODO: создай интерфейсы и классы

    // После реализации раскомментируй:
    // val duck = Duck("Кряква")
    // val penguin = Penguin("Пингвин")
    //
    // println(duck.move())      // Кряква: Летит по воздуху и Плывёт по воде
    // println(duck.speed())     // 20
    // println(penguin.move())   // Плывёт по воде (default от Swimmer)
    // println(penguin.speed())  // 15
}

// ============================================================================
// 10. КОМПЛЕКСНАЯ ЗАДАЧА
// ============================================================================

/*
 * Задача 10 — Система фигур с полным ООП
 *
 * Собери всё вместе:
 *
 * 1. Создай interface Drawable с:
 *    - fun draw(): String
 *
 * 2. Создай abstract class Shape(val color: String) с:
 *    - abstract fun area(): Double
 *    - abstract fun perimeter(): Double
 *    - open fun describe(): String = "Фигура ($color)"
 *
 * 3. Создай классы:
 *    - Circle(color: String, val radius: Double) : Shape, Drawable
 *      area = PI * r^2, perimeter = 2 * PI * r
 *      draw() = "Рисую круг радиусом $radius"
 *      describe() = "${super.describe()}: Круг, r=$radius"
 *
 *    - Rectangle(color: String, val width: Double, val height: Double) : Shape, Drawable
 *      area = w * h, perimeter = 2 * (w + h)
 *      draw() = "Рисую прямоугольник ${width}x$height"
 *      describe() = "${super.describe()}: Прямоугольник, ${width}x$height"
 *
 *    - Triangle(color: String, val a: Double, val b: Double, val c: Double) : Shape, Drawable
 *      area = формула Герона, perimeter = a + b + c
 *      draw() = "Рисую треугольник со сторонами $a, $b, $c"
 *      describe() = "${super.describe()}: Треугольник, стороны $a, $b, $c"
 *
 * 4. Напиши функцию printShapeReport(shapes: List<Shape>) которая для каждой:
 *    - Выводит describe()
 *    - Выводит "Площадь: <area>, Периметр: <perimeter>"
 *    - Если shape is Drawable — вызывает draw()
 *    - Разделяет фигуры пустой строкой
 *
 * 5. В конце выведи "Общая площадь: <sum>"
 */
fun task10() {
    // TODO: создай интерфейс, абстрактный класс и три наследника

    // После реализации раскомментируй:
    // val shapes = listOf(
    //     Circle("красный", 5.0),
    //     Rectangle("синий", 4.0, 6.0),
    //     Triangle("зелёный", 3.0, 4.0, 5.0)
    // )
    //
    // printShapeReport(shapes)

    // Ожидаемый вывод:
    // Фигура (красный): Круг, r=5.0
    // Площадь: 78.53981633974483, Периметр: 31.41592653589793
    // Рисую круг радиусом 5.0
    //
    // Фигура (синий): Прямоугольник, 4.0x6.0
    // Площадь: 24.0, Периметр: 20.0
    // Рисую прямоугольник 4.0x6.0
    //
    // Фигура (зелёный): Треугольник, стороны 3.0, 4.0, 5.0
    // Площадь: 6.0, Периметр: 12.0
    // Рисую треугольник со сторонами 3.0, 4.0, 5.0
    //
    // Общая площадь: 108.53981633974483
}

// ============================================================================
// MAIN — запуск всех задач
// ============================================================================

fun main() {
    println("=== Запусти нужную задачу, раскомментировав её ===")

    // КЛАССЫ И КОНСТРУКТОРЫ
    // task1()

    // INIT БЛОК И ПОРЯДОК ИНИЦИАЛИЗАЦИИ
    // task2()

    // ИНКАПСУЛЯЦИЯ
    // task3()

    // НАСЛЕДОВАНИЕ
    // task4()

    // ПОЛИМОРФИЗМ
    // task5()

    // ИНТЕРФЕЙСЫ
    // task6()

    // ABSTRACT CLASS + INTERFACE
    // task7()

    // КОМПОЗИЦИЯ VS НАСЛЕДОВАНИЕ
    // task8()

    // КОНФЛИКТЫ ИНТЕРФЕЙСОВ
    // task9()

    // КОМПЛЕКСНАЯ ЗАДАЧА
    // task10()
}
