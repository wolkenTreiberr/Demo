package practice.functions

/*
 * Практические задачи по функциям Kotlin
 *
 * Темы:
 * - Параметры функций (default, named, vararg)
 * - Single-expression functions
 * - Extension functions
 * - Higher-order functions + лямбды
 * - Function references (::)
 * - Scope functions (let, run, apply, also)
 * - Рекурсия + tailrec
 * - Замыкание (closure)
 * - Перегрузка функций (overloading)
 * - Переопределение функций (overriding)
 *
 * Как запускать: нажми зелёную стрелку рядом с fun main() внизу файла
 */

// ============================================================================
// 1. ПАРАМЕТРЫ ФУНКЦИЙ
// ============================================================================

/*
 * Задача 1 — Default, named, vararg
 *
 * 1. Напиши функцию formatMessage(text: String, uppercase: Boolean = false, prefix: String = "")
 *    - Если uppercase = true — текст в верхнем регистре
 *    - Если prefix не пустой — добавить перед текстом "prefix: "
 *
 * 2. Напиши функцию joinAll(separator: String, vararg words: String): String
 *    - Соединяет все слова через separator
 *
 * Вызови обе функции разными способами (позиционные, именованные, spread).
 */
fun task1() {
    // TODO: напиши функцию formatMessage и вызови её:
    //   formatMessage("привет")                              -> "привет"
    //   formatMessage("привет", uppercase = true)             -> "ПРИВЕТ"
    //   formatMessage("привет", prefix = "Лог")               -> "Лог: привет"
    //   formatMessage("привет", prefix = "Лог", uppercase = true) -> "Лог: ПРИВЕТ"

    println()
    println("Задача 1")
    println("Функция formatMessage :")
    println()

    fun formatMessage(text: String, uppercase: Boolean = false, prefix: String = "") {
        val result = when {
            uppercase && !prefix.isEmpty() -> "$prefix: ${text.uppercase()}"
            uppercase -> text.uppercase()
            !prefix.isEmpty() -> "$prefix: $text"
            else -> text
        }

        println(result)
    }

    formatMessage("привет")
    formatMessage("привет", uppercase = true)
    formatMessage("привет", prefix = "Лог")
    formatMessage("привет", prefix = "Лог", uppercase = true)

    //  Вариант 2

    println("========= OR ==========")

    fun formatMessage2(text: String, uppercase: Boolean = false, prefix: String = ""): String {
        var result = text
        if (uppercase) result = result.uppercase()
        return if (prefix.isNotEmpty()) "$prefix: $result" else result
    }

    formatMessage2("привет")
    formatMessage2("привет", uppercase = true)
    formatMessage2("привет", prefix = "Лог")
    formatMessage2("привет", prefix = "Лог", uppercase = true)

    // TODO: напиши функцию joinAll и вызови её:
    //   joinAll(" + ", "a", "b", "c")     -> "a + b + c"
    //   val arr = arrayOf("x", "y", "z")
    //   joinAll("-", *arr)                -> "x-y-z"

    println()
    println("Функция joinAll :")
    println()

    fun joinAll(separator: String, vararg words: String): String {
//        return words.joinToString(separator)
        var result = ""

        for ((index, word) in words.withIndex()) {
            if (index > 0) {
                result += separator + word[index]
            } else {
                result = words[index]
            }
        }

        return result
    }

    println(joinAll(" + ", "a", "b", "c"))
    val arr = arrayOf("x", "y", "z")
    println(joinAll("-", *arr))

    // Ожидаемый вывод:
    // привет
    // ПРИВЕТ
    // Лог: привет
    // Лог: ПРИВЕТ
    // a + b + c
    // x-y-z
}

// ============================================================================
// 2. SINGLE-EXPRESSION FUNCTIONS
// ============================================================================

/*
 * Задача 2 — Перепиши в single-expression
 *
 * Перепиши каждую функцию ниже в single-expression form (через =, без фигурных скобок).
 */
fun task2() {
    // TODO: перепиши каждую функцию в single-expression

    println()
    println("Задача 2")
    println("single-expression :")
    println()

    fun isEven(n: Int) = n % 2 == 0

    fun greeting(name: String) = "Привет, $name!"

    fun clamp(value: Int, min: Int, max: Int) = if (value < min) min else if (value > max) max else value

    // После переписывания раскомментируй:
    println(isEven(4)) // true
    println(isEven(7)) // false
    println(greeting("Kotlin")) // Привет, Kotlin!
    println(clamp(15, 0, 10)) // 10
    println(clamp(-5, 0, 10)) // 0
    println(clamp(5, 0, 10)) // 5
}

