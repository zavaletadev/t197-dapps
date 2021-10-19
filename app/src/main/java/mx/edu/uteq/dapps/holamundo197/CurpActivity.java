package mx.edu.uteq.dapps.holamundo197;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
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

    private Spinner spMesNacimiento;

    private RadioButton rbMujer;
    private RadioButton rbHombre;

    /*
    AlertDialog es un componente
    que permite visualizar mensaje en una
    ventana modal que se superpone al contenido
    de la pantlla (screen)
     */
    private AlertDialog.Builder alerta;

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
        Inicializamos nuestra alerta usando el contexto
        de esta clase
         */
        alerta = new AlertDialog.Builder(CurpActivity.this);

        /*
        Las alertas tiene 6 métodos principales
        1.- Titulo
        2.- Texto (cuerpo)
        3.- El ícono
        4.- Botones (hasta 3)
        5.- configuración (indeterminada, cancelable, etc)
        6.- Que sea visible
         */
//        alerta.setTitle("Hola mundo");
//        alerta.setMessage("Aquín en la alerta bien agusto...");
//        alerta.show();


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

        spMesNacimiento = findViewById(R.id.sp_mes_nacimiento);
        rbMujer = findViewById(R.id.rb_mujer);
        rbHombre = findViewById(R.id.rb_hombre);

    }

    /*
    Podemos crear un metodo propio y ligarlo a una acción de un componente
    por ejemplo, al click de un botón, la única condición es que el método
    contenga una parámetro de tipo View
    Para ligar el método al click de un botón utilizamos
    el atributo onclick en el layout (vista)
     */
    public void generaCURP (View v) {

        /*
        Tomamos el valor del mes seleccionado desde el Spinner
         */
        final String mesNacimiento = spMesNacimiento.getSelectedItem().toString();

        //Mostramos el mes seleccionado
        /*Toast.makeText(
                CurpActivity.this,
                mesNacimiento,
                Toast.LENGTH_SHORT
        ).show();*/

        /*
        Validamos si el radio Mujer está seelccionado
         */
        if (rbMujer.isChecked()) {
            Toast.makeText(
                    CurpActivity.this,
                    "Mujer seleccionado",
                    Toast.LENGTH_SHORT
            ).show();
        }

        if (rbHombre.isChecked()) {
            Toast.makeText(
                    CurpActivity.this,
                    "Hombre seleccionado",
                    Toast.LENGTH_SHORT
            ).show();
        }

        /*
        guardamos el texto del campo nombres
         */
        final String nombres = etNombre.getText().toString();

        alerta.setTitle("En el click del boton generar curp");
        alerta.setMessage("Diste clic a este botón");
        /*
        Los botones se dividen en 3 posiciones
        Positive --> acciones que agregan, editan, muestran
        Negative --> acciones que eliminan, esconden o regresan
        Neutral ---> acciones que no generan ningún cambio
         */
        alerta.setPositiveButton("Continuar", null);

        alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /*
                Regresar al home (MainActivity.java)
                 */
                startActivity(
                        new Intent(
                                CurpActivity.this,
                                MainActivity.class
                        )
                );
            }
        });

//        alerta.setNeutralButton("Neu", null);
        //Evitamos que la modal se cierre desde el fondo
        alerta.setCancelable(false);
        alerta.setIcon(R.drawable.alerta);
        //alerta.show(); PARA VER EL EJEMPLO DESCOMENTA ESTA LINEA

        /*
        Mostramos el texto en el titulo SI TIENE MAS DE 3 LETRAS
         */

        if (nombres.trim().length() >= 3) {
            tvTitulo.setText("¡Hola " + nombres + "!");
        }

        else {
            alerta.setTitle("ERROR");
            alerta.setMessage("Nombre inválido");
            alerta.setPositiveButton(null, null);
            alerta.setNegativeButton(null, null);
            alerta.setNeutralButton(
                    "Corregir",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Limpaimos el campo nombres
                            etNombre.setText("");
                            //Ponemos el cursor en este campo
                            etNombre.requestFocus();
                    }
            });
            alerta.show();

        }


    }

}