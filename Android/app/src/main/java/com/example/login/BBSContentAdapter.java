package com.example.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.login.RemoteService.BASE_URL;


public class BBSContentAdapter extends RecyclerView.Adapter<BBSContentAdapter.ViewHolder> {

    Context context;
    List<BBSReplyVO> array;


    public BBSContentAdapter(Context context, List<BBSReplyVO> array) {
        this.context = context;
        this.array = array;
    }

    private BBSItemClickSupport.OnItemClickListener mListener = null;

    public void setOnItemClickListener(BBSItemClickSupport.OnItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.bbs_content_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.content.setText(array.get(i).getContent());
        viewHolder.rWriter.setText(array.get(i).getWriter());
        Date bbsDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM월dd일");
        String replyDate = simpleDateFormat.format(array.get(i).getRegdate());
        viewHolder.rRegdate.setText(replyDate);
        viewHolder.btnReplyDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
                RemoteService rs = retrofit.create(RemoteService.class);
                int rno = array.get(i).getRno();
                Log.d("댓글번호", "dfdfdf" + rno);
                Call<Void> replyDelete = rs.replyDelete(rno);
                replyDelete.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(context, "삭제되었습니당", Toast.LENGTH_SHORT).show();
                        array.remove(i);
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });


            }
        });

    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView content, rWriter, rRegdate, btnReplyDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
            rWriter = itemView.findViewById(R.id.rWriter);
            rRegdate = itemView.findViewById(R.id.rRegdate);
            btnReplyDelete = itemView.findViewById(R.id.btnReplyDelete);

        }
    }
}
