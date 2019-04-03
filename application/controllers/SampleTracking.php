<?php
/**occurred*/

if (!defined('BASEPATH')) exit('No direct script access allowed');

class SampleTracking extends CI_Controller
{

    public function __construct()
    {
        header('Access-Control-Allow-Origin: *');
        header("Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE");
        parent::__construct();
        $this->load->library(array('session','notification','form_validation', 'email', 'pagination'));
        $this->load->helper(array('html', 'url', 'form', 'notification'));
        $this->load->model('Lookups_model');
        $this->load->model('Dashboard_model');
        $this->load->model('Setups_model');
        $this->load->model('reverselookups_model');
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

        public function register_sample()
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
            'district' => $this->Setups_model->get_districts(),
            'transporter_name' => $this->Setups_model->get_transporter_name(),
        );
        $this->load->view('header', $data);
        $this->load->view('sample_tracking/register_sample', $data);
        $this->load->view('footer', $data);

    }
 public function registerSample()
    {
         
          $current_class = $this->router->fetch_class();
          $current_method = $this->router->fetch_method();
          $location = $this->session->userdata['location'];
          $user_id = $this->session->userdata['user_id'];
          $created_by=$this->reverselookups_model->get_user_name($user_id);
          //file upload
            $path = 'uploads/';
            $config['upload_path'] = $path;
            $config['allowed_types'] = 'png|PNG|jpg|JPG|JPEG|jpeg|GIF|gif|PDF|pdf|docx';
            $config['remove_spaces'] = TRUE;
            $this->load->library('upload', $config);
            $this->upload->initialize($config);
            if (!$this->upload->do_upload('uploadFile')){
                $error = array('error' => $this->upload->display_errors());
                $inputFileName = "N/A"; 
            } else {
                $data = array('upload_data' => $this->upload->data());
                $upload_file = $data['upload_data']['file_name'];
               $inputFileName = $path . $upload_file;
            }
          $initialSampleDate=$this->input->post('initialSampleDate');
          $initialDate=date('Y-m-d',strtotime($initialSampleDate));
          $finalDestinationDate=$this->input->post('finalDestinationDate');
          $finalDate=date('Y-m-d',strtotime($finalDestinationDate));
          $sample_id=$this->input->post('barcode');
          $registed_sample = $this->Useradministration_model->check_registered_sample($sample_id);
        if ($registed_sample > 0) {
        $this->session->set_flashdata('message','The sample is already registerd');
         $fm_name = $this->session->userdata['user_org_name'];
         $register_sample='register_sample';
        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($register_sample),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,
            'boolean_response' => $this->Lookups_model->get_boolean_response(),
            'diseases' => $this->Setups_model->get_disease_names(),
            'facility_code' => $this->Setups_model->get_facility_codes($location),
            'destination' => $this->Setups_model->get_destination(),
            'sample_type' => $this->Setups_model->get_sample_type(),
            
        );
        $this->load->view('header', $data);
        $this->load->view('sample_tracking/register_sample', $data);
        $this->load->view('footer', $data);
        }else{
              $id=$this->input->post('facility_code');
              $district=$this->reverselookups_model->get_district_code($id);
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
             $this->update_insert('tbl_registered_samples', $data_to_insert);
             $sample_id=$this->input->post('barcode');
             $message='Sample id'." ".$sample_id." ".'was registered at'." ".$health_facility;
             $notification=$this->notification->notify($message,$created_by, $registered_by="");
        $this->session->set_flashdata('sample_add_success_msg', '
                                    <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                    <p>Sample successfully added</p></div>
                                    ');
        redirect('' . $current_class . '/registered_samples');
    }
    }
public function registered_samples()
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
            'district' => $this->Setups_model->get_districts(),
            'sample_status' => $this->Setups_model->get_sample_status(),
            'boolean_response' => $this->Lookups_model->get_boolean_response(),
        );
        $current_class = $this->router->fetch_class();
        $user_id = $this->session->userdata['user_id'];
        $data['data_get_all_registered_samples'] = $this->Useradministration_model->get_all_registered_samples($id = '');
        $this->load->view('header', $data);
        $this->load->view('sample_tracking/registered_samples', $data);
        $this->load->view('footer', $data);
  
        }
