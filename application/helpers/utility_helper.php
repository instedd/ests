<?php
/**
 * Created by PhpStorm.
 * User: aasiimwe
 * Date: 11/4/2015
 * Time: 11:05 PM
 */
if (!defined('BASEPATH')) exit('No direct script access allowed');
if (!function_exists('encryptor()')) {
function encryptor($action, $string) 
{
    $output = false;

    $encrypt_method = "AES-256-CBC";
    //pls set your unique hashing key
    $secret_key = 'medix';
    $secret_iv = '779218977';

    // hash
    $key = hash('sha256', $secret_key);

    // iv - encrypt method AES-256-CBC expects 16 bytes - else you will get a warning
    $iv = substr(hash('sha256', $secret_iv), 0, 16);

    //do the encyption given text/string/number
    if( $action == 'encrypt' ) {
        $output = openssl_encrypt($string, $encrypt_method, $key, 0, $iv);
        $output = base64_encode($output);
    }
    else if( $action == 'decrypt' ){
        //decrypt the given text/string/number
        $output = openssl_decrypt(base64_decode($string), $encrypt_method, $key, 0, $iv);
    }

    return $output;
 }
}
if (!function_exists('readNumber()')) {
    function readNumber($num, $depth = 0)
    {
        $num = (int)$num;
        $retval = "";
        if ($num < 0) // if it's any other negative, just flip it and call again
            return "negative " + readNumber(-$num, 0);
        if ($num > 99) // 100 and above
        {
            if ($num > 999) // 1000 and higher
                $retval .= readNumber($num / 1000, $depth + 3);

            $num %= 1000; // now we just need the last three digits
            if ($num > 99) // as long as the first digit is not zero
                $retval .= readNumber($num / 100, 2) . " hundred\n";
            $retval .= readNumber($num % 100, 1); // our last two digits
        } else // from 0 to 99
        {
            $mod = floor($num / 10);
            if ($mod == 0) // ones place
            {
                if ($num == 1) $retval .= "one";
                else if ($num == 2) $retval .= "two";
                else if ($num == 3) $retval .= "three";
                else if ($num == 4) $retval .= "four";
                else if ($num == 5) $retval .= "five";
                else if ($num == 6) $retval .= "six";
                else if ($num == 7) $retval .= "seven";
                else if ($num == 8) $retval .= "eight";
                else if ($num == 9) $retval .= "nine";
            } else if ($mod == 1) // if there's a one in the ten's place
            {
                if ($num == 10) $retval .= "ten";
                else if ($num == 11) $retval .= "eleven";
                else if ($num == 12) $retval .= "twelve";
                else if ($num == 13) $retval .= "thirteen";
                else if ($num == 14) $retval .= "fourteen";
                else if ($num == 15) $retval .= "fifteen";
                else if ($num == 16) $retval .= "sixteen";
                else if ($num == 17) $retval .= "seventeen";
                else if ($num == 18) $retval .= "eighteen";
                else if ($num == 19) $retval .= "nineteen";
            } else // if there's a different number in the ten's place
            {
                if ($mod == 2) $retval .= "twenty ";
                else if ($mod == 3) $retval .= "thirty ";
                else if ($mod == 4) $retval .= "forty ";
                else if ($mod == 5) $retval .= "fifty ";
                else if ($mod == 6) $retval .= "sixty ";
                else if ($mod == 7) $retval .= "seventy ";
                else if ($mod == 8) $retval .= "eighty ";
                else if ($mod == 9) $retval .= "ninety ";
                if (($num % 10) != 0) {
                    $retval = rtrim($retval); //get rid of space at end
                    $retval .= "-";
                }
                $retval .= readNumber($num % 10, 0);
            }
        }

        if ($num != 0) {
            if ($depth == 3)
                $retval .= " thousand\n";
            else if ($depth == 6)
                $retval .= " million\n";
            if ($depth == 9)
                $retval .= " billion\n";
        }
        return $retval;
    }

}

