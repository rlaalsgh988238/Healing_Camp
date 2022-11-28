package org.techtown.healing_camp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HealingAdapter extends BaseAdapter {
    Context context = null;
    LayoutInflater layoutInflater= null;
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
    public View getView(int position, View coverView, ViewGroup parent) {
        Context context = parent.getContext();
        View view = layoutInflater.inflate(R.layout.planner_container_layout,parent,false);
        Button onClickWritePlaner = (Button) view.findViewById(R.id.onClickWritePlaner);
        TextView text = (TextView) view.findViewById(R.id.textView);
        text.setText(plannerInformation.get(position).getText());

        Animation popUpAnimation = AnimationUtils.loadAnimation(context, R.anim.pop_up_animation);
        view.setAnimation(popUpAnimation);

        onClickWritePlaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context.getApplicationContext(), getItem(position).getText(),Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


}
