package com.example.login;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class hobbyAdapter extends RecyclerView.Adapter<hobbyAdapter.ViewHolder> {
    Context context;
    List<hobby_categoryVO> array;
    hobby_categoryVO vo;
    int count=0;

    public hobbyAdapter(Context context, List<hobby_categoryVO> array) {
        this.context = context;
        this.array = array;

        for(int i =0;i<array.size();i++){
            array.get(i).setCheck(false);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,  int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.hobbyitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.category.setText(array.get(i).getContent());
        viewHolder.category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vo = array.get(i);
                if (vo.isCheck()) {
                    viewHolder.category.setBackgroundResource(R.drawable.button1);
                    vo.setCheck(false);
                    count--;

                } else {
                    viewHolder.category.setBackgroundResource(R.drawable.button);
                    vo.setCheck(true);
                    count++;

                }
                if (count == 3) {
                    Intent intent = new Intent(context, HobbyActivity.class);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView category;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            category = itemView.findViewById(R.id.category);
        }
    }
}
