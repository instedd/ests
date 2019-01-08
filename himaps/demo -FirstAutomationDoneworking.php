


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

/**/ 
@mysql_select_db("db_ovcmis_me_v3",mysql_connect("localhost","root","craiwrut"))or die(@mysql_error());
 //viewOVCServed($district,$_GET['quarter'],$_GET['fyear'],$_GET['indicator']);
function viewOVCServed($district,$quarter='Jul - Sep',$fyear='2015/2016',$indicator=251){

/* $select="select d.districtCode,d.districtName,d.shapeFile
		  from tbl_district d  
		 where d.entryType like 'District%' 
		  and  d.districtCode='105'
		 group by d.districtCode
		 order by d.districtName asc ";
 $Query=@mysql_query($select) or die(mysql_error());
	$count=0;

 //$dynamicData= '[';
 while($row=mysql_fetch_array($Query)){ */
 
  $select1="select districtCode,sum(total) as totalOVCServed
		  from tbl_organizationreporting 
		 where reportingPeriod like '".$quarter."%' 
		 and year like '".$fyear."%' 
		 and indicator_id='".$indicator."'
		 and districtCode='".$district."'
		 group by districtCode
		 order by districtCode asc ";
 $Query1=@mysql_query($select1) or die(mysql_error());
 $Results=@mysql_fetch_array($Query1);

 $districtData=$Results['totalOVCServed']==''?0:$Results['totalOVCServed'];
 /* 
 $count1=$count<111?',':'';
  $dynamicData.='{<br>
            "hc-key": "'.$row['shapeFile'].'<br>",
            "value": '.$districtData.'
        <br>}' ; 
		$count++;  }*/

		//$dynamicData.='<br>]'; 

return $districtData;



}

$districtDataKALANGALA101_2595=viewOVCServed(101)==''?0:viewOVCServed(101);
$districtDataugMukono108_1687=viewOVCServed(108);
//$districtData485_7074=viewOVCServed(485);
$districtDataBuikwe469_7073=viewOVCServed(469);
$districtDataKAPCHORWA206_2775=viewOVCServed(206);
$districtDataKween476_7084=viewOVCServed(476);
//--------------------------------------------------
$districtDataKibuku492_7082=viewOVCServed(492);
$districtDataBulambuli483_7083=viewOVCServed(483);

$districtDataKUMI208_1677=viewOVCServed(208);
$districtDataKABERAMAIDO213_1678=viewOVCServed(213);
$districtDataKAYUNGA112_1679=viewOVCServed(112);
$districtDataIGANGA203_1680=viewOVCServed(203);
$districtDataKAMULI205_1681=viewOVCServed(205);
$districtDataNamayingo503_7075=viewOVCServed(503);
 //-------------------DONE----------------------
$districtDataAMOLATAR433_1682=viewOVCServed(433);
$districtDataKALIRO416_1683=viewOVCServed(416);
$districtDataBUDAKA446_1684=viewOVCServed(446);
$districtDataNAMUTUMBA422_1685=viewOVCServed(422);
$districtDataSOROTI211_1686=viewOVCServed(211);
$districtDataPALLISA210_1688=viewOVCServed(210);
$districtDataSEMBABULE447_1689=viewOVCServed(447);
$districtDataMPIGI106_1690=viewOVCServed(106);
$districtDataKALANGALA101_2595=viewOVCServed(101); 
$districtDataAmudat479_7058=viewOVCServed(479);
$districtDataKATAKWI207_2785=viewOVCServed(207);
//--------------------------------------------------
$districtDataJINJA204_2744=viewOVCServed(204);
$districtDataAdjumani301_2745=viewOVCServed(301);
$districtDataARUA303_2746=viewOVCServed(303);
$districtDataKOBOKO432_2747=viewOVCServed(432);
$districtDataBULISA449_2748=viewOVCServed(449);
$districtDataNwoya516_2749=viewOVCServed(516);
$districtDataNEBBI310_2750=viewOVCServed(310);
//-------------------------------------------------


