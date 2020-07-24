package projects.main.main_fragement;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.utils.GlideEngine;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;

/**
 * Created by watom_Thinkpad on 2018/9/1.
 */

public class WodeFragment extends Fragment {
    private View wodeView;
    private Context context;
    private LinearLayout llTitleMe;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        wodeView = inflater.inflate(R.layout.activity_main_wode, container, false);
        initView();
        initEvent();
        return wodeView;
    }

    private void initView() {
        llTitleMe = wodeView.findViewById(R.id.ll_title_me);
    }

    private void initEvent() {
        llTitleMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelector.create(getActivity())
                        .openGallery(PictureMimeType.ofImage())
                        .imageEngine(GlideEngine.createGlideEngine()) // 请参考Demo GlideEngine.java
                        .forResult(PictureConfig.CHOOSE_REQUEST);
            }
        });
    }
}
