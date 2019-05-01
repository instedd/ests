<div class="modal-dialog modal-dialog ">
    <div class="modal-content">
        <div class="modal-header modal-header-primary">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i
                        class="fa fa-times-circle" aria-hidden="true"></i></button>
            <h3><i class="fa fa-plus m-r-5"></i> Add New Menu Category</h3>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-md-12">
                    <?php
                    $attributes = array(
                        "class" => "",
                        "id" => "add_menu_categories_form",
                        "name" => "add_menu_categories_form",
                        "role" => "form", "data-toggle" => "validator"
                    );
                    echo form_open("SystemSetups/add_menu_category", $attributes); ?>
                    <fieldset>
                        <div class="form-group col-sm-6">
                            <label for="menu_name">Menu Name</label>
                            <input class="form-control" type="text"
                                   name="menu_name"
                                   id="menu_name"
                                   placeholder="Menu Name" value="<?=set_value('menu_name');?>"
                                   required/>
                            <div class="help-block with-errors"></div>
                        </div>

                        <div class="form-group col-sm-6">
                            <label for="aw_icon">Awesome Icon</label>
                            <input class="form-control" type="text"
                                   name="aw_icon"
                                   id="aw_icon"
                                   placeholder="Awesome Icon" value="<?=set_value('aw_icon');?>"
                                   required/>
                            <div class="help-block with-errors"></div>
                        </div>

                        <div class="form-group col-sm-6">
                            <label for="sys_controller">System Controller</label>
                            <input class="form-control" type="text"
                                   name="sys_controller"
                                   id="sys_controller"
                                   placeholder="System Controller" value="<?=set_value('sys_controller');?>"
                                   required/>
                            <div class="help-block with-errors"></div>
                        </div>
                        <div class="form-group col-sm-6">
                            <label for="cat_rank">Item Rank</label>
                            <input class="form-control" type="number"
                                   name="cat_rank"
                                   id="cat_rank"
                                   placeholder="Item Rank" value="<?=set_value('cat_rank');?>"
                                   required/>
                            <div class="help-block with-errors"></div>
                        </div>

                        <div class="form-group col-sm-6">
                            <label for="menu_display">Display</label>
                            <?php
                            $attrib_menu_display = 'class = " form-control"
                            name="menu_display" id = "menu_display" required';
                            echo form_dropdown('menu_display', $display_drop_down, set_value('menu_display'), $attrib_menu_display);
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