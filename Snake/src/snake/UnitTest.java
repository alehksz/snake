package snake;

import static org.junit.Assert.*;

import org.junit.Test;

public class UnitTest {
	Entity e = new Entity();

	@Test
	public void test_Entity(){
		int x = 5;
		int y = 4;
		e.setX(x);
		e.setY(y);
		int actual_x =e.getX();
		int actual_y =e.getY();
		//test x and y value
		assertEquals(x, actual_x);
		assertEquals(y, actual_y);
		
		String text ="5,4";
		String actual_text = e.toString();
		//test toString
		assertEquals(text, actual_text);
		
		boolean Visible =true; 
		boolean actual_visible =e.getVisible();
		// test visible
		assertEquals(Visible, actual_visible);
		
		Entity a = new Entity(10);
		Entity b = new Entity(8);
		boolean actual_Compare = e.CompareTo(a, b);
		boolean Compare = true; 
		//test CompareTo
		assertEquals(Compare, actual_Compare);	
	}	
}
