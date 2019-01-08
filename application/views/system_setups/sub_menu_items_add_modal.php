<div class="modal-dialog modal-dialog ">
    <div class="modal-content">
        <div class="modal-header modal-header-primary">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i
                        class="fa fa-times-circle" aria-hidden="true"></i></button>
            <h3><i class="fa fa-plus m-r-5"></i> Add New Sub-Menu Item</h3>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-md-12">
                    <?php
                    $attributes = array(
                        "class" => "",
                        "id" => "add_sub_menu_items_form",
                        "name" => "add_sub_menu_items_form",
                        "role" => "form", "data-toggle" => "validator"
                    );
                    echo form_open("SystemSetups/add_sub_menu_item", $attributes); ?>
                    <fieldset>
                        <div class="form-group col-sm-6">
                            <label for="menu_cat">Menu Category</label>
                            <?php
                            $attrib_menu_cat = 'class = " form-control"
                            name="menu_cat" id = "menu_cat" required';
                            echo form_dropdown('menu_cat', $menu_cat_drop_down, set_value('menu_cat'), $attrib_menu_cat);
                            ?>
                            <div class="help-block with-errors"></div>
                        </div>

                        <div class="form-group col-sm-6">
                            <label for="item_name">Sub Menu Item Name</label>
                            <input class="form-control" type="text"
                                   name="item_name"
                                   id="item_name"
                                   placeholder="Sub Menu Item Name" value="<?=set_value('item_name');?>"
                                   required/>
                            <div class="help-block with-errors"></div>
                        </div>

                        <div class="form-group col-sm-6">
                            <label for="sub_controller">Page</label>
                            <input class="form-control" type="text"
                                   name="sub_controller"
                                   id="sub_controller"
                                   placeholder="Page" value="<?=set_value('sub_controller');?>"
                                   required/>
                            <div class="help-block with-errors"></div>
                        </div>
                        <div class="form-group col-sm-6">
                            <label for="cat_rank">Item Rank</label>
                            <input class="form-control" type="number" step="any"
                                   name="cat_rank"
                                   id="cat_rank"
                                   placeholder="Item Rank" value="<?=set_value('cat_rank');?>"
                                   required/>
                            <div class="help-block with-errors"></div>
                        </div>

                        <div class="form-group col-sm-6">
                            <label for="item_display">Display</label>
                            <?php
                            $attrib_item_display = 'class = " form-control"
                            name="item_display" id = "item_display" required';
                            echo form_dropdown('item_display', $item_display, set_value('item_display'), $attrib_item_display);
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