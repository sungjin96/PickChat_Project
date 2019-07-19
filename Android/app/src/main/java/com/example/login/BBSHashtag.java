package com.example.login;

import android.content.Context;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import retrofit2.Callback;

public class BBSHashtag<up> extends ClickableSpan {

    public BBSHashtag(Callback<BBSVO> bbSimgVOCallback) {
    }

    public interface ClickEventListener {
        void onClickEvent(String data);
    }

    private ClickEventListener mClickEventListener = null;
    private Context context;
    private TextPaint textPaint;

    public BBSHashtag(Context ctx) {
        super();
        context = ctx;
    }

    public void setOnClickEventListenner(ClickEventListener listenner) {
        mClickEventListener = listenner;
    }


    @Override
    public void updateDrawState(TextPaint ds) {
        textPaint = ds;
        ds.setColor(ds.linkColor);
        ds.setARGB(225, 30, 114, 255);
    }

    @Override
    public void onClick(View widget) {
        TextView tv = (TextView) widget;
        Spanned s = (Spanned) tv.getText();
        int start = s.getSpanStart(this);
        int end = s.getSpanEnd(this);
        String theWord = s.subSequence(start + 1, end).toString();
        mClickEventListener.onClickEvent(theWord);


    }
}
