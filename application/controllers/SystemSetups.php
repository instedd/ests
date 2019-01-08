<?php
/**occurred*/

if (!defined('BASEPATH')) exit('No direct script access allowed');

class SystemSetups extends CI_Controller
{

    public function __construct()
    {
        header('Access-Control-Allow-Origin: *');
        header("Access-Control-Allow-Methods: GET, POST, OPTIONS, PUT, DELETE");
        parent::__construct();
        $this->load->library(array('session', 'form_validation', 'email', 'pagination'));
        $this->load->helper(array('utility', 'html', 'url', 'form', 'notification'));
        $this->load->database();
        $this->load->model('Lookups_model');
        $this->load->model('Setups_model');
        $this->load->model(array('Reverselookups_model','login_model'));
        $this->load->database();
    }

    public function page_session_expiry($fmId)
    {
        if (!isset($fmId)) {
            $this->session->set_flashdata('login_error_msg', '
<div class="alert alert-danger text-center fadeIn" role="alert" style="color:#BD000A;">Your Session expired, kindly login again!</div>
');
            redirect('FmLogin/index');
        }
    }

    public function protected_page()
    {
        $protection_status = (!isset($this->session->userdata['user_name'])
            and !isset($this->session->userdata['user_id'])) ? 0 : 1;

        return ($protection_status);
    }

    public function update_insert($table, $table_data)
    {
        $update = '';
        $separator = '';
        foreach ($table_data as $key => $value) {
            $update .= $separator . " `$key` = '$value' ";
            $separator = ',';
        }
        $sql = $this->db->insert_string($table, $table_data) . ' ON DUPLICATE KEY UPDATE ' . $update;
        $this->db->query($sql);
        return $this->db->insert_id();
    }


    public function system_users()
    {
        @$this->page_session_expiry($this->session->userdata['fm_id']);
        $fm_id = $this->session->userdata['fm_id'];
        $current_method = $this->router->fetch_method();
        $current_class = $this->router->fetch_class();
        $fm_name = $this->Setups_model->get_fm_name($fm_id);

        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($current_method),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,

            'user_roles' => $this->Setups_model->get_user_roles(),
            'countries' => $this->Lookups_model->get_countries(),
            'boolean_response' => $this->Lookups_model->get_boolean_response(),

        );

        $config = array();
        $config["base_url"] = base_url() . '/' . $current_class . '/' . $current_method . '';
        $config["total_rows"] = $this->Setups_model->record_count_get_all_system_users();
        $config['per_page'] = "50";
        $config["uri_segment"] = 3;
        $choice = $config["total_rows"] / $config['per_page'];
        $config["num_links"] = floor($choice);
        //config for bootstrap pagination class integration
        $config['full_tag_open'] = '<ul class="pagination">';
        $config['full_tag_close'] = '</ul>';
        $config['first_link'] = false;
        $config['last_link'] = false;
        $config['first_tag_open'] = '<li>';
        $config['first_tag_close'] = '</li>';
        $config['prev_link'] = '&laquo';
        $config['prev_tag_open'] = '<li class="prev">';
        $config['prev_tag_close'] = '</li>';
        $config['next_link'] = '&raquo';
        $config['next_tag_open'] = '<li>';
        $config['next_tag_close'] = '</li>';
        $config['last_tag_open'] = '<li>';
        $config['last_tag_close'] = '</li>';
        $config['cur_tag_open'] = '<li class="active"><a href="#">';
        $config['cur_tag_close'] = '</a></li>';
        $config['num_tag_open'] = '<li>';
        $config['num_tag_close'] = '</li>';
        $this->pagination->initialize($config);
        $data['page'] = (($this->uri->segment(3)) !== '') ? $this->uri->segment(3) : 1;
        $data['data_system_users'] = $this->Setups_model->get_all_system_users($config['per_page'], $data['page']);
        $data['pagination'] = $this->pagination->create_links();


        $this->load->view('header', $data);
        $this->load->view('system_setups/system_users', $data);
        $this->load->view('footer', $data);


    }

