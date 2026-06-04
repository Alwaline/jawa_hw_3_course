# Домашние работы — 3 курс, Java (уроки 42–49)

| Папка | Уроки | Тема |
|---|---|---|
| `hw_42_43` | 42–43 | Spring MVC + Spring Data JPA |
| `hw_44` | 44 | Spring Boot — первый проект |
| `hw_45` | 45 | Spring Boot REST API + Swagger-документация |
| `hw_46` | 46 | Spring Boot REST API + пагинация + обработка ошибок |
| `hw_47` | 47 | Spring Boot — use-case «добавить заказ пользователю» |
| `hw_48` | 48 | Spring Boot — валидация (Bean Validation) + GlobalExceptionHandler |
| `hw_49` | 49 | Spring Boot — тестирование сервисов и контроллеров, покрытие |

## Краткое описание

### hw_42_43 — Spring MVC + Spring Data JPA
Проект на Spring MVC (без Spring Boot). Реализованы: контроллер, сервис, репозиторий для сущности `User`. Репозиторий построен на `JpaRepository` (Spring Data JPA), подключена база данных.

### hw_44 — Spring Boot
Первый проект на Spring Boot. REST API для каталога автомобилей (`Car`): методы GET/POST с пагинацией, сущность подключена к базе через Spring Data JPA.

### hw_45 — Spring Boot REST API + документация
Собственный проект на Spring Boot: сущность `Account`, методы GET и POST, API задокументировано через OpenAPI/Swagger (`AccountApi`).

### hw_46 — Пагинация + обработка ошибок
Расширение проекта: добавлена пагинация (`PageDto`), обработка случая «не найдено» (`NotFoundException`).

### hw_47 — Use-case: заказ пользователю
Добавлены домены `accounts` и `orders`. Реализован use-case `AccountOrderService`: проверка существования пользователя и добавление ему заказа.

### hw_48 — Валидация + обработка ошибок
Новый проект с доменами `user` и `order`. Реализована валидация входящих данных (Bean Validation), глобальный обработчик ошибок (`GlobalExceptionHandler`), форматированные ответы об ошибках (`ErrorResponse`, `ValidationErrorResponse`).

### hw_49 — Тестирование
Написаны unit-тесты для сервисов и контроллеров. Тесты сгруппированы через `@Nested`, проверяется валидация, полнота JSON-ответов и покрытие кода.
