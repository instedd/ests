<?php $admin_privilege = (cud_role_check((int)($this->session->userdata['user_group_id']))); ?>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
 <?php include('sidebar.php');?>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Registered Samples
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
        <div class="box-header">
                <a href="<?=base_url('SampleTracking/register_sample');?>"> <div class="btn btn-primary waves-effect waves-light"> 
                            Register Sample
                </div>
                </a>
                <div class="btn btn-primary waves-effect waves-light" data-toggle="modal" data-target="#filter"> 
                            Filter Records
                </div>
              </div>
        <div class="box-body  no-padding">
          <div class="col-xs-12 table-responsive">
                    <table class="table table-striped">
                    <thead>
                    <tr class="info">
                        <th>#</th>
                        <th>Sample Id</th>
                        <th>Health Facility</th>
                        <th>Suspected Disease</th>
                        <th>Specimen</th>
                        <th>Destination</th>
                        <th>Received Status</th>
                       <th>Transporter</th>
                        <th>Date of sample taking</th>
                        <th>Date to reach destination</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <?php
                    $i = 1;
                    $app = 1;
                    if(isset($data_get_all_registered_samples)){
                    foreach ($data_get_all_registered_samples as $row_registered_samples) {
                        ?>
                        <tr>
                            <td><?= $app; ?>.</td>
                            <td><?= $row_registered_samples->sample_id; ?></td>
                            <td><?= $row_registered_samples->facility_code_id; ?></td>
                             <td><?= $row_registered_samples->disease_id; ?></td>
                            <td><?= $row_registered_samples->sample_type_id; ?></td>
                            <td><?= $row_registered_samples->destination_id; ?></td>
                            <td><?= $row_registered_samples->received_status; ?></td>
                            <td><?php if(!empty($row_registered_samples->transporter)){
                                echo $this->reverselookups_model->get_transporter($row_registered_samples->transporter);
                            }?>
                          </td>
                            <td><?= date('d,M Y',strtotime($row_registered_samples->initialSampleDate)); ?> at <?= date('H:i:s',strtotime($row_registered_samples->initialSampleDate)); ?> </td>
                            <td><?= date('d,M Y',strtotime($row_registered_samples->finalDestinationDate)); ?></td>
                            <td>
                                <button type="button" data-record_id="<?= $row_registered_samples->id; ?>"
                                        class="btn btn-add btn-xs edit-registeredSample" data-toggle="modal"
                                        data-target="#updateRegisteredSample
                                        "><i class="fa fa-pencil"></i></button>
                                <button type="button" data-record_id="<?= $row_registered_samples->id; ?>"
                                        class="btn btn-danger btn-xs delete-registeredSample" data-toggle="modal"
                                        data-target="#deleteRegisteredSample"><i class="fa fa-trash-o"></i></button>
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
       
<div class="box-body  no-padding">
          <div class="col-xs-12 table-responsive">
                    <table class="table table-striped">
                    <thead>
                    <tr class="info">
                        <th>#</th>
                        <th>Sample Id</th>
                        <th>Form photo</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <?php
                    $i = 1;
                    $app = 1;
                    if(isset($data_get_all_registered_samples)){
                    foreach ($data_get_all_registered_samples as $row_registered_samples) {
                    $str=substr($row_registered_samples->file_path,8);
                    $photo=!empty($str)?$str:"N/A";
                      if(!empty($str)){
                        ?>
                        <tr>
                            <td><?= $app; ?>.</td>
                            <td><?= $row_registered_samples->sample_id; ?></td>
                            <td><?= $photo; ?></td>
                            <td>
                                <button type="button" data-record_id="<?= $row_registered_samples->id; ?>"
                                        class="btn btn-add btn-xs edit-registeredSample" data-toggle="modal"
                                        data-target="#updateRegisteredSample
                                        "><i class="fa fa-pencil"></i></button>
                                <button type="button" data-record_id="<?= $row_registered_samples->id; ?>"
                                        class="btn btn-danger btn-xs delete-registeredSample" data-toggle="modal"
                                        data-target="#deleteRegisteredSample"><i class="fa fa-trash-o"></i></button>
                            </td>
                        </tr>
                        <?php $app++;
                    }} 
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
  <div class="modal fade" id="filter" role="dialog" aria-hidden="true">
  <div class="modal-dialog modal-lg ">
        <div class="modal-content">
            <div class="modal-header modal-header-primary">
                <button type="button" class="btn btn-rounded close" data-dismiss="modal" aria-hidden="true"><i
                        class="fa fa-times-circle" aria-hidden="true"></i></button>
                <h3><i class="fa fa-filter"></i>Filter records</h3>
            </div>
            <div class="modal-body">
  <div class="row">
    <div class="col-md-12">
   <?php
          $attributes = array(
              "class" => "form-inline",
              "id" => "register_sample_form",
              "name" => "register_sample_form",
              "role" => "form", "data-toggle" => "validator",
              "enctype"=>"multipart/form-data"
          );
    echo form_open("SampleTracking/filter", $attributes); ?>
  <div class="form-group">
    <label>From</label>
    <div class="input-group date">
	<input name="start_date" id="datepicker1"
		   type="text"
		   class="datepicker"
		   value="<?= set_value('start_date'); ?>" required/>
	<span class="text-danger help-block small with-errors">
				<?= form_error('start_date'); ?>
			</span>
   </div>
  </div>
  <div class="form-group">
    <label for="pwd">to:</label>
    <div class="input-group date">
	<input name="end_date" id="datepicker1"
		   type="text"
		   class="datepicker"
		   value="<?= set_value('end_date'); ?>" required/>
	<span class="text-danger help-block small with-errors">
				<?= form_error('end_date'); ?>
			</span>
   </div>
  </div>
  <div class="form-group">
  <label>Filter by district</label>
  <?php
  $attrib_role = 'class = " form-control" name="district" id = "district"';
  echo form_dropdown('district', $district, set_value('district'), $attrib_role);
  ?>
  <span class="text-danger help-block small with-errors">
      <?= form_error('district'); ?>
  </span>
</div>
 <div class="col-md-12 form-group user-form-group">
	<div class="pull-right">
		<button type="reset" class="btn btn-danger">Reset</button>
		<button type="submit" class="btn btn-primary">Filter</button>
	</div>
</div>
<?= form_close(); ?>
  </div>
</div>
</div>
</div>
</div>
</div>
<div class="modal fade" id="addSample" tabindex="-1" role="dialog" aria-hidden="true"></div>
<div class="modal fade" id="updateRegisteredSample" tabindex="-1" role="dialog" aria-hidden="true"></div>
<div class="modal fade" id="deleteRegisteredSample" tabindex="-1" role="dialog" aria-hidden="true"></div>
