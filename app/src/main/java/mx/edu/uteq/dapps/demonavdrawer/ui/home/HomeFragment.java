package mx.edu.uteq.dapps.demonavdrawer.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import mx.edu.uteq.dapps.demonavdrawer.R;
import mx.edu.uteq.dapps.demonavdrawer.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

private FragmentHomeBinding binding;

    /*
    PAra utilizar un ListView necesitamos:
    1.- Inidcar un componente de tipo ListView en el layout (vista)
    2.- Crear una colección con los elementos que deseamos mostrar
        en el <ListView />
    **2.1 EN CASO DE UTILIZAR UN LAYOUT PERSONALIZADO, PRIMERO DEBEMOS
            DISEÑAR NUESTRO LAYOUT**
    3.- Crear un Adaptador para comunicar al listview con la colección de
        datos
    4.- Comunicar al adaptador con el <ListView />
    */
    private List<String> datos;
    //El adaptador debe ser del mismo tipo de dato que la lista
    private ArrayAdapter<String> adaptador;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        /*
        Agregamos elementos a nuestro ArrayList
         */
        datos = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            datos.add("Chile");
            datos.add("Paraguay");
            datos.add("Bolivia");
            datos.add("Perú");
            datos.add("Ecuador");
            datos.add("Brasil");
            datos.add("Colombia");
            datos.add("Venezuela");
            datos.add("Paraguay");
            datos.add("Argentina");
        }

        /*
        El adaptador necesita 3 valores para inicializarse:
        1.- Contexto
        2.- Diseño de cada elemento
        3.- Colección de valores
         */
        adaptador = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                datos
        );

        /*
        Ligar al ListView con el adaptador
         */
        binding.lvEjemplo.setAdapter(adaptador);

        /*
        Programamos el evento swipe (arrastrar y soltar)
         */
        binding.srlEjemplo.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d("Adentro de Swipe", "SI");

                //Borramos el primer elemento de la colección de datos
                datos.remove(0);

                //Actualizamos el listview por medio del adaptador
                adaptador.notifyDataSetChanged();

                //Ocultamos el loader
                binding.srlEjemplo.setRefreshing(false);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}