package com.example.pt;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.pizza.http.RequestHandler;
import com.pizza.http.XmlHelper;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegAddr extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg_addr);
	}
	public void sendMessage(View view) {
		  Intent intent = new Intent(RegAddr.this, Login.class);
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
				String uname = getIntent().getStringExtra("usr");
				String pass = getIntent().getStringExtra("pass");
				String customerXml = XmlHelper.getCustomerXml(name, uname, pass);
				RequestHandler.setCustomerXml(customerXml);
				String addressXml = XmlHelper.getAddressXml(addr, city, state, zip, phone);
				RequestHandler.setAddressXml(addressXml);
				AsyncTask<String, Void, String> result = new newCust(uname,pass, getString(R.string.ServerName)).execute(RequestHandler.getFinalXml());
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
		    }
	}
	class newCust extends AsyncTask<String, Void, String> {

	    private Exception exception;
	    private String srv;
	    private String usr;
	    private String passwd;
	    private String ret;
	    
	    public String toString(){
	    	return ret;
	    }
	    public newCust(String user, String pass, String s) {
			// TODO Auto-generated constructor stub
	    	usr = user;
	    	passwd = pass;
	    	srv = s;
		}
	    protected String doInBackground(String... urls) {
	    	HttpClient httpclient = new DefaultHttpClient();
			try {
				HttpPost httppost = new HttpPost(srv+"/customer?type=add");          
				StringEntity se = new StringEntity(urls[0]);			
				httppost.setEntity(se);  			
				HttpResponse httpResponse = httpclient.execute(httppost);			
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ret;
	    }
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reg_addr, menu);
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
