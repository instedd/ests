<?php if (!defined('BASEPATH')) exit('No direct script access allowed');
class Reports extends CI_Controller
{
    public function __construct()
    {
        header('Access-Control-Allow-Origin: *');
        header("Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE");
        parent::__construct();
        $this->load->library(array('session', 'form_validation','pagination'));
        $this->load->helper(array('utility', 'html', 'url', 'form', 'notification'));
        $this->load->database();
        $this->load->model(array('Lookups_model','Useradministration_model','Setups_model'));
        $this->load->model('Login_model');
        $this->load->model('Dashboard_model');
        $this->load->helper('download');
        $this->load->library('excel');
        $this->load->library('pdf');
        
    }

    public function view_reports()
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
            'total_registered_samples' => $this->Dashboard_model->get_total_registered_samples(),
            'total_undelayed_samples' => $this->Dashboard_model->get_total_undelayed_samples(),
            'total_delayed_samples' => $this->Dashboard_model->get_total_delayed_samples(),
            'last_segment' => 'dashboard'
        );
        $data['monthly_registered_samples']=$this->Dashboard_model->get_monthly_registered_samples();
        $data['monthly_delayed_samples']=$this->Dashboard_model->get_monthly_delayed_samples();
        $data['monthly_received_samples']=$this->Dashboard_model->get_monthly_received_samples();
        $data['registered_samples'] = $this->Useradministration_model->get_all_registered_samples();
        $data['received_samples'] = $this->Useradministration_model->get_all_received_sample();
        $data['received_samples_in_transit'] = $this->Useradministration_model->get_all_received_sample_in_transit();
        $this->load->view('header', $data);
        $this->load->view('reports', $data);
        $this->load->view('footer', $data);
    }
