-- phpMyAdmin SQL Dump
-- version 3.2.0.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 30, 2009 at 06:37 PM
-- Server version: 5.1.37
-- PHP Version: 5.3.0

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `ibill`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill_account`
--

CREATE TABLE IF NOT EXISTS `bill_account` (
  `account_id` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `client_name` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `acct_holder_name` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `bill_address` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`account_id`,`client_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bill_account`
--

INSERT INTO `bill_account` (`account_id`, `client_name`, `acct_holder_name`, `bill_address`, `email`, `phone`) VALUES
('100001', 'OPD', 'Alok Ranjan', 'F-503, Block-12, RTP, Malaysian Township', 'alok.ranjan@walkingtree.in', '9848473539');

-- --------------------------------------------------------

--
-- Table structure for table `bill_activity`
--

CREATE TABLE IF NOT EXISTS `bill_activity` (
  `activity_type_id` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `activity_type_desc` varchar(1024) COLLATE utf8_unicode_ci NOT NULL,
  `activity_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`activity_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bill_activity`
--

INSERT INTO `bill_activity` (`activity_type_id`, `activity_type_desc`, `activity_order`) VALUES
('COMPLETED', 'Process successfully finished execution ', 3),
('ERRORED', 'Process failed to bill the billable items', 2),
('STARTED', 'Process started execution ', 1);

-- --------------------------------------------------------

--
-- Table structure for table `bill_activity_details`
--

