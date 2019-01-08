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
            if ($this->session->flashdata('role_perm_msg') != ''):
                echo $this->session->flashdata('role_perm_msg');
            endif;
            ?>
                <!--<button class="btn btn-pink waves-effect waves-light m-b-5"> <span>Book Flight</span> <i class="fa fa-plane m-l-5"></i> </button>-->
            </div>
        </div>
        <div class="box-body  no-padding">
          <div class="col-xs-12 table-responsive">
                    <?php
                $attributes = array("id" => "role_perm_form", "name" => "role_perm_form");
                echo form_open("SystemSetups/system_permissions", $attributes); ?>
                <table id="dataTableExample1" class="table datatable-buttons table-bordered table-striped table-hover">
                                <thead>
                                <tr class="info">
                                    <th>#</th>
                                    <th>Role</th>
                                    <th>Last Update</th>
                                    <th>Action:Permissions</th>
                                </tr>
                                </thead>
                                <tbody>
                                <?php
                                $i = 1;
                                foreach ($data_system_roles as $row_role) {
                                    ?>
                                    <tr>
                                        <td><?= $i; ?></td>
                                        <td><?= $row_role->name; ?></td>
                                        <td><?= $row_role->date_entry; ?></td>
                                        <td>
                                            <button type="button" data-record_id="<?= $row_role->id; ?>"
                                                    class="btn btn-primary btn-xs edit-permissions" data-toggle="modal"
                                                    data-target="#updatePermissions"><i class="fa fa-pencil"></i></button>
                                            
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
<div class="modal fade" id="addPermissions" tabindex="-1" role="dialog" aria-hidden="true"></div>
<div class="modal fade" id="updatePermissions" tabindex="-1" role="dialog" aria-hidden="true"></div>
<div class="modal fade" id="deletePermissions" tabindex="-1" role="dialog" aria-hidden="true">
</div>





















