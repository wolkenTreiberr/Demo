package practice.solid

/*
 * Практические задачи по SOLID принципам
 *
 * Темы:
 * - SRP: Single Responsibility Principle
 * - OCP: Open-Closed Principle
 * - LSP: Liskov Substitution Principle
 * - ISP: Interface Segregation Principle
 * - DIP: Dependency Inversion Principle
 *
 * Как запускать: нажми зелёную стрелку рядом с fun main() внизу файла
 */

// ============================================================================
// 1. SRP — SINGLE RESPONSIBILITY
// ============================================================================

/*
 * Задача 1 — Рефакторинг God Object
 *
 * Дан класс OrderProcessor, который нарушает SRP — делает слишком много.
 * Раздели его на несколько классов, каждый с одной ответственностью.
 *
 * Подсказка: выдели OrderValidator, OrderRepository, OrderNotifier.
 */
fun task1() {
    // ПЛОХО — God Object:

    // class OrderProcessor {
    //     fun processOrder(orderId: String, items: List<String>, email: String) {
    //         // Валидация
    //         if (items.isEmpty()) throw IllegalArgumentException("Пустой заказ")
    //         if (!email.contains("@")) throw IllegalArgumentException("Невалидный email")
    //
    //         // Расчёт
    //         val total = items.size * 10.0
    //
    //         // Сохранение
    //         println("DB: Сохранён заказ $orderId на сумму $total")
    //
    //         // Уведомление
    //         println("Email на $email: Заказ $orderId принят, сумма $total")
    //     }
    // }

    // TODO: раздели на OrderValidator, PriceCalculator, OrderRepository, OrderNotifier
    //       и OrderService, который их координирует

    // После рефакторинга раскомментируй:
    // val validator = OrderValidator()
    // val calculator = PriceCalculator()
    // val repository = OrderRepository()
    // val notifier = OrderNotifier()
    // val service = OrderService(validator, calculator, repository, notifier)
    //
    // service.processOrder("ORD-001", listOf("Яблоко", "Хлеб"), "user@mail.com")
    // Невалидный email: Заказ $orderId принят, сумма $total
    // DB: Сохранён заказ ORD-001 на сумму 20.0
    // Email на user@mail.com: Заказ ORD-001 принят, сумма 20.0
}

// ============================================================================
// 2. OCP — OPEN-CLOSED
// ============================================================================

/*
 * Задача 2 — Система скидок
 *
 * Дан код с нарушением OCP — при добавлении новой скидки нужно менять when.
 * Перепиши, используя интерфейс DiscountStrategy.
 *
 * Типы скидок:
 * - NoDiscount — без скидки
 * - PercentDiscount(percent: Double) — процент от цены
 * - FixedDiscount(amount: Double) — фиксированная сумма (но не ниже 0)
 * - BuyOneGetOneFree — скидка 50% (каждый второй бесплатно)
 */
fun task2() {
    // ПЛОХО — нарушение OCP:

    // fun calculateDiscount(type: String, price: Double): Double {
    //     return when (type) {
    //         "none" -> price
    //         "percent10" -> price * 0.9
    //         "fixed500" -> maxOf(price - 500, 0.0)
    //         // Каждая новая скидка = изменение этой функции
    //         else -> price
    //     }
    // }

    // TODO: создай interface DiscountStrategy с методом apply(price: Double): Double
    //       и четыре реализации

    // После реализации раскомментируй:
    // val strategies = listOf(
    //     NoDiscount(),
    //     PercentDiscount(15.0),
    //     FixedDiscount(200.0),
    //     BuyOneGetOneFree()
    // )
    //
    // val price = 1000.0
    // strategies.forEach { strategy ->
    //     println("${strategy::class.simpleName}: ${strategy.apply(price)}")
    // }
    // NoDiscount: 1000.0
    // PercentDiscount: 850.0
    // FixedDiscount: 800.0
    // BuyOneGetOneFree: 500.0
}

// ============================================================================
// 3. LSP — LISKOV SUBSTITUTION
// ============================================================================

