package com.example.jahnavi.assignment3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button spButton;
    private Button intButton;
    private Button extButton;
    private Button extPublicButton;
    private Button sqlButton;
    private Button nextButton;
    private EditText nameEdit;
    private EditText rollEdit;
    private EditText semEdit;
    private int flag=0;
    Database dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spButton=(Button)findViewById(R.id.ButtonSP);
        intButton=(Button)findViewById(R.id.ButtonInternal);
        extButton=(Button)findViewById(R.id.ButtonExternal);
        sqlButton=(Button)findViewById(R.id.ButtonSql);
         extPublicButton=(Button)findViewById(R.id.ButtonExternalPublic);
        nameEdit=(EditText)findViewById(R.id.editTextName);
        rollEdit=(EditText)findViewById(R.id.editTextRoll);
        semEdit=(EditText)findViewById(R.id.editTextSem);
        nextButton=(Button)findViewById(R.id.ButtonNext);
        execution();
       //
    }

    public void execution()
    {
        spButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = nameEdit.getText().toString();
                String roll = rollEdit.getText().toString();
                String sem = semEdit.getText().toString();

                if (user.isEmpty() || roll.isEmpty() || sem.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();

                } else {
                    SharedPreferences sp = getSharedPreferences("User_Data", Context.MODE_PRIVATE);
                    SharedPreferences.Editor e = sp.edit();
                    e.putString("Name", nameEdit.getText().toString());
                    e.putString("Roll", rollEdit.getText().toString());
                    e.putString("Semester", semEdit.getText().toString());
                    e.commit();
                    Toast.makeText(getApplicationContext(), "Data saved Successfully into Shared Preferences", Toast.LENGTH_SHORT).show();
                    flag = 1;
                }
            }
         }); // end of SP Button Listerner

        intButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = nameEdit.getText().toString();
                String roll = rollEdit.getText().toString();
                String sem = semEdit.getText().toString();
                if (user.isEmpty() || roll.isEmpty() || sem.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();

                } else {
                    user = user + " ";
                    roll = roll + ".";
                    File file = null;
                    FileOutputStream fos = null;
                    try {
                        file = getFilesDir();
                        fos = openFileOutput("student_data.txt", Context.MODE_PRIVATE);
                        fos.write(user.getBytes());
                        fos.write(roll.getBytes());
                        fos.write(sem.getBytes());
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (fos != null)
                                fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                    Toast.makeText(getApplicationContext(), "Data saved Successfully into Internal storage at" + file, Toast.LENGTH_SHORT).show();
                    flag = 2;

                }
            }
        }); // end of INT Button Listener



        extButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = nameEdit.getText().toString();
                String roll = rollEdit.getText().toString();
                String sem = semEdit.getText().toString();
                if (user.isEmpty() || roll.isEmpty() || sem.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();

                } else {
                    user = user + " ";
                    roll = roll + ".";
                    File folder = getExternalFilesDir("MC_ASS3");
                    File f = new File(folder, "data.txt");
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(f);
                        fos.write(user.getBytes());
                        fos.write(roll.getBytes());
                        fos.write(sem.getBytes());
                    } catch (FileNotFoundException e) {

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (fos != null)
                                fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Toast.makeText(getApplicationContext(), "Data saved Successfully into External storage at" + f.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                    flag = 3;
                }
            }
        }); // end of EXT Button Listener


        extPublicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = nameEdit.getText().toString();
                String roll = rollEdit.getText().toString();
                String sem = semEdit.getText().toString();
                if (user.isEmpty() || roll.isEmpty() || sem.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();

                } else {
                    user = user + " ";
                    roll = roll + ".";
                    File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    File f = new File(folder, "data.txt");
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(f);
                        fos.write(user.getBytes());
                        fos.write(roll.getBytes());
                        fos.write(sem.getBytes());
                    } catch (FileNotFoundException e) {

                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (fos != null)
                                fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    Toast.makeText(getApplicationContext(), "Data saved Successfully into External storage at" + f.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                    flag = 4;

                }
            }
        });





        sqlButton.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View view) {
                dbhelper = new Database(getApplicationContext());
                String roll = rollEdit.getText().toString();
                String user = nameEdit.getText().toString();
                String sem = semEdit.getText().toString();
                if (user.isEmpty() || roll.isEmpty() || sem.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();

                } else {
                    long id = dbhelper.insertData(roll, user, sem);
                    if (id < 0) {
                        Message.message(getApplicationContext(), "Unsuccessful");
                    } else {
                        Message.message(getApplicationContext(), "Successfully inserted a row");
                    }
                    flag = 5;

                }
            }
        }); // end of SQL Button Listener

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag==1)
                {
                    Intent i=new Intent(getApplicationContext(),activity2.class);
                    startActivity(i);
                }
                if(flag==2)
                {
                    Intent i=new Intent(getApplicationContext(),activity4.class);
                    startActivity(i);
                }

                if(flag==3)
                {
                    Intent i=new Intent(getApplicationContext(),activity5.class);
                    startActivity(i);
                }
                if(flag==4)
                {
                    Intent i=new Intent(getApplicationContext(),activity6.class);
                    startActivity(i);
                }
                if(flag==5)
                {
                   Intent i=new Intent(getApplicationContext(),activity3.class);
                    startActivity(i);

                }

            }
        }); // end of NEXT Button Listener


    }



    }// end of main class
