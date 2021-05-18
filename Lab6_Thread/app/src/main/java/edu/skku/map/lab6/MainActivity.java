package edu.skku.map.lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    ThreadEx t1, t2;
    int switch_flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.resultText);
        button = (Button)findViewById(R.id.switchBtn);

        t1 = new ThreadEx();
        t2 = new ThreadEx();

        Thread thread1 = new Thread(t1, "A");
        Thread thread2 = new Thread(t2, "B");

        thread1.start();
        thread2.start();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switch_flag == 0)
                    textView.setText(t1.Result);
                else
                    textView.setText(t2.Result);
                switch_flag = 1 - switch_flag;
            }
        });
    }
}