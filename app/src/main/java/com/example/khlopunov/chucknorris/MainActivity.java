package com.example.khlopunov.chucknorris;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khlopunov.chucknorris.fragments.LoaderJokeFragment;
import com.example.khlopunov.chucknorris.interfaces.TaskInterface;

public class MainActivity extends AppCompatActivity implements TaskInterface{

    TextView tvJoke;
    Button btnRefresh;
    ProgressBar progressBar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvJoke = (TextView) findViewById(R.id.tv_joke);
        btnRefresh = (Button) findViewById(R.id.btn_refresh);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        if(getAsyncFragment().isRunning()){
            progressBar.setVisibility(View.VISIBLE);
        }
        else progressBar.setVisibility(View.GONE);

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    getAsyncFragment().startAsync();
            }
        });
    }
    private LoaderJokeFragment getAsyncFragment(){
        LoaderJokeFragment fragment = (LoaderJokeFragment) getSupportFragmentManager().findFragmentByTag(LoaderJokeFragment.class.getName());
        if(fragment ==null){
            fragment = new LoaderJokeFragment();
            getSupportFragmentManager().beginTransaction().add(fragment, LoaderJokeFragment.class.getName()).commit();
        }
        return fragment;
    }

    @Override
    public void onTaskFinish(String joke) {
        getAsyncFragment().stopAsync();
        progressBar.setVisibility(View.GONE);
        tvJoke.setText(joke);
        Toast.makeText(this, "Finish", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTaskStarted() {
        progressBar.setVisibility(View.VISIBLE);
        Toast.makeText(this, "Start!!", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onTaskProgress() {

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("joke", tvJoke.getText().toString());
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        tvJoke.setText(savedInstanceState.getString("joke"));
    }
}
