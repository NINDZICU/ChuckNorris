package com.example.khlopunov.chucknorris.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.khlopunov.chucknorris.AsyncTasks.MyAsync;
import com.example.khlopunov.chucknorris.interfaces.TaskInterface;

/**
 * Created by Admin on 23.12.2016.
 */

public class LoaderJokeFragment extends Fragment {
    TaskInterface mTaskInterface;
    MyAsync myAsync;

    public boolean isRunning(){
        return myAsync!=null;
    }


    @Override
    public void onAttach(Context context) {
        setInterface(context);
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        setInterface(activity);
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    public void startAsync() {
        if(myAsync==null) {
            myAsync = new MyAsync(mTaskInterface);
            myAsync.execute();
        }
    }


    private void setInterface(Context context) {
        if (context instanceof TaskInterface) {
            mTaskInterface = (TaskInterface) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mTaskInterface=null;
    }
    public void stopAsync(){
        if(myAsync!=null){
            myAsync.cancel(true);
            myAsync=null;
        }
    }

}
