package com.example.pt;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Login extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);		
	}
	public void sendMessage(View view) 
	{
	    Intent intent = new Intent(Login.this, MainActivity.class);
	    intent.putExtra("Prev","LI");
	    intent.putExtra("islog", "false");
	    startActivity(intent);
	}
	public void sendMessage2(View view) 
	{
	    Intent intent = new Intent(Login.this, ViewMenuActivity.class);
	    intent.putExtra("islog", "false");
	    startActivity(intent);
	}
	public void sendMessage3(View view) 
	{
	    Intent intent = new Intent(Login.this, SignIn.class);
	    startActivity(intent);
	}
	public void sendMessage4(View view) 
	{
	    Intent intent = new Intent(Login.this, Register.class);
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
