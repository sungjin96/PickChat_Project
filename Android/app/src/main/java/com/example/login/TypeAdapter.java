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

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.ViewHolder> {
    Context context;
    List<type_categoryVO> array;
    type_categoryVO vo;
    int count = 0;

    public TypeAdapter(Context context, List<type_categoryVO> array) {
        this.context = context;
        this.array = array;

        for (int i = 0; i < array.size(); i++) {
            array.get(i).setCheck(false);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_jyb, parent, false);

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
                   // count--;

                    System.out.println(vo.isCheck() + "," + vo.getTypeid() + "," + R.id.title);
                } else {
                    viewHolder.category.setBackgroundResource(R.drawable.button);
                    vo.setCheck(true);
                    //count++;

                    System.out.println(vo.isCheck() + "," + vo.getTypeid() + "," + R.id.title);
                }
//                if (count == 3) {
//                    Intent intent = new Intent(context, HobbyActivity.class);
//                    context.startActivity(intent);
//
//                }
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
