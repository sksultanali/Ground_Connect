package com.developerali.groundconnect.Adapterts;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developerali.groundconnect.Models.Member;
import com.developerali.groundconnect.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class videoadapter extends FirebaseRecyclerAdapter<Member,videoadapter.myviewholder> {

    public videoadapter(@NonNull FirebaseRecyclerOptions<Member> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Member model) {
        holder.setdata(model);

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.signle_video_row,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {
        VideoView videoView;
        TextView title,desc;
        ProgressBar pbar;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            videoView=(VideoView)itemView.findViewById(R.id.videoView);
            title=(TextView)itemView.findViewById(R.id.textVideoTitle);
            desc=(TextView)itemView.findViewById(R.id.textVideoDescription);
            pbar=(ProgressBar)itemView.findViewById(R.id.videoProgressBar);
        }

        void setdata(Member obj) {
            videoView.setVideoPath(obj.getVideoUrl());
            //videoView.setVideoPath("http://surl.li/jfdjzk");
            title.setText(obj.getUserName());
            desc.setText(obj.getDescription());

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    pbar.setVisibility(View.GONE);
                    mediaPlayer.start();
                }
            });

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
        }
    }


}
