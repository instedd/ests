<div class="modal-dialog modal-dialog modal-full">
    <div class="modal-content">
        <div class="modal-header modal-header-primary">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i
                        class="fa fa-times-circle" aria-hidden="true"></i></button>
            <h3><i class="fa fa-plus m-r-5"></i> Add New Role's Permissions</h3>
        </div>
        <div class="modal-body">
           
                <div class="col-md-12">
        <?php
        $attributes = array("id" => "permissions_form", "name" => "permissions_form");
        echo form_open("SystemSetups/update_permissions", $attributes); ?>
       <div class="table-responsive">
        <table class="table table-bordered table-striped table-hover">
        <thead>
        <tr class="info">
        <th colspan="2">Current Permissions For Role: <?=$this->Setups_model->get_role_name($record_id)?></th>
        </tr>
        <tr class="info">
        <th>#</th>
        <th>Menu Item & Status</th>
        </tr>
        </thead>
        <tbody>
        <?php
        $i = 1;
        foreach ($data_all_menu_categories as $category) {
        ?>
        <tr>
        <input type="hidden" name="role_type"  value="<?=$record_id?>" />
        <td colspan="2"><b>Menu Category:</b><?=$category->name?></td>
        <tr/>
        <?php
            $j = 1;
            foreach ($data_all_menu_items as $item) {
                if (($category->id) == ($item->category_id)) {
                    $item_name = $item->name;
                    $item_id = $item->tbl_menu_itemsId;

                    $checked_permissions_item_id = $this->Lookups_model->get_permissions($category->id, $item_id, $record_id);
                    if (!empty($checked_permissions_item_id)) {
                        foreach ($checked_permissions_item_id as $row_perm) {
                            $checked_permissions_item_id = $row_perm->menu_item_id;

                        }
                    }
                    $checked = ($checked_permissions_item_id == $item_id) ? 'checked="checked"' : '';
                   
                  ?>
                    <tr>
        <td><?=$j?></td>
        <td>
        <label class="checkbox-inline">
        <input type="checkbox" name="menu_item[]"  value="<?=$item_id?>" <?=$checked?> /> <?=$item_name?></label>
        
        </td>

        </tr><?php
                    $j++;
                }
            }

            $i++;
        }?>
        </tbody>
        </table>
        </div>
        <div class="col-md-12 form-group user-form-group">
        <div class="pull-right">
        <button type="reset" class="btn btn-danger btn-sm">Reset</button>
        <button type="submit" class="btn btn-add btn-sm">Update</button>
        </div>
        </div>
        <?=form_close()?>
         </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-danger pull-left" data-dismiss="modal">Close</button>
        </div>
    </div>
    <!-- /.modal-content -->
</div>