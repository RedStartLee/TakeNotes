package com.lihuzi.takenotes.ui.activity;

import android.os.Bundle;
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
    private TextView _monthSum;

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
        _monthSum = findViewById(R.id.act_record_month_sum_tv);
    }

    private void initListener()
    {
    }

    private void initLoad()
    {
        _recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<NotesModel> list = CoreDB.queryThisMonth(System.currentTimeMillis());
        _recyclerView.setAdapter(new RecordAdapter(list));
        setMonthSum(list);
    }

    private void setMonthSum(ArrayList<NotesModel> list)
    {
        float pay = 0;
        float obtain = 0;
        for (int i = 0; i < list.size(); i++)
        {
            NotesModel model = list.get(i);
            if (model._type > 0)
            {
                pay += model._sum;
            }
            else
            {
                obtain += model._sum;
            }
        }
        _monthSum.setText("本月支出: " + String.valueOf(pay) + ",本月收入: " + String.valueOf(obtain));
    }

}
