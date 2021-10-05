package mx.edu.uteq.dapps.holamundo197;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import mx.edu.uteq.dapps.holamundo197.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

      binding = FragmentSecondBinding.inflate(inflater, container, false);
      return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        /*
        Click al boton Linear  para ir a linearFragment
         */
        binding.buttonLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Nos movemos a LinearFragment
                Param 1 ==== instancia de la clase
                Param 2 ==== Id del elemento a donde quiero ir
                 */
                NavHostFragment.findNavController(SecondFragment.this)
                .navigate(R.id.action_SF_to_Lf);
            }
        });

        /*
        Click al boton Scroll para ir a ScrollFragment
         */
        binding.buttonScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(SecondFragment.this)
                .navigate(R.id.action_SF_to_ScF);
            }
        });


    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}