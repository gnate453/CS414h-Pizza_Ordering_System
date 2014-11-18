package com.pizza.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.demo.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void viewMenu(View view) {
		Intent intent = new Intent(this, ViewMenuActivity.class);
	    startActivity(intent);
	}
}
