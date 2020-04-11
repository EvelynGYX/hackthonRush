package com.example.virtuallibrary;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RoomList extends AppCompatActivity {

    private List<RoomInfo> roomList = new ArrayList<>();
    private OkHttpClient client;
    private RoomListAdapter adapter;
    private Button create_room_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getRoomList();
        setContentView(R.layout.activity_room_list);
        create_room_btn = findViewById(R.id.create_room);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.room_list_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter= new RoomListAdapter(roomList);
        recyclerView.setAdapter(adapter);

        create_room_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RoomList.this, "add", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RoomList.this, CreateRoomActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getRoomList(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url("https://techtailors.sytes.net:8400/rooms/listall")
                            .build();
                    client.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                            Toast.makeText(RoomList.this, "Network issue, try it later", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            Log.d("fkkkk", "run: ");
                            String responseData = response.body().string();
                            parseJSONWithJsonObject(responseData);

                        }
                    });

                }catch (Exception e){
                    Log.d("HTTP request", "run: ");
                }
            }
        }).start();
    }

    private void parseJSONWithJsonObject(String jsonData){
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i=0; i < jsonArray.length(); i++){
                Log.d("loop", Integer.toString(i));
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String roomName = jsonObject.getString("title");
                String roomType = jsonObject.getString("roomType");
                String subject = jsonObject.getString("subject");
                String task = jsonObject.getString("tasks");
                JSONArray users = jsonObject.getJSONArray("users");
                String numberOfUser = Integer.toString(users.length());
                RoomInfo room = new RoomInfo(roomName, numberOfUser, roomType, subject, task);
                roomList.add(room);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
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
