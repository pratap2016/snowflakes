package com.snowflakes.admin.snowflakes.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.snowflakes.admin.snowflakes.R;
import com.snowflakes.admin.snowflakes.adpater.ListViewAdapter;
import com.snowflakes.admin.snowflakes.model.ContactModel;

import java.util.ArrayList;

/**
 * Created by Admin on 3/18/2016.
 */
public class NextActivity extends AppCompatActivity{

    private ListView listView;
    private Button button_finish;
    private ArrayList<ContactModel> contactModelArrayList;
    private static Context mContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getIntentData();
        initUI();
        settingListAdapter();
    }


    /*
        Getting intent data from previous activity
     */
    private void getIntentData() {

        Intent intent = getIntent();
        if(intent != null){
            contactModelArrayList = (ArrayList<ContactModel>) intent.getSerializableExtra("listItems");
        }
    }

    /*
        Initializing UI Component
     */
    private void initUI() {

        try{
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }catch (Exception ex){
            System.out.print(ex);
        }

        listView = (ListView) findViewById(R.id.lv_contact_details);
        button_finish = (Button) findViewById(R.id.bt_next);

        button_finish.setText(getResources().getString(R.string.finish));
        button_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }


    /*
        Setting adapter
     */
    private void settingListAdapter() {

        listView.setAdapter(new ListViewAdapter(this, R.layout.layout_listview_items, contactModelArrayList, "Next"));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
