package com.rokomari_coding_test.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.rokomari_coding_test.R;
import com.rokomari_coding_test.databinding.ActivityHomeBinding;
import com.rokomari_coding_test.fragments.DoneTaskFragment;
import com.rokomari_coding_test.fragments.InProgressFragment;
import com.rokomari_coding_test.fragments.OpenTaskFragment;
import com.rokomari_coding_test.fragments.TestTaskFragment;

public class HomeActivity extends AppCompatActivity{
    private ActivityHomeBinding activityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        initObjects();
        bindUiWithComponents();
    }

    private void initObjects() {
        //loading the default fragment
        loadFragment(OpenTaskFragment.newInstance());
    }

    private void bindUiWithComponents() {
        activityBinding.bottomNavigationView.setItemIconTintList(null);
        activityBinding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment fragment = null;
                switch(id){
                    case R.id.nav_open:
                        fragment = OpenTaskFragment.newInstance();
                        break;
                    case R.id.nav_in_progress:
                        fragment = InProgressFragment.newInstance();
                        break;
                    case R.id.nav_test:
                        fragment = TestTaskFragment.newInstance();
                        break;
                    case R.id.nav_done:
                        fragment = DoneTaskFragment.newInstance();
                        break;
                }
                return loadFragment(fragment);
            }
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.flFragment, fragment);
            fragmentTransaction.commit();
            return true;
        }
        return false;
    }
}