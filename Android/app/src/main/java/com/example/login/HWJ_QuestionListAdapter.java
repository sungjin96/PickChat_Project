package com.example.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class HWJ_QuestionListAdapter extends RecyclerView.Adapter<HWJ_QuestionListAdapter.ViewHolder> {
    Context context;
    List<HWJ_QuestionListVO> array;

    public HWJ_QuestionListAdapter(Context context, List<HWJ_QuestionListVO> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_question_hwj_list, viewGroup, false);
        return new HWJ_QuestionListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.qtitle.setText(array.get(i).getQtitle());
        viewHolder.qcontent.setText(array.get(i).getQcontent());
        viewHolder.qccontent.setText(array.get(i).getQccontent());

        if(array.get(i).getQccontent() == null){
            viewHolder.qccontent.setVisibility(View.GONE);
            viewHolder.state.setText("답변중");
        }else{
            viewHolder.qccontent.setVisibility(View.VISIBLE);
            viewHolder.state.setText("답변완료");
        }
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView qtitle,qcontent,qccontent, state;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            qtitle=itemView.findViewById(R.id.qtitle);
            qcontent=itemView.findViewById(R.id.qcontent);
            qccontent=itemView.findViewById(R.id.qccontent);
            state=itemView.findViewById(R.id.state);
        }
    }
}