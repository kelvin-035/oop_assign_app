package com.example.fuck;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fuck.R;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;

    private Spinner spinnerFrom;
    private Spinner spinnerEnd;
    private String dateFromSelected = "2021/06/02";
    private String fromStation, endStation, selectedSeatClassText,selectedSeatFavorText;
    private int i_normalTicket, i_childTicket, i_elderTicket, i_loveTicket, i_stuTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinnerFrom = (Spinner) findViewById(R.id.spinnerFrom);
        spinnerEnd = (Spinner) findViewById(R.id.spinnerEnd);

        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,
                        R.array.station,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerFrom.setSelection(1, false);
        spinnerEnd.setAdapter(adapter);
        spinnerEnd.setSelection(11, false);


        Button btn_getDate = findViewById(R.id.btn_getDate);
        btn_getDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentDateSet = new Intent();
                intentDateSet.setClass(MainActivity.this, calendar.class);
                startActivity(intentDateSet);
            }
        });

        Button btn_sendSearch = findViewById(R.id.btn_sendSearch);
        btn_sendSearch.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
//        ???????????????
                fromStation = spinnerFrom.getSelectedItem().toString();
                endStation = spinnerEnd.getSelectedItem().toString();

//        ?????????????????? ????????????????????????
                RadioGroup seatClass = findViewById(R.id.seatClass);
                int selectedSeatClassId = seatClass.getCheckedRadioButtonId();
                if (selectedSeatClassId != -1){
                    RadioButton selectedSeatClass = findViewById(selectedSeatClassId);
                    selectedSeatClassText = selectedSeatClass.getText().toString();
                }else{
                    Toast.makeText(
                            MainActivity.this,
                            "?????????????????????",
                            Toast.LENGTH_LONG).show();
                    return;
                }

//        ??????????????????
                EditText normalTicket = findViewById(R.id.normalTicket);
                i_normalTicket = Integer.parseInt(normalTicket.getText().toString());
                EditText childTicket = findViewById(R.id.childTicket);
                i_childTicket = Integer.parseInt(childTicket.getText().toString());
                EditText elderTicket = findViewById(R.id.elderTicket);
                i_elderTicket = Integer.parseInt(elderTicket.getText().toString());
                EditText loveTicket = findViewById(R.id.loveTicket);
                i_loveTicket = Integer.parseInt(loveTicket.getText().toString());
                EditText stuTicket = findViewById(R.id.stuTicket);
                i_stuTicket = Integer.parseInt(stuTicket.getText().toString());

                if (i_childTicket + i_elderTicket + i_loveTicket + i_normalTicket + i_stuTicket == 0){
                    Toast.makeText(
                            MainActivity.this,
                            "??????????????????????????????",
                            Toast.LENGTH_LONG).show();
                    return;
                }else if (i_childTicket + i_elderTicket + i_loveTicket + i_normalTicket + i_stuTicket < 0){
                    Toast.makeText(
                            MainActivity.this,
                            "?????????????????????",
                            Toast.LENGTH_LONG).show();
                    return;
                }

//        ??????????????????
                RadioGroup seatFavor = findViewById(R.id.seatFavor);
                int selectedSeatFavorId = seatFavor.getCheckedRadioButtonId();
                if (selectedSeatFavorId != -1){
                    RadioButton selectedSeatFavor = findViewById(selectedSeatFavorId);
                    selectedSeatFavorText = selectedSeatFavor.getText().toString();
                }else{
                    Toast.makeText(
                            MainActivity.this,
                            "?????????????????????",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                String infoMessage = ("????????? : " + fromStation + "  ????????? : " + endStation  + "\n" +
                        "???????????? : " + dateFromSelected + "\n" +
                        "???????????? : " + selectedSeatClassText + "\n" +
                        "??????" + i_normalTicket + "??? " +
                        "??????" + i_childTicket + "??? " +
                        "??????" + i_elderTicket + "??? " +
                        "??????" + i_loveTicket + "??? " +
                        "?????????" + i_stuTicket + "???" + "\n" +
                        "???????????? : " + selectedSeatFavorText);

                Toast.makeText(
                        MainActivity.this,
                        infoMessage,
                        Toast.LENGTH_LONG).show();
            }
        });

        Button btn_searchTicket = findViewById(R.id.btn_searchTicket);
        btn_searchTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSearchTicket = new Intent();
                intentSearchTicket.setClass(MainActivity.this, searchTicket.class);
                startActivity(intentSearchTicket);
            }
        });



    }

}
