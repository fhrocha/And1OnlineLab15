package br.com.globalcode.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowNotificationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_notification_layout);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		
		super.onPostCreate(savedInstanceState);
		showPreviousNotification();		
	}
	
	private void showPreviousNotification() {
		
		Intent intent = getIntent();
		String message = intent.getStringExtra("message");
		
		if( message != null && message.trim().length() > 0 ) {
			
			((TextView)findViewById(R.id.textViewMessage)).setText(message);
		}
	}	
}
