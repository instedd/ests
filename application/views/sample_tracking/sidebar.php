 <header class="main-header">
<?php 
  $ci =& get_instance();
$ci->load->model('Dashboard_model');
 $notifications=$ci->Dashboard_model->get_total_notifications();
   ?>
    <!-- Logo -->
    <a href="<?=base_url();?>Dashboard/index" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
     <span class="logo-mini"><b>ESTS</b></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>SAMPLE TRACKER</b></span>
    </a>

    <!-- Header Navbar: style can be found in header.less -->
    <nav class="navbar navbar-static-top">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- User Account: style can be found in dropdown.less -->
          <li class="dropdown user user-menu">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <!--<img src="dist/img/user2-160x160.jpg" class="user-image" alt="User Image">-->
              <span class="hidden-xs"><?= $this->session->userdata['user_full_name']; ?></span>
            </a>
            <ul class="dropdown-menu">
              <!-- User image -->
              <li class="user-header">
                <!--<img src="dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">-->

                <p>
                 Welcome, <?= $this->session->userdata['user_full_name']; ?>
                </p>
              </li>
             
              <!-- Menu Footer-->
              <li class="user-footer">
                <!-- <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">Profile</a>
                </div> -->
                <div class="pull-right">
                  <a href="<?= site_url('FmLogin/logout'); ?>" class="btn btn-default btn-flat">Sign out</a>
                </div>
              </li>
            </ul>
          </li>
          <!-- Control Sidebar Toggle Button -->
          <li>
            <a href="#" data-toggle="control-sidebar"><i class="fa fa-gears"></i></a>
          </li>
        </ul>
      </div>

    </nav>
  </header>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        
        <?php
                    foreach ($data_menu_category as $row_cat) {
                        $menu_category = $row_cat->name;
                        $menu_category_icon = $row_cat->awesome_icon;
                        $menu_category_id = $row_cat->id;
                        $menu_category_controller = $row_cat->controller;
                        $user_group = $this->session->userdata['user_group_id'];

                        $this->db->select("
                            p.`menu_item_id`,
                            i.`name`,
                            i.`sub_controller`
                            FROM 
                            tbl_menu_items i
                            join tbl_permissions p
                            on (p.`menu_item_id`=i.`tbl_menu_itemsId`)
                            join 
                            `tbl_roles` r                        
                            on (r.`id`=p.`role_id`)
                            where 1
                            and p.menu_cat_id=" . $menu_category_id . "
                            and r.id=" . $user_group . "
                            and i.display=1
                            and r.display=1
                            and p.display=1
                            order by i.rank, i.tbl_menu_itemsId asc", FALSE);
                        $db_rows_menu_items = $this->db->get();

                        $active_link = ($menu_name == $menu_category) ? 'active' : '';

                        switch (true) {
                            case(($menu_name == $menu_category)):
                                $menu_class = 'has_sub';

                                break;
                            default:
                                $menu_class = (intval($menu_category_id) == 1) ? '' : '';
                                break;
                        }
                     $dashboard_link = (intval($menu_category_id) == 1) ? site_url('Dashboard/index') : 'javascript:void(0);';
                        
                        ?>

        
        <li class="treeview">
          <a href="<?=$dashboard_link;?>">
            <i class="<?= $menu_category_icon; ?>"></i>
            <span><?php if($menu_category_id==9)
            {
              echo $menu_category.'('.$notifications.')';
            }else {
              echo $menu_category;
            }?>
          </span>
               <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
		  <?php
                      $html = '';
                      if ($db_rows_menu_items->num_rows() > 0) {
                          foreach ($db_rows_menu_items->result() as $row_sub_menu) {
                              $sub_item_name = $row_sub_menu->name;
                              $sub_item_controller = $row_sub_menu->sub_controller;

                              $html .= '<li><a id="menu_item"  href="' . site_url('' . trim($menu_category_controller) . '/' . trim($sub_item_controller) . '') . '">' . $sub_item_name . '</a></li>';
                          }
                          echo $html;
                      }
                      ?>
                  </ul>
              
          </li>

        <?php } ?>

       </ul>
        
    </section>
    <!-- /.sidebar -->
  </aside>
      