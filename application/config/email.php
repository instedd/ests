<?php
defined('BASEPATH') OR exit('No direct script access allowed');
$config['protocol'] = 'smtp';
$config['smtp_host'] = 'ssl://smtp.gmail.com'; 
$config['smtp_port'] = 465;
$config['smtp_user'] = 'sampletracker18@gmail.com';
$config['smtp_pass'] = 'Pass!!2018';
$config['_smtp_auth']=TRUE;
$config['smtp_timeout'] = 100;
$config['mailtype'] = 'html';
$config['crlf'] = "\r\n";
$config['newline'] = "\r\n";
$config['wordwrap'] = TRUE;

/*$config['protocol'] = 'smtp';
$config['smtp_host'] = 'cinnamonclubs.com'; 
$config['smtp_port'] = 587;
$config['smtp_user'] = 'medard@cinnamonclubs.com';
$config['smtp_pass'] = 'med@ug90';
$config['_smtp_auth']=TRUE;
$config['smtp_crypto']='tls';
$config['smtp_timeout'] = 100;
$config['crlf'] = "\r\n";
$config['newline'] = "\r\n";*/
                        