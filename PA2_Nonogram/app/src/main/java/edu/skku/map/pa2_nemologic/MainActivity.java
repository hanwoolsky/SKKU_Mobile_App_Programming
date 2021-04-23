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
    private ImageView imageView, BWimage;

    private Button gbtn, sbtn;
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

    private TextView row1, row2, row3, row4, row5, row6, row7, row8, row9, row10, row11, row12, row13, row14, row15, row16, row17, row18, row19, row20;
    private TextView col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12, col13, col14, col15, col16, col17, col18, col19, col20;

    Bitmap Image[][] = new Bitmap[20][20];
    Bitmap resize;
    int piecewidth;
    int pieceheight;

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

    int[][] checkBox = {
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

    @SuppressLint("IntentReset")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gbtn = findViewById(R.id.gallery);
        sbtn = findViewById(R.id.search);
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

        String clientID = "wSnG2lnF9KbHWGwCh5za";
        String clientSecret = "QRbaydlYCFQRbaydlYCF";

        gbtn.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
            intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, REQUEST_CODE);
        });

        image00.setOnClickListener(v -> {
            if(image00.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image01.setOnClickListener(v -> {
            if(image01.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image02.setOnClickListener(v -> {
            if(image02.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image03.setOnClickListener(v -> {
            if(image03.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image04.setOnClickListener(v -> {
            if(image04.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image05.setOnClickListener(v -> {
            if(image05.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image06.setOnClickListener(v -> {
            if(image06.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image07.setOnClickListener(v -> {
            if(image07.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image08.setOnClickListener(v -> {
            if(image08.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image09.setOnClickListener(v -> {
            if(image09.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });
        image010.setOnClickListener(v -> {
            if(image010.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image011.setOnClickListener(v -> {
            if(image011.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image012.setOnClickListener(v -> {
            if(image012.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image013.setOnClickListener(v -> {
            if(image013.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image014.setOnClickListener(v -> {
            if(image014.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image015.setOnClickListener(v -> {
            if(image015.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image016.setOnClickListener(v -> {
            if(image016.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image017.setOnClickListener(v -> {
            if(image017.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image018.setOnClickListener(v -> {
            if (image018.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });
        
        image019.setOnClickListener(v -> {
            if(image019.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        }); // 0

        image10.setOnClickListener(v -> {
            if(image10.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image11.setOnClickListener(v -> {
            if(image11.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image12.setOnClickListener(v -> {
            if(image12.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image13.setOnClickListener(v -> {
            if(image13.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image14.setOnClickListener(v -> {
            if(image14.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image15.setOnClickListener(v -> {
            if(image15.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image16.setOnClickListener(v -> {
            if(image16.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image17.setOnClickListener(v -> {
            if(image17.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image18.setOnClickListener(v -> {
            if(image18.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image19.setOnClickListener(v -> {
            if(image19.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });
        image110.setOnClickListener(v -> {
            if(image110.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image111.setOnClickListener(v -> {
            if(image111.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image112.setOnClickListener(v -> {
            if(image112.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image113.setOnClickListener(v -> {
            if(image113.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image114.setOnClickListener(v -> {
            if(image114.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image115.setOnClickListener(v -> {
            if(image115.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image116.setOnClickListener(v -> {
            if(image116.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image117.setOnClickListener(v -> {
            if(image117.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image118.setOnClickListener(v -> {
            if(image118.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image119.setOnClickListener(v -> {
            if(image119.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        }); // 1

        image20.setOnClickListener(v -> {
            if(image20.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image21.setOnClickListener(v -> {
            if(image21.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image22.setOnClickListener(v -> {
            if(image22.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image23.setOnClickListener(v -> {
            if(image23.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image24.setOnClickListener(v -> {
            if(image24.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image25.setOnClickListener(v -> {
            if(image25.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image26.setOnClickListener(v -> {
            if(image26.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image27.setOnClickListener(v -> {
            if(image27.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image28.setOnClickListener(v -> {
            if(image28.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image29.setOnClickListener(v -> {
            if(image29.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });
        image210.setOnClickListener(v -> {
            if(image210.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image211.setOnClickListener(v -> {
            if(image211.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image212.setOnClickListener(v -> {
            if(image212.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image213.setOnClickListener(v -> {
            if(image213.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image214.setOnClickListener(v -> {
            if(image214.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image215.setOnClickListener(v -> {
            if(image215.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image216.setOnClickListener(v -> {
            if(image216.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image217.setOnClickListener(v -> {
            if(image217.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image218.setOnClickListener(v -> {
            if(image218.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image219.setOnClickListener(v -> {
            if(image219.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        }); // 2
        image30.setOnClickListener(v -> {
            if(image30.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image31.setOnClickListener(v -> {
            if(image31.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image32.setOnClickListener(v -> {
            if(image32.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image33.setOnClickListener(v -> {
            if(image33.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image34.setOnClickListener(v -> {
            if(image34.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image35.setOnClickListener(v -> {
            if(image35.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image36.setOnClickListener(v -> {
            if(image36.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image37.setOnClickListener(v -> {
            if(image37.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image38.setOnClickListener(v -> {
            if(image38.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image39.setOnClickListener(v -> {
            if(image39.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });
        image310.setOnClickListener(v -> {
            if(image310.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image311.setOnClickListener(v -> {
            if(image311.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image312.setOnClickListener(v -> {
            if(image312.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image313.setOnClickListener(v -> {
            if(image313.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image314.setOnClickListener(v -> {
            if(image314.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image315.setOnClickListener(v -> {
            if(image315.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image316.setOnClickListener(v -> {
            if(image316.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image317.setOnClickListener(v -> {
            if(image317.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image318.setOnClickListener(v -> {
            if(image318.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image319.setOnClickListener(v -> {
            if(image319.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        }); // 3

        image40.setOnClickListener(v -> {
            if(image40.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image41.setOnClickListener(v -> {
            if(image41.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image42.setOnClickListener(v -> {
            if(image42.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image43.setOnClickListener(v -> {
            if(image43.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image44.setOnClickListener(v -> {
            if(image44.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image45.setOnClickListener(v -> {
            if(image45.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image46.setOnClickListener(v -> {
            if(image46.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image47.setOnClickListener(v -> {
            if(image47.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image48.setOnClickListener(v -> {
            if(image48.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image49.setOnClickListener(v -> {
            if(image49.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });
        image410.setOnClickListener(v -> {
            if(image410.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image411.setOnClickListener(v -> {
            if(image411.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image412.setOnClickListener(v -> {
            if(image412.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image413.setOnClickListener(v -> {
            if(image413.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image414.setOnClickListener(v -> {
            if(image414.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image415.setOnClickListener(v -> {
            if(image415.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image416.setOnClickListener(v -> {
            if(image416.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image417.setOnClickListener(v -> {
            if(image417.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image418.setOnClickListener(v -> {
            if(image418.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image419.setOnClickListener(v -> {
            if(image419.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        }); // 4

        image50.setOnClickListener(v -> {
            if(image50.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image51.setOnClickListener(v -> {
            if(image51.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image52.setOnClickListener(v -> {
            if(image52.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image53.setOnClickListener(v -> {
            if(image53.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image54.setOnClickListener(v -> {
            if(image54.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image55.setOnClickListener(v -> {
            if(image55.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image56.setOnClickListener(v -> {
            if(image56.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image57.setOnClickListener(v -> {
            if(image57.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image58.setOnClickListener(v -> {
            if(image58.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image59.setOnClickListener(v -> {
            if(image59.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });
        image510.setOnClickListener(v -> {
            if(image510.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image511.setOnClickListener(v -> {
            if(image511.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image512.setOnClickListener(v -> {
            if(image512.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image513.setOnClickListener(v -> {
            if(image513.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image514.setOnClickListener(v -> {
            if(image514.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image515.setOnClickListener(v -> {
            if(image515.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image516.setOnClickListener(v -> {
            if(image516.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image517.setOnClickListener(v -> {
            if(image517.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image518.setOnClickListener(v -> {
            if(image518.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image519.setOnClickListener(v -> {
            if(image519.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        }); // 5

        image60.setOnClickListener(v -> {
            if(image60.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image61.setOnClickListener(v -> {
            if(image61.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image62.setOnClickListener(v -> {
            if(image62.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image63.setOnClickListener(v -> {
            if(image63.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image64.setOnClickListener(v -> {
            if(image64.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image65.setOnClickListener(v -> {
            if(image65.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image66.setOnClickListener(v -> {
            if(image66.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image67.setOnClickListener(v -> {
            if(image67.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image68.setOnClickListener(v -> {
            if(image68.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image69.setOnClickListener(v -> {
            if(image69.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });
        image610.setOnClickListener(v -> {
            if(image610.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image611.setOnClickListener(v -> {
            if(image611.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image612.setOnClickListener(v -> {
            if(image612.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image613.setOnClickListener(v -> {
            if(image613.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image614.setOnClickListener(v -> {
            if(image614.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image615.setOnClickListener(v -> {
            if(image615.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image616.setOnClickListener(v -> {
            if(image616.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image617.setOnClickListener(v -> {
            if(image617.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image618.setOnClickListener(v -> {
            if(image618.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image619.setOnClickListener(v -> {
            if(image619.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        }); // 6

        image70.setOnClickListener(v -> {
            if(image70.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image71.setOnClickListener(v -> {
            if(image71.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image72.setOnClickListener(v -> {
            if(image72.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image73.setOnClickListener(v -> {
            if(image73.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image74.setOnClickListener(v -> {
            if(image74.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image75.setOnClickListener(v -> {
            if(image75.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image76.setOnClickListener(v -> {
            if(image76.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image77.setOnClickListener(v -> {
            if(image77.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image78.setOnClickListener(v -> {
            if(image78.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image79.setOnClickListener(v -> {
            if(image79.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });
        image710.setOnClickListener(v -> {
            if(image710.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image711.setOnClickListener(v -> {
            if(image711.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image712.setOnClickListener(v -> {
            if(image712.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image713.setOnClickListener(v -> {
            if(image713.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image714.setOnClickListener(v -> {
            if(image714.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image715.setOnClickListener(v -> {
            if(image715.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image716.setOnClickListener(v -> {
            if(image716.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image717.setOnClickListener(v -> {
            if(image717.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image718.setOnClickListener(v -> {
            if(image718.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image719.setOnClickListener(v -> {
            if(image719.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        }); // 7

        image80.setOnClickListener(v -> {
            if(image80.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image81.setOnClickListener(v -> {
            if(image81.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image82.setOnClickListener(v -> {
            if(image82.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image83.setOnClickListener(v -> {
            if(image83.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image84.setOnClickListener(v -> {
            if(image84.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image85.setOnClickListener(v -> {
            if(image85.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image86.setOnClickListener(v -> {
            if(image86.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image87.setOnClickListener(v -> {
            if(image87.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image88.setOnClickListener(v -> {
            if(image88.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image89.setOnClickListener(v -> {
            if(image89.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });
        image810.setOnClickListener(v -> {
            if(image810.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image811.setOnClickListener(v -> {
            if(image811.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image812.setOnClickListener(v -> {
            if(image812.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image813.setOnClickListener(v -> {
            if(image813.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image814.setOnClickListener(v -> {
            if(image814.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image815.setOnClickListener(v -> {
            if(image815.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image816.setOnClickListener(v -> {
            if(image816.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image817.setOnClickListener(v -> {
            if(image817.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image818.setOnClickListener(v -> {
            if(image818.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image819.setOnClickListener(v -> {
            if(image819.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        }); // 8

        image90.setOnClickListener(v -> {
            if(image90.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image91.setOnClickListener(v -> {
            if(image91.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image92.setOnClickListener(v -> {
            if(image92.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image93.setOnClickListener(v -> {
            if(image93.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image94.setOnClickListener(v -> {
            if(image94.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image95.setOnClickListener(v -> {
            if(image95.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image96.setOnClickListener(v -> {
            if(image96.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image97.setOnClickListener(v -> {
            if(image97.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image98.setOnClickListener(v -> {
            if(image98.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image99.setOnClickListener(v -> {
            if(image99.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });
        image910.setOnClickListener(v -> {
            if(image910.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image911.setOnClickListener(v -> {
            if(image911.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image912.setOnClickListener(v -> {
            if(image912.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image913.setOnClickListener(v -> {
            if(image913.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image914.setOnClickListener(v -> {
            if(image914.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image915.setOnClickListener(v -> {
            if(image915.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image916.setOnClickListener(v -> {
            if(image916.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image917.setOnClickListener(v -> {
            if(image917.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image918.setOnClickListener(v -> {
            if(image918.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image919.setOnClickListener(v -> {
            if(image919.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        }); // 9

        image100.setOnClickListener(v -> {
            if(image100.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image101.setOnClickListener(v -> {
            if(image101.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image102.setOnClickListener(v -> {
            if(image102.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image103.setOnClickListener(v -> {
            if(image103.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image104.setOnClickListener(v -> {
            if(image104.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image105.setOnClickListener(v -> {
            if(image105.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image106.setOnClickListener(v -> {
            if(image106.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image107.setOnClickListener(v -> {
            if(image107.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image108.setOnClickListener(v -> {
            if(image108.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image109.setOnClickListener(v -> {
            if(image109.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });
        image1010.setOnClickListener(v -> {
            if(image1010.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1011.setOnClickListener(v -> {
            if(image1011.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1012.setOnClickListener(v -> {
            if(image1012.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1013.setOnClickListener(v -> {
            if(image1013.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1014.setOnClickListener(v -> {
            if(image1014.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1015.setOnClickListener(v -> {
            if(image1015.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1016.setOnClickListener(v -> {
            if(image1016.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1017.setOnClickListener(v -> {
            if(image1017.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1018.setOnClickListener(v -> {
            if (image1018.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1019.setOnClickListener(v -> {
            if(image1019.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        }); // 10

        image1100.setOnClickListener(v -> {
            if(image1100.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1101.setOnClickListener(v -> {
            if(image1101.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1102.setOnClickListener(v -> {
            if(image1102.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1103.setOnClickListener(v -> {
            if(image1103.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1104.setOnClickListener(v -> {
            if(image1104.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1105.setOnClickListener(v -> {
            if(image1105.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1106.setOnClickListener(v -> {
            if(image1106.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1107.setOnClickListener(v -> {
            if(image1107.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1108.setOnClickListener(v -> {
            if(image1108.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1109.setOnClickListener(v -> {
            if(image1109.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });
        image1110.setOnClickListener(v -> {
            if(image1110.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1111.setOnClickListener(v -> {
            if(image1111.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1112.setOnClickListener(v -> {
            if(image1112.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1113.setOnClickListener(v -> {
            if(image1113.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1114.setOnClickListener(v -> {
            if(image1114.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1115.setOnClickListener(v -> {
            if(image1115.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1116.setOnClickListener(v -> {
            if(image1116.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1117.setOnClickListener(v -> {
            if(image1117.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1118.setOnClickListener(v -> {
            if(image1118.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1119.setOnClickListener(v -> {
            if(image1119.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        }); // 11

        image120.setOnClickListener(v -> {
            if(image120.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image121.setOnClickListener(v -> {
            if(image121.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image122.setOnClickListener(v -> {
            if(image122.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image123.setOnClickListener(v -> {
            if(image123.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image124.setOnClickListener(v -> {
            if(image124.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image125.setOnClickListener(v -> {
            if(image125.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image126.setOnClickListener(v -> {
            if(image126.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image127.setOnClickListener(v -> {
            if(image127.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image128.setOnClickListener(v -> {
            if(image128.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image129.setOnClickListener(v -> {
            if(image129.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });
        image1210.setOnClickListener(v -> {
            if(image1210.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1211.setOnClickListener(v -> {
            if(image1211.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1212.setOnClickListener(v -> {
            if(image1212.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1213.setOnClickListener(v -> {
            if(image1213.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1214.setOnClickListener(v -> {
            if(image1214.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1215.setOnClickListener(v -> {
            if(image1215.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1216.setOnClickListener(v -> {
            if(image1216.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1217.setOnClickListener(v -> {
            if(image1217.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1218.setOnClickListener(v -> {
            if(image1218.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1219.setOnClickListener(v -> {
            if(image1219.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        }); // 12
        image130.setOnClickListener(v -> {
            if(image130.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image131.setOnClickListener(v -> {
            if(image131.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image132.setOnClickListener(v -> {
            if(image132.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image133.setOnClickListener(v -> {
            if(image133.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image134.setOnClickListener(v -> {
            if(image134.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image135.setOnClickListener(v -> {
            if(image135.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image136.setOnClickListener(v -> {
            if(image136.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image137.setOnClickListener(v -> {
            if(image137.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image138.setOnClickListener(v -> {
            if(image138.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image139.setOnClickListener(v -> {
            if(image139.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });
        image1310.setOnClickListener(v -> {
            if(image1310.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1311.setOnClickListener(v -> {
            if(image1311.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1312.setOnClickListener(v -> {
            if(image1312.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1313.setOnClickListener(v -> {
            if(image1313.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1314.setOnClickListener(v -> {
            if(image1314.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1315.setOnClickListener(v -> {
            if(image1315.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1316.setOnClickListener(v -> {
            if(image1316.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1317.setOnClickListener(v -> {
            if(image1317.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1318.setOnClickListener(v -> {
            if(image1318.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1319.setOnClickListener(v -> {
            if(image1319.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        }); // 13

        image140.setOnClickListener(v -> {
            if(image140.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image141.setOnClickListener(v -> {
            if(image141.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image142.setOnClickListener(v -> {
            if(image142.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image143.setOnClickListener(v -> {
            if(image143.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image144.setOnClickListener(v -> {
            if(image144.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image145.setOnClickListener(v -> {
            if(image145.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image146.setOnClickListener(v -> {
            if(image146.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image147.setOnClickListener(v -> {
            if(image147.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image148.setOnClickListener(v -> {
            if(image148.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image149.setOnClickListener(v -> {
            if(image149.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });
        image1410.setOnClickListener(v -> {
            if(image1410.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1411.setOnClickListener(v -> {
            if(image1411.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1412.setOnClickListener(v -> {
            if(image1412.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1413.setOnClickListener(v -> {
            if(image1413.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1414.setOnClickListener(v -> {
            if(image1414.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1415.setOnClickListener(v -> {
            if(image1415.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1416.setOnClickListener(v -> {
            if(image1416.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1417.setOnClickListener(v -> {
            if(image1417.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1418.setOnClickListener(v -> {
            if(image1418.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1419.setOnClickListener(v -> {
            if(image1419.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        }); // 14

        image150.setOnClickListener(v -> {
            if(image150.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image151.setOnClickListener(v -> {
            if(image151.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image152.setOnClickListener(v -> {
            if(image152.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image153.setOnClickListener(v -> {
            if(image153.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image154.setOnClickListener(v -> {
            if(image154.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image155.setOnClickListener(v -> {
            if(image155.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image156.setOnClickListener(v -> {
            if(image156.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image157.setOnClickListener(v -> {
            if(image157.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image158.setOnClickListener(v -> {
            if(image158.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image159.setOnClickListener(v -> {
            if(image159.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });
        image1510.setOnClickListener(v -> {
            if(image1510.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1511.setOnClickListener(v -> {
            if(image1511.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1512.setOnClickListener(v -> {
            if(image1512.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1513.setOnClickListener(v -> {
            if(image1513.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1514.setOnClickListener(v -> {
            if(image1514.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1515.setOnClickListener(v -> {
            if(image1515.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1516.setOnClickListener(v -> {
            if(image1516.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1517.setOnClickListener(v -> {
            if(image1517.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1518.setOnClickListener(v -> {
            if(image1518.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1519.setOnClickListener(v -> {
            if(image1519.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        }); // 15

        image160.setOnClickListener(v -> {
            if(image160.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image161.setOnClickListener(v -> {
            if(image161.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image162.setOnClickListener(v -> {
            if(image162.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image163.setOnClickListener(v -> {
            if(image163.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image164.setOnClickListener(v -> {
            if(image164.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image165.setOnClickListener(v -> {
            if(image165.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image166.setOnClickListener(v -> {
            if(image166.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image167.setOnClickListener(v -> {
            if(image167.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image168.setOnClickListener(v -> {
            if(image168.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image169.setOnClickListener(v -> {
            if(image169.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });
        image1610.setOnClickListener(v -> {
            if(image1610.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1611.setOnClickListener(v -> {
            if(image1611.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1612.setOnClickListener(v -> {
            if(image1612.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1613.setOnClickListener(v -> {
            if(image1613.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1614.setOnClickListener(v -> {
            if(image1614.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1615.setOnClickListener(v -> {
            if(image1615.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1616.setOnClickListener(v -> {
            if(image1616.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1617.setOnClickListener(v -> {
            if(image1617.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1618.setOnClickListener(v -> {
            if(image1618.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1619.setOnClickListener(v -> {
            if(image1619.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        }); // 16

        image170.setOnClickListener(v -> {
            if(image170.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image171.setOnClickListener(v -> {
            if(image171.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image172.setOnClickListener(v -> {
            if(image172.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image173.setOnClickListener(v -> {
            if(image173.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image174.setOnClickListener(v -> {
            if(image174.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image175.setOnClickListener(v -> {
            if(image175.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image176.setOnClickListener(v -> {
            if(image176.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image177.setOnClickListener(v -> {
            if(image177.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image178.setOnClickListener(v -> {
            if(image178.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image179.setOnClickListener(v -> {
            if(image179.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });
        image1710.setOnClickListener(v -> {
            if(image1710.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1711.setOnClickListener(v -> {
            if(image1711.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1712.setOnClickListener(v -> {
            if(image1712.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1713.setOnClickListener(v -> {
            if(image1713.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1714.setOnClickListener(v -> {
            if(image1714.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1715.setOnClickListener(v -> {
            if(image1715.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1716.setOnClickListener(v -> {
            if(image1716.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1717.setOnClickListener(v -> {
            if(image1717.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1718.setOnClickListener(v -> {
            if(image1718.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1719.setOnClickListener(v -> {
            if(image1719.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        }); // 17

        image180.setOnClickListener(v -> {
            if(image180.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image181.setOnClickListener(v -> {
            if(image181.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image182.setOnClickListener(v -> {
            if(image182.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image183.setOnClickListener(v -> {
            if(image183.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image184.setOnClickListener(v -> {
            if(image184.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image185.setOnClickListener(v -> {
            if(image185.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image186.setOnClickListener(v -> {
            if(image186.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image187.setOnClickListener(v -> {
            if(image187.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image188.setOnClickListener(v -> {
            if(image188.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image189.setOnClickListener(v -> {
            if(image189.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });
        image1810.setOnClickListener(v -> {
            if(image1810.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1811.setOnClickListener(v -> {
            if(image1811.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1812.setOnClickListener(v -> {
            if(image1812.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1813.setOnClickListener(v -> {
            if(image1813.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1814.setOnClickListener(v -> {
            if(image1814.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1815.setOnClickListener(v -> {
            if(image1815.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1816.setOnClickListener(v -> {
            if(image1816.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1817.setOnClickListener(v -> {
            if(image1817.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1818.setOnClickListener(v -> {
            if(image1818.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1819.setOnClickListener(v -> {
            if(image1819.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        }); // 18

        image190.setOnClickListener(v -> {
            if(image190.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image191.setOnClickListener(v -> {
            if(image191.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image192.setOnClickListener(v -> {
            if(image192.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image193.setOnClickListener(v -> {
            if(image193.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image194.setOnClickListener(v -> {
            if(image194.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image195.setOnClickListener(v -> {
            if(image195.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image196.setOnClickListener(v -> {
            if(image196.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image197.setOnClickListener(v -> {
            if(image197.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image198.setOnClickListener(v -> {
            if(image198.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image199.setOnClickListener(v -> {
            if(image199.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });
        image1910.setOnClickListener(v -> {
            if(image1910.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1911.setOnClickListener(v -> {
            if(image1911.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1912.setOnClickListener(v -> {
            if(image1912.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1913.setOnClickListener(v -> {
            if(image1913.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1914.setOnClickListener(v -> {
            if(image1914.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1915.setOnClickListener(v -> {
            if(image1915.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1916.setOnClickListener(v -> {
            if(image1916.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1917.setOnClickListener(v -> {
            if(image1917.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1918.setOnClickListener(v -> {
            if(image1918.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        });

        image1919.setOnClickListener(v -> {
            if(image1919.isChecked()) checkBox[0][0] = 1;
            else checkBox[0][0] = 0;
            check();
        }); // 19

    }
/*
        sbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                EditText text = findViewById(R.id.editTextTextPersonName);
                String Search = text.getText().toString();
                String apiURL = "https://openapi.naver.com/v1/search/image?query=" + text;

                Map<String, String> requestHeaders = new HashMap<>();
                requestHeaders.put("X-Naver-Client-Id", clientID);
                requestHeaders.put("X-Naver-Client-Secret", clientSecret);

            }
        });



                //OkHttpClient client = new OkHttpClient();
                //HttpUrl.Builder urlBuilder = HttpUrl.parse(apiURL).newBuilder();
                //urlBuilder.addQueryParameter("query", Search);

                //String url = urlBuilder.build().toString();

                //Request req = new Request.Builder().url(url).build();
                //client.newCall(req).enqueue(new Callback() {
                    //@Override
                    //public void onFailure(@NotNull okhttp3.Call call, @NotNull IOException e) {
                        //e.printStackTrace();
                    //}

                    //@Override
                    //public void onResponse(@NotNull okhttp3.Call call, @NotNull Response response) throws IOException {
                        //final String myResponse = response.body().string();
                        //Gson gson = new GsonBuilder().create();
                        //final DataModel data = gson.fromJson(myResponse, DataModel.class);

                        //MainActivity.this.runOnUiThread(new Runnable() {
                            //@SuppressLint("SetTextI18n")
                            //@Override
                            //public void run() {
                                //textView.setText("Title : " + data.getDisplay());
                            //}
                        //});
                    //}
                //});

    }
 */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        imageView = findViewById(R.id.image);
        BWimage = findViewById(R.id.blackwhite);
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

                    resize = Bitmap.createScaledBitmap(img, imgWidth, imgWidth, false);

                    for(int i = 0; i < 20; i++) {
                        for (int j = 0; j < 20; j++) {
                            Image[i][j] = Bitmap.createBitmap(resize, j * piecewidth, i * pieceheight, piecewidth, pieceheight);
                        }
                    }
                    imageView.setImageBitmap(resize);
                    BWimage.setImageBitmap(grayScale(resize));

                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "cancel", Toast.LENGTH_LONG).show();
            }
        }
    }

    protected Bitmap grayScale(final Bitmap orgBitmap){
        int width = orgBitmap.getWidth();
        int height = orgBitmap.getHeight();
        int R = 0; int G = 0; int B = 0;
        int pixel;
        int i, j, x, y;
        piecewidth = width / 20;
        pieceheight = height / 20;

        Bitmap bmpGrayScale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);

        for(i = 0; i < 20; i++) {
            for (j = 0; j < 20; j++) {
                for (x = piecewidth*j; x < piecewidth*(j+1); ++x) {
                    for (y = pieceheight*i; y < pieceheight*(i+1); ++y) {
                        // get pixel color
                        pixel = orgBitmap.getPixel(x, y);
                        R += Color.red(pixel);
                        G += Color.green(pixel);
                        B += Color.blue(pixel);
                    }
                }
                int gray = (int) (0.3 * R + 0.3 * G + 0.3 * B) / (piecewidth * pieceheight);
                if (gray > 128) {
                    bwarr[i][j] = 0;
                }
                else {
                    bwarr[i][j] = 1;
                }
                R = 0; G = 0; B = 0;
            }
        }
        return bmpGrayScale;
    }

    public void check(){
        //배열 같은지 체크
    }
}