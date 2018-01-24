package com.panku.toolbardemo;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);


        toolbar.setLogo(R.drawable.ic_launcher);
        toolbar.setTitle("Title");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitle("Subtitle");
        toolbar.setSubtitleTextColor(Color.parseColor("#FFFFFF"));
        //别忘了
        setSupportActionBar(toolbar);

        // 注意:Navigation Icon 要設定在setSupoortActionBar之后
        toolbar.setNavigationIcon(R.drawable.icon_pic_list_type);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "NavigationIcon", Toast.LENGTH_SHORT).show();
            }
        });


        //设置toolBar上的MenuItem点击事件
        toolbar.setOnMenuItemClickListener(listener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private final Toolbar.OnMenuItemClickListener listener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_search:
                    Toast.makeText(MainActivity.this, "searach!!!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_share:
                    Toast.makeText(MainActivity.this, "share!!!!", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_settings:
                    //Toast.makeText(MainActivity.this, "设置", Toast.LENGTH_SHORT).show();
                    //弹出自定义popWindow
                    //popUpMyOverflow();
                    break;
                case R.id.action_login:
                    Toast.makeText(MainActivity.this, "登录", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_regis:
                    Toast.makeText(MainActivity.this, "注册", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_help:
                    Toast.makeText(MainActivity.this, "帮助", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_pep:
                    Toast.makeText(MainActivity.this, "个人", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.action_zx:
                    Toast.makeText(MainActivity.this, "中心", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
    };

    PopupWindow mPopupWindow;

    /**
     * 弹出自定义的popWindow
     * 参考:http://blog.csdn.net/mchenys/article/details/51533689
     * 使用自定义请屏蔽 menu_main里id为action_settings下的menu
     */
    public void popUpMyOverflow() {
        //获取状态栏高度
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        //状态栏高度+toolbar的高度
        int yOffset = frame.top + toolbar.getHeight();
        if (null == mPopupWindow) {
            //初始化PopupWindow的布局
            View popView = getLayoutInflater().inflate(R.layout.popwindow, null);
            //popView即popupWindow的布局，ture设置focusAble.
            mPopupWindow = new PopupWindow(popView,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, true);
            //必须设置BackgroundDrawable后setOutsideTouchable(true)才会有效
            mPopupWindow.setBackgroundDrawable(new ColorDrawable());
            //点击外部关闭。
            mPopupWindow.setOutsideTouchable(true);
            //设置一个动画。
            mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
            //设置Gravity，让它显示在右上角。
            mPopupWindow.showAtLocation(toolbar, Gravity.RIGHT | Gravity.TOP, 0, yOffset);
            //设置item的点击监听
            popView.findViewById(R.id.ll_item1).setOnClickListener(this);
            popView.findViewById(R.id.ll_item2).setOnClickListener(this);
            popView.findViewById(R.id.ll_item3).setOnClickListener(this);
        } else {
            mPopupWindow.showAtLocation(toolbar, Gravity.RIGHT | Gravity.TOP, 0, yOffset);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_item1:
                Toast.makeText(MainActivity.this, "11111111", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_item2:
                Toast.makeText(MainActivity.this, "2222222222", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ll_item3:
                Toast.makeText(MainActivity.this, "33333333", Toast.LENGTH_SHORT).show();
                break;
        }
        //点击PopWindow的item后,关闭此PopWindow
        if (null != mPopupWindow && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }
}
