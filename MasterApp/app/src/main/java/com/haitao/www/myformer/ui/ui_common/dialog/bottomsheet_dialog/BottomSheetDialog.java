package com.haitao.www.myformer.ui.ui_common.dialog.bottomsheet_dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.haitao.www.myformer.R;


public class BottomSheetDialog extends BottomSheetDialogFragment {
    private TextView btn_signUp;
    private EditText tv_label_name;
    private EditText tv_label_url;
    private BottomSheetBehavior mBehavior;

    public BottomSheetDialog() {
        super();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getActivity(), R.layout.layout_input_bookmark, null);
        dialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View) view.getParent());
        initChildView(view);
        initEvent();
        return dialog;
    }

    private void initChildView(View parentView) {
        tv_label_name = parentView.findViewById(R.id.tv_labelName);
        tv_label_url = parentView.findViewById(R.id.tv_url);
        btn_signUp = parentView.findViewById(R.id.btn_sign_up_01);
    }

    private void initEvent() {
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_sign_up_01:
                        dismiss();
                        if (onSignUpClickListener != null) {
                            Bundle bundle = new Bundle();
                            bundle.putString("tv_label_name", tv_label_name.getText().toString().trim());
                            bundle.putString("tv_label_url", tv_label_url.getText().toString().trim());
                            onSignUpClickListener.onClick(bundle);
                        }
                        break;
                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);//全屏展开
    }

    private OnSignUpClickListener onSignUpClickListener;

    public void setOnSignUpClickListener(OnSignUpClickListener onSignUpClickListener) {
        this.onSignUpClickListener = onSignUpClickListener;
    }

    public interface OnSignUpClickListener {
        void onClick(Bundle bundle);
    }
}
