# Git Practice - Практические задания

Выполняй задания в терминале IntelliJ IDEA (Alt+F12) или в обычном терминале.
Находясь в папке проекта Demo.

---

## 1. ПРОСМОТР СОСТОЯНИЯ (самое частое)

### Задание 1.1 — Проверь статус репозитория
```bash
# Посмотри текущее состояние: какие файлы изменены, какие в staging
git status
```
**Вопрос:** На какой ветке ты сейчас находишься?

### Задание 1.2 — Посмотри все ветки
```bash
# Локальные ветки
git branch

# Все ветки (включая удалённые)
git branch -a
```
**Вопрос:** Сколько веток существует в репозитории?

### Задание 1.3 — Посмотри историю коммитов
```bash
# Последние 5 коммитов в компактном виде
git log --oneline -5

# С графом веток (красиво)
git log --oneline --graph --all -10
```
**Вопрос:** Кто автор последнего коммита?

---

## 2. СОЗДАНИЕ ВЕТКИ И ПЕРЕКЛЮЧЕНИЕ

### Задание 2.1 — Создай новую ветку
```bash
# Создай ветку для практики и переключись на неё
git checkout -b practice/git-training

# Проверь, что переключился
git branch
```
**Ожидание:** Звёздочка (*) должна быть рядом с practice/git-training

### Задание 2.2 — Переключись обратно на develop
```bash
git checkout develop

# Или новый синтаксис
git switch develop
```

### Задание 2.3 — Вернись на свою ветку
```bash
git checkout practice/git-training
```

---

## 3. ВНЕСЕНИЕ ИЗМЕНЕНИЙ (add, commit)

### Задание 3.1 — Создай тестовый файл и добавь его
```bash
# Создай файл (можно через IDE или команду)
echo "Hello Git" > src/main/kotlin/practice/test.txt

# Проверь статус — файл должен быть Untracked
git status

# Добавь файл в staging
git add src/main/kotlin/practice/test.txt

# Проверь статус — файл должен быть в Changes to be committed
git status
```

### Задание 3.2 — Сделай коммит
```bash
git commit -m "practice: Add test file for git training"

# Проверь, что коммит создался
git log --oneline -3
```

### Задание 3.3 — Измени файл и закоммить изменения
```bash
# Добавь текст в файл
echo "Learning Git is fun" >> src/main/kotlin/practice/test.txt

# Посмотри что изменилось
git diff

# Добавь и закоммить
git add .
git commit -m "practice: Update test file"
```

---

## 4. ОТМЕНА ИЗМЕНЕНИЙ (важно!)

### Задание 4.1 — Отмени изменения до git add
```bash
# Измени файл
echo "This will be discarded" >> src/main/kotlin/practice/test.txt

# Посмотри изменения
git diff

# Отмени изменения (вернись к последнему коммиту)
git restore src/main/kotlin/practice/test.txt

# Проверь — изменения должны исчезнуть
git status
```

### Задание 4.2 — Отмени git add (убери из staging)
```bash
# Измени и добавь файл
echo "Staged but not committed" >> src/main/kotlin/practice/test.txt
git add .

# Проверь — файл в staging
git status

# Убери из staging (но сохрани изменения в файле)
git restore --staged src/main/kotlin/practice/test.txt

# Проверь — файл должен быть modified, но не staged
git status

# Теперь отмени и сами изменения
git restore src/main/kotlin/practice/test.txt
```

### Задание 4.3 — Отмени последний коммит (НЕ запушенный!)
```bash
# Сначала создай коммит для отмены
echo "This commit will be undone" >> src/main/kotlin/practice/test.txt
git add .
git commit -m "practice: This will be undone"

# Посмотри историю
git log --oneline -3

# Отмени коммит, но сохрани изменения
git reset --soft HEAD~1

# Проверь — коммит исчез, но изменения остались в staging
git status
git log --oneline -3

# Убери изменения из staging и отмени их
git restore --staged .
git restore .
```

---

## 5. РАБОТА С УДАЛЁННЫМ РЕПОЗИТОРИЕМ (push, pull)

### Задание 5.1 — Запушь свою ветку
```bash
# Убедись что ты на своей ветке
git branch

# Создай коммит если ещё не создал
echo "Ready to push" > src/main/kotlin/practice/test.txt
git add .
git commit -m "practice: Ready to push"

# Запушь ветку (первый раз с -u для установки upstream)
git push -u origin practice/git-training
```

### Задание 5.2 — Получи изменения из develop
```bash
# Переключись на develop
git checkout develop

# Стяни последние изменения
git pull origin develop

# Вернись на свою ветку
git checkout practice/git-training
```

---

## 6. ПРОСМОТР ИЗМЕНЕНИЙ (diff)

### Задание 6.1 — Посмотри разницу между коммитами
```bash
# Найди хэши двух коммитов
git log --oneline -5

# Посмотри разницу между ними (замени хэши на реальные)
git diff abc123 def456
```

### Задание 6.2 — Посмотри изменения в конкретном коммите
```bash
# Найди хэш коммита
git log --oneline -3

# Посмотри что было изменено (замени хэш)
git show abc123
```

---

## 7. ОЧИСТКА (удаление тестовых данных)

### Задание 7.1 — Удали тестовый файл и ветку
```bash
# Удали тестовый файл
git rm src/main/kotlin/practice/test.txt
git commit -m "practice: Remove test file"
git push origin practice/git-training

# Переключись на develop
git checkout develop

# Удали локальную ветку
git branch -d practice/git-training

# Удали удалённую ветку (опционально)
git push origin --delete practice/git-training
```

---

## ШПАРГАЛКА — Самые частые команды

| Команда | Описание |
|---------|----------|
| `git status` | Проверить состояние |
| `git branch` | Список веток |
| `git checkout -b name` | Создать и переключиться на ветку |
| `git checkout name` | Переключиться на ветку |
| `git add .` | Добавить все изменения в staging |
| `git commit -m "msg"` | Создать коммит |
| `git push` | Отправить на сервер |
| `git pull` | Получить с сервера |
| `git log --oneline -5` | Последние 5 коммитов |
| `git diff` | Показать изменения |
| `git restore file` | Отменить изменения в файле |
| `git restore --staged file` | Убрать из staging |
| `git reset --soft HEAD~1` | Отменить последний коммит |
