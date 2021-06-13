package edu.skku.map.bitmap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 0;
    private ImageView img;
    Bitmap Image[][] = new Bitmap[20][20];
    Bitmap resize;
    int piecewidth;
    int pieceheight;

    private Button gbtn, sbtn;

    int[][] bwarr = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 , 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 , 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 , 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 , 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 , 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 , 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 , 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 , 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 , 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 , 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 , 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 , 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 , 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 , 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 , 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 , 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 , 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 , 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 , 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ,0 , 0, 0},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gbtn = findViewById(R.id.gallery);
        sbtn = findViewById(R.id.search);

        gbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        img = findViewById(R.id.image);
        img.getHeight();
        int imgWidth = img.getWidth(); //getResources().getDisplayMetrics().widthPixels;
        int imgHeight = img.getHeight(); // getResources().getDisplayMetrics().heightPixels;

        piecewidth = imgWidth / 20;
        pieceheight = imgHeight / 20;

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap select = BitmapFactory.decodeStream(in);
                    in.close();

                    resize = Bitmap.createScaledBitmap(select, imgWidth, imgWidth, false);

                    for(int i = 0; i < 20; i++) {
                        for (int j = 0; j < 20; j++) {
                            Image[i][j] = Bitmap.createBitmap(resize, j * piecewidth, i * pieceheight, piecewidth, pieceheight);
                            Image[i][j] = grayScale(Image[i][j], i, j);
                        }
                    }


                    img.setImageBitmap(resize);
                    Canvas canvas = new Canvas(resize);
                    Paint paint = new Paint();
                    EmbossMaskFilter emboss = new EmbossMaskFilter(new float[]{1, 1, 1}, 0.5f, 1, 1);
                    paint.setMaskFilter(emboss);

                    canvas.drawBitmap(resize, 0, 0, null);
                    for(int i = 0; i < 20; i++) {
                        for (int j = 0; j < 20; j++) {
                            canvas.drawBitmap(Image[i][j], j * piecewidth, i * pieceheight, paint);
                        }
                    }


                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "cancel", Toast.LENGTH_LONG).show();
            }
        }
    }
    public void onDraw(Canvas canvas){
        Paint paint = new Paint();
        EmbossMaskFilter emboss = new EmbossMaskFilter(new float[]{1, 1, 1}, 0.5f, 1, 1);
        paint.setMaskFilter(emboss);

        canvas.drawBitmap(resize, 0, 0, null);
        for(int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                canvas.drawBitmap(Image[i][j], j * piecewidth, i * pieceheight, paint);
            }
        }
    }

    protected Bitmap grayScale(final Bitmap orgBitmap, int i, int j){
        int width, height;
        width = orgBitmap.getWidth();
        height = orgBitmap.getHeight();

        Bitmap bmpGrayScale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);

        // color information
        int A, R, G, B;
        int pixel;

        // scan through all pixels
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                // get pixel color
                pixel = orgBitmap.getPixel(x, y);
                A = Color.alpha(pixel);
                R = Color.red(pixel);
                G = Color.green(pixel);
                B = Color.blue(pixel);
                int gray = (int) (0.2989 * R + 0.5870 * G + 0.1140 * B);

                // use 128 as threshold, above -> white, below -> black
                if (gray > 128) {
                    bwarr[i][j] = 1;
                }
                else
                    bwarr[i][j] = 0;
                // set new pixel color to output bitmap
                bmpGrayScale.setPixel(x, y, Color.argb(A, bwarr[i][j]*255, bwarr[i][j]*255, bwarr[i][j]*255));
            }
        }
        return bmpGrayScale;
    }
}