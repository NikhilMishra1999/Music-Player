package com.example.nikhil.musictron;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class FirstActivity extends AppCompatActivity {


    Button playbutton,pausebutton,stopbutton;
    MediaPlayer mediaplayer;
    Button logout;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private void play(){mediaplayer.start();};
    private void pause(){if(mediaplayer.isPlaying()){mediaplayer.pause();}}
    private void stop(){if(mediaplayer.isPlaying()){mediaplayer.stop();}}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Intent i = getIntent();

        logout = (Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i =  new Intent(FirstActivity.this,main.class);
                startActivity(i);
            }
        });

        //Collecting values from FirstActivity--------
        final String p = i.getStringExtra("username");
        TextView textView = (TextView)findViewById(R.id.tv1);
        textView.setText(p);

        final String r = i.getStringExtra("password");
        TextView textView2 = (TextView)findViewById(R.id.tv2);
        textView2.setText(r);

        pausebutton=findViewById(R.id.pausebutton);
        playbutton=findViewById(R.id.playbutton);
        stopbutton=findViewById(R.id.stopbutton);

        mediaplayer=MediaPlayer.create(this,R.raw.nondroid);

        playbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }
        });

        pausebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pause();
            }
        });

        stopbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stop();
            }
        });
    }
}
