<?php
//require_once('lib_sunrise.php');

class MapManager{
 var $query;
 var $role;
function MapManager($query){
$this->query;
}

 function ViewOVCRServed($quarter,$fyear,$indicator,$district){
 //SELECT count(distinct(districtCode)) as d FROM `tbl_organizationreporting` where`year`='2014/2015' and `reportingPeriod`='Jan - Mar'
 $x=0;
		 $select="select districtCode,sum(total) as totalOVCServed
		  from tbl_organizationreporting 
		 where reportingPeriod like '".$quarter."%' 
		 and year='".$fyear."' 
		 and indicator_id='".$indicator."'
		 and districtCode='".$district."'
		 group by districtCode
		 order by districtCode asc ";
 $Query=@mysql_query($select) or die(mysql_error());
 $Results=@mysql_fetch_array($Query);
 
 return $x=$Results['totalOVCServed'];
 }
 
 
 

}

?>