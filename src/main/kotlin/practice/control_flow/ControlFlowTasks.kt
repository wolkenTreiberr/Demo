package practice.control_flow

import kotlin.math.pow

/**
 * Практические задачи по управляющим конструкциям Kotlin
 *
 * Темы:
 * - if/else как expression
 * - when (с аргументом и без)
 * - Smart cast
 * - Type inference
 * - Антипаттерны
 *
 * Как запускать: нажми зелёную стрелку рядом с fun main() внизу файла
 */

// ============================================================================
// 1. IF-EXPRESSION
// ============================================================================

/**
 * Задача 1 — If как выражение
 *
 * Перепишите следующий код с использованием if-expression (одной строкой):
 *
 * var result: String
 * if (temperature > 30) {
 *     result = "Жарко"
 * } else {
 *     result = "Нормально"
 * }
 */
fun task1() {
    val temperature = 35

    // TODO: замени код ниже на if-expression (одна строка: val result = ...)
//    var result: String
//    if (temperature > 30) {
//        result = "Жарко"
//    } else {
//        result = "Нормально"
//    }


    var result = if(temperature > 30) "Жарко" else "Нормально"
    println(result)

    // Ожидаемый вывод:
    // Жарко
}


// ============================================================================
// 2. WHEN
// ============================================================================

/**
 * Задача 2 — When вместо if-else if
 *
 * Перепишите цепочку if-else if через when-expression:
 */
fun task2() {
    val code = 404

    // TODO: замени код ниже на when-expression
//    val message: String
//    if (code == 200) {
//        message = "OK"
//    } else if (code == 404) {
//        message = "Not Found"
//    } else if (code == 500) {
//        message = "Server Error"
//    } else {
//        message = "Unknown"
//    }

    val message = when(code) {
        202 -> "OK"
        404 -> "Not Found"
        500 -> "Server Error"
        else -> "Unknown"
    }

    println(message)

    // Ожидаемый вывод:
    // Not Found
}


// ============================================================================
// 3. WHEN + SMART CAST
// ============================================================================

/**
 * Задача 3 — Smart cast через when
 *
 * Напишите функцию describe(input: Any): String
 * которая через when возвращает:
 * - для Int — "Целое число: <значение>"
 * - для String — "Строка длиной <длина>"
 * - для Boolean — "Логическое: <значение>"
 * - для остального — "Неизвестный тип"
 *
 * Используйте smart cast для доступа к свойствам.
 */

// TODO: напиши функцию describe(input: Any): String

fun task3() {
    fun describe(input: Any): String = when(input) {
        is Int -> "Целое число: $input"
        is String -> "Строка длиной ${input.length}"
        is Boolean -> "Логическое: $input"
        else -> "Неизвестный тип"
    }
    // После реализации describe() раскомментируй:
    // println(describe(42))
    // println(describe("Kotlin"))
    // println(describe(true))
    // println(describe(3.14))

    // Ожидаемый вывод:
    // Целое число: 42
    // Строка длиной 6
    // Логическое: true
    // Неизвестный тип
}


// ============================================================================
// 4. SMART CAST — ОГРАНИЧЕНИЯ
// ============================================================================

/**
 * Задача 4 — Проблема smart cast с var свойством
 *
 * Что не так с этим кодом? Исправьте, чтобы он компилировался.
 *
 * Подсказка: smart cast не работает для var свойств класса.
 * Скопируй value в локальную val-переменную.
 */
class Container(var value: Any?) {
    fun printIfString() {
        // TODO: этот код не скомпилируется — исправь его
        val value2 = value
         if (value2 is String) {
             println(value2.length)
         }
    }
}

fun task4() {
    val container = Container("Hello Kotlin")
    container.printIfString()

    // Ожидаемый вывод:
    // 12
}


// ============================================================================
// 5. WHEN С ДИАПАЗОНАМИ
// ============================================================================

/**
 * Задача 5 — Классификация оценки через when с диапазонами
 *
 * Напишите функцию classify(score: Int): String
 * которая через when с диапазонами возвращает:
 * - 90..100 — "Отлично"
 * - 75..89 — "Хорошо"
 * - 60..74 — "Удовлетворительно"
 * - 0..59 — "Неудовлетворительно"
 * - всё остальное — "Ошибка: некорректная оценка"
 */

// TODO: напиши функцию classify(score: Int): String

