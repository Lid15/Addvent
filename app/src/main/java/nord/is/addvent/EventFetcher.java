package nord.is.addvent;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ólafur Georg Gylfason (ogg4@hi.is) on 13.3.2018.
 *
 * This class fetches a JSON string from our web server,
 * parses it and populates an array with all the event objects
 */

public class EventFetcher {

    private static final String TAG = "EventFetcher";
    private static final String GET_ALL_EVENTS = "https://addvent-ws.herokuapp.com/event/all";

    public byte[] getUrlBytes(String urlSpec) throws IOException {
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpec);
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    public String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    public List<Event> fetchItems() {

        List<Event> items = new ArrayList<>();

        try {
            String url = Uri.parse(GET_ALL_EVENTS)
                    .toString();
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
            JSONArray eventJsonArray = new JSONArray(jsonString);
            parseItems(items, eventJsonArray);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch items", ioe);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);
        }

        return items;
    }

    private void parseItems(List<Event> items, JSONArray eventJsonArray)
            throws IOException, JSONException {
        for (int i = 0; i < eventJsonArray.length(); i++) {
            JSONObject eventJsonObject = eventJsonArray.getJSONObject(i);

            Event event = new Event();
            event.setId(eventJsonObject.getString("id"));
            event.setTitle(eventJsonObject.getString("title"));
            event.setLocation(eventJsonObject.getString("location"));
            event.setHost(eventJsonObject.getString("host"));
            event.setDescription(eventJsonObject.getString("description"));

            String isEventByNord = eventJsonObject.getString("bynord");
            event.setNordEvent(Boolean.parseBoolean(isEventByNord));

            String d = eventJsonObject.getString("time");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = format.parse(d);
                event.setDate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            items.add(event);
        }
    }
}