<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Dashboard_model extends CI_Model
{

    public function __construct()
    {
        parent::__construct();
    }
    function get_monthly_undelayed_samples_at_final_destination()
    {
        $this->db->select("tbl_months.month,count(DISTINCT tbl_registered_samples.sample_id) as mon_undelayed_samples_at_final_destination 
       FROM tbl_registered_samples 
       JOIN tbl_received_sample on (tbl_registered_samples.sample_id=tbl_received_sample.sample_id)
       RIGHT OUTER JOIN tbl_months ON tbl_registered_samples.finalDestinationDate > tbl_received_sample.date_received
      
      GROUP BY tbl_months.month, tbl_months.id ORDER BY tbl_months.id",FALSE);
       $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    } 
     function get_monthly_delayed_samples_at_final_destination()
    {
       $this->db->select("tbl_months.month,count(DISTINCT tbl_registered_samples.sample_id) as received_samples 
       FROM tbl_registered_samples 
       INNER JOIN tbl_received_sample on (tbl_registered_samples.sample_id=tbl_received_sample.sample_id)
       RIGHT OUTER JOIN tbl_months on tbl_months.month = monthname(tbl_received_sample.created_at)
      GROUP BY tbl_months.month, tbl_months.id ORDER BY tbl_months.id",FALSE);
       $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }
    } 
    function get_total_undelayed_samples()
    {
      $this->db->select("count(DISTINCT s.sample_id)as `total_undelayed_samples` FROM `tbl_registered_samples` as `s` inner join tbl_received_sample r on (s.`sample_id`=r.`sample_id`) where 1 
        AND s.`finalDestinationDate`>r.`date_received`
         AND s.`isSampleAtFinal`='YES'", FALSE);
        return $this->db->get()->row()->total_undelayed_samples;

    } 

    function get_total_delayed_samples()
    {
        $this->db->select("count(DISTINCT s.sample_id)as `total_delayed_samples` FROM `tbl_registered_samples` as `s` inner join tbl_received_sample r on (s.`sample_id`=r.`sample_id`) where 1 
          AND s.`finalDestinationDate`<r.`date_received`
         AND s.`isSampleAtFinal`='YES'", FALSE);
        return $this->db->get()->row()->total_delayed_samples;

    }
    function get_total_registered_samples()
    {

        $this->db->select("count(*)as `total_registered_samples` FROM `tbl_registered_samples` where 1", FALSE);
        return $this->db->get()->row()->total_registered_samples;

    }
    
    function get_total_notifications()
    {

        $this->db->select("count(*)as `total_notifications` FROM `tbl_notifications` where 1", FALSE);
        return $this->db->get()->row()->total_notifications;

    }
    function get_total_sample_cphl()
    {

        $this->db->select("count(*)as `total_sample_cphl` FROM `tbl_registered_samples` where 1 AND destination_id='CPHL'", FALSE);
        return $this->db->get()->row()->total_sample_cphl;

    }
    function get_total_sample_idi()
    {

        $this->db->select("count(*)as `total_sample_idi` FROM `tbl_registered_samples` where 1 AND destination_id='IDI'", FALSE);
        return $this->db->get()->row()->total_sample_idi;

    }
    function get_total_sample_dgal()
    {

        $this->db->select("count(*)as `total_sample_dgal` FROM `tbl_registered_samples` where 1 AND destination_id='DGAL'", FALSE);
        return $this->db->get()->row()->total_sample_dgal;

    }
    function get_total_sample_ntlp()
    {

        $this->db->select("count(*)as `total_sample_ntlp` FROM `tbl_registered_samples` where 1 AND destination_id='NTLP'", FALSE);
        return $this->db->get()->row()->total_sample_ntlp;

    }
    function get_total_sample_uvri()
    {

        $this->db->select("count(*)as `total_sample_uvri` FROM `tbl_registered_samples` where 1 AND destination_id='UVRI'", FALSE);
        return $this->db->get()->row()->total_sample_uvri;

    }
     function get_total_samples_in_transit()
    {
       
        $this->db->select("count(DISTINCT s.sample_id)as `total_samples_in_transit` FROM `tbl_registered_samples` as `s` inner join tbl_received_sample r on (s.`sample_id`=r.`sample_id`) where 1 
          and r.date_received !='1970-01-01'and s.`isSampleAtFinal`='NO'
            order by r.`sample_id` asc", FALSE);
        return $this->db->get()->row()->total_samples_in_transit;  
    }
    function get_total_samples_received()
    {
       
        $this->db->select("count(DISTINCT r.sample_id)as `received_samples` FROM `tbl_registered_samples` as `s` inner join tbl_received_sample r on (s.`sample_id`=r.`sample_id`) where 1 
          ", FALSE);
        return $this->db->get()->row()->received_samples;  
    }
     
     function get_received_broken_seals()
    {

        $this->db->select("count(*) as `received_broken_seals` FROM `tbl_received_sample` where 1 AND sample_status like '%Broken%'", FALSE);
        return $this->db->get()->row()->received_broken_seals;

    }
    function get_received_intact()
    {

        $this->db->select("count(*) as `received_intact` FROM `tbl_received_sample` where 1 AND sample_status like '%Intact%'", FALSE);
        return $this->db->get()->row()->received_intact;

    }
    function get_received_open()
    {

        $this->db->select("count(*) as `received_open` FROM `tbl_received_sample` where 1 AND sample_status like '%Open%'", FALSE);
        return $this->db->get()->row()->received_open;

    }
    function get_monthly_registered_samples()
    {
       $this->db->select("tbl_months.month,count(tbl_registered_samples.sample_type_id) as registered_samples 
        FROM tbl_registered_samples RIGHT OUTER JOIN tbl_months ON tbl_months.month = monthname(tbl_registered_samples.created_at)
        GROUP BY tbl_months.month, tbl_months.id ORDER BY tbl_months.id",FALSE);
       $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
    function get_monthly_delayed_samples()
    {
       $this->db->select("tbl_months.month,count(DISTINCT tbl_registered_samples.sample_id) as delayed_samples 
       FROM tbl_registered_samples 
       JOIN tbl_received_sample on (tbl_registered_samples.sample_id=tbl_received_sample.sample_id)
       RIGHT OUTER JOIN tbl_months ON tbl_registered_samples.finalDestinationDate < tbl_received_sample.date_received
       GROUP BY tbl_received_sample.sample_id ORDER BY tbl_received_sample.id",FALSE);
       $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
    function get_monthly_received_samples()
    {
       $this->db->select("tbl_months.month,count(DISTINCT tbl_registered_samples.sample_id) as received_samples 
       FROM tbl_registered_samples 
       INNER JOIN tbl_received_sample on (tbl_registered_samples.sample_id=tbl_received_sample.sample_id)
       RIGHT OUTER JOIN tbl_months on tbl_months.month = monthname(tbl_received_sample.created_at)
      GROUP BY tbl_months.month, tbl_months.id ORDER BY tbl_months.id",FALSE);
       $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
     function get_registered_samples_thisYear()
    {
       $this->db->select("tbl_district.*,tbl_registered_samples.districtCode as code,count(tbl_registered_samples.sample_id) as registered_samples 
        FROM tbl_registered_samples RIGHT OUTER JOIN tbl_district ON (tbl_district.id = tbl_registered_samples.districtCode)
        GROUP BY tbl_district.id, tbl_district.districtName ORDER BY tbl_district.id desc",FALSE);
       $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
    function get_outbreaks()
    {
       $this->db->select("tbl_diseases.name,count(tbl_registered_samples.disease_id) as no_of_outbreaks 
        FROM tbl_registered_samples RIGHT OUTER JOIN tbl_diseases ON tbl_diseases.name = tbl_registered_samples.disease_id
        GROUP BY tbl_diseases.name, tbl_diseases.id ORDER BY tbl_diseases.id",FALSE);
       $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
}