<?php
if (!defined('BASEPATH')) exit('No direct script access allowed');

class Api_model extends CI_Model {

    public function __construct() {
        parent::__construct();
        
        //load database library
        $this->load->database();
    }

    /*
     * Fetch user data
     */
    function getRows($id = "")
	{
        if(!empty($id))
		{
            $condition=array(
			'id' => $id
			);
			$this->db->select("*");
			$this->db->from('tbl_registered_samples');
			$this->db->where($condition);
			$query=$this->db->get();
            return $query->row_array();
       	   }else
			{
           $this->db->select("*");
			$this->db->from('tbl_registered_samples');
			$query=$this->db->get();
            return $query->result_array();
        }
    }
    
    /*
     * Insert data
     */
    public function insert($data = array()) {
        $insert = $this->db->insert('tbl_registered_samples', $data);
        if($insert){
            return $this->db->insert_id();
        }else{
            return false;
        }
    }
    
    /*
     * Update user data
     */
    public function update($data, $id) {
        if(!empty($data) && !empty($id)){
            if(!array_key_exists('modified', $data)){
                $data['modified'] = date("Y-m-d H:i:s");
            }
            $update = $this->db->update('members', $data, array('memberId'=>$id));
            return $update?true:false;
        }else{
            return false;
        }
    }
    
    function getHealthFacilities($id)
    {
        $append_id=($id!=='')?'and id='.$id.'':'';
        $this->db->select("
        `id`, 
        `name` 
        FROM `tbl_facility_codes` 
        WHERE 1
         " . $append_id . " 
          order by `name` asc", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
    function getDiseases($id)
    {
        $append_id=($id!=='')?'and id='.$id.'':'';
        $this->db->select("
        `id`, 
        `name` FROM `tbl_diseases` 
        WHERE 1
         " . $append_id . " 
          order by `name` asc", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
    function getSpecimen($id)
    {
        $append_id=($id!=='')?'and id='.$id.'':'';
        $this->db->select("
        `id`, 
        `name` FROM `tbl_sample_type` 
        WHERE 1
         " . $append_id . " 
          order by `name` asc", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
    function get_destinations($id)
    {
        $append_id=($id!=='')?'and id='.$id.'':'';
        $this->db->select("
        `id`, 
        `name` FROM `tbl_destination` 
        WHERE 1
         " . $append_id . " 
          order by `name` asc", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
    function get_transporters($id)
    {
        $append_id=($id!=='')?'and id='.$id.'':'';
        $this->db->select("
        `id`, 
        `name` 
        FROM `tbl_sample_transporters` 
        WHERE 1
         " . $append_id . " 
          order by `name` asc", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
}
