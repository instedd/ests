<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
 <?php include('sidebar.php');?>
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Reports
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Reports</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">
          <div class="box">
            <!--<div class="box-body">
              <div id="container1" style = "width: 900px; height: 400px; margin: 0 auto">
              </div>
            </div>
            <div class="box-body">
              <div id="sample_status_by_year" style = "width: 900px; height: 400px; margin: 0 auto">
              </div>
            </div>-->
            <div class="box-header">
              <h3 class="box-title">Summary of registered Samples</h3>
               <a class="pull-right "href="<?=base_url('Reports/registered_samples');?>">
                            Export PDF
                </a>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example2" class="table table-bordered table-hover">
                <thead>
                <tr>
                        <th>#</th>
                        <th>Sample Id</th>
                        <th>Facility Code </th>
                        <th>Sample Type</th>
                        <th>Destination</th>
                        <th>Disease Type</th>
                        <th>Initial Sample Date</th>
                        <th>Expected Destination Date </th>
                </tr>
                </thead>
                <tbody>
                  <?php
                    $i = 1;
                    $app = 1;
                  if(isset($registered_samples)){
                    foreach ($registered_samples as $row_samples) {
                        ?>
                        <tr>
                            <td><?= $app; ?>.</td>
                            <td><?= $row_samples->sample_id; ?></td>
                            <td><?= $row_samples->facility_code_id; ?></td>
                            <td><?= $row_samples->sample_type_id; ?></td>
                            <td><?= $row_samples->destination_id; ?></td>
                            <td><?= $row_samples->disease_id; ?></td>
                            <td><?= date('d,M Y h:m:i',strtotime($row_samples->initialSampleDate)); ?></td>
                            <td><?= date('d,M Y h:m:i',strtotime($row_samples->finalDestinationDate)); ?></td>
                        </tr>
                        <?php $app++;
                    } 
          }else
          {
          echo "No records found";  
          }?>
          
                </tfoot>
              </table>
            </div>
            <!-- /.box-body -->
            <div class="box-header">
              <h3 class="box-title">Summary of received Samples at final destination</h3>
               <a class="pull-right "href="<?=base_url('Reports/destination_samples');?>">
                            Export PDF
                </a>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example2" class="table table-bordered table-hover">
                <thead>
                <tr>
                        <th>#</th>
                        <th>Sample Id</th>
                        <th>Status</th>
                        <th>Destination</th>
                        <th>Recorded By</th>
                        <th>Date Received</th>
                        <th>Turn-around time status</th>
                        
                        
                </tr>
                </thead>
                <tbody>
                  <?php
                    $i = 1;
                    $app = 1;
                   if(isset($received_samples)){
                    foreach ($received_samples as $row_received_sample) {
                        $date_received=$row_received_sample->date_received;
                        $finalDestinationDate=$row_received_sample->finalDestinationDate;
                        $destination=$row_received_sample->destination_id;
                        $turn_around_time_status=(($date_received>$finalDestinationDate)?'<span class="label-danger label label-default" >Delayed</span>':'<span class="label-success label label-default" >DELIVERED ON TIME</span>');
                       ?>
                        <tr>
                            <td><?= $app; ?>.</td>
                            <td><?= $row_received_sample->sample_id; ?></td>
                            <td><?= $row_received_sample->sample_status; ?></td>
                            <td><?= $row_received_sample->destination_id; ?></td>
                            <td><?= $row_received_sample->entered_by; ?></td>
                            <td><?= date('d,M Y h:m:i',strtotime($row_received_sample->date_received)); ?></td>
                            <td><?= $turn_around_time_status; ?></td>
                        </tr>
                        <?php $app++;
                    } 
          }else
          {
          echo "No records found";  
          }?>
          
                </tfoot>
              </table>
            </div>
            
            <!-- /.box-body -->
             <!-- /.box-body -->
            <div class="box-header">
              <h3 class="box-title">Summary of received samples in transit</h3>
              <a class="pull-right "href="<?=base_url('Reports/transit_samples');?>">
                            Export PDF
                </a>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
              <table id="example2" class="table table-bordered table-hover">
                <thead>
                <tr>
                        <th>#</th>
                        <th>Sample Id</th>
                        <th>Status</th>
                        <th>Destination</th>
                        <th>Recorded By</th>
                        <th>Date Received</th>
                        <th>Turn-around time status</th>
                        
                        
                </tr>
                </thead>
                <tbody>
                  <?php
                    $i = 1;
                    $app = 1;
                   if(isset($received_samples_in_transit)){
                    foreach ($received_samples_in_transit as $samples_in_transit) {
                        $date_received=$samples_in_transit->date_received;
                        $finalDestinationDate=$samples_in_transit->finalDestinationDate;
                        $destination=$samples_in_transit->destination_id;
                        $turn_around_time_status=(($date_received>$finalDestinationDate)?'<span class="label-danger label label-default" >Delayed</span>':'<span class="label-success label label-default" >DELIVERED ON TIME</span>');
                       ?>
                        <tr>
                            <td><?= $app; ?>.</td>
                            <td><?= $samples_in_transit->sample_id; ?></td>
                            <td><?= $samples_in_transit->sample_status; ?></td>
                            <td><?= $samples_in_transit->destination_id; ?></td>
                            <td><?= $samples_in_transit->entered_by; ?></td>
                            <td><?= date('d,M Y',strtotime($samples_in_transit->date_received)); ?></td>
                            <td><?= $turn_around_time_status; ?></td>
                        </tr>
                        <?php $app++;
                    } 
          }else
          {
          echo "No records found";  
          }?> </tfoot>
              </table>
            </div>
            
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  

