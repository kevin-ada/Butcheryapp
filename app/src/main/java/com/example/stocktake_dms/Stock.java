package com.example.stocktake_dms;

public class Stock {

    String Date, Category, WasteStock, actualStock, totalPrice;

    public Stock() {

    }

    public Stock(String date, String category, String wasteStock, String actualStock, String totalPrice) {
        Date = date;
        Category = category;
        WasteStock = wasteStock;
        this.actualStock = actualStock;
        this.totalPrice = totalPrice;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getWasteStock() {
        return WasteStock;
    }

    public void setWasteStock(String wasteStock) {
        WasteStock = wasteStock;
    }

    public String getActualStock() {
        return actualStock;
    }

    public void setActualStock(String actualStock) {
        this.actualStock = actualStock;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }
}
