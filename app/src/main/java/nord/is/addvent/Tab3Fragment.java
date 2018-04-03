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
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ólafur Georg Gylfason (ogg4@hi.is) on 13.3.2018.
 *
 * This fragment displays the recyclerview that contains
 * all of the events that are marked with filled star
 */

public class Tab3Fragment extends Fragment {
    private static final String TAG = "Tab3Fragment";

    private RecyclerView mEventRecyclerView;
    private List<Event> mEvents = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new FetchItemsTask().execute();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_list, container, false);

        mEventRecyclerView = (RecyclerView) view.findViewById(R.id.event_recycler_view);
        mEventRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        setupAdapter();


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        setupAdapter();
    }

    private class EventHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Event mEvent;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private ImageView mIconImageView;
        private CheckBox mStarCheckBox;

        EventLab mEventLab = EventLab.get(getActivity());

        public EventHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_event, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = (TextView) itemView.findViewById(R.id.event_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.event_date);
            mIconImageView = (ImageView) itemView.findViewById(R.id.event_icon);
            mStarCheckBox = (CheckBox) itemView.findViewById(R.id.event_starCheckBox);

            mStarCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        mEventLab.addEventId(mEvent.getId());
                    } else {
                        mEventLab.deleteEventId(mEvent.getId());
                    }
                }
            });
        }

        public void bind(Event event) {
            mEvent = event;
            mTitleTextView.setText(mEvent.getTitle());
            mDateTextView.setText(mEvent.getDate().toString());
            mIconImageView.setVisibility(event.getNordEvent() ? View.VISIBLE : View.GONE);
            mStarCheckBox.setChecked(mEventLab.isStarred(event.getId()));
        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(getActivity(),
             //       mEvent.getTitle() + " clicked!", Toast.LENGTH_SHORT)
             //       .show();
            Intent intent = new Intent(getActivity(), mEvent.getId());
            startActivity(intent);
        }
    }

    private class EventAdapter extends RecyclerView.Adapter<EventHolder> {

        private List<Event> mEventItems = new ArrayList<>();
        EventLab mEventLab = EventLab.get(getActivity());

        public EventAdapter(List<Event> events) {
            for (Event event : events) {
                //String id = event.getId();
                if (mEventLab.isStarred(event.getId())) {
                    mEventItems.add(event);
                }
            }
        }

        @Override
        public EventHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new EventHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(EventHolder holder, int position) {
            Event event = mEventItems.get(position);
            holder.bind(event);
        }

        @Override
        public int getItemCount() {
            return mEventItems.size();
        }
    }

    private class FetchItemsTask extends AsyncTask<Void, Void, List<Event>> {
        @Override
        protected List<Event> doInBackground(Void... params) {
            return new EventFetcher().fetchItems();
        }

        @Override
        protected void onPostExecute(List<Event> items) {
            mEvents = items;
            setupAdapter();
        }
    }

    private void setupAdapter() {
        if (isAdded()) {
            mEventRecyclerView.setAdapter(new EventAdapter(mEvents));
        }
    }
}