// ============================================================================
// 3. EXTENSION FUNCTIONS
// ============================================================================

/*
 * Задача 3 — Напиши extension functions
 *
 * 1. String.removeSpaces(): String — удаляет все пробелы из строки
 * 2. List<Int>.secondMax(): Int? — возвращает второе по величине число (или null если элементов < 2)
 * 3. Int.isPrime(): Boolean — проверяет, является ли число простым
 */
fun task3() {
    // TODO: напиши три extension functions

    println()
    println("Задача 3")
    println("extension functions :")
    println()

    //    fun String.removeSpaces() = this.replace(" ", "")
    fun String.removeSpaces() = this.filter { it != ' ' }

    fun List<Int>.secondMax(): Int? {
        val max = this.maxOrNull()

        if (max == null || this.size == 1) return null

        var secondMax = this[0]
        for (currentNum in this) {
            if (currentNum in (secondMax + 1)..<max) secondMax = currentNum
        }

        return secondMax
    }

    //  Вариант с сортировкой

    fun List<Int>.secondMax2(): Int? {
        if (this.size < 2) return null

        return this.sortedByDescending { it }[1]
    }

    fun Int.isPrime(): Boolean {
        if (this <= 1) return false
        for (i in 1..this) {
            if (i != 1 && i != this && this % i == 0) return false
        }

        return true
    }

    // После реализации раскомментируй:
    println("Hello World Kotlin".removeSpaces()) // HelloWorldKotlin
    println(listOf(3, 7, 1, 9, 4).secondMax()) // 7
    println(listOf(5).secondMax()) // null

    println(listOf(3, 7, 1, 9, 4).secondMax2()) // 7
    println(listOf(5).secondMax2()) // null
    println(7.isPrime()) // true
    println(10.isPrime()) // false
    println(2.isPrime()) // true
}

// ============================================================================
// 4. HIGHER-ORDER FUNCTIONS + ЛЯМБДЫ
// ============================================================================

/*
 * Задача 4 — Функция с лямбда-параметром
 *
 * Напиши функцию retry(times: Int, action: () -> Boolean)
 * которая вызывает action до times раз, пока action не вернёт true.
 * Выводит "Попытка N..." перед каждым вызовом.
 * Если удалось — "Успех на попытке N", если нет — "Не удалось за N попыток".
 */

//  Функция retry2 из task4. Дженерик не может быть внутри другой функции

fun <T> retry2(times: Int, action: () -> T?): T? {
    for (currentTry in 1..times) {
        println("Попытка $currentTry...")
        var result = action()
        if (result != null) {
            println("Успех на попытке $currentTry")
            return result
        }
    }

    println("Не удалось за $times попыток")
    return null
}

fun task4() {
    // TODO: напиши функцию retry

    fun retry(times: Int, action: () -> Boolean) {
        var result: Boolean

        for (currentTry in 1..times) {
            println("Попытка $currentTry...")
            result = action()
            if (result) {
                println("Успех на попытке $currentTry")
                return
            }

            if (!result && currentTry == times) println("Не удалось за $currentTry попыток")
        }
    }

    // После реализации раскомментируй:
    var counter = 0
    retry(5) {
        counter++
        counter == 3 // "удача" на третьей попытке
    }

    // Ожидаемый вывод:
    // Попытка 1...
    // Попытка 2...
    // Попытка 3...
    // Успех на попытке 3
}

// ============================================================================
// 5. FUNCTION REFERENCES
// ============================================================================

/*
 * Задача 5 — Замени лямбды на function references
 *
 * Перепиши каждый вызов, заменив лямбду на ссылку на функцию (::).
 */
fun task5() {
    val numbers = listOf(1, -2, 3, -4, 5, -6)
    val words = listOf("kotlin", "java", "python")

    fun isPositive(n: Int) = n > 0

    // TODO: замени лямбды на function references (::)

    println()
    println("Задача 5")
    println("function references :")
    println()

    // Было:
    val positives = numbers.filter(::isPositive)
    val upperWords = words.map(String::uppercase)

    val printed = numbers.forEach(::println)

    // Должно стать (раскомментируй):
    // val positives = numbers.filter(::isPositive)
    // val upperWords = words.map(String::uppercase)
    // numbers.forEach(::println)

    println("Положительные: $positives")
    println("В верхнем регистре: $upperWords")
}

