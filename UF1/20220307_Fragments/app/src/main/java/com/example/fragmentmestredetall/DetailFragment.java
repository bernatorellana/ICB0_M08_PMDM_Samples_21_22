package com.example.fragmentmestredetall;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fragmentmestredetall.adapters.ListAdapter;
import com.example.fragmentmestredetall.databinding.FragmentDetailBinding;
import com.example.fragmentmestredetall.network.Pokemon;
import com.example.fragmentmestredetall.viewmodel.PokemonViewModel;

import java.util.List;


public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private int mItemId;
    PokemonViewModel viewmodel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Aquí rebem els paràmetres de l'acció que ens ha disparat a dins de "Arguments"
        if(getArguments()!=null) {
            mItemId = getArguments().getInt("item_id", -1);
            Log.d("Bernat", "" + mItemId);
        }

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentDetailBinding.inflate(inflater, container, false);

        //-------------------------------------------------------------
        // Recupero el ViewModel de l'Activity
        viewmodel = new ViewModelProvider(this).get(PokemonViewModel.class);
        viewmodel.getPokemons().observe(DetailFragment.this.getViewLifecycleOwner(), new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                binding.textviewSecond.setText(">"+pokemons.get(mItemId).getName());
            }
        });

        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if( binding.buttonSecond!=null) {
            binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NavHostFragment.findNavController(DetailFragment.this)
                            .navigate(R.id.action_SecondFragment_to_FirstFragment);
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}