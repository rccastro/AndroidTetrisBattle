package com.sixteen.tetrisbattleandroid;


import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;


public class MainMenu extends Activity {

	Button btstart, batt, stats, opt, quit;
	MediaPlayer btClick, btQuit;
	
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Set Fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main_menu);
		
		//setting btClick SFX
				btClick = MediaPlayer.create(this, R.raw.buttonclick);
				btQuit = MediaPlayer.create(this, R.raw.quit);
				//setting buttons
				btstart = (Button) findViewById(R.id.btstart);
				setButtons(btstart);
				
				batt = (Button) findViewById(R.id.btbatt);
				setButtons(batt);
				
				stats = (Button) findViewById(R.id.btstat);
				setButtons(stats);
				
				opt = (Button) findViewById(R.id.btopt);
				setButtons(opt);
				
				quit = (Button) findViewById(R.id.btquit);
				setButtons(quit);
				
				//Setting Onclick of Each Button
				//onclick btStart
				btstart.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						
						openIntent("com.sixteen.tetrisbattleandroid.STARTACTIVITY");
					}
				});
				
								
				//onclick btbatt
				batt.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
										
						
						openIntent("com.sixteen.tetrisbattleandroid.BATTLEMENU");
					}
				});
				
				
				//onclick btstat
				stats.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						openIntent("com.sixteen.tetrisbattleandroid.STATACTIVITY");
					}
				});
				
				//onclick optbt
				opt.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
					
						openIntent("com.sixteen.tetrisbattleandroid.OPTIONSACTIVITY");	
						
					}
				});
							
				//onclick btquit
				quit.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						btQuit.start();	//Play SFX
						finish();
			            System.exit(0);
					}
				});
			}


	public void openIntent(String pth){
		
		try{
			btClick.start();	//Play SFX
			Intent mainApp = new Intent(pth);
			startActivity(mainApp);
		} catch(Exception e){
			e.printStackTrace();
		}
			
	}	
	
		//setting up the button attributes
		public void setButtons(Button bt){
			String fontPath = "SFSlapstickComicShaded.ttf";
			Typeface txt = Typeface.createFromAsset(getAssets(), fontPath);
			bt.setTextSize(40f);
			bt.setTextColor(Color.parseColor("#A06C23"));
			bt.setTypeface(txt);
		
		}	

}
