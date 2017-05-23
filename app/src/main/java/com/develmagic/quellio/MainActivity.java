package com.develmagic.quellio;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.develmagic.quellio.basket.Basket;
import com.develmagic.quellio.list.ProductList;
import com.develmagic.quellio.service.BackendService;
import com.develmagic.quellio.service.ServiceGenerator;
import com.develmagic.quellio.service.dto.OrderResult;
import com.develmagic.quellio.service.dto.ProductDTOList;
import com.develmagic.quellio.tabmenu.IconLabelTabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private IconLabelTabLayout tabLayout;
    private ViewPager viewPager;
    private static MainActivity instance;
    public static TextView orderSummary;
    public static TextView orderCount;
    private Cache cache;
    private Network network = new BasicNetwork(new HurlStack());
    private RequestQueue mRequestQueue;
    private ProductDTOList items;

    public static final int PICK_MEMBER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cache = new DiskBasedCache(getCacheDir(), 10 * 1024 * 1024); // 1MB cap
        mRequestQueue = new RequestQueue(cache, network);
        mRequestQueue.start();

        instance = this;
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .build();
        BackendService service = ServiceGenerator.createService(BackendService.class, Constants.API_USERNAME, Constants.API_PASS);
        Call<ProductDTOList> call = service.listItems();
        call.enqueue(new Callback<ProductDTOList>() {
            @Override
            public void onResponse(Call<ProductDTOList> call, Response<ProductDTOList> response) {
                getInstance().setItems(response.body());
                initializeComponents();
            }

            @Override
            public void onFailure(Call<ProductDTOList> call, Throwable t) {

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

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.decorateTab(getApplicationContext(), R.drawable.ic_coffee, 0);
        tabLayout.decorateTab(getApplicationContext(), R.drawable.ic_beer, 1);
        tabLayout.decorateTab(getApplicationContext(), R.drawable.ic_softdrinks, 2);
        tabLayout.decorateTab(getApplicationContext(), R.drawable.ic_pastry, 3);

        orderSummary = (TextView) findViewById(R.id.summaryprice);
        orderCount = (TextView) findViewById(R.id.summarycount);

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

    public ProductDTOList getItems() {
        return items;
    }

    public void setItems(ProductDTOList items) {
        this.items = items;
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

}