package io.github.virresh.matvt.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;

import androidx.core.content.ContextCompat;

import io.github.virresh.matvt.R;
import io.github.virresh.matvt.gui.IconStyleSpinnerAdapter;
import io.github.virresh.matvt.helper.Helper;

/**
 * Draw a Mouse Cursor on screen
 */
public class MouseCursorView extends View {
    private static final int DEFAULT_ALPHA= 255;

    private final PointF mPointerLocation;
    private final Paint mPaintBox;
    private Bitmap mPointerBitmap;
    private int pointerDrawableReference;
    private int pointerSizeReference;

    private int pointerOffsetX;
    private int pointerOffsetY;

    public MouseCursorView(Context context) {
        super(context);
        setWillNotDraw(false);
        mPointerLocation = new PointF();
        mPaintBox = new Paint();
        updateFromPreferences();
        setBitmap(context);
    }

    private void setBitmap(Context context) {
        BitmapDrawable bp = (BitmapDrawable) ContextCompat.getDrawable(context, pointerDrawableReference);
        Bitmap originalBitmap = bp.getBitmap();
        // ybtag - changed the scale to 20 to be more suitable for flipphones
        BitmapDrawable d = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(originalBitmap, 20 * pointerSizeReference, 20 * pointerSizeReference, true));
        mPointerBitmap = d.getBitmap();
    }

    public void updateFromPreferences() {
        Context ctx = getContext();
        String iconStr = Helper.getMouseIconPref(ctx);
        pointerDrawableReference = IconStyleSpinnerAdapter.textToResourceIdMap.getOrDefault(iconStr, R.drawable.pointer);
        pointerOffsetX = IconStyleSpinnerAdapter.textToOffsetX.getOrDefault(iconStr, 0);
        pointerOffsetY = IconStyleSpinnerAdapter.textToOffsetY.getOrDefault(iconStr, 0);
        pointerSizeReference = Helper.getMouseSizePref(ctx) + 1;
        setBitmap(getContext());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaintBox.setAlpha(DEFAULT_ALPHA);
        canvas.drawBitmap(mPointerBitmap, mPointerLocation.x - 50 * pointerSizeReference * pointerOffsetX / 209, mPointerLocation.y - 50 * pointerSizeReference * pointerOffsetY / 209, mPaintBox);
    }

    public void updatePosition(PointF p) {
        mPointerLocation.x = p.x;
        mPointerLocation.y = p.y;
        this.postInvalidate();
    }
}