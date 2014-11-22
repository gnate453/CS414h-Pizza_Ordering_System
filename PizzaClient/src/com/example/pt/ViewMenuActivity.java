package com.example.pt;

import java.util.ArrayList;
import java.util.List;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.pizza.http.GetMenu;

public class ViewMenuActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_menu);
		
		AsyncTask<String, Void, List<String>> result = new GetMenu().execute();
		List<String> string = new ArrayList<String>();
		//final String[] matrix = { "_id", "name", "value" };
		//MatrixCursor cursor = new MatrixCursor(matrix);
		try {
			//cursor.close();
			string = (List<String>)result.get();
		} 
		catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
		
	    //final String[] columns = { "name", "value" };
	    //final int[] layouts = { android.R.id.text1, android.R.id.text2 };
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, string);
	    //SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.list_item_2_column, cursor, columns, layouts, 0);
		adapter.notifyDataSetChanged();
		setListAdapter(adapter);
		//cursor.close();
	}
}
