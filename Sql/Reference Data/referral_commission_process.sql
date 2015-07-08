-- phpMyAdmin SQL Dump
-- version 3.1.3.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 30, 2009 at 01:34 AM
-- Server version: 5.1.33
-- PHP Version: 5.2.9

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `hcisit`
--

-- --------------------------------------------------------

--
-- Table structure for table `referral_commission_process`
--

CREATE TABLE IF NOT EXISTS `referral_commission_process` (
  `COMMISSION_TYPE_CODE` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `COMMISSION_TYPE_DESC` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `ACTIVE` varchar(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`COMMISSION_TYPE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `referral_commission_process`
--

INSERT INTO `referral_commission_process` (`COMMISSION_TYPE_CODE`, `COMMISSION_TYPE_DESC`, `ACTIVE`) VALUES
('APPOINTMENTS', 'Appointments', 'Y'),
('SERVICES', 'Services', 'Y');
