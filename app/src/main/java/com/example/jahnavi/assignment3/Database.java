package com.example.jahnavi.assignment3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by jahnavi on 29-09-2016.
 */
public class Database {
    dbHelper helper;

    public Database(Context context)
    {
        helper=new dbHelper(context);
    }


    public long insertData(String roll,String name,String sem)
    {
        SQLiteDatabase db= helper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(dbHelper.roll,roll);
        cv.put(dbHelper.name,name);
        cv.put(dbHelper.sem,sem);
        long id= db.insert(dbHelper.TABLE_NAME,null,cv);
        return id;
    }
    public int updatedata(String roll,String new_name,String new_sem)
    {
        SQLiteDatabase db= helper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(dbHelper.name,new_name);
        cv.put(dbHelper.sem,new_sem);
        String[] old_roll={roll};
       int count= db.update(dbHelper.TABLE_NAME,cv,dbHelper.roll+" =?",old_roll);
        return count;
    }
    public int deletedata(String roll)
    {
        String[] old_roll={roll};
        SQLiteDatabase db= helper.getWritableDatabase();
        int count=db.delete(dbHelper.TABLE_NAME,dbHelper.roll+" =?",old_roll);
        return count;
    }


    public String retrievedata()
    {
        Log.d("Retrieve","HEYY");
        String[] columns={dbHelper.roll,dbHelper.name,dbHelper.sem};
        SQLiteDatabase db= helper.getWritableDatabase();
       // String selection=dbHelper.roll+ " = ?";
       // String selectionArgs[]={roll};
          Cursor cursor=db.query(dbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buff=new StringBuffer();
        while(cursor.moveToNext())
        {
            int index1=cursor.getColumnIndex(dbHelper.roll);
            String roll_num=cursor.getString(index1);
            int index2=cursor.getColumnIndex(dbHelper.name);
           String Name= cursor.getString(index2);
            int index3=cursor.getColumnIndex(dbHelper.sem);
            String Sem=cursor.getString(index3);
            buff.append(roll_num + "       " +Name + "        " +Sem+ "\n");

        }
    return buff.toString();
    }

    static class dbHelper  extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME="Students.db";
        private static final String TABLE_NAME="StudentsTable";
        private static final int DATABASE_VERSION=1;
        private static final String roll="_Roll";
        private static final String name="Name";
        private static final String sem="Semester";
        private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME +" ("+roll+" VARCHAR(255) PRIMARY KEY,"+name+" VARCHAR(255),"+sem+" VARCHAR(255));";
        private static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public dbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context=context;
        }


        @Override
        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
                Message.message(context,"On create called ");
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            try {
                db.execSQL(DROP_TABLE);
                Message.message(context,"On upgrade called ");
                onCreate(db);
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }


        }
    }
}
