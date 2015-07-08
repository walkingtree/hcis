-- phpMyAdmin SQL Dump
-- version 3.1.3.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 14, 2010 at 07:25 PM
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

--
-- Dumping data for table `reference_data_list`
--

INSERT INTO `reference_data_list` (`CONTEXT`, `ATTR_CODE`, `ATTR_DESC`, `SEQ_NBR`, `CREATED_BY`, `CREATE_DTM`, `MODIFIED_BY`, `MODIFIED_DTM`, `VERSION`) VALUES
('ACTIVE_STATUS', 'N', 'No', NULL, 'Bhavesh', '2010-01-27 20:58:33', NULL, NULL, 0),
('ACTIVE_STATUS', 'Y', 'Yes', NULL, 'Bhavesh', '2010-01-27 20:58:33', NULL, NULL, 0),
('APPONITMENT_TYPE', 'FOLLOW-UP', 'Follow-up', NULL, 'vinay kurudi', '2010-01-07 18:08:40', NULL, NULL, 0),
('APPONITMENT_TYPE', 'PRIMARY', 'Primary', NULL, 'vinay kurudi', '2010-01-07 18:07:49', NULL, NULL, 0),
('APPONITMENT_TYPE', 'SECONDARY', 'Secondary', NULL, 'vinay kurudi', '2010-01-07 18:07:59', NULL, NULL, 0),
('HEIGHT', 'CMS', 'cms', NULL, 'vinay kurudi', '2010-01-07 11:08:18', NULL, NULL, 0),
('HEIGHT', 'INCHES', 'Inches', NULL, 'vinay kurudi', '2010-01-07 11:07:45', NULL, NULL, 0),
('PAT_VACC_SCHEDULE_STATUS', 'COMPLETED', 'Completed', NULL, 'Sandeep Kumar', '2009-12-30 11:55:14', 'Sandeep Kumar', '2009-12-30 11:55:14', 0),
('PAT_VACC_SCHEDULE_STATUS', 'NOT_STARTED', 'Not started', NULL, 'Sandeep Kumar', '2009-12-30 11:55:14', 'Sandeep Kumar', '2009-12-30 11:55:14', 0),
('PAT_VACC_SCHEDULE_STATUS', 'PARTIALLY_COMPLETED', 'Partially completed', NULL, 'Sandeep Kumar', '2009-12-30 11:55:14', 'Sandeep Kumar', '2009-12-30 11:55:14', 0),
('PCT_ABS_IND', 'ABSOLUTE', 'Absolute', NULL, 'Bhavesh', '2010-01-02 12:47:30', NULL, NULL, 0),
('PCT_ABS_IND', 'PERCENTAGE', 'Percentage', NULL, 'Bhavesh', '2010-01-02 12:47:44', NULL, NULL, 0),
('PERIOD_IND', 'D', 'Days', NULL, 'vinay kurudi', '2010-01-05 11:27:51', NULL, NULL, 0),
('PERIOD_IND', 'H', 'Hours', NULL, 'vinay kurudi', '2010-01-05 11:27:35', NULL, NULL, 0),
('PERIOD_IND', 'M', 'Months', NULL, 'vinay kurudi', '2010-01-05 11:28:17', NULL, NULL, 0),
('PERIOD_IND', 'W', 'Weeks', NULL, 'vinay kurudi', '2010-01-05 11:28:06', NULL, NULL, 0),
('PERIOD_IND', 'Y', 'Years', NULL, 'vinay kurudi', '2010-01-05 11:28:31', NULL, NULL, 0),
('REFFERAL_TYPE', 'CLINIC', 'Clinic', NULL, 'SREEKANTH', '2009-12-30 11:55:14', 'SREEKANTH', '2009-12-30 11:55:14', 0),
('REFFERAL_TYPE', 'DOCTOR', 'Doctor', NULL, 'SREEKANTH', '2009-12-30 11:53:42', 'SREEKANTH', '2009-12-30 11:53:42', 0),
('REFFERAL_TYPE', 'HOSPITAL', 'Hospital', NULL, 'SREEKANTH', '2009-12-30 11:53:42', 'SREEKANTH', '2009-12-30 11:53:42', 0),
('REFFERAL_TYPE', 'OLD-PAT', 'Old Patient', NULL, 'SREEKANTH', '2009-12-30 11:55:14', 'SREEKANTH', '2009-12-30 11:55:14', 0),
('REPORT', 'AppointmentSummary', 'Appointment Summary', NULL, 'Bhavesh', '2010-01-12 18:40:51', NULL, NULL, 0),
('REPORT', 'DailyAppointments', 'Daily Appointments', NULL, 'Bhavesh', '2010-01-11 11:16:19', NULL, NULL, 0),
('REPORT', 'DailyPatientWaitTime', 'Daily Patient Wait Time', NULL, 'Bhavesh', '2010-01-18 17:53:21', NULL, NULL, 1),
('REPORT', 'DepartmentWiseRevenue', 'Department Wise Revenue', NULL, 'manish', '2010-01-10 11:33:58', NULL, NULL, 0),
('REPORT', 'DoctorwiseRevenueDetail', 'Doctorwise Revenue Detail', NULL, 'Bhavesh', '2009-08-26 18:08:19', NULL, NULL, 0),
('REPORT', 'OPDInflow', 'OPD Inflow', NULL, 'Bhavesh', '2010-01-18 10:53:18', NULL, NULL, 0),
('REPORT', 'OPDPatientBill', 'OPD Patient Bill', NULL, 'Bhavesh', '2010-01-18 10:30:02', NULL, NULL, 0),
('REPORT', 'ServiceWiseRevenue', 'Service Wise Revenue', NULL, 'Bhavesh', '2010-01-12 18:36:24', NULL, NULL, 0),
('ROSTER_ENTITIES', 'DOCTOR', 'Doctor', 1, 'vinaykurudi', '2010-02-09 17:33:32', NULL, NULL, 0),
('VACCINATION', 'ANTHRAX', 'Anthrax', NULL, 'vinay kurudi', '2010-01-05 11:19:29', NULL, NULL, 0),
('VACCINATION', 'DTAP', 'DTaP', NULL, 'vinay kurudi', '2010-01-05 11:19:33', NULL, NULL, 0),
('VACCINATION', 'HEPATITIS-A', 'Hepatitis A', NULL, 'vinay kurudi', '2010-01-05 11:16:04', NULL, NULL, 0),
('VACCINATION', 'HEPATITIS-B', 'Hepatitis B', NULL, 'vinay kurudi', '2010-01-05 11:16:30', NULL, NULL, 0),
('VACCINATION', 'POLIO', 'Polio', NULL, 'vinay kurudi', '2010-01-05 11:19:59', NULL, NULL, 0),
('VACCINATION', 'RABIES', 'Rabies ', NULL, 'vinay kurudi', '2010-01-05 11:22:57', NULL, NULL, 0),
('VACCINATION', 'TETANUS', 'Tetanus/Diptheria/(Pertussis)', NULL, 'vinay kurudi', '2010-01-05 11:22:17', NULL, NULL, 0),
('VACCINATION_ACTIVE_FLAG', 'N', 'In-active', NULL, 'sandeep Kumar', '2010-01-05 11:19:33', NULL, NULL, 0),
('VACCINATION_ACTIVE_FLAG', 'Y', 'Active', NULL, 'sandeep Kumar', '2010-01-05 11:19:33', NULL, NULL, 0),
('VACCINATION_TYPE', 'BOOSER', 'Booster', NULL, 'vinay kurudi', '2010-01-05 11:26:18', NULL, NULL, 0),
('VACCINATION_TYPE', 'MANDATORY', 'Mandatory', NULL, 'vinay kurudi', '2010-01-05 11:24:07', NULL, NULL, 0),
('VACCINATION_TYPE', 'OPTIONAL', 'Optional', NULL, 'vinay kurudi', '2010-01-05 11:24:12', NULL, NULL, 0),
('WEIGHT', 'KGS', 'kgs', NULL, 'vinay kurudi', '2010-01-07 11:11:28', NULL, NULL, 0),
('WEIGHT', 'POUNDS', 'Pounds', NULL, 'vinay kurudi', '2010-01-07 11:11:05', NULL, NULL, 0),
('ROSTER_MODE', 'DAILY', 'Daily', 'N', NULL, 'manish', '0000-00-00 00:00:00', NULL, NULL, 0),
('ROSTER_MODE', 'MONTHLY', 'Monthly', 'N', NULL, 'manish', '0000-00-00 00:00:00', NULL, NULL, 0),
('ROSTER_MODE', 'WEEKLY', 'Weekly', 'N', NULL, 'manish', '0000-00-00 00:00:00', NULL, NULL, 0);

