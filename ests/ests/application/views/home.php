<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
 <?php include('sidebar.php');?>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>Dashboard</h1>
      <ol class="breadcrumb">
        <li class="active"><a href="<?=base_url();?>Reports/map"><button class="btn btn-danger" >Map of outbreaks</button></a></li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <!-- Info boxes -->
      <div class="row">
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-aqua"><i class="fa fa-flask"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Total Samples</span>
              <span class="info-box-number"><?=$total_registered_samples;?><small></small></span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-red"><i class="fa fa-clock-o"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Delayed Samples</span>
              <span class="info-box-text">at destination</span>
              <!-- / <span class="info-box-text">Samples</span> -->
              <span class="info-box-number"><?=$total_delayed_samples;?></span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <!-- /.col -->

        <!-- fix for small devices only -->
        <div class="clearfix visible-sm-block"></div>

        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-green"><i class="fa fa-flask"></i></span>

            <div class="info-box-content">
              <span class="info-box-text">Samples in</span>
              <span class="info-box-text">transit</span>
              <span class="info-box-number"><?=$total_samples_in_transit;?></span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
        <div class="clearfix visible-sm-block"></div>
        <div class="col-md-3 col-sm-6 col-xs-12">
          <div class="info-box">
            <span class="info-box-icon bg-green"><i class="fa fa-clock-o"></i></span>

            <div class="info-box-content">
              <!-- / <span class="info-box-text">Total Delivered</span> -->
              <span class="info-box-text">On-Time Samples</span>
              <span class="info-box-text">at destination</span>
              <span class="info-box-number"><?=$total_undelayed_samples;?></span>
            </div>
            <!-- /.info-box-content -->
          </div>
          <!-- /.info-box -->
        </div>
      </div>
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <div class="box-body">
              <div id="container1" style = "width: 900px; height: 400px; margin: 0 auto">
              </div>
            </div>
            
            <div class="box-body">
              <div id="sample_status_by_year" style = "width: 900px; height: 400px; margin: 0 auto">
              </div>
            </div>
             <div class="box-body">
              <div id="container3" style = "width: 900px; height: 400px; margin: 0 auto">
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
             
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
          <div class="row">
            <div class="col-md-6">
               <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">Summary of samples sent out to top five destinations</h3>

              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <div class="table-responsive">
                <table class="table no-margin">
                  <thead>
                  <tr>
                    <th>Destination</th>
                    <th>No. of samples sent out</th>
                  </tr>
                  </thead>
                  <tbody>
                  
                  <tr>
                    <td><a href="pages/examples/invoice.html">CPHL</a></td>
                    <td><?=$total_sample_cphl?></td>
                  </tr>
                  
                  <tr>
                    <td><a href="pages/examples/invoice.html">IDI</a></td>
                    <td><?=$total_sample_idi?></td>
                  </tr>
                  <tr>
                    <td><a href="pages/examples/invoice.html">UVRI</a></td>
                    <td><?=$total_sample_uvri?></td>
                  </tr>
                  <tr>
                    <td><a href="pages/examples/invoice.html">DGAL</a></td>
                    <td><?=$total_sample_dgal?></td>
                  </tr>
                  <tr>
                    <td><a href="pages/examples/invoice.html">NTLP</a></td>
                    <td><?=$total_sample_ntlp?></td>
                  </tr>
                  </tbody>
                </table>
              </div>
              <!-- /.table-responsive -->
            </div>
            <!-- /.box-body -->
            
            <!-- /.box-footer -->
          </div>
              <!--/.direct-chat -->
            </div>
            <!-- /.col -->

            <div class="col-md-6">
              <!-- USERS LIST -->
              <div class="box box-info">
            <div class="box-header with-border">
              <h3 class="box-title">Summary of received sample status</h3>

              <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
              </div>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <div class="table-responsive">
                <table class="table table-stripped">
                  <thead>
                   <tr> <th>No. of samples receieved: <?=$total_received?></th></tr>
                   <tr><th>Sample status(both received at destination and those in transit)</th></tr>
                  </thead>
                  <tbody>
                    <tr><td style="text-color":"blue";><?=$received_intact?> Received intact</td></tr>
                    <tr><td><?=$received_broken_seals?> Received with broken seals</td></tr>
                    <tr><td><?=$received_open?> Received Open</td></tr>
                  </tbody>

                </table>
              </div>
              <!-- /.table-responsive -->
            </div>
            
            <!-- /.box-footer -->
          </div>


              <!--/.box -->
            </div>
            <!-- /.col -->
          </div>
          <!-- /.row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  

