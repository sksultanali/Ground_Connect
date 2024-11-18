package com.developerali.groundconnect.Adapterts;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.developerali.groundconnect.Models.ToolsModel;
import com.developerali.groundconnect.R;

import java.util.ArrayList;

public class ListAdapterVertical extends BaseAdapter {
    Activity activity;
    ArrayList<ToolsModel> arrayList;

    public ListAdapterVertical(Activity activity, ArrayList<ToolsModel> arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater obj = activity.getLayoutInflater();
        View view1 = obj.inflate(R.layout.sample_tools_vertical, null);
        ImageView imageView = view1.findViewById(R.id.toolImg);
        TextView textView = view1.findViewById(R.id.toolName);
        //LinearLayout linearLayout = view1.findViewById(R.id.linearAd);

        ToolsModel toolsModel = arrayList.get(i);
        textView.setText(toolsModel.getTitle());
        imageView.setImageDrawable(toolsModel.getImg());
        //linearLayout.setBackground(toolsModel.getBackDrawable());

        return view1;
    }
}
