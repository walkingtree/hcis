-- phpMyAdmin SQL Dump
-- version 3.1.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jan 09, 2010 at 10:48 AM
-- Server version: 5.1.30
-- PHP Version: 5.2.8

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
  `CONSULTATION_CHARGE` double  DEFAULT '0',
  `APPOINTMENT_STATUS_CODE` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CAPTURED_DTM` datetime DEFAULT NULL,
  `APPOINTMENT_REMARKS` varchar(225) COLLATE utf8_unicode_ci DEFAULT NULL,
  `NEXT_VISIT_DT` date DEFAULT NULL,
  `ROSTER_CODE` int(11) DEFAULT NULL,
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
  `SERVICE_CODE` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PACKAGE_ASGM_ID` int(11) DEFAULT NULL,
  `PATIENT_ID` int(11) DEFAULT NULL,
  `DOCTOR_ID` int(11) DEFAULT NULL,
  `SERVICE_DATE` datetime DEFAULT NULL,
  `REQUESTED_UNITS` int(11) NOT NULL,
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
-- Table structure for table `gender`
--

CREATE TABLE IF NOT EXISTS `gender` (
  `GENDER_CODE` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`GENDER_CODE`)
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=18 ;

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

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `appointments_ibfk_7` FOREIGN KEY (`ESPECIALTY_CODE`) REFERENCES `especialty` (`ESPECIALTY_CODE`),
  ADD CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`PATIENT_ID`) REFERENCES `patient` (`PATIENT_ID`),
  ADD CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`BOOKING_TYPE_CODE`) REFERENCES `booking_type` (`BOOKING_TYPE_CODE`),
  ADD CONSTRAINT `appointments_ibfk_3` FOREIGN KEY (`REFERRAL_CODE`) REFERENCES `referral` (`REFERRAL_CODE`),
  ADD CONSTRAINT `appointments_ibfk_4` FOREIGN KEY (`ROSTER_CODE`) REFERENCES `roster` (`ROSTER_CODE`),
  ADD CONSTRAINT `appointments_ibfk_5` FOREIGN KEY (`DOCTOR_ID`) REFERENCES `doctor` (`DOCTOR_ID`),
  ADD CONSTRAINT `appointments_ibfk_6` FOREIGN KEY (`APPOINTMENT_STATUS_CODE`) REFERENCES `appointment_status` (`STATUS_CODE`);

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
  ADD CONSTRAINT `assigned_services_ibfk_4` FOREIGN KEY (`PACKAGE_ASGM_ID`) REFERENCES `assigned_package` (`PACKAGE_ASGM_ID`);

--
-- Constraints for table `assigned_service_history`
--
ALTER TABLE `assigned_service_history`
  ADD CONSTRAINT `assigned_service_history_ibfk_1` FOREIGN KEY (`SERVICE_UID`) REFERENCES `assigned_services` (`SERVICE_UID`),
  ADD CONSTRAINT `assigned_service_history_ibfk_2` FOREIGN KEY (`CHANGE_STATUS_CODE`) REFERENCES `assigned_service_status` (`SERVICE_STATUS_CODE`);

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
  ADD CONSTRAINT `dept_especiality_assoc_ibfk_2` FOREIGN KEY (`ESPECIALITY_CODE`) REFERENCES `especialty` (`ESPECIALTY_CODE`),
  ADD CONSTRAINT `dept_especiality_assoc_ibfk_1` FOREIGN KEY (`DEPARTMENT_CODE`) REFERENCES `department` (`DEPARTMENT_CODE`);

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
-- Constraints for table `entity_contact_code`
--
ALTER TABLE `entity_contact_code`
  ADD CONSTRAINT `entity_contact_code_ibfk_1` FOREIGN KEY (`CONTACT_TYPE_IND`) REFERENCES `contact_type` (`CONTACT_TYPE_IND`),
  ADD CONSTRAINT `entity_contact_code_ibfk_2` FOREIGN KEY (`CONTACT_CODE`) REFERENCES `contact_details` (`CONTACT_CODE`),
  ADD CONSTRAINT `entity_contact_code_ibfk_3` FOREIGN KEY (`FOR_ENTITY_CODE`) REFERENCES `app_entities` (`ENTITY_TYPE`);

