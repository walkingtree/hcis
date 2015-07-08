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
-- Dumping data for table `app_entities`
--

INSERT INTO `app_entities` (`ENTITY_TYPE`, `ENTITY_NAME`, `DESCRIPTION`, `ACTIVE`) VALUES
('ADMINISTRATOR', 'Administrator', 'Administrator', 1),
('DOCTOR', 'Doctor', 'Doctor', 1),
('NURSE', 'Nurse', 'Nurse', 1),
('PATIENT', 'Patient', 'Patient', 1),
('WARD_BOY', 'Ward Boy', 'Ward Boy', 1);
