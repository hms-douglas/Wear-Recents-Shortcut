package dev.dect.wear.recentsshortcut.utils;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Toast;

import java.util.UUID;

import dev.dect.wear.recentsshortcut.R;

@SuppressLint("WearRecents")
public class Utils {
    public static void launchRecents(Context ctx) {
        final Intent i = new Intent("com.google.android.wearable.sysui");

        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        i.setComponent(new ComponentName("com.google.android.wearable.sysui", "com.google.android.clockwork.sysui.experiences.globallauncher.RecentsActivity"));

        try {
            ctx.startActivity(i);
        } catch (Exception ignore) {
            launchSamsungRecents(ctx);
        }
    }

    private static void launchSamsungRecents(Context ctx) {
        try {
            final PackageManager packageManager = ctx.getPackageManager();

            ctx.startActivity(
                packageManager.getLaunchIntentForPackage("com.samsung.android.apps.wearable.recent")
            );
        } catch (Exception ignore) {
            Toast.makeText(ctx, ctx.getString(R.string.toast_error_launch), Toast.LENGTH_SHORT).show();
        }
    }

    public static PendingIntent getPendingIntent(Context ctx) {
        final Intent i = new Intent("com.google.android.wearable.sysui");

        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        i.setComponent(new ComponentName("com.google.android.wearable.sysui", "com.google.android.clockwork.sysui.experiences.globallauncher.RecentsActivity"));

        return PendingIntent.getActivity(ctx, UUID.randomUUID().hashCode(), i, PendingIntent.FLAG_IMMUTABLE);
    }
 }
