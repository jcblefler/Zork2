package com.company;

public class Room {

    private int roomNumber;
    private double roomMoney;
    private String roomName;
    private String roomContent;
    private String direction;

    public Room() {

    }

    public Room(String roomName, String roomContent, String direction, double roomMoney) {
        this.roomName = roomName;
        this.roomContent = roomContent;
        this.direction = direction;
        this.roomMoney = roomMoney;
    }


    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getRoomMoney() {
        return roomMoney;
    }

    public void setRoomMoney(double roomMoney) {
        this.roomMoney = roomMoney;
    }


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomContent() {
        return roomContent;
    }

    public void setRoomContent(String roomContent) {
        this.roomContent = roomContent;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
