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

import java.util.List;

import davolusoft.com.youtubefeedca.R;
import davolusoft.com.youtubefeedca.model.CourseDetails;
import davolusoft.com.youtubefeedca.model.Videos;
import davolusoft.com.youtubefeedca.view.CourseDetailsActivity;
import davolusoft.com.youtubefeedca.view.LinkViewActivity;

/**
 * Created by David Oluyale on 3/6/2019.
 */



public class CourseDetailsAdapter extends RecyclerView.Adapter<CourseDetailsAdapter.VideoViewHolder> {

    //this context we will use to inflate the layout
    private Context mCtx;

    ImageLoader imageLoader;

    //we are storing all the videos gotten from the API in a list
    private List<CourseDetails> courseDetailsList;

    //getting the context and videos list with constructor
    public CourseDetailsAdapter(Context mCtx, List<CourseDetails> courseDetailsList)  {
        this.mCtx = mCtx;
        this.courseDetailsList = courseDetailsList;
    }

    @Override
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.course_details_rv, null);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        //getting the videos of the specified position
        CourseDetails courseDetails = courseDetailsList.get(position);

        //binding the data with the viewholder views
        holder.courseDetailsName.setText(courseDetails.getName());
        holder.courseDetailsDuration.setText(courseDetails.getDuration());
        holder.courseDetailsLink.setText(String.valueOf(courseDetails.getLink()));

        //Image Manager
        imageLoader = ImageAdapter.getInstance(mCtx).getImageLoader();

        //load profile Image from url
        imageLoader.get(courseDetails.getImageUrl(),
                ImageLoader.getImageListener(
                        holder.courseDetailsImageURL,//Server Image
                        R.drawable.import_placeholder,//Before loading server image the default showing image.
                        android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
                )
        );


    }



    @Override
    public int getItemCount() {
        return courseDetailsList.size();
    }


    class VideoViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        TextView courseDetailsName, courseDetailsDuration,courseDetailsLink;
        ImageView courseDetailsImageURL;

        public VideoViewHolder(View itemView) {
            super(itemView);

            courseDetailsName = itemView.findViewById(R.id.courseDetailsName);
            courseDetailsDuration = itemView.findViewById(R.id.courseDuration);
            courseDetailsLink = itemView.findViewById(R.id.courseLink);
            courseDetailsImageURL = itemView.findViewById(R.id.courseDetailsImage);

            //event onclick listener
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            TextView clickedName=(TextView)v.findViewById(R.id.courseLink);

            //open the course details activity

            Intent courseDetailsIntent = new Intent(v.getContext(), LinkViewActivity.class);
            courseDetailsIntent.putExtra("link", clickedName.getText().toString()); //pass the id parameter to the activity
            mCtx.startActivity(courseDetailsIntent);

              //Toast.makeText(v.getContext(),clickedName.getText().toString(),Toast.LENGTH_LONG).show();

        }


    }
}
