package com.example.login;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class HWJ_Like1Adapter extends RecyclerView.Adapter<HWJ_Like1Adapter.ViewHolder> {
    Context context;
    List<HWJ_LikeVO> array;

    public HWJ_Like1Adapter(Context context, List<HWJ_LikeVO> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hwj_like1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //텍스트 적용
        holder.usernickname.setText(array.get(position).getUsernickname());
        holder.userage.setText("나이 : " + array.get(position).getUserage());
        holder.userjob.setText("직업 : " + array.get(position).getUserjob());
        holder.localname.setText("사는곳 : " + array.get(position).getLocalname());

        System.out.println(array.get(position).getUserage() + array.get(position).getUsernickname());

        //이미지 적용
        Picasso.with(context)
                .load(array.get(position).getSoloimg())
                .into(holder.soloimg);

        //행을 눌렀을 때 적용
        holder.btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HWJ_OtherActivity.class);
                intent.putExtra("id", array.get(position).getUserid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout btnAll;
        ImageView soloimg;
        TextView usernickname, userage, userjob, localname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            soloimg = itemView.findViewById(R.id.soloimg);
            usernickname = itemView.findViewById(R.id.usernickname);
            userage = itemView.findViewById(R.id.userage);
            userjob = itemView.findViewById(R.id.userjob);
            localname = itemView.findViewById(R.id.localname);
            btnAll = itemView.findViewById(R.id.btnAll);

            //사용자 프로필 사진 동그랗게
            soloimg.setBackground(new ShapeDrawable(new OvalShape()));
            soloimg.setClipToOutline(true);
        }
    }
}