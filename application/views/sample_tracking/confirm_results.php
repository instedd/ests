<?php $admin_privilege = (cud_role_check((int)($this->session->userdata['user_group_id']))); ?>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
 <?php include('sidebar.php');?>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <section class="content-header">
      <h1>
        Test Results Confirmation
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
                        echo form_open("SampleTracking/confirm_results", $attributes); ?>
                        <fieldset>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="">Sample Id</label>
                                <?php
                                $attrib_role = 'class = " form-control" name="barcode" id = "sampleId" required';
                                echo form_dropdown('barcode', $sample_id, set_value('barcode'), $attrib_role);
                                ?>
                                <span class="text-danger help-block small with-errors">
                                    <?= form_error('barcode'); ?>
                                </span>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="disease_name">Suspected Disease</label>
                                <select name="disease" id="disease_name" class="form-control" readonly>
                                 <option value=""></option>
                                </select>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="">Confirmation of finding</label>
                                <select name="result" id="result" class="form-control">
                                <option value="Negative">Negative</option>
                                <option value="Positive">Positive</option>
                              </select> 
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="uploadFile"> Upload a picture of the results/Test form </label>
                                 <input type="file" name="uploadFile" id="uploadFile"
                                  class="form-control" value=""/>
                            </div>

                            <div class="col-md-6 form-group">
                                <label class="control-label" for="notes"> Clinical Notes</label>
                                <textarea name="notes" id="notes"
                                       type="textarea" placeholder="Please fill in the comments"
                                       class="form-control"
                                       value="<?= set_value('notes'); ?>"></textarea>
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
