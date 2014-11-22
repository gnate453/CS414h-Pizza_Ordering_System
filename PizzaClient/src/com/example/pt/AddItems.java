package com.example.pt;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
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
	String price = "0";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Intent i = getIntent();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_items);
		if("AI2".equals(i.getStringExtra("Prev"))){
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
				ArrayList<Item> myList = getIntent().getParcelableArrayListExtra("items");
				Intent intent = new Intent(AddItems.this, AddItems.class);
                Item selectedItem = (Item) addedNames.get(position);
                //pass item info
                String p = selectedItem.price;
                myList.remove(position);
                intent.putParcelableArrayListExtra("items", myList);
                //Pass Price info
                String co = getIntent().getStringExtra("cost");
                double cost = (Double.parseDouble(co)) - (Double.parseDouble(p));
                intent.putExtra("cost", cost+"");
                //Pass Customer info
                ArrayList<String> c = getIntent().getStringArrayListExtra("cust");
                intent.putStringArrayListExtra("cust", c);
                intent.putExtra("Prev","AI2");
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
	    intent.putParcelableArrayListExtra("items", addedNames);
	    startActivity(intent);
	}
	public void sendMessage2(View view) 
	{
	    Intent intent = new Intent(AddItems.this, CashorCredit.class);
	    ArrayList<String> c = getIntent().getStringArrayListExtra("cust");
        intent.putStringArrayListExtra("cust", c);
        intent.putExtra("cost", price);
        intent.putParcelableArrayListExtra("items", addedNames);
	    startActivity(intent);
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