/*
 * Задача 3 — Найди и исправь нарушение LSP
 *
 * Дан код с нарушением LSP.
 * 1. Определи в чём нарушение (ReadOnlyFile бросает исключение при write)
 * 2. Перепиши иерархию так, чтобы LSP не нарушался
 *
 * Подсказка: раздели на Readable и Writable интерфейсы.
 */
fun task3() {
    // ПЛОХО — нарушение LSP:

    // open class File(val name: String) {
    //     open fun read(): String = "Содержимое $name"
    //     open fun write(content: String) {
    //         println("Запись в $name: $content")
    //     }
    // }
    //
    // class ReadOnlyFile(name: String) : File(name) {
    //     override fun write(content: String) {
    //         throw UnsupportedOperationException("$name только для чтения!")
    //     }
    // }
    //
    // fun saveLog(file: File, message: String) {
    //     file.write(message) // для ReadOnlyFile — ИСКЛЮЧЕНИЕ
    // }

    // TODO: перепиши с интерфейсами Readable и Writable

    // После рефакторинга раскомментируй:
    // val regular = RegularFile("log.txt")
    // val readOnly = ReadOnlyFile("config.txt")
    //
    // println(regular.read())      // Содержимое log.txt
    // regular.write("Новая запись") // Запись в log.txt: Новая запись
    // println(readOnly.read())     // Содержимое config.txt
    //
    // fun saveLog(file: Writable, message: String) {
    //     file.write(message)  // принимает ТОЛЬКО Writable — безопасно
    // }
    //
    // saveLog(regular, "OK")       // работает
    // // saveLog(readOnly, "OK")   // ошибка КОМПИЛЯЦИИ, не runtime — это и есть LSP
}

// ============================================================================
// 4. ISP — INTERFACE SEGREGATION
// ============================================================================

/*
 * Задача 4 — Разделение жирного интерфейса
 *
 * Дан жирный интерфейс Worker. Разделите его на маленькие.
 * Каждый класс реализует только нужные интерфейсы.
 */
fun task4() {
    // ПЛОХО — жирный интерфейс:

    // interface Worker {
    //     fun work()
    //     fun eat()
    //     fun sleep()
    //     fun attendMeeting()
    //     fun writeReport()
    // }
    //
    // class Robot : Worker {
    //     override fun work() = println("Робот работает")
    //     override fun eat() = throw UnsupportedOperationException()  // Робот не ест
    //     override fun sleep() = throw UnsupportedOperationException() // Робот не спит
    //     override fun attendMeeting() = throw UnsupportedOperationException()
    //     override fun writeReport() = println("Робот пишет отчёт")
    // }

    // TODO: раздели на интерфейсы: Workable, Feedable, Sleepable, Meetable, Reportable
    //       Robot реализует только Workable и Reportable
    //       Human реализует всё
    //       Intern реализует Workable, Feedable, Sleepable (без митингов и отчётов)

    // После реализации раскомментируй:
    // val robot = Robot("R2D2")
    // val human = Human("Иван")
    // val intern = Intern("Стажёр Петя")
    //
    // robot.work()           // R2D2 работает
    // robot.writeReport()    // R2D2 пишет отчёт
    //
    // human.work()           // Иван работает
    // human.eat()            // Иван обедает
    // human.attendMeeting()  // Иван на митинге
    //
    // intern.work()          // Стажёр Петя работает
    // intern.eat()           // Стажёр Петя обедает
    // intern.sleep()         // Стажёр Петя спит
}

// ============================================================================
// 5. DIP — DEPENDENCY INVERSION
// ============================================================================

/*
 * Задача 5 — Рефакторинг зависимостей
 *
 * Дан класс WeatherApp с жёсткими зависимостями.
 * Перепиши с DIP — зависимости через интерфейсы и конструктор.
 */
