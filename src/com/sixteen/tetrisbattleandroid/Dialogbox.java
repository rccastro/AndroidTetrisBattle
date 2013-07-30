package com.sixteen.tetrisbattleandroid;

import android.os.Bundle;


import android.widget.Button;
import android.app.Activity;


public class Dialogbox extends Activity{


	Button btResume, btRestart, btQuit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialogbox);
		//setFinishOnTouchOutside(false);
				
		 btResume = (Button) findViewById(R.id.resumebt);
		 btRestart = (Button) findViewById(R.id.restartbt);
		 btQuit = (Button) findViewById(R.id.quitbt);
		 
		
		 
		 }

	

}
