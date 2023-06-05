package com.example.bigwork;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class fg_chat extends AppCompatActivity {
    private EditText inputText;
    private Button send;
    private ListView msgListView;
    private MsgAdapter adapter;
    private List<Msg> msgList = new ArrayList<>();




    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initMsgs();
        adapter = new MsgAdapter(fg_chat.this, R.layout.chat_item, msgList);
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        msgListView = (ListView) findViewById(R.id.msg_list_view);
        msgListView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyDataSetChanged();
                    msgListView.setSelection(msgList.size());
                    inputText.setText("");


                }
            }
        });
        Button backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        TextView nameTextView = (TextView) findViewById(R.id.name_text_view);
        String name = getIntent().getStringExtra("name");
        nameTextView.setText(name);

        Button selectImageButton = (Button) findViewById(R.id.select_image_button);
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });



    }

    private void initMsgs() {
        String selectedUser = getIntent().getStringExtra("name") ;// 获取用户选择的用户名
        if (selectedUser.equals("官方小助手")) {
            Msg msg1 = new Msg("欢迎您加入我们Music0.25!", Msg.TYPE_RECEIVED);
            msgList.add(msg1);
            Msg msg2 = new Msg("今日天气", Msg.TYPE_SENT);
            msgList.add(msg2);
            Msg msg3 = new Msg("今天杭州钱塘区30-16摄氏度，阴，昼夜温差较大，记得更替衣物", Msg.TYPE_RECEIVED);
            msgList.add(msg3);
        }
        else if(selectedUser.equals("蓝**")) {
            Msg msg1 = new Msg("在吗？", Msg.TYPE_RECEIVED);
            msgList.add(msg1);
            Msg msg2 = new Msg("嗯。", Msg.TYPE_SENT);
            msgList.add(msg2);
        }
        else if(selectedUser.equals("刘*")) {
            Msg msg1 = new Msg("你去听听看这首", Msg.TYPE_RECEIVED);
            msgList.add(msg1);
            Resources resources = this.getResources();
            Uri uri1 = Uri.parse("android.resource://" + this.getPackageName() + "/" + R.drawable.xzq);
            Msg msg2 = new Msg(uri1, Msg.TYPE_RECEIVED);
            msgList.add(msg2);
            Msg msg3 = new Msg("确实好听", Msg.TYPE_SENT);
            msgList.add(msg3);
        }
    }
    public class Msg {
        public static final int TYPE_RECEIVED = 0;
        public static final int TYPE_SENT = 1;

        private String content;
        private int type;
        private Uri imageUri;

        public Msg(String content, int type) {
            this.content = content;
            this.type = type;
        }

        public Msg(Uri imageUri, int type) {
            this.imageUri = imageUri;
            this.type = type;
        }

        public String getContent() {
            return content;
        }

        public int getType() {
            return type;
        }

        public Uri getImageUri() {
            return imageUri;
        }
    }

    public class MsgAdapter extends ArrayAdapter<Msg> {
        private int resourceId;

        public MsgAdapter(Context context, int textViewResourceId, List<Msg> objects) {
            super(context, textViewResourceId, objects);
            resourceId = textViewResourceId;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Msg msg = getItem(position);
            View view;
            ViewHolder viewHolder;
            if (convertView == null) {
                view = LayoutInflater.from(getContext()).inflate(R.layout.chat_item, parent, false) ;
                viewHolder = new ViewHolder();
                viewHolder.leftLayout = (LinearLayout) view.findViewById(R.id.left_layout);
                viewHolder.rightLayout = (LinearLayout) view.findViewById(R.id.right_layout);
                viewHolder.leftMsg = (TextView) view.findViewById(R.id.left_msg);
                viewHolder.rightMsg = (TextView) view.findViewById(R.id.right_msg);
                viewHolder.leftImage = (ImageView) view.findViewById(R.id.left_image);
                viewHolder.rightImage = (ImageView) view.findViewById(R.id.right_image);
                view.setTag(viewHolder);
            } else {
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();
            }

            if (msg.getType() == Msg.TYPE_RECEIVED) {
                viewHolder.leftLayout.setVisibility(View.VISIBLE);
                viewHolder.rightLayout.setVisibility(View.GONE);
                if (msg.getImageUri() != null) {
                    viewHolder.leftMsg.setVisibility(View.GONE);
                    viewHolder.leftImage.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(msg.getImageUri()).into(viewHolder.leftImage);
                } else {
                    viewHolder.leftMsg.setVisibility(View.VISIBLE);
                    viewHolder.leftImage.setVisibility(View.GONE);
                    viewHolder.leftMsg.setText(msg.getContent());
                }
            } else if (msg.getType() == Msg.TYPE_SENT) {
                viewHolder.rightLayout.setVisibility(View.VISIBLE);
                viewHolder.leftLayout.setVisibility(View.GONE);
                if (msg.getImageUri() != null) {
                    viewHolder.rightMsg.setVisibility(View.GONE);
                    viewHolder.rightImage.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(msg.getImageUri()).into(viewHolder.rightImage);
                } else {
                    viewHolder.rightMsg.setVisibility(View.VISIBLE);
                    viewHolder.rightImage.setVisibility(View.GONE);
                    viewHolder.rightMsg.setText(msg.getContent());
                }
            }

            //查看大图
            viewHolder.leftImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    ImageView imageView = new ImageView(getContext());
                    imageView.setImageURI(msg.getImageUri());
                    builder.setView(imageView);
                    builder.create().show();
                }
            });

            viewHolder.rightImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    ImageView imageView = new ImageView(getContext());
                    imageView.setImageURI(msg.getImageUri());
                    builder.setView(imageView);
                    builder.create().show();
                }
            });
            return view;
        }

        class ViewHolder {
            LinearLayout leftLayout;
            LinearLayout rightLayout;
            TextView leftMsg;
            TextView rightMsg;
            ImageView leftImage;
            ImageView rightImage;
        }

    }

    private static final int REQUEST_CODE_SELECT_IMAGE = 1;

    private final ActivityResultLauncher<Intent> selectImageLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri selectedImageUri = result.getData().getData();
                    Msg msg = new Msg(selectedImageUri, Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyDataSetChanged();
                    msgListView.setSelection(msgList.size());
                }
            });

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        selectImageLauncher.launch(intent);
    }




}