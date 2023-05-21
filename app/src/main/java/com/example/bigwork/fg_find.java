package com.example.bigwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.DataInput;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class fg_find extends Fragment implements SongAdapter.OnItemClickListener {
    private ImageButton playButton;
    private ImageButton btnon_dialog;
    private TextView songTitleTextView;
    private SeekBar progressBar;
    private MediaPlayer mediaPlayer;//音乐播放
    private Timer progressBarTimer;
    private boolean isProgressBarUpdating = false;
    private String musicName = "演员";

    //读取歌曲部分
    private RecyclerView songRecyclerView;
    private SongAdapter songAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_fg_find, container, false);

        // 获取布局控件的引用
        playButton = view.findViewById(R.id.playButton);
        btnon_dialog = view.findViewById(R.id.btnon_dialog);
        songTitleTextView = view.findViewById(R.id.songTitleTextView);
        progressBar = view.findViewById(R.id.progressBar);

        //歌曲部分操作
        // 初始化 RecyclerView
        songRecyclerView = view.findViewById(R.id.songRecyclerView);
        songRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // 创建歌曲列表数据
        List<String> songTitles = createSongTitles();

        // 创建适配器并设置给 RecyclerView
        songAdapter = new SongAdapter(songTitles, this);
        songRecyclerView.setAdapter(songAdapter);
//歌曲部分结束

        //这里的歌曲可以根据 前端的选择来决定播放什么
        mediaPlayer = MediaPlayer.create(getActivity(), R.raw.yanyuan);
//        String songTitle = getSongTitle(); // 获取歌曲的名称
//        songTitleTextView.setText(songTitle); // 设置歌曲名称到TextView

        // 设置最大进度值
        int songDuration = mediaPlayer.getDuration(); // 获取歌曲的总时长
        progressBar.setMax(songDuration);
        // 在这里添加对控件的事件监听器和其他逻辑
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {//暂停

                    if(isProgressBarUpdating){//true时,更该为暂停
                        isProgressBarUpdating = false;
                    }else{
                        isProgressBarUpdating = true;
                    }
                    mediaPlayer.pause();
                    playButton.setImageResource(R.drawable.icon_play);//暂停
                } else {//开始播放
                    // 进度条没有更新，启动更新
                    isProgressBarUpdating = true;
                    startProgressBarUpdate();//启动进度条
                    //播放
                    mediaPlayer.start();
                    playButton.setImageResource(R.drawable.icon_pause);//播放
                }
            }
        });
        //评论按钮
        btnon_dialog.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                showDialogWithWebView();
//
            }
        });

        return view;
    }

    private void showDialogWithWebView() {
        // 获取父级 Activity 的引用
        MainActivity activity = (MainActivity) getActivity();
        if (activity == null) {
            return;
        }
        final Dialog dialog = new Dialog(activity);
        // 创建对话框
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_webview); // 设置对话框布局

        // 获取屏幕高度
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;
        // 设置对话框的高度为屏幕高度的一半
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(dialog.getWindow().getAttributes());
        layoutParams.width = (int) (displayMetrics.widthPixels * 0.9);
        layoutParams.height = (int) (screenHeight * 0.7);
        dialog.getWindow().setAttributes(layoutParams);

        // 获取对话框布局中的 WebView
        WebView webView = dialog.findViewById(R.id.commit_webview);
        webView.setWebViewClient(new WebViewClient()); // 设置 WebViewClient
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        String userAgent = "Mozilla/5.0 (Linux; Android 10; SM-G975F) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Mobile Safari/537.36";
        webSettings.setUserAgentString(userAgent);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

