<?php $admin_privilege = (cud_role_check((int)($this->session->userdata['user_group_id']))); ?>
<!-- Wizard with Validation -->
<div class="row">
    <div class="col-sm-12">
        <div class="card-box">
            <?php
            if ($this->session->flashdata('user_add_success_msg') != ''):
                echo $this->session->flashdata('user_add_success_msg');
            endif;
            ?>

            <div class="btn-group">
                <div class="btn btn-default waves-effect waves-light btn-sm">
                    <a href="#" class="btn btn-inverse" data-toggle="modal" data-target="#add_user"><i
                                class="fa fa-plus"></i> Add new user</a>
                </div>

                <!--<button class="btn btn-pink waves-effect waves-light m-b-5"> <span>Book Flight</span> <i class="fa fa-plane m-l-5"></i> </button>-->
            </div>
            <div class="table-responsive">
                <table class="table  table-colored table-custom">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Full Name</th>
                        <th>User Name</th>
                        <th>User Role</th>
                        <th>Email</th>
                        <th>Last Update</th>

                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <?php
                    $app = 1;
                    foreach ($data_system_users as $row_user) {
                        ?>
                        <tr>
                            <td><?= $app; ?>.</td>
                            <td><?= $row_user->fullNames; ?></td>
                            <td><?= $row_user->username; ?></td>
                            <td><?= $row_user->role_name; ?></td>
                            <td><?= $row_user->email; ?></td>
                            <td><?= $row_user->date_entry; ?></td>
                            <td>
                                <button type="button" data-record_id="<?= $row_user->tbl_system_user_id; ?>"
                                        class="btn btn-add btn-xs edit-user" data-toggle="modal"
                                        data-target="#update_user"><i class="fa fa-pencil"></i></button>
                                <button type="button" data-record_id="<?= $row_user->tbl_system_user_id; ?>"
                                        class="btn btn-danger btn-xs delete-user" data-toggle="modal"
                                        data-target="#delete_user"><i class="fa fa-trash-o"></i></button>
                            </td>
                        </tr>
                        <?php $app++;
                    } ?>
                    </tbody>
                </table>
                <div class="col-sm-12 text-left">
                    <?= $pagination; ?>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End row -->


<div class="modal fade" id="add_user" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
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
                        echo form_open("FmSetups/add_user", $attributes); ?>
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
                                <label class="control-label" for="user_password">Password</label>
                                <input name="user_password" id="user_password"
                                       type="password" placeholder="User Password"
                                       class="form-control"
                                       value="<?= set_value('user_password'); ?>" required/>
                                <span class="text-danger help-block small with-errors">
                                            <?= form_error('user_password'); ?>
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
                                <label class="control-label" for="user_country">Country</label>
                                <?php
                                $attrib_user_country = 'class = " form-control" name="user_country" id = "user_country" required';
                                echo form_dropdown('user_country', $countries, set_value('user_country'), $attrib_user_country);
                                ?>
                                <span
                                        class="text-danger help-block small with-errors"><?= form_error('user_country'); ?>
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

                            <div class="col-md-6 form-group">
                                <label class="control-label" for="role_status">Activate User</label>
                                <?php
                                $attrib_status = 'class = " form-control"
                                            name="role_status" id = "role_status"
                                            required';
                                echo form_dropdown('role_status',
                                    $boolean_response, set_value('role_status'),
                                    $attrib_status);
                                ?>
                                <span class="text-danger help-block small with-errors">
                                            <?= form_error('role_status'); ?>
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
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<div class="modal fade" id="update_user" tabindex="-1" role="dialog" aria-hidden="true"></div>
<div class="modal fade" id="delete_user" tabindex="-1" role="dialog" aria-hidden="true"></div>
