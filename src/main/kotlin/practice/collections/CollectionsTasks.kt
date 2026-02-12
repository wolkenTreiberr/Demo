package practice.collections

/*
 * Практические задачи по коллекциям Kotlin
 *
 * Структура:
 * - List (Read-only) / MutableList
 * - Set (Read-only) / MutableSet
 * - Map (Read-only) / MutableMap
 *
 * Как запускать: нажми зелёную стрелку рядом с fun main() внизу файла
 */

// ============================================================================
// 1. LIST (Read-only)
// ============================================================================

/**
 * Задача 1.1 — Фильтрация заказов
 *
 * Ты получил список заказов из API. Нужно:
 * 1. Отфильтровать только оплаченные заказы (isPaid = true)
 * 2. Отсортировать по сумме (по убыванию)
 * 3. Вывести топ-3 заказа
 */
data class Order(val id: Int, val amount: Double, val isPaid: Boolean)

fun task11() {
    val orders = listOf(
        Order(1, 1500.0, true),
        Order(2, 300.0, false),
        Order(3, 8900.0, true),
        Order(4, 2200.0, true),
        Order(5, 500.0, false),
        Order(6, 4100.0, true),
    )

    // TODO: твой код здесь
    //  Для больших объемов
//    val result = orders.asSequence()
//        .filter { it.isPaid }
//        .sortedByDescending { it.amount }
//        .take(3)
//        .toList()

    val result = orders
        .filter { it.isPaid }
        .sortedByDescending { it.amount }
        .take(3)

    println(result)

    // Ожидаемый вывод:
    // Топ-3 оплаченных заказа:
    // Order(id=3, amount=8900.0, isPaid=true)
    // Order(id=6, amount=4100.0, isPaid=true)
    // Order(id=4, amount=2200.0, isPaid=true)
}

/**
 * Задача 1.2 — Преобразование данных пользователей
 *
 * Из базы пришёл список пользователей. Нужно:
 * 1. Получить только совершеннолетних (age >= 18)
 * 2. Преобразовать в строки формата "Имя (возраст лет)"
 * 3. Вывести список
 */
data class User(val name: String, val age: Int)

fun task12() {
    val users = listOf(
        User("Алексей", 25),
        User("Мария", 17),
        User("Иван", 32),
        User("Анна", 16),
        User("Дмитрий", 19),
    )

    // TODO: твой код здесь

    val adultUsersList = users
        .filter { it.age >= 18 }
        .map { "${it.name} (${it.age})" }

    println(adultUsersList)

    // Ожидаемый вывод:
    // Совершеннолетние пользователи:
    // Алексей (25 лет)
    // Иван (32 лет)
    // Дмитрий (19 лет)
}

/**
 * Задача 1.3 — Статистика по продуктам
 *
 * Нужно посчитать статистику по списку товаров:
 * 1. Общее количество товаров
 * 2. Средняя цена
 * 3. Самый дорогой товар
 * 4. Есть ли товары дешевле 1000?
 */
data class Product(val name: String, val price: Double)

fun task13() {
    val products = listOf(
        Product("Ноутбук", 75000.0),
        Product("Мышь", 1500.0),
        Product("Клавиатура", 3000.0),
        Product("Монитор", 25000.0),
        Product("Наушники", 800.0),
    )

    // TODO: твой код здесь

    val productNumber = products.size
//    val averagePrice = products.sumOf { it.price } / productNumber
    val averagePrice = products.map { it.price }
        .average()
    val maxPrice = products.maxByOrNull { it.price }
    val isPriceLessThan1000 = products.any { it.price < 1000 }

    // Ожидаемый вывод:
    // Всего товаров: 5
    // Средняя цена: 21060.0
    // Самый дорогой: Ноутбук (75000.0)
    // Есть товары дешевле 1000: true
}

// ============================================================================
// 2. MUTABLELIST
// ============================================================================

/**
 * Задача 2.1 — Корзина покупок
 *
 * Реализуй простую корзину покупок:
 * 1. Добавь 3 товара
 * 2. Удали второй товар
 * 3. Добавь ещё один товар в начало списка
 * 4. Выведи итоговую корзину с номерами позиций
 */
