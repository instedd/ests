<?php $admin_privilege = (cud_role_check((int)($this->session->userdata['user_group_id']))); ?>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
 <?php include('sidebar.php');?>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->  
  <section class="content container-fluid">
  <div class="row">
    <div class="col-md-12">
      <div class="box box-info pull-right">
        <div class="box-header with-border">
          <div class="btn-group">
             
            <button class="btn btn-primary waves-effect waves-light add-sub-menu-item" id="primary-alert" data-toggle="modal"
                    data-target="#addSubMenuItem">Add new Sub-Menu Item
            </button>

                <!--<button class="btn btn-pink waves-effect waves-light m-b-5"> <span>Book Flight</span> <i class="fa fa-plane m-l-5"></i> </button>-->
            </div>
        </div>
          <?php
            if ($this->session->flashdata('sub_menu_items_msg') != ''):
                echo $this->session->flashdata('sub_menu_items_msg');
            endif;
            ?>
        <div class="box-body  no-padding">
          <div class="col-xs-12 table-responsive">
                    <?php
                $attributes = array("id" => "sub_menu_items_form", "name" => "sub_menu_items_form");
                echo form_open("SystemSetups/sub_menu_items", $attributes); ?>
                <table  class="table datatable-buttons table-striped table-bordered">
                    <thead>
                    <tr class="info">
                        <th>#</th>
                        <th>Menu Name</th>
                        <th>Sub Menu Item</th>
                        <th>System Controller</th>
                        <th>Menu Rank</th>
                        <th>Display</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <?php
                    $i = 1;
                    foreach ($data_get_all_sub_menu_items as $row_menu_item) {
                        $display=(($row_menu_item->display)==1)?'Yes':'No';
                        ?>
                        <tr>
                            <td><?= $i; ?></td>
                            <td><?= $row_menu_item->menu_name; ?></td>
                            <td><?= $row_menu_item->name; ?></td>
                            <td><?= $row_menu_item->sub_controller; ?></td>
                            <td><?= $row_menu_item->rank; ?></td>
                            <td><?= $display; ?></td>
                            <td>
                                <button type="button" data-record_id="<?= $row_menu_item->tbl_menu_itemsId; ?>"
                                        class="btn btn-add btn-xs edit-sub-menu-item" data-toggle="modal"
                                        data-target="#updateSubMenuItem"><i class="fa fa-pencil"></i></button>
                                <button type="button" data-record_id="<?= $row_menu_item->tbl_menu_itemsId; ?>"
                                        class="btn btn-danger btn-xs delete-sub-menu-item" data-toggle="modal"
                                        data-target="#deleteSubMenuItem"><i class="fa fa-trash-o"></i></button>

                            </td>
                        </tr>
                        <?php $i++;
                    }
                    ?>
                    </tbody>
                </table>
                <?= form_close(); ?>
            </div>
        </div>
       
      </div>
    </div>
  </section>
 <!-- addSubMenuItem Modal-->
<div class="modal fade" id="addSubMenuItem" tabindex="-1" role="dialog" aria-hidden="true"></div>
<!-- /.modal -->

<!-- updateSubMenuItem Modal-->
<div class="modal fade" id="updateSubMenuItem" tabindex="-1" role="dialog" aria-hidden="true"></div>
<!-- /.modal -->

<!-- deleteSubMenuItem Modal -->
<!-- delete deleteMember -->
<div class="modal fade" id="deleteSubMenuItem" tabindex="-1" role="dialog" aria-hidden="true"></div>
<!-- /.modal -->






