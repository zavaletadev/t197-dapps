package mx.edu.uteq.dapps.demonavdrawer.pub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import mx.edu.uteq.dapps.demonavdrawer.R;
import mx.edu.uteq.dapps.demonavdrawer.databinding.FragmentCatalogoBinding;

public class CatalogoFragment extends Fragment {

    private FragmentCatalogoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCatalogoBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CatalogoFragment.this).navigate(R.id.action_CatalogoFragment_to_CarritoFragment);
            }
        });

        binding.btnWs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(CatalogoFragment.this)
                        .navigate(R.id.action_FF_to_WSF);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}