package com.rjxy.agriculture.ui.activity;

import com.rjxy.agriculture.ClientApp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Window;

public class BaseActivity extends Activity {
	public ClientApp mApp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mApp = (ClientApp) getApplicationContext();
	}
}