// ============================================================================
// 6. SCOPE FUNCTIONS
// ============================================================================

/*
 * Задача 6 — Выбери правильный scope function
 *
 * Для каждого случая выбери подходящий scope function
 * и перепиши код. Объясни выбор в комментарии.
 */
data class Server(var host: String = "", var port: Int = 0, var isRunning: Boolean = false)

fun task6() {
    // Случай 1: инициализация объекта
    // TODO: перепиши через подходящий scope function
    val server = Server()
    server.host = "localhost"
    server.port = 8080
    server.isRunning = true
    println(server)

    val server2 = Server().apply {
        host = "localhost"
        port = 8080
        isRunning = true
    }

    // Случай 2: null-проверка + действие
    // TODO: перепиши через подходящий scope function
    val input: String? = "Kotlin"
    if (input != null) {
        println("Длина: ${input.length}")
    }

    val input2: String? = "Java"
    input2?.let {
        println("Длина: ${input2.length}")
    }

    // Случай 3: логирование без изменения цепочки
    // TODO: перепиши через подходящий scope function
    val numbers = mutableListOf(3, 1, 4, 1, 5)
    println("До сортировки: $numbers")
    numbers.sort()
    println("После сортировки: $numbers")

    numbers.also {
        println("До сортировки: $it")
    }
    numbers.sort()
    println("После сортировки: $numbers")

    // Ожидаемый вывод:
    // Server(host=localhost, port=8080, isRunning=true)
    // Длина: 6
    // До сортировки: [3, 1, 4, 1, 5]
    // После сортировки: [1, 1, 3, 4, 5]
}

// ============================================================================
// 7. РЕКУРСИЯ + TAILREC
// ============================================================================

/*
 * Задача 7 — Рекурсия и оптимизация через tailrec
 *
 * 1. Напиши рекурсивную функцию power(base: Int, exp: Int): Long
 *    которая вычисляет base в степени exp (exp >= 0).
 *
 * 2. Перепиши её с tailrec, используя аккумулятор.
 *
 * Проверь обе версии на power(2, 10) и power(3, 5).
 */
fun task7() {
    // TODO: напиши обычную рекурсивную версию power

    fun power(base: Int, exp: Int): Long {
        if (exp == 0) return 1
        return base * power(base, exp - 1)
    }

    // TODO: напиши tailrec версию powerTailrec

    tailrec fun powerTailrec(base: Int, exp: Int, acc: Long = 1): Long {
        if (exp == 0) return acc
        return powerTailrec(base, exp - 1, base * acc)
    }

    // После реализации раскомментируй:
    println(power(2, 10)) // 1024
    println(power(3, 5)) // 243
    println(power(3, 1)) // 3
    println(power(3, 0)) // 1
    println(powerTailrec(2, 10)) // 1024
    println(powerTailrec(3, 5)) // 243
}

// ============================================================================
// 8. ЗАМЫКАНИЕ (CLOSURE)
// ============================================================================

/*
 * Задача 8 — Функция-генератор счётчика
 *
 * Напиши функцию makeCounter(start: Int = 0): () -> Int
 * которая возвращает лямбду-счётчик.
 * При каждом вызове лямбда возвращает следующее число (начиная с start).
 *
 * Подсказка: лямбда захватывает var-переменную из внешней функции.
 */
fun task8() {
    // TODO: напиши функцию makeCounter

    fun makeCounter(start: Int = 0): () -> Int {
        var counter = start
        return { counter++ }
    }

    // После реализации раскомментируй:
    val counter = makeCounter()
    println(counter()) // 0
    println(counter()) // 1
    println(counter()) // 2

    val counterFrom10 = makeCounter(10)
    println(counterFrom10()) // 10
    println(counterFrom10()) // 11
}

// ============================================================================
// 9. ПЕРЕГРУЗКА ФУНКЦИЙ (OVERLOADING)
// ============================================================================

/*
 * Задача 9 — Перегрузка функций
 *
 * 1. Напиши перегруженные функции describe:
 *    - describe(value: Int): String — "Число: <value>"
 *    - describe(value: String): String — "Строка: <value> (длина: <length>)"
 *    - describe(value: List<*>): String — "Список из <size> элементов"
 *
 * 2. Напиши функцию format с default-параметрами, которая заменяет
 *    необходимость в перегрузке:
 *    format(value: String, uppercase: Boolean = false, maxLength: Int = Int.MAX_VALUE): String
 *    - Если uppercase = true — перевести в верхний регистр
 *    - Если maxLength < длины строки — обрезать до maxLength и добавить "..."
 */
