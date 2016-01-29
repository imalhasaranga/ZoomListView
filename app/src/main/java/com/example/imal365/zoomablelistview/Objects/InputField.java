package com.example.imal365.zoomablelistview.Objects;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by imal365 on 1/21/2016.
 */
public class InputField {

    public int width;
    public int height;
    public int x;
    public int y;
    public Context cont;
    String fieldImageUrl;
    public ImageView fiedlImage;



    public InputField(String fieldImageUrl, int width, int height, int x, int y){
        //for now fieldimage url is just drawable
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fieldImageUrl = fieldImageUrl;


    }

    public ImageView create(final Context cont, float zoomFactor){
        this.cont = cont;
        fiedlImage = new ImageView(cont);
        int id  =cont.getResources().getIdentifier(fieldImageUrl, "drawable", cont.getPackageName());
        fiedlImage.setImageDrawable(ContextCompat.getDrawable(cont, id));
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(recal(width,zoomFactor), recal(height,zoomFactor));
        fiedlImage.setLayoutParams(layoutParams);
        fiedlImage.setX(recal(x,zoomFactor));
        fiedlImage.setY(recal(y,zoomFactor));
        fiedlImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(cont, "Clicked", Toast.LENGTH_LONG).show();
            }
        });
        return fiedlImage;
    }

    public int recal(int previous, float zoom){
        return (int)(previous * zoom);
    }


}
