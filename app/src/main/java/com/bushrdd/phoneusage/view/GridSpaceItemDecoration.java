package com.bushrdd.phoneusage.view;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * created
 */
public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final String TAG = "GridSpaceItemDecoration";

    private boolean mIsSame;//行和列间距是否一致
    private int mSpacing;//间距
    private int mRowSpacing;//行间距
    private int mColumnSpacing;// 列间距

    /**
     * @param rowSpacing    行间距
     * @param columnSpacing 列间距
     */
    public GridSpaceItemDecoration(int rowSpacing, int columnSpacing) {
        mIsSame = false;
        this.mRowSpacing = rowSpacing;
        this.mColumnSpacing = columnSpacing;
    }

    /**
     * @param spacing 间距
     */
    public GridSpaceItemDecoration(int spacing) {
        mIsSame = true;
        this.mSpacing = spacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view); // 获取item在所有item中的位置
        if (mIsSame) {
            outRect.top = mSpacing;
            outRect.left = mSpacing;
            if (position % 2 == 1) {
                outRect.right = mSpacing;
            }
        } else {
            outRect.bottom = mRowSpacing;
            if (position % 2 == 0) {//第一列
                outRect.right = mColumnSpacing * 3 / 2;
                outRect.left = mColumnSpacing;
            } else {//第二列
                outRect.right = mColumnSpacing;
            }
        }
    }
}
