
  <div class="control-sidebar-bg"></div>

</div>
<!-- jQuery 3 -->
<script src="<?=base_url();?>vendors/bower_components/jquery/dist/jquery.min.js"></script>
<script src = "<?=base_url();?>highcharts/code/highcharts.js"></script>
<script src="<?=base_url();?>vendors/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script src="<?=base_url();?>vendors/bower_components/fastclick/lib/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="<?=base_url();?>vendors/dist/js/adminlte.min.js"></script>
<!-- Sparkline -->
<script src="<?=base_url();?>vendors/bower_components/jquery-sparkline/dist/jquery.sparkline.min.js"></script>
<!-- jvectormap  -->
<script src="<?=base_url();?>vendors/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
<script src="<?=base_url();?>vendors/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
<!-- SlimScroll -->
<script src="<?=base_url();?>vendors/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
<!-- ChartJS -->
<script src="<?=base_url();?>vendors/bower_components/chart.js/Chart.js"></script>
<script src="<?=base_url();?>vendors/dist/js/demo.js"></script>
<script src="<?=base_url();?>vendors/dist/js/custom-functions.js"></script>

<script src="<?=base_url();?>vendors/bower_components/select2/dist/js/select2.full.min.js"></script>
<!-- InputMask -->
<script src="<?=base_url();?>vendors/plugins/input-mask/jquery.inputmask.js"></script>
<script src="<?=base_url();?>vendors/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="<?=base_url();?>vendors/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<!-- date-range-picker -->
<script src="<?=base_url();?>vendors/bower_components/moment/min/moment.min.js"></script>
<script src="<?=base_url();?>vendors/bower_components/bootstrap-daterangepicker/daterangepicker.js"></script>
<!-- bootstrap datepicker -->
<script src="<?=base_url();?>vendors/bower_components/bootstrap-datepicker/dist/js/bootstrap-datepicker.min.js"></script>
<!-- bootstrap color picker -->
<script src="<?=base_url();?>vendors/bower_components/bootstrap-colorpicker/dist/js/bootstrap-colorpicker.min.js"></script>
<!-- bootstrap time picker -->
<script src="<?=base_url();?>vendors/plugins/timepicker/bootstrap-timepicker.min.js"></script>
<script src="<?=base_url();?>vendors/plugins/iCheck/icheck.min.js"></script>
<script src="<?=base_url();?>vendors/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="<?=base_url();?>vendors/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script>
  $(function () {
    $('#example1').DataTable(
      {
      'paging'      : true,
      'lengthChange': false,
      'searching'   : true,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false
    })
    $('#example2').DataTable({
      'paging'      : true,
      'lengthChange': false,
      'searching'   : false,
      'ordering'    : true,
      'info'        : true,
      'autoWidth'   : false
    })
  })
</script>
<script>
  $(function () {
    //Initialize Select2 Elements
    $('.select2').select2()
    $('[data-mask]').inputmask()

    //Date range picker
    $('#reservation').daterangepicker()
    //Date range picker with time picker
    $('#reservationtime').daterangepicker({ timePicker: true, timePickerIncrement: 30, format: 'MM/DD/YYYY h:mm A' })
    //Date range as a button
    $('#daterange-btn').daterangepicker(
      {
        ranges   : {
          'Today'       : [moment(), moment()],
          'Yesterday'   : [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
          'Last 7 Days' : [moment().subtract(6, 'days'), moment()],
          'Last 30 Days': [moment().subtract(29, 'days'), moment()],
          'This Month'  : [moment().startOf('month'), moment().endOf('month')],
          'Last Month'  : [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
        startDate: moment().subtract(29, 'days'),
        endDate  : moment()
      },
      function (start, end) {
        $('#daterange-btn span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'))
      }
    )

    //Date picker
    $('.datepicker').datepicker({
      autoclose: true
    })

    //iCheck for checkbox and radio inputs
    $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
      checkboxClass: 'icheckbox_minimal-blue',
      radioClass   : 'iradio_minimal-blue'
    })
    //Red color scheme for iCheck
    $('input[type="checkbox"].minimal-red, input[type="radio"].minimal-red').iCheck({
      checkboxClass: 'icheckbox_minimal-red',
      radioClass   : 'iradio_minimal-red'
    })
    //Flat red color scheme for iCheck
    $('input[type="checkbox"].flat-red, input[type="radio"].flat-red').iCheck({
      checkboxClass: 'icheckbox_flat-green',
      radioClass   : 'iradio_flat-green'
    })

    //Colorpicker
    $('.my-colorpicker1').colorpicker()
    //color picker with addon
    $('.my-colorpicker2').colorpicker()

    //Timepicker
    $('.timepicker').timepicker({
      showInputs: false
    })
  })
