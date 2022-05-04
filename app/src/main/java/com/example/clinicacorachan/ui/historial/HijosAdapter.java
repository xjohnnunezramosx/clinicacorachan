package com.example.clinicacorachan.ui.historial;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clinicacorachan.R;
import com.example.clinicacorachan.ui.citas.Doctor;

import java.util.List;

public class HijosAdapter extends RecyclerView.Adapter<HijosAdapter.ViewHolder>  {

    private List<Hijos> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    int selected_position = 0;

    public HijosAdapter(Context context, List<Hijos> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_sons, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hijos doctor = mData.get(position);
        holder.name.setText(doctor.name);
        holder.edad.setText(doctor.age + " años");

        holder.itemView.setBackgroundColor(selected_position == position ? Color.GREEN : Color.TRANSPARENT);
        if(doctor.gender.equals("F")){
            holder.img.setImageDrawable(ResourcesCompat.getDrawable(holder.img.getResources(), R.drawable.girl, null));
        } else {
            holder.img.setImageDrawable(ResourcesCompat.getDrawable(holder.img.getResources(), R.drawable.boy, null));
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView edad;
        ImageView img;


        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtNombre);
            edad = itemView.findViewById(R.id.txtEdad);
            img = itemView.findViewById(R.id.iconProfile);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;

            // Updating old as well as new positions
            notifyItemChanged(selected_position);
            selected_position = getAdapterPosition();
            notifyItemChanged(selected_position);

            if (mClickListener != null) mClickListener.onItemClickHijo(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Hijos getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClickHijo(View view, int position);
    }
}