fun task21() {
    val cart = mutableListOf<String>()
    val products = listOf("Молоко", "Хлеб", "Сыр")

    // TODO: твой код здесь
    // Добавь: "Молоко", "Хлеб", "Сыр"
    // Удали: "Хлеб"
    // Добавь в начало: "Яйца"

    cart.addAll(products)
//    cart.removeAt(1)
    cart.remove("Хлеб")
    cart.addFirst("Яйца")
    cart.forEachIndexed { index, product -> println("${index + 1}: $product") }

    // Ожидаемый вывод:
    // Корзина:
    // 1. Яйца
    // 2. Молоко
    // 3. Сыр
}

/**
 * Задача 2.2 — Очередь задач с приоритетом
 *
 * Есть очередь задач. Нужно:
 * 1. Добавить несколько задач
 * 2. Выполнить (удалить) первую задачу
 * 3. Добавить срочную задачу в начало
 * 4. Заменить задачу с низким приоритетом
 */
data class Task(val id: Int, val title: String, val priority: String)

fun task22() {
    val taskQueue = mutableListOf(
        Task(1, "Ревью кода", "medium"),
        Task(2, "Написать тесты", "low"),
        Task(3, "Фикс бага", "high"),
    )

    // TODO:
    // 1. Удали первую задачу (выполнена)
    // 2. Добавь в начало: Task(4, "Срочный деплой", "critical")
    // 3. Замени задачу с priority="low" на Task(6, "Документация API", "medium")

    taskQueue.add(Task(4, "Код ревью", "medium"))
    taskQueue.removeAt(0)
    taskQueue.addFirst(Task(5, "Срочный деплой", "critical"))
    val index = taskQueue.indexOfFirst { it.priority == "low" }
    if (index != -1) {
        taskQueue[index] = Task(6, "Документация API", "medium")
    }

    // Ожидаемый вывод:
    // Очередь задач:
    // [CRITICAL] Срочный деплой
    // [HIGH] Фикс бага
    // [MEDIUM] Документация API
}

// ============================================================================
// 3. SET (Read-only)
// ============================================================================

/**
 * Задача 3.1 — Проверка прав доступа
 *
 * У пользователя есть набор ролей. Проверь:
 * 1. Есть ли роль ADMIN
 * 2. Есть ли хотя бы одна из ролей: MODERATOR или ADMIN
 * 3. Сколько общих ролей с другим пользователем
 */
fun task31() {
    val userRoles = setOf("USER", "EDITOR", "MODERATOR")
    val requiredRoles = setOf("ADMIN", "MODERATOR")
    val otherUserRoles = setOf("USER", "VIEWER")

    // TODO: твой код здесь

    val isAdminRoleExists = "ADMIN" in userRoles
    val isAdminRoleExists2 = userRoles.contains("ADMIN")
    val isAdminRoleExists3 = userRoles.any { it == "ADMIN" }

    val isAdminOrModeratorRoleExists = userRoles.any { it in requiredRoles }

    val bothHaveRoles = userRoles.count { it in otherUserRoles }
    val bothHaveRoles2 = userRoles.intersect(requiredRoles).size

    // Ожидаемый вывод:
    // Является админом: false
    // Имеет права модерации: true
    // Общих ролей с другим пользователем: 1
}

/**
 * Задача 3.2 — Дедупликация тегов
 *
 * Получены теги из разных источников. Нужно:
 * 1. Объединить все теги без дубликатов
 * 2. Найти теги, которые есть в обоих источниках
 * 3. Найти теги, уникальные для первого источника
 */
fun task32() {
    val blogTags = setOf("kotlin", "android", "mobile", "jetpack")
    val articleTags = setOf("kotlin", "backend", "spring", "android")

    // TODO: твой код здесь

    val uniqueTags = blogTags union articleTags
    val uniqueTags2 = blogTags + articleTags
    val uniqueTags3 = buildSet {
        addAll(blogTags)
        addAll(articleTags)
    }

    val sameTags = uniqueTags.intersect(articleTags)
    val sameTags2 = blogTags.filter { it in articleTags }.toSet()

    val uniqueBlogTags2 = blogTags subtract uniqueTags
    val uniqueBlogTags = blogTags - articleTags

    // Ожидаемый вывод:
    // Все уникальные теги: [kotlin, android, mobile, jetpack, backend, spring]
    // Общие теги: [kotlin, android]
    // Только в блоге: [mobile, jetpack]
}

