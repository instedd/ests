<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title><?=projectName;?>:Home</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.7 -->
  <link rel="stylesheet" href="<?=base_url();?>vendors/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="<?=base_url();?>vendors/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="<?=base_url();?>vendors/bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<?=base_url();?>vendors/dist/css/AdminLTE.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="<?=base_url();?>vendors/plugins/iCheck/square/blue.css">

  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body background="<?=base_url();?>vendors/dist/img/background.jpg">
<div class="container" style="padding-top:10px;">
  <div class="row">
      <div class="col-md-4"><img src="" style="padding-top:10px; class="img-responsive pull-left" width="170"></div>
        <div class="col-md-4" align="center"><img src="<?=base_url();?>vendors/dist/img/logo.png" class="img-responsive" width="150px;" height="80px;"></div>
        <div class="col-md-4"><img src="" class="img-responsive pull-right"></div>
    </div>
</div>
<div class="login-box">
  
  <div class="login-box-body">
    <p class="login-box-msg">Sign in to start your session</p>
<?php
                                        $attributes = array("class" => "form-horizontal", "id" => "login_form", "name" => "login_form");
                                        echo form_open("FmLogin/index", $attributes); ?>

                                        <div class="form-group ">
                                            <div class="col-xs-12">
                                                <input class="form-control" id="txt_username" name="txt_username"
                                                       type="text"
                                                       placeholder="Username" type="text"
                                                       value="<?= set_value('txt_username'); ?>"/>
                                                <span class="text-danger"><?= form_error('txt_username'); ?></span>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <div class="col-xs-12">
                                                <input class="form-control" id="txt_password" name="txt_password"
                                                       type="password"
                                                       required="" placeholder="Password"
                                                       value="<?= set_value('txt_password'); ?>"/>
                                                <span class="text-danger"><?= form_error('txt_password'); ?></span>
                                            </div>
                                        </div>



                                        <div class="form-group account-btn text-center m-t-10">
                                            <div class="col-xs-12">
                                               <?= $this->session->flashdata('login_error_msg');?>
                                               <?= $this->session->flashdata('message'); ?>

                                                <button id="submit" name="submit" value="Sign In"
                                                        class="btn w-md btn-bordered btn-danger waves-effect waves-light"
                                                        type="submit">Login
                                                </button>
                                            </div>
                                        </div>
                                        <?= form_close(); ?>
                                        <div class="clearfix"></div>

    <a href="<?=base_url();?>FmLogin/forgot_password">I forgot my password</a><br>
  </div>
  <!-- /.login-box-body --> 
</div>
<!-- jQuery 3 -->
<script src="<?=base_url();?>vendors/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="<?=base_url();?>vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="<?=base_url();?>vendors/plugins/iCheck/icheck.min.js"></script>
<script>
  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' /* optional */
    });
  });
</script>
</body>
</html>