//        String encodedMusicName;
//        try {
//            encodedMusicName = URLEncoder.encode(musicName, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            encodedMusicName = musicName;
//            e.printStackTrace();
//        }
        //根据musicName来加载页面
        String url = "https://music.163.com/#/song?id=32507038";
        switch (musicName){
            case "演员 - 薛之谦":
                url = "https://music.163.com/#/song?id=32507038";
                break;
            case "渡 - 薛之谦":
                url = "https://music.163.com/#/song?id=517571371";
                break;
            case "聊表心意 - 薛之谦,刘惜君":
                url = "https://music.163.com/#/song?id=1374061043";
                break;
            case "陪你去流浪 - 薛之谦":
                url = "https://music.163.com/#/song?id=1374056689";
                break;
            case "王子归来 - 薛之谦":
                url = "https://music.163.com/#/song?id=169241";
                break;
            case "红尘女子 - 薛之谦":
                url = "https://music.163.com/#/song?id=169168";
                break;
            case "绅士 (电视剧《是！尚先生》插曲) - 薛之谦":
                url = "https://music.163.com/#/song?id=32192436";
                break;
            case "苏黎世的从前 - 薛之谦":
                url = "https://music.163.com/#/song?id=169221";
                break;
            case "迟迟 - 薛之谦":
                url = "https://music.163.com/#/song?id=1808053189";
                break;
            case "男二号 - 薛之谦":
                url = "https://music.163.com/#/song?id=1915297033";
                break;
            case "花儿和少年 - 薛之谦":
                url = "https://music.163.com/#/song?id=421160838";
                break;
            case "笑场 - 薛之谦":
                url ="https://music.163.com/#/song?id=1374061038";
                break;
            case "天外来物 - 薛之谦谦":
                url = "https://music.163.com/#/song?id=1463165983";
                break;
            case "下雨了 - 薛之谦":
                url = "https://music.163.com/#/song?id=32507039";
                break;
            case "像风一样 - 薛之谦":
                url = "https://music.163.com/#/song?id=516657051";
                break;
            case "哑巴 - 薛之谦":
                url = "https://music.163.com/#/song?id=557583281";
                break;
            default:
                url = "https://music.163.com/#/song?id=32507038";
                break;
        }

        // 加载网页

        webView.loadUrl(url);
        // 显示对话框
        dialog.show();
    }
    private void startProgressBarUpdate() {
        if (progressBarTimer != null) {
            progressBarTimer.cancel();
            progressBarTimer = null;
        }

        if (isProgressBarUpdating) {
            progressBarTimer = new Timer();
            progressBarTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (mediaPlayer != null) {
                                int currentPosition = mediaPlayer.getCurrentPosition();
                                progressBar.setProgress(currentPosition);
                            }
                        }
                    });
                }
            }, 0, 1000);
        }
    }
    // 添加数据部分
    private List<String> createSongTitles() {
        List<String> songTitles = new ArrayList<>();

        // 添加歌曲标题数据
        songTitles.add("渡 - 薛之谦");
        songTitles.add("聊表心意 - 薛之谦,刘惜君");
        songTitles.add("演员 - 薛之谦");
        songTitles.add("陪你去流浪 - 薛之谦");
        songTitles.add("王子归来 - 薛之谦");
        songTitles.add("红尘女子 - 薛之谦");
        songTitles.add("绅士 (电视剧《是！尚先生》插曲) - 薛之谦");
        songTitles.add("苏黎世的从前 - 薛之谦");
        songTitles.add("迟迟 - 薛之谦");
        songTitles.add("男二号 - 薛之谦");
        songTitles.add("花儿和少年 - 薛之谦");
        songTitles.add("笑场 - 薛之谦");
        songTitles.add("天外来物 - 薛之谦");
        songTitles.add("下雨了 - 薛之谦");
        songTitles.add("像风一样 - 薛之谦");
        songTitles.add("哑巴 - 薛之谦");
        // 添加更多歌曲标题...

        return songTitles;
    }
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // 处理歌曲列表项的点击事件
    @Override
    public void onItemClick(int position) {
        // 停止之前的播放,先进行逻辑判断
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            isProgressBarUpdating = false;
            progressBar.setProgress(0);
        }
        String songTitle = songAdapter.getItem(position);
        int songResourceId = getSongResourceId(songTitle); // 获取歌曲的资源ID
        if (songResourceId != 0) {
            mediaPlayer = MediaPlayer.create(getActivity(), songResourceId);
            songTitleTextView.setText(songTitle);
            progressBar.setMax(mediaPlayer.getDuration());
            playButton.setImageResource(R.drawable.icon_pause);
            mediaPlayer.start();
            isProgressBarUpdating = true;
            startProgressBarUpdate();
        }
        // 在这里执行播放歌曲的操作，可以根据歌曲标题来获取相应的歌曲数据并进行播放
//        Toast.makeText(getActivity(), "正在播放：" + songTitle, Toast.LENGTH_LONG).show();
        Toast toast = Toast.makeText(getActivity(), "正在播放：" + songTitle, Toast.LENGTH_LONG);
        // 设置 Toast 的位置参数
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 250);
        // 显示 Toast
        toast.show();
    }
    // 根据歌曲标题获取对应的资源ID
    private int getSongResourceId(String songTitle) {
        musicName = songTitle;
        int resourceId = 0;
        // 根据歌曲标题来匹配对应的资源ID，可以自行实现匹配逻辑
        if (songTitle.equals("聊表心意 - 薛之谦,刘惜君")) {
            resourceId = R.raw.liaobiaoxinyi;
        } else if (songTitle.equals("演员 - 薛之谦")) {
            resourceId = R.raw.yanyuan;
        } else if (songTitle.equals("渡 - 薛之谦")) {
            resourceId = R.raw.du;
        } else if (songTitle.equals("陪你去流浪 - 薛之谦")) {
            resourceId = R.raw.peiniquliulang;
        } else if (songTitle.equals("王子归来 - 薛之谦")) {
            resourceId = R.raw.wangziguilai;
        } else if (songTitle.equals("红尘女子 - 薛之谦")) {
            resourceId = R.raw.hongchenglz;
        } else if (songTitle.equals("绅士 (电视剧《是！尚先生》插曲) - 薛之谦")) {
            resourceId = R.raw.shenshi;
        } else if (songTitle.equals("苏黎世的从前 - 薛之谦")) {
            resourceId = R.raw.limingqian;
        } else if (songTitle.equals("迟迟 - 薛之谦")) {
            resourceId = R.raw.chichi;
        } else if (songTitle.equals("男二号 - 薛之谦")) {
            resourceId = R.raw.nanerhao;
        } else if (songTitle.equals("花儿和少年 - 薛之谦")) {
            resourceId = R.raw.huaerheshaonian;
        } else if (songTitle.equals("笑场 - 薛之谦")) {
            resourceId = R.raw.xiaochang;
        } else if (songTitle.equals("天外来物 - 薛之谦")) {
            resourceId = R.raw.tianwailaiwu;
        }else if (songTitle.equals("下雨了 - 薛之谦")) {
            resourceId = R.raw.xiayule;
        }else if (songTitle.equals("像风一样 - 薛之谦")) {
            resourceId = R.raw.xiangfengyiyang;
        }else if (songTitle.equals("哑巴 - 薛之谦")) {
            resourceId = R.raw.yaba;
        }
        return resourceId;
    }
}
