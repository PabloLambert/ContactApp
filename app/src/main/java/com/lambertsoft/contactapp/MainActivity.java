package com.lambertsoft.contactapp;


import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    EditText name, address, phone;
    Button add;
    ImageView imageContact;
    List<Contact> contactList = new ArrayList<Contact>();
    ListView listViewContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText)findViewById(R.id.nameTxt);
        address = (EditText) findViewById(R.id.addressTxt);
        phone = (EditText)findViewById(R.id.phoneTxt);
        add = (Button)findViewById(R.id.addBtn);
        listViewContact = (ListView) findViewById(R.id.listView);
        imageContact = (ImageView) findViewById(R.id.ImageView);


        TabHost tab = (TabHost) findViewById(R.id.tabHost);
        tab.setup();
        TabHost.TabSpec tabspec = tab.newTabSpec("Creator");
        tabspec.setContent(R.id.tab1);
        tabspec.setIndicator("Creator");
        tab.addTab(tabspec);

        tabspec = tab.newTabSpec("List");
        tabspec.setContent(R.id.tab2);
        tabspec.setIndicator("List");
        tab.addTab(tabspec);


        name.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                add.setEnabled(!name.getText().toString().trim().isEmpty());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact nc = new Contact(name.getText().toString(), address.getText().toString(), phone.getText().toString());
                contactList.add(nc);
                Toast.makeText(getApplication(), "Contact created... " + nc.getName(), 5).show();
                listViewContact.setAdapter(new ContactListAdapter());

            }
        });

        imageContact.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), 1);
            }
        });
    }

    public void onActivityResult (int reqCode, int resCode, Intent data) {

        if (resCode == RESULT_OK)
            if (reqCode == 1 )
                imageContact.setImageURI(data.getData());

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

    private class ContactListAdapter extends ArrayAdapter<Contact> {

        public ContactListAdapter(){
            super(MainActivity.this, R.layout.listview_item, contactList);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent){
            if (view == null )
                view = getLayoutInflater().inflate(R.layout.listview_item, parent, false);

            Contact cc = contactList.get(position);
            TextView name = (TextView) view.findViewById(R.id.nameView);
            name.setText(cc.getName());
            TextView address = (TextView) view.findViewById(R.id.addressView);
            address.setText(cc.getAddress());
            TextView phone = (TextView) view.findViewById(R.id.phoneView);
            phone.setText(cc.getPhone());

            return view;

        }
    }
}
