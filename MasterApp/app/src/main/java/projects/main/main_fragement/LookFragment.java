package projects.main.main_fragement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.haitao.www.myformer.R;

/**
 * Created by watom_Thinkpad on 2018/9/2.
 */

public class LookFragment extends Fragment {
    private View wodeView;
    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        context = getActivity();
        wodeView = inflater.inflate(R.layout.activity_main_look, container, false);
        initView();
        return wodeView;
    }

    private void initView() {
        VideoView videoView = wodeView.findViewById(R.id.main_video);
        MediaController controller = new MediaController(context);//实例化控制器
        String videoPath="https://us-3.wl-cdn.com/hls/20200528/28f7d70b3a6f5773db64682f7b0c9a20/film_00000.ts";
        videoView.setVideoURI(Uri.parse(videoPath));
        controller.setMediaPlayer(videoView);
        videoView.setMediaController(controller);
    }
}
