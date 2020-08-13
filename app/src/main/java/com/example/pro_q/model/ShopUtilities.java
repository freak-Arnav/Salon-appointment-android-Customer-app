package com.example.pro_q.model;

public class ShopUtilities
{
    private String utilityName;
    private String utilityValue;

    public ShopUtilities(String utilityName, String utilityValue)
    {
        this.utilityName = utilityName;
        this.utilityValue = utilityValue;
    }

    public String getUtilityName()
    {
        return utilityName;
    }

    public void setUtilityName(String utilityName)
    {
        this.utilityName = utilityName;
    }

    public String getUtilityValue()
    {
        return utilityValue;
    }

    public void setUtilityValue(String utilityValue)
    {
        this.utilityValue = utilityValue;
    }
}
