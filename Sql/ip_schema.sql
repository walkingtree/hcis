-- phpMyAdmin SQL Dump
-- version 2.11.9.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 09, 2009 at 12:27 PM
-- Server version: 5.0.67
-- PHP Version: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `hcis`
--

-- --------------------------------------------------------

--
-- Table structure for table `action_status`
--

CREATE TABLE IF NOT EXISTS `action_status` (
  `ACTION_STATUS_CD` varchar(30) NOT NULL,
  `ACTION_STATUS_DESC` varchar(256) default NULL,
  `CUSTOM_DESC` varchar(256) default NULL,
  `ACTIVE_FLAG` char(1) default NULL,
  PRIMARY KEY  (`ACTION_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `action_status`
--

INSERT INTO `action_status` (`ACTION_STATUS_CD`, `ACTION_STATUS_DESC`, `CUSTOM_DESC`, `ACTIVE_FLAG`) VALUES
('ASSIGNED', 'Assigned', NULL, 'Y'),
('COMPLETED', 'Complete', NULL, 'Y'),
('CREATED', 'Created', NULL, 'Y'),
('HOLD', 'On Hold', NULL, 'Y'),
('INPROGRESS', 'In Progress', NULL, 'Y'),
('REJECTED', 'Rejected', NULL, 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `activity_type`
--

CREATE TABLE IF NOT EXISTS `activity_type` (
  `ACTIVITY_TYPE_CD` varchar(30) NOT NULL,
  `ACTIVITY_DESC` varchar(256) default NULL,
  `ACTIVITY_GROUP` varchar(25) default NULL,
  PRIMARY KEY  (`ACTIVITY_TYPE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `activity_type`
--
-- phpMyAdmin SQL Dump
-- version 3.1.3.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 08, 2010 at 06:09 PM
-- Server version: 5.1.33
-- PHP Version: 5.2.9

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `hcisit`
--

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

--
-- Dumping data for table `activity_type`
--

INSERT INTO `activity_type` (`ACTIVITY_TYPE_CD`, `ACTIVITY_DESC`, `ACTIVITY_GROUP`) VALUES
('ADMISSION_REQUEST', 'Admission Request ', 'GENERAL'),
('ADM_ADMITTED', 'Patient admitted', 'ADMISSION'),
('ADM_APPROVED', 'Admission Request Approved', 'ADMISSION'),
('ADM_CANCELLED', 'Admission Canceled', 'ADMISSION'),
('ADM_DISAPPROVED', 'Admission Approved', 'ADMISSION'),
('ADM_REQUESTED', 'Admission is Requested', 'ADMISSION'),
('ADM_RESCHEDULED', 'Admission Requested', 'ADMISSION'),
('BED_ADDED', 'Bed is added', 'BED_ACTIVITY'),
('BED_AVAILABLE', 'Bed is available', 'BED_ACTIVITY'),
('BED_OCCUPIED', 'Bed is occupied', 'BED_ACTIVITY'),
('BED_RES_ASSIGNED', 'Bed is assigned', 'BED_RES_ACTIVITY'),
('BED_RES_BOOKED', 'Bed is booked', 'BED_RES_ACTIVITY'),
('BED_RES_CANCELED', 'Canceled', 'BED_RES_ACTIVITY'),
('BED_RES_CONFIRMED', 'Confirmed', 'BED_RES_ACTIVITY'),
('BED_RES_WAITLIST', 'Waitlist', 'BED_RES_ACTIVITY'),
('BED_RETIRED', 'Bed is retired', 'BED_ACTIVITY'),
('BED_UNIT_CHANGED', 'Bed has been moved to another unit', 'BED_ACTIVITY'),
('CARRY_TO_DISCHARGE', 'Carry patient to discharge area', 'DISCHARGE'),
('CLM_REQ_APPROVED', 'Insurer has approved complete amount requested by the hospital', 'MEDICLAIM'),
('CLM_REQ_CREATED', 'Claim request has been created', 'MEDICLAIM'),
('CLM_REQ_MOREINFO', 'Insurer has requested for more information from the hospital', 'MEDICLAIM'),
('CLM_REQ_PARTAPPROVED', 'Insurer has partially approved the requested amount', 'MEDICLAIM'),
('CLM_REQ_REJECTED', 'Insurer has rejected the requested insurance amount', 'MEDICLAIM'),
('CLM_REQ_RESUBMITTED', 'The hospital has sent the requested information to the insurer and asked for approval again', 'MEDICLAIM'),
('CLM_REQ_SUBMITTED', 'Claim request has been submitted to the insurer', 'MEDICLAIM'),
('DISC_APPROVED', 'Discharge approved', 'DISCHARGE'),
('DISC_COMPLETED', 'Discharge completed', 'DISCHARGE'),
('DISC_CREATED', 'Discharge entry created', 'DISCHARGE'),
('DISC_DISAPPROVED', 'Discharge disapproved', 'DISCHARGE'),
('ORDER_APPROVED ', 'order approved', 'ORDER'),
('ORDER_CANCELED', 'Order canceled', 'ORDER'),
('ORDER_COMPLETED', 'Order completed', 'ORDER'),
('ORDER_CREATED', 'Order is just created', 'ORDER'),
('ORDER_DISAPPROVED', 'Ordeer disapproved', 'ORDER'),
('PHARMACY_CREDIT_DISCHARGE', 'Issue credit notes to pharmacy for access medicines', 'DISCHARGE'),
('REGISTRATION', 'Patient Registartion', 'GENERAL'),
('RESCHDULE_APPOINTMENTS', 'Reschedule an existing appointment', 'APPOINTMENTS'),
('RESCHEDULED', 'Rescheduleed', NULL);
-- --------------------------------------------------------

--
-- Table structure for table `admission`
--

CREATE TABLE IF NOT EXISTS `admission` (
  `ADMISSION_REQ_NBR` int(11) NOT NULL auto_increment,
  `ADMISSION_NBR` int(11) default '0',
  `ADMISSION_STATUS_CD` varchar(20) default NULL,
  `ADMISSION_REQUESTED_BY` varchar(20) NOT NULL,
  `DOCTOR_ID` int(11) NOT NULL,
  `PATIENT_ID` int(11) NOT NULL,
  `ENTRY_POINT_REFERENCE` varchar(45) default NULL,
  `ENTRY_POINT_NAME` varchar(25) default NULL,
  `REASON_FOR_ADMISSION` varchar(256) default NULL,
  `AGENDA` longtext,
  `ADMISSION_DT` date default NULL,
  `TREATMENT_ESTIMATION_BY` varchar(20) default NULL,
  `TREATMENT_ESTIMATED_COST` double default NULL,
  `TREATMENT_ACTUAL_COST` double default NULL,
  `EXPECTED_DISCHARGE_DTM` timestamp NULL default NULL,
  `DISCHARGE_DTM` timestamp NULL default NULL,
  `DISCHARGE_BY_DOCTOR_ID` int(11) default NULL,
  `NEXT_VISIT_DT` date default NULL,
  `CREATE_DTM` timestamp NOT NULL default '0000-00-00 00:00:00',
  `LAST_UPDATED_DTM` timestamp NULL default NULL,
  `MODIFIED_BY` varchar(20) default NULL,
  `VERSION` int(11) NOT NULL,
  `NURSING_UNIT_TYPE_CD` varchar(30) default NULL,
  PRIMARY KEY  (`ADMISSION_REQ_NBR`),
  UNIQUE KEY `ADMISSION_NBR` (`ADMISSION_NBR`),
  KEY `ADMISSION_ENTRY_POINT_NAME_FK` (`ENTRY_POINT_NAME`),
  KEY `ADMISSION_NURSING_UNIT_TYPE_CD_FK` (`NURSING_UNIT_TYPE_CD`),
  KEY `ADMISSION_STATUS_CD_FK` (`ADMISSION_STATUS_CD`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `admission`
--

-- --------------------------------------------------------

--
-- Table structure for table `admission_activity`
--

CREATE TABLE IF NOT EXISTS `admission_activity` (
  `ADMISSION_REQ_NBR` int(11) NOT NULL,
  `ACTIVITY_TYPE_CD` varchar(30) NOT NULL,
  `ACTIVITY_DTM` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `ADMISSION_STATUS_CD` varchar(20) NOT NULL,
  `REMARKS` mediumtext,
  `CREATED_BY` varchar(25) default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`ADMISSION_REQ_NBR`,`ACTIVITY_TYPE_CD`,`ACTIVITY_DTM`),
  KEY `ADMISSION_ACTIVITY_TYPE_CD_FK` (`ACTIVITY_TYPE_CD`),
  KEY `ADMISSION_ACTIVITY_STATUS_CD_FK` (`ADMISSION_STATUS_CD`),
  KEY `ADMISSION_ACTIVITY_ADMISSION_NBR_FK` (`ADMISSION_REQ_NBR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admission_activity`
--

-- --------------------------------------------------------

--
-- Table structure for table `admission_claim_activity`
--

CREATE TABLE IF NOT EXISTS `admission_claim_activity` (
  `REQUEST_SEQUENCE_NBR` bigint(20) NOT NULL,
  `ACTIVITY_TYPE_CD` varchar(30) NOT NULL,
  `CREATE_DTM` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `CLAIM_STATUS_CD` varchar(20) NOT NULL,
  `CREATED_BY` varchar(20) default NULL,
  `REMARKS` varchar(256) default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`REQUEST_SEQUENCE_NBR`,`CREATE_DTM`,`ACTIVITY_TYPE_CD`),
  KEY `SPONSOR_ACTIVITY_ACTIVITY_TYPE_CD_FK` (`ACTIVITY_TYPE_CD`),
  KEY `SPONSOR_ACTIVITY_CLAIM_STATUS_CD` (`CLAIM_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admission_claim_activity`
--


-- --------------------------------------------------------

--
-- Table structure for table `admission_claim_request`
--

CREATE TABLE IF NOT EXISTS `admission_claim_request` (
  `REQUEST_SEQUENCE_NBR` bigint(20) NOT NULL auto_increment,
  `ADMISSION_REQ_NBR` int(11) NOT NULL,
  `CLAIM_SUBSEQUENCE_NBR` int(11) NOT NULL default '1',
  `SPONSOR_NAME` varchar(80) NOT NULL,
  `INSURER_NAME` varchar(80) default NULL,
  `POLICY_NBR` varchar(30) default NULL,
  `PLAN_CD` varchar(30) NOT NULL,
  `POLICY_EFFECTIVE_FROM_DT` date default NULL,
  `POLICY_EFFECTIVE_TO_DT` date NOT NULL,
  `PREFERENCE_SEQUENCE_NBR` int(11) default NULL,
  `POLICY_HOLDER_NAME` varchar(80) default NULL,
  `CREATED_BY` varchar(20) default NULL,
  `CREATED_DTM` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `REQUEST_DTM` timestamp NULL default NULL,
  `REQUESTED_AMOUNT` double NOT NULL,
  `CLAIM_STATUS_CD` varchar(20) default NULL,
  `APPROVAL_THROUGH` varchar(30) default 'FAX',
  `APPROVAL_DTM` timestamp NULL default NULL,
  `APPROVED_AMOUNT` double default NULL,
  `FINAL_CLAIMED_AMOUNT` double default NULL,
  `PATIENT_AMOUNT` double default NULL,
  `LAST_MODIFIED_DTM` timestamp NULL default NULL,
  `BILL_NBR` bigint(20) default NULL,
  `MODIFIED_BY` varchar(20) default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`REQUEST_SEQUENCE_NBR`),
  UNIQUE KEY `ADMISSION_SUBSEQUENCE_NBR` (`ADMISSION_REQ_NBR`,`CLAIM_SUBSEQUENCE_NBR`),
  KEY `CLAIM_REQUEST_ADMISSION_NBR_FK1` (`ADMISSION_REQ_NBR`),
  KEY `CLAIM_REQUEST_STATUS_CD_FK` (`CLAIM_STATUS_CD`),
  KEY `CLAIM_REQUEST_SPONSOR_NAME` (`SPONSOR_NAME`),
  KEY `CLAIM_REQUEST_PLAN_CD` (`PLAN_CD`),
  KEY `CLAIM_REQUEST_INSURER_NAME_FK` (`INSURER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `admission_claim_request`
--


-- --------------------------------------------------------

--
-- Table structure for table `admission_entry_point`
--

CREATE TABLE IF NOT EXISTS `admission_entry_point` (
  `ENTRY_POINT_NAME` varchar(25) NOT NULL,
  `ENTRY_POINT_DESC` varchar(256) default NULL,
  PRIMARY KEY  (`ENTRY_POINT_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admission_entry_point`
--


INSERT INTO `admission_entry_point` (`ENTRY_POINT_NAME`, `ENTRY_POINT_DESC`) VALUES
('OPD', 'OPD');
-- --------------------------------------------------------

--
-- Table structure for table `admission_status`
--

CREATE TABLE IF NOT EXISTS `admission_status` (
  `ADMISSION_STATUS_CD` varchar(30) NOT NULL,
  `ADMISSION_STATUS_DESC` varchar(256) default NULL,
  `ACTIVE_FLAG` char(1) default NULL,
  PRIMARY KEY  (`ADMISSION_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admission_status`
--

INSERT INTO `admission_status` (`ADMISSION_STATUS_CD`, `ADMISSION_STATUS_DESC`, `ACTIVE_FLAG`) VALUES
('ADMITTED', 'Admitted', 'Y'),
('APPROVED', 'Approved', 'Y'),
('BED_ASSIGNED', 'Bed assigned', 'Y'),
('CANCELED', 'Canceled', 'Y'),
('DISAPPROVED', 'Disapproved', 'Y'),
('DISCHARED', 'Discharged', 'Y'),
('EMERGENCY_REQUEST', 'Emergency Request', 'Y'),
('PRE_DISCHARGE', 'Pre-Discharge', 'Y'),
('REQUESTED', 'Requested', 'Y'),
('RESCHEDULED', 'Rescheduled', 'Y');
-- --------------------------------------------------------

--
-- Table structure for table `attribute`
--

CREATE TABLE IF NOT EXISTS `attribute` (
  `NAME` varchar(35) NOT NULL,
  `DESCRIPTION` varchar(100) NOT NULL,
  `TYPE` varchar(20) NOT NULL,
  `PROVIDER` varchar(100) default NULL,
  PRIMARY KEY  (`NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attribute`
--

INSERT INTO `attribute` (`NAME`, `DESCRIPTION`, `TYPE`, `PROVIDER`) VALUES
('admissionStatus', 'Admission status', 'MVL', 'in.wtc.hcis.ip.common.IPReferenceDataManager.getAdmissionStatus'),
('agenda', 'Agenda', 'longtext', NULL),
('dischargeSummary', 'Discharge summary', 'longtext', NULL),
('dischargeType', 'Discharge type', 'MVL', 'in.wtc.hcis.ip.common.IPReferenceDataManager.getDischargeType'),
('entryPoint', 'Entry point', 'MVL', 'in.wtc.hcis.ip.common.IPReferenceDataManager.getAdmissionEntryPoints'),
('entryPointReference', 'Entry point reference', 'string', NULL),
('estimatedCost', 'Estimated cost', 'number', ''),
('expectedAdmissionDate', 'EDOA', 'datetime', ''),
('expectedDischargeDate', 'EDOD', 'datetime', ''),
('reasonForAdmission', 'Reason for  Admission', 'longtext', NULL),
('unitType', 'Unit type', 'MVL', 'in.wtc.hcis.ip.common.IPReferenceDataManager.getNursingUnitType');
-- --------------------------------------------------------

--
-- Table structure for table `bed_activity`
--

CREATE TABLE IF NOT EXISTS `bed_activity` (
  `BED_NUMBER` varchar(30) NOT NULL,
  `ACTIVITY_TYPE_CD` varchar(30) NOT NULL,
  `CREATE_DTM` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `BED_STATUS_CD` varchar(30) NOT NULL,
  `CREATED_BY` varchar(20) default NULL,
  `REMARKS` varchar(256) default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`BED_NUMBER`,`CREATE_DTM`,`ACTIVITY_TYPE_CD`),
  KEY `BA_BED_NUMBER_FK` (`BED_NUMBER`),
  KEY `BA_ACTIVITY_TYPE_CD_FK` (`ACTIVITY_TYPE_CD`),
  KEY `BA_BED_STATUS_CD_FK` (`BED_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bed_activity`
--


-- --------------------------------------------------------

--
-- Table structure for table `bed_bill_history`
--

CREATE TABLE IF NOT EXISTS `bed_bill_history` (
  `BED_ASSIGNMENT_NBR` int(11) NOT NULL,
  `BILL_NBR` int(11) NOT NULL,
  `BILL_FROM_DTM` datetime NOT NULL,
  `BILL_TO_DTM` datetime NOT NULL,
  `BILLED_HOUR_UNIT` int(11) default NULL,
  `BILLED_DAY_UNIT` int(11) default NULL,
  `HOURLY_CHARGE` double default NULL,
  `DAILY_CHARGE` double default NULL,
  `TOTAL_CHARGE` double NOT NULL,
  `CREATE_DTM` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(35) default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`BED_ASSIGNMENT_NBR`,`BILL_NBR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bed_bill_history`
--


-- --------------------------------------------------------

--
-- Table structure for table `bed_envelope`
--

CREATE TABLE IF NOT EXISTS `bed_envelope` (
  `ENVELOPE_NAME` varchar(30) NOT NULL,
  `DESCRIPTION` varchar(256) default NULL,
  `FACILITY_TYPE_IND` char(1) default 'I',
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`ENVELOPE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bed_envelope`
--


-- --------------------------------------------------------

--
-- Table structure for table `bed_envelope_has_pool`
--

CREATE TABLE IF NOT EXISTS `bed_envelope_has_pool` (
  `ENVELOPE_NAME` varchar(30) NOT NULL,
  `POOL_NAME` varchar(30) NOT NULL,
  `EFFECTIVE_FROM_DT` date default NULL,
  `EFFECTIVE_TO_DT` date default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`ENVELOPE_NAME`,`POOL_NAME`),
  KEY `BEHP_ENVELOPE_NAME_FK` (`ENVELOPE_NAME`),
  KEY `BEHP_POOL_NAME_FK` (`POOL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bed_envelope_has_pool`
--


-- --------------------------------------------------------

--
-- Table structure for table `bed_has_special_features`
--

CREATE TABLE IF NOT EXISTS `bed_has_special_features` (
  `BED_NUMBER` varchar(30) NOT NULL,
  `FEATURE_NAME` varchar(40) NOT NULL,
  `EFFECTIVE_FROM_DATE` date default NULL,
  `EFFECTIVE_TO_DATE` date default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`BED_NUMBER`,`FEATURE_NAME`),
  KEY `BED_SPECIAL_FEATURE_NAME_FK` (`FEATURE_NAME`),
  KEY `BED_SPECIAL_FEATURE_BED_NBR_FK` (`BED_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bed_has_special_features`
--

--
-- Table structure for table `bed_master`
--

CREATE TABLE IF NOT EXISTS `bed_master` (
  `BED_NUMBER` varchar(30) NOT NULL,
  `ROOM_NBR` varchar(30) NOT NULL,
  `FLOOR_NBR` varchar(30) default 'Uncategorised',
  `SITE_NAME` varchar(80) default NULL,
  `NURSING_UNIT_NAME` varchar(30) NOT NULL,
  `DESCRIPTION` varchar(256) default NULL,
  `MODIFIED_BY` varchar(20) default NULL,
  `LAST_MODIFIED_DTM` timestamp NULL default NULL,
  `BED_TYPE_CD` varchar(30) NOT NULL,
  `BED_STATUS_CD` varchar(30) NOT NULL,
  `DAILY_CHARGE` double default NULL,
  `HOURLY_CHARGE` double default NULL,
  `DEPOSIT_AMOUNT` double default NULL,
  `ADMISSION_REQ_NBR` int(11) default NULL,
  `ADMISSION_DTM` timestamp NULL default NULL,
  `EXPECTED_DISCHARGE_DTM` timestamp NULL default NULL,
  `PATIENT_ID` int(11) default NULL,
  `DOCTOR_ID` int(11) default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`BED_NUMBER`),
  KEY `BM_BED_STATUS_CD_FK` (`BED_STATUS_CD`),
  KEY `BM_BED_TYPE_CD_FK` (`BED_TYPE_CD`),
  KEY `BM_NURSING_UNIT_NAME_FK` (`NURSING_UNIT_NAME`),
  KEY `BM_ADMISSION_NBR_FK1` (`ADMISSION_REQ_NBR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bed_master`
--
-- --------------------------------------------------------

--
-- Table structure for table `bed_pool`
--

CREATE TABLE IF NOT EXISTS `bed_pool` (
  `BED_POOL_NAME` varchar(30) NOT NULL,
  `POOL_DESC` varchar(256) default NULL,
  `TOTAL_NUMBER_OF_BED` int(11) NOT NULL,
  `NUMBER_OF_AVAILABLE_BEDS` int(11) NOT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`BED_POOL_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bed_pool`
--


-- --------------------------------------------------------

--
-- Table structure for table `bed_pool_has_unit_type`
--

CREATE TABLE IF NOT EXISTS `bed_pool_has_unit_type` (
  `POOL_NAME` varchar(30) NOT NULL,
  `UNIT_TYPE_CD` varchar(30) NOT NULL,
  `EFFECTIVE_FROM_DT` date default NULL,
  `EFFECTIVE_TO_DATE` date default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`POOL_NAME`,`UNIT_TYPE_CD`),
  KEY `BPHUT_POOL_NAME_FK` (`POOL_NAME`),
  KEY `BPHUT_NURSING_UNIT_TYPE_CD_Fk` (`UNIT_TYPE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bed_pool_has_unit_type`
--


-- --------------------------------------------------------

--
-- Table structure for table `bed_reservation`
--

CREATE TABLE IF NOT EXISTS `bed_reservation` (
  `BED_RESERVATION_NBR` int(11) NOT NULL auto_increment,
  `UNIT_TYPE_CD` varchar(30) NOT NULL,
  `BED_TYPE_CD` varchar(30) NOT NULL,
  `REQUEST_VALID_TILL` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `REQUEST_CREATED_BY` varchar(20) NOT NULL,
  `GOT_PREFERRED_ROOM` char(1) default NULL,
  `BED_NUMBER` varchar(30) default NULL,
  `RESERVATION_FROM_DTM` timestamp NOT NULL default '0000-00-00 00:00:00',
  `RESERVATION_TO_DTM` timestamp NULL default NULL,
  `PATIENT_ID` int(11) default NULL,
  `RESERVATION_STATUS_CD` varchar(30) NOT NULL,
  `REQUEST_CREATION_DTM` timestamp NULL default NULL,
  `MODIFIED_BY` varchar(20) default NULL,
  `LAST_MODIFIED_TIME` timestamp NULL default NULL,
  `VERSION` int(11) NOT NULL,
  `ADMISSION_REQ_NUMBER` int(11) NOT NULL,
  PRIMARY KEY  (`BED_RESERVATION_NBR`),
  KEY `BR_BED_NUMBER_FK1` (`BED_NUMBER`),
  KEY `BR_RESERVATION_STATUS_CD_FK` (`RESERVATION_STATUS_CD`),
  KEY `BR_BED_TYPE_CD_FK` (`BED_TYPE_CD`),
  KEY `BR_UNIT_TYPE_CD_FK` (`UNIT_TYPE_CD`),
  KEY `BR_ADMISSION_REQ_NUMBER_FK` (`ADMISSION_REQ_NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `bed_reservation`
--


-- --------------------------------------------------------

--
-- Table structure for table `bed_reservation_activity`
--

CREATE TABLE IF NOT EXISTS `bed_reservation_activity` (
  `BED_RESERVATION_NBR` int(11) NOT NULL,
  `ACTIVITY_TYPE_CD` varchar(30) NOT NULL,
  `CREATE_DTM` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `RESERVATION_STATUS_CD` varchar(30) NOT NULL,
  `REMARKS` varchar(256) default NULL,
  `CREATED_BY` varchar(30) NOT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`BED_RESERVATION_NBR`,`ACTIVITY_TYPE_CD`,`CREATE_DTM`),
  KEY `BRA_BED_RESERVATION_NBR_FK` (`BED_RESERVATION_NBR`),
  KEY `BRA_ACTIVITY_TYPE_CD_FK` (`ACTIVITY_TYPE_CD`),
  KEY `BRA_RESERVATION_STATUS_CD_FK` (`RESERVATION_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bed_reservation_activity`
--


-- --------------------------------------------------------

--
-- Table structure for table `bed_reservation_special_features`
--

CREATE TABLE IF NOT EXISTS `bed_reservation_special_features` (
  `RESERVATION_NBR` int(11) NOT NULL,
  `FEATURE_NAME` varchar(30) NOT NULL,
  `REQUIRED_FLAG` char(1) default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`RESERVATION_NBR`,`FEATURE_NAME`),
  KEY `BRSF_RESERVATION_NBR_FK` (`RESERVATION_NBR`),
  KEY `BRSF_FEATURE_NAME_FK` (`FEATURE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bed_reservation_special_features`
--


-- --------------------------------------------------------

--
-- Table structure for table `bed_special_feature`
--

CREATE TABLE IF NOT EXISTS `bed_special_feature` (
  `FEATURE_NAME` varchar(40) NOT NULL,
  `DESCRIPTION` varchar(256) default NULL,
  `LOCATION_WRT_BED_IND` varchar(10) default NULL,
  `EFFECTIVE_FROM_DTM` timestamp NULL default NULL,
  `EFFECTIVE_TO_DTM` timestamp NULL default NULL,
  PRIMARY KEY  (`FEATURE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bed_special_feature`
--

INSERT INTO `bed_special_feature` (`FEATURE_NAME`, `DESCRIPTION`, `LOCATION_WRT_BED_IND`, `EFFECTIVE_FROM_DTM`, `EFFECTIVE_TO_DTM`) VALUES
('Adjustable', 'Bed hight adjustable', NULL, '2009-08-11 10:03:38', NULL),
('Aerosol Disinfector', 'Aerosol Disinfector', NULL, '2009-10-26 11:42:11', '2009-10-26 11:42:11'),
('Baby Cradle', 'Baby Cradle', NULL, '2009-10-26 11:42:11', '2009-10-26 11:42:11'),
('Bed Safety Rails', 'Bed Safety Rails', NULL, '2009-10-26 11:42:11', '2009-10-26 11:42:11'),
('Bed side Locker', 'Bed side Locker', NULL, '2009-10-26 11:42:11', '2009-10-26 11:42:11'),
('Bed side Screen', 'Bed side Screen', NULL, '2009-10-26 11:42:11', '2009-10-26 11:42:11'),
('Foldable ', 'Bed is foldable by middle', NULL, '2009-08-11 10:03:38', NULL),
('Fridge', 'Fridge', NULL, '2009-10-26 11:42:11', '2009-10-26 11:42:11'),
('Internet connectivity', 'Internet connectivity', NULL, '2009-10-26 11:42:11', '2009-10-26 11:42:11'),
('Over Bed Table', 'Over Bed Table', NULL, '2009-10-26 11:42:11', '2009-10-26 11:42:11'),
('Over Bed Trolley', 'Over Bed Trolley', NULL, '2009-10-26 11:42:11', '2009-10-26 11:42:11'),
('Television', 'Television', NULL, '2009-10-26 11:42:11', '2009-10-26 11:42:11'),
('Trapeze Floor Stand', 'Trapeze Floor Stand', NULL, '2009-10-26 11:42:11', '2009-10-26 11:42:11'),
('Wheel Chair', 'Wheel Chair', NULL, '2009-10-26 11:42:11', '2009-10-26 11:42:11');

-- --------------------------------------------------------

--
-- Table structure for table `bed_status`
--

CREATE TABLE IF NOT EXISTS `bed_status` (
  `BED_STATUS_CD` varchar(30) NOT NULL,
  `DESCRIPTION` varchar(256) default NULL,
  `ACTIVE_FLAG` char(1) default NULL,
  PRIMARY KEY  (`BED_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bed_status`
--

INSERT INTO `bed_status` (`BED_STATUS_CD`, `DESCRIPTION`, `ACTIVE_FLAG`) VALUES
('AVAILABLE', 'Available', 'Y'),
('CLEANING', 'Being Cleaned', 'Y'),
('DEFECTIVE', 'Defective', 'Y'),
('OCCUPIED', 'Occupied', 'Y'),
('PENDING_CLEANING', 'Pending Cleaning', 'Y'),
('RETIRED', 'Retired', 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `bed_type`
--

CREATE TABLE IF NOT EXISTS `bed_type` (
  `BED_TYPE_CD` varchar(30) NOT NULL,
  `BED_TYPE_DESC` varchar(256) default NULL,
  `ACTIVE_FLAG` char(1) default NULL,
  PRIMARY KEY  (`BED_TYPE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bed_type`
--

INSERT INTO `bed_type` (`BED_TYPE_CD`, `BED_TYPE_DESC`, `ACTIVE_FLAG`) VALUES
('DOUBLE', 'Double occupany', 'Y'),
('FEMALE_WARD', 'Female ward', 'Y'),
('GENERAL_WARD', 'General ward', 'Y'),
('OT', 'Operation theater', 'Y'),
('POST_OP', 'Post-operative', 'Y'),
('PRE_OP', 'Pre-operative', 'Y'),
('SINGLE', 'Single occupancy', 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `bed_usage_history`
--

CREATE TABLE IF NOT EXISTS `bed_usage_history` (
  `BED_ASSIGNMENT_NBR` int(11) NOT NULL auto_increment,
  `BED_NUMBER` varchar(30) NOT NULL,
  `ADMISSION_REQ_NBR` int(11) NOT NULL,
  `CHECK_IN_DTM` timestamp NULL default NULL,
  `CHECK_OUT_DTM` timestamp NULL default NULL,
  `LAST_BILL_NBR` int(11) default NULL,
  `LAST_BILL_DTM` timestamp NULL default NULL,
  `CREATED_BY` varchar(45) NOT NULL,
  `CREATE_DTM` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`BED_ASSIGNMENT_NBR`),
  KEY `BED_NUMBER_FK` (`BED_NUMBER`),
  KEY `ADMISSION_REQ_NBR_FK` (`ADMISSION_REQ_NBR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Dumping data for table `bed_usage_history`
--


-- --------------------------------------------------------

--
-- Table structure for table `claim_document`
--

CREATE TABLE IF NOT EXISTS `claim_document` (
  `REQUEST_SEQUENCE_NBR` bigint(20) NOT NULL,
  `DOCUMENT_NAME` varchar(45) NOT NULL,
  `CREATE_DTM` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `DOCUMENT_PATH` varchar(256) default NULL,
  `CREATED_BY` varchar(25) default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`REQUEST_SEQUENCE_NBR`,`CREATE_DTM`,`DOCUMENT_NAME`),
  KEY `CLAIM_DOCUMENT_REQUEST_SEQUENCE_NBR_FK` (`REQUEST_SEQUENCE_NBR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `claim_document`
--


-- --------------------------------------------------------

--
-- Table structure for table `claim_sponsor`
--

CREATE TABLE IF NOT EXISTS `claim_sponsor` (
  `SPONSORS_NAME` varchar(80) NOT NULL,
  `SPONSOR_DESC` varchar(512) default NULL,
  `SPONSOR_TYPE_CD` varchar(30) NOT NULL,
  `ACCOUNT_NUMBER` varchar(30) default NULL,
  `CREDIT_CLASS_CD` varchar(30) default NULL,
  `CONTACT_CODE` int(11) NOT NULL,
  `CREATED_BY` varchar(35) NOT NULL,
  `CREATED_DTM` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `LAST_MODIFIED_BY` varchar(30) default NULL,
  `MODIFIED_DTM` timestamp NULL default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`SPONSORS_NAME`),
  KEY `CS_SPONSOR_TYPE_CD_FK` (`SPONSOR_TYPE_CD`),
  KEY `CS_CREDIT_CLASS_CD_FK` (`CREDIT_CLASS_CD`),
  KEY `CS_CONTACT_CODE_FK` (`CONTACT_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `claim_sponsor`
--
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
  PRIMARY KEY  (`SPONSOR_NAME`,`ACTIVITY_TYPE_CD`),
  KEY `CLAIM_SPONSOR_SLA_SPONSOR_NAME_FK` (`SPONSOR_NAME`),
  KEY `CLAIM_SPONSOR_SLA_ACTIVITY_TYPE_CD_FK` (`ACTIVITY_TYPE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `claim_sponsor_sla`
--


-- --------------------------------------------------------

--
-- Table structure for table `credit_class`
--

CREATE TABLE IF NOT EXISTS `credit_class` (
  `CREDIT_CLASS_CD` varchar(30) NOT NULL,
  `DESCRIPTION` varchar(512) default NULL,
  PRIMARY KEY  (`CREDIT_CLASS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `credit_class`
--

INSERT INTO `credit_class` (`CREDIT_CLASS_CD`, `DESCRIPTION`) VALUES
('GOLD', 'Gold customers'),
('SILVER', 'Silver customers');

-- --------------------------------------------------------

--
-- Table structure for table `discharge_activity`
--

CREATE TABLE IF NOT EXISTS `discharge_activity` (
  `ADMISSION_REQ_NBR` int(11) NOT NULL,
  `ACTIVITY_TYPE_CD` varchar(30) NOT NULL,
  `CREATION_DTM` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `ORDER_STATUS_CD` varchar(30) NOT NULL,
  `CREATED_BY` varchar(20) default NULL,
  `REMARKS` varchar(512) default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`ADMISSION_REQ_NBR`,`ACTIVITY_TYPE_CD`,`CREATION_DTM`),
  KEY `DA_ADMISSION_NBR_FK1` (`ADMISSION_REQ_NBR`),
  KEY `DA_ACTIVITY_TYPE_CD_FK` (`ACTIVITY_TYPE_CD`),
  KEY `DA_ORDER_STATUS_CD_FK` (`ORDER_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `discharge_activity`
--


-- --------------------------------------------------------

--
-- Table structure for table `discharge_order`
--

CREATE TABLE IF NOT EXISTS `discharge_order` (
  `ADMISSION_REQ_NBR` int(11) NOT NULL,
  `DOCTOR_ORDER_NBR` int(11) NOT NULL,
  `ORDER_CREATION_DTM` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `ORDER_REQUESTED_BY` varchar(20) default NULL,
  `ORDER_STATUS_CD` varchar(20) default NULL,
  `DISCHARGE_TYPE_CD` varchar(30) NOT NULL,
  `APPROVED_BY` varchar(20) default NULL,
  `APPROVAL_TIME` timestamp NULL default NULL,
  `MODIFIED_BY` varchar(20) default NULL,
  `LAST_MODIFIED_DTM` timestamp NULL default NULL,
  `EXPECTED_DISCHARGE_DTM` datetime default NULL,
  `DISCHARGE_SUMMARY` longtext,
  `ACTUAL_DISCHARGE_DTM` datetime default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`ADMISSION_REQ_NBR`),
  KEY `DDO_DISCHARGE_TYPE_CD_FK` (`DISCHARGE_TYPE_CD`),
  KEY `DDO_ADMISSION_NBR_FK` (`ADMISSION_REQ_NBR`),
  KEY `DDO_DOCTOR_ORDER_NBR_FK` (`DOCTOR_ORDER_NBR`),
  KEY `DDO_ORDER_STATUS_CD_FK` (`ORDER_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `discharge_order`
--


-- --------------------------------------------------------

--
-- Table structure for table `discharge_type`
--

CREATE TABLE IF NOT EXISTS `discharge_type` (
  `DISCHARGE_TYPE_CD` varchar(30) NOT NULL,
  `DESCRIPTION` varchar(256) default NULL,
  `PROCEDURE` longtext,
  PRIMARY KEY  (`DISCHARGE_TYPE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `discharge_type` (`DISCHARGE_TYPE_CD`, `DESCRIPTION`, `PROCEDURE`) VALUES
('AMA', 'Against medical advice', 'The hospital must get the AMA form signed by the patient and his or her attendants'),
('EXPIRATION', 'Patient expired', NULL),
('HOME', 'Discharged to home', 'Patient must not need any service which may not be available at his or her home\r\n'),
('HOME_ASSISTANCE', 'Discharged to home with assistance', NULL),
('OTHER_FACILITY', 'Discharged to other facility', NULL);
-- --------------------------------------------------------

--
-- Table structure for table `doctor_order`
--

CREATE TABLE IF NOT EXISTS `doctor_order` (
  `DOCTOR_ORDER_NBR` int(11) NOT NULL auto_increment,
  `CREATION_SEQ_NBR` int(11) NOT NULL COMMENT 'This field indecates that a set of orders is created as part of single order creation event.So for orders wich are created using ''Order Group'' the value of this field remain same',
  `DOCTOR_ID` int(11) NOT NULL,
  `PATIENT_ID` int(11) default NULL,
  `ADMISSION_REQ_NBR` int(11) default NULL,
  `ORDER_TYPE_CD` varchar(20) NOT NULL,
  `ORDER_DESC` mediumtext,
  `ORDER_DICTATION` varchar(25) NOT NULL default 'SELF',
  `ORDER_TEMPLATE_ID` varchar(20) default NULL,
  `ORDER_GROUP_NAME` varchar(80) default NULL,
  `CREATED_BY` varchar(20) default NULL,
  `ORDER_CREATION_DTM` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `ORDER_STATUS_CD` varchar(20) NOT NULL,
  `MODIFIED_BY` varchar(20) default NULL,
  `LAST_MODIFIED_TM` timestamp NULL default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`DOCTOR_ORDER_NBR`),
  KEY `DOCTOR_ORDER_STATUS_CD_FK` (`ORDER_STATUS_CD`),
  KEY `DOCTOR_ORDER_TYPE_CD_FK` (`ORDER_TYPE_CD`),
  KEY `DOCTOR_ORDER_TEMPLATE_ID_FK` (`ORDER_TEMPLATE_ID`),
  KEY `DOCTOR_ORDER_GROUP_NAME_FK` (`ORDER_GROUP_NAME`),
  KEY `ADMISSION_REQ_NBR_FK` (`ADMISSION_REQ_NBR`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `doctor_order`
--
-- --------------------------------------------------------

--
-- Table structure for table `doctor_order_activity`
--

CREATE TABLE IF NOT EXISTS `doctor_order_activity` (
  `DOCTOR_ORDER_NUMBER` int(11) NOT NULL,
  `ACTIVITY_TYPE_CD` varchar(30) NOT NULL default '',
  `ACTIVITY_DTM` timestamp NOT NULL default '0000-00-00 00:00:00',
  `ORDER_STATUS_CD` varchar(30) default NULL,
  `CREATED_BY` varchar(20) default NULL,
  `REMARKS` varchar(512) default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`DOCTOR_ORDER_NUMBER`,`ACTIVITY_TYPE_CD`,`ACTIVITY_DTM`),
  KEY `DOA_DOCTOR_ORDER_NUMBER` (`DOCTOR_ORDER_NUMBER`),
  KEY `DOA_ACTIVITY_TYPE_CD_FK` (`ACTIVITY_TYPE_CD`),
  KEY `DOA_ORDER_STATUS_CD_FK` (`ORDER_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor_order_activity`
--

-- --------------------------------------------------------

--
-- Table structure for table `doctor_order_details`
--

CREATE TABLE IF NOT EXISTS `doctor_order_details` (
  `ORDER_NBR` int(11) NOT NULL,
  `SEQUENCE_NBR` int(11) NOT NULL,
  `SUB_SEQUENCE_NBR` int(11) NOT NULL,
  `RESPONSIBLE_DEPARTMENT_ID` varchar(20) default NULL,
  `SERVICE_ID` varchar(20) default NULL,
  `PACKAGE_ID` varchar(25) default NULL,
  `ACTION_DESC` varchar(512) default NULL,
  `ACTION_STATUS_CD` varchar(20) NOT NULL,
  `ACTION_REMARKS` varchar(256) default NULL,
  `ACTION_DTM` timestamp NULL default NULL,
  `ACTION_TAKEN_BY` varchar(20) default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`ORDER_NBR`,`SEQUENCE_NBR`,`SUB_SEQUENCE_NBR`),
  KEY `DOCTOR_ORDER_NBR_FK1` (`ORDER_NBR`),
  KEY `ACTION_STATUS_CD` (`ACTION_STATUS_CD`),
  KEY `SERVICE_ID` (`SERVICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor_order_details`
--


-- --------------------------------------------------------

--
-- Table structure for table `doctor_order_group`
--

CREATE TABLE IF NOT EXISTS `doctor_order_group` (
  `ORDER_GROUP_NAME` varchar(80) NOT NULL,
  `DESCRIPTION` varchar(512) default NULL,
  `GROUP_FOR_DOCTOR_ID` int(11) default NULL,
  `CREATION_DTM` timestamp NULL default NULL,
  `CREATED_BY` varchar(20) default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`ORDER_GROUP_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor_order_group`
--

-- --------------------------------------------------------

--
-- Table structure for table `doctor_order_group_has_template`
--

CREATE TABLE IF NOT EXISTS `doctor_order_group_has_template` (
  `ORDER_GROUP_NAME` varchar(80) NOT NULL,
  `ORDER_TEMPLATE_ID` varchar(30) NOT NULL,
  `SEQUENCE_NBR` int(11) NOT NULL,
  `EFFECTIVE_FROM_DT` date default NULL,
  `EFFECTIVE_TO_DT` date default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`ORDER_GROUP_NAME`,`ORDER_TEMPLATE_ID`,`SEQUENCE_NBR`),
  KEY `DOGHT_ORDER_GROUP_NAME_FK` (`ORDER_GROUP_NAME`),
  KEY `DOGHT_ORDER_TEMPLATE_ID` (`ORDER_TEMPLATE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor_order_group_has_template`
--
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

--
-- Dumping data for table `doctor_order_status`
--

INSERT INTO `doctor_order_status` (`ORDER_STATUS_CD`, `STATUS_DESC`, `CUSTOM_STATUS_DESC`, `PROCESS_NAME`, `VERSION`) VALUES
('APPROVED', 'Approved', NULL, 'GENERAL', 0),
('CANCELED', 'Canceled', NULL, 'GENERAL', 0),
('COMPLETED', 'Completed', NULL, 'GENERAL', 0),
('CREATED', 'Created', NULL, 'GENERAL', 0),
('DISAPPROVED', 'Disapproved', NULL, 'GENERAL', 0);
-- --------------------------------------------------------

--
-- Table structure for table `doctor_order_template`
--

CREATE TABLE IF NOT EXISTS `doctor_order_template` (
  `TEMPLATE_ID` varchar(30) NOT NULL,
  `TEMPLATE_DESC` varchar(256) NOT NULL,
  `ORDER_TYPE_CD` varchar(30) NOT NULL,
  `DOCTOR_ID` int(11) default NULL,
  `ACTIVE_FLAG` char(1) default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`TEMPLATE_ID`),
  KEY `TEMPLATE_ORDER_TYPE_CD` (`ORDER_TYPE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor_order_template`
--

-- --------------------------------------------------------

--
-- Table structure for table `doctor_order_template_details`
--

CREATE TABLE IF NOT EXISTS `doctor_order_template_details` (
  `TEMPLATE_ID` varchar(30) NOT NULL,
  `SEQUENCE_NBR` int(11) NOT NULL,
  `SUB_SEQUENCE_NBR` int(11) NOT NULL,
  `SERVICE_ID` varchar(20) default NULL,
  `PACKAGE_ID` varchar(25) default NULL,
  `RESPONSIBLE_DEPARTMENT_ID` varchar(20) default NULL,
  `ACTION_DESC` varchar(512) default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`TEMPLATE_ID`,`SEQUENCE_NBR`,`SUB_SEQUENCE_NBR`),
  KEY `DOTD_TEMPLATE_ID_FK` (`TEMPLATE_ID`),
  KEY `SERVICE_ID` (`SERVICE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor_order_template_details`
--
-- --------------------------------------------------------

--
-- Table structure for table `doctor_order_type`
--

CREATE TABLE IF NOT EXISTS `doctor_order_type` (
  `ORDER_TYPE_CD` varchar(30) NOT NULL,
  `ORDER_TYPE_DESC` varchar(256) default NULL,
  PRIMARY KEY  (`ORDER_TYPE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 PACK_KEYS=0;

--
-- Dumping data for table `doctor_order_type`
--

INSERT INTO `doctor_order_type` (`ORDER_TYPE_CD`, `ORDER_TYPE_DESC`) VALUES
('DISCHARGE', 'Discharge'),
('GENERAL', 'General'),
('IP_ADMISSION', 'Admission'),
('MEDICATION', 'Phramacy '),
('TESTS', 'Lab test'),
('TRANSFER', 'Transfer');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE IF NOT EXISTS `feedback` (
  `FEEDBACK_NUMBER` int(11) NOT NULL,
  `FEEDBACK_TYPE_CD` varchar(30) NOT NULL,
  `SEQUENCE_NBR` int(11) NOT NULL,
  `SUBSEQUENCE_NBR` int(11) NOT NULL,
  `CURRENT_DTM` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
  `FEEDBACK_BY` varchar(80) default NULL,
  `ENTITY_TYPE` varchar(20) default 'PATIENT',
  `FEEDBACK_VALUE` mediumtext NOT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`FEEDBACK_NUMBER`,`FEEDBACK_TYPE_CD`,`SEQUENCE_NBR`,`SUBSEQUENCE_NBR`),
  KEY `FEEDBAK_FEEDBACK_TYPE_CD_FK` (`FEEDBACK_TYPE_CD`,`SEQUENCE_NBR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `feedback`
--


-- --------------------------------------------------------

--
-- Table structure for table `feedback_type`
--

CREATE TABLE IF NOT EXISTS `feedback_type` (
  `FEEDBACK_TYPE_CD` varchar(30) NOT NULL,
  `FEEDBACK_SEQUENCE_NBR` int(11) NOT NULL,
  `DESCRIPTION` varchar(256) default NULL,
  `INVOLVED_PROCESS` varchar(256) default NULL,
  `FEED_BACK_VALUE_TYPE` varchar(30) default 'FREETEXT',
  `MAXIMUM_ALLOWED_SUBSEQUENCE` int(11) default NULL,
  PRIMARY KEY  (`FEEDBACK_TYPE_CD`,`FEEDBACK_SEQUENCE_NBR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `feedback_type`
--


-- --------------------------------------------------------

--
-- Table structure for table `insurance_plans`
--

CREATE TABLE IF NOT EXISTS `insurance_plans` (
  `PLAN_CD` varchar(30) NOT NULL,
  `INSURER_NAME` varchar(80) NOT NULL,
  `PLAN_NAME` varchar(80) default NULL,
  `VALID_FROM_DT` date NOT NULL,
  `VALID_TO_DT` date default NULL,
  `TOTAL_COVERAGE_AMT` double default NULL,
  `PERCENT_ABS_IND` varchar(1) default NULL,
  `CREATED_BY` varchar(35) NOT NULL,
  `CREATED_DTM` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `LAST_MODIFIED_DTM` timestamp NULL default NULL,
  `MODIFIED_BY` varchar(20) default NULL,
  `VERSION` int(11) default NULL,
  PRIMARY KEY  (`PLAN_CD`),
  KEY `INSURANCE_PLANS_INSURER_NAME_FK` (`INSURER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `insurance_plans`
--


-- --------------------------------------------------------

--
-- Table structure for table `insurer`
--

CREATE TABLE IF NOT EXISTS `insurer` (
  `INSURER_NAME` varchar(80) NOT NULL,
  `INSURER_DESC` varchar(512) default NULL,
  `CONTACT_CODE` int(11) NOT NULL,
  `CREATED_BY` varchar(35) NOT NULL,
  `CREATED_DTM` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `LAST_MODIFIED_BY` varchar(30) default NULL,
  `MODIFIED_DTM` timestamp NULL default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`INSURER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `insurer`
--


-- --------------------------------------------------------

--
-- Table structure for table `nursing_unit`
--

CREATE TABLE IF NOT EXISTS `nursing_unit` (
  `UNIT_NAME` varchar(45) NOT NULL,
  `UNIT_DESCRIPTION` varchar(256) default NULL,
  `UNIT_COORDINATOR_USER_ID` varchar(20) default NULL,
  `UNIT_TYPE_CD` varchar(30) default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`UNIT_NAME`),
  KEY `NU_UNIT_TYPE_CD_FK` (`UNIT_TYPE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nursing_unit`
--

INSERT INTO `nursing_unit` (`UNIT_NAME`, `UNIT_DESCRIPTION`, `UNIT_COORDINATOR_USER_ID`, `UNIT_TYPE_CD`, `VERSION`) VALUES
('NU1', 'General Unit1', NULL, 'GENERAL', 0),
('NU2', 'Emergency Unit1', NULL, 'EMERGENCY', 0);

-- --------------------------------------------------------

--
-- Table structure for table `nursing_unit_type`
--

CREATE TABLE IF NOT EXISTS `nursing_unit_type` (
  `UNIT_TYPE_CD` varchar(30) NOT NULL,
  `UNIT_TYPE_DESC` varchar(256) default NULL,
  `PARENT_UNIT_TYPE_CD` varchar(20) default NULL,
  `TOTAL_BED_COUNT` int(11) NOT NULL,
  `AVAILABLE_BED_COUNT` int(11) NOT NULL,
  `THRESHOLD_FOR_CONFIRMATION` int(11) default NULL,
  `THRESHOLD_FOR_WAITLIST` int(11) default NULL,
  `MODIFIED_BY` varchar(20) default NULL,
  `LAST_MODIFIED_DTM` timestamp NULL default NULL,
  `CREATE_DATE` date NOT NULL,
  PRIMARY KEY  (`UNIT_TYPE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nursing_unit_type`
--

INSERT INTO `nursing_unit_type` (`UNIT_TYPE_CD`, `UNIT_TYPE_DESC`, `PARENT_UNIT_TYPE_CD`, `TOTAL_BED_COUNT`, `AVAILABLE_BED_COUNT`, `THRESHOLD_FOR_CONFIRMATION`, `THRESHOLD_FOR_WAITLIST`, `MODIFIED_BY`, `LAST_MODIFIED_DTM`, `CREATE_DATE`) VALUES
('EMERGENCY', 'Emergency Unit', 'GENERAL', 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11'),
('GENERAL', 'General', NULL, -2, -2, NULL, NULL, NULL, '2009-11-12 05:15:43', '2009-08-11'),
('ICU', 'Intensive Care Units', NULL, 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:53', '2009-08-11'),
('OUTPATIENT', 'Outpatients Unit', 'GENERAL', 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11'),
('OBSTETRICS_GYNAECOLOGY_WARD', 'Obstetrics & Gynaecology ward', NULL, 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11'),
('LIVER_DIGESTIVE', 'Liver & Digestive', NULL, 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11'),
('GASTROSURGERY', 'Gastrosurgery', NULL, 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11'),
('GENERAL_SURGERY_WARD', 'General surgery ward', NULL, 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11'),
('DENTISTRY', 'Dentistry', NULL, 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11'),
('PULMONOLOGY', 'Pulmonology', NULL, 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11'),
('THORIAC_SURGERY', 'Thoriac surgery', NULL, 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11'),
('NEPHROLOGY_RENAL', 'Nephrology & Renal', NULL, 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11'),
('NEUROLOGY_NEUROSURGERY', 'Neurology & Neurosurgery', NULL, 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11'),
('HEMATOLOGY', 'Hematology', NULL, 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11');
-- --------------------------------------------------------

--
-- Table structure for table `order_attribute_value`
--

CREATE TABLE IF NOT EXISTS `order_attribute_value` (
  `ORDER_NBR` int(11) NOT NULL,
  `ATTRIBUTE_NAME` varchar(35) NOT NULL,
  `ATTRIBUTE_VALUE` longtext,
  `LAST_MODIFIED_DTM` timestamp NULL default NULL on update CURRENT_TIMESTAMP,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`ORDER_NBR`,`ATTRIBUTE_NAME`),
  KEY `ORDER_ATTRIBUTE_VALUE_ATTRIBUTE_NAME_FK` (`ATTRIBUTE_NAME`),
  KEY `ORDER_ATTRIBUTE_VALUE_ORDER_NBR_KF` (`ORDER_NBR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order_attribute_value`
--


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

--
-- Dumping data for table `order_type_has_attributes`
--

INSERT INTO `order_type_has_attributes` (`ORDER_TYPE_CD`, `ATTRIBUTE_NAME`, `IS_MANDATORY`, `SEQUENCE_NUMBER`, `VERSION`) VALUES
('DISCHARGE', 'dischargeSummary', 'N', 3, 0),
('DISCHARGE', 'dischargeType', 'Y', 1, 0),
('DISCHARGE', 'expectedDischargeDate', 'N', 2, 0),
('IP_ADMISSION', 'agenda', 'N', 7, 0),
('IP_ADMISSION', 'entryPoint', 'N', 5, 0),
('IP_ADMISSION', 'entryPointReference', 'N', 6, 0),
('IP_ADMISSION', 'estimatedCost', 'N', 3, 0),
('IP_ADMISSION', 'expectedAdmissionDate', 'Y', 1, 0),
('IP_ADMISSION', 'expectedDischargeDate', 'N', 2, 0),
('IP_ADMISSION', 'reasonForAdmission', 'N', 9, 0),
('IP_ADMISSION', 'unitType', 'Y', 4, 0);


-- --------------------------------------------------------

--
-- Table structure for table `plan_covers_disease`
--

CREATE TABLE IF NOT EXISTS `plan_covers_disease` (
  `PLAN_CD` varchar(30) NOT NULL,
  `DISEASE_NAME` varchar(25) NOT NULL,
  `IS_COVERD` varchar(1) NOT NULL,
  `PERCENT_ABS_IND` char(1) default NULL,
  `COVERAGE_AMT` double default NULL,
  `CREATED_DTM` timestamp NULL default NULL,
  `CREATED_BY` varchar(35) NOT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`PLAN_CD`,`DISEASE_NAME`),
  KEY `PLAN_DISEASE_PLAN_CD_FK` (`PLAN_CD`),
  KEY `PLAN_DISEASE_DISEASE_NAME_FK` (`DISEASE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `plan_covers_disease`
--


-- --------------------------------------------------------

--
-- Table structure for table `plan_has_services`
--

CREATE TABLE IF NOT EXISTS `plan_has_services` (
  `PLAN_CD` varchar(30) NOT NULL,
  `SERVICE_CODE` varchar(15) NOT NULL,
  `IS_COVERD` char(1) NOT NULL,
  `PERCENT_ABS_IND` varchar(1) default NULL,
  `COVERAGE_AMT` double default NULL,
  `CREATED_BY` varchar(35) NOT NULL,
  `CREATED_DTM` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`PLAN_CD`,`SERVICE_CODE`),
  KEY `PLAN_HAS_SERVICE_PLAN_CD_FK` (`PLAN_CD`),
  KEY `PLAN_HAS_SERVICE_SERVICE_CODE_FK` (`SERVICE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `plan_has_services`
--


-- --------------------------------------------------------

--
-- Table structure for table `reservation_status`
--

CREATE TABLE IF NOT EXISTS `reservation_status` (
  `RESERVATION_STATUS_CD` varchar(20) NOT NULL,
  `DESCRIPTION` varchar(256) default NULL,
  `ACTIVE_FLAG` char(1) default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`RESERVATION_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `reservation_status`
--

INSERT INTO `reservation_status` (`RESERVATION_STATUS_CD`, `DESCRIPTION`, `ACTIVE_FLAG`, `VERSION`) VALUES
('ASSIGNED', 'Assigned', 'Y', 0),
('CANCELED', 'Canceled', 'Y', 0),
('CONFIRMED', 'Booking confirmed', 'Y', 0),
('CREATED', 'Reservation is Created', 'Y', 0),
('WAITLIST', 'Waiting List', 'Y', 0);

-- --------------------------------------------------------



--
-- Table structure for table `sponsor_claim_status`
--

CREATE TABLE IF NOT EXISTS `sponsor_claim_status` (
  `CLAIM_STATUS_CD` varchar(20) NOT NULL,
  `CLAIM_STATUS_DESC` varchar(256) default NULL,
  `ACTIVE_FLAG` char(1) default NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`CLAIM_STATUS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sponsor_claim_status`
--

INSERT INTO `sponsor_claim_status` (`CLAIM_STATUS_CD`, `CLAIM_STATUS_DESC`, `ACTIVE_FLAG`, `VERSION`) VALUES
('APPROVED', 'Approved', 'Y', 0),
('CREATED', 'Created', 'Y', 0),
('MOREINFO', 'More Information required', 'Y', 0),
('PARTAPPROVED', 'Partially approved', 'Y', 0),
('REJECTED', 'Rejected', 'Y', 0),
('RESUBMITTED', 'Re-submitted', 'Y', 0),
('SUBMITTED', 'Submitted', 'Y', 0);

-- --------------------------------------------------------

--
-- Table structure for table `sponsor_insurer_association`
--

CREATE TABLE IF NOT EXISTS `sponsor_insurer_association` (
  `SPONSOR_NAME` varchar(80) NOT NULL,
  `INSURER_NAME` varchar(80) NOT NULL,
  `EFFECTIVE_FROM_DT` date default NULL,
  `EFFECTIVE_TO_DT` date default NULL,
  `CREATED_BY` varchar(35) NOT NULL,
  `CREATED_DTM` timestamp NOT NULL default CURRENT_TIMESTAMP,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY  (`SPONSOR_NAME`,`INSURER_NAME`),
  KEY `SP_INS_ASSO_SPONSOR_NAME_FK` (`SPONSOR_NAME`),
  KEY `SP_INS_ASSO_INSURER_NAME_FK` (`INSURER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sponsor_insurer_association`
--


-- --------------------------------------------------------

--
-- Table structure for table `sponsor_type`
--

CREATE TABLE IF NOT EXISTS `sponsor_type` (
  `SPONSOR_TYPE_CD` varchar(30) NOT NULL,
  `SPONSOR_DESC` varchar(256) default NULL,
  PRIMARY KEY  (`SPONSOR_TYPE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sponsor_type`
--

INSERT INTO `sponsor_type` (`SPONSOR_TYPE_CD`, `SPONSOR_DESC`) VALUES
('CORPORATE', 'Employer'),
('TPA', 'Third party administrator'),
('SELF', 'Self');


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
  ADD CONSTRAINT `bed_reservation_activity_ibfk_3` FOREIGN KEY (`RESERVATION_STATUS_CD`) REFERENCES `reservation_status` (`RESERVATION_STATUS_CD`),
  ADD CONSTRAINT `bed_reservation_activity_ibfk_1` FOREIGN KEY (`BED_RESERVATION_NBR`) REFERENCES `bed_reservation` (`BED_RESERVATION_NBR`),
  ADD CONSTRAINT `bed_reservation_activity_ibfk_2` FOREIGN KEY (`ACTIVITY_TYPE_CD`) REFERENCES `activity_type` (`ACTIVITY_TYPE_CD`);

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
-- Constraints for table `doctor_order`
--
ALTER TABLE `doctor_order`
  ADD CONSTRAINT `doctor_order_ibfk_2` FOREIGN KEY (`ADMISSION_REQ_NBR`) REFERENCES `admission` (`ADMISSION_REQ_NBR`),
  ADD CONSTRAINT `doctor_order_ibfk_1` FOREIGN KEY (`ORDER_GROUP_NAME`) REFERENCES `doctor_order_group` (`ORDER_GROUP_NAME`),
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
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `FEEDBAK_FEEDBACK_TYPE_CD_FK` FOREIGN KEY (`FEEDBACK_TYPE_CD`, `SEQUENCE_NBR`) REFERENCES `feedback_type` (`FEEDBACK_TYPE_CD`, `FEEDBACK_SEQUENCE_NBR`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `insurance_plans`
--
ALTER TABLE `insurance_plans`
  ADD CONSTRAINT `insurance_plans_ibfk_1` FOREIGN KEY (`INSURER_NAME`) REFERENCES `insurer` (`INSURER_NAME`);

--
-- Constraints for table `nursing_unit`
--
ALTER TABLE `nursing_unit`
  ADD CONSTRAINT `NU_UNIT_TYPE_CD_FK` FOREIGN KEY (`UNIT_TYPE_CD`) REFERENCES `nursing_unit_type` (`UNIT_TYPE_CD`) ON DELETE NO ACTION ON UPDATE NO ACTION;

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
-- Constraints for table `plan_has_services`
--
ALTER TABLE `plan_has_services`
  ADD CONSTRAINT `plan_has_services_ibfk_1` FOREIGN KEY (`PLAN_CD`) REFERENCES `insurance_plans` (`PLAN_CD`);

--
-- Constraints for table `sponsor_insurer_association`
--
ALTER TABLE `sponsor_insurer_association`
  ADD CONSTRAINT `sponsor_insurer_association_ibfk_1` FOREIGN KEY (`SPONSOR_NAME`) REFERENCES `claim_sponsor` (`SPONSORS_NAME`),
  ADD CONSTRAINT `sponsor_insurer_association_ibfk_2` FOREIGN KEY (`INSURER_NAME`) REFERENCES `insurer` (`INSURER_NAME`);
