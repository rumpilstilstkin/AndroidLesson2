package com.geekbrains.navigationdrawerexample;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.geekbrains.navigationdrawerexample.fragments.EmptyFragment;


public class BottomTabNavigation extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static Intent getIntent(Context context, HomeScreen screen) {
        Intent intent = new Intent(context, BottomTabNavigation.class);
        intent.putExtra(EXTRA_SHOW_SCREEN, screen);
        return intent;
    }

    public static void launch(Context context, HomeScreen screen) {
        context.startActivity(getIntent(context, screen));
    }

    private static String EXTRA_SCREEN_DATA = "screen_data_extra";
    private static String EXTRA_SHOW_SCREEN = "show_screen_extra";

    private HomeScreen current = HomeScreen.FIRST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom);

        if (savedInstanceState != null) {
            current = (HomeScreen) savedInstanceState.getSerializable(EXTRA_SHOW_SCREEN);
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        bottomNavigationView.setSelectedItemId(current.getItemId());
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        current = (HomeScreen) intent.getSerializableExtra(EXTRA_SHOW_SCREEN);
        selectScreen(current);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(EXTRA_SHOW_SCREEN, current);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {
            selectScreen(HomeScreen.FIRST);
        }
        else if (id == R.id.nav_slideshow) {
            selectScreen(HomeScreen.SECOND);
        }
        else if (id == R.id.nav_manage) {
            selectScreen(HomeScreen.THIRD);
        }
        return true;
    }

    ///////////////////////////////////////////////////////////////////////////
    // Navigators
    ///////////////////////////////////////////////////////////////////////////

    private void selectScreen(HomeScreen screen) {
        current = screen;
        switch (screen.getItemId()) {
            case R.id.nav_gallery: {
                setNewScreen(EmptyFragment.getInstats(Color.BLUE));
                break;
            }
            case R.id.nav_slideshow: {
                setNewScreen(EmptyFragment.getInstats(Color.MAGENTA));
                break;
            }
            case R.id.nav_manage: {
                setNewScreen(EmptyFragment.getInstats(Color.RED));
                break;
            }
        }
    }

    private void setNewScreen(EmptyFragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content, fragment);
        fragmentTransaction.commit();
    }
}
