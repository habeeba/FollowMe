package com.findme.application.Adapters;


import java.util.ArrayList;

import com.findme.application.R;
import com.findme.application.Models.PostListItem;




import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;  
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

public class PostAdapter extends BaseAdapter implements Filterable {

    public Context context;
    public ArrayList<PostListItem> PostsArrayList;
    public ArrayList<PostListItem> orig;

    public PostAdapter(Context context, ArrayList<PostListItem> PostsArrayList) {
        super();
        this.context = context;
        this.PostsArrayList = PostsArrayList;
    }


    public class PostHolder
    {
        TextView name;
        TextView post;
        TextView Time;

    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<PostListItem> results = new ArrayList<PostListItem>();
                if (orig == null)
                    orig = PostsArrayList;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (final PostListItem g : orig) {
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
                PostsArrayList = (ArrayList<PostListItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return PostsArrayList.size();
    }

    @Override
    public PostListItem getItem(int position) {
        return PostsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       PostHolder holder;
        if(convertView==null)
        {
            convertView=LayoutInflater.from(context).inflate(R.layout.post_row, parent, false);
            holder=new PostHolder();
            holder.name=(TextView) convertView.findViewById(R.id.txtName1);
            holder.name.setTextColor(Color.rgb(15,77,146));
            holder.post=(TextView) convertView.findViewById(R.id.txtpost);
            holder.Time=(TextView) convertView.findViewById(R.id.txtTimee);

            convertView.setTag(holder);
        }
        else
        {
            holder=(PostHolder) convertView.getTag();
        }

        holder.name.setText(PostsArrayList.get(position).getDrName());
        holder.post.setText(PostsArrayList.get(position).getPost());
        
        
        
        holder.Time.setText(PostsArrayList.get(position).getTime());

         
        return convertView;

    }

}