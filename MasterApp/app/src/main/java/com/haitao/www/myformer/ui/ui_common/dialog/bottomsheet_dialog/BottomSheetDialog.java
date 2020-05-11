package com.haitao.www.myformer.ui.ui_common.dialog.bottomsheet_dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.haitao.www.myformer.R;


public class BottomSheetDialog extends android.support.design.widget.BottomSheetDialogFragment implements View.OnClickListener {
    private TextView btn_signUp;
    private EditText tv_label_name;
    private EditText tv_label_url;

    public BottomSheetDialog() {
        super();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.layout_input_bookmark, null);
        getChildView(view);

        android.support.design.widget.BottomSheetDialog bottomSheetDialog = new android.support.design.widget.BottomSheetDialog(getActivity());
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet).setBackgroundColor(this.getResources().getColor(android.R.color.transparent));
        bottomSheetDialog.show();

        return bottomSheetDialog;
    }

    private void getChildView(View parentView) {
        tv_label_name = parentView.findViewById(R.id.tv_labelName);
        tv_label_url = parentView.findViewById(R.id.tv_url);
        btn_signUp = parentView.findViewById(R.id.btn_sign_up_01);
        btn_signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
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

    private OnSignUpClickListener onSignUpClickListener;

    public void setOnSignUpClickListener(OnSignUpClickListener onSignUpClickListener) {
        this.onSignUpClickListener = onSignUpClickListener;
    }

    public interface OnSignUpClickListener {
        void onClick(Bundle bundle);
    }
}
