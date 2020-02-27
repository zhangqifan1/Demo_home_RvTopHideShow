package com.example.demo_home_rvtophideshow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ConvertUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> data = new ArrayList<>();

        for (int i = 0; i < 40; i++) {

            data.add("  我是第" + i + "条数据");
        }


        RecyclerView recyclerView = findViewById(R.id.rv);
        MyAdapter mAdapter = new MyAdapter(R.layout.item_text, data);


        GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);


        recyclerView.addOnScrollListener(mScrollListener);

    }

    RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
            //这里我使用的是GridLayoutManager,并且RecyclerView只有两列
            if(manager instanceof GridLayoutManager){
                GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
                int firstVisiblePos = gridLayoutManager.findFirstVisibleItemPosition();
                setViewAplha(gridLayoutManager.findViewByPosition(firstVisiblePos));
                setViewAplha(gridLayoutManager.findViewByPosition(firstVisiblePos+1));

                int firstCompletelyVisible = gridLayoutManager.findFirstCompletelyVisibleItemPosition();
                View view1 =  gridLayoutManager.findViewByPosition(firstCompletelyVisible);
                View view2 =  gridLayoutManager.findViewByPosition(firstCompletelyVisible + 1);
                if(view1 != null){
                    view1.findViewById(R.id.tv).setAlpha(1);
                }
                if(view2 != null){
                    view2.findViewById(R.id.tv).setAlpha(1);
                }
            }
        }
    };


    float beginPercent = 0.2f;// 0.2-？70%
    float endValue = 2;

    private void setViewAplha(View view){
        if (view != null ){
            float p = ConvertUtils.px2dp(Math.abs((int) view.getY())) * 1.0f / ConvertUtils.px2dp(view.getHeight()) * 1.0f;
            float curPercent = Float.compare(p - beginPercent, 0.0f) < 0 ? 0.0f : p - beginPercent;
            curPercent = Float.compare(1, curPercent * endValue) < 0 ? 1 : curPercent * endValue;

            view.findViewById(R.id.tv).setAlpha(1 - curPercent);

        }
    }

}
