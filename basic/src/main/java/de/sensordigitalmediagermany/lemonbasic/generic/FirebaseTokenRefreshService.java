package de.sensordigitalmediagermany.lemonbasic.generic;

import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseTokenRefreshService extends FirebaseInstanceIdService
{
    private static final String LOGTAG = FirebaseTokenRefreshService.class.getSimpleName();

    @Override
    public void onTokenRefresh()
    {
        SettingsHandler.updateSessionGCMToken();
    }
}
