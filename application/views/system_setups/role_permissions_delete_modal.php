<?php $html = '';
$html .= '<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header modal-header-primary">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><i
                        class="fa fa-times-circle" aria-hidden="true"></i></button>
            <h3>You are about to delete a Menu Category</h3>
        </div>
        <div class="modal-body">
            <div class="row">
                <div class="col-md-12">';
$attributes = array("id" => "delete_menu_category_form", "name" => "delete_menu_category_form");
echo form_open("SystemSetups/delete_menu_categories", $attributes);
$html .= '
                    <fieldset>
                        <div class="col-md-12 form-group user-form-group">
                            <input type="hidden" name="menu_cat_id" id="menu_cat_id" value="' . $record_id . '"/>
                            <label class="control-label">Do you really want to Delete <b style="color:red;">' . $menu_cat_name . '</b> ?</label>
                            <p><caption><i style="color:red;" class="fa fa-exclamation-triangle fa-3x"></i><b>This Action will trigger the following events:</b></caption>
                            <ul>
                                <li>The menu category will be lost forever.</li></ul>
                            </p>

                            <div class="pull-right">
                                <a href="' . site_url('' . $current_class . '/menu_categories') . '" class="btn btn-danger btn-sm">NO</a>
                                <button type="submit" name="submit" id="submit" value="yes" class="btn btn-success btn-sm">YES</button>
                            </div>
                        </div>
                    </fieldset>';
form_close();
$html .= '</div>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-danger pull-left" data-dismiss="modal">Close</button>
        </div>
    </div>
    <!-- /.modal-content -->
</div>';
echo $html;

