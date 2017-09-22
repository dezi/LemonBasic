package de.sensordigitalmediagermany.lemontrainer.raineralbers;

public class AppStorePacket
{
    private static final AppStorePacket[] APP_STORE_PACKEKTS =
            {
                    new AppStorePacket( 200, 1990),
                    new AppStorePacket( 500, 4590),
                    new AppStorePacket(1000, 8990)
            };

    public static AppStorePacket[] getAppStorePackets()
    {
        return APP_STORE_PACKEKTS;
    }

    public int coins;
    public int cents;

    public  AppStorePacket(int coins, int cents)
    {
        this.coins = coins;
        this.cents = cents;
    }
}
