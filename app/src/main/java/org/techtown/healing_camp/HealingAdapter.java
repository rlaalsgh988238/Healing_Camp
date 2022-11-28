package org.techtown.healing_camp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class HealingAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<PlannerInformation> plannerInformation;
    public HealingAdapter(Context context, ArrayList<PlannerInformation> plannerInformations){
        this.context = context;
        this.plannerInformation = plannerInformations;
        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return plannerInformation.size();
    }

    @Override
    public Object getItem(int i) {
        return plannerInformation.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View coverView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.planner_container_layout,null);
        Button onClickWritePlaner = (Button) view.findViewById(R.id.onClickWritePlaner);
        TextView text = (TextView) view.findViewById(R.id.text);
        return null;
    }
}