--
-- Constraints for table `medicines`
--
ALTER TABLE `medicines`
  ADD CONSTRAINT `medicines_ibfk_1` FOREIGN KEY (`BRAND_CODE`) REFERENCES `brand` (`BRAND_CODE`),
  ADD CONSTRAINT `medicines_ibfk_2` FOREIGN KEY (`MEDICINE_TYPE_CODE`) REFERENCES `medicine_type` (`MEDICINE_TYPE_CODE`);

--
-- Constraints for table `observations`
--
ALTER TABLE `observations`
  ADD CONSTRAINT `observations_ibfk_1` FOREIGN KEY (`APPOINTMENT_NUMBER`) REFERENCES `appointments` (`APPOINTMENT_NUMBER`),
  ADD CONSTRAINT `observations_ibfk_2` FOREIGN KEY (`OBSERVED_BY`) REFERENCES `doctor` (`DOCTOR_ID`);

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
  ADD CONSTRAINT `patient_vaccination_schedule_ibfk_3` FOREIGN KEY (`ASSIGNED_BY_DOCTOR_ID`) REFERENCES `doctor` (`DOCTOR_ID`),
  ADD CONSTRAINT `patient_vaccination_schedule_ibfk_1` FOREIGN KEY (`PATIENT_ID`) REFERENCES `patient` (`PATIENT_ID`),
  ADD CONSTRAINT `patient_vaccination_schedule_ibfk_2` FOREIGN KEY (`SCHEDULE_NAME`) REFERENCES `vaccination_schedule` (`SCHEDULE_NAME`);

--
-- Constraints for table `patient_vaccination_schedule_details`
--
ALTER TABLE `patient_vaccination_schedule_details`
  ADD CONSTRAINT `patient_vaccination_schedule_details_ibfk_6` FOREIGN KEY (`GIVEN_BY_DOCTOR_ID`) REFERENCES `doctor` (`DOCTOR_ID`),
  ADD CONSTRAINT `patient_vaccination_schedule_details_ibfk_2` FOREIGN KEY (`SCHEDULE_NAME`) REFERENCES `vaccination_schedule` (`SCHEDULE_NAME`),
  ADD CONSTRAINT `patient_vaccination_schedule_details_ibfk_3` FOREIGN KEY (`PATIENT_ID`) REFERENCES `patient` (`PATIENT_ID`),
  ADD CONSTRAINT `patient_vaccination_schedule_details_ibfk_4` FOREIGN KEY (`VACCIANTION_CD`) REFERENCES `vaccination` (`VACCINATION_CD`),
  ADD CONSTRAINT `patient_vaccination_schedule_details_ibfk_5` FOREIGN KEY (`SEQ_NBR`) REFERENCES `patient_vaccination_schedule` (`SEQ_NBR`);

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
  ADD CONSTRAINT `referral_payable_ibfk_3` FOREIGN KEY (`COMMISSION_PROCESS_TYPE_CD`) REFERENCES `referral_commission_process` (`COMMISSION_TYPE_CODE`),
  ADD CONSTRAINT `referral_payable_ibfk_1` FOREIGN KEY (`PATIENT_ID`) REFERENCES `patient` (`PATIENT_ID`),
  ADD CONSTRAINT `referral_payable_ibfk_2` FOREIGN KEY (`REFERRAL_CODE`) REFERENCES `referral` (`REFERRAL_CODE`);

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
-- Constraints for table `vaccination_schedule_details`
--
ALTER TABLE `vaccination_schedule_details`
  ADD CONSTRAINT `vaccination_schedule_details_ibfk_2` FOREIGN KEY (`VACCIANTION_CD`) REFERENCES `vaccination` (`VACCINATION_CD`),
  ADD CONSTRAINT `vaccination_schedule_details_ibfk_1` FOREIGN KEY (`SCHEDULE_NAME`) REFERENCES `vaccination_schedule` (`SCHEDULE_NAME`);