    public function add_user()
    {
        $current_class = $this->router->fetch_class();
        $s = substr(str_shuffle(str_repeat(defaultRandomStringArray, 6)), 0, 6);
        $full_name = (trim($this->input->post('first_name')) . ' ' . trim($this->input->post('last_name')));
        $username = create_username($full_name);
        $role_status = ($this->input->post('role_status') == 1) ? 'active' : 'deactivated';
        $query = $this->db->query("SELECT username FROM tbl_system_users
                               WHERE username = '" . $username . "'");
        if ($query->num_rows() == 0) {
            $insert_users = array(
                'tbl_system_user_id' => null,
                'fullNames' => $full_name,
                'username' => $username,
                'password' => md5($this->input->post('user_password')),
                'EncryptionHint' => $s . $this->input->post('user_password'),
                'role_id' => $this->input->post('role_name'),
                'country' => $this->input->post('user_country'),
                'email' => $this->input->post('user_email'),
                'tel_mobile' => $this->input->post('user_tel'),
                'status' => $role_status,
                'display' => 'Yes',
                'emailStatus' => 'Sent',
                'passwordReset' => 'No',
                'ResetConfirmed' => 'No',
                'date_entry' => date('Y-m-d H:i:s'),

            );
            $set = '123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ';
            $password = substr(str_shuffle($set), 0, 6);
            /*send email for verification*/
            $message = '
                    <div style="font-family:Arial,sans-serif;line-height:20px;color:#444444;font-size:13px">
                    <p><b style="color:#777777">Dear
                        ' . $full_name . '</b> <br>
                        An account has been created for you in :<b>' . projectName . '</b> , an  Investment Fund Management Software.<br/>
                        To access the system please click the link below:<br/></p>
                        <p><a href="' . site_url('FmLogin/index/') . '"
                           style="color:#ffffff;text-decoration:none;margin:0px;text-align:center;vertical-align:baseline;border:4px solid #6fb3e0;padding:4px 9px;font-size:15px;line-height:21px;background-color:#6fb3e0"
                           target="_blank">Go to your account</a>
                    </p>
                    <p style="color:#777777">
                    <h4><u></u></h4>
                    User Name:<b>' . $username . '</b><br/>
                    User Password:<b>' . $password. '</b><br/>
                    </p>
                    <p>
                    Please feel free to contact us at <a href="mailto:mis.fundmanager17@gmail.com?subject=FundManagersMISFeedback&cc=apollo.aasiimwe@gmail.com">mis.fundmanager17@gmail.com</a> in case of any queries.<br/>
                    or call <b>+256 783 961426</b>, <b>+256 7723 06944</b>
                    </p>
                    </div>';

              $this->email->from("medardnduhukire@gmail.com","Sample Tracker");
              $this->email->bcc("medardnduhukire@gmail.com"); 
              $this->email->to($email);
              $this->email->subject("Account Creation");
              $this->email->message($message);

            if($this->email->send()){

            $this->db->insert('tbl_system_users', $insert_users);
            $this->session->set_flashdata('user_add_success_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>New User Added successfully. Login credentials have been sent to his email</p></div>
								');
            redirect('' . $current_class . '/system_users');
            }else{
                $this->session->set_flashdata('user_add_success_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>New User Added. Error occured while sending Login credentials to email address</p></div>
                                ');
            redirect('' . $current_class . '/system_users');
            }
        } else {
            $this->session->set_flashdata('msg_md_admin_accounts_create', '
                    <div class="col-sm-offset-1 col-sm-10 alert alert-danger text-center fadeIn" style="color:#2d0b09;" role="alert">
                    A user with similar credentials already exists!!
                    </div>');
            redirect('' . $current_class . '/system_users');
        }


    }

    public function load_edit_user_modal()
    {
        $html = '';
        $user_id = $this->input->post('record_id');
        $update_user_id = $this->session->userdata['user_id'];
        $user_record = $this->Setups_model->get_user_record($user_id);
        $user_name = $this->Setups_model->get_user_name($user_id);
        $drop_down_roles = $this->Setups_model->get_user_roles();
        $boolean_response = $this->Lookups_model->get_boolean_response();
        $drop_down_countries = $this->Setups_model->get_country_residence();
        $current_class = $this->router->fetch_class();

        $html .= '
<div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header modal-header-primary">
                <button type="button" class="btn btn-rounded close" data-dismiss="modal" aria-hidden="true"><i
                        class="fa fa-times-circle" aria-hidden="true"></i></button>
                <h3><i class="fa fa-pencil-square-o m-r-5"></i>Update:<b class="label-danger">' . $user_name . '</b></h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12">';

        $attributes = array(
            "class" => "",
            "id" => "edit_user_form",
            "name" => "edit_user_form",
            "role" => "form", "data-toggle" => "validator"
        );
        $html .= form_open_multipart("Setups/edit_user", $attributes);
        foreach ($user_record as $row) {
            $html .= '<fieldset>
                            <div class="col-md-6 form-group">
                                <label class="control-label" for="role_name">Full Names</label>
                                <input name="full_names" id="full_names"
                                       type="text" placeholder="Full Names"
                                       class="form-control"
                                       value="' . $row->fullNames . '" required/>
                                        <span class="text-danger help-block small with-errors">
                                            ' . form_error('full_names') . '
                                        </span>
                            </div>



                            <div class="col-md-6 form-group">
                                <label class="control-label" for="user_password">Password</label>
                                <input name="user_password" id="user_password"
                                       type="password" placeholder="User Password"
                                       class="form-control"
                                       value="' . set_value('user_password') . '" required/>
                                        <span class="text-danger help-block small with-errors">
                                            ' . form_error('user_password') . '
                                        </span>
                            </div>

                            <div class="col-md-6 form-group">
                                <label class="control-label" for="role_name">Role</label>';
            $attrib_role = 'class = " form-control" name="role_name" id = "role_name" required';
            $html .= form_dropdown('role_name', $drop_down_roles, $row->role_id, $attrib_role);
            $html .= '<span class="text-danger help-block small with-errors">
                                    ' . form_error('role_name') . '
                                </span>
                            </div>

                            <div class="col-md-6 form-group">
                                <label class="control-label" for="user_country">Country</label>';
            $attrib_user_country = 'class = " form-control" name="user_country" id = "user_country" required';
            $html .= form_dropdown('user_country', $drop_down_countries, $row->country, $attrib_user_country);
            $html .= '<span
                                    class="text-danger help-block small with-errors">' . form_error('user_country') . '
                                </span>
                            </div>

                            <div class="col-md-6 form-group">
                                <label class="control-label" for="user_email">Email</label>
                                <input name="user_email" id="user_email"
                                       type="email" placeholder="User Email"
                                       class="form-control"
                                       value="' . $row->email . '" required/>
                                        <span class="text-danger help-block small with-errors">
                                            ' . form_error('user_email') . '
                                        </span>
                            </div>

                            <div class="col-md-6 form-group">
                                <label class="control-label" for="user_tel">Telephone(Format:+256779826311):</label>
                                <input type="tel" pattern="[\+]\d{3}\d{9}" class="form-control"
                                       name="user_tel" id="user_tel"
                                       maxlength="13"
                                       placeholder="User Telephone Number"
                                       value="' . $row->tel_mobile . '" required>
                                        <span class="text-danger help-block small with-errors">
                                            ' . form_error('user_tel') . '
                                        </span>
                            </div>

                            <div class="col-md-6 form-group">
                                <label class="control-label" for="role_status">Activate User</label>';
            $attrib_status = 'class = " form-control"
                                            name="role_status" id = "role_status"
                                            required';
            $html .= form_dropdown('role_status',
                $boolean_response, $row->display,
                $attrib_status);
            $html .= '<span class="text-danger help-block small with-errors">
                                            ' . form_error('role_status') . '
                                        </span>
                            </div>
                            <div class="col-md-12 form-group user-form-group">
                                <div class="pull-right">
                                <input type="hidden" name="user_id" id="user_id" value="' . $user_id . '" />
                                <input type="hidden" name="update_user_id" id="update_user_id" value="' . $update_user_id . '" />
                                    <a href="' . site_url('' . $current_class . '/system_users') . '" class="btn btn-danger btn-sm">Cancel</a>
                                    <button type="submit" name="submit" id="submit" value="yes" class="btn btn-primary btn-sm">Update User</button>
                                </div>
                            </div>
                        </fieldset>';
        }
        $html .= form_close();
        $html .= '</div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger pull-left" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>';


        echo $html;
    }

    public function edit_user()
    {
        $current_class = $this->router->fetch_class();
        $s = substr(str_shuffle(str_repeat(defaultRandomStringArray, 6)), 0, 6);
        $username = create_username($this->input->post('full_names'));
        $user_id = $this->input->post('user_id');
        $user_status = ($this->input->post('role_status') == 1) ? 'active' : 'deactivated';
        $submit = $this->input->post('submit');

        if (isset($submit) and ($submit == 'yes')) {
            $data_to_update = array(
                'fullNames' => $this->input->post('full_names'),
                'username' => $username,
                'password' => md5($this->input->post('user_password')),
                'EncryptionHint' => $s . $this->input->post('user_password'),
                'role_id' => $this->input->post('role_name'),
                'country' => $this->input->post('user_country'),
                'email' => $this->input->post('user_email'),
                'tel_mobile' => $this->input->post('user_tel'),
                'status' => $user_status,
                'display' => 'Yes',
                'emailStatus' => 'Sent',
                'passwordReset' => 'No',
                'ResetConfirmed' => 'No',
                'date_entry' => date('Y-m-d H:i:s'),
            );

            /*send email for verification*/
            $message = '
                    <div style="font-family:Arial,sans-serif;line-height:20px;color:#444444;font-size:13px">
                    <p><b style="color:#777777">Dear
                        ' . $this->input->post('full_names') . '</b> <br>
                        Your account has been modified in  :<b>' . projectName . '</b> , an  Investment Fund Management Software.<br/>
                        To access the system please click the link below:<br/></p>
                        <p><a href="' . site_url('FmLogin/index/') . '"
                           style="color:#ffffff;text-decoration:none;margin:0px;text-align:center;vertical-align:baseline;border:4px solid #6fb3e0;padding:4px 9px;font-size:15px;line-height:21px;background-color:#6fb3e0"
                           target="_blank">Go to your account</a>
                    </p>
                    <p style="color:#777777">
                    <h4><u>Credentials</u></h4>
                    User Name:<b>' . $username . '</b><br/>
                    User Password:<b>' . $this->input->post('user_password') . '</b><br/>
                    </p>
                    <p>
                    Please feel free to contact us at <a href="mailto:mis.fundmanager17@gmail.com?subject=FundManagersMISFeedback&cc=apollo.aasiimwe@gmail.com">mis.fundmanager17@gmail.com</a> in case of any queries.<br/>
                    or call <b>+256 783 961426</b>, <b>+256 7723 06944</b>
                    </p>
                    </div>';

            $this->email->from('' . default_system_mail_sender . '', '' . title_ext . ' - Account Update!');
            $this->email->to($this->input->post('user_email'));
            $this->email->bcc('' . default_copy_address_1 . '', '' . default_copy_address_2 . '', '' . default_copy_address_3 . '');
            $this->email->subject('' . title_ext . ' Activation');
            $this->email->message($message);

            if (!$this->email->send()) {


                $this->session->set_flashdata('user_add_success_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-danger text-center fadeIn" style="color:#810b0e;">
                                <p>' . $this->email->print_debugger() . '</p></div>
								');
                redirect('' . $current_class . '/system_users');
            } else {
                $this->email->send();
                $this->db->where('tbl_system_user_id', $user_id);
                $this->db->update('tbl_system_users', $data_to_update);

                $this->session->set_flashdata('user_add_success_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>User successfully Updated</p></div>
								');
                redirect('' . $current_class . '/system_users');
            }

        }


    }

public function system_permissions()
    {
        @$this->page_session_expiry($this->session->userdata['user_org_id']);
        $fm_id = $this->session->userdata['user_org_id'];
        $current_method = $this->router->fetch_method();
        $current_class = $this->router->fetch_class();
        $fm_name = $this->session->userdata['user_org_name'];

        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($current_method),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,
            'boolean_response' => $this->Lookups_model->get_boolean_response(),

        );
        $data['data_system_roles'] = $this->Setups_model->get_all_system_roles();
    
        $this->load->view('header', $data);
        $this->load->view('system_setups/role_permissions', $data);
        $this->load->view('footer', $data);


    }

    public function add_role()
    {
        $current_class = $this->router->fetch_class();
        $role_status = ($this->input->post('role_status') == 1) ? 'Yes' : 'No';
        $insert_role = array(
            'id' => null,
            'name' => $this->input->post('role_name'),
            'entered_by' => $this->session->userdata['user_id'],
            'date_entry' => date('Y-m-d H:i:s'),
            'display' => $role_status,
        );

        $this->db->insert('tbl_roles', $insert_role);
        $this->session->set_flashdata('role_add_success_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>New Role Added successfully</p></div>
                                ');
        redirect('' . $current_class . '/system_permissions');


    }

    public function load_add_permissions_modal()
    {
        $data = '';
        $data_not_yet_permitted_roles = $this->Lookups_model->get_not_yet_permitted_roles();
        $data_all_modules = $this->Lookups_model->get_all_system_modules($id='');
        $data_all_menu_items_by_module = $this->Lookups_model->get_all_menu_items_by_module();

        $data = array(
            'data_all_modules' => $data_all_modules,
            'awesome_icon' => 'fa fa-cogs',
            'data_all_menu_items_by_module' => $data_all_menu_items_by_module,
            'un_permitted_drop_down' => $data_not_yet_permitted_roles,
        );

        $this->load->view('system_setups/role_permissions_add_modal', $data);
    }
         
    public function add_permissions()
    {
        $current_class = $this->router->fetch_class();
        $menu_name = $this->input->post('menu_name');
        $aw_icon = $this->input->post('aw_icon');
        $sys_controller = $this->input->post('sys_controller');
        $cat_rank = $this->input->post('cat_rank');
        $menu_display = $this->input->post('menu_display');
        $user_id = $this->session->userdata['user_id'];
        if (($menu_name) !== '') {
            $data_to_insert = array(
                'id' => NULL,
                'name' => $menu_name,
                'awesome_icon' => $aw_icon,
                'controller' => $sys_controller,
                'rank' => $cat_rank,
                'display' => $menu_display,
            );

            $this->update_insert('tbl_menu_categories', $data_to_insert);
        }
        $this->session->set_flashdata('menu_cat_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>Menu category successfully added</p></div>
								');
        redirect('' . $current_class . '/menu_categories');

    }

    public function load_edit_permissions_modal()
    {
        $data['record_id'] = $this->input->post('record_id');

        $data['data_all_menu_categories'] = $this->Lookups_model->get_all_menu_categories();
        $data['data_all_menu_items'] = $this->Lookups_model->get_all_menu_items();
       
        $this->load->view('system_setups/role_permissions_update_modal', $data);
    }

    public function update_permissions()
    {
       $current_class = $this->router->fetch_class();

        $get_last_role_id = $this->Setups_model->get_last_role_id();


        $role_type = $this->input->post('role_type');
        /*delete role's current permissions'*/
        $data_to_update = array(
            'display' =>0
        );

        $this->db->where('role_id', $role_type);
        $this->db->update('tbl_permissions', $data_to_update);

        $menu_item = $this->input->post('menu_item');
        if (($menu_item) !== '') {
            foreach ($menu_item as $key => $menu_item_value) {
                $data['cat_id'] = $this->Setups_model->get_menu_category_id($menu_item_value);
                foreach ($data['cat_id'] as $row) {
                    $menu_cat_id = $row->category_id;
                }


                    $data_to_insert_menu_items = array(
                    //'id'=>($get_last_role_id + 1),
                    'role_id' => $role_type,
                    'menu_cat_id' => $menu_cat_id,
                    'menu_item_id' => $menu_item_value,
                    'entry_date' => date('Y-m-d H:i:s'),
                    'entered_by' => $this->session->userdata['user_id'],
                    'display' => 1

                );


                /*insert new roles*/
                $this->db->insert('tbl_permissions', $data_to_insert_menu_items);
            }
        }


        $this->session->set_flashdata('role_add_success_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>Role Permissions successfully updated</p></div>
                                ');
        redirect('' . $current_class . '/system_permissions');


    }

    public function load_delete_permissions_modal()
    {

        $record_id = $this->input->post('record_id');
        $data_all_modules = $this->Lookups_model->get_all_system_modules($record_id);
        $menu_cat_name = $this->Reverselookups_model->get_menu_name($record_id);
        $current_class = $this->router->fetch_class();
        $data = array(
            'record_id' => $record_id,
            'menu_cat_name' => $menu_cat_name,
            'data_all_modules' => $data_all_modules,
            'current_class' => $current_class,
        );

        $this->load->view('system_setups/role_permissions_delete_modal', $data);

    }

    public function delete_permissions()
    {
        $current_class = $this->router->fetch_class();
        $menu_cat_id = $this->input->post('menu_cat_id');
        $submit = $this->input->post('submit');
        if (isset($submit) and ($submit == 'yes')) {
            $this->db->where('id', $menu_cat_id);
            $this->db->delete('tbl_menu_categories');

            $this->session->set_flashdata('menu_cat_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>Menu Item successfully Deleted</p></div>
								');
            redirect('' . $current_class . '/menu_categories');
        }

    }


    public function menu_categories()
    {
        @$this->page_session_expiry($this->session->userdata['user_org_id']);
        $fm_id = $this->session->userdata['user_org_id'];
        $current_method = $this->router->fetch_method();
        $current_class = $this->router->fetch_class();
        $fm_name = $this->session->userdata['user_org_name'];

        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($current_method),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,
            'boolean_response' => $this->Lookups_model->get_boolean_response(),

        );

        $data['data_get_all_menu_items'] = $this->Setups_model->get_all_menu_items();


        $this->load->view('header', $data);
        $this->load->view('system_setups/menu_categories', $data);
        $this->load->view('footer', $data);


    }

    public function load_add_menu_categories_modal()
    {
        $data = '';
        $data = array(
            'display_drop_down' => $this->Lookups_model->get_boolean_response(),
        );

        $this->load->view('system_setups/menu_category_add_modal', $data);
    }

    public function add_menu_category()
    {
        $current_class = $this->router->fetch_class();
        $menu_name = $this->input->post('menu_name');
        $aw_icon = $this->input->post('aw_icon');
        $sys_controller = $this->input->post('sys_controller');
        $cat_rank = $this->input->post('cat_rank');
        $menu_display = $this->input->post('menu_display');
        $user_id = $this->session->userdata['user_id'];
        if (($menu_name) !== '') {
            $data_to_insert = array(
                'id' => NULL,
                'name' => $menu_name,
                'awesome_icon' => $aw_icon,
                'controller' => $sys_controller,
                'rank' => $cat_rank,
                'display' => $menu_display,
            );

            $this->update_insert('tbl_menu_categories', $data_to_insert);
        }
        $this->session->set_flashdata('menu_cat_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>Menu category successfully added</p></div>
								');
        redirect('' . $current_class . '/menu_categories');

    }

    public function load_edit_menu_categories_modal()
    {
        $record_id = $this->input->post('record_id');
        $data_all_modules = $this->Lookups_model->get_all_system_modules($record_id);

        $data = array(
            'record_id' => $record_id,
            'data_all_modules' => $data_all_modules,
        );

        $this->load->view('system_setups/menu_category_update_modal', $data);
    }

    public function update_menu_categories()
    {
        $current_class = $this->router->fetch_class();
        $menu_item_id = $this->input->post('menu_cat_id');
        $menu_name = $this->input->post('menu_name');
        $aw_icon = $this->input->post('aw_icon');
        $sys_controller = $this->input->post('sys_controller');
        $cat_rank = $this->input->post('cat_rank');
        $menu_display = $this->input->post('menu_display');
        $user_id = $this->session->userdata['user_id'];
        if (($menu_item_id) !== '') {
            $data_to_update = array(
                'name' => $menu_name,
                'awesome_icon' => $aw_icon,
                'controller' => $sys_controller,
                'rank' => $cat_rank,
                'display' => $menu_display,
            );

            $this->db->where('id', $menu_item_id);
            $this->db->update('tbl_menu_categories', $data_to_update);
        }
        $this->session->set_flashdata('menu_cat_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>Menu successfully updated</p></div>
								');
        redirect('' . $current_class . '/menu_categories');

    }

    public function load_delete_menu_categories_modal()
    {

        $record_id = $this->input->post('record_id');
        $data_all_modules = $this->Lookups_model->get_all_system_modules($record_id);
        $menu_cat_name = $this->Reverselookups_model->get_menu_name($record_id);
        $current_class = $this->router->fetch_class();
        $data = array(
            'record_id' => $record_id,
            'menu_cat_name' => $menu_cat_name,
            'data_all_modules' => $data_all_modules,
            'current_class' => $current_class,
        );

        $this->load->view('system_setups/menu_category_delete_modal', $data);

    }

    public function delete_menu_categories()
    {
        $current_class = $this->router->fetch_class();
        $menu_cat_id = $this->input->post('menu_cat_id');
        $submit = $this->input->post('submit');
        if (isset($submit) and ($submit == 'yes')) {
            $this->db->where('id', $menu_cat_id);
            $this->db->delete('tbl_menu_categories');

            $this->session->set_flashdata('menu_cat_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>Menu Item successfully Deleted</p></div>
								');
            redirect('' . $current_class . '/menu_categories');
        }

    }


    public function sub_menu_items()
    {
        @$this->page_session_expiry($this->session->userdata['user_org_id']);
        $fm_id = $this->session->userdata['user_org_id'];
        $current_method = $this->router->fetch_method();
        $current_class = $this->router->fetch_class();
        $fm_name = $this->session->userdata['user_org_name'];

        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($current_method),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,
            'boolean_response' => $this->Lookups_model->get_boolean_response(),

        );

        $data['data_get_all_sub_menu_items'] = $this->Setups_model->get_all_sub_menu_items();


        $this->load->view('header', $data);
        $this->load->view('system_setups/sub_menu_items', $data);
        $this->load->view('footer', $data);


    }

    public function load_add_sub_menu_items_modal()
    {
        $data = '';
        $data = array(
            'item_display' => $this->Lookups_model->get_boolean_response(),
            'menu_cat_drop_down' => $this->Lookups_model->get_menu_cat_drop_down(),
        );

        $this->load->view('system_setups/sub_menu_items_add_modal', $data);
    }

    public function add_sub_menu_item()
    {
        $current_class = $this->router->fetch_class();
        $menu_cat = $this->input->post('menu_cat');
        $item_name = $this->input->post('item_name');
        $sub_controller = $this->input->post('sub_controller');
        $cat_rank = $this->input->post('cat_rank');
        $item_display = $this->input->post('item_display');
        $user_id = $this->session->userdata['user_id'];
        if (($item_name) !== '') {
            $data_to_insert = array(
                'tbl_menu_itemsId' => NULL,
                'category_id' => $menu_cat,
                'name' => $item_name,
                'sub_controller' => $sub_controller,
                'rank' => $cat_rank,
                'display' => $item_display,
            );

            $this->update_insert('tbl_menu_items', $data_to_insert);
        }
        $this->session->set_flashdata('sub_menu_items_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>Menu category successfully added</p></div>
								');
        redirect('' . $current_class . '/sub_menu_items');

    }

    public function load_edit_sub_menu_items_modal()
    {
        $record_id = $this->input->post('record_id');
        $data_all_sub_menu_items = $this->Lookups_model->get_all_sub_menu_items($record_id);

        $data = array(
            'record_id' => $record_id,
            'data_all_sub_menu_items' => $data_all_sub_menu_items,
            'item_display' => $this->Lookups_model->get_boolean_response(),
            'menu_cat_drop_down' => $this->Lookups_model->get_menu_cat_drop_down(),
        );

        $this->load->view('system_setups/sub_menu_items_update_modal', $data);
    }

    public function update_sub_menu_items()
    {
        $current_class = $this->router->fetch_class();
        $menu_item_id = $this->input->post('tbl_menu_itemsId');
        $menu_cat = $this->input->post('menu_cat');
        $item_name = $this->input->post('item_name');
        $sub_controller = $this->input->post('sub_controller');
        $cat_rank = $this->input->post('cat_rank');
        $item_display = $this->input->post('item_display');
        $user_id = $this->session->userdata['user_id'];
        if (($menu_item_id) !== '') {
            $data_to_update = array(
                'category_id' => $menu_cat,
                'name' => $item_name,
                'sub_controller' => $sub_controller,
                'rank' => $cat_rank,
                'display' => $item_display,
            );

            $this->db->where('tbl_menu_itemsId', $menu_item_id);
            $this->db->update('tbl_menu_items', $data_to_update);
        }
        $this->session->set_flashdata('sub_menu_items_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>Menu Sub-Item successfully updated</p></div>
								');
        redirect('' . $current_class . '/sub_menu_items');

    }

    public function load_delete_sub_menu_items_modal()
    {

        $record_id = $this->input->post('record_id');
        $data_all_sub_menu_items = $this->Lookups_model->get_all_sub_menu_items($record_id);
        $menu_item_name = $this->Reverselookups_model->get_sub_item_name($record_id);
        $menu_cat_name = $this->Reverselookups_model->get_menu_name_for_sub_item($record_id);
        $current_class = $this->router->fetch_class();
        $data = array(
            'record_id' => $record_id,
            'menu_cat_name' => $menu_cat_name,
            'menu_item_name' => $menu_item_name,
            'data_all_sub_menu_items' => $data_all_sub_menu_items,
            'current_class' => $current_class,
        );

        $this->load->view('system_setups/sub_menu_items_delete_modal', $data);

    }

    public function delete_sub_menu_items()
    {
        $current_class = $this->router->fetch_class();
        $tbl_menu_itemsId = $this->input->post('tbl_menu_itemsId');
        $submit = $this->input->post('submit');
        if (isset($submit) and ($submit == 'yes')) {
            $this->db->where('tbl_menu_itemsId', $tbl_menu_itemsId);
            $this->db->delete('tbl_menu_items');

            $this->session->set_flashdata('sub_menu_items_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>Menu Sub_Item successfully Deleted</p></div>
								');
            redirect('' . $current_class . '/sub_menu_items');
        }

    }


    public function manage_roles()
    {
        @$this->page_session_expiry($this->session->userdata['user_org_id']);
        $fm_id = $this->session->userdata['user_org_id'];
        $current_method = $this->router->fetch_method();
        $current_class = $this->router->fetch_class();
        $fm_name = $this->session->userdata['user_org_name'];
        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($current_method),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,
            'boolean_response' => $this->Lookups_model->get_boolean_response(),

        );
        $data['data_get_all_roles'] = $this->Setups_model->get_all_system_roles($id = '');
        $this->load->view('header', $data);
        $this->load->view('system_setups/roles', $data);
        $this->load->view('footer', $data);
    }

    public function load_add_roles_modal()
    {
        $data = '';
        $data = array(
            'display_drop_down' => $this->Lookups_model->get_boolean_response(),
        );

        $this->load->view('system_setups/roles_add_modal', $data);
    }

    public function add_roles()
    {
        $current_class = $this->router->fetch_class();
        $role_name = $this->input->post('role_name');
        $role_display = $this->input->post('role_display');
        $user_id = $this->session->userdata['user_id'];
        if (($role_name) !== '') {
            $data_to_insert = array(
                'id' => NULL,
                'name' => $role_name,
                'entered_by' => $user_id,
                'date_entry' => date('Y-m-d H:i:s'),
                'display' => $role_display,
            );

            $this->update_insert('tbl_roles', $data_to_insert);
        }
        $this->session->set_flashdata('role_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>Role successfully added</p></div>
								');
        redirect('' . $current_class . '/manage_roles');

    }

    public function load_edit_roles_modal()
    {
        $record_id = $this->input->post('record_id');
        $all_roles = $this->Setups_model->get_all_system_role_details($record_id);
        
        $data = array(
            'record_id' => $record_id,
            'all_roles' => $all_roles,
            'boolean_response' => $this->Lookups_model->get_boolean_response()
        );

        $this->load->view('system_setups/roles_update_modal', $data);
    }

    public function update_roles()
    {
        $current_class = $this->router->fetch_class();
        $role_id = $this->input->post('role_id');
        $role_name = $this->input->post('role_name');
        $role_display = $this->input->post('role_display');
        $user_id = $this->session->userdata['user_id'];
        if (($role_id) !== '') {
            $data_to_update = array(
                'name' => $role_name,
                'entered_by' => $user_id,
                'date_entry' => date('Y-m-d H:i:s'),
                'display' => $role_display,
            );

            $this->db->where('id', $role_id);
            $this->db->update('tbl_roles', $data_to_update);
        }
        $this->session->set_flashdata('role_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>Role successfully updated</p></div>
								');
        redirect('' . $current_class . '/manage_roles');

    }

    public function load_delete_roles_modal()
    {

        $record_id = $this->input->post('record_id');
        $all_roles = $this->Setups_model->get_all_system_roles($record_id);
        $role_name = $this->Reverselookups_model->get_role_name($record_id);
        $current_class = $this->router->fetch_class();
        $data = array(
            'record_id' => $record_id,
            'role_name' => $role_name,
            'all_roles' => $all_roles,
            'current_class' => $current_class,
        );

        $this->load->view('system_setups/roles_delete_modal', $data);

    }

    public function delete_roles()
    {
        $current_class = $this->router->fetch_class();
        $role_id = $this->input->post('role_id');
        $submit = $this->input->post('submit');
        if (isset($submit) and ($submit == 'yes')) {
            $this->db->where('id', $role_id);
            $this->db->delete('tbl_roles');

            $this->session->set_flashdata('role_msg', '
                                <div class="col-sm-offset-1 col-sm-10 alert alert-success text-center fadeIn" style="color:#0C2B2D;">
                                <p>User Role successfully Deleted</p></div>
								');
            redirect('' . $current_class . '/manage_roles');
        }

    }
     public function password_change()
    {
        @$this->page_session_expiry($this->session->userdata['user_org_id']);
        $fm_id = $this->session->userdata['user_org_id'];
        $current_method = $this->router->fetch_method();
        $current_class = $this->router->fetch_class();
        $fm_name = $this->session->userdata['user_org_name'];

        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($current_method),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,
            'boolean_response' => $this->Lookups_model->get_boolean_response(),

        );
        $this->load->view('header', $data);
        $this->load->view('system_setups/password_change', $data);
        $this->load->view('footer', $data);
    }

public function passwordChange(){
    $this->form_validation->set_rules('old_password', 'Password', 'trim|required|xss_clean');
    $this->form_validation->set_rules('new_password', 'New Password', 'required|matches[conf_password]');
    $this->form_validation->set_rules('conf_password', 'Retype Password', 'required');
    if($this->form_validation->run() == FALSE){

        @$this->page_session_expiry($this->session->userdata['user_org_id']);
        $fm_id = $this->session->userdata['user_org_id'];
        $password_change="password_change";
        $current_method = $password_change;
        $current_class = $this->router->fetch_class();
        $fm_name = $this->session->userdata['user_org_name'];

        $data = array(
            'page_protection' => $this->protected_page(),
            'page_title' => title_ext . clean_menu_item_name($current_method),
            'menu_name' => $this->Lookups_model->get_menu_name($current_class),
            'awesome_icon' => $this->Lookups_model->get_menu_icon($current_class),
            'sub_menu_item' => $this->Lookups_model->get_sub_menu_item($current_method),
            'sub_menu_description' => ucfirst(strtolower(substr($current_class, 2))),
            'data_menu_category' => $this->Lookups_model->get_menu_category($this->session->userdata['user_group_id']),
            'data_fm_name' => $fm_name,
            'boolean_response' => $this->Lookups_model->get_boolean_response(),

        );
        $this->load->view('header', $data);
        $this->load->view('system_setups/password_change', $data);
        $this->load->view('footer', $data);
    }else{
      $query = $this->login_model->checkOldPass(md5($this->input->post('old_password')));
      if($query==1){
        $query = $this->login_model->saveNewPass(md5($this->input->post('new_password')));
        if($query==1){
          $this->session->set_flashdata('message','<div class="alert alert-success text-center fadeIn" role="alert">Password was successfully changed</div>');
          redirect('SystemSetups/password_change');
        }else{
            $this->session->set_flashdata('message','<div class="alert alert-danger text-center fadeIn" role="alert">Password change was un successful</div>');
          redirect('SystemSetups/password_change');
        }
      }
      else{
            $this->session->set_flashdata('message','<div class="alert alert-danger text-center fadeIn" role="alert">Invalid old Password</div>');
          redirect('SystemSetups/password_change');
        }
    }
}
}