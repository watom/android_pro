package projects.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.ui.ui_common.component.customassemblewidget.TitleBar;

public class ZhenZhuMeiXueInput extends Activity implements View.OnClickListener {
    private Context context;
    private TitleBar titlebar;
    private EditText tradeJine;
    private EditText tradeType;
    private EditText tradeTime;
    private EditText tradeId;
    private EditText tradeNo;
    private EditText orderId;
    private EditText remark;
    private Button button1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_inputlog);
        context = ZhenZhuMeiXueInput.this;
        findViews();
    }

    private void findViews() {
        titlebar = (TitleBar) this.findViewById(R.id.titlebar);
        titlebar.setTitle("交易记录");
        titlebar.setRightText("生成");
        titlebar.setRightTextColor(R.color.orchid);
        tradeJine = (EditText) this.findViewById(R.id.trade_jine);
        tradeType = (EditText) this.findViewById(R.id.trade_type);
        tradeTime = (EditText) this.findViewById(R.id.trade_time);
        tradeId = (EditText) this.findViewById(R.id.trade_id);
        tradeNo = (EditText) this.findViewById(R.id.trade_no);
        orderId = (EditText) this.findViewById(R.id.order_id);
        remark = (EditText) this.findViewById(R.id.remark);
        titlebar.setRightTextClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("tradeJine", tradeJine.getText().toString());
        bundle.putString("tradeType", tradeType.getText().toString());
        bundle.putString("tradeTime", tradeTime.getText().toString());
        bundle.putString("tradeId", tradeId.getText().toString());
        bundle.putString("tradeNo", tradeNo.getText().toString());
        bundle.putString("orderId", orderId.getText().toString());
        bundle.putString("remark", remark.getText().toString());
        Intent intent = new Intent(context, ZhenZhuMeiXueLog.class);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
