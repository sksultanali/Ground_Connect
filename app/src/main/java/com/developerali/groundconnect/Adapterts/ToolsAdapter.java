package com.developerali.groundconnect.Adapterts;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.developerali.groundconnect.Helper;
import com.developerali.groundconnect.Models.ToolsModel;
import com.developerali.groundconnect.R;
import com.developerali.groundconnect.databinding.ChildSchemesBinding;

import java.util.ArrayList;

public class ToolsAdapter extends RecyclerView.Adapter<ToolsAdapter.ViewHolder>{

    Activity activity;
    ArrayList<ToolsModel> arrayList;

    public ToolsAdapter(Activity activity, ArrayList<ToolsModel> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.child_schemes, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ToolsModel toolsModel = arrayList.get(position);

        holder.binding.textTitle.setText(toolsModel.getTitle());
        if (!activity.isDestroyed()){
            holder.binding.img.setImageDrawable(toolsModel.getImg());
        }


        holder.itemView.setOnClickListener(v->{
            if (toolsModel.getLink() != null){
                if (Helper.isChromeCustomTabsSupported(activity)){
                    Helper.openChromeTab(toolsModel.getLink(), activity);
                }else {
                    Helper.openChromeTab(toolsModel.getLink(), activity);
                }
            }else {
                Toast.makeText(activity, "check", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ChildSchemesBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ChildSchemesBinding.bind(itemView);
        }
    }
}