$districtDataMOYO309_2751=viewOVCServed(309);
$districtDataYUMBE313_2752=viewOVCServed(313);
$districtDataKIBOGA103_2753=viewOVCServed(103);
$districtDataKAMPALA102_2754=viewOVCServed(102);
$districtDataMARACHA444_2755=viewOVCServed(444);
$districtDataNAKASONGOLA109_2756=viewOVCServed(109);
$districtDataWAKISO113_2757=viewOVCServed(113);
//----------------------------------
$districtDataLUWERO104_2758=viewOVCServed(104);
$districtDataMUBENDE107_2759=viewOVCServed(107);
$districtDataMITYANA437_2760=viewOVCServed(437);
$districtDataNAKASEKE440_2761=viewOVCServed(440);
$districtDataLAMWO451_2762=viewOVCServed(451);
$districtDataAPAC302_2763=viewOVCServed(302);
$districtDataGULU304_2764=viewOVCServed(304);
$districtDataLIRA307_2765=viewOVCServed(307);
$districtDataDOKOLO424_2766=viewOVCServed(424);
$districtDataPADER312_2767=viewOVCServed(312);
$districtDataMASINDI409_2768=viewOVCServed(409);
$districtDataOYAM434_2769=viewOVCServed(434);

//-----------------------------------------------------
$districtDataBUNDIBUGYO401_2770=viewOVCServed(401);
$districtDataHOIMA403_2771=viewOVCServed(403);
$districtDataKABAROLE405_2772=viewOVCServed(405);
$districtDataKYENJOJO415_2773=viewOVCServed(415);
$districtDataKIBAALE407_2774=viewOVCServed(407);
$districtDataBUKWO417_2776=viewOVCServed(417);
$districtDataSIRONKO215_2777=viewOVCServed(215);
$districtDataMBALE209_2778=viewOVCServed(209);
$districtDataMANAFWA425_2779=viewOVCServed(425);
$districtDataBUGIRI201_2780=viewOVCServed(201);
$districtDataBUSIA202_2781=viewOVCServed(202);
$districtDataBUTALEJA423_2782=viewOVCServed(423);
$districtDataMAYUGE_214_2783=viewOVCServed(214);
$districtDataTORORO212_2784=viewOVCServed(214);
$districtDataKole493_7053=viewOVCServed(493);
$districtDataKOTIDO306_2786=viewOVCServed(306);
$districtDataAMURIA428_2787=viewOVCServed(428);
$districtDataKaabong491_2788=viewOVCServed(491);
$districtDataABIM445_2789=viewOVCServed(445);
$districtDataMOROTO308_2790=viewOVCServed(308);
$districtDataNAKAPIRIPIRIT311_2791=viewOVCServed(311);
$districtDataRAKAI110_3381=viewOVCServed(110);
$districtDataMASAKA105_3382=viewOVCServed(105);
$districtDataBUSHENYI402_3383=viewOVCServed(402);
$districtDataKASESE406_3384=viewOVCServed(406);
$districtDataKAMWENGE413_3385=viewOVCServed(413);
$districtDataKITGUM305_7051=viewOVCServed(305);
$districtDataKyankwanzi494_7072=viewOVCServed(494);
$districtDataAgago477_7056=viewOVCServed(477);

$districtDataIBANDA442_3386=viewOVCServed(442);
$districtDataISINGIRO436_3387=viewOVCServed(436);
$districtDataKIRUHURA435_3388=viewOVCServed(435);
$districtDataNTUNGAMO411_3389=viewOVCServed(411);
$districtDataRUKUNGIRI412_3390=viewOVCServed(412);
$districtDataMBARARA410_3391=viewOVCServed(410);

$districtDataKABALE404_3392=viewOVCServed(404);
$districtDataKISORO408_3393=viewOVCServed(408);
$districtDataKANUNGU414_3394=viewOVCServed(414);
$districtDataZOMBO427_7048=viewOVCServed(427);
$districtDataAMURU467_7049=viewOVCServed(467);


$districtDataKiryandongo474_7052=viewOVCServed(474);

$districtDataAlebtong478_7054=viewOVCServed(478);
$districtDataOtuke509_7055=viewOVCServed(509);

$districtDataNapak504_7057=viewOVCServed(504);


$districtDataNtoroko506_7059=viewOVCServed(506);
$districtDataKyegegwa495_7060=viewOVCServed(495);
$districtDataRubirizi511_7061=viewOVCServed(511);
$districtDataMitooma501_7062=viewOVCServed(501);
$districtDataSheema513_7063=viewOVCServed(513);
$districtDataBuhweju481_7064=viewOVCServed(481);

