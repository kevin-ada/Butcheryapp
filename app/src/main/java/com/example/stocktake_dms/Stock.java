package com.example.stocktake_dms;

public class Stock {

    String Date;
    double Category;
    double WasteStock;
    double actualStock;
    double totalPrice;

    public Stock() {
    }

    public Stock(String date, double category, double wasteStock, double actualStock, double totalPrice) {
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

    public double getCategory() {
        return Category;
    }

    public void setCategory(double category) {
        Category = category;
    }

    public double getWasteStock() {
        return WasteStock;
    }

    public void setWasteStock(double wasteStock) {
        WasteStock = wasteStock;
    }

    public double getActualStock() {
        return actualStock;
    }

    public void setActualStock(double actualStock) {
        this.actualStock = actualStock;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

}

