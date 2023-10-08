package com.example.proj5_new;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8;
    ListView lv;
    String kind;
    double a1;
    double delta;
    double[] arr = new double[20];
    String[] arr_string = new String[20];
    double eser=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        tv7 = findViewById(R.id.tv7);
        tv8 = findViewById(R.id.tv8);
        lv = (ListView) findViewById(R.id.lv);
        btn = findViewById(R.id.btn);
    }
    public void go(View view){
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        View view1 = LayoutInflater.from(this).inflate(R.layout.dialog, null);
        EditText et1, et2, et3;
        Button btn1, btn2, btn3;
        et1 = view1.findViewById(R.id.et1);
        et2 = view1.findViewById(R.id.et2);
        et3 = view1.findViewById(R.id.et3);
        btn1 = view1.findViewById(R.id.btn1);
        btn2 = view1.findViewById(R.id.btn2);
        btn3 = view1.findViewById(R.id.btn3);


        adb.setView(view1);
        adb.setNeutralButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        adb.show();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        return super.onCreateOptionsMenu(menu);
    }
    /**
     * The operation accepts as a variable parameter a menu bar type selected by the user
     * The action calls the sub-function and changes the color of the screen according to the selected option
     */

}