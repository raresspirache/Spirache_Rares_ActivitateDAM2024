package com.example.seminar_4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.view.View;

import androidx.annotation.NonNull;

public class Desenare extends View
{

    public Desenare(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas){

        super.onDraw(canvas);

        Paint marker=new Paint();
        marker.setColor(Color.GREEN);
        marker.setStrokeWidth(10);
        marker.setStyle(Paint.Style.STROKE);
        canvas.drawLine(0,0,300,300,marker);

        marker.setColor(Color.BLUE);
        canvas.drawRect(300,0,600,300,marker);
        marker.setAlpha(100);

        LinearGradient gradient=new LinearGradient(0,20,40,20,Color.BLUE,Color.RED, Shader.TileMode.MIRROR);
        marker.setShader(gradient);
        marker.setColor(Color.RED);
        marker.setStyle(Paint.Style.FILL);
        canvas.drawCircle(760,150,150,marker);

        marker.setColor(Color.YELLOW);
        marker.setStrokeWidth(2);
        marker.setTextAlign(Paint.Align.CENTER);
        marker.setTextSize(100);
        canvas.drawText("TEXT",600,450,marker);

        Path cale=new Path();
        //cale.addCircle(600,900,300,Path.Direction.CW);
        cale.addArc(400,600,900,1050,180,180);
        canvas.drawTextOnPath("Happy new year!",cale,0,0,marker);

        Paint pensula=new Paint();
        pensula.setColor(Color.YELLOW);
        canvas.drawArc(200,1300,700,1800,30,75,true,pensula);

    }


}
