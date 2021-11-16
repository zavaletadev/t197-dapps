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

    function mostrar_json()
    {
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
                    "calif"    => 'SA'
                ),
                array(
                    "materias" => "INGLES", 
                    "calif"    => 'SA'
                ),
            )
        );  

        
        //Convertimos el arreglo de PHP a JSON
        $obj_json = json_encode($arr_php);

        echo "<pre>";
        echo $obj_json;
        echo "</pre>";      
    }

}

/* End of file Usuarios.php */
/* Location: ./application/controllers/api/Usuarios.php */
