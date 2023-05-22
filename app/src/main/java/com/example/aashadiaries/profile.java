package com.example.aashadiaries;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class profile extends AppCompatActivity {

    TextView AshaID, Email, ContactNo, DOJ, Blood, Area, CountUpto2Val, CountUpto5Val, CountPregnantVal;
    EditText Email_edit, ContactNo_edit, Area_edit, Blood_edit;
    Button Edit, DOJ_Edit;

    DBHandlerUpto2Years handlerUpto2Years = new DBHandlerUpto2Years(this,"Upto2Years",null,1);
    DBHandlerUpto15Years handlerUpto15Years = new DBHandlerUpto15Years(this,"Upto15Years",null,1);
    DBHandlerPregnantWoman handlerPregnantWoman = new DBHandlerPregnantWoman(this,"PregnantWomanData",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        AshaID = findViewById(R.id.AshaID_Value);
        Email = findViewById(R.id.Email_name);
        ContactNo = findViewById(R.id.Phone_name);
        DOJ = findViewById(R.id.DateOJ);
        Blood = findViewById(R.id.Blood);
        Area = findViewById(R.id.Area);
        CountUpto2Val = findViewById(R.id.CountUpto2Val);
        CountUpto5Val = findViewById(R.id.CountUpto5Val);
        CountPregnantVal = findViewById(R.id.CountPregnantVal);

        Email_edit = findViewById(R.id.EMail_edit);
        ContactNo_edit = findViewById(R.id.Phone_edit);
        Area_edit = findViewById(R.id.Area_edit);
        Blood_edit = findViewById(R.id.Blood_edit);

        Edit = findViewById(R.id.Edit);
        DOJ_Edit = findViewById(R.id.selectDate);

        SharedPreferences SP = getSharedPreferences("MyPref",MODE_PRIVATE);

        AshaID.setText(SP.getString("AshaID","Not Available"));
        Email.setText(SP.getString("Email","Not Available"));
        ContactNo.setText(SP.getString("ContactNo","Not Available"));
        DOJ.setText(SP.getString("DOJ","Not Available"));
        Blood.setText(SP.getString("Blood","Not Available"));
        Area.setText(SP.getString("Area","Not Available"));

        CountUpto2Val.setText(String.valueOf(handlerUpto2Years.getSizeUpto2Years()));
        CountUpto5Val.setText(String.valueOf(handlerUpto15Years.getSizeUpto15Years()));
        CountPregnantVal.setText(String.valueOf(handlerPregnantWoman.getSizePregnantWomanData()));

        DOJ_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateText(DOJ);
            }
        });

        // Initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.profile);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.info:
                        startActivity(new Intent(getApplicationContext(),Info.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Edit.getText().toString().equals("Edit")) {
                    setToEditMode();
                } else{
                    setToSaveMode(SP);
                }
            }
        });

    }

    public void setToEditMode(){
        if(AshaID.getText().toString().matches("Not Available")){
            AshaID.setText("12496");
        }
        if(DOJ.getText().toString().matches("Not Available")){
            DOJ_Edit.setVisibility(View.VISIBLE);
        }
        if(Blood.getText().toString().matches("Not Available")){
            Blood.setVisibility(View.INVISIBLE);
            Blood_edit.setVisibility(View.VISIBLE);
        }

        Email_edit.setVisibility(View.VISIBLE);
        Email_edit.setText(Email.getText().toString());
        Email.setVisibility(View.GONE);

        ContactNo_edit.setVisibility(View.VISIBLE);
        ContactNo_edit.setText(ContactNo.getText().toString());
        ContactNo.setVisibility(View.GONE);

        Area_edit.setVisibility(View.VISIBLE);
        Area_edit.setText(Area.getText().toString());
        Area.setVisibility(View.GONE);

        Edit.setText("Save");
    }

    public void setToSaveMode(SharedPreferences SP){
//        if(DOJ_Edit.getVisibility()==View.VISIBLE){
//
//        }
        DOJ_Edit.setVisibility(View.GONE);

        if(Blood_edit.getVisibility()==View.VISIBLE){
            Blood.setText(Blood_edit.getText().toString());
            Blood_edit.setVisibility(View.GONE);
        }

        Email_edit.setVisibility(View.GONE);
        Email.setText(Email_edit.getText().toString());
        Email.setVisibility(View.VISIBLE);

        ContactNo_edit.setVisibility(View.GONE);
        ContactNo.setText(ContactNo_edit.getText().toString());
        ContactNo.setVisibility(View.VISIBLE);

        Area_edit.setVisibility(View.GONE);
        Area_edit.setText(Area_edit.getText().toString());
        Area.setVisibility(View.VISIBLE);

        SharedPreferences.Editor ED = SP.edit();

        ED.putString("AshaID",AshaID.getText().toString());
        ED.putString("Email",Email.getText().toString());
        ED.putString("ContactNo", ContactNo_edit.getText().toString());
        ED.putString("DOJ",DOJ.getText().toString());
        ED.putString("Blood",Blood.getText().toString());
        ED.putString("Area",Area_edit.getText().toString());

        ED.apply();

        Edit.setText("Edit");
    }

    public void setDateText(TextView T){
        //Instance of Calendar
        final Calendar c = Calendar.getInstance();

        //Getting current day,month,year
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        //variable for datepicker dialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                profile.this,R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String VaccineDate="";
                String fullday="",fullMonth="";
                if(dayOfMonth<=9) {
                    fullday = "0" + dayOfMonth;
                } else {
                    fullday = Integer.toString(dayOfMonth);
                }
                if(month<9&&month>=0){
                    fullMonth = "0"+ (month + 1);
                }else{
                    fullMonth = Integer.toString(month+1);
                }
                VaccineDate = fullday+"/"+(fullMonth)+"/"+year;
                T.setText(VaccineDate);
            }
        },
                year,month,day);
        datePickerDialog.show();
    }
}
