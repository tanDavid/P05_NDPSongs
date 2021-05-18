package com.myapplicationdev.android.p05_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ModifyActivity extends AppCompatActivity {

    TextView tvEditId;
    EditText etEditTitle, etEditSinger, etEditYear;
    RadioGroup rgStars;
    Button btnUpdate, btnDelete, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        Intent i = getIntent();
        Song song = (Song) i.getSerializableExtra("song");

        tvEditId = findViewById(R.id.tvEditID);
        etEditTitle = findViewById(R.id.etEditTitle);
        etEditSinger = findViewById(R.id.etEditSinger);
        etEditYear = findViewById(R.id.etEditYear);
        rgStars = findViewById(R.id.rgStars);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        tvEditId.setText(song.getId());
        etEditTitle.setText(song.getTitle());
        etEditSinger.setText(song.getSingers());
        etEditYear.setText(song.getYear());
        rgStars.check(song.getStars());

        btnUpdate.setOnClickListener(v -> {

        });

        btnDelete.setOnClickListener(v -> {

        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });
    }
}