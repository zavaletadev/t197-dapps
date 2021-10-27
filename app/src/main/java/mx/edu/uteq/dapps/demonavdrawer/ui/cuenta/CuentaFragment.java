package mx.edu.uteq.dapps.demonavdrawer.ui.cuenta;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import mx.edu.uteq.dapps.demonavdrawer.databinding.FragmentCuentaBinding;

public class CuentaFragment extends Fragment {

    /*
    Creamos un objeto de referencia entre la vista (layout)
    y el controlador (fragment)
    (binding)
     */
    public FragmentCuentaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*
        Ligamos a binding con la vista del fragmento
         */
        binding = FragmentCuentaBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        final TextView textView = binding.textCuenta;
        textView.setText("Aquí en Cuenta");

        return root;
    }

    /*
    Desvinculamos la vista cuando ya no se ve (usa)
     */

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}