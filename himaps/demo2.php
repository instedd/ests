


<html>
	<head>
	<script src="js/jquery-1.11.0.js"></script>
	<script src="js/highmaps.js"></script>
	<script src="js/exporting.js"></script>
	<script src="js/ug-all.js"></script>
	<link rel="stylesheet" href="css/map_style.css" type="text/css" />
	
	</head>

	<body >
  
		<div id="container" style="width:1800px; height:800px;">
        
              <script language="javascript" type="text/javascript" >
        <?php

/**/ @mysql_select_db("db_ovcmis_me_v3",mysql_connect("localhost","root","craiwrut"))or die(@mysql_error());
 

$dynamicData=require_once("mapdata.php");
?>




$(function () {

    // Prepare demo data
  
	var data=<?php  echo $dynamicData;  ?>
	
		

    // Initiate the chart
    $('#container').highcharts('Map', {

        title : {
            text : '<?php //echo $_GET['IndicatorName']; ?>'
        },

        subtitle : {
            text : 'Source map: <a href="himapsUG/ug-all.js">Uganda</a>'
        },

        mapNavigation: {
            enabled: true,
            buttonOptions: {
                verticalAlign: 'bottom'
            }
        },

        colorAxis: {
            min: 0
        },

        series : [{
            data : data,
            mapData: Highcharts.maps['countries/ug/ug-all'],
            joinBy: 'hc-key',
            name: 'Dynamic Data',
            states: {
                hover: {
                    color: '#BADA55'
                }
            },
            dataLabels: {
                enabled: true,
                format: '{point.name}'
            }
        }]
    });
});

              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              
              </script>
        
        </div>
	</body>

</html>



