package com.example.jahnavi.assignment3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class activity2 extends AppCompatActivity {

    private TextView name;
    private  TextView roll;
    private TextView sem;
    private Button back;
    private Button load;
    private String d="N/A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);
        name=(TextView)findViewById(R.id.TextViewName);
        roll=(TextView)findViewById(R.id.TextViewroll);
        sem=(TextView)findViewById(R.id.TextViewSem);
        back=(Button)findViewById(R.id.ButtonBack);
        load=(Button)findViewById(R.id.ButtonLoad);

        execution();
    }

    public void execution()
    {
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SharedPreferences sp=getSharedPreferences("User_Data", Context.MODE_PRIVATE);
                String Name=sp.getString("Name",d);
                String Roll=sp.getString("Roll",d);
                String Sem=sp.getString("Semester",d);

                if(Name.equals(d)||Roll.equals(d)||Sem.equals(d))
                {
                    Toast.makeText(getApplicationContext(),"No data found",Toast.LENGTH_SHORT).show();
                }

                else
                {
                    name.setText(Name);
                    roll.setText(Roll);
                    sem.setText(Sem);
                    Toast.makeText(getApplicationContext()," Data retrieved using Shared Preferences successfully!",Toast.LENGTH_LONG).show();
                }

            }

    }); // end of Load Button Listener


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();

            }

        }); // end of Back Button Listener



    }// end of Execution Method

}// end of MAin class

