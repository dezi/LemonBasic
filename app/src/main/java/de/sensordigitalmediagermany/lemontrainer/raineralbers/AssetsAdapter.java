package de.sensordigitalmediagermany.lemontrainer.raineralbers;

import android.graphics.Color;
import android.widget.BaseAdapter;
import android.view.ViewGroup;
import android.view.View;
import android.widget.LinearLayout;

public class AssetsAdapter extends BaseAdapter
{
    @Override
    public int getCount()
    {
        return 25;
    }

    @Override
    public Object getItem(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LinearLayout assetFrame = new LinearLayout(parent.getContext());
        assetFrame.setOrientation(LinearLayout.VERTICAL);
        assetFrame.setBackgroundResource(R.drawable.lem_t_iany_ralbers_assetframe);
        Simple.setSizeDip(assetFrame, Simple.MP, 200);

        return assetFrame;
    }
}
