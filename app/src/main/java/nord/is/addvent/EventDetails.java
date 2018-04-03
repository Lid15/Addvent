package nord.is.addvent;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;


/**
 * Created by Ásgerður Inna Antonsdóttir and Hrafnfhildur Olga Hjaltadóttir on 3.4.2018.
 */

public class EventDetails extends AppCompatActivity {
    private Event mEvent;
    private TextView mTitleTextView;
    private TextView mDateTextView;
    private TextView mDescriptionTextView;
    private TextView mHostTextView;
    private ImageView mIconImageView;
    private String mId = mEvent.getId();

    //private CheckBox mStarCheckBox;
    private static final String EXTRA_DETAIL_ID = "nord.is.addvent.detail_id";


    public static Intent newIntent(Context packageContext, int mId)

    {
        Intent intent = new Intent(packageContext, EventDetails.class);
        intent.putExtra(EXTRA_DETAIL_ID,mId);
        return intent;
    }


    protected Fragment createFragment() {
        UUID mId = (UUID) getIntent().getSerializableExtra(EXTRA_DETAIL_ID);
        return DetailFragment.newInstance(mId);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_event_details);
        mEvent = new Event();
    }
}

