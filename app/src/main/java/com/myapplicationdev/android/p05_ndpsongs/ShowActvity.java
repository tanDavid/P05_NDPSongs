package com.myapplicationdev.android.p05_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ShowActvity extends AppCompatActivity {

    Button btnFilter;
    ListView lvSongs;
    ArrayList<Song> al, filteral;
    SongArrayAdapter aa;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        btnFilter = (Button) findViewById(R.id.btnFilter);
        lvSongs = (ListView) findViewById(R.id.lvSongs);

        DBHelper dbh = new DBHelper(ShowActvity.this);
        al = dbh.getAllSongs();
        filteral = al;
        dbh.close();
        aa = new SongArrayAdapter(this, R.layout.row, al);
        lvSongs.setAdapter(aa);

        btnFilter.setOnClickListener(v -> {
            filteral.clear();
            for (Song i: al) {
                if (i.getStars() == 5) {
                    filteral.add(i);
                }
            }
            if (filteral.size() > 0) {
                aa = new SongArrayAdapter(this, R.layout.row, filteral);
            }
            lvSongs.setAdapter(aa);
        });

        lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ShowActvity.this, ModifyActivity.class);
                i.putExtra("song", filteral.get(position));
                startActivity(i);
            }
        });
        spinner.setAdapter(aa);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = spinner.getSelectedItem().toString();
                DBHelper dbh = new DBHelper(ShowActvity.this);
                al.clear();
                al.addAll(dbh.getAllSongs());
                dbh.close();
                aa.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}