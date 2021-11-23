<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Auth extends CI_Controller {

    /**
     * [__construct description]
     */
    public function __construct()
    {
        parent::__construct();
        $this->load->model('auth_model');
    }

    /**
     * [index description]
     * @return [type] [description]
     */
    function index()
    {
        die(
            '
            <br />
            <br />
            <br />
            <center>
            <h1>           
            Auth API
            <hr />
            </h1>
            </center>'
        );        
    }

    /**
     * Inicio de sesión mediante usuario/telefono y contraseña 
     * @example [POST(usuario, password)] https://zavaletazea.dev/labs/awos-dapps197/api/auth
     * @return [type] [description]
     */
    function login()
    {
        header('Content-Type: application/json; charset=utf-8');
        /*
        Utilizando la validación de codeginiter, indicamos que este servicio
        debe contener las variables:
        usuario (requerida)
        password (requerida y siempre de 32 caracteres)
         */
        $this->form_validation->set_rules('usuario', 'usuario', 'trim|required');
        $this->form_validation->set_rules('password', 'password', 'trim|required|exact_length[32]');

        /*
        Si la validación de campos es correcta
         */
        if ($this->form_validation->run()) {
            /*
            Iniciamos el procedimiento para consultar el usuario
             */
            $usuario = $this->input->post('usuario');
            $password = $this->input->post('password');

            //Consultamos si existe un usuario
            $datos_usuario = $this->auth_model->login($usuario, 3);

            //Si el usuario existe
            if (!is_null($datos_usuario)) {
                /*
                Revisamos si la contraseña
                 */
                if ($password === $datos_usuario->pass_auth) {
                    /*
                    Revisamos que el estatus de la cuenta sea activo (1)
                     */
                    if ($datos_usuario->estatus == 1) {
                        /*
                        Regresamos los datos públicos del usuario 
                         */
                        echo json_encode(
                            array(
                                'code'          => 200, 
                                'datos_usuario' => array(
                                    'usuario_id'     => $datos_usuario->usuario_id, 
                                    'email'          => $datos_usuario->email_auth, 
                                    'telefono'       => $datos_usuario->telefono_auth, 
                                    'fecha_registro' => $datos_usuario->fecha_registro
                                )
                            )
                        );
                    }

                    /*
                    Si le usuario está deshabilitado (2)
                     */
                    else if ($datos_usuario->estatus == 2) {
                        echo json_encode(
                            array(
                                'code' => 403
                            )
                        );
                    }

                    //Si la cuenta está eliminada
                    else {
                        echo json_encode(
                            array(
                                'code' => 404
                            )
                        );
                    }
                }

                //Si la contraseña no coincide
                else {
                    echo json_encode(
                        array(
                            'code' => 404
                        )
                    );
                }
            }

            //Si el usuario no existe
            else {
                echo json_encode(
                    array(
                        'code' => 404
                    )
                );
            }
        }

        /*
        Si no se cumple con el criterio de  validación
         */
        else {
            //Limpiamos los caracteres (<p></p>) del mensaje de error
            $this->form_validation->set_error_delimiters('', '');
            echo json_encode(
                array(
                    'code' => 400, 
                    'errors' => validation_errors()
                )
            );
        }
    }

}

/* End of file Auth.php */
/* Location: ./application/controllers/api/Auth.php */
