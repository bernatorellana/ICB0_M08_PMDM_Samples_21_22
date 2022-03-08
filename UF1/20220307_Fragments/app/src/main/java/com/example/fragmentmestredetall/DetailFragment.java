package com.example.fragmentmestredetall;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.fragmentmestredetall.databinding.FragmentDetailBinding;


public class DetailFragment extends Fragment {

    private FragmentDetailBinding binding;
    private int mItemId;

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


        binding.textviewSecond.setText(">"+mItemId);

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