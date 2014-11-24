package com.example.pt;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class CashorCredit extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cashor_credit);
	}
	public void sendMessage(View view) 
	{
	    Intent intent = new Intent(CashorCredit.this, Credit.class);
	    ArrayList<String> c = getIntent().getStringArrayListExtra("cust");
        intent.putStringArrayListExtra("cust", c);
        intent.putExtra("cost", getIntent().getStringExtra("cost"));
        intent.putStringArrayListExtra("items", getIntent().getStringArrayListExtra("items"));
        intent.putExtra("islog", getIntent().getStringExtra("islog"));
        intent.putExtra("red", getIntent().getStringExtra("red"));
        intent.putExtra("user",getIntent().getStringExtra("user"));
	    startActivity(intent);
	}
	public void sendMessage2(View view) 
	{
	    Intent intent = new Intent(CashorCredit.this, Review.class);
	    ArrayList<String> c = getIntent().getStringArrayListExtra("cust");
        intent.putStringArrayListExtra("cust", c);
        intent.putExtra("cost", getIntent().getStringExtra("cost"));
        intent.putStringArrayListExtra("items", getIntent().getStringArrayListExtra("items"));
        intent.putExtra("islog", getIntent().getStringExtra("islog"));
        intent.putExtra("red", getIntent().getStringExtra("red"));
        intent.putExtra("user",getIntent().getStringExtra("user"));
	    startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cashor_credit, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
