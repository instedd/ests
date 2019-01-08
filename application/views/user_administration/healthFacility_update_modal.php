<?php $html = ''; ?>
<div class="modal-dialog modal-lg">
    <div class="modal-content">
        <div class="modal-header modal-header-primary">
            <button type="button" id="update_users" class="close" data-dismiss="modal"
                    aria-hidden="true"><i
                        class="fa fa-times-circle" aria-hidden="true"></i></button>
            <h3><i class="fa fa-plus m-r-5"></i> Update Health Facility</h3>
        </div>
        <div class="modal-body">
            <div class="row">   
                <div class="col-md-12">
                    <?php

                    $attributes = array("id" => "update_healthFacility_form", "name" => "update_healthFacility_form",
                        "role" => "form", "data-toggle" => "validator");
                    echo form_open_multipart("UserAdministration/update_healthFacility", $attributes); ?>
                    <fieldset id="update_healthFacility_container">
                        <?php
                        $i = 1;
                        $app = 1;
                        foreach ($all_healthFacility_details as $row_healthFacility_details) {
                            $html .= '
                            <input type="hidden" name="record_id" id="record_id" value="' . $record_id . '"/>                         
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="role_name">Reference Lab name</label>
                                <input name="name" id="name"
                                       type="text"
                                       class="form-control"
                                       value="'.$row_healthFacility_details->name.'" required/>
                                <span class="text-danger help-block small with-errors">
                                            
                                        </span>
                            </div>
                            <div class="col-md-12 form-group user-form-group">
                                <div class="pull-right">
                                    <button type="reset" class="btn btn-danger btn-sm">Cancel</button>
                                    <button type="submit" class="btn btn-primary btn-sm">Update Health Facility</button>
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