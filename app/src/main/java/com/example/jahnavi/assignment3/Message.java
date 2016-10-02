package com.example.jahnavi.assignment3;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by jahnavi on 29-09-2016.
 */
public class Message{

public static void message(Context context,String message)
{
    Toast.makeText(context,message,Toast.LENGTH_LONG).show();
}

}
