package com.example.roomproj.astronauta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomproj.R;

public class NewAstronautaActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_NAME = "com.example.android.astronautanamelistsql.REPLY";
    public static final String EXTRA_REPLY_ADDRESS = "com.example.android.astronautaaddresslistsql.REPLY";
    public static final String EXTRA_REPLY_AGE = "com.example.android.astronautaagelistsql.REPLY";

    private EditText mEditNameView;
    private EditText mEditAddressView;
    private EditText mEditAgeView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_astronauta);
        mEditNameView = findViewById(R.id.edit_astronauta_name);
        mEditAddressView = findViewById(R.id.edit_astronauta_address);
        mEditAgeView = findViewById(R.id.edit_astronauta_age);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditNameView.getText()) || TextUtils.isEmpty(mEditAddressView.getText()) || TextUtils.isEmpty(mEditAgeView.getText()) ) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String name = mEditNameView.getText().toString();
                    String address = mEditAddressView.getText().toString();
                    String age = mEditAgeView.getText().toString();

                    replyIntent.putExtra(EXTRA_REPLY_NAME, name);
                    replyIntent.putExtra(EXTRA_REPLY_ADDRESS, address);
                    replyIntent.putExtra(EXTRA_REPLY_AGE, age);

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}

