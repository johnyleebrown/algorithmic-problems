package regular.dfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import regular.dfs.FindCheese.AbstractMouse;
import regular.dfs.FindCheese.MouseImpl;
import regular.dfs.FindCheese.Mouse;

class FindCheeseTest {

  @Test
  void stayAtCheesePlaceIfFound() {
    Mouse m = new MouseImpl(2, 2, 1, 1);
    assertEquals(true, m.getCheese());
    assertEquals("[1, 1]", m.getPosition());
  }

  @Test
  void moveToStartIfNotFound() {
    Mouse m = new MouseImpl(2, 2, -1, -1);
    assertEquals(false, m.getCheese());
    assertEquals("[0, 0]", m.getPosition());
  }
}