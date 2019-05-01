<?php $admin_privilege = (cud_role_check((int)($this->session->userdata['user_group_id']))); ?>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
 <?php include('sidebar.php');?>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Health Facility Management
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
                <div class="btn btn-primary waves-effect waves-light add-healthyFacility" data-toggle="modal" data-target="#addHealthyFacility">
                            Add Health Facility
                </div>
            </div>

                <div class="btn btn-primary pull-right waves-effect waves-light" data-toggle="modal" data-target="#import_facility">
                            Bulk Import
                </div> 
        </div>
        <?php
            if ($this->session->flashdata('healthFacility_add_success_msg') != ''):
                echo $this->session->flashdata('healthFacility_add_success_msg');
            endif;
            if ($this->session->flashdata('healthFacility_delete_success_msg') != ''):
                echo $this->session->flashdata('healthFacility_delete_success_msg');
            endif;
            ?>
        <div class="box-body  no-padding">
          <div class="col-xs-12 table-responsive">
                    <table id="example1" class="table table-striped">
                    <thead>
                    <tr class="info">
                        <th>#</th>
                        <th>Name</th>
                        <th>Hub</th>
                        <th>District Code</th>
                        <th>District</th>
                        <th>Entry Date</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <?php
                    $i = 1;
                    $app = 1;
					if(isset($data_get_all_health_facilities)){
                    foreach ($data_get_all_health_facilities as $row_healthyFacility) {
                        ?>
                        <tr>
                            <td><?= $app; ?>.</td>
                            <td><?= $row_healthyFacility->name; ?></td>
                            <td><?= $row_healthyFacility->hub; ?></td>
                            <td><?= $row_healthyFacility->code; ?></td>
                            <td><?= $row_healthyFacility->district; ?></td>
                            <td><?= $row_healthyFacility->created_at; ?></td>
                            <td>
                                <button type="button" data-record_id="<?= $row_healthyFacility->id; ?>"
                                        class="btn btn-add btn-xs edit-healthyFacility" data-toggle="modal"
                                        data-target="#updateHealthyFacility"><i class="fa fa-pencil"></i></button>
                                <button type="button" data-record_id="<?= $row_healthyFacility->id; ?>"
                                        class="btn btn-danger btn-xs delete-healthyFacility" data-toggle="modal"
                                        data-target="#deleteHealthyFacility"><i class="fa fa-trash-o"></i></button>
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
  <div class="modal fade" id="import_facility" role="dialog" aria-hidden="true">
  <div class="modal-dialog modal-dialog ">
        <div class="modal-content">
            <div class="modal-header modal-header-primary">
                <button type="button" class="btn btn-rounded close" data-dismiss="modal" aria-hidden="true"><i
                        class="fa fa-times-circle" aria-hidden="true"></i></button>
                <h3><i class="fa fa-plus m-r-5"></i> Import Health Facilities</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <?php
                        echo form_open_multipart("UserAdministration/import_facilities"); ?>
                        <fieldset>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="role_name"></label>
                                <input type="file" name="uploadFile" id="uploadFile"
                                  class="form-control" value="" required/>
                                <span class="text-danger help-block small with-errors">
                                            <?= form_error('uploadFile'); ?>
                                        </span>
                            </div>
                            <div class="col-md-12 form-group user-form-group">
                                <div class="">
                                    <button type="submit" name="submit" class="btn btn-primary btn-md">Import</button>
                                </div>
                            </div>
                        </fieldset>
                        <?= form_close(); ?>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
<div class="modal fade" id="addHealthyFacility" tabindex="-1" role="dialog" aria-hidden="true"></div>
<div class="modal fade" id="updateHealthyFacility" tabindex="-1" role="dialog" aria-hidden="true"></div>
<div class="modal fade" id="deleteHealthyFacility" tabindex="-1" role="dialog" aria-hidden="true"></div>
