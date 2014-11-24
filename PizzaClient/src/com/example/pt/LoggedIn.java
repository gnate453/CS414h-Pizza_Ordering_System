package com.example.pt;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class LoggedIn extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logged_in);		
		String usr = getIntent().getStringExtra("user");
		if(!usr.equals(null)){
			 TextView text = (TextView) findViewById(R.id.usrLI);
			  text.setText("Welcome "+usr+"!");
		}
	}
	public void sendMessage(View view) 
	{
	    Intent intent = new Intent(LoggedIn.this, MainActivity.class);
	    intent.putExtra("Prev","LI");
	    intent.putExtra("islog", "true");
	    startActivity(intent);
	}
	public void sendMessage2(View view) 
	{
	    Intent intent = new Intent(LoggedIn.this, ViewMenuActivity.class);
	    intent.putExtra("islog", "true");
	    startActivity(intent);
	}
	public void sendMessage3(View view) 
	{
	    Intent intent = new Intent(LoggedIn.this, Login.class);
	    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
	    startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
