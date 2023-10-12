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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnCreateContextMenuListener {
    AlertDialog.Builder adb;
    LinearLayout mydialog;
    Button btn;
    EditText et1, et2, et3;
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8;
    ListView lv;
    String kind;
    double a1;
    double delta;
    Boolean bool1 = false;
    Boolean bool2 = false;
    Boolean bool3 = false;
    Boolean bool4 = false;
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
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        btn = findViewById(R.id.btn);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(bool4) {
                    int n = i + 1;
                    tv7.setText(String.valueOf((n)));
                    if(kind.equals("Invoicing")){
                        eser = (n * (2*a1 + delta*(n-1)))/2;
                    }
                    else{
                        eser = a1 * (Math.pow(delta, n)-1)/(delta-1);
                    }
                    tv8.setText(String.valueOf((eser)));
                }
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.tafrit, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        String str = item.getTitle().toString();
        if(str.equals("Main Activity")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if(str.equals("Credits Activity")){
            Intent intent = new Intent(this, Credits_Activity.class);
            startActivity(intent);
        }
        else if(str.equals("Dialog")){
            go(item.getActionView());
        }
        return super.onOptionsItemSelected(item);
    }
    DialogInterface.OnClickListener myclick = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            if(i==DialogInterface.BUTTON_POSITIVE){

               inputs_check();
            }
            if(i==DialogInterface.BUTTON_NEUTRAL){
                dialogInterface.cancel();
            }

        }
    };
    public void go(View view){
        mydialog = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog, null);
        et1 = mydialog.findViewById(R.id.et1);
        et2 = mydialog.findViewById(R.id.et2);
        et3 = mydialog.findViewById(R.id.et3);
        Button deleteInputBtn = mydialog.findViewById(R.id.deleteInput);
        deleteInputBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    et1.setText("");
                    et2.setText("");
                    et3.setText("");
                    Toast.makeText(MainActivity.this,"Button negative",Toast.LENGTH_SHORT).show();
            }
        });
        adb = new AlertDialog.Builder(this);
        adb.setView(mydialog);
        adb.setTitle("Inputs");
        adb.setMessage("Enter the following data: ");
        adb.setPositiveButton("Inputs",myclick);
        adb.setNeutralButton("Cancel",myclick);
        adb.show();
    }
    public void inputs_check(){
        if((et1.getText().toString().equals("")==false) && (et2.getText().toString().equals("")==false) && (et3.getText().toString().equals("")==false)){
            kind = et1.getText().toString();
            a1 = Double.parseDouble(et2.getText().toString());
            delta = Double.parseDouble(et3.getText().toString());
            if(kind.equals("Invoicing")==true || kind.equals("Engineering")==true){
                bool1 = true;
            }
            else {
                Toast.makeText(this, "you need to choose between Invoicing or Engineering", Toast.LENGTH_SHORT).show();
                kind = et1.getText().toString();
                bool4 = false;
            }
            if(a1>-1000000.0 && a1<1000000.0){
                bool2=true;
            }
            else{
                Toast.makeText(this, "you need to choose a number between -1000000 to 1000000", Toast.LENGTH_SHORT).show();
                a1 = Double.parseDouble(et2.getText().toString());
                bool4 = false;
            }
            if(delta>-1000000.0 && delta<1000000.0){
                bool3=true;
            }
            else{
                Toast.makeText(this, "you need to choose a number between -1000000 to 1000000", Toast.LENGTH_SHORT).show();
                delta = Double.parseDouble(et3.getText().toString());
                bool4 = false;
            }
            if(bool1 && bool2 && bool3){
                lv_full();
            }
        }
        else{
            Toast.makeText(this, "you did not enter everything requested", Toast.LENGTH_SHORT).show();
        }
    }
    public void lv_full(){
        arr[0]=a1;
        arr_string[0]= String.valueOf(a1);
        if(kind.equals("Invoicing")){
            for(int i=1;i<20;i++){
                arr[i]=arr[i-1]+delta;
                arr_string[i]= String.valueOf(arr[i]);
            }
        }
        else{
            for(int i=1;i<20;i++){
                arr[i]=arr[i-1]*delta;
                arr_string[i]=String.valueOf(arr[i]);
            }
        }
        tv5.setText(arr_string[0]);
        tv6.setText(String.valueOf((delta)));
        bool4=true;
        ArrayAdapter<String> adp = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, arr_string);
        lv.setAdapter(adp);
        bool1=false;
        bool2=false;
        bool3=false;
    }
}