//append superpositions
if (!function_exists('appendSuperpositionInteger()')) {
    function appendSuperpositionInteger($string)
    {
        $lastDigitInString = mb_substr($string, -1);
        $sup = '';
        switch ($lastDigitInString) {
            case 0:
                $sup = $string . '<sup>th</sup>';
                break;
            case 1:
                $sup = $string . '<sup>st</sup>';
                break;
            case 2:
                $sup = $string . '<sup>nd</sup>';
                break;
            case 3:
                $sup = $string . '<sup>rd</sup>';
                break;
            case 4:
                $sup = $string . '<sup>th</sup>';
                break;
            case 5:
                $sup = $string . '<sup>th</sup>';
                break;
            case 6:
                $sup = $string . '<sup>th</sup>';
                break;
            case 7:
                $sup = $string . '<sup>th</sup>';
                break;
            case 8:
                $sup = $string . '<sup>th</sup>';
                break;
            case 9:
                $sup = $string . '<sup>th</sup>';
                break;

            default:
                break;
        }
        return $sup;
    }
}


if (!function_exists('largeRoundOff()')) {
    function largeRoundOff($var)
    {
        $val = rtrim(rtrim(sprintf('%.8F', $var), '0'), ".");
        return $val;
    }

}
if (!function_exists('secsToStr()')) {
function secsToStr($secs) { 
    $r=''; 
    if($secs>=86400){$days=floor($secs/86400);$secs=$secs%86400;$r=$days.' day';if($days<>1){$r.='s';}if($secs>0){$r.=', ';}}  
    if($secs>=3600){$hours=floor($secs/3600);$secs=$secs%3600;$r.=$hours.' hour';if($hours<>1){$r.='s';}if($secs>0){$r.=', ';}}  
    if($secs>=60){$minutes=floor($secs/60);$secs=$secs%60;$r.=$minutes.' minute';if($minutes<>1){$r.='s';}if($secs>0){$r.=', ';}}  
    $r.=$secs.' second';if($secs<>1){$r.='s';}  
    return $r;  
} 
} 
if (!function_exists('cleanDivision()')) {
    function cleanDivision($a, $b)
    {
        if ($b === 0)
            return '-';

        return $a / $b;
    }

}


if (!function_exists('create_username()')) {
    function create_username($string)

    {
        $words_last_name = explode(" ", $string);
        $single_letter_from_last_name = substr($words_last_name[1], 0, 1);
        list($firstWord) = explode(' ', $string);
        return strtolower($single_letter_from_last_name . $firstWord);
    }

}

if (!function_exists('fixtags()')) {
    function fixtags($text)
    {
        $text = htmlspecialchars($text);
        $text = preg_replace("/=/", "=\"\"", $text);
        $text = preg_replace("/&quot;/", "&quot;\"", $text);
        $tags = "/&lt;(\/|)(\w*)(\ |)(\w*)([\\\=]*)(?|(\")\"&quot;\"|)(?|(.*)?&quot;(\")|)([\ ]?)(\/|)&gt;/i";
        $replacement = "<$1$2$3$4$5$6$7$8$9$10>";
        $text = preg_replace($tags, $replacement, $text);
        $text = preg_replace("/=\"\"/", "=", $text);
        return $text;
    }

}


if (!function_exists('get_string_part()')) {
    function get_string_part($string, $starting_part, $ending_part)
    {
        $string = " " . $string;
        $initial = strpos($string, $starting_part);
        if ($initial == 0) return "";
        $initial += strlen($starting_part);
        $length = strpos($string, $ending_part, $initial) - $initial;
        return substr($string, $initial, $length);
    }
}

