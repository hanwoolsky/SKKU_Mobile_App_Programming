package edu.skku.map.pa2_nemologic;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 0;
    private ImageView imageView;

    private Button gbtn;
    private CheckBox image00, image01, image02, image03, image04, image05, image06, image07, image08, image09, image010, image011, image012, image013, image014, image015, image016, image017, image018, image019,
        image10, image11, image12, image13, image14, image15, image16, image17, image18, image19, image110, image111, image112, image113, image114, image115, image116, image117, image118, image119,
        image20, image21, image22, image23, image24, image25, image26, image27, image28, image29, image210, image211, image212, image213, image214, image215, image216, image217, image218, image219,
        image30, image31, image32, image33, image34, image35, image36, image37, image38, image39, image310, image311, image312, image313, image314, image315, image316, image317, image318, image319,
        image40, image41, image42, image43, image44, image45, image46, image47, image48, image49, image410, image411, image412, image413, image414, image415, image416, image417, image418, image419,
        image50, image51, image52, image53, image54, image55, image56, image57, image58, image59, image510, image511, image512, image513, image514, image515, image516, image517, image518, image519,
        image60, image61, image62, image63, image64, image65, image66, image67, image68, image69, image610, image611, image612, image613, image614, image615, image616, image617, image618, image619,
        image70, image71, image72, image73, image74, image75, image76, image77, image78, image79, image710, image711, image712, image713, image714, image715, image716, image717, image718, image719,
        image80, image81, image82, image83, image84, image85, image86, image87, image88, image89, image810, image811, image812, image813, image814, image815, image816, image817, image818, image819,
        image90, image91, image92, image93, image94, image95, image96, image97, image98, image99, image910, image911, image912, image913, image914, image915, image916, image917, image918, image919,
        image100, image101, image102, image103, image104, image105, image106, image107, image108, image109, image1010, image1011, image1012, image1013, image1014, image1015, image1016, image1017, image1018, image1019,
        image1100, image1101, image1102, image1103, image1104, image1105, image1106, image1107, image1108, image1109, image1110, image1111, image1112, image1113, image1114, image1115, image1116, image1117, image1118, image1119,
        image120, image121, image122, image123, image124, image125, image126, image127, image128, image129, image1210, image1211, image1212, image1213, image1214, image1215, image1216, image1217, image1218, image1219,
        image130, image131, image132, image133, image134, image135, image136, image137, image138, image139, image1310, image1311, image1312, image1313, image1314, image1315, image1316, image1317, image1318, image1319,
        image140, image141, image142, image143, image144, image145, image146, image147, image148, image149, image1410, image1411, image1412, image1413, image1414, image1415, image1416, image1417, image1418, image1419,
        image150, image151, image152, image153, image154, image155, image156, image157, image158, image159, image1510, image1511, image1512, image1513, image1514, image1515, image1516, image1517, image1518, image1519,
        image160, image161, image162, image163, image164, image165, image166, image167, image168, image169, image1610, image1611, image1612, image1613, image1614, image1615, image1616, image1617, image1618, image1619,
        image170, image171, image172, image173, image174, image175, image176, image177, image178, image179, image1710, image1711, image1712, image1713, image1714, image1715, image1716, image1717, image1718, image1719,
        image180, image181, image182, image183, image184, image185, image186, image187, image188, image189, image1810, image1811, image1812, image1813, image1814, image1815, image1816, image1817, image1818, image1819,
        image190, image191, image192, image193, image194, image195, image196, image197, image198, image199, image1910, image1911, image1912, image1913, image1914, image1915, image1916, image1917, image1918, image1919;

    private CheckBox Checkarr[][] = {
            {image00, image01, image02, image03, image04, image05, image06, image07, image08, image09, image010, image011, image012, image013, image014, image015, image016, image017, image018, image019},
            {image10, image11, image12, image13, image14, image15, image16, image17, image18, image19, image110, image111, image112, image113, image114, image115, image116, image117, image118, image119},
            {image20, image21, image22, image23, image24, image25, image26, image27, image28, image29, image210, image211, image212, image213, image214, image215, image216, image217, image218, image219},
            {image30, image31, image32, image33, image34, image35, image36, image37, image38, image39, image310, image311, image312, image313, image314, image315, image316, image317, image318, image319},
            {image40, image41, image42, image43, image44, image45, image46, image47, image48, image49, image410, image411, image412, image413, image414, image415, image416, image417, image418, image419},
            {image50, image51, image52, image53, image54, image55, image56, image57, image58, image59, image510, image511, image512, image513, image514, image515, image516, image517, image518, image519},
            {image60, image61, image62, image63, image64, image65, image66, image67, image68, image69, image610, image611, image612, image613, image614, image615, image616, image617, image618, image619},
            {image70, image71, image72, image73, image74, image75, image76, image77, image78, image79, image710, image711, image712, image713, image714, image715, image716, image717, image718, image719},
            {image80, image81, image82, image83, image84, image85, image86, image87, image88, image89, image810, image811, image812, image813, image814, image815, image816, image817, image818, image819},
            {image90, image91, image92, image93, image94, image95, image96, image97, image98, image99, image910, image911, image912, image913, image914, image915, image916, image917, image918, image919},
            {image100, image101, image102, image103, image104, image105, image106, image107, image108, image109, image1010, image1011, image1012, image1013, image1014, image1015, image1016, image1017, image1018, image1019},
            {image1100, image1101, image1102, image1103, image1104, image1105, image1106, image1107, image1108, image1109, image1110, image1111, image1112, image1113, image1114, image1115, image1116, image1117, image1118, image1119},
            {image120, image121, image122, image123, image124, image125, image126, image127, image128, image129, image1210, image1211, image1212, image1213, image1214, image1215, image1216, image1217, image1218, image1219},
            {image130, image131, image132, image133, image134, image135, image136, image137, image138, image139, image1310, image1311, image1312, image1313, image1314, image1315, image1316, image1317, image1318, image1319},
            {image140, image141, image142, image143, image144, image145, image146, image147, image148, image149, image1410, image1411, image1412, image1413, image1414, image1415, image1416, image1417, image1418, image1419},
            {image150, image151, image152, image153, image154, image155, image156, image157, image158, image159, image1510, image1511, image1512, image1513, image1514, image1515, image1516, image1517, image1518, image1519},
            {image160, image161, image162, image163, image164, image165, image166, image167, image168, image169, image1610, image1611, image1612, image1613, image1614, image1615, image1616, image1617, image1618, image1619},
            {image170, image171, image172, image173, image174, image175, image176, image177, image178, image179, image1710, image1711, image1712, image1713, image1714, image1715, image1716, image1717, image1718, image1719},
            {image180, image181, image182, image183, image184, image185, image186, image187, image188, image189, image1810, image1811, image1812, image1813, image1814, image1815, image1816, image1817, image1818, image1819},
            {image190, image191, image192, image193, image194, image195, image196, image197, image198, image199, image1910, image1911, image1912, image1913, image1914, image1915, image1916, image1917, image1918, image1919}
    };
    private TextView row1, row2, row3, row4, row5, row6, row7, row8, row9, row10, row11, row12, row13, row14, row15, row16, row17, row18, row19, row20;
    private TextView col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20;

    Bitmap Image[][] = new Bitmap[20][20];
    Bitmap resize;
    int piecewidth;
    int pieceheight;

    int[][] bwarr = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };

    int[][] checkBox = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };

    @SuppressLint("IntentReset")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                checkBox[i][j] = 0;
                bwarr[i][j] = 0;
            }
        }
        setContentView(R.layout.activity_main);
        gbtn = findViewById(R.id.gallery);
        image00 = findViewById(R.id.image00);
        image01 = findViewById(R.id.image01);
        image02 = findViewById(R.id.image02);
        image03 = findViewById(R.id.image03);
        image04 = findViewById(R.id.image04);
        image05 = findViewById(R.id.image05);
        image06 = findViewById(R.id.image06);
        image07 = findViewById(R.id.image07);
        image08 = findViewById(R.id.image08);
        image09 = findViewById(R.id.image09);
        image010 = findViewById(R.id.image010);
        image011 = findViewById(R.id.image011);
        image012 = findViewById(R.id.image012);
        image013 = findViewById(R.id.image013);
        image014 = findViewById(R.id.image014);
        image015 = findViewById(R.id.image015);
        image016 = findViewById(R.id.image016);
        image017 = findViewById(R.id.image017);
        image018 = findViewById(R.id.image018);
        image019 = findViewById(R.id.image019); // 0
        image10 = findViewById(R.id.image10);
        image11 = findViewById(R.id.image11);
        image12 = findViewById(R.id.image12);
        image13 = findViewById(R.id.image13);
        image14 = findViewById(R.id.image14);
        image15 = findViewById(R.id.image15);
        image16 = findViewById(R.id.image16);
        image17 = findViewById(R.id.image17);
        image18 = findViewById(R.id.image18);
        image19 = findViewById(R.id.image19);
        image110 = findViewById(R.id.image110);
        image111 = findViewById(R.id.image111);
        image112 = findViewById(R.id.image112);
        image113 = findViewById(R.id.image113);
        image114 = findViewById(R.id.image114);
        image115 = findViewById(R.id.image115);
        image116 = findViewById(R.id.image116);
        image117 = findViewById(R.id.image117);
        image118 = findViewById(R.id.image118);
        image119 = findViewById(R.id.image119); // 1
        image20 = findViewById(R.id.image20);
        image21 = findViewById(R.id.image21);
        image22 = findViewById(R.id.image22);
        image23 = findViewById(R.id.image23);
        image24 = findViewById(R.id.image24);
        image25 = findViewById(R.id.image25);
        image26 = findViewById(R.id.image26);
        image27 = findViewById(R.id.image27);
        image28 = findViewById(R.id.image28);
        image29 = findViewById(R.id.image29);
        image210 = findViewById(R.id.image210);
        image211 = findViewById(R.id.image211);
        image212 = findViewById(R.id.image212);
        image213 = findViewById(R.id.image213);
        image214 = findViewById(R.id.image214);
        image215 = findViewById(R.id.image215);
        image216 = findViewById(R.id.image216);
        image217 = findViewById(R.id.image217);
        image218 = findViewById(R.id.image218);
        image219 = findViewById(R.id.image219); // 2
        image30 = findViewById(R.id.image30);
        image31 = findViewById(R.id.image31);
        image32 = findViewById(R.id.image32);
        image33 = findViewById(R.id.image33);
        image34 = findViewById(R.id.image34);
        image35 = findViewById(R.id.image35);
        image36 = findViewById(R.id.image36);
        image37 = findViewById(R.id.image37);
        image38 = findViewById(R.id.image38);
        image39 = findViewById(R.id.image39);
        image310 = findViewById(R.id.image310);
        image311 = findViewById(R.id.image311);
        image312 = findViewById(R.id.image312);
        image313 = findViewById(R.id.image313);
        image314 = findViewById(R.id.image314);
        image315 = findViewById(R.id.image315);
        image316 = findViewById(R.id.image316);
        image317 = findViewById(R.id.image317);
        image318 = findViewById(R.id.image318);
        image319 = findViewById(R.id.image319); // 3
        image40 = findViewById(R.id.image40);
        image41 = findViewById(R.id.image41);
        image42 = findViewById(R.id.image42);
        image43 = findViewById(R.id.image43);
        image44 = findViewById(R.id.image44);
        image45 = findViewById(R.id.image45);
        image46 = findViewById(R.id.image46);
        image47 = findViewById(R.id.image47);
        image48 = findViewById(R.id.image48);
        image49 = findViewById(R.id.image49);
        image410 = findViewById(R.id.image410);
        image411 = findViewById(R.id.image411);
        image412 = findViewById(R.id.image412);
        image413 = findViewById(R.id.image413);
        image414 = findViewById(R.id.image414);
        image415 = findViewById(R.id.image415);
        image416 = findViewById(R.id.image416);
        image417 = findViewById(R.id.image417);
        image418 = findViewById(R.id.image418);
        image419 = findViewById(R.id.image419); // 4
        image50 = findViewById(R.id.image50);
        image51 = findViewById(R.id.image51);
        image52 = findViewById(R.id.image52);
        image53 = findViewById(R.id.image53);
        image54 = findViewById(R.id.image54);
        image55 = findViewById(R.id.image55);
        image56 = findViewById(R.id.image56);
        image57 = findViewById(R.id.image57);
        image58 = findViewById(R.id.image58);
        image59 = findViewById(R.id.image59);
        image510 = findViewById(R.id.image510);
        image511 = findViewById(R.id.image511);
        image512 = findViewById(R.id.image512);
        image513 = findViewById(R.id.image513);
        image514 = findViewById(R.id.image514);
        image515 = findViewById(R.id.image515);
        image516 = findViewById(R.id.image516);
        image517 = findViewById(R.id.image517);
        image518 = findViewById(R.id.image518);
        image519 = findViewById(R.id.image519); // 5
        image60 = findViewById(R.id.image60);
        image61 = findViewById(R.id.image61);
        image62 = findViewById(R.id.image62);
        image63 = findViewById(R.id.image63);
        image64 = findViewById(R.id.image64);
        image65 = findViewById(R.id.image65);
        image66 = findViewById(R.id.image66);
        image67 = findViewById(R.id.image67);
        image68 = findViewById(R.id.image68);
        image69 = findViewById(R.id.image69);
        image610 = findViewById(R.id.image610);
        image611 = findViewById(R.id.image611);
        image612 = findViewById(R.id.image612);
        image613 = findViewById(R.id.image613);
        image614 = findViewById(R.id.image614);
        image615 = findViewById(R.id.image615);
        image616 = findViewById(R.id.image616);
        image617 = findViewById(R.id.image617);
        image618 = findViewById(R.id.image618);
        image619 = findViewById(R.id.image619); // 6
        image70 = findViewById(R.id.image70);
        image71 = findViewById(R.id.image71);
        image72 = findViewById(R.id.image72);
        image73 = findViewById(R.id.image73);
        image74 = findViewById(R.id.image74);
        image75 = findViewById(R.id.image75);
        image76 = findViewById(R.id.image76);
        image77 = findViewById(R.id.image77);
        image78 = findViewById(R.id.image78);
        image79 = findViewById(R.id.image79);
        image710 = findViewById(R.id.image710);
        image711 = findViewById(R.id.image711);
        image712 = findViewById(R.id.image712);
        image713 = findViewById(R.id.image713);
        image714 = findViewById(R.id.image714);
        image715 = findViewById(R.id.image715);
        image716 = findViewById(R.id.image716);
        image717 = findViewById(R.id.image717);
        image718 = findViewById(R.id.image718);
        image719 = findViewById(R.id.image719); // 7
        image80 = findViewById(R.id.image80);
        image81 = findViewById(R.id.image81);
        image82 = findViewById(R.id.image82);
        image83 = findViewById(R.id.image83);
        image84 = findViewById(R.id.image84);
        image85 = findViewById(R.id.image85);
        image86 = findViewById(R.id.image86);
        image87 = findViewById(R.id.image87);
        image88 = findViewById(R.id.image88);
        image89 = findViewById(R.id.image89);
        image810 = findViewById(R.id.image810);
        image811 = findViewById(R.id.image811);
        image812 = findViewById(R.id.image812);
        image813 = findViewById(R.id.image813);
        image814 = findViewById(R.id.image814);
        image815 = findViewById(R.id.image815);
        image816 = findViewById(R.id.image816);
        image817 = findViewById(R.id.image817);
        image818 = findViewById(R.id.image818);
        image819 = findViewById(R.id.image819); // 8
        image90 = findViewById(R.id.image90);
        image91 = findViewById(R.id.image91);
        image92 = findViewById(R.id.image92);
        image93 = findViewById(R.id.image93);
        image94 = findViewById(R.id.image94);
        image95 = findViewById(R.id.image95);
        image96 = findViewById(R.id.image96);
        image97 = findViewById(R.id.image97);
        image98 = findViewById(R.id.image98);
        image99 = findViewById(R.id.image99);
        image910 = findViewById(R.id.image910);
        image911 = findViewById(R.id.image911);
        image912 = findViewById(R.id.image912);
        image913 = findViewById(R.id.image913);
        image914 = findViewById(R.id.image914);
        image915 = findViewById(R.id.image915);
        image916 = findViewById(R.id.image916);
        image917 = findViewById(R.id.image917);
        image918 = findViewById(R.id.image918);
        image919 = findViewById(R.id.image919); // 9
        image100 = findViewById(R.id.image100);
        image101 = findViewById(R.id.image101);
        image102 = findViewById(R.id.image102);
        image103 = findViewById(R.id.image103);
        image104 = findViewById(R.id.image104);
        image105 = findViewById(R.id.image105);
        image106 = findViewById(R.id.image106);
        image107 = findViewById(R.id.image107);
        image108 = findViewById(R.id.image108);
        image109 = findViewById(R.id.image109);
        image1010 = findViewById(R.id.image1010);
        image1011 = findViewById(R.id.image1011);
        image1012 = findViewById(R.id.image1012);
        image1013 = findViewById(R.id.image1013);
        image1014 = findViewById(R.id.image1014);
        image1015 = findViewById(R.id.image1015);
        image1016 = findViewById(R.id.image1016);
        image1017 = findViewById(R.id.image1017);
        image1018 = findViewById(R.id.image1018);
        image1019 = findViewById(R.id.image1019); // 10
        image1100 = findViewById(R.id.image1100);
        image1101 = findViewById(R.id.image1101);
        image1102 = findViewById(R.id.image1102);
        image1103 = findViewById(R.id.image1103);
        image1104 = findViewById(R.id.image1104);
        image1105 = findViewById(R.id.image1105);
        image1106 = findViewById(R.id.image1106);
        image1107 = findViewById(R.id.image1107);
        image1108 = findViewById(R.id.image1108);
        image1109 = findViewById(R.id.image1109);
        image1110 = findViewById(R.id.image1110);
        image1111 = findViewById(R.id.image1111);
        image1112 = findViewById(R.id.image1112);
        image1113 = findViewById(R.id.image1113);
        image1114 = findViewById(R.id.image1114);
        image1115 = findViewById(R.id.image1115);
        image1116 = findViewById(R.id.image1116);
        image1117 = findViewById(R.id.image1117);
        image1118 = findViewById(R.id.image1118);
        image1119 = findViewById(R.id.image1119); // 11
        image120 = findViewById(R.id.image120);
        image121 = findViewById(R.id.image121);
        image122 = findViewById(R.id.image122);
        image123 = findViewById(R.id.image123);
        image124 = findViewById(R.id.image124);
        image125 = findViewById(R.id.image125);
        image126 = findViewById(R.id.image126);
        image127 = findViewById(R.id.image127);
        image128 = findViewById(R.id.image128);
        image129 = findViewById(R.id.image129);
        image1210 = findViewById(R.id.image1210);
        image1211 = findViewById(R.id.image1211);
        image1212 = findViewById(R.id.image1212);
        image1213 = findViewById(R.id.image1213);
        image1214 = findViewById(R.id.image1214);
        image1215 = findViewById(R.id.image1215);
        image1216 = findViewById(R.id.image1216);
        image1217 = findViewById(R.id.image1217);
        image1218 = findViewById(R.id.image1218);
        image1219 = findViewById(R.id.image1219); // 12
        image130 = findViewById(R.id.image130);
        image131 = findViewById(R.id.image131);
        image132 = findViewById(R.id.image132);
        image133 = findViewById(R.id.image133);
        image134 = findViewById(R.id.image134);
        image135 = findViewById(R.id.image135);
        image136 = findViewById(R.id.image136);
        image137 = findViewById(R.id.image137);
        image138 = findViewById(R.id.image138);
        image139 = findViewById(R.id.image139);
        image1310 = findViewById(R.id.image1310);
        image1311 = findViewById(R.id.image1311);
        image1312 = findViewById(R.id.image1312);
        image1313 = findViewById(R.id.image1313);
        image1314 = findViewById(R.id.image1314);
        image1315 = findViewById(R.id.image1315);
        image1316 = findViewById(R.id.image1316);
        image1317 = findViewById(R.id.image1317);
        image1318 = findViewById(R.id.image1318);
        image1319 = findViewById(R.id.image1319); // 13
        image140 = findViewById(R.id.image140);
        image141 = findViewById(R.id.image141);
        image142 = findViewById(R.id.image142);
        image143 = findViewById(R.id.image143);
        image144 = findViewById(R.id.image144);
        image145 = findViewById(R.id.image145);
        image146 = findViewById(R.id.image146);
        image147 = findViewById(R.id.image147);
        image148 = findViewById(R.id.image148);
        image149 = findViewById(R.id.image149);
        image1410 = findViewById(R.id.image1410);
        image1411 = findViewById(R.id.image1411);
        image1412 = findViewById(R.id.image1412);
        image1413 = findViewById(R.id.image1413);
        image1414 = findViewById(R.id.image1414);
        image1415 = findViewById(R.id.image1415);
        image1416 = findViewById(R.id.image1416);
        image1417 = findViewById(R.id.image1417);
        image1418 = findViewById(R.id.image1418);
        image1419 = findViewById(R.id.image1419); // 14
        image150 = findViewById(R.id.image150);
        image151 = findViewById(R.id.image151);
        image152 = findViewById(R.id.image152);
        image153 = findViewById(R.id.image153);
        image154 = findViewById(R.id.image154);
        image155 = findViewById(R.id.image155);
        image156 = findViewById(R.id.image156);
        image157 = findViewById(R.id.image157);
        image158 = findViewById(R.id.image158);
        image159 = findViewById(R.id.image159);
        image1510 = findViewById(R.id.image1510);
        image1511 = findViewById(R.id.image1511);
        image1512 = findViewById(R.id.image1512);
        image1513 = findViewById(R.id.image1513);
        image1514 = findViewById(R.id.image1514);
        image1515 = findViewById(R.id.image1515);
        image1516 = findViewById(R.id.image1516);
        image1517 = findViewById(R.id.image1517);
        image1518 = findViewById(R.id.image1518);
        image1519 = findViewById(R.id.image1519); // 15
        image160 = findViewById(R.id.image160);
        image161 = findViewById(R.id.image161);
        image162 = findViewById(R.id.image162);
        image163 = findViewById(R.id.image163);
        image164 = findViewById(R.id.image164);
        image165 = findViewById(R.id.image165);
        image166 = findViewById(R.id.image166);
        image167 = findViewById(R.id.image167);
        image168 = findViewById(R.id.image168);
        image169 = findViewById(R.id.image169);
        image1610 = findViewById(R.id.image1610);
        image1611 = findViewById(R.id.image1611);
        image1612 = findViewById(R.id.image1612);
        image1613 = findViewById(R.id.image1613);
        image1614 = findViewById(R.id.image1614);
        image1615 = findViewById(R.id.image1615);
        image1616 = findViewById(R.id.image1616);
        image1617 = findViewById(R.id.image1617);
        image1618 = findViewById(R.id.image1618);
        image1619 = findViewById(R.id.image1619); // 16
        image170 = findViewById(R.id.image170);
        image171 = findViewById(R.id.image171);
        image172 = findViewById(R.id.image172);
        image173 = findViewById(R.id.image173);
        image174 = findViewById(R.id.image174);
        image175 = findViewById(R.id.image175);
        image176 = findViewById(R.id.image176);
        image177 = findViewById(R.id.image177);
        image178 = findViewById(R.id.image178);
        image179 = findViewById(R.id.image179);
        image1710 = findViewById(R.id.image1710);
        image1711 = findViewById(R.id.image1711);
        image1712 = findViewById(R.id.image1712);
        image1713 = findViewById(R.id.image1713);
        image1714 = findViewById(R.id.image1714);
        image1715 = findViewById(R.id.image1715);
        image1716 = findViewById(R.id.image1716);
        image1717 = findViewById(R.id.image1717);
        image1718 = findViewById(R.id.image1718);
        image1719 = findViewById(R.id.image1719); // 17
        image180 = findViewById(R.id.image180);
        image181 = findViewById(R.id.image181);
        image182 = findViewById(R.id.image182);
        image183 = findViewById(R.id.image183);
        image184 = findViewById(R.id.image184);
        image185 = findViewById(R.id.image185);
        image186 = findViewById(R.id.image186);
        image187 = findViewById(R.id.image187);
        image188 = findViewById(R.id.image188);
        image189 = findViewById(R.id.image189);
        image1810 = findViewById(R.id.image1810);
        image1811 = findViewById(R.id.image1811);
        image1812 = findViewById(R.id.image1812);
        image1813 = findViewById(R.id.image1813);
        image1814 = findViewById(R.id.image1814);
        image1815 = findViewById(R.id.image1815);
        image1816 = findViewById(R.id.image1816);
        image1817 = findViewById(R.id.image1817);
        image1818 = findViewById(R.id.image1818);
        image1819 = findViewById(R.id.image1819); // 18
        image190 = findViewById(R.id.image190);
        image191 = findViewById(R.id.image191);
        image192 = findViewById(R.id.image192);
        image193 = findViewById(R.id.image193);
        image194 = findViewById(R.id.image194);
        image195 = findViewById(R.id.image195);
        image196 = findViewById(R.id.image196);
        image197 = findViewById(R.id.image197);
        image198 = findViewById(R.id.image198);
        image199 = findViewById(R.id.image199);
        image1910 = findViewById(R.id.image1910);
        image1911 = findViewById(R.id.image1911);
        image1912 = findViewById(R.id.image1912);
        image1913 = findViewById(R.id.image1913);
        image1914 = findViewById(R.id.image1914);
        image1915 = findViewById(R.id.image1915);
        image1916 = findViewById(R.id.image1916);
        image1917 = findViewById(R.id.image1917);
        image1918 = findViewById(R.id.image1918);
        image1919 = findViewById(R.id.image1919); // 19

        gbtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_CODE);
        });

        image00.setOnClickListener(v -> {
            if(image00.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check(0, 0);
        });

        image01.setOnClickListener(v -> {
            if(image01.isChecked()) checkBox[0][1] = 1;
            else checkBox[0][1] = 0;
            check(0, 1);
        });

        image02.setOnClickListener(v -> {
            if(image02.isChecked()) checkBox[0][2] = 1;
            else checkBox[0][2] = 0;
            check(0, 2);
        });

        image03.setOnClickListener(v -> {
            if(image03.isChecked()) checkBox[0][3] = 1;
            else checkBox[0][3] = 0;
            check(0, 3);
        });

        image04.setOnClickListener(v -> {
            if(image04.isChecked()) checkBox[0][4] = 1;
            else checkBox[0][4] = 0;
            check(0, 4);
        });

        image05.setOnClickListener(v -> {
            if(image05.isChecked()) checkBox[0][5] = 1;
            else checkBox[0][5] = 0;
            check(0, 5);
        });

        image06.setOnClickListener(v -> {
            if(image06.isChecked()) checkBox[0][6] = 1;
            else checkBox[0][6] = 0;
            check(0, 6);
        });

        image07.setOnClickListener(v -> {
            if(image07.isChecked()) checkBox[0][7] = 1;
            else checkBox[0][7] = 0;
            check(0, 7);
        });

        image08.setOnClickListener(v -> {
            if(image08.isChecked()) checkBox[0][8] = 1;
            else checkBox[0][8] = 0;
            check(0, 8);
        });

        image09.setOnClickListener(v -> {
            if(image09.isChecked()) checkBox[0][9] = 1;
            else checkBox[0][9] = 0;
            check(0, 9);
        });
        image010.setOnClickListener(v -> {
            if(image010.isChecked()) checkBox[0][10] = 1;
            else checkBox[0][10] = 0;
            check(0, 10);
        });

        image011.setOnClickListener(v -> {
            if(image011.isChecked()) checkBox[0][11] = 1;
            else checkBox[0][11] = 0;
            check(0, 11);
        });

        image012.setOnClickListener(v -> {
            if(image012.isChecked()) checkBox[0][12] = 1;
            else checkBox[0][12] = 0;
            check(0, 12);
        });

        image013.setOnClickListener(v -> {
            if(image013.isChecked()) checkBox[0][13] = 1;
            else checkBox[0][13] = 0;
            check(0, 13);
        });

        image014.setOnClickListener(v -> {
            if(image014.isChecked()) checkBox[0][14] = 1;
            else checkBox[0][14] = 0;
            check(0, 14);
        });

        image015.setOnClickListener(v -> {
            if(image015.isChecked()) checkBox[0][15] = 1;
            else checkBox[0][15] = 0;
            check(0, 15);
        });

        image016.setOnClickListener(v -> {
            if(image016.isChecked()) checkBox[0][16] = 1;
            else checkBox[0][16] = 0;
            check(0, 16);
        });

        image017.setOnClickListener(v -> {
            if(image017.isChecked()) checkBox[0][17] = 1;
            else checkBox[0][17] = 0;
            check(0, 17);
        });

        image018.setOnClickListener(v -> {
            if (image018.isChecked()) checkBox[0][18] = 1;
            else checkBox[0][18] = 0;
            check(0, 18);
        });
        
        image019.setOnClickListener(v -> {
            if(image019.isChecked()) checkBox[0][19] = 1;
            else checkBox[0][19] = 0;
            check(0, 19);
        }); // 0

        image10.setOnClickListener(v -> {
            if(image10.isChecked()) checkBox[1][0] = 1;
            else checkBox[1][0] = 0;
            check(1, 0);
        });

        image11.setOnClickListener(v -> {
            if(image11.isChecked()) checkBox[1][1] = 1;
            else checkBox[1][1] = 0;
            check(1, 1);
        });

        image12.setOnClickListener(v -> {
            if(image12.isChecked()) checkBox[1][2] = 1;
            else checkBox[1][2] = 0;
            check(1, 2);
        });

        image13.setOnClickListener(v -> {
            if(image13.isChecked()) checkBox[1][3] = 1;
            else checkBox[1][3] = 0;
            check(1, 3);
        });

        image14.setOnClickListener(v -> {
            if(image14.isChecked()) checkBox[1][4] = 1;
            else checkBox[1][4] = 0;
            check(1, 4);
        });

        image15.setOnClickListener(v -> {
            if(image15.isChecked()) checkBox[1][5] = 1;
            else checkBox[1][5] = 0;
            check(1, 5);
        });

        image16.setOnClickListener(v -> {
            if(image16.isChecked()) checkBox[1][6] = 1;
            else checkBox[1][6] = 0;
            check(1, 6);
        });

        image17.setOnClickListener(v -> {
            if(image17.isChecked()) checkBox[1][7] = 1;
            else checkBox[1][7] = 0;
            check(1, 7);
        });

        image18.setOnClickListener(v -> {
            if(image18.isChecked()) checkBox[1][8] = 1;
            else checkBox[1][8] = 0;
            check(1, 8);
        });

        image19.setOnClickListener(v -> {
            if(image19.isChecked()) checkBox[1][9] = 1;
            else checkBox[1][9] = 0;
            check(1, 9);
        });
        image110.setOnClickListener(v -> {
            if(image110.isChecked()) checkBox[1][10] = 1;
            else checkBox[1][10] = 0;
            check(1, 10);
        });

        image111.setOnClickListener(v -> {
            if(image111.isChecked()) checkBox[1][11] = 1;
            else checkBox[1][11] = 0;
            check(1, 11);
        });

        image112.setOnClickListener(v -> {
            if(image112.isChecked()) checkBox[1][12] = 1;
            else checkBox[1][12] = 0;
            check(1, 12);
        });

        image113.setOnClickListener(v -> {
            if(image113.isChecked()) checkBox[1][13] = 1;
            else checkBox[1][13] = 0;
            check(1, 13);
        });

        image114.setOnClickListener(v -> {
            if(image114.isChecked()) checkBox[1][14] = 1;
            else checkBox[1][14] = 0;
            check(1, 14);
        });

        image115.setOnClickListener(v -> {
            if(image115.isChecked()) checkBox[1][15] = 1;
            else checkBox[1][15] = 0;
            check(1, 15);
        });

        image116.setOnClickListener(v -> {
            if(image116.isChecked()) checkBox[1][16] = 1;
            else checkBox[1][16] = 0;
            check(1, 16);
        });

        image117.setOnClickListener(v -> {
            if(image117.isChecked()) checkBox[1][17] = 1;
            else checkBox[1][17] = 0;
            check(1, 17);
        });

        image118.setOnClickListener(v -> {
            if(image118.isChecked()) checkBox[1][18] = 1;
            else checkBox[1][18] = 0;
            check(1, 18);
        });

        image119.setOnClickListener(v -> {
            if(image119.isChecked()) checkBox[1][19] = 1;
            else checkBox[1][19] = 0;
            check(1, 19);
        }); // 1

        image20.setOnClickListener(v -> {
            if(image20.isChecked()) checkBox[2][0] = 1;
            else checkBox[2][0] = 0;
            check(2, 0);
        });

        image21.setOnClickListener(v -> {
            if(image21.isChecked()) checkBox[2][1] = 1;
            else checkBox[2][1] = 0;
            check(2, 1);
        });

        image22.setOnClickListener(v -> {
            if(image22.isChecked()) checkBox[2][2] = 1;
            else checkBox[2][2] = 0;
            check(2, 2);
        });

        image23.setOnClickListener(v -> {
            if(image23.isChecked()) checkBox[2][3] = 1;
            else checkBox[2][3] = 0;
            check(2, 3);
        });

        image24.setOnClickListener(v -> {
            if(image24.isChecked()) checkBox[2][4] = 1;
            else checkBox[2][4] = 0;
            check(2, 4);
        });

        image25.setOnClickListener(v -> {
            if(image25.isChecked()) checkBox[2][5] = 1;
            else checkBox[2][5] = 0;
            check(2, 5);
        });

        image26.setOnClickListener(v -> {
            if(image26.isChecked()) checkBox[2][6] = 1;
            else checkBox[2][6] = 0;
            check(2, 6);
        });

        image27.setOnClickListener(v -> {
            if(image27.isChecked()) checkBox[2][7] = 1;
            else checkBox[2][7] = 0;
            check(2, 7);
        });

        image28.setOnClickListener(v -> {
            if(image28.isChecked()) checkBox[2][8] = 1;
            else checkBox[2][8] = 0;
            check(2, 8);
        });

        image29.setOnClickListener(v -> {
            if(image29.isChecked()) checkBox[2][9] = 1;
            else checkBox[2][9] = 0;
            check(2, 9);
        });
        image210.setOnClickListener(v -> {
            if(image210.isChecked()) checkBox[2][10] = 1;
            else checkBox[2][10] = 0;
            check(2, 10);
        });

        image211.setOnClickListener(v -> {
            if(image211.isChecked()) checkBox[2][11] = 1;
            else checkBox[2][11] = 0;
            check(2, 11);
        });

        image212.setOnClickListener(v -> {
            if(image212.isChecked()) checkBox[2][12] = 1;
            else checkBox[2][12] = 0;
            check(2, 12);
        });

        image213.setOnClickListener(v -> {
            if(image213.isChecked()) checkBox[2][13] = 1;
            else checkBox[2][13] = 0;
            check(2, 13);
        });

        image214.setOnClickListener(v -> {
            if(image214.isChecked()) checkBox[2][14] = 1;
            else checkBox[2][14] = 0;
            check(2, 14);
        });

        image215.setOnClickListener(v -> {
            if(image215.isChecked()) checkBox[2][15] = 1;
            else checkBox[2][15] = 0;
            check(2, 15);
        });

        image216.setOnClickListener(v -> {
            if(image216.isChecked()) checkBox[2][16] = 1;
            else checkBox[2][16] = 0;
            check(2, 16);
        });

        image217.setOnClickListener(v -> {
            if(image217.isChecked()) checkBox[2][17] = 1;
            else checkBox[2][17] = 0;
            check(2, 17);
        });

        image218.setOnClickListener(v -> {
            if(image218.isChecked()) checkBox[2][18] = 1;
            else checkBox[2][18] = 0;
            check(2, 18);
        });

        image219.setOnClickListener(v -> {
            if(image219.isChecked()) checkBox[2][19] = 1;
            else checkBox[2][19] = 0;
            check(2, 19);
        }); // 2
        
        image30.setOnClickListener(v -> {
            if(image30.isChecked()) checkBox[3][0] = 1;
            else checkBox[3][0] = 0;
            check(3, 0);
        });

        image31.setOnClickListener(v -> {
            if(image31.isChecked()) checkBox[3][1] = 1;
            else checkBox[3][1] = 0;
            check(3, 1);
        });

        image32.setOnClickListener(v -> {
            if(image32.isChecked()) checkBox[3][2] = 1;
            else checkBox[3][2] = 0;
            check(3, 2);
        });

        image33.setOnClickListener(v -> {
            if(image33.isChecked()) checkBox[3][3] = 1;
            else checkBox[3][3] = 0;
            check(3, 3);
        });

        image34.setOnClickListener(v -> {
            if(image34.isChecked()) checkBox[3][4] = 1;
            else checkBox[3][4] = 0;
            check(3, 4);
        });

        image35.setOnClickListener(v -> {
            if(image35.isChecked()) checkBox[3][5] = 1;
            else checkBox[3][5] = 0;
            check(3, 5);
        });

        image36.setOnClickListener(v -> {
            if(image36.isChecked()) checkBox[3][6] = 1;
            else checkBox[3][6] = 0;
            check(3, 6);
        });

        image37.setOnClickListener(v -> {
            if(image37.isChecked()) checkBox[3][7] = 1;
            else checkBox[3][7] = 0;
            check(3, 7);
        });

        image38.setOnClickListener(v -> {
            if(image38.isChecked()) checkBox[3][8] = 1;
            else checkBox[3][8] = 0;
            check(3, 8);
        });

        image39.setOnClickListener(v -> {
            if(image39.isChecked()) checkBox[3][9] = 1;
            else checkBox[3][9] = 0;
            check(3, 9);
        });
        image310.setOnClickListener(v -> {
            if(image310.isChecked()) checkBox[3][10] = 1;
            else checkBox[3][10] = 0;
            check(3, 10);
        });

        image311.setOnClickListener(v -> {
            if(image311.isChecked()) checkBox[3][11] = 1;
            else checkBox[3][11] = 0;
            check(3, 11);
        });

        image312.setOnClickListener(v -> {
            if(image312.isChecked()) checkBox[3][12] = 1;
            else checkBox[3][12] = 0;
            check(3, 12);
        });

        image313.setOnClickListener(v -> {
            if(image313.isChecked()) checkBox[3][13] = 1;
            else checkBox[3][13] = 0;
            check(3, 13);
        });

        image314.setOnClickListener(v -> {
            if(image314.isChecked()) checkBox[3][14] = 1;
            else checkBox[3][14] = 0;
            check(3, 14);
        });

        image315.setOnClickListener(v -> {
            if(image315.isChecked()) checkBox[3][15] = 1;
            else checkBox[3][15] = 0;
            check(3, 15);
        });

        image316.setOnClickListener(v -> {
            if(image316.isChecked()) checkBox[3][16] = 1;
            else checkBox[3][16] = 0;
            check(3, 16);
        });

        image317.setOnClickListener(v -> {
            if(image317.isChecked()) checkBox[3][17] = 1;
            else checkBox[3][17] = 0;
            check(3, 17);
        });

        image318.setOnClickListener(v -> {
            if(image318.isChecked()) checkBox[3][18] = 1;
            else checkBox[3][18] = 0;
            check(3, 18);
        });

        image319.setOnClickListener(v -> {
            if(image319.isChecked()) checkBox[3][19] = 1;
            else checkBox[3][19] = 0;
            check(3, 19);
        }); // 3

        image40.setOnClickListener(v -> {
            if(image40.isChecked()) checkBox[4][0] = 1;
            else checkBox[4][0] = 0;
            check(4, 0);
        });

        image41.setOnClickListener(v -> {
            if(image41.isChecked()) checkBox[4][1] = 1;
            else checkBox[4][1] = 0;
            check(4, 1);
        });

        image42.setOnClickListener(v -> {
            if(image42.isChecked()) checkBox[4][2] = 1;
            else checkBox[4][2] = 0;
            check(4, 2);
        });

        image43.setOnClickListener(v -> {
            if(image43.isChecked()) checkBox[4][3] = 1;
            else checkBox[4][3] = 0;
            check(4, 3);
        });

        image44.setOnClickListener(v -> {
            if(image44.isChecked()) checkBox[4][4] = 1;
            else checkBox[4][4] = 0;
            check(4, 4);
        });

        image45.setOnClickListener(v -> {
            if(image45.isChecked()) checkBox[4][5] = 1;
            else checkBox[4][5] = 0;
            check(4, 5);
        });

        image46.setOnClickListener(v -> {
            if(image46.isChecked()) checkBox[4][6] = 1;
            else checkBox[4][6] = 0;
            check(4, 6);
        });

        image47.setOnClickListener(v -> {
            if(image47.isChecked()) checkBox[4][7] = 1;
            else checkBox[4][7] = 0;
            check(4, 7);
        });

        image48.setOnClickListener(v -> {
            if(image48.isChecked()) checkBox[4][8] = 1;
            else checkBox[4][8] = 0;
            check(4, 8);
        });

        image49.setOnClickListener(v -> {
            if(image49.isChecked()) checkBox[4][9] = 1;
            else checkBox[4][9] = 0;
            check(4, 9);
        });
        image410.setOnClickListener(v -> {
            if(image410.isChecked()) checkBox[4][10] = 1;
            else checkBox[4][10] = 0;
            check(4, 10);
        });

        image411.setOnClickListener(v -> {
            if(image411.isChecked()) checkBox[4][11] = 1;
            else checkBox[4][11] = 0;
            check(4, 11);
        });

        image412.setOnClickListener(v -> {
            if(image412.isChecked()) checkBox[4][12] = 1;
            else checkBox[4][12] = 0;
            check(4, 12);
        });

        image413.setOnClickListener(v -> {
            if(image413.isChecked()) checkBox[4][13] = 1;
            else checkBox[4][13] = 0;
            check(4, 13);
        });

        image414.setOnClickListener(v -> {
            if(image414.isChecked()) checkBox[4][14] = 1;
            else checkBox[4][14] = 0;
            check(4, 14);
        });

        image415.setOnClickListener(v -> {
            if(image415.isChecked()) checkBox[4][15] = 1;
            else checkBox[4][15] = 0;
            check(4, 15);
        });

        image416.setOnClickListener(v -> {
            if(image416.isChecked()) checkBox[4][16] = 1;
            else checkBox[4][16] = 0;
            check(4, 16);
        });

        image417.setOnClickListener(v -> {
            if(image417.isChecked()) checkBox[4][17] = 1;
            else checkBox[4][17] = 0;
            check(4, 17);
        });

        image418.setOnClickListener(v -> {
            if(image418.isChecked()) checkBox[4][18] = 1;
            else checkBox[4][18] = 0;
            check(4, 18);
        });

        image419.setOnClickListener(v -> {
            if(image419.isChecked()) checkBox[4][19] = 1;
            else checkBox[4][19] = 0;
            check(4, 19);
        }); // 4

        image50.setOnClickListener(v -> {
            if(image50.isChecked()) checkBox[5][0] = 1;
            else checkBox[5][0] = 0;
            check(5, 0);
        });

        image51.setOnClickListener(v -> {
            if(image51.isChecked()) checkBox[5][1] = 1;
            else checkBox[5][1] = 0;
            check(5, 1);
        });

        image52.setOnClickListener(v -> {
            if(image52.isChecked()) checkBox[5][2] = 1;
            else checkBox[5][2] = 0;
            check(5, 2);
        });

        image53.setOnClickListener(v -> {
            if(image53.isChecked()) checkBox[5][3] = 1;
            else checkBox[5][3] = 0;
            check(5, 3);
        });

        image54.setOnClickListener(v -> {
            if(image54.isChecked()) checkBox[5][4] = 1;
            else checkBox[5][4] = 0;
            check(5, 4);
        });

        image55.setOnClickListener(v -> {
            if(image55.isChecked()) checkBox[5][5] = 1;
            else checkBox[5][5] = 0;
            check(5, 5);
        });

        image56.setOnClickListener(v -> {
            if(image56.isChecked()) checkBox[5][6] = 1;
            else checkBox[5][6] = 0;
            check(5, 6);
        });

        image57.setOnClickListener(v -> {
            if(image57.isChecked()) checkBox[5][7] = 1;
            else checkBox[5][7] = 0;
            check(5, 7);
        });

        image58.setOnClickListener(v -> {
            if(image58.isChecked()) checkBox[5][8] = 1;
            else checkBox[5][8] = 0;
            check(5, 8);
        });

        image59.setOnClickListener(v -> {
            if(image59.isChecked()) checkBox[5][9] = 1;
            else checkBox[5][9] = 0;
            check(5, 9);
        });
        image510.setOnClickListener(v -> {
            if(image510.isChecked()) checkBox[5][10] = 1;
            else checkBox[5][10] = 0;
            check(5, 10);
        });

        image511.setOnClickListener(v -> {
            if(image511.isChecked()) checkBox[5][11] = 1;
            else checkBox[5][11] = 0;
            check(5, 11);
        });

        image512.setOnClickListener(v -> {
            if(image512.isChecked()) checkBox[5][12] = 1;
            else checkBox[5][12] = 0;
            check(5, 12);
        });

        image513.setOnClickListener(v -> {
            if(image513.isChecked()) checkBox[5][13] = 1;
            else checkBox[5][13] = 0;
            check(5, 13);
        });

        image514.setOnClickListener(v -> {
            if(image514.isChecked()) checkBox[5][14] = 1;
            else checkBox[5][14] = 0;
            check(5, 14);
        });

        image515.setOnClickListener(v -> {
            if(image515.isChecked()) checkBox[5][15] = 1;
            else checkBox[5][15] = 0;
            check(5, 15);
        });

        image516.setOnClickListener(v -> {
            if(image516.isChecked()) checkBox[5][16] = 1;
            else checkBox[5][16] = 0;
            check(5, 16);
        });

        image517.setOnClickListener(v -> {
            if(image517.isChecked()) checkBox[5][17] = 1;
            else checkBox[5][17] = 0;
            check(5, 17);
        });

        image518.setOnClickListener(v -> {
            if(image518.isChecked()) checkBox[5][18] = 1;
            else checkBox[5][18] = 0;
            check(5, 18);
        });

        image519.setOnClickListener(v -> {
            if(image519.isChecked()) checkBox[5][19] = 1;
            else checkBox[5][19] = 0;
            check(5, 19);
        }); // 5

        image60.setOnClickListener(v -> {
            if(image60.isChecked()) checkBox[6][0] = 1;
            else checkBox[6][0] = 0;
            check(6, 0);
        });

        image61.setOnClickListener(v -> {
            if(image61.isChecked()) checkBox[6][1] = 1;
            else checkBox[6][1] = 0;
            check(6, 1);
        });

        image62.setOnClickListener(v -> {
            if(image62.isChecked()) checkBox[6][2] = 1;
            else checkBox[6][2] = 0;
            check(6, 2);
        });

        image63.setOnClickListener(v -> {
            if(image63.isChecked()) checkBox[6][3] = 1;
            else checkBox[6][3] = 0;
            check(6, 3);
        });

        image64.setOnClickListener(v -> {
            if(image64.isChecked()) checkBox[6][4] = 1;
            else checkBox[6][4] = 0;
            check(6, 4);
        });

        image65.setOnClickListener(v -> {
            if(image65.isChecked()) checkBox[6][5] = 1;
            else checkBox[6][5] = 0;
            check(6, 5);
        });

        image66.setOnClickListener(v -> {
            if(image66.isChecked()) checkBox[6][6] = 1;
            else checkBox[6][6] = 0;
            check(6, 6);
        });

        image67.setOnClickListener(v -> {
            if(image67.isChecked()) checkBox[6][7] = 1;
            else checkBox[6][7] = 0;
            check(6, 7);
        });

        image68.setOnClickListener(v -> {
            if(image68.isChecked()) checkBox[6][8] = 1;
            else checkBox[6][8] = 0;
            check(6, 8);
        });

        image69.setOnClickListener(v -> {
            if(image69.isChecked()) checkBox[6][9] = 1;
            else checkBox[6][9] = 0;
            check(6, 9);
        });
        image610.setOnClickListener(v -> {
            if(image610.isChecked()) checkBox[6][10] = 1;
            else checkBox[6][10] = 0;
            check(6, 10);
        });

        image611.setOnClickListener(v -> {
            if(image611.isChecked()) checkBox[6][11] = 1;
            else checkBox[6][11] = 0;
            check(6, 11);
        });

        image612.setOnClickListener(v -> {
            if(image612.isChecked()) checkBox[6][12] = 1;
            else checkBox[6][12] = 0;
            check(6, 12);
        });

        image613.setOnClickListener(v -> {
            if(image613.isChecked()) checkBox[6][13] = 1;
            else checkBox[6][13] = 0;
            check(6, 13);
        });

        image614.setOnClickListener(v -> {
            if(image614.isChecked()) checkBox[6][14] = 1;
            else checkBox[6][14] = 0;
            check(6, 14);
        });

        image615.setOnClickListener(v -> {
            if(image615.isChecked()) checkBox[6][15] = 1;
            else checkBox[6][15] = 0;
            check(6, 15);
        });

        image616.setOnClickListener(v -> {
            if(image616.isChecked()) checkBox[6][16] = 1;
            else checkBox[6][16] = 0;
            check(6, 16);
        });

        image617.setOnClickListener(v -> {
            if(image617.isChecked()) checkBox[6][17] = 1;
            else checkBox[6][17] = 0;
            check(6, 17);
        });

        image618.setOnClickListener(v -> {
            if(image618.isChecked()) checkBox[6][18] = 1;
            else checkBox[6][18] = 0;
            check(6, 18);
        });

        image619.setOnClickListener(v -> {
            if(image619.isChecked()) checkBox[6][19] = 1;
            else checkBox[6][19] = 0;
            check(6, 19);
        }); // 6

        image70.setOnClickListener(v -> {
            if(image70.isChecked()) checkBox[7][0] = 1;
            else checkBox[7][0] = 0;
            check(7, 0);
        });

        image71.setOnClickListener(v -> {
            if(image71.isChecked()) checkBox[7][1] = 1;
            else checkBox[7][1] = 0;
            check(7, 1);
        });

        image72.setOnClickListener(v -> {
            if(image72.isChecked()) checkBox[7][2] = 1;
            else checkBox[7][2] = 0;
            check(7, 2);
        });

        image73.setOnClickListener(v -> {
            if(image73.isChecked()) checkBox[7][3] = 1;
            else checkBox[7][3] = 0;
            check(7, 3);
        });

        image74.setOnClickListener(v -> {
            if(image74.isChecked()) checkBox[7][4] = 1;
            else checkBox[7][4] = 0;
            check(7, 4);
        });

        image75.setOnClickListener(v -> {
            if(image75.isChecked()) checkBox[7][5] = 1;
            else checkBox[7][5] = 0;
            check(7, 5);
        });

        image76.setOnClickListener(v -> {
            if(image76.isChecked()) checkBox[7][6] = 1;
            else checkBox[7][6] = 0;
            check(7, 6);
        });

        image77.setOnClickListener(v -> {
            if(image77.isChecked()) checkBox[7][7] = 1;
            else checkBox[7][7] = 0;
            check(7, 7);
        });

        image78.setOnClickListener(v -> {
            if(image78.isChecked()) checkBox[7][8] = 1;
            else checkBox[7][8] = 0;
            check(7, 8);
        });

        image79.setOnClickListener(v -> {
            if(image79.isChecked()) checkBox[7][9] = 1;
            else checkBox[7][9] = 0;
            check(7, 9);
        });
        image710.setOnClickListener(v -> {
            if(image710.isChecked()) checkBox[7][10] = 1;
            else checkBox[7][10] = 0;
            check(7, 10);
        });

        image711.setOnClickListener(v -> {
            if(image711.isChecked()) checkBox[7][11] = 1;
            else checkBox[7][11] = 0;
            check(7, 11);
        });

        image712.setOnClickListener(v -> {
            if(image712.isChecked()) checkBox[7][12] = 1;
            else checkBox[7][12] = 0;
            check(7, 12);
        });

        image713.setOnClickListener(v -> {
            if(image713.isChecked()) checkBox[7][13] = 1;
            else checkBox[7][13] = 0;
            check(7, 13);
        });

        image714.setOnClickListener(v -> {
            if(image714.isChecked()) checkBox[7][14] = 1;
            else checkBox[7][14] = 0;
            check(7, 14);
        });

        image715.setOnClickListener(v -> {
            if(image715.isChecked()) checkBox[7][15] = 1;
            else checkBox[7][15] = 0;
            check(7, 15);
        });

        image716.setOnClickListener(v -> {
            if(image716.isChecked()) checkBox[7][16] = 1;
            else checkBox[7][16] = 0;
            check(7, 16);
        });

        image717.setOnClickListener(v -> {
            if(image717.isChecked()) checkBox[7][17] = 1;
            else checkBox[7][17] = 0;
            check(7, 17);
        });

        image718.setOnClickListener(v -> {
            if(image718.isChecked()) checkBox[7][18] = 1;
            else checkBox[7][18] = 0;
            check(7, 18);
        });

        image719.setOnClickListener(v -> {
            if(image719.isChecked()) checkBox[7][19] = 1;
            else checkBox[7][19] = 0;
            check(7, 19);
        }); // 7

        image80.setOnClickListener(v -> {
            if(image80.isChecked()) checkBox[8][0] = 1;
            else checkBox[8][0] = 0;
            check(8, 0);
        });

        image81.setOnClickListener(v -> {
            if(image81.isChecked()) checkBox[8][1] = 1;
            else checkBox[8][1] = 0;
            check(8, 1);
        });

        image82.setOnClickListener(v -> {
            if(image82.isChecked()) checkBox[8][2] = 1;
            else checkBox[8][2] = 0;
            check(8, 2);
        });

        image83.setOnClickListener(v -> {
            if(image83.isChecked()) checkBox[8][3] = 1;
            else checkBox[8][3] = 0;
            check(8, 3);
        });

        image84.setOnClickListener(v -> {
            if(image84.isChecked()) checkBox[8][4] = 1;
            else checkBox[8][4] = 0;
            check(8, 4);
        });

        image85.setOnClickListener(v -> {
            if(image85.isChecked()) checkBox[8][5] = 1;
            else checkBox[8][5] = 0;
            check(8, 5);
        });

        image86.setOnClickListener(v -> {
            if(image86.isChecked()) checkBox[8][6] = 1;
            else checkBox[8][6] = 0;
            check(8, 6);
        });

        image87.setOnClickListener(v -> {
            if(image87.isChecked()) checkBox[8][7] = 1;
            else checkBox[8][7] = 0;
            check(8, 7);
        });

        image88.setOnClickListener(v -> {
            if(image88.isChecked()) checkBox[8][8] = 1;
            else checkBox[8][8] = 0;
            check(8, 8);
        });

        image89.setOnClickListener(v -> {
            if(image89.isChecked()) checkBox[8][9] = 1;
            else checkBox[8][9] = 0;
            check(8, 9);
        });
        image810.setOnClickListener(v -> {
            if(image810.isChecked()) checkBox[8][10] = 1;
            else checkBox[8][10] = 0;
            check(8, 10);
        });

        image811.setOnClickListener(v -> {
            if(image811.isChecked()) checkBox[8][11] = 1;
            else checkBox[8][11] = 0;
            check(8, 11);
        });

        image812.setOnClickListener(v -> {
            if(image812.isChecked()) checkBox[8][12] = 1;
            else checkBox[8][12] = 0;
            check(8, 12);
        });

        image813.setOnClickListener(v -> {
            if(image813.isChecked()) checkBox[8][13] = 1;
            else checkBox[8][13] = 0;
            check(8, 13);
        });

        image814.setOnClickListener(v -> {
            if(image814.isChecked()) checkBox[8][14] = 1;
            else checkBox[8][14] = 0;
            check(8, 14);
        });

        image815.setOnClickListener(v -> {
            if(image815.isChecked()) checkBox[8][15] = 1;
            else checkBox[8][15] = 0;
            check(8, 15);
        });

        image816.setOnClickListener(v -> {
            if(image816.isChecked()) checkBox[8][16] = 1;
            else checkBox[8][16] = 0;
            check(8, 16);
        });

        image817.setOnClickListener(v -> {
            if(image817.isChecked()) checkBox[8][17] = 1;
            else checkBox[8][17] = 0;
            check(8, 17);
        });

        image818.setOnClickListener(v -> {
            if(image818.isChecked()) checkBox[8][18] = 1;
            else checkBox[8][18] = 0;
            check(8, 18);
        });

        image819.setOnClickListener(v -> {
            if(image819.isChecked()) checkBox[8][19] = 1;
            else checkBox[8][19] = 0;
            check(8, 19);
        }); // 8

        image90.setOnClickListener(v -> {
            if(image90.isChecked()) checkBox[9][0] = 1;
            else checkBox[9][0] = 0;
            check(9, 0);
        });

        image91.setOnClickListener(v -> {
            if(image91.isChecked()) checkBox[9][1] = 1;
            else checkBox[9][1] = 0;
            check(9, 1);
        });

        image92.setOnClickListener(v -> {
            if(image92.isChecked()) checkBox[9][2] = 1;
            else checkBox[9][2] = 0;
            check(9, 2);
        });

        image93.setOnClickListener(v -> {
            if(image93.isChecked()) checkBox[9][3] = 1;
            else checkBox[9][3] = 0;
            check(9, 3);
        });

        image94.setOnClickListener(v -> {
            if(image94.isChecked()) checkBox[9][4] = 1;
            else checkBox[9][4] = 0;
            check(9, 4);
        });

        image95.setOnClickListener(v -> {
            if(image95.isChecked()) checkBox[9][5] = 1;
            else checkBox[9][5] = 0;
            check(9, 5);
        });

        image96.setOnClickListener(v -> {
            if(image96.isChecked()) checkBox[9][6] = 1;
            else checkBox[9][6] = 0;
            check(9, 6);
        });

        image97.setOnClickListener(v -> {
            if(image97.isChecked()) checkBox[9][7] = 1;
            else checkBox[9][7] = 0;
            check(9, 7);
        });

        image98.setOnClickListener(v -> {
            if(image98.isChecked()) checkBox[9][8] = 1;
            else checkBox[9][8] = 0;
            check(9, 8);
        });

        image99.setOnClickListener(v -> {
            if(image99.isChecked()) checkBox[9][9] = 1;
            else checkBox[9][9] = 0;
            check(9, 9);
        });
        image910.setOnClickListener(v -> {
            if(image910.isChecked()) checkBox[9][10] = 1;
            else checkBox[9][10] = 0;
            check(9, 10);
        });

        image911.setOnClickListener(v -> {
            if(image911.isChecked()) checkBox[9][11] = 1;
            else checkBox[9][11] = 0;
            check(9, 11);
        });

        image912.setOnClickListener(v -> {
            if(image912.isChecked()) checkBox[9][12] = 1;
            else checkBox[9][12] = 0;
            check(9, 12);
        });

        image913.setOnClickListener(v -> {
            if(image913.isChecked()) checkBox[9][13] = 1;
            else checkBox[9][13] = 0;
            check(9, 13);
        });

        image914.setOnClickListener(v -> {
            if(image914.isChecked()) checkBox[9][14] = 1;
            else checkBox[9][14] = 0;
            check(9, 14);
        });

        image915.setOnClickListener(v -> {
            if(image915.isChecked()) checkBox[9][15] = 1;
            else checkBox[9][15] = 0;
            check(9, 15);
        });

        image916.setOnClickListener(v -> {
            if(image916.isChecked()) checkBox[9][16] = 1;
            else checkBox[9][16] = 0;
            check(9, 16);
        });

        image917.setOnClickListener(v -> {
            if(image917.isChecked()) checkBox[9][17] = 1;
            else checkBox[9][17] = 0;
            check(9, 17);
        });

        image918.setOnClickListener(v -> {
            if(image918.isChecked()) checkBox[9][18] = 1;
            else checkBox[9][18] = 0;
            check(9, 18);
        });

        image919.setOnClickListener(v -> {
            if(image919.isChecked()) checkBox[9][19] = 1;
            else checkBox[9][19] = 0;
            check(9, 19);
        }); // 9

        image100.setOnClickListener(v -> {
            if(image100.isChecked()) checkBox[10][0] = 1;
            else checkBox[10][0] = 0;
            check(10, 0);
        });

        image101.setOnClickListener(v -> {
            if(image101.isChecked()) checkBox[10][1] = 1;
            else checkBox[10][1] = 0;
            check(10, 1);
        });

        image102.setOnClickListener(v -> {
            if(image102.isChecked()) checkBox[10][2] = 1;
            else checkBox[10][2] = 0;
            check(10, 2);
        });

        image103.setOnClickListener(v -> {
            if(image103.isChecked()) checkBox[10][3] = 1;
            else checkBox[10][3] = 0;
            check(10, 3);
        });

        image104.setOnClickListener(v -> {
            if(image104.isChecked()) checkBox[10][4] = 1;
            else checkBox[10][4] = 0;
            check(10, 4);
        });

        image105.setOnClickListener(v -> {
            if(image105.isChecked()) checkBox[10][5] = 1;
            else checkBox[10][5] = 0;
            check(10, 5);
        });

        image106.setOnClickListener(v -> {
            if(image106.isChecked()) checkBox[10][6] = 1;
            else checkBox[10][6] = 0;
            check(10, 6);
        });

        image107.setOnClickListener(v -> {
            if(image107.isChecked()) checkBox[10][7] = 1;
            else checkBox[10][7] = 0;
            check(10, 7);
        });

        image108.setOnClickListener(v -> {
            if(image108.isChecked()) checkBox[10][8] = 1;
            else checkBox[10][8] = 0;
            check(10, 8);
        });

        image109.setOnClickListener(v -> {
            if(image109.isChecked()) checkBox[10][9] = 1;
            else checkBox[10][9] = 0;
            check(10, 9);
        });
        image1010.setOnClickListener(v -> {
            if(image1010.isChecked()) checkBox[10][10] = 1;
            else checkBox[10][10] = 0;
            check(10, 10);
        });

        image1011.setOnClickListener(v -> {
            if(image1011.isChecked()) checkBox[10][11] = 1;
            else checkBox[10][11] = 0;
            check(10, 11);
        });

        image1012.setOnClickListener(v -> {
            if(image1012.isChecked()) checkBox[10][12] = 1;
            else checkBox[10][12] = 0;
            check(10, 12);
        });

        image1013.setOnClickListener(v -> {
            if(image1013.isChecked()) checkBox[10][13] = 1;
            else checkBox[10][13] = 0;
            check(10, 13);
        });

        image1014.setOnClickListener(v -> {
            if(image1014.isChecked()) checkBox[10][14] = 1;
            else checkBox[10][14] = 0;
            check(10, 14);
        });

        image1015.setOnClickListener(v -> {
            if(image1015.isChecked()) checkBox[10][15] = 1;
            else checkBox[10][15] = 0;
            check(10, 15);
        });

        image1016.setOnClickListener(v -> {
            if(image1016.isChecked()) checkBox[10][16] = 1;
            else checkBox[10][16] = 0;
            check(10, 16);
        });

        image1017.setOnClickListener(v -> {
            if(image1017.isChecked()) checkBox[10][17] = 1;
            else checkBox[10][17] = 0;
            check(10, 17);
        });

        image1018.setOnClickListener(v -> {
            if (image1018.isChecked()) checkBox[10][18] = 1;
            else checkBox[10][18] = 0;
            check(10, 18);
        });

        image1019.setOnClickListener(v -> {
            if(image1019.isChecked()) checkBox[10][19] = 1;
            else checkBox[10][19] = 0;
            check(10, 19);
        }); // 10

        image1100.setOnClickListener(v -> {
            if(image1100.isChecked()) checkBox[11][0] = 1;
            else checkBox[11][0] = 0;
            check(11, 0);
        });

        image1101.setOnClickListener(v -> {
            if(image1101.isChecked()) checkBox[11][1] = 1;
            else checkBox[11][1] = 0;
            check(11, 1);
        });

        image1102.setOnClickListener(v -> {
            if(image1102.isChecked()) checkBox[11][2] = 1;
            else checkBox[11][2] = 0;
            check(11, 2);
        });

        image1103.setOnClickListener(v -> {
            if(image1103.isChecked()) checkBox[11][3] = 1;
            else checkBox[11][3] = 0;
            check(11, 3);
        });

        image1104.setOnClickListener(v -> {
            if(image1104.isChecked()) checkBox[11][4] = 1;
            else checkBox[11][4] = 0;
            check(11, 4);
        });

        image1105.setOnClickListener(v -> {
            if(image1105.isChecked()) checkBox[11][5] = 1;
            else checkBox[11][5] = 0;
            check(11, 5);
        });

        image1106.setOnClickListener(v -> {
            if(image1106.isChecked()) checkBox[11][6] = 1;
            else checkBox[11][6] = 0;
            check(11, 6);
        });

        image1107.setOnClickListener(v -> {
            if(image1107.isChecked()) checkBox[11][7] = 1;
            else checkBox[11][7] = 0;
            check(11, 7);
        });

        image1108.setOnClickListener(v -> {
            if(image1108.isChecked()) checkBox[11][8] = 1;
            else checkBox[11][8] = 0;
            check(11, 8);
        });

        image1109.setOnClickListener(v -> {
            if(image1109.isChecked()) checkBox[11][9] = 1;
            else checkBox[11][9] = 0;
            check(11, 9);
        });
        image1110.setOnClickListener(v -> {
            if(image1110.isChecked()) checkBox[11][10] = 1;
            else checkBox[11][10] = 0;
            check(11, 10);
        });

        image1111.setOnClickListener(v -> {
            if(image1111.isChecked()) checkBox[11][11] = 1;
            else checkBox[11][11] = 0;
            check(11, 11);
        });

        image1112.setOnClickListener(v -> {
            if(image1112.isChecked()) checkBox[11][12] = 1;
            else checkBox[11][12] = 0;
            check(11, 12);
        });

        image1113.setOnClickListener(v -> {
            if(image1113.isChecked()) checkBox[11][13] = 1;
            else checkBox[11][13] = 0;
            check(11, 13);
        });

        image1114.setOnClickListener(v -> {
            if(image1114.isChecked()) checkBox[11][14] = 1;
            else checkBox[11][14] = 0;
            check(11, 14);
        });

        image1115.setOnClickListener(v -> {
            if(image1115.isChecked()) checkBox[11][15] = 1;
            else checkBox[11][15] = 0;
            check(11, 15);
        });

        image1116.setOnClickListener(v -> {
            if(image1116.isChecked()) checkBox[11][16] = 1;
            else checkBox[11][16] = 0;
            check(11, 16);
        });

        image1117.setOnClickListener(v -> {
            if(image1117.isChecked()) checkBox[11][17] = 1;
            else checkBox[11][17] = 0;
            check(11, 17);
        });

        image1118.setOnClickListener(v -> {
            if(image1118.isChecked()) checkBox[11][18] = 1;
            else checkBox[11][18] = 0;
            check(11, 18);
        });

        image1119.setOnClickListener(v -> {
            if(image1119.isChecked()) checkBox[11][19] = 1;
            else checkBox[11][19] = 0;
            check(11, 19);
        }); // 11

        image120.setOnClickListener(v -> {
            if(image120.isChecked()) checkBox[12][0] = 1;
            else checkBox[12][0] = 0;
            check(12, 0);
        });

        image121.setOnClickListener(v -> {
            if(image121.isChecked()) checkBox[12][1] = 1;
            else checkBox[12][1] = 0;
            check(12, 1);
        });

        image122.setOnClickListener(v -> {
            if(image122.isChecked()) checkBox[12][2] = 1;
            else checkBox[12][2] = 0;
            check(12, 2);
        });

        image123.setOnClickListener(v -> {
            if(image123.isChecked()) checkBox[12][3] = 1;
            else checkBox[12][3] = 0;
            check(12, 3);
        });

        image124.setOnClickListener(v -> {
            if(image124.isChecked()) checkBox[12][4] = 1;
            else checkBox[12][4] = 0;
            check(12, 4);
        });

        image125.setOnClickListener(v -> {
            if(image125.isChecked()) checkBox[12][5] = 1;
            else checkBox[12][5] = 0;
            check(12, 5);
        });

        image126.setOnClickListener(v -> {
            if(image126.isChecked()) checkBox[12][6] = 1;
            else checkBox[12][6] = 0;
            check(12, 6);
        });

        image127.setOnClickListener(v -> {
            if(image127.isChecked()) checkBox[12][7] = 1;
            else checkBox[12][7] = 0;
            check(12, 7);
        });

        image128.setOnClickListener(v -> {
            if(image128.isChecked()) checkBox[12][8] = 1;
            else checkBox[12][8] = 0;
            check(12, 8);
        });

        image129.setOnClickListener(v -> {
            if(image129.isChecked()) checkBox[12][9] = 1;
            else checkBox[12][9] = 0;
            check(12, 9);
        });
        image1210.setOnClickListener(v -> {
            if(image1210.isChecked()) checkBox[12][10] = 1;
            else checkBox[12][10] = 0;
            check(12, 10);
        });

        image1211.setOnClickListener(v -> {
            if(image1211.isChecked()) checkBox[12][11] = 1;
            else checkBox[12][11] = 0;
            check(12, 11);
        });

        image1212.setOnClickListener(v -> {
            if(image1212.isChecked()) checkBox[12][12] = 1;
            else checkBox[12][12] = 0;
            check(12, 12);
        });

        image1213.setOnClickListener(v -> {
            if(image1213.isChecked()) checkBox[12][13] = 1;
            else checkBox[12][13] = 0;
            check(12, 13);
        });

        image1214.setOnClickListener(v -> {
            if(image1214.isChecked()) checkBox[12][14] = 1;
            else checkBox[12][14] = 0;
            check(12, 14);
        });

        image1215.setOnClickListener(v -> {
            if(image1215.isChecked()) checkBox[12][15] = 1;
            else checkBox[12][15] = 0;
            check(12, 15);
        });

        image1216.setOnClickListener(v -> {
            if(image1216.isChecked()) checkBox[12][16] = 1;
            else checkBox[12][16] = 0;
            check(12, 16);
        });

        image1217.setOnClickListener(v -> {
            if(image1217.isChecked()) checkBox[12][17] = 1;
            else checkBox[12][17] = 0;
            check(12, 17);
        });

        image1218.setOnClickListener(v -> {
            if(image1218.isChecked()) checkBox[12][18] = 1;
            else checkBox[12][18] = 0;
            check(12, 18);
        });

        image1219.setOnClickListener(v -> {
            if(image1219.isChecked()) checkBox[12][19] = 1;
            else checkBox[12][19] = 0;
            check(12, 19);
        }); // 12

        image130.setOnClickListener(v -> {
            if(image130.isChecked()) checkBox[13][0] = 1;
            else checkBox[13][0] = 0;
            check(13, 0);
        });

        image131.setOnClickListener(v -> {
            if(image131.isChecked()) checkBox[13][1] = 1;
            else checkBox[13][1] = 0;
            check(13, 1);
        });

        image132.setOnClickListener(v -> {
            if(image132.isChecked()) checkBox[13][2] = 1;
            else checkBox[13][2] = 0;
            check(13, 2);
        });

        image133.setOnClickListener(v -> {
            if(image133.isChecked()) checkBox[13][3] = 1;
            else checkBox[13][3] = 0;
            check(13, 3);
        });

        image134.setOnClickListener(v -> {
            if(image134.isChecked()) checkBox[13][4] = 1;
            else checkBox[13][4] = 0;
            check(13, 4);
        });

        image135.setOnClickListener(v -> {
            if(image135.isChecked()) checkBox[13][5] = 1;
            else checkBox[13][5] = 0;
            check(13, 5);
        });

        image136.setOnClickListener(v -> {
            if(image136.isChecked()) checkBox[13][6] = 1;
            else checkBox[13][6] = 0;
            check(13, 6);
        });

        image137.setOnClickListener(v -> {
            if(image137.isChecked()) checkBox[13][7] = 1;
            else checkBox[13][7] = 0;
            check(13, 7);
        });

        image138.setOnClickListener(v -> {
            if(image138.isChecked()) checkBox[13][8] = 1;
            else checkBox[13][8] = 0;
            check(13, 8);
        });

        image139.setOnClickListener(v -> {
            if(image139.isChecked()) checkBox[13][9] = 1;
            else checkBox[13][9] = 0;
            check(13, 9);
        });
        image1310.setOnClickListener(v -> {
            if(image1310.isChecked()) checkBox[13][10] = 1;
            else checkBox[13][10] = 0;
            check(13, 10);
        });

        image1311.setOnClickListener(v -> {
            if(image1311.isChecked()) checkBox[13][11] = 1;
            else checkBox[13][11] = 0;
            check(13, 11);
        });

        image1312.setOnClickListener(v -> {
            if(image1312.isChecked()) checkBox[13][12] = 1;
            else checkBox[13][12] = 0;
            check(13, 12);
        });

        image1313.setOnClickListener(v -> {
            if(image1313.isChecked()) checkBox[13][13] = 1;
            else checkBox[13][13] = 0;
            check(13, 13);
        });

        image1314.setOnClickListener(v -> {
            if(image1314.isChecked()) checkBox[13][14] = 1;
            else checkBox[13][14] = 0;
            check(13, 14);
        });

        image1315.setOnClickListener(v -> {
            if(image1315.isChecked()) checkBox[13][15] = 1;
            else checkBox[13][15] = 0;
            check(13, 15);
        });

        image1316.setOnClickListener(v -> {
            if(image1316.isChecked()) checkBox[13][16] = 1;
            else checkBox[13][16] = 0;
            check(13, 16);
        });

        image1317.setOnClickListener(v -> {
            if(image1317.isChecked()) checkBox[13][17] = 1;
            else checkBox[13][17] = 0;
            check(13, 17);
        });

        image1318.setOnClickListener(v -> {
            if(image1318.isChecked()) checkBox[13][18] = 1;
            else checkBox[13][18] = 0;
            check(13, 18);
        });

        image1319.setOnClickListener(v -> {
            if(image1319.isChecked()) checkBox[13][19] = 1;
            else checkBox[13][19] = 0;
            check(13, 19);
        }); // 3

        image140.setOnClickListener(v -> {
            if(image140.isChecked()) checkBox[14][0] = 1;
            else checkBox[14][0] = 0;
            check(14, 0);
        });

        image141.setOnClickListener(v -> {
            if(image141.isChecked()) checkBox[14][1] = 1;
            else checkBox[14][1] = 0;
            check(14, 1);
        });

        image142.setOnClickListener(v -> {
            if(image142.isChecked()) checkBox[14][2] = 1;
            else checkBox[14][2] = 0;
            check(14, 2);
        });

        image143.setOnClickListener(v -> {
            if(image143.isChecked()) checkBox[14][3] = 1;
            else checkBox[14][3] = 0;
            check(14, 3);
        });

        image144.setOnClickListener(v -> {
            if(image144.isChecked()) checkBox[14][4] = 1;
            else checkBox[14][4] = 0;
            check(14, 4);
        });

        image145.setOnClickListener(v -> {
            if(image145.isChecked()) checkBox[14][5] = 1;
            else checkBox[14][5] = 0;
            check(14, 5);
        });

        image146.setOnClickListener(v -> {
            if(image146.isChecked()) checkBox[14][6] = 1;
            else checkBox[14][6] = 0;
            check(14, 6);
        });

        image147.setOnClickListener(v -> {
            if(image147.isChecked()) checkBox[14][7] = 1;
            else checkBox[14][7] = 0;
            check(14, 7);
        });

        image148.setOnClickListener(v -> {
            if(image148.isChecked()) checkBox[14][8] = 1;
            else checkBox[14][8] = 0;
            check(14, 8);
        });

        image149.setOnClickListener(v -> {
            if(image149.isChecked()) checkBox[14][9] = 1;
            else checkBox[14][9] = 0;
            check(14, 9);
        });
        image1410.setOnClickListener(v -> {
            if(image1410.isChecked()) checkBox[14][10] = 1;
            else checkBox[14][10] = 0;
            check(14, 10);
        });

        image1411.setOnClickListener(v -> {
            if(image1411.isChecked()) checkBox[14][11] = 1;
            else checkBox[14][11] = 0;
            check(14, 11);
        });

        image1412.setOnClickListener(v -> {
            if(image1412.isChecked()) checkBox[14][12] = 1;
            else checkBox[14][12] = 0;
            check(14, 12);
        });

        image1413.setOnClickListener(v -> {
            if(image1413.isChecked()) checkBox[14][13] = 1;
            else checkBox[14][13] = 0;
            check(14, 13);
        });

        image1414.setOnClickListener(v -> {
            if(image1414.isChecked()) checkBox[14][14] = 1;
            else checkBox[14][14] = 0;
            check(14, 14);
        });

        image1415.setOnClickListener(v -> {
            if(image1415.isChecked()) checkBox[14][15] = 1;
            else checkBox[14][15] = 0;
            check(14, 15);
        });

        image1416.setOnClickListener(v -> {
            if(image1416.isChecked()) checkBox[14][16] = 1;
            else checkBox[14][16] = 0;
            check(14, 16);
        });

        image1417.setOnClickListener(v -> {
            if(image1417.isChecked()) checkBox[14][17] = 1;
            else checkBox[14][17] = 0;
            check(14, 17);
        });

        image1418.setOnClickListener(v -> {
            if(image1418.isChecked()) checkBox[14][18] = 1;
            else checkBox[14][18] = 0;
            check(14, 18);
        });

        image1419.setOnClickListener(v -> {
            if(image1419.isChecked()) checkBox[14][19] = 1;
            else checkBox[14][19] = 0;
            check(14, 19);
        }); // 4

        image150.setOnClickListener(v -> {
            if(image150.isChecked()) checkBox[15][0] = 1;
            else checkBox[15][0] = 0;
            check(15, 0);
        });

        image151.setOnClickListener(v -> {
            if(image151.isChecked()) checkBox[15][1] = 1;
            else checkBox[15][1] = 0;
            check(15, 1);
        });

        image152.setOnClickListener(v -> {
            if(image152.isChecked()) checkBox[15][2] = 1;
            else checkBox[15][2] = 0;
            check(15, 2);
        });

        image153.setOnClickListener(v -> {
            if(image153.isChecked()) checkBox[15][3] = 1;
            else checkBox[15][3] = 0;
            check(15, 3);
        });

        image154.setOnClickListener(v -> {
            if(image154.isChecked()) checkBox[15][4] = 1;
            else checkBox[15][4] = 0;
            check(15, 4);
        });

        image155.setOnClickListener(v -> {
            if(image155.isChecked()) checkBox[15][5] = 1;
            else checkBox[15][5] = 0;
            check(15, 5);
        });

        image156.setOnClickListener(v -> {
            if(image156.isChecked()) checkBox[15][6] = 1;
            else checkBox[15][6] = 0;
            check(15, 6);
        });

        image157.setOnClickListener(v -> {
            if(image157.isChecked()) checkBox[15][7] = 1;
            else checkBox[15][7] = 0;
            check(15, 7);
        });

        image158.setOnClickListener(v -> {
            if(image158.isChecked()) checkBox[15][8] = 1;
            else checkBox[15][8] = 0;
            check(15, 8);
        });

        image159.setOnClickListener(v -> {
            if(image159.isChecked()) checkBox[15][9] = 1;
            else checkBox[15][9] = 0;
            check(15, 9);
        });
        image1510.setOnClickListener(v -> {
            if(image1510.isChecked()) checkBox[15][10] = 1;
            else checkBox[15][10] = 0;
            check(15, 10);
        });

        image1511.setOnClickListener(v -> {
            if(image1511.isChecked()) checkBox[15][11] = 1;
            else checkBox[15][11] = 0;
            check(15, 11);
        });

        image1512.setOnClickListener(v -> {
            if(image1512.isChecked()) checkBox[15][12] = 1;
            else checkBox[15][12] = 0;
            check(15, 12);
        });

        image1513.setOnClickListener(v -> {
            if(image1513.isChecked()) checkBox[15][13] = 1;
            else checkBox[15][13] = 0;
            check(15, 13);
        });

        image1514.setOnClickListener(v -> {
            if(image1514.isChecked()) checkBox[15][14] = 1;
            else checkBox[15][14] = 0;
            check(15, 14);
        });

        image1515.setOnClickListener(v -> {
            if(image1515.isChecked()) checkBox[15][15] = 1;
            else checkBox[15][15] = 0;
            check(15, 15);
        });

        image1516.setOnClickListener(v -> {
            if(image1516.isChecked()) checkBox[15][16] = 1;
            else checkBox[15][16] = 0;
            check(15, 16);
        });

        image1517.setOnClickListener(v -> {
            if(image1517.isChecked()) checkBox[15][17] = 1;
            else checkBox[15][17] = 0;
            check(15, 17);
        });

        image1518.setOnClickListener(v -> {
            if(image1518.isChecked()) checkBox[15][18] = 1;
            else checkBox[15][18] = 0;
            check(15, 18);
        });

        image1519.setOnClickListener(v -> {
            if(image1519.isChecked()) checkBox[15][19] = 1;
            else checkBox[15][19] = 0;
            check(15, 19);
        }); // 5

        image160.setOnClickListener(v -> {
            if(image160.isChecked()) checkBox[16][0] = 1;
            else checkBox[16][0] = 0;
            check(16, 0);
        });

        image161.setOnClickListener(v -> {
            if(image161.isChecked()) checkBox[16][1] = 1;
            else checkBox[16][1] = 0;
            check(16, 1);
        });

        image162.setOnClickListener(v -> {
            if(image162.isChecked()) checkBox[16][2] = 1;
            else checkBox[16][2] = 0;
            check(16, 2);
        });

        image163.setOnClickListener(v -> {
            if(image163.isChecked()) checkBox[16][3] = 1;
            else checkBox[16][3] = 0;
            check(16, 3);
        });

        image164.setOnClickListener(v -> {
            if(image164.isChecked()) checkBox[16][4] = 1;
            else checkBox[16][4] = 0;
            check(16, 4);
        });

        image165.setOnClickListener(v -> {
            if(image165.isChecked()) checkBox[16][5] = 1;
            else checkBox[16][5] = 0;
            check(16, 5);
        });

        image166.setOnClickListener(v -> {
            if(image166.isChecked()) checkBox[16][6] = 1;
            else checkBox[16][6] = 0;
            check(16, 6);
        });

        image167.setOnClickListener(v -> {
            if(image167.isChecked()) checkBox[16][7] = 1;
            else checkBox[16][7] = 0;
            check(16, 7);
        });

        image168.setOnClickListener(v -> {
            if(image168.isChecked()) checkBox[16][8] = 1;
            else checkBox[16][8] = 0;
            check(16, 8);
        });

        image169.setOnClickListener(v -> {
            if(image169.isChecked()) checkBox[16][9] = 1;
            else checkBox[16][9] = 0;
            check(16, 9);
        });
        image1610.setOnClickListener(v -> {
            if(image1610.isChecked()) checkBox[16][10] = 1;
            else checkBox[16][10] = 0;
            check(16, 10);
        });

        image1611.setOnClickListener(v -> {
            if(image1611.isChecked()) checkBox[16][11] = 1;
            else checkBox[16][11] = 0;
            check(16, 11);
        });

        image1612.setOnClickListener(v -> {
            if(image1612.isChecked()) checkBox[16][12] = 1;
            else checkBox[16][12] = 0;
            check(16, 12);
        });

        image1613.setOnClickListener(v -> {
            if(image1613.isChecked()) checkBox[16][13] = 1;
            else checkBox[16][13] = 0;
            check(16, 13);
        });

        image1614.setOnClickListener(v -> {
            if(image1614.isChecked()) checkBox[16][14] = 1;
            else checkBox[16][14] = 0;
            check(16, 14);
        });

        image1615.setOnClickListener(v -> {
            if(image1615.isChecked()) checkBox[16][15] = 1;
            else checkBox[16][15] = 0;
            check(16, 15);
        });

        image1616.setOnClickListener(v -> {
            if(image1616.isChecked()) checkBox[16][16] = 1;
            else checkBox[16][16] = 0;
            check(16, 16);
        });

        image1617.setOnClickListener(v -> {
            if(image1617.isChecked()) checkBox[16][17] = 1;
            else checkBox[16][17] = 0;
            check(16, 17);
        });

        image1618.setOnClickListener(v -> {
            if(image1618.isChecked()) checkBox[16][18] = 1;
            else checkBox[16][18] = 0;
            check(16, 18);
        });

        image1619.setOnClickListener(v -> {
            if(image1619.isChecked()) checkBox[16][19] = 1;
            else checkBox[16][19] = 0;
            check(16, 19);
        }); // 6

        image170.setOnClickListener(v -> {
            if(image170.isChecked()) checkBox[17][0] = 1;
            else checkBox[17][0] = 0;
            check(17, 0);
        });

        image171.setOnClickListener(v -> {
            if(image171.isChecked()) checkBox[17][1] = 1;
            else checkBox[17][1] = 0;
            check(17, 1);
        });

        image172.setOnClickListener(v -> {
            if(image172.isChecked()) checkBox[17][2] = 1;
            else checkBox[17][2] = 0;
            check(17, 2);
        });

        image173.setOnClickListener(v -> {
            if(image173.isChecked()) checkBox[17][3] = 1;
            else checkBox[17][3] = 0;
            check(17, 3);
        });

        image174.setOnClickListener(v -> {
            if(image174.isChecked()) checkBox[17][4] = 1;
            else checkBox[17][4] = 0;
            check(17, 4);
        });

        image175.setOnClickListener(v -> {
            if(image175.isChecked()) checkBox[17][5] = 1;
            else checkBox[17][5] = 0;
            check(17, 5);
        });

        image176.setOnClickListener(v -> {
            if(image176.isChecked()) checkBox[17][6] = 1;
            else checkBox[17][6] = 0;
            check(17, 6);
        });

        image177.setOnClickListener(v -> {
            if(image177.isChecked()) checkBox[17][7] = 1;
            else checkBox[17][7] = 0;
            check(17, 7);
        });

        image178.setOnClickListener(v -> {
            if(image178.isChecked()) checkBox[17][8] = 1;
            else checkBox[17][8] = 0;
            check(17, 8);
        });

        image179.setOnClickListener(v -> {
            if(image179.isChecked()) checkBox[17][9] = 1;
            else checkBox[17][9] = 0;
            check(17, 9);
        });
        image1710.setOnClickListener(v -> {
            if(image1710.isChecked()) checkBox[17][10] = 1;
            else checkBox[17][10] = 0;
            check(17, 10);
        });

        image1711.setOnClickListener(v -> {
            if(image1711.isChecked()) checkBox[17][11] = 1;
            else checkBox[17][11] = 0;
            check(17, 11);
        });

        image1712.setOnClickListener(v -> {
            if(image1712.isChecked()) checkBox[17][12] = 1;
            else checkBox[17][12] = 0;
            check(17, 12);
        });

        image1713.setOnClickListener(v -> {
            if(image1713.isChecked()) checkBox[17][13] = 1;
            else checkBox[17][13] = 0;
            check(17, 13);
        });

        image1714.setOnClickListener(v -> {
            if(image1714.isChecked()) checkBox[17][14] = 1;
            else checkBox[17][14] = 0;
            check(17, 14);
        });

        image1715.setOnClickListener(v -> {
            if(image1715.isChecked()) checkBox[17][15] = 1;
            else checkBox[17][15] = 0;
            check(17, 15);
        });

        image1716.setOnClickListener(v -> {
            if(image1716.isChecked()) checkBox[17][16] = 1;
            else checkBox[17][16] = 0;
            check(17, 16);
        });

        image1717.setOnClickListener(v -> {
            if(image1717.isChecked()) checkBox[17][17] = 1;
            else checkBox[17][17] = 0;
            check(17, 17);
        });

        image1718.setOnClickListener(v -> {
            if(image1718.isChecked()) checkBox[17][18] = 1;
            else checkBox[17][18] = 0;
            check(17, 18);
        });

        image1719.setOnClickListener(v -> {
            if(image1719.isChecked()) checkBox[17][19] = 1;
            else checkBox[17][19] = 0;
            check(17, 19);
        }); // 7

        image180.setOnClickListener(v -> {
            if(image180.isChecked()) checkBox[18][0] = 1;
            else checkBox[18][0] = 0;
            check(18, 0);
        });

        image181.setOnClickListener(v -> {
            if(image181.isChecked()) checkBox[18][1] = 1;
            else checkBox[18][1] = 0;
            check(18, 1);
        });

        image182.setOnClickListener(v -> {
            if(image182.isChecked()) checkBox[18][2] = 1;
            else checkBox[18][2] = 0;
            check(18, 2);
        });

        image183.setOnClickListener(v -> {
            if(image183.isChecked()) checkBox[18][3] = 1;
            else checkBox[18][3] = 0;
            check(18, 3);
        });

        image184.setOnClickListener(v -> {
            if(image184.isChecked()) checkBox[18][4] = 1;
            else checkBox[18][4] = 0;
            check(18, 4);
        });

        image185.setOnClickListener(v -> {
            if(image185.isChecked()) checkBox[18][5] = 1;
            else checkBox[18][5] = 0;
            check(18, 5);
        });

        image186.setOnClickListener(v -> {
            if(image186.isChecked()) checkBox[18][6] = 1;
            else checkBox[18][6] = 0;
            check(18, 6);
        });

        image187.setOnClickListener(v -> {
            if(image187.isChecked()) checkBox[18][7] = 1;
            else checkBox[18][7] = 0;
            check(18, 7);
        });

        image188.setOnClickListener(v -> {
            if(image188.isChecked()) checkBox[18][8] = 1;
            else checkBox[18][8] = 0;
            check(18, 8);
        });

        image189.setOnClickListener(v -> {
            if(image189.isChecked()) checkBox[18][9] = 1;
            else checkBox[18][9] = 0;
            check(18, 9);
        });
        image1810.setOnClickListener(v -> {
            if(image1810.isChecked()) checkBox[18][10] = 1;
            else checkBox[18][10] = 0;
            check(18, 10);
        });

        image1811.setOnClickListener(v -> {
            if(image1811.isChecked()) checkBox[18][11] = 1;
            else checkBox[18][11] = 0;
            check(18, 11);
        });

        image1812.setOnClickListener(v -> {
            if(image1812.isChecked()) checkBox[18][12] = 1;
            else checkBox[18][12] = 0;
            check(18, 12);
        });

        image1813.setOnClickListener(v -> {
            if(image1813.isChecked()) checkBox[18][13] = 1;
            else checkBox[18][13] = 0;
            check(18, 13);
        });

        image1814.setOnClickListener(v -> {
            if(image1814.isChecked()) checkBox[18][14] = 1;
            else checkBox[18][14] = 0;
            check(18, 14);
        });

        image1815.setOnClickListener(v -> {
            if(image1815.isChecked()) checkBox[18][15] = 1;
            else checkBox[18][15] = 0;
            check(18, 15);
        });

        image1816.setOnClickListener(v -> {
            if(image1816.isChecked()) checkBox[18][16] = 1;
            else checkBox[18][16] = 0;
            check(18, 16);
        });

        image1817.setOnClickListener(v -> {
            if(image1817.isChecked()) checkBox[18][17] = 1;
            else checkBox[18][17] = 0;
            check(18, 17);
        });

        image1818.setOnClickListener(v -> {
            if(image1818.isChecked()) checkBox[18][18] = 1;
            else checkBox[18][18] = 0;
            check(18, 18);
        });

        image1819.setOnClickListener(v -> {
            if(image1819.isChecked()) checkBox[18][19] = 1;
            else checkBox[18][19] = 0;
            check(18, 19);
        }); // 8

        image190.setOnClickListener(v -> {
            if(image190.isChecked()) checkBox[19][0] = 1;
            else checkBox[19][0] = 0;
            check(19, 0);
        });

        image191.setOnClickListener(v -> {
            if(image191.isChecked()) checkBox[19][1] = 1;
            else checkBox[19][1] = 0;
            check(19, 1);
        });

        image192.setOnClickListener(v -> {
            if(image192.isChecked()) checkBox[19][2] = 1;
            else checkBox[19][2] = 0;
            check(19, 2);
        });

        image193.setOnClickListener(v -> {
            if(image193.isChecked()) checkBox[19][3] = 1;
            else checkBox[19][3] = 0;
            check(19, 3);
        });

        image194.setOnClickListener(v -> {
            if(image194.isChecked()) checkBox[19][4] = 1;
            else checkBox[19][4] = 0;
            check(19, 4);
        });

        image195.setOnClickListener(v -> {
            if(image195.isChecked()) checkBox[19][5] = 1;
            else checkBox[19][5] = 0;
            check(19, 5);
        });

        image196.setOnClickListener(v -> {
            if(image196.isChecked()) checkBox[19][6] = 1;
            else checkBox[19][6] = 0;
            check(19, 6);
        });

        image197.setOnClickListener(v -> {
            if(image197.isChecked()) checkBox[19][7] = 1;
            else checkBox[19][7] = 0;
            check(19, 7);
        });

        image198.setOnClickListener(v -> {
            if(image198.isChecked()) checkBox[19][8] = 1;
            else checkBox[19][8] = 0;
            check(19, 8);
        });

        image199.setOnClickListener(v -> {
            if(image199.isChecked()) checkBox[19][9] = 1;
            else checkBox[19][9] = 0;
            check(19, 9);
        });
        image1910.setOnClickListener(v -> {
            if(image1910.isChecked()) checkBox[19][10] = 1;
            else checkBox[19][10] = 0;
            check(19, 10);
        });

        image1911.setOnClickListener(v -> {
            if(image1911.isChecked()) checkBox[19][11] = 1;
            else checkBox[19][11] = 0;
            check(19, 11);
        });

        image1912.setOnClickListener(v -> {
            if(image1912.isChecked()) checkBox[19][12] = 1;
            else checkBox[19][12] = 0;
            check(19, 12);
        });

        image1913.setOnClickListener(v -> {
            if(image1913.isChecked()) checkBox[19][13] = 1;
            else checkBox[19][13] = 0;
            check(19, 13);
        });

        image1914.setOnClickListener(v -> {
            if(image1914.isChecked()) checkBox[19][14] = 1;
            else checkBox[19][14] = 0;
            check(19, 14);
        });

        image1915.setOnClickListener(v -> {
            if(image1915.isChecked()) checkBox[19][15] = 1;
            else checkBox[19][15] = 0;
            check(19, 15);
        });

        image1916.setOnClickListener(v -> {
            if(image1916.isChecked()) checkBox[19][16] = 1;
            else checkBox[19][16] = 0;
            check(19, 16);
        });

        image1917.setOnClickListener(v -> {
            if(image1917.isChecked()) checkBox[19][17] = 1;
            else checkBox[19][17] = 0;
            check(19, 17);
        });

        image1918.setOnClickListener(v -> {
            if(image1918.isChecked()) checkBox[19][18] = 1;
            else checkBox[19][18] = 0;
            check(19, 18);
        });

        image1919.setOnClickListener(v -> {
            if(image1919.isChecked()) checkBox[19][19] = 1;
            else checkBox[19][19] = 0;
            check(19, 19);
        }); // 19
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        imageView = findViewById(R.id.image);
        int imgWidth = imageView.getWidth();
        int imgHeight = imageView.getHeight();

        piecewidth = imgWidth / 20;
        pieceheight = imgHeight / 20;

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();

                    resize = Bitmap.createScaledBitmap(img, imgWidth + 1, imgHeight + 1, false);

                    imageView.setImageBitmap(resize);
                    grayScale(resize);

                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "cancel", Toast.LENGTH_LONG).show();
            }
        }
    }

    protected Bitmap grayScale(final Bitmap orgBitmap) {
        int width = orgBitmap.getWidth();
        int height = orgBitmap.getHeight();
        int R = 0;
        int G = 0;
        int B = 0;
        int pixel;
        int i, j, x, y;
        piecewidth = width / 20;
        pieceheight = height / 20;

        Bitmap bmpGrayScale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);

        for (i = 0; i < 20; i++) {
            for (j = 0; j < 20; j++) {
                for (x = piecewidth * j; x < piecewidth * (j + 1); ++x) {
                    for (y = piecewidth * i; y < piecewidth * (i + 1); ++y){
                        pixel = orgBitmap.getPixel(x, y);
                        R += Color.red(pixel);
                        G += Color.green(pixel);
                        B += Color.blue(pixel);
                    }
                }
                int gray = (int) (R + G + B) / (pieceheight*piecewidth);
                if (gray > 128) {
                    bwarr[i][j] = 0;
                } else {
                    bwarr[i][j] = 1;
                }
                R = 0;
                G = 0;
                B = 0;
            }
        }

        maketext(bwarr);
        return bmpGrayScale;
    }
    public void check(int x, int y){
        int count = 0;
        if(bwarr[x][y] == 0 && checkBox[x][y] == 1) {
            image00.setChecked(false);
            image01.setChecked(false);
            image02.setChecked(false);
            image03.setChecked(false);
            image04.setChecked(false);
            image05.setChecked(false);
            image06.setChecked(false);
            image07.setChecked(false);
            image08.setChecked(false);
            image09.setChecked(false);
            image010.setChecked(false);
            image011.setChecked(false);
            image012.setChecked(false);
            image013.setChecked(false);
            image014.setChecked(false);
            image015.setChecked(false);
            image016.setChecked(false);
            image017.setChecked(false);
            image018.setChecked(false);
            image019.setChecked(false); // 0
            image10.setChecked(false);
            image11.setChecked(false);
            image12.setChecked(false);
            image13.setChecked(false);
            image14.setChecked(false);
            image15.setChecked(false);
            image16.setChecked(false);
            image17.setChecked(false);
            image18.setChecked(false);
            image19.setChecked(false);
            image110.setChecked(false);
            image111.setChecked(false);
            image112.setChecked(false);
            image113.setChecked(false);
            image114.setChecked(false);
            image115.setChecked(false);
            image116.setChecked(false);
            image117.setChecked(false);
            image118.setChecked(false);
            image119.setChecked(false); // 1
            image20.setChecked(false);
            image21.setChecked(false);
            image22.setChecked(false);
            image23.setChecked(false);
            image24.setChecked(false);
            image25.setChecked(false);
            image26.setChecked(false);
            image27.setChecked(false);
            image28.setChecked(false);
            image29.setChecked(false);
            image210.setChecked(false);
            image211.setChecked(false);
            image212.setChecked(false);
            image213.setChecked(false);
            image214.setChecked(false);
            image215.setChecked(false);
            image216.setChecked(false);
            image217.setChecked(false);
            image218.setChecked(false);
            image219.setChecked(false); // 2
            image30.setChecked(false);
            image31.setChecked(false);
            image32.setChecked(false);
            image33.setChecked(false);
            image34.setChecked(false);
            image35.setChecked(false);
            image36.setChecked(false);
            image37.setChecked(false);
            image38.setChecked(false);
            image39.setChecked(false);
            image310.setChecked(false);
            image311.setChecked(false);
            image312.setChecked(false);
            image313.setChecked(false);
            image314.setChecked(false);
            image315.setChecked(false);
            image316.setChecked(false);
            image317.setChecked(false);
            image318.setChecked(false);
            image319.setChecked(false); // 3
            image40.setChecked(false);
            image41.setChecked(false);
            image42.setChecked(false);
            image43.setChecked(false);
            image44.setChecked(false);
            image45.setChecked(false);
            image46.setChecked(false);
            image47.setChecked(false);
            image48.setChecked(false);
            image49.setChecked(false);
            image410.setChecked(false);
            image411.setChecked(false);
            image412.setChecked(false);
            image413.setChecked(false);
            image414.setChecked(false);
            image415.setChecked(false);
            image416.setChecked(false);
            image417.setChecked(false);
            image418.setChecked(false);
            image419.setChecked(false); // 4
            image50.setChecked(false);
            image51.setChecked(false);
            image52.setChecked(false);
            image53.setChecked(false);
            image54.setChecked(false);
            image55.setChecked(false);
            image56.setChecked(false);
            image57.setChecked(false);
            image58.setChecked(false);
            image59.setChecked(false);
            image510.setChecked(false);
            image511.setChecked(false);
            image512.setChecked(false);
            image513.setChecked(false);
            image514.setChecked(false);
            image515.setChecked(false);
            image516.setChecked(false);
            image517.setChecked(false);
            image518.setChecked(false);
            image519.setChecked(false); // 5
            image60.setChecked(false);
            image61.setChecked(false);
            image62.setChecked(false);
            image63.setChecked(false);
            image64.setChecked(false);
            image65.setChecked(false);
            image66.setChecked(false);
            image67.setChecked(false);
            image68.setChecked(false);
            image69.setChecked(false);
            image610.setChecked(false);
            image611.setChecked(false);
            image612.setChecked(false);
            image613.setChecked(false);
            image614.setChecked(false);
            image615.setChecked(false);
            image616.setChecked(false);
            image617.setChecked(false);
            image618.setChecked(false);
            image619.setChecked(false); // 6
            image70.setChecked(false);
            image71.setChecked(false);
            image72.setChecked(false);
            image73.setChecked(false);
            image74.setChecked(false);
            image75.setChecked(false);
            image76.setChecked(false);
            image77.setChecked(false);
            image78.setChecked(false);
            image79.setChecked(false);
            image710.setChecked(false);
            image711.setChecked(false);
            image712.setChecked(false);
            image713.setChecked(false);
            image714.setChecked(false);
            image715.setChecked(false);
            image716.setChecked(false);
            image717.setChecked(false);
            image718.setChecked(false);
            image719.setChecked(false); // 7
            image80.setChecked(false);
            image81.setChecked(false);
            image82.setChecked(false);
            image83.setChecked(false);
            image84.setChecked(false);
            image85.setChecked(false);
            image86.setChecked(false);
            image87.setChecked(false);
            image88.setChecked(false);
            image89.setChecked(false);
            image810.setChecked(false);
            image811.setChecked(false);
            image812.setChecked(false);
            image813.setChecked(false);
            image814.setChecked(false);
            image815.setChecked(false);
            image816.setChecked(false);
            image817.setChecked(false);
            image818.setChecked(false);
            image819.setChecked(false); // 8
            image90.setChecked(false);
            image91.setChecked(false);
            image92.setChecked(false);
            image93.setChecked(false);
            image94.setChecked(false);
            image95.setChecked(false);
            image96.setChecked(false);
            image97.setChecked(false);
            image98.setChecked(false);
            image99.setChecked(false);
            image910.setChecked(false);
            image911.setChecked(false);
            image912.setChecked(false);
            image913.setChecked(false);
            image914.setChecked(false);
            image915.setChecked(false);
            image916.setChecked(false);
            image917.setChecked(false);
            image918.setChecked(false);
            image919.setChecked(false); // 9
            image100.setChecked(false);
            image101.setChecked(false);
            image102.setChecked(false);
            image103.setChecked(false);
            image104.setChecked(false);
            image105.setChecked(false);
            image106.setChecked(false);
            image107.setChecked(false);
            image108.setChecked(false);
            image109.setChecked(false);
            image1010.setChecked(false);
            image1011.setChecked(false);
            image1012.setChecked(false);
            image1013.setChecked(false);
            image1014.setChecked(false);
            image1015.setChecked(false);
            image1016.setChecked(false);
            image1017.setChecked(false);
            image1018.setChecked(false);
            image1019.setChecked(false); // 10
            image1100.setChecked(false);
            image1101.setChecked(false);
            image1102.setChecked(false);
            image1103.setChecked(false);
            image1104.setChecked(false);
            image1105.setChecked(false);
            image1106.setChecked(false);
            image1107.setChecked(false);
            image1108.setChecked(false);
            image1109.setChecked(false);
            image1110.setChecked(false);
            image1111.setChecked(false);
            image1112.setChecked(false);
            image1113.setChecked(false);
            image1114.setChecked(false);
            image1115.setChecked(false);
            image1116.setChecked(false);
            image1117.setChecked(false);
            image1118.setChecked(false);
            image1119.setChecked(false); // 11
            image120.setChecked(false);
            image121.setChecked(false);
            image122.setChecked(false);
            image123.setChecked(false);
            image124.setChecked(false);
            image125.setChecked(false);
            image126.setChecked(false);
            image127.setChecked(false);
            image128.setChecked(false);
            image129.setChecked(false);
            image1210.setChecked(false);
            image1211.setChecked(false);
            image1212.setChecked(false);
            image1213.setChecked(false);
            image1214.setChecked(false);
            image1215.setChecked(false);
            image1216.setChecked(false);
            image1217.setChecked(false);
            image1218.setChecked(false);
            image1219.setChecked(false); // 12
            image130.setChecked(false);
            image131.setChecked(false);
            image132.setChecked(false);
            image133.setChecked(false);
            image134.setChecked(false);
            image135.setChecked(false);
            image136.setChecked(false);
            image137.setChecked(false);
            image138.setChecked(false);
            image139.setChecked(false);
            image1310.setChecked(false);
            image1311.setChecked(false);
            image1312.setChecked(false);
            image1313.setChecked(false);
            image1314.setChecked(false);
            image1315.setChecked(false);
            image1316.setChecked(false);
            image1317.setChecked(false);
            image1318.setChecked(false);
            image1319.setChecked(false); // 13
            image140.setChecked(false);
            image141.setChecked(false);
            image142.setChecked(false);
            image143.setChecked(false);
            image144.setChecked(false);
            image145.setChecked(false);
            image146.setChecked(false);
            image147.setChecked(false);
            image148.setChecked(false);
            image149.setChecked(false);
            image1410.setChecked(false);
            image1411.setChecked(false);
            image1412.setChecked(false);
            image1413.setChecked(false);
            image1414.setChecked(false);
            image1415.setChecked(false);
            image1416.setChecked(false);
            image1417.setChecked(false);
            image1418.setChecked(false);
            image1419.setChecked(false); // 14
            image150.setChecked(false);
            image151.setChecked(false);
            image152.setChecked(false);
            image153.setChecked(false);
            image154.setChecked(false);
            image155.setChecked(false);
            image156.setChecked(false);
            image157.setChecked(false);
            image158.setChecked(false);
            image159.setChecked(false);
            image1510.setChecked(false);
            image1511.setChecked(false);
            image1512.setChecked(false);
            image1513.setChecked(false);
            image1514.setChecked(false);
            image1515.setChecked(false);
            image1516.setChecked(false);
            image1517.setChecked(false);
            image1518.setChecked(false);
            image1519.setChecked(false); // 15
            image160.setChecked(false);
            image161.setChecked(false);
            image162.setChecked(false);
            image163.setChecked(false);
            image164.setChecked(false);
            image165.setChecked(false);
            image166.setChecked(false);
            image167.setChecked(false);
            image168.setChecked(false);
            image169.setChecked(false);
            image1610.setChecked(false);
            image1611.setChecked(false);
            image1612.setChecked(false);
            image1613.setChecked(false);
            image1614.setChecked(false);
            image1615.setChecked(false);
            image1616.setChecked(false);
            image1617.setChecked(false);
            image1618.setChecked(false);
            image1619.setChecked(false); // 16
            image170.setChecked(false);
            image171.setChecked(false);
            image172.setChecked(false);
            image173.setChecked(false);
            image174.setChecked(false);
            image175.setChecked(false);
            image176.setChecked(false);
            image177.setChecked(false);
            image178.setChecked(false);
            image179.setChecked(false);
            image1710.setChecked(false);
            image1711.setChecked(false);
            image1712.setChecked(false);
            image1713.setChecked(false);
            image1714.setChecked(false);
            image1715.setChecked(false);
            image1716.setChecked(false);
            image1717.setChecked(false);
            image1718.setChecked(false);
            image1719.setChecked(false); // 17
            image180.setChecked(false);
            image181.setChecked(false);
            image182.setChecked(false);
            image183.setChecked(false);
            image184.setChecked(false);
            image185.setChecked(false);
            image186.setChecked(false);
            image187.setChecked(false);
            image188.setChecked(false);
            image189.setChecked(false);
            image1810.setChecked(false);
            image1811.setChecked(false);
            image1812.setChecked(false);
            image1813.setChecked(false);
            image1814.setChecked(false);
            image1815.setChecked(false);
            image1816.setChecked(false);
            image1817.setChecked(false);
            image1818.setChecked(false);
            image1819.setChecked(false); // 18
            image190.setChecked(false);
            image191.setChecked(false);
            image192.setChecked(false);
            image193.setChecked(false);
            image194.setChecked(false);
            image195.setChecked(false);
            image196.setChecked(false);
            image197.setChecked(false);
            image198.setChecked(false);
            image199.setChecked(false);
            image1910.setChecked(false);
            image1911.setChecked(false);
            image1912.setChecked(false);
            image1913.setChecked(false);
            image1914.setChecked(false);
            image1915.setChecked(false);
            image1916.setChecked(false);
            image1917.setChecked(false);
            image1918.setChecked(false);
            image1919.setChecked(false); // 19

            for(int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    checkBox[i][j] = 0;
                }
            }
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show();
        }
        else{
            for(int i = 0; i < 20; i++){
                for(int j = 0; j < 20; j++) {
                    if (bwarr[i][j] != checkBox[i][j]) count++;
                }
            }
        }
        if(count == 0) Toast.makeText(this, "Finish!", Toast.LENGTH_LONG).show();
    }

    public void maketext(int[][] arr){
        row1 = findViewById(R.id.row1);
        row2 = findViewById(R.id.row2);
        row3 = findViewById(R.id.row3);
        row4 = findViewById(R.id.row4);
        row5 = findViewById(R.id.row5);
        row6 = findViewById(R.id.row6);
        row7 = findViewById(R.id.row7);
        row8 = findViewById(R.id.row8);
        row9 = findViewById(R.id.row9);
        row10 = findViewById(R.id.row10);
        row11 = findViewById(R.id.row11);
        row12 = findViewById(R.id.row12);
        row13 = findViewById(R.id.row13);
        row14 = findViewById(R.id.row14);
        row15 = findViewById(R.id.row15);
        row16 = findViewById(R.id.row16);
        row17 = findViewById(R.id.row17);
        row18 = findViewById(R.id.row18);
        row19 = findViewById(R.id.row19);
        row20 = findViewById(R.id.row20);
        col1 = findViewById(R.id.col1);
        col2 = findViewById(R.id.col2);
        col3 = findViewById(R.id.col3);
        col4 = findViewById(R.id.col4);
        col5 = findViewById(R.id.col5);
        col6 = findViewById(R.id.col6);
        col7 = findViewById(R.id.col7);
        col8 = findViewById(R.id.col8);
        col9 = findViewById(R.id.col9);
        col10 = findViewById(R.id.col10);
        col11 = findViewById(R.id.col11);
        col12 = findViewById(R.id.col12);
        col13 = findViewById(R.id.col13);
        col14 = findViewById(R.id.col14);
        col15 = findViewById(R.id.col15);
        col16 = findViewById(R.id.col16);
        col17 = findViewById(R.id.col17);
        col18 = findViewById(R.id.col18);
        col19 = findViewById(R.id.col19);
        col20 = findViewById(R.id.col20);
        
        long[] row = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        long[] col = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        long sum = 0;
        boolean zero = true;
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                if(bwarr[i][j] == 1) {
                    sum += bwarr[i][j];
                    zero = true;
                }
                else if(bwarr[i][j] == 0 && zero) {
                    sum *= 10;
                    zero = false;
                }
            }
            if(sum % 10 == 0 && !zero) sum /= 10;
            row[i] = sum;
            sum = 0;
            zero = true;
        }
        for(int j = 0; j < 20; j++){
            for(int i = 0; i < 20; i++){
                if(bwarr[i][j] == 1) {
                    sum += bwarr[i][j];
                    zero = true;
                }
                else if(bwarr[i][j] == 0 && zero) {
                    sum *= 10;
                    zero = false;
                }
            }
            if(sum % 10 == 0 && !zero) sum /= 10;
            col[j] = sum;
            sum = 0;
            zero = true;
        }
        row1.setText("" + row[0]);
        row2.setText("" + row[1]);
        row3.setText("" + row[2]);
        row4.setText("" + row[3]);
        row5.setText("" + row[4]);
        row6.setText("" + row[5]);
        row7.setText("" + row[6]);
        row8.setText("" + row[7]);
        row9.setText("" + row[8]);
        row10.setText("" + row[9]);
        row11.setText("" + row[10]);
        row12.setText("" + row[11]);
        row13.setText("" + row[12]);
        row14.setText("" + row[13]);
        row15.setText("" + row[14]);
        row16.setText("" + row[15]);
        row17.setText("" + row[16]);
        row18.setText("" + row[17]);
        row19.setText("" + row[18]);
        row20.setText("" + row[19]);

        col1.setText("" + col[0]);
        col2.setText("" + col[1]);
        col3.setText("" + col[2]);
        col4.setText("" + col[3]);
        col5.setText("" + col[4]);
        col6.setText("" + col[5]);
        col7.setText("" + col[6]);
        col8.setText("" + col[7]);
        col9.setText("" + col[8]);
        col10.setText("" + col[9]);
        col11.setText("" + col[10]);
        col12.setText("" + col[11]);
        col13.setText("" + col[12]);
        col14.setText("" + col[13]);
        col15.setText("" + col[14]);
        col16.setText("" + col[15]);
        col17.setText("" + col[16]);
        col18.setText("" + col[17]);
        col19.setText("" + col[18]);
        col20.setText("" + col[19]);
    }
}