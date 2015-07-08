-- phpMyAdmin SQL Dump
-- version 3.0.1.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 08, 2009 at 09:49 AM
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
-- Dumping data for table `appointment_status`
--

INSERT INTO `appointment_status` (`STATUS_CODE`, `DESCRIPTION`, `ACTIVE`) VALUES
('BOOKED', 'Booked', 1),
('CANCELLED', 'Cancelled', 1),
('CAPTURED', 'Doctor has seen the patient', 1),
('CONFIRMED', 'Confirmed', 1),
('INVALID_ROSTER', 'Invalid Roster', 1),
('RESCHEDULED', 'Rescheduled', 1),
('ROSTER_MODIFIED', 'Roster Modified', 1),
('TOVISIT', 'Patient came to visit ', 1),
('VISITED', 'Patient visited the doctor', 1);
