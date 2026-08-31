package praktikum;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientTest {

    private static final float DELTA = 0.001f;

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    void getTypeReturnsTypeFromConstructor(IngredientType type) {
        Ingredient ingredient = new Ingredient(type, "hot sauce", 100f);

        assertEquals(type, ingredient.getType());
    }

    @ParameterizedTest
    @ValueSource(strings = {"hot sauce", "cutlet", "dinosaur", ""})
    void getNameReturnsNameFromConstructor(String name) {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, name, 100f);

        assertEquals(name, ingredient.getName());
    }

    @ParameterizedTest
    @ValueSource(floats = {0f, 100f, 200f, 300f})
    void getPriceReturnsPriceFromConstructor(float price) {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cutlet", price);

        assertEquals(price, ingredient.getPrice(), DELTA);
    }
}