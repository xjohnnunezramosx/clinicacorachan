package com.example.clinicacorachan.ui.citas;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clinicacorachan.R;

import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder>  {

    private List<Doctor> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    int selected_position = 0;

    public DoctorAdapter(Context context, List<Doctor> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_doctor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Doctor doctor = mData.get(position);
        holder.nameDoctor.setText(doctor.nameDoctor);
        holder.cmpDoctor.setText(doctor.cmpDoctor);
        holder.rating.setRating(doctor.ratingDoctor);

        holder.itemView.setBackgroundColor(selected_position == position ? Color.GREEN : Color.TRANSPARENT);
        if(doctor.genderDoctor){
            holder.imgDoctor.setImageDrawable(ResourcesCompat.getDrawable(holder.imgDoctor.getResources(), R.drawable.girl, null));
        } else {
            holder.imgDoctor.setImageDrawable(ResourcesCompat.getDrawable(holder.imgDoctor.getResources(), R.drawable.boy, null));
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameDoctor;
        TextView cmpDoctor;
        ImageView imgDoctor;
        RatingBar rating;


        ViewHolder(View itemView) {
            super(itemView);
            nameDoctor = itemView.findViewById(R.id.tvNameDoctor);
            cmpDoctor = itemView.findViewById(R.id.tvCMP);
            imgDoctor = itemView.findViewById(R.id.imgDoctor);
            rating = itemView.findViewById(R.id.rating);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;

            // Updating old as well as new positions
            notifyItemChanged(selected_position);
            selected_position = getAdapterPosition();
            notifyItemChanged(selected_position);

            if (mClickListener != null) mClickListener.onItemClickDoctor(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Doctor getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClickDoctor(View view, int position);
    }
}
