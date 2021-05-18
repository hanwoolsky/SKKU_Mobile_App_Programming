package edu.skku.map.async_test;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.service.controls.templates.TemperatureControlTemplate;
import android.widget.TextView;
import android.widget.Toast;
//14분
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView)findViewById(R.id.textView);
        final Context context = this;
        final int[] incount = {0};
        final int[] excount = {0};

        @SuppressLint("StaticFieldLeak") AsyncTask<Integer, Double, String> asyncTask = new AsyncTask<Integer, Double, String>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                Toast.makeText(context, "Start AsyncTask", Toast.LENGTH_LONG).show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                Toast.makeText(context, "End AsyncTask", Toast.LENGTH_LONG).show();
            }

            @Override
            protected void onProgressUpdate(Double... values) {
                super.onProgressUpdate(values);
                textView.setText("Pi = " + Double.toString(values[0]));
            }

            @Override
            protected String doInBackground(Integer... integers) {
                double value = 0;
                double rate = 0;
                int in_circle = 0;
                int ex_circle = 0;
                int count = 0;
                double PI = 3.141592;
                while(rate - PI > 0.000001 || rate - PI < -0.000001) {
                    double x = Math.random();
                    double y = Math.random();
                    try {
                        Thread.sleep(100);
                        value = x * x + y * y;
                        if (value <= 1) in_circle++;
                        else ex_circle++;
                        count++;
                        rate = (double) in_circle * 4 / count;
                        publishProgress(rate);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        };
        asyncTask.execute(1);
    }
}