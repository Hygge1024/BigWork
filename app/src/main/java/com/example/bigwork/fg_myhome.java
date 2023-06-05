package com.example.bigwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fg_myhome extends Fragment {
    private static final int EDIT_REQUEST_CODE = 1;
    private TextView usernameTextView;
    private TextView genderTextView;
    private TextView hobbyTextView;
    private ImageButton editButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fg_myhome, container, false);
        usernameTextView = view.findViewById(R.id.username_text);
        genderTextView = view.findViewById(R.id.gender_text);
        hobbyTextView = view.findViewById(R.id.hobby_text);
        editButton = view.findViewById(R.id.my_image_button);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditActivity.class);
                startActivityForResult(intent, EDIT_REQUEST_CODE);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_REQUEST_CODE && resultCode == getActivity().RESULT_OK && data != null) {
            String editedUsername = data.getStringExtra("editedUsername");
            String editedGender = data.getStringExtra("editedGender");
            String editedHobby = data.getStringExtra("editedHobby");

            usernameTextView.setText(editedUsername);
            genderTextView.setText(editedGender);
            hobbyTextView.setText(editedHobby);
        }
    }
}