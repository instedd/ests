<?php $admin_privilege = (cud_role_check((int)($this->session->userdata['user_group_id']))); ?>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
 <?php include('sidebar.php');?>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Transit Points
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
                        echo form_open("UserAdministration/add_transit_point", $attributes); ?>
                        <fieldset>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="transit_point">Transit Point Name</label>
                                <input name="transit_point" id="transit_point"
                                       type="text"
                                       class="form-control"
                                       value="<?= set_value('transit_point'); ?>" required/>
                                <span class="text-danger help-block small with-errors">
                                           
                                        </span>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="district">District</label>
                                <?php
                                $attrib_role = 'class = " form-control" name="district" id = "district" required';
                                echo form_dropdown('district', $district, set_value('district'), $attrib_role);
                                ?>
                                <span class="text-danger help-block small with-errors">
                                    <?= form_error('district'); ?>
                                </span>
                            </div>
                             <div class="col-md-6 form-group">
                                <label class="control-label" for="name">Name of Manager</label>
                                <input name="name" id="name"
                                       type="text"
                                       class="form-control"
                                       value="<?= set_value('name'); ?>" required/>
                                <span class="text-danger help-block small with-errors">
                                           
                                        </span>
                            </div>

                             <div class="col-md-6 form-group">
                                <label class="control-label" for="phone">Telephone</label>
                                <input name="phone" id="phone"
                                       type="text"
                                       class="form-control"
                                       value="<?= set_value('phone'); ?>" required/>
                                <span class="text-danger help-block small with-errors">
                                           
                                        </span>
                            </div>
                            <div class="col-md-12 form-group user-form-group">
                                <div class="pull-right">
                                    <button type="reset" class="btn btn-danger btn-md">Cancel</button>
                                    <button type="submit" class="btn btn-primary btn-md">Submit </button>
                                </div>
                            </div>
                        </fieldset>
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
                        <?= form_close(); ?>
                    </div>
                </div>
        </div>
       
      </div>
    </div>
  </section>


  
  
  
  
  
<div class="modal fade" id="addDiseases" tabindex="-1" role="dialog" aria-hidden="true"></div>
<div class="modal fade" id="updateDiseases" tabindex="-1" role="dialog" aria-hidden="true"></div>
<div class="modal fade" id="deleteDiseases" tabindex="-1" role="dialog" aria-hidden="true"></div>
