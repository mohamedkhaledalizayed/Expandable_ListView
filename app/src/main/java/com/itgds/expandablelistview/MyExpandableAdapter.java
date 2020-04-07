package com.itgds.expandablelistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MyExpandableAdapter extends BaseExpandableListAdapter {
    private final HashMap<String, List<String>> foodItems;
    private final Context context;
    private final ArrayList<String> foodHeader;

    public MyExpandableAdapter(Context context, ArrayList<String> foodHeader, HashMap<String, List<String>> footItems) {
        this.context = context;
        this.foodHeader = foodHeader;
        this.foodItems = footItems;
    }

    @Override
    public int getGroupCount() {
        return foodHeader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return foodItems.get(foodHeader.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return foodHeader.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return foodItems.get(foodHeader.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup viewGroup) {

        String headerTitle = (String) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_group, null);
        }

        ImageView arrow = view.findViewById(R.id.arrow);

        View myView = view.findViewById(R.id.view);
        if (groupPosition == 2) {
            myView.setVisibility(View.GONE);
        }else {
            myView.setVisibility(View.VISIBLE);
        }

        TextView itemName = view
                .findViewById(R.id.item_name);
        itemName.setText(headerTitle);
        if (isExpanded) {
            itemName.setTextColor(context.getColor(R.color.colorPrimary));
            arrow.setImageResource(R.drawable.ic_expand_less_black_24dp);
        } else {
            itemName.setTextColor(context.getColor(R.color.color_black));
            arrow.setImageResource(R.drawable.ic_expand_more_black_24dp);
        }



        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {

        final String childText = (String) getChild(groupPosition, childPosition);

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_child, null);
        }
//        final ImageView fav = view.findViewById(R.id.image_item_fav);

        View myView = view.findViewById(R.id.child_view);
        if (childPosition == getChildrenCount(groupPosition)-1) {
            myView.setVisibility(View.GONE);
        }else {
            myView.setVisibility(View.VISIBLE);
        }

//        if (groupPosition == 0 && foodItems.size() != 1){
//            fav.setImageResource(R.drawable.ic_favorite_black_24dp);
//        }else {
//            fav.setImageResource(R.drawable.ic_favorite_border_black_24dp);
//        }
//
//        fav.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fav.setImageResource(R.drawable.ic_favorite_black_24dp);
//            }
//        });

        TextView childItemName = view.findViewById(R.id.child_item_name);
        TextView childItemDetails = view.findViewById(R.id.child_item_name);

        childItemName.setText(childText);
        childItemDetails.setText(childText);

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }


}