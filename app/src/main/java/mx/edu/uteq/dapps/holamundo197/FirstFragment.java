package mx.edu.uteq.dapps.holamundo197;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import mx.edu.uteq.dapps.holamundo197.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

      binding = FragmentFirstBinding.inflate(inflater, container, false);
      return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        /*
        Cada vez que nosotros agregamos un componente al layout de este
        fragmento, en automático el método --inflate-- genera una referencia
        entre el componente gráfico y el archivo Java
         */
        binding.buttonCurp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Existen diferentes tipos de alertas en Android
                Toast es una alerta incorporada a Android que no puede ser
                personalizada

                Toast usa los siguientes parámetos:
                1.- El contexto de la Actividad
                    *Si estas en un fragmento usa getActivity()
                    *si estas en un sreen usa this.NOMBRE_CLASE
                2.- Texto de la alerta
                3.- Tiempo
                        *Toast.LENGTH_SHORT
                        *Toast.LENGTH_LONG
                 */

                //Descomenta para ver un ejemplo de
                //alerta en Android
                /*Toast.makeText(
                        getActivity(),
                        "Hola mundo desde un Toast",
                        Toast.LENGTH_LONG
                ).show();*/

                /*
                Para navegar entre pantallas, es necesario invocar
                al metodo startActivity(Intent)

                Un intent indica dos elementos
                param 1: ¿Dónde estás? (Contexto)
                            *Si estas en un fragmento usa getActivity()
                            *si estas en un sreen usa this.NOMBRE_CLASE
                param 2: ¿A dónde vas?
                            NOMBRE_CLASE.class
                 */
                Intent intentNav = new Intent(
                        getActivity(),
                        CurpActivity.class
                );
                startActivity(intentNav);
            }
        });
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}