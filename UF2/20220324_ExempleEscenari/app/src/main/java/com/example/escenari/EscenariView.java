package com.example.escenari;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.Nullable;

public class EscenariView extends View implements ViewTreeObserver.OnGlobalLayoutListener
{
    Paint p;
    Bitmap background;
    Bitmap personatgeSprite;


    int alsadaPersonatge;
    int ampladaPersonatge;
    Rect srcBackground;

    Point personatge;
    int frame=0;

    public EscenariView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Paint p = new Paint();
        background = BitmapFactory.decodeResource(getResources(), R.drawable.back);
        personatgeSprite = BitmapFactory.decodeResource(getResources(), R.drawable.sprite);
        srcBackground = new Rect(278,2015, 518, 2191);

        getViewTreeObserver().addOnGlobalLayoutListener(this);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Rect dstBackground = new Rect(0,0, getWidth(), getHeight());
        canvas.drawBitmap(background,srcBackground, dstBackground, p);

        //-----------------------------------------------------
        if(personatge!=null) {
            Rect dstPersonatge = new Rect(personatge.x, personatge.y,
                    personatge.x + ampladaPersonatge,
                    personatge.y + alsadaPersonatge);
            canvas.drawBitmap(personatgeSprite, getSrcPersonatge(), dstPersonatge, p);
        }
    }

    public void onClick(EscenariView escenari) {
        if(personatge!=null){
            personatge.x += 10;
            frame=(frame+1)%5;
            invalidate();

        }
    }

    public Rect getSrcPersonatge(){
        Rect srcPersonatge = new Rect(10,2   , 41, 33);
        int width = srcPersonatge.width();
        int margin = 3;
        srcPersonatge.left = srcPersonatge.left + frame *(width+margin);
        srcPersonatge.right = srcPersonatge.left +  width;
        return srcPersonatge;
    }



    @Override
   public void onGlobalLayout() {
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
        //-------------------------------

        Rect srcPersonatge = getSrcPersonatge();
        alsadaPersonatge = getHeight() / 6;
        ampladaPersonatge =(int)( alsadaPersonatge * (srcPersonatge.width() / (float)srcPersonatge.height()));

        personatge = new Point();
        personatge.y=getHeight()-alsadaPersonatge;
        personatge.x = (getWidth()-ampladaPersonatge)/2;

        invalidate();

    }

    public static Bitmap createFlippedBitmap(Bitmap source, boolean xFlip, boolean yFlip) {
        Matrix matrix = new Matrix();
        matrix.postScale(xFlip ? -1 : 1, yFlip ? -1 : 1, source.getWidth() / 2f, source.getHeight() / 2f);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }
}
