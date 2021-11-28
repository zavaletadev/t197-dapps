package mx.edu.uteq.dapps.demonavdrawer;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import mx.edu.uteq.dapps.demonavdrawer.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor spEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         binding = ActivityMainBinding.inflate(getLayoutInflater());
         setContentView(binding.getRoot());

        /*
        Inicializamos SharedPReferences y su editor
        */
        sharedPreferences = getSharedPreferences(
                "t197",
                MODE_PRIVATE
        );
        spEditor = sharedPreferences.edit();

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_gallery,
                R.id.nav_slideshow,
                R.id.nav_cuenta)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /*
        Tomar el id del elemento seleccionado
         */
        int id = item.getItemId();

        if (id == R.id.action_salir) {
            //Imrimimos en el log que estamos en el click
            //de salir
            Log.d("EstadoSalir", "Si");

            //Invocamos al método salir
            salir();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /*
    Método para salir y regresar al inicio
     */
    public void salir() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(
                MainActivity.this
        );

        alerta.setTitle("Cerrar sesión");
        alerta.setMessage("¿Realmente deseas salir?");
        alerta.setNegativeButton("No", null);
        alerta.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /*
                Eliminamos id y user_key de nuestro espacio de trabajo
                en SharedPreferences
                 */
                spEditor.remove("id");
                spEditor.remove("user_key");
                spEditor.commit();


                //Eliminamos el historial de navegación
                finish();

                //Enviamos al inicio
                startActivity(
                        new Intent(
                                MainActivity.this,
                                InicioActivity.class
                        )
                );
            }
        });
        alerta.setIcon(R.drawable.alerta);
        alerta.setCancelable(false);
        alerta.show();
    }

    /*
    Sobreescribimos el boton back del sistema operativo
    para mostrar el método salir
     */
    @Override
    public void onBackPressed() {
        salir();
    }
}