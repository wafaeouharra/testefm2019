package com.example.myapptestdatabases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBConnection extends SQLiteOpenHelper {
    final static String DBName="DBUsers";
    final static String TableName="Users";
    final static String ChampNom="Nom";
    final static String ChampVille="Ville";
    final static int dbversion=1;
    final static String reqcreate="CREATE TABLE "+TableName+"("+ChampNom+" TEXT PRIMARY KEY,"+ChampVille+" TEXT)";
    public DBConnection( Context context) {
        super(context, DBName, null, dbversion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(reqcreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void Ajouter(String nm,String vl)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues Ligne=new ContentValues();
        Ligne.put(ChampNom,nm);
        Ligne.put(ChampVille,vl);
        db.insert(TableName,null,Ligne);
    }

    public void Modifier(String nm ,String vl)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("UPDATE "+TableName+" SET "+ChampVille+"='"+vl+"' WHERE "+ChampNom+"='"+nm+"'");
    }
    public void Supprimer(String nm )
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM "+TableName+" WHERE "+ChampNom+"='"+nm+"'");
    }

    public ArrayList Afficher()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        ArrayList lst=new ArrayList();
        Cursor cr=db.rawQuery("SELECT * FROM "+TableName,null);
        cr.moveToFirst();
        while (!cr.isAfterLast())
        {
            lst.add("Nom :"+cr.getString(cr.getColumnIndex(ChampNom))+" -Ville :"+cr.getString(cr.getColumnIndex(ChampVille))+"");
            cr.moveToNext();
        }
        cr.close();
        return  lst;
    }
    public Cursor AfficherCurs()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String[] colu={"rowid _id",ChampNom,ChampVille};
        Cursor Cr=db.query(TableName,colu,null,null,null,null,null);
        return Cr;

    }
}
