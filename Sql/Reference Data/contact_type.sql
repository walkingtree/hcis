-- phpMyAdmin SQL Dump
-- version 3.0.1.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 26, 2009 at 11:58 AM
-- Server version: 5.0.67
-- PHP Version: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `hcis`
--

--
-- Dumping data for table `contact_type`
--

INSERT INTO `contact_type` (`CONTACT_TYPE_IND`, `DESCRIPTION`, `ACTIVE`) VALUES
('CURRENT', 'CURRENT', 1),
('EMERGENCY_PRIMARY', 'EMERGENCY_PRIMARY', 1),
('EMERGENCY_SECONDARY', 'EMERGENCY_SECONDARY', 1),
('PERMANENT', 'PERMANENT', 1),
('SPONSOR', 'SPONSOR', 1);
