<?php
/**
 * Created by PhpStorm.
 * User: aasiimwe
 * Date: 11/4/2015
 * Time: 11:05 PM
 */
if (!defined('BASEPATH')) exit('No direct script access allowed');

if (!function_exists('notification_template()')) {
    function notification_template($title,$name, $msg_main, $jbc_logo)
    {

        $html = '
        <table border="0" cellspacing="0" cellpadding="0" width="100%"
       style="background-color: #CCCCCC; font-family:Arial,sans-serif;line-height:20px;color:#444444;font-size:13px">

    <tr>
        <td>
            <div align="center">
                <table border="0" cellspacing="0" cellpadding="0" width="100%">
                    <tr>
                        <td valign="bottom"><p>&nbsp;</p></td>
                        <td width="640">
                            <div align="center">
                                <table border="0" style="border: 10px #E4E6E9; margin-top: 2px;" cellspacing="0"
                                       cellpadding="0" height="100%">
                                    <tr>
                                        <td>
                                            <table border="0" cellspacing="0" cellpadding="0" width="100%" height="18">
                                                <tr>
                                                    <td width="100%" valign="top">
                                                        <div align="center">
                                                            <table border="0" cellpadding="0"
                                                                   style="background-color: #FFFFFF;">
                                                                <tr>
                                                                    <td width="458">
                                                                        <div align="center">
                                                                            <table border="0" cellspacing="0"
                                                                                   cellpadding="0" width="450">
                                                                                <tr>
                                                                                    <td valign="top">
                                                                                        <table class="header-row"
                                                                                               width="378"
                                                                                               cellspacing="0"
                                                                                               cellpadding="0"
                                                                                               border="0"
                                                                                               style="table-layout: fixed;">
                                                                                            <tbody>
                                                                                            <tr>
                                                                                                <td class="header-row-td"
                                                                                                    width="378"
                                                                                                    style="font-family: Arial, sans-serif; font-weight: bolder; line-height: 19px; color: #478fca; margin: 0px; font-size: 18px; padding-bottom: 10px; padding-top: 15px;"
                                                                                                    valign="top"
                                                                                                    align="center">
                                                                                                    JBC<br/>
                                                                                                    <i>' . $title[0] . '</i><br>
                                                                                                    <span style="font-size:16px;">' . $title[1] . '</span>

                                                                                                </td>
                                                                                            </tr>
                                                                                            </tbody>
                                                                                        </table>
                                                                                        <div style="font-family: Arial, sans-serif; color:#01383C!important; line-height: 20px; font-size: 13px;">
                                                                                            <b style="color: #777777;">Dear
                                                                                                ' . $name . '</b>
                                                                                            <br>
                                                                                            <div id="msg_main">' . $msg_main . '</div>
                                                                                        </div>
                                                                                    </td>
                                                                                </tr>
                                                                            </table>
                                                                        </div>
                                                                        <div align="center">
                                                                            <table border="0" cellspacing="0"
                                                                                   cellpadding="0" width="450"
                                                                                   height="12">
                                                                                <tr>
                                                                                    <td width="450">&nbsp;</td>
                                                                                </tr>
                                                                            </table>
                                                                        </div>
                                                                        <div align="center">
                                                                            <table border="0" cellspacing="0"
                                                                                   cellpadding="0" width="450"
                                                                                   height="12">
                                                                                <tr>

                                                                                </tr>
                                                                            </table>
                                                                        </div>
                                                                        <div align="center">
                                                                            <table border="0" cellspacing="0"
                                                                                   cellpadding="0" width="450"
                                                                                   height="12">
                                                                                <tr>
                                                                                    <td width="450">&nbsp;</td>
                                                                                </tr>
                                                                            </table>
                                                                        </div>
                                                                        <div align="center">
                                                                            <table border="0" cellspacing="0"
                                                                                   cellpadding="0" width="450"
                                                                                   height="12">
                                                                                <tr>
                                                                                    <td width="450">&nbsp;</td>
                                                                                </tr>
                                                                            </table>
                                                                        </div>

                                                                        <div align="center">
                                                                            <table border="0" cellspacing="0"
                                                                                   cellpadding="0" width="450"
                                                                                   height="6">
                                                                                <tr>
                                                                                    <td width="450" align="center">
                                                                                            <img  src="cid:' . trim($jbc_logo) . '"    alt="JBC Logo">                                                                                    </td>
                                                                                </tr>
                                                                            </table>
                                                                        </div>
                                                                        <div align="center">
                                                                            <table border="0" cellspacing="0"
                                                                                   cellpadding="0" width="450"
                                                                                   height="12">
                                                                                <tr>
                                                                                    <td width="450">&nbsp;</td>
                                                                                </tr>
                                                                            </table>
                                                                        </div>
                                                                    </td>
                                                                </tr>
                                                            </table>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                        <td valign="bottom"><p>&nbsp;</p></td>
                    </tr>
                </table>
            </div>
        </td>
    </tr>
    <tr>
        <td>
            <div align="center">
                <table border="0" cellspacing="0" cellpadding="0" width="450" height="12">
                    <tr>
                        <td width="450">&nbsp;</td>
                    </tr>
                </table>
            </div>
            <div align="center">
                <table border="0" cellspacing="0" cellpadding="0" width="100%">
                    <tr>
                        <td valign="bottom"><p>&nbsp;</p></td>
                        <td width="640">
                            <div align="center">
                                <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                    <tr>
                                        <td>
                                            <div align="center">
                                                <table border="0" cellspacing="0" cellpadding="0" width="100%">
                                                    <tr>
                                                        <td><p align="center" class="main-footer"
                                                               style="color: #444;border-top: 1px solid #d2d6de;text-align: center;">
                                                            <strong>Copyright &copy; ' . date('Y') . ' <a href="' . site_url('FmLogin/index') . '">
                                                             Junior BabySitting Center </a>.</strong> All rights reserved
                                                        </p></td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                        <td valign="bottom"><p>&nbsp;</p></td>
                    </tr>
                </table>
            </div>
            <div align="center">
                <table border="0" cellspacing="0" cellpadding="0" width="450" height="12">
                    <tr>
                        <td width="450">&nbsp;</td>
                    </tr>
                </table>
            </div>
            <div align="center">
                <table border="0" cellspacing="0" cellpadding="0" width="450" height="12">
                    <tr>
                        <td width="450">&nbsp;</td>
                    </tr>
                </table>
            </div>
        </td>
    </tr>
</table>

        ';

        return $html;
    }

}

