package edu.skku.map.lab8_get_movie_data;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import edu.skku.map.lab8_get_movie_data.DataModel;
import edu.skku.map.lab8_get_movie_data.R;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView1, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)findViewById(R.id.url);
        textView2 = (TextView)findViewById(R.id.response);
        button = (Button)findViewById(R.id.sendingGET);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText title = findViewById(R.id.editTextTextPersonName);
                String Title = title.getText().toString();

                OkHttpClient client = new OkHttpClient();
                HttpUrl.Builder urlBuilder = HttpUrl.parse("http://www.omdbapi.com").newBuilder();
                urlBuilder.addQueryParameter("t", Title);
                urlBuilder.addQueryParameter("apikey", "53a78f6");

                String url = urlBuilder.build().toString();

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
                        final DataModel data = gson.fromJson(myResponse, DataModel.class);

                        MainActivity.this.runOnUiThread(new Runnable() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void run() {
                                textView2.setText("Title : " + data.getTitle() + "\n"
                                        + "Year : " + data.getYear() + "\n"
                                        + "Released Date : " + data.getReleased() + "\n"
                                        + "Runtime : " + data.getRuntime() + "\n"
                                        + "Director : " + data.getDirector() + "\n"
                                        + "Genre : " + data.getGenre() + "\n"
                                        + "IMDB Rates : " + data.getImdbRating() + "\n"
                                        + "Metascore : " + data.getMetascore());
                            }
                        });
                    }
                });

            }
        });
    }
}