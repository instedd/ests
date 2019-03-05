<?php
if (!defined('BASEPATH')) exit('No direct script access allowed');

//include Rest Controller library
require APPPATH . '/libraries/REST_Controller.php';

class Api extends REST_Controller {

   function __construct($config = 'rest') {
		parent::__construct($config);
        $this->load->model(array('api_model','useradministration_model','login_model','setups_model','reverselookups_model'));
        $this->load->library('notification');
    }
    
    public function registerSample_get($id = '') {
        //returns all rows if the id parameter doesn't exist,
        //otherwise single row will be returned
        if(isset($this->session->userdata['user_id']))
        {
        $registered_samples = $this->api_model->getRows($id);
        
        //check if the member data exists
        if(!empty($registered_samples)){
            //set the response and exit
            $this->response($registered_samples, REST_Controller::HTTP_OK);
        }else{
            //set the response and exit
            $this->response([
                'status' => FALSE,
                'message' => 'No sample was found.'
            ], REST_Controller::HTTP_NOT_FOUND);
        }
    }
        else{
            $this->response([
                'status' => FALSE,
                'message' => 'Please login to access the system'
            ], REST_Controller::HTTP_FORBIDDEN); 

        }
    }
    
    public function registerSample_post() 
    {
       if(isset($this->session->userdata['user_id']))
        {
        $this->form_validation->set_rules('finalDestinationDate', 'Date to reach final destination', 'trim|required|xss_clean');
        $this->form_validation->set_rules('facility_code', 'Health Facility', 'trim|xss_clean|required');
        $this->form_validation->set_rules('barcode', 'Bar code', 'trim|xss_clean|required');
        $this->form_validation->set_rules('destination', 'Destination', 'trim|required|xss_clean');
        $this->form_validation->set_rules('sample_type', 'Sample Type', 'trim|required|xss_clean');
        if($this->form_validation->run()==false){
           $this->response([
                'status' => FALSE,
                'message' => 'Please fill all required fields.'
            ], REST_Controller::HTTP_FORBIDDEN); 
        }
        else
        {
          //$location = $this->session->userdata['location'];
          $user_id = $this->session->userdata['user_id'];
          $created_by=$this->reverselookups_model->get_user_name($user_id);
          //file upload
            $path = 'uploads/';
            $config['upload_path'] = $path;
            $config['allowed_types'] = 'png|PNG|jpg|JPG|JPEG|jpeg|GIF|gif';
            $config['remove_spaces'] = TRUE;
            $this->load->library('upload', $config);
            $this->upload->initialize($config);
            if (!$this->upload->do_upload('uploadFile')){
                $error = array('error' => $this->upload->display_errors());
                $inputFileName = NULL; 
            } else {
                $data = array('upload_data' => $this->upload->data());
                $upload_file = $data['upload_data']['file_name'];
                $inputFileName = $upload_file;
            }
          $initialSampleDate=$this->input->post('initialSampleDate');
          $initialDate=date('Y-m-d',strtotime($initialSampleDate));
          $finalDestinationDate=$this->input->post('finalDestinationDate');
          $finalDate=date('Y-m-d',strtotime($finalDestinationDate));
          $sample_id=$this->input->post('barcode');
          $registed_sample = $this->useradministration_model->check_registered_sample($sample_id);
          if ($registed_sample > 0) {
            $this->response([
                'status' => FALSE,
                'message' => 'The sample is already registered.'
            ], REST_Controller::HTTP_CONFLICT);
         }
         else{
              $id=$this->input->post('facility_code');
              $district=$this->reverselookups_model->get_district($id);
              $health_facility=$this->reverselookups_model->get_health_facility($id);
              $data_to_insert = array(
                'id' => NULL,
                'sample_id' => $this->input->post('barcode'),
                'facility_code_id'=> $health_facility,
                'disease_id'=> $this->reverselookups_model->get_disease($this->input->post('disease')),
                'sample_type_id'=> $this->reverselookups_model->get_sample_type($this->input->post('sample_type')),
                'destination_id'=> $this->reverselookups_model->get_destinations($this->input->post('destination')),
                'clinical_notes'=> $this->input->post('notes'),
                'districtCode'=> $district,
                'initialSampleDate' =>date('Y-m-d H:i:s'),
                'finalDestinationDate' => $finalDate,
                'entered_by' =>$this->session->userdata['user_full_name'],
                'created_at' => date('Y-m-d H:i:s'),
                'transporter'=> $this->input->post('transporter'),
                'file_path'=> $inputFileName,
            );
             $insert = $this->api_model->insert($data_to_insert);
             if($insert){
                //send notification
             $sample_id=$this->input->post('barcode');
             $message='Sample id'." ".$sample_id." ".'was registered at'." ".$health_facility;
             $notification=$this->notification->notify($message,$created_by,$this->session->userdata['user_full_name']);
                //set the response and exit
                $this->response([
                    'status' => TRUE,
                    'message' => 'Sample has been added successfully.'
                ], REST_Controller::HTTP_OK);
            }else{
                //set the response and exit
                $this->response("Some problems occurred, please try again.", REST_Controller::HTTP_BAD_REQUEST);
     }
    }
 }
}else{
     $this->response([
                'status' => FALSE,
                'message' => 'Please login to access the system'
            ], REST_Controller::HTTP_FORBIDDEN); 
}
}
// getting application data

public function appData_get($id=''){
    if(isset($this->session->userdata['user_id']))
    {
    $appData=array(
         'diseases'=>$this->api_model->getDiseases($id),
         'health_facilities' =>$this->api_model->gethealthFacilities($id),
         'specimen' => $this->api_model->getSpecimen($id),
         'destination' => $this->api_model->get_destinations($id),
         'transporter' => $this->api_model->get_transporters($id),
         'sample_id' => $this->Setups_model->get_sample_id(),
         'sample_status' => $this->Setups_model->get_sample_status(),
         'is_destination' => $this->Setups_model->get_is_destination(),
         'receivedSamples' => $this->useradministration_model->get_all_received_samples(),
         'registeredSamples' => $this->useradministration_model->get_all_registered_samples($id = ''),
         'notifications' => $this->notification->get_all_notifications($id = '')
        );
        if(!empty($appData)){
            //set the response and exit
            $this->response($appData, REST_Controller::HTTP_OK);
        }else{
            //set the response and exit
            $this->response([
                'status' => FALSE,
                'message' => 'No results found.'
            ], REST_Controller::HTTP_NOT_FOUND);
        }
    }
    else{
            $this->response([
                'status' => FALSE,
                'message' => 'Please login to access the system'
            ], REST_Controller::HTTP_FORBIDDEN); 

        }
   }
    public function login_post()
    {
        $username = $this->input->post("txt_username");
        $password = $this->input->post("txt_password");

        //set validations
        $this->form_validation->set_rules("txt_username", "Username", "trim|required");
        $this->form_validation->set_rules("txt_password", "Password", "trim|required");

        if ($this->form_validation->run() == FALSE) {
         $this->response([
                'status' => FALSE,
                'message' => 'Form validation errors.'
            ], REST_Controller::HTTP_FORBIDDEN); 
         
        } else {
                $usr_result = $this->Login_model->check_user($username, $password);
                if ($usr_result>0) {
                    $get_credentials = $this->login_model->authenticate_user($username, $password);
                    foreach ($get_credentials as $row) {
                        $user_id = $row->user_id;
                        $user_group_id = $row->user_group_id;
                        $org_id = $row->org_id;
                        $org_name = $this->reverselookups_model->get_org_name($user_group_id, $user_id, $org_id);
                        $user_group_name = $this->reverselookups_model->get_user_group_name($user_group_id);
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
                        $this->login_model->Login_systemLogs();
                        $this->response([
                    'status' => TRUE,
                    'message' => 'You have successfully logged in.'
                 ], REST_Controller::HTTP_OK);
                    }
                 exit;
                } else {
                 $this->response([
                'status' => FALSE,
                'message' => 'Invalid Logon credentials supplied, try again with correct ones or contact site administrator!.
                ones or contact site administrator!'
               ], REST_Controller::HTTP_FORBIDDEN);
                }
           } 
    }
    public function logout_get()
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

