package com.example.pt;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.pizza.http.GetMenu;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AddItem2 extends ActionBarActivity {
	ArrayList<Item> menuNames;
	double price = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_item2);
		
		AsyncTask<String, Void, List<String>> result = new GetMenu().execute();
		ArrayList<String> string = new ArrayList<String>();
		try {
			string = (ArrayList<String>)result.get();
			ListView menuList = (ListView)findViewById(R.id.listView1);
			menuNames = parseItems(string);
			ArrayAdapter<Item> arrayAdapter = new ArrayAdapter<Item>(this,android.R.layout.simple_list_item_1,menuNames);
			menuList.setAdapter(arrayAdapter);
			menuList.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View v,int position, long arg3)
	            {
						ArrayList<String> it = getIntent().getStringArrayListExtra("items");
						Intent intent = new Intent(AddItem2.this, AddItems.class);
	                    Item selectedItem = (Item) menuNames.get(position);
	                    //pass item info
	                    String p = selectedItem.price;
	                    it.add(selectedItem.toString());
	                    intent.putStringArrayListExtra("items", it);
	                    //Pass Price info
	                    String co = getIntent().getStringExtra("cost");
	                    double cost = (Double.parseDouble(co)) + (Double.parseDouble(p));
	                    intent.putExtra("cost", cost+"");
	                    //Pass Customer info
	                    ArrayList<String> c = getIntent().getStringArrayListExtra("cust");
	                    intent.putStringArrayListExtra("cust", c);
	                    intent.putExtra("Prev","AI2");
	                    startActivity(intent);
	            }
			});
		} 
		catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	public ArrayList<Item> parseItems(ArrayList<String> AL){
		ArrayList<Item> result = new ArrayList();
		for(int i = 0; i < AL.size();i++){
			String test = AL.get(i);
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
			}else{
				ds = "false";
				String s[] = test.split(":");
				name = s[0];
				price = s[1].replace(" $", "");
				d = Double.parseDouble(price);
				price = df.format(d);
			}
			result.add(new Item(price,name,ds));
		}
		return result;
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_item2, menu);
		return true;
	}
	public void sendMessage(View view) 
	{
		finish();
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
