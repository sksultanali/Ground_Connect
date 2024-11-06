package com.developerali.groundconnect.Adapterts;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.developerali.groundconnect.Models.PostModel;
import com.developerali.groundconnect.R;
import com.developerali.groundconnect.databinding.ChildWorksBinding;
import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{

    Activity activity;
    ArrayList<PostModel> arrayList;
    int size;
    boolean captionLoad = false;

    public PostAdapter(Activity activity, ArrayList<PostModel> arrayList, int size) {
        this.activity = activity;
        this.arrayList = arrayList;
        this.size = size;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.child_works, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PostModel postModel = arrayList.get(position);

        if (postModel != null){
            if (postModel.getCaption() != null && !postModel.getCaption().isEmpty()){
                if (postModel.getCaption().length() > 255){
                    String captionText = postModel.getCaption().substring(0, 255) + "....Read more";
                    holder.binding.caption.setText(captionText);

                    holder.binding.caption.setOnClickListener(v->{
                        if (captionLoad){
                            holder.binding.caption.setText(captionText);
                            captionLoad = false;
                        }else {
                            holder.binding.caption.setText(postModel.getCaption());
                            captionLoad = true;
                        }
                    });
                }else {
                    holder.binding.caption.setText(postModel.getCaption());
                }
                holder.binding.caption.setVisibility(View.VISIBLE);
            }else {
                holder.binding.caption.setVisibility(View.GONE);
            }

            if (postModel.getImgUrls() != null && !postModel.getImgUrls().isEmpty()){
                if (postModel.getImgUrls().size() == 1){
                    Glide.with(activity)
                            .load(postModel.getImgUrls().get(0))
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                            .placeholder(com.denzcoskun.imageslider.R.drawable.default_placeholder)
                            .into(holder.binding.img1);
                    holder.binding.img1.setVisibility(View.VISIBLE);
                    holder.binding.img2.setVisibility(View.GONE);
                    holder.binding.img3.setVisibility(View.GONE);
                } else if (postModel.getImgUrls().size() == 1) {
                    Glide.with(activity)
                            .load(postModel.getImgUrls().get(0))
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                            .placeholder(com.denzcoskun.imageslider.R.drawable.default_placeholder)
                            .into(holder.binding.img1);
                    Glide.with(activity)
                            .load(postModel.getImgUrls().get(1))
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                            .placeholder(com.denzcoskun.imageslider.R.drawable.default_placeholder)
                            .into(holder.binding.img2);
                    holder.binding.img1.setVisibility(View.VISIBLE);
                    holder.binding.img2.setVisibility(View.VISIBLE);
                    holder.binding.img3.setVisibility(View.GONE);
                }else {
                    Glide.with(activity)
                            .load(postModel.getImgUrls().get(0))
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                            .placeholder(com.denzcoskun.imageslider.R.drawable.default_placeholder)
                            .into(holder.binding.img1);
                    Glide.with(activity)
                            .load(postModel.getImgUrls().get(1))
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                            .placeholder(com.denzcoskun.imageslider.R.drawable.default_placeholder)
                            .into(holder.binding.img2);
                    Glide.with(activity)
                            .load(postModel.getImgUrls().get(2))
                            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                            .placeholder(com.denzcoskun.imageslider.R.drawable.default_placeholder)
                            .into(holder.binding.img3);
                    holder.binding.img1.setVisibility(View.VISIBLE);
                    holder.binding.img2.setVisibility(View.VISIBLE);
                    holder.binding.img3.setVisibility(View.VISIBLE);
                }
                holder.binding.imgSection.setVisibility(View.VISIBLE);
            }else {
                holder.binding.imgSection.setVisibility(View.GONE);
            }

            holder.binding.likeText.setText(postModel.getLike() + " Likes");
            holder.binding.uploadTime.setText("Public Figure | " + TimeAgo.using(postModel.getUploadTime()));
            holder.binding.candidateImg.setImageDrawable(activity.getDrawable(R.drawable.placeholder));

            holder.binding.wishListBtn.setOnLikeListener(new OnLikeListener() {
                @Override
                public void liked(LikeButton likeButton) {
                    postModel.setLike(postModel.getLike() + 1);
                    holder.binding.likeText.setText(postModel.getLike() + " Likes");
                }

                @Override
                public void unLiked(LikeButton likeButton) {
                    postModel.setLike(postModel.getLike() - 1);
                    holder.binding.likeText.setText(postModel.getLike() + " Likes");
                }
            });

            holder.binding.wishListLin.setOnClickListener(v->{
                holder.binding.wishListBtn.performClick();
            });

            holder.binding.share.setOnClickListener(v->{
                Toast.makeText(activity, "sharing post link ...", Toast.LENGTH_SHORT).show();
            });


        }

    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ChildWorksBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ChildWorksBinding.bind(itemView);
        }
    }
}
