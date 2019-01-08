<?php $html = ''; ?>
<div class="modal-dialog modal-lg">
    <div class="modal-content">
        <div class="modal-header modal-header-primary">
            <button type="button" id="update_users" class="close" data-dismiss="modal"
                    aria-hidden="true"><i
                        class="fa fa-times-circle" aria-hidden="true"></i></button>
            <h3><i class="fa fa-plus m-r-5"></i> Update Registered Sample</h3>
        </div>
        <div class="modal-body">
            <div class="row">   
                <div class="col-md-12">
                    <?php

                    $attributes = array("id" => "update_lab_form", "name" => "update_lab_form",
                        "role" => "form", "data-toggle" => "validator");
                    echo form_open_multipart("SampleTracking/update_sample", $attributes); ?>
                    <fieldset id="update_disease_container">
                        <?php
                        foreach ($all_reg_sample_details as $row_sample) {
                            $html .= '
                            <input type="hidden" name="record_id" id="record_id" value="' . $record_id . '"/>                         
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="barcode">Sample ID</label>
                                <input name="barcode" id="barcode"
                                       type="text"
                                       class="form-control"
                                       value="'.$row_sample->sample_id.'" readonly required/>
                                <span class="text-danger help-block small with-errors">
                                            
                                        </span>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="facility_code">Facility Code</label>';
                                $attrib_role = 'class = " form-control" name="facility_code" id = "facility_code" required';
                               $html .=form_dropdown('facility_code', $facility_code, $row_sample->facility_code_id, $attrib_role);
                              $html .='<span class="text-danger help-block small with-errors">
                              </span>            
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="disease_name">Suspected Disease</label>';
                                
                                $attrib_role = 'class = " form-control" name="disease_name" id = "disease_name" required';
                                $html .= form_dropdown('disease', $diseases, $row_sample->disease_id, $attrib_role);
                               
                                $html .='<span class="text-danger help-block small with-errors">
                                   
                                </span>
                            </div>
                               <div class="col-md-6 form-group">
                                <label class="control-label" for="sample_type">Specimen</label>';
                                
                                $attrib_role = 'class = " form-control" name="sample_type" id = "sample_type" required';
                                $html .=form_dropdown('sample_type', $sample_type, $row_sample->sample_type_id, $attrib_role);
                                
                                $html .='<span class="text-danger help-block small with-errors">
                                    
                                </span>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="destination">Destination</label>';
                                
                                $attrib_role = 'class = " form-control" name="destination" id = "destination" required';
                                $html .=form_dropdown('destination', $destination, $row_sample->destination_id, $attrib_role);
                               
                                $html .='<span class="text-danger help-block small with-errors">
                                    
                                </span>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="notes"> Clinical notes</label>
                                <input name="notes" id="notes"
                                " placeholder="Please fill in the comments"
                                       class="form-control"
                                       value="'.$row_sample->clinical_notes.'">
                                <span class="text-danger help-block small with-errors">
                                            '.form_error('notes').'
                                        </span>
                            </div>
                             
        
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="initialSampleDatee">Date of Sample Taking</label>
                          
                                <div class="input-group date">
                                <input name="initialSampleDate" id="datepicker"
                                       type="text"
                                       class="datepicker"
                                       value="'.$row_sample->initialSampleDate.'" required/>
                                <span class="text-danger help-block small with-errors">
                                        </span>
                               </div>
                            </div>

                            <div class="col-md-6 form-group">
                                <label class="control-label" for="finalDestinationDate"> Expected date to reach final destination</label>
                               <div class="input-group date">
                                <input name="finalDestinationDate" id="datepicker1"
                                       type="text"
                                       class="datepicker"
                                       value="'.$row_sample->finalDestinationDate.'" required/>
                                <span class="text-danger help-block small with-errors">
                                            '.form_error('finalDestinationDate').'
                                        </span>
                               </div>
                                
                            </div>

                            <div class="col-md-12 form-group user-form-group">
                                <div class="pull-right">
                                    <button type="reset" class="btn btn-danger btn-sm">Cancel</button>
                                    <button type="submit" class="btn btn-primary btn-sm">Update</button>
                                </div>
                            </div>
                        ';
                        }
                        echo $html;
                        ?>
                    </fieldset>
                    <?= form_close(); ?>
                </div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-danger pull-left" data-dismiss="modal">Close</button>
        </div>
    </div>
    <!-- /.modal-content -->
</div>