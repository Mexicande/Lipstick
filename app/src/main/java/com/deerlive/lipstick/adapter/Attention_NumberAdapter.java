package com.deerlive.lipstick.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.deerlive.lipstick.R;

import java.util.List;

/**
 * Created by apple on 2018/3/29.
 */

public class Attention_NumberAdapter extends BaseQuickAdapter<Integer,BaseViewHolder> {

    public Attention_NumberAdapter(List<Integer> data) {

        super(R.layout.attention_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        helper.setText(R.id.tv_number, String.valueOf(item))
                .setBackgroundColor(R.id.tv_number,mContext.getResources().getColor(R.color.yellow_item));
    }
}
