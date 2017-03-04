package br.com.oliversys.babetteunhas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pedepizza.oliversoft.com.br.babetteunhas.R;

public class NavDrawerListAdapter extends ArrayAdapter {

    private Context context;
    private List<NavDrawerItem> items;

    public NavDrawerListAdapter(Context context, List<NavDrawerItem> navDrawerItems){
        super(context, R.layout.drawer_row, navDrawerItems);
        this.context = context;
        this.items = navDrawerItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(R.layout.drawer_row, null);
        }

        NavDrawerItem item = (NavDrawerItem) items.get(position);

        if(item != null) {
            ImageView imgIcon = (ImageView) convertView.findViewById(R.id.rowIcon);
            TextView txtTitle = (TextView) convertView.findViewById(R.id.rowText);

            imgIcon.setImageResource(item.getIcon());
            txtTitle.setText(item.getTitle());
        }

        return convertView;
    }

}