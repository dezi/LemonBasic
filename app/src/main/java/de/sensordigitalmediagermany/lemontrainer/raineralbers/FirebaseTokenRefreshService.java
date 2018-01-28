package de.sensordigitalmediagermany.lemontrainer.raineralbers;

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
