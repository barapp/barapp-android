package com.develmagic.quellio;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SectionIndexer;

import com.develmagic.quellio.basket.Basket;
import com.develmagic.quellio.service.BackendQuery;
import com.develmagic.quellio.service.BackendService;
import com.develmagic.quellio.service.ServiceGenerator;
import com.develmagic.quellio.service.dto.MemberDTO;
import com.develmagic.quellio.service.dto.MemberDTOList;
import com.develmagic.quellio.service.dto.OrderResultDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mejmo on 15.5.2017.
 */

public class SelectMemberActivity extends AppCompatActivity {

    ListView myListView;
    private SelectMemberActivity instance;
    private List<MemberDTO> items;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instance = this;

        setContentView(R.layout.activity_select_member);

        BackendService service = ServiceGenerator.createService(BackendService.class, Constants.API_USERNAME, Constants.API_PASS);
        Call<MemberDTOList> call = service.listMembers();
        call.enqueue(new Callback<MemberDTOList>() {
            @Override
            public void onResponse(Call<MemberDTOList> call, Response<MemberDTOList> response) {
                instance.setItems(response.body().getProducts());
                initializeComponents();
            }

            @Override
            public void onFailure(Call<MemberDTOList> call, Throwable t) {
                Log.e("barapp", "Cannot get members list", t);
            }
        });

    }

    public void initializeComponents() {
        myListView = (ListView) findViewById(R.id.myListView);
        myListView.setBackgroundColor(getColor(R.color.colorPrimary));
        myListView.setFastScrollEnabled(true);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, final int i, long l) {
                final MemberDTO dto = (MemberDTO) myListView.getItemAtPosition(i);

                AlertDialog.Builder alert = new AlertDialog.Builder(instance);
                alert.setTitle("Confirm order");
                alert.setMessage("Order will be billed to " + dto.toString());
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        BackendService service = ServiceGenerator.createService(BackendService.class, Constants.API_USERNAME, Constants.API_PASS);
                        Call<OrderResultDTO> call = service.order(dto.getId(), Basket.getInstance());
                        call.enqueue(new Callback<OrderResultDTO>() {
                            @Override
                            public void onResponse(Call<OrderResultDTO> call, Response<OrderResultDTO> response) {
                                OrderResultDTO result = response.body();
//                                if (result.getResultCode() == OrderResultDTO.ERROR) {
//                                    Snackbar snackbar = Snackbar.make(view, "Cannot make order: " + result.getError(), Snackbar.LENGTH_LONG);
//                                    snackbar.show();
//                                    return;
//                                } else {
//                                    dialog.dismiss();
//                                    instance.setResult(OrderResultDTO.ERROR);
//                                    Intent returnIntent = new Intent();
//                                    setResult(OrderResultDTO.OK, returnIntent);
//                                    finish();
//                                }
                            }

                            @Override
                            public void onFailure(Call<OrderResultDTO> call, Throwable t) {
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

    public List<MemberDTO> getItems() {
        return items;
    }

    public void setItems(List<MemberDTO> items) {
        this.items = items;
    }

    class MemberListAdapter extends ArrayAdapter<MemberDTO> implements SectionIndexer {
        ArrayList<MemberDTO> myElements;
        HashMap<String, Integer> azIndexer;
        String[] sections;

        public MemberListAdapter(Context context, int textViewResourceId, @NonNull List<MemberDTO> objects) {
            super(context, textViewResourceId, objects);
            myElements = (ArrayList<MemberDTO>) objects;
            azIndexer = new HashMap<>();

            int size = items.size();
            for (int i = size - 1; i >= 0; i--) {
                String element = items.get(i).getName();
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
            return super.getView(position, convertView, parent);
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
