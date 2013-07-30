package com.sixteen.tetrisbattleandroid;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;


public class StartActivity extends Activity {
	
	
	Button btclassic, bttime, btback;
	MediaPlayer btClick, btQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
      //Set Fullscreen
      	requestWindowFeature(Window.FEATURE_NO_TITLE);
      	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
       	setContentView(R.layout.activity_start);
        
      //setting btClick SFX
		btClick = MediaPlayer.create(this, R.raw.buttonclick);
		btQuit = MediaPlayer.create(this, R.raw.quit);
		
		//setting buttons
		btclassic = (Button) findViewById(R.id.btclassic);
		setButtons(btclassic);
		
		bttime = (Button) findViewById(R.id.bttimed);
		setButtons(bttime);
		
		btback = (Button) findViewById(R.id.btback);
		setButtons(btback);
       	
		
		//Setting Onclick of Each Button
		//onclick btclassic
		btclassic.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btClick.start();	//Play SFX
				
				try{
					Intent openMain = new Intent("com.sixteen.tetrisbattleandroid.TETRIS");
					startActivity(openMain);
				}catch(Exception e){
					e.printStackTrace();
								
				}
				
			}
		});
		
		
		//onclick bttime
		bttime.setOnClickListener(new View.OnClickListener() {
					
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btClick.start();	//Play SFX
			}
		});
				
		//onclick btback
		btback.setOnClickListener(new View.OnClickListener() {
					
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btQuit.start();	//Play SFX
				finish();
			}
		});
		
		
    }
    
  //setting up the button attributes
  		public void setButtons(Button bt){
  			String fontPath = "SFSlapstickComicShaded.ttf";
  			Typeface txt = Typeface.createFromAsset(getAssets(), fontPath);
  			bt.setTextSize(30f);
  			bt.setTextColor(Color.parseColor("#A06C23"));
  			bt.setTypeface(txt);
  		
  		}
  	
 
}
