package group_0661.gamecentre;

import android.content.Context;
import android.graphics.Bitmap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ImageToTilesTest {
    ImageToTiles testImageToTiles;

    @Mock
    private Context testContext;

    @Mock
    private Bitmap testImage;

    @Before

    public void setUp() {
        testImageToTiles = new ImageToTiles(testImage, 5,5);
    }

    @Test
    public void createTiles() {
        assertTrue(testImageToTiles.createTiles());
    }

    @Test
    public void getSavePath() {
        testImageToTiles.createTiles();
        testImageToTiles.saveTiles(testContext);
        assertEquals(testContext.getFilesDir().toString() + "/tiles", testImageToTiles.getSavePath() );
    }

    @Test
    public void saveTiles() {
        testImageToTiles.createTiles();
        assertTrue(testImageToTiles.saveTiles(testContext));
    }
}