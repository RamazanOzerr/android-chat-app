package com.example.basicchatapp.Activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.basicchatapp.Fragments.FriendsFragment;
import com.example.basicchatapp.Fragments.MainScreenFragment;
import com.example.basicchatapp.Fragments.RecentChatsFragment;
import com.example.basicchatapp.Fragments.RequestsFragment;
import com.example.basicchatapp.R;
import com.example.basicchatapp.Adapters.CustomViewPager;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    ViewPager viewPager;

    TabLayout tabLayout;
    CustomViewPager customViewPager;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        viewPager = findViewById(R.id.viewpager_message);
        tabLayout = findViewById(R.id.tablayout);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.setTitle("CHATAPP");


        customViewPager = new CustomViewPager(getSupportFragmentManager());
        customViewPager.addFragment(new RecentChatsFragment(),"Chats");
        customViewPager.addFragment(new MainScreenFragment(),"Users");
        customViewPager.addFragment(new RequestsFragment(),"Requests");
        customViewPager.addFragment(new FriendsFragment(),"Friends");

        viewPager.setAdapter(customViewPager);

        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.search:
                Toast.makeText(getApplicationContext(),"CLICKED SEARCH",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.profile:
                startActivity(new Intent(getApplicationContext(),UserProfileDesign.class));
                return true;
            case R.id.log_out:
                firebaseAuth.signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Declare the launcher at the top of your Activity/Fragment:
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if(isGranted) {
                    // FCM SDK (and your app) can post notifications.
                } else {
                    // TODO: Inform user that that your app will not show notifications.
                    Toast.makeText(getApplicationContext(),"YOU WILL NOT NOTIFIED",Toast.LENGTH_LONG).show();
                }
            });


}