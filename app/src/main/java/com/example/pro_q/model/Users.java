package com.example.pro_q.model;

public class Users
{
    private String name,password,phone,emailID,image;

    public Users()
    {

    }

    public Users(String name, String password, String phone, String emailID, String image)
    {
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.emailID = emailID;
        this.image = image;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getEmailID()
    {
        return emailID;
    }

    public void setEmailID(String emailID)
    {
        this.emailID = emailID;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }
}
