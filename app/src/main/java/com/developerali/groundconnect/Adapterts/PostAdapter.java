package com.developerali.groundconnect.Adapterts;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developerali.groundconnect.Models.PostModel;
import com.developerali.groundconnect.R;
import com.developerali.groundconnect.databinding.ChildWorksBinding;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{

    Activity activity;
    ArrayList<PostModel> arrayList;
    int size;

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