public function filter()
{
     @$this->page_session_expiry($this->session->userdata['user_org_id']);
        $current_method = "registered_samples";
        $current_class = $this->router->fetch_class();
        $fm_name = $this->session->userdata['user_org_name'];
        $district=$this->input->post('district');
        $start_date=$this->input->post('start_date');
        $start_date=date('Y-m-d',strtotime($start_date));
        $end_date=$this->input->post('end_date');
        $end_date=date('Y-m-d',strtotime($end_date));
       // $sample_status=$this->input->post('sample_status');
        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($current_method),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,
            'district' => $this->Setups_model->get_districts(),
            'sample_status' => $this->Setups_model->get_sample_status(),
            'boolean_response' => $this->Lookups_model->get_boolean_response(),
        );
        $current_class = $this->router->fetch_class();
        $user_id = $this->session->userdata['user_id'];
        $data['data_get_all_registered_samples'] = $this->Useradministration_model->filter_registered_samples($start_date,$end_date,$district);
        $this->load->view('header', $data);
        $this->load->view('sample_tracking/registered_samples', $data);
        $this->load->view('footer', $data);
}
    
public function receive_sample()
        {
        $current_class = $this->router->fetch_class();
        $current_method = $this->router->fetch_method();
        $fm_name = $this->session->userdata['user_org_name'];
        $location = $this->session->userdata['location'];
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
            'facility_code' => $this->Setups_model->get_facility_codes($location),
            'destination' => $this->Setups_model->get_destination(),
            'sample_id' => $this->Setups_model->get_sample_id(),
            'sample_status' => $this->Setups_model->get_sample_status(),
            'is_destination' => $this->Setups_model->get_is_destination(),
            'facility_code' => $this->Setups_model->get_facility_codes($location),
            'district' => $this->Setups_model->get_districts(),
            'transporter_name' => $this->Setups_model->get_transporter_name(),
        );
        $this->load->view('header', $data);
        $this->load->view('sample_tracking/receive_sample', $data);
        $this->load->view('footer', $data);

    }
    public function receiveSample()
    {
        $current_class = $this->router->fetch_class();
        $current_method = $this->router->fetch_method();
        $user_id = $this->session->userdata['user_id'];
        $created_by=$this->reverselookups_model->get_user_name($user_id);
       // $date_received = $this->input->post('dateReceived');
        $sample_id = $this->input->post('barcode');
        $destination_id = $this->input->post('final_destination');
        //$dateReceived=date('Y-m-d',strtotime($date_received));
       $receive_sample = $this->Useradministration_model->check_sample($sample_id, $destination_id);
        if ($receive_sample > 0) {
        $fm_name = $this->session->userdata['user_org_name'];
         $receive_sample='receive_sample';
         $this->session->set_flashdata('message','The sample is already registerd as received at this destination');
        $location = $this->session->userdata['location'];
        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($receive_sample),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,
            'boolean_response' => $this->Lookups_model->get_boolean_response(),
            'diseases' => $this->Setups_model->get_disease_names(),
            'facility_code' => $this->Setups_model->get_facility_codes($location),
            'destination' => $this->Setups_model->get_destination(),
            'sample_id' => $this->Setups_model->get_sample_id(),
            'sample_status' => $this->Setups_model->get_sample_status(),
            'is_destination' => $this->Setups_model->get_is_destination(),
            'transporter_name' => $this->Setups_model->get_transporter_name()
        );
        $this->load->view('header', $data);
        $this->load->view('sample_tracking/receive_sample', $data);
        $this->load->view('footer', $data);

        } else{  
             $sample_id=$this->input->post('barcode');
             $this->db->select("count(*) as data_received_status FROM tbl_received_sample where 1 
                AND sample_id='".$sample_id."'", FALSE);
             $received_status=$this->db->get()->row()->data_received_status;
             $received_status=($received_status>0)?'received':'pending';
             $sample_id=$this->input->post('barcode');
             $destination_id=$this->input->post('final_destination');
             if(isset($_POST['is_destination'])){
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
             $result=$this->Useradministration_model->get_admin_email($role_id);
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
             $update= array('isSampleAtFinal' =>$is_destination);
             $this->db->where('sample_id', $this->input->post('barcode'));
             $this->db->update('tbl_registered_samples',$update);
             $this->db->insert('tbl_received_sample', $data_to_insert);
             $data_to_update= array('received_status' =>'received');
             $this->db->where('sample_id', $this->input->post('barcode'));
             $this->db->update('tbl_registered_samples',$data_to_update);
             $this->session->set_flashdata('sample_add_success_msg', '
                                    <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                    <p>Sample successfully added</p></div>
                                    ');
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
            if($this->email->send()){
                $this->session->set_flashdata('message','Notification email sent');
             
            redirect('' . $current_class . '/received_samples');
             }
            else{
                $this->session->set_flashdata('message','Some thing went wrong in sending notification email');
               redirect('' . $current_class . '/received_samples');
            }
      }
    }
public function received_samples()
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
            'district' => $this->Setups_model->get_districts(),
            'boolean_response' => $this->Lookups_model->get_boolean_response(),
        );
        $current_class = $this->router->fetch_class();
        $user_id = $this->session->userdata['user_id'];
        $data['data_get_all_received_samples'] = $this->Useradministration_model->get_all_received_samples();
        $this->load->view('header', $data);
        $this->load->view('sample_tracking/received_samples', $data);
        $this->load->view('footer', $data);
    }
    public function load_edit_sample_modal()
    {
        $location = $this->session->userdata['location'];
        $record_id = $this->input->post('record_id');
        $all_reg_sample_details = $this->Useradministration_model->get_all_reg_sample_details($record_id);
        $data = array(
            'record_id' => $record_id,
            'all_reg_sample_details' => $all_reg_sample_details,
            'diseases' => $this->Setups_model->get_disease_names(),
            'facility_code' => $this->Setups_model->get_facility_codes($location),
            'destination' => $this->Setups_model->get_destination(),
            'sample_id' => $this->Setups_model->get_sample_id(),
            'sample_status' => $this->Setups_model->get_sample_status(),
            'sample_type' => $this->Setups_model->get_sample_type(),
        );

        $this->load->view('sample_tracking/sample_update_modal', $data);

    }

    public function update_sample()
    {

        $current_class = $this->router->fetch_class();
        $record_id = $this->input->post('record_id');

        if (($record_id) !== '') {
            $user_id = $this->session->userdata['user_id'];
           $initialSampleDate=$this->input->post('initialSampleDate');
           $initialDate=date('Y-m-d',strtotime($initialSampleDate));
           $finalDestinationDate=$this->input->post('finalDestinationDate');
           $finalDate=date('Y-m-d',strtotime($finalDestinationDate));
           $id=$this->input->post('facility_code');
           $district=$this->reverselookups_model->get_district($id);
           $health_facility=$this->reverselookups_model->get_health_facility($id);
             $data_to_update = array(
                'sample_id' => $this->input->post('barcode'),
                'facility_code_id'=> $health_facility,
                'disease_id'=> $this->input->post('disease'),
                'sample_type_id'=> $this->input->post('sample_type'),
                'destination_id'=> $this->input->post('destination'),
                'clinical_notes'=> $this->input->post('notes'),
                'districtCode'=> $district,
                'initialSampleDate' =>$initialDate ,
                'finalDestinationDate' => $finalDate,
                'entered_by' =>$this->session->userdata['user_full_name']
            );

            $this->db->where('id', $record_id);
            $this->db->update('tbl_registered_samples', $data_to_update);
            $sample_id=$this->input->post('barcode');
            $message='Sample id'." ".$sample_id." ".'was updated';
            $notification=$this->notification->notify($message,$user_id);
        }
        $this->session->set_flashdata('healthFacility_add_success_msg', '
                                 <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                 <p>Record successfully updated</p></div>
                                 ');
        redirect('' . $current_class . '/registered_samples');
    }


    public function load_delete_sample_modal()
    {

        $record_id = $this->input->post('record_id');
        $all_reg_sample_details = $this->Useradministration_model->get_all_reg_sample_details($record_id);
        $current_class = $this->router->fetch_class();
        $data = array(
            'record_id' => $record_id,
            'all_reg_sample_details' => $all_reg_sample_details,
            'current_class' => $current_class,
        );

        $this->load->view('sample_tracking/sample_delete_modal', $data);

    }
   public function delete_sample()
    {
        $current_class = $this->router->fetch_class();
        $record_id = $this->input->post('record_id');
        $submit = $this->input->post('submit');
        if (isset($submit) and ($submit == 'yes')) {
            $this->db->where('id', $record_id);
            $this->db->delete('tbl_registered_samples');

            $this->session->set_flashdata('healthFacility_delete_success_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>Record successfully Deleted</p></div>
                                ');
            redirect('' . $current_class . '/registered_samples');
        }

    }
        public function load_edit_received_sample_modal()
    {
        $location = $this->session->userdata['location'];
        $record_id = $this->input->post('record_id');;
        $all_rec_sample_details = $this->Useradministration_model->get_all_rec_sample_details($record_id);
        $data = array(
            'record_id' => $record_id,
            'all_rec_sample_details' => $all_rec_sample_details,
            'diseases' => $this->Setups_model->get_disease_names(),
            'facility_code' => $this->Setups_model->get_facility_codes($location),
            'destination' => $this->Setups_model->get_destination(),
            'sample_id' => $this->Setups_model->get_sample_id(),
            'sample_status' => $this->Setups_model->get_sample_status(),
            'district' => $this->Setups_model->get_districts(),
            'sample_type' => $this->Setups_model->get_sample_type(),
        );

        $this->load->view('sample_tracking/rec_sample_update_modal', $data);

    }

    public function update_recsample()
    {
        $current_class = $this->router->fetch_class();
        $record_id = $this->input->post('record_id');
        $user_id = $this->session->userdata['user_id'];
        if (($record_id) !== '') {
           $dateReceived=$this->input->post('dateReceived');
           $Date=date('Y-m-d H:i:s',strtotime($dateReceived));
             $data_to_update = array(
                'sample_id' => $this->input->post('barcode'),
                'destination_id'=> $this->input->post('destination'),
                'date_received' => $Date,
                'entered_by' =>$this->session->userdata['user_full_name']
            );

            $this->db->where('id', $record_id);
            $this->db->update('tbl_received_sample', $data_to_update);
        }
        $this->session->set_flashdata('healthFacility_add_success_msg', '
                                 <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                 <p>Record successfully updated</p></div>
                                 ');
        // store notification
        $sample_id=$this->input->post('barcode');
        $message='Received sample id'." ".$sample_id." ".'at'." ".$this->input->post('destination')." ".'was updated';
        $notification=$this->notification->notify($message,$user_id);
        redirect('' . $current_class . '/received_samples');
    }


    public function load_delete_received_sample_modal()
    {

        $record_id = $this->input->post('record_id');
        $all_rec_sample_details = $this->Useradministration_model->get_all_rec_sample_details($record_id);
        $current_class = $this->router->fetch_class();
        $data = array(
            'record_id' => $record_id,
            'all_rec_sample_details' => $all_rec_sample_details,
            'current_class' => $current_class,
        );

        $this->load->view('sample_tracking/rec_sample_delete_modal', $data);

    }
    public function delete_received_sample()
    {
        $current_class = $this->router->fetch_class();
        $record_id = $this->input->post('record_id');
       
            $this->db->where('id', $record_id);
            $this->db->delete('tbl_received_sample');

            $this->session->set_flashdata('healthFacility_delete_success_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>Record successfully Deleted</p></div>
                                ');
            redirect('' . $current_class . '/received_samples');
       
    }
    function sample_not_exist($sample_id)
    {
        $this->form_validation->set_message('sample_not_exist','This sample is already registered');
        if($this->Useradministration_model->check_exist_sample($sample_id))
        {
            return FALSE;
        }else
        {
            return TRUE;  
        }
    }
    function received_sample_not_exist($sample_id)
    {
        $this->form_validation->set_message('received_sample_not_exist','This sample is already registered as received');
        $destination_id= $this->input->post('destination');
        if($this->Useradministration_model->check_exist_received_sample($sample_id,$destination_id))
        {
            return FALSE;
        }else
        {
            return TRUE;
        }
    }
   function fetch_destination()
        {
        if($this->input->post('sample_id'))
        {
          echo $this->Setups_model->fetch_destination($this->input->post('sample_id'));
        }
       }
       function fetch_disease()
        {
        if($this->input->post('sample_id'))
        {
          echo $this->Setups_model->fetch_disease($this->input->post('sample_id'));
        }
       }
       public function notifications()
        {
          $action='decrypt';
          $record_id=encryptor($action,$this->uri->segment(3));
          if(isset($record_id)){
          $data_to_update=array('expires_on'=>date('Y-m-d H:i:s'));
          $this->db->where('id', $record_id);
          $this->db->update('tbl_notifications', $data_to_update);
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
            'total_notifications' => $this->Dashboard_model->get_total_notifications(),
        );
        $data['get_all_notifications'] = $this->notification->get_all_notifications($id = '');
        $this->load->view('header', $data);
        $this->load->view('sample_tracking/notifications', $data);
        $this->load->view('footer', $data);
           }else{
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
            'total_notifications' => $this->Dashboard_model->get_total_notifications(),
        );
        $data['get_all_notifications'] = $this->notification->get_all_notifications($id = '');
        $this->load->view('header', $data);
        $this->load->view('sample_tracking/notifications', $data);
        $this->load->view('footer', $data);
           }
        
    }
    public function search()
    {
        @$this->page_session_expiry($this->session->userdata['user_org_id']);
        $current_method = 'registered_samples';
        $current_class = $this->router->fetch_class();
        $fm_name = $this->session->userdata['user_org_name'];
        $district=$this->input->post('district');
        $start_date=$this->input->post('start_date');
        $start_date=date('Y-m-d',strtotime($start_date));
        $end_date=$this->input->post('end_date');
        $end_date=date('Y-m-d',strtotime($end_date));
        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($current_method),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,
            'district' => $this->Setups_model->get_districts(),
            'boolean_response' => $this->Lookups_model->get_boolean_response(),
        );
        $current_class = $this->router->fetch_class();
        $user_id = $this->session->userdata['user_id'];
        $data['data_get_all_received_samples'] = $this->Useradministration_model->search_received_samples($start_date,$end_date,$district);
        $this->load->view('header', $data);
        $this->load->view('sample_tracking/received_samples', $data);
        $this->load->view('footer', $data);
    }
    public function results()
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
            'sample_id' => $this->Setups_model->fetch_sample_id()
        );
        $this->load->view('header', $data);
        $this->load->view('sample_tracking/confirm_results', $data);
        $this->load->view('footer', $data);

    }
    public function confirm_results()
    {
         
          $current_class = $this->router->fetch_class();
          $current_method = $this->router->fetch_method();
          $user_id = $this->session->userdata['user_id'];
          //file upload
            $path = 'uploads/';
            $config['upload_path'] = $path;
            $config['allowed_types'] = 'png|PNG|jpg|JPG|JPEG|jpeg|GIF|gif|pdf|PDF|docx';
            $config['remove_spaces'] = TRUE;
            $this->load->library('upload', $config);
            $this->upload->initialize($config);
            if (!$this->upload->do_upload('uploadFile')){
                $error = array('error' => $this->upload->display_errors());
                $inputFileName = "N/A"; 
            } else {
                $data = array('upload_data' => $this->upload->data());
                $upload_file = $data['upload_data']['file_name'];
               $inputFileName = $path . $upload_file;
            }
          $sample_id=$this->input->post('barcode');
          $test_result = $this->Setups_model->check_registered_sample($sample_id);
        if ($test_result > 0) {
        $this->session->set_flashdata('message','The record already exists');
         $fm_name = $this->session->userdata['user_org_name'];
         $register_sample='register_sample';
        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($register_sample),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,
            'boolean_response' => $this->Lookups_model->get_boolean_response(),
            'diseases' => $this->Setups_model->get_disease_names(),
            'sample_id' => $this->Setups_model->fetch_sample_id()
        );
        $this->load->view('header', $data);
        $this->load->view('sample_tracking/confirm_results', $data);
        $this->load->view('footer', $data);
        }else{
             $data_to_insert = array(
                'id' => NULL,
                'sample_id' => $this->input->post('barcode'),
                'disease'=> $this->input->post('disease'),
                'result'=> $this->input->post('result'),
                'note'=> $this->input->post('notes'),
                'updated_by'=> $user_id,
                'created_at' =>date('Y-m-d H:i:s'),
                'photo'=> $inputFileName,
            );
             $created_by=$this->reverselookups_model->get_user_name($user_id);
             $this->update_insert('tbl_test_results', $data_to_insert);
             $sample_id=$this->input->post('barcode');
             $message='Sample id'." ".$sample_id." ".'was successfully tested'." ".$this->input->post('result')." ".'of'." ".$this->input->post('disease');
             $notification=$this->notification->notify($message,$created_by, $registered_by="");
        $this->session->set_flashdata('sample_add_success_msg', '
                                    <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                    <p>Sample results successfully added</p></div>
                                    ');
        redirect('' . $current_class . '/test_results');
      }
    }
    public function test_results()
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
            'sample_id' => $this->Setups_model->fetch_sample_id(),
            'sample_results'=>$this->Setups_model->get_all_sample_results()
        );
        $this->load->view('header', $data);
        $this->load->view('sample_tracking/test_results', $data);
        $this->load->view('footer', $data);

    }
}