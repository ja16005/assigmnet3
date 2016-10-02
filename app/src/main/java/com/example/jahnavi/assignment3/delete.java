package com.example.jahnavi.assignment3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class delete extends AppCompatActivity {
    private Button back;
    private Button delete;
    private EditText rollEdit;
    String roll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete);
        back=(Button)findViewById(R.id.ButtonBack);
        delete=(Button)findViewById(R.id.ButtonDelete);
        rollEdit=(EditText)findViewById(R.id.editTextRoll);

       execution();
    }

    public void execution() {
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roll = rollEdit.getText().toString();
                Database dbhelper=new Database(getApplicationContext());
                int c=dbhelper.deletedata(roll);
                if(c<0)
                {
                    Message.message(getApplicationContext(),"No such student");
                }
                else
                {
                    Message.message(getApplicationContext(),"Deleted Successfully");
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

        });
    }
}
