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
-- Dumping data for table `department`
--

INSERT INTO `department` (`DEPARTMENT_CODE`, `DEPARTMENT_NAME`, `ACCOUNT_NUMBER`, `ACTIVE`, `CREATED_DTM`, `CREATED_BY`, `LAST_MODIFIED_DTM`, `MODIFIED_BY`) VALUES
('ADMIN', 'Administrative', NULL, 1, NULL, NULL, NULL, NULL),
('ANESTHESIOLOGY', 'Anesthesiology', NULL, 1, NULL, NULL, NULL, NULL),
('CARDIOLOGY', 'Cardiology', NULL, 1, NULL, NULL, NULL, NULL),
('DENTAL', 'Dental', NULL, 1, NULL, NULL, NULL, NULL),
('ENDOCRINOLOGY', 'Endocrinology', NULL, 1, NULL, NULL, NULL, NULL),
('ENT', 'ENT', NULL, 1, NULL, NULL, NULL, NULL),
('EYE', 'Eye', NULL, 1, NULL, NULL, NULL, NULL),
('GASTROENTEROLOGY', 'Gastroenterology ', NULL, 1, NULL, NULL, NULL, NULL),
('GYN_OBS', 'Gynaecology and Obstetrics ', NULL, 1, NULL, NULL, NULL, NULL),
('MEDICINE', 'Medicine', NULL, 1, NULL, NULL, NULL, NULL),
('NEPHROLOGY', 'Nephrology', NULL, 1, NULL, NULL, NULL, NULL),
('NEUROLOGY', 'Neurology', NULL, 1, NULL, NULL, NULL, NULL),
('ONCOLOGY', 'Oncology', NULL, 1, NULL, NULL, NULL, NULL),
('ORTHOPEDICS', 'Orthopedics', NULL, 1, NULL, NULL, NULL, NULL),
('PATHOLOGY', 'Pathology', NULL, 1, NULL, NULL, NULL, NULL),
('PEDIATRICS', 'Pediatrics', NULL, 1, NULL, NULL, NULL, NULL),
('PSYCHIATRY', 'Psychiatry', NULL, 1, NULL, NULL, NULL, NULL),
('RADIOLOGY_IMAGING', 'Radiology & Imaging', NULL, 1, NULL, NULL, NULL, NULL),
('RHEUMATOLOGY', 'Rheumatology', NULL, 1, NULL, NULL, NULL, NULL),
('SKIN_VD', 'Skin & V.D.', NULL, 1, NULL, NULL, NULL, NULL),
('SURGERY', 'Surgery', NULL, 1, NULL, NULL, NULL, NULL),
('UROLOGY', 'Urology', NULL, 1, NULL, NULL, NULL, NULL);
