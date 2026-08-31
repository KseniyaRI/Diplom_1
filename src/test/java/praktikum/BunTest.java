package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BunTest {

    // delta 
    private static final float DELTA = 0.001f;

    @ParameterizedTest
    @ValueSource(strings = {"black bun", "white bun", "red bun", ""})
    void getNameReturnsNameFromConstructor(String name) {
        Bun bun = new Bun(name, 100f);

        assertEquals(name, bun.getName());
    }

    @ParameterizedTest
    @ValueSource(floats = {0f, 100f, 200.5f, 300f})
    void getPriceReturnsPriceFromConstructor(float price) {
        Bun bun = new Bun("black bun", price);

        assertEquals(price, bun.getPrice(), DELTA);
    }
}