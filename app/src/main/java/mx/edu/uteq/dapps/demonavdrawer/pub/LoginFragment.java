package mx.edu.uteq.dapps.demonavdrawer.pub;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import mx.edu.uteq.dapps.demonavdrawer.MainActivity;
import mx.edu.uteq.dapps.demonavdrawer.R;
import mx.edu.uteq.dapps.demonavdrawer.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    /*
    Una petición asíncrona (ajax)
    en adnroid necesita:
    1.- Volley
    2.- RequestQueue
    3.- url del servicio
    4.- tipo de envio de datos (post/get)
    5.- StringRequest
    6.- Progress
    */
    private RequestQueue conexionServ;
    private StringRequest peticionServ;
    private ProgressDialog progress;
    private AlertDialog.Builder alerta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);

        conexionServ = Volley.newRequestQueue(getActivity());
        progress = new ProgressDialog(getActivity());

        progress.setTitle("Autenticando");
        progress.setMessage("Por favor espera...");
        progress.setCancelable(false);
        progress.setIndeterminate(true);

        /*
        Click en el boton iniciar sesión
         */
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progress.show();

                peticionServ = new StringRequest(
                        //1 Tipo
                        Request.Method.POST,
                        //2 URL
                        "https://zavaletazea.dev/labs/awos-dapps197/api/auth/login",
                        //3 Caso satisfactorio
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progress.hide();

                                /*
                                Intentamos convertir la respuesta a un objeto JSON
                                 */
                                try {
                                    JSONObject objRespuesta = new JSONObject(response);

                                    /*
                                    Si el código de respuesta es 200, significa que el usuario
                                    existe y traemos sus datos en el objeto "datos usuario"
                                     */
                                    if (objRespuesta.getInt("code") == 200) {

                                        JSONObject datosUsuario = objRespuesta.getJSONObject("datos_usuario");

                                        /*
                                        Direccionamos al inicio de la App
                                         */
                                        alerta = new AlertDialog.Builder(getActivity());
                                        alerta.setTitle("Bienvenido");
                                        alerta.setMessage("Hola de nuevo " + datosUsuario.getString("email"));
                                        alerta.setPositiveButton("Acceder", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                startActivity(
                                                        new Intent(
                                                                getActivity(),
                                                                MainActivity.class
                                                        )
                                                );
                                            }
                                        });
                                        alerta.setIcon(R.drawable.saludo);
                                        alerta.setCancelable(false);
                                        alerta.show();
                                    }

                                    /*
                                    Si el usuario se encuentra deshabilitado
                                     */
                                    else if (objRespuesta.getInt("code") == 403){
                                        alerta = new AlertDialog.Builder(getActivity());
                                        alerta.setTitle("Cuenta deshabilitada");
                                        alerta.setMessage("Tu cuenta se encuentra temporalmente deshabilitada.\n\nPor favor contacta con un adminsitrador para más información");
                                        alerta.setPositiveButton("Aceptar", null);
                                        alerta.setIcon(R.drawable.deshabilitado);
                                        alerta.setCancelable(false);
                                        alerta.show();

                                    }

                                    /*
                                    Si la contraseña o el correo electrónico son incorrectos
                                     */
                                    else if (objRespuesta.getInt("code") == 404) {
                                        alerta = new AlertDialog.Builder(getActivity());
                                        alerta.setTitle("¡Hey!");
                                        alerta.setMessage("Usuario / contraseña incorrrectos\n\nPor favor vuelve a intentarlo");
                                        alerta.setPositiveButton("Aceptar", null);
                                        alerta.setIcon(R.drawable.noencontrado);
                                        alerta.setCancelable(false);
                                        alerta.show();
                                    }

                                    /*
                                    Si tenemos cualquier otro código de error
                                     */
                                    else {
                                        alerta = new AlertDialog.Builder(getActivity());
                                        alerta.setTitle("¡Hey!");
                                        alerta.setMessage("Ocurrió un error, por favor vuelve a intentarlo");
                                        alerta.setPositiveButton("Continuar", null);
                                        alerta.setIcon(R.drawable.error);
                                        alerta.setCancelable(false);
                                        alerta.show();
                                    }

                                } catch (JSONException e) {
                                    System.err.println(e.getMessage());
                                    e.printStackTrace();
                                }

                            }
                        },
                        //4 Caso de error con el servidor
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progress.hide();
                                Toast.makeText(getActivity(), error.toString(),
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                ){
                    /*
                    Agregamos las variables a la petición post
                    por medio de un Mapa
                     */
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        String usuario = binding.tietUsuario.getText().toString();
                        String password = binding.tietPassword.getText().toString();


                        //Map<Tipo claves, Tipo valores>
                        Map<String, String> parametros = new HashMap<>();

                        //Añadimos los valores post al servicio
                        parametros.put("usuario", usuario);
                        parametros.put("password", md5(password));

                        //Enviamos los parametros post al servicio
                        return parametros;
                    }
                };
                /*
                NO OLVIDES AGREGAR LA PETICION A LA COLA DEL SERVIDOR
                 */
                conexionServ.add(peticionServ);

            }
        });

        //ÚLTIMA LÍNEA DEL MÉTODO
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public String md5(String texto) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(StandardCharsets.UTF_8.encode(texto));
            return String.format("%032x", new BigInteger(1, md5.digest()));
        } catch (java.security.NoSuchAlgorithmException e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return null;
    }
}