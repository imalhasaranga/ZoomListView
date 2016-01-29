package com.example.imal365.zoomablelistview.Objects;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.imal365.zoomablelistview.R;

import java.util.ArrayList;

/**
 * Created by imal365 on 1/22/2016.
 */
public class PageAdaptor extends ArrayAdapter<Page> {

    private Context context;
    private int resourceID;
    private ArrayList<Page> allpages;
    public static float ZoomFactor = 1.0f;

    public PageAdaptor(Context context, int resource, ArrayList<Page> allpages) {
        super(context, resource, allpages);

        this.context = context;
        this.resourceID = resource;
        this.allpages = allpages;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(resourceID, parent, false);
        LinearLayout pageimage = (LinearLayout)rowView.findViewById(R.id.singlepage);
        Page page = allpages.get(position);
        pageimage.setLayoutParams(new LinearLayout.LayoutParams((int)(page.n_width * ZoomFactor), (int)(page.n_height * ZoomFactor)));
        int id  = context.getResources().getIdentifier(page.getPageImageUrl(), "drawable", context.getPackageName());
        pageimage.setBackground(ContextCompat.getDrawable(context, id));
        if(page.getFields().size() > 0){
            for(InputField field : page.getFields()){
                ImageView viewim =  field.create(context, ZoomFactor);
                viewim.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"Clicked Value",Toast.LENGTH_LONG).show();
                    }
                });
                pageimage.addView(viewim);
            }
        }
        return rowView;
    }

}
