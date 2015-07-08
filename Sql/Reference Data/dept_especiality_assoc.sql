-- phpMyAdmin SQL Dump
-- version 2.11.9.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 09, 2010 at 09:57 PM
-- Server version: 5.0.67
-- PHP Version: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `hcisit`
--

--
-- Dumping data for table `dept_especiality_assoc`
--

INSERT INTO `dept_especiality_assoc` (`DEPARTMENT_CODE`, `ESPECIALITY_CODE`, `ACTIVE`, `LAST_MODIFIED_DTM`, `USER_ID`, `VERSION`) VALUES
('CARDIOLOGY', 'CARDIOLOGISTS', 'Y', '2010-01-09 19:54:04', 'vinay kurudi', 0),
('NEUROLOGY', 'NEUROLOGISTS', 'Y', '2010-01-09 19:54:54', 'vinaykurudi', 0),
('PEDIATRICS', 'PEDIATRICIANS', 'Y', '2010-01-09 19:54:26', 'vinay kurudi', 0),
('RADIOLOGY_IMAGING', 'RADIOLOGISTS', 'Y', '2010-01-09 19:55:36', 'vinay kurudi', 0);
