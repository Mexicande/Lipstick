package com.deerlive.lipstick.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.deerlive.lipstick.R;
import com.deerlive.lipstick.model.GameListBean;

import java.util.List;

/**
 * Created by apple on 2018/3/28.
 */

public class GameAdapter extends BaseQuickAdapter<GameListBean.InfoBean,BaseViewHolder> {
    public GameAdapter(List<GameListBean.InfoBean> data) {
        super(R.layout.game_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GameListBean.InfoBean item) {
        helper.setText(R.id.game_name,item.getName());
        Glide.with(mContext).load(item.getImg())
                .into((ImageView) helper.getView(R.id.game_img));
    }
}
