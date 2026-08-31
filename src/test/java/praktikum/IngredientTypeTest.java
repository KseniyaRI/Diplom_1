package praktikum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

// IngredientType — список типов: SAUCE и FILLING, своего кода у enum нет, но
// Java сама добавляет к нему values() и valueOf(); без вызова в тестах покрытие их не засчитает.
class IngredientTypeTest {

    // Проверяет, что values() возвращает обе константы в порядке объявления: SAUCE, FILLING.
    @Test
    void shouldReturnBothConstantsInDeclarationOrder() {
        IngredientType[] expected = {IngredientType.SAUCE, IngredientType.FILLING};

        assertArrayEquals(expected, IngredientType.values());
    }

    // Проверяет, что valueOf() возвращает ту же константу, чьё имя ему передали.
    @ParameterizedTest
    @EnumSource(IngredientType.class)
    void shouldReturnSameConstantByItsName(IngredientType type) {
        assertEquals(type, IngredientType.valueOf(type.name()));
    }
}
