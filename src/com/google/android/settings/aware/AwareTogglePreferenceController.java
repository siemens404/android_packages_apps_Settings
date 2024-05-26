package com.google.android.settings.aware;

import android.content.Context;
import android.net.Uri;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreferenceCompat;
import com.android.settings.core.TogglePreferenceController;
import com.android.settingslib.core.lifecycle.LifecycleObserver;
import com.android.settingslib.core.lifecycle.events.OnStart;
import com.android.settingslib.core.lifecycle.events.OnStop;
import com.google.android.settings.aware.AwareHelper;

public abstract class AwareTogglePreferenceController extends TogglePreferenceController implements LifecycleObserver, OnStart, OnStop, AwareHelper.Callback {
    protected static final int OFF = 0;
    protected static final int ON = 1;
    protected final AwareHelper mHelper;
    protected SwitchPreferenceCompat mPreference;

    public AwareTogglePreferenceController(Context context, String str) {
        super(context, str);
        this.mHelper = new AwareHelper(context);
    }

    public int getAvailabilityStatus() {
        return this.mHelper.isGestureConfigurable() ? 0 : 5;
    }

    public void displayPreference(PreferenceScreen preferenceScreen) {
        super.displayPreference(preferenceScreen);
        this.mPreference = (SwitchPreferenceCompat) preferenceScreen.findPreference(getPreferenceKey());
    }

    public void updateState(Preference preference) {
        super.updateState(preference);
        refreshSummary(preference);
        int availabilityStatus = getAvailabilityStatus();
        boolean z = true;
        if (!(availabilityStatus == 0 || availabilityStatus == 1)) {
            z = false;
        }
        preference.setEnabled(z);
    }

    public void onStart() {
        this.mHelper.register(this);
    }

    public void onStop() {
        this.mHelper.unregister();
    }

    public void onChange(Uri uri) {
        updateState(this.mPreference);
    }
}
