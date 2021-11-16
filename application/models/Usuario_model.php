<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Usuario_model extends CI_Model {

    function lista_usuarios_v1()
    {
        /*
        1.- Consulta convencional utilizando el metodo Query
        2.- Consulta utilizando Active Record Class
        */ 

       //Forma1
        $cmd = "SELECT * FROM usuario";
        $query = $this->db->query($cmd);

        //Retornar la lista de usuario SI EXISTE, SI NO, regresar NULL
        if ($query->num_rows() > 0) {
            return $query->result();
        }

        else {
            return NULL;
        }
    }

    function lista_usuarios_v2()
    {
        /*
        1.- Consulta convencional utilizando el metodo Query
        2.- Consulta utilizando Active Record Class
            Un subset (libreria) de CodeIgniter para realizar consultas SQL
            de una forma mas rapida e intuitiva
        */ 

       //Forma2
            $query = $this->db->get('usuario');

        //Retornar la lista de usuario SI EXISTE, SI NO, regresar NULL
        //(condicion) ? SI_APLICA : NO_APLICA
            return ($query->num_rows() > 0) ? $query->result() : NULL;
            

        }

        function lista_clientes_activos_v1()
        {
            $cmd = "SELECT * FROM usuario WHERE rol_id = '3' AND estatus = 1";
            $query = $this->db->query($cmd);

            return $query->num_rows() > 0 ? $query->result() : NULL;
        }

        function lista_clientes_activos_v2()
        {
            $this->db->where('rol_id', 3);
            $this->db->where('estatus', 1);
            $query = $this->db->get('usuario');

            return $query->num_rows() > 0 ? $query->result() : NULL;
        }
    }
    /* End of file Usuario_model.php */
    /* Location: ./application/models/Usuario_model.php */
