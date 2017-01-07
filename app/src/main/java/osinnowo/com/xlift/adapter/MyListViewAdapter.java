package osinnowo.com.xlift.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import osinnowo.com.xlift.R;
import osinnowo.com.xlift.model.Person;

/**
 * Created by upperlink on 06/01/2017.
 */

public class MyListViewAdapter extends BaseAdapter {
    @BindView(R.id.FirstNameTxt) TextView FirstNameTxt;
    private ArrayList<Person> mList ;
    private Context mContext ;

    public MyListViewAdapter (Context mCtxt, ArrayList<Person> mList) {
        this.mList = mList;
        this.mContext = mCtxt;
    }

    @Override
    public int getCount() {
        return this.mList.size();
    }

    @Override
    public Person getItem(int position) {
        return this.mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(row == null){
            row = LayoutInflater.from(this.mContext).inflate(R.layout.listview_row, null, false);
        }

        ButterKnife.bind(this, row);

        FirstNameTxt.setText(this.mList.get(position).getFirstName());

        return row;
    }
}
