package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientTest {

    private static final float DELTA = 0.001f;

    // Проверяет, что getType() возвращает тип, который передали в конструктор.
    @ParameterizedTest
    @EnumSource(IngredientType.class)
    void shouldReturnTypeFromConstructor(IngredientType type) {
        Ingredient ingredient = new Ingredient(type, "hot sauce", 100f);

        assertEquals(type, ingredient.getType());
    }

    // Проверяет, что getName() возвращает название, которое передали в конструктор.
    @ParameterizedTest
    @ValueSource(strings = {"hot sauce", "cutlet", "dinosaur", ""})
    void shouldReturnNameFromConstructor(String name) {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, name, 100f);

        assertEquals(name, ingredient.getName());
    }

    // Проверяет, что getPrice() возвращает цену, которую передали в конструктор.
    @ParameterizedTest
    @ValueSource(floats = {0f, 100f, 200f, 300f})
    void shouldReturnPriceFromConstructor(float price) {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cutlet", price);

        assertEquals(price, ingredient.getPrice(), DELTA);
    }
}
