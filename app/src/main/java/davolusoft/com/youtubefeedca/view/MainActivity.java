package davolusoft.com.youtubefeedca.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import  davolusoft.com.youtubefeedca.R;
import davolusoft.com.youtubefeedca.adapters.VideoAdapter;
import  davolusoft.com.youtubefeedca.contract.MainActivityContract.Presenter;
import  davolusoft.com.youtubefeedca.contract.MainActivityContract.View;
 import davolusoft.com.youtubefeedca.model.Videos;
import  davolusoft.com.youtubefeedca.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements View {

    private RecyclerView recyclerView;
    private Presenter mPresenter;


    VideoAdapter adapter;
    //a list to store all the videos
    List<Videos> videoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);



        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mPresenter = new MainActivityPresenter(this);

        mPresenter.loadHomeFeed(MainActivity.this);

   }

    @Override
    public void initView()
    {
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.homeFeedRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the productlist
        videoList = new ArrayList<>();


        //recyclerview listener




    }

    @Override
    public void setViewData(String data) {

//Display the data on the recycler view/


        try {

            JSONObject jsnobject = new JSONObject(data);
            JSONArray jsonArray = jsnobject.getJSONArray("videos");

            for(int i = 0; i<jsonArray.length(); i++)
            {
                JSONObject explrObject = jsonArray.getJSONObject(i);

                //populate the list
                videoList.add(
                        new Videos(
                                Integer.parseInt(explrObject.getString("id")),
                                explrObject.getString("name"),
                                explrObject.getString("imageUrl"),
                                "Number of Views: "+explrObject.getString("numberOfViews"),
                                explrObject.getJSONObject("channel").getString("profileImageUrl")

                                ));

            }

            adapter = new VideoAdapter(this, videoList);
            recyclerView.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}