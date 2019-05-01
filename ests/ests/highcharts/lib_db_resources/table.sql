-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 13, 2015 at 10:57 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `db_datavisualization`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_illegalearnings`
--

CREATE TABLE IF NOT EXISTS `tbl_illegalearnings` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) NOT NULL,
  `male` int(11) NOT NULL,
  `female` int(11) NOT NULL,
  `year` year(4) NOT NULL,
  `updatedBy` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data for table `tbl_illegalearnings`
--

INSERT INTO `tbl_illegalearnings` (`id`, `city`, `male`, `female`, `year`, `updatedBy`) VALUES
(1, 'Kampala', 20, 30, 2013, 'aasiimwe'),
(2, 'Dar-es-Salaam', 20, 30, 2013, 'aasiimwe'),
(3, 'Bujumbura', 40, 50, 2013, 'aasiimwe'),
(4, 'Nairobi', 60, 70, 2013, 'aasiimwe'),
(5, 'Kigali', 80, 80, 2013, 'aasiimwe'),
(6, 'Kampala', 90, 100, 2014, 'aasiimwe'),
(7, 'Dar-es-Salaam', 20, 30, 2014, 'aasiimwe'),
(8, 'Bujumbura', 20, 30, 2014, 'aasiimwe'),
(9, 'Nairobi', 20, 30, 2014, 'aasiimwe'),
(10, 'Kigali', 20, 30, 2014, 'aasiimwe'),
(11, 'Kampala', 20, 80, 2015, 'aasiimwe'),
(12, 'Dar-es-Salaam', 20, 30, 2015, 'aasiimwe'),
(13, 'Bujumbura', 80, 100, 2015, 'aasiimwe'),
(14, 'Nairobi', 20, 50, 2015, 'aasiimwe'),
(15, 'Kigali', 20, 30, 2015, 'aasiimwe'),
(16, 'Kampala', 20, 30, 2016, 'aasiimwe'),
(17, 'Dar-es-Salaam', 100, 30, 2016, 'aasiimwe'),
(18, 'Bujumbura', 80, 30, 2016, 'aasiimwe'),
(19, 'Nairobi', 20, 30, 2016, 'aasiimwe'),
(20, 'Kigali', 20, 50, 2016, 'aasiimwe');
