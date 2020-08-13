package com.example.pro_q.model;

import java.util.HashMap;
import java.util.Map;

public class Appointments
{

    private String Sid, Sname, Saddress, Sdate, Stime, BookingID, Scoordinates, BookingStatus;
    private String Simage;

    public Appointments()
    {

    }

    public Appointments(String sid, String sname, String saddress, String sdate, String stime, String bookingID, String scoordinates, String bookingStatus, String simage)
    {
        Sid = sid;
        Sname = sname;
        Saddress = saddress;
        Sdate = sdate;
        Stime = stime;
        BookingID = bookingID;
        Scoordinates = scoordinates;
        BookingStatus = bookingStatus;
        Simage = simage;
    }

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public String getSaddress() {
        return Saddress;
    }

    public void setSaddress(String saddress) {
        Saddress = saddress;
    }

    public String getSdate() {
        return Sdate;
    }

    public void setSdate(String sdate) {
        Sdate = sdate;
    }

    public String getStime() {
        return Stime;
    }

    public void setStime(String stime) {
        Stime = stime;
    }

    public String getBookingID() {
        return BookingID;
    }

    public void setBookingID(String bookingID) {
        BookingID = bookingID;
    }

    public String getScoordinates() {
        return Scoordinates;
    }

    public void setScoordinates(String scoordinates) {
        Scoordinates = scoordinates;
    }

    public String getBookingStatus() {
        return BookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        BookingStatus = bookingStatus;
    }

    public String getSimage() {
        return Simage;
    }

    public void setSimage(String simage) {
        Simage = simage;
    }
}
