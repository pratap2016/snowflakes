package com.snowflakes.admin.snowflakes.adpater;

import android.app.Activity;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.snowflakes.admin.snowflakes.R;
import com.snowflakes.admin.snowflakes.model.ContactModel;

import java.util.ArrayList;

/**
 * Created by Admin on 3/18/2016.
 */
public class ListViewAdapter extends ArrayAdapter<ContactModel> {

    // Declare Variables
    private Activity mContext;
    private LayoutInflater inflater;
    private ArrayList<ContactModel> arrayList_contectmodel;
    private ArrayList<ContactModel> listData = new ArrayList<>();
    private String FromActivity;

    public ListViewAdapter(Activity context, int resource, ArrayList<ContactModel> arrayList_contectmodel, String main) {
        super(context, resource,arrayList_contectmodel);
        this.mContext = context;
        this.FromActivity = main;
        this.arrayList_contectmodel = arrayList_contectmodel;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflater = LayoutInflater.from(context);
    }

    @Override

    public int getViewTypeCount() {

        return getCount();
    }

    @Override

    public int getItemViewType(int position) {

        return position;
    }


    private class ViewHolder {
        TextView tv_contactName;
        TextView tv_contactNumber;
        CheckBox cb_selection;
    }

    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.layout_listview_items, null);

            // Locate the TextViews in listView_item.xml
            holder.tv_contactName = (TextView) view.findViewById(R.id.tv_contact_name);
            holder.tv_contactNumber = (TextView) view.findViewById(R.id.tv_contact_number);
            holder.cb_selection = (CheckBox) view.findViewById(R.id.cb_selection);

            // Locate the Checkbox in layout_listview_items.xml
            if(FromActivity.equals("Main"))
                holder.cb_selection.setVisibility(View.VISIBLE);
            else
                holder.cb_selection.setVisibility(View.GONE);


            holder.cb_selection.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int getPosition = (Integer) buttonView.getTag();  // Here we get the position that we have set for the checkbox using setTag.
                    arrayList_contectmodel.get(getPosition); // Set the value of checkbox to maintain its state.
                    if(isChecked)
                        listData.add( arrayList_contectmodel.get(getPosition));
                    else
                    if(!isChecked)
                        if(listData.contains(arrayList_contectmodel.get(getPosition)))
                            listData.remove(arrayList_contectmodel.get(getPosition));
                }
            });

            view.setTag(holder);
            view.setTag(R.id.tv_contact_name, holder.tv_contactName);
            view.setTag(R.id.tv_contact_number, holder.tv_contactNumber);
            view.setTag(R.id.cb_selection, holder.cb_selection);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.cb_selection.setTag(position);
        // Capture position and set to the TextViews
        holder.tv_contactName.setText(arrayList_contectmodel.get(position).contact_name);
        holder.tv_contactNumber.setText(arrayList_contectmodel.get(position).contact_number);

        return view;
    }

    public ArrayList<ContactModel> selectedContacts(){
        return listData;
    }

}
