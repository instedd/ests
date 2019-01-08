<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Useradministration_model extends CI_Model
{


    public function __construct()
    {
        parent::__construct();
    $this->db = $this->load->database('default', TRUE,TRUE);
    }
  function getdata(){
     $this->db->select('*');
     $query = $this->db->get('tbl_registered_samples');
     return $query->result_array();
  }
    function get_all_health_facilities($id)
    {
        $append_id=($id!=='')?'and id='.$id.'':'';
        $this->db->select("
        `id`, 
        `name`,
        `code`,
        `district`,
        `hub`,
        `entered_by`,
        `display`,
        `created_at` FROM `tbl_facility_codes` 
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
    function get_all_users($id)
    {
        $append_id=($id!=='')?'and id='.$id.'':'';
        $this->db->select("
        `id`, 
        `fullNames`,
        `username`, 
        `password`,
        `EncryptionHint`,
        `user_group_id`, 
        `role_id`, 
        `org_id`,
        `email`,
        `tel_mobile`,
        `status`, 
        `display`,
        `emailStatus`,
        `passwordReset`, 
        `ResetConfirmed`,
        `date_entry` FROM `tbl_users` 
        WHERE 1
         " . $append_id . " 
          order by `fullNames` asc", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }

    function get_all_diseases($id)
    {
        $append_id=($id!=='')?'and id='.$id.'':'';
        $this->db->select("
        `id`, 
        `name`,
        `entered_by`,
        `display`,
        `entry_date` FROM `tbl_diseases` 
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
        function get_all_transit_points($id)
    {
        $append_id=($id!=='')?'and id='.$id.'':'';
        $this->db->select("* FROM `tbl_transit_points` 
        WHERE 1
         " . $append_id . " 
          order by `id` asc", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }

        function get_all_sample_transporters($id)
    {
        $append_id=($id!=='')?'and id='.$id.'':'';
        $this->db->select("* FROM `tbl_sample_transporters` 
        WHERE 1
         " . $append_id . " 
          order by `id` asc", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
 function get_all_referenceLabs($id)
    {
        $append_id=($id!=='')?'and id='.$id.'':'';
        $this->db->select("
        `id`, 
        `name`,
        `code`,
        `entered_by`,
        `display`,
        `created_at` FROM `tbl_referencelabs` 
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
    function get_all_user_details($record_id)
    {

        $this->db->select('*');
        $this->db->from('tbl_users');
        $this->db->where('id=' . $record_id . '');
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
    function get_all_lab_details($record_id)
    {

        $this->db->select('*');
        $this->db->from('tbl_referencelabs');
        $this->db->where('id=' . $record_id . '');
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
    function get_all_disease_details($record_id)
    {

        $this->db->select('*');
        $this->db->from('tbl_diseases');
        $this->db->where('id=' . $record_id . '');
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
    function get_all_healthFacility_details($record_id)
    {

        $this->db->select('*');
        $this->db->from('tbl_facility_codes');
        $this->db->where('id=' . $record_id . '');
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
     function get_all_reg_sample_details($record_id)
    {

        $this->db->select('*');
        $this->db->from('tbl_registered_samples');
        $this->db->where('id=' . $record_id . '');
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
     function get_all_rec_sample_details($record_id)
    {

        $this->db->select('*');
        $this->db->from('tbl_received_sample');
        $this->db->where('id=' . $record_id . '');
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
    function get_all_locations($id)
    {
        $append_id=($id!=='')?'and id='.$id.'':'';
        $this->db->select("
        `id`, 
        `name`,
        `code`,
        `entered_by`,
        `display`,
        `created_at` FROM `tbl_locations` 
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
    function get_all_location_details($record_id)
    {

        $this->db->select('*');
        $this->db->from('tbl_locations');
        $this->db->where('id=' . $record_id . '');
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
    function get_all_samples($id)
    {
        $append_id=($id!=='')?'and id='.$id.'':'';
        $this->db->select("
        `id`, 
        `name`,
        `entered_by`,
        `display`,
        `created_at` FROM `tbl_sample_type` 
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
    function get_all_sample_details($record_id)
    {

        $this->db->select('*');
        $this->db->from('tbl_sample_type');
        $this->db->where('id=' . $record_id . '');
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
    function get_all_destinations($id)
    {
        $append_id=($id!=='')?'and id='.$id.'':'';
        $this->db->select("
        `id`, 
        `name`,
        `entered_by`,
        `display`,
        `created_at` FROM `tbl_destination` 
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
    function get_all_destination_details($record_id)
    {
        $this->db->select('*');
        $this->db->from('tbl_destination');
        $this->db->where('id=' . $record_id . '');
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
	function get_all_registered_samples()
    {
         $this->db->select('*');
        $this->db->from('tbl_registered_samples');
        $this->db->order_by('sample_id','asc');
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
    function get_all_received_samples()
    {

      $this->db->select('*');
        $this->db->from('tbl_received_sample');
        $this->db->order_by('sample_id','asc');
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }
    }
    function get_all_received_sample()
    {

      $this->db->select(" s.`id`,
                          s.`sample_id`,
                           s.`destination_id`,
                            s.`initialSampleDate`,
                            s.`finalDestinationDate`,
                            r.`sample_id`,
                            r.`sample_status`,
                            r.`destination_id`,
                             r.`is_destination`,
                            r.`date_received`,
                            r.`entered_by`,
                            r.`created_at`
                            FROM `tbl_registered_samples` as `s`
                            left join `tbl_received_sample` as `r`
                            on (s.`sample_id`=r.`sample_id`)
                            WHERE 1 and r.date_received !='1970-01-01'
                            and r.`is_destination`='YES'
                            order by r.`sample_id` asc", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }
    }
    function get_all_received_sample_in_transit()
    {

      $this->db->select(" s.`id`,
                          s.`sample_id`,
                           s.`destination_id`,
                            s.`initialSampleDate`,
                            s.`finalDestinationDate`,
                            r.`sample_id`,
                            r.`sample_status`,
                            r.`destination_id`,
                             r.`is_destination`,
                            r.`date_received`,
                            r.`entered_by`,
                            r.`created_at`
                            FROM `tbl_registered_samples` as `s`
                            left join `tbl_received_sample` as `r`
                            on (s.`sample_id`=r.`sample_id`)
                            WHERE 1 and r.date_received !='1970-01-01'
                            and s.`isSampleAtFinal`='NO'
                            order by r.`sample_id` asc", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }
    }
    public function check_registered_sample($sample_id)
    {
        $this->db->select('*');
        $this->db->from('tbl_registered_samples');
        $this->db->where(array('sample_id'=>$sample_id));
        $db_rows = $this->db->get();
        return $db_rows->num_rows();
    }
  public function check_exist_received_sample($sample_id,$destination_id)
    {
        $query_str = $this->db->select(" sample_id from tbl_received_sample where sample_id = '".$sample_id."'
        AND destination_id='".$destination_id."'",FALSE);           

        $result = $this->db->get($query_str);
        if($result->num_rows() > 0)
        {
            return TRUE;
        }else
        {
            return FALSE;
        }
    } 
    function check_sample($sample_id, $destination_id)
    {
        $this->db->select('*');
        $this->db->from('tbl_received_sample');
        $this->db->where(array('sample_id'=>$sample_id,'destination_id'=>$destination_id,'is_destination'=>'YES'));
        $db_rows = $this->db->get();
        return $db_rows->num_rows();
    }
    function get_sample_location()
    {

      $this->db->select('*');
        $this->db->from('tbl_received_sample');
        $this->db->order_by('destination_id','asc');
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }
    }
    public function get_admin_email($role_id){
        $this->db->select('*');
        $this->db->from('tbl_users');
        $this->db->where('role_id',$role_id);
        $this->db->order_by('id','asc');
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }
        
    }
    function filter_registered_samples($start_date,$end_date,$district)
    {
        if(!empty($district)){
        $this->db->select('*');
        $this->db->from('tbl_registered_samples');
        $this->db->where(array('initialSampleDate >='=>$start_date,'initialSampleDate <='=>$end_date,'districtCode'=>$district));
        $this->db->order_by('sample_id','asc');
    }else{
       $this->db->select('*');
        $this->db->from('tbl_registered_samples');
        $this->db->where(array('initialSampleDate >='=>$start_date,'initialSampleDate <='=>$end_date));
        $this->db->order_by('sample_id','asc'); 
    }
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
function search_received_samples($start_date,$end_date,$district)
    {
        if(!empty($district)){
        $this->db->select('*');
        $this->db->from('tbl_received_sample');
        $this->db->where(array('date_received >='=>$start_date,'date_received <='=>$end_date,'districtCode'=>$district));
        $this->db->order_by('sample_id','asc');
    }else{
       $this->db->select('*');
        $this->db->from('tbl_received_sample');
        $this->db->where(array('date_received >='=>$start_date,'date_received <='=>$end_date));
        $this->db->order_by('sample_id','asc'); 
    }
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
}