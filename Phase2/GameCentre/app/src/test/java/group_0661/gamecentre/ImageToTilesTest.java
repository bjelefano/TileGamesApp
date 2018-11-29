package group_0661.gamecentre;

import android.content.Context;
import android.graphics.Bitmap;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.*;

@RunWith(RobolectricTestRunner.class)
public class ImageToTilesTest {
    private ImageToTiles testImageToTiles;

    @Mock
    private Context testContext;

    @Mock
    private Bitmap testImage;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testImageToTiles = Mockito.spy(new ImageToTiles(testImage, 5,5));
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

}