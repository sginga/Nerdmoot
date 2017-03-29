package com.example.caleb.nerdmoot.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.caleb.nerdmoot.R;


/**
 * Created by caleb on 3/5/2017.
 */

public class ContactsFragment extends Fragment{

    View contactsView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contactsView = inflater.inflate(R.layout.fragment_contacts, container, false);
        System.out.println("Contacts");
        return contactsView;
    }
}
