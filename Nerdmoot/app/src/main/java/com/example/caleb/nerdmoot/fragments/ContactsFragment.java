package com.example.caleb.nerdmoot.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caleb.nerdmoot.R;
import com.example.caleb.nerdmoot.objects.Contact;
import com.example.caleb.nerdmoot.objects.Post;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Created by caleb on 3/5/2017.
 */

public class ContactsFragment extends Fragment{

    View contactsView;

    private static ArrayList<Contact> contacts = new ArrayList<>();
    private static List<Contact> tempContacts;
    private static ArrayAdapter<Contact> adapter;
    private static Contact current;
    private EditText etSearch;

    //Methods for search Lists
    public static void cloneContacts(List<Contact> n) {

        for (int i = 0 ; i<contacts.size();i++){
            n.add(contacts.get(i)) ;
        }
    }

    public static void setContacts(List<Contact> s) {
        contacts.clear();

        for (int i = 0; i < s.size(); i++){
            contacts.add(s.get(i)) ;
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contactsView = inflater.inflate(R.layout.fragment_contacts, container, false);
        System.out.println("Contacts");

        ListView list = (ListView) contactsView.findViewById(R.id.lvContacts);
        tempContacts = new ArrayList<Contact>();
        cloneContacts(tempContacts);

        //temp objects for testing
        Contact contact1 = new Contact("Caleb Arcega", "770-990-8285");
        Contact contact2 = new Contact("Hayley", "777-777-7777");
        Contact contact3 = new Contact("Nick Grimaldi", "888-888-8888");
        Contact contact4 = new Contact("Steven", "999-999-9999");
        Contact contact5 = new Contact("Ash Mady", "555-555-5555");

        //always clear lists so that they do not duplicate contents every time fragment is inflated
        tempContacts.clear();
        tempContacts.add(contact1);
        tempContacts.add(contact2);
        tempContacts.add(contact3);
        tempContacts.add(contact4);
        tempContacts.add(contact5);
        contacts.clear();
        contacts.add(contact1);
        contacts.add(contact2);
        contacts.add(contact3);
        contacts.add(contact4);
        contacts.add(contact5);

        etSearch = (EditText) contactsView.findViewById(R.id.etSearchContacts);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")) {
                    //reset list
                    setContacts(tempContacts);
                    adapter.notifyDataSetChanged();
                }
                else {
                    //perform search
                    searchItem(charSequence.toString());
                }

            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });

        populateLV();
        registerClickCallback();

        return contactsView;
    }


    private void searchItem(String s){
        setContacts(tempContacts); //<--Ensures backspacing will bring back everything that matches
        Iterator<Contact> iter = contacts.iterator();
        Contact d;
        while (iter.hasNext()) {
            d = iter.next();
            if (!d.getName().toLowerCase().contains(s.toLowerCase()) && !d.getNumber().toLowerCase().contains(s.toLowerCase()))
                iter.remove();
        }
        //update adapter
        adapter.notifyDataSetChanged();
    }


    private void registerClickCallback() {

        ListView list = (ListView) contactsView.findViewById(R.id.lvContacts);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                current = contacts.get(position);

                // launchCall(position);

                Toast.makeText(getActivity(), current.getName() + " Loading", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void populateLV() {
        adapter = new MyListAdapter();
        ListView list = (ListView) contactsView.findViewById(R.id.lvContacts);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Contact> {
        String temp = "";
        public MyListAdapter() {
            super(getActivity(), R.layout.contact_item, contacts);
        }





        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //Make sure not given a null view
            View itemView = convertView;
            if (itemView == null) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                itemView = inflater.inflate(R.layout.contact_item, parent, false);
            }


            current = contacts.get(position);
            TextView tvName = (TextView) itemView.findViewById(R.id.tvName_item);
            TextView tvNumber = (TextView) itemView.findViewById(R.id.tvNumber_item);

            temp = current.getName();
            if(temp.length() > 80){
                temp = temp.substring(0, 80)+" . . .";
                System.out.println(temp+"  "+temp.length());
            }
            tvName.setText(temp);
            tvNumber.setText(current.getNumber());


            return itemView;
        }
    }
}
