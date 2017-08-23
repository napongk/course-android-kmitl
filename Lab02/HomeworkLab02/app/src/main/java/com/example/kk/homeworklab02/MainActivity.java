package com.example.kk.homeworklab02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homework002);
    }

    public void Games(View view) {
        Toast.makeText(
                getBaseContext(),
                "Game Clicked ",
                Toast.LENGTH_LONG)
                .show();
    }

    public void Apps(View view) {
        Toast.makeText(
                getBaseContext(),
                "Apps Clicked",
                Toast.LENGTH_LONG)
                .show();
    }

    public void Movies(View view) {
        Toast.makeText(
                getBaseContext(),
                "Movies Clicked",
                Toast.LENGTH_LONG)
                .show();
    }

    public void Books(View view) {
        Toast.makeText(
                getBaseContext(),
                "Books Clicked",
                Toast.LENGTH_LONG)
                .show();
    }

    public void More(View view) {
        Toast.makeText(
                getBaseContext(),
                "More Clicked",
                Toast.LENGTH_LONG)
                .show();
    }

    public void See(View view) {
        Toast.makeText(
                getBaseContext(),
                "See new Topic",
                Toast.LENGTH_LONG)
                .show();
    }
}
