package co.edu.unal.mycontacts.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by jiacontrerasp on 6/23/15.
 */
public class DAO {

    private static String TAG = "dao";

    public static boolean saveContacts(Context context,ArrayList<Contact> data)
    {

        SharedPreferences.Editor contacts = context.getSharedPreferences(Constants.SHAREDPREFERENCE_ALL_CONTACTS, context.MODE_PRIVATE)
                .edit();

        contacts.putInt(Constants.SHAREDPREFERENCE_ALL_CONTACTS_SIZE, data.size());

        for(int i=0;i<data.size();i++)
        {
            contacts.remove(Constants.SHAREDPREFERENCE_ALL_CONTACTS_PREFIX + i + Constants.SHAREDPREFERENCE_ALL_CONTACTS_NAME);
            contacts.putString(Constants.SHAREDPREFERENCE_ALL_CONTACTS_PREFIX + i + Constants.SHAREDPREFERENCE_ALL_CONTACTS_NAME, data.get(i).getName());

            contacts.remove(Constants.SHAREDPREFERENCE_ALL_CONTACTS_PREFIX + i + Constants.SHAREDPREFERENCE_ALL_CONTACTS_EMAIL);
            contacts.putString(Constants.SHAREDPREFERENCE_ALL_CONTACTS_PREFIX + i + Constants.SHAREDPREFERENCE_ALL_CONTACTS_EMAIL, data.get(i).getEmail());

            contacts.remove(Constants.SHAREDPREFERENCE_ALL_CONTACTS_PREFIX + i + Constants.SHAREDPREFERENCE_ALL_CONTACTS_PHONE);
            contacts.putLong(Constants.SHAREDPREFERENCE_ALL_CONTACTS_PREFIX + i + Constants.SHAREDPREFERENCE_ALL_CONTACTS_PHONE, data.get(i).getPhone());

            contacts.remove(Constants.SHAREDPREFERENCE_ALL_CONTACTS_PREFIX + i + Constants.SHAREDPREFERENCE_ALL_CONTACTS_ADDRESS);
            contacts.putString(Constants.SHAREDPREFERENCE_ALL_CONTACTS_PREFIX + i + Constants.SHAREDPREFERENCE_ALL_CONTACTS_ADDRESS, data.get(i).getAddress());
        }
        return contacts.commit();
    }

    public static ArrayList<Contact> loadNuHUBs(Context context)
    {
        ArrayList<Contact> data = new ArrayList<>();

        SharedPreferences preference = context.getSharedPreferences(Constants.SHAREDPREFERENCE_ALL_CONTACTS, context.MODE_PRIVATE);

        int size = preference.getInt(Constants.SHAREDPREFERENCE_ALL_CONTACTS_SIZE, 0);

        for(int i=0;i<size;i++)
        {
            Contact myContact = new Contact();

            myContact.setName(preference.getString(Constants.SHAREDPREFERENCE_ALL_CONTACTS_PREFIX + i +Constants.SHAREDPREFERENCE_ALL_CONTACTS_NAME , ""));
            myContact.setEmail(preference.getString(Constants.SHAREDPREFERENCE_ALL_CONTACTS_PREFIX + i + Constants.SHAREDPREFERENCE_ALL_CONTACTS_EMAIL, ""));
            myContact.setPhone(preference.getLong(Constants.SHAREDPREFERENCE_ALL_CONTACTS_PREFIX + i + Constants.SHAREDPREFERENCE_ALL_CONTACTS_PHONE, 0));
            myContact.setAddress(preference.getString(Constants.SHAREDPREFERENCE_ALL_CONTACTS_PREFIX + i + Constants.SHAREDPREFERENCE_ALL_CONTACTS_ADDRESS, ""));

            data.add(myContact);
        }

        return data;
    }

    public static boolean addContact(Context context, Contact addContact)
    {
        ArrayList<Contact> data = loadNuHUBs(context);

        data.add(data.size(),addContact);

        return saveContacts(context, data);
    }

    public static boolean deleteContact(Context context, Contact deleteContact)
    {
        boolean isComplete = false;
        ArrayList<Contact> data = loadNuHUBs(context);

        data.add(data.size(), deleteContact);

        for(int i=0;i<data.size();i++)
        {
            if (data.get(i).getEmail().equals(deleteContact.getEmail())){
                data.remove(i);
                isComplete=true;
            }
        }

        if (isComplete){
            isComplete = saveContacts(context, data);
        }

        return isComplete;
    }

    public static boolean deleteAllContact(Context context)
    {
        ArrayList<Contact> data = new ArrayList<>();
        return saveContacts(context, data);
    }
}
