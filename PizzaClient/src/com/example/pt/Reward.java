package com.example.pt;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Reward extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reward);
		ListView menuList = (ListView)findViewById(R.id.listView1);
		ArrayList<Item> order = getIntent().getParcelableArrayListExtra("items2");
		ArrayAdapter<Item> arrayAdapter = new ArrayAdapter<Item>(this,android.R.layout.simple_list_item_1,order);
		menuList.setAdapter(arrayAdapter);
		menuList.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
			{
				
               }
		});
	}
	public void sendMessage(View view) 
	{
		redeemed(view);
	}
	public void sendMessage2(View view) 
	{
		 Intent intent = new Intent(Reward.this, CashorCredit.class);
		 ArrayList<String> c = getIntent().getStringArrayListExtra("cust");
	     intent.putStringArrayListExtra("cust", c);
	     intent.putExtra("cost", getIntent().getStringExtra("cost"));
	     intent.putStringArrayListExtra("items", getIntent().getStringArrayListExtra("items"));
	     intent.putExtra("islog", getIntent().getStringExtra("islog"));
		 startActivity(intent);
	}
	private void redeemed(View view){
		AlertDialog.Builder ADB = new AlertDialog.Builder(Reward.this);
		ArrayList<Item> items = getIntent().getParcelableArrayListExtra("items2");
		double d = Double.parseDouble(items.get(0).price);
		DecimalFormat df = new DecimalFormat("#,##0.00");
		int pos = 0;
		for(int i = 0; i < items.size(); i++){
			if(Double.parseDouble(items.get(i).price) < d){
				d =Double.parseDouble(items.get(i).price);
				pos = i;
			}
		}
		items.get(pos).redeemed();
		final ArrayList<String> sitems = new ArrayList<String>();
		for(int i = 0; i < items.size(); i++){
			sitems.add(items.get(i).toString());
		}
		ADB.setTitle("Congratulations!");		
		ADB.setMessage("You just saved $"+df.format(d)+"!");
		final double cost = Double.parseDouble(getIntent().getStringExtra("cost"))-d;
		ADB.setPositiveButton("OK",new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(Reward.this,CashorCredit.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				ArrayList<String> c = getIntent().getStringArrayListExtra("cust");
		        intent.putStringArrayListExtra("cust", c);
		        intent.putExtra("cost", cost+"");
		        intent.putStringArrayListExtra("items", sitems);
		        intent.putExtra("islog", getIntent().getStringExtra("islog"));
				startActivity(intent);				
			}
			
		});
		AlertDialog alert = ADB.create();
		alert.show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reward, menu);
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
