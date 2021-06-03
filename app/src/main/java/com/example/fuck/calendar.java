package com.example.fuck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class calendar extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;

    public String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(() -> {
            runOnUiThread(() -> {
                setContentView(R.layout.activity_calendar);
                setView();
                Spinner spinner = (Spinner) findViewById(R.id.spinner);
                ArrayAdapter<CharSequence> adapter =
                        ArrayAdapter.createFromResource(this,
                                R.array.time_array,
                                android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setSelection(2, false);
                spinner.setOnItemSelectedListener(spnOnItemSelected);
                getInfo();

            });
        }).start();

    }

    private AdapterView.OnItemSelectedListener spnOnItemSelected
            = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            String sPos=String.valueOf(pos);
            String sInfo=parent.getItemAtPosition(pos).toString();
            String tmp[] = sInfo.split(":");
        }
        public void onNothingSelected(AdapterView<?> parent) {
            //
        }
    };

    private void setView() {
        Button btToday;
        CalendarView calendarView = findViewById(R.id.calendarView);
        btToday =(Button) findViewById(R.id.button_Today);

        btToday.setOnClickListener(v -> {

            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdfY = new SimpleDateFormat("yyyy");
            SimpleDateFormat sdfM = new SimpleDateFormat("MM");
            SimpleDateFormat sdfD = new SimpleDateFormat("dd");

            calendar.set(Integer.parseInt(sdfY.format(date))
                    , Integer.parseInt(sdfM.format(date)) - 1
                    , Integer.parseInt(sdfD.format(date)));
            try {

                calendarView.setDate(calendar);
            } catch (OutOfDateRangeException e) {
                e.printStackTrace();
            }
        });
    }

    private void getInfo(){
        Button makeSure;
        makeSure = findViewById(R.id.button_makeSure);
        CalendarView calendarView = findViewById(R.id.calendarView);

        makeSure.setOnClickListener(v -> {
            Spinner myspinner = (Spinner)findViewById(R.id.spinner);
            String text = myspinner.getSelectedItem().toString();

            for (Calendar calendar : calendarView.getSelectedDates()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                res = sdf.format(calendar.getTime());
            }
            res = res + " " + text;
            Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
            //2021/5/16 9:30
            String[] ret = res.split(":| |/");
            Intent intent = getIntent();

            intent.putExtra("year", Integer.parseInt(ret[0]));
            setResult(REQUEST_CODE, intent);
            intent.putExtra("month", Integer.parseInt(ret[1]));
            setResult(REQUEST_CODE, intent);
            intent.putExtra("day", Integer.parseInt(ret[2]));
            setResult(REQUEST_CODE, intent);
            intent.putExtra("hr", Integer.parseInt(ret[3]));
            setResult(REQUEST_CODE, intent);
            intent.putExtra("min", Integer.parseInt(ret[4]));
            setResult(REQUEST_CODE, intent);
            finish();
        });
    }

}