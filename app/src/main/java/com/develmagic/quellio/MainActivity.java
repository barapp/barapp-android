package com.develmagic.quellio;

//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.design.widget.TabLayout;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//import android.support.v4.view.ViewPager;
//import android.support.v7.app.AppCompatActivity;
//
//import java.util.ArrayList;
//import java.util.List;
//
////import fragments.base.Home;
////import fragments.base.Profile;
////import fragments.base.Setting;
//
////import android.os.Bundle;
////import android.support.annotation.Nullable;
////import android.support.design.widget.TabLayout;
////import android.support.v4.app.FragmentPagerAdapter;
////import android.support.v4.view.ViewPager;
////import android.support.v7.app.AppCompatActivity;
////import android.view.LayoutInflater;
////import android.view.View;
////import android.view.ViewGroup;
////import android.widget.TextView;
////
////import java.util.ArrayList;
////import java.util.List;
//
////public class MainActivity extends AppCompatActivity {
////
////    private ViewPager viewPager;
////    private TabLayout tabLayout;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
////
////        viewPager = (ViewPager) findViewById(R.id.container);
////        createViewPager(viewPager);
////
////        tabLayout = (TabLayout) findViewById(R.id.tabs);
////        tabLayout.setupWithViewPager(viewPager);
////        createTabIcons();
////
////        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
////        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
////        mViewPager = (ViewPager) findViewById(R.id.container);
////
////        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
////        tabOne.setText("Beer");
////        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_beer, 0, 0);
////        tabLayout.getTabAt(0).setCustomView(tabOne);
////
////        tabLayout.setupWithViewPager(mViewPager);
////
//////        tabLayout.
////
//////        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//////        fab.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View view) {
//////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//////                        .setAction("Action", null).show();
//////            }
//////        });
////
////    }
////
////    private void createTabIcons() {
////
////        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
////        tabOne.setText("Tab 1");
////        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_beer, 0, 0);
////        tabLayout.getTabAt(0).setCustomView(tabOne);
////
////        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
////        tabTwo.setText("Tab 2");
////        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_beer, 0, 0);
////        tabLayout.getTabAt(1).setCustomView(tabTwo);
////
////        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
////        tabThree.setText("Tab 3");
////        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_beer, 0, 0);
////        tabLayout.getTabAt(2).setCustomView(tabThree);
////    }
////
////    private void createViewPager(ViewPager viewPager) {
////        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
////        adapter.addFrag(new Fragment1(), "Tab 1");
////        adapter.addFrag(new Fragment1(), "Tab 2");
////        adapter.addFrag(new Fragment1(), "Tab 3");
////        viewPager.setAdapter(adapter);
////    }
////
////    class ViewPagerAdapter extends FragmentPagerAdapter {
////        private final List<Fragment> mFragmentList = new ArrayList<>();
////        private final List<String> mFragmentTitleList = new ArrayList<>();
////
////        public ViewPagerAdapter(FragmentManager manager) {
////            super(manager);
////        }
////
////        @Override
////        public Fragment getItem(int position) {
////            return mFragmentList.get(position);
////        }
////
////        @Override
////        public int getCount() {
////            return mFragmentList.size();
////        }
////
////        public void addFrag(Fragment fragment, String title) {
////            mFragmentList.add(fragment);
////            mFragmentTitleList.add(title);
////        }
////
////        @Override
////        public CharSequence getPageTitle(int position) {
////            return mFragmentTitleList.get(position);
////        }
////    }
////
//////    @Override
//////    public boolean onCreateOptionsMenu(Menu menu) {
//////        // Inflate the menu; this adds items to the action bar if it is present.
//////        getMenuInflater().inflate(R.menu.menu_main, menu);
//////        return true;
//////    }
////
//////    @Override
//////    public boolean onOptionsItemSelected(MenuItem item) {
//////        // Handle action bar item clicks here. The action bar will
//////        // automatically handle clicks on the Home/Up button, so long
//////        // as you specify a parent activity in AndroidManifest.xml.
//////        int id = item.getItemId();
//////
//////        //noinspection SimplifiableIfStatement
//////        if (id == R.id.action_settings) {
//////            return true;
//////        }
//////
//////        return super.onOptionsItemSelected(item);
//////    }
////
////    /**
////     * A placeholder fragment containing a simple view.
////     */
////    public static class PlaceholderFragment extends Fragment {
////        /**
////         * The fragment argument representing the section number for this
////         * fragment.
////         */
////        private static final String ARG_SECTION_NUMBER = "section_number";
////
////        public PlaceholderFragment() {
////        }
////
////        /**
////         * Returns a new instance of this fragment for the given section
////         * number.
////         */
////        public static PlaceholderFragment newInstance(int sectionNumber) {
////            PlaceholderFragment fragment = new PlaceholderFragment();
////            Bundle args = new Bundle();
////            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
////            fragment.setArguments(args);
////            return fragment;
////        }
////
////        @Override
////        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
////            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
////            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
////            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
////            return rootView;
////        }
////    }
////
////}
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//
//        //Add tabs icon with setIcon() or simple text with .setText()
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_beer));
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_beer));
//        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.ic_beer));
//
//        //Add fragments
//        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
//        adapter.addFragment(new Fragment1());
////        adapter.addFragment(new Profile());
////        adapter.addFragment(new Setting());
//
//        //Setting adapter
//        viewPager.setAdapter(adapter);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
//
//    }
//
//    class PagerAdapter extends FragmentPagerAdapter {
//
//        private final List<Fragment> mFragments = new ArrayList<>();
//
//        public PagerAdapter(FragmentManager fm) {
//            super(fm);
//        }
//
//        public void addFragment(Fragment fragment) {
//            mFragments.add(fragment);
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return mFragments.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return mFragments.size();
//        }
//    }
//}



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.develmagic.quellio.basket.BasketProductsFragment;
import com.develmagic.quellio.tabmenu.IconLabelTabLayout;

import java.util.ArrayList;
import java.util.List;
import com.develmagic.quellio.list.ProductList;

public class MainActivity extends AppCompatActivity {

    private IconLabelTabLayout tabLayout;
    private ViewPager viewPager;
    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
        setContentView(R.layout.activity_main);

        tabLayout = (IconLabelTabLayout) findViewById(R.id.tabs);

        viewPager = (ViewPager) findViewById(R.id.container);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFrag(new ProductList(), "Coffee");
        viewPagerAdapter.addFrag(new ProductList(), "Beer");
        viewPagerAdapter.addFrag(new ProductList(), "Soft drinks");
        viewPagerAdapter.addFrag(new ProductList(), "Pastry");
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.decorateTab(getApplicationContext(), R.drawable.ic_coffee, 0);
        tabLayout.decorateTab(getApplicationContext(), R.drawable.ic_beer, 1);
        tabLayout.decorateTab(getApplicationContext(), R.drawable.ic_softdrinks, 2);
        tabLayout.decorateTab(getApplicationContext(), R.drawable.ic_pastry, 3);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView myList = (RecyclerView) findViewById(R.id.basket_recycler_view);
        myList.setLayoutManager(layoutManager);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public static MainActivity getInstance() {
        return instance;
    }

}