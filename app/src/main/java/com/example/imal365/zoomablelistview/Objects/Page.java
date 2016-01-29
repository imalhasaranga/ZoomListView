package com.example.imal365.zoomablelistview.Objects;

import java.util.ArrayList;

/**
 * Created by imal365 on 1/21/2016.
 */
public class Page {

    private ArrayList<InputField> fields = new ArrayList<>();
    private String pageImageUrl;
    public int width;
    public int height;

    public float n_width;
    public float n_height;

    public Page(String pageImageUrl){}
    public Page(String pageImageUrl, ArrayList<InputField> fields){
        this.setPageImageUrl(pageImageUrl);
        this.fields = fields;
    }

    public void setwidthHeight(int width, int height, int ScreenWidth, int ScreenHeight){
        this.width = width;
        this.height = height;

        this.n_width = ScreenWidth;
        this.n_height = ((ScreenHeight * 1.0f) / ScreenWidth ) * this.n_width;
        for(InputField field : fields){
            field.x = Math.round((this.n_width / this.width) *field.x);
            field.width = Math.round((this.n_width / this.width) *field.width);
            field.y = Math.round((this.n_height / this.height) *field.y);
            field.height = Math.round((this.n_height / this.height) *field.height);
        }
    }


    public ArrayList<InputField> getFields(){
        return  fields;
    }

    public String getPageImageUrl() {
        return pageImageUrl;
    }

    public void setPageImageUrl(String pageImageUrl) {
        this.pageImageUrl = pageImageUrl;
    }
}
