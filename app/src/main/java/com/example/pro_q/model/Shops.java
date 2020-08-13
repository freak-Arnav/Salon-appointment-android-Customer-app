package com.example.pro_q.model;

public class Shops
{
    private String Sname;
    private String Sfulladdress;
    private String Saddress;
    private String Image1;
    private String Image2;
    private String Image3;
    private String Srating;
    private String Sdepartments;
    private String Gender;
    private String Scoordinates;
    private String Sid;
    private String Sbrands;
    private String sclass;
    private String scontacts;
    private String Sworkingdays;
    private String Stiming;
    private String BookingID;
    private String Swifi;


    public Shops()
    {

    }

    public Shops(String sname, String sfulladdress, String saddress, String image1, String image2, String image3, String srating, String sdepartments, String gender, String scoordinates, String sid, String sbrands, String sclass, String scontacts, String sworkingdays, String stiming, String bookingID, String swifi)
    {
        Sname = sname;
        Sfulladdress = sfulladdress;
        Saddress = saddress;
        Image1 = image1;
        Image2 = image2;
        Image3 = image3;
        Srating = srating;
        Sdepartments = sdepartments;
        Gender = gender;
        Scoordinates = scoordinates;
        Sid = sid;
        Sbrands = sbrands;
        this.sclass = sclass;
        this.scontacts = scontacts;
        Sworkingdays = sworkingdays;
        Stiming = stiming;
        BookingID = bookingID;
        Swifi = swifi;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSfulladdress() {
        return Sfulladdress;
    }

    public void setSfulladdress(String sfulladdress) {
        Sfulladdress = sfulladdress;
    }

    public String getSaddress() {
        return Saddress;
    }

    public void setSaddress(String saddress) {
        Saddress = saddress;
    }

    public String getImage1() {
        return Image1;
    }

    public void setImage1(String image1) {
        Image1 = image1;
    }

    public String getImage2() {
        return Image2;
    }

    public void setImage2(String image2) {
        Image2 = image2;
    }

    public String getImage3() {
        return Image3;
    }

    public void setImage3(String image3) {
        Image3 = image3;
    }

    public String getSrating() {
        return Srating;
    }

    public void setSrating(String srating) {
        Srating = srating;
    }

    public String getSdepartments() {
        return Sdepartments;
    }

    public void setSdepartments(String sdepartments) {
        Sdepartments = sdepartments;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getScoordinates() {
        return Scoordinates;
    }

    public void setScoordinates(String scoordinates) {
        Scoordinates = scoordinates;
    }

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    public String getSbrands() {
        return Sbrands;
    }

    public void setSbrands(String sbrands) {
        Sbrands = sbrands;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public String getScontacts() {
        return scontacts;
    }

    public void setScontacts(String scontacts) {
        this.scontacts = scontacts;
    }

    public String getSworkingdays() {
        return Sworkingdays;
    }

    public void setSworkingdays(String sworkingdays) {
        Sworkingdays = sworkingdays;
    }

    public String getStiming() {
        return Stiming;
    }

    public void setStiming(String stiming) {
        Stiming = stiming;
    }

    public String getBookingID() {
        return BookingID;
    }

    public void setBookingID(String bookingID) {
        BookingID = bookingID;
    }

    public String getSwifi() {
        return Swifi;
    }

    public void setSwifi(String swifi) {
        Swifi = swifi;
    }
}
