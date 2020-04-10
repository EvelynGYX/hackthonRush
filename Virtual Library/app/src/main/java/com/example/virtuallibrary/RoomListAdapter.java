package com.example.virtuallibrary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RoomListAdapter extends RecyclerView.Adapter<RoomListAdapter.ViewHolder> {

    private List<RoomInfo> roomList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView roomName;
        TextView number;
        TextView subject;
        TextView task;

        public ViewHolder(View view){
            super(view);
            roomName = view.findViewById(R.id.room_list_name);
            number = view.findViewById(R.id.room_list_number);
        }

    }

    public RoomListAdapter(List<RoomInfo> roomList){
        this.roomList = roomList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.room_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RoomInfo room = roomList.get(position);
        holder.roomName.setText(room.getRoomName());
        holder.number.setText(room.getNumberOfUsers());
        holder.subject.setText(room.getSubject());
        holder.task.setText(room.getTask());
    }


    @Override
    public int getItemCount() {
        return roomList.size();
    }
}
