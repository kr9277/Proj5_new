package com.example.proj5_new;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    AlertDialog.Builder adb;
    LinearLayout mydialog;
    Button btn;
    EditText et1, et2, et3;
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8;
    ListView lv;
    Intent intent = getIntent();
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
    DialogInterface.OnClickListener myclick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            if(i==DialogInterface.BUTTON_POSITIVE){
               kind = et1.getText().toString();
               a1 = Double.parseDouble(et2.getText().toString());
               delta = Double.parseDouble(et3.getText().toString());
               dialogInterface.dismiss();
            }
            if(i==DialogInterface.BUTTON_NEUTRAL){
                dialogInterface.cancel();
            }
            if(i==DialogInterface.BUTTON_NEGATIVE){
                et1.setText("");
                et2.setText("");
                et3.setText("");
                dialogInterface.dismiss();
            }
        }
    };
    public void go(View view){
        mydialog = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog, null);
        et1 = mydialog.findViewById(R.id.et1);
        et2 = mydialog.findViewById(R.id.et2);
        et3 = mydialog.findViewById(R.id.et3);
        adb = new AlertDialog.Builder(this);
        adb.setView(mydialog);
        adb.setTitle("Inputs");
        adb.setMessage("Enter the following data: ");
        adb.setPositiveButton("Inputs",myclick);
        adb.setNeutralButton("Cancel",myclick);
        adb.setNegativeButton("Delete",myclick);
        adb.show();
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.tafrit, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        String str = item.getTitle().toString();
        if(str.equals("Main Activity")){
            go_main();
        }
        else if(str.equals("Credits Activity")){
            go_credits();
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * The operation accepts as a variable parameter a menu bar type selected by the user
     * The action calls the sub-function and changes the color of the screen according to the selected option
     */
    public void go_main(){
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void go_credits(){
        intent = new Intent(this, Credits_Activity.class);
        startActivity(intent);
    }

}