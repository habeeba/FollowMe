package com.findme.application.Adapters;

import java.util.List;


import com.findme.application.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.findme.application.Activities.ShowMyagendaActivity;


public class BookContentAdapter extends ArrayAdapter<String>{

	List <String> list;
	Context context;
	
	public BookContentAdapter(List<String> list, Context context) {
		super(context, R.layout.book_content_list,list);
		// TODO Auto-generated constructor stub
	    this.list = list;
	    this.context = context;
	}
	private static class BookContentHolder {
		public TextView content;
		public CheckBox check;
	}
	@Override
	public View getView (int position , View convertView , ViewGroup parent){
		View v = convertView;
		BookContentHolder holder = new BookContentHolder();
	
	//	if (convertView == null){
			
		
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.book_content_list, null);
			holder.check = (CheckBox) v.findViewById(R.id.checkbox);
			holder.check.setOnCheckedChangeListener((ShowMyagendaActivity)context);
	/*	}
		else{
			holder = (BookContentHolder)v.getTag();
		}*/
		String conten = list.get(position);
		holder.check.setTag(conten);
		holder.check.setText(conten);
		

		return v;
		
		
	}
	

}
