package com.example.virtuallibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RoomList extends AppCompatActivity {

    private List<RoomInfo> roomList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initRoomList();
        setContentView(R.layout.activity_room_list);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.room_list_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        RoomListAdapter adapter = new RoomListAdapter(roomList);
        recyclerView.setAdapter(adapter);
    }

    private void getRoomList(){

    }

//    private void initRoomList(){
//        for (int i=0; i<6; i++){
//            RoomInfo room1 = new RoomInfo("1", "COMP10001", "1");
//            roomList.add(room1);
//            RoomInfo room2 = new RoomInfo("1", "COMP10002", "2");
//            roomList.add(room2);
//            RoomInfo room3 = new RoomInfo("1", "COMP10003", "3");
//            roomList.add(room3);
//            RoomInfo room4 = new RoomInfo("1", "COMP10004", "4");
//            roomList.add(room4);
//            RoomInfo room5 = new RoomInfo("1", "COMP10001", "1");
//            roomList.add(room5);
//            RoomInfo room6 = new RoomInfo("1", "COMP10002", "2");
//            roomList.add(room6);
//            RoomInfo room7 = new RoomInfo("1", "COMP10003", "3");
//            roomList.add(room7);
//            RoomInfo room8 = new RoomInfo("1", "COMP10004", "4");
//            roomList.add(room8);
//        }

//    }
}
