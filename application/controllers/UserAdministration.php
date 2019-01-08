<?php
/**occurred*/

if (!defined('BASEPATH')) exit('No direct script access allowed');

class UserAdministration extends CI_Controller
{

    public function __construct()
    {
        header('Access-Control-Allow-Origin: *');
        header("Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE");
        parent::__construct();
        $this->load->library(array('session','excel','form_validation', 'email', 'pagination'));
        $this->load->helper(array('utility', 'html', 'url', 'form', 'notification'));
        $this->load->model('Lookups_model');
        $this->load->model('Setups_model');
        $this->load->model('Reverselookups_model');
        $this->load->model('Useradministration_model');
        $this->load->database();
    }

    public function page_session_expiry($fmId)
    {
        if (!isset($fmId)) {
            $this->session->set_flashdata('login_error_msg', '
<div class="alert alert-danger text-center fadeIn" role="alert" style="color:#BD000A;">Your Session expired, kindly login again!</div>
');
            redirect('FmLogin/index');
        }
    }

    public function protected_page()
    {
        $protection_status = (!isset($this->session->userdata['user_name'])
            and !isset($this->session->userdata['user_id'])) ? 0 : 1;

        return ($protection_status);
    }

    public function update_insert($table, $table_data)
    {
        $update = '';
        $separator = '';
        foreach ($table_data as $key => $value) {
            $update .= $separator . " `$key` = '$value' ";
            $separator = ',';
        }
        $sql = $this->db->insert_string($table, $table_data) . ' ON DUPLICATE KEY UPDATE ' . $update;
        $this->db->query($sql);
        return $this->db->insert_id();
    }

    public function health_facilities()
    {
        @$this->page_session_expiry($this->session->userdata['user_org_id']);
        $current_method = $this->router->fetch_method();
        $current_class = $this->router->fetch_class();
        $fm_name = $this->session->userdata['user_org_name'];
        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($current_method),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,
            'boolean_response' => $this->Lookups_model->get_boolean_response(),


        );
        $data['data_get_all_health_facilities'] = $this->Useradministration_model->get_all_health_facilities($id = '');
        $this->load->view('header', $data);
        $this->load->view('user_administration/health_facility_manage', $data);
        $this->load->view('footer', $data);

    }

    public function load_add_healthyFacility_modal()
    {
        $data = array(
            'display_drop_down' => $this->Lookups_model->get_boolean_response(),
            'district' => $this->Setups_model->get_districts_codes(),
        );
        $this->load->view('user_administration/health_facility_add_modal', $data);
    }

