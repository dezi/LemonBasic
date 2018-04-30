package de.sensordigitalmediagermany.lemonbasic.generic;

import java.util.ArrayList;
import java.util.List;

public class AppStorePacket
{
    private final static List<String> APP_STORE_SKUS = new ArrayList<>();

    private final static List<AppStorePacket> APP_STORE_PACKETS = new ArrayList<>();

    static
    {
        APP_STORE_SKUS.add("coins0");
        APP_STORE_SKUS.add("coins1");
        APP_STORE_SKUS.add("coins2");
        APP_STORE_SKUS.add("coins3");
    }

    public static List<String> getAppStoreSkus()
    {
        return APP_STORE_SKUS;
    }

    public static List<AppStorePacket> getAppStorePackets()
    {
        return APP_STORE_PACKETS;
    }

    public static void addAppStorePackets(AppStorePacket packet)
    {
        APP_STORE_PACKETS.add(packet);
    }

    public static void clearAppStorePackets()
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