$districtDataLwengo475_7065=viewOVCServed(475);
$districtDataLYANTONDE450_7066=viewOVCServed(450);
$districtDataBukomansimbi482_7067=viewOVCServed(482);
$districtDataKalungu472_7068=viewOVCServed(472);
$districtDataButambala484_7069=viewOVCServed(484);
$districtDataGomba488_7070=viewOVCServed(488);

$districtDataBuvuma485_7074=viewOVCServed(485);

$districtDataLuuka497_7076=viewOVCServed(497);
$districtDataBuyende486_7078=viewOVCServed(486);
$districtDataSerere512_7079=viewOVCServed(512);
$districtDataNgora505_7080=viewOVCServed(505);
$districtDataBUKEDEA421_7081=viewOVCServed(421);
$districtDataBududa448_7086=viewOVCServed(448);
/*
 
       
        
        
        
        
       
        
        
        
        
        
       
        
        
 */
$dynamicData= '[

{
            "hc-key": "ug-3394",
            "value": '.$districtDataKANUNGU414_3394.'
        },
        {
            "hc-key": "ug-2750",
            "value": '.$districtDataNEBBI310_2750.'
        },
{
            "hc-key": "ug-3382",
            "value": '.$districtDataMASAKA105_3382.'
        },
        {
            "hc-key": "ug-3384",
            "value": '.$districtDataKASESE406_3384.'
        },
        {
            "hc-key": "ug-3381",
            "value": '.$districtDataRAKAI110_3381.'
        },
        {
            "hc-key": "ug-3383",
            "value": '.$districtDataBUSHENYI402_3383.'
        },

{
            "hc-key": "ug-7063",
            "value": '.$districtDataSheema513_7063.'
        },
        {
            "hc-key": "ug-7064",
            "value": '.$districtDataBuhweju481_7064.'
        },
        {
            "hc-key": "ug-7086",
            "value": '.$districtDataBududa448_7086.'
        },
        {
            "hc-key": "ug-3390",
            "value": '.$districtDataRUKUNGIRI412_3390.'
        },
 {
            "hc-key": "ug-2766",
            "value": '.$districtDataDOKOLO424_2766.'
        },
        {
            "hc-key": "ug-2765",
            "value": '.$districtDataLIRA307_2765.'
        },
        {
            "hc-key": "ug-2764",
            "value": '.$districtDataGULU304_2764.'
        },{
            "hc-key": "ug-3386",
            "value": '.$districtDataIBANDA442_3386.'
        },
        {
            "hc-key": "ug-3391",
            "value": '.$districtDataMBARARA410_3391.'
        },
        {
            "hc-key": "ug-3392",
            "value": '.$districtDataKABALE404_3392.'
        },

{
            "hc-key": "ug-2744",
            "value": '.$districtDataJINJA204_2744.'
        },
        {
            "hc-key": "ug-7054",
            "value": '.$districtDataAlebtong478_7054.'
        },
        
        {
            "hc-key": "ug-7078",
            "value": '.$districtDataBuyende486_7078.'
        },
        {
            "hc-key": "ug-7060",
            "value": '.$districtDataKyegegwa495_7060.'
        },
        
        {
            "hc-key": "ug-2760",
            "value": '.$districtDataMITYANA437_2760.'
        },
        {
            "hc-key": "ug-2761",
            "value": '.$districtDataNAKASEKE440_2761.'
        },

{
            "hc-key": "ug-7065",
            "value": '.$districtDataLwengo475_7065.'
        },
        {
            "hc-key": "ug-7066",
            "value": '.$districtDataLYANTONDE450_7066.'
        },
        {
            "hc-key": "ug-7069",
            "value": '.$districtDataButambala484_7069.'
        },
        {
            "hc-key": "ug-7061",
            "value": '.$districtDataRubirizi511_7061.'
        },
        {
            "hc-key": "ug-3389",
            "value": '.$districtDataNTUNGAMO411_3389.'
        },
        {
            "hc-key": "ug-7062",
            "value": '.$districtDataMitooma501_7062.'
        },

 {
            "hc-key": "ug-7057",
            "value": '.$districtDataNapak504_7057.'
        },
        {
            "hc-key": "ug-2790",
            "value": '.$districtDataMOROTO308_2790.'
        },
        {
            "hc-key": "ug-2776",
            "value": '.$districtDataBUKWO417_2776.'
        },
        {
            "hc-key": "ug-7067",
            "value": '.$districtDataBukomansimbi482_7067.'
        },


