"# appForNotes" 
Приложение для сохранения заметок.
Главная страница
Авторизиция

Список заметок

Добавление новой заметки

Регистрация


- Создал докер образ postgres БД:
docker run --name int-postgres --volume db-data:/var/lib/postgresql/data -e POSTGRES_PASSWORD=123 -e POSTGRES_DB=intpostgres -p 5432:5432 postgres:12-alpine
- Подключаться:
jdbc:postgresql://localhost:5432/intpostgres
Создал таблицы(data.sql)
