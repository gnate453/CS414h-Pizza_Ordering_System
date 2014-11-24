package com.example.pt;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	public void sendMessage(View view) 
	{
	    Intent intent = new Intent(MainActivity.this, AddItems.class);
	    EditText t = (EditText) findViewById(R.id.rname);
	    String name = t.getText().toString();
	    t = (EditText) findViewById(R.id.rphone);
	    String phone = t.getText().toString();
	    t = (EditText) findViewById(R.id.raddr);
	    String addr = t.getText().toString();
	    t = (EditText) findViewById(R.id.rcity);
	    String city = t.getText().toString();
	    t = (EditText) findViewById(R.id.rstate);
	    String state = t.getText().toString();
	    t = (EditText) findViewById(R.id.rzip);
	    String zip = t.getText().toString();
	    TextView text = (TextView) findViewById(R.id.rErr);
	    text.setText("");
	    boolean e = false;
	    if(name.equals("")){
	    	e = true;
	    	text.append("Name Error ");
	    }if(phone.length() <10 || phone.length()>11){
	    	e = true;
	    	text.append("Phone Error ");
	    }if(addr.equals("")){
	    	e = true;
	    	text.append("Address Error ");
	    }if(city.equals("")){
	    	e = true;
	    	text.append("City Error ");
	    }if(state.equals("")){
	    	e = true;
	    	text.append("State Error ");
	    }if(zip.length() != 5){
	    	e = true;
	    	text.append("Zip Error");
	    }  
	    if(e == false){
		    ArrayList<String> customer = new ArrayList<String>();
			customer.add(name);
			customer.add(phone);
			customer.add(addr);
			customer.add(city);
			customer.add(state);
			customer.add(zip);
			intent.putStringArrayListExtra("cust", customer);
			intent.putExtra("Prev","MA");
			intent.putExtra("user",getIntent().getStringExtra("user"));
			intent.putExtra("islog", getIntent().getStringExtra("islog"));
			startActivity(intent);
	    }
	}	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
