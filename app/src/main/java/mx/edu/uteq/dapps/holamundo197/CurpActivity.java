package mx.edu.uteq.dapps.holamundo197;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class CurpActivity extends AppCompatActivity {

    /*
    Creamos un atributo del tipo del componente
    que se encuentra en el layout (vista)
     */
    private EditText etNombre;
    private EditText etApellido1;
    private EditText etApellido2;
    private TextView tvTitulo;
    private Button btnCurp;

    /*
    El método onCreate es el detonante o
    metodo inicial en el ciclo de vida de un
    activity, sería, en equivalencia con Java SE
    algo así cómo el método --public static void main--
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Este controlador indica cual es su vista asociada
        setContentView(R.layout.activity_curp);

        /*
        Para indicarle al controlador (activity) cual es el
        elemento relacionado a la vista (layout) utilizamos
        el metodo findViewById para indicar dicha relación
        entre componente DEL MISMO TIPO

        Para ello es necesario que el layout (vista) tenga en
        cada componente un identificador para relacionar los
        elementos vista-controlador
         */
        etNombre = findViewById(R.id.et_nombres);
        tvTitulo = findViewById(R.id.tv_titulo);
        btnCurp  = findViewById(R.id.btn_curp);
    }

    /*
    Podemos crear un metodo propio y ligarlo a una acción de un componente
    por ejemplo, al click de un botón, la única condición es que el método
    contenga una parámetro de tipo View
    Para ligar el método al click de un botón utilizamos
    el atributo onclick en el layout (vista)
     */
    public void generaCURP (View v) {
        Toast.makeText(
                CurpActivity.this,
                "Hola desde mi propio evento",
                Toast.LENGTH_SHORT
        ).show();

        /*
        guardamos el texto del campo nombres
         */
        final String nombres = etNombre.getText().toString();

        /*
        Mostramos el texto en el titulo
         */
        tvTitulo.setText("¡Hola " + nombres + "!");

    }

}