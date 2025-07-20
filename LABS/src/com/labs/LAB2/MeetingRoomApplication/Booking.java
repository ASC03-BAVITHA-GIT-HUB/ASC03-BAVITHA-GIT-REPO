package com.labs.LAB2.MeetingRoomApplication;


import java.time.LocalDate;
import java.time.LocalTime;

public class Booking {

    private String employeeId;
    private MeetingRoom room;
    private LocalDate date;
    private LocalTime time;
    private int durationInMinutes;

    public Booking(String employeeId, MeetingRoom room, LocalDate date, LocalTime time, int durationInMinutes) {
        this.employeeId = employeeId;
        this.room = room;
        this.date = date;
        this.time = time;
        this.durationInMinutes = durationInMinutes;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDetails() {
        return "Booking for " + employeeId + "in" + room.getRoomDetails() + " for" + durationInMinutes + " minutes";
    }
}
