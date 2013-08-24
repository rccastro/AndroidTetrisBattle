package com.sixteen.tetrisbattleandroid;

import com.sixteen.tetrisbattleandroid.TetrisTileView.TetrisPiece;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Tetris extends Activity {
        
		
        public TetrisTileView mTetrisView;
        public Dialogbox dialog;
        public static ImageView mHold, mNext;
        final Context context =this;
        String fontPath1;
        public static TextView lv, score, comlines, goal;
        private Button mStartButton;
        private Button mPauseButton;
        boolean crt = false;
        boolean shown = false;
        public TetrisPiece img;
        public CountDownTimer mCountDown;
        
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	
		//set Fullscreen
				requestWindowFeature(Window.FEATURE_NO_TITLE);
				getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
				
		setContentView(R.layout.activity_tetris);
		
		mHold = (ImageView) findViewById(R.id.holdView);
		mNext = (ImageView) findViewById(R.id.nextView);
		
		fontPath1 = "SFSlapstickComicShaded.ttf";
		Typeface txt = Typeface.createFromAsset(getAssets(), fontPath1);
		
		lv = (TextView) findViewById(R.id.tvLv);
		score = (TextView) findViewById(R.id.tvScore);
		comlines = (TextView) findViewById(R.id.comlines);
		goal =(TextView) findViewById(R.id.goals);
		
		lv.setTypeface(txt);
		score.setTypeface(txt);
		comlines.setTypeface(txt);
		goal.setTypeface(txt);
		
		//score.setText(Integer.toString(TetrisTileView.score));
		//Reset
		score.setText(Integer.toString(0));
		comlines.setText(Integer.toString(0));
	

		 mTetrisView = (TetrisTileView) findViewById(R.id.tetris_tile_view);
	       
	        
	        mStartButton = (Button) findViewById(R.id.start_button);
	        mStartButton.setFocusable(false);
	        mPauseButton = (Button) findViewById(R.id.pause_button);
	        mPauseButton.setFocusable(false);
	        mPauseButton.setVisibility(View.INVISIBLE);
	       
	        
	        mStartButton.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                view.setVisibility(View.INVISIBLE);
	                mPauseButton.setVisibility(View.VISIBLE);
	                mHold.setClickable(true);
	             /* //Reset
	                TetrisTileView.score=0;
	                TetrisTileView.setGoal(TetrisTileView.level);
	                //update scoreboard
	                score.setText(Integer.toString(TetrisTileView.score));
	            	Tetris.lv.setText(Integer.toString(TetrisTileView.level));

	            	goal.setText(Integer.toString(TetrisTileView.goal));
	            	mHold.setClickable(true);
	            	  
 	            	*/
	            	
	            	 mHold.setOnClickListener(new View.OnClickListener() {
	     	            public void onClick(View view) {
	     	           	     	            		     	            	
	     	               	if(mTetrisView.mFalling){
	     	               	try{
		     	            //	mTetrisView.hold();
	     	            	}
	     	            	finally{

	     	            		mHold.setImageDrawable(mTetrisView.loadImage(img.mImage));
	     	            	}
	     	               	}
	     	            	
	     	            	     	    
	     	            }
	     	        });
	                
	              mTetrisView.setMode(TetrisTileView.RUNNING);
	             
	                
	            }
	        });
	        
	      
	        mPauseButton.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	               	                	     
	            	onPause();
	    			
	            }
	        });
	            
	       
	        
	        if (savedInstanceState == null) {
	            // We were just launched -- set up a new game
	                mTetrisView.setMode(TetrisTileView.READY);
	                mTetrisView.setControlMethod(TetrisTileView.CONTROL_TOUCH);
	             
	        } else {
	            // We are being restored
	 
	        }
	    }



	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		crt = true;
		
	}



	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		

        mTetrisView.setMode(TetrisTileView.PAUSE);
        
        if(crt)
        	finish();
        else	
        	openDialog();
        
       
    
	}



	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		
	}



	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		//Reset
        TetrisTileView.score=0;
        TetrisTileView.setGoal(TetrisTileView.level);
        //update scoreboard
        score.setText(Integer.toString(TetrisTileView.score));
    	Tetris.lv.setText(Integer.toString(TetrisTileView.level));

    	goal.setText(Integer.toString(TetrisTileView.goal));
		crt= false;
	}

	
	void openDialog(){
		 // custom dialog
		 final Dialog dialog = new Dialog(context);
	        if(shown){	
	        	return;
	        }
	        else {
	        	
	        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        dialog.setContentView(R.layout.activity_dialogbox);
	          	    
			    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
			  	dialog.setCanceledOnTouchOutside(false);

				dialog.setCancelable(false);
				
				
			  	shown = true;
			  	Button dialogButton = (Button) dialog.findViewById(R.id.resumebtdialog);
			  	Button dialogRestart = (Button) dialog.findViewById(R.id.restartbt);
			  	Button dialogExit = (Button) dialog.findViewById(R.id.quitbt);
				// if button is clicked, close the custom dialog
				
			  dialogButton.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dialog.dismiss();

					shown=false;
					mTetrisView.setMode(TetrisTileView.RUNNING);
					
				}
				  
			  });
			  
			  dialogRestart.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.dismiss();
						
		                
		                //Reset
	                    TetrisTileView.score=0;
	                    TetrisTileView.lines=0;
	                    TetrisTileView.level = 1;
	                    shown= false;
		                TetrisTileView.setGoal(TetrisTileView.level);
	                    
	                    //update scoreboard
	                    score.setText(Integer.toString(TetrisTileView.score));
	                    comlines.setText(Integer.toString(TetrisTileView.lines));
	                    goal.setText(Integer.toString(TetrisTileView.goal));
	                    mHold.setImageDrawable(mTetrisView.loadImage(0));

	        			mTetrisView.setMode(TetrisTileView.READY);

	        			mTetrisView.setMode(TetrisTileView.RUNNING);
					}
					  
				  });
			  
			  dialogExit.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						crt =true;
						shown= false;
						finish();
						
					}
					  
				  });
			  
			  		
			  			dialog.show();
			  			
			  }
	
	}
	
/*	public Drawable loadImage(int key){
	
		Resources r = this.getBaseContext().getResources();
		   
		   if(key == 1)
			   return  r.getDrawable(R.drawable.j_block); 
			 
			else if(key == 2)
			   return  r.getDrawable(R.drawable.l_block);   
			   
			else if(key == 3)
			   return  r.getDrawable(R.drawable.z_block);  
			else if(key == 4)
			   return  r.getDrawable(R.drawable.s_block);  
			else if(key == 5)
			   return  r.getDrawable(R.drawable.t_block);	   
			else if(key == 6)
			   return  r.getDrawable(R.drawable.o_block); 
			else if(key == 7)
			   return  r.getDrawable(R.drawable.i_block);	   
			else
				   return  r.getDrawable(R.drawable.alpha);			   
			   
		   
	       
	 }*/
	 
		

	}