package davolusoft.com.youtubefeedca.contract;

import android.content.Context;

/**
 * Created by David Oluyale on 3/6/2019.
 */



public interface MainActivityContract {

    interface View {

        void initView();

        void setViewData(String data);
    }

    interface Model {

      //  String getHomeFeed(Context mcontext);
     }

    interface Presenter {

        void loadHomeFeed(Context mcontext);
    }
}