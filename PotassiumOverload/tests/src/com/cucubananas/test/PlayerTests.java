package com.cucubananas.test;

import com.cucubananas.core.actor.MoveableObject;
import com.cucubananas.core.actor.Player;
import de.tomgrill.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;

@RunWith(GdxTestRunner.class)
public class PlayerTests {

    @Test
    public void checkDefaultAttributes() {
        Player player = new Player("assets/bird.png", 50, 50);

        // Check default position
        float expectedPos = 50.0f;
        assertEquals(expectedPos, player.getX());
        assertEquals(expectedPos, player.getY());

        // Check default facing direction
        assertEquals(MoveableObject.facingDirections.right, player.getDirection());
    }

    @Test
    public void checkTextureLoading() {
        Player player = new Player("assets/bird.png", 0, 0);

        assertEquals("assets/bird.png", player.getTextureName());
    }

    @Test
    public void checkYBottomCoordinates() {

    }

    @Test
    public void checkYTopCoordinates() {

    }

    @Test
    public void checkXRightCoordinates() {

    }

    @Test
    public void checkXLeftCoordinates() {

    }
}
