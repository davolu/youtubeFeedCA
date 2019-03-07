package davolusoft.com.youtubefeedca.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import davolusoft.com.youtubefeedca.constants.ApiConstants;

/**
 * Created by David Oluyale on 3/6/2019.
 * all volley request from here.
 */

public class VolleyRequests {

    String tag="respone";

    //this fetches the home feeds
    public void fetchHomeFeeds(final DataCallback callback) {
        String url = new  ApiConstants().getBaseURL()+"/home_feed";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(tag, response.toString());

                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(tag, "Error: " + error.getMessage());
                    }
                });

        NetworkController.getInstance().addToRequestQueue(jsonObjectRequest);
    }



    //this fetches the course details based on the supplied ID
    public void fetchCourseDetails(final StrReqCallback callback, final String CourseID, Context mtctx) {

        RequestQueue queue = Volley.newRequestQueue(mtctx);
        String url =new ApiConstants().getBaseURL()+"/course_detail?id="+CourseID;

        StringRequest strRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {

                            callback.onSuccess(response.toString());

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                     }
                })
        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String> params = new HashMap<String, String>();
                params.put("id", CourseID);
                return params;
            }
        };

        queue.add(strRequest);


    }
}
