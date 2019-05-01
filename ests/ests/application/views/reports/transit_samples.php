<html><head></head><body>
    <div class="section">
      <div class="title">Summary of received samples but still in transit</div>
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
          <tbody><?php
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
                      }?>
          </tbody>
        </table>
      </div>
    </div></body></html>