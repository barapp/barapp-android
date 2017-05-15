package com.develmagic.quellio;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.TextView;

import com.develmagic.quellio.basket.Basket;
import com.develmagic.quellio.service.BackendQuery;
import com.develmagic.quellio.service.dto.MemberDTO;
import com.develmagic.quellio.service.dto.OrderResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by mejmo on 15.5.2017.
 */

public class SelectMemberActivity extends AppCompatActivity {

    ListView myListView;
    List<MemberDTO> elements;
    private SelectMemberActivity instance;
    private int resultCode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        instance = this;

        setContentView(R.layout.activity_select_member);

        // elements
        elements = BackendQuery.getMembers();

        // listview
        myListView = (ListView) findViewById(R.id.myListView);
        myListView.setBackgroundColor(getColor(R.color.colorPrimary));
        myListView.setFastScrollEnabled(true);
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, final int i, long l) {
                final MemberDTO dto = (MemberDTO) myListView.getItemAtPosition(i);

                AlertDialog.Builder alert = new AlertDialog.Builder(instance);
                alert.setTitle("Delete");
                alert.setMessage("Order will be billed to "+dto.toString());
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        OrderResult result = BackendQuery.order(dto.getId(), Basket.getInstance());
                        if (result.getResultCode() == OrderResult.ERROR) {
                            Snackbar snackbar = Snackbar.make(view, "Cannot make order: "+result.getError(), Snackbar.LENGTH_LONG);
                            snackbar.show();
                            return;
                        } else {
                            dialog.dismiss();
                            instance.setResult(OrderResult.ERROR);
                            Intent returnIntent = new Intent();
                            setResult(OrderResult.OK, returnIntent);
                            finish();
                        }
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
        MyAZAdapter adapter = new MyAZAdapter(
                getApplicationContext(), android.R.layout.simple_list_item_1,
                elements);
        myListView.setAdapter(adapter);

    }

    class MyAZAdapter extends ArrayAdapter<MemberDTO> implements SectionIndexer {
        ArrayList<MemberDTO> myElements;
        HashMap<String, Integer> azIndexer;
        String[] sections;

        public MyAZAdapter(Context context, int textViewResourceId, @NonNull  List<MemberDTO> objects) {
            super(context, textViewResourceId, objects);
            myElements = (ArrayList<MemberDTO>) objects;
            azIndexer = new HashMap<>(); //stores the positions for the start of each letter

            int size = elements.size();
            for (int i = size - 1; i >= 0; i--) {
                String element = elements.get(i).getName();
                //We store the first letter of the word, and its index.
                azIndexer.put(element.substring(0, 1), i);
            }

            Set<String> keys = azIndexer.keySet(); // set of letters

            Iterator<String> it = keys.iterator();
            ArrayList<String> keyList = new ArrayList<String>();

            while (it.hasNext()) {
                String key = it.next();
                keyList.add(key);
            }
            Collections.sort(keyList);//sort the keylist
            sections = new String[keyList.size()]; // simple conversion to array
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
