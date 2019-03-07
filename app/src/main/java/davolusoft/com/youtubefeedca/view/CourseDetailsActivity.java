package davolusoft.com.youtubefeedca.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import davolusoft.com.youtubefeedca.R;
import davolusoft.com.youtubefeedca.adapters.CourseDetailsAdapter;
 import davolusoft.com.youtubefeedca.contract.CourseDetailsActivityContract.CourseDetailsView;
import davolusoft.com.youtubefeedca.contract.CourseDetailsActivityContract.CourseDetailsPresenter;
import davolusoft.com.youtubefeedca.model.CourseDetails;
 import davolusoft.com.youtubefeedca.presenter.CourseDetailsActivityPresenter;

public class CourseDetailsActivity extends AppCompatActivity implements CourseDetailsView {


    private RecyclerView recyclerView;

    private CourseDetailsPresenter cdPresenter;

    ProgressDialog progressDialog;
    CourseDetailsAdapter adapter;
    //a list to store all the videos
    List<CourseDetails> courseDetailsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        //show dialog while loading courses
        progressDialog = ProgressDialog.show(this, "Courses",
                "Fetching Courses ...", true);

        setTitle("Courses");

        //get the ID from the previous activity via the Intent

        Intent getPost = getIntent();

        String courseID = getPost.getStringExtra("id");
        String courseName = getPost.getStringExtra("name");

        setTitle(courseName);
        cdPresenter = new CourseDetailsActivityPresenter(this);


        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        cdPresenter.loadCourseDetails(CourseDetailsActivity.this, courseID);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initCourseDetailsView() {


        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.courseDetailsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the courseDetails
        courseDetailsList = new ArrayList<>();


        //recyclerview listener


    }

    @Override
    public void setCourseDetailsViewData(String data) {


        try {

            JSONArray jArray = new JSONArray(data);

            for(int i = 0; i<jArray.length(); i++)
            {

                JSONObject json_data = jArray.getJSONObject(i);

                //populate the list
                courseDetailsList.add(
                        new CourseDetails(
                                json_data.getString("number"),
                                json_data.getString("name"),
                                json_data.getString("duration"),
                                json_data.getString("imageUrl"),
                                json_data.getString("link")

                        ));

            }

            adapter = new CourseDetailsAdapter(this, courseDetailsList);
            recyclerView.setAdapter(adapter);
            //dismiss the dialog
            progressDialog.dismiss();
        } catch (JSONException e) {
            e.printStackTrace();
        }





    }
}