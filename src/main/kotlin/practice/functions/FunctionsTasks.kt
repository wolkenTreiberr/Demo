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

    // TODO: напиши функцию joinAll и вызови её:
    //   joinAll(" + ", "a", "b", "c")     -> "a + b + c"
    //   val arr = arrayOf("x", "y", "z")
    //   joinAll("-", *arr)                -> "x-y-z"

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

    fun isEven(n: Int): Boolean {
        return n % 2 == 0
    }

    fun greeting(name: String): String {
        return "Привет, $name!"
    }

    fun clamp(value: Int, min: Int, max: Int): Int {
        return if (value < min) min else if (value > max) max else value
    }

    // После переписывания раскомментируй:
    // println(isEven(4))               // true
    // println(isEven(7))               // false
    // println(greeting("Kotlin"))      // Привет, Kotlin!
    // println(clamp(15, 0, 10))        // 10
    // println(clamp(-5, 0, 10))        // 0
    // println(clamp(5, 0, 10))         // 5
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

    // После реализации раскомментируй:
    // println("Hello World Kotlin".removeSpaces())     // HelloWorldKotlin
    // println(listOf(3, 7, 1, 9, 4).secondMax())       // 7
    // println(listOf(5).secondMax())                    // null
    // println(7.isPrime())                              // true
    // println(10.isPrime())                             // false
    // println(2.isPrime())                              // true
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
fun task4() {
    // TODO: напиши функцию retry

    // После реализации раскомментируй:
    // var counter = 0
    // retry(5) {
    //     counter++
    //     counter == 3 // "удача" на третьей попытке
    // }

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

    // Было:
    val positives = numbers.filter { isPositive(it) }
    val upperWords = words.map { it.uppercase() }
    val printed = numbers.forEach { println(it) }

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

    // Случай 2: null-проверка + действие
    // TODO: перепиши через подходящий scope function
    val input: String? = "Kotlin"
    if (input != null) {
        println("Длина: ${input.length}")
    }

    // Случай 3: логирование без изменения цепочки
    // TODO: перепиши через подходящий scope function
    val numbers = mutableListOf(3, 1, 4, 1, 5)
    println("До сортировки: $numbers")
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

    // TODO: напиши tailrec версию powerTailrec

    // После реализации раскомментируй:
    // println(power(2, 10))         // 1024
    // println(power(3, 5))          // 243
    // println(powerTailrec(2, 10))  // 1024
    // println(powerTailrec(3, 5))   // 243
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

    // После реализации раскомментируй:
    // val counter = makeCounter()
    // println(counter()) // 0
    // println(counter()) // 1
    // println(counter()) // 2

    // val counterFrom10 = makeCounter(10)
    // println(counterFrom10()) // 10
    // println(counterFrom10()) // 11
}

// ============================================================================
// MAIN — запуск всех задач
// ============================================================================

fun main() {
    println("=== Запусти нужную задачу, раскомментировав её ===")

    // ПАРАМЕТРЫ ФУНКЦИЙ
    // task1()

    // SINGLE-EXPRESSION FUNCTIONS
    // task2()

    // EXTENSION FUNCTIONS
    // task3()

    // HIGHER-ORDER FUNCTIONS + ЛЯМБДЫ
    // task4()

    // FUNCTION REFERENCES
    // task5()

    // SCOPE FUNCTIONS
    // task6()

    // РЕКУРСИЯ + TAILREC
    // task7()

    // ЗАМЫКАНИЕ (CLOSURE)
    // task8()
}
