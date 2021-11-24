<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Productos extends CI_Controller {

    public function __construct()
    {
        parent::__construct();
        $this->load->model('producto_model');
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
            Productos API
            <hr />
            </h1>
            </center>'
        );        
    }

    /**
     * [lista description]
     * @return [type] [description]
     * @example [GET] https://zavaletazea.dev/labs/awos-dapps197/api/productos/lista
     */
    function lista()
    {
        header('Content-Type: application/json; charset=utf-8');

        /*
        Seleccionamos todos los productos activos (estatus 1)
         */
        $info_productos = $this->producto_model->get_productos(1);
        $lista_productos = array();

        /*
        Si tenemos productos
         */
        if (!is_null($info_productos)) {
            /*
            Recorremos todos los productos para agregar sus tallas
             */
            foreach($info_productos as $producto) {

                /*
                Seleccionamos las tallas de cada producto
                 */
                $tallas_prod = $this->producto_model->get_tallas($producto->producto_id);                

                /*
                Creamos un arreglo para agregar la talla a la información 
                del producto
                 */
                $producto->tallas = array();

                /*
                Recorremos todas las tallas de cada producto
                 */
                foreach($tallas_prod as $tallas) {
                    /*
                     gregamos al arreglo de tallas
                     */
                     $producto->tallas[] = $tallas->talla;
                 }

                //Agregamos el arreglo de las tallas al objeto dle producto
                 $lista_productos[] = $producto;
             }
         }

         echo json_encode(
            array(
                'code' => 200, 
                'data' => $lista_productos
            )
        );

     }

 }

 /* End of file Productos.php */
 /* Location: ./application/controllers/api/Productos.php */
