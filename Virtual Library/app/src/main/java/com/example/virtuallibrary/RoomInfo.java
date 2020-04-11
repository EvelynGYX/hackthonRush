package com.example.virtuallibrary;


public class RoomInfo {

    private String roomID;
    private String roomName;
    private String numberOfUsers;
    private String roomType;
    private String subject;
    private String task;

    public RoomInfo(String roomID, String roomName, String numberOfUsers,
                    String roomType, String subject, String task){
        this.roomID = roomID;
        this.roomName = roomName;
        this.numberOfUsers = numberOfUsers;
        this.roomType = roomType;
        this.subject = subject;
        this.task = task;
    }

    public String getRoomID(){
        return roomID;
    }

    public String getRoomName(){
        return roomName;
    }

    public String getNumberOfUsers(){
        return numberOfUsers;
    }

    public String getRoomType(){
        return roomType;
    }

    public String getSubject(){
        return subject;
    }

    public String getTask(){
        return task;
    }

}
