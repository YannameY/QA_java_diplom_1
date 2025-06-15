import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void testSetBuns() {
        Bun bun = mock(Bun.class);
        burger.setBuns(bun);
        Assert.assertSame(bun, burger.bun);
    }

    @Test
    public void testAddIngredient() {
        Ingredient ingredient = mock(Ingredient.class);
        burger.addIngredient(ingredient);
        Assert.assertEquals(1, burger.ingredients.size());
        Assert.assertTrue(burger.ingredients.contains(ingredient));
    }

    @Test
    public void testRemoveIngredient() {
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);

        Assert.assertEquals(1, burger.ingredients.size());
        Assert.assertFalse(burger.ingredients.contains(ingredient1));
        Assert.assertTrue(burger.ingredients.contains(ingredient2));
    }

    @Test
    public void testMoveIngredient() {
        Ingredient ingredient1 = mock(Ingredient.class);
        Ingredient ingredient2 = mock(Ingredient.class);
        Ingredient ingredient3 = mock(Ingredient.class);


        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);
        burger.moveIngredient(0, 2);


        Assert.assertEquals(3, burger.ingredients.size());

        Assert.assertSame(ingredient1, burger.ingredients.get(2));
        Assert.assertSame(ingredient2, burger.ingredients.get(0));
        Assert.assertSame(ingredient3, burger.ingredients.get(1));
    }
}