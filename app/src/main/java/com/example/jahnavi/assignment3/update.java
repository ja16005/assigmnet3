package com.example.jahnavi.assignment3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class update extends AppCompatActivity {
    private Button back;
    private Button submit;
    private Button update;
    private EditText nameEdit;
    private EditText semEdit;
    private EditText rollEdit;
    String roll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);
        back=(Button)findViewById(R.id.ButtonBack);
        update=(Button)findViewById(R.id.ButtonUpdate);
        submit=(Button)findViewById(R.id.ButtonSubmit);
        rollEdit=(EditText)findViewById(R.id.editTextRoll);
        nameEdit=(EditText)findViewById(R.id.editTextName);
        semEdit=(EditText)findViewById(R.id.editTextSem);
        execution();

    }

    public void execution() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                roll = rollEdit.getText().toString();



            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = nameEdit.getText().toString();
                String sem = semEdit.getText().toString();

                Database dbhelper=new Database(getApplicationContext());
                int c=dbhelper.updatedata(roll,user,sem);
                if(c<0)
                {
                    Message.message(getApplicationContext(),"No such student exists");
                }
                else
                {
                    Message.message(getApplicationContext(),"Updated successfully");
                }

            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), activity3.class);
                startActivity(i);
                finish();

            }

        }); // end of Back Button Listener


    }// end of execution


}
