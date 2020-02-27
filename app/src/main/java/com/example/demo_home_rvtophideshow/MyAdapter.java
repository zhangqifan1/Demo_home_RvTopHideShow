package com.example.demo_home_rvtophideshow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import androidx.annotation.Nullable;

/**
 * -----------------------------
 * Created by zqf on 2020/2/27.
 * ---------------------------
 */
public class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public MyAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv, item);
        helper.getView(R.id.tv).setAlpha(1);
    }

}
