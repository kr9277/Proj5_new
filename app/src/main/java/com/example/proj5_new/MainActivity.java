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
/** @author Created by karin on 3/10/2023.
 * @version 0.0
 * @since 3/10/2023
 *On this Activity, when we click on the button, a dialog box will open in which we will receive data, and then the members of the series will appear in a list view, and clicking on each member will show its position in the series and the amount from the first member up to it. And there is also a general menu that allows us to switch between screens and the dialog box.
 */

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
    Boolean bool1 = false; // boolean flag for the type of the series
    Boolean bool2 = false; // boolean flag for the first term of the series
    Boolean bool3 = false; // boolean flag for the difference/multiplier of the series
    Boolean bool4 = false; // boolean flag uses to check if the user clicked on item in the list view
    double[] arr = new double[20];
    String[] arr_string = new String[20];
    double eser=0; // sum

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
    /**
     * onCreateOptionsMenu method
     * <p> Creating the options menu
     * </p>
     *
     * @param menu the Menu object to pass to the inflater
     * @return true
     * adds another option to the menu which is go to the dialog box
     */
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.tafrit, menu);
        menu.add(0,0,500, "Dialog");
        return true;
    }
    /**
     * onOptionsItemSelected method
     * <p> Reacting the options menu
     * </p>
     *
     * @param item the MenuItem object that triggered by the listener
     * @return super.onOptionsItemSelected(item)
     */
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
        /**
         * onClick
         * <p> Reacting the buttons in the dialog
         * </p>
         *
         * @param dialogInterface, i the index of the pressed button
         */
        public void onClick(DialogInterface dialogInterface, int i) {
            if(i==DialogInterface.BUTTON_POSITIVE){
               inputs_check();
            }
            else if(i==DialogInterface.BUTTON_NEUTRAL){
                dialogInterface.cancel();
            }
        }
    };
    /**
     * go method
     * <p> Demonstrate custom alert dialog with 3 buttons(witch one of them I created)
     * </p>
     *
     * @param view the view that triggered the method
     */
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
    /**
     * inputs_check method
     * <p> the function checks if all the inputs are normal
     */
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
    /**
     * lv_full
     * <p> the function fulls the array with all the terms
     */
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