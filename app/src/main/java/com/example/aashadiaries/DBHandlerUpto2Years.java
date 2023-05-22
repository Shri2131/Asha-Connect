package com.example.aashadiaries;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandlerUpto2Years extends SQLiteOpenHelper {

    public DBHandlerUpto2Years(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE Upto2Years (UID INTEGER PRIMARY KEY, Name TEXT, DOB TEXT, Status TEXT, BloodGroup TEXT, MotherName Text, FatherName Text, Vaccine1 TEXT, Vaccine2 TEXT, Vaccine3 TEXT, Vaccine4 TEXT, Vaccine5 TEXT, Vaccine6 TEXT, Vaccine7 TEXT, Vaccine8 TEXT, Vaccine9 TEXT, Vaccine10 TEXT, Height TEXT, Weight TEXT)";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String upgrade = String.valueOf("DROP TABLE IF EXISTS");
        db.execSQL(upgrade, new String[]{"Upto2Years"});
        onCreate(db);
    }

    public void AddUpto2Years(classUpto2Years C){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("UID",C.getUID());
        values.put("Name",C.getName());
        values.put("DOB", C.getDateOfBirth());
        values.put("Status", "Visible");
        values.put("BloodGroup",C.getBloodGroup());
        values.put("MotherName",C.getMotherName());
        values.put("FatherName",C.getFatherName());
        values.put("Vaccine1", (String) null);
        values.put("Vaccine2", (String) null);
        values.put("Vaccine3", (String) null);
        values.put("Vaccine4", (String) null);
        values.put("Vaccine5", (String) null);
        values.put("Vaccine6", (String) null);
        values.put("Vaccine7", (String) null);
        values.put("Vaccine8", (String) null);
        values.put("Vaccine9", (String) null);
        values.put("Vaccine10", (String) null);
        values.put("Height",(String) null);
        values.put("Weight",(String) null);
        long check = db.insert("Upto2Years",null,values);
        Log.d("Input Check Tag",Long.toString(check));
        db.close();
    }

//    public String[] GetUpto2Years(int UID){
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursor = db.query("Upto2Years",new String[]{"UID","Name","DOB","BloodGroup","MotherName","FatherName","Vaccine1","Vaccine2","Vaccine3","Vaccine4","Vaccine5","Vaccine6","Vaccine7","Vaccine8","Vaccine9","Vaccine10","Height","Weight"},"UID=?", new String[]{String.valueOf(UID)},null,null,null);
//        if(cursor!=null && cursor.moveToFirst()){
//            Log.d("Tag Name",cursor.getString(1));
//            Log.d("Tag DOB",cursor.getString(2));
//            Log.d("Tag BloodGroup",cursor.getString(3));
//            Log.d("Tag MotherName",cursor.getString(4));
//            Log.d("Tag FatherName",cursor.getString(5));
//            Log.d("Tag Vaccine1",cursor.getString(6));
//            Log.d("Tag Vaccine2",cursor.getString(7));
//            Log.d("Tag Vaccine3",cursor.getString(8));
//            Log.d("Tag Vaccine4",cursor.getString(9));
//            Log.d("Tag Vaccine5",cursor.getString(10));
//            Log.d("Tag Vaccine6",cursor.getString(11));
//            Log.d("Tag Vaccine7",cursor.getString(12));
//            Log.d("Tag Vaccine8",cursor.getString(13));
//            Log.d("Tag Vaccine9",cursor.getString(14));
//            Log.d("Tag Vaccine10",cursor.getString(15));
//            Log.d("Tag Height",cursor.getString(16));
//            Log.d("Tag Weight",cursor.getString(17));
//        }else {
//            Log.d("Tag Print","Output Error");
//        }
//        return new String[]{cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13),cursor.getString(14),cursor.getString(15),cursor.getString(16), cursor.getString(17)};
//    }

    @SuppressLint("Range")
    public Object[] RetrieveAllDetailsUpto2Years(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Upto2Years WHERE Status='Visible'",null);
        int count = cursor.getCount();
        int[] UIDArray = new int[count];
        String[] Name = new String[count];
        String[] DOB = new String[count];
        int i=0;
        if(cursor.moveToFirst()){
            do{
                UIDArray[i] = cursor.getInt(cursor.getColumnIndex("UID"));
                Name[i] = cursor.getString(cursor.getColumnIndex("Name"));
                DOB[i] = cursor.getString(cursor.getColumnIndex("DOB"));
                String allString = UIDArray[i]+" - "+Name[i]+" - "+DOB[i];
                Log.d("Tag All",allString);
                i++;
            }while (cursor.moveToNext());
        }
        Object[] arrayToReturn = {UIDArray,Name,DOB};
        cursor.close();
        db.close();
        return arrayToReturn;
    }

    public int getSizeUpto2Years(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from Upto2Years  WHERE Status='Visible'",null);
        return cursor.getCount();
    }

    @SuppressLint("Range")
    public String getLastUIDValueUpto2Years(){
        int toReturn = 1;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("Select * from Upto2Years",null);
            int count = cursor.getCount();
            int[] UIDArray = new int[count];
            int i=0;
            if(cursor.moveToFirst()){
                do{
                    UIDArray[i] = cursor.getInt(cursor.getColumnIndex("UID"));
                }while (cursor.moveToNext());
            }
            toReturn = UIDArray[i]+1;
            Log.d("Return tag",Integer.toString(toReturn));
        }catch (Exception e){
            Log.d("Error Tag Last UID","ERROR");
        }
        return Integer.toString(toReturn);
    }

    public String[] EachUserGetUpto2Years(int UID){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("Upto2Years",new String[]{"UID","Name","DOB","BloodGroup","MotherName","FatherName","Vaccine1","Vaccine2","Vaccine3","Vaccine4","Vaccine5","Vaccine6","Vaccine7","Vaccine8","Vaccine9","Vaccine10","Height","Weight"},"UID=? AND Status=?", new String[]{String.valueOf(UID),"Visible"},null,null,null);
        if(cursor!=null && cursor.moveToFirst()){
            Log.d("Tag Name",cursor.getString(1));
            Log.d("Tag DOB",cursor.getString(2));
        }else {
            Log.d("Tag Print","Output Error");
        }
        return new String[]{cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12),cursor.getString(13),cursor.getString(14),cursor.getString(15), cursor.getString(16), cursor.getString(17)};
    }

    public void DeleteUpto2Years(int UID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Status","Invisible");
        db.update("Upto2Years",contentValues,"UID=?",new String[]{String.valueOf(UID)});
    }

    public void UpdateValue(int UID, String ColumnName, String ColumnValue){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(ColumnName,ColumnValue);
        db.update("Upto2Years",contentValue,"UID=?",new String[]{String.valueOf(UID)});
    }

    public String ReturnBMI(int UID){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("Upto2Years",new String[]{"UID","Height","Weight"},"UID=?", new String[]{String.valueOf(UID)},null,null,null);

        double Height = 0;
        double Weight = 0;

        try{
            if(cursor!=null && cursor.moveToFirst()){
                Height = Double.parseDouble(cursor.getString(1));
                Weight = Double.parseDouble(cursor.getString(2));
            }
        }catch (Exception e){
            return "0";
        }

        double BMI = Weight/(Height*Height);

        Log.d("Error BMI UID", String.valueOf(UID));
        Log.d("Error BMI Height",cursor.getString(1));
        Log.d("Error BMI Weight",cursor.getString(2));
        return String.format("%.2f",BMI);
    }

    public ArrayList<classUpto2Years> getAllUserForExcel(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<classUpto2Years>list=new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from Upto2Years",null);
        if(cursor.moveToFirst()){
            do{
                @SuppressLint("Range") int UID=cursor.getInt(cursor.getColumnIndex("UID"));
                @SuppressLint("Range") String Name=cursor.getString(cursor.getColumnIndex("Name"));
                @SuppressLint("Range") String DOB=cursor.getString(cursor.getColumnIndex("DOB"));
                @SuppressLint("Range") String MotherName=cursor.getString(cursor.getColumnIndex("MotherName"));
                @SuppressLint("Range") String FatherName=cursor.getString(cursor.getColumnIndex("FatherName"));
                @SuppressLint("Range") String BloodGroup=cursor.getString(cursor.getColumnIndex("BloodGroup"));
                @SuppressLint("Range") String Status=cursor.getString(cursor.getColumnIndex("Status"));

                @SuppressLint("Range") String Vaccine1=cursor.getString(cursor.getColumnIndex("Vaccine1"));
                @SuppressLint("Range") String Vaccine2=cursor.getString(cursor.getColumnIndex("Vaccine2"));
                @SuppressLint("Range") String Vaccine3=cursor.getString(cursor.getColumnIndex("Vaccine3"));
                @SuppressLint("Range") String Vaccine4=cursor.getString(cursor.getColumnIndex("Vaccine4"));
                @SuppressLint("Range") String Vaccine5=cursor.getString(cursor.getColumnIndex("Vaccine5"));
                @SuppressLint("Range") String Vaccine6=cursor.getString(cursor.getColumnIndex("Vaccine6"));
                @SuppressLint("Range") String Vaccine7=cursor.getString(cursor.getColumnIndex("Vaccine7"));
                @SuppressLint("Range") String Vaccine8=cursor.getString(cursor.getColumnIndex("Vaccine8"));
                @SuppressLint("Range") String Vaccine9=cursor.getString(cursor.getColumnIndex("Vaccine9"));
                @SuppressLint("Range") String Vaccine10=cursor.getString(cursor.getColumnIndex("Vaccine10"));

                @SuppressLint("Range") String Height=cursor.getString(cursor.getColumnIndex("Height"));
                @SuppressLint("Range") String Weight=cursor.getString(cursor.getColumnIndex("Weight"));

                list.add(new classUpto2Years(UID,Name,BloodGroup,MotherName,FatherName,Status,DOB,Vaccine1,Vaccine2,Vaccine3,Vaccine4,Vaccine5,Vaccine6,Vaccine7,Vaccine8,Vaccine9,Vaccine10,Height,Weight));
//                list.add(new classPregnantWoman(001 ,"Shrimohan","31-05-03","B+","Rajesh"));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
