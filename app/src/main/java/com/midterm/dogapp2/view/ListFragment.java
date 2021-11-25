package com.midterm.dogapp2.view;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;
import com.midterm.dogapp2.DogAdapter;
import com.midterm.dogapp2.IClickListener;
import com.midterm.dogapp2.R;
import com.midterm.dogapp2.model.DogBreed;
import com.midterm.dogapp2.viewmodel.DogApiService;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListFragment extends Fragment {

    private DogApiService apiService;
    private TextView tv;
    private ArrayList<DogBreed> dogBreedsList;
    private DogAdapter dogAdapter;
    private RecyclerView DogRV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tv = view.findViewById(R.id.textView);

        GridLayoutManager layoutManager =
                new GridLayoutManager(view.getContext(), 2, GridLayoutManager.VERTICAL, false);

        DogRV = view.findViewById(R.id.dogrv);
        DogRV.setLayoutManager(layoutManager);
        dogBreedsList = new ArrayList<DogBreed>();

        apiService = new DogApiService();
        apiService.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<DogBreed> dogBreeds) {
                        Log.d("DEBUG","Success");
                        dogBreedsList.clear();
                        dogBreedsList.addAll(dogBreeds);
                        dogAdapter = new DogAdapter(dogBreedsList, new IClickListener() {
                            @Override
                            public void onItemClicked(DogBreed dogBreed) {
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("D", dogBreed);
                                Navigation.findNavController(view).navigate(R.id.detailFragment, bundle);
                            }
                        });
                        DogRV.setAdapter(dogAdapter);
                        dogAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d("DEBUG","Fail:"+e.getMessage());
                    }
                });

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                tv.setText("Move");
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                tv.setText("Right");
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(DogRV);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                dogAdapter.getFilter().filter(s);
                return true;
            }
        });
    }
}