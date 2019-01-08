<div class="modal-dialog modal-dialog ">
        <div class="modal-content">
            <div class="modal-header modal-header-primary">
                <button type="button" class="btn btn-rounded close" data-dismiss="modal" aria-hidden="true"><i
                        class="fa fa-times-circle" aria-hidden="true"></i></button>
                <h3><i class="fa fa-plus m-r-5"></i> Add Suspected Disease</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">
                        <?php
                        $attributes = array(
                            "class" => "",
                            "id" => "sys_add_role",
                            "name" => "sys_add_role_form",
                            "role" => "form", "data-toggle" => "validator"
                        );
                        echo form_open("UserAdministration/add_diseases", $attributes); ?>
                        <fieldset>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="role_name">Disease Name</label>
                                <input name="name" id="name"
                                       type="text" placeholder="Enter disease name"
                                       class="form-control"
                                       value="<?= set_value('name'); ?>" required/>
                                <span class="text-danger help-block small with-errors">
                                            <?= form_error('name'); ?>
                                        </span>
                            </div>
                            <div class="col-md-12 form-group user-form-group">
                                <div class="pull-right">
                                    <button type="reset" class="btn btn-danger btn-sm">Cancel</button>
                                    <button type="submit" class="btn btn-primary btn-sm">Add Disease</button>
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
    </div>
        <!-- /.modal-content -->
