package nord.is.addvent;

import java.util.Date;

/**
 * Created by Ólafur Georg Gylfason (ogg4@hi.is) on 13.3.2018.
 *
 * The model class for events.
 */
public class Event {

    public String mId;
    public String mTitle;
    public String mLocation;
    public String mHost;
    public String mDescription;
    public Date mDate;
    public Boolean mIsNordEvent;

    public Event() {

    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getHost() {
        return mHost;
    }

    public void setHost(String host) {
        mHost = host;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public Boolean getNordEvent() {
        return mIsNordEvent;
    }

    public void setNordEvent(Boolean nordEvent) {
        mIsNordEvent = nordEvent;
    }
}
