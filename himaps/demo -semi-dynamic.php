


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

//require_once('conf.php');
@mysql_select_db("db_ovcmis_me_v3",mysql_connect("localhost","root","craiwrut"))or die(@mysql_error());
//require_once('mapManagerClass.php');
  

//$district4= MapManager::ViewOVCRServed($_GET['quarter'],$_GET['year'],$_GET['indicator'],102);



/*$district4= MapManager::ViewOVCRServed("Apr - Jun","2014/2015",250,102);

 $select="select districtCode,sum(total) as totalOVCServed
		  from tbl_organizationreporting 
		 where reportingPeriod like '".$quarter."%' 
		 and year='".$fyear."' 
		 and indicator_id='".$indicator."'
		 and districtCode='".$district."'
		 group by districtCode
		 order by districtCode asc ";
 $Query=@Execute($select) or die(mysql_error());
 $Results=@FetchRecords($Query);
 return $Results['totalOVCServed']; */
//$x=100;

//==========Map Data customizations-------------------------------------------

 $dynamicData= '[
 {
            "hc-key": "ug-2762",
            "value": 612
        },
 {
            "hc-key": "ug-7051",
            "value": 611
        },
  {
            "hc-key": "ug-2759",
            "value": 610
        },
 {
            "hc-key": "ug-7072",
            "value": 609
        },
 {
            "hc-key": "ug-1685",
            "value": 608
        },
  {
            "hc-key": "ug-1683",
            "value": 14
        },
  {
            "hc-key": "ug-1682",
            "value": 13
        },
 {
            "hc-key": "ug-1678",
            "value": 607
        },
   {
            "hc-key": "ug-7058",
            "value": 606
        },
 {
            "hc-key": "ug-7084",
            "value": 605
        },
  {
            "hc-key": "ug-7056",
            "value": 604
        },
 {
            "hc-key": "ug-2786",
            "value": 603
        },
 
 {
            "hc-key": "ug-3388",
            "value": 602
        },
  
  {
            "hc-key": "ug-3385",
            "value": 600
        },
  {
            "hc-key": "ug-2791",
            "value": 5
        },
  {
            "hc-key": "ug-2785",
            "value": 4
        },
  {
            "hc-key": "ug-7075",
            "value": 300
        },
 {
            "hc-key": "ug-7074",
            "value": 200
        },
 {
            "hc-key": "ug-7073",
            "value": 1
        },
 
  {
            "hc-key": "ug-2595",
 			"value": 200
        },
{	
            "hc-key": "ug-2753",
            "value": 110
        },
	{
            "hc-key": "ug-2755",
            "value": 111
        },
		{
            "hc-key": "ug-2767",
            "value": 613
        },
		{
            "hc-key": "ug-2777",
            "value": 614
        } ,
        {
            "hc-key": "ug-2778",
            "value": 615
        }
		,
        {
            "hc-key": "ug-2780",
            "value": 616
        }
		 ,
        {
            "hc-key": "ug-2781",
            "value": 617
        }
		,
        {
            "hc-key": "ug-2782",
            "value": 618
        }
		 ,
        {
            "hc-key": "ug-2783",
            "value": 400
        },
        {
            "hc-key": "ug-2779",
            "value": 620
        }
		,
        {
            "hc-key": "ug-2784",
            "value": 700
        },
        {
            "hc-key": "ug-3382",
            "value": 1000
        } ,
        {
            "hc-key": "ug-3384",
            "value": 1500
        } ,
        {
            "hc-key": "ug-3381",
            "value": 2000
        },
        {
            "hc-key": "ug-3383",
            "value": 2300
        },
        {
            "hc-key": "ug-3390",
            "value": 2600
        },
        {
            "hc-key": "ug-3386",
            "value": 2700
        },
        {
            "hc-key": "ug-3391",
            "value": 2800
        } ,
        {
            "hc-key": "ug-3392",
            "value": 2900
        },
        {
            "hc-key": "ug-3394",
            "value": 3000
        },
        {
            "hc-key": "ug-2750",
            "value": 3100
        },
        {
            "hc-key": "ug-7048",
            "value": 3200
        } ,
        {
            "hc-key": "ug-7080",
            "value": 3300
        },
        {
            "hc-key": "ug-7081",
            "value": 3500
        },
        {
            "hc-key": "ug-1684",
            "value": 3700
        },
        {
            "hc-key": "ug-7082",
            "value": 3800
        },
        {
            "hc-key": "ug-7068",
            "value": 3900
        } ,
        {
            "hc-key": "ug-7070",
            "value": 4000
			}
			,
        {
            "hc-key": "ug-7049",
            "value": 4300
        },
        {
            "hc-key": "ug-2787",
            "value": 4400
        },
        {
            "hc-key": "ug-7055",
            "value": 4500
        } ,
        {
            "hc-key": "ug-2769",
            "value": 4600
        },
        {
            "hc-key": "ug-7052",
            "value": 4700
        },
        {
            "hc-key": "ug-2774",
            "value": 4800
        },
        {
            "hc-key": "ug-7059",
            "value": 4900
        },
        {
            "hc-key": "ug-7083",
            "value": 5000
        },
        {
            "hc-key": "ug-7057",
            "value": 5200
        } ,
        {
            "hc-key": "ug-2790",
            "value": 5500
        },
        {
            "hc-key": "ug-2776",
            "value": 5600
        } ,
        {
            "hc-key": "ug-7067",
            "value": 5700
        }
		]';
 /**/

