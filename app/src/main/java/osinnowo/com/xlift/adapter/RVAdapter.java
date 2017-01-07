package osinnowo.com.xlift.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import osinnowo.com.xlift.R;
import osinnowo.com.xlift.model.EtaEstimate;

/**
 * Created by upperlink on 06/01/2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder>{

    private ArrayList<EtaEstimate> mList ;
    private Context mContext ;

    public RVAdapter (Context mCtxt, ArrayList<EtaEstimate> mPeople) {
        this.mList = mPeople;
        this.mContext = mCtxt;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(this.mContext).inflate(R.layout.cardview, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.personName.setText(mList.get(position).getDisplayName());
        holder.personAge.setText(Integer.toString(mList.get(position).getEtaSeconds()) + " secs");
        //holder.personPhoto.setImageResource();
    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder  {

        @BindView(R.id.cv) CardView cv;
        @BindView(R.id.person_name) TextView personName;
        @BindView(R.id.person_age) TextView personAge;
        @BindView(R.id.person_photo) ImageView personPhoto;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
