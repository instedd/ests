<?php $html = ''; ?>
<div class="modal-dialog modal-lg">
    <div class="modal-content">
        <div class="modal-header modal-header-primary">
            <button type="button" id="update_users" class="close" data-dismiss="modal"
                    aria-hidden="true"><i
                        class="fa fa-times-circle" aria-hidden="true"></i></button>
            <h3><i class="fa fa-plus m-r-5"></i> Update User</h3>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-md-12">
                    <?php

                    $attributes = array("id" => "update_users_form", "name" => "update_users_form",
                        "role" => "form", "data-toggle" => "validator");
                    echo form_open_multipart("UserAdministration/update_users", $attributes); ?>
                    <fieldset id="update_users_container">
                        <?php
                        $CI =& get_instance();
                        $CI->load->model('Reverselookups_model');
                        $i = 1;
                        $app = 1;
                        foreach ($all_user_details as $row_user) {
                            $role_name = $CI->Reverselookups_model->get_role_name($row_user->role_id);
                            $html .= '
                            <input type="hidden" name="record_id" id="record_id" value="' . $record_id . '"/>                           

                        <div class="form-group col-sm-6">
                            <label for="full_name">Full Name</label>
                            <input class="form-control" type="text"
                                   name="full_name"
                                   id="full_name"
                                   placeholder="Full Name" value="' . $row_user->fullNames . '"
                                   required/>
                            <div class="help-block with-errors"></div>
                        </div>
                       
                         <div class="form-group col-sm-6">
                            <label class=""
                                   for="user_role">Role:</label>';

                            $attrib_user_roles = 'class = " form-control"
                                            name="user_role"
                                            id = "user_role" required';
                            $html .= form_dropdown('user_role', $roles_drop_down,
                                $row_user->role_id,
                                $attrib_user_roles);

                            $html .= '<div class="help-block with-errors"></div>
                        </div>

                        <div class="form-group col-sm-6">
                            <label for="email">Email</label>
                            <input class="form-control" type="email"
                                   name="email"
                                   id="email"
                                   placeholder="Email" value="' . $row_user->email . '"
                                   required/>
                            <div class="help-block with-errors"></div>
                        </div>

                        

                        <div class="form-group col-sm-6">
                            <label for="telephone">Telephone</label>
                            <input class="form-control" type="text"
                                   name="telephone"
                                   id="telephone"
                                   pattern=\'[\+]\d{3}\d{9}\'
                                   placeholder="Telephone" value="' . $row_user->tel_mobile . '"
                                   required/>
                            <div class="help-block with-errors"></div>
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