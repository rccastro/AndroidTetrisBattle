package com.sixteen.tetrisbattleandroid;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import android.view.View.OnClickListener;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ViewFlipper;
import android.app.Activity;


public class StatActivity extends Activity implements OnClickListener{

	ViewFlipper flippy;
	Button btBack;
	MediaPlayer btQuit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 //Set Fullscreen
      	requestWindowFeature(Window.FEATURE_NO_TITLE);
      	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      	
		setContentView(R.layout.activity_stat);
		
		btQuit = MediaPlayer.create(this, R.raw.quit);
		
		flippy = (ViewFlipper) findViewById(R.id.vfstats);
		
		flippy.setOnClickListener(this);
		
		btBack = (Button) findViewById(R.id.btSBack);
		
		btBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btQuit.start();	//Play SFX
				finish();
			}
		});
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		flippy.showNext();
	}
	


	
}
