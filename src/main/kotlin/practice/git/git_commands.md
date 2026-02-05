Git Workflow - Полная шпаргалка


## 1. КЛОНИРОВАНИЕ РЕПОЗИТОРИЯ И НАЧАЛО РАБОТЫ

# Клонирование репозитория в текущую директорию
git clone https://github.com/username/repository-name

# Переход в директорию проекта
cd repository-name

# Просмотр всех веток (локальных)
git branch

# Просмотр всех веток (включая удалённые)
git branch -a

# Просмотр только удалённых веток
git branch -r

Примечание: По умолчанию в репозитории обычно есть ветки master/main и develop.


## 2. СОЗДАНИЕ НОВОЙ ВЕТКИ

# Создание и переключение на новую ветку от текущей
git checkout -b feature/BP-001-task-description

# Альтернативный синтаксис (новый)
git switch -c feature/BP-001-task-description

Naming convention:
- feature/ — для новых фич
- bugfix/ — для исправления багов
- hotfix/ — для срочных исправлений

Формат: type/KEY-001-task-description
- KEY — идентификатор проекта (например, BP для Black Point)
- 001 — номер тикета
- task-description — краткое описание задачи


## 3. РАБОТА С ИЗМЕНЕНИЯМИ

# Добавить все изменённые файлы
git add .

# Добавить конкретные файлы
git add path/to/file1.txt path/to/file2.js

# Добавить все файлы определённого типа
git add *.js

# Просмотр статуса (что добавлено, что изменено)
git status

# Коммит с сообщением
git commit -m "BP-001: Add user authentication feature"

# Отправка ветки на удалённый репозиторий
# origin - это корневая папка репозитория к твоему проекту, в которой хранятся все ветки на удаленном репозе
git push origin feature/BP-001-task-description

# При первом пуше новой ветки (устанавливает upstream)
git push -u origin feature/BP-001-task-description


## 4. СИНХРОНИЗАЦИЯ С DEVELOP ПЕРЕД МЕРДЖЕМ

# Переключение на develop (без создания новой ветки, потому что без -b)
git checkout develop
# или
git switch develop

# Стягивание всех изменений из удалённого репозитория (origin) локально
# Это нужно чтобы минимизировать конфликты и фиксить только то над чем реально работал
git pull origin develop

# Переключение обратно на свою ветку (опционально, если хочешь смержить develop в свою ветку)
git checkout feature/BP-001-task-description

# Мердж develop в твою ветку (чтобы обновить её перед PR)
git merge develop

# Если были конфликты - разрешаешь их, затем:
git add .
git commit -m "Merge develop into feature branch"
git push origin feature/BP-001-task-description


## 5. МЕРДЖ ТВОЕЙ ВЕТКИ В DEVELOP

# Переключение на develop
git checkout develop

# Стягивание последних изменений
git pull origin develop

# Мердж твоей ветки В текущую (develop)
# Слияние версий кода ИЗ ветки которую ты мержишь В текущую
git merge feature/BP-001-task-description

# Merge commit создаётся автоматически, но можешь изменить сообщение:
git commit -m "Merge BP-001: Add user authentication"
# Иначе дефолтное сообщение всё равно появится в истории

# ВАЖНО: Отправка изменений в удалённый develop
git push origin develop

КРИТИЧНО: После мерджа обязательно нужно запушить изменения в develop!
Иначе изменения останутся только локально.


## 6. ПРОСМОТР ИСТОРИИ GIT

# Базовый просмотр истории
git log

# Компактный вывод (одна строка на коммит)
git log --oneline

# С графом веток
git log --oneline --graph --all

# Красивый форматированный вывод
git log --graph --pretty=format:'%Cred%h%Creset - %s %Cgreen(%cr)%Creset %C(bold blue)<%an>%Creset' --abbrev-commit --all

# Последние N коммитов
git log -5

# История конкретного файла
git log -- path/to/file.txt

# Показать изменения в коммите
git show commit-hash

# Показать разницу между коммитами
git diff commit1 commit2


## 7. ОТМЕНА ИЗМЕНЕНИЙ

--- Отменить git add (убрать файлы из staging) ---

# Убрать все файлы из staging
git reset

# Убрать конкретный файл из staging
git reset path/to/file.txt

# Альтернатива (новый синтаксис)
git restore --staged .
git restore --staged path/to/file.txt


--- Отменить последний коммит (НЕ запушенный) ---

# Отменить коммит, сохранив изменения в staging
git reset --soft HEAD~1

# Отменить коммит, сохранив изменения как unstaged
git reset --mixed HEAD~1
# или просто
git reset HEAD~1

# Отменить коммит и УДАЛИТЬ все изменения (ОПАСНО!)
git reset --hard HEAD~1


--- Удалить файл из последнего коммита (НЕ запушенного) ---

# Убрать файл из последнего коммита, оставив его в рабочей директории
git reset HEAD~1 -- path/to/file.txt
git commit --amend --no-edit

# Или проще (если файл уже закоммичен, но не запушен)
git rm --cached path/to/file.txt
git commit --amend -m "Remove unwanted file"


--- Отменить уже запушенный коммит ---

# Создать новый коммит, который отменяет изменения
git revert commit-hash

# Отменить несколько последних коммитов
git revert HEAD~3..HEAD

# Если уже запушили и хотите переписать историю (ОПАСНО для публичных веток!)
git reset --hard HEAD~1
git push --force origin branch-name


## 8. УДАЛЕНИЕ ФАЙЛОВ ИЗ GIT

# Удалить файл из Git, но оставить в файловой системе
git rm --cached file.txt
git commit -m "Remove file from Git tracking"

# Удалить файл и из Git, и из файловой системы
git rm file.txt
git commit -m "Delete file"

# Удалить папку рекурсивно
git rm -r --cached folder/
git commit -m "Remove folder from Git tracking"

# Добавить файл в .gitignore чтобы он не попадал в коммиты:
echo "file.txt" >> .gitignore
git add .gitignore
git commit -m "Add file.txt to gitignore"


## 9. ВРЕМЕННОЕ СОХРАНЕНИЕ ИЗМЕНЕНИЙ (STASH)

# Сохранить все незакоммиченные изменения во временное хранилище
git stash

# Сохранить с описанием (чтобы потом понять что это было)
git stash push -m "WIP: недоделанная авторизация"

# Посмотреть список сохранённых stash
git stash list
# Вывод: stash@{0}: On feature/auth: WIP: недоделанная авторизация
#         stash@{1}: WIP on develop: abc1234 Previous stash

# Применить последний stash И удалить его из списка
git stash pop

# Применить последний stash БЕЗ удаления из списка
git stash apply

# Применить конкретный stash по индексу
git stash pop stash@{1}
git stash apply stash@{2}

# Удалить конкретный stash
git stash drop stash@{0}

# Удалить все stash
git stash clear
