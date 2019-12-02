package com.cucubananas.test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import com.badlogic.gdx.Gdx;
import com.cucubananas.core.actor.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import de.tomgrill.gdxtesting.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class ExampleTest {

  @Test
  public void oneEqualsOne() {
    assertEquals(1, 1);
  }

  @Test
  public void checkAssetLogoExists() {
    assertTrue("Checks default LibGDX logo is in assets folder", Gdx.files.internal("../core/assets/badlogic.jpg").exists());
  }
}
