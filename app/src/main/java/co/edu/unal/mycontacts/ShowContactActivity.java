package co.edu.unal.mycontacts;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import co.edu.unal.mycontacts.data.Contact;
import co.edu.unal.mycontacts.data.DAO;


public class ShowContactActivity extends ActionBarActivity {
    public String TAG = "ShowContactActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);

        String[] values = getContactsNames();



        if (DAO.loadNuHUBs(getApplication()).size()>0){

            ListView listView = (ListView)findViewById(R.id.list_founds);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,values);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String name = ((TextView) view).getText().toString();
                    Contact contact = new Contact();

                    try {
                        contact = ((MyContacts) getApplicationContext()).searchContactByName(name);
                    } catch (Exception e) {
                        Log.i(TAG, "Error -> " + e);
                    }
                    
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.name_label) + getString(R.string.colon_label) + contact.getName() + getString(R.string.comma_label) +
                                    getString(R.string.email_label) + getString(R.string.colon_label) + contact.getEmail() + getString(R.string.comma_label) +
                                    getString(R.string.phone_label) + getString(R.string.colon_label) + contact.getPhone() + getString(R.string.comma_label) +
                                    getString(R.string.address_label) + getString(R.string.colon_label) + contact.getAddress() + getString(R.string.comma_label)
                            , Toast.LENGTH_LONG);
                }
            });



        }else{
            Toast.makeText(getApplicationContext(),
                    getString(R.string.no_contacts), Toast.LENGTH_LONG);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_contact, menu);
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

    public String[] getContactsNames(){

        ArrayList<Contact> myContacts = DAO.loadNuHUBs(getApplication());
        int size = 0;
        try{
            //size = ((MyContacts)getApplicationContext()).myContacts.size();
            size = myContacts.size();
        }catch (Exception e){
            Log.i(TAG,"Error -> "+e);
            Toast.makeText(getApplicationContext(),
                    getString(R.string.internal_error), Toast.LENGTH_LONG);
        }

        String[] names = new String[size];

        for (int i = 0; i < size; i++){
            //names[i] = ((MyContacts)getApplicationContext()).myContacts.get(i).getName();
            names[i] = myContacts.get(i).getName();
        }
        return names;
    }
}
