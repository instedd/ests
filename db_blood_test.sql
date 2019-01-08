-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 13, 2018 at 02:31 PM
-- Server version: 10.1.30-MariaDB
-- PHP Version: 5.6.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_blood_test`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_destination`
--

CREATE TABLE `tbl_destination` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `entered_by` int(10) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `display` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_destination`
--

INSERT INTO `tbl_destination` (`id`, `name`, `entered_by`, `created_at`, `display`) VALUES
(5, 'CPHL', 1, '2018-10-14 11:37:25', 1),
(6, 'UVRI', 1, '2018-10-14 11:37:43', 1),
(7, 'NTLP', 1, '2018-10-14 11:37:58', 1),
(8, 'DGAL', 1, '2018-10-14 11:38:15', 1),
(9, 'IDI', 1, '2018-10-14 11:38:30', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_diseases`
--

CREATE TABLE `tbl_diseases` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `entered_by` int(2) NOT NULL,
  `entry_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `display` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_diseases`
--

INSERT INTO `tbl_diseases` (`id`, `name`, `entered_by`, `entry_date`, `display`) VALUES
(2, 'Ebola', 1, '2018-10-12 00:35:08', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_facility_codes`
--

CREATE TABLE `tbl_facility_codes` (
  `id` int(10) NOT NULL,
  `code` varchar(50) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `entered_by` int(10) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `display` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_facility_codes`
--

INSERT INTO `tbl_facility_codes` (`id`, `code`, `name`, `entered_by`, `created_at`, `display`) VALUES
(2, NULL, 'Kabale Hospital', 1, '2018-10-12 00:57:51', 1),
(3, NULL, 'Mulago Hospital', 1, '2018-10-11 01:20:25', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_locations`
--

CREATE TABLE `tbl_locations` (
  `id` int(10) NOT NULL,
  `code` varchar(50) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `entered_by` int(10) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `display` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_locations`
--

INSERT INTO `tbl_locations` (`id`, `code`, `name`, `entered_by`, `created_at`, `display`) VALUES
(6, NULL, 'Kisoro District', 1, '2018-10-12 01:46:11', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_login`
--

CREATE TABLE `tbl_login` (
  `login_id` bigint(20) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_name` varchar(300) DEFAULT NULL,
  `user_group_id` int(11) NOT NULL,
  `time` datetime DEFAULT NULL,
  `time_logged_off` datetime DEFAULT NULL,
  `ip_address` varchar(100) NOT NULL,
  `status` set('Active','Logged Off') NOT NULL DEFAULT 'Active'
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_login`
--

INSERT INTO `tbl_login` (`login_id`, `user_id`, `user_name`, `user_group_id`, `time`, `time_logged_off`, `ip_address`, `status`) VALUES
(161, 1, 'nbamwine', 1, NULL, '2018-11-07 17:50:28', '::1', 'Logged Off'),
(162, 1, 'nbamwine', 1, NULL, '2018-11-07 17:50:28', '::1', 'Logged Off'),
(163, 1, 'nbamwine', 1, NULL, '2018-11-07 17:50:28', '::1', 'Logged Off'),
(164, 1, 'nbamwine', 1, NULL, '2018-11-07 17:50:28', '::1', 'Logged Off'),
(165, 1, 'nbamwine', 1, NULL, '2018-11-07 17:50:28', '::1', 'Logged Off'),
(166, 1, 'nbamwine', 1, NULL, '2018-11-07 17:50:28', '::1', 'Logged Off'),
(167, 1, 'nbamwine', 1, NULL, '2018-11-07 17:50:28', '::1', 'Logged Off'),
(168, 1, 'nbamwine', 1, NULL, '2018-11-07 17:50:28', '::1', 'Logged Off'),
(169, 1, 'nbamwine', 1, NULL, '2018-11-07 17:50:28', '::1', 'Logged Off'),
(170, 1, 'nbamwine', 1, NULL, '2018-11-07 17:50:28', '::1', 'Logged Off'),
(171, 1, 'nbamwine', 1, NULL, '2018-11-07 17:50:28', '::1', 'Logged Off'),
(172, 1, 'nbamwine', 1, NULL, '2018-11-07 17:50:28', '::1', 'Logged Off'),
(173, 1, 'nbamwine', 1, NULL, '2018-11-07 17:50:28', '::1', 'Logged Off'),
(174, 1, 'nbamwine', 1, NULL, '2018-11-07 17:50:28', '::1', 'Logged Off'),
(175, 1, 'nbamwine', 1, NULL, '2018-11-07 17:50:28', '::1', 'Logged Off'),
(176, 1, 'nbamwine', 1, NULL, '2018-11-07 17:50:28', '::1', 'Logged Off'),
(177, 19, 'mnduhukire', 12, NULL, NULL, '::1', 'Active'),
(178, 1, 'nbamwine', 1, NULL, '2018-11-07 17:50:28', '::1', 'Logged Off'),
(179, 1, 'nbamwine', 1, NULL, '2018-11-07 17:50:28', '::1', 'Logged Off'),
(180, 1, 'nbamwine', 1, NULL, '2018-11-07 17:50:28', '::1', 'Logged Off'),
(181, 1, 'nbamwine', 1, NULL, '2018-11-07 17:50:28', '::1', 'Logged Off'),
(182, 1, 'nbamwine', 1, NULL, '2018-11-07 17:50:28', '::1', 'Logged Off'),
(183, 1, 'nbamwine', 1, NULL, NULL, '::1', 'Active'),
(184, 1, 'nbamwine', 1, NULL, NULL, '::1', 'Active'),
(185, 1, 'nbamwine', 1, NULL, NULL, '::1', 'Active'),
(186, 1, 'nbamwine', 1, NULL, NULL, '::1', 'Active'),
(187, 1, 'nbamwine', 1, NULL, NULL, '::1', 'Active'),
(188, 1, 'nbamwine', 1, NULL, NULL, '::1', 'Active'),
(189, 1, 'nbamwine', 1, NULL, NULL, '::1', 'Active'),
(190, 1, 'nbamwine', 1, NULL, NULL, '::1', 'Active');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_lookup_boolean_response`
--

CREATE TABLE `tbl_lookup_boolean_response` (
  `tbl_lookup_boolean_responseId` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `created_by` int(11) NOT NULL DEFAULT '3',
  `display` set('Yes','No') NOT NULL DEFAULT 'Yes'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_lookup_boolean_response`
--

INSERT INTO `tbl_lookup_boolean_response` (`tbl_lookup_boolean_responseId`, `name`, `created_by`, `display`) VALUES
(1, 'Yes', 1, 'Yes'),
(2, 'No', 1, 'Yes');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_lookup_gender`
--

CREATE TABLE `tbl_lookup_gender` (
  `tbl_lookup_genderId` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `created_by` int(11) NOT NULL DEFAULT '3',
  `display` set('Yes','No') NOT NULL DEFAULT 'Yes'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_lookup_gender`
--

INSERT INTO `tbl_lookup_gender` (`tbl_lookup_genderId`, `name`, `created_by`, `display`) VALUES
(1, 'Male', 1, 'Yes'),
(2, 'Female', 1, 'Yes');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_lookup_titles`
--

CREATE TABLE `tbl_lookup_titles` (
  `tbl_lookup_titlesId` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `created_by` int(11) NOT NULL DEFAULT '3',
  `display` set('Yes','No') NOT NULL DEFAULT 'Yes'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_lookup_titles`
--

INSERT INTO `tbl_lookup_titles` (`tbl_lookup_titlesId`, `name`, `created_by`, `display`) VALUES
(1, 'Mr', 1, 'Yes'),
(2, 'Mrs', 1, 'Yes'),
(3, 'Miss', 1, 'Yes'),
(4, 'Master', 1, 'Yes'),
(5, 'Dr', 1, 'Yes'),
(6, 'Prof', 1, 'Yes'),
(7, 'Eng', 1, 'Yes');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_menu_categories`
--

CREATE TABLE `tbl_menu_categories` (
  `id` int(11) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `awesome_icon` varchar(500) NOT NULL,
  `controller` varchar(600) NOT NULL,
  `rank` int(11) DEFAULT NULL,
  `display` set('1','2') NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_menu_categories`
--

INSERT INTO `tbl_menu_categories` (`id`, `name`, `awesome_icon`, `controller`, `rank`, `display`) VALUES
(1, 'Dashboards', 'fa fa-dashboard', 'Dashboard', 1, '1'),
(2, 'Reports', 'mdi mdi-google-pages', 'Reports', 5, '1'),
(3, 'Data Entry', 'fa fa-file-text', 'DataEntry', 4, '2'),
(4, 'System Setups', 'fa fa fa-cogs', 'SystemSetups', 7, '1'),
(6, 'Administration', 'fa fa-group', 'UserAdministration', 2, '1'),
(7, 'Documents', ' fa fa-folder-o', 'Documents', 6, '1'),
(8, 'Sample Management', 'fa fa-stethoscope', 'SampleTracking', 3, '1');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_menu_items`
--

CREATE TABLE `tbl_menu_items` (
  `tbl_menu_itemsId` int(11) NOT NULL,
  `category_id` int(200) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `sub_controller` varchar(200) DEFAULT NULL,
  `rank` decimal(6,3) DEFAULT NULL,
  `display` set('1','2') NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_menu_items`
--

INSERT INTO `tbl_menu_items` (`tbl_menu_itemsId`, `category_id`, `name`, `sub_controller`, `rank`, `display`) VALUES
(4, 4, 'Manage System Permissions', 'system_permissions', '1.000', '1'),
(5, 1, 'Dashboard', 'index', '1.000', '1'),
(15, 6, 'Deactivate User Profile', 'deactivate_profile', '6.000', '2'),
(31, 4, 'Manage Menu Categories', 'menu_categories', '12.000', '1'),
(32, 4, 'Manage Sub-Menu Items', 'sub_menu_items', '11.100', '1'),
(33, 6, 'Manage User', 'users_manage', '1.010', '1'),
(34, 4, 'Manage Roles', 'manage_roles', '1.110', '1'),
(37, 8, 'Register Sample', 'register_sample', '1.000', '1'),
(38, 8, 'Receive Sample', 'receive_sample', '2.000', '1'),
(39, 6, 'Manage Diseases', 'manage_diseases', '1.000', '1'),
(40, 6, 'Manage Health Facilities', 'health_facilities', '2.000', '1'),
(41, 6, 'Manage Reference Labs', 'manage_labs', '3.000', '1'),
(42, 6, 'Manage Locations', 'manage_locations', '6.000', '1'),
(43, 8, 'Registered Samples', 'registered_samples', '3.000', '1'),
(44, 6, 'Manage Sample Types', 'sample_manage', '6.000', '1'),
(45, 6, 'Manage Destination', 'manage_destination', '5.000', '1'),
(46, 8, 'Received Samples', 'received_samples', '4.000', '1');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_months`
--

CREATE TABLE `tbl_months` (
  `id` int(10) NOT NULL,
  `month` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_months`
--

INSERT INTO `tbl_months` (`id`, `month`) VALUES
(1, 'January'),
(2, 'February'),
(3, 'March'),
(4, 'April'),
(5, 'May'),
(6, 'June'),
(7, 'July'),
(8, 'August'),
(9, 'September'),
(10, 'October'),
(11, 'November'),
(12, 'December');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_permissions`
--

CREATE TABLE `tbl_permissions` (
  `id` int(11) NOT NULL,
  `role_id` int(255) DEFAULT NULL,
  `menu_cat_id` int(11) NOT NULL,
  `menu_item_id` int(11) NOT NULL,
  `entry_date` datetime DEFAULT NULL,
  `entered_by` int(255) DEFAULT NULL,
  `display` set('1','0') NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_permissions`
--

INSERT INTO `tbl_permissions` (`id`, `role_id`, `menu_cat_id`, `menu_item_id`, `entry_date`, `entered_by`, `display`) VALUES
(1, 1, 3, 1, '2017-12-18 00:00:00', 1, '1'),
(2, 1, 3, 2, '2017-12-18 00:00:00', 1, '1'),
(3, 1, 4, 4, '2017-12-18 00:00:00', 1, '1'),
(4, 1, 1, 5, '2017-12-18 00:00:00', 1, '1'),
(5, 1, 6, 15, '2017-12-18 00:00:00', 1, '1'),
(6, 1, 6, 19, '2017-12-18 00:00:00', 1, '1'),
(7, 1, 6, 22, '2017-12-18 00:00:00', 1, '1'),
(8, 1, 4, 31, '2017-12-18 00:00:00', 1, '1'),
(9, 1, 4, 32, '2017-12-18 00:00:00', 1, '1'),
(10, 1, 6, 33, '2017-12-18 00:00:00', 1, '1'),
(11, 1, 4, 34, '2017-12-18 00:00:00', 1, '1'),
(12, 1, 6, 35, '2017-12-21 06:13:22', 1, '1'),
(14, 1, 6, 36, '2017-12-18 00:00:00', 1, '1'),
(15, 12, 1, 5, '2017-12-18 00:00:00', 1, '1'),
(16, 2, 3, 2, '2017-12-18 00:00:00', 1, '1'),
(17, 2, 4, 4, '2017-12-18 00:00:00', 1, '1'),
(18, 12, 8, 2, '2017-12-18 00:00:00', 1, '1'),
(19, 12, 8, 38, '2017-12-18 00:00:00', 1, '1'),
(20, 12, 8, 43, '2017-12-18 00:00:00', 1, '1'),
(21, 12, 8, 46, '2017-12-18 00:00:00', 1, '1'),
(22, 2, 4, 31, '2017-12-18 00:00:00', 1, '1'),
(23, 2, 4, 32, '2017-12-18 00:00:00', 1, '1'),
(24, 2, 6, 33, '2017-12-18 00:00:00', 1, '1'),
(25, 2, 4, 34, '2017-12-18 00:00:00', 1, '1'),
(26, 2, 6, 35, '2017-12-21 06:13:22', 1, '1'),
(27, 2, 6, 36, '2017-12-18 00:00:00', 1, '1'),
(28, 3, 1, 5, '2017-12-18 00:00:00', 1, '1'),
(29, 3, 6, 35, '2017-12-18 00:00:00', 1, '1'),
(30, 3, 6, 36, '2017-12-18 00:00:00', 1, '1'),
(31, 1, 8, 37, '2018-10-06 00:00:00', NULL, '1'),
(32, 1, 8, 38, '2018-10-06 00:00:00', NULL, '1'),
(33, 1, 6, 39, '2018-10-06 00:00:00', NULL, '1'),
(34, 1, 6, 40, '2018-10-06 00:00:00', NULL, '1'),
(35, 1, 6, 41, '2018-10-06 00:00:00', NULL, '1'),
(36, 1, 6, 42, '2018-10-06 00:00:00', NULL, '1'),
(37, 1, 8, 43, '2018-10-12 00:00:00', 1, '1'),
(38, 1, 6, 44, '2018-10-13 00:00:00', 1, '1'),
(39, 1, 6, 45, '2018-10-14 00:00:00', 1, '1'),
(40, 1, 8, 46, '2018-10-17 00:00:00', 1, '1');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_received_sample`
--

CREATE TABLE `tbl_received_sample` (
  `id` int(10) NOT NULL,
  `sample_id` varchar(50) NOT NULL,
  `sample_status` varchar(50) NOT NULL,
  `destination_id` varchar(50) NOT NULL,
  `date_received` date NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `entered_by` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_received_sample`
--

INSERT INTO `tbl_received_sample` (`id`, `sample_id`, `sample_status`, `destination_id`, `date_received`, `created_at`, `entered_by`) VALUES
(8, '01', 'Not Intact', 'CPHL', '2018-10-18', '2018-10-17 11:41:25', 'Bamwine Nelsoni'),
(9, '02', 'Broken Seals', 'FINAL', '2018-10-24', '2018-10-23 22:01:29', 'Bamwine Nelsoni'),
(10, '03', 'Intact', 'FINAL', '2018-10-24', '2018-10-24 07:13:46', 'Bamwine Nelsoni'),
(11, '04', 'Intact', 'FINAL', '2018-10-23', '2018-10-24 07:30:54', 'Bamwine Nelsoni'),
(12, '06', 'Intact', 'FINAL', '2018-10-23', '2018-10-24 07:43:05', 'Bamwine Nelsoni'),
(13, '07', 'Intact', 'UVRI', '2018-11-05', '2018-11-01 15:47:41', 'Bamwine Nelson'),
(14, '07', 'Intact', 'FINAL', '2018-11-07', '2018-11-01 15:48:10', 'Bamwine Nelson'),
(15, '07', 'Broken Seals', 'CPHL', '2018-11-13', '2018-11-13 03:36:58', 'Bamwine Nelson');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_referencelabs`
--

CREATE TABLE `tbl_referencelabs` (
  `id` int(10) NOT NULL,
  `code` varchar(50) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `entered_by` int(10) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `display` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_referencelabs`
--

INSERT INTO `tbl_referencelabs` (`id`, `code`, `name`, `entered_by`, `created_at`, `display`) VALUES
(5, NULL, 'Lab one', 1, '2018-10-12 00:32:39', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_registered_samples`
--

CREATE TABLE `tbl_registered_samples` (
  `id` int(10) NOT NULL,
  `sample_id` varchar(50) NOT NULL,
  `facility_code_id` varchar(100) NOT NULL,
  `sample_type_id` varchar(100) NOT NULL,
  `destination_id` varchar(100) NOT NULL,
  `disease_id` varchar(100) NOT NULL,
  `cif` varchar(255) NOT NULL,
  `initialSampleDate` date NOT NULL,
  `finalDestinationDate` date NOT NULL,
  `clinical_notes` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `entered_by` varchar(100) NOT NULL,
  `actualFinalDate` date DEFAULT NULL,
  `isSampleAtFinal` varchar(10) NOT NULL DEFAULT 'No'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_registered_samples`
--

INSERT INTO `tbl_registered_samples` (`id`, `sample_id`, `facility_code_id`, `sample_type_id`, `destination_id`, `disease_id`, `cif`, `initialSampleDate`, `finalDestinationDate`, `clinical_notes`, `created_at`, `entered_by`, `actualFinalDate`, `isSampleAtFinal`) VALUES
(9, '01', 'Kabale Hospital', 'Blood', 'CPHL', 'Ebola', '', '2018-10-17', '2018-10-20', 'hfjjkdkkl', '2018-10-17 11:39:56', 'Bamwine Nelsoni', NULL, 'No'),
(10, '02', 'Mulago Hospital', 'Sputum', 'DGAL', 'Ebola', '', '2018-10-17', '2018-10-22', 'hdhjdk', '2018-10-23 08:43:05', 'Bamwine Nelsoni', NULL, 'No'),
(11, '03', 'Kabale Hospital', 'Blood', 'CPHL', 'Ebola', '', '2018-10-17', '2018-10-23', 'hhjjk', '2018-10-24 06:53:09', 'Bamwine Nelsoni', NULL, 'No'),
(12, '04', 'Mulago Hospital', 'Blood', 'DGAL', 'Ebola', '', '2018-10-19', '2018-10-24', 'ghhjjj', '2018-10-24 07:30:18', 'Bamwine Nelsoni', NULL, 'No'),
(13, '06', 'Mulago Hospital', 'Blood', 'IDI', 'Ebola', '', '2018-10-22', '2018-10-25', 'hjjj', '2018-10-24 07:42:34', 'Bamwine Nelsoni', NULL, 'No'),
(14, '07', 'Kabale Hospital', 'Blood', 'DGAL', 'Ebola', '', '2018-11-01', '2018-11-06', '', '2018-11-01 15:45:20', 'Bamwine Nelson', NULL, 'No'),
(15, '02', 'Kabale Hospital', 'Blood', 'DGAL', 'Ebola', '', '2018-11-13', '0000-00-00', 'nmm', '2018-11-13 03:43:15', 'Bamwine Nelson', NULL, 'No'),
(16, '06', 'Mulago Hospital', 'Blood', 'IDI', 'Ebola', '', '2018-11-13', '2018-11-16', 'hjkklll', '2018-11-13 03:46:10', 'Bamwine Nelson', NULL, 'No');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_roles`
--

CREATE TABLE `tbl_roles` (
  `id` int(11) NOT NULL,
  `name` text,
  `entered_by` varchar(200) DEFAULT NULL,
  `date_entry` datetime DEFAULT NULL,
  `display` set('1','2') NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Table for storing user roles';

--
-- Dumping data for table `tbl_roles`
--

INSERT INTO `tbl_roles` (`id`, `name`, `entered_by`, `date_entry`, `display`) VALUES
(1, 'Super Admin', '1', '2018-01-26 06:11:11', '1'),
(4, 'Lab Attendant', '1', '2018-10-06 18:23:52', '1'),
(6, 'Hub Rider', '1', '2018-10-06 22:06:49', '1'),
(7, 'CPHL Transporter', '1', '2018-10-06 22:07:39', '1'),
(8, 'VHF Team Lead from UVRI', '1', '2018-10-06 22:08:39', '1'),
(9, 'EOC Coordinator', '1', '2018-10-06 22:09:26', '1'),
(10, 'Guard at UVRI gate', '1', '2018-10-06 22:14:37', '1'),
(11, 'District Task Force ', '1', '2018-10-06 22:20:25', '1'),
(12, 'District Lab Focal Person', '1', '2018-10-06 22:21:38', '1'),
(13, 'CPHL Samples Reception', '1', '2018-11-09 12:27:05', '1');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_sample_status`
--

CREATE TABLE `tbl_sample_status` (
  `id` int(10) NOT NULL,
  `status` varchar(50) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `entered_by` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_sample_status`
--

INSERT INTO `tbl_sample_status` (`id`, `status`, `created_at`, `entered_by`) VALUES
(1, 'Intact', '2018-10-23 21:56:47', NULL),
(2, 'Open', '2018-10-23 21:56:59', NULL),
(3, 'Broken Seals', '2018-10-23 21:57:21', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_sample_type`
--

CREATE TABLE `tbl_sample_type` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `entered_by` int(10) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `display` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_sample_type`
--

INSERT INTO `tbl_sample_type` (`id`, `name`, `entered_by`, `created_at`, `display`) VALUES
(3, 'Stool', 1, '2018-10-14 09:06:57', 1),
(4, 'Blood', 1, '2018-10-14 09:08:14', 1),
(5, 'Swab', 1, '2018-10-14 09:08:44', 1),
(6, 'Biopsy', 1, '2018-10-14 09:09:03', 1),
(7, 'Sputum', 1, '2018-10-14 09:09:20', 1),
(8, 'Pus', 1, '2018-10-14 09:09:36', 1),
(9, 'Urine', 1, '2018-10-14 09:10:04', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_sent_sample`
--

CREATE TABLE `tbl_sent_sample` (
  `id` int(10) NOT NULL,
  `username` varchar(50) NOT NULL,
  `instanceId` varchar(50) NOT NULL,
  `sample_id` varchar(50) NOT NULL,
  `facility_code_id` int(10) NOT NULL,
  `sample_type_id` int(10) NOT NULL,
  `destination_id` int(10) NOT NULL,
  `disease_id` int(10) NOT NULL,
  `cif` varchar(255) NOT NULL,
  `clinical_notes` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_sys_admin`
--

CREATE TABLE `tbl_sys_admin` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `org_name` text NOT NULL,
  `display` set('1','0') NOT NULL DEFAULT '1'
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COMMENT='All System Administrators';

--
-- Dumping data for table `tbl_sys_admin`
--

INSERT INTO `tbl_sys_admin` (`id`, `user_id`, `org_name`, `display`) VALUES
(1, 1, 'Junior Baby Center', '1');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_users`
--

CREATE TABLE `tbl_users` (
  `id` int(11) NOT NULL COMMENT 'primary key',
  `fullNames` varchar(300) NOT NULL,
  `username` varchar(100) NOT NULL COMMENT 'unique key',
  `password` varchar(100) NOT NULL,
  `EncryptionHint` varchar(200) NOT NULL,
  `user_group_id` int(250) DEFAULT NULL,
  `org_id` int(11) NOT NULL,
  `role_id` int(10) NOT NULL,
  `email` varchar(50) NOT NULL,
  `tel_mobile` varchar(13) NOT NULL,
  `status` set('active','deactivated') NOT NULL DEFAULT 'active',
  `display` set('Yes','No') DEFAULT 'Yes',
  `emailStatus` set('Sent','Not Sent') NOT NULL DEFAULT 'Not Sent',
  `passwordReset` set('Yes','No') NOT NULL DEFAULT 'No',
  `ResetConfirmed` set('Yes','No') NOT NULL DEFAULT 'No',
  `date_entry` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_users`
--

INSERT INTO `tbl_users` (`id`, `fullNames`, `username`, `password`, `EncryptionHint`, `user_group_id`, `org_id`, `role_id`, `email`, `tel_mobile`, `status`, `display`, `emailStatus`, `passwordReset`, `ResetConfirmed`, `date_entry`) VALUES
(1, 'Bamwine Nelson', 'nbamwine', '81dc9bdb52d04dc20036dbd8313ed055', 'pxrKVQ1234', 1, 1, 1, 'nbamwine@outlook.com', '+256779826311', 'active', 'Yes', 'Sent', 'No', 'No', '2017-10-03 05:22:29'),
(19, 'Nduhukire Medard', 'mnduhukire', '81dc9bdb52d04dc20036dbd8313ed055', 's77XwL63goJyZt', 12, 0, 12, 'medardnduhukire@gmail.com', '+256779218977', 'active', 'Yes', 'Not Sent', 'No', 'No', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_destination`
--
ALTER TABLE `tbl_destination`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_diseases`
--
ALTER TABLE `tbl_diseases`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_facility_codes`
--
ALTER TABLE `tbl_facility_codes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_locations`
--
ALTER TABLE `tbl_locations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_login`
--
ALTER TABLE `tbl_login`
  ADD PRIMARY KEY (`login_id`);

--
-- Indexes for table `tbl_lookup_boolean_response`
--
ALTER TABLE `tbl_lookup_boolean_response`
  ADD PRIMARY KEY (`tbl_lookup_boolean_responseId`);

--
-- Indexes for table `tbl_lookup_gender`
--
ALTER TABLE `tbl_lookup_gender`
  ADD PRIMARY KEY (`tbl_lookup_genderId`);

--
-- Indexes for table `tbl_lookup_titles`
--
ALTER TABLE `tbl_lookup_titles`
  ADD PRIMARY KEY (`tbl_lookup_titlesId`);

--
-- Indexes for table `tbl_menu_categories`
--
ALTER TABLE `tbl_menu_categories`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_menu_items`
--
ALTER TABLE `tbl_menu_items`
  ADD PRIMARY KEY (`tbl_menu_itemsId`);

--
-- Indexes for table `tbl_months`
--
ALTER TABLE `tbl_months`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_permissions`
--
ALTER TABLE `tbl_permissions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_received_sample`
--
ALTER TABLE `tbl_received_sample`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_referencelabs`
--
ALTER TABLE `tbl_referencelabs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_registered_samples`
--
ALTER TABLE `tbl_registered_samples`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_roles`
--
ALTER TABLE `tbl_roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_sample_status`
--
ALTER TABLE `tbl_sample_status`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_sample_type`
--
ALTER TABLE `tbl_sample_type`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_sent_sample`
--
ALTER TABLE `tbl_sent_sample`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_sys_admin`
--
ALTER TABLE `tbl_sys_admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_users`
--
ALTER TABLE `tbl_users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_destination`
--
ALTER TABLE `tbl_destination`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `tbl_diseases`
--
ALTER TABLE `tbl_diseases`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tbl_facility_codes`
--
ALTER TABLE `tbl_facility_codes`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_locations`
--
ALTER TABLE `tbl_locations`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `tbl_login`
--
ALTER TABLE `tbl_login`
  MODIFY `login_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=191;

--
-- AUTO_INCREMENT for table `tbl_lookup_boolean_response`
--
ALTER TABLE `tbl_lookup_boolean_response`
  MODIFY `tbl_lookup_boolean_responseId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tbl_lookup_gender`
--
ALTER TABLE `tbl_lookup_gender`
  MODIFY `tbl_lookup_genderId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tbl_lookup_titles`
--
ALTER TABLE `tbl_lookup_titles`
  MODIFY `tbl_lookup_titlesId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tbl_menu_categories`
--
ALTER TABLE `tbl_menu_categories`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tbl_menu_items`
--
ALTER TABLE `tbl_menu_items`
  MODIFY `tbl_menu_itemsId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `tbl_months`
--
ALTER TABLE `tbl_months`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `tbl_permissions`
--
ALTER TABLE `tbl_permissions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT for table `tbl_received_sample`
--
ALTER TABLE `tbl_received_sample`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT for table `tbl_referencelabs`
--
ALTER TABLE `tbl_referencelabs`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tbl_registered_samples`
--
ALTER TABLE `tbl_registered_samples`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `tbl_roles`
--
ALTER TABLE `tbl_roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `tbl_sample_status`
--
ALTER TABLE `tbl_sample_status`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_sample_type`
--
ALTER TABLE `tbl_sample_type`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `tbl_sent_sample`
--
ALTER TABLE `tbl_sent_sample`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tbl_sys_admin`
--
ALTER TABLE `tbl_sys_admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tbl_users`
--
ALTER TABLE `tbl_users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'primary key', AUTO_INCREMENT=20;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
