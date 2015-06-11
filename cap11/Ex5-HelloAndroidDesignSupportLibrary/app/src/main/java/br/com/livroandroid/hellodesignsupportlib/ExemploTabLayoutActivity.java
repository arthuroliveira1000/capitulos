package br.com.livroandroid.hellodesignsupportlib;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class ExemploTabLayoutActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo_tab_layout);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.tabanim_viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(this);
    }

    void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return new PlanetaFragment();
        }

        @Override
        public int getCount() {
            return 3;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return "Tab "+ position;
        }
    }

    @SuppressLint("ValidFragment")
    public static class PlanetaFragment extends Fragment {
        PlanetaAdapter adapter;
        private RecyclerView recyclerView;
        private List<Planeta> planetas;

        public PlanetaFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.planeta_fragment, container, false);

            recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            //recyclerView.setHasFixedSize(true);

            planetas = Planeta.getPlanetas();
            recyclerView.setAdapter(adapter = new PlanetaAdapter(getActivity(), planetas, onClickPlaneta()));

            return view;
        }

        protected PlanetaAdapter.PlanetaOnClickListener onClickPlaneta() {
            final Intent intent = new Intent(getActivity(), PlanetaActivity.class);

            return new PlanetaAdapter.PlanetaOnClickListener() {
                @Override
                public void onClickPlaneta(PlanetaAdapter.PlanetasViewHolder holder, int idx) {
                    Planeta p = planetas.get(idx);

                    ImageView img = holder.img;
                    intent.putExtra("imgPlaneta", p.img);
                    String key = getString(R.string.transition_key);

                    // Compat
                    ActivityOptionsCompat opts = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), img, key);
                    ActivityCompat.startActivity(getActivity(), intent, opts.toBundle());
                }
            };
        }

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        Snackbar
                .make(coordinatorLayout, "Tab: " + tab.getText(), Snackbar.LENGTH_LONG)
                .setAction("Ok", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getBaseContext(), "OK!", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
