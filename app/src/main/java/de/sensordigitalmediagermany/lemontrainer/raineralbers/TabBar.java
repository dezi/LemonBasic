package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.content.Context;
import android.widget.LinearLayout;

public class TabBar extends LinearLayout
{
    public TabBar(Context context)
    {
        super(context);

        setVisibility(Defines.isTabBar ? VISIBLE : GONE);

        setOrientation(HORIZONTAL);
        setBackgroundColor(Defines.COLOR_TABBAR);
        Simple.setSizeDip(this, Simple.MP, Defines.TABBAR_HEIGHT);
        Simple.setPaddingDip(this, Defines.PADDING_XLARGE, 0, Defines.PADDING_XLARGE, 0);

        TabBarItem homeTab = new TabBarItem(context, Defines.TABBAR_HEIGHT);
        homeTab.setContent(R.drawable.lem_t_iany_generic_tabbar_icon_home, R.string.tabbar_home);

        addView(homeTab);

        TabBarItem profileTab = new TabBarItem(context, Defines.TABBAR_HEIGHT);
        profileTab.setContent(R.drawable.lem_t_iany_generic_tabbar_icon_profile, R.string.tabbar_profile);

        addView(profileTab);
    }
}
