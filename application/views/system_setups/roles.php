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
             
            <?php
            if ($this->session->flashdata('role_msg') != ''):
                echo $this->session->flashdata('role_msg');
            endif;
            ?>
             <button class="btn btn-primary waves-effect waves-light add-role" id="primary-alert" data-toggle="modal"
                    data-target="#addRole">Add new Role
            </button>

                <!--<button class="btn btn-pink waves-effect waves-light m-b-5"> <span>Book Flight</span> <i class="fa fa-plane m-l-5"></i> </button>-->
            </div>
        </div>
        <div class="box-body  no-padding">
          <div class="col-xs-12 table-responsive">
                    <?php
                $attributes = array("id" => "roles_form", "name" => "roles_form");
                echo form_open("SystemSetups/manage_roles", $attributes); ?>
                <table class="table datatable-buttons table-striped table-bordered">
                    <thead>
                    <tr class="info">
                        <th>#</th>
                        <th>Role Name</th>
                        <th>Entry Date</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <?php
                    $i = 1;
                    foreach ($data_get_all_roles as $row_role) {
                        ?>
                        <tr>
                            <td><?= $i; ?></td>
                            <td><?= $row_role->name; ?></td>
                            <td><?= clean_date($row_role->date_entry); ?></td>
                            <td>
                                <button type="button" data-record_id="<?= $row_role->id; ?>"
                                        class="btn btn-add btn-xs edit-role" data-toggle="modal"
                                        data-target="#updateRole"><i class="fa fa-pencil"></i></button>
                                <button type="button" data-record_id="<?= $row_role->id; ?>"
                                        class="btn btn-danger btn-xs delete-role" data-toggle="modal"
                                        data-target="#deleteRole"><i class="fa fa-trash-o"></i></button>

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
<div class="modal fade" id="addRole" tabindex="-1" role="dialog" aria-hidden="true"></div>
<div class="modal fade" id="updateRole" tabindex="-1" role="dialog" aria-hidden="true"></div>
<div class="modal fade" id="deleteRole" tabindex="-1" role="dialog" aria-hidden="true"></div>

















