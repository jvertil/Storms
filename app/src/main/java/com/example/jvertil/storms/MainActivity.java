package com.example.jvertil.storms;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

        if (isNetworkAvailable()) {
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
                        Log.v(TAG, response.body().string());
                        if (response.isSuccessful()) {

                        } else {
                            alertUserAboutError();
                        }

                    } catch (IOException e) {
                        Log.e(TAG, "Execption caught: ", e);
                    }

                }
            });
        }

        else {
            Toast.makeText(this, R.string.network_unavailable_message, Toast.LENGTH_LONG).show();
        }

        // The main UI thread resumes here



    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()){
            isAvailable = true;
        }
        return isAvailable;
    }

    private void alertUserAboutError() {
        AlertDialogFragment dialog = new AlertDialogFragment();
        dialog.show(getFragmentManager(), "error_dialog");
    }
}






































