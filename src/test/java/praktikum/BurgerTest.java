package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BurgerTest {

    private static final float DELTA = 0.001f;

    @Mock
    private Bun bun;
    @Mock
    private Ingredient sauce;
    @Mock
    private Ingredient filling;
    @Mock
    private Ingredient cheese;

    private Burger burger;

    @BeforeEach
    void setUp() {
        burger = new Burger();
    }

    // Проверяет, что setBuns() кладёт в поле bun тот же объект, который передали.
    @Test
    void shouldStoreBunInBurger() {
        burger.setBuns(bun);

        assertSame(bun, burger.bun);
    }

    // Проверяет, что addIngredient() добавляет переданный ингредиент в список.
    @Test
    void shouldAddIngredientToList() {
        burger.addIngredient(sauce);

        assertEquals(List.of(sauce), burger.ingredients);
    }

    // Проверяет, что removeIngredient() удаляет ингредиент по индексу.
    @Test
    void shouldRemoveIngredientByIndex() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.removeIngredient(0);

        assertEquals(List.of(filling), burger.ingredients);
    }

    // Проверяет, что moveIngredient() переставляет ингредиент на новую позицию.
    // Стартовый список: [sauce(0), filling(1), cheese(2)].
    // В каждой строке: откуда, куда, какой порядок должен получиться.
    @ParameterizedTest
    @CsvSource({
            "0, 2, '1,2,0'", // sauce в конец     → filling, cheese, sauce
            "2, 0, '2,0,1'", // cheese в начало   → cheese, sauce, filling
            "0, 1, '1,0,2'", // sauce на шаг вперёд → filling, sauce, cheese
            "1, 1, '0,1,2'"  // на то же место    → порядок не меняется
    })
    void shouldMoveIngredientToNewIndex(int index, int newIndex, String expectedOrder) {
        List<Ingredient> initialOrder = List.of(sauce, filling, cheese);
        initialOrder.forEach(burger::addIngredient);

        burger.moveIngredient(index, newIndex);

        List<Ingredient> expected = new ArrayList<>();
        for (String position : expectedOrder.split(",")) {
            expected.add(initialOrder.get(Integer.parseInt(position)));
        }
        assertEquals(expected, burger.ingredients);
    }

    // Проверяет, что getPrice() без начинок возвращает удвоенную цену булочки.
    @Test
    void shouldReturnDoubleBunPriceWhenBurgerHasNoIngredients() {
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);

        assertEquals(200f, burger.getPrice(), DELTA);
    }

    // Проверяет, что getPrice() складывает удвоенную цену булочки и цены всех ингредиентов.
    @Test
    void shouldReturnDoubleBunPricePlusIngredientsPrices() {
        when(bun.getPrice()).thenReturn(100f);
        when(sauce.getPrice()).thenReturn(50f);
        when(filling.getPrice()).thenReturn(300f);
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        assertEquals(550f, burger.getPrice(), DELTA);
    }

    // Проверяет, что getPrice() бросает NullPointerException, если булочку не задали.
    @Test
    void shouldThrowNullPointerExceptionWhenBunIsNotSet() {
        assertThrows(NullPointerException.class, () -> burger.getPrice());
    }

    // Проверяет, что getReceipt() собирает чек из булочки, слоёв и итоговой цены.
    // Ожидаемая строка строится через String.format: %f зависит от локали, %n — от ОС.
    @Test
    void shouldReturnReceiptWithBunNameAndIngredients() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        when(sauce.getName()).thenReturn("hot sauce");
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getPrice()).thenReturn(50f);
        burger.setBuns(bun);
        burger.addIngredient(sauce);

        String expectedReceipt = String.format("(==== black bun ====)%n")
                + String.format("= sauce hot sauce =%n")
                + String.format("(==== black bun ====)%n")
                + String.format("%nPrice: %f%n", 250f);

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}
