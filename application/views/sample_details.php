 <body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
 <?php include('sidebar.php');?>
 <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
       Sample ID
       
        <small># <?=$sample_details['reg_sample_id'];?></small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i>Dashboard</a></li>
        <li><a href="#">Sample</a></li>
        <li class="active">Sample details</li>
      </ol>
    </section>
   <?php 
   $CI =& get_instance();
   $CI->load->model('Reverselookups_model');
   $district=$CI->Reverselookups_model->get_district_name($sample_details['reg_district']);
   
   ?>
    <!-- Main content -->
    <section class="invoice">
      <!-- title row -->
      <div class="row">
        <div class="col-xs-12">
          <h2 class="page-header">
            <i class="fa fa-globe"></i> Sample Summary
            <small class="pull-right">Date: <?=date('d/m/Y');?></small>
          </h2>
        </div>
        <!-- /.col -->
      </div>
      
  
      <!-- info row -->
      <div class="row invoice-info">
        <div class="col-sm-6 invoice-col">
           <h4> <strong>Sample Registration Date:</strong><span class="pull-right badge bg-blue"><?=date('Y-m-d',strtotime($sample_details['initialSampleDate']))?></span></h4><br>
            <h4><strong>Expected Destination Date:</strong><span class="pull-right badge bg-aqua"><?=$sample_details['finalDestinationDate']?></span></h4><br>
            <h4><strong>Destination:</strong><span class="pull-right badge bg-green"><?=$sample_details['destination_id']?></span></h4><br>
         
         <h4><strong>Registered By:<span class="pull-right badge bg-red"><?=$sample_details['registered_by']?></strong></span></h4>
        </div>
        <!-- /.col -->
        <div class="col-sm-6 invoice-col">
          <h4><strong>Place of Registration:</strong></strong><span class="pull-right badge bg-blue"><?=$sample_details['facility_code_id'].", ".$district;?> district</span></h4><br>
         <h4><strong>Suspected Disease:</strong></strong><span class="pull-right badge bg-aqua"><?=$sample_details['disease_id'];?></span></h4><br>
        <h4> <strong>Sample Type:</strong></strong><span class="pull-right badge bg-green"><?=$sample_details['sample_type_id'];?></span></h4><br>
         <h4><strong>Clinical notes:</strong><span class="pull-right badge bg-green"><?=$sample_details['clinical_notes'];?></span></h4><br>
        </div>
       
      </div>
      <!-- /.row -->
        <div class="row">
        <div class="col-xs-12">
          <h2 class="page-header">
            <i class="fa fa-globe"></i> Received Sample Summary
          </h2>
        </div>
        <!-- /.col -->
      </div>
      <!-- Table row -->
      <div class="row">
        <div class="col-xs-12 table-responsive">
          <table class="table table-striped">
            <thead>
            <tr>
              <th>Destination</th>
              <th>Transit Point</th>
              <th>District</th>
              <th>Sample Status</th>
              <th>Date Received</th>
              <th>Received By</th>
              <th>Delivered By</th>
              <th>Time Taken</th>
            </tr>
            </thead>
            <tbody>
             <?php
             if(isset($sample_details['received_status'])&&$sample_details['received_status']=='received'){
              $received_sample_details=$CI->Reverselookups_model->get_received_sample_details($sample_details['sample_id']);
  
             foreach ($received_sample_details as $value) {
              $transporter=$CI->Reverselookups_model->get_transporter_name($value->delivered_by);
              $secs=(strtotime($value->date_received)-strtotime($sample_details['initialSampleDate']));
              ?>
            <tr>
              <td><?=$value->destination_id?></td>
              <td><?=$value->facility_code_id?></td>
              <td><?=$value->districtCode?></td>
              <td><?=$value->sample_status?></td>
              <td><?=$value->date_received?></td>
              <td><?=$value->entered_by?></td>
              <td><?=$transporter?></td>
              <td><?= secsToStr($secs); ?></td>
            </tr>
           <?php   }} ?>
            </tbody>
          </table>
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
      <!-- this row will not appear when printing -->
      <div class="row no-print">
        <div class="col-xs-12">
          <a href="invoice-print.html" target="_blank" class="btn btn-default"><i class="fa fa-print"></i> Print</a>
          <!-- <button type="button" class="btn btn-success pull-right"><i class="fa fa-credit-card"></i> Submit Payment
          </button>
          <button type="button" class="btn btn-primary pull-right" style="margin-right: 5px;">
            <i class="fa fa-download"></i> Generate PDF
          </button> -->
        </div>
      </div>
    </section>
    <!-- /.content -->
    <div class="clearfix"></div>
  </div>
   </div>
  <!-- /.content-wrapper -->

  



