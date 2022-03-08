package com.example.fragmentmestredetall;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.fragmentmestredetall.adapters.ListAdapter;
import com.example.fragmentmestredetall.databinding.FragmentListBinding;
import com.example.fragmentmestredetall.network.Pokemon;
import com.example.fragmentmestredetall.viewmodel.PokemonViewModel;

import java.util.List;


public class ListFragment extends Fragment implements ListAdapter.OnListClick {

    private FragmentListBinding binding;
    private PokemonViewModel viewmodel;

    private View fragment;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        //------------------------------------------------------------
        // Carreguem el Binding
        binding = FragmentListBinding.inflate(inflater, container, false);
        //-------------------------------------------------------------
        // Recupero el ViewModel del fragment
        viewmodel = new ViewModelProvider(this).get(PokemonViewModel.class);
        viewmodel.getPokemons().observe(ListFragment.this.getViewLifecycleOwner(), new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                ListAdapter adapter = new ListAdapter(ListFragment.this, pokemons);
                Log.d("Bernat", "pokemons dowloaded:"+pokemons.size());
                binding.rcyLlista.setAdapter(adapter);
            }
        });

        //-------------------------------------------------------------
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fragment =  binding.getRoot().findViewById(R.id.nav_host_fragment_content_main);


        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle args = new Bundle();
                args.putInt("item_id", 3);
                NavHostFragment.findNavController(ListFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment,args);
            }
        });
        //-----------------------------------
        // Programem el recycler view
        binding.rcyLlista.setLayoutManager(
                new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemListClicked(int position) {
        Bundle args = new Bundle();
        args.putInt("item_id", position);


        if(fragment==null)  {
            // estic en vertical, i senzillament li dic la navegació que faci la feina

            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_FirstFragment_to_SecondFragment,args);
        } else {
            // estic en horitzontal, llavors hem de carregar el fragment al
            //  contenidor <fragment>

            Navigation.findNavController(fragment)
                    .navigate(R.id.DetailFragmentSub, args);
        }



    }
}