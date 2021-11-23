<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Auth_model extends CI_Model {

    /**
     * [login description]
     * @param  [type] $usuario [description]
     * @param  [type] $rol_id  [description]
     * @return [type]          [description]
     */
    function login($usuario, $rol_id = NULL)
    {
        /*SELECT * FROM usuario
        WHERE
        (email_auth LIKE BINARY '4421298927' OR telefono_auth LIKE '4421298927') 
        AND 
        rol_id = 3;        
        */ 
        $this->db->where("(email_auth LIKE BINARY '$usuario' OR telefono_auth LIKE '$usuario')");

        //Si se agrega un rol_id
        if (!is_null($rol_id)) {
            $this->db->where('rol_id', $rol_id);
        }

        $query = $this->db->get('usuario');       

        //Retornamos solo un usuario
        return ($query->num_rows() === 1) ? $query->row() : NULL;        
    }


}

/* End of file Auth_model.php */
/* Location: ./application/models/Auth_model.php */
