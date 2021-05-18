package com.myapplicationdev.android.p05_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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

        DBHelper dbh = new DBHelper(ModifyActivity.this);

        btnUpdate.setOnClickListener(v -> {
            RadioButton rb = findViewById(rgStars.getCheckedRadioButtonId());
            int result = dbh.updateSong(new Song(song.getId(), etEditTitle.getText().toString(), etEditSinger.getText().toString(), Integer.valueOf(etEditYear.getText().toString()), Integer.valueOf(rb.getText().toString())));
        });

        btnDelete.setOnClickListener(v -> {
            int result = dbh.deleteSong(song.getId());
        });

        btnCancel.setOnClickListener(v -> {

        });
    }
}