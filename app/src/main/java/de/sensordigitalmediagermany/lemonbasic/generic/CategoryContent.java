package de.sensordigitalmediagermany.lemonbasic.generic;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.json.JSONArray;
import org.json.JSONObject;

public class CategoryContent extends LinearLayout
{
    private static final String LOGTAG = CategoryContent.class.getSimpleName();

    public CategoryContent(Context context)
    {
        super(context);

        setOrientation(LinearLayout.VERTICAL);
        Simple.setSizeDip(this, Simple.MP, Simple.WC);
    }

    public void setCategories(JSONArray categories, ViewGroup rootview)
    {
        if (categories != null)
        {
            for (int inx = 0; inx < categories.length(); inx++)
            {
                JSONObject catjson = Json.getObject(categories, inx);
                if (catjson == null) continue;

                String category = Json.getString(catjson, "name");
                int categoryCount = Json.getInt(catjson, "_count");

                Log.d(LOGTAG, "onCreate: category=" + category + " count=" + categoryCount);

                if (categoryCount == 0) continue;

                ContentSlider cs = new ContentSlider(this.getContext(), rootview);
                cs.setAssets(category, ContentHandler.getFilteredContent(false, category));

                addView(cs);
            }
        }
    }

    public void updateContent()
    {
        for (int inx = 0; inx < getChildCount(); inx++)
        {
            View child = getChildAt(inx);

            if (child instanceof ContentSlider)
            {
                ((ContentSlider) child).updateContent();
            }
        }
    }
}
