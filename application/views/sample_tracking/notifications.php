<?php $admin_privilege = (cud_role_check((int)($this->session->userdata['user_group_id']))); ?>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
 <?php include('sidebar.php');?>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        System Notifications(<?=$total_notifications;?>)
      </h1>
      <ol class="breadcrumb">
        <li><a href="<?=base_url();?>/Dashboard/index"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active"><a href="<?=base_url();?>/Dashboard/index">Dashboard</a></li>
      </ol>
    </section>   
  <section class="content container-fluid">
      <div class="row">
        <div class="col-md-12">
          <!-- The time line -->
          <ul class="timeline">
            <!-- timeline time label -->
            <li class="time-label">
                  <span class="bg-red">
                    <?=date('d M Y');?>
                  </span>
            </li>
            <!-- /.timeline-label -->
            <!-- timeline item -->
            <?php if(isset($get_all_notifications)):
              foreach($get_all_notifications as $notifications):
            $user_id=$notifications->created_by;
            //$this->CI =& get_instance();
            ?>
            <li>
              <i class="fa fa-bell bg-blue"></i>

              <div class="timeline-item">
                <span class="time"><i class="fa fa-clock-o"></i><?=$this->reverselookups_model->time_elapsed_string($notifications->date_created);?></span>

                <h3 class="timeline-header"><a href="#"><?=$notifications->created_by;?></a> trigered a notification</h3>

                <div class="timeline-body">
                  <?=$notifications->message;?>
                </div>
              </div>
            </li>
            <?php endforeach;?>
          <?php endif;?>
          </ul>
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
  </section>

