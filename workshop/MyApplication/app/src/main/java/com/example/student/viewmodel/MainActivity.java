package com.example.student.viewmodel;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CounterViewModel viewModel;

    TextView number;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(CounterViewModel.class);

        number = findViewById(R.id.counter);
        number.setText(String.valueOf(viewModel.getCounter()));

    }

    public void counterClick(View view) {
        viewModel.setCounter(viewModel.getCounter() + 1);
        number.setText(String.valueOf(viewModel.getCounter()));
    }
}
