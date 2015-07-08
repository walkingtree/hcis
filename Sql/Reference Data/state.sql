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
-- Dumping data for table `state`
--


--
-- Dumping data for table `state`
--

INSERT INTO `state` (`COUNTRY_CODE`, `STATE_CODE`, `DESCRIPTION`, `ACTIVE`, `VERSION`) VALUES
('208', '1000000', 'Andaman and Nicobar Islands', 1, 0),
('208', '1000001', 'Andhra Pradesh', 1, 0),
('208', '1000002', 'Arunachal Pradesh', 1, 0),
('208', '1000003', 'Assam', 1, 0),
('208', '1000004', 'Bihar', 1, 0),
('208', '1000005', 'Chandigarh', 1, 0),
('208', '1000006', 'Chhattisgarh ', 1, 0),
('208', '1000007', 'Daman and Diu', 1, 0),
('208', '1000008', 'Delhi', 1, 0),
('208', '1000009', 'Dadra and Nagar Haveli', 1, 0),
('208', '1000010', 'Goa', 1, 0),
('208', '1000011', 'Gujarat', 1, 0),
('208', '1000012', 'Himachal Pradesh', 1, 0),
('208', '1000013', 'Haryana', 1, 0),
('208', '1000014', 'Jharkhand', 1, 0),
('208', '1000015', 'Jammu and Kashmir', 1, 0),
('208', '1000016', 'Karnataka', 1, 0),
('208', '1000017', 'Kerala', 1, 0),
('208', '1000018', 'Lakshadweep', 1, 0),
('208', '1000019', 'Maharashtra', 1, 0),
('208', '1000020', 'Meghalaya', 1, 0),
('208', '1000021', 'Manipur', 1, 0),
('208', '1000022', 'Madhya Pradesh', 1, 0),
('208', '1000023', 'Mizoram', 1, 0),
('208', '1000024', 'Nagaland', 1, 0),
('208', '1000025', 'Orissa', 1, 0),
('208', '1000026', 'Punjab', 1, 0),
('208', '1000027', 'Puducherry', 1, 0),
('208', '1000028', 'Rajasthan', 1, 0),
('208', '1000029', 'Sikkim', 1, 0),
('208', '1000030', 'Tamil Nadu', 1, 0),
('208', '1000031', 'Tripura', 1, 0),
('208', '1000032', 'Uttarakhand', 1, 0),
('208', '1000033', 'Uttar Pradesh', 1, 0),
('208', '1000034', 'West Bengal', 1, 0),
('238', '1000035', 'Johor', 1, 0),
('238', '1000036', 'Kedah', 1, 0),
('238', '1000037', 'Kelantan', 1, 0),
('238', '1000038', 'Malacca', 1, 0),
('238', '1000039', 'Negeri Sembilan', 1, 0),
('238', '1000040', 'Pahang', 1, 0),
('238', '1000041', 'Penang', 1, 0),
('238', '1000042', 'Perak', 1, 0),
('238', '1000043', 'Perlis', 1, 0),
('238', '1000044', 'Selangor', 1, 0),
('238', '1000045', 'Terengganu', 1, 0),
('238', '1000046', 'Sabah', 1, 0),
('238', '1000047', 'Sarawak', 1, 0),
('238', '1000048', 'Kuala Lumpur', 1, 0),
('238', '1000049', 'Labuan', 1, 0),
('238', '1000050', 'Putrajaya', 1, 0),
('100', '118', 'Alaska', 1, 0),
('100', '114', 'Alabama', 1, 0),
('100', '144', 'Arkansas', 1, 0),
('100', '145', 'Arizona', 1, 0),
('100', '103', 'California', 1, 0),
('100', '149', 'Colorado', 1, 0),
('100', '102', 'Connecticut', 1, 0),
('100', '115', 'Delaware', 1, 0),
('100', '113', 'Florida', 1, 0),
('100', '120', 'Georgia', 1, 0),
('100', '150', 'Hawaii', 1, 0),
('100', '124', 'Iowa', 1, 0),
('100', '147', 'Idaho', 1, 0),
('100', '127', 'Illinois', 1, 0),
('100', '125', 'Indiana', 1, 0),
('100', '138', 'Kansas', 1, 0),
('100', '130', 'Kentucky', 1, 0),
('100', '131', 'Louisiana', 1, 0),
('100', '104', 'Massachusetts', 1, 0),
('100', '154', 'Maryland', 1, 0),
('100', '105', 'Maine', 1, 0),
('100', '152', 'Michigan', 1, 0),
('100', '123', 'Minnesota', 1, 0),
('100', '137', 'Missouri', 1, 0),
('100', '135', 'Mississippi', 1, 0),
('100', '136', 'Montana', 1, 0),
('100', '129', 'North Carolina', 1, 0),
('100', '121', 'North Dakota', 1, 0),
('100', '139', 'Nebraska', 1, 0),
('100', '106', 'New Hampshire', 1, 0),
('100', '109', 'New Jersey', 1, 0),
('100', '141', 'New Mexico', 1, 0),
('100', '148', 'Nevada', 1, 0),
('100', '108', 'New York', 1, 0),
('100', '126', 'Ohio', 1, 0),
('100', '133', 'Oklahoma', 1, 0),
('100', '142', 'Oregon', 1, 0),
('100', '110', 'Pennsylvania', 1, 0),
('100', '107', 'Rhode Island', 1, 0),
('100', '128', 'South Carolina', 1, 0),
('100', '122', 'South Dakota', 1, 0),
('100', '119', 'Tennessee', 1, 0),
('100', '132', 'Texas', 1, 0),
('100', '146', 'Utah', 1, 0),
('100', '111', 'Virginia', 1, 0),
('100', '155', 'Vermont', 1, 0),
('100', '116', 'Washington', 1, 0),
('100', '153', 'Wisconsin', 1, 0),
('100', '112', 'West Virginia', 1, 0),
('100', '140', 'Wyoming', 1, 0);
