package com.example.pt;

import java.util.ArrayList;
import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Credit extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_credit);
	}
	public void sendMessage(View view) 
	{
		Intent intent = new Intent(Credit.this, Review.class);
		EditText t = (EditText) findViewById(R.id.Cardname);
		ArrayList<String> cInfo = new ArrayList<String>();
		String name = t.getText().toString();
		cInfo.add(name);
		t = (EditText) findViewById(R.id.CardNumber);
		String CN = t.getText().toString();
		cInfo.add(CN);
		t = (EditText) findViewById(R.id.expDate);
		String exp = t.getText().toString();
		cInfo.add(exp);
		t = (EditText) findViewById(R.id.securityCode);
		String SC = t.getText().toString();
		cInfo.add(SC);
		TextView text = (TextView) findViewById(R.id.creditErr);
	    text.setText("");
	    boolean e = false;
//	    if(name.equals("")){
//	    	e = true;
//	    	text.append("Name Error ");
//	    }if(CN.length() != 16 || !isNumeric(CN)){
//	    	e = true;
//	    	text.append("Card Number Error ");
//	    }if(exp.length() > 5 || exp.length() < 4){
//	    	e = true;
//	    	text.append("Exp Error ");
//	    }else if(exp.length() == 5){
//	    	String s = exp.substring(3, 5);
//	    	Calendar cal = Calendar.getInstance();
//	    	int te = Integer.parseInt(s);
//	    	if(Integer.parseInt(s) < (cal.get(Calendar.YEAR)-2000)){
//	    		e = true;
//	    		text.append("Card Expired ");
//	    	}else if(Integer.parseInt(s) == (cal.get(Calendar.YEAR)-2000)){
//	    		if(Integer.parseInt(exp.substring(0,2)) < cal.get(Calendar.MONTH)){
//	    			e = true;
//	    			text.append("Card Expired ");
//	    		}
//	    	}
//	    }if(SC.length() != 3 || !isNumeric(SC)){
//	    	e = true;
//	    	text.append("SC Error");
//	    }
	    if(e == false){
		    ArrayList<String> c = getIntent().getStringArrayListExtra("cust");
	        intent.putStringArrayListExtra("cust", c);
	        intent.putExtra("cost", getIntent().getStringExtra("cost"));
	        intent.putStringArrayListExtra("items", getIntent().getStringArrayListExtra("items"));
	        intent.putStringArrayListExtra("card", cInfo);
	        intent.putExtra("islog", getIntent().getStringExtra("islog"));
		    startActivity(intent);
	    }
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.credit, menu);
		return true;
	}
	
	//http://stackoverflow.com/questions/1102891/how-to-check-if-a-string-is-a-numeric-type-in-java
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
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
