package com.sixteen.tetrisbattleandroid;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class BattleMenu extends Activity {

	Button btcampaign, btmarathon, btback;
	MediaPlayer btClick, btQuit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		 //Set Fullscreen
      	requestWindowFeature(Window.FEATURE_NO_TITLE);
      	getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
      	
		setContentView(R.layout.activity_battle_menu);
		
		 //setting btClick SFX
		btClick = MediaPlayer.create(this, R.raw.buttonclick);
		btQuit = MediaPlayer.create(this, R.raw.quit);
		
		
		btcampaign = (Button) findViewById(R.id.btcampaign);
		setButtons(btcampaign);
		
		btmarathon = (Button) findViewById(R.id.btmarathon);
		setButtons(btmarathon);
		
		btback = (Button) findViewById(R.id.btbback);
		setButtons(btback);
		
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
				bt.setTextSize(40f);
				bt.setTextColor(Color.parseColor("#A06C23"));
				bt.setTypeface(txt);
			
			}

	
}
