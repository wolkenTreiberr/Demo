package practice.classes

import kotlin.math.sqrt

/*
 * Практические задачи по типам классов Kotlin
 *
 * Темы:
 * - Data classes (equals, hashCode, toString, copy, destructuring)
 * - Enum classes (свойства, методы, entries, valueOf)
 * - Sealed classes (иерархии, exhaustive when)
 * - Object declarations (singleton)
 * - Companion object (factory methods, константы)
 * - Data objects
 * - Nested / Inner classes
 * - Object expressions (анонимные классы)
 *
 * Как запускать: нажми зелёную стрелку рядом с fun main() внизу файла
 */

// ============================================================================
// 1. DATA CLASS
// ============================================================================

/*
 * Задача 1 — Data class и его возможности
 *
 * 1. Создай data class Point(val x: Double, val y: Double) с методом:
 *    - fun distanceTo(other: Point): Double — расстояние между точками
 *      (формула: sqrt((x2-x1)^2 + (y2-y1)^2))
 *
 * 2. Продемонстрируй:
 *    - toString() — автогенерация
 *    - equals() — сравнение по содержимому
 *    - copy() — копия с изменением одного поля
 *    - Деструктуризация — val (x, y) = point
 */
fun task1() {
    // TODO: создай data class Point

    // После реализации раскомментируй:
    // val p1 = Point(0.0, 0.0)
    // val p2 = Point(3.0, 4.0)
    // val p3 = Point(3.0, 4.0)
    //
    // println(p1)                        // Point(x=0.0, y=0.0)
    // println(p2)                        // Point(x=3.0, y=4.0)
    // println(p1.distanceTo(p2))         // 5.0
    // println(p2 == p3)                  // true (equals по содержимому)
    // println(p2 === p3)                 // false (разные объекты в памяти)
    //
    // val p4 = p2.copy(y = 0.0)
    // println(p4)                        // Point(x=3.0, y=0.0)
    //
    // val (x, y) = p2
    // println("x=$x, y=$y")             // x=3.0, y=4.0
}

// ============================================================================
// 2. ENUM CLASS
// ============================================================================

/*
 * Задача 2 — Enum с свойствами и методами
 *
 * 1. Создай enum class HttpStatus(val code: Int, val description: String) с:
 *    - OK(200, "OK")
 *    - CREATED(201, "Created")
 *    - BAD_REQUEST(400, "Bad Request")
 *    - UNAUTHORIZED(401, "Unauthorized")
 *    - NOT_FOUND(404, "Not Found")
 *    - INTERNAL_ERROR(500, "Internal Server Error")
 *
 * 2. Добавь метод isSuccess(): Boolean — true если код 200-299
 *
 * 3. Добавь companion object с методом fromCode(code: Int): HttpStatus?
 *    — находит статус по коду или возвращает null
 */
fun task2() {
    // TODO: создай enum class HttpStatus

    // После реализации раскомментируй:
    // println(HttpStatus.OK.code)               // 200
    // println(HttpStatus.OK.description)         // OK
    // println(HttpStatus.OK.isSuccess())         // true
    // println(HttpStatus.NOT_FOUND.isSuccess())  // false
    //
    // println(HttpStatus.fromCode(201))          // CREATED
    // println(HttpStatus.fromCode(999))          // null
    //
    // // Перебор всех статусов
    // HttpStatus.entries.forEach { status ->
    //     println("${status.code}: ${status.description} [success=${status.isSuccess()}]")
    // }
    //
    // // when — exhaustive
    // fun handle(status: HttpStatus): String = when (status) {
    //     HttpStatus.OK -> "Всё хорошо"
    //     HttpStatus.CREATED -> "Создано"
    //     HttpStatus.BAD_REQUEST -> "Плохой запрос"
    //     HttpStatus.UNAUTHORIZED -> "Нет доступа"
    //     HttpStatus.NOT_FOUND -> "Не найдено"
    //     HttpStatus.INTERNAL_ERROR -> "Ошибка сервера"
    // }
    // println(handle(HttpStatus.NOT_FOUND)) // Не найдено
}

// ============================================================================
// 3. SEALED CLASS
// ============================================================================

/*
 * Задача 3 — Sealed class для результата операции
 *
 * 1. Создай sealed class ApiResult с:
 *    - data class Success(val data: String) : ApiResult()
 *    - data class Error(val code: Int, val message: String) : ApiResult()
 *    - data object Loading : ApiResult()
 *    - data object Empty : ApiResult()
 *
 * 2. Напиши функцию renderResult(result: ApiResult): String
 *    с exhaustive when (без else)
 *
 * 3. Напиши функцию simulateApi(id: Int): ApiResult
 *    - id == 1 -> Success("User: Иван")
 *    - id == 0 -> Empty
 *    - id < 0 -> Error(400, "Невалидный id")
 *    - иначе -> Error(404, "Не найден")
 */
