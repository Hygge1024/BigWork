package com.example.bigwork;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class fg_setting extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_fg_setting, container, false);
        Button accountButton = view.findViewById(R.id.account_button);
        Button hobbiesButton = view.findViewById(R.id.hobbies_button);
        Button playbackButton = view.findViewById(R.id.playback_button);
        Button themeButton = view.findViewById(R.id.theme_button);
        Button privacyButton = view.findViewById(R.id.privacy_button);
        Button feedbackButton = view.findViewById(R.id.feedback_button);
        Button aboutButton = view.findViewById(R.id.about_button);

        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理账号管理按钮的点击事件
                // 进行相应的操作
                // 创建AlertDialog.Builder对象
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示");
                builder.setMessage("功能待完善，敬请期待");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击确定按钮后的操作
                        dialog.dismiss();
                    }
                });

                // 创建并显示对话框
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        hobbiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理个性爱好按钮的点击事件
                // 进行相应的操作
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示");
                builder.setMessage("功能待完善，敬请期待");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击确定按钮后的操作
                        dialog.dismiss();
                    }
                });

                // 创建并显示对话框
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        playbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理播放记录按钮的点击事件
                // 进行相应的操作
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示");
                builder.setMessage("功能待完善，敬请期待");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击确定按钮后的操作
                        dialog.dismiss();
                    }
                });

                // 创建并显示对话框
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        themeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理主题设置按钮的点击事件
                // 进行相应的操作
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示");
                builder.setMessage("功能待完善，敬请期待");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击确定按钮后的操作
                        dialog.dismiss();
                    }
                });

                // 创建并显示对话框
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        privacyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理隐私设置按钮的点击事件
                // 进行相应的操作
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示");
                builder.setMessage("功能待完善，敬请期待");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击确定按钮后的操作
                        dialog.dismiss();
                    }
                });

                // 创建并显示对话框
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        feedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理意见反馈按钮的点击事件
                // 进行相应的操作
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示");
                builder.setMessage("功能待完善，敬请期待");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击确定按钮后的操作
                        dialog.dismiss();
                    }
                });

                // 创建并显示对话框
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理关于我们按钮的点击事件
                // 进行相应的操作
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("提示");
                builder.setMessage("功能待完善，敬请期待");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 点击确定按钮后的操作
                        dialog.dismiss();
                    }
                });

                // 创建并显示对话框
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        return view;

    }
}