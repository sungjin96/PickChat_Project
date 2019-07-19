package com.example.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import static android.view.View.VISIBLE;
import static android.widget.RelativeLayout.ALIGN_PARENT_LEFT;
import static android.widget.RelativeLayout.ALIGN_PARENT_RIGHT;

public class JHJ_ChatAdapter extends RecyclerView.Adapter<JHJ_ChatAdapter.ViewHolder> {
    List<JHJ_FireBaseChatVO> array;
    Context context;
    HashMap<String, String> nicks;
    JHJ_Test test = JHJ_Test.getTest();
    String strUser="";

    public String getUserID() {
       if(test.getId().equals(test.getId())){
           strUser = nicks.get("myNick");
       }
        return strUser;
    }

    public JHJ_ChatAdapter(List<JHJ_FireBaseChatVO> array, Context context, HashMap<String, String> nicks) {
        this.array = array;
        this.context = context;
        this.nicks = nicks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.jhj_item_chat, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        System.out.println("채팅하는 사람 정보는??????????????"+strUser+"///////"+array.get(i).getUserName()+"ㅇㅇㅇㅇㅇㅇㅇㅇㅇ");
        viewHolder.wdate.setText(array.get(i).getWdate());
        viewHolder.content.setText(array.get(i).getContent());
        viewHolder.name.setText(array.get(i).getUserName());

        Picasso.with(context)
                .load(array.get(i).getImage())
                .into(viewHolder.chatimg);

        LinearLayout.LayoutParams prmWdate = (LinearLayout.LayoutParams) viewHolder.wdate.getLayoutParams();
        LinearLayout.LayoutParams prmLinear = (LinearLayout.LayoutParams) viewHolder.content.getLayoutParams();
        LinearLayout.LayoutParams prmName = (LinearLayout.LayoutParams) viewHolder.name.getLayoutParams();

        RelativeLayout.LayoutParams usercontentlayout = (RelativeLayout.LayoutParams) viewHolder.usercontent.getLayoutParams();
        if (getUserID()!=null){
            if (getUserID().equals(array.get(i).getUserName())) {
                usercontentlayout.addRule(ALIGN_PARENT_RIGHT);
                usercontentlayout.addRule(Gravity.RIGHT);
                viewHolder.chatimg.setVisibility(View.GONE);

                prmLinear.gravity = Gravity.RIGHT;
                prmWdate.gravity = Gravity.RIGHT;
                prmName.gravity = Gravity.RIGHT;
            } else {
                usercontentlayout.addRule(ALIGN_PARENT_LEFT);
                usercontentlayout.addRule(Gravity.LEFT);
                viewHolder.chatimg.setVisibility(View.VISIBLE);
                prmLinear.gravity = Gravity.LEFT;
                prmWdate.gravity = Gravity.LEFT;
                prmName.gravity = Gravity.LEFT;
            }
    }
        viewHolder.content.setLayoutParams(prmLinear);
        viewHolder.wdate.setLayoutParams(prmWdate);
        viewHolder.name.setLayoutParams(prmName);
        //viewHolder.usercontent.setLayoutParams(usercontentlayout);

        viewHolder.content.setText(array.get(i).getContent());
        viewHolder.wdate.setText(array.get(i).getWdate());
        viewHolder.name.setText(array.get(i).getUserName());


    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView content, wdate, name;
        LinearLayout usercontent;
        RoundedImageView chatimg;
        RelativeLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            usercontent=itemView.findViewById(R.id.usercontent);
            wdate = (TextView) itemView.findViewById(R.id.wdate);
            content = (TextView) itemView.findViewById(R.id.content);
            chatimg=itemView.findViewById(R.id.chatImg);

            layout=itemView.findViewById(R.id.layout);
            name=itemView.findViewById(R.id.name);


        }
    }
}
