# Kotlin Backend Stack

> `*` — middle-уровень

---

## I. Kotlin

### 1. Основы языка
> [Теория](src/main/kotlin/practice/language_basics/Основы%20языка.md)

- val / var (immutable / mutable)
- Типы данных (Int, Double, Boolean, Char, String и др.)
- Type casting (is, !is, as, as?) + smart cast
- Операторы (arithmetic, comparison, logical, equality)

### 2. Null Safety
- Nullable types (?)
- Safe call operator (?.)
- Elvis operator (?:)
- Not-null statement (!!)
- Safe casts (as?)
- let с null check

### 3. Control Flow
> [Теория](src/main/kotlin/practice/control_flow/Управляющие%20конструкции.md) | [Задания](src/main/kotlin/practice/control_flow/ControlFlowTasks.kt)

- if / else (statement + expression)
- when (statement + expression)

### 4. Циклы
> [Теория](src/main/kotlin/practice/loops/Циклы.md) | [Задания](src/main/kotlin/practice/loops/LoopTasks.kt)

- for loop + ranges (.. until downTo step)
- while / do-while
- break / continue

### 5. Функции
- Parameters, return types, default values, named arguments
- Single expression functions
- Unit
- Higher-order functions
- Lambda expressions
- Extension functions
- Scope functions (let, run, with, apply, also)
- Overloading
- Overriding

### 6. ООП
- Классы, primary / secondary constructors
- Init block, порядок инициализации
- this keyword
- Inheritance (open, override, super)
- Visibility modifiers (public, private, protected, internal)
- Abstract classes
- Interfaces (default methods, multiple inheritance)

### 7. Типы классов
- Data classes (equals, hashCode, toString, copy, destructuring)
- Enum classes (values, valueOf, properties, abstract methods)
- `*` Sealed classes / sealed interfaces
- Object declarations (singleton)
- Companion object
- `*` Data objects
- `*` Nested / Inner classes
- `*` Anonymous classes / object expressions

### 8. Коллекции
> [Теория](src/main/kotlin/practice/collections/Коллекции.md) | [Задания](src/main/kotlin/practice/collections/CollectionsTasks.kt)

- List / MutableList
- Set / MutableSet
- Map / MutableMap
- Immutable vs Mutable коллекции
- `*` Collection operations (filter, map, flatMap, reduce, fold, groupBy, sortedBy, zip, associate)
- `*` Sequences (lazy evaluation vs eager)

### 9. Generics
- Type parameters, generic classes / functions

---

## II. Spring Boot

### 1. Spring Core
- Dependency Injection (DI) и Inversion of Control (IoC)
- Типы DI: constructor injection, setter injection, field injection
- @Component, @Service, @Repository, @Controller
- @Autowired
- @Bean, @Configuration
- `*` Bean lifecycle (создание, инициализация, уничтожение)
- `*` Bean scopes (singleton, prototype, request, session)
- `*` BeanFactory vs ApplicationContext
- `*` @Qualifier, @Primary
- Constructor injection — идиоматический подход
- `*` all-open plugin
- `*` no-arg plugin

### 2. Spring Boot Core
- @SpringBootApplication (@Configuration + @EnableAutoConfiguration + @ComponentScan)
- Auto-configuration
- Starter dependencies (spring-boot-starter-web, spring-boot-starter-data-jpa и др.)
- application.properties / application.yml
- @Value
- `*` @ConfigurationProperties
- Profiles (dev, test, prod) — @Profile, spring.profiles.active
- Embedded server (Tomcat, Jetty, Undertow)
- Spring Initializr
- DevTools — hot reload

### 3. REST API
- @RestController (@Controller + @ResponseBody)
- @GetMapping, @PostMapping, @PutMapping, @DeleteMapping, @PatchMapping
- @RequestBody, @PathVariable, @RequestParam, @RequestHeader
- ResponseEntity
- Exception handling: @ControllerAdvice + @ExceptionHandler
- `*` Custom error responses, ProblemDetail
- Validation: @Valid, @NotNull, @NotBlank, @Size, @Min, @Max
- `*` Custom validators
- Logging: SLF4J, Logback
- `*` Swagger / OpenAPI документация

