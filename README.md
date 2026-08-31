# Stellar Burgers — юнит-тесты

Дипломный проект Яндекс Практикума: юнит-тесты для программы, которая помогает собрать бургер  
в Stellar Burgers.

## Стек

- Java 11
- Maven
- JUnit 5 (`junit-jupiter`)
- Mockito 5 (`mockito-core`, `mockito-junit-jupiter`)
- JaCoCo (`jacoco-maven-plugin`)

## Структура проекта

```
src/
├── main/java/praktikum/
│   ├── Bun.java              булочка: название и цена
│   ├── Ingredient.java       ингредиент: тип, название, цена
│   ├── IngredientType.java   перечисление: SAUCE, FILLING
│   ├── Burger.java           бургер: булочка + список ингредиентов, расчёт цены и чек
│   ├── Database.java         витрина с готовыми булочками и ингредиентами
│   └── Praktikum.java        точка входа, демонстрационный сценарий
└── test/java/praktikum/
    ├── BunTest.java
    ├── IngredientTest.java
    ├── IngredientTypeTest.java
    ├── BurgerTest.java
    └── DatabaseTest.java
```



## Как запустить тесты

```bash
mvn clean test
```

Открыть отчёт:

```bash
open target/site/jacoco/index.html
```

Запустить один тестовый класс:

```bash
mvn test -Dtest=BurgerTest
```



## Что и как тестируется

### Моки и стабы — только в `BurgerTest`

`Burger` — единственный класс с зависимостями: он не создаёт булочку и ингредиенты сам,
а вызывает их методы `getPrice()`, `getName()`, `getType()`. Поэтому в `BurgerTest`
подставляются моки `Bun` и `Ingredient`, а нужное поведение задаётся стабами
`when(...).thenReturn(...)`. Так тест проверяет только логику бургера и не зависит
от реальных `Bun` и `Ingredient`.

У `Bun`, `Ingredient` и `IngredientType` зависимостей нет — мокать там нечего,
тесты работают с настоящими объектами.

### Параметризация

Параметризация применяется там, где проверка одна, а входных данных много:


| Класс            | Тест                  | Источник данных                                                             |
| ---------------- | --------------------- | --------------------------------------------------------------------------- |
| `Bun`            | `getName`, `getPrice` | `@ValueSource`                                                              |
| `Ingredient`     | `getType`             | `@EnumSource` — покрывает и `SAUCE`, и `FILLING`                            |
| `Ingredient`     | `getName`, `getPrice` | `@ValueSource`                                                              |
| `IngredientType` | `valueOf`             | `@EnumSource`                                                               |
| `Burger`         | `moveIngredient`      | `@CsvSource` — пары индексов: в начало, в конец, в середину, на то же место |


Для `setBuns`, `addIngredient`, `removeIngredient` и `getReceipt` параметризация не используется:  
у этих методов одно поведение, варьировать нечего.

### Дополнительно: Зачем тесты для `Database`

`Database` нет в списке классов из задания, но JaCoCo считает покрытие по всем классам проекта.
Без теста на `Database` общее покрытие составляет 54% — ниже требуемых 70%.
Один тест, создающий `Database` и читающий оба списка, закрывает класс полностью
и поднимает общее покрытие до 83%.

## Покрытие

Общее покрытие по инструкциям — **83%**, при требуемых 70%.
Все четыре класса из задания покрыты на 100%.

Отчёт JaCoCo: покрытие по классам

Сводка по проекту:

Отчёт JaCoCo: сводка

Непокрытым остаётся только `Praktikum` — это демонстрационный `main()`,
который просто печатает чек в консоль и не входит в задание.