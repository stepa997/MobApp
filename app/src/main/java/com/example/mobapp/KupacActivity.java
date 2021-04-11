package com.example.mobapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class KupacActivity extends AppCompatActivity {

    Button profile, potvrda, korpa, dodaj, odutani, opis;
    ImageView logout, left, right, proizvod;
    TableLayout podaci;
    Kupac kupac;
    TextView naziv;
    int current;
    int[] poruci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kupac);

        profile = findViewById(R.id.profile);
        potvrda = findViewById(R.id.potvrda);
        logout = findViewById(R.id.logout);
        podaci = findViewById(R.id.tabla);
        korpa = findViewById(R.id.korpa);
        left = findViewById(R.id.left_button);
        right = findViewById(R.id.right_button);
        proizvod = findViewById(R.id.proizvod);
        dodaj = findViewById(R.id.dodaj);
        odutani = findViewById(R.id.odutani);
        naziv = findViewById(R.id.naziv);
        opis = findViewById(R.id.opis);
        kupac = new Kupac();
        poruci = new int[3];


        podaci.setEnabled(false);

        current = 0;
        proizvod.setImageDrawable(getResources().getDrawable(R.drawable.lipov));
        naziv.setText("Lipov med - 820 dinara");
        profile.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v){
                Bundle extras = getIntent().getExtras();
                naziv.setVisibility(TextView.INVISIBLE);
                podaci.setEnabled(true);
                profile.setTextColor(R.color.purple_200);
                podaci.setVisibility(TableLayout.VISIBLE);
                korpa.setVisibility(Button.INVISIBLE);
                TableRow tableRow = (TableRow) podaci.getChildAt(0);
                TextView textView = (TextView) tableRow.getChildAt(1);
                System.out.println("Extras " + getIntent().getStringExtra("NameAdmin"));
                if (getIntent().getStringExtra("NameAdmin") != null) {
                    kupac.setKorisnicko_ime(getIntent().getStringExtra("NameAdmin"));
                    textView.setText(kupac.getKorisnicko_ime());
                } else kupac.setKorisnicko_ime(textView.getText().toString());


                tableRow = (TableRow) podaci.getChildAt(1);
                textView = (TextView) tableRow.getChildAt(1);
                if (getIntent().getStringExtra("Name") != null) {
                    kupac.setIme(getIntent().getStringExtra("Name"));
                    textView.setText(kupac.getIme());
                } else kupac.setIme(textView.getText().toString());

                tableRow = (TableRow) podaci.getChildAt(2);
                textView = (TextView) tableRow.getChildAt(1);
                if (getIntent().getStringExtra("Surname") != null) {
                    kupac.setPrezime(getIntent().getStringExtra("Surname"));
                    textView.setText(kupac.getPrezime());
                } else kupac.setPrezime(textView.getText().toString());

                tableRow = (TableRow) podaci.getChildAt(3);
                textView = (TextView) tableRow.getChildAt(1);
                if (getIntent().getStringExtra("Number") != null) {
                    kupac.setBroj(getIntent().getStringExtra("Number"));
                    textView.setText(kupac.getBroj());
                } else kupac.setBroj(textView.getText().toString());

                tableRow = (TableRow) podaci.getChildAt(4);
                textView = (TextView) tableRow.getChildAt(1);
                if (getIntent().getStringExtra("Adress") != null) {
                    kupac.setAdresa(getIntent().getStringExtra("Adress"));
                    textView.setText(kupac.getAdresa());
                } else kupac.setAdresa(textView.getText().toString());

                tableRow = (TableRow) podaci.getChildAt(5);
                textView = (TextView) tableRow.getChildAt(1);
                if (getIntent().getStringExtra("PaAdmin") != null) {
                    kupac.setPa(getIntent().getStringExtra("PaAdmin"));
                    textView.setText(kupac.getPa());
                } else kupac.setPa(textView.getText().toString());
            }
        });

        potvrda.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v){
                naziv.setVisibility(TextView.VISIBLE);
                podaci.setVisibility(TableLayout.INVISIBLE);
                TableRow tableRow = (TableRow) podaci.getChildAt(0);
                TextView textView = (TextView) tableRow.getChildAt(1);
                kupac.setKorisnicko_ime(textView.getText().toString());

                tableRow = (TableRow) podaci.getChildAt(1);
                textView = (TextView) tableRow.getChildAt(1);
                kupac.setIme(textView.getText().toString());

                tableRow = (TableRow) podaci.getChildAt(2);
                textView = (TextView) tableRow.getChildAt(1);
                kupac.setPrezime(textView.getText().toString());

                tableRow = (TableRow) podaci.getChildAt(3);
                textView = (TextView) tableRow.getChildAt(1);
                kupac.setBroj(textView.getText().toString());

                tableRow = (TableRow) podaci.getChildAt(4);
                textView = (TextView) tableRow.getChildAt(1);
                kupac.setAdresa(textView.getText().toString());

                tableRow = (TableRow) podaci.getChildAt(5);
                textView = (TextView) tableRow.getChildAt(1);
                kupac.setPa(textView.getText().toString());
                profile.setTextColor(R.color.black);
                korpa.setVisibility(Button.VISIBLE);
                podaci.setEnabled(false);
            }
        });

        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                podaci.setEnabled(false);
                podaci.setVisibility(TableLayout.INVISIBLE);
                Intent intent = new Intent(KupacActivity.this, MainActivity.class);

                TableRow tableRow = (TableRow) podaci.getChildAt(0);
                TextView textView = (TextView) tableRow.getChildAt(1);
                kupac.setKorisnicko_ime(textView.getText().toString());

                tableRow = (TableRow) podaci.getChildAt(5);
                textView = (TextView) tableRow.getChildAt(1);
                kupac.setPa(textView.getText().toString());

                intent.putExtra("NameAdmin", kupac.getKorisnicko_ime());
                intent.putExtra("PaAdmin", kupac.getPa());
                intent.putExtra("Name", kupac.getIme());
                intent.putExtra("Surname", kupac.getPrezime());
                intent.putExtra("Number", kupac.getBroj());
                intent.putExtra("Adress", kupac.getAdresa());
                startActivity(intent);
            }
        });

        left.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (current == 0) current = 2;
                else current--;

                switch (current) {
                    case 0: {
                        proizvod.setImageDrawable(getResources().getDrawable(R.drawable.lipov));
                        naziv.setText("Lipov med - 820 dinara");
                        break;
                    }
                    case 1: {
                        proizvod.setImageDrawable(getResources().getDrawable(R.drawable.med));
                        naziv.setText("Kraljev med - 770 dinara");
                        break;
                    }
                    case 2: {
                        proizvod.setImageDrawable(getResources().getDrawable(R.drawable.propoli));
                        naziv.setText("Propolis - 350 dinara");
                        break;
                    }
                }
            }
        });

        right.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (current == 2) current = 0;
                else current++;
                switch (current) {
                    case 0: {
                        proizvod.setImageDrawable(getResources().getDrawable(R.drawable.lipov));
                        naziv.setText("Lipov med - 820 dinara");
                        break;
                    }
                    case 1: {
                        proizvod.setImageDrawable(getResources().getDrawable(R.drawable.med));
                        naziv.setText("Kraljev med - 770 dinara");
                        break;
                    }
                    case 2: {
                        proizvod.setImageDrawable(getResources().getDrawable(R.drawable.propoli));
                        naziv.setText("Propolis - 350 dinara");
                        break;
                    }
                }
            }
        });

        dodaj.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v){
                if(dodaj.getText().toString() == "Poruci") {
                    for (int i : poruci) poruci[i] = 0;
                    showAlertDialogButtonClicked(v);
                    odutani.setVisibility(Button.INVISIBLE);
                    right.setVisibility(Button.VISIBLE);
                    left.setVisibility(Button.VISIBLE);
                    proizvod.setVisibility(ImageView.VISIBLE);
                    opis.setVisibility(Button.VISIBLE);
                    korpa.setTextColor(R.color.black);
                    odutani.setVisibility(Button.INVISIBLE);
                    switch (current) {
                        case 0: {
                            proizvod.setImageDrawable(getResources().getDrawable(R.drawable.lipov));
                            naziv.setText("Lipov med - 820 dinara");
                            break;
                        }
                        case 1: {
                            proizvod.setImageDrawable(getResources().getDrawable(R.drawable.med));
                            naziv.setText("Kraljev med - 770 dinara");
                            break;
                        }
                        case 2: {
                            proizvod.setImageDrawable(getResources().getDrawable(R.drawable.propoli));
                            naziv.setText("Propolis - 350 dinara");
                            break;
                        }
                    }
                    dodaj.setText("Dodaj");
                    for (int i : poruci) poruci[i] = 0;
                } else poruci[current]++;
            }
        });

        korpa.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v){
                right.setVisibility(Button.INVISIBLE);
                left.setVisibility(Button.INVISIBLE);
                proizvod.setVisibility(ImageView.INVISIBLE);
                opis.setVisibility(Button.INVISIBLE);
                int suma = poruci[0]*820+poruci[1]*770+poruci[2]*350;
                naziv.setText("Lipov med: " + poruci[0]);
                naziv.setText("Kraljev med: " + poruci[1]);
                naziv.setText("Propolis: " + poruci[2]);
                naziv.setText("Poruceno je: "+ suma  + " dinara.");
                korpa.setTextColor(R.color.purple_200);
                odutani.setVisibility(Button.VISIBLE);
                dodaj.setText("Poruci");
            }
        });

        odutani.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v){
                right.setVisibility(Button.VISIBLE);
                left.setVisibility(Button.VISIBLE);
                proizvod.setVisibility(ImageView.VISIBLE);
                opis.setVisibility(Button.VISIBLE);
                korpa.setTextColor(R.color.black);
                odutani.setVisibility(Button.INVISIBLE);
                switch (current) {
                    case 0: {
                        proizvod.setImageDrawable(getResources().getDrawable(R.drawable.lipov));
                        naziv.setText("Lipov med - 820 dinara");
                        break;
                    }
                    case 1: {
                        proizvod.setImageDrawable(getResources().getDrawable(R.drawable.med));
                        naziv.setText("Kraljev med - 770 dinara");
                        break;
                    }
                    case 2: {
                        proizvod.setImageDrawable(getResources().getDrawable(R.drawable.propoli));
                        naziv.setText("Propolis - 350 dinara");
                        break;
                    }
                }
                dodaj.setText("Dodaj");
                for (int i : poruci) poruci[i] = 0;
            }
        });

        opis.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v){
                showAlertDialogOpis(v);
            }
        });
    }

    public void showAlertDialogButtonClicked(View view) {

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Porudzbina");
        int suma = poruci[0]*820+poruci[1]*770+poruci[2]*350;
        if(suma == 0) builder.setMessage("Korpa je prazna.");
        else builder.setMessage("Narucili ste.");

        // add a button
        builder.setPositiveButton("OK", null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void showAlertDialogOpis(View view) {

        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Opis i nacin koriscenja");

        switch (current) {
            case 0: {
                builder.setMessage("Lipov opis i nacin koriscenja.");
                break;
            }
            case 1: {
                builder.setMessage("Kraljev opis i nacin koriscenja.");
                break;
            }
            case 2: {
                builder.setMessage("Propolis opis i nacin koriscenja.");
                break;
            }
        }

        // add a button
        builder.setPositiveButton("OK", null);

        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
