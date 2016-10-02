package com.example.jahnavi.assignment3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class activity4 extends AppCompatActivity {
    private TextView name;
    private TextView roll;
    private TextView sem;
    private Button back;
    private Button load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4);

        name = (TextView) findViewById(R.id.TextViewName);
        roll = (TextView) findViewById(R.id.TextViewroll);
        sem = (TextView) findViewById(R.id.TextViewSem);
        back = (Button) findViewById(R.id.ButtonBack);
        load = (Button) findViewById(R.id.ButtonLoad);
        execution();
    }

    public void execution() {
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileInputStream fis=null;
                try
                {
                   fis=openFileInput("student_data.txt");
                    int read=-1;
                    StringBuffer buffer=new StringBuffer();
                    while((read=fis.read())!=-1)
                    {
                        buffer.append((char)read);
                    }
                    String text1=buffer.substring(0,buffer.indexOf(" "));
                    String text2=buffer.substring(buffer.indexOf(" ")+1,buffer.indexOf("."));
                    String text3=buffer.substring(buffer.indexOf(".")+1);
                    name.setText(text1);
                    roll.setText(text2);
                    sem.setText(text3);
                }

                catch(FileNotFoundException e){
                e.printStackTrace();
                }catch(IOException e){
                    e.printStackTrace();
                }

                finally{
                    try{
                        if(fis!=null)
                        fis.close();
                    }
                    catch(IOException e)
                    {
                        e.printStackTrace();
                    }
                }




            }
        }); // end of Load Button Listener

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();

            }

        }); // end of Back Button Listener

    }// end of execution

}// main
