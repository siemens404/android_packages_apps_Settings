package com.google.android.settings.aware;

import android.content.Context;
import com.android.settings.aware.AwareFeatureProvider;
import com.android.settings.overlay.FeatureFactory;
import com.android.settings.widget.PreferenceCategoryController;

public class AwarePreferenceCategoryController extends PreferenceCategoryController {
    private final AwareFeatureProvider mFeatureProvider;

    public AwarePreferenceCategoryController(Context context, String str) {
        super(context, str);
        this.mFeatureProvider = FeatureFactory.getFeatureFactory().getAwareFeatureProvider();
    }

    public int getAvailabilityStatus() {
        return this.mFeatureProvider.isSupported(this.mContext) ? 0 : 3;
    }
}
