package com.yousefagawin.codingchallengeapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yousefagawin.codingchallengeapp.R;
import com.yousefagawin.codingchallengeapp.datamodels.SongEntity;

import java.util.ArrayList;

public class SongsRVAdapter extends RecyclerView.Adapter <SongsRVAdapter.TransactionsViewHolder>{

    private Context context;
    private ArrayList<SongEntity> SongsList;
    private OnItemClickListener mListener;


    public interface OnItemClickListener{
        void onItemClick(SongEntity song);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public class TransactionsViewHolder extends RecyclerView.ViewHolder{

        public ImageView image_iv;
        public TextView trackName_tv;
        public TextView trackPrice_tv;
        public TextView genre_tv;

        public TransactionsViewHolder(View itemView, final OnItemClickListener listener){
            super(itemView);

            image_iv = itemView.findViewById(R.id.image_iv);
            trackName_tv = itemView.findViewById(R.id.trackName_tv);
            trackPrice_tv = itemView.findViewById(R.id.trackPrice_tv);
            genre_tv = itemView.findViewById(R.id.genre_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(SongsList.get(position));
                    }
                }
            });
        }
    }

    public SongsRVAdapter(Context ctx, ArrayList<SongEntity> songsList){
        context = ctx;
        SongsList = songsList;
    }

    @Override
    public TransactionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.songs_cv, parent, false);
        TransactionsViewHolder evh = new TransactionsViewHolder(v, mListener);

        return evh;
    }

    @Override
    public void onBindViewHolder(final TransactionsViewHolder holder, final int holderPosition) {

        final SongEntity currentItem = SongsList.get(holderPosition);


        //this will hide the texts if some fields are null
        if(currentItem.getTrackName()== null){
            holder.trackName_tv.setVisibility(View.INVISIBLE);
        }
        else{
            holder.trackName_tv.setText(currentItem.getTrackName());
        }


        if(currentItem.getTrackPrice()== null){
            holder.trackPrice_tv.setVisibility(View.INVISIBLE);
        }
        else{
            holder.trackPrice_tv.setText("$" + currentItem.getTrackPrice());
        }


        if(currentItem.getPrimaryGenreName() == null){
            holder.genre_tv.setVisibility(View.INVISIBLE);
        }
        else{
            holder.genre_tv.setText(currentItem.getPrimaryGenreName());
        }



        Glide.with(context).load(SongsList.get(holderPosition).getArtworkUrl100())
                //this will handle the issue if image is not loaded
                .apply(new RequestOptions().placeholder(R.drawable.no_image_available).error(R.drawable.no_image_available))
                .into(holder.image_iv);

    }

    @Override
    public int getItemCount() {
        return SongsList.size();
    }

//    //this is used to get all the data from the array
//    public ArrayList<HotelDetail> getAllData() {
//        return HotelsList;
//    }
}
