package com.example.pt;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable {
	String price;
	String name;
	String ds;
	
	public static final Parcelable.Creator<Item> CREATOR    = new Parcelable.Creator<Item>() {
		public Item createFromParcel(Parcel in) {
			return new Item(in);
		}		
		public Item[] newArray(int size) {
			return new Item[size];
		}
	};
	
	public Item(){
		price = "";
		name = "";
		ds = "";
	}
	public Item(String p, String n, String is){
		price = p;
		name = n;
		ds = is;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeStringArray(new String[] {this.price,
                this.name,
                this.ds});
		
	}
	public String toString(){
		if(ds.equals("false")){
			return name+": "+"$ "+price;
		}else{
			return "(Special)"+name+": "+"$ "+price;
		}
	}
	private Item(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);
        this.price = data[0];
        this.name = data[1];
        this.ds = data[2];
    }
}
