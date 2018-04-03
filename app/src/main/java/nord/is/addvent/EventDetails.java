package nord.is.addvent;


import android.os.AsyncTask;
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
import android.widget.Toast;




/**
 * Created by Ásgerður Inna Antonsdóttir and Hrafnfhildur Olga Hjaltadóttir on 3.4.2018.
 */

public class EventDetails extends Fragment{
    private Event mEvent;
    private TextView mTitleTextView;
    private TextView mDateTextView;
    private TextView mDescriptionTextView;
    private TextView mHostTextView;
    private ImageView mIconImageView;
    //private CheckBox mStarCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_event_details);
        mEvent  = new Event();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
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

    }


}
