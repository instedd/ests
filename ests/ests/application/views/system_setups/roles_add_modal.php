<div class="modal-dialog modal-dialog ">
    <div class="modal-content">
        <div class="modal-header modal-header-primary">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i
                        class="fa fa-times-circle" aria-hidden="true"></i></button>
            <h3><i class="fa fa-plus m-r-5"></i> Add New Role</h3>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-md-12">
                    <?php
                    $attributes = array(
                        "class" => "",
                        "id" => "add_role_form",
                        "name" => "add_role_form",
                        "role" => "form", "data-toggle" => "validator"
                    );
                    echo form_open("SystemSetups/add_roles", $attributes); ?>
                    <fieldset>
                        <div class="form-group col-sm-6">
                            <label for="role_name">Role Name</label>
                            <input class="form-control" type="text"
                                   name="role_name"
                                   id="role_name"
                                   placeholder="Role Name" value="<?=set_value('role_name');?>"
                                   required/>
                            <div class="help-block with-errors"></div>
                        </div>

                        <div class="form-group col-sm-6">
                            <label for="role_display">Display</label>
                            <?php
                            $attrib_role_display = 'class = " form-control"
                            name="role_display" id = "role_display" required';
                            echo form_dropdown('role_display', $display_drop_down, set_value('role_display'), $attrib_role_display);
                           ?>
                        <div class="help-block with-errors"></div>
                        </div>
                        <div class="col-md-12 form-group user-form-group">
                            <div class="pull-right">
                                <button type="reset" class="btn btn-danger btn-sm">Cancel</button>
                                <button type="submit" class="btn btn-add btn-sm">Add</button>
                            </div>
                        </div>
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