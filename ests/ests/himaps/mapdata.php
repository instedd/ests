 <?php

@mysql_select_db("db_ovcmis_me_v3",mysql_connect("localhost","root","craiwrut"))or die(@mysql_error());

 $select="select d.districtCode,d.districtName,d.shapeFile
		  from tbl_district d  
		 where d.entryType like 'District%' 
		 group by d.districtCode
		 order by d.districtName asc limit 0,3 ";
 $Query=@mysql_query($select) or die(mysql_error());
 
//==========Map Data customizations-------------------------------------------
$count=0;
$districtData=0;
 $dynamicData= '[<br>';
 while($row=mysql_fetch_array($Query)){
 
 
 
 
  $select1="select districtCode,sum(total) as totalOVCServed
		  from tbl_organizationreporting 
		 where reportingPeriod like 'Jul - Sep%' 
		 and year='2015/2016' 
		 and indicator_id='251'
		 and districtCode='".$row['districtCode']."'
		 group by districtCode
		 order by districtCode asc ";
 $Query1=@mysql_query($select1) or die(mysql_error());
 $Results=@mysql_fetch_array($Query1);

 $districtData=$Results['totalOVCServed']==''?0:$Results['totalOVCServed'];
 
 $count1=$count<111?'':'';
  $dynamicData.='{<br>
            "hc-key": "'.$row['shapeFile'].'",<br>
            "value": '.$districtData.'
        <br>}'.$count1."<br>";
		$count++;
 }
		$dynamicData.=']';

echo $dynamicData;
//-------------------------------end of data customizations----------------------------------------------



?>
 