// ============================================================================
// 4. MUTABLESET
// ============================================================================

/**
 * Задача 4.1 — Онлайн-пользователи
 *
 * Симуляция онлайн-статуса пользователей:
 * 1. Пользователи заходят и выходят
 * 2. Выведи текущий список онлайн
 * 3. Проверь, онлайн ли конкретный пользователь
 */
fun task41() {
    val onlineUsers = mutableSetOf<String>()

    // TODO:
    // 1. Зашли: "alice", "bob", "charlie"
    // 2. Вышел: "bob"
    // 3. Зашли: "alice" (повторно!), "diana"
    // 4. Проверь, онлайн ли "bob" и "diana"

    val usersList = listOf("alice", "bob", "charlie")
    onlineUsers.addAll(usersList)
    println("Зашли: ${usersList.joinToString(", ")}")

    onlineUsers.remove("bob")
    println("Вышел: bob")

    onlineUsers.addAll(listOf("alice", "diana"))
    println("Зашли: 'alice'(повторно), 'diana'")

    println("'bob' онлайн - ${onlineUsers.contains("bob")}")
    println("'bob' онлайн - ${"bob" in onlineUsers}")
    println("'diana' онлайн - ${onlineUsers.contains("diana")}")
    println("'diana' онлайн - ${"diana" in onlineUsers}")

    println("Онлайн: ${onlineUsers.size} пользователь(ля)")

    // Ожидаемый вывод:
    // Онлайн сейчас: [alice, charlie, diana]
    // bob онлайн: false
    // diana онлайн: true
}

/**
 * Задача 4.2 — Теги статьи
 *
 * Редактирование тегов статьи:
 * 1. Начальные теги статьи
 * 2. Добавить новые теги (некоторые уже есть)
 * 3. Удалить устаревшие теги
 * 4. Вывести финальный список
 */
fun task42() {
    val articleTags = mutableSetOf("java", "programming", "tutorial", "beginner")

    // TODO:
    // 1. Добавь теги: "kotlin", "programming", "android"
    // 2. Удали теги: "java", "beginner"
    // 3. Выведи результат

    val newTags = listOf("kotlin", "programming", "android")
    articleTags.addAll(newTags)
    articleTags.removeAll(listOf("java", "beginner"))
    println("Теги статьи: $articleTags")
    println("Количество тегов: ${articleTags.size}")

    // Ожидаемый вывод:
    // Теги статьи: [programming, tutorial, kotlin, android]
    // Количество тегов: 4
}

// ============================================================================
// 5. MAP (Read-only)
// ============================================================================

/**
 * Задача 5.1 — Конфигурация приложения
 *
 * Работа с конфигом приложения:
 * 1. Получить значение по ключу (с дефолтом если нет)
 * 2. Проверить наличие настройки
 * 3. Вывести все ключи
 */
fun task51() {
    val config = mapOf(
        "app.name" to "MyApp",
        "app.version" to "1.0.0",
        "db.host" to "localhost",
        "db.port" to "5432",
    )

    // TODO:
    // 1. Получи "app.name"
    // 2. Получи "app.debug" с дефолтом "false"
    // 3. Проверь, есть ли "db.password"
    // 4. Выведи все ключи конфига

    println("Имя приложения: ${config["app.name"]}")
    println("Имя приложения: ${config["app.name"]}")
    println("Режим отладки: ${config["app.debug"] ?: "false"}")
    println("Режим отладки: ${config["app.debug"] ?: "false"}")
    println("Пароль БД задан: ${config.contains("db.password")}")
    println("Пароль БД задан: ${"db.password" in config}")
    println("Все настройки: ${config.keys}")

    // Ожидаемый вывод:
    // Имя приложения: MyApp
    // Режим отладки: false
    // Пароль БД задан: false
    // Все настройки: [app.name, app.version, db.host, db.port]
}

/**
 * Задача 5.2 — Преобразование списка в Map
 *
 * Преобразуй список сотрудников в Map для быстрого поиска:
 * 1. Map по ID сотрудника
 * 2. Map по отделу (группировка)
 */
data class Employee(val id: Int, val name: String, val department: String)

