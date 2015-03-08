package br.com.livroandroid.fragments_actionbartabs;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setNavigationMode(android.app.ActionBar.NAVIGATION_MODE_TABS);

        // Cria as tabs (Passa como parâmetro o índice de cada tab: 1,2,3)
        actionBar.addTab(actionBar.newTab().setText("Frag 1").setTabListener(new MyTabListener(this,new Fragment1())));
        actionBar.addTab(actionBar.newTab().setText("Frag 2").setTabListener(new MyTabListener(this,new Fragment2())));
        actionBar.addTab(actionBar.newTab().setText("Frag 3").setTabListener(new MyTabListener(this,new Fragment3())));
    }
}
