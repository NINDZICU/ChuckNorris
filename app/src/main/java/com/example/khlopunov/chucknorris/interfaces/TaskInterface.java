package com.example.khlopunov.chucknorris.interfaces;

/**
 * Created by Admin on 23.12.2016.
 */

public interface TaskInterface {
    void onTaskFinish(boolean success);
    void onTaskStarted() ;
    void onTaskProgress();
}
