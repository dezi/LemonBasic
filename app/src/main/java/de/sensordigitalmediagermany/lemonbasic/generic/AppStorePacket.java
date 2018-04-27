package de.sensordigitalmediagermany.lemonbasic.generic;

import java.util.ArrayList;

public class AppStorePacket
{
    private final static ArrayList<AppStorePacket> APP_STORE_PACKETS = new ArrayList<>();

    public static ArrayList<AppStorePacket> getAppStorePackets()
    {
        return APP_STORE_PACKETS;
    }

    public static void addAppStorePackets(AppStorePacket packet)
    {
        APP_STORE_PACKETS.add(packet);
    }

    public static void  clearAppStorePackets()
    {
        APP_STORE_PACKETS.clear();
    }

    public String productId;
    public int coins;
    public int cents;

    public  AppStorePacket(String productId, int coins, int cents)
    {
        this.productId = productId;
        this.coins = coins;
        this.cents = cents;
    }
}
