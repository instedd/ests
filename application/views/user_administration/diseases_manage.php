<?php $admin_privilege = (cud_role_check((int)($this->session->userdata['user_group_id']))); ?>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
 <?php include('sidebar.php');?>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <section class="content-header">
      <h1>
        Disease Management
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
          <div class="btn-group">
                <div class="btn btn-primary waves-effect waves-light add-diseases" data-toggle="modal" data-target="#addDiseases">
                            Add Diseases
                </div>

                <!--<button class="btn btn-pink waves-effect waves-light m-b-5"> <span>Book Flight</span> <i class="fa fa-plane m-l-5"></i> </button>-->
            </div>
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
                        <th>Name</th>
                        <th>Entry Date</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <?php
                    $i = 1;
                    $app = 1;
					if(isset($data_get_all_diseases)){
                    foreach ($data_get_all_diseases as $row_disease) {
                        ?>
                        <tr>
                            <td><?= $app; ?>.</td>
                            <td><?= $row_disease->name; ?></td>
                            <td><?= $row_disease->entry_date; ?></td>
                            
                            <td>
                                <button type="button" data-record_id="<?= $row_disease->id; ?>"
                                        class="btn btn-add btn-xs edit-diseases" data-toggle="modal"
                                        data-target="#updateDiseases"><i class="fa fa-pencil"></i></button>
                                <button type="button" data-record_id="<?= $row_disease->id; ?>"
                                        class="btn btn-danger btn-xs delete-diseases" data-toggle="modal"
                                        data-target="#deleteDiseases"><i class="fa fa-trash-o"></i></button>
                            </td>
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
  </section>


  
  
  
  
  
<div class="modal fade" id="addDiseases" tabindex="-1" role="dialog" aria-hidden="true"></div>
<div class="modal fade" id="updateDiseases" tabindex="-1" role="dialog" aria-hidden="true"></div>
<div class="modal fade" id="deleteDiseases" tabindex="-1" role="dialog" aria-hidden="true"></div>
