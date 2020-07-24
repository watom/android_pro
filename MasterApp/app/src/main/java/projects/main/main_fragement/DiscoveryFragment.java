package projects.main.main_fragement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.haitao.www.myformer.R;

import projects.main.pages.FriendLifeActivity;

/**
 * Created by watom_Thinkpad on 2018/9/2.
 */

public class DiscoveryFragment extends Fragment {
    private View discovery;
    private Context context;
    private LinearLayout friendLivelihood;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        discovery = inflater.inflate(R.layout.activity_main_discovery, container, false);
        initView();
        initEvent();
        return discovery;
    }

    private void initView() {
        friendLivelihood = discovery.findViewById(R.id.friend_livelihood);
    }

    private void initEvent() {
        friendLivelihood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                startActivity(new Intent(getActivity(), FriendLifeActivity.class));
            }
        });
    }

}
