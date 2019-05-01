<?php $admin_privilege = (cud_role_check((int)($this->session->userdata['user_group_id']))); ?>
<body class="hold-transition skin-blue sidebar-mini">
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
                <button class="btn btn-primary waves-effect waves-light add-menu-category" id="primary-alert" data-toggle="modal"
                    data-target="#addMenuCategory">Add new Menu Item
            </button>

                <!--<button class="btn btn-pink waves-effect waves-light m-b-5"> <span>Book Flight</span> <i class="fa fa-plane m-l-5"></i> </button>-->
            </div>
        </div>
        <?php
            if ($this->session->flashdata('menu_cat_msg') != ''):
                echo $this->session->flashdata('menu_cat_msg');
            endif;
            ?>
        <div class="box-body  no-padding">
          <div class="col-xs-12 table-responsive">
                    <?php
                $attributes = array("id" => "roles_form", "name" => "roles_form");
                echo form_open("SystemSetups/menu_items", $attributes); ?>
                <table  class="table datatable-buttons table-striped table-bordered">
                    <thead>
                    <tr class="info">
                        <th>#</th>
                        <th>Menu Name</th>
                        <th>Icon</th>
                        <th>System Controller</th>
                        <th>Menu Rank</th>
                        <th>Display</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <?php
                    $i = 1;
                    foreach ($data_get_all_menu_items as $row_menu) {
                        $display=(($row_menu->display)==1)?'Yes':'No';
                        ?>
                        <tr>
                            <td><?= $i; ?></td>
                            <td><?= $row_menu->name; ?></td>
                            <td><?= $row_menu->awesome_icon; ?></td>
                            <td><?= $row_menu->controller; ?></td>
                            <td><?= $row_menu->rank; ?></td>
                            <td><?= $display; ?></td>
                            <td>
                                <button type="button" data-record_id="<?= $row_menu->id; ?>"
                                        class="btn btn-add btn-xs edit-menu-category" data-toggle="modal"
                                        data-target="#updateMenuCategory"><i class="fa fa-pencil"></i></button>
                                <button type="button" data-record_id="<?= $row_menu->id; ?>"
                                        class="btn btn-danger btn-xs delete-menu-category" data-toggle="modal"
                                        data-target="#deleteMenuCategory"><i class="fa fa-trash-o"></i></button>

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
 <div class="modal fade" id="addMenuCategory" tabindex="-1" role="dialog" aria-hidden="true"></div>
<div class="modal fade" id="updateMenuCategory" tabindex="-1" role="dialog" aria-hidden="true"></div>
<div class="modal fade" id="deleteMenuCategory" tabindex="-1" role="dialog" aria-hidden="true"></div>








