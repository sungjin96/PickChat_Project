package com.example.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class HWJ_BottomSheetDialog extends BottomSheetDialogFragment implements View.OnClickListener {
    RelativeLayout charge1, charge2, charge3, charge4, charge5, charge6;

    public static HWJ_BottomSheetDialog getInstance() {
        return new HWJ_BottomSheetDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_hwj_bottom_sheet_dialog, container, false);
        charge1 = view.findViewById(R.id.charge1);
        charge2 = view.findViewById(R.id.charge2);
        charge3 = view.findViewById(R.id.charge3);
        charge4 = view.findViewById(R.id.charge4);
        charge5 = view.findViewById(R.id.charge5);
        charge6 = view.findViewById(R.id.charge6);

        charge1.setOnClickListener(this);
        charge2.setOnClickListener(this);
        charge3.setOnClickListener(this);
        charge4.setOnClickListener(this);
        charge5.setOnClickListener(this);
        charge6.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.charge1 :
                Toast.makeText(getContext(), "충전로고 띄움1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.charge2 :
                Toast.makeText(getContext(), "충전로고 띄움2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.charge3 :
                Toast.makeText(getContext(), "충전로고 띄움3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.charge4 :
                Toast.makeText(getContext(), "충전로고 띄움4", Toast.LENGTH_SHORT).show();
                break;
            case R.id.charge5 :
                Toast.makeText(getContext(), "충전로고 띄움5", Toast.LENGTH_SHORT).show();
                break;
            case R.id.charge6 :
                Toast.makeText(getContext(), "충전로고 띄움6", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
