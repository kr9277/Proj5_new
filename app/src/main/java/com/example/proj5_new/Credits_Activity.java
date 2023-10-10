package com.example.proj5_new;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class Credits_Activity extends AppCompatActivity {
    TextView tv1, tv2;
    Intent intent = getIntent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
        tv1 = findViewById(R.id.tv_1);
        tv2 = findViewById(R.id.tv_2);
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
    public void go_main(){
        intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void go_credits(){
        intent = new Intent(this, Credits_Activity.class);
        startActivity(intent);
    }
}