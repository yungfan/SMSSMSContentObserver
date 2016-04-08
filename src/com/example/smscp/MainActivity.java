package com.example.smscp;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity
{
	private EditText code;

	@SuppressLint("HandlerLeak")
	Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg)
		{
			if (msg.what == 1)
			{
				code.setText(msg.obj.toString());
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		code = (EditText) findViewById(R.id.smsCode);

		SMSContentObserver smsContentObserver = new SMSContentObserver(
				MainActivity.this, handler);

		MainActivity.this.getContentResolver().registerContentObserver(
				Uri.parse("content://sms/"), true, smsContentObserver);
	}
}
