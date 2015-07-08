-- phpMyAdmin SQL Dump
-- version 2.11.9.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 16, 2009 at 06:31 AM
-- Server version: 5.0.45
-- PHP Version: 5.2.6

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";

--
-- Database: `mcx`
--

--
-- Dumping data for table `action_status`
--

INSERT INTO `action_status` (`ACTION_STATUS_CD`, `ACTION_STATUS_DESC`, `CUSTOM_DESC`, `ACTIVE_FLAG`) VALUES
('ASSIGNED', 'Action is assigned to the actor', NULL, 'Y'),
('CREATED', 'Action is just created', NULL, 'Y')
('COMPLETED', 'Action is complete', NULL, 'Y'),
('HOLD', 'Action has been put on hold', NULL, 'Y'),
('INPROGRESS', 'Action is being performed by the user', NULL, 'Y'),
('REJECTED', 'Action was rejected by the actor', NULL, 'Y');

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
('CARRY_TO_DISCHARGE', 'Carry patient to discharge area', 'DISCHARGE'),
('CLM_REQ_APPROVED', 'Insurer has approved complete amount requested by the hospital', 'MEDICLAIM'),
('BED_AVAILABLE','Bed is available', 'BED_ACTIVITY'),
('BED_OCCUPIED','Bed is occupied', 'BED_ACTIVITY'),
('BED_ADDED','Bed is added', 'BED_ACTIVITY'),
('BED_RETIRED','Bed is retired', 'BED_ACTIVITY'),
('BED_UNIT_CHANGED','Bed has been moved to another unit', 'BED_ACTIVITY'),
('CLM_REQ_CREATED', 'Claim request has been created', 'MEDICLAIM'),
('CLM_REQ_MOREINFO', 'Insurer has requested for more information from the hospital', 'MEDICLAIM'),
('CLM_REQ_PARTAPPROVED', 'Insurer has partially approved the requested amount', 'MEDICLAIM'),
('CLM_REQ_REJECTED', 'Insurer has rejected the requested insurance amount', 'MEDICLAIM'),
('CLM_REQ_RESUBMITTED', 'The hospital has sent the requested information to the insurer and asked for approval again', 'MEDICLAIM'),
('CLM_REQ_SUBMITTED', 'Claim request has been submitted to the insurer', 'MEDICLAIM'),
('ORDER_APPROVED ', 'order approved', 'ORDER'),
('ORDER_COMPLETED', 'Order completed', 'ORDER'),
('ORDER_CREATED', 'Order is just created', 'ORDER'),
('ORDER_DISAPPROVED', 'Ordeer disapproved', 'ORDER'),
('PHARMACY_CREDIT_DISCHARGE', 'Issue credit notes to pharmacy for access medicines', 'DISCHARGE'),
('REGISTRATION', 'Patient Registartion', 'GENERAL'),
('RESCHDULE_APPOINTMENTS', 'Reschedule an existing appointment', 'APPOINTMENTS'),
('RESCHEDULED', 'Rescheduleed', NULL),
('BED_RES_ASSIGNED','Bed is assigned', 'BED_RES_ACTIVITY'),
('BED_RES_BOOKED','Bed is booked, 'BED_ARES_CTIVITY'),
('BED_RES_CONFIRMED','Confirmed', 'BED_RES_ACTIVITY'),
('BED_RES_CANCELED','Canceled', 'BED_RES_ACTIVITY'),
('BED_RES_WAITLIST','Waitlist', 'BED_RES_ACTIVITY');


INSERT INTO `admission_status` (`ADMISSION_STATUS_CD`, `ADMISSION_STATUS_DESC`, `ACTIVE_FLAG`) VALUES
('ADMITTED', 'Patient has been admitted in the hospital', 1),
('APPROVED', 'Admission Request has been approved by the authorized doctor', 1),
('BED_ASSIGNED', 'The admitted patient was assigned a bed', 1),
('CANCELLED', 'Admission request has been cancelled', 1),
('DISAPPROVED', 'Admission Request has been disapproved by the authorized doctor', 1),
('DISCHARED', 'Patient has been discharged from the hospital', 1),
('EMERGENCY_REQUEST', 'The admission request is for urgent case', 1),
('PRE_DISCHARGE', 'The patient is going to be discharged ', 1),
('REQUESTED', 'Requested For admission', 1),
('RESCHEDULED', 'Admission request has been rescheduled', 1);



--
-- Dumping data for table `bed_status`
--

INSERT INTO `bed_status` (`BED_STATUS_CD`, `DESCRIPTION`, `ACTIVE_FLAG`) VALUES
('AVAILABLE', 'Bed is available for occupancy', 1),
('CLEANING', 'Bed is being cleaned for further occupancy', 1),
('DEFECTIVE', 'Bed is not suitable for patient and needs to fixed', 1),
('OCCUPIED', 'Bed is occupied by a patient', 1),
('PENDING_CLEANING', 'Bed needs to be cleaned up', 1);

--
-- Dumping data for table `bed_type`
--

INSERT INTO `bed_type` (`BED_TYPE_CD`, `BED_TYPE_DESC`, `ACTIVE_FLAG`) VALUES
('DOUBLE', 'Double occupany bed', 1),
('FEMALE_WARD', 'A bed in the female ward', 1),
('GENERAL_WARD', 'A bed in the general ward', 1),
('OT', 'Beds for the operation theater', 1),
('POST_OP', 'Bed for recovery of patient after surgerry', 1),
('PRE_OP', 'Bed for preparing the patient for surgery', 1),
('SINGLE', 'Single occupancy bed', 1);

--
-- Dumping data for table `credit_class`
--

INSERT INTO `credit_class` (`CREDIT_CLASS_CD`, `DESCRIPTION`) VALUES
('GOLD', 'Gold customers'),
('SILVER', 'Silver customers');

--
-- Dumping data for table `discharge_type`
--

INSERT INTO `discharge_type` (`DISCHARGE_TYPE_CD`, `DESCRIPTION`, `PROCEDURE`) VALUES
('AMA', 'Patient is discharged against medical advice', 'The hospital must get the AMA form signed by the patient and his or her attendants'),
('EXPIRATION', 'Patient expired', NULL),
('HOME', 'Patient will be discharged to home', 'Patient must not need any service which may not be available at his or her home\r\n'),
('HOME_ASSISTANCE', 'Patient is discharged to stay at home with some assistance', NULL),
('OTHER_FACILITY', 'Patient is being discharged to some other facility', NULL);

--
-- Dumping data for table `doctor_order_status`
--

INSERT INTO `doctor_order_status` (`ORDER_STATUS_CD`, `STATUS_DESC`, `CUSTOM_STATUS_DESC`, `PROCESS_NAME`) VALUES
('APPROVED', 'Order has been approved by an authorized doctor', NULL, 'GENERAL'),
('COMPLETED', 'Order has been processed', NULL, 'GENERAL'),
('CREATED', 'Order has been created by a doctor', NULL, 'GENERAL'),
('DISAPPROVED', 'Order has been disapproved by the authorized doctor', NULL, 'GENERAL');

--
-- Dumping data for table `doctor_order_type`
--

INSERT INTO `doctor_order_type` (`ORDER_TYPE_CD`, `ORDER_TYPE_DESC`) VALUES
('ADMISSION', 'Doctor order for admission'),
('DISCHARGE', 'Doctor order for discharge'),
('MEDICATION', 'Doctor order to phramacy '),
('TESTS', 'Doctor order for tests');

--
-- Dumping data for table `feedback_type`
--


--
-- Dumping data for table `nursing_unit_type`
--

INSERT INTO `nursing_unit_type` (`UNIT_TYPE_CD`, `UNIT_TYPE_DESC`, `PARENT_UNIT_TYPE_CD`) VALUES
('EMERGENCY', 'Nursing units for emergency department', 'GENERAL'),
('GENERAL', 'General Nursing Unit', NULL),
('ICU', 'Nursing units for intensive care units', NULL),
('OUTPATIENT', 'Nursing units for outpatients', 'GENERAL');

--
-- Dumping data for table `reservation_status`
--

INSERT INTO `reservation_status` (`RESERVATION_STATUS_CD`, `DESCRIPTION`, `ACTIVE_FLAG`, `VERSION`) VALUES
('ASSIGNED', 'Assigned', 'Y', 0),
('BOOKED', 'Booked', 'Y', 0),
('CONFIRMED', 'Booking confirmed', 'Y', 0),
('CREATED', 'Reservation is Created', 'Y', 0),
('WAITLIST', 'Waiting List', 'Y', 0);


--
-- Dumping data for table `sponsor_claim_status`
--

INSERT INTO `sponsor_claim_status` (`CLAIM_STATUS_CD`, `REQUEST_STATUS_DESC`, `ACTIVE_FLAG`) VALUES
('ACKNOWLEDGED', 'TPA send acknowledgement', 1),
('REQUESTED', 'Claim request sent to the TPA', 1);

--
-- Dumping data for table `sponsor_type`
--

INSERT INTO `sponsor_type` (`SPONSOR_TYPE_CD`, `SPONSOR_DESC`) VALUES
('CORPORATE', 'Employer of the patient'),
('TPA', 'Third party administrator'),
('SELF', 'Self');

--
-- bed_special_feature
--

INSERT INTO `bed_special_feature` (`FEATURE_NAME`, `DESCRIPTION`, `LOCATION_WRT_BED_IND`, `EFFECTIVE_FROM_DTM`, `EFFECTIVE_TO_DTM`) VALUES ('Bed Safety Rails', 'Bed Safety Rails', NULL, NOW(), NOW()), ('Trapeze Floor Stand', 'Trapeze Floor Stand', NULL, NOW(), NOW()), ('Over Bed Table', 'Over Bed Table', NULL, NOW(), NOW()), ('Baby Cradle', 'Baby Cradle', NULL, NOW(), NOW()), ('Bed side Screen', 'Bed side Screen', NULL, NOW(), NOW()), ('Bed side Locker', 'Bed side Locker', NULL, NOW(), NOW()), ('Aerosol Disinfector', 'Aerosol Disinfector', NULL, NOW(), NOW()), ('Over Bed Trolley', 'Over Bed Trolley', NULL, NOW(), NOW()), ('Wheel Chair', 'Wheel Chair', NULL, NOW(), NOW()), ('Fridge', 'Fridge', NULL, NOW(), NOW());

INSERT INTO `bed_special_feature` (`FEATURE_NAME`, `DESCRIPTION`, `LOCATION_WRT_BED_IND`, `EFFECTIVE_FROM_DTM`, `EFFECTIVE_TO_DTM`) VALUES ('Television', 'Television', NULL, NOW(), NOW()), ('Internet connectivity', 'Internet connectivity', NULL, NOW(), NOW());

INSERT INTO `admission_entry_point` (`ENTRY_POINT_NAME`, `ENTRY_POINT_DESC`) values ('EMERGENCY', 'Emergency');
INSERT INTO `admission_entry_point` (`ENTRY_POINT_NAME`, `ENTRY_POINT_DESC`) values ('OPD', 'Out Patient');