fun task52() {
    val employees = listOf(
        Employee(1, "Иван", "IT"),
        Employee(2, "Мария", "HR"),
        Employee(3, "Пётр", "IT"),
        Employee(4, "Анна", "HR"),
        Employee(5, "Сергей", "Sales"),
    )

    // TODO: твой код здесь

    val employeeMapById = employees.associateBy({ it.id }, { it.name })
    val employeeMapByDep = employees.groupBy({ it.department }, { it.name })

    println("Сотрудник с ID=3: ${employeeMapById[3]}")
    println("Сотрудники IT отдела: ${employeeMapByDep["IT"]}")
    println("Количество в HR: ${employeeMapByDep["HR"]?.size}")

    // Ожидаемый вывод:
    // Сотрудник с ID=3: Пётр
    // Сотрудники IT отдела: [Иван, Пётр]
    // Количество в HR: 2
}

/**
 * Задача 5.3 — Словарь переводов
 *
 * Простой переводчик слов:
 * 1. Переведи несколько слов
 * 2. Обработай отсутствующий перевод
 */
fun task53() {
    val dictionary = mapOf(
        "hello" to "привет",
        "world" to "мир",
        "kotlin" to "котлин",
        "code" to "код",
    )

    val wordsToTranslate = listOf("hello", "world", "java", "code")

    // TODO: переведи каждое слово, если перевода нет — выведи "[нет перевода]"

    wordsToTranslate.forEach {
        println("$it -> ${dictionary[it] ?: "[нет перевода]"}")
    }

    // Ожидаемый вывод:
    // hello -> привет
    // world -> мир
    // java -> [нет перевода]
    // code -> код
}

// ============================================================================
// 6. MUTABLEMAP
// ============================================================================

/**
 * Задача 6.1 — Счётчик слов
 *
 * Подсчитай частоту слов в тексте
 */
fun task61() {
    val words = listOf("kotlin", "java", "kotlin", "python", "kotlin", "java")
//    val wordCount = mutableMapOf<String, Int>()

    // TODO: подсчитай количество каждого слова

//    words.forEach {
//        wordCount[it] = wordCount.getOrDefault(it, 0) + 1
//    }

    val wordCount = words.groupingBy { it }.eachCount()

    println("Частота слов:")
    wordCount.forEach { println("${it.key} : ${it.value}") }

    // Ожидаемый вывод:
    // Частота слов:
    // kotlin: 3
    // java: 2
    // python: 1
}

/**
 * Задача 6.2 — Кэш пользователей
 *
 * Реализуй простой кэш:
 * 1. Добавление в кэш
 * 2. Получение из кэша
 * 3. Обновление данных
 * 4. Удаление из кэша
 */
data class UserProfile(val id: Int, val name: String, val email: String)

fun task62() {
    val userCache = mutableMapOf<Int, UserProfile>()

    // TODO:
    // 1. Добавь пользователей: (1, "Alice", "alice@mail.com"), (2, "Bob", "bob@mail.com")
    // 2. Получи пользователя с id=1
    // 3. Обнови email пользователя с id=2 на "bob.new@mail.com"
    // 4. Удали пользователя с id=1
    // 5. Выведи финальное состояние кэша

    val alice = UserProfile(1, "Alice", "alice@mail.com")
    val bob = UserProfile(2, "Bob", "bob@mail.com")

    userCache[alice.id] = alice
    userCache[bob.id] = bob

    val user1 = userCache[1]

    println("Пользователь 1: ${user1?.name} (${user1?.email})")

    userCache[2] = userCache[2]!!.copy(email = "bob.new@mail.com")
    userCache.remove(1)

    println("После обновления и удаления:")
    println("Кэш: $userCache")

    // Ожидаемый вывод:
    // Пользователь 1: Alice (alice@mail.com)
    // После обновления и удаления:
    // Кэш: {2=UserProfile(id=2, name=Bob, email=bob.new@mail.com)}
}

/**
 * Задача 6.3 — Группировка и агрегация
 *
 * Подсчитай сумму продаж по категориям
 */
data class Sale(val category: String, val amount: Double)

