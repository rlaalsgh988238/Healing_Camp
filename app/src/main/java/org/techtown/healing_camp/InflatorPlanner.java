package org.techtown.healing_camp;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InflatorPlanner extends LinearLayout {
    public InflatorPlanner(Context context, PlannerInformation plannerInformation) {
        super(context);
        addPlanner(context,plannerInformation);
    }

    private void addPlanner(Context context, PlannerInformation plannerInformation){
        LayoutInflater inflator = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflator.inflate(R.layout.planner_container_layout,this,true);

        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText("인덱스 번호: "+ plannerInformation.getText());
    }

}
