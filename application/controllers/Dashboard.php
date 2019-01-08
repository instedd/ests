<?php
/**occurred*/

if (!defined('BASEPATH')) exit('No direct script access allowed');

class Dashboard extends CI_Controller
{

    public function __construct()
    {
        header('Access-Control-Allow-Origin: *');
        header("Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE");
        parent::__construct();
        $this->load->library(array('session', 'form_validation', 'email', 'pagination'));
        $this->load->helper(array('utility', 'html', 'url', 'form', 'notification'));
        $this->load->database();
        $this->load->model(array('Lookups_model','Useradministration_model','Setups_model'));
        $this->load->model('Login_model');
        $this->load->model('Dashboard_model');
    }

    public function index()
    {
        @$this->page_session_expiry($this->session->userdata['user_org_id']);
        $fm_id = $this->session->userdata['user_org_id'];
        $current_method = $this->router->fetch_method();
        $current_class = $this->router->fetch_class();
        $fm_name = $this->session->userdata['user_org_name'];

        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . 'Dashboard',
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item('index'),
            'sub_menu_description' => 'Home page',
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'boolean_response' => $this->Lookups_model->get_boolean_response(),
            'drop_down_roles' => $this->Setups_model->drop_down_roles(),
            'total_samples_in_transit' => $this->Dashboard_model->get_total_samples_in_transit(),
            'total_received' => $this->Dashboard_model->get_total_samples_received(),
            'total_registered_samples' => $this->Dashboard_model->get_total_registered_samples(),
            'total_sample_cphl' => $this->Dashboard_model->get_total_sample_cphl(),
            'total_sample_idi' => $this->Dashboard_model->get_total_sample_idi(),
            'total_sample_dgal' => $this->Dashboard_model->get_total_sample_dgal(),
            'total_sample_ntlp' => $this->Dashboard_model->get_total_sample_ntlp(),
            'total_undelayed_samples' => $this->Dashboard_model->get_total_undelayed_samples(),
            'total_sample_uvri' => $this->Dashboard_model->get_total_sample_uvri(),
            'total_delayed_samples' => $this->Dashboard_model->get_total_delayed_samples(),
            'last_segment' => 'dashboard'
        );
        $data['monthly_delayed_samples_at_final_destination']=$this->Dashboard_model->get_monthly_delayed_samples_at_final_destination();
        $data['monthly_undelayed_samples_at_final_destination']=$this->Dashboard_model->get_monthly_undelayed_samples_at_final_destination();
        $data['monthly_registered_samples']=$this->Dashboard_model->get_monthly_registered_samples();
        $data['monthly_delayed_samples']=$this->Dashboard_model->get_monthly_delayed_samples();
        $data['monthly_received_samples']=$this->Dashboard_model->get_monthly_received_samples();
        $data['registered_samples'] = $this->Useradministration_model->get_all_registered_samples();
        $data['received_samples'] = $this->Useradministration_model->get_all_received_sample();
        $data['received_broken_seals'] = $this->Dashboard_model->get_received_broken_seals();
        $data['received_intact'] = $this->Dashboard_model->get_received_intact();
        $data['received_open'] = $this->Dashboard_model->get_received_open();
        $data['outbreaks']=$this->Dashboard_model->get_outbreaks();
        
        $this->load->view('header', $data);
        $this->load->view('home', $data);
        $this->load->view('footer', $data);
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


}