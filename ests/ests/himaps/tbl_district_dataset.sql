-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Nov 25, 2015 at 10:22 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `db_ovcmis_me_v3`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_district`
--

CREATE TABLE IF NOT EXISTS `tbl_district` (
  `districtCode` bigint(20) NOT NULL AUTO_INCREMENT,
  `districtName` varchar(100) NOT NULL,
  `acronym` char(3) DEFAULT NULL,
  `zone` int(11) NOT NULL,
  `tso_service_providers` tinyint(4) NOT NULL,
  `shapeFile` varchar(20) NOT NULL,
  `entryType` varchar(20) NOT NULL,
  `Display` varchar(3) NOT NULL DEFAULT 'Yes',
  `updatedby` varchar(100) NOT NULL,
  PRIMARY KEY (`districtCode`),
  UNIQUE KEY `districtName` (`districtName`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=518 ;

--
-- Dumping data for table `tbl_district`
--

INSERT INTO `tbl_district` (`districtCode`, `districtName`, `acronym`, `zone`, `tso_service_providers`, `shapeFile`, `entryType`, `Display`, `updatedby`) VALUES

(208, 'KUMI', 'KUM', 4, 0, ' ug-1677', 'District', 'Yes', ''),//$districtDataKUMI208
(213, 'KABERAMAIDO', 'KAB', 4, 0, ' ug-1678', 'District', 'Yes', ''),//$districtDataKABERAMAIDO213
(112, 'KAYUNGA', 'KAY', 5, 0, ' ug-1679', 'District', 'Yes', ''),//$districtDataKAYUNGA112
(203, 'IGANGA', 'IGA', 5, 0, ' ug-1680', 'District', 'Yes', ''),//$districtDataIGANGA203_
(205, 'KAMULI', 'KAM', 5, 0, ' ug-1681', 'District', 'Yes', ''),//$districtDataKAMULI205_
(433, 'AMOLATAR', 'AMO', 1, 0, ' ug-1682', 'District', 'Yes', ''),//$districtDataAMOLATAR433_
(416, 'KALIRO', 'KLR', 5, 0, ' ug-1683', 'District', 'Yes', ''),//$districtDataKALIRO416_
(446, 'BUDAKA', 'BDK', 3, 0, ' ug-1684', 'District', 'Yes', ''),//$districtDataBUDAKA446_
(422, 'NAMUTUMBA', 'NAM', 5, 0, ' ug-1685', 'District', 'Yes', ''),//$districtDataNAMUTUMBA422_
(211, 'SOROTI', 'SOR', 4, 0, ' ug-1686', 'District', 'Yes', ''),//$districtDataSOROTI211_
(210, 'PALLISA', 'PAL', 3, 0, ' ug-1688', 'District', 'Yes', ''),//$districtDataPALLISA210_





(447, 'SEMBABULE', 'SMB', 6, 0, ' ug-1689', 'District', 'Yes', ''),//$districtDataSEMBABULE447_
(106, 'MPIGI', 'MPG', 9, 0, ' ug-1690', 'District', 'Yes', ''),//$districtDataMPIGI106_
(101, 'KALANGALA', 'KAL', 9, 0, ' ug-2595', 'District', 'Yes', 'national.mapping'),//$districtDataKALANGALA101_
(204, 'JINJA', 'JNJ', 5, 0, ' ug-2744', 'District', 'Yes', ''),//$districtDataJINJA204_
(301, 'Adjumani', 'ADJ', 8, 0, ' ug-2745', 'District', 'Yes', ''),//$districtDataAdjumani301_
(303, 'ARUA', 'ARA', 8, 0, ' ug-2746', 'District', 'Yes', ''),//$districtDataARUA303_
(432, 'KOBOKO', 'KOB', 8, 0, ' ug-2747', 'District', 'Yes', ''),//$districtDataKOBOKO432_
(449, 'BULISA', 'BLS', 2, 0, ' ug-2748', 'District', 'Yes', ''),//$districtDataBULISA449_
(516, 'Nwoya', 'NWY', 1, 0, ' ug-2749', 'District', 'Yes', ''),//$districtDataNwoya516_
(310, 'NEBBI', 'NEB', 8, 0, ' ug-2750', 'District', 'Yes', ''),//$districtDataNEBBI310_
(309, 'MOYO', 'MOY', 8, 0, ' ug-2751', 'District', 'Yes', ''),//$districtDataMOYO309_
(313, 'YUMBE', 'YMB', 8, 0, ' ug-2752', 'District', 'Yes', ''),//$districtDataYUMBE313_
(103, 'KIBOGA', 'KBA', 6, 0, ' ug-2753', 'District', 'Yes', 'national.mapping'),//$districtDataKIBOGA103_
(102, 'KAMPALA', 'KLA', 6, 0, ' ug-2754', 'District', 'Yes', 'national.mapping'),//$districtDataKAMPALA102_
(444, 'MARACHA', 'MAR', 8, 0, ' ug-2755', 'District', 'Yes', ''),//$districtDataMARACHA444_
(109, 'NAKASONGOLA', 'NAK', 6, 0, ' ug-2756', 'District', 'Yes', ''),//$districtDataNAKASONGOLA109_
(113, 'WAKISO', 'WAK', 6, 0, ' ug-2757', 'District', 'Yes', ''),//$districtDataWAKISO113_
(104, 'LUWERO', 'LUW', 6, 0, ' ug-2758', 'District', 'Yes', 'national.mapping'),//$districtDataLUWERO104_
(107, 'MUBENDE', 'MUB', 6, 0, ' ug-2759', 'District', 'Yes', 'national.mapping'),//$districtDataMUBENDE107_
(437, 'MITYANA', 'MIT', 6, 0, ' ug-2760', 'District', 'Yes', ''),//$districtDataMITYANA437_
(440, 'NAKASEKE', 'NKK', 6, 0, ' ug-2761', 'District', 'Yes', ''),//$districtDataNAKASEKE440_
(451, 'LAMWO', 'LMW', 1, 0, ' ug-2762', 'District', 'Yes', ''),//$districtDataLAMWO451_
(302, 'APAC', 'APC', 1, 0, ' ug-2763', 'District', 'Yes', ''),//$districtDataAPAC451_
(304, 'GULU', 'GUL', 1, 0, ' ug-2764', 'District', 'Yes', ''),//$districtDataGULU304_
(307, 'LIRA', 'LRA', 1, 0, ' ug-2765', 'District', 'Yes', ''),//$districtDataLIRA307_
(424, 'DOKOLO', 'DKL', 1, 0, ' ug-2766', 'District', 'Yes', ''),//$districtDataDOKOLO424_
(312, 'PADER', 'PDR', 1, 0, ' ug-2767', 'District', 'Yes', ''),//$districtDataPADER312_
(409, 'MASINDI', 'MAS', 2, 0, ' ug-2768', 'District', 'Yes', ''),//$districtDataMASINDI409_
(434, 'OYAM', 'OYM', 1, 0, ' ug-2769', 'District', 'Yes', ''),//$districtDataOYAM434_
(401, 'BUNDIBUGYO', 'BUN', 2, 0, ' ug-2770', 'District', 'Yes', ''),//$districtDataBUNDIBUGYO401_
(403, 'HOIMA', 'HMA', 2, 0, ' ug-2771', 'District', 'Yes', ''),//$districtDataHOIMA403_
(405, 'KABAROLE', 'KBR', 2, 0, ' ug-2772', 'District', 'Yes', ''),//$districtDataKABAROLE405_
(415, 'KYENJOJO', 'KYE', 2, 0, ' ug-2773', 'District', 'Yes', ''),//$districtDataKYENJOJO415_
(407, 'KIBAALE', 'KIB', 2, 0, ' ug-2774', 'District', 'Yes', ''),//$districtDataKIBAALE407_
(417, 'BUKWO', 'BKW', 3, 0, ' ug-2776', 'District', 'Yes', ''),//$districtDataBUKWO417_
(215, 'SIRONKO', 'SIR', 3, 0, ' ug-2777', 'District', 'Yes', ''),//$districtDataSIRONKO215_
(209, 'MBALE', 'MBA', 3, 0, ' ug-2778', 'District', 'Yes', ''),//$districtDataMBALE209_
(425, 'MANAFWA', 'MNF', 3, 0, ' ug-2779', 'District', 'Yes', ''),//$districtDataMANAFWA425_
(201, 'BUGIRI', 'BUG', 5, 0, ' ug-2780', 'District', 'Yes', ''),//$districtDataBUGIRI201_
(202, 'BUSIA', 'BSA', 3, 0, ' ug-2781', 'District', 'Yes', ''),//$districtDataBUSIA202_
(423, 'BUTALEJA', 'BTJ', 3, 0, ' ug-2782', 'District', 'Yes', ''),//$districtDataBUTALEJA423_
(214, 'MAYUGE', 'MAY', 5, 0, ' ug-2783', 'District', 'Yes', ''),//$districtDataMAYUGE_214
(212, 'TORORO', 'TOR', 3, 0, ' ug-2784', 'District', 'Yes', ''),//$districtDataTORORO212_
(207, 'KATAKWI', 'KAT', 4, 0, ' ug-2785', 'District', 'Yes', ''),//$districtDataKATAKWI207_
(306, 'KOTIDO', 'KTD', 4, 0, ' ug-2786', 'District', 'Yes', ''),//$districtDataKOTIDO306_
(428, 'AMURIA', 'AMA', 4, 0, ' ug-2787', 'District', 'Yes', ''),//$districtDataAMURIA428_
(491, 'Kaabong', NULL, 4, 0, ' ug-2788', 'District', 'Yes', ''),//$districtDataKaabong491_
(445, 'ABIM', 'ABM', 4, 0, ' ug-2789', 'District', 'Yes', ''),//$districtDataABIM445_
(308, 'MOROTO', 'MRT', 4, 0, ' ug-2790', 'District', 'Yes', ''),//$districtDataMOROTO308_
(311, 'NAKAPIRIPIRIT', 'NKP', 4, 0, ' ug-2791', 'District', 'Yes', ''),//$districtDataNAKAPIRIPIRIT311_
(110, 'RAKAI', 'RAK', 9, 0, ' ug-3381', 'District', 'Yes', ''),//$districtDataRAKAI110_
(105, 'MASAKA', 'MSK', 9, 0, ' ug-3382', 'District', 'Yes', 'national.mapping'),//$districtDataMASAKA105_
(402, 'BUSHENYI', 'BUS', 7, 0, ' ug-3383', 'District', 'Yes', ''),//$districtDataBUSHENYI402_
(406, 'KASESE', 'KSE', 2, 0, ' ug-3384', 'District', 'Yes', ''),//$districtDataKASESE406_
(413, 'KAMWENGE', 'KMG', 2, 0, ' ug-3385', 'District', 'Yes', ''),//$districtDataKAMWENGE413_
(442, 'IBANDA', 'IBD', 7, 0, ' ug-3386', 'District', 'Yes', ''),//$districtDataIBANDA442_
(436, 'ISINGIRO', 'ISI', 7, 0, ' ug-3387', 'District', 'Yes', ''),//$districtDataISINGIRO436_
(435, 'KIRUHURA', 'KHR', 7, 0, ' ug-3388', 'District', 'Yes', ''),//$districtDataKIRUHURA435_
(411, 'NTUNGAMO', 'NTU', 7, 0, ' ug-3389', 'District', 'Yes', ''),//$districtDataNTUNGAMO411_
(412, 'RUKUNGIRI', 'RUK', 7, 0, ' ug-3390', 'District', 'Yes', ''),//$districtDataRUKUNGIRI412_
(410, 'MBARARA', 'MBR', 7, 0, ' ug-3391', 'District', 'Yes', ''),//$districtDataMBARARA410_
(404, 'KABALE', 'KBL', 7, 0, ' ug-3392', 'District', 'Yes', ''),//$districtDataKABALE404_
(408, 'KISORO', 'KIS', 7, 0, ' ug-3393', 'District', 'Yes', ''),//$districtDataKISORO408_
(414, 'KANUNGU', 'KAN', 7, 0, ' ug-3394', 'District', 'Yes', ''),//$districtDataKANUNGU414_
(427, 'ZOMBO', 'ZMB', 8, 0, ' ug-7048', 'District', 'Yes', ''),//$districtDataZOMBO427_
(467, 'AMURU', 'AMR', 1, 0, ' ug-7049', 'District', 'Yes', ''),//$districtDataAMURU467_
(305, 'KITGUM', 'KTG', 1, 0, ' ug-7051', 'District', 'Yes', ''),//$districtDataKITGUM305_
(474, 'Kiryandongo', 'KRY', 2, 0, ' ug-7052', 'District', 'Yes', ''),//$districtDataKiryandongo474_
(493, 'Kole', NULL, 1, 0, ' ug-7053', 'District', 'Yes', ''),//$districtDataKole493_
(478, 'Alebtong', NULL, 1, 0, ' ug-7054', 'District', 'Yes', ''),//$districtDataAlebtong478_
(509, 'Otuke', NULL, 1, 0, ' ug-7055', 'District', 'Yes', ''),//$districtDataOtuke509_
(477, 'Agago', NULL, 1, 0, ' ug-7056', 'District', 'Yes', ''),//$districtDataAgago477_
(504, 'Napak', NULL, 4, 0, ' ug-7057', 'District', 'Yes', ''),//$districtDataNapak504_
(479, 'Amudat', NULL, 4, 0, ' ug-7058', 'District', 'Yes', ''),//$districtDataAmudat479_
(506, 'Ntoroko', NULL, 2, 0, ' ug-7059', 'District', 'Yes', ''),//$districtDataNtoroko506_
(495, 'Kyegegwa', NULL, 2, 0, ' ug-7060', 'District', 'Yes', ''),//$districtDataKyegegwa495_
(511, 'Rubirizi', NULL, 7, 0, ' ug-7061', 'District', 'Yes', ''),//$districtDataRubirizi511_
(501, 'Mitooma', NULL, 7, 0, ' ug-7062', 'District', 'Yes', ''),//$districtDataMitooma501_
(513, 'Sheema', NULL, 7, 0, ' ug-7063', 'District', 'Yes', ''),//$districtDataSheema513_
(481, 'Buhweju', NULL, 7, 0, ' ug-7064', 'District', 'Yes', ''),//$districtDataBuhweju481_
(475, 'Lwengo', 'LWG', 6, 0, ' ug-7065', 'District', 'Yes', ''),//$districtDataLwengo475_
(450, 'LYANTONDE', 'LYT', 9, 0, ' ug-7066', 'District', 'Yes', ''),//$districtDataLYANTONDE450_
(482, 'Bukomansimbi', NULL, 9, 0, ' ug-7067', 'District', 'Yes', ''),//$districtDataBukomansimbi482_
(472, 'Kalungu', 'KLN', 9, 0, ' ug-7068', 'District', 'Yes', ''),//$districtDataKalungu472_
(484, 'Butambala', NULL, 9, 0, ' ug-7069', 'District', 'Yes', ''),//$districtDataButambala484_
(488, 'Gomba', NULL, 9, 0, ' ug-7070', 'District', 'Yes', ''),//$districtDataGomba488_
(494, 'Kyankwanzi', NULL, 2, 0, ' ug-7072', 'District', 'Yes', ''),//$districtDataKyankwanzi494_
(469, 'Buikwe', 'BUK', 6, 0, ' ug-7073', 'District', 'Yes', ''),//$districtDataBuikwe469_
(485, 'Buvuma', NULL, 6, 0, ' ug-7074', 'District', 'Yes', ''),//$districtDataBuvuma485_
(503, 'Namayingo', NULL, 5, 0, ' ug-7075', 'District', 'Yes', ''),//$districtDataNamayingo503_
(497, 'Luuka', NULL, 5, 0, ' ug-7076', 'District', 'Yes', ''),//$districtDataLuuka497_
(486, 'Buyende', NULL, 5, 0, ' ug-7078', 'District', 'Yes', ''),//$districtDataBuyende486_
(512, 'Serere', NULL, 4, 0, ' ug-7079', 'District', 'Yes', ''),//$districtDataSerere512_
(505, 'Ngora', NULL, 4, 0, ' ug-7080', 'District', 'Yes', ''),//$districtDataNgora505_
(421, 'BUKEDEA', 'BKD', 4, 0, ' ug-7081', 'District', 'Yes', ''),//$districtDataBUKEDEA421_
(492, 'Kibuku', NULL, 3, 0, ' ug-7082', 'District', 'Yes', ''),//$districtDataKibuku492_7082--done
(483, 'Bulambuli', NULL, 3, 0, ' ug-7083', 'District', 'Yes', ''),//$districtDataBulambuli483_7083--done
(206, 'KAPCHORWA', 'KAP', 3, 0, ' ug-7084', 'District', 'Yes', ''),//$districtDataKAPCHORWA206_2775--done
(476, 'Kween', 'KWN', 3, 0, ' ug-7084', 'District', 'Yes', ''),//$districtDataKween476_7084-------done
(448, 'BUDUDA', 'BDD', 3, 0, ' ug-7086', 'District', 'Yes', ''),//$districtDataBududa448_7086;--done
(108, 'MUKONO', 'MUK', 5, 0, 'ug-1687', 'District', 'Yes', ''); //$districtDataMukono108_1687;--done

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
