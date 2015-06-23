package co.edu.unal.mycontacts;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.unal.mycontacts.data.Contact;


public class AddContactActivity extends Activity {

    private String TAG = "AddContactActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        Button okButton = (Button) findViewById(R.id.ok_add_contact);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast;
                EditText nameField = (EditText) findViewById(R.id.add_contact_name_edit);
                EditText emailField = (EditText) findViewById(R.id.add_contact_email_edit);
                EditText phoneField = (EditText) findViewById(R.id.add_contact_phone_edit);
                EditText addressField = (EditText) findViewById(R.id.add_contact_address_edit);

                Contact newContact = new Contact();
                newContact.setName(nameField.getText().toString());
                newContact.setEmail(emailField.getText().toString());
                newContact.setPhone(Long.parseLong(phoneField.getText().toString()));
                newContact.setAddress(addressField.getText().toString());

                try{
                    ((MyContacts) getApplicationContext()).myContacts.add(newContact);
                }catch (Exception e){
                    Log.i(TAG,"Error -> "+e);
                }


                toast = Toast.makeText(getApplication(),getString(R.string.added_message),Toast.LENGTH_LONG);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
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
