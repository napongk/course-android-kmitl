package com.example.student.viewmodel;

import android.arch.lifecycle.ViewModel;

/**
 * Created by student on 17/11/2017 AD.
 */

public class CounterViewModel extends ViewModel{

    private int counter;

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
