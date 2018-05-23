package de.sensordigitalmediagermany.lemonbasic.generic;

import android.content.Context;
import android.view.View;
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

        final TabBarItem homeTab = new TabBarItem(context, Defines.TABBAR_HEIGHT);
        homeTab.setContent(R.drawable.lem_t_iany_generic_tabbar_icon_home, R.string.tabbar_home);

        ApplicationBase.handler.post(new Runnable()
        {
            @Override
            public void run()
            {
                if (ApplicationBase.getCurrentActivity(getContext()) instanceof ContentActivity)
                {
                    homeTab.tintContent(Defines.COLOR_TABBAR_ACT_ICON, Defines.COLOR_TABBAR_ACT_TEXT);
                }
            }
        });

        homeTab.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!(ApplicationBase.getCurrentActivity(getContext()) instanceof ContentActivity))
                {
                    Simple.startActivityTop(getContext(), ContentActivity.class);
                }
            }
        });

        addView(homeTab);

        final TabBarItem profileTab = new TabBarItem(context, Defines.TABBAR_HEIGHT);
        profileTab.setContent(R.drawable.lem_t_iany_generic_tabbar_icon_profile, R.string.tabbar_profile);

        ApplicationBase.handler.post(new Runnable()
        {
            @Override
            public void run()
            {
                if (ApplicationBase.getCurrentActivity(getContext()) instanceof SettingsActivity)
                {
                    profileTab.tintContent(Defines.COLOR_TABBAR_ACT_ICON, Defines.COLOR_TABBAR_ACT_TEXT);
                }
            }
        });

        profileTab.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!(ApplicationBase.getCurrentActivity(getContext()) instanceof SettingsActivity))
                {
                    Simple.startActivity(getContext(), SettingsActivity.class);
                }
            }
        });

        addView(profileTab);

        TabBarItem certsTab = new TabBarItem(context, Defines.TABBAR_HEIGHT);
        certsTab.setContent(R.drawable.lem_t_iany_generic_tabbar_icon_certs, R.string.tabbar_certs);

        certsTab.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            }
        });

        addView(certsTab);

        TabBarItem chatTab = new TabBarItem(context, Defines.TABBAR_HEIGHT);
        chatTab.setContent(R.drawable.lem_t_iany_generic_tabbar_icon_chat, R.string.tabbar_chat);

        chatTab.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            }
        });

        addView(chatTab);
    }
}
