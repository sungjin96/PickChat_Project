package com.example.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    Context context;
    ArrayList<ChatVO> array;
    String userid;

    public ChatAdapter(Context context, ArrayList<ChatVO> array,String userid) {
        this.context = context;
        this.array = array;
        this.userid=userid;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        LinearLayout.LayoutParams prmContent=(LinearLayout.LayoutParams)viewHolder.content.getLayoutParams();
        LinearLayout.LayoutParams prmDate=(LinearLayout.LayoutParams)viewHolder.wdate.getLayoutParams();

        if(userid==array.get(i).getUserid()){
            prmContent.gravity= Gravity.RIGHT;
            prmDate.gravity=Gravity.RIGHT;
        }else {
            prmContent.gravity=Gravity.LEFT;
            prmDate.gravity=Gravity.LEFT;
        }
        viewHolder.content.setLayoutParams(prmContent);
        viewHolder.wdate.setLayoutParams(prmDate);

        viewHolder.content.setText(array.get(i).getContent());
        viewHolder.wdate.setText(array.get(i).getWdate());
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView content, wdate,writer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            content=itemView.findViewById(R.id.content);
            wdate=itemView.findViewById(R.id.wdate);
        }
    }
}
