package com.example.sqliteandroid;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    TextView tv1;
    EditText et1,et2,et3;
    Button b1,b2,b3,b4;
    String rno;
    String name;
    String dept;



SQLiteDatabase db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tv1=findViewById(R.id.tv1);
        et1 =findViewById(R.id.et1);
        et2=findViewById(R.id.et2);
        et3=findViewById(R.id.et3);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        b3=findViewById(R.id.b3);
        b4=findViewById(R.id.b4);





        DBHelper dbHelper=new DBHelper(this);
        db=dbHelper.getWritableDatabase();
        db=dbHelper.getReadableDatabase();

    }


    public void oninsert(View view) {
        rno = et1.getText().toString();
        name = et2.getText().toString();
        dept = et3.getText().toString();

        if (rno.equals("") || name.equals("") || dept.equals("")) {
            Toast.makeText(this, "pls enter values", Toast.LENGTH_SHORT).show();
            return;

        } else {

            ContentValues values = new ContentValues();
            values.put("rollno", rno);
            values.put("name", name);
            values.put("dept", dept);

            db.insert("student", null, values);
            Toast.makeText(this, "inserted.........", Toast.LENGTH_SHORT).show();
        }
    }

    public void onupdate(View view) {
        rno = et1.getText().toString();
        name = et2.getText().toString();
        dept = et3.getText().toString();

        if (rno.equals("") ||name.equals("")||dept.equals(""))
        {
            Toast.makeText(this, "pls enter rollno", Toast.LENGTH_SHORT).show();
            return;

        } else {

            ContentValues values = new ContentValues();
            values.put("rollno", rno);
            values.put("name", name);
            values.put("dept", dept);

            db.update("student", values,"rollno="+rno,null);
            Toast.makeText(this, "updated done", Toast.LENGTH_SHORT).show();
        }
    }



    public void onread(View view) {
        StringBuffer buffer = new StringBuffer();
        Cursor c = db.rawQuery("select * from student", null);
        while (c.moveToNext()) {

            buffer.append("\n" + c.getString(0));
            buffer.append("\n" + c.getString(1));
            buffer.append("\n" + c.getString(2));

        }
        Toast.makeText(this,buffer.toString(),Toast.LENGTH_SHORT).show();
    }

    public void ondelete(View view)
    {
        rno = et1.getText().toString();
        name = et2.getText().toString();
        dept = et3.getText().toString();

        if (rno.equals("") ){
            Toast.makeText(this, "pls enter values", Toast.LENGTH_SHORT).show();
            return;

        } else {



            db.delete("student", "rollno="+rno, null);
            Toast.makeText(this, "delete.........", Toast.LENGTH_SHORT).show();
        }
    }

}
