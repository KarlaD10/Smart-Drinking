package com.example.smart_drinking;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class ToastCaller {

    public void callToast(View layout, Context context, String string){
        TextView tv_toast;
        tv_toast = (TextView) layout.findViewById(R.id.tv_toast);
        final Toast tostada = new Toast(context);
        tostada.setGravity(Gravity.CENTER_VERTICAL,0,0);
        tostada.setDuration(Toast.LENGTH_LONG);
        tostada.setView(layout);
        tv_toast.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv_toast.setTextSize(25);
        tv_toast.setText(string);
        tostada.show();
    }
}