fun task5() {
    fun classify(score: Int): String = when(score) {
        in 90..100 -> "Отлично"
        in 75..89 -> "Хорошо"
        in 60..74 -> "Удовлетворительно"
        in 0..59 -> "Неудовлетворительно"
        else -> "Ошибка: некорректная оценка"
    }
    // После реализации classify() раскомментируй:
    // println(classify(95))
    // println(classify(82))
    // println(classify(65))
    // println(classify(40))
    // println(classify(-5))

    // Ожидаемый вывод:
    // Отлично
    // Хорошо
    // Удовлетворительно
    // Неудовлетворительно
    // Ошибка: некорректная оценка
}


// ============================================================================
// 6. EXHAUSTIVE WHEN + SEALED CLASS
// ============================================================================

/**
 * Задача 6 — Площадь фигуры через exhaustive when
 *
 * Дана sealed class Shape. Напишите функцию area(shape: Shape): Double
 * используя exhaustive when.
 *
 * Вопрос: нужен ли else? Почему?
 */
sealed class Shape
class Circle(val radius: Double) : Shape()
class Rectangle(val width: Double, val height: Double) : Shape()
class Triangle(val base: Double, val height: Double) : Shape()

// TODO: напиши функцию area(shape: Shape): Double

fun task6() {
    fun area(shape: Shape): Double = when(shape) {
        is Circle -> 3.14 * shape.radius.pow(2)
        is Rectangle -> shape.width * shape.height
        is Triangle -> (shape.base * shape.height) / 2
    }
    // После реализации area() раскомментируй:
//     println("Круг: ${area(Circle(5.0))}")
//     println("Прямоугольник: ${area(Rectangle(4.0, 6.0))}")
//     println("Треугольник: ${area(Triangle(3.0, 8.0))}")

    // Ожидаемый вывод:
    // Круг: 78.53981633974483
    // Прямоугольник: 24.0
    // Треугольник: 12.0
}


// ============================================================================
// 7. АНТИПАТТЕРНЫ
// ============================================================================

/**
 * Задача 7 — Найди и исправь антипаттерны
 *
 * В функции processData() есть несколько антипаттернов:
 * 1. Цепочка if-else вместо when
 * 2. Избыточный каст (as) после is-проверки
 * 3. Использование var вместо val с expression
 *
 * Перепиши функцию с использованием when, smart cast и expression.
 */
fun processData(data: Any): String {
    // TODO: перепиши этот код — исправь все антипаттерны
//    val result: String
//    if (data is String) {
//        result = (data as String).uppercase()
//    } else if (data is Int) {
//        result = (data as Int).toString()
//    } else if (data is Boolean) {
//        if (data as Boolean) {
//            result = "true"
//        } else {
//            result = "false"
//        }
//    } else {
//        result = "unknown"
//    }
//    return result

    val result = when(data) {
        is String -> data.uppercase()
        is Int -> data.toString()
        is Boolean -> "true"
        else -> "unknown"
    }
    return result
}

fun task7() {
    println(processData("hello"))
    println(processData(42))
    println(processData(true))
    println(processData(3.14))

    // Ожидаемый вывод:
    // HELLO
    // 42
    // true
    // unknown
}


// ============================================================================
// 8. TYPE INFERENCE
// ============================================================================

/**
 * Задача 8 — Явные типы: нужны или нет?
 *
 * Для каждого объявления определи:
 * - Нужен ли явный тип?
 * - Почему?
 *
 * Раскомментируй код и добавь комментарии с ответами.
 */
fun task8() {
    // TODO: для каждого объявления напиши комментарий —
    //       нужен ли явный тип и почему

    // val a = 42
    // val b: List<String>
    // val c = emptyList<Int>()
    // fun d() = "hello"
    // fun e(): Int { return 1 + 2 }
    // val f = mapOf("key" to 1)

    // Ответь в комментариях:
    // a — ?
    // b — ?
    // c — ?
    // d — ?
    // e — ?
    // f — ?
}


// ============================================================================
// MAIN — запуск всех задач
// ============================================================================

fun main() {
    println("=== Запусти нужную задачу, раскомментировав её ===")

    // IF-EXPRESSION
    // task1()

    // WHEN
    // task2()

    // WHEN + SMART CAST
    // task3()

    // SMART CAST — ОГРАНИЧЕНИЯ
    // task4()

    // WHEN С ДИАПАЗОНАМИ
    // task5()

    // EXHAUSTIVE WHEN + SEALED CLASS
//     task6()

    // АНТИПАТТЕРНЫ
//     task7()

    // TYPE INFERENCE
    // task8()
}
