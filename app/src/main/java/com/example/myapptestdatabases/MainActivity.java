package com.example.myapptestdatabases;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nom;
    Spinner vill;
    Button ajout,modif,supp,first,last,prec,suiv;
    ListView users;
    String[] ville={"Ifrane","Meknes","Marrakech"};
    DBConnection db;
    Cursor cr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nom=findViewById(R.id.txtnom);
        vill=findViewById(R.id.spnville);
        ajout=findViewById(R.id.btnajout);
        modif=findViewById(R.id.btnmodif);
        supp=findViewById(R.id.btnsupp);
        first=findViewById(R.id.btnfirst);
        last=findViewById(R.id.btnlast);
        prec=findViewById(R.id.btnprec);
        suiv=findViewById(R.id.btnsuiv);
        users=findViewById(R.id.lvusers);
        ArrayAdapter adr=new ArrayAdapter(this,android.R.layout.simple_spinner_item,ville);
        vill.setAdapter(adr);
        db=new DBConnection(getApplicationContext());
        affich();
        cr=db.AfficherCurs();
        ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm=nom.getText().toString();
                String vil=vill.getSelectedItem().toString();
                db.Ajouter(nm,vil);
                affich();
            }
        });
        modif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm=nom.getText().toString();
                String vil=vill.getSelectedItem().toString();
                db.Modifier(nm,vil);
                affich();
            }
        });
        supp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm=nom.getText().toString();
                db.Supprimer(nm);
                affich();
            }
        });
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cr.moveToFirst();
                nom.setText(cr.getString(cr.getColumnIndex(DBConnection.ChampNom)));
                vill.setSelection(getpos(cr.getString(cr.getColumnIndex(DBConnection.ChampVille))));
            }
        });
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cr.moveToLast();
                nom.setText(cr.getString(cr.getColumnIndex(DBConnection.ChampNom)));
                vill.setSelection(getpos(cr.getString(cr.getColumnIndex(DBConnection.ChampVille))));
            }
        });
        suiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cr.moveToNext();
               if(!cr.isAfterLast()) {
                   nom.setText(cr.getString(cr.getColumnIndex(DBConnection.ChampNom)));
                   vill.setSelection(getpos(cr.getString(cr.getColumnIndex(DBConnection.ChampVille))));
               }
            }
        });
        prec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cr.moveToPrevious();
                if(!cr.isBeforeFirst())
                {
                    nom.setText(cr.getString(cr.getColumnIndex(DBConnection.ChampNom)));
                    vill.setSelection(getpos(cr.getString(cr.getColumnIndex(DBConnection.ChampVille))));
                }

            }
        });
    }
    public void affich(){
      /*  ListAdapter ladr=new ArrayAdapter(this,android.R.layout.simple_list_item_1,db.Afficher());
        users.setAdapter(ladr);*/
      ListAdapter ladr=new SimpleCursorAdapter(this,R.layout.vueuser,db.AfficherCurs(),new String[] {DBConnection.ChampNom,DBConnection.ChampVille},new int[] {R.id.lblnom,R.id.lblville},0);
      users.setAdapter(ladr);
    }
    public int getpos(String v)
    {
        if(v.equals("Ifrane")) return 0;
        else if(v.equals("Meknes")) return 1;
        else return 2;
    }
}
