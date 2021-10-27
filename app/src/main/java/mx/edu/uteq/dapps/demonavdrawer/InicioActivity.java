package mx.edu.uteq.dapps.demonavdrawer;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import mx.edu.uteq.dapps.demonavdrawer.databinding.ActivityInicioBinding;

public class InicioActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityInicioBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInicioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_inicio);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    /*
    Método que inicializa el menú (lo nmuestra)
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pub_main, menu);
        return true;
    }


    /*
    Método que evalua el clic de los elementos
    del menu
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //tomamos el id del elemento seleccionado
        int id = item.getItemId();

        NavController navController = Navigation.findNavController(
                InicioActivity.this,
                R.id.nav_host_fragment_content_inicio
        );

        if (id == R.id.menu_carrito) {
            navController.navigateUp();
            //Indicamos el id del componente en el archivo de
            //navegación (nav_graph)
            navController.navigate(R.id.CarritoFragment);
        }

        if (id == R.id.menu_login) {

            navController.navigateUp();
            //Indicamos el id del componente en el archivo de
            //navegación (nav_graph)
            navController.navigate(R.id.LoginFragment);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_inicio);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
}