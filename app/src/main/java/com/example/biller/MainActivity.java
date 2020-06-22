package com.example.biller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    TextView tv1,tv2,tv3,curt;
    float totalVal;
    Button summary;
    String str="";
    private long backPressedTime ;
    private  Toast backToast;

    //Exit from the app if back pressed from home screen
    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis() )
        {
            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else
        {
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.settings,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.exit:
                MainActivity.super.onBackPressed();
                return true;
//            case R.id.update:
//                click("https://rohitsinghkcodes.github.io/EmerCall-Updates/");
//                return true;
            case R.id.about:
                Intent i =new Intent(MainActivity.this,about_section.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.plusSign);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        summary = findViewById(R.id.summary);
        curt = findViewById(R.id.curt);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.activity_alert_box,null);

                final EditText rate = (EditText) view.findViewById(R.id.rate);
                final EditText qty = (EditText) view.findViewById(R.id.qty);
                final EditText nm = (EditText) view.findViewById(R.id.name);

                 AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                 builder.setMessage("    INPUT YOUR ENTRIES")
                         .setView(view)
                         .setPositiveButton("Add Item", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {

                         //Entries calculation part
                         String r1 = rate.getText().toString();
                         String q1 = qty.getText().toString();
                         String name = nm.getText().toString();

                         float r = Float.parseFloat(r1);
                         int q = Integer.parseInt(q1);

                         float k = r*q;
                         tv1.setText("> "+k+" <");
                         totalVal+=k;

                         str+="\n" + name + ":    " + r1 + " X " + q1 + "  =  " + k;

                         tv3.setVisibility(View.VISIBLE);
                         curt.setVisibility(View.VISIBLE);
                         summary.setVisibility(View.VISIBLE);
                         tv2.setText(totalVal+"");
                     }
                 }).setNegativeButton("Calcel",null).setCancelable(false);

                 AlertDialog alert = builder.create();
                 alert.show();


            }
        });

        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,summary.class);
                intent.putExtra("detailSummary",str);
                startActivity(intent);
            }
        });

    }
}