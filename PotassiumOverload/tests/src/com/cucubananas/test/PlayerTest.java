package com.cucubananas.test;

import com.cucubananas.core.actor.MoveableObject;
import com.cucubananas.core.actor.Player;
import de.tomgrill.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;

@RunWith(GdxTestRunner.class)
public class PlayerTest {

    @Test
    public void emptyTest() {
        
    }
//    @Test
//    public void checkDefaultAttributes() {
//        Player player = new Player(50, 50);
//
//        // Check default position
//        float expectedPos = 50.0f;
//        assertEquals(expectedPos, player.getX());
//        assertEquals(expectedPos, player.getY());
//
//        // Check default facing direction
//        assertEquals(MoveableObject.FACING_DIRECTIONS.right, player.getDirection());
//    }
//
//    @Test
//    public void checkTextureLoading() {
//        Player player = new Player(0, 0);
//
//        assertEquals("characterspritesheet.png", player.getTextureName());
//    }

}