        $this->response([
        'status' => TRUE,
        'message' => 'You have successfully logged out.'
        ], REST_Controller::HTTP_OK);
    }
    public function receiveSample_post() 
    {
       if(isset($this->session->userdata['user_id']))
        {
        //$this->form_validation->set_rules('dateReceived', 'Date received', 'trim|required|xss_clean');
        $this->form_validation->set_rules('barcode', 'Sample ID', 'trim|required|xss_clean');
        $this->form_validation->set_rules('facility_code', 'Health Facility', 'trim|xss_clean|required');
        $this->form_validation->set_rules('final_destination', 'Destinatin', 'trim|required|xss_clean');
        if($this->form_validation->run()==false){
           $this->response([
                'status' => FALSE,
                'message' => 'Please fill all required fields.'
            ], REST_Controller::HTTP_FORBIDDEN); 
        }
        else
        {
        $user_id = $this->session->userdata['user_id'];
       // $date_received = $this->input->post('dateReceived');
        $sample_id = $this->input->post('barcode');
        $destination_id = $this->input->post('final_destination');
        //$dateReceived=date('Y-m-d',strtotime($date_received));
       $receive_sample = $this->useradministration_model->check_sample($sample_id, $destination_id);
        if ($receive_sample > 0) {
           $this->response([
                'status' => FALSE,
                'message' => 'The sample is already registered as received at this destination.'
            ], REST_Controller::HTTP_CONFLICT);

        } else{  
             $sample_id=$this->input->post('barcode');
             $this->db->select("count(*) as data_received_status FROM tbl_received_sample where 1 
                AND sample_id='".$sample_id."'", FALSE);
             $received_status=$this->db->get()->row()->data_received_status;
             $received_status=($received_status>0)?'received':'pending';
             $sample_id=$this->input->post('barcode');
             $destination_id=$this->input->post('final_destination');
                  if(isset($_POST['is_destination']))
                   {
                   $is_destination=$this->input->post('is_destination'); 
                   }else{
                    $is_destination='NO';
                    }
                     $sample_status=$this->input->post('sample_status');
                     $id=$this->input->post('facility_code');
                     $district=$this->reverselookups_model->get_district($id);
                     $health_facility=$this->reverselookups_model->get_health_facility($id);
             
             //get email
             $role_id =1;
             $result=$this->useradministration_model->get_admin_email($role_id);
            foreach ($result as $value) {
                $email=$value->email ;
            }
             $data_to_insert = array(
                'id' => NULL,
                'sample_id' => $this->input->post('barcode'),
                'destination_id'=> $this->input->post('final_destination'),
                'sample_status'=> $this->input->post('sample_status'),
                'is_destination'=> $is_destination,
                'received_status'=> $received_status,
                'date_received' => date('Y-m-d H:i:s'),
                'facility_code_id'=> $health_facility,
                'districtCode'=> $district,
                'entered_by' =>$this->session->userdata['user_full_name'],
                'created_at' => date('Y-m-d H:i:s'),
                'delivered_by'=> $this->input->post('transporter')
            );
             $Suspected_disease=$this->reverselookups_model->get_suspected_disease($this->input->post('barcode'));
             $delivered_by=$this->reverselookups_model->get_transporter_name($this->input->post('transporter'));
             $tel=$this->reverselookups_model->get_transporter_contact($this->input->post('transporter'));
             $received_by=$this->session->userdata['user_full_name'];
             $created_by=$this->session->userdata['user_full_name'];
             $update= array('isSampleAtFinal' =>$is_destination);
             $this->db->where('sample_id', $this->input->post('barcode'));
             $this->db->update('tbl_registered_samples',$update);
             $this->db->insert('tbl_received_sample', $data_to_insert);
             $data_to_update= array('received_status' =>'received');
             $this->db->where('sample_id', $this->input->post('barcode'));
             $this->db->update('tbl_registered_samples',$data_to_update);
              
                  $message =  "
                        <html>
                        <head>
                            <title><b>Sample Notification</b></title>
                        </head>
                        <body>
                            <h2>Received Sample Notification</h2>
                            <p>Sample with the following details has been received</p>
                            <p>Sample ID: ".$sample_id."</p>
                            <p>Received at: ".$health_facility." Transit Point Located in ".$district." district</p>
                            <p>Sample Status: ".$sample_status."</p>
                            <p>Delivered by: ".$delivered_by."(".$tel.")</p>
                            <p>Received by: ".$received_by."</p>
                            <p>Suspected Disease: ".$Suspected_disease."</p>
                        </body>
                        </html>
                        ";
               // store notification
              $sample_id=$this->input->post('barcode');
              $notification=$this->notification->notify($message,$created_by,$received_by);
              $this->email->from("sampletracker18@gmail.com","Sample Tracker");
              $this->email->bcc("medardnduhukire@gmail.com"); 
              $this->email->to($email);
              $this->email->subject("Sample Tracking Notification");
              $this->email->message($message);
            //sending email
            if($this->email->send())
            {
            $this->response([
                    'status' => TRUE,
                    'message' => 'Sample has been added successfully. Notification mail sent out'
                ], REST_Controller::HTTP_OK);
        }else{
            $this->response([
                    'status' => TRUE,
                    'message' => 'Sample has been added successfully but error occured in sending email'
                ], REST_Controller::HTTP_OK);
        }
      }
     }
   }else{
   $this->response([
    'status' => FALSE,
    'message' => 'Please login to access the system'
   ], REST_Controller::HTTP_FORBIDDEN); 
 }
}
}
