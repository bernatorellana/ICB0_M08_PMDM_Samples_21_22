package com.example.paintingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class DemoView extends View {

    public DemoView(Context context) {
        super(context);
    }

    public DemoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        p = new Paint();
        p.setARGB(255,0,0,0);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(10);

    }

    Paint p;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        // 1) netejar / pintar tota la pantalla
        canvas.drawARGB(255,255,0,0);
        // 2) primitives bàsiques
        //  2.1) punt
        p.setStrokeWidth(40);
        canvas.drawPoint( 100,100, p);
        //  2.2) linia
        p.setStrokeWidth(10);
        canvas.drawLine(0,0,200,200,p);
        //  2.3) quadrats
        canvas.drawRect(0.f,0.f, getWidth(), getHeight(), p);
        //  2.4) el·lipses
        canvas.drawOval(0.f,0.f, getWidth(), getHeight(), p);
        //  2.5) corbes - "paths"
        Path path = new Path();
        path.setLastPoint(0,400);
        path.lineTo(200,100);
        path.lineTo(500,100);
        path.cubicTo( getWidth()/2,getHeight()/2,
                getWidth(), getHeight()/2,
                getWidth(), 0);
        canvas.drawPath(path, p);
        //  2.6) imatges
        Bitmap mario = BitmapFactory.decodeResource(
                        getResources() ,
                R.drawable.mario);
        Rect src = new Rect(0,0,mario.getWidth(), mario.getHeight()/2);
        Rect dst = new Rect(0,this.getHeight()/2    ,this.getWidth(), this.getHeight());
        canvas.drawBitmap(mario,src,dst, p );

    }
}
