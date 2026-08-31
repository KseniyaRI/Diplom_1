package praktikum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IngredientTypeTest {

    @Test
    void valuesReturnsSauceAndFillingInDeclarationOrder() {
        IngredientType[] expected = {IngredientType.SAUCE, IngredientType.FILLING};

        assertArrayEquals(expected, IngredientType.values());
    }

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    void valueOfReturnsSameConstantByItsName(IngredientType type) {
        assertEquals(type, IngredientType.valueOf(type.name()));
    }
}