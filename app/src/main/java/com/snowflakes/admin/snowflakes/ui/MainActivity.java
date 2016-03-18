package com.snowflakes.admin.snowflakes.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.snowflakes.admin.snowflakes.R;
import com.snowflakes.admin.snowflakes.adpater.ListViewAdapter;
import com.snowflakes.admin.snowflakes.model.ContactModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*
        Variables Declarations
     */
    private static int i =0;
    private static ListView listView_contact;
    private static Button button_next;
    private static ArrayList<ContactModel> arrayList_contactModels;
    private static ListViewAdapter listviewadapter;
    private Context mContext;
    private ArrayList<ContactModel> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mContext = getApplicationContext();
        initUi();
        gettingContactValues();
        listViewAdapterSetting();
    }


    /*
   *   Initializing Ui view
   *
    */
    private void initUi() {

        try{
            getSupportActionBar().setTitle(getResources().getString(R.string.contact_list));
        }
        catch (Exception ex)
        {
            System.out.print(ex);
        }

        listView_contact = (ListView) findViewById(R.id.lv_contact_details);
        button_next = (Button)findViewById(R.id.bt_next);

    }


    /*
    *   Adding contact details to Array list
    *
     */
    private void gettingContactValues() {

        arrayList_contactModels = new ArrayList<ContactModel>();
        for(i = 0; i< 120; i++){

            ContactModel contactModels = new ContactModel();
            contactModels.contact_name = "Amit Kumar " + i;
            if(i<10)
                contactModels.contact_number = "996600112" + i;
            else if(1<99)
                contactModels.contact_number = "99660011" + i;
            else
                contactModels.contact_number = "9966001" + i;

            arrayList_contactModels.add(contactModels);
        }
    }



    /*
   *   Adding data to listView adatpter
   * @param mContext
   * @param arrayList_contactModels
    */
    private void listViewAdapterSetting() {

        listviewadapter = new ListViewAdapter(this, R.layout.layout_listview_items,arrayList_contactModels,"Main");
        listView_contact.setAdapter(listviewadapter);
        listView_contact.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        findViewById(R.id.bt_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, NextActivity.class)
                        .putExtra("listItems", listviewadapter.selectedContacts())
                        .setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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
