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
    }
}
