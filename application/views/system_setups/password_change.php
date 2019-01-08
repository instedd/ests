<?php $admin_privilege = (cud_role_check((int)($this->session->userdata['user_group_id']))); ?>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
 <?php include('sidebar.php');?>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <section class="content-header">
      <h1>
        Change Password
      </h1>
      <ol class="breadcrumb">
        <li><a href="<?=base_url();?>/Dashboard/index"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active"><a href="<?=base_url();?>/Dashboard/index">Dashboard</a></li>
      </ol>
    </section> 
    <!-- Content Header (Page header) -->  
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
                            "id" => "",
                            "name" => "",
                            "role" => "form", "data-toggle" => "validator",
                            "enctype"=>"multipart/form-data"
                        );
                        echo form_open("Systemsetups/passwordChange", $attributes); ?>
                        <fieldset>
                              <div class="col-md-6 form-group">
                                <label class="control-label" for="old_password"> Enter Old Password</label>
                                <input name="old_password" id="old_password"
                                       type="password" placeholder=""
                                       class="form-control"
                                       value="<?= set_value('old_password'); ?>" required/>
                                <span class="text-danger help-block small with-errors">
                                           
                                        </span>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="new_password"> Enter New Passord</label>
                                <input name="new_password" id="new_password"
                                       type="password" placeholder=""
                                       class="form-control"
                                       value="<?= set_value('new_password'); ?>" required/>
                                <span class="text-danger help-block small with-errors">
                                           
                                        </span>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="conf_password"> Confirm New Passord</label>
                                <input name="conf_password" id="conf_password"
                                       type="password" placeholder=""
                                       class="form-control"
                                       value="<?= set_value('conf_password'); ?>" required/>
                                <span class="text-danger help-block small with-errors">
                                           
                                        </span>
                            </div>

                            
                                  <div class="col-md-12 form-group user-form-group">
                                <div class="pull-right">
                                    <button type="reset" class="btn btn-danger btn-md">Cancel</button>
                                    <button type="submit" class="btn btn-primary btn-md">Submit </button>
                                </div>
                            </div>

                       <div class="col-md-9 pull-right"><font color="#AA0000" face="Arial">
             <?php if(validation_errors()){?>
            <div class="alert alert-danger text-center">
              <?php echo validation_errors(); ?>
            </div>
            <?php
          }
        if($this->session->flashdata('message')){
          
             echo $this->session->flashdata('message');
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
