package org.techtown.healing_camp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MemoAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Memo> memoList;

    public MemoAdapter(Context context, ArrayList<Memo> memo){
        this.context = context;
        this.memoList = memo;
        this.layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return memoList.size();
    }

    @Override
    public Memo getItem(int i) {
        return memoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View covertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.memo_layout,parent,false);
        TextView memo = view.findViewById(R.id.memoContent);

        memo.setText(memoList.get(position).getMemo());

        return view;
    }
}