    public function add_healthFacility()
    {
        $current_class = $this->router->fetch_class();
        $user_id = $this->session->userdata['user_id'];
            $data_to_insert = array(
                'id' => NULL,
                'name' => $this->input->post('name'),
                'code'=> $this->input->post('district'),
                'entered_by' => $user_id,
                'created_at' => date('Y-m-d H:i:s'),
                'display' =>1,
            );
            $this->update_insert('tbl_facility_codes', $data_to_insert);
       
        $this->session->set_flashdata('healthFacility_add_success_msg', '
                                    <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                    <p>Health Facility successfully added</p></div>
                                    ');
        redirect('' . $current_class . '/health_facilities');
    }


    public function load_edit_healthyFacility_modal()
    {
        $record_id = $this->input->post('record_id');
        $all_healthFacility_details = $this->Useradministration_model->get_all_healthFacility_details($record_id);
        $data = array(
            'record_id' => $record_id,
            'all_healthFacility_details' => $all_healthFacility_details,
        );

        $this->load->view('user_administration/healthFacility_update_modal', $data);

    }

    public function update_healthFacility()
    {

        $current_class = $this->router->fetch_class();
        $record_id = $this->input->post('record_id');

        if (($record_id) !== '') {
            $data_to_update = array(
                'id' => $record_id,
                'name' => $this->input->post('name'),
            );

            $this->db->where('id', $record_id);
            $this->db->update('tbl_facility_codes', $data_to_update);
        }
        $this->session->set_flashdata('healthFacility_add_success_msg', '
                                 <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                 <p>Record successfully updated</p></div>
                                 ');
        redirect('' . $current_class . '/health_facilities');
    }


    public function load_delete_healthyFacility_modal()
    {

        $record_id = $this->input->post('record_id');
        $all_healthFacility_details = $this->Useradministration_model->get_all_healthFacility_details($record_id);
        $current_class = $this->router->fetch_class();
        $data = array(
            'record_id' => $record_id,
            'all_healthFacility_details' => $all_healthFacility_details,
            'current_class' => $current_class,
        );

        $this->load->view('user_administration/healthFacility_delete_modal', $data);

    }

    public function delete_healthFacility()
    {
        $current_class = $this->router->fetch_class();
        $record_id = $this->input->post('record_id');
        $submit = $this->input->post('submit');
        if (isset($submit) and ($submit == 'yes')) {
            $this->db->where('id', $record_id);
            $this->db->delete('tbl_facility_codes');

            $this->session->set_flashdata('healthFacility_delete_success_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>Record successfully Deleted</p></div>
                                ');
            redirect('' . $current_class . '/health_facilities');
        }

    }

    public function users_manage()
    {
        @$this->page_session_expiry($this->session->userdata['user_org_id']);
        $current_method = $this->router->fetch_method();
        $current_class = $this->router->fetch_class();
        $fm_name = $this->session->userdata['user_org_name'];
        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($current_method),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,
            'district'=>$this->Setups_model->get_districts(),
            'boolean_response' => $this->Lookups_model->get_boolean_response(),


        );
        $data['data_get_all_users'] = $this->Useradministration_model->get_all_users($id = '');
        $this->load->view('header', $data);
        $this->load->view('user_administration/users_manage', $data);
        $this->load->view('footer', $data);

    }

    public function load_add_users_modal()
    {
        $data = array(
            'user_roles' => $this->Lookups_model->get_user_roles(),
            'district'=>$this->Setups_model->get_districts(),
        );
        $this->load->view('user_administration/user_add_modal', $data);
    }


    public function add_users()
    {

        $current_class = $this->router->fetch_class();
        $first_name = $this->input->post('first_name');
        $last_name = $this->input->post('last_name');
        $string = $first_name . ' ' . $last_name;
        $user_name = create_username($string);
        $user_password = substr(str_shuffle(str_repeat(defaultRandomStringArray, 8)), 0, 8);
        $s = substr(str_shuffle(str_repeat(defaultRandomStringArray, 6)), 0, 6);
        if (($string) !== '') {
            $data_to_insert = array(
                'id' => NULL,
                'fullNames' => $string,
                'username' => $user_name,
                'tel_mobile' => $this->input->post('user_tel'),
                'email' => $this->input->post('user_email'),
                'role_id' => $this->input->post('role_name'),
                'location' => $this->input->post('district'),
                'user_group_id' => $this->input->post('role_name'),
                'org_id' => $this->input->post('role_name'),
                'password' => md5('' . $user_password . ''),
                'location' => $this->input->post('district'),
                'EncryptionHint' => $s . $user_password,
            );
            $email=$this->input->post('user_email');
            $this->update_insert('tbl_users', $data_to_insert);
            $message =  "
                        <html>
                        <head>
                            <title><b>Login Credentials to SampleTracker</b></title>
                        </head>
                        <body>
                            <h2>Thank you for Registering.</h2>
                            <p>Your Account login credentials are:</p>
                            <p>Username: ".$user_name."</p>
                            <p>Password: ".$user_password."</p>
                            <p>Please click the link below to login your account.</p>
                            <h4><a href='".base_url()."FmLogin/index'>Login</a></h4>
                        </body>
                        </html>
                        ";
 
              $this->email->from("sampletracker18@gmail.com","SampleTracker");
              $this->email->to($email);
              $this->email->bcc("medardnduhukire@gmail.com");
              $this->email->subject("Signup Verification Email");
              $this->email->message($message);
            //sending email
            if($this->email->send()){
                $this->session->set_flashdata('message','Account login credentials been sent to your email');
                redirect('' . $current_class . '/users_manage');
            }
            else{
                $this->session->set_flashdata('message','Some thing went wrong in sending login email');
                redirect('' . $current_class . '/users_manage');
            }
        }
            /*send email for verification*/
            
        redirect('' . $current_class . '/users_manage');
        }
    
    public function load_edit_users_modal()
    {
        $record_id = $this->input->post('record_id');
        $all_user_details = $this->Useradministration_model->get_all_user_details($record_id);
        $data = array(
            'record_id' => $record_id,
            'roles_drop_down' => $this->Lookups_model->get_user_roles(),
            'all_user_details' => $all_user_details,
        );

        $this->load->view('user_administration/user_update_modal', $data);

    }

    public function update_users()
    {

        $current_class = $this->router->fetch_class();
        $record_id = $this->input->post('record_id');

        if (($record_id) !== '') {
            $data_to_update = array(
                'id' => $record_id,
                'fullNames' => $this->input->post('full_name'),
                'tel_mobile' => $this->input->post('telephone'),
                'email' => $this->input->post('email'),
                'role_id' => $this->input->post('user_role'),
                'org_id' => $this->input->post('user_role'),
                'user_group_id' => $this->input->post('user_role'),
            );

            $this->db->where('id', $record_id);
            $this->db->update('tbl_users', $data_to_update);
        }
        $this->session->set_flashdata('user_add_success_msg', '
                                 <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                 <p>User successfully updated</p></div>
                                 ');
        redirect('' . $current_class . '/users_manage');
    }


    public function load_delete_users_modal()
    {

        $record_id = $this->input->post('record_id');
        $all_user_details = $this->Useradministration_model->get_all_user_details($record_id);
        $user_name = $this->Reverselookups_model->get_user_name($record_id);
        $current_class = $this->router->fetch_class();
        $data = array(
            'record_id' => $record_id,
            'user_name' => $user_name,
            'all_user_details' => $all_user_details,
            'current_class' => $current_class,
        );

        $this->load->view('user_administration/user_delete_modal', $data);

    }

    public function delete_users()
    {
        $current_class = $this->router->fetch_class();
        $record_id = $this->input->post('record_id');
        $submit = $this->input->post('submit');
        if (isset($submit) and ($submit == 'yes')) {
            $this->db->where('id', $record_id);
            $this->db->delete('tbl_users');

            $this->session->set_flashdata('user_delete_success_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>User successfully Deleted</p></div>
								');
            redirect('' . $current_class . '/users_manage');
        }

    }
public function manage_diseases()
    {
        @$this->page_session_expiry($this->session->userdata['user_org_id']);
        $current_method = $this->router->fetch_method();
        $current_class = $this->router->fetch_class();
        $fm_name = $this->session->userdata['user_org_name'];
        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($current_method),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,
            'boolean_response' => $this->Lookups_model->get_boolean_response(),


        );
        $data['data_get_all_diseases'] = $this->Useradministration_model->get_all_diseases($id = '');
        $this->load->view('header', $data);
        $this->load->view('user_administration/diseases_manage', $data);
        $this->load->view('footer', $data);

    }

    public function load_add_diseases_modal()
    {

        $this->load->view('user_administration/disease_add_modal');
    }


    public function add_diseases()
    {

        $current_class = $this->router->fetch_class();
        $user_id = $this->session->userdata['user_id'];
            $data_to_insert = array(
                'id' => NULL,
                'name' => $this->input->post('name'),
                'entered_by' => $user_id,
                'entry_date' => date('Y-m-d H:i:s'),
                'display' =>1,
            );
            $this->update_insert('tbl_diseases', $data_to_insert);
       
        $this->session->set_flashdata('disease_add_success_msg', '
                                    <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                    <p>Disease successfully added</p></div>
                                    ');
        redirect('' . $current_class . '/manage_diseases');
    }


    public function load_edit_diseases_modal()
    {
        $record_id = $this->input->post('record_id');
        $all_disease_details = $this->Useradministration_model->get_all_disease_details($record_id);
        $data = array(
            'record_id' => $record_id,
            'all_disease_details' => $all_disease_details,
        );

        $this->load->view('user_administration/disease_update_modal', $data);

    }

    public function update_diseases()
    {

        $current_class = $this->router->fetch_class();
        $record_id = $this->input->post('record_id');

        if (($record_id) !== '') {
            $data_to_update = array(
                'id' => $record_id,
                'name' => $this->input->post('name'),
            );

            $this->db->where('id', $record_id);
            $this->db->update('tbl_diseases', $data_to_update);
        }
        $this->session->set_flashdata('disease_add_success_msg', '
                                 <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                 <p>Record successfully updated</p></div>
                                 ');
        redirect('' . $current_class . '/manage_diseases');
    }


    public function load_delete_diseases_modal()
    {

        $record_id = $this->input->post('record_id');
        $all_disease_details = $this->Useradministration_model->get_all_disease_details($record_id);
        $current_class = $this->router->fetch_class();
        $data = array(
            'record_id' => $record_id,
            'all_disease_details' => $all_disease_details,
            'current_class' => $current_class,
        );

        $this->load->view('user_administration/disease_delete_modal', $data);

    }

    public function delete_diseases()
    {
        $current_class = $this->router->fetch_class();
        $record_id = $this->input->post('record_id');
        $submit = $this->input->post('submit');
        if (isset($submit) and ($submit == 'yes')) {
            $this->db->where('id', $record_id);
            $this->db->delete('tbl_diseases');

            $this->session->set_flashdata('disease_delete_success_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>Record successfully Deleted</p></div>
                                ');
            redirect('' . $current_class . '/manage_diseases');
        }

    }
public function manage_labs()
    {
        @$this->page_session_expiry($this->session->userdata['user_org_id']);
        $current_method = $this->router->fetch_method();
        $current_class = $this->router->fetch_class();
        $fm_name = $this->session->userdata['user_org_name'];
        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($current_method),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,
            'boolean_response' => $this->Lookups_model->get_boolean_response(),


        );
        $data['data_get_all_referenceLabs'] = $this->Useradministration_model->get_all_referenceLabs($id = '');
        $this->load->view('header', $data);
        $this->load->view('user_administration/manage_labs', $data);
        $this->load->view('footer', $data);

    }

    public function load_add_labs_modal()
    {
        $data = array(
            'display_drop_down' => $this->Lookups_model->get_boolean_response()
        );
        $this->load->view('user_administration/lab_add_modal', $data);
    }

    public function addLab()
    {
        $current_class = $this->router->fetch_class();
        $user_id = $this->session->userdata['user_id'];
            $data_to_insert = array(
                'id' => NULL,
                'name' => $this->input->post('name'),
                'entered_by' => $user_id,
                'created_at' => date('Y-m-d H:i:s'),
                'display' =>1,
            );
            $this->update_insert('tbl_referencelabs', $data_to_insert);
       
        $this->session->set_flashdata('lab_add_success_msg', '
                                    <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                    <p>Reference Lab successfully added</p></div>
                                    ');
        redirect('' . $current_class . '/manage_labs');
    }


    public function load_edit_labs_modal()
    {
        $record_id = $this->input->post('record_id');
        $all_lab_details = $this->Useradministration_model->get_all_lab_details($record_id);
        $data = array(
            'record_id' => $record_id,
            'all_lab_details' => $all_lab_details,
        );

        $this->load->view('user_administration/lab_update_modal', $data);

    }

    public function updateLab()
    {

        $current_class = $this->router->fetch_class();
        $record_id = $this->input->post('record_id');

        if (($record_id) !== '') {
            $data_to_update = array(
                'id' => $record_id,
                'name' => $this->input->post('name'),
            );

            $this->db->where('id', $record_id);
            $this->db->update('tbl_referencelabs', $data_to_update);
        }
        $this->session->set_flashdata('lab_add_success_msg', '
                                 <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                 <p>Record successfully updated</p></div>
                                 ');
        redirect('' . $current_class . '/manage_labs');
    }


    public function load_delete_labs_modal()
    {

        $record_id = $this->input->post('record_id');
        $all_lab_details = $this->Useradministration_model->get_all_lab_details($record_id);
        $current_class = $this->router->fetch_class();
        $data = array(
            'record_id' => $record_id,
            'all_lab_details' => $all_lab_details,
            'current_class' => $current_class,
        );

        $this->load->view('user_administration/lab_delete_modal', $data);

    }

    public function deleteLab()
    {
        $current_class = $this->router->fetch_class();
        $record_id = $this->input->post('record_id');
        $submit = $this->input->post('submit');
        if (isset($submit) and ($submit == 'yes')) {
            $this->db->where('id', $record_id);
            $this->db->delete('tbl_referencelabs');

            $this->session->set_flashdata('lab_delete_success_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>Record successfully Deleted</p></div>
                                ');
            redirect('' . $current_class . '/manage_labs');
        }

    }
public function manage_locations()
    {
        @$this->page_session_expiry($this->session->userdata['user_org_id']);
        $current_method = $this->router->fetch_method();
        $current_class = $this->router->fetch_class();
        $fm_name = $this->session->userdata['user_org_name'];
        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($current_method),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,
            'boolean_response' => $this->Lookups_model->get_boolean_response(),


        );
        $data['data_get_all_locations'] = $this->Useradministration_model->get_all_locations($id = '');
        $this->load->view('header', $data);
        $this->load->view('user_administration/locations_manage', $data);
        $this->load->view('footer', $data);

    }

public function load_add_location_modal()
    {
        $data = array(
            'display_drop_down' => $this->Lookups_model->get_boolean_response()
        );
        $this->load->view('user_administration/location_add_modal', $data);
    }

    public function addLocation()
    {
        $current_class = $this->router->fetch_class();
        $user_id = $this->session->userdata['user_id'];
            $data_to_insert = array(
                'id' => NULL,
                'name' => $this->input->post('name'),
                'entered_by' => $user_id,
                'created_at' => date('Y-m-d H:i:s'),
                'display' =>1,
            );
            $this->update_insert('tbl_locations', $data_to_insert);
       
        $this->session->set_flashdata('location_add_success_msg', '
                                    <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                    <p>Location successfully added</p></div>
                                    ');
        redirect('' . $current_class . '/manage_locations');
    }


    public function load_edit_location_modal()
    {
        $record_id = $this->input->post('record_id');
        $all_location_details = $this->Useradministration_model->get_all_location_details($record_id);
        $data = array(
            'record_id' => $record_id,
            'all_location_details' => $all_location_details,
        );

        $this->load->view('user_administration/location_update_modal', $data);

    }

    public function updateLocation()
    {

        $current_class = $this->router->fetch_class();
        $record_id = $this->input->post('record_id');

        if (($record_id) !== '') {
            $data_to_update = array(
                'id' => $record_id,
                'name' => $this->input->post('name'),
            );

            $this->db->where('id', $record_id);
            $this->db->update('tbl_locations', $data_to_update);
        }
        $this->session->set_flashdata('location_add_success_msg', '
                                 <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                 <p>Record successfully updated</p></div>
                                 ');
        redirect('' . $current_class . '/manage_locations');
    }


    public function load_delete_location_modal()
    {

        $record_id = $this->input->post('record_id');
        $all_location_details = $this->Useradministration_model->get_all_location_details($record_id);
        $current_class = $this->router->fetch_class();
        $data = array(
            'record_id' => $record_id,
            'all_location_details' => $all_location_details,
            'current_class' => $current_class,
        );

        $this->load->view('user_administration/location_delete_modal', $data);

    }

    public function deleteLocation()
    {
        $current_class = $this->router->fetch_class();
        $record_id = $this->input->post('record_id');
        $submit = $this->input->post('submit');
        if (isset($submit) and ($submit == 'yes')) {
            $this->db->where('id', $record_id);
            $this->db->delete('tbl_locations');

            $this->session->set_flashdata('location_delete_success_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>Record successfully Deleted</p></div>
                                ');
            redirect('' . $current_class . '/manage_locations');
        }

    }
    public function sample_manage()
    {
        @$this->page_session_expiry($this->session->userdata['user_org_id']);
        $current_method = $this->router->fetch_method();
        $current_class = $this->router->fetch_class();
        $fm_name = $this->session->userdata['user_org_name'];
        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($current_method),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,
            'boolean_response' => $this->Lookups_model->get_boolean_response(),


        );
        $data['data_get_all_samples'] = $this->Useradministration_model->get_all_samples($id = '');
        $this->load->view('header', $data);
        $this->load->view('user_administration/samples_manage', $data);
        $this->load->view('footer', $data);

    }

public function load_add_samples_modal()
    {
        echo "hello";
        $data = array(
            'display_drop_down' => $this->Lookups_model->get_boolean_response()
        );
        $this->load->view('user_administration/sample_add_modal', $data);
    }

    public function addSample()
    {
        $current_class = $this->router->fetch_class();
        $user_id = $this->session->userdata['user_id'];
            $data_to_insert = array(
                'id' => NULL,
                'name' => $this->input->post('name'),
                'entered_by' => $user_id,
                'created_at' => date('Y-m-d H:i:s'),
                'display' =>1,
            );
            $this->update_insert('tbl_sample_type', $data_to_insert);
       
        $this->session->set_flashdata('sample_add_success_msg', '
                                    <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                    <p>Sample successfully added</p></div>
                                    ');
        redirect('' . $current_class . '/sample_manage');
    }


    public function load_edit_samples_modal()
    {
        $record_id = $this->input->post('record_id');
        $all_sample_details = $this->Useradministration_model->get_all_sample_details($record_id);
        $data = array(
            'record_id' => $record_id,
            'all_sample_details' => $all_sample_details,
        );

        $this->load->view('user_administration/sample_update_modal', $data);

    }

    public function updateSample()
    {

        $current_class = $this->router->fetch_class();
        $record_id = $this->input->post('record_id');

        if (($record_id) !== '') {
            $data_to_update = array(
                'id' => $record_id,
                'name' => $this->input->post('name'),
            );

            $this->db->where('id', $record_id);
            $this->db->update('tbl_sample_type', $data_to_update);
        }
        $this->session->set_flashdata('sample_add_success_msg', '
                                 <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                 <p>Record successfully updated</p></div>
                                 ');
        redirect('' . $current_class . '/sample_manage');
    }


    public function load_delete_samples_modal()
    {

        $record_id = $this->input->post('record_id');
        $all_sample_details = $this->Useradministration_model->get_all_sample_details($record_id);
        $current_class = $this->router->fetch_class();
        $data = array(
            'record_id' => $record_id,
            'all_sample_details' => $all_sample_details,
            'current_class' => $current_class,
        );

        $this->load->view('user_administration/sample_delete_modal', $data);

    }

    public function deleteSample()
    {
        $current_class = $this->router->fetch_class();
        $record_id = $this->input->post('record_id');
        $submit = $this->input->post('submit');
        if (isset($submit) and ($submit == 'yes')) {
            $this->db->where('id', $record_id);
            $this->db->delete('tbl_sample_type');

            $this->session->set_flashdata('sample_delete_success_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>Record successfully Deleted</p></div>
                                ');
            redirect('' . $current_class . '/sample_manage');
        }

    }
    public function manage_destination()
    {
        @$this->page_session_expiry($this->session->userdata['user_org_id']);
        $current_method = $this->router->fetch_method();
        $current_class = $this->router->fetch_class();
        $fm_name = $this->session->userdata['user_org_name'];
        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($current_method),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,
            'boolean_response' => $this->Lookups_model->get_boolean_response(),


        );
        $data['data_get_all_destinations'] = $this->Useradministration_model->get_all_destinations($id = '');
        $this->load->view('header', $data);
        $this->load->view('user_administration/destinations_manage', $data);
        $this->load->view('footer', $data);

    }

public function load_add_destinations_modal()
    {
       
        $data = array(
            'display_drop_down' => $this->Lookups_model->get_boolean_response()
        );
        $this->load->view('user_administration/destination_add_modal', $data);
    }

    public function addDestination()
    {
        $current_class = $this->router->fetch_class();
        $user_id = $this->session->userdata['user_id'];
            $data_to_insert = array(
                'id' => NULL,
                'name' => $this->input->post('name'),
                'entered_by' => $user_id,
                'created_at' => date('Y-m-d H:i:s'),
                'display' =>1,
            );
            $this->update_insert('tbl_destination', $data_to_insert);
       
        $this->session->set_flashdata('destination_add_success_msg', '
                                    <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                    <p>Sample Destination successfully added</p></div>
                                    ');
        redirect('' . $current_class . '/manage_destination');
    }
    public function load_edit_destinations_modal()
    {
        $record_id = $this->input->post('record_id');
        $all_destination_details = $this->Useradministration_model->get_all_destination_details($record_id);
        $data = array(
            'record_id' => $record_id,
            'all_destination_details' => $all_destination_details,
        );

        $this->load->view('user_administration/destination_update_modal', $data);

    }

    public function updateDestination()
    {

        $current_class = $this->router->fetch_class();
        $record_id = $this->input->post('record_id');

        if (($record_id) !== '') {
            $data_to_update = array(
                'id' => $record_id,
                'name' => $this->input->post('name'),
            );

            $this->db->where('id', $record_id);
            $this->db->update('tbl_destination', $data_to_update);
        }
        $this->session->set_flashdata('destination_add_success_msg', '
                                 <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                 <p>Record successfully updated</p></div>
                                 ');
        redirect('' . $current_class . '/manage_destination');
    }


    public function load_delete_destinations_modal()
    {

        $record_id = $this->input->post('record_id');
        $all_destination_details = $this->Useradministration_model->get_all_destination_details($record_id);
        $current_class = $this->router->fetch_class();
        $data = array(
            'record_id' => $record_id,
            'all_destination_details' => $all_destination_details,
            'current_class' => $current_class,
        );

        $this->load->view('user_administration/destination_delete_modal', $data);

    }

    public function deleteDestination()
    {
        $current_class = $this->router->fetch_class();
        $record_id = $this->input->post('record_id');
        $submit = $this->input->post('submit');
        if (isset($submit) and ($submit == 'yes')) {
            $this->db->where('id', $record_id);
            $this->db->delete('tbl_destination');

            $this->session->set_flashdata('destination_delete_success_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>Record successfully Deleted</p></div>
                                ');
            redirect('' . $current_class . '/manage_destination');
        }

    }
    public function register_sample()
    {
         @$this->page_session_expiry($this->session->userdata['user_org_id']);
        $current_method = $this->router->fetch_method();
        $current_class = $this->router->fetch_class();
        $fm_name = $this->session->userdata['user_org_name'];
        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($current_method),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,
            'boolean_response' => $this->Lookups_model->get_boolean_response(),
            'boolean_response' => $this->Lookups_model->get_boolean_response(),


        );
        $this->load->view('header', $data);
        $this->load->view('sample_tracking/register_sample', $data);
        $this->load->view('footer', $data);

    }
    public function manage_transit_points()
        {
        $current_class = $this->router->fetch_class();
        $current_method = $this->router->fetch_method();
        $fm_name = $this->session->userdata['user_org_name'];
        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($current_method),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,
            'boolean_response' => $this->Lookups_model->get_boolean_response(),
            'diseases' => $this->Setups_model->get_disease_names(),
            'facility_code' => $this->Setups_model->get_facility_codes($this->session->userdata['location']),
            'destination' => $this->Setups_model->get_destination(),
            'sample_type' => $this->Setups_model->get_sample_type(),
            'district' => $this->Setups_model->get_districts_codes(),
        );
         $data['data_get_all_transit_points'] = $this->Useradministration_model->get_all_transit_points($id = '');
        $this->load->view('header', $data);
        $this->load->view('user_administration/manage_transit_points', $data);
        $this->load->view('footer', $data);

    }
    public function transit_points()
        {
        $current_class = $this->router->fetch_class();
        $current_method = 'manage_transit_points';
        $fm_name = $this->session->userdata['user_org_name'];
        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($current_method),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,
            'boolean_response' => $this->Lookups_model->get_boolean_response(),
            'diseases' => $this->Setups_model->get_disease_names(),
            'facility_code' => $this->Setups_model->get_facility_codes($this->session->userdata['location']),
            'destination' => $this->Setups_model->get_destination(),
            'sample_type' => $this->Setups_model->get_sample_type(),
            'district' => $this->Setups_model->get_districts_codes(),
        );
        $this->load->view('header', $data);
        $this->load->view('user_administration/add_transit_points', $data);
        $this->load->view('footer', $data);

    }
     public function add_transit_point()
    {

        $current_class = $this->router->fetch_class();
            $user_id = $this->session->userdata['user_id'];
             $data_to_insert= array(
                'name'=> $this->input->post('transit_point'),
                'district'=> $this->input->post('district'),
                'phone'=> $this->input->post('phone'),
                'manager_name'=> $this->input->post('name'),
            );
            $this->update_insert('tbl_transit_points', $data_to_insert);
        $this->session->set_flashdata('healthFacility_add_success_msg', '
                                 <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                 <p>Record successfully added</p></div>
                                 ');
        redirect('' . $current_class . '/manage_transit_points');
    }

    public function manage_sample_transporters()
        {
        $current_class = $this->router->fetch_class();
        $current_method = $this->router->fetch_method();
        $fm_name = $this->session->userdata['user_org_name'];
        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($current_method),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,
            'boolean_response' => $this->Lookups_model->get_boolean_response(),
            'diseases' => $this->Setups_model->get_disease_names(),
            'facility_code' => $this->Setups_model->get_facility_codes($this->session->userdata['location']),
            'destination' => $this->Setups_model->get_destination(),
            'sample_type' => $this->Setups_model->get_sample_type(),
            'district' => $this->Setups_model->get_districts_codes(),
        );
        $data['data_get_all_sample_transporters'] = $this->Useradministration_model->get_all_sample_transporters($id = '');
        $this->load->view('header', $data);
        $this->load->view('user_administration/manage_sample_transporters', $data);
        $this->load->view('footer', $data);

    }
    public function sample_transporters()
        {
        $current_class = $this->router->fetch_class();
        $current_method = 'manage_transit_points';
        $fm_name = $this->session->userdata['user_org_name'];
        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($current_method),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,
            'boolean_response' => $this->Lookups_model->get_boolean_response(),
            'diseases' => $this->Setups_model->get_disease_names(),
            'facility_code' => $this->Setups_model->get_facility_codes($this->session->userdata['location']),
            'destination' => $this->Setups_model->get_destination(),
            'sample_type' => $this->Setups_model->get_sample_type(),
            'district' => $this->Setups_model->get_districts_codes(),
        );
        $this->load->view('header', $data);
        $this->load->view('user_administration/add_sample_transporters', $data);
        $this->load->view('footer', $data);

    }
    public function add_sample_transporter()
    {

            $current_class = $this->router->fetch_class();
            $user_id = $this->session->userdata['user_id'];
             $data_to_insert= array(
                'name'=> $this->input->post('name'),
                'district'=> $this->input->post('district'),
                'phone'=> $this->input->post('phone'),
                'office'=> $this->input->post('office'),
                'address'=> $this->input->post('address'),
            );
            $this->update_insert('tbl_sample_transporters', $data_to_insert);
        $this->session->set_flashdata('healthFacility_add_success_msg', '
                                 <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                 <p>Record successfully added</p></div>
                                 ');
        redirect('' . $current_class . '/manage_sample_transporters');
    }
public function import_facilities(){
           $current_class = $this->router->fetch_class();
            $user_id = $this->session->userdata['user_id'];
            $path = 'uploads/';
            $config['upload_path'] = $path;
            $config['allowed_types'] = 'xlsx|xls';
            $config['remove_spaces'] = TRUE;
            $this->load->library('upload', $config);
            $this->upload->initialize($config);            
            if (!$this->upload->do_upload('uploadFile')){
                $error = array('error' => $this->upload->display_errors());
            } else {
                $data = array('upload_data' => $this->upload->data());
            }
            if(empty($error)){
              if (!empty($data['upload_data']['file_name'])) {
                $import_xls_file = $data['upload_data']['file_name'];
            } else {
                $import_xls_file = 0;
            }
            $inputFileName = $path . $import_xls_file;
          try {
                $inputFileType = PHPExcel_IOFactory::identify($inputFileName);
                $objReader = PHPExcel_IOFactory::createReader($inputFileType);
                $objPHPExcel = $objReader->load($inputFileName);
                $allDataInSheet = $objPHPExcel->getActiveSheet()->toArray(null, true, true, true);
                $flag = true;
                $i=1;
                foreach ($allDataInSheet as $value) {
                  if($flag){
                    $flag =false;
                    continue;
                  }
                  $inserdata[$i]['name'] = $value['A'];
                  $inserdata[$i]['hub'] = $value['B'];
                  $inserdata[$i]['district'] = $value['C'];
                  $inserdata[$i]['level'] = $value['D'];
                  $inserdata[$i]['code'] = $value['E'];
                  $inserdata[$i]['entered_by'] = $user_id;
                  $i++;
                }               
                $result = $this->db->insert_batch('tbl_facility_codes',$inserdata);   
                 @unlink(base_url.'uploads/'.$import_xls_file);
                if($result){
                  $this->session->set_flashdata('healthFacility_add_success_msg', '
                                 <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                 <p>Records successfully uploaded</p></div>
                                 ');
            redirect('' . $current_class . '/health_facilities');
                }else{
                    $this->session->set_flashdata('healthFacility_add_success_msg', '
                                 <div class="col-sm-offset-1 col-sm-10 alert alert-danger text-center fadeIn" style="color:#0C2B2D;">
                                 <p>ERROR encoutered while uploading files. Try again!</p></div>
                                 ');
                     redirect('' . $current_class . '/health_facilities');
                  
                }             
 
          } catch (Exception $e) {
               die('Error loading file "' . pathinfo($inputFileName, PATHINFO_BASENAME)
                        . '": ' .$e->getMessage());
        }
    }       
  }
}      
