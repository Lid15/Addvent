package nord.is.addvent.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import nord.is.addvent.Event;
import nord.is.addvent.database.EventDbSchema.EventTable;

/**
 * Created by olafur on 22.3.2018.
 */

public class EventCursorWrapper extends CursorWrapper {
    public EventCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public String getEventId() {
        String id = getString(getColumnIndex(EventTable.Cols.ID));

        return id;
    }
}
