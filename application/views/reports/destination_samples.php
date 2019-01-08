<html><head></head><body>
    <div class="section">
      <div class="title">Summary of received samples at destination</div>
      <div class="title"></div>
      <div class="title">Date:<?=date('d M Y')?></div>
      <br>
      <div class="content">
        <table class="collapse" id="pattern-style-a">
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
                            <td><?= date('d,M Y',strtotime($row_received_sample->date_received)); ?></td>
                            <td><?= $turn_around_time_status; ?></td>
                        </tr>
                          <?php $app++;
                        }
                      }?>
          </tbody>
        </table>
      </div>
    </div></body></html>