public function map()
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
            'total_undelayed_samples' => $this->Dashboard_model->get_total_undelayed_samples(),
            'total_delayed_samples' => $this->Dashboard_model->get_total_delayed_samples(),
            'last_segment' => 'dashboard',
        );
       $data['registered_samples_thisYear']=$this->Dashboard_model->get_registered_samples_thisYear();
       
       $this->load->view('header', $data);
        $this->load->view('maps', $data);
        //$this->load->view('footer', $data);
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
      public function sample_location()
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
        $current_class = $this->router->fetch_class();
        $user_id = $this->session->userdata['user_id'];
        $data['data_get_sample_location'] = $this->Useradministration_model->get_sample_location($id = '');
        $this->load->view('header', $data);
        $this->load->view('sample_tracking/sample_location', $data);
        $this->load->view('footer', $data);
  
        }
    public function registered_samples()
    {   
        $data['registered_samples'] = $this->Useradministration_model->get_all_registered_samples();
        $html=$this->load->view('reports/registered_samples',$data, true); //load the pdf_output.php by passing our data and get all data in $html varriable.
     
        //this the the PDF filename that user will get to download
        $pdfFilePath ="registered_samples-".time()."-download.pdf";
        //actually, you can pass mPDF parameter on this load() function
        $pdf=new Pdf();
        $pdf = $this->pdf->load();
        //generate the PDF!
        $pdf->WriteHTML($html,2);
        ob_end_clean();
        //offer it to user via browser download! (The PDF won't be saved on your server HDD)
        $pdf->Output($pdfFilePath, "D");
  }
    public function destination_samples()
    {   
        $data['received_samples'] = $this->Useradministration_model->get_all_received_sample();
        $html=$this->load->view('reports/destination_samples', $data,true);
        //this the the PDF filename that user will get to download
        $pdfFilePath ="received_samples-".time()."-download.pdf";
        //actually, you can pass mPDF parameter on this load() function
        $pdf = $this->pdf->load();
        //generate the PDF!
        $pdf->WriteHTML($html,2);
        //offer it to user via browser download! (The PDF won't be saved on your server HDD)
        $pdf->Output($pdfFilePath, "D");
    }
    public function transit_samples()
    {   
        $data['received_samples_in_transit'] = $this->Useradministration_model->get_all_received_sample_in_transit();
        $html=$this->load->view('reports/transit_samples', $data,true);
        //this the the PDF filename that user will get to download
        $pdfFilePath ="transit_samples-".time()."-download.pdf";
        //actually, you can pass mPDF parameter on this load() function
        $pdf = $this->pdf->load();
        //generate the PDF!
        $pdf->WriteHTML($html,2);
        //offer it to user via browser download! (The PDF won't be saved on your server HDD)
        $pdf->Output($pdfFilePath, "D");
    }
    public function reg_samples_excel()
    {
    require_once './application/third_party/PHPExcel.php';
    require_once './application/third_party/PHPExcel/IOFactory.php';

    // Create new PHPExcel object
    $objPHPExcel = new PHPExcel();

    $default_border = array(
        'style' => PHPExcel_Style_Border::BORDER_THIN,
        'color' => array('rgb' => '000000'),
    );

    $acc_default_border = array(
        'style' => PHPExcel_Style_Border::BORDER_THIN,
        'color' => array('rgb' => 'c7c7c7'),
    );
    $outlet_style_header = array(
        'font' => array(
            'color' => array('rgb' => '000000'),
            'size' => 10,
            'name' => 'Arial',
            'bold' => true,
        ),
    );
    $top_header_style = array(
        'borders' => array(
            'bottom' => $default_border,
            'left' => $default_border,
            'top' => $default_border,
            'right' => $default_border,
        ),
        'fill' => array(
            'type' => PHPExcel_Style_Fill::FILL_SOLID,
            'color' => array('rgb' => 'ffff03'),
        ),
        'font' => array(
            'color' => array('rgb' => '000000'),
            'size' => 15,
            'name' => 'Arial',
            'bold' => true,
        ),
        'alignment' => array(
            'vertical' => PHPExcel_Style_Alignment::VERTICAL_CENTER,
            'horizontal' => PHPExcel_Style_Alignment::HORIZONTAL_CENTER,
        ),
    );
    $style_header = array(
        'borders' => array(
            'bottom' => $default_border,
            'left' => $default_border,
            'top' => $default_border,
            'right' => $default_border,
        ),
        'fill' => array(
            'type' => PHPExcel_Style_Fill::FILL_SOLID,
            'color' => array('rgb' => 'ffff03'),
        ),
        'font' => array(
            'color' => array('rgb' => '000000'),
            'size' => 12,
            'name' => 'Arial',
            'bold' => true,
        ),
        'alignment' => array(
            'vertical' => PHPExcel_Style_Alignment::VERTICAL_CENTER,
            'horizontal' => PHPExcel_Style_Alignment::HORIZONTAL_LEFT,
        ),
    );
    $account_value_style_header = array(
        'borders' => array(
            'bottom' => $default_border,
            'left' => $default_border,
            'top' => $default_border,
            'right' => $default_border,
        ),
        'font' => array(
            'color' => array('rgb' => '000000'),
            'size' => 12,
            'name' => 'Arial',
        ),
        'alignment' => array(
            'vertical' => PHPExcel_Style_Alignment::VERTICAL_CENTER,
            'horizontal' => PHPExcel_Style_Alignment::HORIZONTAL_LEFT,
        ),
    );
    $text_align_style = array(
        'alignment' => array(
            'horizontal' => PHPExcel_Style_Alignment::HORIZONTAL_CENTER,
            'vertical' => PHPExcel_Style_Alignment::VERTICAL_CENTER,
        ),
        'borders' => array(
            'bottom' => $default_border,
            'left' => $default_border,
            'top' => $default_border,
            'right' => $default_border,
        ),
        'fill' => array(
            'type' => PHPExcel_Style_Fill::FILL_SOLID,
            'color' => array('rgb' => 'ffff03'),
        ),
        'font' => array(
            'color' => array('rgb' => '000000'),
            'size' => 12,
            'name' => 'Arial',
            'bold' => true,
        ),
    );

    $objPHPExcel->setActiveSheetIndex(0)->mergeCells('A1:H1');
    $objPHPExcel->getActiveSheet()->setCellValue('A1', 'Registered samples Report');

    $objPHPExcel->getActiveSheet()->getStyle('A1')->applyFromArray($top_header_style);
    $objPHPExcel->getActiveSheet()->getStyle('B1')->applyFromArray($top_header_style);
    $objPHPExcel->getActiveSheet()->getStyle('C1')->applyFromArray($top_header_style);
    $objPHPExcel->getActiveSheet()->getStyle('D1')->applyFromArray($top_header_style);
    $objPHPExcel->getActiveSheet()->getStyle('E1')->applyFromArray($top_header_style);
    $objPHPExcel->getActiveSheet()->getStyle('F1')->applyFromArray($top_header_style);
    $objPHPExcel->getActiveSheet()->getStyle('G1')->applyFromArray($top_header_style);

    $objPHPExcel->getActiveSheet()->setCellValue('A2', 'Sample Id');
    $objPHPExcel->getActiveSheet()->setCellValue('B2', 'Facility');
    $objPHPExcel->getActiveSheet()->setCellValue('C2', 'Sample Type');
    $objPHPExcel->getActiveSheet()->setCellValue('D2', 'Destination');
    $objPHPExcel->getActiveSheet()->setCellValue('E2', 'Disease');
    $objPHPExcel->getActiveSheet()->setCellValue('F2', 'Registration Date');
    $objPHPExcel->getActiveSheet()->setCellValue('G2', 'Expected Destination Date');
   
    $objPHPExcel->getActiveSheet()->getStyle('A2')->applyFromArray($style_header);
    $objPHPExcel->getActiveSheet()->getStyle('B2')->applyFromArray($style_header);
    $objPHPExcel->getActiveSheet()->getStyle('C2')->applyFromArray($style_header);
    $objPHPExcel->getActiveSheet()->getStyle('D2')->applyFromArray($style_header);
    $objPHPExcel->getActiveSheet()->getStyle('E2')->applyFromArray($style_header);
    $objPHPExcel->getActiveSheet()->getStyle('F2')->applyFromArray($style_header);
    $objPHPExcel->getActiveSheet()->getStyle('G2')->applyFromArray($style_header);

    $objPHPExcel->getActiveSheet()->getColumnDimension('A')->setWidth(25);
    $objPHPExcel->getActiveSheet()->getColumnDimension('B')->setWidth(25);
    $objPHPExcel->getActiveSheet()->getColumnDimension('C')->setWidth(25);
    $objPHPExcel->getActiveSheet()->getColumnDimension('D')->setWidth(25);
    $objPHPExcel->getActiveSheet()->getColumnDimension('E')->setWidth(25);
    $objPHPExcel->getActiveSheet()->getColumnDimension('F')->setWidth(25);
    $objPHPExcel->getActiveSheet()->getColumnDimension('G')->setWidth(25);
    $objPHPExcel->getActiveSheet()->getRowDimension('1')->setRowHeight(30);

    $row = 3;
    $data  = $this->Useradministration_model->getdata();
    foreach ($data  as $value)
    {
        
        $objPHPExcel->getActiveSheet()->setCellValue('A'.$row, $value->sample_id);
        $objPHPExcel->getActiveSheet()->setCellValue('B'.$row, $value->facility_code_id);
        $objPHPExcel->getActiveSheet()->setCellValue('C'.$row, $value->sample_type_id);
        $objPHPExcel->getActiveSheet()->setCellValue('D'.$row, $value->destination_id);
        $objPHPExcel->getActiveSheet()->setCellValue('E'.$row, $value->disease_type);
        $objPHPExcel->getActiveSheet()->setCellValue('F'.$row, $value->initialSampleDate);
        $objPHPExcel->getActiveSheet()->setCellValue('G'.$row, $value->finalDestinationDate);
        $row++;
    }

    header('Content-Type: application/vnd.ms-excel');
    header('Content-Disposition: attachment;filename="Registered_sample.xls"');
    $objWriter = PHPExcel_IOFactory::createWriter($objPHPExcel, 'Excel5');
    $objWriter->save('php://output');
    }
     public function sampleDetails()
    {
        $action='decrypt';
        $sample_id=encryptor($action,$this->uri->segment(3));
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
            'last_segment' => 'dashboard'
        );
        
        $data['sample_details'] = $this->Useradministration_model->get_sample_details($sample_id);
        $this->load->view('header', $data);
        $this->load->view('sample_details', $data);
        $this->load->view('footer', $data);
    }
}