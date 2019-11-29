package com.example.nikhil.musictron;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class main extends AppCompatActivity {

    Button login;
    TextView here;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (Button)findViewById(R.id.login);
        here = (TextView)findViewById(R.id.herelink);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplication(), com.example.nikhil.musictron.login.class);
                getApplication().startActivity(i);
            }
        });

        here.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplication(),signup.class);
                getApplication().startActivity(intent);
            }
        });
    }
}
