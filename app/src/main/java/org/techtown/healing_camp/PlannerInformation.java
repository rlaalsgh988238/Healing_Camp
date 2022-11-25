package org.techtown.healing_camp;

import android.content.Context;
import android.widget.LinearLayout;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import org.w3c.dom.Text;

public class PlannerInformation {
    static String text;

    PlannerInformation(String text) {
        this.text = text;
    }

    //리턴 함수 모음
    public static String getText(){
        return text;
    }
}
