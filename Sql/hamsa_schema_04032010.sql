-- phpMyAdmin SQL Dump
-- version 3.1.3.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Mar 30, 2010 at 07:36 PM
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

-- --------------------------------------------------------

--
-- Table structure for table `action_status`
--

CREATE TABLE IF NOT EXISTS `action_status` (
  `ACTION_STATUS_CD` varchar(30) NOT NULL,
  `ACTION_STATUS_DESC` varchar(256) DEFAULT NULL,
  `CUSTOM_DESC` varchar(256) DEFAULT NULL,
  `ACTIVE_FLAG` char(1) DEFAULT NULL,
  PRIMARY KEY (`ACTION_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `activity_type`
--

CREATE TABLE IF NOT EXISTS `activity_type` (
  `ACTIVITY_TYPE_CD` varchar(30) NOT NULL,
  `ACTIVITY_DESC` varchar(256) DEFAULT NULL,
  `ACTIVITY_GROUP` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`ACTIVITY_TYPE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `admission`
--

CREATE TABLE IF NOT EXISTS `admission` (
  `ADMISSION_REQ_NBR` int(11) NOT NULL AUTO_INCREMENT,
  `ADMISSION_NBR` int(11) DEFAULT '0',
  `ADMISSION_STATUS_CD` varchar(20) DEFAULT NULL,
  `ADMISSION_REQUESTED_BY` varchar(20) NOT NULL,
  `DOCTOR_ID` int(11) NOT NULL,
  `PATIENT_ID` int(11) NOT NULL,
  `ENTRY_POINT_REFERENCE` varchar(45) DEFAULT NULL,
  `ENTRY_POINT_NAME` varchar(25) DEFAULT NULL,
  `REASON_FOR_ADMISSION` varchar(256) DEFAULT NULL,
  `AGENDA` longtext,
  `ADMISSION_DT` date DEFAULT NULL,
  `TREATMENT_ESTIMATION_BY` varchar(20) DEFAULT NULL,
  `TREATMENT_ESTIMATED_COST` double DEFAULT NULL,
  `TREATMENT_ACTUAL_COST` double DEFAULT NULL,
  `EXPECTED_DISCHARGE_DTM` timestamp NULL DEFAULT NULL,
  `DISCHARGE_DTM` timestamp NULL DEFAULT NULL,
  `DISCHARGE_BY_DOCTOR_ID` int(11) DEFAULT NULL,
  `NEXT_VISIT_DT` date DEFAULT NULL,
  `CREATE_DTM` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATED_DTM` timestamp NULL DEFAULT NULL,
  `MODIFIED_BY` varchar(20) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  `NURSING_UNIT_TYPE_CD` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ADMISSION_REQ_NBR`),
  UNIQUE KEY `ADMISSION_NBR` (`ADMISSION_NBR`),
  KEY `ADMISSION_ENTRY_POINT_NAME_FK` (`ENTRY_POINT_NAME`),
  KEY `ADMISSION_NURSING_UNIT_TYPE_CD_FK` (`NURSING_UNIT_TYPE_CD`),
  KEY `ADMISSION_STATUS_CD_FK` (`ADMISSION_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `admission_activity`
--

CREATE TABLE IF NOT EXISTS `admission_activity` (
  `ADMISSION_REQ_NBR` int(11) NOT NULL,
  `ACTIVITY_TYPE_CD` varchar(30) NOT NULL,
  `ACTIVITY_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ADMISSION_STATUS_CD` varchar(20) NOT NULL,
  `REMARKS` mediumtext,
  `CREATED_BY` varchar(25) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`ADMISSION_REQ_NBR`,`ACTIVITY_TYPE_CD`,`ACTIVITY_DTM`),
  KEY `ADMISSION_ACTIVITY_TYPE_CD_FK` (`ACTIVITY_TYPE_CD`),
  KEY `ADMISSION_ACTIVITY_STATUS_CD_FK` (`ADMISSION_STATUS_CD`),
  KEY `ADMISSION_ACTIVITY_ADMISSION_NBR_FK` (`ADMISSION_REQ_NBR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `admission_claim_activity`
--

CREATE TABLE IF NOT EXISTS `admission_claim_activity` (
  `REQUEST_SEQUENCE_NBR` bigint(20) NOT NULL,
  `ACTIVITY_TYPE_CD` varchar(30) NOT NULL,
  `CREATE_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CLAIM_STATUS_CD` varchar(20) NOT NULL,
  `CREATED_BY` varchar(20) DEFAULT NULL,
  `REMARKS` varchar(256) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`REQUEST_SEQUENCE_NBR`,`CREATE_DTM`,`ACTIVITY_TYPE_CD`),
  KEY `SPONSOR_ACTIVITY_ACTIVITY_TYPE_CD_FK` (`ACTIVITY_TYPE_CD`),
  KEY `SPONSOR_ACTIVITY_CLAIM_STATUS_CD` (`CLAIM_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `admission_claim_request`
--

CREATE TABLE IF NOT EXISTS `admission_claim_request` (
  `REQUEST_SEQUENCE_NBR` bigint(20) NOT NULL AUTO_INCREMENT,
  `ADMISSION_REQ_NBR` int(11) NOT NULL,
  `CLAIM_SUBSEQUENCE_NBR` int(11) NOT NULL DEFAULT '1',
  `SPONSOR_NAME` varchar(80) NOT NULL,
  `INSURER_NAME` varchar(80) DEFAULT NULL,
  `POLICY_NBR` varchar(30) DEFAULT NULL,
  `PLAN_CD` varchar(30) NOT NULL,
  `POLICY_EFFECTIVE_FROM_DT` date DEFAULT NULL,
  `POLICY_EFFECTIVE_TO_DT` date NOT NULL,
  `PREFERENCE_SEQUENCE_NBR` int(11) DEFAULT NULL,
  `POLICY_HOLDER_NAME` varchar(80) DEFAULT NULL,
  `CREATED_BY` varchar(20) DEFAULT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `REQUEST_DTM` timestamp NULL DEFAULT NULL,
  `REQUESTED_AMOUNT` double NOT NULL,
  `CLAIM_STATUS_CD` varchar(20) DEFAULT NULL,
  `APPROVAL_THROUGH` varchar(30) DEFAULT 'FAX',
  `APPROVAL_DTM` timestamp NULL DEFAULT NULL,
  `APPROVED_AMOUNT` double DEFAULT NULL,
  `FINAL_CLAIMED_AMOUNT` double DEFAULT NULL,
  `PATIENT_AMOUNT` double DEFAULT NULL,
  `LAST_MODIFIED_DTM` timestamp NULL DEFAULT NULL,
  `BILL_NBR` bigint(20) DEFAULT NULL,
  `MODIFIED_BY` varchar(20) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`REQUEST_SEQUENCE_NBR`),
  UNIQUE KEY `ADMISSION_SUBSEQUENCE_NBR` (`ADMISSION_REQ_NBR`,`CLAIM_SUBSEQUENCE_NBR`),
  KEY `CLAIM_REQUEST_ADMISSION_NBR_FK1` (`ADMISSION_REQ_NBR`),
  KEY `CLAIM_REQUEST_STATUS_CD_FK` (`CLAIM_STATUS_CD`),
  KEY `CLAIM_REQUEST_SPONSOR_NAME` (`SPONSOR_NAME`),
  KEY `CLAIM_REQUEST_PLAN_CD` (`PLAN_CD`),
  KEY `CLAIM_REQUEST_INSURER_NAME_FK` (`INSURER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `admission_entry_point`
--

CREATE TABLE IF NOT EXISTS `admission_entry_point` (
  `ENTRY_POINT_NAME` varchar(25) NOT NULL,
  `ENTRY_POINT_DESC` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`ENTRY_POINT_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `admission_status`
--

CREATE TABLE IF NOT EXISTS `admission_status` (
  `ADMISSION_STATUS_CD` varchar(30) NOT NULL,
  `ADMISSION_STATUS_DESC` varchar(256) DEFAULT NULL,
  `ACTIVE_FLAG` char(1) DEFAULT NULL,
  PRIMARY KEY (`ADMISSION_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `allergies`
--

CREATE TABLE IF NOT EXISTS `allergies` (
  `ALLERGIES_CODE` int(5) NOT NULL AUTO_INCREMENT,
  `ALLERGRY_DESCRIPTION` varchar(75) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`ALLERGIES_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE IF NOT EXISTS `appointments` (
  `APPOINTMENT_NUMBER` int(11) NOT NULL AUTO_INCREMENT,
  `APPOINTMENT_DATE` datetime NOT NULL,
  `PATIENT_ID` int(11) DEFAULT NULL,
  `BOOKING_TYPE_CODE` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL,
  `APPOINTMENT_TYPE_CODE` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `ESPECIALTY_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DOCTOR_ID` int(11) DEFAULT NULL,
  `APPT_START_TIME` varchar(8) COLLATE utf8_unicode_ci DEFAULT NULL,
  `APPT_END_TIME` varchar(8) COLLATE utf8_unicode_ci DEFAULT NULL,
  `APPOINTMENT_AGENDA` varchar(225) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REFERRAL_CODE` int(11) DEFAULT NULL,
  `CONSULTATION_CHARGE` double DEFAULT '0',
  `APPOINTMENT_STATUS_CODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CAPTURED_DTM` datetime DEFAULT NULL,
  `APPOINTMENT_REMARKS` varchar(225) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NEXT_VISIT_DT` date DEFAULT NULL,
  `ROSTER_CODE` int(11) DEFAULT NULL,
  `DATE_DIM_ID` int(11) DEFAULT NULL,
  `CREATE_DTM` datetime DEFAULT NULL,
  `CREATED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_MODIFIED_DTM` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`APPOINTMENT_NUMBER`),
  KEY `Appointments_Patient_id_FK` (`PATIENT_ID`),
  KEY `Appointments_Booking_Type_Code_FK` (`BOOKING_TYPE_CODE`),
  KEY `Appointments_Appointment_Status_Code_FK` (`APPOINTMENT_STATUS_CODE`),
  KEY `Appointments_Rooster_Code_FK` (`ROSTER_CODE`),
  KEY `DOCTOR_ID` (`DOCTOR_ID`),
  KEY `appointments_ibfk_3` (`REFERRAL_CODE`),
  KEY `ESPECIALTY_CODE_FK` (`ESPECIALTY_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

--
-- Triggers `appointments`
--
DROP TRIGGER IF EXISTS `hcisit`.`BIAppntmtCreateDateID`;
DELIMITER //
CREATE TRIGGER `hcisit`.`BIAppntmtCreateDateID` BEFORE INSERT ON `hcisit`.`appointments`
 FOR EACH ROW BEGIN

    declare n int(11);

    SELECT date_id INTO n FROM date_dim WHERE concat( date_dim.YEAR, '-', date_dim.MONTH, '-', date_dim.DAY_OF_MONTH ) = current_date;

       SET NEW.DATE_DIM_ID=n;



END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `appointment_history`
--

CREATE TABLE IF NOT EXISTS `appointment_history` (
  `APPOINTMENT_NUMBER` int(11) NOT NULL,
  `STATUS_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `CREATED_DTM` datetime DEFAULT NULL,
  `CREATED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`APPOINTMENT_NUMBER`,`STATUS_CODE`),
  KEY `Appointment_History_Appointmnet_Number_FK` (`APPOINTMENT_NUMBER`),
  KEY `Appointment_History_Status_Coder_FK` (`STATUS_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `appointment_status`
--

CREATE TABLE IF NOT EXISTS `appointment_status` (
  `STATUS_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`STATUS_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `app_entities`
--

CREATE TABLE IF NOT EXISTS `app_entities` (
  `ENTITY_TYPE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `ENTITY_NAME` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`ENTITY_TYPE`),
  UNIQUE KEY `ENTITY_NAME_UQ` (`ENTITY_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `assigned_package`
--

CREATE TABLE IF NOT EXISTS `assigned_package` (
  `PACKAGE_ASGM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SERVICE_ORDER_NUMBER` int(11) NOT NULL,
  `PACKAGE_ID` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `REFERENCE_NUMBER` varchar(25) NOT NULL DEFAULT '0',
  `REFERENCE_TYPE` varchar(30) NOT NULL DEFAULT 'DIRECT',
  `REQUESTED_UNIT` int(11) NOT NULL DEFAULT '1',
  `EFFECTIVE_CHARGE` double NOT NULL,
  `STATUS_CODE` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `DOCTOR_ID` int(11) DEFAULT NULL,
  `PATIENT_ID` int(11) DEFAULT NULL,
  `CREATE_DATE` date NOT NULL,
  `CREATED_BY` varchar(45) DEFAULT NULL,
  `MODIFICATION_DTM` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(45) DEFAULT NULL,
  `BILL_NBR` int(11) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`PACKAGE_ASGM_ID`),
  KEY `assigned_package_ibfk_2` (`STATUS_CODE`),
  KEY `DOCTOR_ID` (`DOCTOR_ID`),
  KEY `PATIENT_ID` (`PATIENT_ID`),
  KEY `PACKAGE_ID` (`PACKAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `assigned_package_status`
--

CREATE TABLE IF NOT EXISTS `assigned_package_status` (
  `STATUS_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`STATUS_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `assigned_services`
--

CREATE TABLE IF NOT EXISTS `assigned_services` (
  `SERVICE_UID` int(11) NOT NULL AUTO_INCREMENT,
  `SERVICE_ORDER_NUMBER` int(11) NOT NULL,
  `SEQ_NBR` int(11) DEFAULT NULL,
  `SERVICE_CODE` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PACKAGE_ASGM_ID` int(11) DEFAULT NULL,
  `PATIENT_ID` int(11) DEFAULT NULL,
  `DOCTOR_ID` int(11) DEFAULT NULL,
  `SERVICE_DATE` datetime DEFAULT NULL,
  `REQUESTED_UNITS` int(11) NOT NULL,
  `SERVICE_DATE_DIM_ID` int(11) DEFAULT NULL,
  `RENDERED_UNITS` int(11) NOT NULL DEFAULT '0',
  `CANCELED_UNITS` int(11) NOT NULL DEFAULT '0',
  `BILLED_UNITS` int(11) NOT NULL DEFAULT '0',
  `LAST_BILL_NBR` int(11) DEFAULT NULL,
  `SERVICE_CHARGE` double NOT NULL DEFAULT '0',
  `ASSIGNED_SERVICE_STATUS_CODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REFERENCE_NUMBER` varchar(30) COLLATE utf8_unicode_ci NOT NULL DEFAULT '0',
  `REFERENCE_TYPE` varchar(30) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'DIRECT',
  `REMARKS` varchar(225) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATE_DTM` datetime DEFAULT NULL,
  `CREATED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_MODIFIED_DTM` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`SERVICE_UID`),
  UNIQUE KEY `SERVICE_ORDER_NUMBER` (`SERVICE_ORDER_NUMBER`,`SEQ_NBR`),
  KEY `Assigned_Services_Patient_id_FK` (`PATIENT_ID`),
  KEY `Assigned_Services_Service_Code_FK` (`SERVICE_CODE`),
  KEY `Assigned_Services_Status_Code_FK` (`ASSIGNED_SERVICE_STATUS_CODE`),
  KEY `PACKAGE_ASGM_ID` (`PACKAGE_ASGM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `assigned_service_history`
--

CREATE TABLE IF NOT EXISTS `assigned_service_history` (
  `SERVICE_UID` int(11) NOT NULL,
  `CHANGE_STATUS_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `CREATED_DTM` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `CREATED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`SERVICE_UID`,`CHANGE_STATUS_CODE`,`CREATED_DTM`),
  KEY `Service_History_Service_UID_FK` (`SERVICE_UID`),
  KEY `Service_History_Change_Status_Code_FK` (`CHANGE_STATUS_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `assigned_service_status`
--

CREATE TABLE IF NOT EXISTS `assigned_service_status` (
  `SERVICE_STATUS_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SERVICE_STATUS_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `attribute`
--

CREATE TABLE IF NOT EXISTS `attribute` (
  `NAME` varchar(35) NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  `TYPE` varchar(20) NOT NULL,
  `PROVIDER` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bed_activity`
--

CREATE TABLE IF NOT EXISTS `bed_activity` (
  `BED_NUMBER` varchar(30) NOT NULL,
  `ACTIVITY_TYPE_CD` varchar(30) NOT NULL,
  `CREATE_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `BED_STATUS_CD` varchar(30) NOT NULL,
  `CREATED_BY` varchar(20) DEFAULT NULL,
  `REMARKS` varchar(256) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`BED_NUMBER`,`CREATE_DTM`,`ACTIVITY_TYPE_CD`),
  KEY `BA_BED_NUMBER_FK` (`BED_NUMBER`),
  KEY `BA_ACTIVITY_TYPE_CD_FK` (`ACTIVITY_TYPE_CD`),
  KEY `BA_BED_STATUS_CD_FK` (`BED_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bed_bill_history`
--

CREATE TABLE IF NOT EXISTS `bed_bill_history` (
  `BED_ASSIGNMENT_NBR` int(11) NOT NULL,
  `BILL_NBR` int(11) NOT NULL,
  `BILL_FROM_DTM` datetime NOT NULL,
  `BILL_TO_DTM` datetime NOT NULL,
  `BILLED_HOUR_UNIT` int(11) DEFAULT NULL,
  `BILLED_DAY_UNIT` int(11) DEFAULT NULL,
  `HOURLY_CHARGE` double DEFAULT NULL,
  `DAILY_CHARGE` double DEFAULT NULL,
  `TOTAL_CHARGE` double NOT NULL,
  `CREATE_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(35) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`BED_ASSIGNMENT_NBR`,`BILL_NBR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bed_envelope`
--

CREATE TABLE IF NOT EXISTS `bed_envelope` (
  `ENVELOPE_NAME` varchar(30) NOT NULL,
  `DESCRIPTION` varchar(256) DEFAULT NULL,
  `FACILITY_TYPE_IND` char(1) DEFAULT 'I',
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`ENVELOPE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bed_envelope_has_pool`
--

CREATE TABLE IF NOT EXISTS `bed_envelope_has_pool` (
  `ENVELOPE_NAME` varchar(30) NOT NULL,
  `POOL_NAME` varchar(30) NOT NULL,
  `EFFECTIVE_FROM_DT` date DEFAULT NULL,
  `EFFECTIVE_TO_DT` date DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`ENVELOPE_NAME`,`POOL_NAME`),
  KEY `BEHP_ENVELOPE_NAME_FK` (`ENVELOPE_NAME`),
  KEY `BEHP_POOL_NAME_FK` (`POOL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bed_has_special_features`
--

CREATE TABLE IF NOT EXISTS `bed_has_special_features` (
  `BED_NUMBER` varchar(30) NOT NULL,
  `FEATURE_NAME` varchar(40) NOT NULL,
  `EFFECTIVE_FROM_DATE` date DEFAULT NULL,
  `EFFECTIVE_TO_DATE` date DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`BED_NUMBER`,`FEATURE_NAME`),
  KEY `BED_SPECIAL_FEATURE_NAME_FK` (`FEATURE_NAME`),
  KEY `BED_SPECIAL_FEATURE_BED_NBR_FK` (`BED_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bed_master`
--

CREATE TABLE IF NOT EXISTS `bed_master` (
  `BED_NUMBER` varchar(30) NOT NULL,
  `ROOM_NBR` varchar(30) NOT NULL,
  `FLOOR_NBR` varchar(30) DEFAULT 'Uncategorised',
  `SITE_NAME` varchar(80) DEFAULT NULL,
  `NURSING_UNIT_NAME` varchar(30) NOT NULL,
  `DESCRIPTION` varchar(256) DEFAULT NULL,
  `MODIFIED_BY` varchar(20) DEFAULT NULL,
  `LAST_MODIFIED_DTM` timestamp NULL DEFAULT NULL,
  `BED_TYPE_CD` varchar(30) NOT NULL,
  `BED_STATUS_CD` varchar(30) NOT NULL,
  `DAILY_CHARGE` double DEFAULT NULL,
  `HOURLY_CHARGE` double DEFAULT NULL,
  `DEPOSIT_AMOUNT` double DEFAULT NULL,
  `ADMISSION_REQ_NBR` int(11) DEFAULT NULL,
  `ADMISSION_DTM` timestamp NULL DEFAULT NULL,
  `EXPECTED_DISCHARGE_DTM` timestamp NULL DEFAULT NULL,
  `PATIENT_ID` int(11) DEFAULT NULL,
  `DOCTOR_ID` int(11) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`BED_NUMBER`),
  KEY `BM_BED_STATUS_CD_FK` (`BED_STATUS_CD`),
  KEY `BM_BED_TYPE_CD_FK` (`BED_TYPE_CD`),
  KEY `BM_NURSING_UNIT_NAME_FK` (`NURSING_UNIT_NAME`),
  KEY `BM_ADMISSION_NBR_FK1` (`ADMISSION_REQ_NBR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bed_pool`
--

CREATE TABLE IF NOT EXISTS `bed_pool` (
  `BED_POOL_NAME` varchar(30) NOT NULL,
  `POOL_DESC` varchar(256) DEFAULT NULL,
  `TOTAL_NUMBER_OF_BED` int(11) NOT NULL,
  `NUMBER_OF_AVAILABLE_BEDS` int(11) NOT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`BED_POOL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bed_pool_has_unit_type`
--

CREATE TABLE IF NOT EXISTS `bed_pool_has_unit_type` (
  `POOL_NAME` varchar(30) NOT NULL,
  `UNIT_TYPE_CD` varchar(30) NOT NULL,
  `EFFECTIVE_FROM_DT` date DEFAULT NULL,
  `EFFECTIVE_TO_DATE` date DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`POOL_NAME`,`UNIT_TYPE_CD`),
  KEY `BPHUT_POOL_NAME_FK` (`POOL_NAME`),
  KEY `BPHUT_NURSING_UNIT_TYPE_CD_Fk` (`UNIT_TYPE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bed_reservation`
--

CREATE TABLE IF NOT EXISTS `bed_reservation` (
  `BED_RESERVATION_NBR` int(11) NOT NULL AUTO_INCREMENT,
  `UNIT_TYPE_CD` varchar(30) NOT NULL,
  `BED_TYPE_CD` varchar(30) NOT NULL,
  `REQUEST_VALID_TILL` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `REQUEST_CREATED_BY` varchar(20) NOT NULL,
  `GOT_PREFERRED_ROOM` char(1) DEFAULT NULL,
  `BED_NUMBER` varchar(30) DEFAULT NULL,
  `RESERVATION_FROM_DTM` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `RESERVATION_TO_DTM` timestamp NULL DEFAULT NULL,
  `PATIENT_ID` int(11) DEFAULT NULL,
  `RESERVATION_STATUS_CD` varchar(30) NOT NULL,
  `REQUEST_CREATION_DTM` timestamp NULL DEFAULT NULL,
  `MODIFIED_BY` varchar(20) DEFAULT NULL,
  `LAST_MODIFIED_TIME` timestamp NULL DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  `ADMISSION_REQ_NUMBER` int(11) NOT NULL,
  PRIMARY KEY (`BED_RESERVATION_NBR`),
  KEY `BR_BED_NUMBER_FK1` (`BED_NUMBER`),
  KEY `BR_RESERVATION_STATUS_CD_FK` (`RESERVATION_STATUS_CD`),
  KEY `BR_BED_TYPE_CD_FK` (`BED_TYPE_CD`),
  KEY `BR_UNIT_TYPE_CD_FK` (`UNIT_TYPE_CD`),
  KEY `BR_ADMISSION_REQ_NUMBER_FK` (`ADMISSION_REQ_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `bed_reservation_activity`
--

CREATE TABLE IF NOT EXISTS `bed_reservation_activity` (
  `BED_RESERVATION_NBR` int(11) NOT NULL,
  `ACTIVITY_TYPE_CD` varchar(30) NOT NULL,
  `CREATE_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `RESERVATION_STATUS_CD` varchar(30) NOT NULL,
  `REMARKS` varchar(256) DEFAULT NULL,
  `CREATED_BY` varchar(30) NOT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`BED_RESERVATION_NBR`,`ACTIVITY_TYPE_CD`,`CREATE_DTM`),
  KEY `BRA_BED_RESERVATION_NBR_FK` (`BED_RESERVATION_NBR`),
  KEY `BRA_ACTIVITY_TYPE_CD_FK` (`ACTIVITY_TYPE_CD`),
  KEY `BRA_RESERVATION_STATUS_CD_FK` (`RESERVATION_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bed_reservation_special_features`
--

CREATE TABLE IF NOT EXISTS `bed_reservation_special_features` (
  `RESERVATION_NBR` int(11) NOT NULL,
  `FEATURE_NAME` varchar(30) NOT NULL,
  `REQUIRED_FLAG` char(1) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`RESERVATION_NBR`,`FEATURE_NAME`),
  KEY `BRSF_RESERVATION_NBR_FK` (`RESERVATION_NBR`),
  KEY `BRSF_FEATURE_NAME_FK` (`FEATURE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bed_special_feature`
--

CREATE TABLE IF NOT EXISTS `bed_special_feature` (
  `FEATURE_NAME` varchar(40) NOT NULL,
  `DESCRIPTION` varchar(256) DEFAULT NULL,
  `LOCATION_WRT_BED_IND` varchar(10) DEFAULT NULL,
  `EFFECTIVE_FROM_DTM` timestamp NULL DEFAULT NULL,
  `EFFECTIVE_TO_DTM` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`FEATURE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bed_status`
--

CREATE TABLE IF NOT EXISTS `bed_status` (
  `BED_STATUS_CD` varchar(30) NOT NULL,
  `DESCRIPTION` varchar(256) DEFAULT NULL,
  `ACTIVE_FLAG` char(1) DEFAULT NULL,
  PRIMARY KEY (`BED_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bed_type`
--

CREATE TABLE IF NOT EXISTS `bed_type` (
  `BED_TYPE_CD` varchar(30) NOT NULL,
  `BED_TYPE_DESC` varchar(256) DEFAULT NULL,
  `ACTIVE_FLAG` char(1) DEFAULT NULL,
  PRIMARY KEY (`BED_TYPE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bed_usage_history`
--

CREATE TABLE IF NOT EXISTS `bed_usage_history` (
  `BED_ASSIGNMENT_NBR` int(11) NOT NULL AUTO_INCREMENT,
  `BED_NUMBER` varchar(30) NOT NULL,
  `ADMISSION_REQ_NBR` int(11) NOT NULL,
  `CHECK_IN_DTM` timestamp NULL DEFAULT NULL,
  `CHECK_OUT_DTM` timestamp NULL DEFAULT NULL,
  `LAST_BILL_NBR` int(11) DEFAULT NULL,
  `LAST_BILL_DTM` timestamp NULL DEFAULT NULL,
  `CREATED_BY` varchar(45) NOT NULL,
  `CREATE_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`BED_ASSIGNMENT_NBR`),
  KEY `BED_NUMBER_FK` (`BED_NUMBER`),
  KEY `ADMISSION_REQ_NBR_FK` (`ADMISSION_REQ_NBR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

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

-- --------------------------------------------------------

--
-- Table structure for table `blood_group`
--

CREATE TABLE IF NOT EXISTS `blood_group` (
  `BLOOD_GROUP_CODE` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`BLOOD_GROUP_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `booking_type`
--

CREATE TABLE IF NOT EXISTS `booking_type` (
  `BOOKING_TYPE_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`BOOKING_TYPE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `brand`
--

CREATE TABLE IF NOT EXISTS `brand` (
  `BRAND_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`BRAND_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `business_rule`
--

CREATE TABLE IF NOT EXISTS `business_rule` (
  `BUSINESS_RULE_CODE` varchar(56) COLLATE utf8_unicode_ci NOT NULL,
  `RULE_FOR_CODE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `RULE` longtext COLLATE utf8_unicode_ci,
  `ACTIVE` smallint(1) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`BUSINESS_RULE_CODE`,`RULE_FOR_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `change_reason`
--

CREATE TABLE IF NOT EXISTS `change_reason` (
  `CHANGE_REASON_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`CHANGE_REASON_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `claim_document`
--

CREATE TABLE IF NOT EXISTS `claim_document` (
  `REQUEST_SEQUENCE_NBR` bigint(20) NOT NULL,
  `DOCUMENT_NAME` varchar(45) NOT NULL,
  `CREATE_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `DOCUMENT_PATH` varchar(256) DEFAULT NULL,
  `CREATED_BY` varchar(25) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`REQUEST_SEQUENCE_NBR`,`CREATE_DTM`,`DOCUMENT_NAME`),
  KEY `CLAIM_DOCUMENT_REQUEST_SEQUENCE_NBR_FK` (`REQUEST_SEQUENCE_NBR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `claim_sponsor`
--

CREATE TABLE IF NOT EXISTS `claim_sponsor` (
  `SPONSORS_NAME` varchar(80) NOT NULL,
  `SPONSOR_DESC` varchar(512) DEFAULT NULL,
  `SPONSOR_TYPE_CD` varchar(30) NOT NULL,
  `ACCOUNT_NUMBER` varchar(30) DEFAULT NULL,
  `CREDIT_CLASS_CD` varchar(30) DEFAULT NULL,
  `CONTACT_CODE` int(11) NOT NULL,
  `CREATED_BY` varchar(35) NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_MODIFIED_BY` varchar(30) DEFAULT NULL,
  `MODIFIED_DTM` timestamp NULL DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`SPONSORS_NAME`),
  KEY `CS_SPONSOR_TYPE_CD_FK` (`SPONSOR_TYPE_CD`),
  KEY `CS_CREDIT_CLASS_CD_FK` (`CREDIT_CLASS_CD`),
  KEY `CS_CONTACT_CODE_FK` (`CONTACT_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `claim_sponsor_sla`
--

CREATE TABLE IF NOT EXISTS `claim_sponsor_sla` (
  `SPONSOR_NAME` varchar(80) NOT NULL,
  `ACTIVITY_TYPE_CD` varchar(30) NOT NULL,
  `SLA_PERIOD` double NOT NULL,
  `PERIOD_UNIT` varchar(20) NOT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`SPONSOR_NAME`,`ACTIVITY_TYPE_CD`),
  KEY `CLAIM_SPONSOR_SLA_SPONSOR_NAME_FK` (`SPONSOR_NAME`),
  KEY `CLAIM_SPONSOR_SLA_ACTIVITY_TYPE_CD_FK` (`ACTIVITY_TYPE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `contact_details`
--

CREATE TABLE IF NOT EXISTS `contact_details` (
  `CONTACT_CODE` int(11) NOT NULL AUTO_INCREMENT,
  `SALUATION_CODE` varchar(6) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FIRST_NAME` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MIDDLE_NAME` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_NAME` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `GENDER_CODE` varchar(6) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS_LINE1` varchar(225) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS_LINE2` varchar(225) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CITY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `COUNTRY_CODE` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATE_CODE` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PINCODE` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CONTACT_NUMBER` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MOBILE_NUMBER` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FAX_NUMBER` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAIL` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STAY_DURATION` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `RELATION_CODE` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATE_DTM` datetime DEFAULT NULL,
  `CREATED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MODIFIED_DTM` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`CONTACT_CODE`),
  KEY `Contact_Details_Relation_Code_FK` (`RELATION_CODE`),
  KEY `Contact_Details_State_Code_FK` (`STATE_CODE`,`COUNTRY_CODE`),
  KEY `Contact_Details_Saluation_Code_FK` (`SALUATION_CODE`),
  KEY `Contact_Details_Gender_Code_FK` (`GENDER_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `contact_type`
--

CREATE TABLE IF NOT EXISTS `contact_type` (
  `CONTACT_TYPE_IND` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`CONTACT_TYPE_IND`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `country`
--

CREATE TABLE IF NOT EXISTS `country` (
  `COUNTRY_CODE` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`COUNTRY_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `credit_class`
--

CREATE TABLE IF NOT EXISTS `credit_class` (
  `CREDIT_CLASS_CD` varchar(30) NOT NULL,
  `DESCRIPTION` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`CREDIT_CLASS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `date_dim`
--

CREATE TABLE IF NOT EXISTS `date_dim` (
  `DATE_ID` int(11) NOT NULL,
  `DAY_OF_YEAR` int(11) DEFAULT NULL,
  `YEAR` int(11) DEFAULT NULL,
  `MONTH` int(11) DEFAULT NULL,
  `YEAR_QUARTER` int(11) DEFAULT NULL,
  `YEAR_AND_MONTH` int(11) DEFAULT NULL,
  `YEAR_MONTH_WEEK` int(11) DEFAULT NULL,
  `YEAR_MONTH_DATE` int(11) DEFAULT NULL,
  `DAY_OF_MONTH` int(11) DEFAULT NULL,
  `WEEK_NBR` int(11) DEFAULT NULL,
  `QUARTER_NBR` int(11) DEFAULT NULL,
  `DAY_NAME` char(3) DEFAULT NULL,
  `MONTH_NAME` char(3) DEFAULT NULL,
  PRIMARY KEY (`DATE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `default_reminders`
--

CREATE TABLE IF NOT EXISTS `default_reminders` (
  `PERSONEL_ID` int(11) NOT NULL,
  `REMINDER_FOR` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `REMINDER_OPTION_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `REMINDER_NO` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `REMINDER_TIME` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DURATION_CODE` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATED_DTM` datetime DEFAULT NULL,
  `CREATED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_MODIFIED_DTM` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`PERSONEL_ID`,`REMINDER_FOR`,`REMINDER_OPTION_CODE`,`REMINDER_NO`),
  KEY `Default_Reminders_Duration_Code_FK` (`DURATION_CODE`),
  KEY `Default_Reminders_Reminder_Options_code_FK` (`REMINDER_OPTION_CODE`),
  KEY `Default_Reminders_Reminder_For_FK` (`REMINDER_FOR`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE IF NOT EXISTS `department` (
  `DEPARTMENT_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DEPARTMENT_NAME` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `ACCOUNT_NUMBER` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PROFIT_CENTER_CODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  `CREATED_DTM` datetime DEFAULT NULL,
  `CREATED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_MODIFIED_DTM` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`DEPARTMENT_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `dept_especiality_assoc`
--

CREATE TABLE IF NOT EXISTS `dept_especiality_assoc` (
  `DEPARTMENT_CODE` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ESPECIALITY_CODE` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `ACTIVE` varchar(1) NOT NULL,
  `LAST_MODIFIED_DTM` datetime NOT NULL,
  `USER_ID` varchar(45) NOT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`DEPARTMENT_CODE`,`ESPECIALITY_CODE`),
  KEY `ESPECIALITY_CODE` (`ESPECIALITY_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `discharge_activity`
--

CREATE TABLE IF NOT EXISTS `discharge_activity` (
  `ADMISSION_REQ_NBR` int(11) NOT NULL,
  `ACTIVITY_TYPE_CD` varchar(30) NOT NULL,
  `CREATION_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ORDER_STATUS_CD` varchar(30) NOT NULL,
  `CREATED_BY` varchar(20) DEFAULT NULL,
  `REMARKS` varchar(512) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`ADMISSION_REQ_NBR`,`ACTIVITY_TYPE_CD`,`CREATION_DTM`),
  KEY `DA_ADMISSION_NBR_FK1` (`ADMISSION_REQ_NBR`),
  KEY `DA_ACTIVITY_TYPE_CD_FK` (`ACTIVITY_TYPE_CD`),
  KEY `DA_ORDER_STATUS_CD_FK` (`ORDER_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `discharge_order`
--

CREATE TABLE IF NOT EXISTS `discharge_order` (
  `ADMISSION_REQ_NBR` int(11) NOT NULL,
  `DOCTOR_ORDER_NBR` int(11) NOT NULL,
  `ORDER_CREATION_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ORDER_REQUESTED_BY` varchar(20) DEFAULT NULL,
  `ORDER_STATUS_CD` varchar(20) DEFAULT NULL,
  `DISCHARGE_TYPE_CD` varchar(30) NOT NULL,
  `APPROVED_BY` varchar(20) DEFAULT NULL,
  `APPROVAL_TIME` timestamp NULL DEFAULT NULL,
  `MODIFIED_BY` varchar(20) DEFAULT NULL,
  `LAST_MODIFIED_DTM` timestamp NULL DEFAULT NULL,
  `EXPECTED_DISCHARGE_DTM` datetime DEFAULT NULL,
  `DISCHARGE_SUMMARY` longtext,
  `ACTUAL_DISCHARGE_DTM` datetime DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`ADMISSION_REQ_NBR`),
  KEY `DDO_DISCHARGE_TYPE_CD_FK` (`DISCHARGE_TYPE_CD`),
  KEY `DDO_ADMISSION_NBR_FK` (`ADMISSION_REQ_NBR`),
  KEY `DDO_DOCTOR_ORDER_NBR_FK` (`DOCTOR_ORDER_NBR`),
  KEY `DDO_ORDER_STATUS_CD_FK` (`ORDER_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `discharge_type`
--

CREATE TABLE IF NOT EXISTS `discharge_type` (
  `DISCHARGE_TYPE_CD` varchar(30) NOT NULL,
  `DESCRIPTION` varchar(256) DEFAULT NULL,
  `PROCEDURE` longtext,
  PRIMARY KEY (`DISCHARGE_TYPE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `disease`
--

CREATE TABLE IF NOT EXISTS `disease` (
  `DISEASE_NAME` varchar(30) NOT NULL,
  `DESCRIPTION` varchar(512) DEFAULT NULL,
  `DISEASE_GROUP` varchar(30) DEFAULT NULL,
  `CREATED_BY` varchar(150) NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_BY` varchar(150) DEFAULT NULL,
  `LAST_MODIFIED_DTM` timestamp NULL DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`DISEASE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `disease_requires_service`
--

CREATE TABLE IF NOT EXISTS `disease_requires_service` (
  `DISEASE_NAME` varchar(30) NOT NULL,
  `SERVICE_CODE` varchar(15) NOT NULL,
  `CREATED_BY` varchar(35) NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`DISEASE_NAME`,`SERVICE_CODE`),
  KEY `DISEASE_REQUIRS_SERVICE_DISEASE_NAME_FK` (`DISEASE_NAME`),
  KEY `DISEASE_REQUIRS_SERVICE_SERVICE_CODE_FK` (`SERVICE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE IF NOT EXISTS `doctor` (
  `DOCTOR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SALUATION_CODE` varchar(6) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FIRST_NAME` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MIDDLE_NAME` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_NAME` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FULL_NAME` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `ACTIVE` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATED_DTM` datetime DEFAULT NULL,
  `CREATED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_MODIFIED_DTM` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`DOCTOR_ID`),
  KEY `SALUATION_CODE` (`SALUATION_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `doctor_detail`
--

CREATE TABLE IF NOT EXISTS `doctor_detail` (
  `DOCTOR_ID` int(11) NOT NULL,
  `WORK_EXPERIENCE` varchar(4000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DUTY_START_TIME` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DUTY_END_TIME` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PERMANENT` smallint(1) DEFAULT NULL,
  `CREATED_DTM` datetime DEFAULT NULL,
  `CREATED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_MODIFIED_DTM` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `JOINING_DT` date DEFAULT NULL,
  `LEAVING_DT` date DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `MARITAL_CODE` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HEIGHT` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `WEIGHT` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `GENDER_CODE` varchar(6) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FATHER_HUSBAND_NAME` varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ID_PROOF_CODE` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ID_NUMBER` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ID_VALID_UPTO` date DEFAULT NULL,
  `BLOOD_GROUP_CODE` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BLOOD_DONOR_ID` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `RELIGION_CODE` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `KNOWN_LANGUAGES` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `QUALIFICATION` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REFERRED_BY` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NATIONALITY_CODE` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`DOCTOR_ID`),
  KEY `MARITAL_CODE` (`MARITAL_CODE`),
  KEY `GENDER_CODE` (`GENDER_CODE`),
  KEY `RELIGION_CODE` (`RELIGION_CODE`),
  KEY `BLOOD_GROUP_CODE` (`BLOOD_GROUP_CODE`),
  KEY `ID_PROOF_CODE` (`ID_PROOF_CODE`),
  KEY `NATIONALITY_CODE` (`NATIONALITY_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `doctor_especialty`
--

CREATE TABLE IF NOT EXISTS `doctor_especialty` (
  `ESPECIALTY_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DOCTOR_ID` int(11) NOT NULL,
  `DEPARTMENT_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  `CREATED_DTM` datetime DEFAULT NULL,
  `CREATED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_MODIFIED_DTM` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `JOINING_DT` date DEFAULT NULL,
  `LEAVING_DT` date DEFAULT NULL,
  `ROOM_CODE` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CONSULTATION_CHARGE` double DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`ESPECIALTY_CODE`,`DOCTOR_ID`,`DEPARTMENT_CODE`),
  KEY `Doctor_Especialty_Department_Code_FK` (`DEPARTMENT_CODE`),
  KEY `Doctor_Especialty_Especialty_Code_FK` (`ESPECIALTY_CODE`),
  KEY `Doctor_Especialty_Doctor_Id_FK` (`DOCTOR_ID`),
  KEY `ROOM_CODE` (`ROOM_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `doctor_order`
--

CREATE TABLE IF NOT EXISTS `doctor_order` (
  `DOCTOR_ORDER_NBR` int(11) NOT NULL AUTO_INCREMENT,
  `CREATION_SEQ_NBR` int(11) NOT NULL COMMENT 'This field indecates that a set of orders is created as part of single order creation event.So for orders wich are created using ''Order Group'' the value of this field remain same',
  `DOCTOR_ID` int(11) NOT NULL,
  `PATIENT_ID` int(11) DEFAULT NULL,
  `ADMISSION_REQ_NBR` int(11) DEFAULT NULL,
  `ORDER_TYPE_CD` varchar(20) NOT NULL,
  `ORDER_DESC` mediumtext,
  `ORDER_DICTATION` varchar(25) NOT NULL DEFAULT 'SELF',
  `ORDER_TEMPLATE_ID` varchar(20) DEFAULT NULL,
  `ORDER_GROUP_NAME` varchar(80) DEFAULT NULL,
  `CREATED_BY` varchar(20) DEFAULT NULL,
  `ORDER_CREATION_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ORDER_STATUS_CD` varchar(20) NOT NULL,
  `MODIFIED_BY` varchar(20) DEFAULT NULL,
  `LAST_MODIFIED_TM` timestamp NULL DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`DOCTOR_ORDER_NBR`),
  KEY `DOCTOR_ORDER_STATUS_CD_FK` (`ORDER_STATUS_CD`),
  KEY `DOCTOR_ORDER_TYPE_CD_FK` (`ORDER_TYPE_CD`),
  KEY `DOCTOR_ORDER_TEMPLATE_ID_FK` (`ORDER_TEMPLATE_ID`),
  KEY `DOCTOR_ORDER_GROUP_NAME_FK` (`ORDER_GROUP_NAME`),
  KEY `ADMISSION_REQ_NBR_FK` (`ADMISSION_REQ_NBR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `doctor_order_activity`
--

CREATE TABLE IF NOT EXISTS `doctor_order_activity` (
  `DOCTOR_ORDER_NUMBER` int(11) NOT NULL,
  `ACTIVITY_TYPE_CD` varchar(30) NOT NULL DEFAULT '',
  `ACTIVITY_DTM` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `ORDER_STATUS_CD` varchar(30) DEFAULT NULL,
  `CREATED_BY` varchar(20) DEFAULT NULL,
  `REMARKS` varchar(512) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`DOCTOR_ORDER_NUMBER`,`ACTIVITY_TYPE_CD`,`ACTIVITY_DTM`),
  KEY `DOA_DOCTOR_ORDER_NUMBER` (`DOCTOR_ORDER_NUMBER`),
  KEY `DOA_ACTIVITY_TYPE_CD_FK` (`ACTIVITY_TYPE_CD`),
  KEY `DOA_ORDER_STATUS_CD_FK` (`ORDER_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `doctor_order_details`
--

CREATE TABLE IF NOT EXISTS `doctor_order_details` (
  `ORDER_NBR` int(11) NOT NULL,
  `SEQUENCE_NBR` int(11) NOT NULL,
  `SUB_SEQUENCE_NBR` int(11) NOT NULL,
  `RESPONSIBLE_DEPARTMENT_ID` varchar(20) DEFAULT NULL,
  `SERVICE_ID` varchar(20) DEFAULT NULL,
  `PACKAGE_ID` varchar(25) DEFAULT NULL,
  `ACTION_DESC` varchar(512) DEFAULT NULL,
  `ACTION_STATUS_CD` varchar(20) NOT NULL,
  `ACTION_REMARKS` varchar(256) DEFAULT NULL,
  `ACTION_DTM` timestamp NULL DEFAULT NULL,
  `ACTION_TAKEN_BY` varchar(20) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`ORDER_NBR`,`SEQUENCE_NBR`,`SUB_SEQUENCE_NBR`),
  KEY `DOCTOR_ORDER_NBR_FK1` (`ORDER_NBR`),
  KEY `ACTION_STATUS_CD` (`ACTION_STATUS_CD`),
  KEY `SERVICE_ID` (`SERVICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `doctor_order_group`
--

CREATE TABLE IF NOT EXISTS `doctor_order_group` (
  `ORDER_GROUP_NAME` varchar(80) NOT NULL,
  `DESCRIPTION` varchar(512) DEFAULT NULL,
  `GROUP_FOR_DOCTOR_ID` int(11) DEFAULT NULL,
  `CREATION_DTM` timestamp NULL DEFAULT NULL,
  `CREATED_BY` varchar(20) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`ORDER_GROUP_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `doctor_order_group_has_template`
--

CREATE TABLE IF NOT EXISTS `doctor_order_group_has_template` (
  `ORDER_GROUP_NAME` varchar(80) NOT NULL,
  `ORDER_TEMPLATE_ID` varchar(30) NOT NULL,
  `SEQUENCE_NBR` int(11) NOT NULL,
  `EFFECTIVE_FROM_DT` date DEFAULT NULL,
  `EFFECTIVE_TO_DT` date DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`ORDER_GROUP_NAME`,`ORDER_TEMPLATE_ID`,`SEQUENCE_NBR`),
  KEY `DOGHT_ORDER_GROUP_NAME_FK` (`ORDER_GROUP_NAME`),
  KEY `DOGHT_ORDER_TEMPLATE_ID` (`ORDER_TEMPLATE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `doctor_order_status`
--

CREATE TABLE IF NOT EXISTS `doctor_order_status` (
  `ORDER_STATUS_CD` varchar(30) NOT NULL,
  `STATUS_DESC` varchar(256) DEFAULT NULL,
  `CUSTOM_STATUS_DESC` varchar(256) DEFAULT NULL,
  `PROCESS_NAME` varchar(80) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`ORDER_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `doctor_order_template`
--

CREATE TABLE IF NOT EXISTS `doctor_order_template` (
  `TEMPLATE_ID` varchar(30) NOT NULL,
  `TEMPLATE_DESC` varchar(256) NOT NULL,
  `ORDER_TYPE_CD` varchar(30) NOT NULL,
  `DOCTOR_ID` int(11) DEFAULT NULL,
  `ACTIVE_FLAG` char(1) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`TEMPLATE_ID`),
  KEY `TEMPLATE_ORDER_TYPE_CD` (`ORDER_TYPE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `doctor_order_template_details`
--

CREATE TABLE IF NOT EXISTS `doctor_order_template_details` (
  `TEMPLATE_ID` varchar(30) NOT NULL,
  `SEQUENCE_NBR` int(11) NOT NULL,
  `SUB_SEQUENCE_NBR` int(11) NOT NULL,
  `SERVICE_ID` varchar(20) DEFAULT NULL,
  `PACKAGE_ID` varchar(25) DEFAULT NULL,
  `RESPONSIBLE_DEPARTMENT_ID` varchar(20) DEFAULT NULL,
  `ACTION_DESC` varchar(512) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`TEMPLATE_ID`,`SEQUENCE_NBR`,`SUB_SEQUENCE_NBR`),
  KEY `DOTD_TEMPLATE_ID_FK` (`TEMPLATE_ID`),
  KEY `SERVICE_ID` (`SERVICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `doctor_order_type`
--

CREATE TABLE IF NOT EXISTS `doctor_order_type` (
  `ORDER_TYPE_CD` varchar(30) NOT NULL,
  `ORDER_TYPE_DESC` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`ORDER_TYPE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 PACK_KEYS=0;

-- --------------------------------------------------------

--
-- Table structure for table `duration`
--

CREATE TABLE IF NOT EXISTS `duration` (
  `DURATION_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`DURATION_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `emergency`
--

CREATE TABLE IF NOT EXISTS `emergency` (
  `EMERGENCY_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`EMERGENCY_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `entity_acct_map`
--

CREATE TABLE IF NOT EXISTS `entity_acct_map` (
  `ENTITY_ID` int(11) NOT NULL,
  `ENTITY_TYPE` varchar(25) CHARACTER SET latin1 NOT NULL,
  `BUSINESS_PARTNER_ID` int(11) NOT NULL,
  `USER_ID` varchar(45) CHARACTER SET latin1 NOT NULL,
  `LAST_MODIFIED_DTM` datetime NOT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`ENTITY_ID`,`ENTITY_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `entity_contact_code`
--

CREATE TABLE IF NOT EXISTS `entity_contact_code` (
  `PERSONEL_ID` int(11) NOT NULL,
  `FOR_ENTITY_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `CONTACT_TYPE_IND` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `CONTACT_CODE` int(11) DEFAULT NULL,
  `SAME_AS_CURRENT` smallint(1) DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  `CREATE_DTM` datetime DEFAULT NULL,
  `CREATED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_MODIFIED_DTM` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`PERSONEL_ID`,`FOR_ENTITY_CODE`,`CONTACT_TYPE_IND`),
  KEY `Entity_Contact_Code_For_Entity_Code_FK` (`FOR_ENTITY_CODE`),
  KEY `Entity_Contact_Code_Contact_Type_Ind_FK` (`CONTACT_TYPE_IND`),
  KEY `Entity_Contact_Code_Contact_Code_FK` (`CONTACT_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `especialty`
--

CREATE TABLE IF NOT EXISTS `especialty` (
  `ESPECIALTY_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `ESPECIALTY_NAME` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  `ESPECILALTY` smallint(1) DEFAULT NULL,
  `CREATED_DTM` datetime DEFAULT NULL,
  `CREATED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_MODIFIED_DTM` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ESPECIALTY_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE IF NOT EXISTS `feedback` (
  `FEEDBACK_NUMBER` int(11) NOT NULL,
  `FEEDBACK_TYPE_CD` varchar(30) NOT NULL,
  `SEQUENCE_NBR` int(11) NOT NULL,
  `SUBSEQUENCE_NBR` int(11) NOT NULL,
  `CURRENT_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `FEEDBACK_BY` varchar(80) DEFAULT NULL,
  `ENTITY_TYPE` varchar(20) DEFAULT 'PATIENT',
  `FEEDBACK_VALUE` mediumtext NOT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`FEEDBACK_NUMBER`,`FEEDBACK_TYPE_CD`,`SEQUENCE_NBR`,`SUBSEQUENCE_NBR`),
  KEY `FEEDBAK_FEEDBACK_TYPE_CD_FK` (`FEEDBACK_TYPE_CD`,`SEQUENCE_NBR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `feedback_type`
--

CREATE TABLE IF NOT EXISTS `feedback_type` (
  `FEEDBACK_TYPE_CD` varchar(30) NOT NULL,
  `FEEDBACK_SEQUENCE_NBR` int(11) NOT NULL,
  `DESCRIPTION` varchar(256) DEFAULT NULL,
  `INVOLVED_PROCESS` varchar(256) DEFAULT NULL,
  `FEED_BACK_VALUE_TYPE` varchar(30) DEFAULT 'FREETEXT',
  `MAXIMUM_ALLOWED_SUBSEQUENCE` int(11) DEFAULT NULL,
  PRIMARY KEY (`FEEDBACK_TYPE_CD`,`FEEDBACK_SEQUENCE_NBR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

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

-- --------------------------------------------------------

--
-- Table structure for table `gender`
--

CREATE TABLE IF NOT EXISTS `gender` (
  `GENDER_CODE` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`GENDER_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `hospital`
--

CREATE TABLE IF NOT EXISTS `hospital` (
  `HOSPITAL_CODE` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `HOSPITAL_NAME` varchar(256) CHARACTER SET latin1 NOT NULL,
  `CONTACT_CODE` int(11) NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(80) CHARACTER SET latin1 NOT NULL,
  PRIMARY KEY (`HOSPITAL_CODE`),
  KEY `CONTACT_CODE` (`CONTACT_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `id_proofs`
--

CREATE TABLE IF NOT EXISTS `id_proofs` (
  `ID_PROOFS_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`ID_PROOFS_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `immunization`
--

CREATE TABLE IF NOT EXISTS `immunization` (
  `NAME` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `insurance_plans`
--

CREATE TABLE IF NOT EXISTS `insurance_plans` (
  `PLAN_CD` varchar(30) NOT NULL,
  `INSURER_NAME` varchar(80) NOT NULL,
  `PLAN_NAME` varchar(80) DEFAULT NULL,
  `VALID_FROM_DT` date NOT NULL,
  `VALID_TO_DT` date DEFAULT NULL,
  `TOTAL_COVERAGE_AMT` double DEFAULT NULL,
  `PERCENT_ABS_IND` varchar(1) DEFAULT NULL,
  `CREATED_BY` varchar(35) NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_MODIFIED_DTM` timestamp NULL DEFAULT NULL,
  `MODIFIED_BY` varchar(20) DEFAULT NULL,
  `VERSION` int(11) DEFAULT NULL,
  PRIMARY KEY (`PLAN_CD`),
  KEY `INSURANCE_PLANS_INSURER_NAME_FK` (`INSURER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `insurer`
--

CREATE TABLE IF NOT EXISTS `insurer` (
  `INSURER_NAME` varchar(80) NOT NULL,
  `INSURER_DESC` varchar(512) DEFAULT NULL,
  `CONTACT_CODE` int(11) NOT NULL,
  `CREATED_BY` varchar(35) NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_MODIFIED_BY` varchar(30) DEFAULT NULL,
  `MODIFIED_DTM` timestamp NULL DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`INSURER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `lab_collection_point`
--

CREATE TABLE IF NOT EXISTS `lab_collection_point` (
  `COLLECTION_POINT_ID` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `NAME` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `CONTACT_CODE` int(11) NOT NULL,
  `AREA_COVERED` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATED_BY` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_BY` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MODIFIED_DTM` timestamp NULL DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`COLLECTION_POINT_ID`),
  KEY `CONTACT_CODE_fk` (`CONTACT_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `lab_collection_point_lab_association`
--

CREATE TABLE IF NOT EXISTS `lab_collection_point_lab_association` (
  `LAB_ID` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `COLLECTION_POINT_ID` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `EFFECTIVE_FROM` date DEFAULT NULL,
  `EFFECTIVE_TO` date DEFAULT NULL,
  `CREATED_BY` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`LAB_ID`),
  KEY `COLLECTION_ID` (`COLLECTION_POINT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `lab_details`
--

CREATE TABLE IF NOT EXISTS `lab_details` (
  `LAB_ID` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `LAB_NAME` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `LAB_TYPE` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `BUSINESS_NAME` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BRANCH_NAME` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HOSPITAL_CODE` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CONTACT_DETAIL_CODE` int(11) NOT NULL,
  `DIRECTION_FROM_KNOWN_PLACE` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAB_OPERATOR_ID` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATED_BY` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_BY` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MODIFIED_DTM` timestamp NULL DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`LAB_ID`),
  KEY `CONTACT_DETAIL_ID_FK` (`CONTACT_DETAIL_CODE`),
  KEY `HOSPITAL_CODE` (`HOSPITAL_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `lab_patient_test_attribute_value`
--

CREATE TABLE IF NOT EXISTS `lab_patient_test_attribute_value` (
  `PATIENT_TEST_ID` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `ATTRIBUTE_CODE` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `SEQ_NBR` int(4) NOT NULL,
  `ATTRIBUTE_VALUE` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REMARKS` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `COMPARISON_IND` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATED_BY` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_MODIFIED_BY` varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_MODIFIED_DTM` timestamp NULL DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`PATIENT_TEST_ID`,`ATTRIBUTE_CODE`,`SEQ_NBR`),
  KEY `ATTRIBUTE_CODE` (`ATTRIBUTE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `lab_patient_test_change_history`
--

CREATE TABLE IF NOT EXISTS `lab_patient_test_change_history` (
  `PATIENT_TEST_ID` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `ATTRIBUTE_CODE` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `ATTRIBUTE_VALUE` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATED_BY` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`PATIENT_TEST_ID`,`ATTRIBUTE_CODE`,`CREATED_DTM`),
  KEY `ATTRIBUTE_CODE` (`ATTRIBUTE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `lab_patient_test_detail`
--

CREATE TABLE IF NOT EXISTS `lab_patient_test_detail` (
  `PATIENT_TEST_ID` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `TEST_CODE` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `TEST_PERFORM_DTM` datetime NOT NULL,
  `STATUS_CODE` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `APPROVE_DATE` datetime DEFAULT NULL,
  `APPROVER_NAME` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PATIENT_ID` int(11) NOT NULL,
  `DOCTOR_ID` int(11) NOT NULL,
  `INVESTIGATOR_ID` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `TECHNIQUE_ID` int(11) DEFAULT NULL,
  `SPECIMEN_COLLECTION_DTM` datetime DEFAULT NULL,
  `REMARKS` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATED_DATE_DIM` int(11) NOT NULL,
  `CREATED_BY` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_BY` varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MODIFIED_DTM` timestamp NULL DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  `REPORT_COLLECTED_BY` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `REPORT_COLLECTED_DTM` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`PATIENT_TEST_ID`),
  KEY `TEST_CODE_FK` (`TEST_CODE`),
  KEY `TECHNIQUE_ID_FK` (`TECHNIQUE_ID`),
  KEY `DOCTOR_ID_FK` (`DOCTOR_ID`),
  KEY `PATIENT_ID_FK` (`PATIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `lab_patient_test_specimen`
--

CREATE TABLE IF NOT EXISTS `lab_patient_test_specimen` (
  `PATIENT_TEST_ID` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `SPECIMEN_ID` int(11) NOT NULL,
  `QUANTITY` double DEFAULT NULL,
  `UNIT` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `COLLECTION_DTM` datetime DEFAULT NULL,
  `COLLECTION_POINT_ID` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `CREATED_BY` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_BY` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MODIFIED_DTM` timestamp NULL DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`PATIENT_TEST_ID`,`SPECIMEN_ID`),
  KEY `COLLECTION_POINT_ID` (`COLLECTION_POINT_ID`),
  KEY `SPECIMEN_ID` (`SPECIMEN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `lab_requisition_order`
--

CREATE TABLE IF NOT EXISTS `lab_requisition_order` (
  `ORDER_NBR` int(11) NOT NULL AUTO_INCREMENT,
  `PATIENT_ID` int(11) NOT NULL,
  `DOCTOR_ID` int(11) NOT NULL,
  `STATUS_CODE` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `CREATED_BY` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `TOTAL_CHARGES` double DEFAULT NULL,
  `REFUNDED_AMT` double DEFAULT NULL,
  `LAST_BILLING_DTM` timestamp NULL DEFAULT NULL,
  `REFUNDABLE_AMT` double DEFAULT NULL,
  `REFERRAL_CODE` int(11) DEFAULT NULL,
  `IS_EMERGENCY` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATED_DATE_DIM` int(11) NOT NULL,
  `LAST_MODIFIED_BY` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_MODIFIED_DTM` timestamp NULL DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`ORDER_NBR`),
  KEY `PATIENT_ID_fk` (`PATIENT_ID`),
  KEY `DOCTOR_ID_fk` (`DOCTOR_ID`),
  KEY `PATIENT_ID` (`PATIENT_ID`),
  KEY `DOCTOR_ID` (`DOCTOR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `lab_specimen`
--

CREATE TABLE IF NOT EXISTS `lab_specimen` (
  `SPECIMEN_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SPECIMEN_NAME` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CONTAINER_TYPE` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATED_BY` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `CREATION_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`SPECIMEN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `lab_technique_reagent`
--

CREATE TABLE IF NOT EXISTS `lab_technique_reagent` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `IS_TECHNIQUE` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATED_BY` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `lab_template_widget`
--

CREATE TABLE IF NOT EXISTS `lab_template_widget` (
  `WIDGET_CODE` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `WIDGET_LABEL` varchar(120) COLLATE utf8_unicode_ci NOT NULL,
  `ATTRIBUTE_CODE` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `WIDGET_TYPE` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `SECTION_CODE` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `VALUE_TYPE` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `WIDGET_VALUE_PROVIDER` varchar(120) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`WIDGET_CODE`),
  KEY `ATTRIBUTE_CODE` (`ATTRIBUTE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `lab_test`
--

CREATE TABLE IF NOT EXISTS `lab_test` (
  `TEST_NAME` varchar(290) COLLATE utf8_unicode_ci NOT NULL,
  `TEST_CODE` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `TECHNIQUE_ID` int(11) DEFAULT NULL,
  `TEST_TYPE` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `AVAILABLE_FOR_GENDER` varchar(10) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Both',
  `LAB_ID` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `PRE_REQUISITE` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MIN_TIME_REQUIRED` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `REVIEW_REQUIRED` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `DEFAULT_REVIEWER_ID` varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL,
  `RESULT_TEMPLATE_ID` int(11) DEFAULT NULL,
  `CREATED_BY` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `MODIFIED_BY` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MODIFIED_DTM` timestamp NULL DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`TEST_CODE`),
  KEY `LAB_ID_FK` (`LAB_ID`),
  KEY `TECHNIQUE_ID_FK` (`TECHNIQUE_ID`),
  KEY `RESULT_TEMPLATE_ID_FK` (`RESULT_TEMPLATE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `lab_test_attribute`
--

CREATE TABLE IF NOT EXISTS `lab_test_attribute` (
  `ATTRIBUTE_CODE` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `ATTRIBUTE_NAME` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `TYPE` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `MIN_VALUE` double DEFAULT NULL,
  `MAX_VALUE` double DEFAULT NULL,
  `UNIT` varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL,
  `OBSERVATION_VALUE` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'comma separated values',
  `CREATED_BY` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `CREATED_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ATTRIBUTE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `lab_test_attribute_association`
--

CREATE TABLE IF NOT EXISTS `lab_test_attribute_association` (
  `TEST_CODE` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `TECHNIQUE_ID` int(11) NOT NULL,
  `ATTRIBUTE_CODE` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `MIN_VALUE` double DEFAULT NULL,
  `MAX_VALUE` double DEFAULT NULL,
  `IS_MANDATORY` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATED_BY` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`TEST_CODE`,`ATTRIBUTE_CODE`,`TECHNIQUE_ID`),
  KEY `ATTRIBUTE_CODE` (`ATTRIBUTE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `lab_test_specimen_association`
--

CREATE TABLE IF NOT EXISTS `lab_test_specimen_association` (
  `TEST_CODE` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `SPECIMEN_ID` int(11) NOT NULL,
  `QUANTITY` int(10) DEFAULT NULL,
  `UNIT` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IS_MANDATORY` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
  `CREATED_BY` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`TEST_CODE`,`SPECIMEN_ID`),
  KEY `SPECIMEN_ID` (`SPECIMEN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `lab_test_technique_template`
--

CREATE TABLE IF NOT EXISTS `lab_test_technique_template` (
  `TEST_CODE` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `TECHNIQUE_ID` int(11) NOT NULL,
  `TEMPLATE_ID` int(11) NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`TEST_CODE`,`TECHNIQUE_ID`),
  KEY `TEMPLATE_ID_FK` (`TEMPLATE_ID`),
  KEY `TECHNIQUE_ID` (`TECHNIQUE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `lab_test_template`
--

CREATE TABLE IF NOT EXISTS `lab_test_template` (
  `TEMPLATE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TEMPLATE_NAME` int(11) NOT NULL,
  `CREATED_BY` varchar(80) NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_MODIFIED_BY` varchar(80) DEFAULT NULL,
  `LAST_MODIFIED_DTM` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`TEMPLATE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `lab_test_template_detail`
--

CREATE TABLE IF NOT EXISTS `lab_test_template_detail` (
  `TEMPLATE_ID` int(11) NOT NULL,
  `SECTION_CODE` varchar(30) NOT NULL,
  `CELL_ID` varchar(5) DEFAULT NULL,
  `SEQ_NBR` int(11) NOT NULL,
  `WIDGET_CODE` varchar(30) NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(80) NOT NULL,
  PRIMARY KEY (`TEMPLATE_ID`,`SECTION_CODE`,`SEQ_NBR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `marital`
--

CREATE TABLE IF NOT EXISTS `marital` (
  `MARITAL_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`MARITAL_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `medicines`
--

CREATE TABLE IF NOT EXISTS `medicines` (
  `MEDICINE_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `MEDICINE_NAME` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BRAND_CODE` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STRENGTH` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MEDICINE_TYPE_CODE` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MAXIMUM_DOSAGE` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`MEDICINE_CODE`),
  KEY `Medicines_Medicine_Type_Code_FK` (`MEDICINE_TYPE_CODE`),
  KEY `Medicines_Brand_Code_FK` (`BRAND_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `medicine_type`
--

CREATE TABLE IF NOT EXISTS `medicine_type` (
  `MEDICINE_TYPE_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`MEDICINE_TYPE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `mother_tongue`
--

CREATE TABLE IF NOT EXISTS `mother_tongue` (
  `MOTHER_TONGUE_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  `DEFAULT_CODE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`MOTHER_TONGUE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `nationality`
--

CREATE TABLE IF NOT EXISTS `nationality` (
  `NATIONALITY_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  `DEFAULT_CODE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`NATIONALITY_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `nursing_unit`
--

CREATE TABLE IF NOT EXISTS `nursing_unit` (
  `UNIT_NAME` varchar(45) NOT NULL,
  `UNIT_DESCRIPTION` varchar(256) DEFAULT NULL,
  `UNIT_COORDINATOR_USER_ID` varchar(20) DEFAULT NULL,
  `UNIT_TYPE_CD` varchar(30) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`UNIT_NAME`),
  KEY `NU_UNIT_TYPE_CD_FK` (`UNIT_TYPE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `nursing_unit_type`
--

CREATE TABLE IF NOT EXISTS `nursing_unit_type` (
  `UNIT_TYPE_CD` varchar(30) NOT NULL,
  `UNIT_TYPE_DESC` varchar(256) DEFAULT NULL,
  `PARENT_UNIT_TYPE_CD` varchar(20) DEFAULT NULL,
  `TOTAL_BED_COUNT` int(11) NOT NULL,
  `AVAILABLE_BED_COUNT` int(11) NOT NULL,
  `THRESHOLD_FOR_CONFIRMATION` int(11) DEFAULT NULL,
  `THRESHOLD_FOR_WAITLIST` int(11) DEFAULT NULL,
  `MODIFIED_BY` varchar(20) DEFAULT NULL,
  `LAST_MODIFIED_DTM` timestamp NULL DEFAULT NULL,
  `CREATE_DATE` date NOT NULL,
  PRIMARY KEY (`UNIT_TYPE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `observations`
--

CREATE TABLE IF NOT EXISTS `observations` (
  `APPOINTMENT_NUMBER` int(11) NOT NULL,
  `OBSERVATION_SEQ_NO` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `OBSERVED_BY` int(11) DEFAULT NULL,
  `OBSERVATIONS` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REMARKS` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATE_DTM` datetime DEFAULT NULL,
  `CREADTED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_MODIFIED_DTM` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`OBSERVATION_SEQ_NO`,`APPOINTMENT_NUMBER`),
  KEY `Observations_Appointmnet_Number_FK` (`APPOINTMENT_NUMBER`),
  KEY `OBSERVED_BY` (`OBSERVED_BY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_attribute_value`
--

CREATE TABLE IF NOT EXISTS `order_attribute_value` (
  `ORDER_NBR` int(11) NOT NULL,
  `ATTRIBUTE_NAME` varchar(35) NOT NULL,
  `ATTRIBUTE_VALUE` longtext,
  `LAST_MODIFIED_DTM` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`ORDER_NBR`,`ATTRIBUTE_NAME`),
  KEY `ORDER_ATTRIBUTE_VALUE_ATTRIBUTE_NAME_FK` (`ATTRIBUTE_NAME`),
  KEY `ORDER_ATTRIBUTE_VALUE_ORDER_NBR_KF` (`ORDER_NBR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `order_type_has_attributes`
--

CREATE TABLE IF NOT EXISTS `order_type_has_attributes` (
  `ORDER_TYPE_CD` varchar(30) NOT NULL,
  `ATTRIBUTE_NAME` varchar(30) NOT NULL,
  `IS_MANDATORY` varchar(1) DEFAULT NULL,
  `SEQUENCE_NUMBER` int(5) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`ORDER_TYPE_CD`,`ATTRIBUTE_NAME`),
  KEY `ORDER_ATTRIBUTE_ORDER_TYPE_FK` (`ORDER_TYPE_CD`),
  KEY `ORDER_ATTRIBUTE_ATTRIBUTE_NAME_FK` (`ATTRIBUTE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `package_has_service`
--

CREATE TABLE IF NOT EXISTS `package_has_service` (
  `PACKAGE_ID` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `SERVICE_CODE` varchar(15) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `NUMBER_OF_UNITS` int(11) NOT NULL DEFAULT '1',
  `SERVICE_CHARGE` double NOT NULL DEFAULT '0',
  `DISCOUNT_TYPE` varchar(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'A',
  `DISCOUNT_AMT_PCT` double NOT NULL DEFAULT '0',
  `EFFECTIVE_CHARGE` double NOT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`PACKAGE_ID`,`SERVICE_CODE`),
  KEY `package_has_service_ibfk_2` (`SERVICE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE IF NOT EXISTS `patient` (
  `PATIENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `REGISTRATION_DATE` datetime NOT NULL,
  `REGISTRATION_TYPE_CODE` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REGISTRATION_STATUS` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SALUATION_CODE` varchar(6) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FIRST_NAME` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MIDDLE_NAME` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_NAME` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FULL_NAME` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `DATE_OF_BIRTH` date DEFAULT NULL,
  `GENDER_CODE` varchar(6) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NATIONALITY_CODE` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MARITAL_CODE` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `RELIGION_CODE` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `BLOOD_GROUP_CODE` varchar(5) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MOTHER_TONGUE_CODE` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MONTHLY_INCOME` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `OCCUPATION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CATEGORY_CODE` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `RATING_CODE` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HEIGHT` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HEIGHT_IND` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `WEIGHT` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `WEIGHT_IND` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FATHER_HUSBAND` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IMAGE` blob,
  `CURRENT_CONTACT_DETAIL_ID` int(11) NOT NULL,
  `PERMANENT_CONTACT_DETAIL_ID` int(11) DEFAULT NULL,
  `CREATE_DTM` datetime DEFAULT NULL,
  `CREATED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_MODIFIED_DTM` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REFERRAL_CODE` int(11) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`PATIENT_ID`),
  KEY `Patient_Rating_Code_FK` (`RATING_CODE`),
  KEY `Patient_Category_Code_FK` (`CATEGORY_CODE`),
  KEY `Patient_Marital_Code_FK` (`MARITAL_CODE`),
  KEY `Patient_Gender_Code_FK` (`GENDER_CODE`),
  KEY `Patient_Nationality_Code_FK` (`NATIONALITY_CODE`),
  KEY `Patient_Blood_Group_Code_FK` (`BLOOD_GROUP_CODE`),
  KEY `Patient_Religion_Code_FK` (`RELIGION_CODE`),
  KEY `Patient_Mother_Tongue_Code_FK` (`MOTHER_TONGUE_CODE`),
  KEY `Patient_Registration_type_Code_FK` (`REGISTRATION_TYPE_CODE`),
  KEY `Patient_Registration_Status_Code_FK` (`REGISTRATION_STATUS`),
  KEY `Patient_Saluation_Code_FK` (`SALUATION_CODE`),
  KEY `REFERRAL_CODE` (`REFERRAL_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci PACK_KEYS=0 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `patient_allergies`
--

CREATE TABLE IF NOT EXISTS `patient_allergies` (
  `ALLERGY_SEQ_NUM` int(11) NOT NULL AUTO_INCREMENT,
  `PATIENT_ID` int(11) NOT NULL,
  `ALLERGY_CODE` int(5) NOT NULL,
  `START_DATE` date NOT NULL,
  `END_DATE` date DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`ALLERGY_SEQ_NUM`),
  KEY `Patient_Allergies_Allergy_Code` (`ALLERGY_CODE`),
  KEY `Patient_Allergies_Patient_id` (`PATIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `patient_category`
--

CREATE TABLE IF NOT EXISTS `patient_category` (
  `CATEGORY_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  `DEFAULT_CODE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`CATEGORY_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `patient_details`
--

CREATE TABLE IF NOT EXISTS `patient_details` (
  `PATIENT_ID` int(11) NOT NULL,
  `BLOOD_DONOR_ID` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ORGAN_DONOR_ID` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ORGAN_DONATED_TO` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ID_PROOF_CODE` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ID_NUMBER` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ID_VALID_UPTO` datetime DEFAULT NULL,
  `VISA_NUMBER` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VISA_VALID_UPTO` datetime DEFAULT NULL,
  `SMOKING` smallint(1) DEFAULT NULL,
  `DRINKING` smallint(1) DEFAULT NULL,
  `FITNESS_ACTIVITY` varchar(225) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATED_DTM` datetime DEFAULT NULL,
  `CREATED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_MODIFIED_DTM` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`PATIENT_ID`),
  KEY `Patient_Details_FKIndex1` (`PATIENT_ID`),
  KEY `Patient_Details_Patient_id_fk` (`PATIENT_ID`),
  KEY `Patient_Details_ID_Proof_Code_fk` (`ID_PROOF_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci PACK_KEYS=0;

-- --------------------------------------------------------

--
-- Table structure for table `patient_immunization`
--

CREATE TABLE IF NOT EXISTS `patient_immunization` (
  `CODE` int(11) NOT NULL AUTO_INCREMENT,
  `PATIENT_ID` int(11) NOT NULL,
  `IMMUNIZATION_NAME` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `IMMUNIZATION_DTM` datetime NOT NULL,
  `CREATE_DT` date NOT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`CODE`),
  KEY `Patient_Immunization_Immunization_Name` (`IMMUNIZATION_NAME`),
  KEY `Patient_Immunization_Patient_id` (`PATIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `patient_rating`
--

CREATE TABLE IF NOT EXISTS `patient_rating` (
  `RATING_CODE` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  `DEFAULT_CODE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`RATING_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `patient_vaccination_schedule`
--

CREATE TABLE IF NOT EXISTS `patient_vaccination_schedule` (
  `SEQ_NBR` int(11) NOT NULL AUTO_INCREMENT,
  `PATIENT_ID` int(11) NOT NULL,
  `SCHEDULE_NAME` varchar(50) NOT NULL,
  `ASSIGNED_BY_DOCTOR_ID` int(11) NOT NULL,
  `START_DT` datetime NOT NULL,
  `STATUS_CD` varchar(50) NOT NULL,
  `USER_ID` varchar(50) NOT NULL,
  `LAST_MODIFIED_DT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`SEQ_NBR`),
  KEY `ASSIGNED_BY_DOCTOR_ID_FK` (`ASSIGNED_BY_DOCTOR_ID`),
  KEY `PATIENT_ID_FK` (`PATIENT_ID`),
  KEY `SCHEDULE_NAME_FK` (`SCHEDULE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `patient_vaccination_schedule_details`
--

CREATE TABLE IF NOT EXISTS `patient_vaccination_schedule_details` (
  `SEQ_NBR` int(11) NOT NULL,
  `SUB_SEQ_NBR` int(11) NOT NULL,
  `SCHEDULE_NAME` varchar(45) NOT NULL,
  `PATIENT_ID` int(11) NOT NULL,
  `GIVEN_BY_DOCTOR_ID` int(11) DEFAULT NULL,
  `PERIOD_IN_DAYS` int(11) NOT NULL,
  `AGE` varchar(50) NOT NULL,
  `VACCIANTION_CD` varchar(25) NOT NULL,
  `VACCINATION_TYPE_CD` varchar(150) DEFAULT NULL,
  `DOSAGE` varchar(50) NOT NULL,
  `DOCTOR_COMMENTS` varchar(500) DEFAULT NULL,
  `USER_ID` varchar(50) NOT NULL,
  `DUE_DATE` datetime DEFAULT NULL,
  `GIVEN_DATE` datetime DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`SEQ_NBR`,`SUB_SEQ_NBR`,`SCHEDULE_NAME`,`PATIENT_ID`),
  KEY `PATIENT_ID_FK1` (`PATIENT_ID`),
  KEY `VACCIANTION_CD` (`VACCIANTION_CD`),
  KEY `SCHEDULE_NAME` (`SCHEDULE_NAME`),
  KEY `GIVEN_BY_DOCTOR_ID` (`GIVEN_BY_DOCTOR_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `plan_covers_disease`
--

CREATE TABLE IF NOT EXISTS `plan_covers_disease` (
  `PLAN_CD` varchar(30) NOT NULL,
  `DISEASE_NAME` varchar(25) NOT NULL,
  `IS_COVERD` varchar(1) NOT NULL,
  `PERCENT_ABS_IND` char(1) DEFAULT NULL,
  `COVERAGE_AMT` double DEFAULT NULL,
  `CREATED_DTM` timestamp NULL DEFAULT NULL,
  `CREATED_BY` varchar(35) NOT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`PLAN_CD`,`DISEASE_NAME`),
  KEY `PLAN_DISEASE_PLAN_CD_FK` (`PLAN_CD`),
  KEY `PLAN_DISEASE_DISEASE_NAME_FK` (`DISEASE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `plan_has_services`
--

CREATE TABLE IF NOT EXISTS `plan_has_services` (
  `PLAN_CD` varchar(30) NOT NULL,
  `SERVICE_CODE` varchar(15) NOT NULL,
  `IS_COVERD` char(1) NOT NULL,
  `PERCENT_ABS_IND` varchar(1) DEFAULT NULL,
  `COVERAGE_AMT` double DEFAULT NULL,
  `CREATED_BY` varchar(35) NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`PLAN_CD`,`SERVICE_CODE`),
  KEY `PLAN_HAS_SERVICE_PLAN_CD_FK` (`PLAN_CD`),
  KEY `PLAN_HAS_SERVICE_SERVICE_CODE_FK` (`SERVICE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

CREATE TABLE IF NOT EXISTS `prescription` (
  `PRESCRIPTION_NUMBER` int(20) NOT NULL AUTO_INCREMENT,
  `SRC_REF_NUMBER` int(11) NOT NULL COMMENT 'For OPD - this will contain the APPOINTMENT_NUMBER and for IPD this will contain the admission number',
  `PRESCRIPTION_DATE` datetime DEFAULT NULL,
  `PRESCRIPTION_TEXT` varchar(4000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`PRESCRIPTION_NUMBER`),
  KEY `Prescription_Appointment_Number_FK` (`SRC_REF_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `prescription_medicine_assoc`
--

CREATE TABLE IF NOT EXISTS `prescription_medicine_assoc` (
  `PRESCRIPTION_NUMBER` int(20) NOT NULL,
  `MEDICINE_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DOSAGE` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REPEATS` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REMARKS` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`PRESCRIPTION_NUMBER`,`MEDICINE_CODE`),
  KEY `Prescription_Medicine_Code_FK` (`MEDICINE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `record`
--

CREATE TABLE IF NOT EXISTS `record` (
  `RECORD_ID` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `SERVICE_UID` int(11) DEFAULT NULL,
  `SERVICE_DATE` datetime DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  `CREATE_DTM` datetime DEFAULT NULL,
  `CREATED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_MODIFIED_DTM` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`RECORD_ID`),
  KEY `Record_Service_Uid_FK` (`SERVICE_UID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reference_data_list`
--

CREATE TABLE IF NOT EXISTS `reference_data_list` (
  `CONTEXT` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `ATTR_CODE` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `ATTR_DESC` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SEQ_NBR` int(4) DEFAULT NULL,
  `CREATED_BY` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `CREATE_DTM` datetime NOT NULL,
  `MODIFIED_BY` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MODIFIED_DTM` datetime DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`CONTEXT`,`ATTR_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `referral`
--

CREATE TABLE IF NOT EXISTS `referral` (
  `REFERRAL_CODE` int(11) NOT NULL AUTO_INCREMENT,
  `REFERRAL_TYPE_CODE` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Doctor',
  `QUALIFICATION` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REFERRAL_NAME` varchar(225) COLLATE utf8_unicode_ci NOT NULL,
  `PREFERRED_CONTACT_TIME` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ADDRESS` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `CITY` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `STATE_CODE` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `COUNTRY_CODE` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PIN_CODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PHONE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MOBILE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FAX` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAIL` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATED_BY` varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_COMMISSION_PROCESS_DTM` datetime DEFAULT NULL,
  `CREATE_DTM` datetime NOT NULL,
  `LAST_MODIFIED_DTM` datetime DEFAULT NULL,
  `ACTIVE` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`REFERRAL_CODE`),
  KEY `STATE_FK` (`STATE_CODE`),
  KEY `COUNTRY_FK` (`COUNTRY_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `referral_commission`
--

CREATE TABLE IF NOT EXISTS `referral_commission` (
  `SEQ_NBR` int(11) NOT NULL AUTO_INCREMENT,
  `REFERRAL_CODE` int(11) NOT NULL,
  `COMMISSION_PROCESS_TYPE_CD` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `PCT_ABS_IND` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `PCT_ABS_VALUE` double NOT NULL,
  `CREATED_BY` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `CREATED_DTM` datetime NOT NULL,
  PRIMARY KEY (`SEQ_NBR`),
  KEY `COMMISSION_PROCESS_TYPE_CD` (`COMMISSION_PROCESS_TYPE_CD`),
  KEY `REFERRAL_CODE` (`REFERRAL_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `referral_commission_process`
--

CREATE TABLE IF NOT EXISTS `referral_commission_process` (
  `COMMISSION_TYPE_CODE` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `COMMISSION_TYPE_DESC` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `ACTIVE` varchar(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`COMMISSION_TYPE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `referral_payable`
--

CREATE TABLE IF NOT EXISTS `referral_payable` (
  `REFERRAL_CODE` int(11) NOT NULL,
  `COMMISSION_PROCESS_TYPE_CD` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `REFERENCE_NBR` int(11) NOT NULL,
  `PROCESS_REFERENCE_TEXT` varchar(50) COLLATE utf8_unicode_ci NOT NULL DEFAULT '-',
  `PAYABLE_CREATE_DTM` datetime NOT NULL,
  `PATIENT_ID` int(11) NOT NULL,
  `EVENT_DTM` datetime NOT NULL,
  `PAYABLE_AMT` double NOT NULL,
  `ACCOUNTED` varchar(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'N',
  `PROCESSED_DTM` datetime DEFAULT NULL,
  `CREATED_BY` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`REFERRAL_CODE`,`COMMISSION_PROCESS_TYPE_CD`,`REFERENCE_NBR`,`PROCESS_REFERENCE_TEXT`,`PAYABLE_CREATE_DTM`),
  KEY `COMMISSION_PROCESS_TYPE_CD` (`COMMISSION_PROCESS_TYPE_CD`),
  KEY `PATIENT_ID_FK` (`PATIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `registration_history`
--

CREATE TABLE IF NOT EXISTS `registration_history` (
  `PATIENT_ID` int(11) NOT NULL,
  `CHANGE_DATE` datetime NOT NULL,
  `REGISTRATION_STATUS` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CHANGE_REASON_CODE` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATED_DTM` datetime DEFAULT NULL,
  `CREATED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_MODIFIED_DTM` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`PATIENT_ID`,`CHANGE_DATE`),
  KEY `Registration_History_Patient_id_FK` (`PATIENT_ID`),
  KEY `Registration_Change_Reason_Code_FK` (`CHANGE_REASON_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `registration_status`
--

CREATE TABLE IF NOT EXISTS `registration_status` (
  `REGISTRATION_STATUS_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`REGISTRATION_STATUS_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `registration_type`
--

CREATE TABLE IF NOT EXISTS `registration_type` (
  `REGISTRATION_TYPE_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`REGISTRATION_TYPE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `relation`
--

CREATE TABLE IF NOT EXISTS `relation` (
  `RELATION_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`RELATION_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `religion`
--

CREATE TABLE IF NOT EXISTS `religion` (
  `RELIGION_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`RELIGION_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reminders`
--

CREATE TABLE IF NOT EXISTS `reminders` (
  `APPOINTMENT_NUMBER` int(11) NOT NULL,
  `REMINDER_FOR` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `REMINDER_OPTION_CODE` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `REMINDER_NO` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `REMINDER_TIME` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `DURATION_CODE` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`APPOINTMENT_NUMBER`,`REMINDER_OPTION_CODE`,`REMINDER_NO`,`REMINDER_FOR`),
  KEY `Reminders_Reminder_Option_Code_FK` (`REMINDER_OPTION_CODE`),
  KEY `Reminders_Duration_Code_FK` (`DURATION_CODE`),
  KEY `Reminders_Appointment_Number_FK` (`APPOINTMENT_NUMBER`),
  KEY `Reminders_Reminder_For_FK` (`REMINDER_FOR`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reminder_options`
--

CREATE TABLE IF NOT EXISTS `reminder_options` (
  `REMINDER_OPTIONS_CODE` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`REMINDER_OPTIONS_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `rendered_service`
--

CREATE TABLE IF NOT EXISTS `rendered_service` (
  `SERVICE_UID` int(11) NOT NULL,
  `REFERENCE_NBR` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `REFERENCE_TYPE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `RENDERED_QTY` int(11) NOT NULL,
  `BILL_NBR` int(11) DEFAULT NULL,
  `SERVICE_CHARGE` double NOT NULL DEFAULT '0',
  `PACKAGE_ASGM_ID` int(11) DEFAULT NULL,
  `RENDERED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `RENDERED_DTM` datetime NOT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`SERVICE_UID`,`RENDERED_DTM`),
  KEY `Render_Service_Service_UID_FK` (`SERVICE_UID`),
  KEY `PACKAGE_ASGM_ID` (`PACKAGE_ASGM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `report_param`
--

CREATE TABLE IF NOT EXISTS `report_param` (
  `REPORT_CODE` varchar(150) NOT NULL,
  `WIDGET_NAME` varchar(50) NOT NULL,
  `WIDGET_LABEL` varchar(50) DEFAULT NULL,
  `WIDGET_LENGTH` int(11) DEFAULT NULL,
  `XTYPE` varchar(50) NOT NULL COMMENT 'It is type of widget in UI. MVL represents Multi valued list, i.e. combobox',
  `DATA_PROVIDER_METHOD` varchar(300) DEFAULT NULL COMMENT 'This is the method name along with the class name which provides values for MVL widget type',
  `REQUIRED_FLAG` varchar(1) NOT NULL,
  `WIDGET_SEQ_NBR` int(4) DEFAULT NULL,
  PRIMARY KEY (`REPORT_CODE`,`WIDGET_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `reservation_status`
--

CREATE TABLE IF NOT EXISTS `reservation_status` (
  `RESERVATION_STATUS_CD` varchar(20) NOT NULL,
  `DESCRIPTION` varchar(256) DEFAULT NULL,
  `ACTIVE_FLAG` char(1) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`RESERVATION_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

CREATE TABLE IF NOT EXISTS `room` (
  `ROOM_CODE` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`ROOM_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `roster`
--

CREATE TABLE IF NOT EXISTS `roster` (
  `ROSTER_CODE` int(11) NOT NULL AUTO_INCREMENT,
  `WORKING_DATE` date NOT NULL,
  `ENTITY_TYPE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ENTITY_ID` int(20) NOT NULL,
  `START_TIME` varchar(8) COLLATE utf8_unicode_ci NOT NULL,
  `END_TIME` varchar(8) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ROOM_CODE` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATED_DTM` datetime DEFAULT NULL,
  `CREATED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_MODIFIED_DTM` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`ROSTER_CODE`),
  UNIQUE KEY `ROOSTER_Unique_identity` (`ROSTER_CODE`,`WORKING_DATE`,`ENTITY_ID`,`START_TIME`),
  KEY `ROSTER_ROOM_CODE_FK` (`ROOM_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `saluation`
--

CREATE TABLE IF NOT EXISTS `saluation` (
  `SALUATION_CODE` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`SALUATION_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `samples`
--

CREATE TABLE IF NOT EXISTS `samples` (
  `SAMPLE_CODE` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `RECORD_ID` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DOCTOR_ID` int(11) DEFAULT NULL,
  `COLLECTED_DTM` datetime DEFAULT NULL,
  `REMARKS` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATE_DTM` datetime DEFAULT NULL,
  `CREATED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_MODIFIED_DTM` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`SAMPLE_CODE`),
  KEY `Samples_Record_id_FK` (`RECORD_ID`),
  KEY `Samples_Sample_Code_FK` (`SAMPLE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sample_master`
--

CREATE TABLE IF NOT EXISTS `sample_master` (
  `SAMPLE_CODE` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `SAMPLE_DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`SAMPLE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `secondary_consultation_criteria`
--

CREATE TABLE IF NOT EXISTS `secondary_consultation_criteria` (
  `SECONDARY_CONSULTATION_CRITERIA_CODE` varchar(56) COLLATE utf8_unicode_ci NOT NULL,
  `NUMBER_OF_DAYS` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NUMBER_OF_VISITS` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DOCTOR_ID` int(11) DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`SECONDARY_CONSULTATION_CRITERIA_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

CREATE TABLE IF NOT EXISTS `service` (
  `SERVICE_CODE` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `SERVICE_NAME` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `SERVICE_DESC` varchar(1024) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SERVICE_PROCEDURE` varchar(10240) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SERVICE_STATUS_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `SERVICE_CHARGE` double NOT NULL,
  `SERVICE_GROUP_CODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `SERVICE_TYPE_CD` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `DEPARTMENT_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `CREATE_DTM` datetime DEFAULT NULL,
  `CREATED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EFFECTIVE_FROM_DT` date NOT NULL,
  `EFFECTIVE_TO_DT` date DEFAULT NULL,
  `SUSPENDED_FROM_DT` date DEFAULT NULL,
  `SUSPENDED_TO_DT` date DEFAULT NULL,
  `DEPOSIT_AMT` double NOT NULL,
  `LAST_MODIFIED_DTM` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`SERVICE_CODE`),
  KEY `SERVICE_SERVICE_STATUS_CODE` (`SERVICE_STATUS_CODE`),
  KEY `service_ibfk_2` (`DEPARTMENT_CODE`),
  KEY `SERVICE_GROUP_CODE` (`SERVICE_GROUP_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=COMPACT;

-- --------------------------------------------------------

--
-- Table structure for table `service_group`
--

CREATE TABLE IF NOT EXISTS `service_group` (
  `SERVICE_GROUP_CODE` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `GROUP_NAME` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PARENT_GROUP_ID` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATE_DTM` datetime DEFAULT NULL,
  `CREATED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_MODIFIED_DTM` datetime DEFAULT NULL,
  `MODIFIED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`SERVICE_GROUP_CODE`),
  KEY `Service_Group_Parent_Group_Code_FK` (`PARENT_GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `service_history`
--

CREATE TABLE IF NOT EXISTS `service_history` (
  `SERVICE_CODE` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `NEW_STATUS_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`SERVICE_CODE`,`CREATED_DTM`),
  KEY `NEW_STATUS_CODE` (`NEW_STATUS_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `service_package`
--

CREATE TABLE IF NOT EXISTS `service_package` (
  `PACKAGE_ID` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `NAME` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(2048) COLLATE utf8_unicode_ci NOT NULL,
  `CREATION_DATE` date NOT NULL,
  `CREATED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PACKAGE_CHARGE` double NOT NULL,
  `CHARGE_OVERRIDE_LEVEL` varchar(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'P',
  `DISCOUNT_AMT_PCT` double NOT NULL,
  `DISCOUNT_TYPE` varchar(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'A',
  `EFFECTIVE_CHARGE` double NOT NULL,
  `EFFECTIVE_FROM_DT` date NOT NULL,
  `EFFECTIVE_TO_DT` date DEFAULT NULL,
  `STATUS_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `SUSPENDED_FROM_DT` date DEFAULT NULL,
  `SUSPENDED_TO_DT` date DEFAULT NULL,
  `SUSPEND_LEVEL_FLAG` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MODIFIED_BY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MODIFICATION_DTM` datetime DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`PACKAGE_ID`),
  KEY `service_package_ibfk_1` (`STATUS_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `service_package_history`
--

CREATE TABLE IF NOT EXISTS `service_package_history` (
  `PACKAGE_ID` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `NEW_STATUS_CODE` varchar(25) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(45) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`PACKAGE_ID`,`CREATED_DTM`),
  KEY `NEW_STATUS_CODE` (`NEW_STATUS_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `service_package_status`
--

CREATE TABLE IF NOT EXISTS `service_package_status` (
  `STATUS_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`STATUS_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `service_price_detail`
--

CREATE TABLE IF NOT EXISTS `service_price_detail` (
  `SERVICE_CD` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `SERVICE_CHARGE` double NOT NULL,
  `EFFECTIVE_FROM` date NOT NULL,
  `EFFECTIVE_TO` date DEFAULT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `LAST_MODIFIED_DTM` timestamp NULL DEFAULT NULL,
  `MODIFIED_BY` int(11) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`SERVICE_CD`,`CREATED_DTM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `service_status`
--

CREATE TABLE IF NOT EXISTS `service_status` (
  `SERVICE_STATUS_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`SERVICE_STATUS_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sponsor_claim_status`
--

CREATE TABLE IF NOT EXISTS `sponsor_claim_status` (
  `CLAIM_STATUS_CD` varchar(20) NOT NULL,
  `CLAIM_STATUS_DESC` varchar(256) DEFAULT NULL,
  `ACTIVE_FLAG` char(1) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`CLAIM_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sponsor_insurer_association`
--

CREATE TABLE IF NOT EXISTS `sponsor_insurer_association` (
  `SPONSOR_NAME` varchar(80) NOT NULL,
  `INSURER_NAME` varchar(80) NOT NULL,
  `EFFECTIVE_FROM_DT` date DEFAULT NULL,
  `EFFECTIVE_TO_DT` date DEFAULT NULL,
  `CREATED_BY` varchar(35) NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`SPONSOR_NAME`,`INSURER_NAME`),
  KEY `SP_INS_ASSO_SPONSOR_NAME_FK` (`SPONSOR_NAME`),
  KEY `SP_INS_ASSO_INSURER_NAME_FK` (`INSURER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sponsor_type`
--

CREATE TABLE IF NOT EXISTS `sponsor_type` (
  `SPONSOR_TYPE_CD` varchar(30) NOT NULL,
  `SPONSOR_DESC` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`SPONSOR_TYPE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `state`
--

CREATE TABLE IF NOT EXISTS `state` (
  `COUNTRY_CODE` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `STATE_CODE` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`STATE_CODE`,`COUNTRY_CODE`),
  KEY `COUNTRY_CODE_FK1` (`COUNTRY_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE IF NOT EXISTS `status` (
  `STATUS_CODE` int(11) NOT NULL,
  `STATUS_DESC` varchar(25) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Table structure for table `system_parameter`
--

CREATE TABLE IF NOT EXISTS `system_parameter` (
  `ATTR_NAME` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `ATTR_LABEL` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `ATTR_VALUE` varchar(512) COLLATE utf8_unicode_ci NOT NULL,
  `DATA_TYPE` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `ATTR_WIDTH` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `DATA_PROVIDER` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ATTR_DESC` varchar(512) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ATTR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `treatment_reason`
--

CREATE TABLE IF NOT EXISTS `treatment_reason` (
  `REASON_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`REASON_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `um_app_user`
--

CREATE TABLE IF NOT EXISTS `um_app_user` (
  `APP_USER_ID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `ROLE_LEVEL_IND` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `MEMBER_OF` int(25) DEFAULT NULL,
  `FIRST_NAME` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `LAST_NAME` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MIDDLE_NAME` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `USER_EFF_FROM_DATE` date DEFAULT NULL,
  `USER_EFF_TO_DATE` date DEFAULT NULL,
  `CONTACT_DETAIL_ID` int(25) DEFAULT NULL,
  PRIMARY KEY (`APP_USER_ID`),
  KEY `CONTACT_DETAIL_ID` (`CONTACT_DETAIL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `um_contact_detail`
--

CREATE TABLE IF NOT EXISTS `um_contact_detail` (
  `CONTACT_DETAIL_ID` int(25) NOT NULL AUTO_INCREMENT,
  `CONTACT_PERSON_NAME` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PHONE_NUMBER` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `FAX_NUMBER` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `EMAIL_ADDRESS` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CORRESPONDENCE_ADDRESS` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ZIP_CODE` int(25) DEFAULT NULL,
  `STATE_CODE` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `COUNTRY_CODE` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CITY` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`CONTACT_DETAIL_ID`),
  KEY `COUNTRY_CODE` (`COUNTRY_CODE`),
  KEY `country_state_code` (`COUNTRY_CODE`,`STATE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `um_country`
--

CREATE TABLE IF NOT EXISTS `um_country` (
  `COUNTRY_CODE` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`COUNTRY_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `um_role`
--

CREATE TABLE IF NOT EXISTS `um_role` (
  `ROLE_ID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `ROLE_NAME` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ROLE_DESC` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ROLE_LEVEL_IND_ID` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `INTERNAL_OR_EXTERNAL` char(1) COLLATE utf8_unicode_ci NOT NULL,
  `EFF_FROM_DATE` date NOT NULL,
  `EFF_TO_DATE` date DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `um_role_has_service`
--

CREATE TABLE IF NOT EXISTS `um_role_has_service` (
  `ROLE_ID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `SERVICE_ID` int(25) NOT NULL,
  `CREATE_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ROLE_ID`,`SERVICE_ID`),
  KEY `ROLE_HAS_SERVICE_FK2` (`SERVICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `um_service`
--

CREATE TABLE IF NOT EXISTS `um_service` (
  `SERVICE_ID` int(25) NOT NULL,
  `NAME` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PARENT_SERVICE_ID` int(25) DEFAULT NULL,
  `CREATE_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`SERVICE_ID`),
  UNIQUE KEY `SERVICE_UQ1` (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `um_state`
--

CREATE TABLE IF NOT EXISTS `um_state` (
  `COUNTRY_CODE` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `STATE_CODE` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`STATE_CODE`,`COUNTRY_CODE`),
  KEY `COUNTRY_CODE_FK1` (`COUNTRY_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `um_user_has_role`
--

CREATE TABLE IF NOT EXISTS `um_user_has_role` (
  `APP_USER_ID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `ROLE_ID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `CREATE_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`APP_USER_ID`,`ROLE_ID`),
  KEY `ROLE_ID` (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `um_user_password_history`
--

CREATE TABLE IF NOT EXISTS `um_user_password_history` (
  `APP_USER_ID` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `PASSWORD` varchar(80) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `START_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `END_DTM` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `CREATE_DTM` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `LAST_UPDATE_DTM` timestamp NULL DEFAULT '0000-00-00 00:00:00',
  `VERSION` int(25) NOT NULL DEFAULT '1',
  PRIMARY KEY (`APP_USER_ID`,`PASSWORD`,`START_DTM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `um_user_status`
--

CREATE TABLE IF NOT EXISTS `um_user_status` (
  `USER_STATUS_ID` int(25) NOT NULL,
  `USER_STATUS` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `USER_STATUS_DESC` varchar(128) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`USER_STATUS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `vaccination`
--

CREATE TABLE IF NOT EXISTS `vaccination` (
  `VACCINATION_CD` varchar(25) NOT NULL,
  `VACCINATION_NAME` varchar(50) NOT NULL,
  `AGE_RANGE` varchar(50) NOT NULL,
  `SUBSTITUTE_FOR` varchar(20) DEFAULT NULL,
  `ACTIVE_FLAG` varchar(1) NOT NULL,
  `USER_ID` varchar(45) NOT NULL,
  `LAST_MODIFIED_DTM` datetime NOT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`VACCINATION_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Table structure for table `vaccination_schedule`
--

CREATE TABLE IF NOT EXISTS `vaccination_schedule` (
  `SCHEDULE_NAME` varchar(50) NOT NULL,
  `SCHEDULE_DESC` varchar(100) DEFAULT NULL,
  `AGE_GROUP` varchar(50) DEFAULT NULL,
  `USER_ID` varchar(45) NOT NULL,
  `LAST_MODIFIED_DT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ACTIVE_FLAG` varchar(1) NOT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`SCHEDULE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `vaccination_schedule_details`
--

CREATE TABLE IF NOT EXISTS `vaccination_schedule_details` (
  `SCHEDULE_NAME` varchar(50) NOT NULL,
  `SEQ_NBR` int(11) NOT NULL,
  `PERIOD_IN_DAYS` int(11) NOT NULL,
  `VACCIANTION_CD` varchar(25) NOT NULL,
  `VACCINATION_TYPE_CD` varchar(150) DEFAULT NULL,
  `DOSAGE` varchar(20) DEFAULT NULL,
  `AGE` varchar(50) DEFAULT NULL,
  `DELETED_FLAG` varchar(1) DEFAULT NULL,
  `USER_ID` varchar(50) NOT NULL,
  `LAST_MODIFIED_DT` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`SCHEDULE_NAME`,`SEQ_NBR`),
  KEY `SCHEDULE_NAME_FK1` (`SCHEDULE_NAME`),
  KEY `VACCIANTION_CD_FK2` (`VACCIANTION_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admission`
--
ALTER TABLE `admission`
  ADD CONSTRAINT `admission_ibfk_1` FOREIGN KEY (`ADMISSION_STATUS_CD`) REFERENCES `admission_status` (`ADMISSION_STATUS_CD`),
  ADD CONSTRAINT `admission_ibfk_2` FOREIGN KEY (`ENTRY_POINT_NAME`) REFERENCES `admission_entry_point` (`ENTRY_POINT_NAME`),
  ADD CONSTRAINT `admission_ibfk_3` FOREIGN KEY (`NURSING_UNIT_TYPE_CD`) REFERENCES `nursing_unit_type` (`UNIT_TYPE_CD`);

--
-- Constraints for table `admission_activity`
--
ALTER TABLE `admission_activity`
  ADD CONSTRAINT `admission_activity_ibfk_1` FOREIGN KEY (`ADMISSION_REQ_NBR`) REFERENCES `admission` (`ADMISSION_REQ_NBR`),
  ADD CONSTRAINT `ADMISSION_ACTIVITY_STATUS_CD_FK` FOREIGN KEY (`ADMISSION_STATUS_CD`) REFERENCES `admission_status` (`ADMISSION_STATUS_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `ADMISSION_ACTIVITY_TYPE_CD_FK` FOREIGN KEY (`ACTIVITY_TYPE_CD`) REFERENCES `activity_type` (`ACTIVITY_TYPE_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `admission_claim_activity`
--
ALTER TABLE `admission_claim_activity`
  ADD CONSTRAINT `SPONSOR_ACTIVITY_ACTIVITY_TYPE_CD_FK` FOREIGN KEY (`ACTIVITY_TYPE_CD`) REFERENCES `activity_type` (`ACTIVITY_TYPE_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `SPONSOR_ACTIVITY_CLAIM_STATUS_CD` FOREIGN KEY (`CLAIM_STATUS_CD`) REFERENCES `sponsor_claim_status` (`CLAIM_STATUS_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `admission_claim_request`
--
ALTER TABLE `admission_claim_request`
  ADD CONSTRAINT `admission_claim_request_ibfk_1` FOREIGN KEY (`SPONSOR_NAME`) REFERENCES `claim_sponsor` (`SPONSORS_NAME`),
  ADD CONSTRAINT `admission_claim_request_ibfk_2` FOREIGN KEY (`PLAN_CD`) REFERENCES `insurance_plans` (`PLAN_CD`),
  ADD CONSTRAINT `admission_claim_request_ibfk_3` FOREIGN KEY (`ADMISSION_REQ_NBR`) REFERENCES `admission` (`ADMISSION_REQ_NBR`),
  ADD CONSTRAINT `admission_claim_request_ibfk_4` FOREIGN KEY (`INSURER_NAME`) REFERENCES `insurer` (`INSURER_NAME`),
  ADD CONSTRAINT `SPONSOR_CLAIM_REQUEST_STATUS_CD_FK` FOREIGN KEY (`CLAIM_STATUS_CD`) REFERENCES `sponsor_claim_status` (`CLAIM_STATUS_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`PATIENT_ID`) REFERENCES `patient` (`PATIENT_ID`),
  ADD CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`BOOKING_TYPE_CODE`) REFERENCES `booking_type` (`BOOKING_TYPE_CODE`),
  ADD CONSTRAINT `appointments_ibfk_3` FOREIGN KEY (`REFERRAL_CODE`) REFERENCES `referral` (`REFERRAL_CODE`),
  ADD CONSTRAINT `appointments_ibfk_4` FOREIGN KEY (`ROSTER_CODE`) REFERENCES `roster` (`ROSTER_CODE`),
  ADD CONSTRAINT `appointments_ibfk_5` FOREIGN KEY (`DOCTOR_ID`) REFERENCES `doctor` (`DOCTOR_ID`),
  ADD CONSTRAINT `appointments_ibfk_6` FOREIGN KEY (`APPOINTMENT_STATUS_CODE`) REFERENCES `appointment_status` (`STATUS_CODE`),
  ADD CONSTRAINT `appointments_ibfk_7` FOREIGN KEY (`ESPECIALTY_CODE`) REFERENCES `especialty` (`ESPECIALTY_CODE`);

--
-- Constraints for table `appointment_history`
--
ALTER TABLE `appointment_history`
  ADD CONSTRAINT `appointment_history_ibfk_1` FOREIGN KEY (`STATUS_CODE`) REFERENCES `appointment_status` (`STATUS_CODE`),
  ADD CONSTRAINT `appointment_history_ibfk_2` FOREIGN KEY (`APPOINTMENT_NUMBER`) REFERENCES `appointments` (`APPOINTMENT_NUMBER`);

--
-- Constraints for table `assigned_package`
--
ALTER TABLE `assigned_package`
  ADD CONSTRAINT `assigned_package_ibfk_2` FOREIGN KEY (`STATUS_CODE`) REFERENCES `assigned_package_status` (`STATUS_CODE`),
  ADD CONSTRAINT `assigned_package_ibfk_3` FOREIGN KEY (`DOCTOR_ID`) REFERENCES `doctor` (`DOCTOR_ID`),
  ADD CONSTRAINT `assigned_package_ibfk_4` FOREIGN KEY (`PATIENT_ID`) REFERENCES `patient` (`PATIENT_ID`),
  ADD CONSTRAINT `assigned_package_ibfk_5` FOREIGN KEY (`PACKAGE_ID`) REFERENCES `service_package` (`PACKAGE_ID`);

--
-- Constraints for table `assigned_services`
--
ALTER TABLE `assigned_services`
  ADD CONSTRAINT `assigned_services_ibfk_1` FOREIGN KEY (`PATIENT_ID`) REFERENCES `patient` (`PATIENT_ID`),
  ADD CONSTRAINT `assigned_services_ibfk_2` FOREIGN KEY (`SERVICE_CODE`) REFERENCES `service` (`SERVICE_CODE`),
  ADD CONSTRAINT `assigned_services_ibfk_3` FOREIGN KEY (`ASSIGNED_SERVICE_STATUS_CODE`) REFERENCES `assigned_service_status` (`SERVICE_STATUS_CODE`),
  ADD CONSTRAINT `assigned_services_ibfk_4` FOREIGN KEY (`PACKAGE_ASGM_ID`) REFERENCES `assigned_package` (`PACKAGE_ASGM_ID`),
  ADD CONSTRAINT `assigned_services_ibfk_5` FOREIGN KEY (`SERVICE_ORDER_NUMBER`) REFERENCES `lab_requisition_order` (`ORDER_NBR`);

--
-- Constraints for table `assigned_service_history`
--
ALTER TABLE `assigned_service_history`
  ADD CONSTRAINT `assigned_service_history_ibfk_1` FOREIGN KEY (`SERVICE_UID`) REFERENCES `assigned_services` (`SERVICE_UID`),
  ADD CONSTRAINT `assigned_service_history_ibfk_2` FOREIGN KEY (`CHANGE_STATUS_CODE`) REFERENCES `assigned_service_status` (`SERVICE_STATUS_CODE`);

--
-- Constraints for table `bed_activity`
--
ALTER TABLE `bed_activity`
  ADD CONSTRAINT `BA_ACTIVITY_TYPE_CD_FK` FOREIGN KEY (`ACTIVITY_TYPE_CD`) REFERENCES `activity_type` (`ACTIVITY_TYPE_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `BA_BED_NUMBER_FK` FOREIGN KEY (`BED_NUMBER`) REFERENCES `bed_master` (`BED_NUMBER`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `BA_BED_STATUS_CD_FK` FOREIGN KEY (`BED_STATUS_CD`) REFERENCES `bed_status` (`BED_STATUS_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `bed_bill_history`
--
ALTER TABLE `bed_bill_history`
  ADD CONSTRAINT `bed_bill_history_ibfk_1` FOREIGN KEY (`BED_ASSIGNMENT_NBR`) REFERENCES `bed_usage_history` (`BED_ASSIGNMENT_NBR`);

--
-- Constraints for table `bed_envelope_has_pool`
--
ALTER TABLE `bed_envelope_has_pool`
  ADD CONSTRAINT `BEHP_ENVELOPE_NAME_FK` FOREIGN KEY (`ENVELOPE_NAME`) REFERENCES `bed_envelope` (`ENVELOPE_NAME`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `BEHP_POOL_NAME_FK` FOREIGN KEY (`POOL_NAME`) REFERENCES `bed_pool` (`BED_POOL_NAME`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `bed_has_special_features`
--
ALTER TABLE `bed_has_special_features`
  ADD CONSTRAINT `BED_SPECIAL_FEATURE_BED_NBR_FK` FOREIGN KEY (`BED_NUMBER`) REFERENCES `bed_master` (`BED_NUMBER`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `BED_SPECIAL_FEATURE_NAME_FK` FOREIGN KEY (`FEATURE_NAME`) REFERENCES `bed_special_feature` (`FEATURE_NAME`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `bed_master`
--
ALTER TABLE `bed_master`
  ADD CONSTRAINT `bed_master_ibfk_1` FOREIGN KEY (`ADMISSION_REQ_NBR`) REFERENCES `admission` (`ADMISSION_REQ_NBR`),
  ADD CONSTRAINT `BM_BED_STATUS_CD_FK` FOREIGN KEY (`BED_STATUS_CD`) REFERENCES `bed_status` (`BED_STATUS_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `BM_BED_TYPE_CD_FK` FOREIGN KEY (`BED_TYPE_CD`) REFERENCES `bed_type` (`BED_TYPE_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `BM_NURSING_UNIT_NAME_FK` FOREIGN KEY (`NURSING_UNIT_NAME`) REFERENCES `nursing_unit` (`UNIT_NAME`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `bed_pool_has_unit_type`
--
ALTER TABLE `bed_pool_has_unit_type`
  ADD CONSTRAINT `BPHUT_NURSING_UNIT_TYPE_CD_Fk` FOREIGN KEY (`UNIT_TYPE_CD`) REFERENCES `nursing_unit_type` (`UNIT_TYPE_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `BPHUT_POOL_NAME_FK` FOREIGN KEY (`POOL_NAME`) REFERENCES `bed_pool` (`BED_POOL_NAME`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `bed_reservation`
--
ALTER TABLE `bed_reservation`
  ADD CONSTRAINT `bed_reservation_ibfk_1` FOREIGN KEY (`BED_NUMBER`) REFERENCES `bed_master` (`BED_NUMBER`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bed_reservation_ibfk_2` FOREIGN KEY (`RESERVATION_STATUS_CD`) REFERENCES `reservation_status` (`RESERVATION_STATUS_CD`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bed_reservation_ibfk_4` FOREIGN KEY (`UNIT_TYPE_CD`) REFERENCES `nursing_unit_type` (`UNIT_TYPE_CD`),
  ADD CONSTRAINT `bed_reservation_ibfk_5` FOREIGN KEY (`BED_TYPE_CD`) REFERENCES `bed_type` (`BED_TYPE_CD`),
  ADD CONSTRAINT `bed_reservation_ibfk_6` FOREIGN KEY (`ADMISSION_REQ_NUMBER`) REFERENCES `admission` (`ADMISSION_REQ_NBR`);

--
-- Constraints for table `bed_reservation_activity`
--
ALTER TABLE `bed_reservation_activity`
  ADD CONSTRAINT `bed_reservation_activity_ibfk_1` FOREIGN KEY (`BED_RESERVATION_NBR`) REFERENCES `bed_reservation` (`BED_RESERVATION_NBR`),
  ADD CONSTRAINT `bed_reservation_activity_ibfk_2` FOREIGN KEY (`ACTIVITY_TYPE_CD`) REFERENCES `activity_type` (`ACTIVITY_TYPE_CD`),
  ADD CONSTRAINT `bed_reservation_activity_ibfk_3` FOREIGN KEY (`RESERVATION_STATUS_CD`) REFERENCES `reservation_status` (`RESERVATION_STATUS_CD`);

--
-- Constraints for table `bed_reservation_special_features`
--
ALTER TABLE `bed_reservation_special_features`
  ADD CONSTRAINT `BRSF_FEATURE_NAME_FK` FOREIGN KEY (`FEATURE_NAME`) REFERENCES `bed_special_feature` (`FEATURE_NAME`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `BRSF_RESERVATION_NBR_FK` FOREIGN KEY (`RESERVATION_NBR`) REFERENCES `bed_reservation` (`BED_RESERVATION_NBR`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `bed_usage_history`
--
ALTER TABLE `bed_usage_history`
  ADD CONSTRAINT `bed_usage_history_ibfk_1` FOREIGN KEY (`BED_NUMBER`) REFERENCES `bed_master` (`BED_NUMBER`),
  ADD CONSTRAINT `bed_usage_history_ibfk_2` FOREIGN KEY (`ADMISSION_REQ_NBR`) REFERENCES `admission` (`ADMISSION_REQ_NBR`);

--
-- Constraints for table `bill_activity_details`
--
ALTER TABLE `bill_activity_details`
  ADD CONSTRAINT `bill_activity_details_ibfk_1` FOREIGN KEY (`bill_nbr`) REFERENCES `bill_info` (`BILL_NBR`),
  ADD CONSTRAINT `bill_activity_details_ibfk_2` FOREIGN KEY (`activity_type_cd`) REFERENCES `bill_activity` (`activity_type_id`);

--
-- Constraints for table `bill_item_details`
--
ALTER TABLE `bill_item_details`
  ADD CONSTRAINT `bill_item_details_ibfk_1` FOREIGN KEY (`bill_number`) REFERENCES `bill_info` (`BILL_NBR`),
  ADD CONSTRAINT `bill_item_details_ibfk_2` FOREIGN KEY (`process_name`) REFERENCES `bill_register` (`PROCESS_NAME`);

--
-- Constraints for table `bill_process_event`
--
ALTER TABLE `bill_process_event`
  ADD CONSTRAINT `bill_process_event_ibfk_1` FOREIGN KEY (`bill_nbr`) REFERENCES `bill_info` (`BILL_NBR`),
  ADD CONSTRAINT `bill_process_event_ibfk_2` FOREIGN KEY (`process_name`) REFERENCES `bill_register` (`PROCESS_NAME`);

--
-- Constraints for table `bill_process_routing`
--
ALTER TABLE `bill_process_routing`
  ADD CONSTRAINT `bill_process_routing_ibfk_1` FOREIGN KEY (`PROCESS_NAME`) REFERENCES `bill_register` (`PROCESS_NAME`);

--
-- Constraints for table `claim_document`
--
ALTER TABLE `claim_document`
  ADD CONSTRAINT `claim_document_ibfk_1` FOREIGN KEY (`REQUEST_SEQUENCE_NBR`) REFERENCES `admission_claim_request` (`REQUEST_SEQUENCE_NBR`);

--
-- Constraints for table `claim_sponsor`
--
ALTER TABLE `claim_sponsor`
  ADD CONSTRAINT `CS_CREDIT_CLASS_CD_FK` FOREIGN KEY (`CREDIT_CLASS_CD`) REFERENCES `credit_class` (`CREDIT_CLASS_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `CS_SPONSOR_TYPE_CD_FK` FOREIGN KEY (`SPONSOR_TYPE_CD`) REFERENCES `sponsor_type` (`SPONSOR_TYPE_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `claim_sponsor_sla`
--
ALTER TABLE `claim_sponsor_sla`
  ADD CONSTRAINT `claim_sponsor_sla_ibfk_2` FOREIGN KEY (`ACTIVITY_TYPE_CD`) REFERENCES `activity_type` (`ACTIVITY_TYPE_CD`),
  ADD CONSTRAINT `claim_sponsor_sla_ibfk_3` FOREIGN KEY (`SPONSOR_NAME`) REFERENCES `claim_sponsor` (`SPONSORS_NAME`);

--
-- Constraints for table `contact_details`
--
ALTER TABLE `contact_details`
  ADD CONSTRAINT `contact_details_ibfk_1` FOREIGN KEY (`SALUATION_CODE`) REFERENCES `saluation` (`SALUATION_CODE`),
  ADD CONSTRAINT `contact_details_ibfk_2` FOREIGN KEY (`GENDER_CODE`) REFERENCES `gender` (`GENDER_CODE`),
  ADD CONSTRAINT `contact_details_ibfk_4` FOREIGN KEY (`RELATION_CODE`) REFERENCES `relation` (`RELATION_CODE`);

--
-- Constraints for table `dept_especiality_assoc`
--
ALTER TABLE `dept_especiality_assoc`
  ADD CONSTRAINT `dept_especiality_assoc_ibfk_1` FOREIGN KEY (`DEPARTMENT_CODE`) REFERENCES `department` (`DEPARTMENT_CODE`),
  ADD CONSTRAINT `dept_especiality_assoc_ibfk_2` FOREIGN KEY (`ESPECIALITY_CODE`) REFERENCES `especialty` (`ESPECIALTY_CODE`);

--
-- Constraints for table `discharge_activity`
--
ALTER TABLE `discharge_activity`
  ADD CONSTRAINT `DA_ACTIVITY_TYPE_CD_FK` FOREIGN KEY (`ACTIVITY_TYPE_CD`) REFERENCES `activity_type` (`ACTIVITY_TYPE_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `DA_ORDER_STATUS_CD_FK` FOREIGN KEY (`ORDER_STATUS_CD`) REFERENCES `doctor_order_status` (`ORDER_STATUS_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `discharge_activity_ibfk_1` FOREIGN KEY (`ADMISSION_REQ_NBR`) REFERENCES `admission` (`ADMISSION_REQ_NBR`);

--
-- Constraints for table `discharge_order`
--
ALTER TABLE `discharge_order`
  ADD CONSTRAINT `DDO_DISCHARGE_TYPE_CD_FK` FOREIGN KEY (`DISCHARGE_TYPE_CD`) REFERENCES `discharge_type` (`DISCHARGE_TYPE_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `DDO_DOCTOR_ORDER_NBR_FK` FOREIGN KEY (`DOCTOR_ORDER_NBR`) REFERENCES `doctor_order` (`DOCTOR_ORDER_NBR`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `DDO_ORDER_STATUS_CD_FK` FOREIGN KEY (`ORDER_STATUS_CD`) REFERENCES `doctor_order_status` (`ORDER_STATUS_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `discharge_order_ibfk_1` FOREIGN KEY (`ADMISSION_REQ_NBR`) REFERENCES `admission` (`ADMISSION_REQ_NBR`);

--
-- Constraints for table `disease_requires_service`
--
ALTER TABLE `disease_requires_service`
  ADD CONSTRAINT `disease_requires_service_ibfk_1` FOREIGN KEY (`DISEASE_NAME`) REFERENCES `disease` (`DISEASE_NAME`);

--
-- Constraints for table `doctor`
--
ALTER TABLE `doctor`
  ADD CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`SALUATION_CODE`) REFERENCES `saluation` (`SALUATION_CODE`);

--
-- Constraints for table `doctor_detail`
--
ALTER TABLE `doctor_detail`
  ADD CONSTRAINT `doctor_detail_ibfk_1` FOREIGN KEY (`DOCTOR_ID`) REFERENCES `doctor` (`DOCTOR_ID`),
  ADD CONSTRAINT `doctor_detail_ibfk_3` FOREIGN KEY (`MARITAL_CODE`) REFERENCES `marital` (`MARITAL_CODE`),
  ADD CONSTRAINT `doctor_detail_ibfk_4` FOREIGN KEY (`GENDER_CODE`) REFERENCES `gender` (`GENDER_CODE`),
  ADD CONSTRAINT `doctor_detail_ibfk_5` FOREIGN KEY (`ID_PROOF_CODE`) REFERENCES `id_proofs` (`ID_PROOFS_CODE`),
  ADD CONSTRAINT `doctor_detail_ibfk_6` FOREIGN KEY (`BLOOD_GROUP_CODE`) REFERENCES `blood_group` (`BLOOD_GROUP_CODE`),
  ADD CONSTRAINT `doctor_detail_ibfk_7` FOREIGN KEY (`RELIGION_CODE`) REFERENCES `religion` (`RELIGION_CODE`),
  ADD CONSTRAINT `doctor_detail_ibfk_8` FOREIGN KEY (`NATIONALITY_CODE`) REFERENCES `nationality` (`NATIONALITY_CODE`);

--
-- Constraints for table `doctor_especialty`
--
ALTER TABLE `doctor_especialty`
  ADD CONSTRAINT `doctor_especialty_ibfk_1` FOREIGN KEY (`ESPECIALTY_CODE`) REFERENCES `especialty` (`ESPECIALTY_CODE`),
  ADD CONSTRAINT `doctor_especialty_ibfk_2` FOREIGN KEY (`DOCTOR_ID`) REFERENCES `doctor` (`DOCTOR_ID`),
  ADD CONSTRAINT `doctor_especialty_ibfk_3` FOREIGN KEY (`DEPARTMENT_CODE`) REFERENCES `department` (`DEPARTMENT_CODE`),
  ADD CONSTRAINT `doctor_especialty_ibfk_4` FOREIGN KEY (`ROOM_CODE`) REFERENCES `room` (`ROOM_CODE`);

--
-- Constraints for table `doctor_order`
--
ALTER TABLE `doctor_order`
  ADD CONSTRAINT `doctor_order_ibfk_1` FOREIGN KEY (`ORDER_GROUP_NAME`) REFERENCES `doctor_order_group` (`ORDER_GROUP_NAME`),
  ADD CONSTRAINT `doctor_order_ibfk_2` FOREIGN KEY (`ADMISSION_REQ_NBR`) REFERENCES `admission` (`ADMISSION_REQ_NBR`),
  ADD CONSTRAINT `DOCTOR_ORDER_STATUS_CD_FK` FOREIGN KEY (`ORDER_STATUS_CD`) REFERENCES `doctor_order_status` (`ORDER_STATUS_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `DOCTOR_ORDER_TEMPLATE_ID_FK` FOREIGN KEY (`ORDER_TEMPLATE_ID`) REFERENCES `doctor_order_template` (`TEMPLATE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `DOCTOR_ORDER_TYPE_CD_FK` FOREIGN KEY (`ORDER_TYPE_CD`) REFERENCES `doctor_order_type` (`ORDER_TYPE_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `doctor_order_activity`
--
ALTER TABLE `doctor_order_activity`
  ADD CONSTRAINT `DOA_ACTIVITY_TYPE_CD_FK` FOREIGN KEY (`ACTIVITY_TYPE_CD`) REFERENCES `activity_type` (`ACTIVITY_TYPE_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `DOA_DOCTOR_ORDER_NUMBER` FOREIGN KEY (`DOCTOR_ORDER_NUMBER`) REFERENCES `doctor_order` (`DOCTOR_ORDER_NBR`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `DOA_ORDER_STATUS_CD_FK` FOREIGN KEY (`ORDER_STATUS_CD`) REFERENCES `doctor_order_status` (`ORDER_STATUS_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `doctor_order_details`
--
ALTER TABLE `doctor_order_details`
  ADD CONSTRAINT `ACTION_STATUS_CD` FOREIGN KEY (`ACTION_STATUS_CD`) REFERENCES `action_status` (`ACTION_STATUS_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `DOCTOR_ORDER_NBR_FK1` FOREIGN KEY (`ORDER_NBR`) REFERENCES `doctor_order` (`DOCTOR_ORDER_NBR`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `doctor_order_group_has_template`
--
ALTER TABLE `doctor_order_group_has_template`
  ADD CONSTRAINT `DOGHT_ORDER_GROUP_NAME_FK` FOREIGN KEY (`ORDER_GROUP_NAME`) REFERENCES `doctor_order_group` (`ORDER_GROUP_NAME`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `DOGHT_ORDER_TEMPLATE_ID` FOREIGN KEY (`ORDER_TEMPLATE_ID`) REFERENCES `doctor_order_template` (`TEMPLATE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `doctor_order_template`
--
ALTER TABLE `doctor_order_template`
  ADD CONSTRAINT `TEMPLATE_ORDER_TYPE_CD` FOREIGN KEY (`ORDER_TYPE_CD`) REFERENCES `doctor_order_type` (`ORDER_TYPE_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `doctor_order_template_details`
--
ALTER TABLE `doctor_order_template_details`
  ADD CONSTRAINT `DOTD_TEMPLATE_ID_FK` FOREIGN KEY (`TEMPLATE_ID`) REFERENCES `doctor_order_template` (`TEMPLATE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `entity_contact_code`
--
ALTER TABLE `entity_contact_code`
  ADD CONSTRAINT `entity_contact_code_ibfk_1` FOREIGN KEY (`CONTACT_TYPE_IND`) REFERENCES `contact_type` (`CONTACT_TYPE_IND`),
  ADD CONSTRAINT `entity_contact_code_ibfk_2` FOREIGN KEY (`CONTACT_CODE`) REFERENCES `contact_details` (`CONTACT_CODE`),
  ADD CONSTRAINT `entity_contact_code_ibfk_3` FOREIGN KEY (`FOR_ENTITY_CODE`) REFERENCES `app_entities` (`ENTITY_TYPE`);

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `FEEDBAK_FEEDBACK_TYPE_CD_FK` FOREIGN KEY (`FEEDBACK_TYPE_CD`, `SEQUENCE_NBR`) REFERENCES `feedback_type` (`FEEDBACK_TYPE_CD`, `FEEDBACK_SEQUENCE_NBR`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `fncl_charge`
--
ALTER TABLE `fncl_charge`
  ADD CONSTRAINT `fncl_charge_ibfk_1` FOREIGN KEY (`CHARGE_TYPE_ID`) REFERENCES `fncl_charge_type` (`CHARGE_TYPE_ID`),
  ADD CONSTRAINT `fncl_charge_ibfk_2` FOREIGN KEY (`BILL_NBR`) REFERENCES `bill_info` (`BILL_NBR`);

--
-- Constraints for table `fncl_trnsct_summary`
--
ALTER TABLE `fncl_trnsct_summary`
  ADD CONSTRAINT `fncl_trnsct_summary_ibfk_1` FOREIGN KEY (`bill_nbr`) REFERENCES `bill_info` (`BILL_NBR`);

--
-- Constraints for table `hospital`
--
ALTER TABLE `hospital`
  ADD CONSTRAINT `hospital_ibfk_1` FOREIGN KEY (`CONTACT_CODE`) REFERENCES `contact_details` (`CONTACT_CODE`);

--
-- Constraints for table `insurance_plans`
--
ALTER TABLE `insurance_plans`
  ADD CONSTRAINT `insurance_plans_ibfk_1` FOREIGN KEY (`INSURER_NAME`) REFERENCES `insurer` (`INSURER_NAME`);

--
-- Constraints for table `lab_collection_point`
--
ALTER TABLE `lab_collection_point`
  ADD CONSTRAINT `lab_collection_point_ibfk_1` FOREIGN KEY (`CONTACT_CODE`) REFERENCES `contact_details` (`CONTACT_CODE`);

--
-- Constraints for table `lab_collection_point_lab_association`
--
ALTER TABLE `lab_collection_point_lab_association`
  ADD CONSTRAINT `lab_collection_point_lab_association_ibfk_1` FOREIGN KEY (`LAB_ID`) REFERENCES `lab_details` (`LAB_ID`),
  ADD CONSTRAINT `lab_collection_point_lab_association_ibfk_2` FOREIGN KEY (`COLLECTION_POINT_ID`) REFERENCES `lab_collection_point` (`COLLECTION_POINT_ID`);

--
-- Constraints for table `lab_details`
--
ALTER TABLE `lab_details`
  ADD CONSTRAINT `lab_details_ibfk_1` FOREIGN KEY (`CONTACT_DETAIL_CODE`) REFERENCES `contact_details` (`CONTACT_CODE`),
  ADD CONSTRAINT `lab_details_ibfk_2` FOREIGN KEY (`HOSPITAL_CODE`) REFERENCES `hospital` (`HOSPITAL_CODE`);

--
-- Constraints for table `lab_patient_test_attribute_value`
--
ALTER TABLE `lab_patient_test_attribute_value`
  ADD CONSTRAINT `lab_patient_test_attribute_value_ibfk_1` FOREIGN KEY (`PATIENT_TEST_ID`) REFERENCES `lab_patient_test_detail` (`PATIENT_TEST_ID`),
  ADD CONSTRAINT `lab_patient_test_attribute_value_ibfk_2` FOREIGN KEY (`ATTRIBUTE_CODE`) REFERENCES `lab_test_attribute` (`ATTRIBUTE_CODE`);

--
-- Constraints for table `lab_patient_test_change_history`
--
ALTER TABLE `lab_patient_test_change_history`
  ADD CONSTRAINT `lab_patient_test_change_history_ibfk_1` FOREIGN KEY (`PATIENT_TEST_ID`) REFERENCES `lab_patient_test_detail` (`PATIENT_TEST_ID`),
  ADD CONSTRAINT `lab_patient_test_change_history_ibfk_2` FOREIGN KEY (`ATTRIBUTE_CODE`) REFERENCES `lab_test_attribute` (`ATTRIBUTE_CODE`);

--
-- Constraints for table `lab_patient_test_detail`
--
ALTER TABLE `lab_patient_test_detail`
  ADD CONSTRAINT `lab_patient_test_detail_ibfk_1` FOREIGN KEY (`TEST_CODE`) REFERENCES `lab_test` (`TEST_CODE`),
  ADD CONSTRAINT `lab_patient_test_detail_ibfk_2` FOREIGN KEY (`PATIENT_ID`) REFERENCES `patient` (`PATIENT_ID`),
  ADD CONSTRAINT `lab_patient_test_detail_ibfk_3` FOREIGN KEY (`DOCTOR_ID`) REFERENCES `doctor` (`DOCTOR_ID`),
  ADD CONSTRAINT `lab_patient_test_detail_ibfk_4` FOREIGN KEY (`TECHNIQUE_ID`) REFERENCES `lab_technique_reagent` (`ID`);

--
-- Constraints for table `lab_patient_test_specimen`
--
ALTER TABLE `lab_patient_test_specimen`
  ADD CONSTRAINT `lab_patient_test_specimen_ibfk_1` FOREIGN KEY (`COLLECTION_POINT_ID`) REFERENCES `lab_collection_point` (`COLLECTION_POINT_ID`),
  ADD CONSTRAINT `lab_patient_test_specimen_ibfk_2` FOREIGN KEY (`SPECIMEN_ID`) REFERENCES `lab_specimen` (`SPECIMEN_ID`),
  ADD CONSTRAINT `lab_patient_test_specimen_ibfk_3` FOREIGN KEY (`PATIENT_TEST_ID`) REFERENCES `lab_patient_test_detail` (`PATIENT_TEST_ID`);

--
-- Constraints for table `lab_requisition_order`
--
ALTER TABLE `lab_requisition_order`
  ADD CONSTRAINT `lab_requisition_order_ibfk_1` FOREIGN KEY (`PATIENT_ID`) REFERENCES `patient` (`PATIENT_ID`),
  ADD CONSTRAINT `lab_requisition_order_ibfk_2` FOREIGN KEY (`DOCTOR_ID`) REFERENCES `doctor` (`DOCTOR_ID`);

--
-- Constraints for table `lab_template_widget`
--
ALTER TABLE `lab_template_widget`
  ADD CONSTRAINT `lab_template_widget_ibfk_1` FOREIGN KEY (`ATTRIBUTE_CODE`) REFERENCES `lab_test_attribute` (`ATTRIBUTE_CODE`);

--
-- Constraints for table `lab_test`
--
ALTER TABLE `lab_test`
  ADD CONSTRAINT `lab_test_ibfk_1` FOREIGN KEY (`TEST_CODE`) REFERENCES `service` (`SERVICE_CODE`),
  ADD CONSTRAINT `lab_test_ibfk_2` FOREIGN KEY (`TECHNIQUE_ID`) REFERENCES `lab_technique_reagent` (`ID`),
  ADD CONSTRAINT `lab_test_ibfk_3` FOREIGN KEY (`LAB_ID`) REFERENCES `lab_details` (`LAB_ID`),
  ADD CONSTRAINT `lab_test_ibfk_4` FOREIGN KEY (`RESULT_TEMPLATE_ID`) REFERENCES `lab_test_template` (`TEMPLATE_ID`);

--
-- Constraints for table `lab_test_attribute_association`
--
ALTER TABLE `lab_test_attribute_association`
  ADD CONSTRAINT `lab_test_attribute_association_ibfk_1` FOREIGN KEY (`ATTRIBUTE_CODE`) REFERENCES `lab_test_attribute` (`ATTRIBUTE_CODE`),
  ADD CONSTRAINT `lab_test_attribute_association_ibfk_2` FOREIGN KEY (`TEST_CODE`) REFERENCES `lab_test` (`TEST_CODE`);

--
-- Constraints for table `lab_test_specimen_association`
--
ALTER TABLE `lab_test_specimen_association`
  ADD CONSTRAINT `lab_test_specimen_association_ibfk_1` FOREIGN KEY (`TEST_CODE`) REFERENCES `lab_test` (`TEST_CODE`),
  ADD CONSTRAINT `lab_test_specimen_association_ibfk_2` FOREIGN KEY (`SPECIMEN_ID`) REFERENCES `lab_specimen` (`SPECIMEN_ID`);

--
-- Constraints for table `lab_test_technique_template`
--
ALTER TABLE `lab_test_technique_template`
  ADD CONSTRAINT `lab_test_technique_template_ibfk_1` FOREIGN KEY (`TEST_CODE`) REFERENCES `lab_test` (`TEST_CODE`),
  ADD CONSTRAINT `lab_test_technique_template_ibfk_2` FOREIGN KEY (`TECHNIQUE_ID`) REFERENCES `lab_test` (`TECHNIQUE_ID`),
  ADD CONSTRAINT `lab_test_technique_template_ibfk_3` FOREIGN KEY (`TEMPLATE_ID`) REFERENCES `lab_technique_reagent` (`ID`);

--
-- Constraints for table `lab_test_template_detail`
--
ALTER TABLE `lab_test_template_detail`
  ADD CONSTRAINT `lab_test_template_detail_ibfk_1` FOREIGN KEY (`TEMPLATE_ID`) REFERENCES `lab_test_template` (`TEMPLATE_ID`);

--
-- Constraints for table `medicines`
--
ALTER TABLE `medicines`
  ADD CONSTRAINT `medicines_ibfk_1` FOREIGN KEY (`BRAND_CODE`) REFERENCES `brand` (`BRAND_CODE`),
  ADD CONSTRAINT `medicines_ibfk_2` FOREIGN KEY (`MEDICINE_TYPE_CODE`) REFERENCES `medicine_type` (`MEDICINE_TYPE_CODE`);

--
-- Constraints for table `nursing_unit`
--
ALTER TABLE `nursing_unit`
  ADD CONSTRAINT `NU_UNIT_TYPE_CD_FK` FOREIGN KEY (`UNIT_TYPE_CD`) REFERENCES `nursing_unit_type` (`UNIT_TYPE_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `observations`
--
ALTER TABLE `observations`
  ADD CONSTRAINT `observations_ibfk_1` FOREIGN KEY (`APPOINTMENT_NUMBER`) REFERENCES `appointments` (`APPOINTMENT_NUMBER`),
  ADD CONSTRAINT `observations_ibfk_2` FOREIGN KEY (`OBSERVED_BY`) REFERENCES `doctor` (`DOCTOR_ID`);

--
-- Constraints for table `order_attribute_value`
--
ALTER TABLE `order_attribute_value`
  ADD CONSTRAINT `order_attribute_value_ibfk_1` FOREIGN KEY (`ORDER_NBR`) REFERENCES `doctor_order` (`DOCTOR_ORDER_NBR`),
  ADD CONSTRAINT `order_attribute_value_ibfk_2` FOREIGN KEY (`ATTRIBUTE_NAME`) REFERENCES `attribute` (`NAME`);

--
-- Constraints for table `order_type_has_attributes`
--
ALTER TABLE `order_type_has_attributes`
  ADD CONSTRAINT `order_type_has_attributes_ibfk_1` FOREIGN KEY (`ORDER_TYPE_CD`) REFERENCES `doctor_order_type` (`ORDER_TYPE_CD`),
  ADD CONSTRAINT `order_type_has_attributes_ibfk_2` FOREIGN KEY (`ATTRIBUTE_NAME`) REFERENCES `attribute` (`NAME`);

--
-- Constraints for table `package_has_service`
--
ALTER TABLE `package_has_service`
  ADD CONSTRAINT `package_has_service_ibfk_2` FOREIGN KEY (`SERVICE_CODE`) REFERENCES `service` (`SERVICE_CODE`),
  ADD CONSTRAINT `package_has_service_ibfk_3` FOREIGN KEY (`PACKAGE_ID`) REFERENCES `service_package` (`PACKAGE_ID`);

--
-- Constraints for table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `patient_ibfk_1` FOREIGN KEY (`REGISTRATION_TYPE_CODE`) REFERENCES `registration_type` (`REGISTRATION_TYPE_CODE`),
  ADD CONSTRAINT `patient_ibfk_10` FOREIGN KEY (`CATEGORY_CODE`) REFERENCES `patient_category` (`CATEGORY_CODE`),
  ADD CONSTRAINT `patient_ibfk_11` FOREIGN KEY (`RATING_CODE`) REFERENCES `patient_rating` (`RATING_CODE`),
  ADD CONSTRAINT `patient_ibfk_12` FOREIGN KEY (`REFERRAL_CODE`) REFERENCES `referral` (`REFERRAL_CODE`),
  ADD CONSTRAINT `patient_ibfk_2` FOREIGN KEY (`REGISTRATION_STATUS`) REFERENCES `registration_status` (`REGISTRATION_STATUS_CODE`),
  ADD CONSTRAINT `patient_ibfk_3` FOREIGN KEY (`SALUATION_CODE`) REFERENCES `saluation` (`SALUATION_CODE`),
  ADD CONSTRAINT `patient_ibfk_4` FOREIGN KEY (`GENDER_CODE`) REFERENCES `gender` (`GENDER_CODE`),
  ADD CONSTRAINT `patient_ibfk_5` FOREIGN KEY (`NATIONALITY_CODE`) REFERENCES `nationality` (`NATIONALITY_CODE`),
  ADD CONSTRAINT `patient_ibfk_6` FOREIGN KEY (`MARITAL_CODE`) REFERENCES `marital` (`MARITAL_CODE`),
  ADD CONSTRAINT `patient_ibfk_7` FOREIGN KEY (`RELIGION_CODE`) REFERENCES `religion` (`RELIGION_CODE`),
  ADD CONSTRAINT `patient_ibfk_8` FOREIGN KEY (`BLOOD_GROUP_CODE`) REFERENCES `blood_group` (`BLOOD_GROUP_CODE`),
  ADD CONSTRAINT `patient_ibfk_9` FOREIGN KEY (`MOTHER_TONGUE_CODE`) REFERENCES `mother_tongue` (`MOTHER_TONGUE_CODE`);

--
-- Constraints for table `patient_allergies`
--
ALTER TABLE `patient_allergies`
  ADD CONSTRAINT `patient_allergies_ibfk_1` FOREIGN KEY (`PATIENT_ID`) REFERENCES `patient` (`PATIENT_ID`),
  ADD CONSTRAINT `patient_allergies_ibfk_2` FOREIGN KEY (`ALLERGY_CODE`) REFERENCES `allergies` (`ALLERGIES_CODE`);

--
-- Constraints for table `patient_details`
--
ALTER TABLE `patient_details`
  ADD CONSTRAINT `patient_details_ibfk_1` FOREIGN KEY (`PATIENT_ID`) REFERENCES `patient` (`PATIENT_ID`),
  ADD CONSTRAINT `patient_details_ibfk_2` FOREIGN KEY (`ID_PROOF_CODE`) REFERENCES `id_proofs` (`ID_PROOFS_CODE`);

--
-- Constraints for table `patient_immunization`
--
ALTER TABLE `patient_immunization`
  ADD CONSTRAINT `patient_immunization_ibfk_1` FOREIGN KEY (`PATIENT_ID`) REFERENCES `patient` (`PATIENT_ID`),
  ADD CONSTRAINT `patient_immunization_ibfk_2` FOREIGN KEY (`IMMUNIZATION_NAME`) REFERENCES `immunization` (`NAME`);

--
-- Constraints for table `patient_vaccination_schedule`
--
ALTER TABLE `patient_vaccination_schedule`
  ADD CONSTRAINT `patient_vaccination_schedule_ibfk_1` FOREIGN KEY (`PATIENT_ID`) REFERENCES `patient` (`PATIENT_ID`),
  ADD CONSTRAINT `patient_vaccination_schedule_ibfk_2` FOREIGN KEY (`SCHEDULE_NAME`) REFERENCES `vaccination_schedule` (`SCHEDULE_NAME`),
  ADD CONSTRAINT `patient_vaccination_schedule_ibfk_3` FOREIGN KEY (`ASSIGNED_BY_DOCTOR_ID`) REFERENCES `doctor` (`DOCTOR_ID`);

--
-- Constraints for table `patient_vaccination_schedule_details`
--
ALTER TABLE `patient_vaccination_schedule_details`
  ADD CONSTRAINT `patient_vaccination_schedule_details_ibfk_2` FOREIGN KEY (`SCHEDULE_NAME`) REFERENCES `vaccination_schedule` (`SCHEDULE_NAME`),
  ADD CONSTRAINT `patient_vaccination_schedule_details_ibfk_3` FOREIGN KEY (`PATIENT_ID`) REFERENCES `patient` (`PATIENT_ID`),
  ADD CONSTRAINT `patient_vaccination_schedule_details_ibfk_4` FOREIGN KEY (`VACCIANTION_CD`) REFERENCES `vaccination` (`VACCINATION_CD`),
  ADD CONSTRAINT `patient_vaccination_schedule_details_ibfk_5` FOREIGN KEY (`SEQ_NBR`) REFERENCES `patient_vaccination_schedule` (`SEQ_NBR`),
  ADD CONSTRAINT `patient_vaccination_schedule_details_ibfk_6` FOREIGN KEY (`GIVEN_BY_DOCTOR_ID`) REFERENCES `doctor` (`DOCTOR_ID`);

--
-- Constraints for table `plan_has_services`
--
ALTER TABLE `plan_has_services`
  ADD CONSTRAINT `plan_has_services_ibfk_1` FOREIGN KEY (`PLAN_CD`) REFERENCES `insurance_plans` (`PLAN_CD`);

--
-- Constraints for table `prescription_medicine_assoc`
--
ALTER TABLE `prescription_medicine_assoc`
  ADD CONSTRAINT `prescription_medicine_assoc_ibfk_1` FOREIGN KEY (`PRESCRIPTION_NUMBER`) REFERENCES `prescription` (`PRESCRIPTION_NUMBER`),
  ADD CONSTRAINT `prescription_medicine_assoc_ibfk_2` FOREIGN KEY (`MEDICINE_CODE`) REFERENCES `medicines` (`MEDICINE_CODE`);

--
-- Constraints for table `record`
--
ALTER TABLE `record`
  ADD CONSTRAINT `record_ibfk_1` FOREIGN KEY (`SERVICE_UID`) REFERENCES `assigned_services` (`SERVICE_UID`);

--
-- Constraints for table `referral`
--
ALTER TABLE `referral`
  ADD CONSTRAINT `referral_ibfk_2` FOREIGN KEY (`COUNTRY_CODE`) REFERENCES `country` (`COUNTRY_CODE`);

--
-- Constraints for table `referral_commission`
--
ALTER TABLE `referral_commission`
  ADD CONSTRAINT `referral_commission_ibfk_1` FOREIGN KEY (`REFERRAL_CODE`) REFERENCES `referral` (`REFERRAL_CODE`),
  ADD CONSTRAINT `referral_commission_ibfk_2` FOREIGN KEY (`COMMISSION_PROCESS_TYPE_CD`) REFERENCES `referral_commission_process` (`COMMISSION_TYPE_CODE`);

--
-- Constraints for table `referral_payable`
--
ALTER TABLE `referral_payable`
  ADD CONSTRAINT `referral_payable_ibfk_1` FOREIGN KEY (`PATIENT_ID`) REFERENCES `patient` (`PATIENT_ID`),
  ADD CONSTRAINT `referral_payable_ibfk_2` FOREIGN KEY (`REFERRAL_CODE`) REFERENCES `referral` (`REFERRAL_CODE`),
  ADD CONSTRAINT `referral_payable_ibfk_3` FOREIGN KEY (`COMMISSION_PROCESS_TYPE_CD`) REFERENCES `referral_commission_process` (`COMMISSION_TYPE_CODE`);

--
-- Constraints for table `registration_history`
--
ALTER TABLE `registration_history`
  ADD CONSTRAINT `registration_history_ibfk_1` FOREIGN KEY (`PATIENT_ID`) REFERENCES `patient` (`PATIENT_ID`),
  ADD CONSTRAINT `registration_history_ibfk_2` FOREIGN KEY (`CHANGE_REASON_CODE`) REFERENCES `change_reason` (`CHANGE_REASON_CODE`);

--
-- Constraints for table `reminders`
--
ALTER TABLE `reminders`
  ADD CONSTRAINT `reminders_ibfk_1` FOREIGN KEY (`APPOINTMENT_NUMBER`) REFERENCES `appointments` (`APPOINTMENT_NUMBER`),
  ADD CONSTRAINT `reminders_ibfk_2` FOREIGN KEY (`REMINDER_FOR`) REFERENCES `app_entities` (`ENTITY_TYPE`),
  ADD CONSTRAINT `reminders_ibfk_3` FOREIGN KEY (`REMINDER_OPTION_CODE`) REFERENCES `reminder_options` (`REMINDER_OPTIONS_CODE`),
  ADD CONSTRAINT `reminders_ibfk_4` FOREIGN KEY (`DURATION_CODE`) REFERENCES `duration` (`DURATION_CODE`);

--
-- Constraints for table `rendered_service`
--
ALTER TABLE `rendered_service`
  ADD CONSTRAINT `rendered_service_ibfk_1` FOREIGN KEY (`SERVICE_UID`) REFERENCES `assigned_services` (`SERVICE_UID`),
  ADD CONSTRAINT `rendered_service_ibfk_2` FOREIGN KEY (`PACKAGE_ASGM_ID`) REFERENCES `assigned_package` (`PACKAGE_ASGM_ID`);

--
-- Constraints for table `roster`
--
ALTER TABLE `roster`
  ADD CONSTRAINT `roster_ibfk_1` FOREIGN KEY (`ROOM_CODE`) REFERENCES `room` (`ROOM_CODE`);

--
-- Constraints for table `samples`
--
ALTER TABLE `samples`
  ADD CONSTRAINT `samples_ibfk_1` FOREIGN KEY (`SAMPLE_CODE`) REFERENCES `sample_master` (`SAMPLE_CODE`),
  ADD CONSTRAINT `samples_ibfk_2` FOREIGN KEY (`RECORD_ID`) REFERENCES `record` (`RECORD_ID`);

--
-- Constraints for table `service`
--
ALTER TABLE `service`
  ADD CONSTRAINT `service_ibfk_1` FOREIGN KEY (`SERVICE_STATUS_CODE`) REFERENCES `service_status` (`SERVICE_STATUS_CODE`),
  ADD CONSTRAINT `service_ibfk_2` FOREIGN KEY (`DEPARTMENT_CODE`) REFERENCES `department` (`DEPARTMENT_CODE`),
  ADD CONSTRAINT `service_ibfk_3` FOREIGN KEY (`SERVICE_GROUP_CODE`) REFERENCES `service_group` (`SERVICE_GROUP_CODE`);

--
-- Constraints for table `service_group`
--
ALTER TABLE `service_group`
  ADD CONSTRAINT `service_group_ibfk_1` FOREIGN KEY (`PARENT_GROUP_ID`) REFERENCES `service_group` (`SERVICE_GROUP_CODE`);

--
-- Constraints for table `service_history`
--
ALTER TABLE `service_history`
  ADD CONSTRAINT `service_history_ibfk_1` FOREIGN KEY (`SERVICE_CODE`) REFERENCES `service` (`SERVICE_CODE`),
  ADD CONSTRAINT `service_history_ibfk_2` FOREIGN KEY (`NEW_STATUS_CODE`) REFERENCES `service_status` (`SERVICE_STATUS_CODE`);

--
-- Constraints for table `service_package`
--
ALTER TABLE `service_package`
  ADD CONSTRAINT `service_package_ibfk_1` FOREIGN KEY (`STATUS_CODE`) REFERENCES `service_package_status` (`STATUS_CODE`);

--
-- Constraints for table `service_package_history`
--
ALTER TABLE `service_package_history`
  ADD CONSTRAINT `service_package_history_ibfk_1` FOREIGN KEY (`PACKAGE_ID`) REFERENCES `service_package` (`PACKAGE_ID`),
  ADD CONSTRAINT `service_package_history_ibfk_2` FOREIGN KEY (`NEW_STATUS_CODE`) REFERENCES `service_package_status` (`STATUS_CODE`);

--
-- Constraints for table `service_price_detail`
--
ALTER TABLE `service_price_detail`
  ADD CONSTRAINT `service_price_detail_ibfk_1` FOREIGN KEY (`SERVICE_CD`) REFERENCES `service` (`SERVICE_CODE`);

--
-- Constraints for table `sponsor_insurer_association`
--
ALTER TABLE `sponsor_insurer_association`
  ADD CONSTRAINT `sponsor_insurer_association_ibfk_1` FOREIGN KEY (`SPONSOR_NAME`) REFERENCES `claim_sponsor` (`SPONSORS_NAME`),
  ADD CONSTRAINT `sponsor_insurer_association_ibfk_2` FOREIGN KEY (`INSURER_NAME`) REFERENCES `insurer` (`INSURER_NAME`);

--
-- Constraints for table `um_app_user`
--
ALTER TABLE `um_app_user`
  ADD CONSTRAINT `app_user_ibfk_1` FOREIGN KEY (`CONTACT_DETAIL_ID`) REFERENCES `um_contact_detail` (`CONTACT_DETAIL_ID`);

--
-- Constraints for table `um_role_has_service`
--
ALTER TABLE `um_role_has_service`
  ADD CONSTRAINT `role_has_service_ibfk_2` FOREIGN KEY (`SERVICE_ID`) REFERENCES `um_service` (`SERVICE_ID`),
  ADD CONSTRAINT `um_role_has_service_ibfk_1` FOREIGN KEY (`ROLE_ID`) REFERENCES `um_role` (`ROLE_ID`);

--
-- Constraints for table `um_state`
--
ALTER TABLE `um_state`
  ADD CONSTRAINT `state_ibfk_1` FOREIGN KEY (`COUNTRY_CODE`) REFERENCES `um_country` (`COUNTRY_CODE`);

--
-- Constraints for table `um_user_has_role`
--
ALTER TABLE `um_user_has_role`
  ADD CONSTRAINT `um_user_has_role_ibfk_1` FOREIGN KEY (`APP_USER_ID`) REFERENCES `um_app_user` (`APP_USER_ID`),
  ADD CONSTRAINT `um_user_has_role_ibfk_2` FOREIGN KEY (`ROLE_ID`) REFERENCES `um_role` (`ROLE_ID`);

--
-- Constraints for table `um_user_password_history`
--
ALTER TABLE `um_user_password_history`
  ADD CONSTRAINT `user_password_history_ibfk_1` FOREIGN KEY (`APP_USER_ID`) REFERENCES `um_app_user` (`APP_USER_ID`);

--
-- Constraints for table `vaccination_schedule_details`
--
ALTER TABLE `vaccination_schedule_details`
  ADD CONSTRAINT `vaccination_schedule_details_ibfk_1` FOREIGN KEY (`SCHEDULE_NAME`) REFERENCES `vaccination_schedule` (`SCHEDULE_NAME`),
  ADD CONSTRAINT `vaccination_schedule_details_ibfk_2` FOREIGN KEY (`VACCIANTION_CD`) REFERENCES `vaccination` (`VACCINATION_CD`);
