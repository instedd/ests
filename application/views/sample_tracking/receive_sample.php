<?php $admin_privilege = (cud_role_check((int)($this->session->userdata['user_group_id']))); ?>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
 <?php include('sidebar.php');?>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <section class="content-header">
      <h1>
        Received Samples
      </h1>
      <ol class="breadcrumb">
        <li><a href="<?=base_url();?>/Dashboard/index"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active"><a href="<?=base_url();?>/Dashboard/index">Dashboard</a></li>
      </ol>
    </section> 
    <!-- Content Header (Page header) -->  
  <section class="content container-fluid">
  <?php
   $admin_roles=array(1,7,8,9,10,13,14);
   $role_id=$this->session->userdata['role_id'];
  ?>
  <div class="row">
    <div class="col-md-12">
      <div class="box box-info pull-right">
        <div class="box-body  no-padding">
           <div class="row">
                    <div class="col-md-12">
                        <?php
                        $attributes = array(
                            "class" => "",
                            "id" => "register_sample_form",
                            "name" => "register_sample_form",
                            "role" => "form", "data-toggle" => "validator",
                            "enctype"=>"multipart/form-data"
                        );
                        echo form_open("SampleTracking/receiveSample", $attributes); ?>
                        <fieldset>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="destination">Sample Id</label>
                                <?php
                                $attrib_role = 'class = " form-control" name="barcode" id = "barcode" required';
                                echo form_dropdown('barcode', $sample_id, set_value('barcode'), $attrib_role);
                                ?>
                                <span class="text-danger help-block small with-errors">
                                    <?= form_error('barcode'); ?>
                                </span>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="status">Sample Status</label>
                                <?php
                                $attrib_role = 'class = " form-control" name="sample_status" id = "status" required';
                                echo form_dropdown('sample_status',$sample_status, set_value('sample_status'), $attrib_role);
                                ?>
                                <span class="text-danger help-block small with-errors">
                                    <?= form_error('sample_status'); ?>
                                </span>
                            </div>
                            <?php if(in_array($role_id, $admin_roles)):?>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="status">Is sample at final destination?</label>
                                
                                <?php
                                $attrib_role = 'class = " form-control" name="is_destination" id = "is_destination" required';
                                echo form_dropdown('is_destination',$is_destination, set_value('is_destination'), $attrib_role);
                                ?>
                                <span class="text-danger help-block small with-errors">
                                    <?= form_error('is_destination'); ?>
                                </span>
                            </div>
                           <?php endif?>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="facility_code">Transit Point</label>
                                
                                <?php
                                $attrib_role = 'class = " form-control" name="facility_code" id = "facility_code" required';
                                echo form_dropdown('facility_code',$facility_code, set_value('facility_code'), $attrib_role);
                                ?>
                                <span class="text-danger help-block small with-errors">
                                    <?= form_error('facility_code'); ?>
                                </span>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="final_destination"> Destination</label>
                                <select name="final_destination" id="final_destination" class="form-control" readonly>
                                 <option value=""></option>
                                </select>
                              </div>
                             <!-- <div class="col-md-6 form-group">
                                <label class="control-label" for="dateReceived"> Received On:</label>
                                 <div class="input-group date">
                                <input name="dateReceived" id="dateReceived"
                                       type="text" placeholder=""
                                       class="datepicker"
                                       value="<?= set_value('dateReceived'); ?>" required/>
                                <span class="text-danger help-block small with-errors">
                                            <?= form_error('dateReceived'); ?>
                                 </span>
                                    </div>
                            </div>-->

                            <div class="col-md-6 form-group">
                                <label class="control-label" for="destination">Delivered by </label>
                                <?php
                                $attrib_role = 'class = " form-control" name="transporter" id = "transporter" required';
                                echo form_dropdown('transporter', $transporter_name, set_value('transporter'), $attrib_role);
                                ?>
                                <span class="text-danger help-block small with-errors">
                                    <?= form_error('transporter'); ?>
                                </span>
                            </div>
                                  <div class="col-md-12 form-group user-form-group">
                                <div class="pull-right">
                                    <button type="reset" class="btn btn-danger btn-md">Cancel</button>
                                    <button type="submit" class="btn btn-primary btn-md">Submit </button>
                                </div>
                            </div>

                       <div class="col-md-9 pull-right"><font color="#AA0000" face="Arial">
      <?php
          if(validation_errors()){
            ?>
            <div class="alert alert-danger text-center">
              <?php echo validation_errors(); ?>
            </div>
            <?php
          }
 
        if($this->session->flashdata('message')){
          ?>
          <div class="alert alert-danger text-center">
            <?php echo $this->session->flashdata('message'); ?>
          </div>
          <?php
        } 
        ?>
     </font>
     </div>
        </div>
       
      </div>
    </div>
  </section>
  
<div class="modal fade" id="addDiseases" tabindex="-1" role="dialog" aria-hidden="true"></div>
<div class="modal fade" id="updateDiseases" tabindex="-1" role="dialog" aria-hidden="true"></div>
<div class="modal fade" id="deleteDiseases" tabindex="-1" role="dialog" aria-hidden="true"></div>
