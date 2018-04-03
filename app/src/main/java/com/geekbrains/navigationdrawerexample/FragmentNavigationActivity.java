package com.geekbrains.navigationdrawerexample;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.geekbrains.navigationdrawerexample.fragments.EmptyFragment;


public class FragmentNavigationActivity extends AppCompatActivity{

    public static void launch(Context context) {
        context.startActivity(new Intent(context, FragmentNavigationActivity.class));
    }

    NavigationView navigationView;

    ///////////////////////////////////////////////////////////////////////////
    // Lifecycle
    ///////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_fragment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Floating button - часть материального дизайна и будет рассмотрена позже в уроке 6.

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.VISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Toast.makeText(FragmentNavigationActivity.this, "Replace with your own action", Toast.LENGTH_SHORT).show();
            }
        });

        // Находим drawer в ресурсах.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Этот класс связывает drawer и toolbar, позволяя drawer-у корректно отображаться с hamburger menu в
        // action bar, а также выдвигает drawer и задвигает.
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        // Вешаем слушателя на drawer.
        drawer.addDrawerListener(toggle);
        // Cинхронизирует hamburger menu с drawer.
        toggle.syncState();

        // Специальный класс для размещения пунктов списка в drawer'е
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        initHeader();
        setNewScreen(EmptyFragment.getInstats(Color.GREEN));
    }

    private void initHeader(){
        ImageView imageView = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.imageView);
        TextView nameView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.nameView);
        imageView.setImageResource(R.drawable.ic_profile);
        nameView.setText("Nina");
    }

    // Отрабатываем нажатие на кнопку Назад:
    // если drawer открыт, то закрываем его, если закрыт - закрываем приложение
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setNewScreen(EmptyFragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content, fragment);
        fragmentTransaction.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //закрываем NavigationView
        //параметр определяет анимацию закрытия
        drawer.closeDrawer(GravityCompat.START);
    }
}
