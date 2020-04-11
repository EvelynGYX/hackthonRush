package com.example.virtuallibrary;

import androidx.annotation.Nullable;
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

public class RoomListActivity extends AppCompatActivity {

    private List<RoomInfo> roomList = new ArrayList<>();
    private OkHttpClient client;
    private RoomListAdapter adapter;
    private Button create_room_btn;
    private String userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getRoomList();
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
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
                Intent intent = new Intent(RoomListActivity.this, CreateRoomActivity.class);
                intent.putExtra("username", userName);
//                startActivity(intent);
                startActivityForResult(intent, 1);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    getRoomList();
                    Toast.makeText(RoomListActivity.this, "You created a new room", Toast.LENGTH_SHORT).show();
                }
        }
    }
}
