package com.example.encuesta;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Start extends Activity {
	
	Bundle bundle;
    protected PowerManager.WakeLock wakelock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_start);
		
		bundle = getIntent().getExtras();
		
		buttonsOff();
	}

	private void buttonsOff() {
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

	public void Start(View view) {
		Intent i = new Intent(getBaseContext(), Encuesta.class);
		// i.putExtra("PersonID", personID);
		i.putExtra("namePerson", bundle.getString("namePerson"));
		startActivity(i);
		overridePendingTransition(R.anim.slide_in_right,
				R.anim.activity_animation_zoom_out);

	}
	
	@SuppressLint("Wakelock")
	protected void onDestroy(){
        super.onDestroy();
        this.wakelock.release();
    }
}
