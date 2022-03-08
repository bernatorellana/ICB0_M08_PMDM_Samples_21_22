package com.example.fragmentmestredetall;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.fragmentmestredetall.adapters.ListAdapter;
import com.example.fragmentmestredetall.databinding.FragmentListBinding;


public class ListFragment extends Fragment implements ListAdapter.OnListClick {

    private FragmentListBinding binding;

    private View fragment;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentListBinding.inflate(inflater, container, false);


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
        ListAdapter adapter = new ListAdapter(this);
        binding.rcyLlista.setAdapter(adapter);
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