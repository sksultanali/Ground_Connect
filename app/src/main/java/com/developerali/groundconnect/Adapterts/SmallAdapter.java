package com.developerali.groundconnect.Adapterts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.developerali.groundconnect.Activities.TemporayPlayer;
import com.developerali.groundconnect.Models.Member;
import com.developerali.groundconnect.R;

import java.util.ArrayList;

public class SmallAdapter extends RecyclerView.Adapter<SmallAdapter.myviewholder> {

    Context context, cstx;;
    ArrayList<Member> models;

    public SmallAdapter(Context context, ArrayList<Member> models, Context cstx) {
        this.context = context;
        this.models = models;
        this.cstx = cstx;

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_video, parent, false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {
        Member membermodel = models.get(position);

        holder.textView.setText(String.valueOf(membermodel.getVideoViews()));
        Glide.with(context).load(membermodel.getVideoUrl()).into(holder.imge);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(cstx, TemporayPlayer.class);
                intent.putExtra("videoUrlUT", membermodel.getVideoUrl());
                intent.putExtra("decUT", membermodel.getDescription());
                intent.putExtra("userNameUT", membermodel.getUserName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class myviewholder extends RecyclerView.ViewHolder{
        ImageView imge;
        TextView textView;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            imge = itemView.findViewById(R.id.thumb_image);
            textView = itemView.findViewById(R.id.view_txt);

        }
    }

}
