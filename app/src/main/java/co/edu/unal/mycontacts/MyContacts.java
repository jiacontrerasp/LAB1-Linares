package co.edu.unal.mycontacts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import co.edu.unal.mycontacts.data.Contact;


public class MyContacts extends Activity {

    public String TAG = "MyActivity";

    public ArrayList<Contact> myContacts;

    public MyContacts(){
        myContacts = new ArrayList<Contact>();
    }

    public Contact searchContactByName(String name){
        Contact myContact = null;

        for (Contact contact : myContacts){
            if (contact.getName()==name){
                myContact = contact;
                break;
            }
        }

        return myContact;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_contacts);

        Button addButton = (Button) findViewById(R.id.add_contact);
        Button selectButton = (Button) findViewById(R.id.select_contact);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addContactActivity = new Intent(getApplication(), AddContactActivity.class);
                startActivity(addContactActivity);

            }
        });

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showContactActivity = new Intent(getApplication(), ShowContactActivity.class);
                startActivity(showContactActivity);

            }
        });
    }

    public void renderAddContact(View view){
        Intent addContactActivity = new Intent(getApplication(),AddContactActivity.class);
        startActivity(addContactActivity);

    }

    public void renderSelectContact(View view){
        Intent showContactActivity = new Intent(getApplication(),ShowContactActivity.class);
        startActivity(showContactActivity);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_contacts, menu);
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
}
