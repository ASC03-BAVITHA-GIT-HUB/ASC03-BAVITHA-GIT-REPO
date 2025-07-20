package com.labs.LAB2.MeetingRoomApplication;

public class ZoomMeetingRoom extends MeetingRoom {

    private String zoomDeviceId;
    private String zoomAccountId;

    public ZoomMeetingRoom(String roomId, int capacity, int floor, String zoomDeviceId, String zoomAccountId) {

        super(roomId, capacity, floor);

        this.zoomDeviceId = zoomDeviceId;
        this.zoomAccountId = zoomAccountId;
    }

    public String getZoomInfo() {
        return "Zoom Device id: " + zoomDeviceId + "  Zoom Account id: " + zoomAccountId;
    }

    @Override
    public String getRoomDetails() {
        return super.getRoomDetails() + " [Zoom: " + getZoomInfo() + "]";
    }
}