fun task63() {
    val sales = listOf(
        Sale("Electronics", 15000.0),
        Sale("Books", 500.0),
        Sale("Electronics", 8000.0),
        Sale("Clothing", 3000.0),
        Sale("Books", 1200.0),
        Sale("Electronics", 22000.0),
    )

    val salesByCategory = mutableMapOf<String, Double>()

    // TODO: подсчитай сумму продаж по каждой категории

    val salesByCategory2 = sales.groupBy { it.category }
        .mapValues { (_, items) -> items.sumOf { it.amount } }
    println(salesByCategory2)

//    sales.forEach {
//        salesByCategory[it.category] = salesByCategory.getOrDefault(it.category, 0.0) + it.amount
//    }

    println("Продажи по категориям:")
    salesByCategory2.forEach {
        println("${it.key}: ${it.value}")
    }

    // Ожидаемый вывод:
    // Продажи по категориям:
    // Electronics: 45000.0
    // Books: 1700.0
    // Clothing: 3000.0
}

// ============================================================================
// 7. ФУНКЦИОНАЛЬНЫЕ ОПЕРАЦИИ (flatMap, reduce, fold, zip)
// ============================================================================

/**
 * Задача 7.1 — flatMap: Все технологии проектов
 *
 * Есть список проектов, каждый содержит список используемых технологий.
 * 1. Получи плоский список ВСЕХ технологий (с повторами)
 * 2. Получи список УНИКАЛЬНЫХ технологий
 * 3. Найди технологии, которые используются более чем в одном проекте
 */
data class Project(val name: String, val technologies: List<String>)

fun task71() {
    val projects = listOf(
        Project("Backend API", listOf("Kotlin", "Spring", "PostgreSQL", "Redis")),
        Project("Mobile App", listOf("Kotlin", "Android", "Room")),
        Project("Web Frontend", listOf("TypeScript", "React", "Redis")),
        Project("Data Pipeline", listOf("Kotlin", "Kafka", "PostgreSQL")),
    )

    val allTechnologies = projects.flatMap { it.technologies }
    val noDuplicates = allTechnologies.distinct()
    // or
    val noDuplicates2 = allTechnologies.toSet().toList()
    val commonTechnologies = noDuplicates
        .filter { technology -> projects.count { technology in it.technologies } > 1 }

//    val commonTechnologies2 = allTechnologies
//        .groupBy { it }
//        .filter { (_, occurrences) -> occurrences.size > 1 }
//        .keys
//        .toList()

    println(commonTechnologies)

    // TODO:
    // 1. flatMap — получи все технологии (с повторами)
    // 2. Из результата — уникальные технологии
    // 3. Найди технологии, которые встречаются в 2+ проектах

    // Ожидаемый вывод:
    // Все технологии: [Kotlin, Spring, PostgreSQL, Redis, Kotlin, Android, Room, TypeScript, React, Redis, Kotlin, Kafka, PostgreSQL]
    // Уникальные: [Kotlin, Spring, PostgreSQL, Redis, Android, Room, TypeScript, React, Kafka]
    // В нескольких проектах: [Kotlin, PostgreSQL, Redis]
}

/**
 * Задача 7.2 — reduce: Агрегация без начального значения
 *
 * 1. Найди самый длинный город из списка через reduce
 * 2. Склей все города в строку через " -> " (маршрут) через reduce
 * 3. Найди суммарное население через reduce
 */
data class City(val name: String, val population: Int)

fun task72() {
    val cities = listOf(
        City("Москва", 13_000_000),
        City("Санкт-Петербург", 5_600_000),
        City("Новосибирск", 1_600_000),
        City("Екатеринбург", 1_500_000),
    )

    // TODO:
    // 1. reduce — найди город с наибольшим населением
    // 2. reduce на именах — склей в маршрут "Москва -> Санкт-Петербург -> ..."
    // 3. reduce — суммарное население (map + reduce)

    val largestCity = cities.reduce { acc, city ->
        println(acc)
        if (city.population > acc.population) city else acc
    }

    val route = cities
        .map { it.name }
        .reduce { acc, city -> "$acc -> $city" }

    val commonPopulation = cities
        .map { it.population }
        .reduce { acc, population -> acc + population }

    // Ожидаемый вывод:
    // Крупнейший город: Москва (13000000)
    // Маршрут: Москва -> Санкт-Петербург -> Новосибирск -> Екатеринбург
    // Общее население: 21700000
}

/**
 * Задача 7.3 — fold: Агрегация с начальным значением
 *
 * Банковский счёт: начальный баланс + список транзакций.
 * 1. Посчитай финальный баланс через fold
 * 2. Посчитай отдельно сумму доходов и расходов через fold (в Pair)
 * 3. Сформируй строку-выписку через fold
 */