fun task5() {
    // ПЛОХО — жёсткие зависимости:

    // class WeatherApp {
    //     private val api = OpenWeatherApi()      // жёстко привязан
    //     private val cache = RedisCache()         // жёстко привязан
    //     private val logger = ConsoleLogger()     // жёстко привязан
    //
    //     fun getWeather(city: String): String {
    //         logger.log("Запрос погоды для $city")
    //         val cached = cache.get(city)
    //         if (cached != null) return cached
    //         val weather = api.fetch(city)
    //         cache.set(city, weather)
    //         return weather
    //     }
    // }

    // TODO: создай интерфейсы WeatherApi, Cache, Logger
    //       и перепиши WeatherApp с DI через конструктор

    // После реализации раскомментируй:
    // // Реализации
    // class SimpleWeatherApi : WeatherApi {
    //     override fun fetch(city: String) = "Солнечно в $city, +25C"
    // }
    //
    // class InMemoryCache : Cache {
    //     private val storage = mutableMapOf<String, String>()
    //     override fun get(key: String) = storage[key]
    //     override fun set(key: String, value: String) { storage[key] = value }
    // }
    //
    // class PrintLogger : Logger {
    //     override fun log(message: String) = println("[LOG] $message")
    // }
    //
    // val app = WeatherApp(SimpleWeatherApi(), InMemoryCache(), PrintLogger())
    //
    // println(app.getWeather("Москва"))
    // [LOG] Запрос погоды для Москва
    // Солнечно в Москва, +25C
    //
    // println(app.getWeather("Москва")) // второй раз — из кеша
    // [LOG] Запрос погоды для Москва
    // Солнечно в Москва, +25C (из кеша или нет — зависит от реализации)
}

// ============================================================================
// 6. КОМПЛЕКСНАЯ ЗАДАЧА — ВСЕ ПРИНЦИПЫ
// ============================================================================

/*
 * Задача 6 — Система логирования с SOLID
 *
 * Спроектируй систему логирования, соблюдая все 5 принципов:
 *
 * 1. SRP — каждый класс делает одно:
 *    - LogFormatter — форматирование сообщений
 *    - LogWriter — запись куда-то
 *    - Logger — координация
 *
 * 2. OCP — новые форматы и направления записи добавляются без изменения Logger:
 *    - interface LogFormatter с методом format(level: String, message: String): String
 *    - interface LogWriter с методом write(formattedMessage: String)
 *
 * 3. LSP — все реализации соблюдают контракт
 *
 * 4. ISP — интерфейсы маленькие и специализированные
 *
 * 5. DIP — Logger зависит от абстракций (LogFormatter, LogWriter)
 *
 * Реализуй:
 * - SimpleFormatter: "[LEVEL] message"
 * - TimestampFormatter: "[LEVEL] [timestamp] message" (timestamp = System.currentTimeMillis())
 * - ConsoleWriter: println(...)
 * - ListWriter: сохраняет в MutableList<String> (для тестирования)
 * - Logger(formatter: LogFormatter, writers: List<LogWriter>) с методами info(), warn(), error()
 */
fun task6() {
    // TODO: спроектируй и реализуй систему логирования

    // После реализации раскомментируй:
    // val listWriter = ListWriter()
    // val logger = Logger(
    //     formatter = SimpleFormatter(),
    //     writers = listOf(ConsoleWriter(), listWriter)
    // )
    //
    // logger.info("Приложение запущено")
    // logger.warn("Мало памяти")
    // logger.error("Ошибка подключения")
    //
    // println("\nЗаписи в listWriter:")
    // listWriter.entries.forEach { println("  $it") }

    // Ожидаемый вывод:
    // [INFO] Приложение запущено
    // [WARN] Мало памяти
    // [ERROR] Ошибка подключения
    //
    // Записи в listWriter:
    //   [INFO] Приложение запущено
    //   [WARN] Мало памяти
    //   [ERROR] Ошибка подключения
}

// ============================================================================
// MAIN — запуск всех задач
// ============================================================================

fun main() {
    println("=== Запусти нужную задачу, раскомментировав её ===")

    // SRP — SINGLE RESPONSIBILITY
    // task1()

    // OCP — OPEN-CLOSED
    // task2()

    // LSP — LISKOV SUBSTITUTION
    // task3()

    // ISP — INTERFACE SEGREGATION
    // task4()

    // DIP — DEPENDENCY INVERSION
    // task5()

    // КОМПЛЕКСНАЯ ЗАДАЧА
    // task6()
}
