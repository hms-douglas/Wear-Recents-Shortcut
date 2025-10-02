package dev.dect.wear.recentsshortcut.complication;

import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.wear.watchface.complications.data.ComplicationData;
import androidx.wear.watchface.complications.data.ComplicationType;
import androidx.wear.watchface.complications.data.GoalProgressComplicationData;
import androidx.wear.watchface.complications.data.MonochromaticImage;
import androidx.wear.watchface.complications.data.PlainComplicationText;
import androidx.wear.watchface.complications.data.RangedValueComplicationData;
import androidx.wear.watchface.complications.datasource.ComplicationDataSourceService;
import androidx.wear.watchface.complications.datasource.ComplicationRequest;

import dev.dect.wear.recentsshortcut.R;
import dev.dect.wear.recentsshortcut.utils.Utils;

public class RecentsProgress extends ComplicationDataSourceService {
    public static final String TAG = RecentsProgress.class.getSimpleName();

    @Nullable
    @Override
    public ComplicationData getPreviewData(@NonNull ComplicationType complicationType) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && complicationType.equals(ComplicationType.GOAL_PROGRESS)) {
            return goalProgress();
        }

        return rangedValue();
    }

    @Override
    public void onComplicationRequest(@NonNull ComplicationRequest complicationRequest, @NonNull ComplicationRequestListener complicationRequestListener) {
        try {
            if(complicationRequest.getComplicationType().equals(ComplicationType.RANGED_VALUE)) {
                complicationRequestListener.onComplicationData(rangedValue());
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && complicationRequest.getComplicationType().equals(ComplicationType.GOAL_PROGRESS)) {
                complicationRequestListener.onComplicationData(goalProgress());
            }
        } catch (RemoteException e) {
            Log.e(TAG, "onComplicationRequest: " + e.getMessage() );
        }
    }

    private RangedValueComplicationData rangedValue() {
        final RangedValueComplicationData.Builder builder = new RangedValueComplicationData.Builder(
            1,
            0,
            1,
            new PlainComplicationText.Builder(getString(R.string.complication_recents_progress)).build()
        );

        builder.setText(new PlainComplicationText.Builder(getString(R.string.complication_recents)).build());

        final Icon icon = Icon.createWithResource(this, R.drawable.icon_recents);

        builder.setMonochromaticImage(new MonochromaticImage(icon, icon));

        builder.setTapAction(Utils.getPendingIntent(this));

        return builder.build();
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    private GoalProgressComplicationData goalProgress() {
        final GoalProgressComplicationData.Builder builder = new GoalProgressComplicationData.Builder(
            1,
            1,
            new PlainComplicationText.Builder(getString(R.string.complication_recents_progress)).build()
        );

        builder.setText(new PlainComplicationText.Builder(getString(R.string.complication_recents)).build());

        final Icon icon = Icon.createWithResource(this, R.drawable.icon_recents);

        builder.setMonochromaticImage(new MonochromaticImage(icon, icon));

        builder.setTapAction(Utils.getPendingIntent(this));

        return builder.build();
    }
}
