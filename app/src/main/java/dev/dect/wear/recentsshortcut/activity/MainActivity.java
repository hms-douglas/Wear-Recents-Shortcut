package dev.dect.wear.recentsshortcut.activity;

import android.app.Activity;
import android.os.Bundle;


import dev.dect.wear.recentsshortcut.utils.Utils;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Utils.launchRecents(this);

        finish();
    }
}
