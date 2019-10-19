package android.example.recylcerviewlearning.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.example.recylcerviewlearning.R;
import android.example.recylcerviewlearning.models.Movie;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movieList;
    private Context context;

    public MovieAdapter(List<Movie> movies, Context context){
        this.movieList = movies;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView posterView;
        TextView titleView;
        TextView descriptionView;

        public ViewHolder(View itemView){
            super(itemView);

            posterView = itemView.findViewById(R.id.imageView2);
            titleView = itemView.findViewById(R.id.textView);
            descriptionView = itemView.findViewById(R.id.textView2);
        }

        public void bind(Movie movie) {
            titleView.setText(movie.getTitle());
            descriptionView.setText(movie.getDescription());

            String imageUrl;
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                imageUrl = movie.getBackdropPath();
            }else{
                imageUrl = movie.getPosterUrl();
            }
            Glide.with(context)
                .load(imageUrl)
                .into(posterView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        //View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);

        View movieView = inflater.inflate(R.layout.movie_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(movieView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

}
