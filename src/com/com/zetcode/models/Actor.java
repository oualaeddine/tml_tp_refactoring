package com.zetcode.models;

import javax.swing.*;
import java.awt.Image;

public class Actor {


    private int x;
    private int y;
    private Image image;

    protected Actor(int x, int y) {

        this.x = x;
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    void setImage(Image img) {
        image = img;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    protected void setX(int x) {
        this.x = x;
    }

    protected void setY(int y) {
        this.y = y;
    }

    protected void initImage(String uri) {
        ImageIcon iicon = new ImageIcon(uri);
        setImage(iicon.getImage());
    }

}
