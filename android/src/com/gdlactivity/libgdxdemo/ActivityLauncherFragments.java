package com.gdlactivity.libgdxdemo;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

public class ActivityLauncherFragments extends ActionBarActivity implements  AndroidFragmentApplication.Callbacks{

    private Toolbar appbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appbar = (Toolbar)findViewById(R.id.appbar);
        setSupportActionBar(appbar);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        navView = (NavigationView)findViewById(R.id.navview);
        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        boolean fragmentTransaction = false;

                        LibGDXFragment fragment = new LibGDXFragment();

                        Bundle bundle = new Bundle();

                        switch (menuItem.getItemId()) {
                            case R.id.menu_seccion_1:
                                fragment = new LibGDXFragment();
                                fragmentTransaction = true;
                                bundle.putInt("screen", 0);
                                break;
                            case R.id.menu_seccion_2:
                                fragment = new LibGDXFragment();
                                fragmentTransaction = true;
                                bundle.putInt("screen", 1);
                                break;
                            case R.id.menu_seccion_3:
                                fragment = new LibGDXFragment();
                                fragmentTransaction = true;
                                bundle.putInt("screen", 2);
                                break;
                            case R.id.menu_seccion_4:
                                fragment = new LibGDXFragment();
                                fragmentTransaction = true;
                                bundle.putInt("screen", 3);
                                break;
                            case R.id.menu_option_1:
                                Log.i("NavigationView", "Option 1");
                                break;
                            case R.id.menu_option_2:
                                Log.i("NavigationView", "Option 2");
                                break;
                        }

                        fragment.setArguments(bundle);

                        if(fragmentTransaction) {
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.content_frame, fragment)
                                    .commit();

                            menuItem.setChecked(true);
                            getSupportActionBar().setTitle(menuItem.getTitle());
                        }

                        drawerLayout.closeDrawers();

                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void exit() {

    }
}