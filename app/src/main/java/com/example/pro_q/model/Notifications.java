package com.example.pro_q.model;

public class Notifications
{
    private String NotificationTitle, NotificationSlogan, NotificationContent, NotificationDate, NotificationTime;
    private String NotificationImg;

    public Notifications()
    {

    }

    public Notifications(String notificationTitle, String notificationSlogan, String notificationContent, String notificationDate, String notificationTime, String notificationImg)
    {
        NotificationTitle = notificationTitle;
        NotificationSlogan = notificationSlogan;
        NotificationContent = notificationContent;
        NotificationDate = notificationDate;
        NotificationTime = notificationTime;
        NotificationImg = notificationImg;
    }

    public String getNotificationTitle() {
        return NotificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        NotificationTitle = notificationTitle;
    }

    public String getNotificationSlogan() {
        return NotificationSlogan;
    }

    public void setNotificationSlogan(String notificationSlogan) {
        NotificationSlogan = notificationSlogan;
    }

    public String getNotificationContent() {
        return NotificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        NotificationContent = notificationContent;
    }

    public String getNotificationDate() {
        return NotificationDate;
    }

    public void setNotificationDate(String notificationDate) {
        NotificationDate = notificationDate;
    }

    public String getNotificationTime() {
        return NotificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        NotificationTime = notificationTime;
    }

    public String getNotificationImg() {
        return NotificationImg;
    }

    public void setNotificationImg(String notificationImg) {
        NotificationImg = notificationImg;
    }
}
