package com.example.khlopunov.chucknorris.AsyncTasks;

import android.os.AsyncTask;

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

public class MyAsync extends AsyncTask<Object, Object, String> {
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
    protected String doInBackground(Object... params) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.icndb.com").addConverterFactory(GsonConverterFactory.create())
                .build();


        ChuckNorrisApi openWeatherMap = retrofit.create(ChuckNorrisApi.class);

        Call<Value> weatherCall = openWeatherMap.getJoke();
        String joke = "";

        try {
            Response<Value> response = weatherCall.execute();
            if (response.errorBody() != null) {                                //обработка ошибки
//                result=false;
//                contin=true;
//                return String.valueOf(response.code());
            }
//            contin=true;
            Value value = response.body();
            joke = value.getJoke();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return joke;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mTaskInterface.onTaskFinish(!(s==null));
    }
}
