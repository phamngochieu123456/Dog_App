package com.midterm.dogapp2.view;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.midterm.dogapp2.R;
import com.midterm.dogapp2.model.DogBreed;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class DetailFragment extends Fragment {

    private DogBreed dog;
    private ImageView imageView_details;
    private EditText editText_name;
    private EditText editText_breedfor;
    private EditText editText_breedgroup;
    private EditText editText_lifespan;
    private EditText editText_origin;
    private EditText editText_temperament;
    private EditText editText_height;
    private EditText editText_weight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dog = (DogBreed) getArguments().getSerializable("D");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView_details = view.findViewById(R.id.iv_dog_details);
        editText_name = view.findViewById(R.id.ev_name);
        editText_breedfor = view.findViewById(R.id.ev_breedfor);
        editText_lifespan = view.findViewById(R.id.ev_lifespan);
        editText_origin = view.findViewById(R.id.ev_origin);
        editText_temperament = view.findViewById(R.id.ev_temperament);
        editText_height = view.findViewById(R.id.ev_height);
        editText_weight = view.findViewById(R.id.ev_weight);
        editText_breedgroup = view.findViewById(R.id.ev_breedgroup);

        editText_name.setText(dog.getName());
        editText_breedfor.setText(dog.getBredFor());
        editText_weight.setText(dog.getWeight().toString());
        editText_height.setText(dog.getHeight().toString());
        editText_temperament.setText(dog.getTemperament());
        editText_origin.setText(dog.getOrigin());
        editText_breedgroup.setText(dog.getBreedgroup());
        editText_lifespan.setText(dog.getLifeSpan());

        Picasso.get().load(dog.getUrl()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                imageView_details.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });


    }
}