package org.techtown.healing_camp;

import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HealingAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<PlannerInformation> plannerInformation;

    public HealingAdapter(Context context, ArrayList<PlannerInformation> plannerInformation){
        this.context = context;
        this.plannerInformation = plannerInformation;
        this.layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return plannerInformation.size();
    }

    @Override
    public PlannerInformation getItem(int position) {
        return plannerInformation.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View covertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.planner_container_layout,parent,false);
        TextView title = view.findViewById(R.id.title);
        TextView content = view.findViewById(R.id.content);
        TextView date = view.findViewById(R.id.date);

        title.setText(plannerInformation.get(position).getTitle());
        content.setText(plannerInformation.get(position).getContent());
        date.setText(plannerInformation.get(position).getDate());
        //애니메이션
        Animation popUpAnimation = AnimationUtils.loadAnimation(context, (position==0) ? R.anim.pop_up_animation : R.anim.non_animation);
        view.setAnimation(popUpAnimation);


        return view;
    }
}
