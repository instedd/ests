<div class="modal-dialog modal-dialog modal-full">
    <div class="modal-content">
        <div class="modal-header modal-header-primary">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i
                        class="fa fa-times-circle" aria-hidden="true"></i></button>
            <h3><i class="fa <?= $awesome_icon; ?> m-r-5"></i> Add New Role's Permissions</h3>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-md-12">
                    <?php
                    $attributes = array("id" => "add_role_perm_form", "name" => "add_role_perm_form");
                    echo form_open("SystemSetups/add_permissions", $attributes); ?>
                    <fieldset id="add_permissions_container">
                        <div class="form-group col-sm-6">
                            <label for="role_id">Role Name</label>
                            <?php
                            $attrib_role_id = 'class = " form-control"
                            name="role_id" id = "role_id" required';
                            echo form_dropdown('role_id', $un_permitted_drop_down, set_value('role_id'), $attrib_role_id);
                            ?>
                            <div class="help-block with-errors"></div>
                        </div>
                        <div class="col-md-12">


                            <?php
                            if (empty($data_all_modules)) {
                                ?>
                                <div class="col-sm-offset-1 col-sm-10 alert alert-danger text-center fadeIn" style="color:#0C2B2D;">
                                    <h4>No Items Found!!</h4>
                                </div>
                                <?php
                            } else {
                                foreach ($data_all_modules as $row_module) {
                                    $module_name = $row_module->name;
                                    $module_id = $row_module->id;

                                    ?>
                                    <div class="search-item">
                                        <h4><i class="fa fa-cog"></i> <?= $module_name; ?></h4>
                                        <?php
                                        if (empty($data_all_menu_items_by_module)) {
                                            ?>
                                            <div class="col-sm-offset-1 col-sm-10 alert alert-danger text-center fadeIn"
                                                 style="color:#0C2B2D;">
                                                <h4>No Menu Items Found!!</h4>
                                            </div>
                                            <?php
                                        } else {
                                            ?>
                                            <p class="m-b-0">
                                                <input type="hidden" name="module_id" id="module_id" value="<?= $module_id; ?>"/>
                                                <?php
                                                foreach ($data_all_menu_items_by_module as $row_item) {
                                                    if ((($module_id) == ($row_item->category_id))) {
                                                        $menu_item_name = $row_item->name;
                                                        $menu_cat_id = $row_item->category_id;
                                                        $menu_item_id = $row_item->tbl_menu_itemsId;
                                                        ?>
                                                        <label class="checkbox-inline">

                                                            <input type="checkbox" name="menu_item_id[]"
                                                                   value="<?= $menu_item_id; ?>"/><?= $menu_item_name; ?>
                                                        </label>

                                                        <?php
                                                    }
                                                } ?>
                                            </p>
                                            <?php
                                        }
                                        ?>

                                    </div>
                                    <div class="clearfix"></div>
                                    <?php
                                }
                            }
                            ?>
                        </div>
                        <div class="col-md-12 form-group user-form-group">
                            <div class="pull-right">
                                <button type="button" data-dismiss="modal" class="btn btn-danger btn-md">Cancel</button>
                                <button type="submit" class="btn btn-add btn-md">Add</button>
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