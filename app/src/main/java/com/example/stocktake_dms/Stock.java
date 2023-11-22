package com.example.stocktake_dms;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Stock {
    private String itemname;
    private String itemcategory;
    private String itemprice;
    private String itemquantity;

    public Stock() {
    }

    public Stock(String itemname, String itemcategory, String itemprice, String itemquantity) {
        this.itemname = itemname;
        this.itemcategory = itemcategory;
        this.itemprice = itemprice;
        this.itemquantity = itemquantity;
    }

    public String getItemname() {
        return itemname;
    }

    public String getItemcategory() {
        return itemcategory;
    }

    public String getItemprice() {
        return itemprice;
    }

    public String getItemquantity() {
        return itemquantity;
    }
}
