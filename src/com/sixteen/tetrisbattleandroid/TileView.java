package com.sixteen.tetrisbattleandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;


/**
 * TileView: a View-variant designed for handling arrays of "icons" or other
 * drawables.
 * 
 */
public class TileView extends View {

    /**
     * Parameters controlling the size of the tiles and their range within view.
     * Width/Height are in pixels, and Drawables will be scaled to fit to these
     * dimensions. X/Y Tile Counts are the number of tiles that will be drawn.
     */

    protected static int mTileSize;

    protected static int mXTileCount;
    protected static int mYTileCount;

    private static int mXOffset;
    private static int mYOffset;


    /**
     * A hash that maps integer handles specified by the subclasser to the
     * drawable that will be used for that reference
     */
    private Bitmap[] mTileArray; 

    /**
     * A two-dimensional array of integers in which the number represents the
     * index of the tile that should be drawn at that locations
     */
    private int[][] mTileGrid;

    private final Paint mPaint = new Paint();

    public TileView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle); 
        initTileView();
    }

    public TileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTileView();
    }
    
    private void initTileView()
    {
        int w = getWidth();
        int h = getHeight();
        
        if(w>0 && h>0)
        {
                mXTileCount = 10;
                mYTileCount = 22;
                
                int xSize = (int) Math.floor(w / mXTileCount);
                int ySize = (int) Math.floor(h / (mYTileCount -2));
                
                
                
                mTileSize = Math.min(xSize, ySize);
              //  mTileSize = (int) Math.floor((xSize + ySize)/2);
                
                
            mXOffset = ((w - (mTileSize * mXTileCount)) / 2);
          //  mYOffset = ((h - (mTileSize * mYTileCount)) / 2);
        
            mYOffset = 0 - (mTileSize * 2);
            mTileGrid = new int[mXTileCount][mYTileCount];
            clearTiles();
        }
        else
        {
                mXTileCount = 0;
                mYTileCount = 0;
                mTileSize = 0;
        }        
        return;
    }
    
    /**
     * Rests the internal array of Bitmaps used for drawing tiles, and
     * sets the maximum index of tiles to be inserted
     * 
     * @param tilecount
     */
    
    public void resetTiles(int tilecount) {
        mTileArray = new Bitmap[tilecount];
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        
        initTileView();
    }

    /**
     * Function to set the specified Drawable as the tile for a particular
     * integer key.
     * 
     * @param key
     * @param tile
     */
    public void loadTile(int key, Drawable tile) {
        Bitmap bitmap = Bitmap.createBitmap(mTileSize, mTileSize, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        tile.setBounds(0, 0, mTileSize, mTileSize);
        tile.draw(canvas);
       
        
        mTileArray[key] = bitmap;
    }

    /**
     * Resets all tiles to 0 (empty)
     * 
     */
    public void clearTiles() {
        for (int x = 0; x < mXTileCount; x++) {
            for (int y = 0; y < mYTileCount; y++) {
                setTile(0, x, y);
            }
        }
    }

    /**
     * Used to indicate that a particular tile (set with loadTile and referenced
     * by an integer) should be drawn at the given x/y coordinates during the
     * next invalidate/draw cycle.
     * 
     * @param tileindex
     * @param x
     * @param y
     */
    public void setTile(int tileindex, int x, int y) {
    	
    		mTileGrid[x][y] = tileindex;
    		
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int x = 0; x < mXTileCount; x += 1) {
            for (int y = 0; y < mYTileCount; y += 1) {
                if (mTileGrid[x][y] >= 0) {
                	
                    canvas.drawBitmap(mTileArray[mTileGrid[x][y]], 
                                mXOffset + x * mTileSize,
                                mYOffset + y * mTileSize,
                                mPaint);
                }
            }
        }

    }

}
