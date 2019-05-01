<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title></title>
  <!-- Tell the browser to be responsive to screen width -->
 <link rel="stylesheet" href="<?=base_url();?>vendors/bower_components/bootstrap/dist/css/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="<?=base_url();?>vendors/bower_components/font-awesome/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="<?=base_url();?>vendors/bower_components/Ionicons/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="<?=base_url();?>vendors/dist/css/AdminLTE.min.css">
  <!-- iCheck -->
  <link rel="stylesheet" href="<?=base_url();?>vendors/plugins/iCheck/square/blue.css">
  <!-- Google Font -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body background="<?=base_url();?>vendors/dist/img/background.JPG">
<div class="register-box">
  <div class="register-box-body">
    <p class="login-box-msg">New password will be sent to your email</p>
    <?php
    $attributes = array("id" => "login_form", "name" => "login_form");
    echo form_open("FmLogin/forgot", $attributes); ?>
      <div class="form-group">
        <input type="email" name="email" class="form-control" placeholder="Enter your email address to reset your passord" required>
        <span class="glyphicon glyphicon-email form-control-feedback"></span>
      </div>
      <div class="row">
        
        <div class="col-xs-4">
          <button type="submit" class="btn btn-primary btn-block btn-flat">Submit</button>
        </div>
        <!-- /.col -->
      </div>
   <?=form_close();?>
  </div>
  <!-- /.form-box -->
</div>
<!-- /.register-box -->

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