{
            "hc-key": "ug-2787",
            "value": '.$districtDataAMURIA428_2787.'
        },
        {
            "hc-key": "ug-7055",
            "value": '.$districtDataOtuke509_7055.'
        },
        {
            "hc-key": "ug-2769",
            "value": '.$districtDataOYAM434_2769.'
        },
        {
            "hc-key": "ug-7052",
            "value": '.$districtDataKiryandongo474_7052.'
        },
        {
            "hc-key": "ug-2774",
            "value": '.$districtDataKIBAALE407_2774.'
        },
        {
            "hc-key": "ug-7059",
            "value": '.$districtDataNtoroko506_7059.'
        },

  {
            "hc-key": "ug-7048",
            "value": '.$districtDataZOMBO427_7048.'
        },
        {
            "hc-key": "ug-7080",
            "value": '.$districtDataNgora505_7080.'
        },
        {
            "hc-key": "ug-7081",
            "value": '.$districtDataBUKEDEA421_7081.'
        },
        
        {
            "hc-key": "ug-7070",
            "value": '.$districtDataGomba488_7070.'
        },
        {
            "hc-key": "ug-7049",
            "value": '.$districtDataAMURU467_7049.'
        },
        

 {
            "hc-key": "ug-2780",
            "value": '.$districtDataBUGIRI201_2780.'
        },
        {
            "hc-key": "ug-2781",
            "value": '.$districtDataBUSIA202_2781.'
        },
        {
            "hc-key": "ug-2782",
            "value": '.$districtDataBUTALEJA423_2782.'
        },
        {
            "hc-key": "ug-2783",
            "value": '.$districtDataMAYUGE_214_2783.'
        },
        {
            "hc-key": "ug-2779",
            "value": '.$districtDataMANAFWA425_2779.'
        },
        {
            "hc-key": "ug-2784",
            "value": '.$districtDataTORORO212_2784.'
        },

 {
            "hc-key": "ug-7051",
            "value": '.$districtDataKITGUM305_7051.'
        },
        {
            "hc-key": "ug-2762",
            "value": '.$districtDataLAMWO451_2762.'
        },
        {
            "hc-key": "ug-2767",
            "value": '.$districtDataPADER312_2767.'
        },
        {
            "hc-key": "ug-2777",
            "value": '.$districtDataSIRONKO215_2777.'
        },
        {
            "hc-key": "ug-2778",
            "value": '.$districtDataMBALE209_2778.'
        },
{
            "hc-key": "ug-3388",
            "value": '.$districtDataKIRUHURA435_3388.'
        },
        {
            "hc-key": "ug-2786",
            "value": '.$districtDataKOTIDO306_2786.'
        },
        {
            "hc-key": "ug-7056",
            "value": '.$districtDataAgago477_7056.'
        },
        
        {
            "hc-key": "ug-7072",
            "value": '.$districtDataKyankwanzi494_7072.'
        },
        {
            "hc-key": "ug-2759",
            "value": '.$districtDataMUBENDE107_2759.'
        },

{
            "hc-key": "ug-2785",
            "value": '.$districtDataKATAKWI207_2785.'
        },
        {
            "hc-key": "ug-2791",
            "value": '.$districtDataNAKAPIRIPIRIT311_2791.'
        },
        {
            "hc-key": "ug-3385",
            "value": '.$districtDataKAMWENGE413_3385.'
        },

{
            "hc-key": "ug-2768",
            "value": '.$districtDataMASINDI409_2768.'
        },
        {
            "hc-key": "ug-2763",
            "value": '.$districtDataAPAC302_2763.'
        },
        {
            "hc-key": "ug-2748",
            "value": '.$districtDataBULISA449_2748.'
        },
        {
            "hc-key": "ug-2771",
            "value": '.$districtDataHOIMA403_2771.'
        },

{
            "hc-key": "ug-2772",
            "value": '.$districtDataKABAROLE405_2772.'
        },
        
        {
            "hc-key": "ug-2788",
            "value": '.$districtDataKaabong491_2788.'
        },
        {
            "hc-key": "ug-2789",
            "value": '.$districtDataABIM445_2789.'
        },
        {
            "hc-key": "ug-3387",
            "value": '.$districtDataISINGIRO436_3387.'
        },


