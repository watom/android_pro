package projects.main.main_fragement;

import androidx.fragment.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haitao.www.myformer.R;

/**
 * Created by watom_Thinkpad on 2018/9/2.
 */

public class NewsFragment extends Fragment {
    private View wodeView;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        wodeView = inflater.inflate(R.layout.activity_main_news, container, false);
        initView();
        return wodeView;
    }

    private void initView() {

    }
}
