<?php $admin_privilege = (cud_role_check((int)($this->session->userdata['user_group_id']))); ?>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
 <?php include('sidebar.php');?>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Last Known Transit Point For A Sample
      </h1>
      <ol class="breadcrumb">
        <li><a href="<?=base_url();?>/Dashboard/index"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active"><a href="<?=base_url();?>/Dashboard/index">Dashboard</a></li>
      </ol>
    </section>   
  <section class="content container-fluid">
  <div class="row">
    <div class="col-md-12">
      <div class="box box-info pull-right">
        <div class="box-header with-border">
          
            </div>
               <?php
            if ($this->session->flashdata('disease_add_success_msg') != ''):
                echo $this->session->flashdata('disease_add_success_msg');
            endif;
            if ($this->session->flashdata('disease_delete_success_msg') != ''):
                echo $this->session->flashdata('disease_delete_success_msg');
            endif;
            ?>
        <div class="box-body  no-padding">
          <div class="col-xs-12 table-responsive">
                    <table class="table table-striped">
                    <thead>
                    <tr class="info">
                        <th>#</th>
                        <th>Sample Id</th>
                        <th>Last Received At</th>
                        <th>District</th>
                        <th>Date Received</th>
                    </tr>
                    </thead>
                    <tbody>
                    <?php
                    $i = 1;
                    $app = 1;
                    if(isset($data_get_sample_location)){
                    foreach ($data_get_sample_location as $row_receieved_samples) {
                        $date_received=date('d,M Y',strtotime($row_receieved_samples->date_received));
                       if(!empty($row_receieved_samples->facility_code_id)){
                         $facility_code=$row_receieved_samples->facility_code_id;
                       }else{
                        $facility_code="Not availabe";
                       }
                       if(!empty($row_receieved_samples->districtCode)){
                         $districtCode=$row_receieved_samples->districtCode;
                       }else{
                        $districtCode="Not availabe";
                       }
                        ?>
                        <tr>
                            <td><?= $app; ?>.</td>
                            <td><?= $row_receieved_samples->sample_id; ?></td>
                            <td><?= $facility_code; ?></td>
                            <td><?= $districtCode; ?></td>
                            <td><?= $date_received ?></td>
                           
                        </tr>
                        <?php $app++;
                    } 
                    }else
                    {
                    echo "No records found";    
                    }?>
                    
                    </tbody>
                </table>
            </div>
        </div>
       
        </div>
     
      </div>
    </div>
  </section>


  
  
  
  
  
<div class="modal fade" id="addRec_sample" tabindex="-1" role="dialog" aria-hidden="true"></div>
<div class="modal fade" id="updateReceivedSample" tabindex="-1" role="dialog" aria-hidden="true"></div>
<div class="modal fade" id="deleteReceivedSample" tabindex="-1" role="dialog" aria-hidden="true"></div>

