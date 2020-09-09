package com.haitao.www.myformer.logic.LogicTest.album;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.utils.GlideEngine;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

import java.util.ArrayList;

import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class SelectImageFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private View mView;
    private ShowImageAdapter mShowImageAdapter;
    /**
     * 每次拿到的图片url list集合
     */
    private ArrayList<String> mImageList = new ArrayList<>();
    /**
     * 一共所选择图片url list集合
     */
    private ArrayList<String> mTotalImageList = new ArrayList<>();
    public static final int REQUEST_CODE = 999;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.layout_select_image, container, false);
        initViews();
        initEvents();
        return mView;
    }
    private void initViews() {
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mShowImageAdapter = new ShowImageAdapter(getContext());
        mRecyclerView.setAdapter(mShowImageAdapter);
    }

    private void initEvents() {
        mShowImageAdapter.setOnPickerListener(new ShowImageAdapter.OnPickerListener() {
            @Override
            public void onPicker(int position) {
                //根据位置大小判断
                // 当前是跳转到选择图片Activity(MultiImageSelectorActivity)中还是预览Activity(PhotoPreviewActivity)中
                if (position == mImageList.size()) {
                    if (position != ShowImageAdapter.MAX_NUMBER) {
                        //此处intent中携带的参数是根据MultiImageSelectorActivity的参数互相匹配使用，个人可根据自己的项目要求进行设置
                        Intent intent = new Intent(getActivity(), MultiImageSelectorActivity.class);
                        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
                        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, ShowImageAdapter.MAX_NUMBER);
                        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
                        if (mImageList != null && mImageList.size() > 0) {
                            intent.putStringArrayListExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, mImageList);
                        }
                        startActivityForResult(intent, REQUEST_CODE);
                    }
                } else {
                    //跳转到图片预览界面
//                    PhotoPreviewActivity.startActivity(getActivity(), position, mImageList);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (getActivity().RESULT_OK == resultCode) {
                    //获取图片url
                    mImageList = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                    mTotalImageList.clear();
                    for (String imageUrl : mImageList) {
                        mTotalImageList.add(imageUrl);
                    }
                    mShowImageAdapter.setImageUrlList(mTotalImageList);
                }
                break;
        }
    }
}
