<?php $html = ''; ?>
<div class="modal-dialog modal-lg">
    <div class="modal-content">
        <div class="modal-header modal-header-primary">
            <button type="button" id="invoice_update" class="close" data-dismiss="modal"
                    aria-hidden="true"><i
                        class="fa fa-times-circle" aria-hidden="true"></i></button>
            <h3><i class="fa fa-plus m-r-5"></i> Update Menu Category</h3>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-md-12">
                    <?php
                    $attributes = array("id" => "update_menu_categories_form", "name" => "update_menu_categories_form",
                        "role" => "form", "data-toggle" => "validator");
                    echo form_open_multipart("SystemSetups/update_menu_categories", $attributes); ?>
                    <fieldset id="update_member_container">
                        <?php
                        foreach ($data_all_modules as $row_module) {
                            $html .= '
                            <input type="hidden" name="menu_cat_id" id="menu_cat_id" value="' . $record_id . '"/>
                            
                        
                        <div class="form-group col-sm-6">
                            <label for="menu_name">Menu Name</label>
                            <input class="form-control" type="text"
                                   name="menu_name"
                                   id="menu_name"
                                   placeholder="Menu Name" value="' . $row_module->name . '"
                                   required/>
                            <div class="help-block with-errors"></div>
                        </div>

                        <div class="form-group col-sm-6">
                            <label for="aw_icon">Awesome Icon</label>
                            <input class="form-control" type="text"
                                   name="aw_icon"
                                   id="aw_icon"
                                   placeholder="Awesome Icon" value="' . $row_module->awesome_icon . '"
                                   required/>
                            <div class="help-block with-errors"></div>
                        </div>

                        <div class="form-group col-sm-6">
                            <label for="sys_controller">System Controller</label>
                            <input class="form-control" type="text"
                                   name="sys_controller"
                                   id="sys_controller"
                                   placeholder="System Controller" value="' . $row_module->controller . '"
                                   required/>
                            <div class="help-block with-errors"></div>
                        </div>
                        <div class="form-group col-sm-6">
                            <label for="cat_rank">Item Rank</label>
                            <input class="form-control" type="number"
                                   name="cat_rank"
                                   id="cat_rank"
                                   placeholder="Item Rank" value="' . $row_module->rank . '"
                                   required/>
                            <div class="help-block with-errors"></div>
                        </div>
                        
                        <div class="form-group col-sm-6">
                        <label for="menu_display">Display</label>';
                                            $menu_display = (($row_module->display) !== '') ? $row_module->display : set_value('menu_display');
                                            $attrib_menu_display = 'class = " form-control"
                        name="menu_display" id = "menu_display" required';
                                            $html .= form_dropdown('menu_display', $this->Lookups_model->get_boolean_response(),
                                                $menu_display, $attrib_menu_display);

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