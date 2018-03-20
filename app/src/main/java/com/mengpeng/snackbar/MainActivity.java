package com.mengpeng.snackbar;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.mengpeng.mphelper.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioGroup main_theme_rg;
    private RadioGroup main_time_rg;

    private int icon;
    private String content;
    private int background;

    private int time;
    private CheckBox checkbox1;
    private CheckBox checkbox2;

    private String btn1;
    private String btn2;
    private List<RadioButton> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_theme_rg = findViewById(R.id.main_theme_rg);
        main_time_rg = findViewById(R.id.main_time_rg);
        checkbox1 = findViewById(R.id.checkbox1);
        checkbox2 = findViewById(R.id.checkbox2);

        ToastUtils.getInstance().initToast(this);

        ((RadioButton) main_theme_rg.getChildAt(0)).setChecked(true);
        ((RadioButton) main_time_rg.getChildAt(0)).setChecked(true);

        icon = R.mipmap.toast_success;
        content = " Success ";
        background = R.color.toastSuccessColor;
        time = Snackbar.LENGTH_SHORT;

        main_theme_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.theme1) {
                    icon = R.mipmap.toast_success;
                    content = " Success ";
                    background = R.color.toastSuccessColor;
                } else if (checkedId == R.id.theme2) {
                    icon = R.mipmap.toast_error;
                    content = " Error ";
                    background = R.color.toastErrorColor;
                } else if (checkedId == R.id.theme3) {
                    icon = R.mipmap.toast_info;
                    content = " Info ";
                    background = R.color.toastInfoColor;
                } else if (checkedId == R.id.theme4) {
                    icon = R.mipmap.toast_warn;
                    content = " Warn ";
                    background = R.color.toastWarningColor;
                } else if (checkedId == R.id.theme5) {
                    icon = R.mipmap.toast_default;
                    content = " Default ";
                    background = R.color.toastDefaultColor;
                }
            }
        });

        main_time_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.time1) {
                    time = Snackbar.LENGTH_SHORT;
                } else if (checkedId == R.id.time2) {
                    time = Snackbar.LENGTH_LONG;
                } else if (checkedId == R.id.time3) {
                    time = Snackbar.LENGTH_INDEFINITE;
                }
            }
        });

        checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    btn1 = "按钮1";
                } else {
                    btn1 = "";
                }
            }
        });
        checkbox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    btn2 = "按钮2";
                } else {
                    btn2 = "";
                }
            }
        });

    }


    public void onCloseClick(View view) {
        SnackbarUtils.dismiss();
    }

    public void onShowClick(View view) {


        SnackbarUtils
                .create(MainActivity.this) //  .create(Activity activity) 必须
                .setIcon(icon)       //  左边的icon
                .setContent(content)                   //  提示文字
                .setSnackbarBackgroundColor(background)  // 背景颜色
                .setActionBtn1(btn1, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.onSuccessShowToast(btn1);
                    }
                })
                .setActionBtn2(btn2, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.onWarnShowToast(btn2);
                    }
                })
                .setDuration(time)  //设置显示时长
                .build();  //创建并show  必须调用
    }

    /**
     * 自定义view
     */
    public void onCustumViewClick(View view) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_view, null);

        RadioGroup itemView_tab_container = inflate.findViewById(R.id.itemView_tab_container);
        RelativeLayout itemView_container = inflate.findViewById(R.id.itemView_container);


        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(dp2px(70), dp2px(70));
        params.width = dp2px(70);
        params.height = dp2px(70);

        list.clear();
        for (int i = 0; i < 20; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setLayoutParams(params);
            radioButton.setButtonDrawable(null);
            radioButton.setCompoundDrawablesRelativeWithIntrinsicBounds(
                    null,
                    ContextCompat.getDrawable(this, R.mipmap.ic_launcher_round),
                    null, null);
            radioButton.setText("张三" + i);
            radioButton.setGravity(Gravity.CENTER);
            list.add(radioButton);
            itemView_tab_container.addView(radioButton);
        }

        SnackbarUtils
                .create(MainActivity.this, inflate, 100) //  .create(Activity activity) 必须
                .setDuration(time)  //设置显示时长
                .build();  //创建并show  必须调用


        itemView_tab_container.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getId() == checkedId) {
                        ToastUtils.onSuccessShowToast("张三" + i);
                        return;
                    }
                }
            }
        });


    }

    public int dp2px(float dpValue) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }


}
