package com.example.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.login.RemoteService.BASE_URL;

public class HWJ_BlockListAdapter extends RecyclerView.Adapter<HWJ_BlockListAdapter.ViewHolder> {
    Context context;
    List<HWJ_BlockVO> array;
    Retrofit retrofit;
    RemoteService rs;
    int intBlockno;

    public HWJ_BlockListAdapter(Context context, List<HWJ_BlockVO> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_hwj_block, viewGroup, false);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        rs = retrofit.create(RemoteService.class);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.name.setText(array.get(i).getBlockedname());
        viewHolder.phone.setText(array.get(i).getBlocked());

        //차단해제
        viewHolder.blockuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder box = new AlertDialog.Builder(context);
                box.setTitle("차단해제");
                box.setMessage("차단해제 하시겠습니까?");
                box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        intBlockno = array.get(i).getBlockno();

                        Call<Void> call = rs.deleteBlock(intBlockno);
                        call.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                array.remove(i);
                                notifyDataSetChanged();
                                Toast.makeText(context, "삭제되었습니다", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(context, "실패", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                box.setNegativeButton("아니오", null);
                box.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout blockuser;
        TextView name, phone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            blockuser = itemView.findViewById(R.id.blockuser);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
        }
    }
}