if (!function_exists('replace_img_src()')) {
    function replace_img_src($img_tag, $sp_type)
    {
        $doc = new DOMDocument();
        $doc->loadHTML($img_tag);
        $tags = $doc->getElementsByTagName('img');
        foreach ($tags as $tag) {
            $start_str = ":";
            $end_str = "@";
            $old_src = $tag->getAttribute('src');
            $old_src_cleaned = get_string_part($old_src, $start_str, $end_str);
            $new_src_url = base_url() . 'assets/images/' . $sp_type . '/' . $old_src_cleaned;
            $tag->setAttribute('src', $new_src_url);
        }
        return $doc->saveHTML();
    }
}


if (!function_exists('getAge()')) {
    function getAge($dob) {
        //calculate years of age (input string: YYYY-MM-DD)
        list($year, $month, $day) = explode("-", $dob);

        $year_diff  = date("Y") - $year;
        $month_diff = date("m") - $month;
        $day_diff   = date("d") - $day;

        // if we are any month before the birthdate: year - 1
        // OR if we are in the month of birth but on a day
        // before the actual birth day: year - 1
        if ( ($month_diff < 0 ) || ($month_diff === 0 && $day_diff < 0))
            $year_diff--;

        return $year_diff;
    }
}


if (!function_exists('clean_api_narration()')) {
    function clean_api_narration($str)
    {
        //maximum allowable characters is 30
        switch (true) {
            case (strlen($str) > 27):
                $new_string = substr($str, 0, 27) . '...';
                break;

            default:
                $new_string = $str;
                break;
        }
        return $new_string;
    }
}


if (!function_exists('get_date_18yrs_ago()')) {
    function get_date_18yrs_ago($selected_date)
    {
        $selected_date = new DateTime($selected_date);
        $selected_date->modify('-18 years');
        $final_date = strtoupper($selected_date->format('d-m-Y'));
        return $final_date;
    }


}

if (!function_exists('get_notification_type()')) {
    function get_notification_type($type)
    {
        switch (true) {
            case($type == 'email'):
                return $notification_type = 'email';
                break;
            case($type == 'sms'):
                return $notification_type = 'sms';
                break;
            default:
                return $notification_type = 'email';
                break;

        }
    }
}

if (!function_exists('clean_date()')) {
    function clean_date($str)
    {
        //maximum allowable characters is 30

        return date('Y-m-d', strtotime($str));
    }
}
if (!function_exists('clean_report_date()')) {
    function clean_report_date($str)
    {
        //maximum allowable characters is 30

        return date('d-m-Y', strtotime($str));
    }
}

if (!function_exists('stripQuotes()')) {
    function stripQuotes($text)
    {
        $unquoted = preg_replace('/^(\'(.*)\'|"(.*)")$/', '$2$3', $text);
        return $unquoted;
    }
}
if (!function_exists('remove_trailing_comma()')) {
    function remove_trailing_comma($string)
    {
        $string = trim($string);
        if (substr($string, -1, 1) == ',') {
            $clean_string = substr($string, 0, -1);

        }
        return $clean_string;
    }
}

if (!function_exists('cud_role_check()')) {
    function cud_role_check($user_role)
    {
        $bool_var = (!in_array($user_role, explode(",", default_admin_roles))) ? 0 : 1;
        return $bool_var;
    }
}

if (!function_exists('display_30_max()')) {
    function display_30_max($str)
    {
        $extras = '...';
        $num_chars = strlen($str);
        switch (true) {
            case($num_chars >= 30):
                $str_to_30 = substr($str, 0, 30);
                $clean_string = substr($str_to_30, 0, strripos($str_to_30, ' ')) . $extras;
                break;

            default:
                $clean_string = $str;
                break;

        }

        return $clean_string;
    }
}

//str_replace('_', ' ', $input_string);

if (!function_exists('clean_menu_item_name()')) {
    function clean_menu_item_name($str)
    {
        $clean_string = ucfirst(strtolower(str_replace('_', ' ', $str)));

        return $clean_string;
    }
}

if (!function_exists('get_first_letter()')) {
    function get_first_letter($str)
    {
        $result=strtoupper(strtolower(substr($str, 0, 1)));
        return $result;
    }
}