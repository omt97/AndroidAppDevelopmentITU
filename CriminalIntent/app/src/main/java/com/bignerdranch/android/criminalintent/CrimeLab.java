package com.bignerdranch.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.bignerdranch.android.criminalintent.database.CrimeDbSchema;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CrimeLab {
    private static CrimeLab crimeLab;

   // private List<Crime> crimes;
    private static Context mContext;
    private static SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context){
        if (crimeLab == null){
            crimeLab = new CrimeLab(context);

        }
        return crimeLab;
    }

    private CrimeLab(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();
    }

    public static void createRow() {
        mDatabase.delete(CrimeDbSchema.CrimeTable.NAME, null, null);
        ContentValues contentValues = new ContentValues();
        contentValues.put(CrimeDbSchema.CrimeTable.Cols.UUID, new UUID(2,6).toString());
        contentValues.put(CrimeDbSchema.CrimeTable.Cols.TITLE, "abc");
        contentValues.put(CrimeDbSchema.CrimeTable.Cols.DATE, new Date().toString());
        contentValues.put(CrimeDbSchema.CrimeTable.Cols.SOLVED, true);
        mDatabase.insert(CrimeDbSchema.CrimeTable.NAME, null, contentValues);

    }

    public List<Crime> getCrimes() {

        //return new ArrayList<>();
        List<Crime> crimes = new ArrayList<>();
        CrimeCursorWrapper cursor = queryCrimes(null, null);


        try {
            cursor.moveToFirst();
           // System.out.println(cursor.getCrime().toString());
            while (!cursor.isAfterLast()) {
                System.out.println(6);
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        }finally {
            System.out.println(7);
            cursor.close();
        }

        return crimes;
    }

    public void addCrime(Crime c){
        ContentValues values = getContentValues(c);
        mDatabase.insert(CrimeDbSchema.CrimeTable.NAME, null, values);
    }

    public void updateCrime(Crime c){
        String uuidString = c.getmId().toString();
        ContentValues values = getContentValues(c);

        mDatabase.update(CrimeDbSchema.CrimeTable.NAME, values,
                CrimeDbSchema.CrimeTable.Cols.UUID + " = ?", new String[] { uuidString });
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                CrimeDbSchema.CrimeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new CrimeCursorWrapper(cursor);
    }

    public Crime getCrimeById(UUID id){
        /*for (Crime crime : crimes){
            if(crime.getmId().equals(id)){
                return crime;
            }
        }*/
        //return null;
        CrimeCursorWrapper cursor = queryCrimes(
                CrimeDbSchema.CrimeTable.Cols.UUID + " = ?",
                new String[] {id.toString()}
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getCrime();
        }finally{
            cursor.close();
        }
    }

    public static ContentValues getContentValues(Crime crime){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CrimeDbSchema.CrimeTable.Cols.UUID, crime.getmId().toString());
        contentValues.put(CrimeDbSchema.CrimeTable.Cols.TITLE, crime.getmTitle());
        contentValues.put(CrimeDbSchema.CrimeTable.Cols.DATE, crime.getmDate().toString());
        contentValues.put(CrimeDbSchema.CrimeTable.Cols.SOLVED, crime.ismSolved());
        return contentValues;
    }
}
