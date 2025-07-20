package com.labs.LAB2.MeetingRoomApplication;

import java.time.LocalDate;
import java.time.LocalTime;

public class Main {

    public static void main(String[]args) {


        MeetingRoom normalRoom = new MeetingRoom("1", 30, 4);
        ZoomMeetingRoom zoomRoom = new ZoomMeetingRoom("2", 20, 5, "Zoom123", "ZoomCompany.com");
        BookingManager manager = new BookingManager();
        manager.bookRoom("Emp1", normalRoom, LocalDate.of(2025, 12, 23), LocalTime.of(12, 59, 59), 60);
        manager.bookRoom("Emp2", zoomRoom, LocalDate.of(2025, 12, 24), LocalTime.of(12, 59, 59), 60);

        manager.getBookingByDate((LocalDate.of(2025,12,23)));
    }
}
