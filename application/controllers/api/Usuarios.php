<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Usuarios extends CI_Controller {

    public function __construct()
    {
        parent::__construct();
        //Cargamos el modelo de usuarios 
        //para todas las funciones (metodo)
        //de esta clase 
        $this->load->model('usuario_model');
    }

    function index()
    {

    }

    function lista_todos()
    {        
        $lista_usuarios = $this->usuario_model->lista_usuarios_v1();
        //$lista_usuarios = $this->usuario_model->lista_usuarios_v2();
        
        echo "<pre>";
        var_dump($lista_usuarios);
        echo "</pre>";
    }

    function lista_clientes_activos() {
        $lista_clientes_activos = $this->usuario_model->lista_clientes_activos_v1();

        //Convertimos el arreglo a JSON
        $clientes_json = json_encode($lista_clientes_activos);
        
        echo "<pre>";
        echo $clientes_json;
        echo "</pre>";
    }

    /**
     * [mostrar_json description]
     * @example [GET] https://zavaletazea.dev/labs/awos-dapps197/api/usuarios/mostrar_json
     * @return [type] [description]
     */
    function mostrar_json()
    {
        /*
        Simular un servicio lento
         */
        sleep(1);
        /**
         * 1.- AGREGAR LA LIBRERÍA VOLLEY A NUESTRO PROYECTO DE ANDROID DESDE EL APP.GRADLE
         * 2.- HABILITAR EL PERMISO DE INTERNET DESDE EL MANIFEST.XML DE NUESTRA APP
         * 3.- HABILITAR LA CONEXIÓN VÍA HTTP DESDE EL ARCHIVO MANIFEST.XML DE NUESTRA APP
         */

        /*
        Indicamos por medio de un encabezado que 
        el contenido que retornamos es JSON
         */
        header('Content-Type: application/json; charset=utf-8');

        //En PHP los objetos JSON se transforman 
        //a partir de arregos asociativos
        $arr_php = array(
            'nombre'    => 'Raul', 
            'apellido1' => 'Zavaleta',
            'direccion' => array(
                'calle'        => 'Av. Marmota', 
                'numero'       => '68-3',
                'codigopostal' => 76269,
            ),
            'telefonos'      => array('4422048329', '4421298927'), 
            'calificaciones' => array(
                array(
                    "materias" => "DAPPS", 
                    "calif"    => 'AU'
                ),
                array(
                    "materias" => "INGLES", 
                    "calif"    => 'SA'
                ),
            )
        );  

        
        //Convertimos el arreglo de PHP a JSON
        $obj_json = json_encode($arr_php);
        
        echo $obj_json;        
    }

}

/* End of file Usuarios.php */
/* Location: ./application/controllers/api/Usuarios.php */
