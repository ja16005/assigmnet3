package com.example.jahnavi.assignment3;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class activity5 extends AppCompatActivity {

    private TextView name;
    private TextView roll;
    private TextView sem;
    private Button back;
    private Button load;

public void writeData(File f)
{
    FileInputStream fis=null;
    try
    {
        fis=new FileInputStream(f);
        int read=-1;
        StringBuffer buffer=new StringBuffer();
        Toast.makeText(getApplicationContext(), "HELLOooo", Toast.LENGTH_SHORT).show();
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity5);

        name = (TextView) findViewById(R.id.TextViewName);
        roll = (TextView) findViewById(R.id.TextViewroll);
        sem = (TextView) findViewById(R.id.TextViewSem);
        back = (Button) findViewById(R.id.ButtonBack);
        load = (Button) findViewById(R.id.ButtonLoad);
        Toast.makeText(getApplicationContext(), "HI", Toast.LENGTH_SHORT).show();

        execution();
    }

    public void execution() {
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                File folder=getExternalFilesDir("MC_ASS3");
                File f = new File(folder, "data.txt");
                Toast.makeText(getApplicationContext(), "HELLO", Toast.LENGTH_SHORT).show();
                writeData(f);

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


}// end of main