fun task9() {
    // TODO: напиши три перегруженные функции describe

    fun describe(value: Int): String = "Число: $value"

    fun describe(value: String): String = "Строка: <$value> (длина: <${value.length}>)"

    fun describe(value: List<*>): String = "Список из <${value.size}> элементов"

    // После реализации раскомментируй:
    // println(describe(42))                        // Число: 42
    // println(describe("Kotlin"))                  // Строка: Kotlin (длина: 6)
    // println(describe(listOf(1, 2, 3)))           // Список из 3 элементов

    // TODO: напиши функцию format с default-параметрами

    fun format(value: String, uppercase: Boolean = false, maxLength: Int = Int.MAX_VALUE): String {
        val result = if (uppercase) value.uppercase() else value
        return if (maxLength < value.length) result.take(maxLength) + "..." else result
    }

    // После реализации раскомментируй:
    // println(format("hello"))                     // hello
    // println(format("hello", uppercase = true))   // HELLO
    // println(format("hello world", maxLength = 5)) // hello...
}

// ============================================================================
// 10. ПЕРЕОПРЕДЕЛЕНИЕ ФУНКЦИЙ (OVERRIDING)
// ============================================================================

/*
 * Задача 10 — Переопределение функций
 *
 * 1. Создай open class Shape с:
 *    - open fun area(): Double = 0.0
 *    - open fun describe(): String = "Фигура"
 *
 * 2. Создай класс Circle(val radius: Double) : Shape()
 *    - Переопредели area() — π * r²
 *    - Переопредели describe() — используй super.describe() + ": Круг с радиусом <radius>"
 *
 * 3. Создай класс Rectangle(val width: Double, val height: Double) : Shape()
 *    - Переопредели area() — width * height
 *    - Переопредели describe() — аналогично с super
 *
 * 4. Напиши функцию printShapeInfo(shape: Shape), которая выводит
 *    describe() и area(). Вызови с Circle и Rectangle — продемонстрируй полиморфизм.
 */
fun task10() {
    // TODO: создай классы Shape, Circle, Rectangle

    // TODO: напиши функцию printShapeInfo(shape: Shape)

    open class Shape {
        open fun area(): Double = 0.0

        open fun describe(): String = "Фигура"
    }

    class Circle(val radius: Double) : Shape() {
        override fun area(): Double = Math.PI * radius * radius

        override fun describe(): String = "${super.describe()}: Круг с радиусом $radius"
    }

    class Rectangle(val width: Double, val height: Double) : Shape() {
        override fun area(): Double = width * height

        override fun describe(): String = "${super.describe()}: Прямоугольник с шириной $width и высотой $height>"
    }

    fun printShapeInfo(shape: Shape) {
        println(shape.describe())
        println(shape.area())
    }

    // После реализации раскомментируй:
    // val circle = Circle(5.0)
    // val rectangle = Rectangle(3.0, 4.0)
    //
    // printShapeInfo(circle)
    // printShapeInfo(rectangle)

    // Ожидаемый вывод:
    // Фигура: Круг с радиусом 5.0
    // Площадь: 78.53981633974483
    // Фигура: Прямоугольник 3.0 x 4.0
    // Площадь: 12.0
}

// ============================================================================
// MAIN — запуск всех задач
// ============================================================================

fun main() {
    println("=== Запусти нужную задачу, раскомментировав её ===")

    // ПАРАМЕТРЫ ФУНКЦИЙ
//    task1()

    // SINGLE-EXPRESSION FUNCTIONS
//    task2()

    // EXTENSION FUNCTIONS
//    task3()

    // HIGHER-ORDER FUNCTIONS + ЛЯМБДЫ
//    task4()

    // FUNCTION REFERENCES
//    task5()

    // SCOPE FUNCTIONS
    // task6()

    // РЕКУРСИЯ + TAILREC
//    task7()

    // ЗАМЫКАНИЕ (CLOSURE)
    task8()

    // ПЕРЕГРУЗКА ФУНКЦИЙ (OVERLOADING)
    // task9()

    // ПЕРЕОПРЕДЕЛЕНИЕ ФУНКЦИЙ (OVERRIDING)
    // task10()
}
