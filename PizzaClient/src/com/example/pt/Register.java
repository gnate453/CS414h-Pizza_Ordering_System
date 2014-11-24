package com.example.pt;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
	}
	public void sendMessage(View view) {
		 Intent intent = new Intent(Register.this, RegAddr.class);
		 EditText t = (EditText) findViewById(R.id.rusr);
		 String uname = t.getText().toString();
		 t = (EditText) findViewById(R.id.rpass);
		 String pass = t.getText().toString();
		 AsyncTask<String, Void, String> result = new checkU(uname,getString(R.string.ServerName)).execute();
			try {
				result.get(1000, TimeUnit.MILLISECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		String s = result.toString();
		 if(!result.toString().equalsIgnoreCase("exists")){
			 intent.putExtra("usr", uname);
			 intent.putExtra("pass", pass);
			 startActivity(intent);
		 }else{
			 TextView text = (TextView) findViewById(R.id.lgErr);
			 text.setText("Invalid username taken.");
		 }
	}
	class checkU extends AsyncTask<String, Void, String> {

	    private String srv;
	    private String usr;
	    private String ret;
	    
	    public String toString(){
	    	return ret;
	    }
	    public checkU(String user, String s) {
			// TODO Auto-generated constructor stub
	    	usr = user;
	    	srv = s;
		}
		protected String doInBackground(String... urls) {
	    	HttpClient httpclient = new DefaultHttpClient();
			try {
				HttpPost httppost = new HttpPost(srv+"/customer?type=unameexist&id="+usr);          		
				HttpResponse httpResponse = httpclient.execute(httppost);
				ret = EntityUtils.toString(httpResponse.getEntity());
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
		getMenuInflater().inflate(R.menu.register, menu);
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