CREATE TABLE IF NOT EXISTS `bill_activity_details` (
  `billing_activity_details_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bill_nbr` bigint(20) NOT NULL,
  `activity_type_cd` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `activity_time` datetime NOT NULL,
  `remarks` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`billing_activity_details_id`),
  KEY `activity_type_cd` (`activity_type_cd`),
  KEY `bill_nbr` (`bill_nbr`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=41 ;

--
-- Dumping data for table `bill_activity_details`
--


-- --------------------------------------------------------

--
-- Table structure for table `bill_info`
--

CREATE TABLE IF NOT EXISTS `bill_info` (
  `BILL_NBR` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_ID` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `CLIENT_NAME` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `BILL_DATE` date NOT NULL,
  `BILL_AMT` double NOT NULL,
  `CURRENT_BALANCE` double DEFAULT NULL,
  `DUE_DATE` date DEFAULT NULL,
  `PAID_AMOUNT` double DEFAULT NULL,
  `PREVIOUS_BALANCE` double DEFAULT NULL,
  `LAST_MODIFIED_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `BILL_FROM_DATE` date DEFAULT NULL,
  `BILL_TO_DATE` date DEFAULT NULL,
  `DUPLICATE_BILL_PRINT_COUNT` int(11) DEFAULT NULL,
  PRIMARY KEY (`BILL_NBR`),
  KEY `ACCOUNT_ID_CLIENT_NAME` (`ACCOUNT_ID`,`CLIENT_NAME`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=19 ;

--
-- Dumping data for table `bill_info`
--


-- --------------------------------------------------------

--
-- Table structure for table `bill_item_details`
--

CREATE TABLE IF NOT EXISTS `bill_item_details` (
  `bill_number` bigint(20) NOT NULL,
  `process_name` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `sub_process_name` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `item_sequence_nbr` int(11) NOT NULL,
  `item_name` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `item_count` int(11) DEFAULT NULL,
  `item_price` double DEFAULT NULL,
  `discounts` double DEFAULT NULL,
  `total_amount` double NOT NULL,
  PRIMARY KEY (`bill_number`,`process_name`,`sub_process_name`,`item_sequence_nbr`),
  KEY `process_name` (`process_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bill_item_details`
--


-- --------------------------------------------------------

--
-- Table structure for table `bill_process_event`
--

CREATE TABLE IF NOT EXISTS `bill_process_event` (
  `event_sequence_nbr` bigint(20) NOT NULL AUTO_INCREMENT,
  `bill_nbr` bigint(20) NOT NULL,
  `event_status` varchar(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'C',
  `event_creation_tm` datetime NOT NULL,
  `process_name` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `last_modified_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`event_sequence_nbr`),
  KEY `bill_nbr` (`bill_nbr`),
  KEY `process_name` (`process_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

--
-- Dumping data for table `bill_process_event`
--


-- --------------------------------------------------------

--
-- Table structure for table `bill_process_routing`
--

CREATE TABLE IF NOT EXISTS `bill_process_routing` (
  `CLIENT_NAME` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `PROCESS_NAME` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `PROCESS_SEQ_NBR` int(11) NOT NULL,
  `OVERRIDE_IMPL_CLASS_NAME` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`CLIENT_NAME`,`PROCESS_NAME`),
  KEY `PROCESS_NAME` (`PROCESS_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


--
-- Dumping data for table `bill_process_routing`
--

INSERT INTO `bill_process_routing` (`CLIENT_NAME`, `PROCESS_NAME`, `PROCESS_SEQ_NBR`, `OVERRIDE_IMPL_CLASS_NAME`) VALUES
('DIR', 'ACCOUNTING', 2, NULL),
('DIR', 'SERVICES', 1, NULL),
('EMR', 'ACCOUNTING', 2, NULL),
('IPD', 'ACCOUNTING', 3, NULL),
('IPD', 'BED', 2, NULL),
('IPD', 'SERVICES', 1, NULL),
('OPD', 'ACCOUNTING', 2, NULL),
('OPD', 'SERVICES', 1, NULL),
('PAT', 'ACCOUNTING', 2, NULL),
('PAT', 'SERVICES', 1, NULL),
('PHA', 'PHARMACY', 1, NULL),
('IPD', 'PHARMACY', 4, NULL),
('OPD', 'PHARMACY', 3, NULL),
('PAT', 'PHARMACY', 3, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `bill_register`
--

CREATE TABLE IF NOT EXISTS `bill_register` (
  `PROCESS_NAME` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `CREATE_DETAILS_FLAG` varchar(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'N',
  `SYNC_ASYNC_TYPE` varchar(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'S',
  `ACTIVE_FLAG` varchar(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Y',
  `DISPLAY_SEQUENCE_NBR` int(11) NOT NULL,
  `DISPLAY_DESCRIPTION` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `IMPL_CLASS_NAME` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`PROCESS_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bill_register`
--

INSERT INTO `bill_register` (`PROCESS_NAME`, `CREATE_DETAILS_FLAG`, `SYNC_ASYNC_TYPE`, `ACTIVE_FLAG`, `DISPLAY_SEQUENCE_NBR`, `DISPLAY_DESCRIPTION`, `IMPL_CLASS_NAME`) VALUES
('ACCOUNTING', 'Y', 'S', 'Y', 2, 'Transaction and Account details', 'in.wtc.hcis.billing.process.AccountingProcessImpl'),
('BED', 'Y', 'S', 'Y', 2, 'Bed Usage', 'in.wtc.hcis.billing.process.BedBillngProcessImpl'),
('PHARMACY', 'Y', 'S', 'Y', 4, 'Pharmacy Information', 'in.wtc.hcis.billing.process.PharmacyProcessImpl'),
('SERVICES', 'Y', 'S', 'Y', 1, 'Services billed', 'in.wtc.hcis.billing.process.ServiceProcessImpl');

-- --------------------------------------------------------

--
-- Table structure for table `bill_setting`
--

CREATE TABLE IF NOT EXISTS `bill_setting` (
  `ATTR_NAME` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `ATTR_VALUE` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `DATA_TYPE` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `ATTR_DESC` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ATTR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bill_setting`
--

INSERT INTO `bill_setting` (`ATTR_NAME`, `ATTR_VALUE`, `DATA_TYPE`, `ATTR_DESC`) VALUES
('BILL_DUE_DATE_PERIOD', '7', 'INTEGER', 'The due date must be today plus the attribute value'),
('CREATE_TRNSCT_DETAILS_FLAG', 'Y', 'STRING', 'This flag indicates whether transaction details need to be created or not'),
('DUE_DATE_FROM_CALENDAR', 'N', 'STRING', 'The due date must be calculated based on the number of working days'),
('DUPLICATE_BILL_PRINT_CHARGE', '50', 'DOUBLE', 'The amount that must be charged from the patient, if he or she is asking for duplicate bill'),
('EVALUATE_DEPOSIT_REFUND', 'Y', 'STRING', 'The value of Y for this field indicates that deposits must be evaluated'),
('FREE_DUPLICATE_BILL_COUNT', '1', 'INTEGER', 'This count indicates that the customer should not be charged, unless the duplicate bill count exceeds this value');

-- --------------------------------------------------------

--
-- Table structure for table `fncl_charge`
--

CREATE TABLE IF NOT EXISTS `fncl_charge` (
  `FNCL_CHARGES_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_ID` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `CHARGE_TYPE_ID` int(11) NOT NULL,
  `CHARGE_AMOUNT` double NOT NULL,
  `CREATION_DATE` date NOT NULL,
  `BILL_NBR` bigint(20) DEFAULT NULL,
  `REMARKS` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`FNCL_CHARGES_ID`),
  KEY `BILL_NBR` (`BILL_NBR`),
  KEY `CHARGE_TYPE_ID` (`CHARGE_TYPE_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=9 ;

--
-- Dumping data for table `fncl_charge`
--


-- --------------------------------------------------------

--
-- Table structure for table `fncl_charge_type`
--

CREATE TABLE IF NOT EXISTS `fncl_charge_type` (
  `CHARGE_TYPE_ID` int(11) NOT NULL,
  `CHARGE_TYPE_NAME` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `PROCESS_NAME` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `DISPLAY_SEQUENCE_NBR` int(11) DEFAULT NULL,
  `DISPLAY_DETAILS` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`CHARGE_TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `fncl_charge_type`
--


INSERT INTO `fncl_charge_type` (`CHARGE_TYPE_ID`, `CHARGE_TYPE_NAME`, `PROCESS_NAME`, `DISPLAY_SEQUENCE_NBR`, `DISPLAY_DETAILS`) VALUES
(1001, 'PAYMENT', 'ACCOUNTING', NULL, NULL),
(1002, 'CREDITADJ', 'ACCOUNTING', NULL, NULL),
(2001, 'SERVICE', 'SERVICES', NULL, NULL),
(2002, 'PACKAGE', 'SERVICES', NULL, NULL),
(3001, 'PHARMACY', 'PHARMACY', NULL, NULL),
(4001, 'BED_USAGE', 'BED', 1, 'Bed');
-- --------------------------------------------------------

--
-- Table structure for table `fncl_trnsct_summary`
--

CREATE TABLE IF NOT EXISTS `fncl_trnsct_summary` (
  `bill_nbr` bigint(20) NOT NULL,
  `transaction_type` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `transaction_reference` varchar(40) COLLATE utf8_unicode_ci NOT NULL DEFAULT '-',
  `transaction_date` date NOT NULL,
  `credit_debit_ind` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`bill_nbr`,`transaction_type`,`transaction_reference`),
  KEY `bill_number` (`bill_nbr`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `fncl_trnsct_summary`
--


--
-- Constraints for dumped tables
--

--
-- Constraints for table `bill_activity_details`
--
ALTER TABLE `bill_activity_details`
  ADD CONSTRAINT `bill_activity_details_ibfk_2` FOREIGN KEY (`activity_type_cd`) REFERENCES `bill_activity` (`activity_type_id`),
  ADD CONSTRAINT `bill_activity_details_ibfk_1` FOREIGN KEY (`bill_nbr`) REFERENCES `bill_info` (`BILL_NBR`);

--
-- Constraints for table `bill_item_details`
--
ALTER TABLE `bill_item_details`
  ADD CONSTRAINT `bill_item_details_ibfk_2` FOREIGN KEY (`process_name`) REFERENCES `bill_register` (`PROCESS_NAME`),
  ADD CONSTRAINT `bill_item_details_ibfk_1` FOREIGN KEY (`bill_number`) REFERENCES `bill_info` (`BILL_NBR`);

--
-- Constraints for table `bill_process_event`
--
ALTER TABLE `bill_process_event`
  ADD CONSTRAINT `bill_process_event_ibfk_2` FOREIGN KEY (`process_name`) REFERENCES `bill_register` (`PROCESS_NAME`),
  ADD CONSTRAINT `bill_process_event_ibfk_1` FOREIGN KEY (`bill_nbr`) REFERENCES `bill_info` (`BILL_NBR`);

--
-- Constraints for table `bill_process_routing`
--
ALTER TABLE `bill_process_routing`
  ADD CONSTRAINT `bill_process_routing_ibfk_1` FOREIGN KEY (`PROCESS_NAME`) REFERENCES `bill_register` (`PROCESS_NAME`);

--
-- Constraints for table `fncl_charge`
--
ALTER TABLE `fncl_charge`
  ADD CONSTRAINT `fncl_charge_ibfk_2` FOREIGN KEY (`BILL_NBR`) REFERENCES `bill_info` (`BILL_NBR`),
  ADD CONSTRAINT `fncl_charge_ibfk_1` FOREIGN KEY (`CHARGE_TYPE_ID`) REFERENCES `fncl_charge_type` (`CHARGE_TYPE_ID`);

--
-- Constraints for table `fncl_trnsct_summary`
--
ALTER TABLE `fncl_trnsct_summary`
  ADD CONSTRAINT `fncl_trnsct_summary_ibfk_1` FOREIGN KEY (`bill_nbr`) REFERENCES `bill_info` (`BILL_NBR`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
