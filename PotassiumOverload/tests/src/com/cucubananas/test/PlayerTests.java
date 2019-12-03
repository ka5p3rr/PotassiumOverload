package com.cucubananas.test;

import com.cucubananas.core.actor.Player;
import de.tomgrill.gdxtesting.GdxTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(GdxTestRunner.class)
public class PlayerTests {

    @Test
    public void checkDefaultCoordinates() {
        Player player = new Player("bird.png", 50, 50);
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
