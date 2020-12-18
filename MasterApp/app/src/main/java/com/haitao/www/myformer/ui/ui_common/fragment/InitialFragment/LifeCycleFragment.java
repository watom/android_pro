package com.haitao.www.myformer.ui.ui_common.fragment.InitialFragment;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.log.Log;

/**
 * Created by Administrator on 2017/12/1 0001.
 */

public class LifeCycleFragment extends Fragment {
    private static final String Tag ="LifeCycleFragment";
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e(Tag,"onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(Tag,"onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(Tag,"onCreateView");
        View view = inflater.inflate(R.layout.fragment_lifecycle, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e(Tag,"onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e(Tag,"onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e(Tag,"onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e(Tag,"onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e(Tag,"onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(Tag,"onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(Tag,"onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e(Tag,"onDetach");
    }
}
