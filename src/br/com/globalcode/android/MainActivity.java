package br.com.globalcode.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		bindWidgetEvents();
	}
	
	private void bindWidgetEvents() {
		
		findViewById(R.id.buttonSchedule).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(isUserInputsValid()) {
					
					createNotification();
				} else {
					
					showDialogInvalidUserInputs();
				}
			}
		});
	}

	protected void showDialogInvalidUserInputs() {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Aviso");
		builder.setMessage("Informe os campos antes de agendar.");
		builder.setPositiveButton("Ok", null);
		builder.create();
		builder.show();
	}

	protected boolean isUserInputsValid() {
		
		return isTimeValid() && isMessageValid();
	}
	
	protected boolean isTimeValid() {
		
		return ((EditText)findViewById(R.id.editTextTime)).getText().toString().trim().length() > 0; 
	}
	
	protected boolean isMessageValid() {
		
		return ((EditText)findViewById(R.id.editTextMessage)).getText().toString().trim().length() > 0;
	}

	protected void createNotification() {
		
		int time = Integer.valueOf(((EditText)findViewById(R.id.editTextTime)).getText().toString());
		String message = ((EditText)findViewById(R.id.editTextMessage)).getText().toString();
		
		Intent i = new Intent(this, NotificationService.class);
		i.putExtra("message", message);
		i.putExtra("time", time);

		startService(i);
	}

}
