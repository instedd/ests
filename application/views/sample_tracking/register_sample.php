<?php $admin_privilege = (cud_role_check((int)($this->session->userdata['user_group_id']))); ?>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
 <?php include('sidebar.php');?>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Sample Registration
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
                        echo form_open("SampleTracking/registerSample", $attributes); ?>
                        <fieldset>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="barcode"> Sample Id</label>
                                <input name="barcode" id="barcode"
                                       type="text" placeholder="Please scan barcode on sample"
                                       class="form-control"
                                       value="<?= set_value('barcode'); ?>" required/>
                                <span class="text-danger help-block small with-errors">
                                           
                                        </span>
                            </div>

                            <div class="col-md-6 form-group">
                                <label class="control-label" for="facility_code">Health Facility</label>
                                <?php
                                $attrib_role = 'class = " form-control" name="facility_code" id = "facility_code" required';
                                echo form_dropdown('facility_code', $facility_code, set_value('facility_code'), $attrib_role);
                                ?>
                                <span class="text-danger help-block small with-errors">
                                    <?= form_error('facility_code'); ?>
                                </span>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="disease_name">Suspected Disease</label>
                                <?php
                                $attrib_role = 'class = " form-control" name="disease_name" id = "disease_name" required';
                                echo form_dropdown('disease', $diseases, set_value('disease'), $attrib_role);
                                ?>
                                <span class="text-danger help-block small with-errors">
                                    <?= form_error('disease'); ?>
                                </span>
                            </div>
                               <div class="col-md-6 form-group">
                                <label class="control-label" for="sample_type">Specimen</label>
                                <?php
                                $attrib_role = 'class = " form-control" name="sample_type" id = "sample_type" required';
                                echo form_dropdown('sample_type', $sample_type, set_value('sample_type'), $attrib_role);
                                ?>
                                <span class="text-danger help-block small with-errors">
                                    <?= form_error('sample_type'); ?>
                                </span>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="destination">Destination</label>
                                <?php
                                $attrib_role = 'class = " form-control" name="destination" id = "destination" required';
                                echo form_dropdown('destination', $destination, set_value('destination'), $attrib_role);
                                ?>
                                <span class="text-danger help-block small with-errors">
                                    <?= form_error('destination'); ?>
                                </span>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="destination">Transporter</label>
                                <?php
                                $attrib_role = 'class = " form-control" name="transporter" id = "transporter" required';
                                echo form_dropdown('transporter', $transporter_name, set_value('transporter'), $attrib_role);
                                ?>
                                <span class="text-danger help-block small with-errors">
                                    <?= form_error('transporter'); ?>
                                </span>
                            </div>
                            <!--<div class="col-md-6 form-group">
                                <label class="control-label" for="initialSampleDatee">Date of Sample Taking</label>
                          
                                <div class="input-group date">
                                <input name="initialSampleDate" id="datepicker"
                                       type="text"
                                       class="datepicker"
                                       value="<?= set_value('initialSampleDate'); ?>" required/>
                                <span class="text-danger help-block small with-errors">
                                            <?= form_error('initialSampleDate'); ?>
                                        </span>
                               </div>
                            </div> -->

                            <div class="col-md-6 form-group">
                                <label class="control-label" for="finalDestinationDate"> Expected date to reach final destination</label>
                               <div class="input-group date">
                                <input name="finalDestinationDate" id="datepicker1"
                                       type="text"
                                       class="datepicker"
                                       value="<?= set_value('finalDestinationDate'); ?>" required/>
                                <span class="text-danger help-block small with-errors">
                                            <?= form_error('finalDestinationDate'); ?>
                                        </span>
                               </div>
                                
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="notes"> Clinical notes</label>
                                <textarea name="notes" id="notes"
                                       type="textarea" placeholder="Please fill in the comments"
                                       class="form-control"
                                       value="<?= set_value('notes'); ?>"></textarea>
                                <span class="text-danger help-block small with-errors">
                                            <?= form_error('notes'); ?>
                                        </span>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="uploadFile"> Upload a photo of the form </label>
                                 <input type="file" name="uploadFile" id="uploadFile"
                                  class="form-control" value=""/>
                                <span class="text-danger help-block small with-errors">
                                            <?= form_error('uploadFile'); ?>
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
