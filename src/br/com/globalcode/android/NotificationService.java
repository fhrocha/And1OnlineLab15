package br.com.globalcode.android;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class NotificationService extends Service {

	private static final int AGENDA_NOTIFICATION = 1;

	@Override
	public IBinder onBind(Intent intent) {

		return null;
	}
	
	@Override
	public int onStartCommand(final Intent intent, final int flags, final int startId) {
		
		final long timeInMiliseconds = intent.getIntExtra("time", 10) * 1000;
		
		TimerTask scheduledNotification = new TimerTask() {
			
			@Override
			public void run() {
			
				generateNotification(intent);
			}
		};
		
		Timer timer = new Timer();
		timer.schedule(scheduledNotification, new Date(System.currentTimeMillis() + timeInMiliseconds));
		
		return 0;
	}

	private void generateNotification(Intent intent) {
		
		String message = intent.getStringExtra("message");
		long time = intent.getIntExtra("time", 10);
		
		CharSequence tickerText = message;
		int icon = R.drawable.ic_stat_name;
		Notification notification = new Notification(icon, tickerText, System.currentTimeMillis());
		
		Context context = getApplicationContext();
		CharSequence title = "Agenda";
		CharSequence text = message;
		
		Intent notificationIntent = new Intent(this, ShowNotificationActivity.class);
		notificationIntent.putExtra("message", message);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

		notification.setLatestEventInfo(context, title, text, contentIntent);
		
		NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		nManager.notify(AGENDA_NOTIFICATION, notification);
	}
	
}