fun task3() {
    // TODO: создай sealed class ApiResult и функции

    // После реализации раскомментируй:
    // val results = listOf(
    //     simulateApi(1),
    //     simulateApi(0),
    //     simulateApi(-1),
    //     simulateApi(99)
    // )
    //
    // results.forEach { println(renderResult(it)) }
    // Данные: User: Иван
    // Нет данных
    // Ошибка 400: Невалидный id
    // Ошибка 404: Не найден
}

// ============================================================================
// 4. OBJECT DECLARATION (SINGLETON)
// ============================================================================

/*
 * Задача 4 — Singleton-конфигурация
 *
 * 1. Создай object AppConfig с:
 *    - var appName: String = "MyApp"
 *    - var version: String = "1.0.0"
 *    - var debug: Boolean = false
 *    - fun summary(): String = "$appName v$version (debug=$debug)"
 *
 * 2. Создай interface Loggable с fun log(message: String)
 *
 * 3. Создай object ConsoleLogger : Loggable
 *    - log() выводит "[LOG] $message"
 *
 * 4. Продемонстрируй что object — singleton (один экземпляр):
 *    Измени appName, проверь что изменение видно отовсюду.
 */
fun task4() {
    // TODO: создай object AppConfig, interface Loggable, object ConsoleLogger

    // После реализации раскомментируй:
    // println(AppConfig.summary())  // MyApp v1.0.0 (debug=false)
    // AppConfig.appName = "KotlinApp"
    // AppConfig.debug = true
    // println(AppConfig.summary())  // KotlinApp v1.0.0 (debug=true)
    //
    // ConsoleLogger.log("Приложение запущено")  // [LOG] Приложение запущено
    //
    // // Передача как объект
    // fun setup(logger: Loggable) {
    //     logger.log("Инициализация")
    // }
    // setup(ConsoleLogger)  // [LOG] Инициализация
}

// ============================================================================
// 5. COMPANION OBJECT
// ============================================================================

/*
 * Задача 5 — Factory methods через companion object
 *
 * 1. Создай data class Color(val r: Int, val g: Int, val b: Int) с:
 *    - init: проверка что r, g, b в диапазоне 0..255
 *    - fun toHex(): String — "#RRGGBB" (шестнадцатеричная строка)
 *
 * 2. Добавь companion object с factory methods:
 *    - fun red() = Color(255, 0, 0)
 *    - fun green() = Color(0, 255, 0)
 *    - fun blue() = Color(0, 0, 255)
 *    - fun fromHex(hex: String): Color — парсит "#RRGGBB" обратно в Color
 *      (подсказка: hex.removePrefix("#"), substring, toInt(16))
 *
 * 3. Добавь const val MAX_VALUE = 255 в companion object
 */
fun task5() {
    // TODO: создай data class Color с companion object

    // После реализации раскомментируй:
    // val red = Color.red()
    // println(red)            // Color(r=255, g=0, b=0)
    // println(red.toHex())    // #FF0000
    //
    // val custom = Color(128, 64, 32)
    // println(custom.toHex()) // #804020
    //
    // val parsed = Color.fromHex("#FF8000")
    // println(parsed)         // Color(r=255, g=128, b=0)
    //
    // println(Color.MAX_VALUE) // 255
    //
    // // Ошибка: невалидное значение
    // // val invalid = Color(300, 0, 0) // IllegalArgumentException
}

// ============================================================================
// 6. NESTED И INNER КЛАССЫ
// ============================================================================

/*
 * Задача 6 — Builder через nested class
 *
 * 1. Создай класс Html с:
 *    - private val elements: MutableList<String>
 *    - fun render(): String — соединяет все элементы через "\n"
 *
 * 2. Внутри Html создай nested class Builder с:
 *    - private val elements = mutableListOf<String>()
 *    - fun head(title: String): Builder — добавляет "<head>$title</head>", возвращает this
 *    - fun body(content: String): Builder — добавляет "<body>$content</body>", возвращает this
 *    - fun paragraph(text: String): Builder — добавляет "<p>$text</p>", возвращает this
 *    - fun build(): Html — создаёт Html с собранными элементами
 *
 * Это nested class (не inner) — Builder не нужен доступ к состоянию Html.
 */
