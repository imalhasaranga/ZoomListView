/*
* Zoomable Listview which is not using the Canvas but resizing the entair element set
* According to a Zoom factor.
* See the Sample Activity for how to implement, ZoomListView is only responsible of saying
* how much the all the element hierarchy should zoom based on that zooming impelentation should be handled
* like the sample
* */



package com.example.imal365.zoomablelistview;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;

import com.example.imal365.zoomablelistview.CustomElements.CustomHorizontalScrollView;
import com.example.imal365.zoomablelistview.CustomElements.ZoomListView;
import com.example.imal365.zoomablelistview.Objects.InputField;
import com.example.imal365.zoomablelistview.Objects.Page;
import com.example.imal365.zoomablelistview.Objects.PageAdaptor;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    CustomHorizontalScrollView scrollview;
    ZoomListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int ScreenWidth = size.x;
        int ScreenHeight = size.y;

        scrollview = (CustomHorizontalScrollView)findViewById(R.id.horizontascroll);
        listView  = (ZoomListView)findViewById(R.id.pages);
        listView.setCustomHorizontalScrollView(scrollview);

        listView.setPinchScaleListner(new ZoomListView.PinchZoomListner() {
            @Override
            public void onPinchZoom(float zoom) {
                PageAdaptor.ZoomFactor = zoom;
                listView.invalidateViews();
            }

            @Override
            public void onPinchEnd() {
                System.out.println("On Pinch End");
            }

            @Override
            public void onPinchStarted() {
                System.out.println("On Pinch Started");
            }
        });

        ArrayList<Page> pages  = new ArrayList<>();

        ArrayList<InputField> fields = new ArrayList<>();
        fields.add(new InputField("examplefield",100, 50, 100,100));
        fields.add(new InputField("examplefield",100, 50, 150,150));
        Page p = new Page("exampledocpage",fields);
        p.setwidthHeight(600,1800,ScreenWidth, ScreenHeight);

        Page p2 = new Page("exampledocpage",new ArrayList<InputField>());
        Page p3 = new Page("exampledocpage",new ArrayList<InputField>());



        p2.setwidthHeight(600,1800,ScreenWidth, ScreenHeight);
        p3.setwidthHeight(600, 1800, ScreenWidth, ScreenHeight);
        pages.add(p);
        pages.add(p2);
        pages.add(p3);
        for(int i = 0; i< 100; ++i){
            Page px = new Page("exampledocpage",new ArrayList<InputField>());
            px.setwidthHeight(600,1800,ScreenWidth, ScreenHeight);
            pages.add(px);
        }

        PageAdaptor pagesAdaptor = new PageAdaptor(this, R.layout.page, pages);
        listView.setAdapter(pagesAdaptor);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
