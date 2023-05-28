package com.example.bimmer;

public class Item {
    private int imageResource;
    private String name;

    public Item(int imageResource, String name) {
        this.imageResource = imageResource;
        this.name = name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getName() {
        return name;
    }
}