fun task6() {
    // TODO: создай класс Html с nested class Builder

    // После реализации раскомментируй:
    // val html = Html.Builder()
    //     .head("Моя страница")
    //     .body("Контент")
    //     .paragraph("Параграф 1")
    //     .paragraph("Параграф 2")
    //     .build()
    //
    // println(html.render())
    // <head>Моя страница</head>
    // <body>Контент</body>
    // <p>Параграф 1</p>
    // <p>Параграф 2</p>
}

// ============================================================================
// 7. OBJECT EXPRESSION (АНОНИМНЫЙ КЛАСС)
// ============================================================================

/*
 * Задача 7 — Анонимные реализации интерфейсов
 *
 * 1. Создай interface Comparator<T> с:
 *    - fun compare(a: T, b: T): Int
 *
 * 2. Создай data class Student(val name: String, val grade: Double)
 *
 * 3. Напиши функцию sortStudents(students: List<Student>, comparator: Comparator<Student>): List<Student>
 *
 * 4. Вызови sortStudents с тремя разными анонимными object expression:
 *    - Сортировка по имени (алфавитно)
 *    - Сортировка по оценке (от большей к меньшей)
 *    - Сортировка по длине имени
 */
fun task7() {
    // TODO: создай интерфейс, data class, функцию

    // После реализации раскомментируй:
    // val students = listOf(
    //     Student("Борис", 4.5),
    //     Student("Анна", 5.0),
    //     Student("Вера", 3.8),
    //     Student("Ян", 4.2)
    // )
    //
    // val byName = sortStudents(students, object : Comparator<Student> {
    //     override fun compare(a: Student, b: Student) = a.name.compareTo(b.name)
    // })
    // println("По имени: $byName")
    // // [Student(name=Анна, ...), Student(name=Борис, ...), Student(name=Вера, ...), Student(name=Ян, ...)]
    //
    // val byGrade = sortStudents(students, object : Comparator<Student> {
    //     override fun compare(a: Student, b: Student) = b.grade.compareTo(a.grade)
    // })
    // println("По оценке: $byGrade")
    // // [Student(name=Анна, grade=5.0), ..., Student(name=Вера, grade=3.8)]
    //
    // val byNameLength = sortStudents(students, object : Comparator<Student> {
    //     override fun compare(a: Student, b: Student) = a.name.length - b.name.length
    // })
    // println("По длине имени: $byNameLength")
    // // [Student(name=Ян, ...), ..., Student(name=Борис, ...)]
}

// ============================================================================
// 8. SEALED + ENUM + DATA CLASS ВМЕСТЕ
// ============================================================================

/*
 * Задача 8 — Система событий
 *
 * Спроектируй систему событий приложения:
 *
 * 1. Создай enum class Priority { LOW, MEDIUM, HIGH, CRITICAL }
 *
 * 2. Создай sealed class AppEvent с:
 *    - data class UserAction(val userId: String, val action: String, val priority: Priority)
 *    - data class SystemError(val code: Int, val message: String, val priority: Priority)
 *    - data class NetworkEvent(val url: String, val statusCode: Int)
 *    - data object AppStarted
 *    - data object AppStopped
 *
 * 3. Напиши функцию processEvent(event: AppEvent): String
 *    с exhaustive when
 *
 * 4. Напиши функцию filterCritical(events: List<AppEvent>): List<AppEvent>
 *    — возвращает только события с priority == CRITICAL
 *    (для событий без priority — пропускать)
 */
fun task8() {
    // TODO: создай enum, sealed class и функции

    // После реализации раскомментируй:
    // val events = listOf(
    //     AppEvent.AppStarted,
    //     AppEvent.UserAction("user-1", "login", Priority.LOW),
    //     AppEvent.SystemError(500, "DB down", Priority.CRITICAL),
    //     AppEvent.NetworkEvent("https://api.com", 200),
    //     AppEvent.UserAction("user-2", "delete_all", Priority.CRITICAL),
    //     AppEvent.AppStopped
    // )
    //
    // println("=== Все события ===")
    // events.forEach { println(processEvent(it)) }
    //
    // println("\n=== Критические ===")
    // filterCritical(events).forEach { println(processEvent(it)) }
}

// ============================================================================
// MAIN — запуск всех задач
// ============================================================================

fun main() {
    println("=== Запусти нужную задачу, раскомментировав её ===")

    // DATA CLASS
    // task1()

    // ENUM CLASS
    // task2()

    // SEALED CLASS
    // task3()

    // OBJECT DECLARATION
    // task4()

    // COMPANION OBJECT
    // task5()

    // NESTED / INNER КЛАССЫ
    // task6()

    // OBJECT EXPRESSION
    // task7()

    // КОМПЛЕКСНАЯ ЗАДАЧА
    // task8()
}
