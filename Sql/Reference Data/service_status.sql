-- phpMyAdmin SQL Dump
-- version 3.0.1.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 26, 2009 at 12:03 PM
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
-- Dumping data for table `service_status`
--

INSERT INTO `service_status` (`SERVICE_STATUS_CODE`, `DESCRIPTION`, `ACTIVE`) VALUES

('CREATED', 'Created', 1),
('ACTIVE', 'Active', 1),
('SUSPENDED', 'Suspended', 1),
('EXPIRED', 'Expired', 1);

