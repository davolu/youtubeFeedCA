package davolusoft.com.youtubefeedca.presenter;

import android.content.Context;

import org.json.JSONObject;

import davolusoft.com.youtubefeedca.contract.CourseDetailsActivityContract;

import davolusoft.com.youtubefeedca.contract.CourseDetailsActivityContract.Model;
import davolusoft.com.youtubefeedca.contract.CourseDetailsActivityContract.CourseDetailsView;
import davolusoft.com.youtubefeedca.model.CourseDetailsActivityModel;
import davolusoft.com.youtubefeedca.network.DataCallback;
import davolusoft.com.youtubefeedca.network.StrReqCallback;
import davolusoft.com.youtubefeedca.network.VolleyRequests;

/**
 * Created by David Oluyale on 3/6/2019.
 */

public class CourseDetailsActivityPresenter implements  CourseDetailsActivityContract.CourseDetailsPresenter {


    private CourseDetailsView mView;
    private Model mModel;

    public CourseDetailsActivityPresenter(CourseDetailsView view) {
        mView = view;
        initPresenter();
    }

    private void initPresenter() {
        mModel = new CourseDetailsActivityModel();
        mView.initCourseDetailsView();
    }

    @Override
    public void loadCourseDetails(Context mcontext, String CourseID) {

        //load the course details feeds.
        new VolleyRequests().fetchCourseDetails(new StrReqCallback() {
            @Override
            public void onSuccess(String result) {
                mView.setCourseDetailsViewData(result.toString());

            }


        }, CourseID, mcontext);




    }
}
