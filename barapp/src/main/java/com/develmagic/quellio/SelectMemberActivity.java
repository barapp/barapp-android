package com.develmagic.quellio;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.develmagic.quellio.basket.Basket;
import com.develmagic.quellio.basket.ProductQuantity;
import com.develmagic.quellio.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rii.solutions.campus.data.CampusMember;
import rii.solutions.campus.data.MenuItem;
import rii.solutions.campus.data.OrderResult;
import rii.solutions.campus.data.ResponseWrapperModel;
import rii.solutions.campus.data.source.CampusDataSource;
import rii.solutions.campus.data.source.Credentials;

/**
 * Created by mejmo on 15.5.2017.
 */

public class SelectMemberActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ListView myListView;
    private SelectMemberActivity instance;
    private List<CampusMember> items;
    private CampusDataSource mCampusSource;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instance = this;

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }

        setContentView(R.layout.activity_select_member);

        mCampusSource = new CampusDataSource(Credentials.API_USERNAME, Credentials.API_PASS);
        Call<ResponseWrapperModel<List<CampusMember>>> call = mCampusSource.members();
        call.enqueue(new Callback<ResponseWrapperModel<List<CampusMember>>>() {
            @Override
            public void onResponse(@NonNull Call<ResponseWrapperModel<List<CampusMember>>> call,
                                   @NonNull Response<ResponseWrapperModel<List<CampusMember>>> response) {
                ResponseWrapperModel<List<CampusMember>> body = response.body();
                if (body != null) {
                    instance.setItems(body.results);
                    initializeComponents();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseWrapperModel<List<CampusMember>>> call,
                                  @NonNull Throwable t) {
                Log.e("barapp", "Cannot get members list", t);
            }
        });

    }

    public void initializeComponents() {
        myListView = (ListView) findViewById(R.id.myListView);

        myListView.setBackgroundColor(ResourcesCompat.getColor(getResources(),
                R.color.colorPrimary,
                getTheme()));
        myListView.setFastScrollEnabled(true);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, final int i, long l) {
                final CampusMember dto = (CampusMember) myListView.getItemAtPosition(i);

                AlertDialog.Builder alert = new AlertDialog.Builder(instance);
                alert.setTitle("Confirm order");
                alert.setMessage("Order will be billed to " + dto.toString());
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {

                        List<MenuItem> dtos = new ArrayList<>();
                        for (ProductQuantity pq : Basket.getInstance()) {
                            for (int i = 0; i < pq.getQuantity(); i++) {
                                MenuItem dto = new MenuItem();
                                dto.setId(pq.getProductId());
                                dtos.add(dto);
                            }
                        }

                        Call<OrderResult> call = mCampusSource.order(Util.generateTransaction(dto.getId(), dtos));
                        call.enqueue(new Callback<OrderResult>() {
                            @Override
                            public void onResponse(Call<OrderResult> call, Response<OrderResult> response) {
                                OrderResult result = response.body();

                                if (result == null || result.getResultCode() == OrderResult.ERROR) {
                                    Snackbar snackbar = Snackbar.make(view, "Cannot make order: " + result.getError(), Snackbar.LENGTH_LONG);
                                    snackbar.show();
                                } else {
                                    dialog.dismiss();
                                    instance.setResult(OrderResult.ERROR);
                                    Intent returnIntent = new Intent();
                                    setResult(OrderResult.OK, returnIntent);
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<OrderResult> call, Throwable t) {
                                Log.e("barapp", "Cannot get members list", t);
                                Snackbar snackbar = Snackbar.make(view, "Cannot make order", Snackbar.LENGTH_LONG);
                                snackbar.show();
                            }
                        });
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alert.show();
            }
        });
        MemberListAdapter adapter = new MemberListAdapter(
                getApplicationContext(), android.R.layout.simple_list_item_1,
                items);
        myListView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView)
                MenuItemCompat.getActionView(menu.findItem(R.id.app_bar_search));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        searchView.setOnQueryTextListener(this);
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.requestFocus();

        return true;
    }

    public List<CampusMember> getItems() {
        return items;
    }

    public void setItems(List<CampusMember> items) {
        this.items = items;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        MemberListAdapter adapter = (MemberListAdapter) myListView.getAdapter();
        Log.d("SelectMember", newText);
        adapter.getFilter().filter(newText);
        return true;
    }

    private static class MemberListAdapter extends ArrayAdapter<CampusMember> implements SectionIndexer {
        HashMap<String, Integer> azIndexer;
        String[] sections;

        MemberListAdapter(Context context, int textViewResourceId, @NonNull List<CampusMember> objects) {
            super(context, textViewResourceId, objects);
            initLetterIndexForFastSearch(objects);
        }

        private void initLetterIndexForFastSearch(@NonNull List<CampusMember> objects) {
            azIndexer = new HashMap<>();

            int size = objects.size();
            for (int i = size - 1; i >= 0; i--) {
                String element = objects.get(i).getName();
                azIndexer.put(element.substring(0, 1), i);
            }

            Set<String> keys = azIndexer.keySet();

            Iterator<String> it = keys.iterator();
            ArrayList<String> keyList = new ArrayList<String>();

            while (it.hasNext()) {
                String key = it.next();
                keyList.add(key);
            }
            Collections.sort(keyList);
            sections = new String[keyList.size()];
            keyList.toArray(sections);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater
                        .from(parent.getContext())
                        .inflate(android.R.layout.simple_list_item_1, parent, false);
            }
            CampusMember item = getItem(position);
            if (item != null) {
                ((TextView) convertView).setText(item.getName());
            }
            return convertView;
        }

        public int getPositionForSection(int section) {
            String letter = sections[section];
            return azIndexer.get(letter);
        }

        public int getSectionForPosition(int position) {
            return 0;
        }

        public Object[] getSections() {
            return sections; // to string will be called to display the letter
        }
    }
}
