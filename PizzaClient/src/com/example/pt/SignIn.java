package com.example.pt;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
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

public class SignIn extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_in, menu);
		return true;
	}
	
	public void sendMessage(View view) 
	{
		EditText t = (EditText) findViewById(R.id.user);		    
		String user =t.getText().toString();
		t = (EditText) findViewById(R.id.passwd);
		String pass= t.getText().toString();
		AsyncTask<String, Void, String> result = new loginCust(user,pass, getString(R.string.ServerName)).execute();
		try {
			result.get(10000, TimeUnit.MILLISECONDS);
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
		if(!s.equalsIgnoreCase("invalid")){
			 Intent intent = new Intent(SignIn.this, LoggedIn.class);
			 intent.putExtra("user",result.toString());
			 startActivity(intent);
		}else{
			TextView text = (TextView) findViewById(R.id.SignErr);
		    text.setText("Invalid usernames/password. Please try again.");
		}
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
class loginCust extends AsyncTask<String, Void, String> {

    private Exception exception;
    private String srv;
    private String usr;
    private String passwd;
    private String ret;
    
    public String toString(){
    	return ret;
    }
    public loginCust(String user, String pass, String s) {
		// TODO Auto-generated constructor stub
    	usr = user;
    	passwd = pass;
    	srv = s;
	}
	protected String doInBackground(String... urls) {
    	HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpPost httppost = new HttpPost(srv+"/customer?type=Login&id="+usr+"&pw="+passwd);          		
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
