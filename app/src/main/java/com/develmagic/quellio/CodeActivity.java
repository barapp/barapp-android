package com.develmagic.quellio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by mejmo on 15.5.2017.
 */

public class CodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_code);

        Button btn = (Button) findViewById(R.id.enter_code_button);
        final EditText editText = (EditText) findViewById(R.id.editText);

        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.main_content_enter_code);

        final CodeActivity instance = this;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                if (editText.getText().toString().equals("0200")) {
                    Intent intent = new Intent(instance, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Snackbar snackbar = Snackbar.make(linearLayout, "Invalid code", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
            }
        });
    }

}