{
            "hc-key": "ug-3393",
            "value": '.$districtDataKISORO408_3393.'
        },
        {
            "hc-key": "ug-7079",
            "value": '.$districtDataSerere512_7079.'
        },
        {
            "hc-key": "ug-7076",
            "value": '.$districtDataLuuka497_7076.'
        },
        {
            "hc-key": "ug-2746",
            "value": '.$districtDataARUA303_2746.'
        },
        

{
            "hc-key": "ug-2747",
            "value": '.$districtDataKOBOKO432_2747.'
        },
        {
            "hc-key": "ug-2751",
            "value": '.$districtDataMOYO309_2751.'
        },
        {
            "hc-key": "ug-2758",
            "value": '.$districtDataLUWERO104_2758.'
        },
        {
            "hc-key": "ug-2756",
            "value": '.$districtDataNAKASONGOLA109_2756.'
        },
        

{
            "hc-key": "ug-7053",
            "value": '.$districtDataKole493_7053.'
        },
        {
            "hc-key": "ug-2770",
            "value": '.$districtDataBUNDIBUGYO401_2770.'
        },
        {
            "hc-key": "ug-2773",
            "value": '.$districtDataKYENJOJO415_2773.'
        },
        
        {
            "hc-key": "ug-2753",
            "value": '.$districtDataKIBOGA103_2753.'
        },
{
            "hc-key": "ug-2755",
            "value": '.$districtDataMARACHA444_2755.'
        },
        {
            "hc-key": "ug-2745",
            "value": '.$districtDataAdjumani301_2745.'
        },
        {
            "hc-key": "ug-2752",
            "value": '.$districtDataYUMBE313_2752.'
        },
{
            "hc-key": "ug-2754",
            "value": '.$districtDataKAMPALA102_2754.'
        },

 {
            "hc-key": "ug-2757",
            "value": '.$districtDataWAKISO113_2757.'
        }
		,
{
            "hc-key": "ug-7068",
            "value": '.$districtDataKalungu472_7068.'
        },

{
            "hc-key": "ug-1677",
            "value": '.$districtDataKUMI208_1677.'
        },
		
{
            "hc-key": "ug-2595",
 			"value": '.$districtDataKALANGALA101_2595.'
        },
        {
            "hc-key": "ug-7073",
            "value": '.$districtDataBuikwe469_7073.'
        },        {
            "hc-key": "ug-7074",
            "value": '.$districtDataBuvuma485_7074.'
        },
        
		{
            "hc-key": "ug-7084",
            "value": '.$districtDataKween476_7084.'
        },
        {
            "hc-key": "ug-7058",
            "value": '.$districtDataAmudat479_7058.'
        },
        {
            "hc-key": "ug-1678",
            "value": '.$districtDataKABERAMAIDO213_1678.'
        },
 {
            "hc-key": "ug-1682",
            "value": '.$districtDataAMOLATAR433_1682.'
        },
        {
            "hc-key": "ug-1683",
            "value": '.$districtDataKALIRO416_1683.'
        },
        {
            "hc-key": "ug-1685",
            "value": '.$districtDataNAMUTUMBA422_1685.'
        },
 {
            "hc-key": "ug-1684",
            "value": '.$districtDataBUDAKA446_1684.'
        },
		{
            "hc-key": "ug-7083",
            "value": '.$districtDataBulambuli483_7083.'
        },
        {
            "hc-key": "ug-1679",
            "value": '.$districtDataKAYUNGA112_1679.'
        },
        {
            "hc-key": "ug-1680",
            "value": '.$districtDataIGANGA203_1680.'
        },
		{
            "hc-key": "ug-1686",
            "value": '.$districtDataSOROTI211_1686.'
        },
		{
            "hc-key": "ug-1687",
            "value": '.$districtDataugMukono108_1687.'
        },
		{
            "hc-key": "ug-1689",
            "value": '.$districtDataSEMBABULE447_1689.'
        }
		,{
            "hc-key": "ug-2775",
            "value": '.$districtDataKAPCHORWA206_2775.'
        },
		{
            "hc-key": "ug-1681",
            "value": '.$districtDataKAMULI205_1681.'
        },
 {
            "hc-key": "ug-7075",
            "value": '.$districtDataNamayingo503_7075.'
        }
		,

        {
            "hc-key": "ug-7082",
            "value": '.$districtDataKibuku492_7082.'
        },
        {
            "hc-key": "ug-1688",
            "value": '.$districtDataPALLISA210_1688.'
        }
		
		]';


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



