package edu.skku.map.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit = findViewById(R.id.editText);
                String search = edit.getText().toString();
                Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                intent.putExtra(SearchManager.QUERY, search);
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edit2 = findViewById(R.id.editText2);
                String phone = String.format("tel:%s", edit2.getText().toString());
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phone));
                intent.setData(Uri.parse(phone));
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivity(intent);
            }
        });
    }
}