package com.developer.aashish.calanderandtime;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener
//        TimePickerDialog.OnTimeSetListener
{
EditText ed1;

    Button cal,dat,sh;
    ListView list;
    ArrayAdapter array;
    ArrayList<String> arraylist = new ArrayList<String>();
    TextView tv;

    int day,month,year,hour,minute;
    int finalday,finalmonth,finalyear,finalhour,finalminute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.list);
        ed1=(EditText)findViewById(R.id.name);
        cal=(Button) findViewById(R.id.calander);
        tv=(TextView)findViewById(R.id.text);
        dat=(Button) findViewById(R.id.time);
        sh=(Button)findViewById(R.id.show);
        cal.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                year=c.get(Calendar.YEAR);
                day=c.get(Calendar.DAY_OF_MONTH);
                month=c.get(Calendar.MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(MainActivity.this,MainActivity.this,year,month,day);
                datePickerDialog.show();


            }
        });

        dat.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
//                Calendar t=Calendar.getInstance();
//
//
//                hour=t.get(Calendar.HOUR_OF_DAY);
//                minute=t.get(Calendar.MINUTE);
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                       dat.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();


            }
        });







        sh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String mn=dat.getText().toString();

                tv.setText(ed1.getText()+"  "+finalday+ "/"+finalmonth+ "/"+finalyear+" Time is"+mn);
                arraylist.add(tv.getText().toString());
                array = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arraylist);
                list.setAdapter(array);
                ed1.setText("");
                tv.setText("");
                cal.setText("CALANDER");
                dat.setText("TIME PICK");



            }
        });
    }






















//    @Override
//    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//
//
//        finalhour=hourOfDay;
//        finalminute=minute;
////        TimePickerDialog timePickerDialog=new TimePickerDialog(MainActivity.this,MainActivity.this,hour,minute);
////        timePickerDialog.show();
//
//    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
finalyear=year;
        finalmonth=month+1;
        finalday=dayOfMonth;
        Calendar t=Calendar.getInstance();
        hour=t.get(Calendar.HOUR_OF_DAY);
        minute=t.get(Calendar.MINUTE);

//String mn=tv.getText().toString();

        cal.setText(finalday+ "/"+finalmonth+ "/"+finalyear);

    }

}
