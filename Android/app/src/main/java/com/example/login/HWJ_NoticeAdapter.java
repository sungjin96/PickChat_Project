package com.example.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class HWJ_NoticeAdapter extends RecyclerView.Adapter<HWJ_NoticeAdapter.ViewHolder> {
    Context context;
    List<HWJ_NoticeVO> array;

    public HWJ_NoticeAdapter(Context context, List<HWJ_NoticeVO> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hwj_notice, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {
        holder.title.setText(array.get(i).getTitle());
        holder.content.setText(array.get(i).getContent());

        //공지사항 버튼 설정
        holder.btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.content.getVisibility()!=View.VISIBLE){
                    holder.content.setVisibility(View.VISIBLE);
                    holder.btnView.setImageResource(R.drawable.icon_top);
                }else{
                    holder.content.setVisibility(View.GONE);
                    holder.btnView.setImageResource(R.drawable.icon_down);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, content;
        ImageView btnView;
        RelativeLayout btnAll;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            btnView = itemView.findViewById(R.id.btnView);
            btnAll = itemView.findViewById(R.id.btnAll);
        }
    }
}