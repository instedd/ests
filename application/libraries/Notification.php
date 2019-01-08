<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Notification{

	public $CI;

	public function __construct(){
		$this->CI =& get_instance();
	}

	public function notify($message,$user_id)
	{
         $result =$this->CI->db->insert('tbl_notifications',array('message'=>$message,'created_by'=>$user_id));
				
	}
      function get_all_notifications($id)
    {
        $append_id=($id!=='')?'and id='.$id.'':'';
        $this->CI->db->select("* FROM `tbl_notifications` 
        WHERE 1
         " . $append_id . " 
          order by `id` desc LIMIT 10", FALSE);
        $db_rows = $this->CI->db->get();
        if ($db_rows->num_rows() > 0) {
            foreach ($db_rows->result() as $data) {
                $db_data_fetched_array[] = $data;
            }
            return $db_data_fetched_array;
        }

    }
}
?>