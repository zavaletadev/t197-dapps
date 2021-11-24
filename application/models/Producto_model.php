<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Producto_model extends CI_Model {

    /**
     * [get_productos description]
     * @param  [type] $estatus [description]
     * @return [type]          [description]
     */
    function get_productos($estatus = NULL)
    {
        if (!is_null($estatus)) {
            $this->db->where('estatus', $estatus);
        }
        $query = $this->db->get('producto');

        return $query->num_rows() > 0 ? $query->result() : NULL;
    }

    /**
     * [get_tallas description]
     * @param  [type] $producto_id [description]
     * @return [type]              [description]
     */
    function get_tallas($producto_id)
    {
        /*
        Seleccionamos las tallas del producto 
        a partir de su id
         */        
        $this->db->where('producto_id', $producto_id);        
        $query = $this->db->get('talla_producto');

        return $query->num_rows() > 0 ? $query->result() : NULL;
    }    

}

/* End of file Producto_model.php */
/* Location: ./application/models/Producto_model.php */
