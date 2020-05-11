package com.haitao.www.myformer.nettys.netprogramme;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.haitao.www.myformer.R;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;

public class NetBaseActivity extends AppCompatActivity {
    private TextView tvLocalIp;
    private TextView tvRemoteIp;
    private TextView tvReachableIp;
    private InetAddress localHost;
    private InetAddress remoteHost;
    private boolean reachable;

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    tvLocalIp.append(localHost.getHostAddress());
                    tvRemoteIp.append(remoteHost.getHostAddress());
                    tvReachableIp.append(Boolean.toString(reachable));
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_base);
        findViews();
        initData();
    }

    private void findViews() {
        tvLocalIp = (TextView) findViewById(R.id.tv_local_ip);
        tvRemoteIp = (TextView) findViewById(R.id.tv_remote_ip);
        tvReachableIp = (TextView) findViewById(R.id.tv_reachable_ip);
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    localHost = InetAddress.getLocalHost();
                    remoteHost = InetAddress.getByName("www.baidu.com");
                    reachable = localHost.isReachable(NetworkInterface.getByName("www.baidu.com"), 50, 5000);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        }).start();
    }
}
