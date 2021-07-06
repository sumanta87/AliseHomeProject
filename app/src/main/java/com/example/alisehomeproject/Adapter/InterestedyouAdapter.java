package com.example.alisehomeproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alisehomeproject.R;


public class InterestedyouAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mcontext;
    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_EMPTY = 1;

    public InterestedyouAdapter(Context mcontext) {
        this.mcontext = mcontext;

    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        switch (viewType) {
            case VIEW_TYPE_ITEM:
                view = inflater.inflate(R.layout.likeyou,
                        viewGroup, false);
                ManualandCriticalAssignmentViewholder manualandCriticalAssignmentViewholder = new ManualandCriticalAssignmentViewholder(view);
                return manualandCriticalAssignmentViewholder;

            default:
                view = inflater.inflate(R.layout.no_item_layout,
                        viewGroup, false);
               EmptyListViewHolder emptyListViewHolder = new EmptyListViewHolder(view);
                return emptyListViewHolder;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

    }
    @Override
    public int getItemViewType(int position) {
            return VIEW_TYPE_ITEM;
    }


//    public void setBookingList(List<Booking> assignmentneedhelp){
//        this.assignmentneedhelp = assignmentneedhelp;
//        notifyDataSetChanged();
//    }

    @Override
    public int getItemCount() {
       // int assignmentneed = assignmentneedhelp.size();
        return 2;
    }


    public class EmptyListViewHolder extends RecyclerView.ViewHolder {

        TextView emptyListMessage;

        public EmptyListViewHolder(View itemView) {
            super(itemView);
            emptyListMessage = (TextView) itemView.findViewById(R.id.empty_message);
        }
    }

    public class ManualandCriticalAssignmentViewholder extends RecyclerView.ViewHolder {
        public  ManualandCriticalAssignmentViewholder (@NonNull View itemView) {
            super(itemView);

        }
    }


}
