package com.example.stocktake_dms;

public class Stock {
    double price;
    double totalStock;
    String date;
    double totalWaste;

    double actualStock;

    double buyingPrice;

    public Stock() {
    }

    public Stock(double price, double totalStock, String date, double totalWaste, double actualStock, double buyingPrice) {
        this.price = price;
        this.totalStock = totalStock;
        this.date = date;
        this.totalWaste = totalWaste;
        this.actualStock = totalStock - totalWaste;
        this.buyingPrice = buyingPrice;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(double totalStock) {
        this.totalStock = totalStock;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalWaste() {
        return totalWaste;
    }

    public void setTotalWaste(double totalWaste) {
        this.totalWaste = totalWaste;
    }

    public double getActualStock() {
        return actualStock;
    }

    public void setActualStock(double actualStock) {
        this.actualStock = actualStock;
    }
}
