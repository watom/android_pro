package com.haitao.www.myformer.ui.ui_common.ModuleTest.ratingbarview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.Toast;

import com.haitao.www.myformer.R;
import com.haitao.www.myformer.utils.ToastUtils;

import java.math.BigDecimal;

public class RatingBarActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ratingbar);
        setRatingBar();
    }

    private void setRatingBar() {
        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(RatingBarActivity.this,String.format("current rating val:%f, fromUser = %d", rating, fromUser ? 1:0),
                        Toast.LENGTH_SHORT).show();
                if(new BigDecimal(1.0).equals(new BigDecimal(rating))){
                    ratingBar.setRating(1);
                    ratingBar.setIndeterminateDrawable(getDrawable(R.drawable.ic_anger));
                }else if(new BigDecimal(2.0).equals(new BigDecimal(rating))){

                }else{

                }
            }
        });

        final RatingBarView2 ratingBar2 = (RatingBarView2) findViewById(R.id.ratingBar2);
        ratingBar2.setOnRatingListener(new RatingBarView2.OnRatingListener() {
            @Override
            public void onRating(int mSelectedPosition) {
                ToastUtils.showToast(RatingBarActivity.this,""+mSelectedPosition);
            }
        });
    }
}
