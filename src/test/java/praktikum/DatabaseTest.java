package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Database нет в задании, но без его покрытия общий процент падает до 54% — ниже требуемых 70%.
class DatabaseTest {

    private Database database;

    @BeforeEach
    void setUp() {
        database = new Database();
    }

    // Проверяет, что availableBuns() возвращает все три булочки из конструктора.
    @Test
    void shouldReturnAllBuns() {
        assertEquals(3, database.availableBuns().size());
    }

    // Проверяет, что availableIngredients() возвращает все шесть ингредиентов из конструктора.
    @Test
    void shouldReturnAllIngredients() {
        assertEquals(6, database.availableIngredients().size());
    }
}
