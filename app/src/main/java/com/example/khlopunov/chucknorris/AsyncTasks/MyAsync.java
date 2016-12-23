package com.example.khlopunov.chucknorris.AsyncTasks;

import android.os.AsyncTask;

import com.example.khlopunov.chucknorris.POJO.Example;
import com.example.khlopunov.chucknorris.POJO.Value;
import com.example.khlopunov.chucknorris.interfaces.ChuckNorrisApi;
import com.example.khlopunov.chucknorris.interfaces.TaskInterface;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Admin on 23.12.2016.
 */

public class MyAsync extends AsyncTask<Void, Void, String> {
    private TaskInterface mTaskInterface;

    public MyAsync(TaskInterface taskInterface){
        this.mTaskInterface = taskInterface;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mTaskInterface.onTaskStarted();
    }

    @Override
    protected String doInBackground(Void... params) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.icndb.com/").addConverterFactory(GsonConverterFactory.create())
                .build();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ChuckNorrisApi chuckNorrisApi = retrofit.create(ChuckNorrisApi.class);

        Call<Example> jokeCall = chuckNorrisApi.getJoke();
        String joke="";
        try {
            Response<Example> response = jokeCall.execute();
            if (response.errorBody() != null) {                                //обработка ошибки
               joke="Не удалось загрузить шутку";
            }else {
                Example example = response.body();
                joke = example.getValue().getJoke();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return joke;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mTaskInterface.onTaskFinish(s);
    }
}
