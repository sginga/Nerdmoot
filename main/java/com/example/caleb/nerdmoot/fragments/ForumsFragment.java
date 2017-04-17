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
import com.example.caleb.nerdmoot.objects.Post;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ForumsFragment extends Fragment {

    View forumsView;


    private static ArrayList<Post> forums = new ArrayList<>();
    private static List<Post> tempForums;
    private static ArrayAdapter<Post> adapter;
    private static Post current;

    private EditText etSearch;

    //Methods for search Lists
    public static void cloneContacts(List<Post> n) {

        for (int i = 0 ; i<forums.size();i++){
            n.add(forums.get(i)) ;
        }
    }

    public static void setContacts(List<Post> s) {
        forums.clear();

        for (int i = 0; i < s.size(); i++){
            forums.add(s.get(i)) ;
        }
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        forumsView = inflater.inflate(R.layout.fragment_forums, container, false);
        System.out.println("Forums");

        ListView list = (ListView) forumsView.findViewById(R.id.lvForums);
        tempForums = new ArrayList<Post>();
        cloneContacts(tempForums);

        //Test Posts
        Post forum1 = new Post();
        forum1.setTitle("Class discussion on dummy topics to test the length abbreviation for this application when using over 80 characters.");
        forum1.setContent("THIS IS AS DUMMY POST");

        Post forum2 = new Post();
        forum2.setTitle("Second dummy post for search testing");
        forum2.setContent("THIS IS AS DUMMY POST");

        forums.clear();
        forums.add(forum1);
        forums.add(forum2);
        tempForums.clear();
        tempForums.add(forum1);
        tempForums.add(forum2);


        etSearch = (EditText) forumsView.findViewById(R.id.etSearchForums);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals("")) {
                    //reset list
                    setContacts(tempForums);
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

        return forumsView;
    }


    private void searchItem(String s){
        setContacts(tempForums); //<--Ensures backspacing will bring back everything that matches
        Iterator<Post> iter = forums.iterator();
        Post d;
        while (iter.hasNext()) {
            d = iter.next();
            if (!d.getTitle().toLowerCase().contains(s.toLowerCase()) && !d.getContent().toLowerCase().contains(s.toLowerCase()) && !d.getDate().toLowerCase().contains(s.toLowerCase()))
                iter.remove();
        }
        //update adapter
        adapter.notifyDataSetChanged();
    }
    private void registerClickCallback() {

        ListView list = (ListView) forumsView.findViewById(R.id.lvForums);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                current = forums.get(position);

                // launchCall(position);

                Toast.makeText(getActivity(), current.getTitle() + " Loading", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void populateLV() {
        adapter = new MyListAdapter();
        ListView list = (ListView) forumsView.findViewById(R.id.lvForums);
        list.setAdapter(adapter);
    }

    private class MyListAdapter extends ArrayAdapter<Post> {
        String temp = "";
        public MyListAdapter() {
            super(getActivity(), R.layout.forum_item, forums);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //Make sure not given a null view
            View itemView = convertView;
            if (itemView == null) {
                LayoutInflater inflater = LayoutInflater.from(getActivity());
                itemView = inflater.inflate(R.layout.forum_item, parent, false);
            }


            current = forums.get(position);
            TextView tvTitle = (TextView) itemView.findViewById(R.id.tvTitle_item);
            TextView tvSubTime = (TextView) itemView.findViewById(R.id.tvSubTime_item);

            temp = current.getTitle();
            if(temp.length() > 80){
                temp = temp.substring(0, 80)+" . . .";
                System.out.println(temp+"  "+temp.length());
            }
            tvTitle.setText(temp);
            tvSubTime.setText(current.getDate());


            return itemView;
        }
    }
}