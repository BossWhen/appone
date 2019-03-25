package com.example.joker.appone.tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;
/*
* 自定义金额输入框
* */
@SuppressLint("AppCompatCustomView")
public class AmountEditText extends EditText {

    private Context mContext;

    public AmountEditText(Context context) {
        super(context);
        init(context);
    }

    public AmountEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AmountEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        mContext = context;
    }

    @Override
    public void setFilters(InputFilter[] filters) {
        filters = new InputFilter[]{new CashierInputFilter()};
        super.setFilters(filters);
    }
    public boolean isConformRules(){
        String result = super.getText().toString();
        if (TextUtils.isEmpty(result)){
            return false;
        }else if (result.contains(".")){
            if (result.startsWith(".")){
                return false;
            }else if (result.startsWith("0")){
                int indexZero = result.indexOf("0");
                int indexPoint = result.indexOf(".");
                if (indexPoint - indexZero != 1){
                    return false;
                }else if (TextUtils.equals("0.",result)){
                    return false;
                }
            }
        }else if (!result.contains(".")){
            if (result.startsWith("0")){
                return false;
            }
        }
        return true;
    }
    @Override
    public Editable getText() {
        return super.getText();
    }
}

