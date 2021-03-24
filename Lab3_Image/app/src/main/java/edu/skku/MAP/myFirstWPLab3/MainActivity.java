package edu.skku.MAP.myFirstWPLab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int i = 0;
    int j = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView text = (TextView)findViewById(R.id.textView);

        Button btn1 = (Button)findViewById(R.id.button_top);
        btn1.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                i = 1 - i;
                if(i == 1){
                    text.setText("2018310478");
                }
                if(i == 0){
                    text.setText("Hanwool Huh");
                }
            }
        });

        Button btn3 = (Button)findViewById(R.id.button_character);
        final ImageView img1 = (ImageView)findViewById(R.id.imageView);
        btn3.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                j++;
                j = j % 3;
                if(j == 0){
                    img1.setImageResource(R.drawable.olaf1);
                }
                else if(j == 1){
                    img1.setImageResource(R.drawable.olaf2);
                }
                else{
                    img1.setImageResource(R.drawable.olaf3);
                }
            }
        });
    }
}