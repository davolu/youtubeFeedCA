package davolusoft.com.youtubefeedca.presenter;

import android.content.Context;

import org.json.JSONObject;

import davolusoft.com.youtubefeedca.contract.MainActivityContract;
import davolusoft.com.youtubefeedca.contract.MainActivityContract.Model;
import davolusoft.com.youtubefeedca.contract.MainActivityContract.View;
import davolusoft.com.youtubefeedca.network.DataCallback;
import davolusoft.com.youtubefeedca.network.VolleyRequests;
import davolusoft.com.youtubefeedca.model.MainActivityModel;

/**
 * Created by David Oluyale on 3/6/2019.
 */

public class MainActivityPresenter implements MainActivityContract.Presenter {

    private View mView;
    private Model mModel;

    public MainActivityPresenter(View view) {
        mView = view;
        initPresenter();
    }

    private void initPresenter() {
        mModel = new MainActivityModel();
        mView.initView();
    }

    @Override
    public void loadHomeFeed(Context mcontext) {

        //load the home feeds.
        new VolleyRequests().fetchHomeFeeds(new DataCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                mView.setViewData(result.toString());

            }
        });




    }
}