data class Transaction(val description: String, val amount: Double)

fun task73() {
    val initialBalance = 10_000.0
    val transactions = listOf(
        Transaction("Зарплата", 50_000.0),
        Transaction("Аренда", -15_000.0),
        Transaction("Продукты", -5_500.0),
        Transaction("Фриланс", 12_000.0),
        Transaction("Кафе", -1_200.0),
    )

    // TODO:
    // 1. fold с начальным значением initialBalance — посчитай финальный баланс
    // 2. fold в Pair(доходы, расходы) — начальное Pair(0.0, 0.0)
    // 3. fold в строку — выписка вида "Зарплата: +50000.0 | Аренда: -15000.0 | ..."

    val totalBalance = transactions
        .fold(initialBalance) { acc, transaction -> acc + transaction.amount }
    val (income, expenses) = transactions
        .fold(Pair(0.0, 0.0)) { (inc, exp), transaction ->
            if (transaction.amount > 0) Pair(inc + transaction.amount, exp)
            else Pair(inc, exp + transaction.amount)
        }
    val string = transactions
        .fold("") { str, transaction ->
            val sign = if (transaction.amount > 0) "+" else ""
            val result = "${transaction.description} : $sign${transaction.amount}"
            if (str.isEmpty()) result else "$str | $result"
        }

    // Ожидаемый вывод:
    // Финальный баланс: 50300.0
    // Доходы: 62000.0, Расходы: -21700.0
    // Выписка: Зарплата: +50000.0 | Аренда: -15000.0 | Продукты: -5500.0 | Фриланс: +12000.0 | Кафе: -1200.0
}

/**
 * Задача 7.4 — zip: Объединение двух списков
 *
 * Есть два списка: студенты и их оценки за экзамен.
 * 1. Объедини через zip в список пар (имя, оценка)
 * 2. Отфильтруй только тех, кто сдал (оценка >= 60)
 * 3. Найди лучшего студента
 * 4. Что произойдёт, если списки разной длины?
 */
fun task74() {
    val students = listOf("Алиса", "Борис", "Вика", "Григорий", "Дана")
    val grades = listOf(85, 42, 91, 60, 78)

    // TODO:
    // 1. zip — объедини студентов с оценками
    // 2. Отфильтруй сдавших (>= 60)
    // 3. Найди студента с лучшей оценкой
    // 4. Попробуй zip с коротким списком — что получится?

    val zipped = students.zip(grades)
    val whoPassed = zipped.filter { it.second >= 60 }
//    val whoPassed2 = zipped.filter { (_, grade) -> grade >= 60 }      - ДЕСТРУКТУРИЗАЦИЯ
    val bestStudent = whoPassed.reduce { acc, currentStudent ->
        if (currentStudent.second > acc.second) currentStudent else acc
    }

    val shortGrades = listOf(100, 55, 73) // для п.4

    val shortZipped = students.zip(shortGrades)
    // Если список оценок меньше списка студентов, то объединяются возможные пары, остальные элементы игнорируются

    // Ожидаемый вывод:
    // Все результаты: [(Алиса, 85), (Борис, 42), (Вика, 91), (Григорий, 60), (Дана, 78)]
    // Сдали экзамен: [(Алиса, 85), (Вика, 91), (Григорий, 60), (Дана, 78)]
    // Лучший студент: Вика (91)
    // С коротким списком: [(Алиса, 100), (Борис, 55), (Вика, 73)]
}

// ============================================================================
// MAIN — запуск всех задач
// ============================================================================

fun main() {
    println("=== Запусти нужную задачу, раскомментировав её ===")

    // LIST (Read-only)
//     task11()
    // task12()
    // task13()

    // MUTABLELIST
//     task21()
    // task22()

    // SET (Read-only)
    // task31()
    // task32()

    // MUTABLESET
//     task41()
//     task42()

    // MAP (Read-only)
//     task51()
//     task52()
//     task53()

    // MUTABLEMAP
//     task61()
//     task62()
//     task63()

    // ФУНКЦИОНАЛЬНЫЕ ОПЕРАЦИИ (flatMap, reduce, fold, zip)
//     task71()
//     task72()
//     task73()
//     task74()
}
