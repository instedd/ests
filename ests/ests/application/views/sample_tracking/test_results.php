<?php $admin_privilege = (cud_role_check((int)($this->session->userdata['user_group_id']))); ?>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
 <?php include('sidebar.php');?>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Sample Test Results
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
               <?php
            if ($this->session->flashdata('result_add_success_msg') != ''):
                echo $this->session->flashdata('result_add_success_msg');
            endif;
            if ($this->session->flashdata('result_delete_success_msg') != ''):
                echo $this->session->flashdata('result_delete_success_msg');
            endif;
            ?>
        <div class="box-body  no-padding">
          <div class="col-xs-12 table-responsive">
                    <table class="table table-striped">
                    <thead>
                    <tr class="info">
                        <th>#</th>
                        <th>Sample Id</th>
                        <th>Suspected Disease</th>
                        <th>Clinical Notes</th>
                        <th>Test Result</th>
                        <th>Test Form </th>
                        <th>Created on</th>
                    </tr>
                    </thead>
                    <tbody>
                    <?php
                    $i = 1;
                    $app = 1;
                    if(isset($sample_results)){
                    foreach ($sample_results as $result) {
                        $str=substr($result->photo,8);
                        $photo=!empty($str)?$str:"N/A";
                        ?>
                        <tr>
                            <td><?= $app; ?>.</td>
                            <td><?= $result->sample_id; ?></td>
                            <td><?= $result->disease; ?></td>
                            <td><?= $result->note; ?></td>
                            <td><?= $result->result; ?></td>
                            <td><?=$photo?></td>
                            <td><?= $result->created_at; ?></td>

                           <!-- <td>
                                <button type="button" data-record_id="<?= $result->id; ?>"
                                        ><i class="fa fa-downlod"></i></button>-->
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
