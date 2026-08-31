package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BunTest {

    private static final float DELTA = 0.001f;

    // Проверяет, что getName() возвращает название, которое передали в конструктор.
    @ParameterizedTest
    @ValueSource(strings = {"black bun", "white bun", "red bun", ""})
    void shouldReturnNameFromConstructor(String name) {
        Bun bun = new Bun(name, 100f);

        assertEquals(name, bun.getName());
    }

    // Проверяет, что getPrice() возвращает цену, которую передали в конструктор.
    @ParameterizedTest
    @ValueSource(floats = {0f, 100f, 200.5f, 300f})
    void shouldReturnPriceFromConstructor(float price) {
        Bun bun = new Bun("black bun", price);

        assertEquals(price, bun.getPrice(), DELTA);
    }
}
