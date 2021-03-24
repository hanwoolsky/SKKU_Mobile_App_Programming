package edu.skku.map.pa1_puzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {

    int[][] puzzle2 = {
            {1, 2 , 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    public void checkFin2(){
        int count = 0;
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                if (puzzle2[r][c] == r * 4 + c + 1) {
                    count++;
                }
            }
        }
        if (count == 15) {
            Toast.makeText(getApplicationContext(), "Congratulations!", Toast.LENGTH_SHORT).show();
            puzzle2[3][3] = -1;
        }
        count = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        int[] imgArray = {
                R.drawable.image0016, R.drawable.image001, R.drawable.image002, R.drawable.image003,
                R.drawable.image004, R.drawable.image005, R.drawable.image006, R.drawable.image007,
                R.drawable.image008, R.drawable.image009, R.drawable.image0010, R.drawable.image0011,
                R.drawable.image0012, R.drawable.image0013, R.drawable.image0014, R.drawable.image0015
        };

        final ImageView img01 = (ImageView)findViewById(R.id.imageView001);
        final ImageView img02 = (ImageView)findViewById(R.id.imageView002);
        final ImageView img03 = (ImageView)findViewById(R.id.imageView003);
        final ImageView img04 = (ImageView)findViewById(R.id.imageView004);
        final ImageView img05 = (ImageView)findViewById(R.id.imageView005);
        final ImageView img06 = (ImageView)findViewById(R.id.imageView006);
        final ImageView img07 = (ImageView)findViewById(R.id.imageView007);
        final ImageView img08 = (ImageView)findViewById(R.id.imageView008);
        final ImageView img09 = (ImageView)findViewById(R.id.imageView009);
        final ImageView img010 = (ImageView)findViewById(R.id.imageView010);
        final ImageView img011 = (ImageView)findViewById(R.id.imageView011);
        final ImageView img012 = (ImageView)findViewById(R.id.imageView012);
        final ImageView img013 = (ImageView)findViewById(R.id.imageView013);
        final ImageView img014 = (ImageView)findViewById(R.id.imageView014);
        final ImageView img015 = (ImageView)findViewById(R.id.imageView015);
        final ImageView img016 = (ImageView)findViewById(R.id.imageView016);

        Button btn = findViewById(R.id.button5);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[][] puzzle2 = {
                        {1, 2 , 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12},
                        {13, 14, 15, 0}
                };
                img01.setImageResource(imgArray[puzzle2[0][0]]);
                img02.setImageResource(imgArray[puzzle2[0][1]]);
                img03.setImageResource(imgArray[puzzle2[0][2]]);
                img04.setImageResource(imgArray[puzzle2[0][3]]);
                img05.setImageResource(imgArray[puzzle2[1][0]]);
                img06.setImageResource(imgArray[puzzle2[1][1]]);
                img07.setImageResource(imgArray[puzzle2[1][2]]);
                img08.setImageResource(imgArray[puzzle2[1][3]]);
                img09.setImageResource(imgArray[puzzle2[2][0]]);
                img010.setImageResource(imgArray[puzzle2[2][1]]);
                img011.setImageResource(imgArray[puzzle2[2][2]]);
                img012.setImageResource(imgArray[puzzle2[2][3]]);
                img013.setImageResource(imgArray[puzzle2[3][0]]);
                img014.setImageResource(imgArray[puzzle2[3][1]]);
                img015.setImageResource(imgArray[puzzle2[3][2]]);
                img016.setImageResource(imgArray[puzzle2[3][3]]);
            }
        });

        Button btn1 = findViewById(R.id.button4);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        img01.setImageResource(imgArray[puzzle2[0][0]]);
        img02.setImageResource(imgArray[puzzle2[0][1]]);
        img03.setImageResource(imgArray[puzzle2[0][2]]);
        img04.setImageResource(imgArray[puzzle2[0][3]]);
        img05.setImageResource(imgArray[puzzle2[1][0]]);
        img06.setImageResource(imgArray[puzzle2[1][1]]);
        img07.setImageResource(imgArray[puzzle2[1][2]]);
        img08.setImageResource(imgArray[puzzle2[1][3]]);
        img09.setImageResource(imgArray[puzzle2[2][0]]);
        img010.setImageResource(imgArray[puzzle2[2][1]]);
        img011.setImageResource(imgArray[puzzle2[2][2]]);
        img012.setImageResource(imgArray[puzzle2[2][3]]);
        img013.setImageResource(imgArray[puzzle2[3][0]]);
        img014.setImageResource(imgArray[puzzle2[3][1]]);
        img015.setImageResource(imgArray[puzzle2[3][2]]);
        img016.setImageResource(imgArray[puzzle2[3][3]]);

        Button btn2 = findViewById(R.id.button6);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                for (int r = 0; r < 4; r++) {
                    int a = random.nextInt(4);
                    int b = random.nextInt(4);
                    int tmp = puzzle2[r][a];
                    puzzle2[r][a] = puzzle2[r][b];
                    puzzle2[r][b] = tmp;
                }
                for (int c = 0; c < 4; c++) {
                    int a = random.nextInt(4);
                    int b = random.nextInt(4);
                    int tmp = puzzle2[a][c];
                    puzzle2[a][c] = puzzle2[b][c];
                    puzzle2[b][c] = tmp;
                }
                img01.setImageResource(imgArray[puzzle2[0][0]]);
                img02.setImageResource(imgArray[puzzle2[0][1]]);
                img03.setImageResource(imgArray[puzzle2[0][2]]);
                img04.setImageResource(imgArray[puzzle2[0][3]]);
                img05.setImageResource(imgArray[puzzle2[1][0]]);
                img06.setImageResource(imgArray[puzzle2[1][1]]);
                img07.setImageResource(imgArray[puzzle2[1][2]]);
                img08.setImageResource(imgArray[puzzle2[1][3]]);
                img09.setImageResource(imgArray[puzzle2[2][0]]);
                img010.setImageResource(imgArray[puzzle2[2][1]]);
                img011.setImageResource(imgArray[puzzle2[2][2]]);
                img012.setImageResource(imgArray[puzzle2[2][3]]);
                img013.setImageResource(imgArray[puzzle2[3][0]]);
                img014.setImageResource(imgArray[puzzle2[3][1]]);
                img015.setImageResource(imgArray[puzzle2[3][2]]);
                img016.setImageResource(imgArray[puzzle2[3][3]]);

                img01.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if(puzzle2[0][1] == 0){
                            img01.setImageResource(imgArray[0]);
                            img02.setImageResource(imgArray[puzzle2[0][0]]);
                            puzzle2[0][1] = puzzle2[0][0];
                            puzzle2[0][0] = 0;
                        }
                        else if(puzzle2[1][0] == 0){
                            img01.setImageResource(imgArray[0]);
                            img05.setImageResource(imgArray[puzzle2[0][0]]);
                            puzzle2[1][0] = puzzle2[0][0];
                            puzzle2[0][0] = 0;
                        }
                        checkFin2();
                    }
                });
                img02.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if(puzzle2[0][0] == 0){
                            img02.setImageResource(imgArray[0]);
                            img01.setImageResource(imgArray[puzzle2[0][1]]);
                            puzzle2[0][0] = puzzle2[0][1];
                            puzzle2[0][1] = 0;
                        }
                        else if(puzzle2[0][2] == 0){
                            img02.setImageResource(imgArray[0]);
                            img03.setImageResource(imgArray[puzzle2[0][1]]);
                            puzzle2[0][2] = puzzle2[0][1];
                            puzzle2[0][1] = 0;
                        }
                        else if(puzzle2[1][1] == 0){
                            img02.setImageResource(imgArray[0]);
                            img06.setImageResource(imgArray[puzzle2[0][1]]);
                            puzzle2[1][1] = puzzle2[0][1];
                            puzzle2[0][1] = 0;
                        }
                        checkFin2();
                    }
                });
                img03.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if(puzzle2[0][1] == 0){
                            img03.setImageResource(imgArray[0]);
                            img02.setImageResource(imgArray[puzzle2[0][2]]);
                            puzzle2[0][1] = puzzle2[0][2];
                            puzzle2[0][2] = 0;
                        }
                        else if(puzzle2[0][3] == 0){
                            img03.setImageResource(imgArray[0]);
                            img04.setImageResource(imgArray[puzzle2[0][2]]);
                            puzzle2[0][3] = puzzle2[0][2];
                            puzzle2[0][2] = 0;
                        }
                        else if(puzzle2[1][2] == 0){
                            img03.setImageResource(imgArray[0]);
                            img07.setImageResource(imgArray[puzzle2[0][2]]);
                            puzzle2[1][2] = puzzle2[0][2];
                            puzzle2[0][2] = 0;
                        }
                        checkFin2();
                    }
                });
                img04.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if (puzzle2[0][2] == 0) {
                            img04.setImageResource(imgArray[0]);
                            img03.setImageResource(imgArray[puzzle2[0][3]]);
                            puzzle2[0][2] = puzzle2[0][3];
                            puzzle2[0][3] = 0;
                        } else if (puzzle2[1][3] == 0) {
                            img04.setImageResource(imgArray[0]);
                            img08.setImageResource(imgArray[puzzle2[0][3]]);
                            puzzle2[1][3] = puzzle2[0][3];
                            puzzle2[0][3] = 0;
                        }
                        checkFin2();
                    }
                });
                img05.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if (puzzle2[0][0] == 0) {
                            img05.setImageResource(imgArray[0]);
                            img01.setImageResource(imgArray[puzzle2[1][0]]);
                            puzzle2[0][0] = puzzle2[1][0];
                            puzzle2[1][0] = 0;
                        } else if (puzzle2[1][1] == 0) {
                            img05.setImageResource(imgArray[0]);
                            img06.setImageResource(imgArray[puzzle2[1][0]]);
                            puzzle2[1][1] = puzzle2[1][0];
                            puzzle2[1][0] = 0;
                        } else if (puzzle2[2][0] == 0) {
                            img05.setImageResource(imgArray[0]);
                            img09.setImageResource(imgArray[puzzle2[1][0]]);
                            puzzle2[2][0] = puzzle2[1][0];
                            puzzle2[1][0] = 0;
                        }
                        checkFin2();
                    }
                });
                img06.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if(puzzle2[0][1] == 0){
                            img06.setImageResource(imgArray[0]);
                            img02.setImageResource(imgArray[puzzle2[1][1]]);
                            puzzle2[0][1] = puzzle2[1][1];
                            puzzle2[1][1] = 0;
                        }
                        else if(puzzle2[1][0] == 0){
                            img06.setImageResource(imgArray[0]);
                            img05.setImageResource(imgArray[puzzle2[1][1]]);
                            puzzle2[1][0] = puzzle2[1][1];
                            puzzle2[1][1] = 0;
                        }
                        else if(puzzle2[1][2] == 0){
                            img06.setImageResource(imgArray[0]);
                            img07.setImageResource(imgArray[puzzle2[1][1]]);
                            puzzle2[1][2] = puzzle2[1][1];
                            puzzle2[1][1] = 0;
                        }
                        else if(puzzle2[2][1] == 0){
                            img06.setImageResource(imgArray[0]);
                            img010.setImageResource(imgArray[puzzle2[1][1]]);
                            puzzle2[2][1] = puzzle2[1][1];
                            puzzle2[1][1] = 0;
                        }
                        checkFin2();
                    }
                });
                img07.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if(puzzle2[0][2] == 0){
                            img07.setImageResource(imgArray[0]);
                            img03.setImageResource(imgArray[puzzle2[1][2]]);
                            puzzle2[0][2] = puzzle2[1][2];
                            puzzle2[1][2] = 0;
                        }
                        else if(puzzle2[1][1] == 0){
                            img07.setImageResource(imgArray[0]);
                            img06.setImageResource(imgArray[puzzle2[1][2]]);
                            puzzle2[1][1] = puzzle2[1][2];
                            puzzle2[1][2] = 0;
                        }
                        else if(puzzle2[1][3] == 0){
                            img07.setImageResource(imgArray[0]);
                            img08.setImageResource(imgArray[puzzle2[1][2]]);
                            puzzle2[1][3] = puzzle2[1][2];
                            puzzle2[1][2] = 0;
                        }
                        else if(puzzle2[2][2] == 0){
                            img07.setImageResource(imgArray[0]);
                            img011.setImageResource(imgArray[puzzle2[1][2]]);
                            puzzle2[2][2] = puzzle2[1][2];
                            puzzle2[1][2] = 0;
                        }
                        checkFin2();
                    }
                });
                img08.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if(puzzle2[0][3] == 0){
                            img08.setImageResource(imgArray[0]);
                            img04.setImageResource(imgArray[puzzle2[1][3]]);
                            puzzle2[0][3] = puzzle2[1][3];
                            puzzle2[1][3] = 0;
                        }
                        else if(puzzle2[1][2] == 0){
                            img08.setImageResource(imgArray[0]);
                            img07.setImageResource(imgArray[puzzle2[1][3]]);
                            puzzle2[1][2] = puzzle2[1][3];
                            puzzle2[1][3] = 0;
                        }
                        else if(puzzle2[2][3] == 0){
                            img08.setImageResource(imgArray[0]);
                            img012.setImageResource(imgArray[puzzle2[1][3]]);
                            puzzle2[2][3] = puzzle2[1][3];
                            puzzle2[1][3] = 0;
                        }
                        checkFin2();
                    }
                });
                img09.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if(puzzle2[1][0] == 0){
                            img09.setImageResource(imgArray[0]);
                            img05.setImageResource(imgArray[puzzle2[2][0]]);
                            puzzle2[1][0] = puzzle2[2][0];
                            puzzle2[2][0] = 0;
                        }
                        else if(puzzle2[2][1] == 0){
                            img09.setImageResource(imgArray[0]);
                            img010.setImageResource(imgArray[puzzle2[2][0]]);
                            puzzle2[2][1] = puzzle2[2][0];
                            puzzle2[2][0] = 0;
                        }
                        else if(puzzle2[3][0] == 0){
                            img09.setImageResource(imgArray[0]);
                            img013.setImageResource(imgArray[puzzle2[2][0]]);
                            puzzle2[3][0] = puzzle2[2][0];
                            puzzle2[2][0] = 0;
                        }
                        checkFin2();
                    }
                });
                img010.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if(puzzle2[1][1] == 0){
                            img010.setImageResource(imgArray[0]);
                            img06.setImageResource(imgArray[puzzle2[2][1]]);
                            puzzle2[1][1] = puzzle2[2][1];
                            puzzle2[2][1] = 0;
                        }
                        else if(puzzle2[2][0] == 0){
                            img010.setImageResource(imgArray[0]);
                            img09.setImageResource(imgArray[puzzle2[2][1]]);
                            puzzle2[2][0] = puzzle2[2][1];
                            puzzle2[2][1] = 0;
                        }
                        else if(puzzle2[2][2] == 0){
                            img010.setImageResource(imgArray[0]);
                            img011.setImageResource(imgArray[puzzle2[2][1]]);
                            puzzle2[2][2] = puzzle2[2][1];
                            puzzle2[2][1] = 0;
                        }
                        else if(puzzle2[3][1] == 0){
                            img010.setImageResource(imgArray[0]);
                            img014.setImageResource(imgArray[puzzle2[2][1]]);
                            puzzle2[3][1] = puzzle2[2][1];
                            puzzle2[2][1] = 0;
                        }
                        checkFin2();
                    }
                });
                img011.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if(puzzle2[1][2] == 0){
                            img011.setImageResource(imgArray[0]);
                            img07.setImageResource(imgArray[puzzle2[2][2]]);
                            puzzle2[1][2] = puzzle2[2][2];
                            puzzle2[2][2] = 0;
                        }
                        else if(puzzle2[2][1] == 0){
                            img011.setImageResource(imgArray[0]);
                            img010.setImageResource(imgArray[puzzle2[2][2]]);
                            puzzle2[2][1] = puzzle2[2][2];
                            puzzle2[2][2] = 0;
                        }
                        else if(puzzle2[2][3] == 0){
                            img011.setImageResource(imgArray[0]);
                            img012.setImageResource(imgArray[puzzle2[2][2]]);
                            puzzle2[2][3] = puzzle2[2][2];
                            puzzle2[2][2] = 0;
                        }
                        else if(puzzle2[3][2] == 0){
                            img011.setImageResource(imgArray[0]);
                            img015.setImageResource(imgArray[puzzle2[2][2]]);
                            puzzle2[3][2] = puzzle2[2][2];
                            puzzle2[2][2] = 0;
                        }
                        checkFin2();
                    }
                });
                img012.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if(puzzle2[1][3] == 0){
                            img012.setImageResource(imgArray[0]);
                            img08.setImageResource(imgArray[puzzle2[2][3]]);
                            puzzle2[1][3] = puzzle2[2][3];
                            puzzle2[2][3] = 0;
                        }
                        else if(puzzle2[2][2] == 0){
                            img012.setImageResource(imgArray[0]);
                            img011.setImageResource(imgArray[puzzle2[2][3]]);
                            puzzle2[2][2] = puzzle2[2][3];
                            puzzle2[2][3] = 0;
                        }
                        else if(puzzle2[3][3] == 0){
                            img012.setImageResource(imgArray[0]);
                            img016.setImageResource(imgArray[puzzle2[2][3]]);
                            puzzle2[3][3] = puzzle2[2][3];
                            puzzle2[2][3] = 0;
                        }
                        checkFin2();
                    }
                });
                img013.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if(puzzle2[2][0] == 0){
                            img013.setImageResource(imgArray[0]);
                            img09.setImageResource(imgArray[puzzle2[3][0]]);
                            puzzle2[2][0] = puzzle2[3][0];
                            puzzle2[3][0] = 0;
                        }
                        else if(puzzle2[3][1] == 0){
                            img013.setImageResource(imgArray[0]);
                            img014.setImageResource(imgArray[puzzle2[3][0]]);
                            puzzle2[3][1] = puzzle2[3][0];
                            puzzle2[3][0] = 0;
                        }
                        checkFin2();
                    }
                });
                img014.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if(puzzle2[2][1] == 0){
                            img014.setImageResource(imgArray[0]);
                            img010.setImageResource(imgArray[puzzle2[3][1]]);
                            puzzle2[2][1] = puzzle2[3][1];
                            puzzle2[3][1] = 0;
                        }
                        else if(puzzle2[3][0] == 0){
                            img014.setImageResource(imgArray[0]);
                            img013.setImageResource(imgArray[puzzle2[3][1]]);
                            puzzle2[3][0] = puzzle2[3][1];
                            puzzle2[3][1] = 0;
                        }
                        else if(puzzle2[3][2] == 0){
                            img014.setImageResource(imgArray[0]);
                            img015.setImageResource(imgArray[puzzle2[3][1]]);
                            puzzle2[3][2] = puzzle2[3][1];
                            puzzle2[3][1] = 0;
                        }
                        checkFin2();
                    }
                });
                img015.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if(puzzle2[2][2] == 0){
                            img015.setImageResource(imgArray[0]);
                            img011.setImageResource(imgArray[puzzle2[3][2]]);
                            puzzle2[2][2] = puzzle2[3][2];
                            puzzle2[3][2] = 0;
                        }
                        else if(puzzle2[3][1] == 0){
                            img015.setImageResource(imgArray[0]);
                            img014.setImageResource(imgArray[puzzle2[3][2]]);
                            puzzle2[3][1] = puzzle2[3][2];
                            puzzle2[3][2] = 0;
                        }
                        else if(puzzle2[3][3] == 0){
                            img015.setImageResource(imgArray[0]);
                            img016.setImageResource(imgArray[puzzle2[3][2]]);
                            puzzle2[3][3] = puzzle2[3][2];
                            puzzle2[3][2] = 0;
                        }
                        checkFin2();
                    }
                });
                img016.setOnClickListener(new ImageView.OnClickListener(){
                    public void onClick(View v) {
                        if(puzzle2[2][3] == 0){
                            img016.setImageResource(imgArray[0]);
                            img012.setImageResource(imgArray[puzzle2[3][3]]);
                            puzzle2[2][3] = puzzle2[3][3];
                            puzzle2[3][3] = 0;
                        }
                        else if(puzzle2[3][2] == 0){
                            img016.setImageResource(imgArray[0]);
                            img015.setImageResource(imgArray[puzzle2[3][3]]);
                            puzzle2[3][2] = puzzle2[3][3];
                            puzzle2[3][3] = 0;
                        }
                        checkFin2();
                    }
                });
            }
        });

    }
}