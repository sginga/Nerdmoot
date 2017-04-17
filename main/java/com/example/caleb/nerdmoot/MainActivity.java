package com.example.caleb.nerdmoot;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;

import com.example.caleb.nerdmoot.fragments.ContactsFragment;
import com.example.caleb.nerdmoot.fragments.ForumsFragment;
import com.example.caleb.nerdmoot.fragments.ProfileFragment;
import com.example.caleb.nerdmoot.fragments.SavedFragment;
import com.example.caleb.nerdmoot.fragments.SettingsFragment;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    PNConfiguration pnConfiguration = new PNConfiguration();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //initializes pubnub to our free connection
        pnConfiguration.setSubscribeKey("sub-c-b3e75a4e-212a-11e7-894d-0619f8945a4f");
        pnConfiguration.setPublishKey("pub-c-e3eede0d-be2c-4792-93eb-f82b7817bae2");
        pnConfiguration.setSecure(false);
        PubNub pubnub = new PubNub(pnConfiguration);

        final Spinner spnAdd = (Spinner) findViewById(R.id.spnAdd);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_main, new ForumsFragment()).commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        menu.clear();
        menu.add("New Forum");
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final Intent intent = new Intent(getApplicationContext(), CreateForum.class);
        int id = item.getItemId();
        FragmentManager fm = getFragmentManager();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            fm.beginTransaction().replace(R.id.content_main, new SettingsFragment()).commit();
            return true;
        }
        else if(id == 0){
            startActivity(intent);
            return true;
        }




        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fm = getFragmentManager();

        if (id == R.id.nav_forums) {
            fm.beginTransaction().replace(R.id.content_main, new ForumsFragment()).commit();
        } else if (id == R.id.nav_contacts) {
            fm.beginTransaction().replace(R.id.content_main, new ContactsFragment()).commit();
        } else if (id == R.id.nav_saved) {
            fm.beginTransaction().replace(R.id.content_main, new SavedFragment()).commit();
        } else if (id == R.id.nav_profile) {
            fm.beginTransaction().replace(R.id.content_main, new ProfileFragment()).commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
