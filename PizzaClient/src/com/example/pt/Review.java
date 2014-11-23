package com.example.pt;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.protocol.HTTP;

import com.pizza.http.RequestHandler;
import com.pizza.http.XmlHelper;

import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Review extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_review);
		
		ArrayList<String> items = getIntent().getStringArrayListExtra("items");
		ListView order = (ListView)findViewById(R.id.listView1);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
		order.setAdapter(arrayAdapter);
		String price = getIntent().getStringExtra("cost");
		TextView text = (TextView) findViewById(R.id.name);
		ArrayList<String> c = getIntent().getStringArrayListExtra("cust");
		String name = c.get(0);
		text.setText(name);
	    text = (TextView) findViewById(R.id.phone);
		text.setText(c.get(1));
		text = (TextView) findViewById(R.id.addr);
		text.setText(c.get(2));
		text = (TextView) findViewById(R.id.total);
		text.setText(price);
	}
	public void sendMessage(View view) 
	{
		ArrayList<String> customer = getIntent().getStringArrayListExtra("cust");
		ArrayList<String> items = getIntent().getStringArrayListExtra("items");
		String price = getIntent().getStringExtra("cost");
		for(int i  = 0; i < items.size();i++){
			ArrayList<String> temp = new ArrayList<String>();
			temp = parseItems(items.get(i));
			RequestHandler.addItemXml(XmlHelper.getItemXml(temp.get(0), temp.get(1), temp.get(2)));
		}
		RequestHandler.addPaymentXml(XmlHelper.getCashPaymentXml(price));
		RequestHandler.setCustomerXml(XmlHelper.getCustomerXml(customer.get(0)));
		RequestHandler.setAddressXml(XmlHelper.getAddressXml(customer.get(2), customer.get(3), customer.get(4), customer.get(5), customer.get(1)));
		String finalXML = RequestHandler.getFinalXml();
		new submitOrder().execute(finalXML);
		orderComplete(view);
	}
	public ArrayList<String> parseItems(String string){
		ArrayList<String> result = new ArrayList<String>();
		String test = string;
		String ds = "";
		DecimalFormat df = new DecimalFormat("#,##0.00");
		double d = 0;
		String price = "";
		String name = "";
		if(test.contains("(Special)")){
			ds = "true";
			test = test.replace("(Special)", "");
			String s[] = test.split(":");
			name = s[0];
			price = s[1].replace(" $", "");
			d = Double.parseDouble(price);
			price = df.format(d);
			result.add(name);
			result.add(price);
			result.add(ds);
		}else{
			ds = "false";
			String s[] = test.split(":");
			name = s[0];
			price = s[1].replace(" $", "");
			d = Double.parseDouble(price);
			price = df.format(d);
			result.add(name);
			result.add(price);
			result.add(ds);
		}
		return result;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.review, menu);
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
	private void orderComplete(View view){
		AlertDialog.Builder ADB = new AlertDialog.Builder(Review.this);
			
		ADB.setTitle("Finished!");
		ADB.setMessage("Your order was successful!");
		ADB.setPositiveButton("OK",new DialogInterface.OnClickListener(){

			public void onClick(DialogInterface dialog, int which) {
				Intent in = new Intent(Review.this,Login.class);
				in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(in);				
			}
			
		});
		AlertDialog alert = ADB.create();
		alert.show();

	}
	
}
class submitOrder extends AsyncTask<String, Void, String> {

    private Exception exception;

    protected String doInBackground(String... urls) {
    	HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpPost httppost = new HttpPost("http://saint-paul.cs.colostate.edu:8000/order?type=place");          
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
		return null;
    }
}
