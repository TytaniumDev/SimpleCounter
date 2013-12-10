package com.tywholland.simplecounter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SimpleCounterActivity extends Activity {
	private static final String COUNTER_PREFS_KEY = "counter";
	private View mCounterButton;
	private Button mResetButton;
	private TextView mCounterText;
	int mCount = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_simple_counter);
		mCounterButton = findViewById(R.id.counter_button);
		mResetButton = (Button) findViewById(R.id.reset_button);
		mCounterText = (TextView) findViewById(R.id.counter_button_text);

		mCounterButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mCount++;
				mCounterText.setText("" + mCount);
			}
		});

		mCounterButton.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				mCount--;
				mCounterText.setText("" + mCount);
				return true;
			}
		});

		mResetButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mCount = 0;
				mCounterText.setText("" + mCount);
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Get last counter from shared prefs
		SharedPreferences prefs = getPreferences(MODE_PRIVATE);
		mCount = prefs.getInt(COUNTER_PREFS_KEY, 0);
		mCounterText.setText("" + mCount);
	}

	@Override
	protected void onPause() {
		SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
		editor.putInt(COUNTER_PREFS_KEY, mCount);
		editor.commit();
		super.onPause();
	}
}
