package com.example.roomproj;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AstronautaListAdapter extends RecyclerView.Adapter<AstronautaListAdapter.AstronautaViewHolder> {

    class AstronautaViewHolder extends RecyclerView.ViewHolder {
        private final TextView astronautaItemView;

        private AstronautaViewHolder(View itemView) {
            super(itemView);
            astronautaItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater mInflater;
    private List<Astronauta> mAstronautas; // Cached copy of words
    private OnItemClicked onClick;

    public interface OnItemClicked {
        void onItemClick(int position);
    }

    public AstronautaListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public AstronautaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new AstronautaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AstronautaViewHolder holder, int position) {
        if (mAstronautas != null) {
            Astronauta current = mAstronautas.get(position);
            holder.astronautaItemView.setText(current.getName());
            holder.astronautaItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClick.onItemClick(position);
                }
            });
        } else {
            // Covers the case of data not being ready yet.
            holder.astronautaItemView.setText("No Astronauta");
        }
    }

    public void setOnClick(OnItemClicked onClick)
    {
        this.onClick = onClick;
    }

    public void setAstronautas(List<Astronauta> astronautas){
        mAstronautas = astronautas;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mAstronautas != null)
            return mAstronautas.size();
        else return 0;
    }
}
