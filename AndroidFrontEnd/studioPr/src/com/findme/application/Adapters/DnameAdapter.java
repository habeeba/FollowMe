package com.findme.application.Adapters;


import java.util.ArrayList;

import com.findme.application.R;
import com.findme.application.Models.DoctorNameListItem;





import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class DnameAdapter extends BaseAdapter implements Filterable {

    public Context context;
    public ArrayList<DoctorNameListItem> DrnameArrayList;
    public ArrayList<DoctorNameListItem> orig;

    public DnameAdapter(Context context, ArrayList<DoctorNameListItem> DrnameArrayList) {
        super();
        this.context = context;
        this.DrnameArrayList = DrnameArrayList;
    }


    public class EmployeeHolder
    {
        TextView name;
    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<DoctorNameListItem> results = new ArrayList<DoctorNameListItem>();
                if (orig == null)
                    orig = DrnameArrayList;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final DoctorNameListItem g : orig) {
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
                DrnameArrayList = (ArrayList<DoctorNameListItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return DrnameArrayList.size();
    }

    @Override
    public DoctorNameListItem getItem(int position) {
        return DrnameArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EmployeeHolder holder;
        if(convertView==null)
        {
            convertView=LayoutInflater.from(context).inflate(R.layout.row, parent, false);
            holder=new EmployeeHolder();
            holder.name=(TextView) convertView.findViewById(R.id.txtName);
            convertView.setTag(holder);
        }
        else
        {
            holder=(EmployeeHolder) convertView.getTag();
        }

        holder.name.setText(DrnameArrayList.get(position).getDrName());

        return convertView;

    }

}