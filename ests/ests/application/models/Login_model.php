<?php
/**
 * Created by Asiimwe Apollo.
 * User: aasiimwe
 * Date: 9/19/2015
 * Time: 11:17 PM
 */

if (!defined('BASEPATH')) exit('No direct script access allowed');

class Login_model extends CI_Model
{
    function __construct()
    {

        parent::__construct();
    }


    function authenticate_user($username, $password)
    {
        $this->db->select("`u`.`id` as `user_id`,
                    `u`.`user_group_id`,
                    `u`.`role_id`,
                    `u`.`org_id`,
                    `u`.`fullNames`,
                    `u`.`userName`,
                    `u`.`password`,
                    `u`.`location`,
                    `u`.`status`
                    from `tbl_users` as `u`
                    left join `tbl_roles` as `r` 
                    on(`r`.`id`=`u`.`user_group_id`)
                    where 1 
                    and `u`.`username`='" . $username . "'
                    and `u`.`password`= md5('" . $password . "')
                    and `u`.`status` like 'Active'", FALSE);
        $db_rows = $this->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }

    function check_user($username, $password)
    {
        $this->db->select("`u`.`id` as `user_id`,
                    `u`.`user_group_id`,
                    `u`.`fullNames`,
                    `u`.`userName`,
                    `u`.`password`,
                    `u`.`location`,
                    `u`.`status`
                    from `tbl_users` as `u`
                    left join `tbl_roles` as `r` 
                    on(`r`.`id`=`u`.`user_group_id`)
                    where 1 
                    and `u`.`username`='" . $username . "'
                    and `u`.`password`= md5('" . $password . "')
                    and `u`.`status` like 'Active'", FALSE);

        $db_rows = $this->db->get();
        return $db_rows->num_rows();
    }

    function Login_systemLogs()
    {
        $user_name = $this->session->userdata['user_name'];
        $ipAddress = $this->session->userdata['ip_address'];
        $user_id = $this->session->userdata['user_id'];
        $user_role = $this->session->userdata['user_group_id'];

        $data = array(
            'user_id' => $user_id,
            'user_name' => $user_name,
            'user_group_id' => $user_role,
            'ip_address' => $ipAddress
        );
        $this->db->insert('tbl_login', $data);
    }

public function getUserInfoByEmail($email)
    {
        $q = $this->db->get_where('tbl_users', array('email' => $email), 1);  
        if($this->db->affected_rows() > 0){
            $row = $q->row();
            return $row;
        }else{
            error_log('no user found getUserInfo('.$email.')');
            return false;
        }
    }
    public function getUserInfo($id)
    {
        $q = $this->db->get_where('tbl_users', array('id' => $id), 1);  
        if($this->db->affected_rows() > 0){
            $row = $q->row();
            return $row;
        }else{
            error_log('no user found getUserInfo('.$id.')');
            return false;
        }
    }
    public function saveNewPass($new_pass){
    $array = array(
            'password'=>$new_pass
            );
    $this->db->where('id', $this->session->userdata('user_id'));
    $query = $this->db->update('tbl_users',$array);
    if($query){
      return true;
    }else{
      return false;
    }
  }  
public function checkOldPass($old_password){
    $this->db->where('id', $this->session->userdata('user_id'));
    $this->db->where('password', $old_password);
    $query = $this->db->get('tbl_users');
    if($query->num_rows() > 0)
        return true;
    else
        return false;
    }
  }

