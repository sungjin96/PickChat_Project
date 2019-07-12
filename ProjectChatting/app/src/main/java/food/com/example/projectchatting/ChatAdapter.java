package food.com.example.projectchatting;

import android.content.Context;
import android.opengl.Visibility;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.view.View.VISIBLE;
import static android.widget.RelativeLayout.ALIGN_PARENT_END;
import static android.widget.RelativeLayout.ALIGN_PARENT_LEFT;
import static android.widget.RelativeLayout.ALIGN_PARENT_RIGHT;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    List<ChatVO> array;
    Context context;
    String strUserName;


    public ChatAdapter(List<ChatVO> array, Context context, String strUserName) {
        this.array = array;
        this.context = context;
        this.strUserName = strUserName;


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        LinearLayout.LayoutParams prmWdate = (LinearLayout.LayoutParams) viewHolder.wdate.getLayoutParams();
        LinearLayout.LayoutParams prmLinear = (LinearLayout.LayoutParams) viewHolder.content.getLayoutParams();
        LinearLayout.LayoutParams prmName = (LinearLayout.LayoutParams) viewHolder.name.getLayoutParams();

        RelativeLayout.LayoutParams usercontentlayout = (RelativeLayout.LayoutParams)viewHolder.usercontent.getLayoutParams();
        if (strUserName.equals(array.get(i).getUserName())) {
          /* prmContent.gravity = Gravity.RIGHT;
            prmWdate.gravity = Gravity.RIGHT;*/

            //System.out.println(strUserName);
           usercontentlayout.addRule(ALIGN_PARENT_RIGHT);
          //  usercontentlayout.addRule(Gravity.RIGHT);
            prmLinear.gravity=Gravity.RIGHT;
            prmWdate.gravity=Gravity.RIGHT;
            prmName.gravity=Gravity.RIGHT;

        } else {

//            prmContent.gravity = Gravity.LEFT;
//             prmWdate.gravity = Gravity.LEFT;
                usercontentlayout.addRule(ALIGN_PARENT_LEFT);
                viewHolder.chatimg.setVisibility(VISIBLE);
            prmLinear.gravity=Gravity.LEFT;
            prmWdate.gravity=Gravity.LEFT;
            prmName.gravity=Gravity.LEFT;
        }
        viewHolder.content.setLayoutParams(prmLinear);
        viewHolder.wdate.setLayoutParams(prmWdate);
        viewHolder.name.setLayoutParams(prmName);
        viewHolder.usercontent.setLayoutParams(usercontentlayout);

        viewHolder.wdate.setText(array.get(i).getWdate());
        viewHolder.content.setText(array.get(i).getContent());
        viewHolder.name.setText(array.get(i).getUserName());
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView content, wdate, name;
        RelativeLayout usercontent;
        ImageView chatimg;
        LinearLayout linear;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            usercontent=itemView.findViewById(R.id.usercontent);
            wdate = (TextView) itemView.findViewById(R.id.wdate);
            content = (TextView) itemView.findViewById(R.id.content);
            chatimg=itemView.findViewById(R.id.chatImg);
            linear=itemView.findViewById(R.id.linear);
            name=itemView.findViewById(R.id.name);


        }
    }
}
