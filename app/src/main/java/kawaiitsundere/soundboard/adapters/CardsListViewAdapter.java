package kawaiitsundere.soundboard.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kawaiitsundere.soundboard.R;

public class CardsListViewAdapter extends RecyclerView.Adapter<CardsListViewAdapter.ViewHolder> {

    //Data will get passed on to the adapter by the main activity
    private List<String> mDataAuthors;
    private List<String> mDataAudios;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    //pass the data into the constructor
    public CardsListViewAdapter(Context context, List<String> authordata, List<String> audiodata){
        this.mInflater = LayoutInflater.from(context);
        this.mDataAuthors = authordata;
        this.mDataAudios = audiodata;
    }

    //inflate the row layout from xml
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.card_row, parent, false);
        return new ViewHolder(view);
    }


    //bind the data to the cardview in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //change what happens with the initialized row items here
        holder.audioCard.setCardBackgroundColor(holder.itemView.getContext().getResources().getColor(R.color.background_fucktard_deluxe));
        holder.audioAuthorTV.setText(mDataAuthors.get(position));
        holder.audioNameTV.setText(mDataAudios.get(position));
    }

    //total number of cards that will be shown
    @Override
    public int getItemCount(){
        return mDataAuthors.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //Initialize row items here
        CardView audioCard;
        TextView audioAuthorTV;
        TextView audioNameTV;

        ViewHolder(View itemView){
            super(itemView);
            //find the row items from the row layout here
            audioCard = itemView.findViewById(R.id.audioCard);
            audioAuthorTV = itemView.findViewById(R.id.audioAuthorText);
            audioNameTV = itemView.findViewById(R.id.audioNameText);
            audioCard.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            if (mClickListener !=null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    //convenience method for getting data at the click position
    String getItem(int id){
        return mDataAuthors.get(id);
    }

    //allows click events to be caught
    public void setClickListener(ItemClickListener itemClickListener){
        this.mClickListener = itemClickListener;
    }

    //the main activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
