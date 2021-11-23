package mx.edu.uteq.dapps.demonavdrawer.pub;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import mx.edu.uteq.dapps.demonavdrawer.databinding.FragmentEjemplowebserviceBinding;

public class EjemplowebserviceFragment extends Fragment {

    private FragmentEjemplowebserviceBinding binding;

    /*
    Para consumir un servicio desde una url mediante Volley se necesita:
    1.- Una variable d etipo Requestqueue que inicialize la conexión entre
    el servidor y android

    2.- Una variable de tipo StringQuest para la petición particular
     */
    private RequestQueue conexionServ;
    private StringRequest peticionServ;

    //Creamos un ProgressDialog para indicar el tiempo de carga
    private ProgressDialog progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEjemplowebserviceBinding.inflate(inflater, container, false);

        //Inicializamos al ProgressBar
        progress = new ProgressDialog(getActivity());

        //Construimos el progress
        progress.setTitle("Cagando");
        progress.setMessage("Por favor espera...");

        //indica que la ventana muestre un loader
        progress.setIndeterminate(true);

        progress.setCancelable(false);

        /*
        Inicializamos la conexión al servidor
         */
        conexionServ = Volley.newRequestQueue(getActivity());

        /*
        Configuramos el boton que invoca al servicio web
         */
        binding.btnConsumirWs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Mostramos el loader
                progress.show();

                /*
                Preparamos la peticion a datos personales y necesitas:
                1.- Tipo de envio de datos (GET/POST)
                2.- URL del web service
                3.- Programar si todo sale ok (el servidor contesta)
                4.- Programar si todo sale mal (el servidor no contesta)
                5.- (SOLO PARA POST) agregar las variables por medio de un Arbol (HashMap)
                 */
                peticionServ = new StringRequest(
                        //1
                        Request.Method.GET,
                        //2
                        "https://zavaletazea.dev/labs/awos-dapps197/api/usuarios/mostrar_json",
                        //3
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                /*
                                Si el servidor contesta mostramos la respuesta
                                 */
                                //Toast.makeText(getActivity(), response, Toast.LENGTH_LONG).show();

                                /*
                                Intentaremos convertir la respuesta en formato json
                                 */
                                try {
                                    /*
                                    Creamos un objeto json a partir de la respuesta del servidor
                                     */
                                    JSONObject respuesta = new JSONObject(response);

                                    //Accedemos a cada valor indicando su clave y el tipo de dato
                                    //- getString
                                    //- getInt
                                    //- getDouble
                                    //- getFloat
                                    //- getJsonObject
                                    //- getJsonArray
                                    //que retornará
                                    String nombre = respuesta.getString("nombre");
                                    String apellidos = respuesta.getString("apellido1");
                                    binding.tvNombre.setText(nombre + " " + apellidos);

                                    //Tomamos el objeto json de la dirección
                                    JSONObject direccion = respuesta.getJSONObject("direccion");

                                    String calle = direccion.getString("calle");
                                    binding.tietCalle.setText(calle);

                                    binding.tietNumero.setText(
                                            direccion.getString("numero")
                                    );

                                    //Pasar a String (forma nice)
//                                    binding.tietCp.setText(
//                                            String.valueOf(direccion.getInt("codigopostal"))
//                                    );
                                    binding.tietCp.setText(
                                            direccion.getInt("codigopostal") + ""
                                    );

                                    //Sacamos los telefonos desde un arreglo json
                                    JSONArray telefonos = respuesta.getJSONArray("telefonos");

                                    //Tomamos el primer telefono
                                    binding.tietTel1.setText(
                                            telefonos.getString(0)
                                    );

                                    //Tomamos el segundo telefono
                                    binding.tietTel2.setText(
                                            telefonos.getString(1)
                                    );

                                    //Tomamos las calificaciones
                                    //Son un arreglo de objetos jason

                                    JSONArray calificaciones = respuesta.getJSONArray("calificaciones");

                                    //Tomamos los datos de una materia
                                    JSONObject calif1 = calificaciones.getJSONObject(0);
                                    binding.tvC1.setText(
                                            calif1.getString("materias") + ": " + calif1.getString("calif")
                                    );
                                    JSONObject calif2 = calificaciones.getJSONObject(1);
                                    binding.tvC2.setText(
                                            calif2.getString("materias") + ": " + calif2.getString("calif")
                                    );




                                }

                                catch (Exception e) {
                                    Toast.makeText(getActivity(), e.getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }

                                progress.hide();
                            }
                        },
                        //4
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                progress.hide();

                                /*
                                Si el servidor no contesta, o contesta con un error
                                mostramos el motivo del error
                                 */
                                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }

                );

                //Agregamos la petición a la conexión con el servidor
                conexionServ.add(peticionServ);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}