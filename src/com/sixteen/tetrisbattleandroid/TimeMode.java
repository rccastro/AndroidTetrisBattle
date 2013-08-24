package com.sixteen.tetrisbattleandroid;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TimeMode extends Activity {
	
	 final static long LIMIT = 120000;
	 long pause;
	 public TetrisTileView mTetrisView;
     public Dialogbox dialog;
     public static ImageView mHold, mNext;
     public CountDownTimer countdwn;
     final Context context =this;
     String fontPath1;
     public static TextView timer, score, comlines, goal;
     private Button mStartButton;
     private Button mPauseButton;
     boolean crt = false;
     boolean shown = false;
     MyCount count;
  //   WakeLock wL;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//set Fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//wake-lock
		//PowerManager pM = (PowerManager)getSystemService(Context.POWER_SERVICE);
		//wL = pM.newWakeLock(PowerManager.FULL_WAKE_LOCK, "aaar");
		
		setContentView(R.layout.activity_time_mode);
	//	wL.acquire();
		
		
		mHold = (ImageView) findViewById(R.id.holdViewTM);
		mNext = (ImageView) findViewById(R.id.nextViewTM);
		
		fontPath1 = "SFSlapstickComicShaded.ttf";
		Typeface txt = Typeface.createFromAsset(getAssets(), fontPath1);
		
		score = (TextView) findViewById(R.id.tvScoreTM);
		comlines = (TextView) findViewById(R.id.comlinesTM);
		timer = (TextView) findViewById(R.id.coundownTM);
		
		score.setTypeface(txt);
		comlines.setTypeface(txt);
		timer.setTypeface(txt);
		
		
		score.setText(Integer.toString(0));
		comlines.setText(Integer.toString(0));
	

		 mTetrisView = (TetrisTileView) findViewById(R.id.tetris_tile_viewTM);
	       
	        
	        mStartButton = (Button) findViewById(R.id.start_buttonTM);
	        mStartButton.setFocusable(false);
	        mPauseButton = (Button) findViewById(R.id.pause_buttonTM);
	        mPauseButton.setFocusable(false);
	        mPauseButton.setVisibility(View.INVISIBLE);
	        
	        mStartButton.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                view.setVisibility(View.INVISIBLE);
	                mPauseButton.setVisibility(View.VISIBLE);
	                mHold.setClickable(true);
	             
	                       
	              mTetrisView.setMode(TetrisTileView.RUNNING);
	              count =  new MyCount(LIMIT, 1000); 
	              count.start();
	         
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
		//wL.release();
		count.cancel();
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
        //update scoreboard
        score.setText(Integer.toString(TetrisTileView.score));
    	
		crt= false;
	}

	public String formatTime(long millis) {  
	    String output = "00:00";  
	    long seconds = millis / 1000;  
	    long minutes = seconds / 60;  

	    seconds = seconds % 60;  
	    minutes = minutes % 60;  

	    String sec = String.valueOf(seconds);  
	    String min = String.valueOf(minutes);  

	    if (seconds < 10)  
	        sec = "0" + seconds;  
	    if (minutes < 10)  
	        min= "0" + minutes;  

	    output = min + ":" + sec;  
	    return output;
	}//formatTime  
	
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
					count =  new MyCount(pause, 1000); 
		            count.start();
					
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
		                   
		                    shown= false;
			             
		                    
		                    //update scoreboard
		                    score.setText(Integer.toString(TetrisTileView.score));
		                    comlines.setText(Integer.toString(TetrisTileView.lines));
		                  
		              
		                	 TetrisTileView.score=0;
			                 TetrisTileView.lines=0;
			                 score.setText(Integer.toString(TetrisTileView.score));
			                 comlines.setText(Integer.toString(TetrisTileView.lines));
	                    
			            
	        			mTetrisView.setMode(TetrisTileView.READY);
	        			count =  new MyCount(LIMIT, 1000); 
	  	              	count.start();
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

	void timesUp(){
		 // custom dialog
		 final Dialog dialog = new Dialog(context);
	        if(shown){	
	        	return;
	        }
	        else {
	        	
	        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        dialog.setContentView(R.layout.activity_out_of_time);
	          	    
			dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		  	dialog.setCanceledOnTouchOutside(false);
			dialog.setCancelable(false);
				
				
			  	shown = true;
			  	Button dialogRestart = (Button) dialog.findViewById(R.id.btTryAgain);
			  	Button  dialogExit = (Button) dialog.findViewById(R.id.btExitTM);
				// if button is clicked, close the custom dialog
				
			  dialogRestart.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						dialog.dismiss();
						
		                
		                	 //Reset
		                    TetrisTileView.score=0;
		                    TetrisTileView.lines=0;
		                   
		                    
		                    //update scoreboard
		                    score.setText(Integer.toString(TetrisTileView.score));
		                    comlines.setText(Integer.toString(TetrisTileView.lines));
		                  
		              
		                	 TetrisTileView.score=0;
			                 TetrisTileView.lines=0;
			                 score.setText(Integer.toString(TetrisTileView.score));
			                 comlines.setText(Integer.toString(TetrisTileView.lines));
	                    
			            
	        			mTetrisView.setMode(TetrisTileView.READY);
	        			count =  new MyCount(LIMIT, 1000); 
	  	              	count.start();
	        			mTetrisView.setMode(TetrisTileView.RUNNING);
	        			shown = false;
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
	
	class MyCount extends CountDownTimer {  
	    public MyCount(long millisInFuture, long countDownInterval) {  
	        super(millisInFuture, countDownInterval);  
	    }//MyCount  

	  
	    public void onTick(long millisUntilFinished) {             
	        timer.setText(formatTime(millisUntilFinished));   
	        pause = millisUntilFinished;
	    }//on tick  

	    @Override  
	    public void onFinish() {  
	    	timer.setText("00:00");
	    	mTetrisView.setMode(TetrisTileView.PAUSE);
	    	timesUp();
	  			
	    }//finish  
	}//class MyCount 
	
}
