package dev.dect.wear.recentsshortcut.complication;

import android.graphics.drawable.Icon;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.wear.watchface.complications.data.ComplicationData;
import androidx.wear.watchface.complications.data.ComplicationType;
import androidx.wear.watchface.complications.data.LongTextComplicationData;
import androidx.wear.watchface.complications.data.MonochromaticImage;
import androidx.wear.watchface.complications.data.PlainComplicationText;
import androidx.wear.watchface.complications.data.ShortTextComplicationData;
import androidx.wear.watchface.complications.datasource.ComplicationDataSourceService;
import androidx.wear.watchface.complications.datasource.ComplicationRequest;

import dev.dect.wear.recentsshortcut.R;
import dev.dect.wear.recentsshortcut.utils.Utils;

public class RecentsText extends ComplicationDataSourceService {
    public static final String TAG = RecentsText.class.getSimpleName();

    @Nullable
    @Override
    public ComplicationData getPreviewData(@NonNull ComplicationType complicationType) {
        if(complicationType.equals(ComplicationType.SHORT_TEXT)) {
            return shortText();
        }

        return longText();
    }

    @Override
    public void onComplicationRequest(@NonNull ComplicationRequest complicationRequest, @NonNull ComplicationRequestListener complicationRequestListener) {
        try {
            if(complicationRequest.getComplicationType().equals(ComplicationType.SHORT_TEXT)) {
                complicationRequestListener.onComplicationData(shortText());
            } else if(complicationRequest.getComplicationType().equals(ComplicationType.LONG_TEXT)) {
                complicationRequestListener.onComplicationData(longText());
            }
        } catch (RemoteException e) {
            Log.e(TAG, "onComplicationRequest: " + e.getMessage() );
        }
    }

    private ShortTextComplicationData shortText() {
        final ShortTextComplicationData.Builder builder = new ShortTextComplicationData.Builder(
            new PlainComplicationText.Builder(getString(R.string.complication_recents)).build(),
            new PlainComplicationText.Builder(getString(R.string.complication_recents_text)).build()
        );

        final Icon icon = Icon.createWithResource(this, R.drawable.icon_recents);

        builder.setMonochromaticImage(new MonochromaticImage(icon, icon));

        builder.setTapAction(Utils.getPendingIntent(this));

        return builder.build();
    }

    private LongTextComplicationData longText() {
        final LongTextComplicationData.Builder builder = new LongTextComplicationData.Builder(
            new PlainComplicationText.Builder(getString(R.string.complication_recents)).build(),
            new PlainComplicationText.Builder(getString(R.string.complication_recents_text)).build()
        );

        final Icon icon = Icon.createWithResource(this, R.drawable.icon_recents);

        builder.setMonochromaticImage(new MonochromaticImage(icon, icon));

        builder.setTapAction(Utils.getPendingIntent(this));

        return builder.build();
    }
}