</script>
<script language = "JavaScript">
         $(document).ready(function() {
            var title = {
               text: 'Annual Sample Tracking Statistics'   
            };
            var subtitle = {
               text: ''
            };
            var xAxis = {
               categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                  'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            };
            var yAxis = {
               title: {
                  text: 'No. of Samples'
               },
               plotLines: [{
                  value: 0,
                  width: 1,
                  color: '#808080'
               }]
            };   

            var tooltip = {
               valueSuffix: ''
            }
            var legend = {
               layout: 'vertical',
               align: 'right',
               verticalAlign: 'middle',
               borderWidth: 0
            };
            var series =  [{
                  name: 'Registered Samples',
                  data: [<?=$monthly_registered_samples[0]->registered_samples;?>, <?=$monthly_registered_samples[1]->registered_samples;?>, <?=$monthly_registered_samples[2]->registered_samples;?>,
                  <?=$monthly_registered_samples[3]->registered_samples;?>, <?=$monthly_registered_samples[4]->registered_samples;?>,
                  <?=$monthly_registered_samples[5]->registered_samples;?>, <?=$monthly_registered_samples[6]->registered_samples;?>,
                  <?=$monthly_registered_samples[7]->registered_samples;?>, <?=$monthly_registered_samples[8]->registered_samples;?>, <?=$monthly_registered_samples[9]->registered_samples;?>,
                   <?=$monthly_registered_samples[10]->registered_samples;?>, <?=$monthly_registered_samples[11]->registered_samples;?>]
               },
               {
                  name: 'Received Samples',
                  data: [
                  <?=$monthly_received_samples[0]->received_samples;?>, <?=$monthly_received_samples[1]->received_samples;?>, 
                  <?=$monthly_received_samples[2]->received_samples;?>,<?=$monthly_received_samples[3]->received_samples;?>, 
                  <?=$monthly_received_samples[4]->received_samples;?>,<?=$monthly_received_samples[5]->received_samples;?>,
                  <?=$monthly_received_samples[6]->received_samples;?>,<?=$monthly_received_samples[7]->received_samples;?>,
                  <?=$monthly_received_samples[8]->received_samples;?>,<?=$monthly_received_samples[9]->received_samples;?>,
                  <?=$monthly_received_samples[10]->received_samples;?>,<?=$monthly_received_samples[11]->received_samples;?>]
              }
            ];
             
            var json = {};
            json.title = title;
            json.subtitle = subtitle;
            json.xAxis = xAxis;
            json.yAxis = yAxis;
            json.tooltip = tooltip;
            json.legend = legend;
            json.series = series;

            $('#container1').highcharts(json);
         });
      </script>
      <script>
         $(document).ready(function() {
            var title = {
               text: 'Annual Sample Tracking Statistics of received Samples at final destination'   
            };
            var subtitle = {
               text: ''
            };
            var xAxis = {
               categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                  'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec',]
            };
            var yAxis = {
               title: {
                  text: 'No. of Samples'
               },
               plotLines: [{
                  value: 0,
                  width: 1,
                  color: '#808080'
               }]
            };   

            var tooltip = {
               valueSuffix: ''
            }
            var legend = {
               layout: 'vertical',
               align: 'right',
               verticalAlign: 'middle',
               borderWidth: 0
            };
            var series =  [{
                  name: 'Received delayed Samples at final destination',
                  data: [<?=$monthly_delayed_samples_at_final_destination[0]->mon_delayed_samples_at_final_destination;?>, 
                  <?=$monthly_delayed_samples_at_final_destination[1]->mon_delayed_samples_at_final_destination;?>,
                   <?=$monthly_delayed_samples_at_final_destination[2]->mon_delayed_samples_at_final_destination;?>,
                  <?=$monthly_delayed_samples_at_final_destination[3]->mon_delayed_samples_at_final_destination;?>, 
                  <?=$monthly_delayed_samples_at_final_destination[4]->mon_delayed_samples_at_final_destination;?>,
                  <?=$monthly_delayed_samples_at_final_destination[5]->mon_delayed_samples_at_final_destination;?>, 
                  <?=$monthly_delayed_samples_at_final_destination[6]->mon_delayed_samples_at_final_destination;?>,
                  <?=$monthly_delayed_samples_at_final_destination[7]->mon_delayed_samples_at_final_destination;?>, 
                  <?=$monthly_delayed_samples_at_final_destination[8]->mon_delayed_samples_at_final_destination;?>, 
                  <?=$monthly_delayed_samples_at_final_destination[9]->mon_delayed_samples_at_final_destination;?>,
                   <?=$monthly_delayed_samples_at_final_destination[10]->mon_delayed_samples_at_final_destination;?>, 
                   <?=$monthly_delayed_samples_at_final_destination[11]->mon_delayed_samples_at_final_destination;?>]
               },
               {
                  name: 'Received un delayed Samples at final destination',
                  data: [
                  <?=$monthly_undelayed_samples_at_final_destination[0]->mon_undelayed_samples_at_final_destination;?>, 
                  <?=$monthly_undelayed_samples_at_final_destination[1]->mon_undelayed_samples_at_final_destination;?>, 
                  <?=$monthly_undelayed_samples_at_final_destination[2]->mon_undelayed_samples_at_final_destination;?>,
                  <?=$monthly_undelayed_samples_at_final_destination[3]->mon_undelayed_samples_at_final_destination;?>, 
                  <?=$monthly_undelayed_samples_at_final_destination[4]->mon_undelayed_samples_at_final_destination;?>,
                  <?=$monthly_undelayed_samples_at_final_destination[5]->mon_undelayed_samples_at_final_destination;?>,
                  <?=$monthly_undelayed_samples_at_final_destination[6]->mon_undelayed_samples_at_final_destination;?>,
                  <?=$monthly_undelayed_samples_at_final_destination[7]->mon_undelayed_samples_at_final_destination;?>,
                  <?=$monthly_undelayed_samples_at_final_destination[8]->mon_undelayed_samples_at_final_destination;?>,
                  <?=$monthly_undelayed_samples_at_final_destination[9]->mon_undelayed_samples_at_final_destination;?>,
                  <?=$monthly_undelayed_samples_at_final_destination[10]->mon_undelayed_samples_at_final_destination;?>,
                  <?=$monthly_undelayed_samples_at_final_destination[11]->mon_undelayed_samples_at_final_destination;?>]
              }
            ];
             
            var json = {};
            json.title = title;
            json.subtitle = subtitle;
            json.xAxis = xAxis;
            json.yAxis = yAxis;
            json.tooltip = tooltip;
            json.legend = legend;
            json.series = series;

            $('#container2').highcharts(json);
         });
      </script>
      <script>
        jQuery(document).ready(function() {
            Highcharts.setOptions({
                global: {
                    useUTC: false,
                },
                lang: {
                    decimalPoint: '.',
                    thousandsSep: ','
                }
            });
            $('#sample_status_by_year').highcharts({
                chart: {
                    plotBackgroundColor: null,
                    plotBorderWidth: 1,//null,
                    plotShadow: false
                },
                title: {
                    text: 'Pie Chart showing Sample Status for this Year'
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        },
                        showInLegend: true
                    }
                },
                series: [{
                    type: 'pie',
                    name: 'Sample Status',
                    data: [
                        ['Delayed Samples', <?=(!empty($total_delayed_samples)) ? ($total_delayed_samples) : 0;?>],
                         ['On-Time Samples', <?=(!empty($total_undelayed_samples)) ? ($total_undelayed_samples) : 0;?>]
                    ],
                }]
            });
        });

    </script>

