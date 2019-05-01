<?php $html = ''; ?>
<div class="modal-dialog modal-lg">
    <div class="modal-content">
        <div class="modal-header modal-header-primary">
            <button type="button" id="invoice_update" class="close" data-dismiss="modal"
                    aria-hidden="true"><i
                        class="fa fa-times-circle" aria-hidden="true"></i></button>
            <h3><i class="fa fa-plus m-r-5"></i> Update Role</h3>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-md-12">
                    <?php
                    $attributes = array("id" => "update_role_form", "name" => "update_role_form",
                        "role" => "form", "data-toggle" => "validator");
                    echo form_open_multipart("SystemSetups/update_roles", $attributes); ?>
                    <fieldset id="update_member_container">
                        <?php
                        foreach ($all_roles as $row_role) {
                            $html .= '
                            <input type="hidden" name="role_id" id="role_id" value="' . $record_id . '"/>
                            
                        
                        <div class="form-group col-sm-6">
                            <label for="role_name">Role Name</label>
                            <input class="form-control" type="text"
                                   name="role_name"
                                   id="role_name"
                                   placeholder="Role Name" value="' . $row_role->name . '"
                                   required/>
                            <div class="help-block with-errors"></div>
                        </div>
                        
                        <div class="form-group col-sm-6">
                        <label for="role_display">Display</label>';
                                            $role_display = (($row_role->display) !== '') ? $row_role->display : set_value('role_display');
                                            $attrib_role_display = 'class = " form-control"
                        name="role_display" id = "role_display" required';
                                            $html .= form_dropdown('role_display', $this->Lookups_model->get_boolean_response(),
                                                $role_display, $attrib_role_display);

                                            $html .= '<div class="help-block with-errors"></div>
                        </div>
                        
                        <div class="col-md-12 form-group user-form-group">
                            <div class="pull-right">
                                <button type="reset" class="btn btn-danger btn-sm">Cancel</button>
                                <button type="submit" class="btn btn-add btn-sm">Update</button>
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