package group_0661.gamecentre;


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
public class MatchingTiles_ImageToTiles {
    /**
     * The new width (in pixels) of the input bitmap after adjustment
     */
    private final int RESIZE_WIDTH = 1800;
    /**
     * The new height (in pixels) of the input bitmap after adjustment
     */
    private final int RESIZE_HEIGHT = 2400;
    /**
     * File gamePath of the saved Bitmap
     */
    private String gamePath;
    /**
     * The path for saved cover
     */
    private String coverPath;
    /**
     * The bitmap image for game
     */
    private Bitmap image;
    /**
     * The bitmap image for cover;
     */
    private Bitmap cover;
    /**
     * The number of rows that will make up the sliced up image
     */
    private int columns;
    /**
     * The number of columns that will make up the sliced up image
     */
    private int rows;
    /**
     * An arrayList containing the sliced up Bitmap image
     */
    private ArrayList<Bitmap> tiles;
    /**
     * An arrayList containing the sliced up Bitmap image
     */
    private ArrayList<Bitmap> cover_tiles;
    /**
     * Constructs the image slicer module
     */
    public MatchingTiles_ImageToTiles(Bitmap image, Bitmap cover, int columns) {
        this.image = android.graphics.Bitmap.createScaledBitmap(image,RESIZE_WIDTH, RESIZE_HEIGHT,true);
        this.cover = android.graphics.Bitmap.createScaledBitmap(cover,RESIZE_WIDTH, RESIZE_HEIGHT,true);
        this.columns = columns;
        this.rows = columns + 1;
        this.tiles = new ArrayList<>();
        this.cover_tiles = new ArrayList<>();

        createTiles();
        createCover();
    }

    /**
     * Performs the Bitmap slicing and puts the resulting sliced images into ArrayList tiles
     */
    private void createTiles() {
        int x = 0;
        int y = 0;

        int num_tiles = columns * rows;
        int tileWidth = RESIZE_WIDTH / columns;
        int tileHeight = RESIZE_HEIGHT / rows;

        for (int i = 0; i < num_tiles; i++) {
            Bitmap crop = android.graphics.Bitmap.createBitmap(this.image, x, y, tileWidth, tileHeight);
            this.tiles.add(crop);
            x += tileWidth;

            if (x == RESIZE_WIDTH) {
                x = 0;
                y += tileHeight;
            }
        }
    }

    /**
     * Performs the Bitmap slicing and puts the resulting sliced images into ArrayList tiles
     */
    private void createCover() {
        int x = 0;
        int y = 0;

        int num_tiles = columns * rows;
        int tileWidth = RESIZE_WIDTH / columns;
        int tileHeight = RESIZE_HEIGHT / rows;

        for (int i = 0; i < num_tiles; i++) {
            Bitmap crop = android.graphics.Bitmap.createBitmap(this.cover,x,y,tileWidth,tileHeight);
            this.cover_tiles.add(crop);
            x += tileWidth;

            if (x == RESIZE_WIDTH) {
                x = 0;
                y += tileHeight;
            }
        }
    }

    /**
     * A getter for the save gamePath of the bitmap
     */
    public String getSaveGamePath() {
        return gamePath;
    }

    /**
     *
     */
    public String getSaveCoverPath() {return coverPath;}

    /**
     * Saves the sliced images to a directory within the internal files of the application
     *
     * @return true iff the save is a success
     */
    public boolean saveTiles(Context c) {
        File dir = new File(c.getFilesDir(), "/matchingtiles");
        if (dir.exists()) { dir.delete(); }
        dir.mkdir();
        gamePath = dir.getAbsolutePath();
        File cover_dir = new File(c.getFilesDir(), "/matchingtiles/covers");
        if (cover_dir.exists()) { cover_dir.delete(); }
        cover_dir.mkdir();
        coverPath = cover_dir.getAbsolutePath();

        int i = 1;
        for (Bitmap img : tiles) {
            File newImage = new File(dir,  String.format(Locale.CANADA, "matchingtile_%d.png", i));
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
        i = 1;
        for (Bitmap img : cover_tiles) {
            File newImage = new File(cover_dir,  String.format(Locale.CANADA, "cover_%d.png", i));
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