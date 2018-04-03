package nord.is.addvent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Ásgerður Inna Antonsdóttir and Hrafnfhildur Olga Hjaltadóttir on 3.4.2018.
 */

public class DetailFragment extends Fragment{

    private Event mEvent;
    private TextView mTitleTextView;
    private TextView mDateTextView;
    private TextView mDescriptionTextView;
    private TextView mHostTextView;
    private ImageView mIconImageView;

    private static final String TAG = "detail_id";

    private RecyclerView mEventRecyclerView;
    private List<Event> mEvents = new ArrayList<>();

    public static DetailFragment newInstance(UUID mId){
        Bundle args = new Bundle();
        args.putSerializable(TAG, mId);

        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //new DetailFragment.execute();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_details, container, false);



        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    /*public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event_details, container, false);
        mTitleTextView = (TextView) v.findViewById(R.id.event_title);
        mTitleTextView.setText(mEvent.mTitle);
        //mDateTextView = (TextView) v.findViewById(R.id.event_date);
        //mDateTextView.setText(mEvent.mDate);
        mDescriptionTextView = (TextView) v.findViewById(R.id.event_description);
        mDescriptionTextView.setText(mEvent.mDescription);
        mHostTextView = (TextView) v.findViewById(R.id.event_host);
        mHostTextView.setText(mEvent.mHost);
        mIconImageView = (ImageView) v.findViewById(R.id.event_icon);


        return v;

    }*/



}