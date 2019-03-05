<?php
if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Reverselookups_model extends CI_Model
{
    public function __construct()
    {
        parent::__construct();
    }

    function get_health_facility($id)
    {
        $this->db->select("name FROM `tbl_facility_codes` WHERE 1 and id=" . $id . "", FALSE);
        return $this->db->get()->row()->name;
    }
   function get_destinations($id)
    {
        $this->db->select("name FROM `tbl_destination` WHERE 1 and id=" . $id . "", FALSE);
        return $this->db->get()->row()->name;
    }
    function get_district($id)
    {
        $this->db->select("district FROM `tbl_facility_codes` WHERE 1 and id=" . $id . "", FALSE);
        return $this->db->get()->row()->district;
    }
    function get_district_code($id)
    {
        $this->db->select("code FROM `tbl_facility_codes` WHERE 1 and id=" . $id . "", FALSE);
        return $this->db->get()->row()->code;
    }
    function get_disease($id)
    {
        $this->db->select("name FROM `tbl_diseases` WHERE 1 and id=" . $id . "", FALSE);
        return $this->db->get()->row()->name;
    }
    function get_sample_type($id)
    {
        $this->db->select("name FROM `tbl_sample_type` WHERE 1 and id=" . $id . "", FALSE);
        return $this->db->get()->row()->name;
    }
        function get_transporter_name($id)
    {
        $this->db->select(" name FROM `tbl_sample_transporters` WHERE 1 and id=" . $id . "", FALSE);
        return $this->db->get()->row()->name;
    }
 function get_suspected_disease($id)
    {
        $this->db->select("disease_id FROM `tbl_registered_samples` WHERE 1 and sample_id=" . $id . "", FALSE);
        return $this->db->get()->row()->disease_id;
    }

    function get_transporter_contact($id)
    {
        $this->db->select("phone FROM `tbl_sample_transporters` WHERE 1 and id=" . $id . "", FALSE);
        return $this->db->get()->row()->phone;
    }
    function get_org_name($grp_id, $user_id, $org_id)
    {
        switch (true) {
            case($grp_id == 1):
                $this->db->select("org_name 
                FROM `tbl_sys_admin` 
                WHERE 1 
                and user_id=" . $user_id . "
                and id=" . $org_id . "                
                ", FALSE);
                $org_name = $this->db->get()->row()->org_name;
                break;

            default:
                $org_name = 'JBC User';
                break;
        }
        return $org_name;
    }

    function get_user_group_name($id)
    {
        $this->db->select("name FROM `tbl_roles` WHERE 1 and id=" . $id . "", FALSE);
        return $this->db->get()->row()->name;
    }

    function get_menu_name($id)
    {
        $this->db->select("`c`.`name`
                            from `tbl_menu_categories` c
                            where 1
                            and c.id='" . $id . "'", FALSE);
        return $this->db->get()->row()->name;
    }

    function get_menu_name_for_sub_item($id)
    {
        $this->db->select("`c`.`name`
                            from `tbl_menu_categories` c
                            join tbl_menu_items i
                            on (i.category_id=c.id)
                            where 1
                            and i.tbl_menu_itemsId='" . $id . "'", FALSE);
        return $this->db->get()->row()->name;
    }

    function get_sub_item_name($id)
    {
        $this->db->select("`i`.`name`
                            from `tbl_menu_items` i
                            where 1
                            and i.tbl_menu_itemsId='" . $id . "'", FALSE);
        return $this->db->get()->row()->name;
    }

    function get_role_name($id)
    {
        $this->db->select("`l`.`name`
                            from `tbl_roles` l
                            where 1
                            and l.id='" . $id . "'", FALSE);
        return $this->db->get()->row()->name;
    }

    function get_user_name($id)
    {
        $this->db->select("fullNames as `name`
                            from `tbl_users` u
                            where 1
                            and u.id='" . $id . "'", FALSE);
        return $this->db->get()->row()->name;
    }
  
    function get_transporter($id)
    {

        $this->db->select("
        case
            when c.`name` = ''
            then 'n/a'
            else
            c.`name`
            end
            as  `name`
            from `tbl_sample_transporters` c
            where 1
            and c.id='" . $id . "'", FALSE);

        return $this->db->get()->row()->name;
    }

  

    function time_elapsed_string($datetime, $full = false) {
    $now = new DateTime;
    $ago = new DateTime($datetime);
    $diff = $now->diff($ago);

    $diff->w = floor($diff->d / 7);
    $diff->d -= $diff->w * 7;

    $string = array(
        'y' => 'year',
        'm' => 'month',
        'w' => 'week',
        'd' => 'day',
        'h' => 'hour',
        'i' => 'minute',
        's' => 'second',
    );
    foreach ($string as $k => &$v) {
        if ($diff->$k) {
            $v = $diff->$k . ' ' . $v . ($diff->$k > 1 ? 's' : '');
        } else {
            unset($string[$k]);
        }
    }

    if (!$full) $string = array_slice($string, 0, 1);
    return $string ? implode(', ', $string) . ' ago' : 'just now';
}
}