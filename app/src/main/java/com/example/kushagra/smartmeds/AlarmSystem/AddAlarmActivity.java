package com.example.kushagra.smartmeds.AlarmSystem;

import android.app.DatePickerDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.kushagra.smartmeds.R;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class AddAlarmActivity extends AppCompatActivity implements com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {



    Calendar calendar;


    DatePickerDialog datePickerDialog;
    Button btnAddDate,btnAddTime;

    TextView pickedDate,pickedTime;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);



        btnAddDate = findViewById(R.id.btn_add_alarm_datepick);
       btnAddTime = findViewById(R.id.btn_add_alarm_timepick);


       pickedDate = findViewById(R.id.btn_add_alarm_datepick);
       pickedTime = findViewById(R.id.btn_add_alarm_timepick);


        btnAddDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar now = Calendar.getInstance();

                com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialog = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.
                        newInstance(AddAlarmActivity.this
                                ,now.get(Calendar.YEAR)
                                ,now.get(Calendar.MONTH)
                                ,now.get(Calendar.DAY_OF_MONTH));

                                datePickerDialog.setTitle("DATE");
                                datePickerDialog.show(getFragmentManager(),"Date Fragment");
            }
        });

        btnAddTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now =Calendar.getInstance();
                TimePickerDialog timePickerDialog = TimePickerDialog.
                        newInstance( AddAlarmActivity.this
                                ,now.get(Calendar.HOUR_OF_DAY)
                                ,now.get(Calendar.MINUTE)
                                ,true);

                timePickerDialog.setTitle("TIME");
                timePickerDialog.show(getFragmentManager(),"Time Fragment");
            }
        });

    }


    @Override
    public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {


        Toast.makeText(this, String.format("Your date: %d %d %d",monthOfYear,dayOfMonth,year), Toast.LENGTH_SHORT).show();



    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {

        Toast.makeText(this, String.format("Your time: %02d %02d %02d",hourOfDay,minute,second), Toast.LENGTH_SHORT).show();

    }
}
