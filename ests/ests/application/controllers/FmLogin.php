<?php
/**occurred*/

if (!defined('BASEPATH')) exit('No direct script access allowed');

class FmLogin extends CI_Controller
{

    public function __construct()
    {
        header('Access-Control-Allow-Origin: *');
        header("Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE");
        parent::__construct();
        $this->load->library(array('session', 'form_validation', 'email', 'pagination'));
        $this->load->helper(array('utility','security', 'html', 'url', 'form'));
        $this->load->database();
        $this->load->model('Reverselookups_model');
        $this->load->model('Login_model');
    }

    public function index()
    {

        $data['page_title'] = 'Sample Tracking';
        $username = $this->input->post("txt_username");
        $password = $this->input->post("txt_password");
        
        //set validations
        $this->form_validation->set_rules("txt_username", "Username", "trim|required");
        $this->form_validation->set_rules("txt_password", "Password", "trim|required");

        if ($this->form_validation->run() == FALSE) {
            $this->load->view('login/page_login', $data);
        } else {
            if ($this->input->post('submit') == "Sign In") {
                $usr_result = $this->Login_model->check_user($username, $password);
                if ($usr_result > 0) {
                    $get_credentials = $this->Login_model->authenticate_user($username, $password);
                    foreach ($get_credentials as $row) {
                        $user_id = $row->user_id;
                        $user_group_id = $row->user_group_id;
                        $org_id = $row->org_id;
                        $org_name = $this->Reverselookups_model->get_org_name($user_group_id, $user_id, $org_id);
                        $user_group_name = $this->Reverselookups_model->get_user_group_name($user_group_id);
                        $user_name = $row->userName;
                        $role_id = $row->role_id;
                        $session_data = array(
                            'user_full_name' => $row->fullNames,
                            'user_name' => $user_name,
                            'user_group_id' => $user_group_id,
                            'user_group_name' => $user_group_name,
                            'user_id' => $user_id,
                            'user_org_id' => $org_id,
                            'location' => $row->location,
                            'user_org_name' => $org_name,
                            'role_id' => $role_id,
                            'ip_address' => $_SERVER['REMOTE_ADDR'],
                        );
                        $this->session->set_userdata($session_data);
                        $this->Login_model->Login_systemLogs();
                        redirect("Dashboard/index");

                    }

                } else {
                    $this->session->set_flashdata('login_error_msg', '<div class="alert alert-danger text-center fadeIn" role="alert">Invalid Logon credentials supplied, try again with correct
ones or
contact site administrator!</div>');
                    redirect('FmLogin/index');
                }
            } else {
                $this->session->set_flashdata('login_error_msg', '<div class="alert alert-danger text-center fadeIn" role="alert">Logon Failure!</div>');
                redirect('FmLogin/index');
            }
        }
    }

    public function logout()
    {
        //update particulars of user
        $data_to_update = array(
            'status' => 'Logged Off',
            'time_logged_off' => date('Y-m-d H:i:s')
        );

        $this->db->where('user_id', $this->session->userdata['user_id']);
        $this->db->where('user_name', $this->session->userdata['user_name']);
        $this->db->where('user_group_id', $this->session->userdata['user_group_id']);
        $this->db->update('tbl_login', $data_to_update);

        $this->session->sess_destroy();
        redirect('FmLogin/index');
    }
  public function forgot_password(){
    $this->load->view('password_reset');
   }
public function forgot()
{
        $passwordplain = "";
        $email=$this->input->post('email');
        $passwordplain  = substr(str_shuffle(str_repeat(defaultRandomStringArray, 8)), 0, 8);
        $newpass = md5($passwordplain);
        $s = substr(str_shuffle(str_repeat(defaultRandomStringArray, 6)), 0, 6);
        $encryption_hint=$s.$passwordplain;
        $this->db->where('email', $email);
        $this->db->update('tbl_users', array('password'=>$newpass,'EncryptionHint'=>$encryption_hint)); 
        $message =  "
                        <html>
                        <head>
                            <title><b>Password Reset</b></title>
                        </head>
                        <body>
                            <h2>Your password has been reset.</h2>
                            <p>New password: ".$passwordplain."</p>
                            <p>Please click the link below to login your account.</p>
                            <h4><a href='".base_url()."FmLogin/index'>Login</a></h4>
                        </body>
                        </html>
                        ";
        $this->email->from("sampletracker18@gmail.com","Sample Tracker");
        $this->email->bcc("medardnduhukire@gmail.com"); 
        $this->email->to($email);
        $this->email->subject("Password Reset");
        $this->email->message($message);
            //sending email
        if($this->email->send()){
        $this->session->set_flashdata('message','<div class="alert alert-success text-center fadeIn" role="alert">Password was sent to your email</div>');
             
            redirect('FmLogin/index');
             }
            else{
                $this->session->set_flashdata('message','<div class="alert alert-danger text-center fadeIn" role="alert">Some thing went wrong in sending password reset link</div>');
               redirect('FmLogin/index');
            }
    }

}