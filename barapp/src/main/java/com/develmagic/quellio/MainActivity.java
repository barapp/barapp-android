package com.develmagic.quellio;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.develmagic.quellio.basket.Basket;
import com.develmagic.quellio.basket.BasketAdapter;
import com.develmagic.quellio.list.ProductList;
import com.develmagic.quellio.tabmenu.IconLabelTabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rii.solutions.campus.data.Category;
import rii.solutions.campus.data.MenuItem;
import rii.solutions.campus.data.OrderResult;
import rii.solutions.campus.data.ResponseWrapperModel;
import rii.solutions.campus.data.source.CampusDataSource;
import rii.solutions.campus.data.source.Credentials;

public class MainActivity extends AppCompatActivity  {

    private IconLabelTabLayout tabLayout;
    private ViewPager viewPager;
    private static MainActivity instance;
    public static TextView orderSummary;
    public static TextView orderCount;
    private Button finishOrder;
    private Cache cache;
    private Network network = new BasicNetwork(new HurlStack());
    private RequestQueue mRequestQueue;
    private List<MenuItem> items;
    private GridView basketProducts;

    public static final int PICK_MEMBER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cache = new DiskBasedCache(getCacheDir(), 10 * 1024 * 1024); // 1MB cap
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();

        instance = this;
        setContentView(R.layout.activity_main);

        CampusDataSource service = new CampusDataSource(Credentials.API_USERNAME, Credentials.API_PASS);
        Call<ResponseWrapperModel<List<MenuItem>>> call = service.menuItems();
        call.enqueue(new Callback<ResponseWrapperModel<List<MenuItem>>>() {
            @Override
            public void onResponse(@NonNull Call<ResponseWrapperModel<List<MenuItem>>> call,
                                   @NonNull Response<ResponseWrapperModel<List<MenuItem>>> response) {
                ResponseWrapperModel<List<MenuItem>> responseBody = response.body();
                if (responseBody != null) {
                    getInstance().setItems(responseBody.results);
                    initializeComponents();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseWrapperModel<List<MenuItem>>> call, @NonNull Throwable t) {
                Log.e("barapp", "Cannot retrieve items");
                Snackbar snackbar = Snackbar.make(findViewById(R.id.container), "Cannot retrieve items", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

    }

    private void initializeComponents() {
        tabLayout = (IconLabelTabLayout) findViewById(R.id.tabs);

        viewPager = (ViewPager) findViewById(R.id.container);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        ProductList coffeeFragment = new ProductList();
        coffeeFragment.setCategory(Category.COFFEE);
        viewPagerAdapter.addFrag(coffeeFragment, "Coffee");

        ProductList beerFragment = new ProductList();
        beerFragment.setCategory(Category.BEER);
        viewPagerAdapter.addFrag(beerFragment, "Beer");

        ProductList softDrinksFragment = new ProductList();
        softDrinksFragment.setCategory(Category.SOFT_DRINKS);
        viewPagerAdapter.addFrag(softDrinksFragment, "Soft drinks");

        ProductList pastryFragment = new ProductList();
        pastryFragment.setCategory(Category.PASTRY);
        viewPagerAdapter.addFrag(pastryFragment, "Pastry");

        viewPager.setAdapter(viewPagerAdapter);
        finishOrder = (Button) findViewById(R.id.finish_order);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.decorateTab(getApplicationContext(), R.drawable.ic_coffee, 0);
        tabLayout.decorateTab(getApplicationContext(), R.drawable.ic_beer, 1);
        tabLayout.decorateTab(getApplicationContext(), R.drawable.ic_softdrinks, 2);
        tabLayout.decorateTab(getApplicationContext(), R.drawable.ic_pastry, 3);

        orderSummary = (TextView) findViewById(R.id.summaryprice);
        orderCount = (TextView) findViewById(R.id.summarycount);

        Basket.getInstance().setAdapter(new BasketAdapter(getBaseContext()));
        basketProducts = (GridView) findViewById(R.id.basket_grid_view);
        basketProducts.setNumColumns(3);
        basketProducts.setHorizontalSpacing(10);
        basketProducts.setAdapter(Basket.getInstance().getAdapter());
        basketProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (Basket.getInstance().get(position).getQuantity() == 1) {
                    Basket.getInstance().remove(position);
                } else {
                    Basket.getInstance().get(position).decrement();
                }
                Basket.getInstance().updateUI();
            }
        });
    }

    public void clickFinishOrder(View view) {
        if (Basket.getInstance().size() == 0) {
            Snackbar snackbar = Snackbar.make(this.viewPager, "No products selected", Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }
        Intent intent = new Intent(this, SelectMemberActivity.class);
        startActivityForResult(intent, PICK_MEMBER_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_MEMBER_REQUEST) {
            if (resultCode == OrderResult.OK) {
                Snackbar snackbar = Snackbar.make(this.viewPager, "Order sucessfully processed", Snackbar.LENGTH_LONG);
                snackbar.show();
                Basket.getInstance().clear();
                Basket.getInstance().updateUI();
            }
        }
    }

    public static MainActivity getInstance() {
        return instance;
    }

    public Button getFinishOrder() {
        return finishOrder;
    }

    private static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
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

        void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

}