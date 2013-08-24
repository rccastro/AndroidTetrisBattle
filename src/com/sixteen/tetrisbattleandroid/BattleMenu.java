package com.sixteen.tetrisbattleandroid;



import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class BattleMenu extends Activity {
	
	
	 final Activity me = this;
     public static final String TAG = "TetrisBattle";

     static final int REQUEST_DISCOVER_DEVICE = 1;
     final static int REQUEST_ENABLE_BT = 2;
     // Member object for the chat services
     //private BluetoothService mChatService = null;
  //   private BluetoothConnection mBTconnection = null;
 
     // Local Bluetooth adapter
     private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	
	

	Button btcreate, btjoin, btback;
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
		
		
		btcreate = (Button) findViewById(R.id.btcreate);
		setButtons(btcreate);
		
		btjoin = (Button) findViewById(R.id.btjoin);
		setButtons(btjoin);
		
		btback = (Button) findViewById(R.id.btbback);
		setButtons(btback);
		
		
		//onclick btcreate
		btcreate.setOnClickListener(new View.OnClickListener() {
					
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btClick.start();	//Play SFX
				
				
	//			BattleMode.mIsHost = true;
				
				  if (mBluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE)  {
					Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
					discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
					startActivity(discoverableIntent);
				}
				  else
				  {
					  
					  openIntent("com.sixteen.tetrisbattleandroid.BATTLEMODE");	
					  
				  }
				
			}
		});
		
		//onclick btjoin
				btjoin.setOnClickListener(new View.OnClickListener() {
							
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						btClick.start();	//Play SFX
						
							

						  openIntent("com.sixteen.tetrisbattleandroid.BATTLEMODE");	
						
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
				bt.setTextSize(40f);
				bt.setTextColor(Color.parseColor("#A06C23"));
				bt.setTypeface(txt);
			
			}

			@Override
			protected void onStart() {
				// TODO Auto-generated method stub
				super.onStart();
				
				if (mBluetoothAdapter == null) {
				    // Device does not support Bluetooth
					finish();
				}
				if (!mBluetoothAdapter.isEnabled()) {
					Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
					//discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
					startActivityForResult(discoverableIntent, REQUEST_ENABLE_BT);
					
				}
			
			}

			@Override
			protected void onActivityResult(int requestCode, int resultCode,
					Intent data) {
				// TODO Auto-generated method stub
				super.onActivityResult(requestCode, resultCode, data);
				
				 switch (requestCode) {
				 case REQUEST_DISCOVER_DEVICE:
					 if(resultCode == Activity.RESULT_OK) {//TODO add checking BT enabled flag
			        		// Get the device MAC address
			                String address = data.getExtras()
			                    .getString(DeviceList.EXTRA_DEVICE_ADDRESS);
			                // Get the BLuetoothDevice object
			                BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(address);
			                // Attempt to connect to the device
			        	//	mBTconnection.connect(device);
			        		
			        		break;
			        	}
					 //openIntent("com.sixteen.tetrisbattleandroid.BATTLEMODE");	
					// break;
				   case REQUEST_ENABLE_BT:
					   if (resultCode == RESULT_CANCELED) {    
					    	 finish();
					    	 Toast.makeText(this, "bluetooth not enabled", 5000).show();
					     }
						 break;
				 }
			       
				//if (requestCode == REQUEST_ENABLE_BT) {

				  
				  //}
				
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
		
			
			
}
