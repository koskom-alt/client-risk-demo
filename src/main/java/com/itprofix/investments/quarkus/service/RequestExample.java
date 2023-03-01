package com.itprofix.investments.quarkus.service;

public class RequestExample {
    String id;
    String color;
    int size;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "RequestExample{" +
                "id='" + id + '\'' +
                ", color='" + color + '\'' +
                ", size=" + size +
                '}';
    }
}
