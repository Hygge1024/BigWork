package com.example.bigwork;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class fg_message extends Fragment {
    private ListView listView;
    private List<Msg> MsgList = new ArrayList<>();
    private MsgAdapter adapter;

    private List<Msg> msgList = new ArrayList<>();
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fg_message, container, false);
        super.onCreate(savedInstanceState);
        initMsgs();
        adapter = new MsgAdapter(getActivity(), R.layout.msg_item, MsgList);
        listView = (ListView) view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Msg Msg = MsgList.get(position);
                Intent intent = new Intent(getActivity(), fg_chat.class);
                intent.putExtra("name", Msg.getName());
                startActivity(intent);
            }
        });
        return view;
    }

    private void initMsgs() {
        Msg msg1 = new Msg("官方小助手", R.drawable.head, "今天杭州钱塘区……");
        MsgList.add(msg1);
        Msg msg2 = new Msg("蓝**", R.drawable.head, "在？");
        MsgList.add(msg2);
        Msg msg3 = new Msg("刘*", R.drawable.head, "确实好听");
        MsgList.add(msg3);
    }
    public class Msg {
        public static final int TYPE_RECEIVED = 0;
        public static final int TYPE_SENT = 1;

        private String name;
        private String content;
        private int type;

        public Msg(String name, int type, String content) {
            this.name = name;
            this.content = content;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public String getContent() {
            return content;
        }

        public int getType() {
            return type;
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
                view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.headImage = (ImageView) view.findViewById(R.id.head);
                viewHolder.nameText = (TextView) view.findViewById(R.id.name);
                viewHolder.msgText = (TextView) view.findViewById(R.id.msg);
                view.setTag(viewHolder);
            } else {
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.headImage.setImageResource(msg.getType());
            viewHolder.nameText.setText(msg.getName());
            viewHolder.msgText.setText(msg.getContent());
            return view;
        }

        class ViewHolder {
            ImageView headImage;
            TextView nameText;
            TextView msgText;
        }
    }




}