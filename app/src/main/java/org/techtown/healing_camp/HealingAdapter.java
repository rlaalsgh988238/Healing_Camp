package org.techtown.healing_camp;

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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
        int index =  Integer.parseInt(plannerInformation.get(position).getText())+1;
        text.setText(getTime()+"에 작성된\n"+ index +"번째 플레너 입니다.");

        Animation popUpAnimation = AnimationUtils.loadAnimation(context, R.anim.pop_up_animation);
        //view.setAnimation(popUpAnimation);

        onClickWritePlaner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context.getApplicationContext(), InsidePlannerActivity.class);
                intent.putExtra("index",position);
                ((Activity)context).startActivity(intent);
            }
        });


        return view;
    }
    private String getTime(){
        long now = System.currentTimeMillis();
        Date data = new Date(now);
        SimpleDateFormat timeFormat = new SimpleDateFormat("MM월 dd일 hh시 mm분");
        String getTime = timeFormat.format(data);

        return getTime;
    }
}
