<?php
/**
 * Created by PhpStorm.
 * User: aasiimwe
 * Date: 9/25/2015
 * Time: 11:53 AM
 */
if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Lookups_model extends CI_Model
{


    public function __construct()
    {
        parent::__construct();
    }


    function get_gender()
    {
        $this->db->select('tbl_lookup_genderId');
        $this->db->select('name');
        $this->db->from('tbl_lookup_gender');
        $this->db->where('display="Yes"');
        $this->db->order_by('name', 'asc');
        $query = $this->db->get();
        $result = $query->result();

        $tbl_lookup_genderId = array('');
        $gender_name = array('-select gender-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($tbl_lookup_genderId, $result[$i]->tbl_lookup_genderId);
            array_push($gender_name, $result[$i]->name);
        }
        return $gender_result = array_combine($tbl_lookup_genderId, $gender_name);
    }

    function get_marital_status()
    {
        $this->db->select('tbl_lookup_marital_statusId');
        $this->db->select('name');
        $this->db->from('tbl_lookup_marital_status');
        $this->db->where('display="Yes"');
        $this->db->order_by('name', 'asc');
        $query = $this->db->get();
        $result = $query->result();

        $tbl_lookup_marital_statusId = array('');
        $marital_status_name = array('-select marital status-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($tbl_lookup_marital_statusId, $result[$i]->tbl_lookup_marital_statusId);
            array_push($marital_status_name, $result[$i]->name);
        }
        return $marital_status_result = array_combine($tbl_lookup_marital_statusId, $marital_status_name);
    }

    function get_applicant_type()
    {
        $this->db->select('tbl_lookup_applicant_typeId');
        $this->db->select('name');
        $this->db->from('tbl_lookup_applicant_type');
        $this->db->where('display="Yes"');
        $this->db->order_by('name', 'asc');
        $query = $this->db->get();
        $result = $query->result();

        $tbl_lookup_applicant_typeId = array('');
        $applicant_type_name = array('-select applicant type-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($tbl_lookup_applicant_typeId, $result[$i]->tbl_lookup_applicant_typeId);
            array_push($applicant_type_name, $result[$i]->name);
        }
        return $applicant_type_result = array_combine($tbl_lookup_applicant_typeId, $applicant_type_name);
    }

    function get_identification_type()
    {
        $this->db->select('tbl_lookup_identification_typeId');
        $this->db->select('name');
        $this->db->from('tbl_lookup_identification_type');
        $this->db->where('display="Yes"');
        $this->db->order_by('name', 'asc');
        $query = $this->db->get();
        $result = $query->result();

        $tbl_lookup_identification_typeId = array('');
        $identification_type_name = array('-select identification type-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($tbl_lookup_identification_typeId, $result[$i]->tbl_lookup_identification_typeId);
            array_push($identification_type_name, $result[$i]->name);
        }
        return $identification_type_result = array_combine($tbl_lookup_identification_typeId, $identification_type_name);
    }

    function get_countries()
    {
        $this->db->select('tbl_countriesId');
        $this->db->select('name');
        $this->db->from('tbl_lookup_countries');
        $this->db->where('display="Yes"');
        $this->db->order_by('name', 'asc');
        $query = $this->db->get();
        $result = $query->result();

        $tbl_lookup_countriesId = array('');
        $countries_name = array('-select country-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($tbl_lookup_countriesId, $result[$i]->tbl_countriesId);
            array_push($countries_name, $result[$i]->name);
        }
        return $countries_result = array_combine($tbl_lookup_countriesId, $countries_name);
    }

    function get_boolean_response()
    {
        $this->db->select('tbl_lookup_boolean_responseId');
        $this->db->select('name');
        $this->db->from('tbl_lookup_boolean_response');
        $this->db->where('display="Yes"');
        $this->db->order_by('name', 'asc');
        $query = $this->db->get();
        $result = $query->result();

        $tbl_lookup_boolean_responseId = array('');
        $boolean_response_name = array('-select response-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($tbl_lookup_boolean_responseId, $result[$i]->tbl_lookup_boolean_responseId);
            array_push($boolean_response_name, $result[$i]->name);
        }
        return $boolean_response_result = array_combine($tbl_lookup_boolean_responseId, $boolean_response_name);
    }

    function get_joint_instruction_signer()
    {
        $this->db->select('tbl_lookup_joint_instructions_to_be_signed_byId');
        $this->db->select('name');
        $this->db->from('tbl_lookup_joint_instructions_to_be_signed_by');
        $this->db->where('display="Yes"');
        $this->db->order_by('name', 'asc');
        $query = $this->db->get();
        $result = $query->result();

        $tbl_lookup_joint_instructions_to_be_signed_byId = array('');
        $joint_instructions_to_be_signed_by_name = array('-select joint application instruction signer-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($tbl_lookup_joint_instructions_to_be_signed_byId, $result[$i]->tbl_lookup_joint_instructions_to_be_signed_byId);
            array_push($joint_instructions_to_be_signed_by_name, $result[$i]->name);
        }
        return $joint_instructions_to_be_signed_by_result = array_combine($tbl_lookup_joint_instructions_to_be_signed_byId, $joint_instructions_to_be_signed_by_name);
    }

    function get_payment_method()
    {
        $this->db->select('tbl_lookup_payment_methodId');
        $this->db->select('name');
        $this->db->from('tbl_lookup_payment_method');
        $this->db->where('display="Yes"');
        $this->db->order_by('name', 'asc');
        $query = $this->db->get();
        $result = $query->result();

        $tbl_lookup_payment_methodId = array('');
        $payment_method_name = array('-select payment method-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($tbl_lookup_payment_methodId, $result[$i]->tbl_lookup_payment_methodId);
            array_push($payment_method_name, $result[$i]->name);
        }
        return $result = array_combine($tbl_lookup_payment_methodId, $payment_method_name);
    }

    function get_banker_fund_inflow_account()
    {
        $this->db->select('tbl_lookup_banker_fund_inflow_accountId');
        $this->db->select('name');
        $this->db->from('tbl_lookup_banker_fund_inflow_account');
        $this->db->where('display="Yes"');
        $this->db->order_by('name', 'asc');
        $query = $this->db->get();
        $result = $query->result();

        $id = array('');
        $name = array('-select banker fund inflow account-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($id, $result[$i]->tbl_lookup_banker_fund_inflow_accountId);
            array_push($name, $result[$i]->name);
        }
        return $result = array_combine($id, $name);
    }

    function get_banker()
    {
        $this->db->select('tbl_lookup_banksId');
        $this->db->select('name');
        $this->db->from('tbl_lookup_banks');
        $this->db->where('display="Yes"');
        $this->db->order_by('name', 'asc');
        $query = $this->db->get();
        $result = $query->result();

        $id = array('');
        $name = array('-select banker-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($id, $result[$i]->tbl_lookup_banksId);
            array_push($name, $result[$i]->name);
        }
        return $result = array_combine($id, $name);
    }

    function get_bank_account_type()
    {
        $this->db->select('tbl_lookup_account_typeId');
        $this->db->select('name');
        $this->db->from('tbl_lookup_account_type');
        $this->db->where('display="Yes"');
        $this->db->order_by('name', 'asc');
        $query = $this->db->get();
        $result = $query->result();

        $id = array('');
        $name = array('-select account type-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($id, $result[$i]->tbl_lookup_account_typeId);
            array_push($name, $result[$i]->name);
        }
        return $result = array_combine($id, $name);
    }

    function get_menu_category($user_group)
    {
        $role = ($user_group == '') ? 0 : $user_group;
        $this->db->select("c.`id`,
                            c.`name`,
                            c.`awesome_icon`,
                            c.`controller`,
                            c.`rank`
                            FROM 
                            `tbl_menu_categories` c 
                            join tbl_permissions p
                            on (p.`menu_cat_id`=c.`id`)
                            join tbl_roles r
                            on (r.id=p.role_id)
                            WHERE 1
                            and r.id=" . $user_group . "
                            and  c.`display`=1
                            and r.display=1
                            and p.display=1
                            GROUP by c.id,r.id
                            order by c.`rank`", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }

    function get_menu_name($controller_name)
    {
        $this->db->select("`c`.`id`,
                            `c`.`name`,
                            c.controller
                            from `tbl_menu_categories` c
                            where `c`.`display`=1
                            and c.controller='" . $controller_name . "'", FALSE);
        return $this->db->get()->row()->name;
    }

    function get_menu_icon($controller_name)
    {
        $this->db->select("`c`.`id`,
                            `c`.`name`,
                            c.awesome_icon
                            from `tbl_menu_categories` c
                            where `c`.`display`=1
                            and c.controller='" . $controller_name . "'", FALSE);
        return $this->db->get()->row()->awesome_icon;
    }

    function get_sub_menu_item_name($id)
    {
        $this->db->select("`i`.`tbl_menu_itemsId`,
                            `i`.`name`,
                            `i`.`sub_controller`
                            from `tbl_menu_items` as `i`
                            where i.`display`=1
                            and `i`.`tbl_menu_itemsId`='" . $id . "'", FALSE);
        return $this->db->get()->row()->name;

    }

    function get_sub_menu_item_sub_controller($id)
    {
        $this->db->select("`i`.`tbl_menu_itemsId`,
                            `i`.`name`,
                            `i`.`sub_controller`
                            from `tbl_menu_items` as `i`
                            where i.`display`=1
                            and `i`.`tbl_menu_itemsId`='" . $id . "'", FALSE);
        return $this->db->get()->row()->sub_controller;

    }

    function get_sub_menu_item($sub_controller_name)
    {
        $this->db->select("`i`.`tbl_menu_itemsId`,
                            `i`.`name`,
                            `i`.`sub_controller`
                            from `tbl_menu_items` as `i`
                            where i.`display`=1
                            and `i`.`sub_controller`='" . $sub_controller_name . "'", FALSE);
        return $this->db->get()->row()->name;

    }

    function get_all_menu_items()
    {
        $this->db->select("`i`.`tbl_menu_itemsId`,
                            `i`.`category_id`,
                            `i`.`name`,
                            `i`.`sub_controller`
                            from `tbl_menu_items` as `i`
                            where i.`display`=1
                            order by `i`.`rank`", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }

    function get_all_menu_items_by_module()
    {
        $this->db->select("`tbl_menu_itemsId`, `category_id`, `name`, `sub_controller`, `rank`, `display`
                            from tbl_menu_items
                            WHERE 1
                            and `display`=1
                            order by  category_id,`tbl_menu_itemsId`", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }

    function get_all_menu_items_permitted($menu_cat_id, $member_role)
    {
        $this->db->select("p.`menu_cat_id`,
                            p.`menu_item_id`
                            FROM `tbl_permissions` p
                            where
                            p.menu_cat_id=" . $menu_cat_id . "
                            and p.role_id=" . $member_role . "
                            and p.display='Yes'
                            order by menu_item_id asc", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }

    function get_all_menu_categories()
    {
        $this->db->select("
                            `id`,
                            `name`,
                            `rank`
                            FROM `tbl_menu_categories`
                            WHERE 1
                            and  `display`=1
                            order by `rank`", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
   function get_permissions($menu_cat_id, $item_id, $role_id)
    {
        $menu_cat_id = ($menu_cat_id !== '') ? $menu_cat_id : 0;
        $item_id = ($item_id !== '') ? $item_id : 0;
        $role_id = ($role_id !== '') ? $role_id : 0;
        $this->db->select("`id`, `role_id`, `menu_cat_id`,`menu_item_id`,
         `entry_date`, `entered_by`, `display`
        FROM `tbl_permissions`
        WHERE 1
        and `menu_cat_id`=" . $menu_cat_id . "
        and `menu_item_id`=" . $item_id . "
        and role_id=" . $role_id . "
        and display='1'", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
    /*function get_permissions($menu_cat_id, $item_id, $user_group_id)
    {
        $menu_cat_id = ($menu_cat_id !== '') ? $menu_cat_id : 0;
        $item_id = ($item_id !== '') ? $item_id : 0;
        $user_group_id = ($user_group_id !== '') ? $user_group_id : 0;
        $this->db->select("        
        r.`id`, 
        r.`user_group_id`, 
        r.`menu_cat_id`,
        r.`menu_item_id`,
        r.`display`
        FROM         
        `tbl_roles` as `r`  
        join tbl_permissions p
        on (p.`role_id`=r.`id`)      
        WHERE 1
        and `r`.`menu_cat_id`=" . $menu_cat_id . "
        and `r`.`menu_item_id`=" . $item_id . "
        and r.user_group_id=" . $user_group_id . "        
        and `r`.display=1
        and p.display=1        
        ", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }

*/
    function get_all_system_modules($id)
    {
        $append_id = ($id !== '') ? 'and id=' . $id . '' : '';
        $this->db->select("*
                        FROM `tbl_menu_categories` 
                        WHERE 1
                        " . $append_id . "
                        order by `id`", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
    function get_all_system_roles($id)
    {
        $append_id = ($id !== '') ? 'and id=' . $id . '' : '';
        $this->db->select("*
                        FROM `tbl_roles`
                        WHERE 1
                        " . $append_id . "
                        order by `id`", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }

    function get_menu_cat_drop_down()
    {
        $this->db->select('id');
        $this->db->select('name');
        $this->db->from('tbl_menu_categories');
        $this->db->where('display=1');
        $this->db->order_by('name', 'asc');
        $query = $this->db->get();
        $result = $query->result();

        $id = array('');
        $name = array('-select-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($id, $result[$i]->id);
            array_push($name, $result[$i]->name);
        }
        return array_combine($id, $name);
    }

    function get_all_sub_menu_items($id)
    {
        $append_id = ($id !== '') ? 'and tbl_menu_itemsId=' . $id . '' : '';
        $this->db->select("`tbl_menu_itemsId`, `category_id`, `name`, `sub_controller`, `rank`, display
                            FROM `tbl_menu_items`
                            WHERE 1
                            " . $append_id . "
                            order by `rank`", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }

    function get_not_yet_permitted_roles()
    {
        $this->db->select('r.id,
                            r.name 
                            FROM 
                            `tbl_roles` r
                            where 1
                            and r.id not in (
                            SELECT role_id FROM `tbl_permissions` GROUP by role_id)
                            ORDER BY r.`name` ASC', FALSE);
        $query = $this->db->get();
        $result = $query->result();

        $id = array('');
        $name = array('-select-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($id, $result[$i]->id);
            array_push($name, $result[$i]->name);
        }
        return array_combine($id, $name);
    }

    function get_titles_drop_down()
    {
        $this->db->select('tbl_lookup_titlesId');
        $this->db->select('name');
        $this->db->from('tbl_lookup_titles');
        $this->db->where('display="Yes"');
        $this->db->order_by('name', 'asc');
        $query = $this->db->get();
        $result = $query->result();

        $id = array('');
        $name = array('-select title-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($id, $result[$i]->tbl_lookup_titlesId);
            array_push($name, $result[$i]->name);
        }
        return $drp_result = array_combine($id, $name);
    }

    function get_clubs_drop_down()
    {
        $this->db->select('tbl_lookup_employee_clubsId');
        $this->db->select('name');
        $this->db->from('tbl_lookup_employee_clubs');
        $this->db->where('display="1"');
        $this->db->order_by('tbl_lookup_employee_clubsId', 'asc');
        $query = $this->db->get();
        $result = $query->result();

        $id = array('');
        $name = array('-select club-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($id, $result[$i]->tbl_lookup_employee_clubsId);
            array_push($name, $result[$i]->name);
        }
        return $drp_result = array_combine($id, $name);
    }
    function get_booking_amounts_drop_down()
    {
        $this->db->select('tbl_lookup_employee_clubsId');
        $this->db->select('amount_paid');
        $this->db->from('tbl_lookup_employee_clubs');
        $this->db->where('display="1"');
        $this->db->order_by('tbl_lookup_employee_clubsId', 'asc');
        $query = $this->db->get();
        $result = $query->result();

        $id = array('');
        $name = array('-select Amount-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($id, $result[$i]->tbl_lookup_employee_clubsId);
            array_push($name, $result[$i]->amount_paid);
        }
        return $drp_result = array_combine($id, $name);
    }

    function get_customers_drop_down()
    {
        $this->db->select('custId');
        $this->db->select('concat(firstname," ", lastname) as name');
        $this->db->from('tbl_customer_profile');
        $this->db->where('display="1"');
        $this->db->order_by('firstname', 'asc');
        $query = $this->db->get();
        $result = $query->result();

        $id = array('');
        $name = array('-select customer-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($id, $result[$i]->custId);
            array_push($name, $result[$i]->name);
        }
        return $drp_result = array_combine($id, $name);
    }

    function get_employee_drop_down()
    {
        $this->db->select('id as employeeId');
        $this->db->select('fullNames as name');
        $this->db->from('tbl_users');
        $this->db->where('role_id=3');
        $this->db->where('display="Yes"');
        $this->db->order_by('fullNames', 'asc');
        $query = $this->db->get();
        $result = $query->result();
        $id = array('');
        $name = array('-select employee-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($id, $result[$i]->employeeId);
            array_push($name, $result[$i]->name);
        }
        return $drp_result = array_combine($id, $name);
    }

    function get_user_roles()
    {
        $this->db->select('id');
        $this->db->select('name');
        $this->db->from('tbl_roles');
        $this->db->where('display="1"');
        $this->db->order_by('name', 'asc');
        $query = $this->db->get();
        $result = $query->result();

        $id = array('');
        $name = array('-select Role-');

        for ($i = 0; $i < count($result); $i++) {
            array_push($id, $result[$i]->id);
            array_push($name, $result[$i]->name);
        }
        return $drp_result = array_combine($id, $name);
    }


}