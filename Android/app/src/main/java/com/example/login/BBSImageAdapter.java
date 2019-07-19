package com.example.login;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.login.RemoteService.BASE_URL;


public class BBSImageAdapter extends RecyclerView.Adapter<BBSImageAdapter.ViewHolder> {
    Context context;
    ArrayList<BBSVO> array;
    Bitmap bitmap;


    //생성자
    public BBSImageAdapter(Context context, ArrayList<BBSVO> array) {
        this.context = context;
        this.array = array;
    }


    //리사이클뷰에 레이아웃
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.img_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Picasso.with(context).load(BASE_URL + "image/" + array.get(i).getImgpath()).resize(155, 150).into(viewHolder.img);

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL(array.get(i).getImgpath());
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException f) {
                    f.printStackTrace();
                }
            }
        };
        thread.start();
        try {
            thread.join();
            viewHolder.img.setImageBitmap(bitmap);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.gridImg);


        }
    }
}
