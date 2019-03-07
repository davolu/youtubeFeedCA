package davolusoft.com.youtubefeedca.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.toolbox.ImageLoader;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

import davolusoft.com.youtubefeedca.R;
import davolusoft.com.youtubefeedca.model.Videos;
import davolusoft.com.youtubefeedca.view.CourseDetailsActivity;

/**
 * Created by David Oluyale on 3/6/2019.
 */



public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

     //this context we will use to inflate the layout
    private Context mCtx;

    ImageLoader imageLoader;

    //we are storing all the videos gotten from the API in a list
    private List<Videos> videoList;

    //getting the context and videos list with constructor
    public VideoAdapter(Context mCtx, List<Videos> videoList)  {
        this.mCtx = mCtx;
        this.videoList = videoList;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.home_feeds_rv, null);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        //getting the videos of the specified position
        Videos videos = videoList.get(position);

        //binding the data with the viewholder views
        holder.textViewFeedName.setText(videos.getName());
        holder.textViewFeedNumberOfViews.setText(videos.getNumberOfViews());
        holder.textViewFeedID.setText(String.valueOf(videos.getId()));

        //Image Manager
        imageLoader = ImageAdapter.getInstance(mCtx).getImageLoader();

        //load  video poster image from url
        imageLoader.get(videos.getImageURL(),
                ImageLoader.getImageListener(
                        holder.imageViewFeedImageURL,//Server Image
                        R.drawable.import_placeholder,//Before loading server image the default showing image.
                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
                )
        );


        //load profile Image from url
        imageLoader.get(videos.getProfileImageURL(),
                ImageLoader.getImageListener(
                        holder.feedProfileImageURL,//Server Image
                        R.drawable.import_placeholder,//Before loading server image the default showing image.
                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
                )
        );


    }



    @Override
    public int getItemCount() {
        return videoList.size();
    }


    class VideoViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        TextView textViewFeedName, textViewFeedNumberOfViews, textViewFeedID;
        ImageView imageViewFeedImageURL, feedProfileImageURL;


        public VideoViewHolder(View itemView) {
            super(itemView);

            textViewFeedName = itemView.findViewById(R.id.feedName);
            textViewFeedNumberOfViews = itemView.findViewById(R.id.feednumberOfViews);
            textViewFeedID = itemView.findViewById(R.id.feedid);
            imageViewFeedImageURL = itemView.findViewById(R.id.feedimageURL);
            feedProfileImageURL = itemView.findViewById(R.id.feedprofileImageURL);

            //event onclick listener
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            TextView clickedID=(TextView)v.findViewById(R.id.feedid);
            TextView clickedName=(TextView)v.findViewById(R.id.feedName);

            //open the course details activity

            Intent courseDetailsIntent = new Intent(v.getContext(), CourseDetailsActivity.class);
                courseDetailsIntent.putExtra("id", clickedID.getText().toString()); //pass the id parameter to the activity
            courseDetailsIntent.putExtra("name", clickedName.getText().toString()); //pass the name parameter to the activity

            mCtx.startActivity(courseDetailsIntent);
          //  Toast.makeText(v.getContext(),clickedName.getText().toString(),Toast.LENGTH_LONG).show();

         }


    }
}
