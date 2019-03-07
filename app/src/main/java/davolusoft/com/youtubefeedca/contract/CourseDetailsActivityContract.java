package davolusoft.com.youtubefeedca.contract;

import android.content.Context;

/**
 * Created by David Oluyale on 3/6/2019.
 */

public interface CourseDetailsActivityContract {

    interface CourseDetailsView {

        void initCourseDetailsView();

        void setCourseDetailsViewData(String data);
    }

    interface Model {

    }

    interface CourseDetailsPresenter {

        void loadCourseDetails(Context mcontext, String CourseID);


    }
}
