package com.example.jvertil.storms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String apiKey = "989a8a3a1eae1395fb8c41be2798b182";
        double latitude = 19.85333;
        double longitude =  -73.19167;
        String forecastUrl = "https://api.darksky.net/forecast/" + apiKey + "/" + latitude + "," + longitude;
        OkHttpClient client = new OkHttpClient();
        // Now we need to create a request that the client will send to the server
        Request request = new Request.Builder()
                .url(forecastUrl).build();

        // Next we create a call object in which we'll put the request
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    // We want to check if the response was successfull

                    if (response.isSuccessful()){
                        Log.v(TAG, response.body().string());
                    }

                } catch (IOException e) {
                    Log.e(TAG, "Execption caught: ", e);
                }

            }
        });



    }
}






































