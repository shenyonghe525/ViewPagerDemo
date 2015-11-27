package com.syhviewpagerdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;

public class LoadActivity extends Activity {

	private static final int TIME = 2000;
	private static final int GO_HOME = 1000;
	private static final int GO_GUIDE = 1001;

	private Handler mHandler = new Handler()
	{
		public void handleMessage(android.os.Message msg)
		{
			switch (msg.what)
			{
			case GO_HOME:
				goHome();
				break;

			case GO_GUIDE:
				goGuide();
				break;
			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.load);
		init();
	}

	private void init()
	{
		SharedPreferences sp = getSharedPreferences("WA", MODE_PRIVATE);
		boolean isFirst = sp.getBoolean("isFirst", true);
		if (isFirst)
		{
			mHandler.sendEmptyMessageDelayed(GO_GUIDE, TIME);
			Editor editor = sp.edit();
			editor.putBoolean("isFirst", false);
			editor.commit();
		} else
		{
			mHandler.sendEmptyMessageDelayed(GO_HOME, TIME);
		}
	}

	private void goHome()
	{
		Intent intent = new Intent(LoadActivity.this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	private void goGuide()
	{
		Intent intent = new Intent(LoadActivity.this, GuideActivity.class);
		startActivity(intent);
		finish();
	}
}
