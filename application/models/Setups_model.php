<?php
if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Setups_model extends CI_Model
{


    public function __construct()
    {
        parent::__construct();
    }

    function get_system_roles($id)
    {
        $append_id = ($id !== '') ? 'and r.id=' . $id . '' : '';

        $this->db->select("
        r.`id`,
         r.`name`, 
         u.`fullNames` as `creator`,
          r.`date_entry`,
          r.display
        FROM `tbl_roles` r
        join tbl_users  u on
         (u.id=r.`entered_by`)        
        WHERE 1 
        " . $append_id . " 
        order by r.id desc", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }

    function get_all_permitted_roles($id)
    {
        $append_id = ($id !== '') ? 'and r.id=' . $id . '' : '';

        $this->db->select("
        r.`id` as `current_role_id`,
         r.`name`, 
         u.`fullNames` as `creator`,
          max(p.`entry_date`) as `entry_date`,
          r.display
        FROM `tbl_roles` r
        join tbl_permissions p
         on (p.role_id=r.`id`)  
        join tbl_users  u on
         (u.id=p.`entered_by`) 
               
        WHERE 1 
        " . $append_id . " 
        and p.display=1
        group by p.role_id
        order by r.id desc", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }


    function get_all_menu_items()
    {

        $this->db->select("*
        FROM `tbl_menu_categories`
        WHERE 1
        order by id desc", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
    function get_transporter_name()
    {
        $this->db->select("`c`.`id`,
                        `c`.`name`
                        FROM `tbl_sample_transporters` as `c`
                        WHERE 1
                        order by `c`.`id` ASC", FALSE);
        $query = $this->db->get();
        $result = $query->result();
        $id = array('');
        $name = array('-Select transporter-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($id, $result[$i]->id);
            array_push($name, $result[$i]->name);
        }
        return $transporter_result = array_combine($id, $name);
    }
    function get_all_sub_menu_items()
    {

        $this->db->select("
        i.*,
        c.name as `menu_name`
        FROM `tbl_menu_items` i
        join `tbl_menu_categories` c 
        on (c.id=i.category_id)
        WHERE 1
        order by i.category_id,i.rank,i.tbl_menu_itemsId desc", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }

    function get_role_permissions($id)
    {
        $id = ($id == '') ? 0 : $id;
        $this->db->select("`id`, `name`, `entered_by`, `date_entry`, `display`
         FROM `tbl_roles`
          WHERE 1
          and id=" . $id . "", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }

    function get_role_name($id)
    {
        $this->db->select("`id`, `name`, `entered_by`, `date_entry`, `display`
         FROM `tbl_roles`
          WHERE 1
          and id=" . $id . "", FALSE);
        return $this->db->get()->row()->name;
    }
    function get_menu_cat_id()
    {
        $this->db->select("max(`id`) as `id`, `name`, `entered_by`, `date_entry`, `display`
         FROM `tbl_roles`
          WHERE 1", FALSE);
        return $this->db->get()->row()->id;
    }
   function get_last_role_id()
    {
        $this->db->select("max(`id`) as `id`, `name`, `entered_by`, `date_entry`, `display`
         FROM `tbl_roles`
          WHERE 1", FALSE);
        return $this->db->get()->row()->id;
    }
    function get_menu_category_id($menu_item_value)
    {
        $this->db->select('category_id');
        $this->db->select('tbl_menu_itemsId');
        $this->db->from('tbl_menu_items');
        $this->db->where('tbl_menu_itemsId=' . $menu_item_value . '');
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
    function get_highest_role_id()
    {
        $this->db->select("max(`id`) as `id`
         FROM `tbl_roles`
          WHERE 1", FALSE);
        return $this->db->get()->row()->id;
    }

    function check_if_user_grp_exists($user_grp_id)
    {
        $this->db->select("`id`, `name`, `entered_by`, `date_entry`, `display`
         FROM `tbl_roles`
          WHERE 1
          and id=" . $user_grp_id . "
          group by id", FALSE);
        $var = $this->db->get()->row()->id;
        $return_var = ($var >= 1) ? 1 : 0;
        return $return_var;
    }
    function record_count_get_all_system_roles()
    {

        $this->db->select("`id`,
         `name`, `entered_by`, `date_entry`
        FROM `tbl_roles`
        WHERE 1
        order by id desc", FALSE);
        $db_rows = $this->db->get();
        $result = $db_rows->num_rows();

        return $result;

    }
      function get_all_system_roles()
    {

        $this->db->select("`id`,
         `name`, `entered_by`, `date_entry`
        FROM `tbl_roles`
        WHERE 1
        order by id desc", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
    function get_all_system_role_details($id)
    {
        $append_id=($id!=='')?'and id='.$id.'':'';
        $this->db->select("
        `id`, 
        `name`,
        `entered_by`,
        `date_entry`,
        `display`FROM `tbl_roles` 
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
    function get_all_system_users($limit, $start)
    {

        $this->db->select("`u`.`tbl_system_user_id`,
                            `u`.`fullNames`,
                            `u`.`username`,
                            `u`.`role_id`,
                            `r`.`name` as `role_name`,
                            `u`.`country`,
                            `u`.`email`,
                            `u`.`status`,
                            `u`.`display`,
                            `u`.`emailStatus`,
                            `u`.`passwordReset`,
                            `u`.`ResetConfirmed`,
                            `u`.`date_entry`
                            FROM `tbl_system_users` as `u`
                            left join `tbl_roles` as `r`
                            on (`r`.`id`=`u`.`role_id`)
                            WHERE 1
                            and `u`.`display`='Yes'
                            order by `tbl_system_user_id` desc", FALSE);
        $this->db->limit($limit, $start);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }


    function record_count_get_all_system_users()
    {

        $this->db->select("`tbl_system_user_id`,
        `fullNames`,
        `username`,
        `role_id`, `country`, `email`,
        `status`, `display`,
        `emailStatus`,
        `passwordReset`,
        `ResetConfirmed`,
        `date_entry`
        FROM `tbl_system_users`
        WHERE 1
        order by `tbl_system_user_id` desc", FALSE);
        $db_rows = $this->db->get();
        $result = $db_rows->num_rows();

        return $result;

    }

    function get_menu_items()
    {

        $this->db->select("
            c.`id`,
            c.`name`,
            c.`awesome_icon`,
            c.`controller`,
            c.`rank`,
            c.`page`,
            c.`has_sub_menu`
            FROM `tbl_menu_categories` as c
            inner join tbl_permissions p
            on (p.menu_cat_id=c.id)
            WHERE c.`display`='1'
            and  p.`display`='Yes'
            and p.role_id=" . $this->session->userdata['user_group_id'] . "
            group by c.`id`
            order by c.id,c.rank asc", FALSE);

        $db_rows = $this->db->get();


        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }
    }

    function get_sub_menu_items()
    {

        $this->db->select("`i`.`tbl_menu_itemsId`,
                            `i`.`category_id`,
                            `i`.`name`,
                            `i`.`sub_controller`,
                            `i`.`rank`,
                            `i`.`has_sub_menu`
                            from `tbl_menu_items` as `i`
                            inner join tbl_permissions p
                            on (p.menu_cat_id=i.category_id)
                            where p.role_id=" . $this->session->userdata['user_group_id'] . "
                            and  i.`display`=1
                            and  p.`display`=1
                            order by `i`.`category_id`,`i`.rank asc", FALSE);

        $db_rows = $this->db->get();


        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }
    }

    function get_sub_sub_menu_items()
    {

        $this->db->select("`tbl_sub_menu_itemsId`, `category_id`, `sub_category_id`, `name`, `sub_controller`, `rank`
        FROM `tbl_sub_menu_items`
        WHERE `display`='Yes'
        order by `category_id`,`sub_category_id`,`rank` asc", FALSE);

        $db_rows = $this->db->get();


        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }
    }


    function get_facility_codes($location)
    {
        $role_id=$this->session->userdata['role_id'];
        if($role_id==1){
        $this->db->select("`c`.`id`,
                        `c`.`name`
                        FROM `tbl_facility_codes` as `c`
                        WHERE 1 
                        order by `c`.`name` ASC", FALSE);

        }else{
            $this->db->select("`c`.`id`,
                        `c`.`name`
                        FROM `tbl_facility_codes` as `c`
                        WHERE 1 and `c`.`code`='".$location."'
                        order by `c`.`name` ASC", FALSE);

        }
        $query = $this->db->get();
        $result = $query->result();
        $facility_codes_id = array('');
        $facility_codes_name = array('-Select-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($facility_codes_id, $result[$i]->id);
            array_push($facility_codes_name, $result[$i]->name);
        }
        return $facility_codes_result = array_combine($facility_codes_id, $facility_codes_name);
    }
    function get_facility_code()
    {
        $user_id=$this->session->userdata['role_id'];
        if($user_id==1){
             $this->db->select("`c`.`id`,
                        `c`.`name`
                        FROM `tbl_facility_codes` as `c`
                        WHERE 1
                        order by `c`.`name` ASC", FALSE);
         }else{
        $this->db->select("`c`.`id`,
                        `c`.`name`
                        FROM `tbl_facility_codes` as `c`
                        WHERE 1 and `c`.`code`='".$location."'
                        order by `c`.`name` ASC", FALSE);
         }
        $query = $this->db->get();
        $result = $query->result();
        $facility_codes_id = array('');
        $facility_codes_name = array('-Select-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($facility_codes_id, $result[$i]->id);
            array_push($facility_codes_name, $result[$i]->name);
        }
        return $facility_codes_result = array_combine($facility_codes_id, $facility_codes_name);
    }
    function get_districts()
    {
        $this->db->select("`c`.`id`,
                        `c`.`districtName`
                        FROM `tbl_district` as `c`
                        WHERE 1
                        order by `c`.`districtName` ASC", FALSE);
        $query = $this->db->get();
        $result = $query->result();
        $districtCode = array('');
        $districtName = array('-Select district-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($districtCode, $result[$i]->id);
            array_push($districtName, $result[$i]->districtName);
        }
        return $district_result = array_combine($districtCode, $districtName);
    }
    function get_districts_codes()
    {
        $this->db->select("`c`.`id`,
                        `c`.`districtName`
                        FROM `tbl_district` as `c`
                        WHERE 1
                        order by `c`.`districtName` ASC", FALSE);
        $query = $this->db->get();
        $result = $query->result();
        $districtCode = array('');
        $districtName = array('-Select district-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($districtCode, $result[$i]->id);
            array_push($districtName, $result[$i]->districtName);
        }
        return $district_result = array_combine($districtCode, $districtName);
    }
    function get_is_destination()
    {
        $this->db->select("`c`.`id`,
                        `c`.`status`
                        FROM `is_destination` as `c`
                        WHERE 1
                        order by `c`.`id` ASC", FALSE);
        $query = $this->db->get();
        $result = $query->result();
        $isDestination_id = array('');
        $isDestination_Name = array('-Select-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($isDestination_id, $result[$i]->status);
            array_push($isDestination_Name, $result[$i]->status);
        }
        return $district_result = array_combine($isDestination_id, $isDestination_Name);
    }
    function get_user_roles()
    {
        $this->db->select("`r`.`id`,
                            `r`.`name`
                        from `tbl_roles` as `r`
                        where `r`.`display` =1
                        order by `r`.`name` ASC", FALSE);
        $query = $this->db->get();
        $result = $query->result();

        //array to store department id & department name
        $id = array('');
        $name = array('-select user role-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($id, $result[$i]->id);
            array_push($name, $result[$i]->name);
        }
        return $lookup_result = array_combine($id, $name);
    }

    function get_destination()
    {
        $this->db->select('id');
        $this->db->select('name');
        $this->db->from('tbl_destination');
        $this->db->order_by('name', 'asc');
        $query = $this->db->get();
        $result = $query->result();

        $destination_id = array('');
        $destination_name = array('-Select destination-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($destination_id, $result[$i]->id);
            array_push($destination_name, $result[$i]->name);
        }
        return $destination_result = array_combine($destination_id, $destination_name);
    }
      function get_sample_status()
    {
        $this->db->select('id');
        $this->db->select('status');
        $this->db->from('tbl_sample_status');
        $this->db->order_by('status', 'asc');
        $query = $this->db->get();
        $result = $query->result();

        $status_id = array('');
        $status_name = array('-Select Sample Status-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($status_id, $result[$i]->id);
            array_push($status_name, $result[$i]->status);
        }
        return $status_result = array_combine($status_name, $status_name);
    }
     function get_sample_id()
    {
        $this->db->select('id');
        $this->db->select('sample_id');
        $this->db->from('tbl_registered_samples');
        $this->db->where('isSampleAtFinal', 'NO');
        $this->db->order_by('id', 'asc');
        $query = $this->db->get();
        $result = $query->result();
        $sample_id = array('');
        $sample_name = array('-Select Sample ID-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($sample_id, $result[$i]->sample_id);
            array_push($sample_name, $result[$i]->sample_id);
        }
        return $status_result = array_combine($sample_id, $sample_name);
    }
 function fetch_destination($sample_id)
 {
  $this->db->where('sample_id', $sample_id);
  $this->db->order_by('id', 'ASC');
  $query = $this->db->get('tbl_registered_samples');
  $output='';
  foreach($query->result() as $row)
  {
   $output .= '<option value="'.$row->destination_id.'">'.$row->destination_id.'</option>';
  }
  return $output;
 }
 function fetch_disease($sample_id)
 {
  $this->db->where('sample_id', $sample_id);
  $this->db->order_by('id', 'ASC');
  $query = $this->db->get('tbl_registered_samples');
  $output='';
  foreach($query->result() as $row)
  {
   $output .= '<option value="'.$row->disease_id.'">'.$row->disease_id.'</option>';
  }
  return $output;
 }
function delete_user($id)
    {

        $status = 'Deactivated';
        $data = array('status' => $status);
        $this->db->where('tbl_system_user_id', $id);
        $this->db->update('tbl_system_users', $data);
    }

    function get_user_record($id)
    {
        $this->db->select("
        `u`.`tbl_system_user_id`,
        `u`.`fullNames`,
        `u`.`username`,
        `u`.`password`,
        `u`.`EncryptionHint`,
        `u`.`role_id`,
        `u`.`country`,
        `u`.`email`,
        `u`.`tel_mobile`,
        `u`.`status`,
        `u`.`display`,
        `u`.`emailStatus`,
        `u`.`passwordReset`,
        `u`.`ResetConfirmed`,
        `u`.`date_entry`
        FROM `tbl_system_users` as `u`
        join `tbl_roles` r
        on(`r`.`id`=`u`.`role_id`)
        WHERE 1
        and `u`.tbl_system_user_id=" . $id . "
        and `r`.`display`='Yes'
        order by `u`.tbl_system_user_id desc", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }
    }

    function get_user_name($id)
    {
        $this->db->select(" `u`.`fullNames`
                            from `tbl_system_users` u
                            where 1
                            and u.tbl_system_user_id='" . $id . "'", FALSE);
        return $this->db->get()->row()->fullNames;
    }

    function get_fm_name($id)
    {
        $this->db->select("* from `tbl_fund_managers`
                            where 1
                             and id='" . $id . "'", FALSE);
        return $this->db->get()->row()->name;
    }

    function drop_down_roles()
    {
        $this->db->select("`r`.`id`,
                        `r`.`name`
                        FROM `tbl_roles` as `r`
                        WHERE `r`.`display` =1
                        and `r`.`id` <>1
                        order by `r`.`name` ASC", FALSE);
        $query = $this->db->get();
        $result = $query->result();


        $id = array('');
        $name = array('-select role-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($id, $result[$i]->id);
            array_push($name, $result[$i]->name);
        }
        return $lookup_result = array_combine($id, $name);
    }

function get_disease_names()
    {
        $this->db->select('id');
        $this->db->select('name');
        $this->db->from('tbl_diseases');
        $this->db->order_by('name', 'asc');
        $query = $this->db->get();
        $result = $query->result();

        $disease_id = array('');
        $disease_name = array('-Please select the disease-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($disease_id, $result[$i]->id);
            array_push($disease_name, $result[$i]->name);
        }
        return $disease_result = array_combine($disease_id, $disease_name);
    }
    function get_sample_type()
    {
        $this->db->select('id');
        $this->db->select('name');
        $this->db->from('tbl_sample_type');
        $this->db->order_by('name', 'asc');
        $query = $this->db->get();
        $result = $query->result();

        $sample_type_id = array('');
        $sample_type_name = array('-Please select sample type-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($sample_type_id, $result[$i]->id);
            array_push($sample_type_name, $result[$i]->name);
        }
        return $sample_type_result = array_combine($sample_type_id, $sample_type_name);
    }
    function fetch_sample_id()
    {
        $this->db->select('id');
        $this->db->select('sample_id');
        $this->db->from('tbl_received_sample');
        $this->db->where('is_destination', 'YES');
        $this->db->order_by('id', 'asc');
        $query = $this->db->get();
        $result = $query->result();
        $sample_id = array('');
        $sample_name = array('-Select Sample ID-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($sample_id, $result[$i]->sample_id);
            array_push($sample_name, $result[$i]->sample_id);
        }
        return $status_result = array_combine($sample_id, $sample_name);
    }
    public function check_registered_sample($sample_id)
    {
        $this->db->select('*');
        $this->db->from('tbl_test_results');
        $this->db->where(array('sample_id'=>$sample_id));
        $db_rows = $this->db->get();
        return $db_rows->num_rows();
    }
    function get_all_sample_results()
    {

        $this->db->select("*
        FROM `tbl_test_results`
        WHERE 1
        order by id asc", FALSE);

        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }
    }
}