# SnackbarUtils

#### 效果图
![](snackbar.gif)


### 使用方式
```
SnackbarUtils
    .create(MainActivity.this)           //  .create(Activity activity) 必须
    .setIcon(R.mipmap.toast_error)       //  左边的icon
    .setContent("失败")                   //  提示文字
    .setSnackbarBackgroundColor(R.color.toastErrorColor)  // 背景颜色
    .setActionBtn1("按钮1", new View.OnClickListener() {  // 按钮1
        @Override
        public void onClick(View v) {
            Log.d("MainActivity", "按钮1");
        }
    })
    .setActionBtn2("按钮2", new View.OnClickListener() {  // 按钮2
        @Override
        public void onClick(View v) {
            Log.d("MainActivity", "按钮2");
        }
    })
    .setDuration(Snackbar.LENGTH_INDEFINITE)  //设置显示时长
    .build();  //创建并show  必须调用
```