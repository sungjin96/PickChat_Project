package com.example.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class HWJ_HobbyAdapter extends RecyclerView.Adapter<HWJ_HobbyAdapter.ViewHolder> {
    Context context;
    List<HWJ_HobbyVO> array;

    public HWJ_HobbyAdapter(Context context, List<HWJ_HobbyVO> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_hwj_category, viewGroup, false);
        return new HWJ_HobbyAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.category.setText(array.get(i).getContent());
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
