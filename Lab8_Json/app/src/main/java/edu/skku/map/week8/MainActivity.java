package edu.skku.map.week8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    Button button, button2;
    TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)findViewById(R.id.url);
        textView2 = (TextView)findViewById(R.id.response);
        button = (Button)findViewById(R.id.sendingGET);
        button2 = (Button)findViewById(R.id.sendingPOST);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient();

                DataModel data = new DataModel();
                data.setName("Sam");
                data.setJob("Programmer");

                Gson gson = new Gson();
                String json = gson.toJson(data, DataModel.class);

                HttpUrl.Builder urlBuilder = HttpUrl.parse("https://reqres.in/api/users").newBuilder();
                String url = urlBuilder.build().toString();
                textView1.setText(url);

                Request req = new Request.Builder().url(url).post(RequestBody.create(MediaType.get("application/json"), json)).build();

                client.newCall(req).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        final String myResponse = response.body().string();

                        final DataModel data = gson.fromJson(myResponse, DataModel.class);


                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView2.setText(myResponse);
                            }
                        });
                    }
                });
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpClient client = new OkHttpClient();
                HttpUrl.Builder urlBuilder = HttpUrl.parse("https://reqres.in/api/users").newBuilder();
                urlBuilder.addQueryParameter("page", "2");

                String url = urlBuilder.build().toString();
                textView1.setText(url);

                Request req = new Request.Builder().url(url).build();

                client.newCall(req).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        final String myResponse = response.body().string();

                        Gson gson = new GsonBuilder().create();
                        DataModel data = gson.fromJson(myResponse, DataModel.class);

                        MainActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView2.setText(data.getPage());
                            }
                        });
                    }
                });

            }
        });
    }
}