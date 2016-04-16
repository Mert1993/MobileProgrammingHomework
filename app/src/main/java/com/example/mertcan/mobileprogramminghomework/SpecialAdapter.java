package com.example.mertcan.mobileprogramminghomework;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mert Can on 13.04.2016.
 */
public class SpecialAdapter extends BaseAdapter
{

    private LayoutInflater layoutInflater;
    private List<Person> personList;

    public SpecialAdapter(Activity activity, List<Person> personList)
    {
        //Take the XML and convert to View by Inflater
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // take the person list to be showed
        this.personList = personList;
    }

    @Override
    public int getCount()
    {
        return personList.size();
    }

    @Override
    public Person getItem(int position)
    {
        //şöyle de olabilir: public Object getItem(int position)
        return personList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View customView;

        customView = layoutInflater.inflate(R.layout.custom_listview_design, null);
        TextView textViewNameSurname = (TextView) customView.findViewById(R.id.nameSurname);
        TextView textViewPhoneNumber = (TextView) customView.findViewById(R.id.phoneNumber);
        ImageView imageView = (ImageView) customView.findViewById(R.id.personPhoto);

        Person person = personList.get(position);

        textViewNameSurname.setText(person.getNameSurname());
        textViewPhoneNumber.setText(person.getPhoneNumber());


        imageView.setImageResource(R.drawable.person);

        return customView;
    }
}
