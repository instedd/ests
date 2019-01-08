<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');
class Home extends CI_Controller {

	public function __construct()
	{
		header('Access-Control-Allow-Origin: *');
        header("Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE");
		parent::__construct();
        
		$this->load->database();
		$this->load->helper('url');
        $this->load->helper('form');
		$this->load->library(array('session','form_validation'));
		$this->load->model(array('Login_model'));
	}
	public function index()
	{
		
		$this->load->view('login');
		
	}
	public function login()
	{
        $username = $this->input->post("username");
        $password = $this->input->post("password");
        //set validations
        $this->form_validation->set_rules("username", "Username", "trim|required");
        $this->form_validation->set_rules("password", "Password", "trim|required");

        if ($this->form_validation->run() == FALSE) {
            
            $this->load->view('login');
        } else {
                $usr_result = $this->Login_model->get_user($username, $password);


                if ($usr_result > 0) {
                    $data['data_get_credentials'] = $this->Login_model->get_user($username, $password);
                    foreach ($data['data_get_credentials'] as $row) {
                        $session_data = array(
                            'username' => $row->username
                        );
                        $this->load->view('header',$data);
                        $this->load->view('home',$data);
                        $this->load->view('footer',$data);
                    }

                } else {
                    $this->session->set_flashdata('msg', '<div class="alert alert-danger text-center fadeIn" role="alert" style="color:#BD000A;">Invalid Login credentials supplied!</div>');
                    redirect('Home/index');
                }
            } 
	}
	
	public function forgot_password()
	{	
		$this->load->view('password_reset');
	}


}
