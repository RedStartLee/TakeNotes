package com.lihuzi.takenotes.ui.activity;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.lihuzi.takenotes.R;
import com.lihuzi.takenotes.db.CoreDB;
import com.lihuzi.takenotes.model.NotesModel;
import com.lihuzi.takenotes.ui.adapter.RecordAdapter;

import java.util.ArrayList;

public class RecordActivity extends AppCompatActivity
{
    private ArrayList<NotesModel> _list;

    private RecyclerView _recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        initView();
        initListener();
        initLoad();
    }

    private void initView()
    {
        _recyclerView = findViewById(R.id.act_record_recyclerview);
    }

    private void initListener()
    {
        _recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState)
            {
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                int position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
                if (position != headerPosition)
                {

                }
            }
        });
    }

    private void setHeaderView()
    {
        float pay = 0;
        float obtain = 0;
        for (int i = 0; i < _list.size(); i++)
        {
            NotesModel model = _list.get(i);
            if (model._type > 0)
            {
                pay += model._sum;
            }
            else
            {
                obtain += model._sum;
            }
        }
//        _headerView.setText("本月支出: " + String.valueOf(pay) + ",本月收入: " + String.valueOf(obtain));
    }

    private int headerPosition;

    private void initLoad()
    {
        _recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<NotesModel> list = CoreDB.queryThisMonth(System.currentTimeMillis());
        TextView header = new TextView(this);
        header.setTextColor(ContextCompat.getColor(this, R.color.text_color_dark));
        header.setTextSize(20);
//        _recyclerView.setAdapter(new RecordAdapter(list));
    }
}
