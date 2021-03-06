package com.deerlive.lipstick.fragment;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


import com.deerlive.lipstick.R;
import com.deerlive.lipstick.common.Api;
import com.deerlive.lipstick.common.Contacts;
import com.deerlive.lipstick.common.WebviewActivity;
import com.deerlive.lipstick.intf.DialogListener;
import com.deerlive.lipstick.utils.ActivityUtils;
import com.deerlive.lipstick.utils.SPUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * 挑战失败
 */
public class DefeateFragment extends DialogFragment {


    @Bind(R.id.tv_spend)
    TextView tvSpend;
    private String mToken;
    private DialogListener callback;

    public static DefeateFragment newInstance(int nu) {
        DefeateFragment instance = new DefeateFragment();
        Bundle args = new Bundle();
        args.putInt("blance", nu);
        instance.setArguments(args);
        return instance;

    }

    public DefeateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_defeate, container, false);
        final Window window = getDialog().getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.CENTER;
            wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
            wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(wlp);
        }
        ButterKnife.bind(this, view);
        mToken = SPUtils.getInstance().getString("token");
        int blance = getArguments().getInt("blance");
        tvSpend.setText(blance + "金币/次");
        setListener();
        return view;
    }

    private void setListener() {
        //监听返回键
        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
                    callback.onDefeateComplete();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.Base_AlertDialog);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback= (DialogListener) context;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        final Activity activity = getActivity();
        if (activity instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.iv_again, R.id.bt_share,R.id.layout,R.id.cancel1,R.id.cancel2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_again:
                dismiss();
                break;
            case R.id.layout:
                callback.onDefeateComplete();
                break;
            case R.id.bt_share:
                Bundle temp = new Bundle();
                temp.putString("type", Contacts.ATTENTION_TYPE);
                temp.putString("title", getResources().getString(R.string.yaoqing_me));
                temp.putString("jump", Api.URL_GAME_YAOQING + "&token=" + mToken);
                ActivityUtils.startActivity(temp, WebviewActivity.class);
                callback.onDefeateComplete();

                break;
            case R.id.cancel1:
            case R.id.cancel2:
                callback.onDefeateComplete();
                break;
            default:
                break;
        }
    }

}
