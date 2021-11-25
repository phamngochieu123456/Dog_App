package com.midterm.dogapp2;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.midterm.dogapp2.model.DogBreed;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogAdapter.ViewHolder> implements Filterable
{
    private ArrayList<DogBreed> dogBreeds;
    private ArrayList<DogBreed> dogBreedsAll;
    private IClickListener iClickListener;
    public DogAdapter(ArrayList<DogBreed> dogBreeds, IClickListener iClickListener){
        this.dogBreeds = dogBreeds;
        dogBreedsAll = new ArrayList<DogBreed>(dogBreeds);
        this.iClickListener = iClickListener;
    }

    @NonNull
    @Override
    public DogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DogAdapter.ViewHolder holder, int position) {
        Picasso.get().load(dogBreeds.get(position).getUrl()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                holder.ivDogImage.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
        holder.tvDogName.setText(dogBreeds.get(position).getName());
        holder.tvDogDetail.setText(dogBreeds.get(position).getBredFor());
        final DogBreed dog = dogBreeds.get(position);
        holder.cdDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickListener.onItemClicked(dog);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dogBreeds.size();
    }

    @Override
    public Filter getFilter() {
        return dogFilter;
    }

    private Filter dogFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<DogBreed> filteredList = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(dogBreedsAll);
            }
            else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for (DogBreed dog : dogBreedsAll){
                    if (dog.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(dog);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            dogBreeds.clear();
            dogBreeds.addAll((List)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivDogImage;
        public TextView tvDogName;
        public TextView tvDogDetail;
        public CardView cdDog;

        public ViewHolder(View view){
            super(view);
            ivDogImage = view.findViewById(R.id.iv_DogImage);
            tvDogName = view.findViewById(R.id.tv_DogName);
            tvDogDetail = view.findViewById(R.id.tv_DogDetail);
            cdDog = view.findViewById(R.id.cdDog);
        }
    }

}
