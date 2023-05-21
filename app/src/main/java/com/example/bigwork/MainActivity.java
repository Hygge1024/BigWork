package com.example.bigwork;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    private TextView txt_topbar;
    private TextView txt_find;
    private TextView txt_message;
    private TextView txt_myhome;
    private TextView txt_setting;
    private FrameLayout ly_content;//暂时不需要了

    //Fragment Object;
    private fg_find fg1;
    private fg_message fg2;
    private fg_myhome fg3;
    private fg_setting fg4;
//    private MyFragment02 fg1_2;
    private FragmentManager fManager;//Fragment管理器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        fManager = getSupportFragmentManager();//获取Fragement组件
        bindView();
        txt_find.performClick();//模拟一次点击，既进去后选择,也就是最开始进去的


        //点击button事件
        // 将布局文件转换为视图对象
//        View view = LayoutInflater.from(this).inflate(R.layout.activity_fg_find, null);
        // 查找按钮对象
//        Button btn_find01 = view.findViewById(R.id.btn_find01);

//        // 设置点击事件监听器
//        btn_find01.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this, "按钮被点击了", Toast.LENGTH_SHORT).show();
//                Log.e("btn listener:", "btn_find is clicked!");
//            }
//        });

    }
    //Ui组件初始化与事件绑定
    private void bindView(){
//        txt_topbar = (TextView) findViewById(R.id.txt_topbar);
        txt_find = (TextView) findViewById(R.id.txt_find);//发现页面
        txt_message = (TextView) findViewById(R.id.txt_message);//消息页面
        txt_myhome = (TextView) findViewById(R.id.txt_myhome);//我的主页
        txt_setting = (TextView) findViewById(R.id.txt_setting);//设置页面
        ly_content = (FrameLayout) findViewById(R.id.ly_content);

        txt_find.setOnClickListener(this);
        txt_message.setOnClickListener(this);
        txt_myhome.setOnClickListener(this);
        txt_setting.setOnClickListener(this);
    }
    //重置所有文本的选中状态
    private void setSelected(){
        txt_find.setSelected(false);
        txt_message.setSelected(false);
        txt_myhome.setSelected(false);
        txt_setting.setSelected(false);
    }
    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(fg1 != null)fragmentTransaction.hide(fg1);
        if(fg2 != null)fragmentTransaction.hide(fg2);
        if(fg3 != null)fragmentTransaction.hide(fg3);
        if(fg4 != null)fragmentTransaction.hide(fg4);
    }

    @Override
    public void onClick(View view) {
        FragmentTransaction fTransaction = fManager.beginTransaction();//获取事务对象
        hideAllFragment(fTransaction);
        switch (view.getId()){
            case R.id.txt_find:
                setSelected();
                txt_find.setSelected(true);
                //topbar绑定选择信息
//                txt_topbar.setText("发现");
                Log.e("Txt listener:", "txt_find is clicked!");
                if(fg1 == null){
                    fg1 = new fg_find();
                    fTransaction.add(R.id.ly_content,fg1);
                }else{
                    fTransaction.show(fg1);
                }
                break;
            case R.id.txt_message:
                setSelected();
                txt_message.setSelected(true);
                //topbar绑定选择信息
//                txt_topbar.setText("消息");
                Log.e("Txt listener:", "txt_message is clicked!");
                if(fg2 == null){
                    fg2 = new fg_message();
                    fTransaction.add(R.id.ly_content,fg2);
                }else{
                    fTransaction.show(fg2);
                }
                break;
            case R.id.txt_myhome:
                setSelected();
                txt_myhome.setSelected(true);
                //topbar绑定选择信息
//                txt_topbar.setText("我的");
                Log.e("Txt listener:", "txt_myhome is clicked!");
                if(fg3 == null){
                    fg3 = new fg_myhome();
                    fTransaction.add(R.id.ly_content,fg3);
                }else{
                    fTransaction.show(fg3);
                }
                break;
            case R.id.txt_setting:
                setSelected();
                txt_setting.setSelected(true);
                //topbar绑定选择信息
//                txt_topbar.setText("设置");
                Log.e("Txt listener:", "txt_setting is clicked!");
                if(fg4 == null){
                    fg4 = new fg_setting();
                    fTransaction.add(R.id.ly_content,fg4);
                }else{
                    fTransaction.show(fg4);
                }
                break;
        }
        fTransaction.commit();
    }

}