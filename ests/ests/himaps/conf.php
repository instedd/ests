<?php
/*@filename conf.php
 *@date 2011-06-20 Mon
 *@author Noah Nambale [namnoah@gmail.com]
 *TODO
 *database general settings
 *
 */

	
if(!defined("DB_TYPE"))
	define("DB_TYPE","mysql");
	
if(!defined("DB_REPORTLINKS"))
	define("DB_REPORTLINKS","16");
	

if(!defined("DB_SERVER"))
	define("DB_SERVER","localhost");
if(!defined("DB_USER"))
	define("DB_USER","root");

if(!defined("DB_PWD"))
	define("DB_PWD","craiwrut");

if(!defined("DB_NAME"));
	define("DB_NAME","db_ovcmis_me_v3");
	#define("DB_NAME","mglsd_ovcmisnssppi2_demoB");

if(!defined("DB_PORT"))
	define("DB_PORT","3306");
if(!defined("CLIENTNAME"))
	define("CLIENTNAME","Ministry of Gender Labor And Social Development;OVCMIS2:");

//$cnx = pg_connect("host=".$dbHost." user=".$dbUser." password=".$dbPWD." dbname=".$dbName);

/**
 * Cookie Constants - these are the parameters
 * to the setcookie function call.
 */
define("COOKIE_EXPIRE", 60*60*24*100);  //100 days by default
define("COOKIE_PATH", "/");  //Avaible in whole domain

/**
* some constants for user levels
*
*/
//------------------------------------------------
if(!defined("ADMIN_NAME"))
define("ADMIN_NAME", "admin");
if(!defined("BOOK_ADMIN_NAME"))
define("BOOK_ADMIN_NAME", "admin book");
if(!defined("SUPER_ADMIN_NAME"))
define("SUPER_ADMIN_NAME", "super admin");
if(!defined("GUEST_NAME"))
define("GUEST_NAME", "Guest");
if(!defined("USER_NAME"))
define("USER_NAME", "shop users");
//----------------------------------------------
if(!defined("ADMIN_LEVEL"))
define("ADMIN_LEVEL", 1);
if(!defined("USER_LEVEL"))
define("USER_LEVEL",  2);
if(!defined("SUPER_ADMIN_LEVEL"))
define("SUPER_ADMIN_LEVEL", 3);
if(!defined("BOOK_ADMIN_LEVEL"))
define("BOOK_ADMIN_LEVEL", 4);
//---------------------------------------------
//function to connect to database		
	function connect(){
		$conn = mysql_connect(DB_SERVER,DB_USER,DB_PWD);
		$db = mysql_select_db(DB_NAME,$conn);		 
		if($conn){
			return TRUE;
		} else{
			throw new Exception("Database Connection Failure");
		}
	}
		
	
	
	function secureInput($var)
	{
			
		$output = '';
		if (is_array($var)){
			foreach($var as $key=>$val){
				$output[$key] = secureInput($val);
			}
		} else {
			$var = strip_tags(trim($var));
			if (function_exists("get_magic_quotes_gpc")) {
				$output = mysql_escape_string(get_magic_quotes_gpc() ? stripslashes($var) : $var);
			} else {
				$output = mysql_escape_string($var);
			}
		}
		if (!empty($output))
		return $output;
	}
	
	
	/* function disconnect(){
		pg_close( pg_connect("host=".dbHost." user=".dbUser." password=".dbPWD." dbname=".dbName. " port=".DB_PORT ));	
	}*/


?>