### 4. Data Access / Persistence
- Spring Data JPA — repositories (CrudRepository, JpaRepository)
- @Entity, @Table, @Id, @GeneratedValue
- Query methods (findByName, findByAgeGreaterThan и др.)
- `*` JPQL и @Query
- `*` Native queries
- `*` Hibernate ORM — relationships (@OneToMany, @ManyToOne, @ManyToMany, @OneToOne)
- `*` Fetch types (LAZY vs EAGER)
- `*` @Transactional
- `*` Database migrations: Flyway / Liquibase
- `*` Connection pooling: HikariCP
- `*` Spring Data Pagination (Pageable, Sort)

### 5. Spring Security
- `*` SecurityFilterChain
- `*` Form-based login / Basic Auth
- `*` JWT — token-based аутентификация
- `*` OAuth2 / OpenID Connect
- `*` Role-Based Access Control (RBAC)
- `*` @PreAuthorize, @Secured — method-level security
- `*` CORS конфигурация
- `*` CSRF защита

### 6. Testing
- JUnit 5 — unit тесты
- @SpringBootTest — интеграционные тесты
- `*` MockMvc
- `*` MockK
- `*` @MockBean
- `*` TestContainers
- Kotest + MockK

### 7. Spring Boot Actuator
- `*` Health checks (/actuator/health)

### 8. Messaging
- `*` Apache Kafka — producers, consumers, topics, partitions, consumer-groups

### 9. Кэширование и планирование
- `*` Spring Cache — @Cacheable, @CacheEvict, @CachePut
- `*` Caffeine
- `*` @Scheduled — cron, fixedRate, fixedDelay

### 10. Продвинутое / Современное
- `*` Virtual Threads (Project Loom)
- `*` Observability — Prometheus, Grafana, Micrometer

---

## III. Backend — общие концепции

### 1. Алгоритмы
- Sorting
- Searching (Linear, Binary Search)
- `*` Рекурсия vs Итерация
- `*` Big O нотация (время и память)

### 2. Базы данных
- Реляционные БД: PostgreSQL
- CRUD операции
- `*` Нормализация и денормализация
- `*` Индексы — как работают, когда использовать
- `*` ACID (Atomicity, Consistency, Isolation, Durability)
- `*` Транзакции
- `*` JOIN-ы (INNER, LEFT, RIGHT, FULL)
- `*` Query optimization (EXPLAIN, N+1 проблема)

### 3. Сети и протоколы
- REST принципы (stateless, resource-based)
- `*` gRPC — концепция

### 4. API Design
- REST API best practices
- JSON как формат обмена
- Статус-коды (200, 201, 400, 401, 403, 404, 500)
- `*` Версионирование API
- `*` Pagination, Filtering, Sorting (концепция)
- `*` Rate limiting
- `*` Idempotency

### 5. Безопасность
- Аутентификация vs Авторизация
- `*` SQL injection — предотвращение
- `*` XSS, CSRF — что это и как защищаться
- `*` OWASP Top 10
- `*` CORS — концепция

### 6. Git и Version Control
> [Команды](src/main/kotlin/practice/git/git_commands.md) | [Практика](src/main/kotlin/practice/git/GitPractice.md)

- Основы Git (clone, add, commit, push, pull, branch, merge)
- Feature branches, Git Flow
- `*` Merge conflicts
- `*` Pull Requests / Code Review

### 7. CI/CD
- `*` Что такое CI/CD и зачем
- `*` GitHub Actions / GitLab CI
- `*` Pipeline: build → test → deploy

### 8. Docker и контейнеризация
- `*` Контейнер vs VM
- `*` Dockerfile
- `*` Docker Compose
- `*` Основные команды (build, run, ps, logs, exec)
- `*` Docker volumes, networks
- `*` Kubernetes basics (pods, services, deployments)

### 9. System Design
- `*` Monolith vs Microservices — trade-offs
- `*` Horizontal vs Vertical scaling
- `*` Load balancing
- `*` Caching strategies (Redis, in-memory)
- `*` Message queues — зачем нужны
- `*` Stateless vs Stateful

### 10. Паттерны проектирования
- `*` Singleton, Factory, Builder
- `*` Observer, Strategy
- `*` Repository pattern
- `*` Dependency Injection (как паттерн)
- `*` MVC

### 11. Облачные сервисы (обзорно)
- `*` S3 / Cloud Storage
