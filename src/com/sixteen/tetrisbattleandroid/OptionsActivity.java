package com.sixteen.tetrisbattleandroid;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class OptionsActivity extends Activity {

	TextView lb;
	Boolean sFx = true;
	Button btOn, btOff, btRgame, btBack;
	String fontPath, fontPath1;
	MediaPlayer btSFX;
		
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //set Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
        setContentView(R.layout.activity_options);
        
        btSFX = MediaPlayer.create(this, R.raw.quit);
        fontPath = "KBAStitchInTime.ttf";
		fontPath1 = "SFSlapstickComicShaded.ttf";
		Typeface txt = Typeface.createFromAsset(getAssets(), fontPath);
		Typeface btTF = Typeface.createFromAsset(getAssets(), fontPath1);
		lb = (TextView) findViewById(R.id.tvLb);
		lb.setTypeface(txt);
		
		btOn = (Button) findViewById(R.id.btOn);
		btOn.setTypeface(btTF);
		
		btOn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				btSFX.start();
				sFx = true;
				btOn.setBackgroundResource(R.drawable.bt_pressed);
				
				if(sFx)
				{
					btOff.setBackgroundResource(R.drawable.alpha);
					
				}
			}
		});
		
		btOff = (Button) findViewById(R.id.btOff);
		btOff.setTypeface(btTF);
		
		btOff.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				btSFX.start();
				sFx = false;
				btOff.setBackgroundResource(R.drawable.bt_pressed);
				
				if(!(sFx))
				{
					btOn.setBackgroundResource(R.drawable.alpha);
					
				}
			}
		});
			
		btRgame = (Button) findViewById(R.id.btRset);
		btRgame.setTypeface(btTF);
		
		btBack = (Button) findViewById(R.id.btBack);
		btBack.setTypeface(btTF);
		
		btBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btSFX.start();
				finish();
			}
		});
    }}

