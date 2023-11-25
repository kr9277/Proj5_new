package com.example.proj5_new;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
/** @author Created by karin on 3/10/2023.
 * @version 1.1
 * @since 22/11/2023
 *In this Activity there is  2 text View and a general menu that allows us to switch between screens.
 */

public class Credits_Activity extends AppCompatActivity implements View.OnCreateContextMenuListener {
    TextView tv1, tv2;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        tv1 = findViewById(R.id.tv_1);
        tv2 = findViewById(R.id.tv_2);
        iv = findViewById(R.id.iv);
        iv.setImageResource(R.drawable.img);
    }
    /**
     * onCreateOptionsMenu method
     * <p> Creating the options menu
     * </p>
     *
     * @param menu the Menu object to pass to the inflater
     * @return true
     */
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.tafrit, menu);
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
        return super.onOptionsItemSelected(item);
    }


}