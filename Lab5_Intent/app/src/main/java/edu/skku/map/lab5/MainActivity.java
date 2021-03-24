package edu.skku.map.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText id = findViewById(R.id.editText);
                String ID = id.getText().toString();

                EditText pw = findViewById(R.id.editText2);
                String PASS = pw.getText().toString();

                if(ID.equals("MAP") && PASS.equals("weloveprofessor")){
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                }
                else Toast.makeText(getApplicationContext(), "wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}