package com.example.jahnavi.assignment3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class activity3 extends AppCompatActivity {
    private TextView tagTextView;
    private Button back;
    private Button view;
    private Button update;
    private Button delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);
        tagTextView=(TextView)findViewById(R.id.ResultView);
        back=(Button)findViewById(R.id.backButtonId);
        view=(Button)findViewById(R.id.ButtonView);
        update= (Button)findViewById(R.id.ButtonUpdate);
        delete=(Button)findViewById(R.id.ButtonDelete);
        execution();


    }

    public void execution() {


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        Database dbhelper=new Database(getApplicationContext());
          String s=dbhelper.retrievedata();
     tagTextView.setText(s);

            }

        }); // end of View Button Listener


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), update.class);
                startActivity(i);
                finish();
            }
    });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), delete.class);
                startActivity(i);
                finish();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();

            }

        }); // end of Back Button Listener

    }


}
