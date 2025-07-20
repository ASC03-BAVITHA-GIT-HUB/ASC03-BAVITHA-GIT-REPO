package com.labs.LAB2.MeetingRoomApplication;

public class MeetingRoom {
    private String roomId;
    private int capacity;
    private int floor;

    public MeetingRoom(String meetingId , int capacity , int floor) {
        this.roomId = meetingId;
        this.capacity = capacity;
        this.floor = floor;


    }

    public String getRoomId() {
        return roomId;
    }

    public int getCapacity() {
        return capacity;

    }

    public int getFloor() {
        return floor;
    }

    public String getRoomDetails() {
        return "  Room id: " + roomId + "  Capacity: " + capacity + "  Floor: " + floor;
    }
}
