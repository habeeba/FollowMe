package com.findme.application.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.findme.application.Models.CheckedinListItem;
import com.findme.application.R;

import java.util.ArrayList;

/**
 * Created by Habeeba on 6/29/2016.
 */

public class CheckedinAdapter extends BaseAdapter implements Filterable {

    public Context context;
    public ArrayList<CheckedinListItem> CheckedinArraylist;
    public ArrayList<CheckedinListItem> orig;

    public CheckedinAdapter(Context context, ArrayList<CheckedinListItem> CheckedinArraylist) {
        super();
        this.context = context;
        this.CheckedinArraylist = CheckedinArraylist;
    }


    public class CheckedinHolder {
        TextView name;
        TextView Checkedin;
        TextView Time;

    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<CheckedinListItem> results = new ArrayList<CheckedinListItem>();
                if (orig == null)
                    orig = CheckedinArraylist;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final CheckedinListItem g : orig) {
                            if (g.getDrName().toLowerCase()
                                    .contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                CheckedinArraylist = (ArrayList<CheckedinListItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return CheckedinArraylist.size();
    }

    @Override
    public CheckedinListItem getItem(int position) {
        return CheckedinArraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CheckedinAdapter.CheckedinHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.checkedin_adapter, parent, false);
            holder = new CheckedinAdapter.CheckedinHolder();
            holder.name = (TextView) convertView.findViewById(R.id.drName);
            holder.name.setTextColor(Color.rgb(15, 77, 146));
            holder.Checkedin = (TextView) convertView.findViewById(R.id.checkedin);
            holder.Time = (TextView) convertView.findViewById(R.id.txtTimee);

            convertView.setTag(holder);
        } else {
            holder = (CheckedinAdapter.CheckedinHolder) convertView.getTag();
        }

        holder.name.setText(CheckedinArraylist.get(position).getDrName());
        holder.Checkedin.setText(CheckedinArraylist.get(position).getCheckedin());


        holder.Time.setText(CheckedinArraylist.get(position).getTime());


        return convertView;

    }

}