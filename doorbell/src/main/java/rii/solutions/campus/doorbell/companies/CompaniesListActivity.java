package rii.solutions.campus.doorbell.companies;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Fullscreen;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rii.solutions.campus.data.CampusMember;
import rii.solutions.campus.data.ResponseWrapperModel;
import rii.solutions.campus.data.source.CampusDataSource;
import rii.solutions.campus.doorbell.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
@Fullscreen
@EActivity(R.layout.activity_companies_list)
public class CompaniesListActivity extends AppCompatActivity {

    // TODO: 04.06.2017 move to dagger or smth like that
    private CampusDataSource mSource;

    @ViewById(android.R.id.list)
    RecyclerView mContentView;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();

    private final Runnable mHideRunnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    private CampusDataSource getDataSource() {
        if (mSource == null) {
            // TODO: 04.06.2017 move credentials to storage
            mSource = new CampusDataSource("barapp", "k238sdfaaslsad");
        }

        return mSource;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        hide();
        retrieveMembers();
    }

    @Background
    void retrieveMembers() {
        getDataSource().members().enqueue(new Callback<ResponseWrapperModel<List<CampusMember>>>() {
            @Override
            public void onResponse(@NonNull Call<ResponseWrapperModel<List<CampusMember>>> call,
                                   @NonNull Response<ResponseWrapperModel<List<CampusMember>>> response) {
                ResponseWrapperModel<List<CampusMember>> body = response.body();
                if (body != null) {
                    populateMembers(body.results);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseWrapperModel<List<CampusMember>>> call,
                                  @NonNull Throwable t) {
                // nop
                Log.e("Network", t.getLocalizedMessage());
            }
        });
    }

    @UiThread
    void populateMembers(List<CampusMember> response) {
        mContentView.setAdapter(new CompaniesListAdapter(response));
    }

    private void hide() {
        mHideHandler.postDelayed(mHideRunnable, UI_ANIMATION_DELAY);
    }
}