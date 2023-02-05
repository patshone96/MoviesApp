package com.example.u6_ae1_oliverbaidezpatrick;

import java.io.Serializable;

public class Film implements Serializable {

    //Clase serializable película. Contiene todos los datos necesarios para generar un elemento película
    private String title;
    private String desc;
    private int image;
    private int year;
    private boolean fav;


    public Film(){

    }

    public Film(String title, String desc, int image, int year, boolean fav) {
        this.title = title;
        this.desc = desc;
        this.image = image;
        this.year = year;
        this.fav = fav;
    }


    public int getYear() {
        return year;
    }

    public boolean getFav() {
        return fav;
    }

    public void setFav() {
        if(fav){
            fav=false;
        }else{
            fav = true;
        };
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {

        if(title.length()>20){
            return title.substring(0, 20);
        }
        return title;
    }

    public String getFullTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {

        if(desc.length()>40){
            return desc.substring(0,40);
        }
        return desc;

    }

    public String getFullDesc(){
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