//-------------------------------end of data customizations----------------------------------------------



?>




$(function () {

    // Prepare demo data
   /*  var data = [
       
        
        
       
    

     ,
        {
            "hc-key": "ug-7065",
            "value": 58
        },
        {
            "hc-key": "ug-7066",
            "value": 59
        },
        {
            "hc-key": "ug-7069",
            "value": 60
        },
        {
            "hc-key": "ug-7061",
            "value": 61
        },
        {
            "hc-key": "ug-3389",
            "value": 62
        },
        {
            "hc-key": "ug-7062",
            "value": 63
        },
        {
            "hc-key": "ug-7063",
            "value": 64
        },
        {
            "hc-key": "ug-7064",
            "value": 65
        },
        {
            "hc-key": "ug-7086",
            "value": 66
        },
        {
            "hc-key": "ug-2744",
            "value": 67
        },
        {
            "hc-key": "ug-1679",
            "value": 68
        },
        {
            "hc-key": "ug-1680",
            "value": 69
        },
        {
            "hc-key": "ug-7054",
            "value": 70
        },
        {
            "hc-key": "ug-1686",
            "value": 71
        },
        {
            "hc-key": "ug-7078",
            "value": 72
        },
        {
            "hc-key": "ug-1677",
            "value": 73
        },
        {
            "hc-key": "ug-1688",
            "value": 74
        },
        {
            "hc-key": "ug-1690",
            "value": 75
        },
        {
            "hc-key": "ug-2745",
            "value": 76
        },
        {
            "hc-key": "ug-2752",
            "value": 77
        },
        {
            "hc-key": "ug-2754",
            "value": 78
        },
        {
            "hc-key": "ug-1687",
            "value": 79
        },
        {
            "hc-key": "ug-2757",
            "value": 80
        },
        {
            "hc-key": "ug-7060",
            "value": 81
        },
        {
            "hc-key": "ug-1689",
            "value": 82
        },
        {
            "hc-key": "ug-2760",
            "value": 83
        },
        {
            "hc-key": "ug-2761",
            "value": 84
        },
        {
            "hc-key": "ug-2766",
            "value": 85
        },
        {
            "hc-key": "ug-2765",
            "value": 86
        },
        {
            "hc-key": "ug-2764",
            "value": 87
        },
        {
            "hc-key": "ug-2749",
            "value": 88
        },
        {
            "hc-key": "ug-2768",
            "value": 89
        },
        {
            "hc-key": "ug-2763",
            "value": 90
        },
        {
            "hc-key": "ug-2748",
            "value": 91
        },
        {
            "hc-key": "ug-2771",
            "value": 92
        },
        {
            "hc-key": "ug-2772",
            "value": 93
        },
        {
            "hc-key": "ug-2775",
            "value": 94
        },
        {
            "hc-key": "ug-2788",
            "value": 95
        },
        {
            "hc-key": "ug-2789",
            "value": 96
        },
        {
            "hc-key": "ug-3387",
            "value": 97
        },
        {
            "hc-key": "ug-3393",
            "value": 98
        },
        {
            "hc-key": "ug-7079",
            "value": 99
        },
        {
            "hc-key": "ug-7076",
            "value": 100
        },
        {
            "hc-key": "ug-2746",
            "value": 101
        },
        {
            "hc-key": "ug-2747",
            "value": 102
        },
        {
            "hc-key": "ug-2751",
            "value": 103
        },
        {
            "hc-key": "ug-2758",
            "value": 104
        },
        {
            "hc-key": "ug-2756",
            "value": 105
        },
        {
            "hc-key": "ug-7053",
            "value": 106
        },
        {
            "hc-key": "ug-2770",
            "value": 107
        },
        {
            "hc-key": "ug-2773",
            "value": 108
        },
        {
            "hc-key": "ug-1681",
            "value": 109
        },
        {	//----
            "hc-key": "ug-2753",
            "value": 110
        },
        {	//kiboga--------
            "hc-key": "ug-2755",
            "value": 111
        }
    ] */
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



