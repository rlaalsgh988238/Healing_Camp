package org.techtown.healing_camp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class SearchAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater;
    ArrayList<SearchList> searchList;


    public SearchAdapter(Context context, ArrayList<SearchList> searchList){
        this.context = context;
        this.searchList = searchList;
        this.layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return searchList.size();
    }

    @Override
    public Object getItem(int position) {
        return searchList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View coverView, ViewGroup viewGroup) {
        View view = layoutInflater.inflate(R.layout.search_result_container_layout,viewGroup,false);

        ImageView imageView;
        TextView nameCampingPlace,infoCampingPlace,whereCampingPlace,telCampingPlace,urlCampingPlace;
        nameCampingPlace = view.findViewById(R.id.nameCampingPlace);
        infoCampingPlace = view.findViewById(R.id.InfoCampingPlace);
        whereCampingPlace = view.findViewById(R.id.whereCampingPlace);
        telCampingPlace = view.findViewById(R.id.telCampingPlace);
        urlCampingPlace = view.findViewById(R.id.urlCampingPlace);
        imageView = view.findViewById(R.id.imageView);

        Glide.with(view).load(searchList.get(position).getUrl())
                .centerCrop()
                .fallback(R.drawable.no_image)
                .into(imageView);
        nameCampingPlace.setText(searchList.get(position).getName());
        infoCampingPlace.setText(searchList.get(position).getInfo());
        whereCampingPlace.setText(searchList.get(position).getLocal());
        telCampingPlace.setText(searchList.get(position).getTel());
        urlCampingPlace.setText(searchList.get(position).getHomePage());

        return view;
    }
}
