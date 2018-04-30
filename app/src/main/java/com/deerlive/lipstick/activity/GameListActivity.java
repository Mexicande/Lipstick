package com.deerlive.lipstick.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.deerlive.lipstick.R;
import com.deerlive.lipstick.activity.game.AddSpeedActivity;
import com.deerlive.lipstick.activity.game.AttentionActivity;
import com.deerlive.lipstick.adapter.GameAdapter;
import com.deerlive.lipstick.base.BaseActivity;
import com.deerlive.lipstick.common.Api;
import com.deerlive.lipstick.common.WebviewActivity;
import com.deerlive.lipstick.intf.OnRequestDataListener;
import com.deerlive.lipstick.model.GameListBean;
import com.deerlive.lipstick.utils.ActivityUtils;
import com.deerlive.lipstick.utils.SPUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;

import butterknife.Bind;

public class GameListActivity extends BaseActivity {

    @Bind(R.id.layout_top_back)
    ImageView layoutTopBack;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private GameAdapter mGameAdapter;
    private String mToken;
    public void goBack(View v) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToken = SPUtils.getInstance().getString("token");

        tvTitle.setText(R.string.title_game);
        initView();
        initDate();
        setListener();
    }

    private void initDate() {

        HashMap<String,String>parm=new HashMap<>();
        parm.put("token",mToken);
        Api.setGetGame(this, parm, new OnRequestDataListener() {
            @Override
            public void requestSuccess(int code, JSONObject data) {
                if (mRefreshLayout.isRefreshing()) {
                    mRefreshLayout.finishRefresh();
                }
                    GameListBean gameListBean = JSON.parseObject(data.toString(), GameListBean.class);
                    if (gameListBean.getInfo()!=null){
                        mGameAdapter.setNewData(gameListBean.getInfo());
                    }
            }

            @Override
            public void requestFailure(int code, String msg) {
                toast(msg);
                if (mRefreshLayout.isRefreshing()) {
                    mRefreshLayout.finishRefresh();
                }
            }
        });
    }

    private void setListener() {
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                initDate();
            }
        });
        mGameAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GameListBean.InfoBean item = mGameAdapter.getItem(position);
                if("2".equals(item.getStyle())){
                    Bundle temp = new Bundle();
                    temp.putString("title", item.getName());
                    temp.putString("jump", item.getUrl());
                    ActivityUtils.startActivity(temp, WebviewActivity.class);
                }else {
                    if("1".equals(item.getType())){
                        ActivityUtils.startActivity(AddSpeedActivity.class);
                    }else {
                        ActivityUtils.startActivity(AttentionActivity.class);
                    }
                }
            }
        });
    }

    private void initView() {
        mGameAdapter=new GameAdapter(null);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(mGameAdapter);

    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_game_list;
    }
}
