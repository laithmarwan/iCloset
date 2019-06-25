package com.example.icloset;

public class Item {

    private String ID;
    private String Type;
    private String Desc;
    private String Times_worn;
    private String Last_worn;
    private boolean Available;
    private int Thumbnail;

    public Item(){

    }

    public Item(String ID, String type, String desc, int thumbnail) {
        this.ID = ID;
        Type = type;
        Desc = desc;
        Thumbnail = thumbnail;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getTimes_worn() {
        return Times_worn;
    }

    public void setTimes_worn(String times_worn) {
        Times_worn = times_worn;
    }

    public String getLast_worn() {
        return Last_worn;
    }

    public void setLast_worn(String last_worn) {
        Last_worn = last_worn;
    }

    public boolean isAvailable() {
        return Available;
    }

    public void setAvailable(boolean available) {
        Available = available;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}
