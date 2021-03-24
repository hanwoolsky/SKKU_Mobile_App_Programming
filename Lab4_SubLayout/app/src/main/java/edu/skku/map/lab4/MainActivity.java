package edu.skku.map.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        container = (LinearLayout)findViewById(R.id.container);

        Button btn1 = (Button)findViewById(R.id.first);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.sublayout, container, true);

                TextView textView = (TextView)findViewById(R.id.textView);
                textView.setText("First");
                ListView listView = (ListView)findViewById(R.id.listView);

                ArrayList<Integer> data1 = new ArrayList<Integer>();
                for(int i = 0; i <= 10; i++)
                    data1.add(i);

                final ArrayAdapter adapter = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_list_item_1, data1);
                listView.setAdapter(adapter);
            }
        });

        Button btn2 = (Button)findViewById(R.id.second);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.sublayout, container, true);

                TextView textView = (TextView)findViewById(R.id.textView);
                textView.setText("Second");
                ListView listView = (ListView)findViewById(R.id.listView);

                ArrayList<Integer> data1 = new ArrayList<Integer>();
                for(int i = 1; i <= 10; i++)
                    data1.add((int) Math.pow(2,i));

                final ArrayAdapter adapter = new ArrayAdapter<Integer>(getApplicationContext(), android.R.layout.simple_list_item_1, data1);
                listView.setAdapter(adapter);
            }
        });

        Button btn3 = (Button)findViewById(R.id.third);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.sublayout, container, true);

                TextView textView = (TextView)findViewById(R.id.textView);
                textView.setText("Third");
                ListView listView = (ListView)findViewById(R.id.listView);

                ArrayList<String> data = new ArrayList<String>();
                data.add("2018310478");
                data.add("Hanwool Huh");
                data.add("Department of Mathematics / Software");
                data.add("College of Matural Science");
                data.add("SungKyunKwan University");

                final ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, data);
                listView.setAdapter(adapter);
            }
        });
    }
}