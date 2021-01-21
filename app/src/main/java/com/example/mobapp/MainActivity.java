package com.example.mobapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageButton kupac;
    EditText name;
    EditText pa;
    TextView poruka;

    String NameAdmin = "kupac";
    String PaAdmin = "kupac123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kupac = findViewById(R.id.kupac);
        name = findViewById(R.id.name);
        pa = findViewById(R.id.password);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            NameAdmin = getIntent().getStringExtra("NameAdmin");
            PaAdmin = getIntent().getStringExtra("PaAdmin");
            //The key argument here must match that used in the other activity
        }

        kupac.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(name.getText().toString().equals(NameAdmin) && pa.getText().toString().equals(PaAdmin)) {
                    Intent intent = new Intent(MainActivity.this, KupacActivity.class);
                    intent.putExtra("NameAdmin", getIntent().getStringExtra("NameAdmin"));
                    intent.putExtra("PaAdmin", getIntent().getStringExtra("PaAdmin"));
                    intent.putExtra("Name", getIntent().getStringExtra("Name"));
                    intent.putExtra("Surname", getIntent().getStringExtra("Surname"));
                    intent.putExtra("Number", getIntent().getStringExtra("Number"));
                    intent.putExtra("Adress", getIntent().getStringExtra("Adress"));
                    startActivity(intent);
                } else {
                    poruka = findViewById(R.id.poruka);
                    poruka.setVisibility(TextView.VISIBLE);
                }
            }
        });
    }

}