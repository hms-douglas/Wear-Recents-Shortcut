package dev.dect.wear.recentsshortcut.complication;

import android.graphics.drawable.Icon;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.wear.watchface.complications.data.ComplicationData;
import androidx.wear.watchface.complications.data.ComplicationType;
import androidx.wear.watchface.complications.data.MonochromaticImage;
import androidx.wear.watchface.complications.data.MonochromaticImageComplicationData;
import androidx.wear.watchface.complications.data.PlainComplicationText;
import androidx.wear.watchface.complications.datasource.ComplicationDataSourceService;
import androidx.wear.watchface.complications.datasource.ComplicationRequest;

import dev.dect.wear.recentsshortcut.R;
import dev.dect.wear.recentsshortcut.utils.Utils;

public class RecentsIcon extends ComplicationDataSourceService {
    public static final String TAG = RecentsIcon.class.getSimpleName();

    @Nullable
    @Override
    public ComplicationData getPreviewData(@NonNull ComplicationType complicationType) {
        return icon();
    }

    @Override
    public void onComplicationRequest(@NonNull ComplicationRequest complicationRequest, @NonNull ComplicationRequestListener complicationRequestListener) {
        try {
            if(complicationRequest.getComplicationType().equals(ComplicationType.MONOCHROMATIC_IMAGE)) {
                complicationRequestListener.onComplicationData(icon());
            }
        } catch (RemoteException e) {
            Log.e(TAG, "onComplicationRequest: " + e.getMessage() );
        }
    }

    private MonochromaticImageComplicationData icon() {
        final Icon icon = Icon.createWithResource(this, R.drawable.icon_recents);

        final MonochromaticImageComplicationData.Builder builder = new MonochromaticImageComplicationData.Builder(
            new MonochromaticImage(icon, icon),
            new PlainComplicationText.Builder(getString(R.string.complication_recents_icon)).build()
        );

        builder.setTapAction(Utils.getPendingIntent(this));

        return builder.build();
    }
}
