package de.sensordigitalmediagermany.lemonbasic.purchase;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class IABSkuDetails
{
    private static final String LOGTAG = IABSkuDetails.class.getSimpleName();

    private final String jsonStr;

    private final String productId;
    private final String type;
    private final String title;
    private final String description;
    private final String price;
    private final String priceCurrencyCode;

    private final long priceAmountMicros;

    public IABSkuDetails(String jsonSkuDetails) throws JSONException
    {
        jsonStr = jsonSkuDetails;

        JSONObject jsonObj = new JSONObject(jsonStr);

        productId = jsonObj.optString("productId");

        type = jsonObj.optString("type");
        title = jsonObj.optString("title");
        description = jsonObj.optString("description");

        price = jsonObj.optString("price");
        priceCurrencyCode = jsonObj.optString("price_currency_code");
        priceAmountMicros = jsonObj.optLong("price_amount_micros");

        Log.d(LOGTAG, "SkuDetails: " + jsonObj.toString(2));
    }

    public String getSku()
    {
        return productId;
    }

    public String getType()
    {
        return type;
    }

    public String getPrice()
    {
        return price;
    }

    public long getPriceAmountMicros()
    {
        return priceAmountMicros;
    }

    public String getPriceCurrencyCode()
    {
        return priceCurrencyCode;
    }

    public String getTitle()
    {
        return title;
    }

    public String getDescription()
    {
        return description;
    }

    @Override
    public String toString()
    {
        return "SkuDetails:" + jsonStr;
    }
}
