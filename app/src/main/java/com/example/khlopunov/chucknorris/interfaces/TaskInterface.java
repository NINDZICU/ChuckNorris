package com.example.khlopunov.chucknorris.interfaces;

/**
 * Created by Admin on 23.12.2016.
 */

public interface TaskInterface {
    void onTaskFinish(String joke);
    void onTaskStarted() ;
    void onTaskProgress();
}
