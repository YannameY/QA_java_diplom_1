import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.List;


@RunWith(Parameterized.class)
public class BurgerGetReceiptTest {
    private final Bun bun;
    private final List<Ingredient> ingredients;
    private final String expectedReceipt;

    public BurgerGetReceiptTest(Bun bun, List<Ingredient> ingredients, String expectedReceipt) {
        this.bun = bun;
        this.ingredients = ingredients;
        this.expectedReceipt = expectedReceipt;
    }

    @Parameterized.Parameters
    public static Object[][] testData() {
        return new Object[][]{
                {
                        new Bun("black bun", 100f), // Создание объекта Bun с именем "black bun" и ценой 100
                        List.of( // Создание списка ингредиентов для бургера
                                new Ingredient(IngredientType.SAUCE, "sour cream", 200f), // Ингредиент "sour cream" типа "SAUCE" с ценой 200
                                new Ingredient(IngredientType.FILLING, "cutlet", 100f), // Ингредиент "cutlet" типа "FILLING" с ценой 100
                                new Ingredient(IngredientType.FILLING, "dinosaur", 200f) // Ингредиент "dinosaur" типа "FILLING" с ценой 200
                        ),
                        // Ожидаемый чек для данного набора тестовых данных
                        "(==== black bun ====)" + System.lineSeparator() + // Заголовок бургера
                                "= sauce sour cream =" + System.lineSeparator() + // Ингредиент "sour cream"
                                "= filling cutlet =" + System.lineSeparator() + // Ингредиент "cutlet"
                                "= filling dinosaur =" + System.lineSeparator() + // Ингредиент "dinosaur"
                                "(==== black bun ====)" + System.lineSeparator() + // Заголовок бургера
                                System.lineSeparator() + // Пустая строка
                                "Price: 700,000000" + System.lineSeparator() // Цена бургера
                },
                {
                        // Тестовые данные для бургера с белой булочкой и несколькими ингредиентами
                        new Bun("white bun", 1f),
                        List.of(
                                new Ingredient(IngredientType.FILLING, "cutlet", 0.5f),
                                new Ingredient(IngredientType.SAUCE, "chili sauce", 4f),
                                new Ingredient(IngredientType.FILLING, "sausage", 1f)
                        ),
                        // Ожидаемый чек
                        "(==== white bun ====)" + System.lineSeparator() +
                                "= filling cutlet =" + System.lineSeparator() +
                                "= sauce chili sauce =" + System.lineSeparator() +
                                "= filling sausage =" + System.lineSeparator() +
                                "(==== white bun ====)" + System.lineSeparator() +
                                System.lineSeparator() +
                                "Price: 7,500000" + System.lineSeparator()
                },
                {
                        // Тестовые данные для бургера с красной булочкой и несколькими ингредиентами
                        new Bun("red bun", 1f),
                        List.of(
                                new Ingredient(IngredientType.SAUCE, "hot sauce", 5.5f),
                                new Ingredient(IngredientType.FILLING, "sausage", 14.5f),
                                new Ingredient(IngredientType.FILLING, "dinosaur", 200f)
                        ),
                        // Ожидаемый чек
                        "(==== red bun ====)" + System.lineSeparator() +
                                "= sauce hot sauce =" + System.lineSeparator() +
                                "= filling sausage =" + System.lineSeparator() +
                                "= filling dinosaur =" + System.lineSeparator() +
                                "(==== red bun ====)" + System.lineSeparator() +
                                System.lineSeparator() +
                                "Price: 222,000000" + System.lineSeparator()
                },
        };
    }

    // Тестовый метод для проверки генерации чека
    @Test
    public void testGetReceipt() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        ingredients.forEach(burger::addIngredient);
        String receipt = burger.getReceipt();
        Assert.assertEquals(expectedReceipt, receipt);
    }
}