package com.example.nikhil.musictron;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.text.SimpleDateFormat;
import java.util.Date;

public class signup extends AppCompatActivity {

    TextView link;
    EditText name;
    EditText email;
    EditText password;
    EditText phone;
    Button sign;
    Button reset;
    FirebaseAuth mFirebaseAuth;
    DatabaseReference dataReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        link = (TextView)findViewById(R.id.link);
        sign = (Button)findViewById(R.id.sign_up);
        reset = (Button)findViewById(R.id.reset);

        name = (EditText)findViewById(R.id.name);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        phone = (EditText)findViewById(R.id.phone);

        mFirebaseAuth = FirebaseAuth.getInstance();
        dataReference = FirebaseDatabase.getInstance().getReference("data");

        //Setting constraints to the RESET Button---------
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                email.setText("");
                password.setText("");
                phone.setText("");
            }
        });

        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplication(), login.class);
                getApplication().startActivity(i);
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nam = name.getText().toString();
                final String em = email.getText().toString();
                final String pass = password.getText().toString();
                final String pn = phone.getText().toString();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss aa");
                final String checkin = simpleDateFormat.format(new Date());

                //Checking if the EditText Fields are Empty------
                if (TextUtils.isEmpty(nam)) {
                    name.requestFocus();
                    name.setError("This field can't be empty");
                    Toast.makeText(getApplication(),"Enter the required fields",Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(em)) {
                    email.setError("This field can't be empty");
                    email.requestFocus();
                    Toast.makeText(getApplication(),"Enter the required fields",Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    password.setError("This field can't be empty");
                    password.requestFocus();
                    Toast.makeText(getApplication(),"Enter the required fields",Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(pn)) {
                    phone.setError("This field can't be empty");
                    phone.requestFocus();
                    Toast.makeText(getApplication(),"Enter the required fields",Toast.LENGTH_LONG).show();
                    return;
                }

                else if (!(em.isEmpty() && pass.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(em,pass).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            String id = dataReference.push().getKey();
                            data d = new data(nam,em,pass,pn,checkin);
                            dataReference.child(id).setValue(d);
                            Toast.makeText(getApplication(),"Account Created Successfully",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(signup.this,FirstActivity.class));

                        }
                    });
                }
                else {
                    Toast.makeText(getApplication(),"Error Occurred !!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
