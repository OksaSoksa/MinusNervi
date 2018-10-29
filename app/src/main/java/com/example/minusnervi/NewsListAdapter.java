package com.example.minusnervi;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Администратор on 29.10.2018.
 */

public class NewsListAdapter extends ArrayAdapter<News> {

    private Activity context;
    private List<News> newsList;
    Date date = new Date();
    Long dateNews;

    public NewsListAdapter (Activity context,List<News> newsList){
        super(context,R.layout.custom_news,newsList);
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listItem = inflater.inflate(R.layout.custom_news,null,true);
        TextView NewsName = listItem.findViewById(R.id.customName);
        TextView NewsDesc = listItem.findViewById(R.id.customDesc);
        TextView NewsDate = listItem.findViewById(R.id.customDate);

        News news = newsList.get(position);
        NewsName.setText(news.getName());
        NewsDesc.setText(news.getDescription());

        dateNews = news.getDate()*1000;
        date = new java.util.Date(dateNews);
        String DateStr = new SimpleDateFormat("MM dd,yyyy, hh:mma").format(date);
        NewsDate.setText(DateStr);

        return listItem;
    }
}
