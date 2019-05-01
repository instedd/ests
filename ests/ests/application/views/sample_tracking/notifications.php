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
            $expires_on=$notifications->expires_on;
            $action="encrypt";
            //$this->CI =& get_instance();
            ?>
            <?php if($expires_on==NULL){?>
            <li>
              <i class="fa fa-bell bg-blue"></i>

              <div class="timeline-item">
                <span class="time ">
                  <a href="<?=base_url('sampleTracking/notifications/'.encryptor($action,$notifications->id));?>">Mark as seen</a>
                </span>
               <span class="time"><i class="fa fa-clock-o"></i><?=$this->reverselookups_model->time_elapsed_string($notifications->date_created);?></span>
                <h3 class="timeline-header"><a href="#"><?=$notifications->created_by;?></a> trigered a notification</h3>

                <div class="timeline-body">
                  <?=$notifications->message;?>
                </div>
              </div>
            </li>
             <?php } else{?>
               <li>
              <i class="fa fa-bell bg-blue"></i>

              <div class="timeline-item" style="background-color: #D3D3D3;">
                <span class="time" style="text-color: red;"><i class="fa fa-check">Seen</i></span>
               <span class="time"><i class="fa fa-clock-o"></i><?=$this->reverselookups_model->time_elapsed_string($notifications->date_created);?></span>
                <h3 class="timeline-header"><a href="#"><?=$notifications->created_by;?></a> trigered a notification</h3>

                <div class="timeline-body">
                  <?=$notifications->message;?>
                </div>
              </div>
            </li>
            <?php }?>
            <?php endforeach;?>
          <?php endif;?>
          </ul>
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->
  </section>

