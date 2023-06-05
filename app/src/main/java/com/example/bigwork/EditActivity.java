package com.example.bigwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText genderEditText;
    private EditText hobbyEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        usernameEditText = findViewById(R.id.username_edittext);
        genderEditText = findViewById(R.id.gender_edittext);
        hobbyEditText = findViewById(R.id.hobby_edittext);
        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editedUsername = usernameEditText.getText().toString();
                String editedGender = genderEditText.getText().toString();
                String editedHobby = hobbyEditText.getText().toString();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("editedUsername", editedUsername);
                resultIntent.putExtra("editedGender", editedGender);
                resultIntent.putExtra("editedHobby", editedHobby);
                setResult(RESULT_OK, resultIntent);

                finish();
            }
        });
    }
}