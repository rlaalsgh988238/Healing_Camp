package org.techtown.healing_camp;

import android.content.Context;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlannerInformation {
    String title, content,date;

    public PlannerInformation(String title, String content) {
        this.title = title;
        this.content = content;
        this.date = getTime();
    }
    //리턴 함수 모음

    public String getTitle(){return title;}
    public String getContent(){return content;}
    public String  getDate(){return date;}
    public void setTitle(String title) {this.title = title;}
    public void setDate(String date) {this.date = date;}
    public void setContent(String content) {this.content = content;}

    private String getTime(){
        long now = System.currentTimeMillis();
        Date data = new Date(now);
        SimpleDateFormat timeFormat = new SimpleDateFormat("MM월 dd일 hh시 mm분");
        String getTime = timeFormat.format(data);

        return getTime;
    }
}
