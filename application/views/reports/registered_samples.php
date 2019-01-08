<html>
<head>
</head>
<body>
    <div class="section">
      <div class="title">Summary of registered samples</div>
      <div class="title"></div>
      <div class="title">Date:<?=date('d M Y')?></div>
      <br>
      <div class="content">
        <table autosize="1" style="overflow: wrap">
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
                            <td><?= date('d,M Y',strtotime($row_samples->initialSampleDate)); ?></td>
                            <td><?= date('d,M Y',strtotime($row_samples->finalDestinationDate)); ?></td>
                        </tr>
                          <?php $app++;
                        }
                      }?>
          </tbody>
        </table>
      </div>
    </div></body></html>