-- phpMyAdmin SQL Dump
-- version 3.0.1.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 26, 2009 at 11:57 AM
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
-- Dumping data for table `blood_group`
--

INSERT INTO `blood_group` (`BLOOD_GROUP_CODE`, `DESCRIPTION`, `ACTIVE`) VALUES
('ABNEG', 'AB -ve', 1),
('ABPOS', 'AB +ve', 1),
('ANEG', 'A -ve', 1),
('APOS', 'A +ve', 1),
('BNEG', 'B -ve', 1),
('BPOS', 'B +ve', 1),
('ONEG', 'O -ve', 1),
('OPOS', 'O +ve', 1);
