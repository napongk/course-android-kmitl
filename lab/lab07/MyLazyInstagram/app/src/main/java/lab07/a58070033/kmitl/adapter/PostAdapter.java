package lab07.a58070033.kmitl.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import lab07.a58070033.kmitl.model.PostModel;
import lab07.a58070033.kmitl.R;

/**
 * Created by student on 10/6/2017 AD.
 */

class Holder extends RecyclerView.ViewHolder {
    public ImageView image;
    public TextView like, comment;

    public Holder(View itemView) {
        super(itemView);
        image = (ImageView) itemView.findViewById(R.id.imageViewer);
        like = (TextView) itemView.findViewById(R.id.likeText);
        comment = (TextView) itemView.findViewById(R.id.commentText);
    }
}

public class PostAdapter extends RecyclerView.Adapter<Holder> {

    private Activity activity;
    private List<PostModel> data;
    private String choose;

    public PostAdapter(Activity activity, String choose) {
        this.activity = activity;
        this.choose = choose;
        data = new ArrayList<>();
    }


    public void setData(List<PostModel> data) {
        this.data = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if(choose.equals("grid")) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
    }
        else if (choose.equals("list")) {
            itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2, null);
        }
        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String imageUrl = data.get(position).getUrl();
        String likeText = data.get(position).getLike();
        String commentText = data.get(position).getComment();
        ImageView image = holder.image;

        TextView likenum = holder.like;
        likenum.setText(likeText+"");

        TextView commentnum = holder.comment;
        commentnum.setText(commentText+"");

        Glide.with(activity).load(imageUrl).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}