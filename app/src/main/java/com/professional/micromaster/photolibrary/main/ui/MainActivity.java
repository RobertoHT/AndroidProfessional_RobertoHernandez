package com.professional.micromaster.photolibrary.main.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.professional.micromaster.photolibrary.R;
import com.professional.micromaster.photolibrary.fragments.GalleryFragment;
import com.professional.micromaster.photolibrary.fragments.InspectFragment;
import com.professional.micromaster.photolibrary.fragments.main.ui.MainScreenFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);
        showFragment(MainScreenFragment.class, 0);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Class fragment = null;

        switch (id) {
            case R.id.nav_main:
                fragment = MainScreenFragment.class;
                showFragment(fragment, R.string.nav_main);
                break;
            case R.id.nav_camera:
                Log.d("NAV","Camera");
                break;
            case R.id.nav_gallery:
                fragment = GalleryFragment.class;
                showFragment(fragment, R.string.nav_gallery);
                break;
            case R.id.nav_inspect:
                fragment = InspectFragment.class;
                showFragment(fragment, R.string.nav_inspect);
                break;
            case R.id.nav_share:
                Log.d("NAV","Share");
                break;
            case R.id.nav_logout:
                Log.d("NAV","Logout");
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showFragment(Class fragmentClass, int title) {
        Fragment fragment = null;

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        if (title != 0) {
            toolbar.setTitle(title);
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.flContent, fragment)
                .commit();
    }
}
