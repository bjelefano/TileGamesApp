package group_0580.gamecentre;


import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * A module that cuts an input bitmap into n^2 pieces. Allows for custom backgrounds in sliding tiles
 */
public class ImageToTiles {
    /**
     * The new width (in pixels) of the input bitmap after adjustment
     */
    private final int RESIZE_WIDTH = 1800;
    /**
     * The new height (in pixels) of the input bitmap after adjustment
     */
    private final int RESIZE_HEIGHT = 2400;
    /**
     * File path of the saved Bitmap
     */
    private String path;
    /**
     * The bitmap image
     */
    private Bitmap image;
    /**
     * The number of rows that will make up the sliced up image
     */
    private int size;
    /**
     * An arrayList containing the sliced up Bitmap image
     */
    private ArrayList<Bitmap> tiles;

    /**
     * Constructs the image slicer module
     */
    public ImageToTiles(Bitmap image, int size) {
        this.image = android.graphics.Bitmap.createScaledBitmap(image,RESIZE_WIDTH, RESIZE_HEIGHT,true);
        this.size = size;
        this.tiles = new ArrayList<>();

        createTiles();
    }

    /**
     * Performs the Bitmap slicing and puts the resulting sliced images into ArrayList tiles
     */
    private void createTiles() {
        int x = 0;
        int y = 0;

        int num_tiles = size * size;
        int tileWidth = RESIZE_WIDTH / size;
        int tileHeight = RESIZE_HEIGHT / size;

        for (int i = 0; i < num_tiles - 1; i++) {
            Bitmap crop = android.graphics.Bitmap.createBitmap(this.image, x, y, tileWidth, tileHeight);
            this.tiles.add(crop);
            x += tileWidth;

            if (x == RESIZE_WIDTH) {
                x = 0;
                y += tileHeight;
            }
        }
        tiles.add(createBlankTile());
    }

    /**
     * Creates a blank Bitmap
     *
     * @return a blank white Bitmap the size of the tiles contained in ArrayList tiles
     */
    private Bitmap createBlankTile() {
        Bitmap blankTile = android.graphics.Bitmap.createBitmap(this.image, 0, 0, RESIZE_WIDTH / 2, RESIZE_HEIGHT / 2);
        blankTile.eraseColor(Color.WHITE);
        return blankTile;
    }

    /**
     * A getter for the save path of the bitmap
     */
    public String getSavePath() {
        return path;
    }

    /**
     * Saves the sliced images to a directory within the internal files of the application
     *
     * @return true iff the save is a success
     */
    public boolean saveTiles(Context c) {
        File dir = new File(c.getFilesDir(), "/tiles");
        if (dir.exists()) { dir.delete(); }
        dir.mkdir();
        path = dir.getAbsolutePath();

        int i = 1;
        for (Bitmap img : tiles) {
            File newImage = new File(dir,  String.format(Locale.CANADA, "tile_%d.png", i));
            try {
                FileOutputStream out = new FileOutputStream(newImage);
                img.compress(android.graphics.Bitmap.CompressFormat.PNG, 100, out);
                out.close();
                i++;
            }
            catch (IOException e) {
                return false;
            }
        }
        return true;
    }
}
