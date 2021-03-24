package edu.skku.map.pa1_puzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int[][] puzzle = {
            {1, 2 ,3},
            {4, 5, 6},
            {7, 8, 0}
    };

    public void checkFin(){
        int count = 0;
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (puzzle[r][c] == r * 3 + c + 1) {
                    count++;
                }
            }
        }
        if (count == 8) {
            Toast.makeText(getApplicationContext(), "Congratulations!", Toast.LENGTH_SHORT).show();
            puzzle[2][2] = -1;
        }
        count = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int[] imgArray = {
                R.drawable.image09, R.drawable.image01, R.drawable.image02,
                R.drawable.image03, R.drawable.image04, R.drawable.image05,
                R.drawable.image06, R.drawable.image07, R.drawable.image08
        };

        final ImageView img1 = (ImageView)findViewById(R.id.imageView1);
        final ImageView img2 = (ImageView)findViewById(R.id.imageView2);
        final ImageView img3 = (ImageView)findViewById(R.id.imageView3);
        final ImageView img4 = (ImageView)findViewById(R.id.imageView4);
        final ImageView img5 = (ImageView)findViewById(R.id.imageView5);
        final ImageView img6 = (ImageView)findViewById(R.id.imageView6);
        final ImageView img7 = (ImageView)findViewById(R.id.imageView7);
        final ImageView img8 = (ImageView)findViewById(R.id.imageView8);
        final ImageView img9 = (ImageView)findViewById(R.id.imageView9);

        img1.setImageResource(imgArray[puzzle[0][0]]);
        img2.setImageResource(imgArray[puzzle[0][1]]);
        img3.setImageResource(imgArray[puzzle[0][2]]);
        img4.setImageResource(imgArray[puzzle[1][0]]);
        img5.setImageResource(imgArray[puzzle[1][1]]);
        img6.setImageResource(imgArray[puzzle[1][2]]);
        img7.setImageResource(imgArray[puzzle[2][0]]);
        img8.setImageResource(imgArray[puzzle[2][1]]);
        img9.setImageResource(imgArray[puzzle[2][2]]);

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[][] puzzle = {
                        {1, 2 ,3},
                        {4, 5, 6},
                        {7, 8, 0}
                };
                img1.setImageResource(imgArray[puzzle[0][0]]);
                img2.setImageResource(imgArray[puzzle[0][1]]);
                img3.setImageResource(imgArray[puzzle[0][2]]);
                img4.setImageResource(imgArray[puzzle[1][0]]);
                img5.setImageResource(imgArray[puzzle[1][1]]);
                img6.setImageResource(imgArray[puzzle[1][2]]);
                img7.setImageResource(imgArray[puzzle[2][0]]);
                img8.setImageResource(imgArray[puzzle[2][1]]);
                img9.setImageResource(imgArray[puzzle[2][2]]);
            }
        });

        Button btn1 = findViewById(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });

        Button btn2 = findViewById(R.id.button3);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                for (int r = 0; r < 3; r++) {
                    int a = random.nextInt(3);
                    int b = random.nextInt(3);
                    int tmp = puzzle[r][a];
                    puzzle[r][a] = puzzle[r][b];
                    puzzle[r][b] = tmp;
                }
                for (int c = 0; c < 3; c++) {
                    int a = random.nextInt(3);
                    int b = random.nextInt(3);
                    int tmp = puzzle[a][c];
                    puzzle[a][c] = puzzle[b][c];
                    puzzle[b][c] = tmp;
                }
                img1.setImageResource(imgArray[puzzle[0][0]]);
                img2.setImageResource(imgArray[puzzle[0][1]]);
                img3.setImageResource(imgArray[puzzle[0][2]]);
                img4.setImageResource(imgArray[puzzle[1][0]]);
                img5.setImageResource(imgArray[puzzle[1][1]]);
                img6.setImageResource(imgArray[puzzle[1][2]]);
                img7.setImageResource(imgArray[puzzle[2][0]]);
                img8.setImageResource(imgArray[puzzle[2][1]]);
                img9.setImageResource(imgArray[puzzle[2][2]]);

                img1.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if(puzzle[0][1] == 0){
                            img1.setImageResource(imgArray[0]);
                            img2.setImageResource(imgArray[puzzle[0][0]]);
                            puzzle[0][1] = puzzle[0][0];
                            puzzle[0][0] = 0;
                        }
                        else if(puzzle[1][0] == 0){
                            img1.setImageResource(imgArray[0]);
                            img4.setImageResource(imgArray[puzzle[0][0]]);
                            puzzle[1][0] = puzzle[0][0];
                            puzzle[0][0] = 0;
                        }
                        checkFin();
                    }
                });
                img2.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if(puzzle[0][0] == 0){
                            img2.setImageResource(imgArray[0]);
                            img1.setImageResource(imgArray[puzzle[0][1]]);
                            puzzle[0][0] = puzzle[0][1];
                            puzzle[0][1] = 0;
                        }
                        else if(puzzle[0][2] == 0){
                            img2.setImageResource(imgArray[0]);
                            img3.setImageResource(imgArray[puzzle[0][1]]);
                            puzzle[0][2] = puzzle[0][1];
                            puzzle[0][1] = 0;
                        }
                        else if(puzzle[1][1] == 0){
                            img2.setImageResource(imgArray[0]);
                            img5.setImageResource(imgArray[puzzle[0][1]]);
                            puzzle[1][1] = puzzle[0][1];
                            puzzle[0][1] = 0;
                        }
                        checkFin();
                    }
                });
                img3.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if(puzzle[0][1] == 0){
                            img3.setImageResource(imgArray[0]);
                            img2.setImageResource(imgArray[puzzle[0][2]]);
                            puzzle[0][1] = puzzle[0][2];
                            puzzle[0][2] = 0;
                        }
                        else if(puzzle[1][2] == 0){
                            img3.setImageResource(imgArray[0]);
                            img6.setImageResource(imgArray[puzzle[0][2]]);
                            puzzle[1][2] = puzzle[0][2];
                            puzzle[0][2] = 0;
                        }
                        checkFin();
                    }
                });
                img4.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if (puzzle[0][0] == 0) {
                            img4.setImageResource(imgArray[0]);
                            img1.setImageResource(imgArray[puzzle[1][0]]);
                            puzzle[0][0] = puzzle[1][0];
                            puzzle[1][0] = 0;
                        } else if (puzzle[1][1] == 0) {
                            img4.setImageResource(imgArray[0]);
                            img5.setImageResource(imgArray[puzzle[1][0]]);
                            puzzle[1][1] = puzzle[1][0];
                            puzzle[1][0] = 0;
                        } else if (puzzle[2][0] == 0) {
                            img4.setImageResource(imgArray[0]);
                            img7.setImageResource(imgArray[puzzle[1][0]]);
                            puzzle[2][0] = puzzle[1][0];
                            puzzle[1][0] = 0;
                        }
                        checkFin();
                    }
                });
                img5.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if(puzzle[0][1] == 0){
                            img5.setImageResource(imgArray[0]);
                            img2.setImageResource(imgArray[puzzle[1][1]]);
                            puzzle[0][1] = puzzle[1][1];
                            puzzle[1][1] = 0;
                        }
                        else if(puzzle[1][0] == 0){
                            img5.setImageResource(imgArray[0]);
                            img4.setImageResource(imgArray[puzzle[1][1]]);
                            puzzle[1][0] = puzzle[1][1];
                            puzzle[1][1] = 0;
                        }
                        else if(puzzle[1][2] == 0){
                            img5.setImageResource(imgArray[0]);
                            img6.setImageResource(imgArray[puzzle[1][1]]);
                            puzzle[1][2] = puzzle[1][1];
                            puzzle[1][1] = 0;
                        }
                        else if(puzzle[2][1] == 0){
                            img5.setImageResource(imgArray[0]);
                            img8.setImageResource(imgArray[puzzle[1][1]]);
                            puzzle[2][1] = puzzle[1][1];
                            puzzle[1][1] = 0;
                        }
                        checkFin();
                    }
                });
                img6.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if(puzzle[0][2] == 0){
                            img6.setImageResource(imgArray[0]);
                            img3.setImageResource(imgArray[puzzle[1][2]]);
                            puzzle[0][2] = puzzle[1][2];
                            puzzle[1][2] = 0;
                        }
                        else if(puzzle[1][1] == 0){
                            img6.setImageResource(imgArray[0]);
                            img5.setImageResource(imgArray[puzzle[1][2]]);
                            puzzle[1][1] = puzzle[1][2];
                            puzzle[1][2] = 0;
                        }
                        else if(puzzle[2][2] == 0){
                            img6.setImageResource(imgArray[0]);
                            img9.setImageResource(imgArray[puzzle[1][2]]);
                            puzzle[2][2] = puzzle[1][2];
                            puzzle[1][2] = 0;
                        }
                        checkFin();
                    }
                });
                img7.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if(puzzle[1][0] == 0){
                            img7.setImageResource(imgArray[0]);
                            img4.setImageResource(imgArray[puzzle[2][0]]);
                            puzzle[1][0] = puzzle[2][0];
                            puzzle[2][0] = 0;
                        }
                        else if(puzzle[2][1] == 0){
                            img7.setImageResource(imgArray[0]);
                            img8.setImageResource(imgArray[puzzle[2][0]]);
                            puzzle[2][1] = puzzle[2][0];
                            puzzle[2][0] = 0;
                        }
                        checkFin();
                    }
                });
                img8.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if(puzzle[1][1] == 0){
                            img8.setImageResource(imgArray[0]);
                            img5.setImageResource(imgArray[puzzle[2][1]]);
                            puzzle[1][1] = puzzle[2][1];
                            puzzle[2][1] = 0;
                        }
                        else if(puzzle[2][0] == 0){
                            img8.setImageResource(imgArray[0]);
                            img7.setImageResource(imgArray[puzzle[2][1]]);
                            puzzle[2][0] = puzzle[2][1];
                            puzzle[2][1] = 0;
                        }
                        else if(puzzle[2][2] == 0){
                            img8.setImageResource(imgArray[0]);
                            img9.setImageResource(imgArray[puzzle[2][1]]);
                            puzzle[2][2] = puzzle[2][1];
                            puzzle[2][1] = 0;
                        }
                        checkFin();
                    }
                });
                img9.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if(puzzle[1][2] == 0){
                            img9.setImageResource(imgArray[0]);
                            img6.setImageResource(imgArray[puzzle[2][2]]);
                            puzzle[1][2] = puzzle[2][2];
                            puzzle[2][2] = 0;
                        }
                        else if(puzzle[2][1] == 0){
                            img9.setImageResource(imgArray[0]);
                            img8.setImageResource(imgArray[puzzle[2][2]]);
                            puzzle[2][1] = puzzle[2][2];
                            puzzle[2][2] = 0;
                        }
                        checkFin();
                    }
                });
            }
        });
    }
}