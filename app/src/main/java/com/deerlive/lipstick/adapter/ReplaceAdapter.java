package com.deerlive.lipstick.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.deerlive.lipstick.R;
import com.deerlive.lipstick.model.DanmuMessage;
import com.deerlive.lipstick.model.GrabBean;

import java.util.List;

/**
 * Created by apple on 2018/3/27.
 */

public class ReplaceAdapter extends BaseQuickAdapter<GrabBean.InfoBean,BaseViewHolder> {

    public ReplaceAdapter(int layoutResId, List<GrabBean.InfoBean> data) {
        super(R.layout.item_record_wq, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GrabBean.InfoBean item) {

    }
}