<script>
$(document).ready(function(){
 $('#barcode').change(function(){
  var sample_id = $('#barcode').val();
  if(sample_id != '')
  {
   $.ajax({
    url:"<?php echo base_url(); ?>SampleTracking/fetch_destination",
    method:"POST",
    data:{sample_id:sample_id},
    success:function(data)
    {
     $('#final_destination').html(data);
    }
   });
  }
  else
  {
   $('#final_destination').html('<option value=""></option>');
  }
 }); 
});
</script>


      <script type="text/javascript">
        $(function () {
            Highcharts.setOptions({
                global: {
                    useUTC: false,
                },
                lang: {
                    decimalPoint: '.',
                    thousandsSep: ','
                }
            });
            $('#container3').highcharts({
                chart: {
                    type: 'column',
                   options3d: {
                     enabled: true,
                     alpha: 30
                     }
                },
                title: {
                    text: '<h4>Bar graph showing suspected disease registered</h4>'
                },
                credits: {
                    enabled: false
                },
                  xAxis: {
                    categories: [
                      <?php foreach($outbreaks as $outbreak):?>
                      '<?=$outbreak->name?>',
                      <?php endforeach;?>
                    ],
                    crosshair: true
                  },
                yAxis: {
                    min: 0,
                    title: {
                        text: 'No of outbreaks (Count)'
                    }
                },
                legend: {
                    enabled: false
                },
                tooltip: {
                    pointFormat: 'Outbreaks: <b>{point.y:,.1f} </b>'
                },
                series: [{
                    name: 'Out breaks',
                    data: [

                      <?php foreach($outbreaks as $outbreak):?>
                      ['<?=$outbreak->name?>',<?=$outbreak->no_of_outbreaks?>],
                      <?php endforeach;?>
                        ],
                    dataLabels: {
                        enabled: false,
                        rotation: -90,
                        color: '#FFFFFF',
                        align: 'right',
                        format: '{point.y:,.1f}', // one decimal
                        y: 10, // 10 pixels down from the top
                        style: {
                            fontSize: '13px',
                            fontFamily: 'Verdana, sans-serif'
                        }
                    }
                }]
            });
        });

    </script>
</body>
</html>