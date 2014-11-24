package com.example.pt;

import java.io.IOException;
import java.util.ArrayList;
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
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class AddItems extends ActionBarActivity {
	ArrayList<Item> addedNames = new ArrayList<Item>();
	ArrayList<String> sItems = new ArrayList<String>();
	String price = "0";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Intent i = getIntent();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_items);
		if("AI2".equals(i.getStringExtra("Prev"))){
			sItems = i.getStringArrayListExtra("items2");
			addedNames = i.getParcelableArrayListExtra("items");
			price = i.getStringExtra("cost");
			TextView t = (TextView) findViewById(R.id.aiprice);
			t.setText(price);
		}
		ListView menuList = (ListView)findViewById(R.id.listView1);
		ArrayAdapter<Item> arrayAdapter = new ArrayAdapter<Item>(this,android.R.layout.simple_list_item_1,addedNames);
		menuList.setAdapter(arrayAdapter);
		menuList.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
			{
				ArrayList<Item> myList = getIntent().getParcelableArrayListExtra("items2");
				ArrayList<String> myList2 = getIntent().getStringArrayListExtra("items");
				Intent intent = new Intent(AddItems.this, AddItems.class);
				Item selectedItem = myList.get(position);
                //pass item info
                String p = selectedItem.price;
                myList.remove(position); 
                myList2.remove(position);
                intent.putStringArrayListExtra("items", myList2);
                intent.putParcelableArrayListExtra("items2",myList);
                //Pass Price info
                String co = getIntent().getStringExtra("cost");
                double cost = (Double.parseDouble(co)) - (Double.parseDouble(p));
                intent.putExtra("cost", cost+"");
                //Pass Customer info
                ArrayList<String> c = getIntent().getStringArrayListExtra("cust");
                intent.putStringArrayListExtra("cust", c);
                intent.putExtra("Prev","AI2");
                intent.putExtra("islog", getIntent().getStringExtra("islog"));
                startActivity(intent);
               }
		});
		
		
	}
	public void sendMessage(View view) 
	{
	    Intent intent = new Intent(AddItems.this, AddItem2.class);
	    ArrayList<String> c = getIntent().getStringArrayListExtra("cust");
        intent.putStringArrayListExtra("cust", c);
        intent.putExtra("cost", price);
        intent.putExtra("islog", getIntent().getStringExtra("islog"));
        intent.putExtra("user",getIntent().getStringExtra("user"));
        intent.putStringArrayListExtra("items2", sItems);
	    intent.putParcelableArrayListExtra("items", addedNames);
	    intent.putExtra("red", "false");
	    startActivity(intent);
	}
	public void sendMessage2(View view) 
	{
		String user = getIntent().getStringExtra("user");
		//String user = "bm1";
		AsyncTask<String, Void, String> result = new checkRew(user,getString(R.string.ServerName)).execute();
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
		if(s.equals("TRUE")){
			Intent intent = new Intent(AddItems.this, Reward.class);
		    ArrayList<String> c = getIntent().getStringArrayListExtra("cust");
	        intent.putStringArrayListExtra("cust", c);
	        intent.putExtra("cost", price);
	        intent.putExtra("islog", getIntent().getStringExtra("islog"));
	        intent.putStringArrayListExtra("items2", sItems);
	        intent.putParcelableArrayListExtra("items", addedNames);
	        intent.putExtra("red", "false");
	        intent.putExtra("user",getIntent().getStringExtra("user"));
		    startActivity(intent);
		}
		else{
		    Intent intent = new Intent(AddItems.this, CashorCredit.class);
		    ArrayList<String> c = getIntent().getStringArrayListExtra("cust");
	        intent.putStringArrayListExtra("cust", c);
	        intent.putExtra("cost", price);
	        intent.putExtra("islog", getIntent().getStringExtra("islog"));
	        intent.putParcelableArrayListExtra("items", addedNames);
	        intent.putExtra("user",getIntent().getStringExtra("user"));
	        intent.putExtra("red", "false");
		    startActivity(intent);
		}
	}
	public void addItem(Item s){
		addedNames.add(s);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_items, menu);
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
class checkRew extends AsyncTask<String, Void, String> {

    private Exception exception;
    private String srv;
    private String usr;
    private String ret;
    
    public String toString(){
    	return ret;
    }
    public checkRew(String user, String s) {
		// TODO Auto-generated constructor stub
    	usr = user;
    	srv = s;
	}
	protected String doInBackground(String... urls) {
    	HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpPost httppost = new HttpPost(srv+"/customer?type=rewardavailable&username="+usr);          		
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

