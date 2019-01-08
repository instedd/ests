

<div class="modal-dialog modal-dialog ">
        <div class="modal-content">
            <div class="modal-header modal-header-primary">
                <button type="button" class="btn btn-rounded close" data-dismiss="modal" aria-hidden="true"><i
                        class="fa fa-times-circle" aria-hidden="true"></i></button>
                <h3><i class="fa fa-plus m-r-5"></i> Add New User</h3>
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
                        echo form_open("UserAdministration/add_users", $attributes); ?>
                        <fieldset>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="role_name">First Name</label>
                                <input name="first_name" id="first_name"
                                       type="text" placeholder="First Name"
                                       class="form-control"
                                       value="<?= set_value('first_name'); ?>" required/>
                                <span class="text-danger help-block small with-errors">
                                            <?= form_error('first_name'); ?>
                                        </span>
                            </div>

                            <div class="col-md-6 form-group">
                                <label class="control-label" for="last_name">Last Name</label>
                                <input name="last_name" id="last_name"
                                       type="text" placeholder="Last Name"
                                       class="form-control"
                                       value="<?= set_value('last_name'); ?>" required/>
                                <span class="text-danger help-block small with-errors">
                                            <?= form_error('last_name'); ?>
                                        </span>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="district">Location</label>
                                <?php
                                $attrib_role = 'class = " form-control" name="district" id = "district" required';
                                echo form_dropdown('district', $district, set_value('district'), $attrib_role);
                                ?>
                                <span class="text-danger help-block small with-errors">
                                    <?= form_error('district'); ?>
                                </span>
                              </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="role_name">Role</label>
                                <?php
                                $attrib_role = 'class = " form-control" name="role_name" id = "role_name" required';
                                echo form_dropdown('role_name', $user_roles, set_value('role_name'), $attrib_role);
                                ?>
                                <span class="text-danger help-block small with-errors">
                                    <?= form_error('role_name'); ?>
                                </span>
                            </div>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="user_email">Email</label>
                                <input name="user_email" id="user_email"
                                       type="email" placeholder="User Email"
                                       class="form-control"
                                       value="<?= set_value('user_email'); ?>" required/>
                                <span class="text-danger help-block small with-errors">
                                            <?= form_error('user_email'); ?>
                                        </span>
                            </div>

                            <div class="col-md-6 form-group">
                                <label class="control-label" for="user_tel">Telephone(Format:+256779826311):</label>
                                <input type='tel' pattern='[\+]\d{3}\d{9}' class="form-control"
                                       name="user_tel" id="user_tel"
                                       maxlength="13"
                                       placeholder="User Telephone Number"
                                       value="<?= set_value('user_tel'); ?>" required>
                                <span class="text-danger help-block small with-errors">
                                            <?= form_error('user_tel'); ?>
                                        </span>
                            </div>

                            <div class="col-md-12 form-group user-form-group">
                                <div class="pull-right">
                                    <button type="reset" class="btn btn-danger btn-sm">Cancel</button>
                                    <button type="submit" class="btn btn-primary btn-sm">Add User</button>
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
