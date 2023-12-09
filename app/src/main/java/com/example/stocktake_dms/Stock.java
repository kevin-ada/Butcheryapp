package com.example.stocktake_dms;

public class Stock {
    double price;
    double openingStock;

    double expenses;

    double closingStock;
    String date;
    double totalWaste;

    double actualStock;

    double buyingPrice;

    public Stock() {
    }

    public Stock(double price, double openingStock, String date, double totalWaste, double actualStock, double buyingPrice, double closingStock, double expenses) {
        this.price = price;
        this.openingStock = openingStock;
        this.date = date;
        this.totalWaste = totalWaste;
        this.actualStock = openingStock - closingStock - totalWaste;
        this.buyingPrice = buyingPrice;
        this.closingStock = closingStock;
        this.expenses = expenses;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public double getClosingStock() {
        return closingStock;
    }

    public void setClosingStock(double closingStock) {
        this.closingStock = closingStock;
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

    public double getOpeningStock() {
        return openingStock;
    }

    public void setOpeningStock(double openingStock) {
        this.openingStock = openingStock;
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
