package mx.edu.uteq.dapps.demonavdrawer.pub;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import mx.edu.uteq.dapps.demonavdrawer.MainActivity;
import mx.edu.uteq.dapps.demonavdrawer.R;
import mx.edu.uteq.dapps.demonavdrawer.databinding.FragmentCatalogoBinding;

public class CatalogoFragment extends Fragment {

    private FragmentCatalogoBinding binding;

    /*
    Referencia a SharedPreferences
     */
    private SharedPreferences sharedPreferences;

    private ProgressDialog progress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCatalogoBinding.inflate(inflater, container, false);

        /*
        Inicializamos SharedPreferences en nuestro espacio de trabajo
         */
        sharedPreferences = getActivity().getSharedPreferences(
                "t197",
                Context.MODE_PRIVATE
        );

        /*
        Inicializar el progress
         */
        progress = new ProgressDialog(getActivity());
        progress.setTitle("Inicializando App");
        progress.setMessage("Por favor espera...");
        progress.setIndeterminate(true);
        progress.setCancelable(false);
        progress.show();

        /*
        Tomamos los datos guardados en nuestro espacio de SharedPreferences
         */
        String md5UsuarioId = sharedPreferences.getString("id", null);
        String md5PassAuth = sharedPreferences.getString("user_key", null);

        /*
        Si ambos valores son diferentes de nulo, lo direccionamos
        al Home privado
         */
        if (md5UsuarioId != null && md5PassAuth != null) {
            startActivity(
                    new Intent(
                            getActivity(),
                            MainActivity.class
                    )
            );
        }

        //Si no encontramos valores en las preferencias
        progress.hide();

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