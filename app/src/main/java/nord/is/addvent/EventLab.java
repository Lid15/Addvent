package nord.is.addvent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import nord.is.addvent.database.EventBaseHelper;
import nord.is.addvent.database.EventCursorWrapper;
import nord.is.addvent.database.EventDbSchema.EventTable;

/**
 * Created by Ólafur Georg Gylfason (ogg4@hi.is) on 21.3.2018.
 *
 * The layer used to communicate queries to the database layer.
 */

public class EventLab {
    private static EventLab sEventLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static EventLab get(Context context) {
        if (sEventLab == null) {
            sEventLab = new EventLab(context);
        }
        return sEventLab;
    }

    private EventLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new EventBaseHelper(mContext)
                .getWritableDatabase();
    }

    public void addEventId(String id) {
        ContentValues values = getContentValues(id);

        mDatabase.insert(EventTable.NAME, null, values);
    }

    public void deleteEventId(String id) {
        ContentValues values = getContentValues(id);

        mDatabase.delete(
                EventTable.NAME,
                EventTable.Cols.ID + " = ?",
                new String[] { id }
        );
    }

    public List<String> getEventIds() {
        List<String> eventIds = new ArrayList<>();

        EventCursorWrapper cursor = queryEvent(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                eventIds.add(cursor.getEventId());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return eventIds;
    }

    public boolean isStarred(String id) {
        EventCursorWrapper cursor = queryEvent(
                EventTable.Cols.ID + " = ?",
                new String[] { id }
        );

        try {
            if (cursor.getCount() == 0) {
                return false;
            }

            return true;
        } finally {
            cursor.close();
        }
    }


    private EventCursorWrapper queryEvent(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                EventTable.NAME,
                null, // columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null // orderBy
        );

        return new EventCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(String id) {
        ContentValues values = new ContentValues();
        values.put(EventTable.Cols.ID, id);

        return values;
    }
}
