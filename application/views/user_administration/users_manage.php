<?php $admin_privilege = (cud_role_check((int)($this->session->userdata['user_group_id']))); ?>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
 <?php include('sidebar.php');?>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <section class="content-header">
      <h1>
        User Management
      </h1>
      <ol class="breadcrumb">
        <li><a href="<?=base_url();?>/Dashboard/index"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active"><a href="<?=base_url();?>/Dashboard/index">Dashboard</a></li>
      </ol>
    </section>  
  <section class="content container-fluid">
  <div class="row">
    <div class="col-md-12">
      <div class="box box-info pull-right">
        <div class="box-header with-border">
          <div class="btn-group">
                <div class="btn btn-primary waves-effect waves-light add-users" data-toggle="modal" data-target="#addUsers">
                            Add new user
                </div>

                <!--<button class="btn btn-pink waves-effect waves-light m-b-5"> <span>Book Flight</span> <i class="fa fa-plane m-l-5"></i> </button>-->
            </div>
        </div>
        <?php
            if ($this->session->flashdata('message') != ''):
                echo $this->session->flashdata('message');
            endif;
            if ($this->session->flashdata('user_delete_success_msg') != ''):
                echo $this->session->flashdata('user_delete_success_msg');
            endif;
            ?>
        <div class="box-body  no-padding">
          <div class="col-xs-12 table-responsive">
                    <table class="table table-striped">
                    <thead>
                    <tr class="info">
                        <th>#</th>
                        <th>Full Name</th>
                        <th>User Name</th>
                        <th>User Role</th>
                        <th>Email</th>
                        <th>Telephone</th>

                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <?php
                    $CI =& get_instance();
                    $CI->load->model('Reverselookups_model');
                    $i = 1;
                    $app = 1;
                    foreach ($data_get_all_users as $row_user) {
                        $role_name = $CI->Reverselookups_model->get_role_name($row_user->role_id);
                        ?>
                        <tr>
                            <td><?= $app; ?>.</td>
                            <td><?= $row_user->fullNames; ?></td>
                            <td><?= $row_user->username; ?></td>
                            <td><?= $role_name; ?></td>
                            <td><?= $row_user->email; ?></td>
                            <td><?= $row_user->tel_mobile; ?></td>
                            <td>
                                <button type="button" data-record_id="<?= $row_user->id; ?>"
                                        class="btn btn-add btn-xs edit-users" data-toggle="modal"
                                        data-target="#updateUsers"><i class="fa fa-pencil"></i></button>
                                <button type="button" data-record_id="<?= $row_user->id; ?>"
                                        class="btn btn-danger btn-xs delete-users" data-toggle="modal"
                                        data-target="#deleteUsers"><i class="fa fa-trash-o"></i></button>
                            </td>
                        </tr>
                        <?php $app++;
                    } ?>
                    </tbody>
                </table>
            </div>
        </div>
       
      </div>
    </div>
  </section>


  
  
  
  
  
<div class="modal fade" id="addUsers" tabindex="-1" role="dialog" aria-hidden="true"></div>
<div class="modal fade" id="updateUsers" tabindex="-1" role="dialog" aria-hidden="true"></div>
<div class="modal fade" id="deleteUsers" tabindex="-1" role="dialog" aria-hidden="true"></div>
