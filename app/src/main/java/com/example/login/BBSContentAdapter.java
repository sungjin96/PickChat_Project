package com.example.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BBSContentAdapter extends RecyclerView.Adapter<BBSContentAdapter.ViewHolder> {

    Context context;
    List<BBSimgVO> array;

    public BBSContentAdapter(Context context, List<BBSimgVO> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.bbs_content_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.content.setText(array.get(i).getContent());
        viewHolder.rWriter.setText(array.get(i).getWriter());
        Date bbsDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년MM월dd일");
        String replyDate = simpleDateFormat.format(array.get(i).getRegdate());
        viewHolder.rRegdate.setText(replyDate);

    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView content, rWriter, rRegdate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
            rWriter = itemView.findViewById(R.id.rWriter);
            rRegdate = itemView.findViewById(R.id.rRegdate);
        }
    }
}
