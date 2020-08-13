package com.example.pro_q.model;

public class ShopServices
{
    private String servicesName;
    private String servicesPrice;

    public ShopServices(String servicesName, String servicesPrice)
    {
        this.servicesName = servicesName;
        this.servicesPrice = servicesPrice;
    }

    public String getServicesName() {
        return servicesName;
    }

    public void setServicesName(String servicesName) {
        this.servicesName = servicesName;
    }

    public String getServicesPrice() {
        return servicesPrice;
    }

    public void setServicesPrice(String servicesPrice) {
        this.servicesPrice = servicesPrice;
    }
}