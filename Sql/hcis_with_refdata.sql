-- phpMyAdmin SQL Dump
-- version 3.2.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jul 20, 2010 at 03:15 PM
-- Server version: 5.1.41
-- PHP Version: 5.2.13

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

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
  `CREATE_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `CLAIM_STATUS_CD` varchar(20) NOT NULL,
  `CREATED_BY` varchar(20) DEFAULT NULL,
  `REMARKS` varchar(256) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`REQUEST_SEQUENCE_NBR`,`CREATE_DTM`,`ACTIVITY_TYPE_CD`),
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `admission_claim_request`
--


-- --------------------------------------------------------

--
-- Table structure for table `admission_entry_point`
--

CREATE TABLE IF NOT EXISTS `admission_entry_point` (
  `ENTRY_POINT_NAME` varchar(25) NOT NULL,
  `ENTRY_POINT_DESC` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`ENTRY_POINT_NAME`)
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
  `ADMISSION_STATUS_DESC` varchar(256) DEFAULT NULL,
  `ACTIVE_FLAG` char(1) DEFAULT NULL,
  PRIMARY KEY (`ADMISSION_STATUS_CD`)
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
-- Table structure for table `allergies`
--

CREATE TABLE IF NOT EXISTS `allergies` (
  `ALLERGIES_CODE` int(5) NOT NULL AUTO_INCREMENT,
  `ALLERGRY_DESCRIPTION` varchar(75) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`ALLERGIES_CODE`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=2983 ;

--
-- Dumping data for table `allergies`
--

INSERT INTO `allergies` (`ALLERGIES_CODE`, `ALLERGRY_DESCRIPTION`, `ACTIVE`) VALUES
(1, 'A& D Emollient', 1),
(2, 'A& D Zinc Oxide Cream', 1),
(3, 'A-Fil', 1),
(4, 'A-MANTLE', 1),
(5, 'A.E.R Traveler', 1),
(6, 'A.E.R. Witch Hazel', 1),
(7, 'A.P.L.', 1),
(8, 'A/G Pro', 1),
(9, 'Abacavir', 1),
(10, 'Abarelix', 1),
(11, 'Abatacept', 1),
(12, 'Abbokinase', 1),
(13, 'Abciximab', 1),
(14, 'ABDEK', 1),
(15, 'Abelcet', 1),
(16, 'Abilify', 1),
(17, 'Abilify Discmelt', 1),
(18, 'Abraxane', 1),
(19, 'Abreva', 1),
(20, 'Absorbine Jr.', 1),
(21, 'Absorbine Jr. Extra Strength', 1),
(22, 'Acacia', 1),
(23, 'Acamprosate', 1),
(24, 'Acamprosate And Derivatives', 1),
(25, 'Acarbose', 1),
(26, 'Accolate', 1),
(27, 'AccuHist', 1),
(28, 'Accuhist DM', 1),
(29, 'Accuhist DM Pediatric', 1),
(30, 'Accuhist LA', 1),
(31, 'AccuHist PDX', 1),
(32, 'Accuhist Pediatric', 1),
(33, 'AccuNeb', 1),
(34, 'Accupril', 1),
(35, 'Accuretic', 1),
(36, 'Accutane', 1),
(37, 'AccuTuss DM', 1),
(38, 'ACCUZYME', 1),
(39, 'Accuzyme SE', 1),
(40, 'ACD-A', 1),
(41, 'Ace Inhibitors', 1),
(42, 'Ace+Z', 1),
(43, 'Acebutolol', 1),
(44, 'Acecarbromal', 1),
(45, 'Aceclidine', 1),
(46, 'Aceclofenac', 1),
(47, 'Acefylline', 1),
(48, 'Acefylline Piperazine', 1),
(49, 'Acemannan', 1),
(50, 'Acemetacin', 1),
(51, 'Acenocoumarol', 1),
(52, 'Aceon', 1),
(53, 'Acepromazine', 1),
(54, 'Aceprometazine', 1),
(55, 'Acerola', 1),
(56, 'Acerola (Malpighia Glabra)', 1),
(57, 'Acesulfame', 1),
(58, 'Acetadote', 1),
(59, 'Acetamide MEA', 1),
(60, 'Acetaminophen', 1),
(61, 'Acetanilide', 1),
(62, 'Acetarsol', 1),
(63, 'Acetazolamide', 1),
(64, 'Acetiamine', 1),
(65, 'Acetic Acid', 1),
(66, 'Acetohexamide', 1),
(67, 'Acetohydroxamic Acid', 1),
(68, 'Acetomenaphthone', 1),
(69, 'Acetone', 1),
(70, 'Acetophenazine', 1),
(71, 'Acetoxolone', 1),
(72, 'Acetoxolone Aluminum', 1),
(73, 'Acetrizoic Acid', 1),
(74, 'Acetyl-D-Glucosamine', 1),
(75, 'Acetylated Monoglceride', 1),
(76, 'Acetylcholine', 1),
(77, 'Acetylcysteine', 1),
(78, 'Acetyldigitoxin', 1),
(79, 'Acetyldigoxin', 1),
(80, 'Acetylleucine', 1),
(81, 'Acetylmethionine', 1),
(82, 'Acetyltributyl Citrate', 1),
(83, 'Acetyltryptophanate', 1),
(84, 'Acexamic Acid', 1),
(85, 'Achromycin', 1),
(86, 'Achromycin V', 1),
(87, 'Acid Green 50', 1),
(88, 'Acid Mantle', 1),
(89, 'Acidoll', 1),
(90, 'Acigest II', 1),
(91, 'Aciphex', 1),
(92, 'Acipimox', 1),
(93, 'Acitazanolast', 1),
(94, 'Acitretin', 1),
(95, 'Aclaro', 1),
(96, 'Aclaro PD', 1),
(97, 'Aclarubicin', 1),
(98, 'Aclovate', 1),
(99, 'Acne-Aid', 1),
(100, 'Aconite', 1),
(101, 'Aconitum', 1),
(102, 'Acridine Analogues', 1),
(103, 'Acriflavine', 1),
(104, 'Acrisorcin', 1),
(105, 'Acrivastine', 1),
(106, 'Acrylate Copolymers', 1),
(107, 'Acrylic Acid', 1),
(108, 'ACT Fluoride', 1),
(109, 'ACT for Kids', 1),
(110, 'Acta-Char', 1),
(111, 'Actamin Maximum Strength', 1),
(112, 'Acthar H.P.', 1),
(113, 'ActHIB', 1),
(114, 'Acthrel', 1),
(115, 'Actical', 1),
(116, 'Actidose/Sorbitol', 1),
(117, 'Actifed', 1),
(118, 'Actifed Allergy Daytime/Night', 1),
(119, 'Actifed Cold and Sinus', 1),
(120, 'Actifed Cold-Allergy', 1),
(121, 'Actifed Plus', 1),
(122, 'Actifed Sinus', 1),
(123, 'Actifed Sinus Daytime/Night', 1),
(124, 'Actifed With Codeine', 1),
(125, 'Actigall', 1),
(126, 'Actimmune', 1),
(127, 'Actinoquinol', 1),
(128, 'Actiq', 1),
(129, 'Actisite', 1),
(130, 'Activase', 1),
(131, 'Activated Charcoal', 1),
(132, 'Activella', 1),
(133, 'Actonel', 1),
(134, 'Actonel With Calcium', 1),
(135, 'actoplus met', 1),
(136, 'Actos', 1),
(137, 'Acuflex', 1),
(138, 'Acular', 1),
(139, 'Acular LS', 1),
(140, 'Acular PF', 1),
(141, 'Acunol', 1),
(142, 'Acuprin', 1),
(143, 'Acyclovir', 1),
(144, 'Acyclovir Analogues', 1),
(145, 'Acys-5', 1),
(146, 'Adacel', 1),
(147, 'Adagen', 1),
(148, 'Adalat', 1),
(149, 'Adalat CC', 1),
(150, 'Adalimumab', 1),
(151, 'Adapalene', 1),
(152, 'Adderall', 1),
(153, 'Adderall XR', 1),
(154, 'Ade', 1),
(155, 'Adeflor M', 1),
(156, 'Adefovir', 1),
(157, 'ADEKS', 1),
(158, 'Adeks Pediatric', 1),
(159, 'Ademetionine', 1),
(160, 'Adenine', 1),
(161, 'Adenocard', 1),
(162, 'Adenoscan', 1),
(163, 'Adenosine', 1),
(164, 'Adenosine Analogues', 1),
(165, 'Adexol', 1),
(166, 'Adexol-DM', 1),
(167, 'Adhesive', 1),
(168, 'Adhesive Bandage', 1),
(169, 'Adhesive Foot Cushion', 1),
(170, 'Adhesive Remover', 1),
(171, 'Adhesive Tape', 1),
(172, 'Adhesive, Silicone', 1),
(173, 'Adiphenine', 1),
(174, 'Adipic Acid', 1),
(175, 'Adonitol', 1),
(176, 'ADOXA', 1),
(177, 'ADOXA Pak', 1),
(178, 'Adrafinil', 1),
(179, 'Adrenal', 1),
(180, 'Adrenal Cortex (Porcine)', 1),
(181, 'Adrenalin', 1),
(182, 'Adrenalone', 1),
(183, 'Adriamycin', 1),
(184, 'Adriamycin RDF', 1),
(185, 'Adsorbocarpine', 1),
(186, 'Adult Low Dose Aspirin', 1),
(187, 'Advair Diskus', 1),
(188, 'ADVAIR HFA', 1),
(189, 'ADVANCED Calcium', 1),
(190, 'ADVANCED Ester C', 1),
(191, 'ADVANCED High Gamma E', 1),
(192, 'Advantage 24 Bioad Contracept', 1),
(193, 'ADVATE', 1),
(194, 'Advicor', 1),
(195, 'Advil Allergy Sinus', 1),
(196, 'Advil Cold& Sinus', 1),
(197, 'Advil Flu& Body Ache', 1),
(198, 'Advil Liqui-Gel', 1),
(199, 'Advil Migraine', 1),
(200, 'Advil Multi-Symptom Cold', 1),
(201, 'Aerobid', 1),
(202, 'Aerobid-M', 1),
(203, 'Aerohist', 1),
(204, 'aeroKid', 1),
(205, 'Aerolate 111', 1),
(206, 'Aerolate III TD', 1),
(207, 'AeroTuss 12', 1),
(208, 'Aescin', 1),
(209, 'Aescin Sodium Polysulfate', 1),
(210, 'Aflexa', 1),
(211, 'Afloqualone', 1),
(212, 'Afrin', 1),
(213, 'Afrin Childrens', 1),
(214, 'Afrin Extra Moisturizing', 1),
(215, 'Afrin Menthol Spray', 1),
(216, 'Afrin Nasal Spray', 1),
(217, 'Afrin Sinus', 1),
(218, 'Aftate', 1),
(219, 'Aftate Athletes Foot', 1),
(220, 'Aftate Jock Itch', 1),
(221, 'Agalsidase Alpha', 1),
(222, 'Agalsidase Beta', 1),
(223, 'Agar', 1),
(224, 'Age Spot/Skin Lightning', 1),
(225, 'Agenerase', 1),
(226, 'Aggrastat', 1),
(227, 'Aggrenox', 1),
(228, 'Agoral', 1),
(229, 'Agrimony', 1),
(230, 'Agrimony (Agrimonia Eupatoria)', 1),
(231, 'Agrylin', 1),
(232, 'AH-Chew', 1),
(233, 'AH-Chew D', 1),
(234, 'AH-Chew II', 1),
(235, 'AHIST', 1),
(236, 'Airshield', 1),
(237, 'Ajmaline', 1),
(238, 'Akineton', 1),
(239, 'Akne-Mycin', 1),
(240, 'Akoline CB W/Zinc', 1),
(241, 'Ala-Seb', 1),
(242, 'Alacepril', 1),
(243, 'Alacol', 1),
(244, 'Alacol DM', 1),
(245, 'Alamag', 1),
(246, 'Alamast', 1),
(247, 'Alanine', 1),
(248, 'Alantolactone', 1),
(249, 'Alanylglutamine', 1),
(250, 'Alba-3', 1),
(251, 'Alba-3 HC', 1),
(252, 'Alba-Lybe NR', 1),
(253, 'Alba-Temp 300', 1),
(254, 'ALBAFORT', 1),
(255, 'Albafort Pediatric', 1),
(256, 'Albafort S', 1),
(257, 'Albalybe', 1),
(258, 'Albamycin', 1),
(259, 'Albapec CF', 1),
(260, 'Albatussin CF', 1),
(261, 'Albatussin CF Pediatric', 1),
(262, 'Albatussin DM', 1),
(263, 'Albatussin DM Pediatric', 1),
(264, 'Albatussin EX', 1),
(265, 'Albatussin EX Pediatric', 1),
(266, 'Albatussin LA', 1),
(267, 'Albatussin NN', 1),
(268, 'Albatussin PE', 1),
(269, 'Albatussin PE Pediatric', 1),
(270, 'Albatussin Pediatric', 1),
(271, 'Albatussin Pediatric Drops', 1),
(272, 'Albatussin Pediatric NR', 1),
(273, 'Albatussin SR', 1),
(274, 'Albatussin SR F', 1),
(275, 'Albatussin SR Senior', 1),
(276, 'Albay Honey Bee Venom', 1),
(277, 'Albay Mixed Vespid Venom', 1),
(278, 'Albay White Faced Hornet Venom', 1),
(279, 'Albendazole', 1),
(280, 'Albenza', 1),
(281, 'Albumin Human', 1),
(282, 'Albumin Products', 1),
(283, 'Albutein', 1),
(284, 'Albuterol', 1),
(285, 'Alcalak Plus', 1),
(286, 'ALCET', 1),
(287, 'Alclometasone', 1),
(288, 'Alcloxa', 1),
(289, 'Alcohol', 1),
(290, 'Alcoholado Maravilla 70', 1),
(291, 'Alcomed Ca', 1),
(292, 'Alconox', 1),
(293, 'Alcortin', 1),
(294, 'Alcuronium', 1),
(295, 'Aldactazide', 1),
(296, 'Aldactone', 1),
(297, 'Aldara', 1),
(298, 'Aldesleukin', 1),
(299, 'ALDEX', 1),
(300, 'ALDEX D', 1),
(301, 'ALDEX DM', 1),
(302, 'ALDEX G', 1),
(303, 'ALDEX-CT', 1),
(304, 'Aldioxa', 1),
(305, 'Aldoclor', 1),
(306, 'Aldomet', 1),
(307, 'Aldoril D30', 1),
(308, 'Aldoril D50', 1),
(309, 'Aldoril-15', 1),
(310, 'Aldoril-25', 1),
(311, 'Aldosterone', 1),
(312, 'Aldosterone Antagonists', 1),
(313, 'Aldurazyme', 1),
(314, 'Alefacept', 1),
(315, 'Alemtuzumab', 1),
(316, 'Alendronic Acid', 1),
(317, 'Alera', 1),
(318, 'Alesse (21)', 1),
(319, 'Alesse-28', 1),
(320, 'Aleve', 1),
(321, 'Aleve Cold& Sinus', 1),
(322, 'Aleve Sinus& Headache', 1),
(323, 'Alexitol Sodium', 1),
(324, 'Alfacalcidol', 1),
(325, 'Alfalfa', 1),
(326, 'Alfalfa (Medicago Sativa)', 1),
(327, 'Alfenta', 1),
(328, 'Alfentanil', 1),
(329, 'Alferon N', 1),
(330, 'Alfuzosin', 1),
(331, 'Alga-K', 1),
(332, 'Algestone', 1),
(333, 'Alginate Dressing', 1),
(334, 'Alginic Acid', 1),
(335, 'Alglucerase', 1),
(336, 'Alglucosidase Alfa', 1),
(337, 'Alibendol', 1),
(338, 'ALIMTA', 1),
(339, 'Alinia', 1),
(340, 'Aliphatic Alcohols', 1),
(341, 'Alitretinoin', 1),
(342, 'Alizapride', 1),
(343, 'ALK SQ Cat Hair', 1),
(344, 'Alka-Aid', 1),
(345, 'Alka-Mint', 1),
(346, 'Alka-Mints', 1),
(347, 'Alka-Seltzer', 1),
(348, 'Alka-Seltzer Antacid', 1),
(349, 'Alka-Seltzer Extra Strength', 1),
(350, 'Alka-Seltzer Gold', 1),
(351, 'Alka-Seltzer Plus', 1),
(352, 'Alka-Seltzer Plus Cold', 1),
(353, 'Alka-Seltzer Plus Cold/Cough', 1),
(354, 'Alka-Seltzer Plus Flu/Body', 1),
(355, 'Alka-Seltzer Plus Night-Time', 1),
(356, 'Alka-Seltzer Plus Sinus', 1),
(357, 'Alka-Seltzer PM', 1),
(358, 'Alkeran', 1),
(359, 'Alkyl Acrylate Crosspolymer', 1),
(360, 'Alkyl-Aryl Sulfonate', 1),
(361, 'All Clear', 1),
(362, 'All Clear AR', 1),
(363, 'Allantoin', 1),
(364, 'Allbee C-800', 1),
(365, 'Allbee C-800 Plus Iron', 1),
(366, 'Allclenz', 1),
(367, 'Allegra', 1),
(368, 'Allegra-D 12 Hour', 1),
(369, 'Allegra-D 24 Hour', 1),
(370, 'Alleract', 1),
(371, 'Allerest', 1),
(372, 'Allerest 12 Hour', 1),
(373, 'Allerest Children''s', 1),
(374, 'Allergy Relief Spray', 1),
(375, 'Allerscript', 1),
(376, 'AllerTan', 1),
(377, 'Allerwell Allergy Formula', 1),
(378, 'AlleRx', 1),
(379, 'AlleRx DF', 1),
(380, 'AlleRx Dose Pack', 1),
(381, 'AlleRx PE', 1),
(382, 'AlleRx-D', 1),
(383, 'Allethrins', 1),
(384, 'ALLFEN', 1),
(385, 'ALLFEN C', 1),
(386, 'Allfen CX', 1),
(387, 'Allfen Jr', 1),
(388, 'ALLFEN-DM', 1),
(389, 'Allobarbital', 1),
(390, 'Allopurinol', 1),
(391, 'Allspice', 1),
(392, 'Allylestrenol', 1),
(393, 'Allylisothiocyanate', 1),
(394, 'Almagate', 1),
(395, 'Almasilate', 1),
(396, 'Almebex Plus B-12', 1),
(397, 'Almecillin', 1),
(398, 'Alminoprofen', 1),
(399, 'Almitrine', 1),
(400, 'Almond (Prunus Amygdalus)', 1),
(401, 'Almond Oil', 1),
(402, 'Almotriptan', 1),
(403, 'Alocril', 1),
(404, 'Aloe', 1),
(405, 'Aloe Cape', 1),
(406, 'Aloe Cort', 1),
(407, 'Aloe Polysaccharide', 1),
(408, 'Aloe Vera', 1),
(409, 'Aloe Vesta', 1),
(410, 'Aloe Vesta 2-n-1 Antifungal', 1),
(411, 'Aloe Vesta 2-n-1 Body Wash', 1),
(412, 'Aloe Vesta 2-n-1 Cleanser', 1),
(413, 'Aloe Vesta 2-n-1 Protective', 1),
(414, 'Aloe Vesta Antifungal', 1),
(415, 'Aloe Vesta Bathing Cloths', 1),
(416, 'Aloe Vesta Perineal', 1),
(417, 'Aloe Vesta Perineal II', 1),
(418, 'Aloe Vesta Protective', 1),
(419, 'Aloe Vesta Whirlbath Additive', 1),
(420, 'Aloglutamol', 1),
(421, 'Aloin', 1),
(422, 'Alomide', 1),
(423, 'Aloprim', 1),
(424, 'Alosetron', 1),
(425, 'Aloxi', 1),
(426, 'Aloxiprin', 1),
(427, 'Alpha 2 Adrenergic Agonist', 1),
(428, 'Alpha Cyclodextrin', 1),
(429, 'Alpha Glow', 1),
(430, 'Alpha Glucosidase', 1),
(431, 'Alpha Hydroxy Acids', 1),
(432, 'Alpha Interferon Moiety', 1),
(433, 'Alpha Keri Shower-Bath', 1),
(434, 'Alpha Pinene', 1),
(435, 'Alpha-1-Proteinase Inhibitor', 1),
(436, 'Alpha-Amylase', 1),
(437, 'Alpha-D-Galactosidase', 1),
(438, 'Alphagan', 1),
(439, 'Alphagan P', 1),
(440, 'Alphanate', 1),
(441, 'AlphaNine SD', 1),
(442, 'Alphaprodine', 1),
(443, 'Alprazolam', 1),
(444, 'Alprazolam Intensol', 1),
(445, 'Alprenolol', 1),
(446, 'Alprostadil', 1),
(447, 'Alrex', 1),
(448, 'Alseroxylon', 1),
(449, 'Alstonia', 1),
(450, 'Altace', 1),
(451, 'Alteplase', 1),
(452, 'Alteplase(Tissue Plasminogen Activator)', 1),
(453, 'Althiazide', 1),
(454, 'Altocor', 1),
(455, 'Altoprev', 1),
(456, 'Altretamine', 1),
(457, 'Alu-Cap', 1),
(458, 'Alu-Tab', 1),
(459, 'Alumina,Colloidal', 1),
(460, 'Aluminium Chlorate', 1),
(461, 'Aluminum', 1),
(462, 'Aluminum Aspirin', 1),
(463, 'Aluminum Carbonate,Basic', 1),
(464, 'Aluminum Containing Products', 1),
(465, 'Aluminum Flufenamate', 1),
(466, 'Aluminum Gluconate', 1),
(467, 'Aluminum Histidinate', 1),
(468, 'Aluminum Hydroxide', 1),
(469, 'Aluminum Hydroxyphosphate', 1),
(470, 'Aluminum Hydroxyquinoleate', 1),
(471, 'Aluminum Lactate', 1),
(472, 'Aluminum Lake', 1),
(473, 'Aluminum Monostearate', 1),
(474, 'Aluminum Nicotinate', 1),
(475, 'Aluminum Oxide', 1),
(476, 'Aluminum Phosphate', 1),
(477, 'Aluminum Powder', 1),
(478, 'Aluminum Salicylate', 1),
(479, 'Aluminum Silicate', 1),
(480, 'Aluminum Starch Octenylsucc.', 1),
(481, 'Aluminum Stearate', 1),
(482, 'Aluminum Triformate', 1),
(483, 'Alupent', 1),
(484, 'Alustra', 1),
(485, 'Alverine', 1),
(486, 'Amantadine', 1),
(487, 'Amantadine And Derivatives', 1),
(488, 'Amaranth', 1),
(489, 'Amaryl', 1),
(490, 'Ambenonium', 1),
(491, 'Ambenyl-D', 1),
(492, 'Amber Oil', 1),
(493, 'AMBI 40-1000 (PE)', 1),
(494, 'AMBI 40-1000-60 (PE)', 1),
(495, 'AMBI 5-15-100', 1),
(496, 'AMBI 60-1000', 1),
(497, 'AMBI 60-1000-30', 1),
(498, 'Ambien', 1),
(499, 'Ambien CR', 1),
(500, 'AMBIEN PAK', 1),
(501, 'Ambifed-G', 1),
(502, 'Ambifed-G DM', 1),
(503, 'AmBisome', 1),
(504, 'Ambroxol', 1),
(505, 'Ambroxol Acefyllinate', 1),
(506, 'Ambufylline', 1),
(507, 'Ambutonium', 1),
(508, 'Amcinonide', 1),
(509, 'Amdinocillin', 1),
(510, 'Amdinocillin Pivoxil', 1),
(511, 'Amerge', 1),
(512, 'Americaine', 1),
(513, 'Americaine First Aid', 1),
(514, 'Americaine Hemorrhoidal', 1),
(515, 'American Ginseng', 1),
(516, 'Amerifed', 1),
(517, 'Amerifed DM', 1),
(518, 'Amerigel Preventive Care', 1),
(519, 'Amerigel Wound Wash', 1),
(520, 'Amerituss AD', 1),
(521, 'Amevive', 1),
(522, 'Amezinium Metilsulfate', 1),
(523, 'AMICAR', 1),
(524, 'Amidate', 1),
(525, 'Amide Type Anesthetics', 1),
(526, 'Amifloxacin', 1),
(527, 'Amifostine', 1),
(528, 'Amikacin', 1),
(529, 'Amikin', 1),
(530, 'Amilomer', 1),
(531, 'Amiloride', 1),
(532, 'Amiloxate', 1),
(533, 'Amina-21', 1),
(534, 'Aminacrine', 1),
(535, 'Aminaphthone', 1),
(536, 'Amine Ergot Alkaloid', 1),
(537, 'Amine Oxidase', 1),
(538, 'Amineptine', 1),
(539, 'Amino Acids', 1),
(540, 'Amino Plus', 1),
(541, 'Amino-Cerv', 1),
(542, 'Aminobenzoic Acid', 1),
(543, 'Aminobenzoic Acid (B Vit)', 1),
(544, 'Aminobrain', 1),
(545, 'Aminobrain Plus', 1),
(546, 'Aminocaproic Acid', 1),
(547, 'Aminoglutethimide', 1),
(548, 'Aminoglycosides', 1),
(549, 'Aminohippuric Acid', 1),
(550, 'Aminohydroxybutyric Acid', 1),
(551, 'Aminolevulinate Analogues', 1),
(552, 'Aminolevulinic Acid HCl', 1),
(553, 'Aminomethylbenzoic Acid', 1),
(554, 'Aminomethylpropanol', 1),
(555, 'Aminopentamide', 1),
(556, 'Aminophyllin', 1),
(557, 'Aminophylline', 1),
(558, 'Aminopyrine', 1),
(559, 'Aminoquinuride', 1),
(560, 'Aminosalicylic Acid', 1),
(561, 'Aminosyn', 1),
(562, 'Aminoxin', 1),
(563, 'Amiodarone', 1),
(564, 'Amiodarone Analogues', 1),
(565, 'Amipaque', 1),
(566, 'Amipaque W/Diluent', 1),
(567, 'Amisometradine', 1),
(568, 'Amisulpride', 1),
(569, 'Amitiza', 1),
(570, 'Amitone', 1),
(571, 'Amitriptyline', 1),
(572, 'AmLactin', 1),
(573, 'AmLactin AP', 1),
(574, 'AmLactin Distribution Pack', 1),
(575, 'AmLactin XL', 1),
(576, 'Amlexanox', 1),
(577, 'Amlodipine', 1),
(578, 'Ammi Visnaga', 1),
(579, 'Ammonia', 1),
(580, 'Ammonia Water', 1),
(581, 'Ammonia Water (Ammonium Hydroxide)', 1),
(582, 'Ammonium', 1),
(583, 'Ammonium Alum', 1),
(584, 'Ammonium Chloride', 1),
(585, 'Ammonium Glycolate', 1),
(586, 'Ammonium Glycyrrhizinate', 1),
(587, 'Ammonium Iodide', 1),
(588, 'Ammonium Laureth Sulfate', 1),
(589, 'Ammonium Lauryl Sulfate', 1),
(590, 'Ammonium Metavanadate', 1),
(591, 'Ammonium Molybdate', 1),
(592, 'Ammonium Oleate', 1),
(593, 'Ammonium Phosphate', 1),
(594, 'Ammonium Stearate', 1),
(595, 'AMMONUL', 1),
(596, 'Amo Endosol Extra', 1),
(597, 'Amobarbital', 1),
(598, 'Amodiaquine', 1),
(599, 'Amol', 1),
(600, 'Amorolfine', 1),
(601, 'Amosan', 1),
(602, 'Amosulalol', 1),
(603, 'Amoxapine', 1),
(604, 'Amoxicillin', 1),
(605, 'Amoxil', 1),
(606, 'Amphetamine', 1),
(607, 'Amphetaminil', 1),
(608, 'Amphojel', 1),
(609, 'Amphotec', 1),
(610, 'Amphotericin B', 1),
(611, 'Ampicillin', 1),
(612, 'Amprenavir', 1),
(613, 'Amprenavir Derivatives', 1),
(614, 'Amprotropine', 1),
(615, 'Amsacrine', 1),
(616, 'Amur Corktree', 1),
(617, 'Amvisc', 1),
(618, 'Amvisc Plus', 1),
(619, 'Amyl Nitrite', 1),
(620, 'Amyl Salicylate', 1),
(621, 'Amylamine', 1),
(622, 'Amylase', 1),
(623, 'Amylene Hydrate', 1),
(624, 'Amylmetacresol', 1),
(625, 'Amylocaine', 1),
(626, 'Amytal', 1),
(627, 'Ana-Guard', 1),
(628, 'Anacardium Orientale', 1),
(629, 'Anacin', 1),
(630, 'Anaclasine', 1),
(631, 'Anadrol-50', 1),
(632, 'Anafranil', 1),
(633, 'Anagrelide', 1),
(634, 'Anagrelide Analogues', 1),
(635, 'Anahist', 1),
(636, 'Anakinra', 1),
(637, 'Analpram-HC', 1),
(638, 'Analpram-HC Singles', 1),
(639, 'AnaMantle HC', 1),
(640, 'AnaMantle HC Forte', 1),
(641, 'Anana', 1),
(642, 'Anana Forte', 1),
(643, 'Anaplex DM', 1),
(644, 'Anaplex DMX', 1),
(645, 'Anaplex HD', 1),
(646, 'Anaprox', 1),
(647, 'Anaprox DS', 1),
(648, 'Anastrozole', 1),
(649, 'Anatuss DM', 1),
(650, 'Anavar', 1),
(651, 'Anbesol', 1),
(652, 'Anbesol Maximum Strength', 1),
(653, 'Ancef', 1),
(654, 'Ancestim', 1),
(655, 'Ancobon', 1),
(656, 'Ancrod', 1),
(657, 'Ancrod Antiserum', 1),
(658, 'Androderm', 1),
(659, 'AndroGel', 1),
(660, 'Androgenic Anabolic Steroid', 1),
(661, 'Androgens', 1),
(662, 'Andrographis Paniculata', 1),
(663, 'Andropogon Schoenanthus', 1),
(664, 'Androstenedione', 1),
(665, 'Anecortave', 1),
(666, 'Anectine', 1),
(667, 'Anectine Flo-Pack', 1),
(668, 'Anemagen OB', 1),
(669, 'Anestacon', 1),
(670, 'Anethole', 1),
(671, 'Anethole Trithione', 1),
(672, 'Anexsia', 1),
(673, 'Angelica', 1),
(674, 'ANGELIQ', 1),
(675, 'Angio-Conray', 1),
(676, 'Angio-Pak with Conray', 1),
(677, 'Angio-Pak with Conray-30', 1),
(678, 'Angio-Pak with Conray-325', 1),
(679, 'Angio-Pak with Conray-400', 1),
(680, 'Angio-Pak with Conray-43', 1),
(681, 'Angio-Pak with Vascoray', 1),
(682, 'Angiomax', 1),
(683, 'Angiotensin I,Human', 1),
(684, 'Angiotensin II,Human', 1),
(685, 'Angiotensin III,Human', 1),
(686, 'Angiscein', 1),
(687, 'Anidulafungin', 1),
(688, 'Anileridine', 1),
(689, 'Animi-3', 1),
(690, 'Anion Exchange Resins', 1),
(691, 'Aniracetam', 1),
(692, 'Anise', 1),
(693, 'Anise Flavor', 1),
(694, 'Anise Oil', 1),
(695, 'Aniseed', 1),
(696, 'Anisindione', 1),
(697, 'Anisohydrocinnamol', 1),
(698, 'Anisotropine Methylbromide', 1),
(699, 'Anistreplase', 1),
(700, 'Annato Extract', 1),
(701, 'Annatto', 1),
(702, 'Ansaid', 1),
(703, 'Anspor', 1),
(704, 'Antabuse', 1),
(705, 'Antacid', 1),
(706, 'Antagon', 1),
(707, 'Antara', 1),
(708, 'Antazoline', 1),
(709, 'Anthra-Derm', 1),
(710, 'Anthracyclines', 1),
(711, 'Anthralin', 1),
(712, 'Anthraquinone', 1),
(713, 'Anthrax Vaccine', 1),
(714, 'Anthroposophic Drugs,O.U.', 1),
(715, 'Anti-Bacterial Cleansing', 1),
(716, 'Anti-Inhibitor Coagulant Comp.', 1),
(717, 'ANTI-NAUSEA GINGER', 1),
(718, 'Anti-Snore', 1),
(719, 'Anticholinergics,Other', 1),
(720, 'Anticholinergics,Quaternary', 1),
(721, 'Antidiuretic Hormone', 1),
(722, 'Antifoam', 1),
(723, 'Antifoam A', 1),
(724, 'Antifoam AF', 1),
(725, 'Antifoam DC', 1),
(726, 'Antifoam M', 1),
(727, 'Antihaemophilic Globulin', 1),
(728, 'Antihemophilic Factor VIII', 1),
(729, 'Antihemophilic Factors', 1),
(730, 'Antihistamine', 1),
(731, 'Antihistamines', 1),
(732, 'Antilirium', 1),
(733, 'Antiminth', 1),
(734, 'Antimony', 1),
(735, 'Antiox', 1),
(736, 'Antioxidant', 1),
(737, 'Antioxidant Formula', 1),
(738, 'Antioxidant Vitamin& Mineral', 1),
(739, 'Antioxidant Vitamins', 1),
(740, 'Antipyrine', 1),
(741, 'Antirabies Serum', 1),
(742, 'Antiseptic', 1),
(743, 'Antiseptic Bio-Hand', 1),
(744, 'Antiseptic Gel', 1),
(745, 'Antiseptic Wound-Skin', 1),
(746, 'Antiseptic& Anesthetic', 1),
(747, 'Antisera', 1),
(748, 'Antithrombin III,Human', 1),
(749, 'Antivenin Crotalidae', 1),
(750, 'Antivenin,Crotalidae (Equine)', 1),
(751, 'Antivenin,Crotalidae Fab(Ovin)', 1),
(752, 'Antivenin,Latrodectus Mactans', 1),
(753, 'Antivenin,Micrurus Fulvius', 1),
(754, 'Antivenom, Cobra', 1),
(755, 'Antivenom, Five Pace Snake', 1),
(756, 'Antivenom, Green Pit Viper', 1),
(757, 'Antivenom, King Cobra', 1),
(758, 'Antivenom, Krait Snake', 1),
(759, 'Antivenom, Naja Naja', 1),
(760, 'Antivenom, Russells Viper', 1),
(761, 'Antivenom, Siberian Pit Viper', 1),
(762, 'Antivenom, Spitting Cobra', 1),
(763, 'Antivenom,Black Mamba', 1),
(764, 'Antivenom,Black Snake', 1),
(765, 'Antivenom,Bothrops Alternatus', 1),
(766, 'Antivenom,Bothrops Neuwiedii', 1),
(767, 'Antivenom,Box Jellyfish', 1),
(768, 'Antivenom,Brown Snake', 1),
(769, 'Antivenom,Cape Cobra', 1),
(770, 'Antivenom,Centruroide Scorpion', 1),
(771, 'Antivenom,Crotalus Terrifus', 1),
(772, 'Antivenom,Death Adder', 1),
(773, 'Antivenom,European Viper', 1),
(774, 'Antivenom,Eygptian Cobra', 1),
(775, 'Antivenom,Forest Cobra', 1),
(776, 'Antivenom,Funnel Web Spider', 1),
(777, 'Antivenom,Gaboon Adder', 1),
(778, 'Antivenom,Green Mamba', 1),
(779, 'Antivenom,Jameson''s Mamba', 1),
(780, 'Antivenom,Mozambique Spitting', 1),
(781, 'Antivenom,Paralysis Tick', 1),
(782, 'Antivenom,Polyvalent Snake', 1),
(783, 'Antivenom,Puff Adder', 1),
(784, 'Antivenom,Red Back Spider', 1),
(785, 'Antivenom,Rinkhals', 1),
(786, 'Antivenom,Sea Snake', 1),
(787, 'Antivenom,Stonefish', 1),
(788, 'Antivenom,Taipan', 1),
(789, 'Antivenom,Tiger Snake', 1),
(790, 'Antivert', 1),
(791, 'Antivert/25', 1),
(792, 'Antiviral Drugs', 1),
(793, 'Antizol', 1),
(794, 'Anturane', 1),
(795, 'Anusert', 1),
(796, 'Anusol', 1),
(797, 'Anusol HC-1', 1),
(798, 'Anzemet', 1),
(799, 'Apalcillin', 1),
(800, 'APAP 500', 1),
(801, 'Apatate', 1),
(802, 'Apatate/Fluoride', 1),
(803, 'Apazone', 1),
(804, 'Apetigen', 1),
(805, 'Apetigen Plus', 1),
(806, 'Apetimar', 1),
(807, 'Apetizinc', 1),
(808, 'Aphthasol', 1),
(809, 'Apidra', 1),
(810, 'Apis Mellifera Venom', 1),
(811, 'Apligraf', 1),
(812, 'Aplisol', 1),
(813, 'Aplitest', 1),
(814, 'APOKYN', 1),
(815, 'Apomorphine', 1),
(816, 'Appearex', 1),
(817, 'Appetite Control', 1),
(818, 'Apple', 1),
(819, 'Apple Bitter', 1),
(820, 'Apple Cider Vinegar Complex', 1),
(821, 'Apple Cider Vinegar Plus', 1),
(822, 'Apple Flavor', 1),
(823, 'Apple Juice', 1),
(824, 'Apple Pectin', 1),
(825, 'Apple Tree', 1),
(826, 'AppTrim', 1),
(827, 'AppTrim Program', 1),
(828, 'AppTrim-D', 1),
(829, 'Apraclonidine', 1),
(830, 'Aprepitant', 1),
(831, 'Apresazide', 1),
(832, 'Apresoline', 1),
(833, 'Apricot', 1),
(834, 'Apricot Flavor', 1),
(835, 'Apricot Kernel Oil', 1),
(836, 'Apricot Tree', 1),
(837, 'Aprindine', 1),
(838, 'Aprobarbital', 1),
(839, 'Aprotinin', 1),
(840, 'Aptigen-Plus', 1),
(841, 'Aptivus', 1),
(842, 'Aqua Glycolic', 1),
(843, 'Aqua Glycolic Astringent', 1),
(844, 'Aqua Lacten', 1),
(845, 'Aqua-Ban/Pamabrom', 1),
(846, 'Aqua-Film Tears', 1),
(847, 'Aqua-Mephyton', 1),
(848, 'Aquacare Hp', 1),
(849, 'Aquachloral', 1),
(850, 'AquADEKs', 1),
(851, 'AquADEKs Pediatric', 1),
(852, 'AQUADERM', 1),
(853, 'Aquamed', 1),
(854, 'Aquaphilic with Tac+Carbamide', 1),
(855, 'Aquaphor', 1),
(856, 'Aquaphor with Natural Healing', 1),
(857, 'Aquasite', 1),
(858, 'Aquasol A', 1),
(859, 'Aquasol E', 1),
(860, 'AQUATAB D', 1),
(861, 'AQUATAB DM', 1),
(862, 'AQUATAB-C', 1),
(863, 'Arabinogalactan', 1),
(864, 'Arabinoxylan', 1),
(865, 'Arachidonic Acid (ARA)', 1),
(866, 'Arachidyl Alcohol', 1),
(867, 'Arachidyl Glucoside', 1),
(868, 'Aralen', 1),
(869, 'Aranesp', 1),
(870, 'Aranesp (Polysorbate)', 1),
(871, 'Aranesp SureClick -Polysorbate', 1),
(872, 'Arava', 1),
(873, 'Arbekacin', 1),
(874, 'Arbutamine', 1),
(875, 'Arcitumomab', 1),
(876, 'Ardeparin,Porcine', 1),
(877, 'Areca', 1),
(878, 'Aredia', 1),
(879, 'Arestin', 1),
(880, 'Arformoterol', 1),
(881, 'Argatroban', 1),
(882, 'Argentum Metallicum', 1),
(883, 'ArgiMent', 1),
(884, 'Arginine', 1),
(885, 'Arginine Zinc', 1),
(886, 'Arginine-Pyroglutamate', 1),
(887, 'Argipressin', 1),
(888, 'Argitein', 1),
(889, 'Aricept', 1),
(890, 'Aricept ODT', 1),
(891, 'Aridex Pediatric', 1),
(892, 'ARIDEX-D pediatric', 1),
(893, 'Arimidex', 1),
(894, 'Aripiprazole', 1),
(895, 'Aristo-Pak', 1),
(896, 'Aristocort', 1),
(897, 'Aristocort A', 1),
(898, 'Aristocort HP', 1),
(899, 'Aristocort Intralesional', 1),
(900, 'Aristocort LP', 1),
(901, 'Aristocort R', 1),
(902, 'Aristospan Intra-Articular', 1),
(903, 'Aristospan Intralesional', 1),
(904, 'Aristospan Parenteral', 1),
(905, 'Arixtra', 1),
(906, 'Armour Thyroid', 1),
(907, 'Arnica', 1),
(908, 'Aromasin', 1),
(909, 'Arotinolol', 1),
(910, 'Arranon', 1),
(911, 'Arrow Root', 1),
(912, 'Arsenic', 1),
(913, 'Arsenic Analogues', 1),
(914, 'Artane', 1),
(915, 'Artemether', 1),
(916, 'Artesunate', 1),
(917, 'Arthricare Pain Relieving Rub', 1),
(918, 'Arthritis Foundation', 1),
(919, 'Arthritis Hot Pain Relief', 1),
(920, 'Arthritis Strength BC Powder', 1),
(921, 'Arthropan', 1),
(922, 'Arthropod-Borne And Other Neurotoxic Virus Va', 1),
(923, 'Arthrotec 50', 1),
(924, 'Arthrotec 75', 1),
(925, 'Arthx SS', 1),
(926, 'Articaine', 1),
(927, 'Artichoke', 1),
(928, 'Artra Beauty', 1),
(929, 'Asacol', 1),
(930, 'Asafetida', 1),
(931, 'Ascocid-1000', 1),
(932, 'Ascocid-500-D', 1),
(933, 'Ascorbic Acid', 1),
(934, 'Ascorbic Acid (Vitamin C)', 1),
(935, 'Ascriptin', 1),
(936, 'Ascriptin A/D', 1),
(937, 'Ascriptin AP', 1),
(938, 'Asendin', 1),
(939, 'Ashwagandha', 1),
(940, 'Asiatic Dogwood', 1),
(941, 'Asmanex Twisthaler', 1),
(942, 'Asparaginase', 1),
(943, 'Asparaginase (E. Coli)', 1),
(944, 'Asparaginase (Erwinia)', 1),
(945, 'Asparaginase (PEG, E. Coli)', 1),
(946, 'Asparagine', 1),
(947, 'Asparagus', 1),
(948, 'Asparagus Flavor', 1),
(949, 'Aspartame', 1),
(950, 'Aspartate-Mg+K', 1),
(951, 'Aspartic Acid', 1),
(952, 'Aspercreme', 1),
(953, 'Aspercreme with Aloe', 1),
(954, 'Aspergillus Nidulans', 1),
(955, 'Aspergillus Oryzae Enzymes', 1),
(956, 'Aspergum', 1),
(957, 'Aspidium', 1),
(958, 'Aspirin', 1),
(959, 'Aspirin Regimen Bayer/Calcium', 1),
(960, 'Astelin', 1),
(961, 'Astemizole', 1),
(962, 'Asthmatic Paroxysms No.21', 1),
(963, 'Astragalus', 1),
(964, 'Astragalus (Astragalus Membranaceus)', 1),
(965, 'Astramorph-PF', 1),
(966, 'Astringents', 1),
(967, 'Astringyn', 1),
(968, 'Astroglide', 1),
(969, 'ATABEX PRENATAL', 1),
(970, 'Atacand', 1),
(971, 'Atacand HCT', 1),
(972, 'Atarax', 1),
(973, 'Atazanavir', 1),
(974, 'Atazanavir Derivatives', 1),
(975, 'Atenolol', 1),
(976, 'Atgam', 1),
(977, 'Ativan', 1),
(978, 'Atmos 300', 1),
(979, 'Atomoxetine', 1),
(980, 'Atopiclair', 1),
(981, 'Atorvastatin', 1),
(982, 'Atosiban', 1),
(983, 'Atovaquone', 1),
(984, 'ATP', 1),
(985, 'Atrac-Tain', 1),
(986, 'Atractylodes, O.U.', 1),
(987, 'Atracurium Besylate', 1),
(988, 'ATRIDOX', 1),
(989, 'ATRIPLA', 1),
(990, 'Atromid-S', 1),
(991, 'Atropa Belladonna', 1),
(992, 'Atropine', 1),
(993, 'Atropine Borate', 1),
(994, 'Atrovent', 1),
(995, 'Atrovent HFA', 1),
(996, 'Attapulgite', 1),
(997, 'Attends Washcloths', 1),
(998, 'Attenuvax', 1),
(999, 'Atuss DR', 1),
(1000, 'Atuss DS', 1),
(1001, 'Atuss EX', 1),
(1002, 'Atuss G', 1),
(1003, 'Atuss HC', 1),
(1004, 'Atuss HD', 1),
(1005, 'Atuss HS', 1),
(1006, 'Atuss HX', 1),
(1007, 'Atuss MR', 1),
(1008, 'Atuss MS', 1),
(1009, 'Atuss NX', 1),
(1010, 'Atuss-12 DM', 1),
(1011, 'Atuss-12 DX', 1),
(1012, 'Augmentin', 1),
(1013, 'Augmentin ES-600', 1),
(1014, 'Augmentin XR', 1),
(1015, 'Auragen', 1),
(1016, 'Auranofin', 1),
(1017, 'Aurothioglucose', 1),
(1018, 'Aurotioprol', 1),
(1019, 'Aurum Chloratum', 1),
(1020, 'Autologous Cult. Chondrocytes', 1),
(1021, 'Autoplex T', 1),
(1022, 'Autumn Crocus', 1),
(1023, 'Avage', 1),
(1024, 'Avalide', 1),
(1025, 'AVANDAMET', 1),
(1026, 'Avandaryl', 1),
(1027, 'Avandia', 1),
(1028, 'Avapro', 1),
(1029, 'Avar', 1),
(1030, 'AVASTIN', 1),
(1031, 'AVC Vaginal', 1),
(1032, 'Aveeno', 1),
(1033, 'Aveeno Anti-Itch', 1),
(1034, 'Aveeno Daily Moisturizing', 1),
(1035, 'Aveeno Moisturizing', 1),
(1036, 'Aveeno Oatmeal Bath Oilated', 1),
(1037, 'Aveeno Oatmeal Bath Regular', 1),
(1038, 'Aveenobar', 1),
(1039, 'Avelox', 1),
(1040, 'Avelox ABC Pack', 1),
(1041, 'Aventyl', 1),
(1042, 'Avinza', 1),
(1043, 'Avitene', 1),
(1044, 'Avitene Flour', 1),
(1045, 'Avlosulfon', 1),
(1046, 'Avobenzone', 1),
(1047, 'Avocado', 1),
(1048, 'Avocado (Laurus Persea)', 1),
(1049, 'Avodart', 1),
(1050, 'Avonex', 1),
(1051, 'Avonex Administration Pack', 1),
(1052, 'Avosil', 1),
(1053, 'Axert', 1),
(1054, 'Axid', 1),
(1055, 'Axid AR', 1),
(1056, 'Axsain', 1),
(1057, 'Ayahuasca', 1),
(1058, 'Ayr Allergy& Sinus', 1),
(1059, 'Ayr Saline', 1),
(1060, 'Ayr Saline Gel', 1),
(1061, 'Ayr Sinus Rinse', 1),
(1062, 'Ayr Sinus Rinse Refill', 1),
(1063, 'Ayr Snore Relieving', 1),
(1064, 'Azacitidine', 1),
(1065, 'Azactam', 1),
(1066, 'Azactam-Iso-osmotic Dextrose', 1),
(1067, 'Azapetine', 1),
(1068, 'Azaribine', 1),
(1069, 'Azasan', 1),
(1070, 'Azaserine', 1),
(1071, 'Azasetron', 1),
(1072, 'Azatadine', 1),
(1073, 'Azathioprine', 1),
(1074, 'Azelaic Acid', 1),
(1075, 'Azelastine', 1),
(1076, 'Azelex', 1),
(1077, 'Azidamfenicol', 1),
(1078, 'Azidocillin', 1),
(1079, 'AZILECT', 1),
(1080, 'Azintamide', 1),
(1081, 'Azithromycin', 1),
(1082, 'Azlocillin', 1),
(1083, 'Azmacort', 1),
(1084, 'Azo-Dine', 1),
(1085, 'Azo-Dine Urinary Analgesic', 1),
(1086, 'Azopt', 1),
(1087, 'Azosemide', 1),
(1088, 'Aztreonam', 1),
(1089, 'Azulene', 1),
(1090, 'Azulfidine', 1),
(1091, 'Azulfidine EN-tabs', 1),
(1092, 'Azuresin', 1),
(1093, 'B& O 15-A Supprette', 1),
(1094, 'B& O 16-A Supprette', 1),
(1095, 'B+DRIER Anti-Perspirant', 1),
(1096, 'B-50 Formula', 1),
(1097, 'B-6-500', 1),
(1098, 'B-Redi/Red Hearts/Red Roosters', 1),
(1099, 'B.F.I', 1),
(1100, 'Ba Ji Tian', 1),
(1101, 'Babee Teething', 1),
(1102, 'Baby Anbesol', 1),
(1103, 'Baby Orajel', 1),
(1104, 'Baby Orajel Tooth/Gum Cleanser', 1),
(1105, 'Baby Sunblock', 1),
(1106, 'Bacampicillin', 1),
(1107, 'Baciguent', 1),
(1108, 'Bacillus Cereus', 1),
(1109, 'Bacitracin', 1),
(1110, 'Bacitracin Zinc', 1),
(1111, 'Bacitracin Zinc Micronized', 1),
(1112, 'Backache', 1),
(1113, 'Baclofen', 1),
(1114, 'Bacmin', 1),
(1115, 'Bacteriostatic Sodium Chloride', 1),
(1116, 'Bactine', 1),
(1117, 'Bactrim', 1),
(1118, 'Bactrim Iv', 1),
(1119, 'Bactroban', 1),
(1120, 'Bactroban Nasal', 1),
(1121, 'Badger Fat', 1),
(1122, 'Baicalin', 1),
(1123, 'Bal in Oil', 1),
(1124, 'Balamine DM', 1),
(1125, 'Balnetar', 1),
(1126, 'Balofloxacin', 1),
(1127, 'Balsalazide', 1),
(1128, 'Balsam Canada', 1),
(1129, 'Balsam Peru', 1),
(1130, 'Baltussin', 1),
(1131, 'Bamboo', 1),
(1132, 'Bambuterol', 1),
(1133, 'Bamethan', 1),
(1134, 'Bamifylline', 1),
(1135, 'Bamipine', 1),
(1136, 'Bamipine Salicylate', 1),
(1137, 'Banaba', 1),
(1138, 'Banalg-HS Liniment', 1),
(1139, 'Banana', 1),
(1140, 'Banana Flavor', 1),
(1141, 'Bancap HC', 1),
(1142, 'Baquacil', 1),
(1143, 'Bar-Test', 1),
(1144, 'Baraclude', 1),
(1145, 'Barberry', 1),
(1146, 'Barberry (Berberis Vulgaris)', 1),
(1147, 'Barbexaclone', 1),
(1148, 'Barbidonna', 1),
(1149, 'Barbidonna #2', 1),
(1150, 'Barbital', 1),
(1151, 'Barbiturates', 1),
(1152, 'Baricon', 1),
(1153, 'Barium Iodide', 1),
(1154, 'Barium Lake', 1),
(1155, 'Barium Sulfate', 1),
(1156, 'Barley', 1),
(1157, 'Barnidipine', 1),
(1158, 'Barobag Enema Kit', 1),
(1159, 'Baros', 1),
(1160, 'Barosperse 110', 1),
(1161, 'Barosperse Enema', 1),
(1162, 'Barosperse-Baro-Flex', 1),
(1163, 'Barrenwort', 1),
(1164, 'Barri-Care', 1),
(1165, 'Basaljel', 1),
(1166, 'Basil', 1),
(1167, 'Basiliximab', 1),
(1168, 'Basis', 1),
(1169, 'Bassorin', 1),
(1170, 'Batherapy Mineral Bath', 1),
(1171, 'Batroxobin', 1),
(1172, 'Bavarian Cream Flavor', 1),
(1173, 'Bay Oil', 1),
(1174, 'Bayberry', 1),
(1175, 'Baycol', 1),
(1176, 'Bayer Aspirin', 1),
(1177, 'Bayer Childrens Aspirin', 1),
(1178, 'Bayer Select', 1),
(1179, 'Bayer Select Menstrual', 1),
(1180, 'BayRab', 1),
(1181, 'BayTet', 1),
(1182, 'Baza Cleanse& Protect', 1),
(1183, 'BAZA CLEAR', 1),
(1184, 'Baza Cream Hospital Care Pack', 1),
(1185, 'Baza Cream Skin Care Pack', 1),
(1186, 'Baza Pro', 1),
(1187, 'Baza Protect', 1),
(1188, 'BC', 1),
(1189, 'Bc Cold', 1),
(1190, 'BC Cold Powder/Cold-Sinus', 1),
(1191, 'BC Headache', 1),
(1192, 'BC Headache Powder', 1),
(1193, 'BCG Live', 1),
(1194, 'BCG Tice Vaccine', 1),
(1195, 'BCG Vaccine', 1),
(1196, 'BD Glucose', 1),
(1197, 'Be-Flex Plus', 1),
(1198, 'Bean Pod', 1),
(1199, 'Beano', 1),
(1200, 'Bear''s Garlic', 1),
(1201, 'Bear-E-Yum GI', 1),
(1202, 'Bearberry', 1),
(1203, 'Bebulin VH', 1),
(1204, 'Becaplermin', 1),
(1205, 'Beclomethasone', 1),
(1206, 'Beclovent/Beconase Refill', 1),
(1207, 'Beconase AQ', 1),
(1208, 'Bedsore Spray', 1),
(1209, 'Bee Pollen', 1),
(1210, 'Bee Pollen Plus Ginseng', 1),
(1211, 'Bee Pollen with Royal Jelly', 1),
(1212, 'Bee Pollens', 1),
(1213, 'Bee Sting Kit', 1),
(1214, 'Beef Containing Products', 1),
(1215, 'Beef Protein', 1),
(1216, 'Beeswax', 1),
(1217, 'Beet', 1),
(1218, 'Beet Juice', 1),
(1219, 'Befunolol', 1),
(1220, 'Behenamidopropyl Dimeth.Behen.', 1),
(1221, 'Belladonna', 1),
(1222, 'Belladonna Alkaloids', 1),
(1223, 'Belladonna Leaf', 1),
(1224, 'Bellergal-S', 1),
(1225, 'Bemegride', 1),
(1226, 'Bemetizide', 1),
(1227, 'Bemiparin', 1),
(1228, 'Ben Loz', 1),
(1229, 'Ben-Aqua 10', 1),
(1230, 'Benactyzine', 1),
(1231, 'Benadryl', 1),
(1232, 'Benadryl Allergy', 1),
(1233, 'Benadryl Allergy Decongestant', 1),
(1234, 'Benadryl Allergy Sinus Child''s', 1),
(1235, 'Benadryl Allergy-Sinus', 1),
(1236, 'Benadryl Allergy-Sinus-Head', 1),
(1237, 'Benadryl Allergy/Cold', 1),
(1238, 'Benadryl Allergy/Sinus/Headach', 1),
(1239, 'Benadryl Childrens Formula', 1),
(1240, 'Benadryl Cold', 1),
(1241, 'Benadryl Cold/Flu', 1),
(1242, 'Benadryl Decongestant', 1),
(1243, 'Benadryl Dye-Free Allergy', 1),
(1244, 'Benadryl Extra Strength', 1),
(1245, 'Benadryl Itch Relief', 1),
(1246, 'Benadryl Itch Relief Stick', 1),
(1247, 'Benadryl Itch Stopping', 1),
(1248, 'Benadryl Itch Stopping Child', 1),
(1249, 'Benadryl Itch Stopping Max St', 1),
(1250, 'Benadryl Maximum Strength', 1),
(1251, 'Benadryl Spray', 1),
(1252, 'Benadryl Steri-Dose', 1),
(1253, 'Benadryl Steri-Vial', 1),
(1254, 'Benadryl-D Allergy& Sinus', 1),
(1255, 'Benadryl-D Fastmelt', 1),
(1256, 'Benazepril', 1),
(1257, 'Bencyclane', 1),
(1258, 'Bendamustine', 1),
(1259, 'Bendazac', 1),
(1260, 'Bendito Alcoholado', 1),
(1261, 'Bendroflumethiazide', 1),
(1262, 'Benecol', 1),
(1263, 'Benefiber Clear', 1),
(1264, 'Benefiber Plus Calcium', 1),
(1265, 'BeneFIX', 1),
(1266, 'Benemid', 1),
(1267, 'Benethamine Penicillin', 1),
(1268, 'Benexate', 1),
(1269, 'Benfluorex', 1),
(1270, 'Benfotiamine', 1),
(1271, 'BenGay Daytime', 1),
(1272, 'BenGay Pain Relieving', 1),
(1273, 'BenGay Vanishing Scent', 1),
(1274, 'BenGay Warming Ice', 1),
(1275, 'Benicar', 1),
(1276, 'Benicar HCT', 1),
(1277, 'Benidipine', 1),
(1278, 'Benoquin', 1),
(1279, 'Benorilate', 1),
(1280, 'Benoxaprofen', 1),
(1281, 'Benoxinate', 1),
(1282, 'Benoxyl 10', 1),
(1283, 'Benoxyl 5', 1),
(1284, 'Benperidol', 1),
(1285, 'Benproperine', 1),
(1286, 'Bensal HP', 1),
(1287, 'Benserazide', 1),
(1288, 'Bentiromide', 1),
(1289, 'Bentonite', 1),
(1290, 'Bentyl', 1),
(1291, 'Benylin', 1),
(1292, 'Benylin Expectorant', 1),
(1293, 'Benylin-DME', 1),
(1294, 'Benz-O-Sthetic', 1),
(1295, 'Benz-Protect', 1),
(1296, 'Benz-Protect Sterile', 1),
(1297, 'Benzac', 1),
(1298, 'Benzac AC', 1),
(1299, 'Benzac AC Wash', 1),
(1300, 'Benzac W', 1),
(1301, 'Benzac W Wash', 1),
(1302, 'Benzaclin', 1),
(1303, 'Benzagel-10', 1),
(1304, 'Benzagel-5', 1),
(1305, 'Benzaldehyde', 1),
(1306, 'Benzalkonium', 1),
(1307, 'Benzamycin', 1),
(1308, 'BenzamycinPak', 1),
(1309, 'Benzbromarone', 1),
(1310, 'Benzedrex', 1),
(1311, 'Benzene', 1),
(1312, 'Benzeneacetic Acid', 1),
(1313, 'Benzestrol', 1),
(1314, 'Benzethonium Chloride', 1),
(1315, 'Benzimidazole', 1),
(1316, 'Benziq', 1),
(1317, 'Benziq LS', 1),
(1318, 'Benznidazole', 1),
(1319, 'Benzo-Creme', 1),
(1320, 'Benzo-Menth', 1),
(1321, 'Benzoate Derivatives', 1),
(1322, 'Benzocaine', 1),
(1323, 'Benzodent', 1),
(1324, 'Benzodiazepines', 1),
(1325, 'Benzododecinium', 1),
(1326, 'Benzoic Acid', 1),
(1327, 'Benzoin', 1),
(1328, 'Benzoin Compound', 1),
(1329, 'Benzonatate', 1),
(1330, 'Benzophenone', 1),
(1331, 'Benzoquinonium', 1),
(1332, 'Benzoxonium', 1),
(1333, 'Benzoyl Peroxide', 1),
(1334, 'Benzoyl Peroxide 10', 1),
(1335, 'Benzoyl Peroxide 5', 1),
(1336, 'Benzphetamine', 1),
(1337, 'Benzpyrinium', 1),
(1338, 'Benzquercin', 1),
(1339, 'Benzquinamide', 1),
(1340, 'Benzthiazide', 1),
(1341, 'Benztropine', 1),
(1342, 'Benzydamine', 1),
(1343, 'Benzyl Alcohol', 1),
(1344, 'Benzyl Cinnamate', 1),
(1345, 'Benzyl Mandelate', 1),
(1346, 'Benzyl Nicotinate', 1),
(1347, 'Benzyl Salicylate', 1),
(1348, 'Benzylephedrine', 1),
(1349, 'Benzylhydrochlorothiazide', 1),
(1350, 'Benzylisoquinolinium', 1),
(1351, 'Benzylparaben', 1),
(1352, 'Benzylthiouracil', 1),
(1353, 'Bephenium', 1),
(1354, 'Bepridil', 1),
(1355, 'Beractant', 1),
(1356, 'Beraprost', 1),
(1357, 'Berberine', 1),
(1358, 'Bergamot Oil', 1),
(1359, 'Bergamot Oil, Imitation', 1),
(1360, 'Berry Flavor', 1),
(1361, 'Beta Greens', 1),
(1362, 'Beta Interferon Moiety', 1),
(1363, 'Beta Pinene', 1),
(1364, 'Beta-Adrenergic Agents', 1),
(1365, 'Beta-Adrenergic Blocking Agents', 1),
(1366, 'Beta-Alanine', 1),
(1367, 'Beta-Carotene', 1),
(1368, 'Beta-Carotene(A) w-C& E', 1),
(1369, 'Betadex', 1),
(1370, 'Betadine', 1),
(1371, 'Betadine Antibiotic/Moisturize', 1),
(1372, 'Betadine Ophthalmic Prep', 1),
(1373, 'Betadine Perineal Wash', 1),
(1374, 'Betadine Plus', 1),
(1375, 'Betadine Prepstick', 1),
(1376, 'Betadine Prepstick Plus', 1),
(1377, 'Betadine RTU', 1),
(1378, 'Betadine Skin Cleanser', 1),
(1379, 'Betadine Spray', 1),
(1380, 'Betadine Surgi-Prep', 1),
(1381, 'Betadine Surgical Scrub', 1),
(1382, 'Betadine Swab Aid', 1),
(1383, 'Betadine Swabsticks', 1),
(1384, 'Betagan', 1),
(1385, 'Betahistine', 1),
(1386, 'Betahistines', 1),
(1387, 'Betaine', 1),
(1388, 'Betalactams', 1),
(1389, 'Betamethasone', 1),
(1390, 'Betamethasone Benzoate', 1),
(1391, 'Betamide', 1),
(1392, 'Betanaphthol', 1),
(1393, 'Betapace', 1),
(1394, 'Betapace AF', 1),
(1395, 'Betasal', 1),
(1396, 'Betaseron', 1),
(1397, 'BetaTan', 1),
(1398, 'Betatar Gel', 1),
(1399, 'Betavent', 1),
(1400, 'Betaxolol', 1),
(1401, 'Betazole', 1),
(1402, 'Betazyme', 1),
(1403, 'Bethanechol', 1),
(1404, 'Bethanidine', 1),
(1405, 'Betimol', 1),
(1406, 'Betoptic S', 1),
(1407, 'Betotastine', 1),
(1408, 'Betula Leaf (Birch)', 1),
(1409, 'Bevacizumab', 1),
(1410, 'Bevantolol', 1),
(1411, 'Bexarotene', 1),
(1412, 'Bextra', 1),
(1413, 'Bexxar', 1),
(1414, 'Bezafibrate', 1),
(1415, 'Bi-Zets', 1),
(1416, 'Bi-Zets/Benzotroches', 1),
(1417, 'Biafine Emulsion', 1),
(1418, 'Biavax II', 1),
(1419, 'Biaxin', 1),
(1420, 'Biaxin XL', 1),
(1421, 'Biaxin XL Pak', 1),
(1422, 'Bibrocathol', 1),
(1423, 'Bicalutamide', 1),
(1424, 'Bicarsim Forte', 1),
(1425, 'Bicillin C-R', 1),
(1426, 'Bicillin L-A', 1),
(1427, 'Bicisate', 1),
(1428, 'Bicitra', 1),
(1429, 'Biclotymol', 1),
(1430, 'BiCNU', 1),
(1431, 'Bicozene', 1),
(1432, 'Bidex-DM', 1),
(1433, 'BiDil', 1),
(1434, 'Bifemelane', 1),
(1435, 'Bifido Factor', 1),
(1436, 'Bifidobacterium Bifidum', 1),
(1437, 'Bifidobacterium Breve', 1),
(1438, 'Bifidobacterium Infantis', 1),
(1439, 'Bifidobacterium longum', 1),
(1440, 'Bifonazole', 1),
(1441, 'Biguanides', 1),
(1442, 'Bilax', 1),
(1443, 'Bilberry', 1),
(1444, 'Bilberry Extract', 1),
(1445, 'Bile And Bile Salts', 1),
(1446, 'Bilopaque', 1),
(1447, 'Biltricide', 1),
(1448, 'Bimatoprost', 1),
(1449, 'Binaca Drops', 1),
(1450, 'binora', 1),
(1451, 'Bio-Immunex', 1),
(1452, 'Bio-Statin', 1),
(1453, 'Bio-Throid', 1),
(1454, 'Bioallethrin', 1),
(1455, 'Bioflavonoid, Lemon', 1),
(1456, 'Bioflavonoids', 1),
(1457, 'Bioflavonoids, Citrus', 1),
(1458, 'Biohist LA', 1),
(1459, 'Biolex', 1),
(1460, 'Bionect', 1),
(1461, 'Biotect Plus', 1),
(1462, 'Biotene', 1),
(1463, 'Biotin', 1),
(1464, 'Biotin Forte', 1),
(1465, 'Biozyme-C', 1),
(1466, 'Biperiden', 1),
(1467, 'Biphenamine', 1),
(1468, 'Bipyridine Inotropic Agents', 1),
(1469, 'Birch', 1),
(1470, 'Birch Tar Oil(Rec)', 1),
(1471, 'Bisabolol', 1),
(1472, 'Bisacodyl', 1),
(1473, 'Bisdequalinium', 1),
(1474, 'Bismuth', 1),
(1475, 'Bismuth Aluminate', 1),
(1476, 'Bismuth Chloride Oxide', 1),
(1477, 'Bismuth Containing Compounds', 1),
(1478, 'Bismuth Hydroxide', 1),
(1479, 'Bismuth Iodide Oxide', 1),
(1480, 'Bismuth Magma', 1),
(1481, 'Bismuth Oil', 1),
(1482, 'Bismuth Oxide', 1),
(1483, 'Bismuth Resorcin', 1),
(1484, 'Bismuth Subcarbonate', 1),
(1485, 'Bismuth Subgallate', 1),
(1486, 'Bismuth Subnitrate', 1),
(1487, 'Bismuth Subsalicylate', 1),
(1488, 'Bismuth Tribromophenate', 1),
(1489, 'Bismuth Valproate', 1),
(1490, 'Bisoprolol', 1),
(1491, 'Bisphosphonates', 1),
(1492, 'Bithionol', 1),
(1493, 'Bitolterol', 1),
(1494, 'Bitter Almond Oil', 1),
(1495, 'Bitter Melon', 1),
(1496, 'Bitter-Bur', 1),
(1497, 'Bittersweet', 1),
(1498, 'Bivalirudin', 1),
(1499, 'Black Cherry Flavor', 1),
(1500, 'Black Cohosh', 1),
(1501, 'Black Cohosh Extract', 1),
(1502, 'Black Currant', 1),
(1503, 'Black Currant Flavor', 1),
(1504, 'Black Dye', 1),
(1505, 'Black Haw', 1),
(1506, 'Black Pepper', 1),
(1507, 'Black Raspberry Flavor', 1),
(1508, 'Black Root', 1),
(1509, 'Black Seed', 1),
(1510, 'Black Seed (Nigella Sativa)', 1),
(1511, 'Black Walnut', 1),
(1512, 'Blackberry', 1),
(1513, 'Blackberry Flavor', 1),
(1514, 'Blackberry Leaf', 1),
(1515, 'Blackthorn,Sloe', 1),
(1516, 'Bladderwrack', 1),
(1517, 'Bladderwrack (Fucus Vesiculosus)', 1),
(1518, 'Blend 15', 1),
(1519, 'Blenoxane', 1),
(1520, 'Bleomycin', 1),
(1521, 'Bleomycin Analogues', 1),
(1522, 'Blephamide', 1),
(1523, 'Blephamide S.O.P.', 1),
(1524, 'Blessed Thistle', 1),
(1525, 'Blinx Eye Wash', 1),
(1526, 'Blistex', 1),
(1527, 'Blistex Lip Ointment', 1),
(1528, 'Blistik', 1),
(1529, 'Blistik Lip Balm', 1),
(1530, 'Blocadren', 1),
(1531, 'Blood Clotting Spray', 1),
(1532, 'Blood-Group Specific Substance', 1),
(1533, 'Bloodroot', 1),
(1534, 'Blue Algae (Haslea Ostrearia)', 1),
(1535, 'Blue D&C No.2', 1),
(1536, 'Blue Dye', 1),
(1537, 'Blue Flag', 1),
(1538, 'Blue Green Algae', 1),
(1539, 'Blue Mussel Extract', 1),
(1540, 'Blue-Green Algae', 1),
(1541, 'Blueberry', 1),
(1542, 'Blueberry Flavor', 1),
(1543, 'BODI Kleen', 1),
(1544, 'BODI LUX', 1),
(1545, 'BODI OIL', 1),
(1546, 'BODI PROTECT', 1),
(1547, 'BODI Whirl OIL', 1),
(1548, 'Bogbean', 1),
(1549, 'Bogbean (Menyanthes)', 1),
(1550, 'Boldine', 1),
(1551, 'Boldo', 1),
(1552, 'Bone Marrow', 1),
(1553, 'Bone Meal', 1),
(1554, 'Boneset', 1),
(1555, 'Boneset (Eupatorium Perfoliatum)', 1),
(1556, 'Bonine', 1),
(1557, 'Bonisara', 1),
(1558, 'Boniva', 1),
(1559, 'Boost Diabetic', 1),
(1560, 'Boost Pudding', 1),
(1561, 'BOOSTRIX', 1),
(1562, 'Bopindolol', 1),
(1563, 'Borage', 1),
(1564, 'Borage (Borago Officinalis)', 1),
(1565, 'Boric Acid', 1),
(1566, 'Bornaprine', 1),
(1567, 'Bornyl', 1),
(1568, 'Bornyl Salicylate', 1),
(1569, 'Boroglycerin Glycerite', 1),
(1570, 'Boron', 1),
(1571, 'Bortezomib', 1),
(1572, 'Bosentan', 1),
(1573, 'Botox', 1),
(1574, 'Botox Cosmetic', 1),
(1575, 'Botulinum Toxin', 1),
(1576, 'Botulinum Toxin Type A', 1),
(1577, 'Botulinum Toxin Type B', 1),
(1578, 'Botulism Antitoxin', 1),
(1579, 'Bovine Cartilage', 1),
(1580, 'Bovine Complex', 1),
(1581, 'Brahmi', 1),
(1582, 'Brain Extract', 1),
(1583, 'Brallobarbital', 1),
(1584, 'Bran', 1),
(1585, 'Branchamin', 1),
(1586, 'Brasivol', 1),
(1587, 'Brassica Oleacea', 1),
(1588, 'Bravelle', 1),
(1589, 'Brazilian Ginseng', 1),
(1590, 'Brazilian Ginseng (Suma Root)', 1),
(1591, 'Breath Relief', 1),
(1592, 'Breonesin', 1),
(1593, 'Brethaire', 1),
(1594, 'Brethine', 1),
(1595, 'Bretylium', 1),
(1596, 'Bretylol', 1),
(1597, 'Brevibloc', 1),
(1598, 'Brevital', 1),
(1599, 'Brevoxyl-4', 1),
(1600, 'Brevoxyl-4 Acne Wash Kit', 1),
(1601, 'Brevoxyl-4 Creamy Wash', 1),
(1602, 'Brevoxyl-8', 1),
(1603, 'Brevoxyl-8 Acne Wash Kit', 1),
(1604, 'Brevoxyl-8 Cleansing', 1),
(1605, 'Brevoxyl-8 Creamy Wash', 1),
(1606, 'Brewer''s Yeast', 1),
(1607, 'Bricanyl', 1),
(1608, 'Bright Beginnings Prenatal', 1),
(1609, 'Brij 30', 1),
(1610, 'Brij 35', 1),
(1611, 'Brij 58', 1),
(1612, 'Brij 78', 1),
(1613, 'Brij 99', 1),
(1614, 'Brilliant Green', 1),
(1615, 'Brimonidine', 1),
(1616, 'Brindall Berry', 1),
(1617, 'Brindall Berry (Garcinia Cambogia)', 1),
(1618, 'Brinzolamide', 1),
(1619, 'Brivudine', 1),
(1620, 'Brodimoprim', 1),
(1621, 'Brofed', 1),
(1622, 'Bromase', 1),
(1623, 'Bromase 3600', 1),
(1624, 'Bromazepam', 1),
(1625, 'Bromchlorophen', 1),
(1626, 'Bromelains', 1),
(1627, 'Bromfed', 1),
(1628, 'Bromfed PD', 1),
(1629, 'Bromfenac', 1),
(1630, 'Bromhexine', 1),
(1631, 'Bromi-Talc', 1),
(1632, 'Bromi-Talc Plus', 1),
(1633, 'Bromide', 1),
(1634, 'Bromisovalum', 1),
(1635, 'Bromochlorosalicylanilide', 1),
(1636, 'Bromocriptine', 1),
(1637, 'Bromodiphenhydramine', 1),
(1638, 'Bromoform', 1),
(1639, 'Bromopride', 1),
(1640, 'Bromosalicylic Acid', 1),
(1641, 'Bromperidol', 1),
(1642, 'Brompheniramine', 1),
(1643, 'Broncap', 1),
(1644, 'Broncholate', 1),
(1645, 'Broncodur', 1),
(1646, 'Broncomar-1', 1),
(1647, 'Broncopectol', 1),
(1648, 'Broncopectol CF', 1),
(1649, 'Broncopectol DM', 1),
(1650, 'Broncopectol NN', 1),
(1651, 'Broncopectol NNSR', 1),
(1652, 'Broncotron-D', 1),
(1653, 'Bronkaid', 1),
(1654, 'Bronko Tuss', 1),
(1655, 'Bronkometer', 1),
(1656, 'Bronopol', 1),
(1657, 'Brontex', 1),
(1658, 'Brontuss SF', 1),
(1659, 'Broparestrol', 1),
(1660, 'Brotizolam', 1),
(1661, 'Brovanexine', 1),
(1662, 'Brovex', 1),
(1663, 'Brovex CT', 1),
(1664, 'Brovex HC', 1),
(1665, 'Brovex SR', 1),
(1666, 'BroveX-D', 1),
(1667, 'Broxyquinoline', 1),
(1668, 'Brucine Sulfate', 1),
(1669, 'Bryonia Sp.', 1),
(1670, 'BSS Plus', 1),
(1671, 'Btrex', 1),
(1672, 'Bucalcide', 1),
(1673, 'Bucalsep', 1),
(1674, 'Buchu', 1),
(1675, 'Buchu,Juniper&Pot Acetate', 1),
(1676, 'Buckley''s DM', 1),
(1677, 'Buckley''s Mixture', 1),
(1678, 'Buckley''s White Rub', 1),
(1679, 'Buckthorn', 1),
(1680, 'Buckthorn (Frangula, Rhamnus Sp.)', 1),
(1681, 'Buckwheat', 1),
(1682, 'Buclizine', 1),
(1683, 'Budesonide', 1),
(1684, 'Budipine', 1),
(1685, 'Buf-Oxal 10', 1),
(1686, 'Buf-Puf', 1),
(1687, 'Bufexamac', 1),
(1688, 'Bufferin Arthritis Strength', 1),
(1689, 'Buffers', 1),
(1690, 'Buflomedil', 1),
(1691, 'Bufo', 1),
(1692, 'Bulgaricum I.B.', 1),
(1693, 'Bullfrog', 1),
(1694, 'Bullfrog for Kids', 1),
(1695, 'Bullfrog Moisturizing Body', 1),
(1696, 'Bullfrog Quik SPF18', 1),
(1697, 'Bullfrog SPF18', 1),
(1698, 'Bullfrog SPF36', 1),
(1699, 'Bullfrog Sport SPF18', 1),
(1700, 'Bumadizone', 1),
(1701, 'Bumetanide', 1),
(1702, 'Bumex', 1),
(1703, 'Bunazosin', 1),
(1704, 'Buphenyl', 1),
(1705, 'Bupivacaine', 1),
(1706, 'Bupleurum', 1),
(1707, 'Bupranolol', 1),
(1708, 'Buprenex', 1),
(1709, 'Buprenorphine', 1),
(1710, 'Bupropion', 1),
(1711, 'Bupropion Analogues', 1),
(1712, 'Burdock', 1),
(1713, 'Burdock (Articum Lappa)', 1),
(1714, 'Burn Calories', 1),
(1715, 'BURN RELIEF', 1),
(1716, 'Burn Spray', 1),
(1717, 'Burn-O-Jel', 1),
(1718, 'Burnet-Saxifrage Root', 1),
(1719, 'Burnetosaxifrage Root (Pimpinell Radix)', 1),
(1720, 'Buro-Sol', 1),
(1721, 'Buserelin', 1),
(1722, 'BuSpar', 1),
(1723, 'Buspirone', 1),
(1724, 'Busulfan', 1),
(1725, 'Busulfex', 1),
(1726, 'Butabarbital', 1),
(1727, 'Butacaine', 1),
(1728, 'Butalamine', 1),
(1729, 'Butalbital', 1),
(1730, 'Butamben', 1),
(1731, 'Butamirate', 1),
(1732, 'Butaperazine', 1),
(1733, 'Butaverine', 1),
(1734, 'Butchers Broom', 1),
(1735, 'Butenafine', 1),
(1736, 'Butesin Picrate', 1),
(1737, 'Butetamate', 1),
(1738, 'Butethal', 1),
(1739, 'Butethamine', 1),
(1740, 'Buthiazide', 1),
(1741, 'Butibel', 1),
(1742, 'Butibufen', 1),
(1743, 'Butinoline', 1),
(1744, 'Butirosin', 1),
(1745, 'Butisol', 1),
(1746, 'Butoconazole', 1),
(1747, 'Butorphanol', 1),
(1748, 'Butoxycaine', 1),
(1749, 'Butriptyline', 1),
(1750, 'Butropium', 1),
(1751, 'Butter Flavor', 1),
(1752, 'Butter Pecan Flavor', 1),
(1753, 'Butterbur', 1),
(1754, 'Butterscotch Flavor', 1),
(1755, 'Butyl Acetate', 1),
(1756, 'Butyl Alcohol', 1),
(1757, 'Butylated Hydroxyanisole', 1),
(1758, 'Butylated Hydroxytoluene (BHT)', 1),
(1759, 'Butylparaben', 1),
(1760, 'Butyric Acid', 1),
(1761, 'Butyrophenones', 1),
(1762, 'Buzepide Metiodide', 1),
(1763, 'Byetta', 1),
(1764, 'C-1000 with Rose Hips', 1),
(1765, 'C-1500 With Rose Hips', 1),
(1766, 'C-Buff', 1),
(1767, 'C-Max', 1),
(1768, 'C-Plex', 1),
(1769, 'Ca-DTPA', 1),
(1770, 'Ca-Orotate', 1),
(1771, 'Cabbage', 1),
(1772, 'Cabbage Juice', 1),
(1773, 'Cabergoline', 1),
(1774, 'Cadexomer Iodine', 1),
(1775, 'Cadralazine', 1),
(1776, 'Caduet', 1),
(1777, 'Cafcit', 1),
(1778, 'Cafedrine', 1),
(1779, 'Cafergot', 1),
(1780, 'Caffeine', 1),
(1781, 'Cajeput Oil', 1),
(1782, 'Cajuput Oil', 1),
(1783, 'Cal-Carb Forte', 1),
(1784, 'Cal-Citrate', 1),
(1785, 'Cal-G', 1),
(1786, 'Cal-Lac', 1),
(1787, 'Cal-O.D.C.', 1),
(1788, 'Cal-Stat', 1),
(1789, 'Cal-Sup 600 Plus', 1),
(1790, 'Cal-Tussic', 1),
(1791, 'Cal-Y', 1),
(1792, 'Caladryl', 1),
(1793, 'Caladryl Clear', 1),
(1794, 'Caladryl for Kids', 1),
(1795, 'Calafol Rx', 1),
(1796, 'Calamine', 1),
(1797, 'Calamus', 1),
(1798, 'Calamus (Sweet Flag)', 1),
(1799, 'Calamycin', 1),
(1800, 'Calan', 1),
(1801, 'Calan SR', 1),
(1802, 'Calcibind', 1),
(1803, 'Calcibite', 1),
(1804, 'Calcidrine', 1),
(1805, 'Calcifediol', 1),
(1806, 'Calciferol', 1),
(1807, 'CalciFol', 1),
(1808, 'Calcifolic-D', 1),
(1809, 'Calcijex', 1),
(1810, 'Calcimar', 1),
(1811, 'Calcimin-250', 1),
(1812, 'Calcimin-300', 1),
(1813, 'Calciolysin', 1),
(1814, 'Calcipotriene', 1),
(1815, 'Calcitonin', 1),
(1816, 'Calcitriol', 1),
(1817, 'Calcium', 1),
(1818, 'CALCIUM 600 + D', 1),
(1819, 'Calcium Bromolactobionate', 1),
(1820, 'Calcium Carbonate', 1),
(1821, 'Calcium Carrageenan', 1),
(1822, 'Calcium Caseinate', 1),
(1823, 'Calcium Channel Blocking Agents-Benzothiazepines', 1),
(1824, 'Calcium Channel Blocking Agents-Dihydropyridines', 1),
(1825, 'Calcium Channel Blocking Agents-Phenylalkylamines', 1),
(1826, 'Calcium Channel Blocking-Diarylaminopropylamines', 1),
(1827, 'Calcium Citrate', 1),
(1828, 'Calcium Disodium Versenate', 1),
(1829, 'Calcium Fortified Cookie', 1),
(1830, 'Calcium Hydroxide', 1),
(1831, 'Calcium Iodate', 1),
(1832, 'Calcium Lake', 1),
(1833, 'Calcium Orotate', 1),
(1834, 'Calcium Oxide', 1),
(1835, 'Calcium Phosphate', 1),
(1836, 'Calcium Plus Vitamin D-Iron', 1),
(1837, 'Calcium Polysulfide', 1),
(1838, 'Calcium Saccharate', 1),
(1839, 'Calcium Silicate', 1),
(1840, 'Calcium Stearate', 1),
(1841, 'Calcium Sulfate Hemihydrate', 1),
(1842, 'Calcium Thioglycollate', 1),
(1843, 'Calcium Thiosulfate', 1),
(1844, 'Calcium Threonate', 1),
(1845, 'Calcobutrol', 1),
(1846, 'Calderol', 1),
(1847, 'Caldesene', 1),
(1848, 'Caldiamide', 1),
(1849, 'Caldiamide Analogues', 1),
(1850, 'Caldyphen', 1),
(1851, 'Calf Spleen', 1),
(1852, 'Calfactant', 1),
(1853, 'Calgon Vestal Medicated Soap', 1),
(1854, 'Calicheamicin Analogues', 1),
(1855, 'Calicylic', 1),
(1856, 'California Poppy', 1),
(1857, 'Calmoseptine', 1),
(1858, 'Calomel', 1),
(1859, 'Calor-Aid Instant Drink', 1),
(1860, 'Calphosan', 1),
(1861, 'Calphron', 1),
(1862, 'Calteridol', 1),
(1863, 'Caltrate 600', 1),
(1864, 'Caltrate Jr.', 1),
(1865, 'Caltrate Plus', 1),
(1866, 'Caltussic', 1),
(1867, 'Calusterone', 1),
(1868, 'Calvite P&D', 1),
(1869, 'Cama', 1),
(1870, 'Cambendazole', 1),
(1871, 'Camella Sinensis', 1),
(1872, 'Camellia Oleifera Seed Oil', 1),
(1873, 'Campath', 1),
(1874, 'Camphene', 1),
(1875, 'Campho-Phenique', 1),
(1876, 'Campho-Phenique Antibiotic', 1),
(1877, 'Camphor', 1),
(1878, 'Camphor And Soap', 1),
(1879, 'Camphoric Acid', 1),
(1880, 'Campral', 1),
(1881, 'Campral Dose Pak', 1),
(1882, 'Camptosar', 1),
(1883, 'Camylofin', 1),
(1884, 'Canasa', 1),
(1885, 'Cancidas', 1),
(1886, 'Candelilla Wax', 1),
(1887, 'Candesartan', 1),
(1888, 'Candex', 1),
(1889, 'Candicidin', 1),
(1890, 'Candida Albicans Allergn Ext', 1),
(1891, 'Candida Albicans Skin Test', 1),
(1892, 'Candy Apple Flavor', 1),
(1893, 'Canine Proteins', 1),
(1894, 'Cannabidiol', 1),
(1895, 'Canola Oil', 1),
(1896, 'Canrenoic Acid', 1),
(1897, 'Canrenone', 1),
(1898, 'Cantharidin', 1),
(1899, 'Canthaxanthin', 1),
(1900, 'Cantil', 1),
(1901, 'Capastat', 1),
(1902, 'Capecitabine', 1),
(1903, 'Capelin Oil', 1),
(1904, 'Capex', 1),
(1905, 'Caphosol', 1),
(1906, 'Capital with Codeine', 1),
(1907, 'Capitrol', 1),
(1908, 'Capoten', 1),
(1909, 'Capozide', 1),
(1910, 'Capreomycin', 1),
(1911, 'Caproic Acid', 1),
(1912, 'Capromab', 1),
(1913, 'Caprylic/Capric Triglyceride', 1),
(1914, 'Capryloyl Glycine', 1),
(1915, 'Capsagel', 1),
(1916, 'Capsagel Extra Strength', 1),
(1917, 'Capsagel Maximum Strength', 1),
(1918, 'Capsaicin', 1),
(1919, 'Capsicum', 1),
(1920, 'Capsin', 1),
(1921, 'Captodiamine', 1),
(1922, 'Captopril', 1),
(1923, 'Capzasin-HP', 1),
(1924, 'Carac', 1),
(1925, 'CaraCream', 1),
(1926, 'Carafate', 1),
(1927, 'Caramel', 1),
(1928, 'Caramel Flavor', 1),
(1929, 'Caramiphen', 1),
(1930, 'Caraway', 1),
(1931, 'Caraway Oil', 1),
(1932, 'Carazolol', 1),
(1933, 'Carba-XP', 1),
(1934, 'Carbachol', 1),
(1935, 'Carbacrylamine Resins', 1),
(1936, 'Carbamates', 1),
(1937, 'Carbamazepine', 1),
(1938, 'Carbamazepine Derivatives', 1),
(1939, 'Carbamide Peroxide', 1),
(1940, 'Carbapenem', 1),
(1941, 'Carbaphen 12', 1),
(1942, 'Carbaphen 12 Ped', 1),
(1943, 'Carbarsone', 1),
(1944, 'Carbaryl', 1),
(1945, 'Carbaspirin Calcium', 1),
(1946, 'Carbatrol', 1),
(1947, 'Carbatuss', 1),
(1948, 'CARBATUSS-12', 1),
(1949, 'Carbatuss-CL', 1),
(1950, 'Carbazochrome', 1),
(1951, 'Carbazochrome Salicylate', 1),
(1952, 'Carbenicillin', 1),
(1953, 'Carbenoxolone', 1),
(1954, 'Carbetapentane', 1),
(1955, 'Carbetocin', 1),
(1956, 'Carbidopa', 1);
INSERT INTO `allergies` (`ALLERGIES_CODE`, `ALLERGRY_DESCRIPTION`, `ACTIVE`) VALUES
(1957, 'Carbidopa/Levodopa', 1),
(1958, 'Carbimazole', 1),
(1959, 'Carbimazole And Derivatives', 1),
(1960, 'Carbinoxamine', 1),
(1961, 'Carbitol', 1),
(1962, 'Carbo Grabber', 1),
(1963, 'Carbo Vegetabilis', 1),
(1964, 'Carbocaine', 1),
(1965, 'Carbocaine W/Neo-Cobefrin', 1),
(1966, 'Carbocysteine', 1),
(1967, 'Carbomer', 1),
(1968, 'Carbomer 940', 1),
(1969, 'Carbomer 974', 1),
(1970, 'Carbomer 980', 1),
(1971, 'Carbon Monoxide', 1),
(1972, 'Carbon Tetrachloride', 1),
(1973, 'Carbonal', 1),
(1974, 'Carbonal 971P', 1),
(1975, 'Carbonic Anhydrase Inhibitors', 1),
(1976, 'Carbopex', 1),
(1977, 'Carboplatin', 1),
(1978, 'Carboprost', 1),
(1979, 'Carboxymethylcellulose', 1),
(1980, 'Carboxymethylcellulose Calcium', 1),
(1981, 'Carboxypolyethylene', 1),
(1982, 'Carbromal', 1),
(1983, 'Carbutamide', 1),
(1984, 'Carbuterol', 1),
(1985, 'Cardamom', 1),
(1986, 'Cardene', 1),
(1987, 'Cardene IV', 1),
(1988, 'Cardene SR', 1),
(1989, 'Cardia', 1),
(1990, 'Cardio-Green', 1),
(1991, 'Cardioquin', 1),
(1992, 'Cardiotek', 1),
(1993, 'Cardiotek Rx', 1),
(1994, 'CardioVid PLUS', 1),
(1995, 'Cardizem', 1),
(1996, 'Cardizem CD', 1),
(1997, 'Cardizem LA', 1),
(1998, 'Cardizem SR', 1),
(1999, 'Cardura', 1),
(2000, 'Cardura XL', 1),
(2001, 'Care-Creme', 1),
(2002, 'Carglumic Acid', 1),
(2003, 'Carisoprodol', 1),
(2004, 'Carlesta', 1),
(2005, 'Carmex', 1),
(2006, 'Carmine', 1),
(2007, 'Carmofur', 1),
(2008, 'CARMOL', 1),
(2009, 'CARMOL 10', 1),
(2010, 'CARMOL 20', 1),
(2011, 'CARMOL 40', 1),
(2012, 'CARMOL HC', 1),
(2013, 'CARMOL Scalp Treatment', 1),
(2014, 'Carmustine', 1),
(2015, 'Carnauba Wax', 1),
(2016, 'Carni Q-Gel Forte', 1),
(2017, 'Carni-Vite', 1),
(2018, 'Carnitine', 1),
(2019, 'Carnitine Analogues', 1),
(2020, 'Carnitor', 1),
(2021, 'Carnosine', 1),
(2022, 'Carnosine (L-Carnosine)', 1),
(2023, 'Carob', 1),
(2024, 'Caroid Laxative', 1),
(2025, 'Carotenoid Dye E160', 1),
(2026, 'Carphenazine', 1),
(2027, 'Carpipramine', 1),
(2028, 'Carprofen', 1),
(2029, 'Carqueja', 1),
(2030, 'CarraFoam', 1),
(2031, 'CarraFree Odor Eliminator', 1),
(2032, 'Carrageenan', 1),
(2033, 'Carraklenz', 1),
(2034, 'Carrasyn V', 1),
(2035, 'Carrasyn V Wound Dressing', 1),
(2036, 'CarraVite', 1),
(2037, 'CarraWash', 1),
(2038, 'Carrington', 1),
(2039, 'Carrington Moist Barrier-Zinc', 1),
(2040, 'Carrot', 1),
(2041, 'Carteolol', 1),
(2042, 'Carticel', 1),
(2043, 'Cartilage', 1),
(2044, 'Cartrol', 1),
(2045, 'Carumonam', 1),
(2046, 'Carvacrol', 1),
(2047, 'Carvedilol', 1),
(2048, 'Carzenide', 1),
(2049, 'Casanthranol', 1),
(2050, 'Cascara Sagrada', 1),
(2051, 'Casec', 1),
(2052, 'Casein', 1),
(2053, 'Casodex', 1),
(2054, 'Caspofungin', 1),
(2055, 'Cassia Angustifolia Seed Polys', 1),
(2056, 'Castellani Paint Modified', 1),
(2057, 'Castor Oil', 1),
(2058, 'Castor Oil And Derivatives', 1),
(2059, 'Cat Hair Std Extract', 1),
(2060, 'Cat Pelt Std Extract', 1),
(2061, 'Cat''s Claw', 1),
(2062, 'Cat''s Claw (Uncaria Tomentosa)', 1),
(2063, 'Cat/Feline Product Derivatives', 1),
(2064, 'Cataflam', 1),
(2065, 'Catalase', 1),
(2066, 'Catapres', 1),
(2067, 'Catapres-TTS-1', 1),
(2068, 'Catapres-TTS-2', 1),
(2069, 'Catapres-TTS-3', 1),
(2070, 'Catechin', 1),
(2071, 'Catechu', 1),
(2072, 'Cathflo Activase', 1),
(2073, 'Cathine', 1),
(2074, 'Catnip', 1),
(2075, 'Catuaba', 1),
(2076, 'Caverject', 1),
(2077, 'Caverject Impulse', 1),
(2078, 'Cayenne', 1),
(2079, 'CCP', 1),
(2080, 'CCP Caffeine Free', 1),
(2081, 'Ceclor', 1),
(2082, 'Ceclor CD', 1),
(2083, 'Cecon', 1),
(2084, 'Cedar Leaf (Thuja)', 1),
(2085, 'Cedar Leaf Oil', 1),
(2086, 'Cedar Leaf(Thuja occidentalis)', 1),
(2087, 'Cedarwood', 1),
(2088, 'Cedarwood (Juniperus)', 1),
(2089, 'Cedax', 1),
(2090, 'CeeNU', 1),
(2091, 'Cefaclor', 1),
(2092, 'Cefadroxil', 1),
(2093, 'Cefadyl', 1),
(2094, 'Cefamandole', 1),
(2095, 'Cefatrizine', 1),
(2096, 'Cefazedone', 1),
(2097, 'Cefazolin', 1),
(2098, 'Cefbuperazone', 1),
(2099, 'Cefdinir', 1),
(2100, 'Cefditoren', 1),
(2101, 'Cefepime', 1),
(2102, 'Cefetamet', 1),
(2103, 'Cefixime', 1),
(2104, 'Cefizox', 1),
(2105, 'Cefmenoxime', 1),
(2106, 'Cefmetazole', 1),
(2107, 'Cefminox', 1),
(2108, 'Cefobid', 1),
(2109, 'Cefodizime', 1),
(2110, 'Cefonicid', 1),
(2111, 'Cefoperazone', 1),
(2112, 'Ceforanide', 1),
(2113, 'Cefotan', 1),
(2114, 'Cefotaxime', 1),
(2115, 'Cefotetan', 1),
(2116, 'Cefotiam', 1),
(2117, 'Cefoxitin', 1),
(2118, 'Cefpiramide', 1),
(2119, 'Cefpirome', 1),
(2120, 'Cefpodoxime', 1),
(2121, 'Cefprozil', 1),
(2122, 'Cefroxadine', 1),
(2123, 'Cefsulodin', 1),
(2124, 'Ceftazidime', 1),
(2125, 'Cefteram', 1),
(2126, 'Ceftezole', 1),
(2127, 'Ceftibuten', 1),
(2128, 'Ceftin', 1),
(2129, 'Ceftizoxime', 1),
(2130, 'Ceftriaxone', 1),
(2131, 'Cefuroxime', 1),
(2132, 'Cefzil', 1),
(2133, 'Celebrex', 1),
(2134, 'Celecoxib', 1),
(2135, 'Celery', 1),
(2136, 'Celery (Apium Graveolens) (Umbelliferae)', 1),
(2137, 'Celestone', 1),
(2138, 'Celestone Soluspan', 1),
(2139, 'Celexa', 1),
(2140, 'Celiprolol', 1),
(2141, 'Cell Wall Skeleton', 1),
(2142, 'Cellacefate', 1),
(2143, 'CellCept', 1),
(2144, 'CellCept Intravenous', 1),
(2145, 'Cellugel', 1),
(2146, 'Cellulase', 1),
(2147, 'Cellulose', 1),
(2148, 'Cellulose 90', 1),
(2149, 'Cellulose Analogues', 1),
(2150, 'Cellulose HD 90', 1),
(2151, 'Cellulose Sodium Phosphate', 1),
(2152, 'Cellulose,Oxidized', 1),
(2153, 'Celluvisc', 1),
(2154, 'Celontin', 1),
(2155, 'Cemill + Bioflavonoids', 1),
(2156, 'Cenestin', 1),
(2157, 'Cenogen Ultra', 1),
(2158, 'Cenogen-OB', 1),
(2159, 'Cenolate', 1),
(2160, 'Centaury', 1),
(2161, 'Centella Asiaticoside', 1),
(2162, 'Central Vite', 1),
(2163, 'Centrum', 1),
(2164, 'Centrum Heart', 1),
(2165, 'Centrum Performance', 1),
(2166, 'Century', 1),
(2167, 'Ceo-Two', 1),
(2168, 'Cepacol', 1),
(2169, 'Cepacol Maximum Strength', 1),
(2170, 'Cepacol Mouthwash/Gargle', 1),
(2171, 'Cepacol Sore Throat', 1),
(2172, 'Cepacol Sore Throat Spray', 1),
(2173, 'Cepacol Viractin', 1),
(2174, 'Cepacol Zinc Lozenges', 1),
(2175, 'Cepastat', 1),
(2176, 'Cephacetrile', 1),
(2177, 'Cephaeline', 1),
(2178, 'Cephalexin', 1),
(2179, 'Cephaloglycin', 1),
(2180, 'Cephaloridine', 1),
(2181, 'Cephalosporins', 1),
(2182, 'Cephalothin', 1),
(2183, 'Cephapirin', 1),
(2184, 'Cephradine', 1),
(2185, 'Cephulac', 1),
(2186, 'Ceptaz', 1),
(2187, 'CeraLyte 50', 1),
(2188, 'CeraLyte 70', 1),
(2189, 'CeraLyte 90', 1),
(2190, 'Ceralyte-70', 1),
(2191, 'Ceramide', 1),
(2192, 'CeraSport Concentrate', 1),
(2193, 'CeraVe', 1),
(2194, 'Cerebrolysin', 1),
(2195, 'Cerebyx', 1),
(2196, 'Ceredase', 1),
(2197, 'Cerefolin', 1),
(2198, 'Cerefolin NAC', 1),
(2199, 'Ceresin', 1),
(2200, 'Cerezyme', 1),
(2201, 'Cerivastatin', 1),
(2202, 'Cernevit', 1),
(2203, 'Cerose-DM', 1),
(2204, 'Cerous Nitrate', 1),
(2205, 'Certoparin', 1),
(2206, 'CERTUSS', 1),
(2207, 'CERTUSS-D', 1),
(2208, 'Cerubidine', 1),
(2209, 'Ceruletide', 1),
(2210, 'Cervical Cap', 1),
(2211, 'Cervidil', 1),
(2212, 'Cesamet', 1),
(2213, 'Cetamolol', 1),
(2214, 'Cetaphil', 1),
(2215, 'Cetaphil Antibacterial', 1),
(2216, 'Cetaphil Gentle Cleanser', 1),
(2217, 'Cetaphil Hand', 1),
(2218, 'Cetaphil Moisturizing', 1),
(2219, 'Ceteareth-12', 1),
(2220, 'Ceteareth-15', 1),
(2221, 'Ceteareth-20', 1),
(2222, 'Ceteareth-30', 1),
(2223, 'Cetearyl Ethylhexanoate', 1),
(2224, 'Ceteth-10', 1),
(2225, 'Cethexonium', 1),
(2226, 'Cetiedil', 1),
(2227, 'Cetirizine', 1),
(2228, 'Cetomacrogol 1000', 1),
(2229, 'Cetomacrogol Cream', 1),
(2230, 'Cetomacrogol Emulsifier', 1),
(2231, 'Cetostearyl Alcohol', 1),
(2232, 'Cetostearyl Glucoside', 1),
(2233, 'Cetostearyl Octanoate', 1),
(2234, 'Cetraxate', 1),
(2235, 'Cetrimide', 1),
(2236, 'Cetrimonium', 1),
(2237, 'Cetrorelix', 1),
(2238, 'Cetrotide', 1),
(2239, 'Cetuximab', 1),
(2240, 'Cetuximab Analogues', 1),
(2241, 'Cetyl Acetate', 1),
(2242, 'Cetyl Alcohol', 1),
(2243, 'Cetyl Esthers', 1),
(2244, 'Cetyl Myristoleate', 1),
(2245, 'Cetyl Palmitate', 1),
(2246, 'Cevalin', 1),
(2247, 'Cevimeline', 1),
(2248, 'Chamomile Flower', 1),
(2249, 'Chamomile Flowers', 1),
(2250, 'Chantix', 1),
(2251, 'Chap-Et', 1),
(2252, 'CharcoAid', 1),
(2253, 'CharcoAid 2000', 1),
(2254, 'Charcoal Plus', 1),
(2255, 'Charcoal Plus DS', 1),
(2256, 'Charcoal, Animal Based', 1),
(2257, 'CharcoCaps', 1),
(2258, 'CharcoCaps A-D', 1),
(2259, 'Charcodote', 1),
(2260, 'Chaste Tree', 1),
(2261, 'Chaulmoogra', 1),
(2262, 'Cheetah', 1),
(2263, 'Chem-Hist D', 1),
(2264, 'Chem-Sol', 1),
(2265, 'Chem-Tuss N', 1),
(2266, 'Chem-Tuss Ne', 1),
(2267, 'Chemet', 1),
(2268, 'Chenodiol', 1),
(2269, 'Cheracol Cough/Sore Throat', 1),
(2270, 'Cherokee Rose', 1),
(2271, 'Cherry Creme Flavor', 1),
(2272, 'Cherry Flavor', 1),
(2273, 'Cherry Ice Lipbalm', 1),
(2274, 'Chest Congestion Relief PE', 1),
(2275, 'Chew Q', 1),
(2276, 'Chewable Amino Acids', 1),
(2277, 'Chewable Calcium', 1),
(2278, 'Chewable Prenatal', 1),
(2279, 'Chibroxin', 1),
(2280, 'Chicken Derived', 1),
(2281, 'Chicken Embryo Derived', 1),
(2282, 'Chicken Flavor', 1),
(2283, 'Chickweed', 1),
(2284, 'Chicory', 1),
(2285, 'Chigg-Away', 1),
(2286, 'Chiggerex', 1),
(2287, 'Chiggertox', 1),
(2288, 'Child Tylenol Plus Cold-Allerg', 1),
(2289, 'Child''s Dimetapp Nighttime Flu', 1),
(2290, 'Child''s Tylenol Cough& R.Nose', 1),
(2291, 'Child''s Tylenol Plus Cough&ST', 1),
(2292, 'Children''s Advil Allergy Sinus', 1),
(2293, 'Children''s Benadryl Allergy', 1),
(2294, 'Children''s Benadryl Allg-Cold', 1),
(2295, 'Children''s Formula', 1),
(2296, 'Children''s Motrin', 1),
(2297, 'Children''s Motrin Cold', 1),
(2298, 'Children''s Motrin Jr Strength', 1),
(2299, 'Children''s Mucinex', 1),
(2300, 'Children''s Mylanta', 1),
(2301, 'Children''s Nasalcrom', 1),
(2302, 'Children''s NyQuil Cold/Cough', 1),
(2303, 'Children''s Sudafed', 1),
(2304, 'Children''s Sudafed Cold-Cough', 1),
(2305, 'Children''s Tylenol', 1),
(2306, 'Children''s Tylenol Allergy-D', 1),
(2307, 'Children''s Tylenol Cold', 1),
(2308, 'Children''s Tylenol Cold-Cough', 1),
(2309, 'Children''s Tylenol Flu', 1),
(2310, 'Children''s Tylenol Meltaways', 1),
(2311, 'Children''s Tylenol Plus', 1),
(2312, 'Children''s Tylenol Plus Cold', 1),
(2313, 'Children''s Tylenol Plus Flu', 1),
(2314, 'Children''s Tylenol Sinus', 1),
(2315, 'Childrens Tylenol Plus MS Cold', 1),
(2316, 'Chinese Boxthorn', 1),
(2317, 'Chinese Licorice', 1),
(2318, 'Chinese Peony', 1),
(2319, 'Chinese Rubber Tree', 1),
(2320, 'Chiniofon', 1),
(2321, 'Chirocaine (PF)', 1),
(2322, 'Chitosan', 1),
(2323, 'Chitosan-Plus', 1),
(2324, 'Chlo-Amine', 1),
(2325, 'Chlophedianol', 1),
(2326, 'Chlor-3', 1),
(2327, 'Chlor-Trimeton', 1),
(2328, 'Chlor-Trimeton Allergy Repetab', 1),
(2329, 'Chlor-Trimeton Non-Drowsy', 1),
(2330, 'Chlor-Trimeton Repetabs', 1),
(2331, 'Chloral Hydrate', 1),
(2332, 'Chloral Hydrate Analogues', 1),
(2333, 'Chloralose', 1),
(2334, 'Chlorambucil', 1),
(2335, 'Chloramine T', 1),
(2336, 'Chloramphenicol', 1),
(2337, 'Chloraseptic', 1),
(2338, 'Chloraseptic Sore Throat', 1),
(2339, 'Chlorbutol', 1),
(2340, 'Chlorcyclizine', 1),
(2341, 'Chlordiazepoxide', 1),
(2342, 'Chlorella Algae', 1),
(2343, 'Chlorella Algae (Green, Species Unspec.)', 1),
(2344, 'Chloresium', 1),
(2345, 'Chloresium Plain', 1),
(2346, 'Chlorfenethazine', 1),
(2347, 'Chlorhexidine', 1),
(2348, 'Chlorinated Lime', 1),
(2349, 'Chlormadinone', 1),
(2350, 'Chlormerodrin', 1),
(2351, 'Chlormethiazole', 1),
(2352, 'Chlormethiazole Analogues', 1),
(2353, 'Chlormezanone', 1),
(2354, 'Chlorobutanol', 1),
(2355, 'Chlorobutanol And Deriv', 1),
(2356, 'Chlorocresol', 1),
(2357, 'Chloroform', 1),
(2358, 'Chloromycetin', 1),
(2359, 'Chlorophenothane', 1),
(2360, 'Chlorophyllin', 1),
(2361, 'Chlorophyllin (Chlorophyll) Analogues', 1),
(2362, 'Chloroprocaine', 1),
(2363, 'Chloropyramine', 1),
(2364, 'Chloroquine', 1),
(2365, 'Chlorothen', 1),
(2366, 'Chlorotheophylline', 1),
(2367, 'Chlorothiazide', 1),
(2368, 'Chlorothymol', 1),
(2369, 'Chlorotrianisene', 1),
(2370, 'Chloroxine', 1),
(2371, 'Chloroxylenol', 1),
(2372, 'Chlorphenesin', 1),
(2373, 'Chlorphenindione', 1),
(2374, 'Chlorpheniramine', 1),
(2375, 'Chlorphenoxamine', 1),
(2376, 'Chlorphentermine', 1),
(2377, 'Chlorproethazine', 1),
(2378, 'Chlorproguanil', 1),
(2379, 'Chlorpromazine', 1),
(2380, 'Chlorpropamide', 1),
(2381, 'Chlorprothixene', 1),
(2382, 'Chlorquinaldol', 1),
(2383, 'Chlortetracycline', 1),
(2384, 'Chlorthalidone', 1),
(2385, 'Chlorthenoxazine', 1),
(2386, 'Chlorzoxazone', 1),
(2387, 'ChocoBase', 1),
(2388, 'Cholebrine', 1),
(2389, 'Cholecalciferol', 1),
(2390, 'Cholecystokinin', 1),
(2391, 'Choledyl SA', 1),
(2392, 'Cholera Vaccine', 1),
(2393, 'Cholest Off', 1),
(2394, 'Cholesterol And Deriv', 1),
(2395, 'Cholesterol Fighter', 1),
(2396, 'Cholestryl Benzoate', 1),
(2397, 'Cholestyramine', 1),
(2398, 'Cholic Acid', 1),
(2399, 'Choline', 1),
(2400, 'Choline Alfoscerate', 1),
(2401, 'Choline Analogues', 1),
(2402, 'Cholinesterase Inhibitor(Carbamate)', 1),
(2403, 'Cholografin Meglumine', 1),
(2404, 'Choloxin', 1),
(2405, 'Chondroitin And Derivatives', 1),
(2406, 'Chondroitin Sulfate A', 1),
(2407, 'Chondroitin Sulfate Complex', 1),
(2408, 'Chondroitinase', 1),
(2409, 'Chorex-10', 1),
(2410, 'Chromacaps', 1),
(2411, 'Chromagen', 1),
(2412, 'Chromagen (w/Sumalate)', 1),
(2413, 'Chromagen FA', 1),
(2414, 'Chromagen FA (w/Sumalate)', 1),
(2415, 'Chromagen FORTE', 1),
(2416, 'Chromagen Forte (w/Sumalate)', 1),
(2417, 'Chromagen-OB', 1),
(2418, 'Chrome Alum', 1),
(2419, 'Chromelin', 1),
(2420, 'Chromic Acid', 1),
(2421, 'Chromimin', 1),
(2422, 'Chromium', 1),
(2423, 'Chromium And Derivatives', 1),
(2424, 'Chromium Nicotinate', 1),
(2425, 'Chromium Oxide Green', 1),
(2426, 'Chromium Picolinate KLB6', 1),
(2427, 'Chromium-51', 1),
(2428, 'Chromocarb', 1),
(2429, 'Chromonar', 1),
(2430, 'Chronulac', 1),
(2431, 'Chrysarobin', 1),
(2432, 'Chrysin', 1),
(2433, 'Chymodiactin', 1),
(2434, 'Chymopapain', 1),
(2435, 'Chymotripsinogen', 1),
(2436, 'Chymotrypsin', 1),
(2437, 'CIALIS', 1),
(2438, 'Ciclesonide', 1),
(2439, 'Cicletanine', 1),
(2440, 'Cicliomennol', 1),
(2441, 'Ciclonium', 1),
(2442, 'Ciclopirox', 1),
(2443, 'Cicloxilic Acid', 1),
(2444, 'Cider Vinegar', 1),
(2445, 'Cidex Plus 28 Day', 1),
(2446, 'Cidofovir', 1),
(2447, 'Cifenline', 1),
(2448, 'Cilastatin', 1),
(2449, 'Cilazapril', 1),
(2450, 'Cilnidipine', 1),
(2451, 'Cilostazol', 1),
(2452, 'Ciloxan', 1),
(2453, 'Cimetidine', 1),
(2454, 'Cimetropium', 1),
(2455, 'Cin-Quin', 1),
(2456, 'Cinacalcet', 1),
(2457, 'Cinacalcet Derivatives', 1),
(2458, 'Cinametic Acid', 1),
(2459, 'Cinchona Bark', 1),
(2460, 'Cinchona Succirubra', 1),
(2461, 'Cinchonine', 1),
(2462, 'Cinepazide', 1),
(2463, 'Cinepazide Analogues', 1),
(2464, 'Cinitapride', 1),
(2465, 'Cinnamate Derivatives', 1),
(2466, 'Cinnamaverine', 1),
(2467, 'Cinnamedrine', 1),
(2468, 'Cinnamic Acid (Tolu Sy)', 1),
(2469, 'Cinnamon', 1),
(2470, 'Cinnamon Analogues', 1),
(2471, 'Cinnamon Oil', 1),
(2472, 'Cinnarizine', 1),
(2473, 'Cinobac', 1),
(2474, 'Cinoxacin', 1),
(2475, 'Cinoxate', 1),
(2476, 'Cipro', 1),
(2477, 'Cipro HC', 1),
(2478, 'Cipro XR', 1),
(2479, 'Ciprocinonide', 1),
(2480, 'CIPRODEX', 1),
(2481, 'Ciprofibrate', 1),
(2482, 'Ciprofloxacin', 1),
(2483, 'Cisapride', 1),
(2484, 'Cisatracurium', 1),
(2485, 'Cisplatin', 1),
(2486, 'Cit-Phos-Dex-Aden Solution', 1),
(2487, 'Citalopram', 1),
(2488, 'Citalopram Analogues', 1),
(2489, 'Citanest', 1),
(2490, 'Citanest Forte', 1),
(2491, 'Citicoline', 1),
(2492, 'Citiolone', 1),
(2493, 'Citracal', 1),
(2494, 'Citracal + D', 1),
(2495, 'Citracal Plus', 1),
(2496, 'Citracal Prenatal + DHA', 1),
(2497, 'Citracal Prenatal Rx', 1),
(2498, 'Citracal Ultradense', 1),
(2499, 'Citraderm', 1),
(2500, 'Citrate', 1),
(2501, 'Citrate Ester', 1),
(2502, 'Citrate-Phos-Dex Solution', 1),
(2503, 'Citric Acid', 1),
(2504, 'Citrimax', 1),
(2505, 'Citrimax 500 Plus', 1),
(2506, 'Citrocarbonate', 1),
(2507, 'Citrolith', 1),
(2508, 'Citronella Oil', 1),
(2509, 'Citrucel', 1),
(2510, 'Citrucel Fiber Laxative', 1),
(2511, 'Citrucel Fiber Shake', 1),
(2512, 'Citrudex', 1),
(2513, 'Citrulline', 1),
(2514, 'Citrulline (L-Citrulline)', 1),
(2515, 'Citrus And Derivatives', 1),
(2516, 'Citrus Berry Flavor', 1),
(2517, 'Citrus Derived', 1),
(2518, 'Citrus Flavor', 1),
(2519, 'Cladribine', 1),
(2520, 'Claforan', 1),
(2521, 'Claforan Viaflex Plus', 1),
(2522, 'Clarinex', 1),
(2523, 'Clarinex-D 12 HOUR', 1),
(2524, 'Clarinex-D 24 HOUR', 1),
(2525, 'Claripel', 1),
(2526, 'Clarithromycin', 1),
(2527, 'Claritin', 1),
(2528, 'Claritin RediTabs', 1),
(2529, 'Claritin-D 12 Hour', 1),
(2530, 'Claritin-D 24 Hour', 1),
(2531, 'Clary Sage', 1),
(2532, 'Clavulanic Acid', 1),
(2533, 'CLEAR COUGH PM', 1),
(2534, 'Clear Eyes ACR', 1),
(2535, 'Clear Total Lice', 1),
(2536, 'Clearasil Maximum Strength', 1),
(2537, 'Clebopride', 1),
(2538, 'Clebopride Analogues', 1),
(2539, 'Cleeravue-M Convenience Kit', 1),
(2540, 'Clemastine', 1),
(2541, 'Clemizole', 1),
(2542, 'Clemizole Analogues', 1),
(2543, 'Clenbuterol', 1),
(2544, 'Cleocin', 1),
(2545, 'Cleocin T', 1),
(2546, 'Clidinium', 1),
(2547, 'Climara', 1),
(2548, 'Climara Pro', 1),
(2549, 'Climbazole', 1),
(2550, 'Clinac BPO', 1),
(2551, 'Clindagel', 1),
(2552, 'Clindamycin', 1),
(2553, 'Clindesse', 1),
(2554, 'Clinimix 2.75/D5 Sulfite Free', 1),
(2555, 'Clinimix 4.25/D5 Sulfite Free', 1),
(2556, 'Clinimix 5/D15 Sulfite Free', 1),
(2557, 'Clinimix 5/D20 Sulfite Free', 1),
(2558, 'Clinimix 5/D25 Sulfite Free', 1),
(2559, 'Clinimix E 2.75/D10 SulfitFree', 1),
(2560, 'Clinimix E 2.75/D5 SulfiteFree', 1),
(2561, 'Clinimix E 4.25/D10 SulfitFree', 1),
(2562, 'Clinimix E 4.25/D5 SulfiteFree', 1),
(2563, 'Clinimix E 5/D15 Sulfite Free', 1),
(2564, 'Clinimix E 5/D20 Sulfite Free', 1),
(2565, 'Clinimix E 5/D25 Sulfite Free', 1),
(2566, 'Clinimix E 5/D35 Sulfite Free', 1),
(2567, 'Clinisol SF 15%', 1),
(2568, 'Clinoril', 1),
(2569, 'Clioquinol', 1),
(2570, 'Clivers', 1),
(2571, 'Clivers (Galium Aparine)', 1),
(2572, 'Clobazam', 1),
(2573, 'Clobenzorex', 1),
(2574, 'Clobetasol', 1),
(2575, 'Clobetasone', 1),
(2576, 'CLOBEX', 1),
(2577, 'Clobutinol', 1),
(2578, 'Clocinizine', 1),
(2579, 'Cloconazole', 1),
(2580, 'Clocortolone', 1),
(2581, 'Cloderm', 1),
(2582, 'Clodronic Acid', 1),
(2583, 'Clofarabine', 1),
(2584, 'Clofazimine', 1),
(2585, 'Clofexamide', 1),
(2586, 'Clofezone', 1),
(2587, 'Clofibrate', 1),
(2588, 'Clofibrate Aluminum', 1),
(2589, 'Clofibride', 1),
(2590, 'Clofoctol', 1),
(2591, 'CLOLAR', 1),
(2592, 'Clomid', 1),
(2593, 'Clomiphene', 1),
(2594, 'Clomipramine', 1),
(2595, 'Clomocycline', 1),
(2596, 'Clonazepam', 1),
(2597, 'Clonidine', 1),
(2598, 'Clonixin', 1),
(2599, 'Clopamide', 1),
(2600, 'Clopidogrel', 1),
(2601, 'Cloponone', 1),
(2602, 'Cloprednol', 1),
(2603, 'Clorazepic Acid', 1),
(2604, 'Clorfed', 1),
(2605, 'Clorophene', 1),
(2606, 'Clorpactin', 1),
(2607, 'Clorpactin Xcb', 1),
(2608, 'Clorprenaline', 1),
(2609, 'Clortermine', 1),
(2610, 'Clostebol', 1),
(2611, 'Clostridium tet(Hvrd st) Deriv', 1),
(2612, 'Clothiapine', 1),
(2613, 'Clotiazepam', 1),
(2614, 'Clotrimazole', 1),
(2615, 'Clove', 1),
(2616, 'Clove Oil', 1),
(2617, 'Cloxacillin', 1),
(2618, 'Cloxazolam', 1),
(2619, 'Cloxyquin', 1),
(2620, 'Clozapine', 1),
(2621, 'Clozaril', 1),
(2622, 'Clubmoss', 1),
(2623, 'CMC 7-HF', 1),
(2624, 'Co Advil', 1),
(2625, 'Co Q-10', 1),
(2626, 'Co Q-10 (with Vit E)', 1),
(2627, 'Co Q-10-Vitamin E-Fish Oil', 1),
(2628, 'CO-CO Bear Calcium', 1),
(2629, 'Co-Pyronil 2', 1),
(2630, 'Coal Tar', 1),
(2631, 'Cobalamin And Derivatives', 1),
(2632, 'Cobalt', 1),
(2633, 'Cobamamide', 1),
(2634, 'Cobra Venom', 1),
(2635, 'Cocaine', 1),
(2636, 'Cocamide DEA', 1),
(2637, 'Cocamidopropyl Analogues', 1),
(2638, 'Cocamidopropyl Betaine', 1),
(2639, 'Coccidioidin Skin Test', 1),
(2640, 'Cochineal', 1),
(2641, 'Cocillana', 1),
(2642, 'Cocoa', 1),
(2643, 'Cocoa Butter', 1),
(2644, 'Cocoa Flavor', 1),
(2645, 'Cocoamido Ether Sulfate Cmplx', 1),
(2646, 'Cocoamidopropylbetaine', 1),
(2647, 'Cocoamine Oxide', 1),
(2648, 'Cocoamphocarboxyglycinate, Disodium', 1),
(2649, 'Cocoglycerides', 1),
(2650, 'Coconut', 1),
(2651, 'Coconut Custard Flavor', 1),
(2652, 'Coconut Fatty Acid Diethanolam', 1),
(2653, 'Coconut Flavor', 1),
(2654, 'Coconut Milk', 1),
(2655, 'Coconut Oil', 1),
(2656, 'Cod Liver Oil', 1),
(2657, 'Cod Liver Oil Conc with Vit C', 1),
(2658, 'Codeine', 1),
(2659, 'Codeine Antitussive Cough', 1),
(2660, 'Codeine Phosphate Soluble', 1),
(2661, 'CodiCLEAR DH', 1),
(2662, 'Codimal DH', 1),
(2663, 'Codimal DM', 1),
(2664, 'Codimal PH', 1),
(2665, 'Coenzyme A', 1),
(2666, 'Coffea Tosta', 1),
(2667, 'Coffee Break', 1),
(2668, 'Coffee Carbon', 1),
(2669, 'Coffee Flavor', 1),
(2670, 'Cogentin', 1),
(2671, 'Cognex', 1),
(2672, 'Cola Flavor', 1),
(2673, 'Cola Syrup', 1),
(2674, 'Colace', 1),
(2675, 'Colace Microenema', 1),
(2676, 'Colazal', 1),
(2677, 'Colchicine', 1),
(2678, 'Colchicine Derivatives', 1),
(2679, 'Cold Cream', 1),
(2680, 'Cold Relief', 1),
(2681, 'Cold Sore', 1),
(2682, 'Cold& Allergy', 1),
(2683, 'Cold& Hot Extra Strength', 1),
(2684, 'Cold-Eeze', 1),
(2685, 'Colesevelam', 1),
(2686, 'Colestid', 1),
(2687, 'Colestid Flavored', 1),
(2688, 'Colestipol', 1),
(2689, 'Coleus', 1),
(2690, 'Coleus (Coleus Forskholii)', 1),
(2691, 'Colfosceril Palmitate', 1),
(2692, 'Colistimethate Sodium', 1),
(2693, 'Colistin', 1),
(2694, 'Colistin Methanesulfonate', 1),
(2695, 'Collagen', 1),
(2696, 'Collagen Implant,Ophthalmic', 1),
(2697, 'Collagen Plus Vitamin C', 1),
(2698, 'Collagen Type II', 1),
(2699, 'Collagen, Hydrolyzed', 1),
(2700, 'Collagen,Bovine', 1),
(2701, 'Collagenase', 1),
(2702, 'Collodion', 1),
(2703, 'Colloidal Bismuth Subcitrate', 1),
(2704, 'Colloidal Oatmeal', 1),
(2705, 'Colombo', 1),
(2706, 'Colon-Help Nutrition', 1),
(2707, 'Colony Stimulating Factors', 1),
(2708, 'Color Bar Schirmer Tear Test', 1),
(2709, 'Colrex Compound', 1),
(2710, 'Coltsfoot', 1),
(2711, 'Coly-Mycin M', 1),
(2712, 'Coly-Mycin M Parenteral', 1),
(2713, 'Coly-Mycin S', 1),
(2714, 'Colytrol Pediatric', 1),
(2715, 'CombiPatch', 1),
(2716, 'Combipres', 1),
(2717, 'Combipres-1', 1),
(2718, 'Combipres-2', 1),
(2719, 'Combipres-3', 1),
(2720, 'Combivent', 1),
(2721, 'Combivir', 1),
(2722, 'Combunox', 1),
(2723, 'COMFORT SHIELD', 1),
(2724, 'COMFORT SHIELD Large', 1),
(2725, 'Comfrey', 1),
(2726, 'Comfrey (Symphytum Officinale)', 1),
(2727, 'Comhist', 1),
(2728, 'Comhist LA', 1),
(2729, 'Commit', 1),
(2730, 'Compazine', 1),
(2731, 'Complement C1 Esterase Inhib', 1),
(2732, 'Complete Sinus Relief', 1),
(2733, 'Complex 15', 1),
(2734, 'Complex B', 1),
(2735, 'Complex B-50', 1),
(2736, 'Computer Eye', 1),
(2737, 'Comt Inhibitor,Nitrocatechols', 1),
(2738, 'Comtan', 1),
(2739, 'Comtrex Cold-Cough', 1),
(2740, 'Comtrex Day& Night', 1),
(2741, 'Comtrex Day/Night Cold& Cough', 1),
(2742, 'Comtrex Flu Therapy', 1),
(2743, 'Comtrex Nighttime Cold& Cough', 1),
(2744, 'Comtrex Severe Cold& Sinus', 1),
(2745, 'Comvax', 1),
(2746, 'Conceptrol', 1),
(2747, 'Conceptrol Inserts', 1),
(2748, 'CONCERTA', 1),
(2749, 'Condoms, Female', 1),
(2750, 'Condurango', 1),
(2751, 'Condylox', 1),
(2752, 'Conex Pediatric', 1),
(2753, 'Congo Red', 1),
(2754, 'Conivaptan', 1),
(2755, 'Conpec', 1),
(2756, 'Conpec DM', 1),
(2757, 'Conpec LA NR', 1),
(2758, 'Conray', 1),
(2759, 'Conray 43', 1),
(2760, 'Conray-30', 1),
(2761, 'Conray-325', 1),
(2762, 'Conray-400', 1),
(2763, 'Constant Clens', 1),
(2764, 'Contac', 1),
(2765, 'Contac 12 Hour', 1),
(2766, 'Converzyme', 1),
(2767, 'Copaiba Balsam', 1),
(2768, 'Copaxone', 1),
(2769, 'Cope', 1),
(2770, 'Copegus', 1),
(2771, 'Cophene-S', 1),
(2772, 'Copolymer Foam', 1),
(2773, 'Copper', 1),
(2774, 'Copper Chloride', 1),
(2775, 'Copper Sulfate', 1),
(2776, 'Copperas Crystals Tech', 1),
(2777, 'Coppermin', 1),
(2778, 'Coppertone', 1),
(2779, 'Coppertone Kids', 1),
(2780, 'CoQ-10& Fish Oil', 1),
(2781, 'CoQ10 SG 100', 1),
(2782, 'Coral', 1),
(2783, 'Coral Calcium', 1),
(2784, 'Cordarone', 1),
(2785, 'Cordarone IV', 1),
(2786, 'Cordran', 1),
(2787, 'Cordran Patch', 1),
(2788, 'Cordran SP', 1),
(2789, 'Coreg', 1),
(2790, 'Coreg CR', 1),
(2791, 'Corgard', 1),
(2792, 'Coriander', 1),
(2793, 'Coriander Oil', 1),
(2794, 'Coricidin HBP', 1),
(2795, 'Coricidin HBP Cough& Cold', 1),
(2796, 'Corlopam', 1),
(2797, 'Corn', 1),
(2798, 'Corn Containing Products', 1),
(2799, 'Corn Liquid', 1),
(2800, 'Corn Oil', 1),
(2801, 'Corn Oil Solvent', 1),
(2802, 'Corn Starch', 1),
(2803, 'Corn Syrup', 1),
(2804, 'Cornflower', 1),
(2805, 'Correctol', 1),
(2806, 'Cort-Dome', 1),
(2807, 'Cortaid', 1),
(2808, 'Cortaid FastStick Max St', 1),
(2809, 'Cortaid Maximum Strength', 1),
(2810, 'Cortane', 1),
(2811, 'Cortane-B', 1),
(2812, 'Cortef', 1),
(2813, 'Cortef Acetate', 1),
(2814, 'Cortef Feminine Itch', 1),
(2815, 'Cortef Rectal Itch', 1),
(2816, 'Cortenema', 1),
(2817, 'Cortex Eucommiae', 1),
(2818, 'Corticorelin Ovine Triflutate', 1),
(2819, 'Corticotropin', 1),
(2820, 'Corticotropin-Releasing Hormon', 1),
(2821, 'Corticotropin-RF,Ovine', 1),
(2822, 'Corticotropin-Zinc', 1),
(2823, 'Cortifoam', 1),
(2824, 'Cortisone', 1),
(2825, 'Cortisporin', 1),
(2826, 'Cortisporin-TC', 1),
(2827, 'Cortivazol', 1),
(2828, 'Cortizone-10', 1),
(2829, 'Cortone', 1),
(2830, 'Cortril', 1),
(2831, 'Cortrosyn', 1),
(2832, 'Corvert', 1),
(2833, 'CORVITE', 1),
(2834, 'Corynebacterium Cutis', 1),
(2835, 'Corynebacterium Parvum', 1),
(2836, 'Coryza Forte', 1),
(2837, 'Corzide', 1),
(2838, 'Cosamin DS', 1),
(2839, 'Cosmegen', 1),
(2840, 'Cosmetic,O.U.', 1),
(2841, 'Cosopt', 1),
(2842, 'Cosyntropin', 1),
(2843, 'Cosyntropin Acetate-Zinc', 1),
(2844, 'Cotazym-S', 1),
(2845, 'Cottonseed Oil', 1),
(2846, 'CoTylenol', 1),
(2847, 'Couch Grass', 1),
(2848, 'Couch Grass (Triticum Repens)', 1),
(2849, 'Cough And Cold Reliever', 1),
(2850, 'Cough Drops', 1),
(2851, 'Cough Due To Colds No.7', 1),
(2852, 'Cough Formula', 1),
(2853, 'Cough& Runny Nose', 1),
(2854, 'Cough-X', 1),
(2855, 'Coumadin', 1),
(2856, 'Coumarin', 1),
(2857, 'Coumarin Analogues', 1),
(2858, 'Covera-HS', 1),
(2859, 'Cowslip', 1),
(2860, 'Cowslip (Primula Veris)', 1),
(2861, 'Cozaar', 1),
(2862, 'CP-2', 1),
(2863, 'CR-Plus-Protein', 1),
(2864, 'Cramp Bark', 1),
(2865, 'Cranberry', 1),
(2866, 'Cranberry Juice', 1),
(2867, 'Cream Flavor', 1),
(2868, 'Creamalin', 1),
(2869, 'Creatine', 1),
(2870, 'Creatinine', 1),
(2871, 'Cremophor El', 1),
(2872, 'Creomulsion Complete', 1),
(2873, 'Creon 10', 1),
(2874, 'Creon 20', 1),
(2875, 'Creon 5', 1),
(2876, 'Creosote', 1),
(2877, 'Cresol', 1),
(2878, 'Crestor', 1),
(2879, 'Cresylate', 1),
(2880, 'Crinone', 1),
(2881, 'Critic-Aid', 1),
(2882, 'Critic-Aid Clear', 1),
(2883, 'Critic-Aid Clear AF', 1),
(2884, 'Critic-Aid Skin Care Pack', 1),
(2885, 'Crixivan', 1),
(2886, 'CrM', 1),
(2887, 'CroFab', 1),
(2888, 'Cromolyn', 1),
(2889, 'Croscarmellose Calcium', 1),
(2890, 'Croscarmellose Sodium', 1),
(2891, 'Crotalus Horridus Horridus', 1),
(2892, 'Crotamiton', 1),
(2893, 'Croton Oil', 1),
(2894, 'CRP', 1),
(2895, 'Cruex', 1),
(2896, 'Cryptenamine', 1),
(2897, 'Crypthecodinium Cohnii Oil', 1),
(2898, 'Crystodigin', 1),
(2899, 'Cu-Plus', 1),
(2900, 'CUBICIN', 1),
(2901, 'Cucumber (Cucumis Sativus)', 1),
(2902, 'Cudbear', 1),
(2903, 'Cult Skin Subst,Human-Porcine', 1),
(2904, 'Culturelle', 1),
(2905, 'Cuprimine', 1),
(2906, 'Curasol Wound Cleanser', 1),
(2907, 'Curel', 1),
(2908, 'Curity Plain Packing Strip', 1),
(2909, 'Curosurf', 1),
(2910, 'Curtal', 1),
(2911, 'Cutar', 1),
(2912, 'Cutivate', 1),
(2913, 'Cyamemazine', 1),
(2914, 'Cyanamide', 1),
(2915, 'Cyanocobalamin', 1),
(2916, 'Cyclacillin', 1),
(2917, 'Cyclamate', 1),
(2918, 'Cyclamate And Deriv', 1),
(2919, 'Cyclandelate', 1),
(2920, 'Cyclessa', 1),
(2921, 'Cyclinex-2', 1),
(2922, 'Cyclizine', 1),
(2923, 'Cyclobarbital', 1),
(2924, 'Cyclobenzaprine', 1),
(2925, 'Cyclobutyrol', 1),
(2926, 'Cyclocort', 1),
(2927, 'Cyclocumarol', 1),
(2928, 'Cyclodrine', 1),
(2929, 'Cyclofenil', 1),
(2930, 'Cycloguanil', 1),
(2931, 'Cyclogyl', 1),
(2932, 'Cycloheximide', 1),
(2933, 'Cyclomethicone', 1),
(2934, 'Cyclomethycaine', 1),
(2935, 'Cyclomydril', 1),
(2936, 'Cyclooctaamylose', 1),
(2937, 'Cyclopentamine', 1),
(2938, 'Cyclopentasiloxane', 1),
(2939, 'Cyclopenthiazide', 1),
(2940, 'Cyclopentolate', 1),
(2941, 'Cyclophosphamide', 1),
(2942, 'Cyclopropane', 1),
(2943, 'Cycloserine', 1),
(2944, 'Cyclosporine', 1),
(2945, 'Cyclothiazide', 1),
(2946, 'Cycrimine', 1),
(2947, 'Cyklokapron', 1),
(2948, 'Cylert', 1),
(2949, 'Cymarin', 1),
(2950, 'Cymbalta', 1),
(2951, 'Cynara (Artichoke)', 1),
(2952, 'Cypress', 1),
(2953, 'Cyprodenate', 1),
(2954, 'Cyproheptadine', 1),
(2955, 'Cyproterone', 1),
(2956, 'Cystadane', 1),
(2957, 'Cystagon', 1),
(2958, 'Cysteamine', 1),
(2959, 'Cysteic Acid', 1),
(2960, 'Cysteine', 1),
(2961, 'Cysteine Derivatives', 1),
(2962, 'Cystex', 1),
(2963, 'Cystine', 1),
(2964, 'Cystine(L-Cystine)', 1),
(2965, 'Cysto-Conray', 1),
(2966, 'Cystografin', 1),
(2967, 'Cystografin-Dilute', 1),
(2968, 'Cystospaz', 1),
(2969, 'Cytadren', 1),
(2970, 'Cytarabine', 1),
(2971, 'Cytidine', 1),
(2972, 'Cytochrome C', 1),
(2973, 'CytoGam', 1),
(2974, 'Cytomegalovirus Immune Glob', 1),
(2975, 'Cytomel', 1),
(2976, 'Cytosar-U', 1),
(2977, 'Cytosar-U Powder W/Diluent', 1),
(2978, 'Cytotec', 1),
(2979, 'Cytovene', 1),
(2980, 'Cytoxan', 1),
(2981, 'Cytoxan Lyophilized', 1),
(2982, 'Calamine,Phenolated', 1);

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
-- Dumping data for table `appointments`
--


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

--
-- Dumping data for table `appointment_history`
--


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

--
-- Dumping data for table `appointment_status`
--

INSERT INTO `appointment_status` (`STATUS_CODE`, `DESCRIPTION`, `ACTIVE`) VALUES
('BOOKED', 'Booked', 1),
('CANCELLED', 'Cancelled', 1),
('CAPTURED', 'Doctor has seen the patient', 1),
('CONFIRMED', 'Confirmed', 1),
('INVALID_ROSTER', 'Invalid Roster', 1),
('RESCHEDULED', 'Rescheduled', 1),
('ROSTER_MODIFIED', 'Roster Modified', 1),
('TOVISIT', 'Patient came to visit ', 1),
('VISITED', 'Patient visited the doctor', 1);

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

--
-- Dumping data for table `app_entities`
--

INSERT INTO `app_entities` (`ENTITY_TYPE`, `ENTITY_NAME`, `DESCRIPTION`, `ACTIVE`) VALUES
('ADMINISTRATOR', 'Administrator', 'Administrator', 1),
('DOCTOR', 'Doctor', 'Doctor', 1),
('NURSE', 'Nurse', 'Nurse', 1),
('PATIENT', 'Patient', 'Patient', 1),
('WARD_BOY', 'Ward Boy', 'Ward Boy', 1);

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

--
-- Dumping data for table `assigned_package`
--


-- --------------------------------------------------------

--
-- Table structure for table `assigned_package_status`
--

CREATE TABLE IF NOT EXISTS `assigned_package_status` (
  `STATUS_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`STATUS_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `assigned_package_status`
--

INSERT INTO `assigned_package_status` (`STATUS_CODE`, `DESCRIPTION`) VALUES
('CANCELED', 'Canceled'),
('DELETED', 'Deleted'),
('RENDERED', 'Rendered'),
('REQUESTED', 'Requested');

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
  `IS_BILLABLE` varchar(1) COLLATE utf8_unicode_ci NOT NULL,
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

--
-- Dumping data for table `assigned_services`
--


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

--
-- Dumping data for table `assigned_service_history`
--


-- --------------------------------------------------------

--
-- Table structure for table `assigned_service_status`
--

CREATE TABLE IF NOT EXISTS `assigned_service_status` (
  `SERVICE_STATUS_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`SERVICE_STATUS_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `assigned_service_status`
--

INSERT INTO `assigned_service_status` (`SERVICE_STATUS_CODE`, `DESCRIPTION`) VALUES
('APPROVED', 'Approved'),
('CANCELED', 'Canceled'),
('DELETED', 'Deleted'),
('DISAPPROVED', 'Disapproved'),
('PARTCANCELED', 'Partially Canceled'),
('PARTRENDERED', 'Partially Rendered'),
('PARTREPLACED', 'Partially Replaced'),
('RENDERED', 'Rendered'),
('REPLACED', 'Replaced'),
('REQUESTED', 'Requested'),
('RESULT_ENTERED', 'Result entered'),
('SPECIMEN_COLLECTED', 'Specimen collected'),
('TEST_PERFORMED', 'Test performed');

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

--
-- Dumping data for table `bed_bill_history`
--


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

--
-- Dumping data for table `bed_envelope`
--

INSERT INTO `bed_envelope` (`ENVELOPE_NAME`, `DESCRIPTION`, `FACILITY_TYPE_IND`, `VERSION`) VALUES
('Infection', 'Infectious Patients', 'I', 0);

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

--
-- Dumping data for table `bed_envelope_has_pool`
--

INSERT INTO `bed_envelope_has_pool` (`ENVELOPE_NAME`, `POOL_NAME`, `EFFECTIVE_FROM_DT`, `EFFECTIVE_TO_DT`, `VERSION`) VALUES
('Infection', 'Infectious ', '2010-06-15', NULL, 0);

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

--
-- Dumping data for table `bed_has_special_features`
--


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

--
-- Dumping data for table `bed_master`
--


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

--
-- Dumping data for table `bed_pool`
--

INSERT INTO `bed_pool` (`BED_POOL_NAME`, `POOL_DESC`, `TOTAL_NUMBER_OF_BED`, `NUMBER_OF_AVAILABLE_BEDS`, `VERSION`) VALUES
('Custodial', 'A zone for the patient from Police Station ', 0, 0, 3),
('Infectious ', 'Infectious Disease', 0, 0, 0);

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

--
-- Dumping data for table `bed_pool_has_unit_type`
--

INSERT INTO `bed_pool_has_unit_type` (`POOL_NAME`, `UNIT_TYPE_CD`, `EFFECTIVE_FROM_DT`, `EFFECTIVE_TO_DATE`, `VERSION`) VALUES
('Custodial', 'EMERGENCY', '2010-06-14', NULL, 0),
('Custodial', 'GENERAL', '2010-06-16', NULL, 0),
('Custodial', 'OUTPATIENT', '2010-06-15', NULL, 0),
('Infectious ', 'LIVER_DIGESTIVE', '2010-06-15', NULL, 0);

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
  `REQUIRED_FLAG` char(1) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`RESERVATION_NBR`,`FEATURE_NAME`),
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
  `DESCRIPTION` varchar(256) DEFAULT NULL,
  `LOCATION_WRT_BED_IND` varchar(10) DEFAULT NULL,
  `EFFECTIVE_FROM_DTM` timestamp NULL DEFAULT NULL,
  `EFFECTIVE_TO_DTM` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`FEATURE_NAME`)
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
  `DESCRIPTION` varchar(256) DEFAULT NULL,
  `ACTIVE_FLAG` char(1) DEFAULT NULL,
  PRIMARY KEY (`BED_STATUS_CD`)
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
  `BED_TYPE_DESC` varchar(256) DEFAULT NULL,
  `ACTIVE_FLAG` char(1) DEFAULT NULL,
  PRIMARY KEY (`BED_TYPE_CD`)
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `bed_usage_history`
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=1 ;

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
('IPD', 'PHARMACY', 4, NULL),
('IPD', 'SERVICES', 1, NULL),
('OPD', 'ACCOUNTING', 2, NULL),
('OPD', 'PHARMACY', 3, NULL),
('OPD', 'SERVICES', 1, NULL),
('PAT', 'ACCOUNTING', 2, NULL),
('PAT', 'PHARMACY', 3, NULL),
('PAT', 'SERVICES', 1, NULL),
('PHA', 'PHARMACY', 1, NULL);

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
-- Table structure for table `blood_group`
--

CREATE TABLE IF NOT EXISTS `blood_group` (
  `BLOOD_GROUP_CODE` varchar(5) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`BLOOD_GROUP_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `blood_group`
--

INSERT INTO `blood_group` (`BLOOD_GROUP_CODE`, `DESCRIPTION`, `ACTIVE`) VALUES
('ABNEG', 'AB -ve', 1),
('ABPOS', 'AB +ve', 1),
('ANEG', 'A -ve', 1),
('APOS', 'A +ve', 1),
('BNEG', 'B -ve', 1),
('BPOS', 'B +ve', 1),
('ONEG', 'O -ve', 1),
('OPOS', 'O +ve', 1);

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

--
-- Dumping data for table `booking_type`
--

INSERT INTO `booking_type` (`BOOKING_TYPE_CODE`, `DESCRIPTION`, `ACTIVE`) VALUES
('EMAIL', 'E-mail', 1),
('PHONE', 'Phone', 1),
('WALK', 'Walk-In', 1);

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

--
-- Dumping data for table `brand`
--

INSERT INTO `brand` (`BRAND_CODE`, `DESCRIPTION`, `ACTIVE`) VALUES
('ALKE', 'Alkem laboratories', 1),
('AVEN', 'Aventis Pharma', 1),
('NOVA', 'Novartis', 1),
('PFIZ', 'Pfizer', 1),
('RANB', 'Ranbaxy', 1);

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

--
-- Dumping data for table `business_rule`
--


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

--
-- Dumping data for table `change_reason`
--

INSERT INTO `change_reason` (`CHANGE_REASON_CODE`, `DESCRIPTION`, `ACTIVE`) VALUES
('LOGIN_FAILED', 'Multiple login failed', 1),
('MULTIPLE_CANCELLATION', 'Multiple appointments cancelled for a month', 1),
('REGISTRATION_FEES_UNPAID', 'Registration fees not paid', 1);

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

--
-- Dumping data for table `claim_document`
--


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
  PRIMARY KEY (`SPONSOR_NAME`,`ACTIVITY_TYPE_CD`),
  KEY `CLAIM_SPONSOR_SLA_SPONSOR_NAME_FK` (`SPONSOR_NAME`),
  KEY `CLAIM_SPONSOR_SLA_ACTIVITY_TYPE_CD_FK` (`ACTIVITY_TYPE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `claim_sponsor_sla`
--


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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=321 ;

--
-- Dumping data for table `contact_details`
--

INSERT INTO `contact_details` (`CONTACT_CODE`, `SALUATION_CODE`, `FIRST_NAME`, `MIDDLE_NAME`, `LAST_NAME`, `GENDER_CODE`, `ADDRESS_LINE1`, `ADDRESS_LINE2`, `CITY`, `COUNTRY_CODE`, `STATE_CODE`, `PINCODE`, `CONTACT_NUMBER`, `MOBILE_NUMBER`, `FAX_NUMBER`, `EMAIL`, `STAY_DURATION`, `RELATION_CODE`, `CREATE_DTM`, `CREATED_BY`, `MODIFIED_DTM`, `MODIFIED_BY`, `VERSION`) VALUES
(1, 'MR', 'Rakesh', '', '', NULL, NULL, '', '', '208', '1000002', '', '', '', '', '', '', NULL, '2010-04-02 18:47:27', 'ajit kumar', NULL, NULL, 0),
(233, NULL, NULL, NULL, NULL, NULL, '45', 'BanjaraHills', 'Hyderabad', '208', '1000001', NULL, '+91-7868-223583', '9542364010', '223583', 'Religare@gmail.com', NULL, NULL, NULL, NULL, NULL, NULL, 0),
(234, NULL, NULL, NULL, NULL, NULL, '', '', '', '208', '1000006', NULL, '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, 2),
(244, NULL, NULL, NULL, NULL, NULL, '', '', '', '208', '1000008', NULL, '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, 1),
(253, 'DR', 'Tanmay', 'Kumar', 'sahu', NULL, NULL, 'St-50', 'Hyderabad', '208', '1000001', '500050', '+91-07868-223583', '+91-9542364010', '223583', 'tanmay-007@gmail.com', '5 years', NULL, '2010-05-20 18:04:00', 'ajit kumar', NULL, NULL, 0),
(254, 'DR', 'Tanmay', 'Kumar', 'sahu', NULL, NULL, 'St-50', 'Hyderabad', '208', '1000001', '500050', '+91-07868-223583', '+91-9542364010', '223583', 'tanmay-007@gmail.com', '5 years', NULL, '2010-05-20 18:04:00', 'ajit kumar', NULL, NULL, 0),
(255, 'MS', 'Vanay', 'kumar', 'sharma', 'MALE', NULL, 'St-50', 'Hyderabad', '208', '1000001', '500050', '+91-7868-223583', '+91-9542364010', '', '', '5 years', 'FRIEND', '2010-05-20 18:04:00', 'ajit kumar', NULL, NULL, 0),
(256, 'MR', 'Vipul', 'kumar', 'chandra', 'MALE', NULL, 'St 007', 'hyderabad', '208', '1000001', '500012', '07868223583', '09542364010', '223583', 'vipul@gmail.com', '', NULL, '2010-05-20 18:20:25', 'ajit kumar', '2010-06-22 08:12:00', NULL, 10),
(257, 'MR', 'Vipul', 'kumar', 'chandra', 'MALE', NULL, 'St 007', 'hyderabad', '208', '1000001', '500012', '07868223583', '9542364010', '223583', 'vipul@gmail.com', '', NULL, '2010-05-20 18:20:25', 'ajit kumar', '2010-06-22 08:12:00', NULL, 10),
(258, NULL, 'Sandeep', 'Kumar', '', NULL, NULL, 'Malaysian Township', 'Hyderabad', '208', '1000001', '500072', '', '', '', 'sandeep.kumar@walkingtree.in', '1 year', NULL, '2010-05-20 20:32:16', 'ajit kumar', '2010-06-22 06:32:13', NULL, 2),
(259, NULL, 'Sandeep', 'Kumar', '', NULL, NULL, 'Malaysian Township', 'Hyderabad', '208', '1000001', '500072', '', '', '', 'sandeep.kumar@walkingtree.in', '1 year', NULL, '2010-05-20 20:32:16', 'ajit kumar', '2010-06-22 06:32:13', NULL, 2),
(260, NULL, 'Vinay', '', '', NULL, NULL, '', '', '208', NULL, '', '', '', '', '', '', NULL, '2010-05-20 20:36:26', 'ajit kumar', '2010-06-03 17:50:54', NULL, 2),
(261, NULL, 'Vinay', '', '', NULL, NULL, '', '', NULL, NULL, '', '', '', '', '', '', NULL, '2010-05-20 20:36:26', 'ajit kumar', '2010-06-03 17:50:54', NULL, 2),
(262, 'MR', 'Vikas', '', '', NULL, NULL, '', '', '208', NULL, '', '', '', '', '', '', NULL, '2010-05-20 22:36:17', 'ajit kumar', '2010-06-03 18:51:46', NULL, 5),
(263, 'MR', 'Vikas', '', '', NULL, NULL, '', '', '208', NULL, '', '', '', '', '', '', NULL, '2010-05-20 22:36:17', 'ajit kumar', '2010-06-03 18:51:46', NULL, 5),
(264, 'MS', 'Rani', '', 'sinha', 'FEMALE', NULL, '', '', '208', NULL, '', '', '', '', '', '', NULL, '2010-05-20 23:26:04', 'ajit kumar', '2010-06-22 07:02:29', NULL, 5),
(265, 'MS', 'Rani', '', 'sinha', 'FEMALE', NULL, '', '', NULL, NULL, '', '', '', '', '', '', NULL, '2010-05-20 23:26:04', 'ajit kumar', '2010-06-22 07:02:29', NULL, 5),
(266, NULL, 'Ravish', '', '', NULL, NULL, '', '', '208', NULL, '', '', '', '', '', '', NULL, '2010-05-21 00:39:31', 'ajit kumar', NULL, NULL, 0),
(267, NULL, 'Ravish', '', '', NULL, NULL, '', '', NULL, NULL, '', '', '', '', '', '', NULL, '2010-05-21 00:39:31', 'ajit kumar', NULL, NULL, 0),
(270, 'MR', 'Sagar', 'kumar', 'sharma', 'MALE', NULL, '', '', '208', NULL, '', '', '', '', '', '', NULL, '2010-05-23 18:58:27', 'ajit kumar', '2010-06-22 06:57:10', NULL, 3),
(271, 'MR', 'Sagar', 'kumar', 'sharma', 'MALE', NULL, '', '', NULL, NULL, '', '', '', '', '', '', NULL, '2010-05-23 18:58:27', 'ajit kumar', '2010-06-22 06:57:10', NULL, 3),
(283, NULL, NULL, NULL, NULL, NULL, '', '', '', '208', '', NULL, '', '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, 3),
(284, 'MR', 'Ajay', 'Kumar', 'Sinha', 'MALE', NULL, '', '', '208', '1000001', '', '', '', '', '', '', NULL, '2010-05-25 15:58:12', 'Alok Ranjan', '2010-06-22 06:56:37', NULL, 1),
(285, 'MR', 'Ajay', 'Kumar', 'Sinha', 'MALE', NULL, '', '', '208', '1000001', '', '', '', '', '', '', NULL, '2010-05-25 15:58:12', 'Alok Ranjan', '2010-06-22 06:56:37', NULL, 1),
(286, 'MR', 'Ramanad', 'k', 'Shastri', 'MALE', NULL, '', '', '208', '1000033', '', '', '', '', '', '', NULL, '2010-06-01 16:13:59', 'ajit kumar', NULL, NULL, 0),
(287, 'MR', 'Ramanad', 'k', 'Shastri', 'MALE', NULL, '', '', NULL, NULL, '', '', '', '', '', '', NULL, '2010-06-01 16:13:59', 'ajit kumar', NULL, NULL, 0),
(288, NULL, 'Bhavesh', '', '', NULL, NULL, '', '', '208', NULL, '', '', '', '', '', '', NULL, '2010-06-02 14:43:54', 'ajit kumar', NULL, NULL, 0),
(289, NULL, 'Bhavesh', '', '', NULL, NULL, '', '', NULL, NULL, '', '', '', '', '', '', NULL, '2010-06-02 14:43:54', 'ajit kumar', NULL, NULL, 0),
(292, 'MR', 'Mohan', '', '', NULL, NULL, '', '', '208', NULL, '', '', '', '', '', '', NULL, '2010-06-03 18:15:17', 'Bhavesh ', NULL, NULL, 0),
(293, 'MR', 'Mohan', '', '', NULL, NULL, '', '', NULL, NULL, '', '', '', '', '', '', NULL, '2010-06-03 18:15:17', 'Bhavesh ', NULL, NULL, 0),
(294, 'MRS', 'Meena', '', '', NULL, NULL, '', '', '208', NULL, '', '', '', '', '', '', NULL, '2010-06-03 18:20:01', 'Bhavesh ', NULL, NULL, 0),
(295, 'MRS', 'Meena', '', '', NULL, NULL, '', '', NULL, NULL, '', '', '', '', '', '', NULL, '2010-06-03 18:20:01', 'Bhavesh ', NULL, NULL, 0),
(296, 'MR', 'Vinay', '', '', NULL, NULL, '', '', '208', NULL, '', '', '', '', '', '', NULL, '2010-06-03 18:22:52', 'Bhavesh ', '2010-06-03 19:12:28', NULL, 2),
(297, 'MR', 'Vinay', '', '', NULL, NULL, '', '', NULL, NULL, '', '', '', '', '', '', NULL, '2010-06-03 18:22:52', 'Bhavesh ', '2010-06-03 19:12:28', NULL, 2),
(298, 'MR', 'Ramesh', 'k', 'Ahuja', NULL, NULL, '', '', '208', '1000010', '', '', '', NULL, NULL, NULL, NULL, '2010-06-15 15:44:46', 'ajit kumar', NULL, NULL, 0),
(299, 'MR', 'Ramesh', 'k', 'Ahuja', NULL, NULL, '', '', '208', '1000010', '', '', '', NULL, NULL, NULL, NULL, '2010-06-15 15:44:46', 'ajit kumar', NULL, NULL, 0),
(302, 'MR', 'Shatish', '', '', NULL, NULL, '', '', '208', NULL, '', '', '', '', '', '', NULL, '2010-06-15 17:46:16', 'ajit kumar', NULL, NULL, 0),
(303, 'MR', 'Shatish', '', '', NULL, NULL, '', '', NULL, NULL, '', '', '', '', '', '', NULL, '2010-06-15 17:46:16', 'ajit kumar', NULL, NULL, 0),
(304, 'MR', 'Subba', 'K', 'Rao', 'MALE', NULL, NULL, NULL, NULL, NULL, NULL, '40201991', '9892382939', '0404040402', 'subba@sbi.in', NULL, NULL, '2010-06-15 18:13:27', 'ajit kumar', NULL, NULL, 0),
(305, 'MR', 'Venkat', 'K', 'Mangipudi', 'MALE', NULL, NULL, NULL, '208', NULL, NULL, '020930223', '92839287349', '', 'venkat@sbi-insurance.in', NULL, NULL, '2010-06-15 18:15:03', 'ajit kumar', NULL, NULL, 0),
(306, 'MR', 'Somnath', 'K', 'Chattarjee', 'MALE', NULL, NULL, NULL, '208', NULL, NULL, '23487283', '9829382377', '234532343', 'somnath@phs.co.in', NULL, NULL, '2010-06-16 08:01:27', 'ajit kumar', NULL, NULL, 0),
(309, 'MR', 'Sohan', 'Kumar', 'Raj', NULL, NULL, '', '', '208', NULL, '', '', '', NULL, NULL, NULL, NULL, '2010-06-17 14:35:02', 'Bhavesh ', NULL, NULL, 0),
(310, 'MR', 'Sohan', 'Kumar', 'Raj', NULL, NULL, '', '', '208', NULL, '', '', '', NULL, NULL, NULL, NULL, '2010-06-17 14:35:02', 'Bhavesh ', NULL, NULL, 0),
(313, 'MR', 'Piyush', 'Kumar', 'Jain', NULL, NULL, '', '', '208', NULL, '', '', '', '', '', '', NULL, '2010-06-18 04:09:08', 'ajit kumar', NULL, NULL, 0),
(314, 'MR', 'Piyush', 'Kumar', 'Jain', NULL, NULL, '', '', NULL, NULL, '', '', '', '', '', '', NULL, '2010-06-18 04:09:08', 'ajit kumar', NULL, NULL, 0),
(315, 'MR', 'Mohammed', '', 'Janib', 'MALE', NULL, '', '', '208', NULL, '', '', '', '', '', '', NULL, '2010-06-18 10:40:13', 'ajit kumar', '2010-06-26 18:06:39', NULL, 6),
(316, 'MR', 'Mohammed', '', 'Janib', 'MALE', NULL, '', '', NULL, NULL, '', '', '', '', '', '', NULL, '2010-06-18 10:40:13', 'ajit kumar', '2010-06-26 18:06:39', NULL, 6),
(317, 'DR', 'Deepak', 'K', 'Lavania', NULL, NULL, 'Adarsh Nagar', 'Agra', '208', '1000033', '', '', '', '', '', '', NULL, '2010-06-23 07:39:37', 'Bhavesh ', NULL, NULL, 0),
(318, 'DR', 'Deepak', 'K', 'Lavania', NULL, NULL, 'Adarsh Nagar', 'Agra', '208', '1000033', '', '', '', '', '', '', NULL, '2010-06-23 07:39:37', 'Bhavesh ', NULL, NULL, 0),
(319, 'DR', 'Mukesh', '', 'Ambani', NULL, NULL, '', '', '208', '1000001', '', '', '', '', '', '', NULL, '2010-06-28 14:22:53', 'Bhavesh ', NULL, NULL, 0),
(320, 'DR', 'Mukesh', '', 'Ambani', NULL, NULL, '', '', NULL, NULL, '', '', '', '', '', '', NULL, '2010-06-28 14:22:53', 'Bhavesh ', NULL, NULL, 0);

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

--
-- Dumping data for table `contact_type`
--

INSERT INTO `contact_type` (`CONTACT_TYPE_IND`, `DESCRIPTION`, `ACTIVE`) VALUES
('CURRENT', 'CURRENT', 1),
('EMERGENCY_PRIMARY', 'EMERGENCY_PRIMARY', 1),
('EMERGENCY_SECONDARY', 'EMERGENCY_SECONDARY', 1),
('PERMANENT', 'PERMANENT', 1),
('SPONSOR', 'SPONSOR', 1);

-- --------------------------------------------------------

--
-- Table structure for table `country`
--

CREATE TABLE IF NOT EXISTS `country` (
  `COUNTRY_CODE` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IS_DEFAULT` varchar(1) COLLATE utf8_unicode_ci DEFAULT 'N',
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`COUNTRY_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `country`
--

INSERT INTO `country` (`COUNTRY_CODE`, `DESCRIPTION`, `IS_DEFAULT`, `ACTIVE`) VALUES
('100', 'United States', 'N', 1),
('208', 'India', 'Y', 1),
('238', 'Malaysia', 'N', 1);

-- --------------------------------------------------------

--
-- Table structure for table `credit_class`
--

CREATE TABLE IF NOT EXISTS `credit_class` (
  `CREDIT_CLASS_CD` varchar(30) NOT NULL,
  `DESCRIPTION` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`CREDIT_CLASS_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `credit_class`
--

INSERT INTO `credit_class` (`CREDIT_CLASS_CD`, `DESCRIPTION`) VALUES
('GOLD', 'Gold customers'),
('SILVER', 'Silver customers');

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

--
-- Dumping data for table `date_dim`
--

INSERT INTO `date_dim` (`DATE_ID`, `DAY_OF_YEAR`, `YEAR`, `MONTH`, `YEAR_QUARTER`, `YEAR_AND_MONTH`, `YEAR_MONTH_WEEK`, `YEAR_MONTH_DATE`, `DAY_OF_MONTH`, `WEEK_NBR`, `QUARTER_NBR`, `DAY_NAME`, `MONTH_NAME`) VALUES
(1, 1, 2009, 1, 20091, 200901, 2009011, 20090101, 1, 1, 1, 'THU', 'JAN'),
(2, 2, 2009, 1, 20091, 200901, 2009011, 20090102, 2, 1, 1, 'FRI', 'JAN'),
(3, 3, 2009, 1, 20091, 200901, 2009011, 20090103, 3, 1, 1, 'SAT', 'JAN'),
(4, 4, 2009, 1, 20091, 200901, 2009011, 20090104, 4, 1, 1, 'SUN', 'JAN'),
(5, 5, 2009, 1, 20091, 200901, 2009011, 20090105, 5, 1, 1, 'MON', 'JAN'),
(6, 6, 2009, 1, 20091, 200901, 2009011, 20090106, 6, 1, 1, 'TUE', 'JAN'),
(7, 7, 2009, 1, 20091, 200901, 2009011, 20090107, 7, 1, 1, 'WED', 'JAN'),
(8, 8, 2009, 1, 20091, 200901, 2009012, 20090108, 8, 2, 1, 'THU', 'JAN'),
(9, 9, 2009, 1, 20091, 200901, 2009012, 20090109, 9, 2, 1, 'FRI', 'JAN'),
(10, 10, 2009, 1, 20091, 200901, 2009012, 20090110, 10, 2, 1, 'SAT', 'JAN'),
(11, 11, 2009, 1, 20091, 200901, 2009012, 20090111, 11, 2, 1, 'SUN', 'JAN'),
(12, 12, 2009, 1, 20091, 200901, 2009012, 20090112, 12, 2, 1, 'MON', 'JAN'),
(13, 13, 2009, 1, 20091, 200901, 2009012, 20090113, 13, 2, 1, 'TUE', 'JAN'),
(14, 14, 2009, 1, 20091, 200901, 2009012, 20090114, 14, 2, 1, 'WED', 'JAN'),
(15, 15, 2009, 1, 20091, 200901, 2009013, 20090115, 15, 3, 1, 'THU', 'JAN'),
(16, 16, 2009, 1, 20091, 200901, 2009013, 20090116, 16, 3, 1, 'FRI', 'JAN'),
(17, 17, 2009, 1, 20091, 200901, 2009013, 20090117, 17, 3, 1, 'SAT', 'JAN'),
(18, 18, 2009, 1, 20091, 200901, 2009013, 20090118, 18, 3, 1, 'SUN', 'JAN'),
(19, 19, 2009, 1, 20091, 200901, 2009013, 20090119, 19, 3, 1, 'MON', 'JAN'),
(20, 20, 2009, 1, 20091, 200901, 2009013, 20090120, 20, 3, 1, 'TUE', 'JAN'),
(21, 21, 2009, 1, 20091, 200901, 2009013, 20090121, 21, 3, 1, 'WED', 'JAN'),
(22, 22, 2009, 1, 20091, 200901, 2009014, 20090122, 22, 4, 1, 'THU', 'JAN'),
(23, 23, 2009, 1, 20091, 200901, 2009014, 20090123, 23, 4, 1, 'FRI', 'JAN'),
(24, 24, 2009, 1, 20091, 200901, 2009014, 20090124, 24, 4, 1, 'SAT', 'JAN'),
(25, 25, 2009, 1, 20091, 200901, 2009014, 20090125, 25, 4, 1, 'SUN', 'JAN'),
(26, 26, 2009, 1, 20091, 200901, 2009014, 20090126, 26, 4, 1, 'MON', 'JAN'),
(27, 27, 2009, 1, 20091, 200901, 2009014, 20090127, 27, 4, 1, 'TUE', 'JAN'),
(28, 28, 2009, 1, 20091, 200901, 2009014, 20090128, 28, 4, 1, 'WED', 'JAN'),
(29, 29, 2009, 1, 20091, 200901, 2009015, 20090129, 29, 5, 1, 'THU', 'JAN'),
(30, 30, 2009, 1, 20091, 200901, 2009015, 20090130, 30, 5, 1, 'FRI', 'JAN'),
(31, 31, 2009, 1, 20091, 200901, 2009015, 20090131, 31, 5, 1, 'SAT', 'JAN'),
(32, 32, 2009, 2, 20091, 200902, 2009021, 20090201, 1, 1, 1, 'SUN', 'FEB'),
(33, 33, 2009, 2, 20091, 200902, 2009021, 20090202, 2, 1, 1, 'MON', 'FEB'),
(34, 34, 2009, 2, 20091, 200902, 2009021, 20090203, 3, 1, 1, 'TUE', 'FEB'),
(35, 35, 2009, 2, 20091, 200902, 2009021, 20090204, 4, 1, 1, 'WED', 'FEB'),
(36, 36, 2009, 2, 20091, 200902, 2009021, 20090205, 5, 1, 1, 'THU', 'FEB'),
(37, 37, 2009, 2, 20091, 200902, 2009021, 20090206, 6, 1, 1, 'FRI', 'FEB'),
(38, 38, 2009, 2, 20091, 200902, 2009021, 20090207, 7, 1, 1, 'SAT', 'FEB'),
(39, 39, 2009, 2, 20091, 200902, 2009022, 20090208, 8, 2, 1, 'SUN', 'FEB'),
(40, 40, 2009, 2, 20091, 200902, 2009022, 20090209, 9, 2, 1, 'MON', 'FEB'),
(41, 41, 2009, 2, 20091, 200902, 2009022, 20090210, 10, 2, 1, 'TUE', 'FEB'),
(42, 42, 2009, 2, 20091, 200902, 2009022, 20090211, 11, 2, 1, 'WED', 'FEB'),
(43, 43, 2009, 2, 20091, 200902, 2009022, 20090212, 12, 2, 1, 'THU', 'FEB'),
(44, 44, 2009, 2, 20091, 200902, 2009022, 20090213, 13, 2, 1, 'FRI', 'FEB'),
(45, 45, 2009, 2, 20091, 200902, 2009022, 20090214, 14, 2, 1, 'SAT', 'FEB'),
(46, 46, 2009, 2, 20091, 200902, 2009023, 20090215, 15, 3, 1, 'SUN', 'FEB'),
(47, 47, 2009, 2, 20091, 200902, 2009023, 20090216, 16, 3, 1, 'MON', 'FEB'),
(48, 48, 2009, 2, 20091, 200902, 2009023, 20090217, 17, 3, 1, 'TUE', 'FEB'),
(49, 49, 2009, 2, 20091, 200902, 2009023, 20090218, 18, 3, 1, 'WED', 'FEB'),
(50, 50, 2009, 2, 20091, 200902, 2009023, 20090219, 19, 3, 1, 'THU', 'FEB'),
(51, 51, 2009, 2, 20091, 200902, 2009023, 20090220, 20, 3, 1, 'FRI', 'FEB'),
(52, 52, 2009, 2, 20091, 200902, 2009023, 20090221, 21, 3, 1, 'SAT', 'FEB'),
(53, 53, 2009, 2, 20091, 200902, 2009024, 20090222, 22, 4, 1, 'SUN', 'FEB'),
(54, 54, 2009, 2, 20091, 200902, 2009024, 20090223, 23, 4, 1, 'MON', 'FEB'),
(55, 55, 2009, 2, 20091, 200902, 2009024, 20090224, 24, 4, 1, 'TUE', 'FEB'),
(56, 56, 2009, 2, 20091, 200902, 2009024, 20090225, 25, 4, 1, 'WED', 'FEB'),
(57, 57, 2009, 2, 20091, 200902, 2009024, 20090226, 26, 4, 1, 'THU', 'FEB'),
(58, 58, 2009, 2, 20091, 200902, 2009024, 20090227, 27, 4, 1, 'FRI', 'FEB'),
(59, 59, 2009, 2, 20091, 200902, 2009024, 20090228, 28, 4, 1, 'SAT', 'FEB'),
(60, 60, 2009, 3, 20091, 200903, 2009031, 20090301, 1, 1, 1, 'SUN', 'MAR'),
(61, 61, 2009, 3, 20091, 200903, 2009031, 20090302, 2, 1, 1, 'MON', 'MAR'),
(62, 62, 2009, 3, 20091, 200903, 2009031, 20090303, 3, 1, 1, 'TUE', 'MAR'),
(63, 63, 2009, 3, 20091, 200903, 2009031, 20090304, 4, 1, 1, 'WED', 'MAR'),
(64, 64, 2009, 3, 20091, 200903, 2009031, 20090305, 5, 1, 1, 'THU', 'MAR'),
(65, 65, 2009, 3, 20091, 200903, 2009031, 20090306, 6, 1, 1, 'FRI', 'MAR'),
(66, 66, 2009, 3, 20091, 200903, 2009031, 20090307, 7, 1, 1, 'SAT', 'MAR'),
(67, 67, 2009, 3, 20091, 200903, 2009032, 20090308, 8, 2, 1, 'SUN', 'MAR'),
(68, 68, 2009, 3, 20091, 200903, 2009032, 20090309, 9, 2, 1, 'MON', 'MAR'),
(69, 69, 2009, 3, 20091, 200903, 2009032, 20090310, 10, 2, 1, 'TUE', 'MAR'),
(70, 70, 2009, 3, 20091, 200903, 2009032, 20090311, 11, 2, 1, 'WED', 'MAR'),
(71, 71, 2009, 3, 20091, 200903, 2009032, 20090312, 12, 2, 1, 'THU', 'MAR'),
(72, 72, 2009, 3, 20091, 200903, 2009032, 20090313, 13, 2, 1, 'FRI', 'MAR'),
(73, 73, 2009, 3, 20091, 200903, 2009032, 20090314, 14, 2, 1, 'SAT', 'MAR'),
(74, 74, 2009, 3, 20091, 200903, 2009033, 20090315, 15, 3, 1, 'SUN', 'MAR'),
(75, 75, 2009, 3, 20091, 200903, 2009033, 20090316, 16, 3, 1, 'MON', 'MAR'),
(76, 76, 2009, 3, 20091, 200903, 2009033, 20090317, 17, 3, 1, 'TUE', 'MAR'),
(77, 77, 2009, 3, 20091, 200903, 2009033, 20090318, 18, 3, 1, 'WED', 'MAR'),
(78, 78, 2009, 3, 20091, 200903, 2009033, 20090319, 19, 3, 1, 'THU', 'MAR'),
(79, 79, 2009, 3, 20091, 200903, 2009033, 20090320, 20, 3, 1, 'FRI', 'MAR'),
(80, 80, 2009, 3, 20091, 200903, 2009033, 20090321, 21, 3, 1, 'SAT', 'MAR'),
(81, 81, 2009, 3, 20091, 200903, 2009034, 20090322, 22, 4, 1, 'SUN', 'MAR'),
(82, 82, 2009, 3, 20091, 200903, 2009034, 20090323, 23, 4, 1, 'MON', 'MAR'),
(83, 83, 2009, 3, 20091, 200903, 2009034, 20090324, 24, 4, 1, 'TUE', 'MAR'),
(84, 84, 2009, 3, 20091, 200903, 2009034, 20090325, 25, 4, 1, 'WED', 'MAR'),
(85, 85, 2009, 3, 20091, 200903, 2009034, 20090326, 26, 4, 1, 'THU', 'MAR'),
(86, 86, 2009, 3, 20091, 200903, 2009034, 20090327, 27, 4, 1, 'FRI', 'MAR'),
(87, 87, 2009, 3, 20091, 200903, 2009034, 20090328, 28, 4, 1, 'SAT', 'MAR'),
(88, 88, 2009, 3, 20091, 200903, 2009035, 20090329, 29, 5, 1, 'SUN', 'MAR'),
(89, 89, 2009, 3, 20091, 200903, 2009035, 20090330, 30, 5, 1, 'MON', 'MAR'),
(90, 90, 2009, 3, 20091, 200903, 2009035, 20090331, 31, 5, 1, 'TUE', 'MAR'),
(91, 91, 2009, 4, 20092, 200904, 2009041, 20090401, 1, 1, 2, 'WED', 'APR'),
(92, 92, 2009, 4, 20092, 200904, 2009041, 20090402, 2, 1, 2, 'THU', 'APR'),
(93, 93, 2009, 4, 20092, 200904, 2009041, 20090403, 3, 1, 2, 'FRI', 'APR'),
(94, 94, 2009, 4, 20092, 200904, 2009041, 20090404, 4, 1, 2, 'SAT', 'APR'),
(95, 95, 2009, 4, 20092, 200904, 2009041, 20090405, 5, 1, 2, 'SUN', 'APR'),
(96, 96, 2009, 4, 20092, 200904, 2009041, 20090406, 6, 1, 2, 'MON', 'APR'),
(97, 97, 2009, 4, 20092, 200904, 2009041, 20090407, 7, 1, 2, 'TUE', 'APR'),
(98, 98, 2009, 4, 20092, 200904, 2009042, 20090408, 8, 2, 2, 'WED', 'APR'),
(99, 99, 2009, 4, 20092, 200904, 2009042, 20090409, 9, 2, 2, 'THU', 'APR'),
(100, 100, 2009, 4, 20092, 200904, 2009042, 20090410, 10, 2, 2, 'FRI', 'APR'),
(101, 101, 2009, 4, 20092, 200904, 2009042, 20090411, 11, 2, 2, 'SAT', 'APR'),
(102, 102, 2009, 4, 20092, 200904, 2009042, 20090412, 12, 2, 2, 'SUN', 'APR'),
(103, 103, 2009, 4, 20092, 200904, 2009042, 20090413, 13, 2, 2, 'MON', 'APR'),
(104, 104, 2009, 4, 20092, 200904, 2009042, 20090414, 14, 2, 2, 'TUE', 'APR'),
(105, 105, 2009, 4, 20092, 200904, 2009043, 20090415, 15, 3, 2, 'WED', 'APR'),
(106, 106, 2009, 4, 20092, 200904, 2009043, 20090416, 16, 3, 2, 'THU', 'APR'),
(107, 107, 2009, 4, 20092, 200904, 2009043, 20090417, 17, 3, 2, 'FRI', 'APR'),
(108, 108, 2009, 4, 20092, 200904, 2009043, 20090418, 18, 3, 2, 'SAT', 'APR'),
(109, 109, 2009, 4, 20092, 200904, 2009043, 20090419, 19, 3, 2, 'SUN', 'APR'),
(110, 110, 2009, 4, 20092, 200904, 2009043, 20090420, 20, 3, 2, 'MON', 'APR'),
(111, 111, 2009, 4, 20092, 200904, 2009043, 20090421, 21, 3, 2, 'TUE', 'APR'),
(112, 112, 2009, 4, 20092, 200904, 2009044, 20090422, 22, 4, 2, 'WED', 'APR'),
(113, 113, 2009, 4, 20092, 200904, 2009044, 20090423, 23, 4, 2, 'THU', 'APR'),
(114, 114, 2009, 4, 20092, 200904, 2009044, 20090424, 24, 4, 2, 'FRI', 'APR'),
(115, 115, 2009, 4, 20092, 200904, 2009044, 20090425, 25, 4, 2, 'SAT', 'APR'),
(116, 116, 2009, 4, 20092, 200904, 2009044, 20090426, 26, 4, 2, 'SUN', 'APR'),
(117, 117, 2009, 4, 20092, 200904, 2009044, 20090427, 27, 4, 2, 'MON', 'APR'),
(118, 118, 2009, 4, 20092, 200904, 2009044, 20090428, 28, 4, 2, 'TUE', 'APR'),
(119, 119, 2009, 4, 20092, 200904, 2009045, 20090429, 29, 5, 2, 'WED', 'APR'),
(120, 120, 2009, 4, 20092, 200904, 2009045, 20090430, 30, 5, 2, 'THU', 'APR'),
(121, 121, 2009, 5, 20092, 200905, 2009051, 20090501, 1, 1, 2, 'FRI', 'MAY'),
(122, 122, 2009, 5, 20092, 200905, 2009051, 20090502, 2, 1, 2, 'SAT', 'MAY'),
(123, 123, 2009, 5, 20092, 200905, 2009051, 20090503, 3, 1, 2, 'SUN', 'MAY'),
(124, 124, 2009, 5, 20092, 200905, 2009051, 20090504, 4, 1, 2, 'MON', 'MAY'),
(125, 125, 2009, 5, 20092, 200905, 2009051, 20090505, 5, 1, 2, 'TUE', 'MAY'),
(126, 126, 2009, 5, 20092, 200905, 2009051, 20090506, 6, 1, 2, 'WED', 'MAY'),
(127, 127, 2009, 5, 20092, 200905, 2009051, 20090507, 7, 1, 2, 'THU', 'MAY'),
(128, 128, 2009, 5, 20092, 200905, 2009052, 20090508, 8, 2, 2, 'FRI', 'MAY'),
(129, 129, 2009, 5, 20092, 200905, 2009052, 20090509, 9, 2, 2, 'SAT', 'MAY'),
(130, 130, 2009, 5, 20092, 200905, 2009052, 20090510, 10, 2, 2, 'SUN', 'MAY'),
(131, 131, 2009, 5, 20092, 200905, 2009052, 20090511, 11, 2, 2, 'MON', 'MAY'),
(132, 132, 2009, 5, 20092, 200905, 2009052, 20090512, 12, 2, 2, 'TUE', 'MAY'),
(133, 133, 2009, 5, 20092, 200905, 2009052, 20090513, 13, 2, 2, 'WED', 'MAY'),
(134, 134, 2009, 5, 20092, 200905, 2009052, 20090514, 14, 2, 2, 'THU', 'MAY'),
(135, 135, 2009, 5, 20092, 200905, 2009053, 20090515, 15, 3, 2, 'FRI', 'MAY'),
(136, 136, 2009, 5, 20092, 200905, 2009053, 20090516, 16, 3, 2, 'SAT', 'MAY'),
(137, 137, 2009, 5, 20092, 200905, 2009053, 20090517, 17, 3, 2, 'SUN', 'MAY'),
(138, 138, 2009, 5, 20092, 200905, 2009053, 20090518, 18, 3, 2, 'MON', 'MAY'),
(139, 139, 2009, 5, 20092, 200905, 2009053, 20090519, 19, 3, 2, 'TUE', 'MAY'),
(140, 140, 2009, 5, 20092, 200905, 2009053, 20090520, 20, 3, 2, 'WED', 'MAY'),
(141, 141, 2009, 5, 20092, 200905, 2009053, 20090521, 21, 3, 2, 'THU', 'MAY'),
(142, 142, 2009, 5, 20092, 200905, 2009054, 20090522, 22, 4, 2, 'FRI', 'MAY'),
(143, 143, 2009, 5, 20092, 200905, 2009054, 20090523, 23, 4, 2, 'SAT', 'MAY'),
(144, 144, 2009, 5, 20092, 200905, 2009054, 20090524, 24, 4, 2, 'SUN', 'MAY'),
(145, 145, 2009, 5, 20092, 200905, 2009054, 20090525, 25, 4, 2, 'MON', 'MAY'),
(146, 146, 2009, 5, 20092, 200905, 2009054, 20090526, 26, 4, 2, 'TUE', 'MAY'),
(147, 147, 2009, 5, 20092, 200905, 2009054, 20090527, 27, 4, 2, 'WED', 'MAY'),
(148, 148, 2009, 5, 20092, 200905, 2009054, 20090528, 28, 4, 2, 'THU', 'MAY'),
(149, 149, 2009, 5, 20092, 200905, 2009055, 20090529, 29, 5, 2, 'FRI', 'MAY'),
(150, 150, 2009, 5, 20092, 200905, 2009055, 20090530, 30, 5, 2, 'SAT', 'MAY'),
(151, 151, 2009, 5, 20092, 200905, 2009055, 20090531, 31, 5, 2, 'SUN', 'MAY'),
(152, 152, 2009, 6, 20092, 200906, 2009061, 20090601, 1, 1, 2, 'MON', 'JUN'),
(153, 153, 2009, 6, 20092, 200906, 2009061, 20090602, 2, 1, 2, 'TUE', 'JUN'),
(154, 154, 2009, 6, 20092, 200906, 2009061, 20090603, 3, 1, 2, 'WED', 'JUN'),
(155, 155, 2009, 6, 20092, 200906, 2009061, 20090604, 4, 1, 2, 'THU', 'JUN'),
(156, 156, 2009, 6, 20092, 200906, 2009061, 20090605, 5, 1, 2, 'FRI', 'JUN'),
(157, 157, 2009, 6, 20092, 200906, 2009061, 20090606, 6, 1, 2, 'SAT', 'JUN'),
(158, 158, 2009, 6, 20092, 200906, 2009061, 20090607, 7, 1, 2, 'SUN', 'JUN'),
(159, 159, 2009, 6, 20092, 200906, 2009062, 20090608, 8, 2, 2, 'MON', 'JUN'),
(160, 160, 2009, 6, 20092, 200906, 2009062, 20090609, 9, 2, 2, 'TUE', 'JUN'),
(161, 161, 2009, 6, 20092, 200906, 2009062, 20090610, 10, 2, 2, 'WED', 'JUN'),
(162, 162, 2009, 6, 20092, 200906, 2009062, 20090611, 11, 2, 2, 'THU', 'JUN'),
(163, 163, 2009, 6, 20092, 200906, 2009062, 20090612, 12, 2, 2, 'FRI', 'JUN'),
(164, 164, 2009, 6, 20092, 200906, 2009062, 20090613, 13, 2, 2, 'SAT', 'JUN'),
(165, 165, 2009, 6, 20092, 200906, 2009062, 20090614, 14, 2, 2, 'SUN', 'JUN'),
(166, 166, 2009, 6, 20092, 200906, 2009063, 20090615, 15, 3, 2, 'MON', 'JUN'),
(167, 167, 2009, 6, 20092, 200906, 2009063, 20090616, 16, 3, 2, 'TUE', 'JUN'),
(168, 168, 2009, 6, 20092, 200906, 2009063, 20090617, 17, 3, 2, 'WED', 'JUN'),
(169, 169, 2009, 6, 20092, 200906, 2009063, 20090618, 18, 3, 2, 'THU', 'JUN'),
(170, 170, 2009, 6, 20092, 200906, 2009063, 20090619, 19, 3, 2, 'FRI', 'JUN'),
(171, 171, 2009, 6, 20092, 200906, 2009063, 20090620, 20, 3, 2, 'SAT', 'JUN'),
(172, 172, 2009, 6, 20092, 200906, 2009063, 20090621, 21, 3, 2, 'SUN', 'JUN'),
(173, 173, 2009, 6, 20092, 200906, 2009064, 20090622, 22, 4, 2, 'MON', 'JUN'),
(174, 174, 2009, 6, 20092, 200906, 2009064, 20090623, 23, 4, 2, 'TUE', 'JUN'),
(175, 175, 2009, 6, 20092, 200906, 2009064, 20090624, 24, 4, 2, 'WED', 'JUN'),
(176, 176, 2009, 6, 20092, 200906, 2009064, 20090625, 25, 4, 2, 'THU', 'JUN'),
(177, 177, 2009, 6, 20092, 200906, 2009064, 20090626, 26, 4, 2, 'FRI', 'JUN'),
(178, 178, 2009, 6, 20092, 200906, 2009064, 20090627, 27, 4, 2, 'SAT', 'JUN'),
(179, 179, 2009, 6, 20092, 200906, 2009064, 20090628, 28, 4, 2, 'SUN', 'JUN'),
(180, 180, 2009, 6, 20092, 200906, 2009065, 20090629, 29, 5, 2, 'MON', 'JUN'),
(181, 181, 2009, 6, 20092, 200906, 2009065, 20090630, 30, 5, 2, 'TUE', 'JUN'),
(182, 182, 2009, 7, 20093, 200907, 2009071, 20090701, 1, 1, 3, 'WED', 'JUL'),
(183, 183, 2009, 7, 20093, 200907, 2009071, 20090702, 2, 1, 3, 'THU', 'JUL'),
(184, 184, 2009, 7, 20093, 200907, 2009071, 20090703, 3, 1, 3, 'FRI', 'JUL'),
(185, 185, 2009, 7, 20093, 200907, 2009071, 20090704, 4, 1, 3, 'SAT', 'JUL'),
(186, 186, 2009, 7, 20093, 200907, 2009071, 20090705, 5, 1, 3, 'SUN', 'JUL'),
(187, 187, 2009, 7, 20093, 200907, 2009071, 20090706, 6, 1, 3, 'MON', 'JUL'),
(188, 188, 2009, 7, 20093, 200907, 2009071, 20090707, 7, 1, 3, 'TUE', 'JUL'),
(189, 189, 2009, 7, 20093, 200907, 2009072, 20090708, 8, 2, 3, 'WED', 'JUL'),
(190, 190, 2009, 7, 20093, 200907, 2009072, 20090709, 9, 2, 3, 'THU', 'JUL'),
(191, 191, 2009, 7, 20093, 200907, 2009072, 20090710, 10, 2, 3, 'FRI', 'JUL'),
(192, 192, 2009, 7, 20093, 200907, 2009072, 20090711, 11, 2, 3, 'SAT', 'JUL'),
(193, 193, 2009, 7, 20093, 200907, 2009072, 20090712, 12, 2, 3, 'SUN', 'JUL'),
(194, 194, 2009, 7, 20093, 200907, 2009072, 20090713, 13, 2, 3, 'MON', 'JUL'),
(195, 195, 2009, 7, 20093, 200907, 2009072, 20090714, 14, 2, 3, 'TUE', 'JUL'),
(196, 196, 2009, 7, 20093, 200907, 2009073, 20090715, 15, 3, 3, 'WED', 'JUL'),
(197, 197, 2009, 7, 20093, 200907, 2009073, 20090716, 16, 3, 3, 'THU', 'JUL'),
(198, 198, 2009, 7, 20093, 200907, 2009073, 20090717, 17, 3, 3, 'FRI', 'JUL'),
(199, 199, 2009, 7, 20093, 200907, 2009073, 20090718, 18, 3, 3, 'SAT', 'JUL'),
(200, 200, 2009, 7, 20093, 200907, 2009073, 20090719, 19, 3, 3, 'SUN', 'JUL'),
(201, 201, 2009, 7, 20093, 200907, 2009073, 20090720, 20, 3, 3, 'MON', 'JUL'),
(202, 202, 2009, 7, 20093, 200907, 2009073, 20090721, 21, 3, 3, 'TUE', 'JUL'),
(203, 203, 2009, 7, 20093, 200907, 2009074, 20090722, 22, 4, 3, 'WED', 'JUL'),
(204, 204, 2009, 7, 20093, 200907, 2009074, 20090723, 23, 4, 3, 'THU', 'JUL'),
(205, 205, 2009, 7, 20093, 200907, 2009074, 20090724, 24, 4, 3, 'FRI', 'JUL'),
(206, 206, 2009, 7, 20093, 200907, 2009074, 20090725, 25, 4, 3, 'SAT', 'JUL'),
(207, 207, 2009, 7, 20093, 200907, 2009074, 20090726, 26, 4, 3, 'SUN', 'JUL'),
(208, 208, 2009, 7, 20093, 200907, 2009074, 20090727, 27, 4, 3, 'MON', 'JUL'),
(209, 209, 2009, 7, 20093, 200907, 2009074, 20090728, 28, 4, 3, 'TUE', 'JUL'),
(210, 210, 2009, 7, 20093, 200907, 2009075, 20090729, 29, 5, 3, 'WED', 'JUL'),
(211, 211, 2009, 7, 20093, 200907, 2009075, 20090730, 30, 5, 3, 'THU', 'JUL'),
(212, 212, 2009, 7, 20093, 200907, 2009075, 20090731, 31, 5, 3, 'FRI', 'JUL'),
(213, 213, 2009, 8, 20093, 200908, 2009081, 20090801, 1, 1, 3, 'SAT', 'AUG'),
(214, 214, 2009, 8, 20093, 200908, 2009081, 20090802, 2, 1, 3, 'SUN', 'AUG'),
(215, 215, 2009, 8, 20093, 200908, 2009081, 20090803, 3, 1, 3, 'MON', 'AUG'),
(216, 216, 2009, 8, 20093, 200908, 2009081, 20090804, 4, 1, 3, 'TUE', 'AUG'),
(217, 217, 2009, 8, 20093, 200908, 2009081, 20090805, 5, 1, 3, 'WED', 'AUG'),
(218, 218, 2009, 8, 20093, 200908, 2009081, 20090806, 6, 1, 3, 'THU', 'AUG'),
(219, 219, 2009, 8, 20093, 200908, 2009081, 20090807, 7, 1, 3, 'FRI', 'AUG'),
(220, 220, 2009, 8, 20093, 200908, 2009082, 20090808, 8, 2, 3, 'SAT', 'AUG'),
(221, 221, 2009, 8, 20093, 200908, 2009082, 20090809, 9, 2, 3, 'SUN', 'AUG'),
(222, 222, 2009, 8, 20093, 200908, 2009082, 20090810, 10, 2, 3, 'MON', 'AUG'),
(223, 223, 2009, 8, 20093, 200908, 2009082, 20090811, 11, 2, 3, 'TUE', 'AUG'),
(224, 224, 2009, 8, 20093, 200908, 2009082, 20090812, 12, 2, 3, 'WED', 'AUG'),
(225, 225, 2009, 8, 20093, 200908, 2009082, 20090813, 13, 2, 3, 'THU', 'AUG'),
(226, 226, 2009, 8, 20093, 200908, 2009082, 20090814, 14, 2, 3, 'FRI', 'AUG'),
(227, 227, 2009, 8, 20093, 200908, 2009083, 20090815, 15, 3, 3, 'SAT', 'AUG'),
(228, 228, 2009, 8, 20093, 200908, 2009083, 20090816, 16, 3, 3, 'SUN', 'AUG'),
(229, 229, 2009, 8, 20093, 200908, 2009083, 20090817, 17, 3, 3, 'MON', 'AUG'),
(230, 230, 2009, 8, 20093, 200908, 2009083, 20090818, 18, 3, 3, 'TUE', 'AUG'),
(231, 231, 2009, 8, 20093, 200908, 2009083, 20090819, 19, 3, 3, 'WED', 'AUG'),
(232, 232, 2009, 8, 20093, 200908, 2009083, 20090820, 20, 3, 3, 'THU', 'AUG'),
(233, 233, 2009, 8, 20093, 200908, 2009083, 20090821, 21, 3, 3, 'FRI', 'AUG'),
(234, 234, 2009, 8, 20093, 200908, 2009084, 20090822, 22, 4, 3, 'SAT', 'AUG'),
(235, 235, 2009, 8, 20093, 200908, 2009084, 20090823, 23, 4, 3, 'SUN', 'AUG'),
(236, 236, 2009, 8, 20093, 200908, 2009084, 20090824, 24, 4, 3, 'MON', 'AUG'),
(237, 237, 2009, 8, 20093, 200908, 2009084, 20090825, 25, 4, 3, 'TUE', 'AUG'),
(238, 238, 2009, 8, 20093, 200908, 2009084, 20090826, 26, 4, 3, 'WED', 'AUG'),
(239, 239, 2009, 8, 20093, 200908, 2009084, 20090827, 27, 4, 3, 'THU', 'AUG'),
(240, 240, 2009, 8, 20093, 200908, 2009084, 20090828, 28, 4, 3, 'FRI', 'AUG'),
(241, 241, 2009, 8, 20093, 200908, 2009085, 20090829, 29, 5, 3, 'SAT', 'AUG'),
(242, 242, 2009, 8, 20093, 200908, 2009085, 20090830, 30, 5, 3, 'SUN', 'AUG'),
(243, 243, 2009, 8, 20093, 200908, 2009085, 20090831, 31, 5, 3, 'MON', 'AUG'),
(244, 244, 2009, 9, 20093, 200909, 2009091, 20090901, 1, 1, 3, 'TUE', 'SEP'),
(245, 245, 2009, 9, 20093, 200909, 2009091, 20090902, 2, 1, 3, 'WED', 'SEP'),
(246, 246, 2009, 9, 20093, 200909, 2009091, 20090903, 3, 1, 3, 'THU', 'SEP'),
(247, 247, 2009, 9, 20093, 200909, 2009091, 20090904, 4, 1, 3, 'FRI', 'SEP'),
(248, 248, 2009, 9, 20093, 200909, 2009091, 20090905, 5, 1, 3, 'SAT', 'SEP'),
(249, 249, 2009, 9, 20093, 200909, 2009091, 20090906, 6, 1, 3, 'SUN', 'SEP'),
(250, 250, 2009, 9, 20093, 200909, 2009091, 20090907, 7, 1, 3, 'MON', 'SEP'),
(251, 251, 2009, 9, 20093, 200909, 2009092, 20090908, 8, 2, 3, 'TUE', 'SEP'),
(252, 252, 2009, 9, 20093, 200909, 2009092, 20090909, 9, 2, 3, 'WED', 'SEP'),
(253, 253, 2009, 9, 20093, 200909, 2009092, 20090910, 10, 2, 3, 'THU', 'SEP'),
(254, 254, 2009, 9, 20093, 200909, 2009092, 20090911, 11, 2, 3, 'FRI', 'SEP'),
(255, 255, 2009, 9, 20093, 200909, 2009092, 20090912, 12, 2, 3, 'SAT', 'SEP'),
(256, 256, 2009, 9, 20093, 200909, 2009092, 20090913, 13, 2, 3, 'SUN', 'SEP'),
(257, 257, 2009, 9, 20093, 200909, 2009092, 20090914, 14, 2, 3, 'MON', 'SEP'),
(258, 258, 2009, 9, 20093, 200909, 2009093, 20090915, 15, 3, 3, 'TUE', 'SEP'),
(259, 259, 2009, 9, 20093, 200909, 2009093, 20090916, 16, 3, 3, 'WED', 'SEP'),
(260, 260, 2009, 9, 20093, 200909, 2009093, 20090917, 17, 3, 3, 'THU', 'SEP'),
(261, 261, 2009, 9, 20093, 200909, 2009093, 20090918, 18, 3, 3, 'FRI', 'SEP'),
(262, 262, 2009, 9, 20093, 200909, 2009093, 20090919, 19, 3, 3, 'SAT', 'SEP'),
(263, 263, 2009, 9, 20093, 200909, 2009093, 20090920, 20, 3, 3, 'SUN', 'SEP'),
(264, 264, 2009, 9, 20093, 200909, 2009093, 20090921, 21, 3, 3, 'MON', 'SEP'),
(265, 265, 2009, 9, 20093, 200909, 2009094, 20090922, 22, 4, 3, 'TUE', 'SEP'),
(266, 266, 2009, 9, 20093, 200909, 2009094, 20090923, 23, 4, 3, 'WED', 'SEP'),
(267, 267, 2009, 9, 20093, 200909, 2009094, 20090924, 24, 4, 3, 'THU', 'SEP'),
(268, 268, 2009, 9, 20093, 200909, 2009094, 20090925, 25, 4, 3, 'FRI', 'SEP'),
(269, 269, 2009, 9, 20093, 200909, 2009094, 20090926, 26, 4, 3, 'SAT', 'SEP'),
(270, 270, 2009, 9, 20093, 200909, 2009094, 20090927, 27, 4, 3, 'SUN', 'SEP'),
(271, 271, 2009, 9, 20093, 200909, 2009094, 20090928, 28, 4, 3, 'MON', 'SEP'),
(272, 272, 2009, 9, 20093, 200909, 2009095, 20090929, 29, 5, 3, 'TUE', 'SEP'),
(273, 273, 2009, 9, 20093, 200909, 2009095, 20090930, 30, 5, 3, 'WED', 'SEP'),
(274, 274, 2009, 10, 20094, 200910, 2009101, 20091001, 1, 1, 4, 'THU', 'OCT'),
(275, 275, 2009, 10, 20094, 200910, 2009101, 20091002, 2, 1, 4, 'FRI', 'OCT'),
(276, 276, 2009, 10, 20094, 200910, 2009101, 20091003, 3, 1, 4, 'SAT', 'OCT'),
(277, 277, 2009, 10, 20094, 200910, 2009101, 20091004, 4, 1, 4, 'SUN', 'OCT'),
(278, 278, 2009, 10, 20094, 200910, 2009101, 20091005, 5, 1, 4, 'MON', 'OCT'),
(279, 279, 2009, 10, 20094, 200910, 2009101, 20091006, 6, 1, 4, 'TUE', 'OCT'),
(280, 280, 2009, 10, 20094, 200910, 2009101, 20091007, 7, 1, 4, 'WED', 'OCT'),
(281, 281, 2009, 10, 20094, 200910, 2009102, 20091008, 8, 2, 4, 'THU', 'OCT'),
(282, 282, 2009, 10, 20094, 200910, 2009102, 20091009, 9, 2, 4, 'FRI', 'OCT'),
(283, 283, 2009, 10, 20094, 200910, 2009102, 20091010, 10, 2, 4, 'SAT', 'OCT'),
(284, 284, 2009, 10, 20094, 200910, 2009102, 20091011, 11, 2, 4, 'SUN', 'OCT'),
(285, 285, 2009, 10, 20094, 200910, 2009102, 20091012, 12, 2, 4, 'MON', 'OCT'),
(286, 286, 2009, 10, 20094, 200910, 2009102, 20091013, 13, 2, 4, 'TUE', 'OCT'),
(287, 287, 2009, 10, 20094, 200910, 2009102, 20091014, 14, 2, 4, 'WED', 'OCT'),
(288, 288, 2009, 10, 20094, 200910, 2009103, 20091015, 15, 3, 4, 'THU', 'OCT'),
(289, 289, 2009, 10, 20094, 200910, 2009103, 20091016, 16, 3, 4, 'FRI', 'OCT'),
(290, 290, 2009, 10, 20094, 200910, 2009103, 20091017, 17, 3, 4, 'SAT', 'OCT'),
(291, 291, 2009, 10, 20094, 200910, 2009103, 20091018, 18, 3, 4, 'SUN', 'OCT'),
(292, 292, 2009, 10, 20094, 200910, 2009103, 20091019, 19, 3, 4, 'MON', 'OCT'),
(293, 293, 2009, 10, 20094, 200910, 2009103, 20091020, 20, 3, 4, 'TUE', 'OCT'),
(294, 294, 2009, 10, 20094, 200910, 2009103, 20091021, 21, 3, 4, 'WED', 'OCT'),
(295, 295, 2009, 10, 20094, 200910, 2009104, 20091022, 22, 4, 4, 'THU', 'OCT'),
(296, 296, 2009, 10, 20094, 200910, 2009104, 20091023, 23, 4, 4, 'FRI', 'OCT'),
(297, 297, 2009, 10, 20094, 200910, 2009104, 20091024, 24, 4, 4, 'SAT', 'OCT'),
(298, 298, 2009, 10, 20094, 200910, 2009104, 20091025, 25, 4, 4, 'SUN', 'OCT'),
(299, 299, 2009, 10, 20094, 200910, 2009104, 20091026, 26, 4, 4, 'MON', 'OCT'),
(300, 300, 2009, 10, 20094, 200910, 2009104, 20091027, 27, 4, 4, 'TUE', 'OCT'),
(301, 301, 2009, 10, 20094, 200910, 2009104, 20091028, 28, 4, 4, 'WED', 'OCT'),
(302, 302, 2009, 10, 20094, 200910, 2009105, 20091029, 29, 5, 4, 'THU', 'OCT'),
(303, 303, 2009, 10, 20094, 200910, 2009105, 20091030, 30, 5, 4, 'FRI', 'OCT'),
(304, 304, 2009, 10, 20094, 200910, 2009105, 20091031, 31, 5, 4, 'SAT', 'OCT'),
(305, 305, 2009, 11, 20094, 200911, 2009111, 20091101, 1, 1, 4, 'SUN', 'NOV'),
(306, 306, 2009, 11, 20094, 200911, 2009111, 20091102, 2, 1, 4, 'MON', 'NOV'),
(307, 307, 2009, 11, 20094, 200911, 2009111, 20091103, 3, 1, 4, 'TUE', 'NOV'),
(308, 308, 2009, 11, 20094, 200911, 2009111, 20091104, 4, 1, 4, 'WED', 'NOV'),
(309, 309, 2009, 11, 20094, 200911, 2009111, 20091105, 5, 1, 4, 'THU', 'NOV'),
(310, 310, 2009, 11, 20094, 200911, 2009111, 20091106, 6, 1, 4, 'FRI', 'NOV'),
(311, 311, 2009, 11, 20094, 200911, 2009111, 20091107, 7, 1, 4, 'SAT', 'NOV'),
(312, 312, 2009, 11, 20094, 200911, 2009112, 20091108, 8, 2, 4, 'SUN', 'NOV'),
(313, 313, 2009, 11, 20094, 200911, 2009112, 20091109, 9, 2, 4, 'MON', 'NOV'),
(314, 314, 2009, 11, 20094, 200911, 2009112, 20091110, 10, 2, 4, 'TUE', 'NOV'),
(315, 315, 2009, 11, 20094, 200911, 2009112, 20091111, 11, 2, 4, 'WED', 'NOV'),
(316, 316, 2009, 11, 20094, 200911, 2009112, 20091112, 12, 2, 4, 'THU', 'NOV'),
(317, 317, 2009, 11, 20094, 200911, 2009112, 20091113, 13, 2, 4, 'FRI', 'NOV'),
(318, 318, 2009, 11, 20094, 200911, 2009112, 20091114, 14, 2, 4, 'SAT', 'NOV'),
(319, 319, 2009, 11, 20094, 200911, 2009113, 20091115, 15, 3, 4, 'SUN', 'NOV'),
(320, 320, 2009, 11, 20094, 200911, 2009113, 20091116, 16, 3, 4, 'MON', 'NOV'),
(321, 321, 2009, 11, 20094, 200911, 2009113, 20091117, 17, 3, 4, 'TUE', 'NOV'),
(322, 322, 2009, 11, 20094, 200911, 2009113, 20091118, 18, 3, 4, 'WED', 'NOV'),
(323, 323, 2009, 11, 20094, 200911, 2009113, 20091119, 19, 3, 4, 'THU', 'NOV'),
(324, 324, 2009, 11, 20094, 200911, 2009113, 20091120, 20, 3, 4, 'FRI', 'NOV'),
(325, 325, 2009, 11, 20094, 200911, 2009113, 20091121, 21, 3, 4, 'SAT', 'NOV'),
(326, 326, 2009, 11, 20094, 200911, 2009114, 20091122, 22, 4, 4, 'SUN', 'NOV'),
(327, 327, 2009, 11, 20094, 200911, 2009114, 20091123, 23, 4, 4, 'MON', 'NOV'),
(328, 328, 2009, 11, 20094, 200911, 2009114, 20091124, 24, 4, 4, 'TUE', 'NOV'),
(329, 329, 2009, 11, 20094, 200911, 2009114, 20091125, 25, 4, 4, 'WED', 'NOV'),
(330, 330, 2009, 11, 20094, 200911, 2009114, 20091126, 26, 4, 4, 'THU', 'NOV'),
(331, 331, 2009, 11, 20094, 200911, 2009114, 20091127, 27, 4, 4, 'FRI', 'NOV'),
(332, 332, 2009, 11, 20094, 200911, 2009114, 20091128, 28, 4, 4, 'SAT', 'NOV'),
(333, 333, 2009, 11, 20094, 200911, 2009115, 20091129, 29, 5, 4, 'SUN', 'NOV'),
(334, 334, 2009, 11, 20094, 200911, 2009115, 20091130, 30, 5, 4, 'MON', 'NOV'),
(335, 335, 2009, 12, 20094, 200912, 2009121, 20091201, 1, 1, 4, 'TUE', 'DEC'),
(336, 336, 2009, 12, 20094, 200912, 2009121, 20091202, 2, 1, 4, 'WED', 'DEC'),
(337, 337, 2009, 12, 20094, 200912, 2009121, 20091203, 3, 1, 4, 'THU', 'DEC'),
(338, 338, 2009, 12, 20094, 200912, 2009121, 20091204, 4, 1, 4, 'FRI', 'DEC'),
(339, 339, 2009, 12, 20094, 200912, 2009121, 20091205, 5, 1, 4, 'SAT', 'DEC'),
(340, 340, 2009, 12, 20094, 200912, 2009121, 20091206, 6, 1, 4, 'SUN', 'DEC'),
(341, 341, 2009, 12, 20094, 200912, 2009121, 20091207, 7, 1, 4, 'MON', 'DEC'),
(342, 342, 2009, 12, 20094, 200912, 2009122, 20091208, 8, 2, 4, 'TUE', 'DEC'),
(343, 343, 2009, 12, 20094, 200912, 2009122, 20091209, 9, 2, 4, 'WED', 'DEC'),
(344, 344, 2009, 12, 20094, 200912, 2009122, 20091210, 10, 2, 4, 'THU', 'DEC'),
(345, 345, 2009, 12, 20094, 200912, 2009122, 20091211, 11, 2, 4, 'FRI', 'DEC'),
(346, 346, 2009, 12, 20094, 200912, 2009122, 20091212, 12, 2, 4, 'SAT', 'DEC'),
(347, 347, 2009, 12, 20094, 200912, 2009122, 20091213, 13, 2, 4, 'SUN', 'DEC'),
(348, 348, 2009, 12, 20094, 200912, 2009122, 20091214, 14, 2, 4, 'MON', 'DEC'),
(349, 349, 2009, 12, 20094, 200912, 2009123, 20091215, 15, 3, 4, 'TUE', 'DEC'),
(350, 350, 2009, 12, 20094, 200912, 2009123, 20091216, 16, 3, 4, 'WED', 'DEC'),
(351, 351, 2009, 12, 20094, 200912, 2009123, 20091217, 17, 3, 4, 'THU', 'DEC'),
(352, 352, 2009, 12, 20094, 200912, 2009123, 20091218, 18, 3, 4, 'FRI', 'DEC'),
(353, 353, 2009, 12, 20094, 200912, 2009123, 20091219, 19, 3, 4, 'SAT', 'DEC'),
(354, 354, 2009, 12, 20094, 200912, 2009123, 20091220, 20, 3, 4, 'SUN', 'DEC'),
(355, 355, 2009, 12, 20094, 200912, 2009123, 20091221, 21, 3, 4, 'MON', 'DEC'),
(356, 356, 2009, 12, 20094, 200912, 2009124, 20091222, 22, 4, 4, 'TUE', 'DEC'),
(357, 357, 2009, 12, 20094, 200912, 2009124, 20091223, 23, 4, 4, 'WED', 'DEC'),
(358, 358, 2009, 12, 20094, 200912, 2009124, 20091224, 24, 4, 4, 'THU', 'DEC'),
(359, 359, 2009, 12, 20094, 200912, 2009124, 20091225, 25, 4, 4, 'FRI', 'DEC'),
(360, 360, 2009, 12, 20094, 200912, 2009124, 20091226, 26, 4, 4, 'SAT', 'DEC'),
(361, 361, 2009, 12, 20094, 200912, 2009124, 20091227, 27, 4, 4, 'SUN', 'DEC'),
(362, 362, 2009, 12, 20094, 200912, 2009124, 20091228, 28, 4, 4, 'MON', 'DEC'),
(363, 363, 2009, 12, 20094, 200912, 2009125, 20091229, 29, 5, 4, 'TUE', 'DEC'),
(364, 364, 2009, 12, 20094, 200912, 2009125, 20091230, 30, 5, 4, 'WED', 'DEC'),
(365, 365, 2009, 12, 20094, 200912, 2009125, 20091231, 31, 5, 4, 'THU', 'DEC'),
(366, 1, 2010, 1, 20101, 201001, 2010011, 20100101, 1, 1, 1, 'FRI', 'JAN'),
(367, 2, 2010, 1, 20101, 201001, 2010011, 20100102, 2, 1, 1, 'SAT', 'JAN'),
(368, 3, 2010, 1, 20101, 201001, 2010011, 20100103, 3, 1, 1, 'SUN', 'JAN'),
(369, 4, 2010, 1, 20101, 201001, 2010011, 20100104, 4, 1, 1, 'MON', 'JAN'),
(370, 5, 2010, 1, 20101, 201001, 2010011, 20100105, 5, 1, 1, 'TUE', 'JAN'),
(371, 6, 2010, 1, 20101, 201001, 2010011, 20100106, 6, 1, 1, 'WED', 'JAN'),
(372, 7, 2010, 1, 20101, 201001, 2010011, 20100107, 7, 1, 1, 'THU', 'JAN'),
(373, 8, 2010, 1, 20101, 201001, 2010012, 20100108, 8, 2, 1, 'FRI', 'JAN'),
(374, 9, 2010, 1, 20101, 201001, 2010012, 20100109, 9, 2, 1, 'SAT', 'JAN'),
(375, 10, 2010, 1, 20101, 201001, 2010012, 20100110, 10, 2, 1, 'SUN', 'JAN'),
(376, 11, 2010, 1, 20101, 201001, 2010012, 20100111, 11, 2, 1, 'MON', 'JAN'),
(377, 12, 2010, 1, 20101, 201001, 2010012, 20100112, 12, 2, 1, 'TUE', 'JAN'),
(378, 13, 2010, 1, 20101, 201001, 2010012, 20100113, 13, 2, 1, 'WED', 'JAN'),
(379, 14, 2010, 1, 20101, 201001, 2010012, 20100114, 14, 2, 1, 'THU', 'JAN'),
(380, 15, 2010, 1, 20101, 201001, 2010013, 20100115, 15, 3, 1, 'FRI', 'JAN'),
(381, 16, 2010, 1, 20101, 201001, 2010013, 20100116, 16, 3, 1, 'SAT', 'JAN'),
(382, 17, 2010, 1, 20101, 201001, 2010013, 20100117, 17, 3, 1, 'SUN', 'JAN'),
(383, 18, 2010, 1, 20101, 201001, 2010013, 20100118, 18, 3, 1, 'MON', 'JAN'),
(384, 19, 2010, 1, 20101, 201001, 2010013, 20100119, 19, 3, 1, 'TUE', 'JAN'),
(385, 20, 2010, 1, 20101, 201001, 2010013, 20100120, 20, 3, 1, 'WED', 'JAN'),
(386, 21, 2010, 1, 20101, 201001, 2010013, 20100121, 21, 3, 1, 'THU', 'JAN'),
(387, 22, 2010, 1, 20101, 201001, 2010014, 20100122, 22, 4, 1, 'FRI', 'JAN'),
(388, 23, 2010, 1, 20101, 201001, 2010014, 20100123, 23, 4, 1, 'SAT', 'JAN'),
(389, 24, 2010, 1, 20101, 201001, 2010014, 20100124, 24, 4, 1, 'SUN', 'JAN'),
(390, 25, 2010, 1, 20101, 201001, 2010014, 20100125, 25, 4, 1, 'MON', 'JAN'),
(391, 26, 2010, 1, 20101, 201001, 2010014, 20100126, 26, 4, 1, 'TUE', 'JAN'),
(392, 27, 2010, 1, 20101, 201001, 2010014, 20100127, 27, 4, 1, 'WED', 'JAN'),
(393, 28, 2010, 1, 20101, 201001, 2010014, 20100128, 28, 4, 1, 'THU', 'JAN'),
(394, 29, 2010, 1, 20101, 201001, 2010015, 20100129, 29, 5, 1, 'FRI', 'JAN'),
(395, 30, 2010, 1, 20101, 201001, 2010015, 20100130, 30, 5, 1, 'SAT', 'JAN'),
(396, 31, 2010, 1, 20101, 201001, 2010015, 20100131, 31, 5, 1, 'SUN', 'JAN'),
(397, 32, 2010, 2, 20101, 201002, 2010021, 20100201, 1, 1, 1, 'MON', 'FEB'),
(398, 33, 2010, 2, 20101, 201002, 2010021, 20100202, 2, 1, 1, 'TUE', 'FEB'),
(399, 34, 2010, 2, 20101, 201002, 2010021, 20100203, 3, 1, 1, 'WED', 'FEB'),
(400, 35, 2010, 2, 20101, 201002, 2010021, 20100204, 4, 1, 1, 'THU', 'FEB'),
(401, 36, 2010, 2, 20101, 201002, 2010021, 20100205, 5, 1, 1, 'FRI', 'FEB'),
(402, 37, 2010, 2, 20101, 201002, 2010021, 20100206, 6, 1, 1, 'SAT', 'FEB'),
(403, 38, 2010, 2, 20101, 201002, 2010021, 20100207, 7, 1, 1, 'SUN', 'FEB'),
(404, 39, 2010, 2, 20101, 201002, 2010022, 20100208, 8, 2, 1, 'MON', 'FEB'),
(405, 40, 2010, 2, 20101, 201002, 2010022, 20100209, 9, 2, 1, 'TUE', 'FEB'),
(406, 41, 2010, 2, 20101, 201002, 2010022, 20100210, 10, 2, 1, 'WED', 'FEB'),
(407, 42, 2010, 2, 20101, 201002, 2010022, 20100211, 11, 2, 1, 'THU', 'FEB'),
(408, 43, 2010, 2, 20101, 201002, 2010022, 20100212, 12, 2, 1, 'FRI', 'FEB'),
(409, 44, 2010, 2, 20101, 201002, 2010022, 20100213, 13, 2, 1, 'SAT', 'FEB'),
(410, 45, 2010, 2, 20101, 201002, 2010022, 20100214, 14, 2, 1, 'SUN', 'FEB'),
(411, 46, 2010, 2, 20101, 201002, 2010023, 20100215, 15, 3, 1, 'MON', 'FEB'),
(412, 47, 2010, 2, 20101, 201002, 2010023, 20100216, 16, 3, 1, 'TUE', 'FEB'),
(413, 48, 2010, 2, 20101, 201002, 2010023, 20100217, 17, 3, 1, 'WED', 'FEB'),
(414, 49, 2010, 2, 20101, 201002, 2010023, 20100218, 18, 3, 1, 'THU', 'FEB'),
(415, 50, 2010, 2, 20101, 201002, 2010023, 20100219, 19, 3, 1, 'FRI', 'FEB'),
(416, 51, 2010, 2, 20101, 201002, 2010023, 20100220, 20, 3, 1, 'SAT', 'FEB'),
(417, 52, 2010, 2, 20101, 201002, 2010023, 20100221, 21, 3, 1, 'SUN', 'FEB'),
(418, 53, 2010, 2, 20101, 201002, 2010024, 20100222, 22, 4, 1, 'MON', 'FEB'),
(419, 54, 2010, 2, 20101, 201002, 2010024, 20100223, 23, 4, 1, 'TUE', 'FEB'),
(420, 55, 2010, 2, 20101, 201002, 2010024, 20100224, 24, 4, 1, 'WED', 'FEB'),
(421, 56, 2010, 2, 20101, 201002, 2010024, 20100225, 25, 4, 1, 'THU', 'FEB'),
(422, 57, 2010, 2, 20101, 201002, 2010024, 20100226, 26, 4, 1, 'FRI', 'FEB'),
(423, 58, 2010, 2, 20101, 201002, 2010024, 20100227, 27, 4, 1, 'SAT', 'FEB'),
(424, 59, 2010, 2, 20101, 201002, 2010024, 20100228, 28, 4, 1, 'SUN', 'FEB'),
(425, 60, 2010, 3, 20101, 201003, 2010031, 20100301, 1, 1, 1, 'MON', 'MAR'),
(426, 61, 2010, 3, 20101, 201003, 2010031, 20100302, 2, 1, 1, 'TUE', 'MAR'),
(427, 62, 2010, 3, 20101, 201003, 2010031, 20100303, 3, 1, 1, 'WED', 'MAR'),
(428, 63, 2010, 3, 20101, 201003, 2010031, 20100304, 4, 1, 1, 'THU', 'MAR'),
(429, 64, 2010, 3, 20101, 201003, 2010031, 20100305, 5, 1, 1, 'FRI', 'MAR'),
(430, 65, 2010, 3, 20101, 201003, 2010031, 20100306, 6, 1, 1, 'SAT', 'MAR'),
(431, 66, 2010, 3, 20101, 201003, 2010031, 20100307, 7, 1, 1, 'SUN', 'MAR'),
(432, 67, 2010, 3, 20101, 201003, 2010032, 20100308, 8, 2, 1, 'MON', 'MAR'),
(433, 68, 2010, 3, 20101, 201003, 2010032, 20100309, 9, 2, 1, 'TUE', 'MAR'),
(434, 69, 2010, 3, 20101, 201003, 2010032, 20100310, 10, 2, 1, 'WED', 'MAR'),
(435, 70, 2010, 3, 20101, 201003, 2010032, 20100311, 11, 2, 1, 'THU', 'MAR'),
(436, 71, 2010, 3, 20101, 201003, 2010032, 20100312, 12, 2, 1, 'FRI', 'MAR'),
(437, 72, 2010, 3, 20101, 201003, 2010032, 20100313, 13, 2, 1, 'SAT', 'MAR'),
(438, 73, 2010, 3, 20101, 201003, 2010032, 20100314, 14, 2, 1, 'SUN', 'MAR'),
(439, 74, 2010, 3, 20101, 201003, 2010033, 20100315, 15, 3, 1, 'MON', 'MAR'),
(440, 75, 2010, 3, 20101, 201003, 2010033, 20100316, 16, 3, 1, 'TUE', 'MAR'),
(441, 76, 2010, 3, 20101, 201003, 2010033, 20100317, 17, 3, 1, 'WED', 'MAR'),
(442, 77, 2010, 3, 20101, 201003, 2010033, 20100318, 18, 3, 1, 'THU', 'MAR'),
(443, 78, 2010, 3, 20101, 201003, 2010033, 20100319, 19, 3, 1, 'FRI', 'MAR'),
(444, 79, 2010, 3, 20101, 201003, 2010033, 20100320, 20, 3, 1, 'SAT', 'MAR'),
(445, 80, 2010, 3, 20101, 201003, 2010033, 20100321, 21, 3, 1, 'SUN', 'MAR'),
(446, 81, 2010, 3, 20101, 201003, 2010034, 20100322, 22, 4, 1, 'MON', 'MAR'),
(447, 82, 2010, 3, 20101, 201003, 2010034, 20100323, 23, 4, 1, 'TUE', 'MAR'),
(448, 83, 2010, 3, 20101, 201003, 2010034, 20100324, 24, 4, 1, 'WED', 'MAR'),
(449, 84, 2010, 3, 20101, 201003, 2010034, 20100325, 25, 4, 1, 'THU', 'MAR'),
(450, 85, 2010, 3, 20101, 201003, 2010034, 20100326, 26, 4, 1, 'FRI', 'MAR'),
(451, 86, 2010, 3, 20101, 201003, 2010034, 20100327, 27, 4, 1, 'SAT', 'MAR'),
(452, 87, 2010, 3, 20101, 201003, 2010034, 20100328, 28, 4, 1, 'SUN', 'MAR'),
(453, 88, 2010, 3, 20101, 201003, 2010035, 20100329, 29, 5, 1, 'MON', 'MAR'),
(454, 89, 2010, 3, 20101, 201003, 2010035, 20100330, 30, 5, 1, 'TUE', 'MAR'),
(455, 90, 2010, 3, 20101, 201003, 2010035, 20100331, 31, 5, 1, 'WED', 'MAR'),
(456, 91, 2010, 4, 20102, 201004, 2010041, 20100401, 1, 1, 2, 'THU', 'APR'),
(457, 92, 2010, 4, 20102, 201004, 2010041, 20100402, 2, 1, 2, 'FRI', 'APR'),
(458, 93, 2010, 4, 20102, 201004, 2010041, 20100403, 3, 1, 2, 'SAT', 'APR'),
(459, 94, 2010, 4, 20102, 201004, 2010041, 20100404, 4, 1, 2, 'SUN', 'APR'),
(460, 95, 2010, 4, 20102, 201004, 2010041, 20100405, 5, 1, 2, 'MON', 'APR'),
(461, 96, 2010, 4, 20102, 201004, 2010041, 20100406, 6, 1, 2, 'TUE', 'APR'),
(462, 97, 2010, 4, 20102, 201004, 2010041, 20100407, 7, 1, 2, 'WED', 'APR'),
(463, 98, 2010, 4, 20102, 201004, 2010042, 20100408, 8, 2, 2, 'THU', 'APR'),
(464, 99, 2010, 4, 20102, 201004, 2010042, 20100409, 9, 2, 2, 'FRI', 'APR'),
(465, 100, 2010, 4, 20102, 201004, 2010042, 20100410, 10, 2, 2, 'SAT', 'APR'),
(466, 101, 2010, 4, 20102, 201004, 2010042, 20100411, 11, 2, 2, 'SUN', 'APR'),
(467, 102, 2010, 4, 20102, 201004, 2010042, 20100412, 12, 2, 2, 'MON', 'APR'),
(468, 103, 2010, 4, 20102, 201004, 2010042, 20100413, 13, 2, 2, 'TUE', 'APR'),
(469, 104, 2010, 4, 20102, 201004, 2010042, 20100414, 14, 2, 2, 'WED', 'APR'),
(470, 105, 2010, 4, 20102, 201004, 2010043, 20100415, 15, 3, 2, 'THU', 'APR'),
(471, 106, 2010, 4, 20102, 201004, 2010043, 20100416, 16, 3, 2, 'FRI', 'APR'),
(472, 107, 2010, 4, 20102, 201004, 2010043, 20100417, 17, 3, 2, 'SAT', 'APR'),
(473, 108, 2010, 4, 20102, 201004, 2010043, 20100418, 18, 3, 2, 'SUN', 'APR'),
(474, 109, 2010, 4, 20102, 201004, 2010043, 20100419, 19, 3, 2, 'MON', 'APR'),
(475, 110, 2010, 4, 20102, 201004, 2010043, 20100420, 20, 3, 2, 'TUE', 'APR'),
(476, 111, 2010, 4, 20102, 201004, 2010043, 20100421, 21, 3, 2, 'WED', 'APR'),
(477, 112, 2010, 4, 20102, 201004, 2010044, 20100422, 22, 4, 2, 'THU', 'APR'),
(478, 113, 2010, 4, 20102, 201004, 2010044, 20100423, 23, 4, 2, 'FRI', 'APR'),
(479, 114, 2010, 4, 20102, 201004, 2010044, 20100424, 24, 4, 2, 'SAT', 'APR'),
(480, 115, 2010, 4, 20102, 201004, 2010044, 20100425, 25, 4, 2, 'SUN', 'APR'),
(481, 116, 2010, 4, 20102, 201004, 2010044, 20100426, 26, 4, 2, 'MON', 'APR'),
(482, 117, 2010, 4, 20102, 201004, 2010044, 20100427, 27, 4, 2, 'TUE', 'APR'),
(483, 118, 2010, 4, 20102, 201004, 2010044, 20100428, 28, 4, 2, 'WED', 'APR'),
(484, 119, 2010, 4, 20102, 201004, 2010045, 20100429, 29, 5, 2, 'THU', 'APR'),
(485, 120, 2010, 4, 20102, 201004, 2010045, 20100430, 30, 5, 2, 'FRI', 'APR'),
(486, 121, 2010, 5, 20102, 201005, 2010051, 20100501, 1, 1, 2, 'SAT', 'MAY'),
(487, 122, 2010, 5, 20102, 201005, 2010051, 20100502, 2, 1, 2, 'SUN', 'MAY'),
(488, 123, 2010, 5, 20102, 201005, 2010051, 20100503, 3, 1, 2, 'MON', 'MAY'),
(489, 124, 2010, 5, 20102, 201005, 2010051, 20100504, 4, 1, 2, 'TUE', 'MAY'),
(490, 125, 2010, 5, 20102, 201005, 2010051, 20100505, 5, 1, 2, 'WED', 'MAY'),
(491, 126, 2010, 5, 20102, 201005, 2010051, 20100506, 6, 1, 2, 'THU', 'MAY'),
(492, 127, 2010, 5, 20102, 201005, 2010051, 20100507, 7, 1, 2, 'FRI', 'MAY'),
(493, 128, 2010, 5, 20102, 201005, 2010052, 20100508, 8, 2, 2, 'SAT', 'MAY'),
(494, 129, 2010, 5, 20102, 201005, 2010052, 20100509, 9, 2, 2, 'SUN', 'MAY'),
(495, 130, 2010, 5, 20102, 201005, 2010052, 20100510, 10, 2, 2, 'MON', 'MAY'),
(496, 131, 2010, 5, 20102, 201005, 2010052, 20100511, 11, 2, 2, 'TUE', 'MAY'),
(497, 132, 2010, 5, 20102, 201005, 2010052, 20100512, 12, 2, 2, 'WED', 'MAY'),
(498, 133, 2010, 5, 20102, 201005, 2010052, 20100513, 13, 2, 2, 'THU', 'MAY'),
(499, 134, 2010, 5, 20102, 201005, 2010052, 20100514, 14, 2, 2, 'FRI', 'MAY'),
(500, 135, 2010, 5, 20102, 201005, 2010053, 20100515, 15, 3, 2, 'SAT', 'MAY'),
(501, 136, 2010, 5, 20102, 201005, 2010053, 20100516, 16, 3, 2, 'SUN', 'MAY'),
(502, 137, 2010, 5, 20102, 201005, 2010053, 20100517, 17, 3, 2, 'MON', 'MAY'),
(503, 138, 2010, 5, 20102, 201005, 2010053, 20100518, 18, 3, 2, 'TUE', 'MAY'),
(504, 139, 2010, 5, 20102, 201005, 2010053, 20100519, 19, 3, 2, 'WED', 'MAY'),
(505, 140, 2010, 5, 20102, 201005, 2010053, 20100520, 20, 3, 2, 'THU', 'MAY'),
(506, 141, 2010, 5, 20102, 201005, 2010053, 20100521, 21, 3, 2, 'FRI', 'MAY'),
(507, 142, 2010, 5, 20102, 201005, 2010054, 20100522, 22, 4, 2, 'SAT', 'MAY'),
(508, 143, 2010, 5, 20102, 201005, 2010054, 20100523, 23, 4, 2, 'SUN', 'MAY'),
(509, 144, 2010, 5, 20102, 201005, 2010054, 20100524, 24, 4, 2, 'MON', 'MAY'),
(510, 145, 2010, 5, 20102, 201005, 2010054, 20100525, 25, 4, 2, 'TUE', 'MAY'),
(511, 146, 2010, 5, 20102, 201005, 2010054, 20100526, 26, 4, 2, 'WED', 'MAY'),
(512, 147, 2010, 5, 20102, 201005, 2010054, 20100527, 27, 4, 2, 'THU', 'MAY'),
(513, 148, 2010, 5, 20102, 201005, 2010054, 20100528, 28, 4, 2, 'FRI', 'MAY'),
(514, 149, 2010, 5, 20102, 201005, 2010055, 20100529, 29, 5, 2, 'SAT', 'MAY'),
(515, 150, 2010, 5, 20102, 201005, 2010055, 20100530, 30, 5, 2, 'SUN', 'MAY'),
(516, 151, 2010, 5, 20102, 201005, 2010055, 20100531, 31, 5, 2, 'MON', 'MAY'),
(517, 152, 2010, 6, 20102, 201006, 2010061, 20100601, 1, 1, 2, 'TUE', 'JUN'),
(518, 153, 2010, 6, 20102, 201006, 2010061, 20100602, 2, 1, 2, 'WED', 'JUN'),
(519, 154, 2010, 6, 20102, 201006, 2010061, 20100603, 3, 1, 2, 'THU', 'JUN'),
(520, 155, 2010, 6, 20102, 201006, 2010061, 20100604, 4, 1, 2, 'FRI', 'JUN'),
(521, 156, 2010, 6, 20102, 201006, 2010061, 20100605, 5, 1, 2, 'SAT', 'JUN'),
(522, 157, 2010, 6, 20102, 201006, 2010061, 20100606, 6, 1, 2, 'SUN', 'JUN'),
(523, 158, 2010, 6, 20102, 201006, 2010061, 20100607, 7, 1, 2, 'MON', 'JUN'),
(524, 159, 2010, 6, 20102, 201006, 2010062, 20100608, 8, 2, 2, 'TUE', 'JUN'),
(525, 160, 2010, 6, 20102, 201006, 2010062, 20100609, 9, 2, 2, 'WED', 'JUN'),
(526, 161, 2010, 6, 20102, 201006, 2010062, 20100610, 10, 2, 2, 'THU', 'JUN'),
(527, 162, 2010, 6, 20102, 201006, 2010062, 20100611, 11, 2, 2, 'FRI', 'JUN'),
(528, 163, 2010, 6, 20102, 201006, 2010062, 20100612, 12, 2, 2, 'SAT', 'JUN'),
(529, 164, 2010, 6, 20102, 201006, 2010062, 20100613, 13, 2, 2, 'SUN', 'JUN'),
(530, 165, 2010, 6, 20102, 201006, 2010062, 20100614, 14, 2, 2, 'MON', 'JUN'),
(531, 166, 2010, 6, 20102, 201006, 2010063, 20100615, 15, 3, 2, 'TUE', 'JUN'),
(532, 167, 2010, 6, 20102, 201006, 2010063, 20100616, 16, 3, 2, 'WED', 'JUN'),
(533, 168, 2010, 6, 20102, 201006, 2010063, 20100617, 17, 3, 2, 'THU', 'JUN'),
(534, 169, 2010, 6, 20102, 201006, 2010063, 20100618, 18, 3, 2, 'FRI', 'JUN'),
(535, 170, 2010, 6, 20102, 201006, 2010063, 20100619, 19, 3, 2, 'SAT', 'JUN'),
(536, 171, 2010, 6, 20102, 201006, 2010063, 20100620, 20, 3, 2, 'SUN', 'JUN'),
(537, 172, 2010, 6, 20102, 201006, 2010063, 20100621, 21, 3, 2, 'MON', 'JUN'),
(538, 173, 2010, 6, 20102, 201006, 2010064, 20100622, 22, 4, 2, 'TUE', 'JUN'),
(539, 174, 2010, 6, 20102, 201006, 2010064, 20100623, 23, 4, 2, 'WED', 'JUN'),
(540, 175, 2010, 6, 20102, 201006, 2010064, 20100624, 24, 4, 2, 'THU', 'JUN'),
(541, 176, 2010, 6, 20102, 201006, 2010064, 20100625, 25, 4, 2, 'FRI', 'JUN'),
(542, 177, 2010, 6, 20102, 201006, 2010064, 20100626, 26, 4, 2, 'SAT', 'JUN'),
(543, 178, 2010, 6, 20102, 201006, 2010064, 20100627, 27, 4, 2, 'SUN', 'JUN'),
(544, 179, 2010, 6, 20102, 201006, 2010064, 20100628, 28, 4, 2, 'MON', 'JUN'),
(545, 180, 2010, 6, 20102, 201006, 2010065, 20100629, 29, 5, 2, 'TUE', 'JUN'),
(546, 181, 2010, 6, 20102, 201006, 2010065, 20100630, 30, 5, 2, 'WED', 'JUN'),
(547, 182, 2010, 7, 20103, 201007, 2010071, 20100701, 1, 1, 3, 'THU', 'JUL'),
(548, 183, 2010, 7, 20103, 201007, 2010071, 20100702, 2, 1, 3, 'FRI', 'JUL'),
(549, 184, 2010, 7, 20103, 201007, 2010071, 20100703, 3, 1, 3, 'SAT', 'JUL'),
(550, 185, 2010, 7, 20103, 201007, 2010071, 20100704, 4, 1, 3, 'SUN', 'JUL'),
(551, 186, 2010, 7, 20103, 201007, 2010071, 20100705, 5, 1, 3, 'MON', 'JUL'),
(552, 187, 2010, 7, 20103, 201007, 2010071, 20100706, 6, 1, 3, 'TUE', 'JUL'),
(553, 188, 2010, 7, 20103, 201007, 2010071, 20100707, 7, 1, 3, 'WED', 'JUL'),
(554, 189, 2010, 7, 20103, 201007, 2010072, 20100708, 8, 2, 3, 'THU', 'JUL'),
(555, 190, 2010, 7, 20103, 201007, 2010072, 20100709, 9, 2, 3, 'FRI', 'JUL'),
(556, 191, 2010, 7, 20103, 201007, 2010072, 20100710, 10, 2, 3, 'SAT', 'JUL'),
(557, 192, 2010, 7, 20103, 201007, 2010072, 20100711, 11, 2, 3, 'SUN', 'JUL'),
(558, 193, 2010, 7, 20103, 201007, 2010072, 20100712, 12, 2, 3, 'MON', 'JUL'),
(559, 194, 2010, 7, 20103, 201007, 2010072, 20100713, 13, 2, 3, 'TUE', 'JUL'),
(560, 195, 2010, 7, 20103, 201007, 2010072, 20100714, 14, 2, 3, 'WED', 'JUL'),
(561, 196, 2010, 7, 20103, 201007, 2010073, 20100715, 15, 3, 3, 'THU', 'JUL'),
(562, 197, 2010, 7, 20103, 201007, 2010073, 20100716, 16, 3, 3, 'FRI', 'JUL'),
(563, 198, 2010, 7, 20103, 201007, 2010073, 20100717, 17, 3, 3, 'SAT', 'JUL'),
(564, 199, 2010, 7, 20103, 201007, 2010073, 20100718, 18, 3, 3, 'SUN', 'JUL'),
(565, 200, 2010, 7, 20103, 201007, 2010073, 20100719, 19, 3, 3, 'MON', 'JUL'),
(566, 201, 2010, 7, 20103, 201007, 2010073, 20100720, 20, 3, 3, 'TUE', 'JUL'),
(567, 202, 2010, 7, 20103, 201007, 2010073, 20100721, 21, 3, 3, 'WED', 'JUL'),
(568, 203, 2010, 7, 20103, 201007, 2010074, 20100722, 22, 4, 3, 'THU', 'JUL'),
(569, 204, 2010, 7, 20103, 201007, 2010074, 20100723, 23, 4, 3, 'FRI', 'JUL'),
(570, 205, 2010, 7, 20103, 201007, 2010074, 20100724, 24, 4, 3, 'SAT', 'JUL'),
(571, 206, 2010, 7, 20103, 201007, 2010074, 20100725, 25, 4, 3, 'SUN', 'JUL'),
(572, 207, 2010, 7, 20103, 201007, 2010074, 20100726, 26, 4, 3, 'MON', 'JUL'),
(573, 208, 2010, 7, 20103, 201007, 2010074, 20100727, 27, 4, 3, 'TUE', 'JUL'),
(574, 209, 2010, 7, 20103, 201007, 2010074, 20100728, 28, 4, 3, 'WED', 'JUL'),
(575, 210, 2010, 7, 20103, 201007, 2010075, 20100729, 29, 5, 3, 'THU', 'JUL'),
(576, 211, 2010, 7, 20103, 201007, 2010075, 20100730, 30, 5, 3, 'FRI', 'JUL'),
(577, 212, 2010, 7, 20103, 201007, 2010075, 20100731, 31, 5, 3, 'SAT', 'JUL'),
(578, 213, 2010, 8, 20103, 201008, 2010081, 20100801, 1, 1, 3, 'SUN', 'AUG'),
(579, 214, 2010, 8, 20103, 201008, 2010081, 20100802, 2, 1, 3, 'MON', 'AUG'),
(580, 215, 2010, 8, 20103, 201008, 2010081, 20100803, 3, 1, 3, 'TUE', 'AUG'),
(581, 216, 2010, 8, 20103, 201008, 2010081, 20100804, 4, 1, 3, 'WED', 'AUG'),
(582, 217, 2010, 8, 20103, 201008, 2010081, 20100805, 5, 1, 3, 'THU', 'AUG'),
(583, 218, 2010, 8, 20103, 201008, 2010081, 20100806, 6, 1, 3, 'FRI', 'AUG'),
(584, 219, 2010, 8, 20103, 201008, 2010081, 20100807, 7, 1, 3, 'SAT', 'AUG'),
(585, 220, 2010, 8, 20103, 201008, 2010082, 20100808, 8, 2, 3, 'SUN', 'AUG'),
(586, 221, 2010, 8, 20103, 201008, 2010082, 20100809, 9, 2, 3, 'MON', 'AUG'),
(587, 222, 2010, 8, 20103, 201008, 2010082, 20100810, 10, 2, 3, 'TUE', 'AUG'),
(588, 223, 2010, 8, 20103, 201008, 2010082, 20100811, 11, 2, 3, 'WED', 'AUG'),
(589, 224, 2010, 8, 20103, 201008, 2010082, 20100812, 12, 2, 3, 'THU', 'AUG'),
(590, 225, 2010, 8, 20103, 201008, 2010082, 20100813, 13, 2, 3, 'FRI', 'AUG'),
(591, 226, 2010, 8, 20103, 201008, 2010082, 20100814, 14, 2, 3, 'SAT', 'AUG'),
(592, 227, 2010, 8, 20103, 201008, 2010083, 20100815, 15, 3, 3, 'SUN', 'AUG'),
(593, 228, 2010, 8, 20103, 201008, 2010083, 20100816, 16, 3, 3, 'MON', 'AUG'),
(594, 229, 2010, 8, 20103, 201008, 2010083, 20100817, 17, 3, 3, 'TUE', 'AUG'),
(595, 230, 2010, 8, 20103, 201008, 2010083, 20100818, 18, 3, 3, 'WED', 'AUG'),
(596, 231, 2010, 8, 20103, 201008, 2010083, 20100819, 19, 3, 3, 'THU', 'AUG'),
(597, 232, 2010, 8, 20103, 201008, 2010083, 20100820, 20, 3, 3, 'FRI', 'AUG'),
(598, 233, 2010, 8, 20103, 201008, 2010083, 20100821, 21, 3, 3, 'SAT', 'AUG'),
(599, 234, 2010, 8, 20103, 201008, 2010084, 20100822, 22, 4, 3, 'SUN', 'AUG'),
(600, 235, 2010, 8, 20103, 201008, 2010084, 20100823, 23, 4, 3, 'MON', 'AUG'),
(601, 236, 2010, 8, 20103, 201008, 2010084, 20100824, 24, 4, 3, 'TUE', 'AUG'),
(602, 237, 2010, 8, 20103, 201008, 2010084, 20100825, 25, 4, 3, 'WED', 'AUG'),
(603, 238, 2010, 8, 20103, 201008, 2010084, 20100826, 26, 4, 3, 'THU', 'AUG'),
(604, 239, 2010, 8, 20103, 201008, 2010084, 20100827, 27, 4, 3, 'FRI', 'AUG'),
(605, 240, 2010, 8, 20103, 201008, 2010084, 20100828, 28, 4, 3, 'SAT', 'AUG'),
(606, 241, 2010, 8, 20103, 201008, 2010085, 20100829, 29, 5, 3, 'SUN', 'AUG'),
(607, 242, 2010, 8, 20103, 201008, 2010085, 20100830, 30, 5, 3, 'MON', 'AUG'),
(608, 243, 2010, 8, 20103, 201008, 2010085, 20100831, 31, 5, 3, 'TUE', 'AUG'),
(609, 244, 2010, 9, 20103, 201009, 2010091, 20100901, 1, 1, 3, 'WED', 'SEP'),
(610, 245, 2010, 9, 20103, 201009, 2010091, 20100902, 2, 1, 3, 'THU', 'SEP'),
(611, 246, 2010, 9, 20103, 201009, 2010091, 20100903, 3, 1, 3, 'FRI', 'SEP'),
(612, 247, 2010, 9, 20103, 201009, 2010091, 20100904, 4, 1, 3, 'SAT', 'SEP'),
(613, 248, 2010, 9, 20103, 201009, 2010091, 20100905, 5, 1, 3, 'SUN', 'SEP'),
(614, 249, 2010, 9, 20103, 201009, 2010091, 20100906, 6, 1, 3, 'MON', 'SEP'),
(615, 250, 2010, 9, 20103, 201009, 2010091, 20100907, 7, 1, 3, 'TUE', 'SEP'),
(616, 251, 2010, 9, 20103, 201009, 2010092, 20100908, 8, 2, 3, 'WED', 'SEP'),
(617, 252, 2010, 9, 20103, 201009, 2010092, 20100909, 9, 2, 3, 'THU', 'SEP'),
(618, 253, 2010, 9, 20103, 201009, 2010092, 20100910, 10, 2, 3, 'FRI', 'SEP'),
(619, 254, 2010, 9, 20103, 201009, 2010092, 20100911, 11, 2, 3, 'SAT', 'SEP'),
(620, 255, 2010, 9, 20103, 201009, 2010092, 20100912, 12, 2, 3, 'SUN', 'SEP'),
(621, 256, 2010, 9, 20103, 201009, 2010092, 20100913, 13, 2, 3, 'MON', 'SEP'),
(622, 257, 2010, 9, 20103, 201009, 2010092, 20100914, 14, 2, 3, 'TUE', 'SEP'),
(623, 258, 2010, 9, 20103, 201009, 2010093, 20100915, 15, 3, 3, 'WED', 'SEP'),
(624, 259, 2010, 9, 20103, 201009, 2010093, 20100916, 16, 3, 3, 'THU', 'SEP'),
(625, 260, 2010, 9, 20103, 201009, 2010093, 20100917, 17, 3, 3, 'FRI', 'SEP'),
(626, 261, 2010, 9, 20103, 201009, 2010093, 20100918, 18, 3, 3, 'SAT', 'SEP'),
(627, 262, 2010, 9, 20103, 201009, 2010093, 20100919, 19, 3, 3, 'SUN', 'SEP'),
(628, 263, 2010, 9, 20103, 201009, 2010093, 20100920, 20, 3, 3, 'MON', 'SEP'),
(629, 264, 2010, 9, 20103, 201009, 2010093, 20100921, 21, 3, 3, 'TUE', 'SEP'),
(630, 265, 2010, 9, 20103, 201009, 2010094, 20100922, 22, 4, 3, 'WED', 'SEP'),
(631, 266, 2010, 9, 20103, 201009, 2010094, 20100923, 23, 4, 3, 'THU', 'SEP'),
(632, 267, 2010, 9, 20103, 201009, 2010094, 20100924, 24, 4, 3, 'FRI', 'SEP'),
(633, 268, 2010, 9, 20103, 201009, 2010094, 20100925, 25, 4, 3, 'SAT', 'SEP'),
(634, 269, 2010, 9, 20103, 201009, 2010094, 20100926, 26, 4, 3, 'SUN', 'SEP'),
(635, 270, 2010, 9, 20103, 201009, 2010094, 20100927, 27, 4, 3, 'MON', 'SEP'),
(636, 271, 2010, 9, 20103, 201009, 2010094, 20100928, 28, 4, 3, 'TUE', 'SEP'),
(637, 272, 2010, 9, 20103, 201009, 2010095, 20100929, 29, 5, 3, 'WED', 'SEP'),
(638, 273, 2010, 9, 20103, 201009, 2010095, 20100930, 30, 5, 3, 'THU', 'SEP'),
(639, 274, 2010, 10, 20104, 201010, 2010101, 20101001, 1, 1, 4, 'FRI', 'OCT'),
(640, 275, 2010, 10, 20104, 201010, 2010101, 20101002, 2, 1, 4, 'SAT', 'OCT'),
(641, 276, 2010, 10, 20104, 201010, 2010101, 20101003, 3, 1, 4, 'SUN', 'OCT'),
(642, 277, 2010, 10, 20104, 201010, 2010101, 20101004, 4, 1, 4, 'MON', 'OCT'),
(643, 278, 2010, 10, 20104, 201010, 2010101, 20101005, 5, 1, 4, 'TUE', 'OCT'),
(644, 279, 2010, 10, 20104, 201010, 2010101, 20101006, 6, 1, 4, 'WED', 'OCT'),
(645, 280, 2010, 10, 20104, 201010, 2010101, 20101007, 7, 1, 4, 'THU', 'OCT'),
(646, 281, 2010, 10, 20104, 201010, 2010102, 20101008, 8, 2, 4, 'FRI', 'OCT'),
(647, 282, 2010, 10, 20104, 201010, 2010102, 20101009, 9, 2, 4, 'SAT', 'OCT'),
(648, 283, 2010, 10, 20104, 201010, 2010102, 20101010, 10, 2, 4, 'SUN', 'OCT'),
(649, 284, 2010, 10, 20104, 201010, 2010102, 20101011, 11, 2, 4, 'MON', 'OCT'),
(650, 285, 2010, 10, 20104, 201010, 2010102, 20101012, 12, 2, 4, 'TUE', 'OCT'),
(651, 286, 2010, 10, 20104, 201010, 2010102, 20101013, 13, 2, 4, 'WED', 'OCT');
INSERT INTO `date_dim` (`DATE_ID`, `DAY_OF_YEAR`, `YEAR`, `MONTH`, `YEAR_QUARTER`, `YEAR_AND_MONTH`, `YEAR_MONTH_WEEK`, `YEAR_MONTH_DATE`, `DAY_OF_MONTH`, `WEEK_NBR`, `QUARTER_NBR`, `DAY_NAME`, `MONTH_NAME`) VALUES
(652, 287, 2010, 10, 20104, 201010, 2010102, 20101014, 14, 2, 4, 'THU', 'OCT'),
(653, 288, 2010, 10, 20104, 201010, 2010103, 20101015, 15, 3, 4, 'FRI', 'OCT'),
(654, 289, 2010, 10, 20104, 201010, 2010103, 20101016, 16, 3, 4, 'SAT', 'OCT'),
(655, 290, 2010, 10, 20104, 201010, 2010103, 20101017, 17, 3, 4, 'SUN', 'OCT'),
(656, 291, 2010, 10, 20104, 201010, 2010103, 20101018, 18, 3, 4, 'MON', 'OCT'),
(657, 292, 2010, 10, 20104, 201010, 2010103, 20101019, 19, 3, 4, 'TUE', 'OCT'),
(658, 293, 2010, 10, 20104, 201010, 2010103, 20101020, 20, 3, 4, 'WED', 'OCT'),
(659, 294, 2010, 10, 20104, 201010, 2010103, 20101021, 21, 3, 4, 'THU', 'OCT'),
(660, 295, 2010, 10, 20104, 201010, 2010104, 20101022, 22, 4, 4, 'FRI', 'OCT'),
(661, 296, 2010, 10, 20104, 201010, 2010104, 20101023, 23, 4, 4, 'SAT', 'OCT'),
(662, 297, 2010, 10, 20104, 201010, 2010104, 20101024, 24, 4, 4, 'SUN', 'OCT'),
(663, 298, 2010, 10, 20104, 201010, 2010104, 20101025, 25, 4, 4, 'MON', 'OCT'),
(664, 299, 2010, 10, 20104, 201010, 2010104, 20101026, 26, 4, 4, 'TUE', 'OCT'),
(665, 300, 2010, 10, 20104, 201010, 2010104, 20101027, 27, 4, 4, 'WED', 'OCT'),
(666, 301, 2010, 10, 20104, 201010, 2010104, 20101028, 28, 4, 4, 'THU', 'OCT'),
(667, 302, 2010, 10, 20104, 201010, 2010105, 20101029, 29, 5, 4, 'FRI', 'OCT'),
(668, 303, 2010, 10, 20104, 201010, 2010105, 20101030, 30, 5, 4, 'SAT', 'OCT'),
(669, 304, 2010, 10, 20104, 201010, 2010105, 20101031, 31, 5, 4, 'SUN', 'OCT'),
(670, 305, 2010, 11, 20104, 201011, 2010111, 20101101, 1, 1, 4, 'MON', 'NOV'),
(671, 306, 2010, 11, 20104, 201011, 2010111, 20101102, 2, 1, 4, 'TUE', 'NOV'),
(672, 307, 2010, 11, 20104, 201011, 2010111, 20101103, 3, 1, 4, 'WED', 'NOV'),
(673, 308, 2010, 11, 20104, 201011, 2010111, 20101104, 4, 1, 4, 'THU', 'NOV'),
(674, 309, 2010, 11, 20104, 201011, 2010111, 20101105, 5, 1, 4, 'FRI', 'NOV'),
(675, 310, 2010, 11, 20104, 201011, 2010111, 20101106, 6, 1, 4, 'SAT', 'NOV'),
(676, 311, 2010, 11, 20104, 201011, 2010111, 20101107, 7, 1, 4, 'SUN', 'NOV'),
(677, 312, 2010, 11, 20104, 201011, 2010112, 20101108, 8, 2, 4, 'MON', 'NOV'),
(678, 313, 2010, 11, 20104, 201011, 2010112, 20101109, 9, 2, 4, 'TUE', 'NOV'),
(679, 314, 2010, 11, 20104, 201011, 2010112, 20101110, 10, 2, 4, 'WED', 'NOV'),
(680, 315, 2010, 11, 20104, 201011, 2010112, 20101111, 11, 2, 4, 'THU', 'NOV'),
(681, 316, 2010, 11, 20104, 201011, 2010112, 20101112, 12, 2, 4, 'FRI', 'NOV'),
(682, 317, 2010, 11, 20104, 201011, 2010112, 20101113, 13, 2, 4, 'SAT', 'NOV'),
(683, 318, 2010, 11, 20104, 201011, 2010112, 20101114, 14, 2, 4, 'SUN', 'NOV'),
(684, 319, 2010, 11, 20104, 201011, 2010113, 20101115, 15, 3, 4, 'MON', 'NOV'),
(685, 320, 2010, 11, 20104, 201011, 2010113, 20101116, 16, 3, 4, 'TUE', 'NOV'),
(686, 321, 2010, 11, 20104, 201011, 2010113, 20101117, 17, 3, 4, 'WED', 'NOV'),
(687, 322, 2010, 11, 20104, 201011, 2010113, 20101118, 18, 3, 4, 'THU', 'NOV'),
(688, 323, 2010, 11, 20104, 201011, 2010113, 20101119, 19, 3, 4, 'FRI', 'NOV'),
(689, 324, 2010, 11, 20104, 201011, 2010113, 20101120, 20, 3, 4, 'SAT', 'NOV'),
(690, 325, 2010, 11, 20104, 201011, 2010113, 20101121, 21, 3, 4, 'SUN', 'NOV'),
(691, 326, 2010, 11, 20104, 201011, 2010114, 20101122, 22, 4, 4, 'MON', 'NOV'),
(692, 327, 2010, 11, 20104, 201011, 2010114, 20101123, 23, 4, 4, 'TUE', 'NOV'),
(693, 328, 2010, 11, 20104, 201011, 2010114, 20101124, 24, 4, 4, 'WED', 'NOV'),
(694, 329, 2010, 11, 20104, 201011, 2010114, 20101125, 25, 4, 4, 'THU', 'NOV'),
(695, 330, 2010, 11, 20104, 201011, 2010114, 20101126, 26, 4, 4, 'FRI', 'NOV'),
(696, 331, 2010, 11, 20104, 201011, 2010114, 20101127, 27, 4, 4, 'SAT', 'NOV'),
(697, 332, 2010, 11, 20104, 201011, 2010114, 20101128, 28, 4, 4, 'SUN', 'NOV'),
(698, 333, 2010, 11, 20104, 201011, 2010115, 20101129, 29, 5, 4, 'MON', 'NOV'),
(699, 334, 2010, 11, 20104, 201011, 2010115, 20101130, 30, 5, 4, 'TUE', 'NOV'),
(700, 335, 2010, 12, 20104, 201012, 2010121, 20101201, 1, 1, 4, 'WED', 'DEC'),
(701, 336, 2010, 12, 20104, 201012, 2010121, 20101202, 2, 1, 4, 'THU', 'DEC'),
(702, 337, 2010, 12, 20104, 201012, 2010121, 20101203, 3, 1, 4, 'FRI', 'DEC'),
(703, 338, 2010, 12, 20104, 201012, 2010121, 20101204, 4, 1, 4, 'SAT', 'DEC'),
(704, 339, 2010, 12, 20104, 201012, 2010121, 20101205, 5, 1, 4, 'SUN', 'DEC'),
(705, 340, 2010, 12, 20104, 201012, 2010121, 20101206, 6, 1, 4, 'MON', 'DEC'),
(706, 341, 2010, 12, 20104, 201012, 2010121, 20101207, 7, 1, 4, 'TUE', 'DEC'),
(707, 342, 2010, 12, 20104, 201012, 2010122, 20101208, 8, 2, 4, 'WED', 'DEC'),
(708, 343, 2010, 12, 20104, 201012, 2010122, 20101209, 9, 2, 4, 'THU', 'DEC'),
(709, 344, 2010, 12, 20104, 201012, 2010122, 20101210, 10, 2, 4, 'FRI', 'DEC'),
(710, 345, 2010, 12, 20104, 201012, 2010122, 20101211, 11, 2, 4, 'SAT', 'DEC'),
(711, 346, 2010, 12, 20104, 201012, 2010122, 20101212, 12, 2, 4, 'SUN', 'DEC'),
(712, 347, 2010, 12, 20104, 201012, 2010122, 20101213, 13, 2, 4, 'MON', 'DEC'),
(713, 348, 2010, 12, 20104, 201012, 2010122, 20101214, 14, 2, 4, 'TUE', 'DEC'),
(714, 349, 2010, 12, 20104, 201012, 2010123, 20101215, 15, 3, 4, 'WED', 'DEC'),
(715, 350, 2010, 12, 20104, 201012, 2010123, 20101216, 16, 3, 4, 'THU', 'DEC'),
(716, 351, 2010, 12, 20104, 201012, 2010123, 20101217, 17, 3, 4, 'FRI', 'DEC'),
(717, 352, 2010, 12, 20104, 201012, 2010123, 20101218, 18, 3, 4, 'SAT', 'DEC'),
(718, 353, 2010, 12, 20104, 201012, 2010123, 20101219, 19, 3, 4, 'SUN', 'DEC'),
(719, 354, 2010, 12, 20104, 201012, 2010123, 20101220, 20, 3, 4, 'MON', 'DEC'),
(720, 355, 2010, 12, 20104, 201012, 2010123, 20101221, 21, 3, 4, 'TUE', 'DEC'),
(721, 356, 2010, 12, 20104, 201012, 2010124, 20101222, 22, 4, 4, 'WED', 'DEC'),
(722, 357, 2010, 12, 20104, 201012, 2010124, 20101223, 23, 4, 4, 'THU', 'DEC'),
(723, 358, 2010, 12, 20104, 201012, 2010124, 20101224, 24, 4, 4, 'FRI', 'DEC'),
(724, 359, 2010, 12, 20104, 201012, 2010124, 20101225, 25, 4, 4, 'SAT', 'DEC'),
(725, 360, 2010, 12, 20104, 201012, 2010124, 20101226, 26, 4, 4, 'SUN', 'DEC'),
(726, 361, 2010, 12, 20104, 201012, 2010124, 20101227, 27, 4, 4, 'MON', 'DEC'),
(727, 362, 2010, 12, 20104, 201012, 2010124, 20101228, 28, 4, 4, 'TUE', 'DEC'),
(728, 363, 2010, 12, 20104, 201012, 2010125, 20101229, 29, 5, 4, 'WED', 'DEC'),
(729, 364, 2010, 12, 20104, 201012, 2010125, 20101230, 30, 5, 4, 'THU', 'DEC'),
(730, 365, 2010, 12, 20104, 201012, 2010125, 20101231, 31, 5, 4, 'FRI', 'DEC');

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

--
-- Dumping data for table `default_reminders`
--


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

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`DEPARTMENT_CODE`, `DEPARTMENT_NAME`, `ACCOUNT_NUMBER`, `PROFIT_CENTER_CODE`, `ACTIVE`, `CREATED_DTM`, `CREATED_BY`, `LAST_MODIFIED_DTM`, `MODIFIED_BY`) VALUES
('ADMIN', 'Administrative', NULL, NULL, 1, NULL, NULL, NULL, NULL),
('ANESTHESIOLOGY', 'Anesthesiology', NULL, NULL, 1, NULL, NULL, NULL, NULL),
('CARDIOLOGY', 'Cardiology', NULL, NULL, 1, NULL, NULL, NULL, NULL),
('DENTAL', 'Dental', NULL, NULL, 1, NULL, NULL, NULL, NULL),
('ENDOCRINOLOGY', 'Endocrinology', NULL, NULL, 1, NULL, NULL, NULL, NULL),
('ENT', 'ENT', NULL, NULL, 1, NULL, NULL, NULL, NULL),
('EYE', 'Eye', NULL, NULL, 1, NULL, NULL, NULL, NULL),
('GASTROENTEROLOGY', 'Gastroenterology ', NULL, NULL, 1, NULL, NULL, NULL, NULL),
('GYN_OBS', 'Gynaecology and Obstetrics ', NULL, NULL, 1, NULL, NULL, NULL, NULL),
('MEDICINE', 'Medicine', NULL, NULL, 1, NULL, NULL, NULL, NULL),
('NEPHROLOGY', 'Nephrology', NULL, NULL, 1, NULL, NULL, NULL, NULL),
('NEUROLOGY', 'Neurology', NULL, NULL, 1, NULL, NULL, NULL, NULL),
('ONCOLOGY', 'Oncology', NULL, NULL, 1, NULL, NULL, NULL, NULL),
('ORTHOPEDICS', 'Orthopedics', NULL, NULL, 1, NULL, NULL, NULL, NULL),
('PATHOLOGY', 'Pathology', NULL, NULL, 1, NULL, NULL, NULL, NULL),
('PEDIATRICS', 'Pediatrics', NULL, NULL, 1, NULL, NULL, NULL, NULL),
('PSYCHIATRY', 'Psychiatry', NULL, NULL, 1, NULL, NULL, NULL, NULL),
('RADIOLOGY_IMAGING', 'Radiology & Imaging', NULL, NULL, 1, NULL, NULL, NULL, NULL),
('RHEUMATOLOGY', 'Rheumatology', NULL, NULL, 1, NULL, NULL, NULL, NULL),
('SKIN_VD', 'Skin & V.D.', NULL, NULL, 1, NULL, NULL, NULL, NULL),
('SURGERY', 'Surgery', NULL, NULL, 1, NULL, NULL, NULL, NULL),
('UROLOGY', 'Urology', NULL, NULL, 1, NULL, NULL, NULL, NULL);

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

--
-- Dumping data for table `dept_especiality_assoc`
--

INSERT INTO `dept_especiality_assoc` (`DEPARTMENT_CODE`, `ESPECIALITY_CODE`, `ACTIVE`, `LAST_MODIFIED_DTM`, `USER_ID`, `VERSION`) VALUES
('CARDIOLOGY', 'CARDIOLOGISTS', 'Y', '2010-01-09 19:54:04', 'vinay kurudi', 0),
('NEUROLOGY', 'NEUROLOGISTS', 'Y', '2010-01-09 19:54:54', 'vinaykurudi', 0),
('PEDIATRICS', 'PEDIATRICIANS', 'Y', '2010-01-09 19:54:26', 'vinay kurudi', 0),
('RADIOLOGY_IMAGING', 'RADIOLOGISTS', 'Y', '2010-01-09 19:55:36', 'vinay kurudi', 0);

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

--
-- Dumping data for table `discharge_order`
--


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

--
-- Dumping data for table `discharge_type`
--

INSERT INTO `discharge_type` (`DISCHARGE_TYPE_CD`, `DESCRIPTION`, `PROCEDURE`) VALUES
('AMA', 'Against medical advice', 'The hospital must get the AMA form signed by the patient and his or her attendants'),
('EXPIRATION', 'Patient expired', NULL),
('HOME', 'Discharged to home', 'Patient must not need any service which may not be available at his or her home\r\n'),
('HOME_ASSISTANCE', 'Discharged to home with assistance', NULL),
('OTHER_FACILITY', 'Discharged to other facility', NULL);

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

--
-- Dumping data for table `disease`
--


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

--
-- Dumping data for table `disease_requires_service`
--


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

--
-- Dumping data for table `doctor`
--


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

--
-- Dumping data for table `doctor_detail`
--


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

--
-- Dumping data for table `doctor_especialty`
--


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

--
-- Dumping data for table `doctor_order_details`
--


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

--
-- Dumping data for table `doctor_order_group`
--

INSERT INTO `doctor_order_group` (`ORDER_GROUP_NAME`, `DESCRIPTION`, `GROUP_FOR_DOCTOR_ID`, `CREATION_DTM`, `CREATED_BY`, `VERSION`) VALUES
('Hip Surgery - Basic', 'Primitive Hip Surgery ', NULL, '2010-06-19 07:16:55', NULL, 0);

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

--
-- Dumping data for table `doctor_order_group_has_template`
--

INSERT INTO `doctor_order_group_has_template` (`ORDER_GROUP_NAME`, `ORDER_TEMPLATE_ID`, `SEQUENCE_NBR`, `EFFECTIVE_FROM_DT`, `EFFECTIVE_TO_DT`, `VERSION`) VALUES
('Hip Surgery - Basic', 'Hip Surgery Discharge', 4, '2010-06-18', NULL, 0),
('Hip Surgery - Basic', 'Hip-General', 1, '2010-06-18', NULL, 0),
('Hip Surgery - Basic', 'Post Hip Surgery Laboratory Te', 2, '2010-06-18', NULL, 0),
('Hip Surgery - Basic', 'Post Hip Surgery Laboratory Te', 3, '2010-06-18', NULL, 0);

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
  `DOCTOR_ID` int(11) DEFAULT NULL,
  `ACTIVE_FLAG` char(1) DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`TEMPLATE_ID`),
  KEY `TEMPLATE_ORDER_TYPE_CD` (`ORDER_TYPE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor_order_template`
--

INSERT INTO `doctor_order_template` (`TEMPLATE_ID`, `TEMPLATE_DESC`, `ORDER_TYPE_CD`, `DOCTOR_ID`, `ACTIVE_FLAG`, `VERSION`) VALUES
('Hip Surgery Discharge', 'Discharging patient after HIP surgery', 'DISCHARGE', NULL, 'N', 0),
('Hip-General', 'General order for Hip Surgery', 'GENERAL', NULL, 'Y', 0),
('Knee-General', 'General order for patients for knee surgery', 'GENERAL', NULL, 'Y', 0),
('Post Hip Surgery Laboratory Te', 'More Laboratory Test required after hip surgery ', 'TESTS', NULL, 'Y', 0);

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

--
-- Dumping data for table `doctor_order_template_details`
--

INSERT INTO `doctor_order_template_details` (`TEMPLATE_ID`, `SEQUENCE_NBR`, `SUB_SEQUENCE_NBR`, `SERVICE_ID`, `PACKAGE_ID`, `RESPONSIBLE_DEPARTMENT_ID`, `ACTION_DESC`, `VERSION`) VALUES
('Hip Surgery Discharge', 1, 1, '', NULL, 'ADMIN', 'Arrange for transportation', 0),
('Hip Surgery Discharge', 2, 1, '', NULL, 'SURGERY', 'Check that patient will be fine after reaching home', 0),
('Hip-General', 1, 1, '', NULL, 'ORTHOPEDICS', 'Clean the hip area using the standard method', 0),
('Hip-General', 2, 1, 'BDT', NULL, 'ORTHOPEDICS', '', 0),
('Hip-General', 3, 1, 'BPC', NULL, 'ORTHOPEDICS', '', 0),
('Knee-General', 1, 1, '', NULL, '', 'Cleanup the knee area', 0),
('Knee-General', 1, 2, 'BoneT', NULL, 'ORTHOPEDICS', '', 0),
('Post Hip Surgery Laboratory Te', 1, 1, 'BLOOD_TEST', NULL, 'PATHOLOGY', '', 0),
('Post Hip Surgery Laboratory Te', 2, 1, 'CT_SCAN', NULL, 'RADIOLOGY_IMAGING', '', 0),
('Post Hip Surgery Laboratory Te', 3, 1, 'Urine', NULL, 'PATHOLOGY', '', 0),
('Post Hip Surgery Laboratory Te', 3, 2, 'DIABTAIC', NULL, 'PATHOLOGY', '', 0);

-- --------------------------------------------------------

--
-- Table structure for table `doctor_order_type`
--

CREATE TABLE IF NOT EXISTS `doctor_order_type` (
  `ORDER_TYPE_CD` varchar(30) NOT NULL,
  `ORDER_TYPE_DESC` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`ORDER_TYPE_CD`)
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
-- Table structure for table `duration`
--

CREATE TABLE IF NOT EXISTS `duration` (
  `DURATION_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` varchar(1) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`DURATION_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `duration`
--

INSERT INTO `duration` (`DURATION_CODE`, `DESCRIPTION`, `ACTIVE`) VALUES
('DAY', 'Day(s)', '1'),
('HOUR', 'Hour(s)', '1'),
('MIN', 'Minute(s)', '1');

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

--
-- Dumping data for table `emergency`
--


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

--
-- Dumping data for table `entity_acct_map`
--


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

--
-- Dumping data for table `entity_contact_code`
--


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

--
-- Dumping data for table `especialty`
--

INSERT INTO `especialty` (`ESPECIALTY_CODE`, `ESPECIALTY_NAME`, `ACTIVE`, `ESPECILALTY`, `CREATED_DTM`, `CREATED_BY`, `LAST_MODIFIED_DTM`, `MODIFIED_BY`) VALUES
('ANESTHESIOLOGISTS', 'Anesthesiologists', 1, 1, NULL, NULL, NULL, NULL),
('CARDIOLOGISTS', 'Cardiologists', 1, 1, NULL, NULL, NULL, NULL),
('DERMATOLOGISTS', 'Dermatologists', 1, 1, NULL, NULL, NULL, NULL),
('DIABETOLOGISTS', 'Diabetologists', 1, 1, NULL, NULL, NULL, NULL),
('ENDOCRINOLOGISTS', 'Endocrinologists', 1, 1, NULL, NULL, NULL, NULL),
('GASTROENTEROLOGISTS', 'Gastroenterologists', 1, 1, NULL, NULL, NULL, NULL),
('HEAD_NECK_SURGEON', 'Head / Neck Surgeons', 1, 1, NULL, NULL, NULL, NULL),
('NEUROLOGISTS', 'Neurologists', 1, 1, NULL, NULL, NULL, NULL),
('OPHTHALMOLOGISTS', 'Ophthalmologists', 1, 1, NULL, NULL, NULL, NULL),
('OTOLARYNGOLOGISTS', 'Otolaryngologists', 1, 1, NULL, NULL, NULL, NULL),
('PATHOLOGISTS', 'Pathologists', 1, 1, NULL, NULL, NULL, NULL),
('PEDIATRICIANS', 'Pediatricians', 1, 1, NULL, NULL, NULL, NULL),
('PSYCHIATRISTS', 'Psychiatrists', 1, 1, NULL, NULL, NULL, NULL),
('RADIOLOGISTS', 'Radiologists', 1, 1, NULL, NULL, NULL, NULL),
('RHEMATOLOGISTS', 'Rheumatologists', 1, 1, NULL, NULL, NULL, NULL);

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
  `DESCRIPTION` varchar(256) DEFAULT NULL,
  `INVOLVED_PROCESS` varchar(256) DEFAULT NULL,
  `FEED_BACK_VALUE_TYPE` varchar(30) DEFAULT 'FREETEXT',
  `MAXIMUM_ALLOWED_SUBSEQUENCE` int(11) DEFAULT NULL,
  PRIMARY KEY (`FEEDBACK_TYPE_CD`,`FEEDBACK_SEQUENCE_NBR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `feedback_type`
--


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


-- --------------------------------------------------------

--
-- Table structure for table `gender`
--

CREATE TABLE IF NOT EXISTS `gender` (
  `GENDER_CODE` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`GENDER_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `gender`
--

INSERT INTO `gender` (`GENDER_CODE`, `DESCRIPTION`) VALUES
('FEMALE', 'Female'),
('MALE', 'Male');

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

--
-- Dumping data for table `hospital`
--

INSERT INTO `hospital` (`HOSPITAL_CODE`, `HOSPITAL_NAME`, `CONTACT_CODE`, `CREATED_DTM`, `CREATED_BY`) VALUES
('H1', 'APOLO', 1, '2010-04-04 00:13:07', 'Asha');

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

--
-- Dumping data for table `id_proofs`
--

INSERT INTO `id_proofs` (`ID_PROOFS_CODE`, `DESCRIPTION`, `ACTIVE`) VALUES
('DRIVING', 'Driving Licence', 1),
('PANCARD', 'Pan Card', 1),
('PASSPORT', 'Passport', 1),
('RATION', 'Ration Card', 1),
('SSN', 'Social Security Number', 1),
('VOTERID', 'Voter Id Card', 1);

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

--
-- Dumping data for table `immunization`
--

INSERT INTO `immunization` (`NAME`, `DESCRIPTION`, `ACTIVE`) VALUES
('ADENOVIRUS VACCINE, TYPE 4', 'Adenovirus Vaccine, Type 4', 1),
('ADENOVIRUS VACCINE, TYPE 7', 'Adenovirus Vaccine, Type 7', 1),
('ADENOVIRUS VACCINE, TYPE UNKNOWN', 'Adenovirus Vaccine, Type Unknown', 1),
('ANTHRAX VACCINE', 'Anthrax Vaccine', 1),
('BCG (TUBERCULOSIS) VACCINE', 'BCG (Tuberculosis) Vaccine', 1),
('BOTULINUM ANTITOXIN', 'Botulinum Antitoxin', 1),
('CHICKENPOX VACCINE', 'Chickenpox Vaccine', 1),
('CHOLERA VACCINE', 'Cholera Vaccine', 1),
('CMV IMMUNE GLOBULIN', 'CMV Immune Globulin', 1),
('CYTOMEGALOVIRUS IMMUNE GLOBULIN (CMV-IGIV)', 'Cytomegalovirus immune globulin (CMV-IGIV)', 1),
('DENGUE FEVER VACCINE', 'Dengue Fever Vaccine', 1),
('DIPHTHERIA ANTITOXIN', 'Diphtheria Antitoxin', 1),
('DIPHTHERIA/TETANUS (DT) TOXOIDS, PEDIATRIC', 'Diphtheria/Tetanus (DT) Toxoids, Pediatric', 1),
('DIPHTHERIA/TETANUS/PERTUSSIS (DTAP) VACCINE', 'Diphtheria/Tetanus/Pertussis (DTaP) Vaccine', 1),
('DIPHTHERIA/TETANUS/PERTUSSIS (DTAP) VACCINE, 5 PERTUSSIS ANTIGENS', 'Diphtheria/Tetanus/Pertussis (DTaP) Vaccine, 5 Pertussis Antigens', 1),
('DIPHTHERIA/TETANUS/PERTUSSIS (DTP) VACCINE', 'Diphtheria/Tetanus/Pertussis (DTP) Vaccine', 1),
('DTAP/HEPATITS B/POLIOVIRUS VACCINE', 'DTaP/Hepatits B/Poliovirus Vaccine', 1),
('DTAP/HIB VACCINE', 'DTaP/Hib Vaccine', 1),
('DTAP/HIB/POLIOVIRUS VACCINE', 'DTaP/Hib/Poliovirus Vaccine', 1),
('DTP/HIB/HEPATITIS B VACCINE', 'DTP/Hib/Hepatitis B Vaccine', 1),
('FLU SHOT', 'Flu Shot', 1),
('HANTAVIRUS VACCINE', 'Hantavirus Vaccine', 1),
('HEPATITIS A VACCINE, ADULT', 'Hepatitis A Vaccine, Adult', 1),
('HEPATITIS A VACCINE, PEDIATRIC/ADOLESCENT, 2 DOSE', 'Hepatitis A Vaccine, Pediatric/Adolescent, 2 Dose', 1),
('HEPATITIS A VACCINE, PEDIATRIC/ADOLESCENT, 3 DOSE', 'Hepatitis A Vaccine, Pediatric/Adolescent, 3 Dose', 1),
('HEPATITIS A VACCINE, TYPE UNKNOWN', 'Hepatitis A Vaccine, Type Unknown', 1),
('HEPATITIS A/HEPATITIS B VACCINE', 'Hepatitis A/Hepatitis B Vaccine', 1),
('HEPATITIS B IMMUNE GLOBULIN (HBIG)', 'Hepatitis B Immune Globulin (HBIG)', 1),
('HEPATITIS B VACCINE, ADOLESCENT OR PEDIATRIC', 'Hepatitis B Vaccine, Adolescent or Pediatric', 1),
('HEPATITIS B VACCINE, ADULT', 'Hepatitis B Vaccine, Adult', 1),
('HEPATITIS B VACCINE, DIALYSIS', 'Hepatitis B Vaccine, Dialysis', 1),
('HEPATITIS B VACCINE, TYPE UNKNOWN', 'Hepatitis B Vaccine, Type Unknown', 1),
('HEPATITIS E VACCINE', 'Hepatitis E Vaccine', 1),
('HERPES SIMPLEX 2 VACCINE', 'Herpes Simplex 2 Vaccine', 1),
('HIB (HBOC) VACCINE', 'Hib (HbOC) Vaccine', 1),
('HIB (HEMOPHILUS INFLUENZAE TYPE B), TYPE UNKNOWN', 'Hib (Hemophilus influenzae Type B), Type Unknown', 1),
('HIB (PRP-D) VACCINE', 'Hib (PRP-D) Vaccine', 1),
('HIB (PRP-OMP) VACCINE', 'Hib (PRP-OMP) Vaccine', 1),
('HIB (PRP-T) VACCINE', 'Hib (PRP-T) Vaccine', 1),
('HIB/HEPATITIS B VACCINE', 'Hib/Hepatitis B Vaccine', 1),
('HPV (HUMAN PAPILLOMAVIRUS) VACCINE, BIVALENT', 'HPV (Human Papillomavirus) Vaccine, Bivalent', 1),
('HPV (HUMAN PAPILLOMAVIRUS) VACCINE, QUADRIVALENT', 'HPV (Human Papillomavirus) Vaccine, Quadrivalent', 1),
('IMMUNE GLOBULIN (IG), INTRAMUSCULAR', 'Immune Globulin (IG), Intramuscular', 1),
('IMMUNE GLOBULIN (IG), TYPE UNKNOWN', 'Immune Globulin (IG), Type Unknown', 1),
('IMMUNE GLOBULIN, INTRAVENOUS (IVIG)', 'Immune Globulin, Intravenous (IVIG)', 1),
('INFLUENZA VACCINE, LIVE, INTRANASAL', 'influenza Vaccine, Live, Intranasal', 1),
('INFLUENZA VACCINE, SPLIT', 'influenza Vaccine, Split', 1),
('INFLUENZA VACCINE, TYPE UNKNOWN', 'Influenza Vaccine, Type Unknown', 1),
('INFLUENZA VACCINE, WHOLE', 'Influenza Vaccine, Whole', 1),
('JUNIN VIRUS VACCINE', 'Junin Virus Vaccine', 1),
('LEPROSY VACCINE', 'Leprosy Vaccine', 1),
('LYME DISEASE VACCINE', 'Lyme disease vaccine', 1),
('MEASLES VACCINE', 'Measles Vaccine', 1),
('MEASLES/MUMPS/RUBELLA (MMR) VACCINE', 'Measles/Mumps/Rubella (MMR) Vaccine', 1),
('MEASLES/MUMPS/RUBELLA/VARICELLA (MMRV) VACCINE', 'Measles/Mumps/Rubella/Varicella (MMRV) Vaccine', 1),
('MEASLES/RUBELLA (M/R) VACCINE', 'Measles/Rubella (M/R) Vaccine', 1),
('MENINGOCOCCAL C CONJUGATE VACCINE', 'Meningococcal C Conjugate Vaccine', 1),
('MENINGOCOCCAL POLYSACCHARIDE VACCINE (MPSV4)', 'Meningococcal Polysaccharide Vaccine (MPSV4)', 1),
('MENINGOCOCCAL VACCINE, TYPE UNKNOWN', 'Meningococcal Vaccine, Type Unknown', 1),
('MENINGOCOCCAL/DIPHTHERIA CONJUGATE VACCINE (MCV4)', 'Meningococcal/Diphtheria Conjugate Vaccine (MCV4)', 1),
('MUMPS VACCINE', 'Mumps Vaccine', 1),
('PERTUSSIS VACCINE', 'Pertussis Vaccine', 1),
('PLAGUE VACCINE', 'Plague Vaccine', 1),
('PNEUMOCOCCAL CONJUGATE VACCINE', 'Pneumococcal conjugate vaccine', 1),
('PNEUMOCOCCAL POLYSACCHARIDE VACCINE', 'Pneumococcal polysaccharide vaccine', 1),
('PNEUMOCOCCAL VACCINE, TYPE UNKNOWN', 'Pneumococcal Vaccine, Type Unknown', 1),
('POLIOVIRUS VACCINE, INACTIVATED (IPV)', 'Poliovirus vaccine, inactivated (IPV)', 1),
('POLIOVIRUS VACCINE, LIVE, ORAL (OPV)', 'Poliovirus Vaccine, Live, Oral (OPV)', 1),
('POLIOVIRUS VACCINE, TYPE UNKNOWN', 'Poliovirus Vaccine, Type Unknown', 1),
('Q FEVER VACCINE', 'Q Fever Vaccine', 1),
('RABIES IMMUNE GLOBULIN (RIG)', 'Rabies Immune Globulin (RIG)', 1),
('RABIES VACCINE, INTRADERMAL INJECTION', 'Rabies Vaccine, Intradermal Injection', 1),
('RABIES VACCINE, INTRAMUSCULAR INJECTION', 'Rabies Vaccine, Intramuscular Injection', 1),
('RABIES VACCINE, TYPE UNKNOWN', 'Rabies Vaccine, Type Unknown', 1),
('RHO(D) IMMUNE GLOBULIN - INTRAVENOUS (RHIVIG)', 'Rho(D) Immune Globulin - Intravenous (RhIVIg)', 1),
('RHO(D) IMMUNE GLOBULIN (RHIG)', 'Rho(D) immune globulin (RhIG)', 1),
('RIFT VALLEY FEVER VACCINE', 'Rift Valley Fever Vaccine', 1),
('ROTAVIRUS VACCINE, MONOVALENT', 'Rotavirus Vaccine, Monovalent', 1),
('ROTAVIRUS VACCINE, PENTAVALENT', 'Rotavirus Vaccine, Pentavalent', 1),
('ROTAVIRUS VACCINE, TETRAVALENT', 'Rotavirus Vaccine, Tetravalent', 1),
('ROTAVIRUS VACCINE, TYPE UNKNOWN', 'Rotavirus Vaccine, Type Unknown', 1),
('RSV IMMUNE GLOBULIN', 'RSV Immune Globulin', 1),
('RSV MONOCLONAL ANTIBODY', 'RSV Monoclonal Antibody', 1),
('RUBELLA VACCINE', 'Rubella Vaccine', 1),
('RUBELLA/MUMPS VACCINE', 'Rubella/Mumps Vaccine', 1),
('SHINGLES VACCINE', 'Shingles Vaccine', 1),
('SMALLPOX (VACCINIA) VACCINE', 'Smallpox (Vaccinia) Vaccine', 1),
('SMALLPOX (VACCINIA) VACCINE, DILUTED', 'Smallpox (Vaccinia) Vaccine, Diluted', 1),
('TETANUS IMMUNE GLOBULIN (TIG)', 'Tetanus Immune Globulin (TIG)', 1),
('TETANUS TOXOID, UNKNOWN TYPE', 'Tetanus Toxoid, Unknown Type', 1),
('TETANUS/DIPHTERIA (TD) TOXOIDS, OLDER CHILDREN AND ADULTS', 'Tetanus/Diphteria (Td) Toxoids, Older Children and Adults', 1),
('TETANUS/DIPHTHERIA/PERTUSSIS (TDAP) VACCINE', 'Tetanus/Diphtheria/Pertussis (Tdap) Vaccine', 1),
('TICK-BORNE ENCEPHALITIS VACCINE', 'Tick-Borne Encephalitis Vaccine', 1),
('TULAREMIA VACCINE', 'Tularemia Vaccine', 1),
('TYPHOID VACCINE, PARENTERAL', 'Typhoid Vaccine, Parenteral', 1),
('TYPHOID VACCINE, PARENTERAL, ACETONE-KILLED (U.S. MILITARY)', 'Typhoid Vaccine, Parenteral, Acetone-Killed (U.S. Military)', 1),
('TYPHOID VACCINE, UNKNOWN TYPE', 'Typhoid Vaccine, Unknown Type', 1),
('TYPHOID VI CAPSULAR POLYSACCHARIDE VACCINE', 'Typhoid Vi Capsular Polysaccharide Vaccine', 1),
('TYPHOID-PARATYPHOID VACCINE', 'Typhoid-Paratyphoid Vaccine', 1),
('TYPHUS VACCINE', 'Typhus Vaccine', 1),
('VACCINIA IMMUNE GLOBULIN (VIG)', 'Vaccinia Immune Globulin (VIG)', 1),
('VARICELLA VACCINE', 'Varicella Vaccine', 1),
('VARICELLA ZOSTER IMMUNE GLOBULIN (VZIG)', 'Varicella Zoster Immune Globulin (VZIG)', 1),
('YELLOW FEVER VACCINE', 'Yellow Fever Vaccine', 1),
('ZOSTER VACCINE', 'Zoster Vaccine', 1);

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

--
-- Dumping data for table `insurance_plans`
--


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

--
-- Dumping data for table `insurer`
--


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

--
-- Dumping data for table `lab_collection_point`
--


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
  PRIMARY KEY (`LAB_ID`,`COLLECTION_POINT_ID`),
  KEY `COLLECTION_ID` (`COLLECTION_POINT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `lab_collection_point_lab_association`
--


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

--
-- Dumping data for table `lab_details`
--

INSERT INTO `lab_details` (`LAB_ID`, `LAB_NAME`, `LAB_TYPE`, `BUSINESS_NAME`, `BRANCH_NAME`, `HOSPITAL_CODE`, `CONTACT_DETAIL_CODE`, `DIRECTION_FROM_KNOWN_PLACE`, `LAB_OPERATOR_ID`, `CREATED_BY`, `CREATED_DTM`, `MODIFIED_BY`, `MODIFIED_DTM`, `VERSION`) VALUES
('LAB_1', 'Religare', 'CT SCAN', 'LalPathology', 'BanjaraHills', 'H1', 233, 'near temple', 'B45', 'ajit kumar', '2010-05-19 18:10:22', NULL, NULL, 0),
('LAB_2', 'Viajya lab', 'CT Scan', 'Viajya lab', 'Begumpet', 'H1', 234, 'near Lal bunglow', 'L18', 'ajit kumar', '2010-05-19 18:16:38', NULL, NULL, 8),
('LAB_7', 'LABORAORY 7', 'MICROBOLOGY', '', '', 'H1', 244, '', '', 'ajit kumar', '2010-05-19 19:36:15', NULL, NULL, 2),
('LAB-20', 'Ultrasound Chest', 'BIOPSIES', 'Apolo Health City', 'Jubilee Hills', 'H1', 283, 'Near to KVR Park', 'Operator-20', 'Alok Ranjan', '2010-05-25 14:50:17', NULL, NULL, 2);

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

--
-- Dumping data for table `lab_patient_test_attribute_value`
--


-- --------------------------------------------------------

--
-- Table structure for table `lab_patient_test_change_history`
--

CREATE TABLE IF NOT EXISTS `lab_patient_test_change_history` (
  `PATIENT_TEST_ID` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `ATTRIBUTE_CODE` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `SEQ_NBR` int(11) NOT NULL,
  `ATTRIBUTE_VALUE` varchar(256) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATED_BY` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`PATIENT_TEST_ID`,`ATTRIBUTE_CODE`,`CREATED_DTM`,`SEQ_NBR`),
  KEY `ATTRIBUTE_CODE` (`ATTRIBUTE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `lab_patient_test_change_history`
--


-- --------------------------------------------------------

--
-- Table structure for table `lab_patient_test_detail`
--

CREATE TABLE IF NOT EXISTS `lab_patient_test_detail` (
  `PATIENT_TEST_ID` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `TEST_CODE` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `TEST_PERFORM_DTM` datetime DEFAULT NULL,
  `STATUS_CODE` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `APPROVE_DATE` datetime DEFAULT NULL,
  `APPROVER_NAME` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `PATIENT_ID` int(11) NOT NULL,
  `DOCTOR_ID` int(11) DEFAULT NULL,
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
  `REPORT_COLLECTED_BY` varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL,
  `REPORT_COLLECTED_DTM` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`PATIENT_TEST_ID`),
  KEY `TEST_CODE_FK` (`TEST_CODE`),
  KEY `TECHNIQUE_ID_FK` (`TECHNIQUE_ID`),
  KEY `DOCTOR_ID_FK` (`DOCTOR_ID`),
  KEY `PATIENT_ID_FK` (`PATIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `lab_patient_test_detail`
--


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

--
-- Dumping data for table `lab_patient_test_specimen`
--


-- --------------------------------------------------------

--
-- Table structure for table `lab_requisition_order`
--

CREATE TABLE IF NOT EXISTS `lab_requisition_order` (
  `ORDER_NBR` int(11) NOT NULL AUTO_INCREMENT,
  `PATIENT_ID` int(11) NOT NULL,
  `DOCTOR_ID` int(11) DEFAULT NULL,
  `REFERENCE_NUMBER` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `REFERENCE_TYPE` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
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

--
-- Dumping data for table `lab_requisition_order`
--


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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=3 ;

--
-- Dumping data for table `lab_specimen`
--

INSERT INTO `lab_specimen` (`SPECIMEN_ID`, `SPECIMEN_NAME`, `DESCRIPTION`, `CONTAINER_TYPE`, `CREATED_BY`, `CREATION_DTM`) VALUES
(1, 'Blood', 'Blood', NULL, 'Bhavesh', '2010-04-04 19:14:40'),
(2, 'Serum', 'Serum', NULL, 'Bhavesh', '2010-04-04 19:14:40');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=27 ;

--
-- Dumping data for table `lab_technique_reagent`
--

INSERT INTO `lab_technique_reagent` (`ID`, `NAME`, `IS_TECHNIQUE`, `DESCRIPTION`, `CREATED_BY`, `CREATED_DTM`, `VERSION`) VALUES
(14, 'CLIA', 'Y', 'Diagnostic Testing for Influenza', 'ajit kumar', '2010-05-19 19:01:40', 0),
(15, 'ELISA', 'N', 'Enzyme-linked immunosorbent assay ', 'ajit kumar', '2010-05-19 19:03:13', 4),
(17, 'Flow Cytometry ', 'Y', 'Flow Cytometry ', 'Alok Ranjan', '2010-05-25 12:24:56', 0),
(18, 'Spectrophotometry', 'Y', 'Spectrophotometry', 'Alok Ranjan', '2010-05-25 13:27:09', 0),
(19, 'Immunoturbidometry', 'Y', 'Immunoturbidometry', 'Alok Ranjan', '2010-05-25 13:27:27', 0),
(20, 'Chemiluminescent Immunoassay ', 'Y', 'Chemiluminescent Immunoassay ', 'Alok Ranjan', '2010-05-25 13:28:02', 0),
(21, 'Enzyme Immunoassay ', 'Y', 'Enzyme Immunoassay ', 'Alok Ranjan', '2010-05-25 13:28:36', 0),
(22, 'Strip Test ', 'Y', 'Strip test ', 'Alok Ranjan', '2010-05-25 13:29:24', 0),
(23, 'Radiobinding Assay ', 'Y', 'Radiobinding Assay ', 'Alok Ranjan', '2010-05-25 13:30:42', 0),
(24, 'PEG Precipitation', 'Y', 'PEG Precipitation', 'Alok Ranjan', '2010-05-25 13:32:14', 0),
(25, 'IIFA', 'Y', 'Indirect Immuno fluorescence Assay ', 'Alok Ranjan', '2010-05-25 13:32:43', 2),
(26, 'MEIA', 'Y', 'Microparticle Enzyme Immuno Assay ', 'Alok Ranjan', '2010-05-25 14:40:08', 1);

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

--
-- Dumping data for table `lab_template_widget`
--

INSERT INTO `lab_template_widget` (`WIDGET_CODE`, `WIDGET_LABEL`, `ATTRIBUTE_CODE`, `WIDGET_TYPE`, `SECTION_CODE`, `VALUE_TYPE`, `WIDGET_VALUE_PROVIDER`) VALUES
('approverDateAndTime', 'Date & time', NULL, 'label', 'SECTION_5', 'DATETIME', NULL),
('approverName', 'Approver name', NULL, 'string', 'SECTION_5', 'TEXT', NULL),
('approverSignature', 'Approver signature', NULL, 'label', 'SECTION_5', 'TEXT', NULL),
('doctorId', 'Doctor id', NULL, 'number', 'SECTION_2', 'NUMERIC', 'getDoctorId'),
('doctorName', 'Doctor name', NULL, 'string', 'SECTION_2', 'TEXT', 'getFirstName'),
('doctorQualification', 'Qualification', NULL, 'string', 'SECTION_2', 'TEXT', 'getDoctorDetail.getQualification'),
('doctorWorkExperience', 'Work experience', NULL, 'string', 'SECTION_2', 'TEXT', 'getDoctorDetail.getWorkExperience'),
('investigatorName', 'Investigator name', NULL, 'string', 'SECTION_4', 'TEXT', NULL),
('patientEmailId', 'Email id', NULL, 'string', 'SECTION_1', 'TEXT', 'getContactDetailsBM.getEmail'),
('patientId', 'Patient id', NULL, 'number', 'SECTION_1', 'NUMERIC', 'getPatientId'),
('patientMobileNbr', 'Mobile nbr', NULL, 'number', 'SECTION_1', 'NUMERIC', 'getContactDetailsBM.getMobileNumber'),
('patientName', 'Patient name', NULL, 'string', 'SECTION_1', 'TEXT', 'getFullName'),
('patientPhoneNbr', 'Phone nbr', NULL, 'number', 'SECTION_1', 'NUMERIC', 'getContactDetailsBM.getPhoneNumber'),
('testName', 'Test name', NULL, 'string', 'SECTION_4', 'TEXT', 'getLabTest.getTestName'),
('REMARKS', 'Remarks', 'REMARKS', 'string', 'SECTION_3', 'TEXT', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `lab_test`
--

CREATE TABLE IF NOT EXISTS `lab_test` (
  `TEST_NAME` varchar(290) COLLATE utf8_unicode_ci NOT NULL,
  `TEST_CODE` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `TECHNIQUE_ID` int(11) NOT NULL,
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

--
-- Dumping data for table `lab_test`
--


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

--
-- Dumping data for table `lab_test_attribute`
--

INSERT INTO `lab_test_attribute` (`ATTRIBUTE_CODE`, `ATTRIBUTE_NAME`, `TYPE`, `MIN_VALUE`, `MAX_VALUE`, `UNIT`, `OBSERVATION_VALUE`, `CREATED_BY`, `CREATED_DATE`) VALUES
('REMARKS', 'Remarks', 'TEXT', NULL, NULL, NULL, NULL, 'Bhavesh ', '2010-05-31 11:35:51');
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

--
-- Dumping data for table `lab_test_attribute_association`
--


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

--
-- Dumping data for table `lab_test_specimen_association`
--


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

--
-- Dumping data for table `lab_test_technique_template`
--


-- --------------------------------------------------------

--
-- Table structure for table `lab_test_template`
--

CREATE TABLE IF NOT EXISTS `lab_test_template` (
  `TEMPLATE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TEMPLATE_NAME` varchar(30) NOT NULL,
  `CREATED_BY` varchar(80) NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_MODIFIED_BY` varchar(80) DEFAULT NULL,
  `LAST_MODIFIED_DTM` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`TEMPLATE_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=40 ;

--
-- Dumping data for table `lab_test_template`
--

INSERT INTO `lab_test_template` (`TEMPLATE_ID`, `TEMPLATE_NAME`, `CREATED_BY`, `CREATED_DTM`, `LAST_MODIFIED_BY`, `LAST_MODIFIED_DTM`) VALUES
(23, 'TEAMPLATE-CT_SCAN', 'ajit kumar', '2010-05-19 21:58:34', 'ajit kumar', '2010-05-25 10:51:19'),
(24, 'TEAMPLATE-DIABTAIC', 'ajit kumar', '2010-05-19 22:42:59', NULL, NULL),
(25, 'TEAMPLATE-ECG', 'ajit kumar', '2010-05-19 22:48:56', 'Bhavesh ', '2010-05-31 11:42:30'),
(26, 'TEAMPLATE-SUGAR_TEST', 'ajit kumar', '2010-05-20 15:50:35', 'ajit kumar', '2010-05-20 16:52:49'),
(27, 'TEAMPLATE-Abdomen Sono', 'Alok Ranjan', '2010-05-22 03:21:41', 'Bhavesh ', '2010-05-31 11:37:51'),
(28, 'TEAMPLATE-UltraSound', 'Alok Ranjan', '2010-05-22 17:15:22', 'Bhavesh ', '2010-05-24 21:42:03'),
(29, 'TEAMPLATE-AEC', 'Alok Ranjan', '2010-05-24 20:06:41', 'Alok Ranjan', '2010-05-24 21:18:31'),
(30, 'TEAMPLATE-ANC', 'Alok Ranjan', '2010-05-25 11:05:35', NULL, NULL),
(31, 'TEAMPLATE-ALC', 'Alok Ranjan', '2010-05-25 11:07:59', NULL, NULL),
(32, 'TEAMPLATE-ASPERGILLUS', 'Alok Ranjan', '2010-05-25 11:32:35', NULL, NULL),
(33, 'TEAMPLATE-BLOOD_TEST', 'Alok Ranjan', '2010-05-25 11:52:21', 'Bhavesh ', '2010-05-31 11:40:31'),
(34, 'TEAMPLATE-STOOL', 'Alok Ranjan', '2010-05-25 11:52:49', 'Alok Ranjan', '2010-05-25 16:28:41'),
(35, 'TEAMPLATE-Urine', 'Alok Ranjan', '2010-05-25 11:53:12', 'Alok Ranjan', '2010-05-25 17:04:43'),
(36, 'TEAMPLATE-BPC', 'Alok Ranjan', '2010-05-25 11:54:14', 'Bhavesh ', '2010-05-31 11:47:06'),
(37, 'TEAMPLATE-AB-KILLER', 'Alok Ranjan', '2010-05-25 12:27:38', 'ajit kumar', '2010-06-15 14:39:59'),
(38, 'TEAMPLATE-CBP', 'ajit kumar', '2010-05-29 16:53:46', 'Bhavesh ', '2010-05-31 11:47:20'),
(39, 'TEAMPLATE-BoneT', 'ajit kumar', '2010-06-18 20:44:04', 'ajit kumar', '2010-06-18 20:45:49');

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

--
-- Dumping data for table `lab_test_template_detail`
--


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

--
-- Dumping data for table `marital`
--

INSERT INTO `marital` (`MARITAL_CODE`, `DESCRIPTION`, `ACTIVE`) VALUES
('DIVORCED', 'Divorced', 'Y'),
('MARRIED', 'Married', 'Y'),
('SEPARATED', 'Separated', 'Y'),
('SINGLE', 'Single', 'Y'),
('WIDOWED', 'Widowed', 'Y');

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

--
-- Dumping data for table `medicines`
--

INSERT INTO `medicines` (`MEDICINE_CODE`, `MEDICINE_NAME`, `BRAND_CODE`, `STRENGTH`, `MEDICINE_TYPE_CODE`, `MAXIMUM_DOSAGE`, `ACTIVE`, `VERSION`) VALUES
('Artesunate', 'Artesunate', 'NOVA', '50 mg', 'Tablet', '2', 'N', 1),
('Atenolol', 'Atenolol', 'NOVA', '200 mg', 'TABLET', '3', 'Y', 0),
('Bromhexine', 'Bromhexine Syrup', 'AVEN', '100 ml', 'Syrup', '2', 'N', 1),
('Captopril', 'Captopril', 'NOVA', '25 mg', 'TABLET', '3', 'Y', 0),
('Carbmazepine', 'Carbmazepine', 'PFIZ', '200 mg', 'TABLET', '1', 'Y', 0),
('Kfex', 'Kofex', 'ALKE', '25 mg', 'SYRUP', '2', 'Y', 0),
('Ranitidine', 'Ranitidine', 'PFIZ', '150 gm', 'TABLET', '3', 'Y', 0),
('Zidovudine', 'Zidovudine', 'NOVA', '100 mg', 'CAPSULE', '2', 'Y', 0);

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

--
-- Dumping data for table `medicine_type`
--

INSERT INTO `medicine_type` (`MEDICINE_TYPE_CODE`, `DESCRIPTION`, `ACTIVE`) VALUES
('CAPSULE', 'Capsule', 1),
('SYRUP', 'Syrup', 1),
('TABLET', 'Tablet', 1);

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

--
-- Dumping data for table `mother_tongue`
--

INSERT INTO `mother_tongue` (`MOTHER_TONGUE_CODE`, `DESCRIPTION`, `ACTIVE`, `DEFAULT_CODE`) VALUES
('IN_AS', 'Assamese', 1, 0),
('IN_BH', 'Bihari', 1, 0),
('IN_BN', 'Bengali', 1, 0),
('IN_GU', 'Gujrati', 1, 0),
('IN_HI', 'Hindi', 1, 0),
('IN_KS', 'Kashmiri', 1, 0),
('IN_MR', 'Marathi', 1, 0),
('IN_NE', 'Nepali', 1, 0),
('IN_OR', 'Oriya', 1, 0),
('IN_PA', 'Punjabi', 1, 0),
('IN_SA', 'Sanskrit', 1, 0),
('IN_SD', 'Sindhi', 1, 0),
('IN_SI', 'Singhalese', 1, 0),
('IN_TA', 'Tamil', 1, 0),
('IN_TE', 'Telugu', 1, 0),
('IN_UR', 'Urdu', 1, 0);

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

--
-- Dumping data for table `nationality`
--

INSERT INTO `nationality` (`NATIONALITY_CODE`, `DESCRIPTION`, `ACTIVE`, `DEFAULT_CODE`) VALUES
('IN', 'Indian', 1, 1),
('MA', 'Malaysian', 1, 0),
('US', 'American', 1, 0);

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

--
-- Dumping data for table `nursing_unit_type`
--

INSERT INTO `nursing_unit_type` (`UNIT_TYPE_CD`, `UNIT_TYPE_DESC`, `PARENT_UNIT_TYPE_CD`, `TOTAL_BED_COUNT`, `AVAILABLE_BED_COUNT`, `THRESHOLD_FOR_CONFIRMATION`, `THRESHOLD_FOR_WAITLIST`, `MODIFIED_BY`, `LAST_MODIFIED_DTM`, `CREATE_DATE`) VALUES
('DENTISTRY', 'Dentistry', NULL, 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11'),
('EMERGENCY', 'Emergency Unit', 'GENERAL', 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11'),
('GASTROSURGERY', 'Gastrosurgery', NULL, 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11'),
('GENERAL', 'General', NULL, 0, 0, NULL, NULL, NULL, '2010-06-18 16:39:02', '2009-08-11'),
('GENERAL_SURGERY_WARD', 'General surgery ward', NULL, 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11'),
('HEMATOLOGY', 'Hematology', NULL, 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11'),
('ICU', 'Intensive Care Units', NULL, 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:53', '2009-08-11'),
('LIVER_DIGESTIVE', 'Liver & Digestive', NULL, 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11'),
('NEPHROLOGY_RENAL', 'Nephrology & Renal', NULL, 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11'),
('NEUROLOGY_NEUROSURGERY', 'Neurology & Neurosurgery', NULL, 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11'),
('OBSTETRICS_GYNAECOLOGY_WARD', 'Obstetrics & Gynaecology ward', NULL, 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11'),
('OUTPATIENT', 'Outpatients Unit', 'GENERAL', 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11'),
('PULMONOLOGY', 'Pulmonology', NULL, 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11'),
('THORIAC_SURGERY', 'Thoriac surgery', NULL, 0, 0, NULL, NULL, NULL, '2009-11-05 21:55:37', '2009-08-11');

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

--
-- Dumping data for table `observations`
--


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

--
-- Dumping data for table `package_has_service`
--


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

--
-- Dumping data for table `patient`
--


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

--
-- Dumping data for table `patient_allergies`
--


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

--
-- Dumping data for table `patient_category`
--

INSERT INTO `patient_category` (`CATEGORY_CODE`, `DESCRIPTION`, `ACTIVE`, `DEFAULT_CODE`) VALUES
('1000001', 'Corporate', 1, 0),
('1000002', 'Management Discount', 1, 0),
('1000003', 'Self', 1, 1),
('1000004', 'TPA', 1, 0);

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

--
-- Dumping data for table `patient_details`
--


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

--
-- Dumping data for table `patient_immunization`
--


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

--
-- Dumping data for table `patient_rating`
--

INSERT INTO `patient_rating` (`RATING_CODE`, `DESCRIPTION`, `ACTIVE`, `DEFAULT_CODE`) VALUES
('G', 'Gold', 1, 0),
('P', 'Platinum', 1, 0),
('R', 'Regular', 1, 1),
('S', 'Silver', 1, 0);

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

--
-- Dumping data for table `patient_vaccination_schedule`
--


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

--
-- Dumping data for table `patient_vaccination_schedule_details`
--


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
  `PERCENT_ABS_IND` varchar(1) DEFAULT NULL,
  `COVERAGE_AMT` double DEFAULT NULL,
  `CREATED_BY` varchar(35) NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`PLAN_CD`,`SERVICE_CODE`),
  KEY `PLAN_HAS_SERVICE_PLAN_CD_FK` (`PLAN_CD`),
  KEY `PLAN_HAS_SERVICE_SERVICE_CODE_FK` (`SERVICE_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `plan_has_services`
--


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

--
-- Dumping data for table `prescription`
--


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

--
-- Dumping data for table `prescription_medicine_assoc`
--


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

--
-- Dumping data for table `record`
--


-- --------------------------------------------------------

--
-- Table structure for table `reference_data_list`
--

CREATE TABLE IF NOT EXISTS `reference_data_list` (
  `CONTEXT` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `ATTR_CODE` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `ATTR_DESC` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `IS_DEFAULT` varchar(1) COLLATE utf8_unicode_ci DEFAULT 'N',
  `SEQ_NBR` int(4) DEFAULT NULL,
  `CREATED_BY` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `CREATE_DTM` datetime NOT NULL,
  `MODIFIED_BY` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL,
  `MODIFIED_DTM` datetime DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`CONTEXT`,`ATTR_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `reference_data_list`
--

INSERT INTO `reference_data_list` (`CONTEXT`, `ATTR_CODE`, `ATTR_DESC`, `IS_DEFAULT`, `SEQ_NBR`, `CREATED_BY`, `CREATE_DTM`, `MODIFIED_BY`, `MODIFIED_DTM`, `VERSION`) VALUES
('ACTIVE_STATUS', 'N', 'No', 'N', NULL, 'Bhavesh', '2010-01-27 20:58:33', NULL, NULL, 0),
('ACTIVE_STATUS', 'Y', 'Yes', 'N', NULL, 'Bhavesh', '2010-01-27 20:58:33', NULL, NULL, 0),
('APPONITMENT_TYPE', 'FOLLOW-UP', 'Follow-up', 'N', NULL, 'vinay kurudi', '2010-01-07 18:08:40', NULL, NULL, 0),
('APPONITMENT_TYPE', 'PRIMARY', 'Primary', 'N', NULL, 'vinay kurudi', '2010-01-07 18:07:49', NULL, NULL, 0),
('APPONITMENT_TYPE', 'SECONDARY', 'Secondary', 'N', NULL, 'vinay kurudi', '2010-01-07 18:07:59', NULL, NULL, 0),
('HEIGHT', 'CMS', 'cms', 'N', NULL, 'vinay kurudi', '2010-01-07 11:08:18', NULL, NULL, 0),
('HEIGHT', 'INCHES', 'Inches', 'N', NULL, 'vinay kurudi', '2010-01-07 11:07:45', NULL, NULL, 0),
('LAB_MEASUREMENT_UNIT', 'COUNT', 'Count', 'N', 3, 'Bhavesh', '2010-04-01 11:31:16', NULL, NULL, 0),
('LAB_MEASUREMENT_UNIT', 'GM', 'gms', 'N', 2, 'Bhavesh', '2010-04-01 11:31:41', NULL, NULL, 0),
('LAB_MEASUREMENT_UNIT', 'ML', 'ml', 'N', 1, 'Bhavesh', '2010-04-04 00:00:00', NULL, NULL, 0),
('LAB_TEST_RESULT_STATUS', 'CREATED', 'Created', 'N', 1, 'Bhavesh', '2010-04-01 11:31:16', NULL, NULL, 0),
('LAB_TEST_RESULT_STATUS', 'RESULT_APPROVED', 'Result approved', 'N', 4, 'Bhavesh', '2010-04-01 11:31:41', NULL, NULL, 0),
('LAB_TEST_RESULT_STATUS', 'RESULT_ENTERED', 'Result entered', 'N', 2, 'Bhavesh', '2010-04-01 11:31:41', NULL, NULL, 0),
('LAB_TEST_RESULT_STATUS', 'SPECIMEN_COLLECTED', 'Specimen collected', 'N', 2, 'Bhavesh', '2010-04-01 11:31:41', NULL, NULL, 0),
('LAB_TEST_RESULT_STATUS', 'TEST_PERFORMED', 'Test performed', 'N', 3, 'Bhavesh', '2010-04-01 11:31:16', NULL, NULL, 0),
('LAB_TYPE', 'BIOPSIES', 'Biopsies', 'N', NULL, '', '2010-04-02 18:39:26', NULL, NULL, 0),
('LAB_TYPE', 'CT SCAN', 'CT Scan', 'N', NULL, '', '0000-00-00 00:00:00', NULL, NULL, 0),
('LAB_TYPE', 'ECG', 'Ecg', 'N', NULL, '', '2010-04-02 18:41:12', NULL, NULL, 0),
('LAB_TYPE', 'MICROBOLOGY', 'Microbiology', 'N', NULL, '', '2010-04-02 18:38:16', NULL, NULL, 0),
('LAB_TYPE', 'PET', 'Pet', 'N', NULL, '', '2010-04-02 18:39:46', NULL, NULL, 0),
('LAB_TYPE', 'ULTRASOUND', 'Ultrasound', 'N', NULL, '', '2010-04-02 18:40:15', NULL, NULL, 0),
('PAT_VACC_SCHEDULE_STATUS', 'COMPLETED', 'Completed', 'N', NULL, 'Sandeep Kumar', '2009-12-30 11:55:14', 'Sandeep Kumar', '2009-12-30 11:55:14', 0),
('PAT_VACC_SCHEDULE_STATUS', 'NOT_STARTED', 'Not started', 'N', NULL, 'Sandeep Kumar', '2009-12-30 11:55:14', 'Sandeep Kumar', '2009-12-30 11:55:14', 0),
('PAT_VACC_SCHEDULE_STATUS', 'PARTIALLY_COMPLETED', 'Partially completed', 'N', NULL, 'Sandeep Kumar', '2009-12-30 11:55:14', 'Sandeep Kumar', '2009-12-30 11:55:14', 0),
('PATIENT_TEST_DETAIL_STATUS', 'APPROVED', 'Approved', 'N', 4, 'Bhavesh', '2010-04-01 11:31:16', NULL, NULL, 0),
('PATIENT_TEST_DETAIL_STATUS', 'CREATED', 'Created', 'N', 1, 'Bhavesh', '2010-04-01 11:31:41', NULL, NULL, 0),
('PATIENT_TEST_DETAIL_STATUS', 'DISAPPROVED', 'Disapproved', 'N', 5, 'Bhavesh', '2010-04-01 11:31:41', NULL, NULL, 0),
('PATIENT_TEST_DETAIL_STATUS', 'RESULT_ENTERED', 'Result entered', 'N', 6, 'Bhavesh', '2010-04-01 11:31:41', NULL, NULL, 0),
('PATIENT_TEST_DETAIL_STATUS', 'SPECIMEN_COLLECTED', 'Specimen collected', 'N', 2, 'Bhavesh', '2010-04-01 11:31:16', NULL, NULL, 0),
('PATIENT_TEST_DETAIL_STATUS', 'TEST_PERFORMED', 'Test performed', 'N', NULL, 'manish', '0000-00-00 00:00:00', NULL, NULL, 0),
('PCT_ABS_IND', 'ABSOLUTE', 'Absolute', 'N', NULL, 'Bhavesh', '2010-01-02 12:47:30', NULL, NULL, 0),
('PCT_ABS_IND', 'PERCENTAGE', 'Percentage', 'N', NULL, 'Bhavesh', '2010-01-02 12:47:44', NULL, NULL, 0),
('PERIOD_IND', 'D', 'Days', 'N', NULL, 'vinay kurudi', '2010-01-05 11:27:51', NULL, NULL, 0),
('PERIOD_IND', 'H', 'Hours', 'N', NULL, 'vinay kurudi', '2010-01-05 11:27:35', NULL, NULL, 0),
('PERIOD_IND', 'M', 'Months', 'N', NULL, 'vinay kurudi', '2010-01-05 11:28:17', NULL, NULL, 0),
('PERIOD_IND', 'W', 'Weeks', 'N', NULL, 'vinay kurudi', '2010-01-05 11:28:06', NULL, NULL, 0),
('PERIOD_IND', 'Y', 'Years', 'N', NULL, 'vinay kurudi', '2010-01-05 11:28:31', NULL, NULL, 0),
('QUALIFICATION', 'DM', 'DM', 'N', 2, 'ASHA', '2010-03-17 14:44:35', NULL, NULL, 1),
('QUALIFICATION', 'ENDOCRINOLOGIST', 'ENDOCRINOLOGIST', 'N', 3, 'ASHA', '2010-03-17 14:46:01', NULL, NULL, 1),
('QUALIFICATION', 'FRCS', 'F.R.C.S', 'N', 4, 'ASHA', '2010-03-17 14:46:39', NULL, NULL, 0),
('QUALIFICATION', 'MBBS', 'M.B.B.S', 'N', 5, 'ASHA', '2010-03-17 14:48:27', NULL, NULL, 0),
('QUALIFICATION', 'MD', 'M.D', 'N', 1, 'ASHA', '2010-03-17 14:01:23', NULL, NULL, 0),
('QUALIFICATION', 'MD,DM', 'MD,DM', 'N', NULL, 'ASHA', '2010-03-17 14:49:35', NULL, NULL, 0),
('QUALIFICATION', 'MD(RADIOLOGIST)', 'MD(RADIOLOGIST)', 'N', NULL, 'ASHA', '2010-03-17 14:49:06', NULL, NULL, 1),
('QUALIFICATION', 'MS', 'MS', 'N', NULL, 'ASHA', '2010-03-17 14:50:02', NULL, NULL, 1),
('QUALIFICATION', 'RMP', 'RMP', 'N', NULL, 'ASHA', '2010-03-17 14:50:44', NULL, NULL, 1),
('REFERENCE_TYPE', 'IPD', 'IPD', 'N', NULL, '', '0000-00-00 00:00:00', NULL, NULL, 0),
('REFERENCE_TYPE', 'OPD', 'OPD', 'N', NULL, '', '0000-00-00 00:00:00', NULL, NULL, 0),
('REFERENCE_TYPE', 'PAT', 'Direct patient', 'N', NULL, '', '0000-00-00 00:00:00', NULL, NULL, 0),
('REFFERAL_TYPE', 'CLINIC', 'Clinic', 'N', NULL, 'SREEKANTH', '2009-12-30 11:55:14', 'SREEKANTH', '2009-12-30 11:55:14', 0),
('REFFERAL_TYPE', 'DOCTOR', 'Doctor', 'N', NULL, 'SREEKANTH', '2009-12-30 11:53:42', 'SREEKANTH', '2009-12-30 11:53:42', 0),
('REFFERAL_TYPE', 'HOSPITAL', 'Hospital', 'N', NULL, 'SREEKANTH', '2009-12-30 11:53:42', 'SREEKANTH', '2009-12-30 11:53:42', 0),
('REFFERAL_TYPE', 'OLD-PAT', 'Old Patient', 'N', NULL, 'SREEKANTH', '2009-12-30 11:55:14', 'SREEKANTH', '2009-12-30 11:55:14', 0),
('REPORT', 'AppointmentSummary', 'Appointment Summary', 'N', NULL, 'Bhavesh', '2010-01-12 18:40:51', NULL, NULL, 0),
('REPORT', 'DailyAppointments', 'Daily Appointments', 'N', NULL, 'Bhavesh', '2010-01-11 11:16:19', NULL, NULL, 0),
('REPORT', 'DailyPatientWaitTime', 'Daily Patient Wait Time', 'N', NULL, 'Bhavesh', '2010-01-18 17:53:21', NULL, NULL, 1),
('REPORT', 'DailyReceiptStatement', 'Daily Receipt Statement', 'N', NULL, 'Bhavesh', '2010-01-18 17:55:42', NULL, NULL, 0),
('REPORT', 'DepartmentWiseRevenue', 'Department Wise Revenue', 'N', NULL, 'manish', '2010-01-10 11:33:58', NULL, NULL, 0),
('REPORT', 'DoctorwiseRevenueDetail', 'Doctorwise Revenue Detail', 'N', NULL, 'Bhavesh', '2009-08-26 18:08:19', NULL, NULL, 0),
('REPORT', 'DoctorWiseServiceAssignment', 'Doctor Wise Service Assignment', 'N', NULL, 'Bhavesh', '0000-00-00 00:00:00', NULL, NULL, 0),
('REPORT', 'OPDInflow', 'OPD Inflow', 'N', NULL, 'Bhavesh', '2010-01-18 10:53:18', NULL, NULL, 0),
('REPORT', 'OPDPatientBill', 'OPD Patient Bill', 'N', NULL, 'Bhavesh', '2010-01-18 10:30:02', NULL, NULL, 0),
('REPORT', 'ServiceWiseRevenue', 'Service Wise Revenue', 'N', NULL, 'Bhavesh', '2010-01-12 18:36:24', NULL, NULL, 0),
('ROSTER_ENTITIES', 'DOCTOR', 'Doctor', 'N', 1, 'vinaykurudi', '2010-02-09 17:33:32', NULL, NULL, 0),
('SERVICE_TYPE', 'ADMINISTRATIVE', 'Administrative', 'N', NULL, 'Bhavesh', '2010-04-01 11:31:16', NULL, NULL, 0),
('SERVICE_TYPE', 'LABORATORY', 'Laboratory test', 'N', 1, 'Bhavesh', '2010-04-01 11:31:16', NULL, NULL, 0),
('SERVICE_TYPE', 'NURSING', 'Nursing', 'N', 2, 'Bhavesh', '2010-04-01 11:31:41', NULL, NULL, 0),
('SERVICE_TYPE', 'SURGICAL', 'Surgical', 'N', 3, 'Bhavesh', '2010-04-01 11:31:41', NULL, NULL, 0),
('TEST_ATTRIBUTE_TYPE', 'NUMERIC', 'Numeric', 'N', 1, 'Bhavesh', '2010-04-01 11:31:16', NULL, NULL, 0),
('TEST_ATTRIBUTE_TYPE', 'OBSERVATION', 'Observation', 'N', 3, 'Bhavesh', '2010-04-01 11:31:16', NULL, NULL, 0),
('TEST_ATTRIBUTE_TYPE', 'TEXT', 'Text', 'N', 2, 'Bhavesh', '2010-04-01 11:31:41', NULL, NULL, 0),
('TEST_FOR_GENDER', 'BOTH', 'Both', 'N', 1, 'Bhavesh', '2010-04-01 11:31:16', NULL, NULL, 0),
('TEST_FOR_GENDER', 'FEMALE', 'Female', 'N', 3, 'Bhavesh', '2010-04-01 11:31:41', NULL, NULL, 0),
('TEST_FOR_GENDER', 'MALE', 'Male', 'N', 2, 'Bhavesh', '2010-04-04 00:00:00', NULL, NULL, 0),
('TEST_REQUISITION_STATUS', 'BILLED', 'Billed', 'N', NULL, 'manish', '0000-00-00 00:00:00', NULL, NULL, 0),
('TEST_REQUISITION_STATUS', 'CANCELED', 'Canceled', 'N', 2, 'manish', '0000-00-00 00:00:00', NULL, NULL, 0),
('TEST_REQUISITION_STATUS', 'COMPLETED', 'Completed', 'N', 4, 'manish', '2010-04-01 00:00:00', NULL, NULL, 0),
('TEST_REQUISITION_STATUS', 'CREATED', 'Created', 'N', 1, 'manish', '0000-00-00 00:00:00', NULL, NULL, 0),
('TEST_REQUISITION_STATUS', 'IN_PROGRESS', 'In progress', 'N', 3, '', '0000-00-00 00:00:00', NULL, NULL, 0),
('TEST_REQUISITION_STATUS', 'PAID', 'Paid', 'N', NULL, 'manish', '0000-00-00 00:00:00', NULL, NULL, 0),
('TEST_REQUISITION_STATUS', 'RENDERED', 'Rendered', 'N', NULL, 'manish', '0000-00-00 00:00:00', NULL, NULL, 0),
('VACCINATION', 'ANTHRAX', 'Anthrax', 'N', NULL, 'vinay kurudi', '2010-01-05 11:19:29', NULL, NULL, 0),
('VACCINATION', 'DTAP', 'DTaP', 'N', NULL, 'vinay kurudi', '2010-01-05 11:19:33', NULL, NULL, 0),
('VACCINATION', 'HEPATITIS-A', 'Hepatitis A', 'N', NULL, 'vinay kurudi', '2010-01-05 11:16:04', NULL, NULL, 0),
('VACCINATION', 'HEPATITIS-B', 'Hepatitis B', 'N', NULL, 'vinay kurudi', '2010-01-05 11:16:30', NULL, NULL, 0),
('VACCINATION', 'POLIO', 'Polio', 'N', NULL, 'vinay kurudi', '2010-01-05 11:19:59', NULL, NULL, 0),
('VACCINATION', 'RABIES', 'Rabies ', 'N', NULL, 'vinay kurudi', '2010-01-05 11:22:57', NULL, NULL, 0),
('VACCINATION', 'TETANUS', 'Tetanus/Diptheria/(Pertussis)', 'N', NULL, 'vinay kurudi', '2010-01-05 11:22:17', NULL, NULL, 0),
('VACCINATION_ACTIVE_FLAG', 'N', 'In-active', 'N', NULL, 'sandeep Kumar', '2010-01-05 11:19:33', NULL, NULL, 0),
('VACCINATION_ACTIVE_FLAG', 'Y', 'Active', 'N', NULL, 'sandeep Kumar', '2010-01-05 11:19:33', NULL, NULL, 0),
('VACCINATION_TYPE', 'BOOSER', 'Booster', 'N', NULL, 'vinay kurudi', '2010-01-05 11:26:18', NULL, NULL, 0),
('VACCINATION_TYPE', 'MANDATORY', 'Mandatory', 'N', NULL, 'vinay kurudi', '2010-01-05 11:24:07', NULL, NULL, 0),
('VACCINATION_TYPE', 'OPTIONAL', 'Optional', 'N', NULL, 'vinay kurudi', '2010-01-05 11:24:12', NULL, NULL, 0),
('WEIGHT', 'KGS', 'kgs', 'N', NULL, 'vinay kurudi', '2010-01-07 11:11:28', NULL, NULL, 0),
('WEIGHT', 'POUNDS', 'Pounds', 'N', NULL, 'vinay kurudi', '2010-01-07 11:11:05', NULL, NULL, 0);

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

--
-- Dumping data for table `referral`
--


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

--
-- Dumping data for table `referral_commission`
--


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

--
-- Dumping data for table `referral_commission_process`
--

INSERT INTO `referral_commission_process` (`COMMISSION_TYPE_CODE`, `COMMISSION_TYPE_DESC`, `ACTIVE`) VALUES
('APPOINTMENTS', 'Appointments', 'Y'),
('SERVICES', 'Services', 'Y');

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

--
-- Dumping data for table `referral_payable`
--


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

--
-- Dumping data for table `registration_history`
--


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

--
-- Dumping data for table `registration_status`
--

INSERT INTO `registration_status` (`REGISTRATION_STATUS_CODE`, `DESCRIPTION`, `ACTIVE`) VALUES
('ACTIVE', 'Active', 1),
('INACTIVE', 'In Active', 1),
('SUSPENDED', 'Suspended', 1);

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

--
-- Dumping data for table `registration_type`
--

INSERT INTO `registration_type` (`REGISTRATION_TYPE_CODE`, `DESCRIPTION`, `ACTIVE`) VALUES
('DIRECTSERVICEREGISTRATION', 'Direct', 1),
('EMERGENCY', 'Emergency', 1),
('NORMAL', 'Normal', 1);

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

--
-- Dumping data for table `relation`
--

INSERT INTO `relation` (`RELATION_CODE`, `DESCRIPTION`, `ACTIVE`) VALUES
('AUNT', 'Aunt', 1),
('BROTHER', 'Brother', 1),
('EMPLOYEE', 'Employee', 1),
('EMPLOYER', 'Employer', 1),
('FATHER', 'Father', 1),
('FRIEND', 'Friend', 1),
('MOTHER', 'Mother', 1),
('SISTER', 'Sister', 1),
('SPOUSE', 'Spouse', 1),
('UNCLE', 'Uncle', 1);

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

--
-- Dumping data for table `religion`
--

INSERT INTO `religion` (`RELIGION_CODE`, `DESCRIPTION`, `ACTIVE`) VALUES
('BUDDHIST', 'Buddhist', 1),
('CHRISTIAN', 'Christian', 1),
('HINDU', 'Hindu', 1),
('JAIN', 'Jain', 1),
('JEWISH', 'Jewish', 1),
('MUSLIM', 'Muslim', 1),
('PARSI', 'Parsi', 1),
('SIKH', 'Sikh', 1);

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

--
-- Dumping data for table `reminders`
--


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

--
-- Dumping data for table `reminder_options`
--

INSERT INTO `reminder_options` (`REMINDER_OPTIONS_CODE`, `DESCRIPTION`, `ACTIVE`, `VERSION`) VALUES
('EMAIL', 'Email', 1, 0),
('PHONE', 'Phone', 1, 0),
('SMS', 'SMS', 1, 0);

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

--
-- Dumping data for table `rendered_service`
--


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

--
-- Dumping data for table `report_param`
--

INSERT INTO `report_param` (`REPORT_CODE`, `WIDGET_NAME`, `WIDGET_LABEL`, `WIDGET_LENGTH`, `XTYPE`, `DATA_PROVIDER_METHOD`, `REQUIRED_FLAG`, `WIDGET_SEQ_NBR`) VALUES
('AppointmentSummary', 'fromDate', 'From', NULL, 'date', NULL, 'N', 1),
('AppointmentSummary', 'toDate', 'To', NULL, 'date', NULL, 'N', 2),
('DailyAppointments', 'date', 'For date', NULL, 'date', NULL, 'N', 1),
('DailyAppointments', 'departmentCode', 'Department', NULL, 'MVL', 'in.wtc.hcis.bo.common.DataModelManager.getDepartments', 'N', 2),
('DailyAppointments', 'doctorId', 'Doctor Name', NULL, 'MVL', 'in.wtc.hcis.bo.common.DataModelManager.getDoctors', 'N', 3),
('DailyPatientWaitTime', 'date', 'For date', NULL, 'date', NULL, 'Y', NULL),
('DailyPatientWaitTime', 'departmentCode', 'Department', NULL, 'MVL', 'in.wtc.hcis.bo.common.DataModelManager.getDepartments', 'N', NULL),
('DailyReceiptStatement', 'fromDate', 'From', NULL, 'date', NULL, 'N', NULL),
('DailyReceiptStatement', 'toDate', 'To', NULL, 'date', NULL, 'N', NULL),
('DepartmentWiseRevenue', 'departmentCode', 'Department', 90, 'MVL', 'in.wtc.hcis.bo.common.DataModelManager.getDepartments', 'N', 1),
('DepartmentWiseRevenue', 'fromDate', 'Date(from)', 90, 'date', NULL, 'N', 2),
('DepartmentWiseRevenue', 'toDate', 'Date(to)', 90, 'date', NULL, 'N', 3),
('DoctorwiseRevenueDetail', 'doctorId', 'Doctor', NULL, 'MVL', 'in.wtc.hcis.bo.common.DataModelManager.getDoctors', 'N', 3),
('DoctorwiseRevenueDetail', 'fromDate', 'From', NULL, 'date', NULL, 'N', 1),
('DoctorwiseRevenueDetail', 'toDate', 'To', NULL, 'date', NULL, 'N', 2),
('DoctorWiseServiceAssignment', 'doctorId', 'Doctor', NULL, 'MVL', 'in.wtc.hcis.bo.common.DataModelManager.getDoctors', 'N', 3),
('DoctorWiseServiceAssignment', 'fromDate', 'From', NULL, 'date', NULL, 'N', 1),
('DoctorWiseServiceAssignment', 'toDate', 'To', NULL, 'date', NULL, 'N', 2),
('OPDInflow', 'departmentCode', 'Department', NULL, 'MVL', 'in.wtc.hcis.bo.common.DataModelManager.getDepartments', 'N', 3),
('OPDInflow', 'fromDate', 'From', NULL, 'date', NULL, 'N', 1),
('OPDInflow', 'toDate', 'To', NULL, 'date', NULL, 'N', 2),
('OPDPatientBill', 'billNbr', 'Bill Number', NULL, 'string', NULL, 'Y', NULL),
('ServiceWiseRevenue', 'fromDate', 'From', NULL, 'date', NULL, 'N', 1),
('ServiceWiseRevenue', 'toDate', 'To', NULL, 'date', NULL, 'N', 2);

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
-- Table structure for table `room`
--

CREATE TABLE IF NOT EXISTS `room` (
  `ROOM_CODE` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACTIVE` smallint(1) DEFAULT NULL,
  PRIMARY KEY (`ROOM_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`ROOM_CODE`, `DESCRIPTION`, `ACTIVE`) VALUES
('100', 'Room 100', 1),
('1001', 'Room 1001', 1),
('1002', 'Room 1002', 1),
('1003', 'Room 1003', 1),
('1004', 'Room 1004', 1),
('1005', 'Room 1005', 1),
('102', 'Room 102', 1),
('103', 'Room 103', 1),
('104', 'Room 105', 1),
('105', 'Room 105', 1);

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

--
-- Dumping data for table `roster`
--


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

--
-- Dumping data for table `saluation`
--

INSERT INTO `saluation` (`SALUATION_CODE`, `DESCRIPTION`, `ACTIVE`) VALUES
('DR', 'Dr.', 1),
('MASTER', 'Master', 1),
('MR', 'Mr.', 1),
('MRS', 'Mrs.', 1),
('MS', 'Ms.', 1);

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

--
-- Dumping data for table `samples`
--


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

--
-- Dumping data for table `sample_master`
--

INSERT INTO `sample_master` (`SAMPLE_CODE`, `SAMPLE_DESCRIPTION`, `ACTIVE`, `VERSION`) VALUES
('b001', 'Blood', 1, 0);

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

--
-- Dumping data for table `secondary_consultation_criteria`
--


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

--
-- Dumping data for table `service`
--

INSERT INTO `service` (`SERVICE_CODE`, `SERVICE_NAME`, `SERVICE_DESC`, `SERVICE_PROCEDURE`, `SERVICE_STATUS_CODE`, `SERVICE_CHARGE`, `SERVICE_GROUP_CODE`, `SERVICE_TYPE_CD`, `DEPARTMENT_CODE`, `CREATE_DTM`, `CREATED_BY`, `EFFECTIVE_FROM_DT`, `EFFECTIVE_TO_DT`, `SUSPENDED_FROM_DT`, `SUSPENDED_TO_DT`, `DEPOSIT_AMT`, `LAST_MODIFIED_DTM`, `MODIFIED_BY`, `VERSION`) VALUES
('APPOINTMENT', 'Appointment', 'Appointment', '', 'ACTIVE', 200, 'Administrative', 'ADMINISTRATIVE', 'ADMIN', NULL, NULL, '2010-04-01', NULL, NULL, NULL, 0, '2010-05-25 10:13:50', 'Alok Ranjan', 1),
('BDT', 'Bone Density Testing', 'Bone density testing to detect osteoporosis', '', 'ACTIVE', 2500, 'Bones', 'SURGICAL', 'RADIOLOGY_IMAGING', '2010-05-24 19:56:37', 'Alok Ranjan', '2010-05-24', NULL, NULL, NULL, 400, '2010-06-01 13:53:48', 'Service price update process', 3),
('BERMUDA', 'BERMUDA GRASS', '', '', 'ACTIVE', 8000, 'Allergy', 'SURGICAL', 'GYN_OBS', '2010-05-25 12:11:33', 'Alok Ranjan', '2010-05-25', NULL, NULL, NULL, 800, '2010-05-25 12:22:48', 'Alok Ranjan', 1),
('FUMIGATUS', 'ASPERGILLUS FUMIGATUS', '', '', 'ACTIVE', 5000, 'Allergy', 'SURGICAL', 'GYN_OBS', '2010-05-25 12:10:36', 'Alok Ranjan', '2010-05-25', NULL, NULL, NULL, 500, '2010-05-25 12:23:30', 'Alok Ranjan', 1),
('OBSTETRIC FLOW', 'OBSTETRIC FLOW PANEL', ' OBSTETRIC FLOW PANEL ', '', 'ACTIVE', 7000, 'Abortion', 'SURGICAL', 'GYN_OBS', '2010-05-25 12:15:39', 'Alok Ranjan', '2010-05-25', NULL, NULL, NULL, 0, NULL, NULL, 0),
('REGISTRATION', 'Registration', 'Registration', NULL, 'ACTIVE', 50, 'Administrative', 'ADMINISTRATIVE', 'ADMIN', NULL, NULL, '2010-04-01', NULL, NULL, NULL, 0, '2010-05-25 10:13:50', 'Alok Ranjan', 1),
('TENUIS ', 'ALTERNARIA TENUIS ', '', '', 'ACTIVE', 5500, 'Allergy', 'SURGICAL', 'GYN_OBS', '2010-05-25 12:13:13', 'Alok Ranjan', '2010-05-25', NULL, NULL, NULL, 0, '2010-06-01 13:39:30', 'Service price update process', 2);

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

--
-- Dumping data for table `service_group`
--

INSERT INTO `service_group` (`SERVICE_GROUP_CODE`, `GROUP_NAME`, `PARENT_GROUP_ID`, `CREATE_DTM`, `CREATED_BY`, `LAST_MODIFIED_DTM`, `MODIFIED_BY`, `VERSION`) VALUES
('Abortion', 'Abortion', NULL, '2010-05-25 12:08:47', 'Alok Ranjan', NULL, NULL, 0),
('Administrative', 'Administrative', NULL, '2010-05-25 10:13:50', 'Alok Ranjan', NULL, NULL, 0),
('Allergy', 'Allergy', NULL, '2010-05-25 12:16:32', 'Alok Ranjan', NULL, NULL, 0),
('Bones', 'Bones Related', NULL, '2010-05-25 10:14:28', 'Alok Ranjan', NULL, NULL, 0),
('GENERAL', 'General', NULL, NULL, NULL, NULL, NULL, 0),
('HIV', 'Human Immunodeficiency Virus - HIV', 'Infections', '2010-05-25 10:26:24', 'Alok Ranjan', NULL, NULL, 0),
('Infections', 'Infections ', NULL, '2010-05-25 10:17:07', 'Alok Ranjan', '2010-05-25 11:34:07', NULL, 1),
('Radiology', 'Radiology', NULL, '2010-05-25 10:13:15', 'Alok Ranjan', NULL, NULL, 0),
('Viral', 'Viral Infection', 'Infections', '2010-05-25 10:17:32', 'Alok Ranjan', NULL, NULL, 0);

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

--
-- Dumping data for table `service_history`
--


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

--
-- Dumping data for table `service_package`
--


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

--
-- Dumping data for table `service_package_history`
--


-- --------------------------------------------------------

--
-- Table structure for table `service_package_status`
--

CREATE TABLE IF NOT EXISTS `service_package_status` (
  `STATUS_CODE` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `DESCRIPTION` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`STATUS_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `service_package_status`
--

INSERT INTO `service_package_status` (`STATUS_CODE`, `DESCRIPTION`) VALUES
('ACTIVE', 'Active'),
('CREATED', 'Created'),
('EXPIRED', 'Expired'),
('PUBLISHED', 'Published'),
('SUSPENDED', 'Suspended');

-- --------------------------------------------------------

--
-- Table structure for table `service_price_detail`
--

CREATE TABLE IF NOT EXISTS `service_price_detail` (
  `SERVICE_CD` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `SERVICE_CHARGE` double NOT NULL,
  `EFFECTIVE_FROM` date NOT NULL,
  `EFFECTIVE_TO` date DEFAULT NULL,
  `PROCESSED` varchar(1) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'N',
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATED_BY` varchar(80) COLLATE utf8_unicode_ci NOT NULL,
  `LAST_MODIFIED_DTM` timestamp NULL DEFAULT NULL,
  `MODIFIED_BY` varchar(80) COLLATE utf8_unicode_ci DEFAULT NULL,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`SERVICE_CD`,`EFFECTIVE_FROM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `service_price_detail`
--


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

--
-- Dumping data for table `service_status`
--

INSERT INTO `service_status` (`SERVICE_STATUS_CODE`, `DESCRIPTION`, `ACTIVE`) VALUES
('ACTIVE', 'Active', 1),
('CREATED', 'Created', 1),
('EXPIRED', 'Expired', 1),
('SUSPENDED', 'Suspended', 1);

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
  `EFFECTIVE_FROM_DT` date DEFAULT NULL,
  `EFFECTIVE_TO_DT` date DEFAULT NULL,
  `CREATED_BY` varchar(35) NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `VERSION` int(11) NOT NULL,
  PRIMARY KEY (`SPONSOR_NAME`,`INSURER_NAME`),
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
  `SPONSOR_DESC` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`SPONSOR_TYPE_CD`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sponsor_type`
--

INSERT INTO `sponsor_type` (`SPONSOR_TYPE_CD`, `SPONSOR_DESC`) VALUES
('CORPORATE', 'Employer'),
('SELF', 'Self'),
('TPA', 'Third party administrator');

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
('100', '102', 'Connecticut', 1, 0),
('100', '103', 'California', 1, 0),
('100', '104', 'Massachusetts', 1, 0),
('100', '105', 'Maine', 1, 0),
('100', '106', 'New Hampshire', 1, 0),
('100', '107', 'Rhode Island', 1, 0),
('100', '108', 'New York', 1, 0),
('100', '109', 'New Jersey', 1, 0),
('100', '110', 'Pennsylvania', 1, 0),
('100', '111', 'Virginia', 1, 0),
('100', '112', 'West Virginia', 1, 0),
('100', '113', 'Florida', 1, 0),
('100', '114', 'Alabama', 1, 0),
('100', '115', 'Delaware', 1, 0),
('100', '116', 'Washington', 1, 0),
('100', '118', 'Alaska', 1, 0),
('100', '119', 'Tennessee', 1, 0),
('100', '120', 'Georgia', 1, 0),
('100', '121', 'North Dakota', 1, 0),
('100', '122', 'South Dakota', 1, 0),
('100', '123', 'Minnesota', 1, 0),
('100', '124', 'Iowa', 1, 0),
('100', '125', 'Indiana', 1, 0),
('100', '126', 'Ohio', 1, 0),
('100', '127', 'Illinois', 1, 0),
('100', '128', 'South Carolina', 1, 0),
('100', '129', 'North Carolina', 1, 0),
('100', '130', 'Kentucky', 1, 0),
('100', '131', 'Louisiana', 1, 0),
('100', '132', 'Texas', 1, 0),
('100', '133', 'Oklahoma', 1, 0),
('100', '135', 'Mississippi', 1, 0),
('100', '136', 'Montana', 1, 0),
('100', '137', 'Missouri', 1, 0),
('100', '138', 'Kansas', 1, 0),
('100', '139', 'Nebraska', 1, 0),
('100', '140', 'Wyoming', 1, 0),
('100', '141', 'New Mexico', 1, 0),
('100', '142', 'Oregon', 1, 0),
('100', '144', 'Arkansas', 1, 0),
('100', '145', 'Arizona', 1, 0),
('100', '146', 'Utah', 1, 0),
('100', '147', 'Idaho', 1, 0),
('100', '148', 'Nevada', 1, 0),
('100', '149', 'Colorado', 1, 0),
('100', '150', 'Hawaii', 1, 0),
('100', '152', 'Michigan', 1, 0),
('100', '153', 'Wisconsin', 1, 0),
('100', '154', 'Maryland', 1, 0),
('100', '155', 'Vermont', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `status`
--

CREATE TABLE IF NOT EXISTS `status` (
  `STATUS_CODE` int(11) NOT NULL,
  `STATUS_DESC` varchar(25) CHARACTER SET latin1 NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `status`
--

INSERT INTO `status` (`STATUS_CODE`, `STATUS_DESC`) VALUES
(1, 'Active'),
(0, 'In-active');

-- --------------------------------------------------------

--
-- Table structure for table `status_transition`
--

CREATE TABLE IF NOT EXISTS `status_transition` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CONTEXT` varchar(128) NOT NULL,
  `INPUT_1` varchar(128) DEFAULT NULL,
  `INPUT_2` varchar(128) DEFAULT NULL,
  `FROM_STATUS` varchar(256) NOT NULL,
  `TO_STATUS` varchar(256) NOT NULL DEFAULT '',
  `CREATED_BY` varchar(80) NOT NULL,
  `CREATED_DTM` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CONTEXT` (`CONTEXT`,`INPUT_1`,`INPUT_2`,`FROM_STATUS`,`TO_STATUS`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `status_transition`
--

INSERT INTO `status_transition` (`ID`, `CONTEXT`, `INPUT_1`, `INPUT_2`, `FROM_STATUS`, `TO_STATUS`, `CREATED_BY`, `CREATED_DTM`) VALUES
(1, 'PATIENT_TEST_DETAIL', NULL, NULL, 'APPROVED', 'SHARED', 'Bhavesh', '2010-05-05 22:05:45'),
(2, 'PATIENT_TEST_DETAIL', NULL, NULL, 'CREATED', 'SPECIMEN_COLLECTED', 'Bhavesh', '2010-05-05 22:05:45'),
(3, 'PATIENT_TEST_DETAIL', NULL, NULL, 'CREATED', 'TEST_PERFORMED', 'Bhavesh', '2010-05-05 22:05:45'),
(4, 'PATIENT_TEST_DETAIL', NULL, NULL, 'DISAPPROVED', 'SPECIMEN_COLLECTED', 'Bhavesh', '2010-05-05 22:05:45'),
(5, 'PATIENT_TEST_DETAIL', NULL, NULL, 'DISAPPROVED', 'TEST_PERFORMED', 'Bhavesh', '2010-05-05 22:05:45'),
(6, 'PATIENT_TEST_DETAIL', NULL, NULL, 'RESULT_ENTERED', 'APPROVED', 'Bhavesh', '2010-05-05 22:05:45'),
(7, 'PATIENT_TEST_DETAIL', NULL, NULL, 'RESULT_ENTERED', 'DISAPPROVED', 'Bhavesh', '2010-05-05 22:05:45'),
(8, 'PATIENT_TEST_DETAIL', NULL, NULL, 'RESULT_SHARED', 'RESULT_COLLECTED', 'Bhavesh', '2010-05-05 22:05:45'),
(9, 'PATIENT_TEST_DETAIL', NULL, NULL, 'SPECIMEN_COLLECTED', 'TEST_PERFORMED', 'Bhavesh', '2010-05-05 22:05:45'),
(10, 'PATIENT_TEST_DETAIL', NULL, NULL, 'TEST_PERFORMED', 'RESULT_ENTERED', 'Bhavesh', '2010-05-05 22:05:45');

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

--
-- Dumping data for table `system_parameter`
--

INSERT INTO `system_parameter` (`ATTR_NAME`, `ATTR_LABEL`, `ATTR_VALUE`, `DATA_TYPE`, `ATTR_WIDTH`, `DATA_PROVIDER`, `ATTR_DESC`) VALUES
('CURRENCY_INDICATOR', 'Currency indicator', 'Rs', 'string', '40', NULL, 'It indicates currency indicator'),
('DATE_EMPTY_TEXT', 'Empty text for date fields', 'DD/MM/YYYY', 'string', '40', NULL, 'Text should be shown when a date field is empty'),
('IS_INTEGRATED_IPD', 'Integrate IPD', 'Y', 'MVL', '40', 'in.wtc.hcis.bo.common.DataModelManager.getActiveStatus', 'To enable/disable for IPD functionality in OPD'),
('IS_INTEGRATED_LIMS', 'Integrate LIMS', 'Y', 'MVL', '40', 'in.wtc.hcis.bo.common.DataModelManager.getActiveStatus', 'Flag to indecate whether LIMS is integrated or not, based on this flag certain decision  needs to be taken i.e. flow of controll for service manager'),
('IS_ROSTER_REQUIRED', 'Roster required', 'Y', 'MVL', '40', 'in.wtc.hcis.bo.common.DataModelManager.getActiveStatus', 'To enable/disable roster functionality'),
('ORDER_APPROVE_AT_CREATION', 'Approve order directly', 'N', 'MVL', '40', 'in.wtc.hcis.bo.common.DataModelManager.getActiveStatus', 'Approving order directly or through process ');

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

--
-- Dumping data for table `treatment_reason`
--

INSERT INTO `treatment_reason` (`REASON_CODE`, `DESCRIPTION`, `ACTIVE`) VALUES
('h001', 'Hairline fracture', 1),
('h002', 'Blockage in heart', 1),
('loo1', 'Ligament tear', 1),
('s001', 'Pain In abdomen', 1);

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

--
-- Dumping data for table `um_app_user`
--

INSERT INTO `um_app_user` (`APP_USER_ID`, `ROLE_LEVEL_IND`, `MEMBER_OF`, `FIRST_NAME`, `LAST_NAME`, `MIDDLE_NAME`, `USER_EFF_FROM_DATE`, `USER_EFF_TO_DATE`, `CONTACT_DETAIL_ID`) VALUES
('ajit', 'A', NULL, 'ajit', 'kumar', NULL, '2009-08-10', NULL, 5),
('alok', 'A', NULL, 'Alok', 'Ranjan', NULL, '2010-05-20', NULL, 9),
('bhavesh', 'A', NULL, 'Bhavesh', '', NULL, '2010-03-07', NULL, 8),
('sandeep', 'A', NULL, 'Sandeep', 'Kumar', NULL, '2010-03-07', NULL, 6),
('vinay', 'A', NULL, 'vinay', 'kurudi', NULL, '2010-03-07', NULL, 7);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci AUTO_INCREMENT=10 ;

--
-- Dumping data for table `um_contact_detail`
--

INSERT INTO `um_contact_detail` (`CONTACT_DETAIL_ID`, `CONTACT_PERSON_NAME`, `PHONE_NUMBER`, `FAX_NUMBER`, `EMAIL_ADDRESS`, `CORRESPONDENCE_ADDRESS`, `ZIP_CODE`, `STATE_CODE`, `COUNTRY_CODE`, `CITY`) VALUES
(5, 'ajit,kumar', '99877665544', NULL, 'ajit@gmail.com', '1-11-212radha krishna nagar', 500027, '1000002', '208', NULL),
(6, 'Sandeep,Kumar', '', NULL, '', ',', NULL, '1000042', '238', ''),
(7, 'vinay,kurudi', '', NULL, '', ',', NULL, 'CA', '100', ''),
(8, 'Bhavesh,', '', NULL, '', ',', NULL, '1000006', '208', ''),
(9, 'Alok,Ranjan', '9848473539', NULL, 'alok.ranjan@walkingtree.in', ',', 500072, '1000001', '208', 'Hyderabad');

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

--
-- Dumping data for table `um_country`
--

INSERT INTO `um_country` (`COUNTRY_CODE`, `DESCRIPTION`, `ACTIVE`) VALUES
('100', 'United States', 1),
('208', 'India', 1),
('238', 'Malaysia', 1);

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

--
-- Dumping data for table `um_role`
--

INSERT INTO `um_role` (`ROLE_ID`, `ROLE_NAME`, `ROLE_DESC`, `ROLE_LEVEL_IND_ID`, `INTERNAL_OR_EXTERNAL`, `EFF_FROM_DATE`, `EFF_TO_DATE`) VALUES
('ROLE_ADMIN', 'Administrator', 'Administrator', 'A', 'Y', '2009-08-06', NULL);

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

--
-- Dumping data for table `um_role_has_service`
--

INSERT INTO `um_role_has_service` (`ROLE_ID`, `SERVICE_ID`, `CREATE_DTM`) VALUES
('ROLE_ADMIN', 1, '2010-01-10 10:26:42'),
('ROLE_ADMIN', 2, '2010-01-10 10:26:42'),
('ROLE_ADMIN', 3, '2010-01-10 10:26:42'),
('ROLE_ADMIN', 4, '2010-01-10 10:26:42'),
('ROLE_ADMIN', 5, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 6, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 7, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 8, '2010-01-29 20:31:13'),
('ROLE_ADMIN', 9, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 10, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 11, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 12, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 13, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 14, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 15, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 17, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 18, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 19, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 20, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 21, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 22, '2010-04-05 19:38:24'),
('ROLE_ADMIN', 23, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 24, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 25, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 26, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 27, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 28, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 29, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 30, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 31, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 32, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 33, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 35, '2010-01-26 20:00:35'),
('ROLE_ADMIN', 36, '2010-01-27 23:02:20'),
('ROLE_ADMIN', 50, '2010-04-09 01:16:48'),
('ROLE_ADMIN', 100, '2010-02-03 20:22:42'),
('ROLE_ADMIN', 101, '2010-01-12 04:31:58'),
('ROLE_ADMIN', 102, '2010-01-12 04:31:58'),
('ROLE_ADMIN', 103, '2010-01-12 04:33:29'),
('ROLE_ADMIN', 104, '2010-01-12 04:33:29'),
('ROLE_ADMIN', 105, '2010-01-12 04:34:14'),
('ROLE_ADMIN', 106, '2010-01-12 04:35:48'),
('ROLE_ADMIN', 107, '2010-02-03 20:41:10'),
('ROLE_ADMIN', 108, '2010-02-03 20:14:45'),
('ROLE_ADMIN', 109, '2010-02-03 20:14:45'),
('ROLE_ADMIN', 110, '2010-02-06 00:13:11'),
('ROLE_ADMIN', 111, '2010-02-03 20:14:45'),
('ROLE_ADMIN', 112, '2010-02-03 20:14:45'),
('ROLE_ADMIN', 113, '2010-02-03 20:40:55'),
('ROLE_ADMIN', 121, '2010-01-10 22:31:07'),
('ROLE_ADMIN', 122, '2010-02-19 19:57:27'),
('ROLE_ADMIN', 123, '2010-01-10 23:23:53'),
('ROLE_ADMIN', 124, '2010-02-03 20:15:33'),
('ROLE_ADMIN', 125, '2010-02-03 20:15:33'),
('ROLE_ADMIN', 126, '2010-01-11 19:33:16'),
('ROLE_ADMIN', 127, '2010-02-19 19:10:05'),
('ROLE_ADMIN', 128, '2010-02-19 19:57:27'),
('ROLE_ADMIN', 129, '2010-02-03 20:15:52'),
('ROLE_ADMIN', 131, '2010-02-03 20:15:52'),
('ROLE_ADMIN', 132, '2010-02-03 20:16:19'),
('ROLE_ADMIN', 133, '2010-02-06 00:14:03'),
('ROLE_ADMIN', 134, '2010-02-03 20:16:19'),
('ROLE_ADMIN', 200, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 201, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 202, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 203, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 204, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 205, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 206, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 207, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 208, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 209, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 210, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 213, '2010-02-17 11:51:04'),
('ROLE_ADMIN', 242, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 300, '2010-02-19 19:55:58'),
('ROLE_ADMIN', 301, '2010-02-19 19:55:58'),
('ROLE_ADMIN', 302, '2010-02-19 19:55:58'),
('ROLE_ADMIN', 303, '2010-02-19 19:55:58'),
('ROLE_ADMIN', 304, '2010-02-19 19:55:58'),
('ROLE_ADMIN', 305, '2010-02-19 19:55:57'),
('ROLE_ADMIN', 311, '2010-02-19 20:01:24'),
('ROLE_ADMIN', 312, '2010-02-19 20:01:24'),
('ROLE_ADMIN', 313, '2010-02-19 20:01:24'),
('ROLE_ADMIN', 314, '2010-02-19 20:01:23'),
('ROLE_ADMIN', 321, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 331, '2010-02-03 20:16:19'),
('ROLE_ADMIN', 341, '2010-01-10 10:26:43'),
('ROLE_ADMIN', 342, '2010-03-02 20:15:05'),
('ROLE_ADMIN', 343, '2010-03-02 20:15:05'),
('ROLE_ADMIN', 344, '2010-03-02 20:15:05'),
('ROLE_ADMIN', 345, '2010-03-02 20:15:05'),
('ROLE_ADMIN', 346, '2010-03-02 20:15:05'),
('ROLE_ADMIN', 347, '2010-03-02 20:15:05'),
('ROLE_ADMIN', 348, '2010-03-02 20:15:05'),
('ROLE_ADMIN', 349, '2010-03-02 20:15:05'),
('ROLE_ADMIN', 350, '2010-03-07 22:00:38'),
('ROLE_ADMIN', 351, '2010-03-07 22:00:38'),
('ROLE_ADMIN', 352, '2010-03-11 10:42:28'),
('ROLE_ADMIN', 353, '2010-03-13 15:18:41'),
('ROLE_ADMIN', 400, '2010-03-29 19:47:46'),
('ROLE_ADMIN', 401, '2010-03-29 19:47:46'),
('ROLE_ADMIN', 402, '2010-03-31 04:19:28'),
('ROLE_ADMIN', 410, '2010-03-29 20:17:03'),
('ROLE_ADMIN', 411, '2010-03-29 23:55:36'),
('ROLE_ADMIN', 412, '2010-04-05 23:27:56'),
('ROLE_ADMIN', 413, '2010-05-10 17:04:08'),
('ROLE_ADMIN', 421, '2010-03-30 20:11:37'),
('ROLE_ADMIN', 430, '2010-04-13 04:57:57'),
('ROLE_ADMIN', 432, '2010-04-14 03:46:39');

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

--
-- Dumping data for table `um_service`
--

INSERT INTO `um_service` (`SERVICE_ID`, `NAME`, `DESCRIPTION`, `PARENT_SERVICE_ID`, `CREATE_DTM`) VALUES
(1, 'OPD', 'OPD', NULL, '2010-01-10 10:26:42'),
(2, 'patientRegistration', 'Patient registration', 1, '2010-01-10 10:26:42'),
(3, 'patietnList', 'Patient list', 1, '2010-01-10 10:26:42'),
(4, 'newAppointment', 'New appointment', 1, '2010-03-08 22:16:26'),
(5, 'appointmentList', 'Appointment list', 1, '2010-01-10 10:26:43'),
(6, 'treatmentHistory', 'Treatment history', 1, '2010-01-10 10:26:43'),
(7, 'billing', 'OPD Billing', 200, '2010-02-21 00:57:29'),
(8, 'patientServiceManagement', 'Patient service management', 1, '2010-01-29 20:31:13'),
(9, 'administration', 'Administration', NULL, '2010-01-10 10:26:43'),
(10, 'doctor', 'Add doctor', 205, '2010-01-10 10:26:43'),
(11, 'doctorList', 'Doctor list', 205, '2010-01-10 10:26:43'),
(12, 'addRoster', 'Add roster', 207, '2010-01-10 10:26:43'),
(13, 'rosterList', 'Roster list', 207, '2010-01-10 10:26:43'),
(14, 'addReferral', 'Add referral', 205, '2010-01-10 10:26:43'),
(15, 'referralList', 'Referral list', 205, '2010-01-10 10:26:43'),
(17, 'addVaccinationSchedule', 'Add vaccination schedule', 207, '2010-01-10 10:26:43'),
(18, 'vaccinationScheduleList', 'Vaccination schedule list', 207, '2010-01-10 10:26:43'),
(19, 'patientVaccinationSchedule', ' Patient vaccination schedule ', 207, '2010-01-10 10:26:43'),
(20, 'configureServiceGroup', ' Configure service group ', 203, '2010-01-10 10:26:43'),
(21, 'serviceGroupList', ' Service group list ', 203, '2010-01-10 10:26:43'),
(22, 'configureService', 'Configure service', 203, '2010-04-05 19:38:05'),
(23, 'serviceList', ' Service list ', 203, '2010-01-10 10:26:43'),
(24, 'configurePackage', ' Configure package', 203, '2010-01-10 10:26:43'),
(25, 'packageList', ' Package list ', 203, '2010-01-10 10:26:43'),
(26, 'configureBrand', ' Configure brand', 208, '2010-01-10 10:26:43'),
(27, 'brandList', ' Brand list', 208, '2010-01-10 10:26:43'),
(28, 'configureMedicine', ' Configure medicine', 208, '2010-01-10 10:26:43'),
(29, 'medicineList', ' Medicine list', 208, '2010-01-10 10:26:43'),
(30, 'createUser', ' Create user', 204, '2010-01-10 10:26:43'),
(31, 'userList', ' User list', 204, '2010-01-10 10:26:43'),
(32, 'createRole', ' Create role', 204, '2010-01-10 10:26:43'),
(33, 'roleList', 'Role list', 204, '2010-01-10 10:26:43'),
(35, 'systemConfiguration', 'System configuration', 210, '2010-01-26 20:00:35'),
(36, 'issueReceipt', 'Issue receipt', 200, '2010-01-27 23:02:20'),
(50, 'updateServicePrice', 'Update service price', 203, '2010-04-19 00:07:21'),
(100, 'IPD', 'IPD', NULL, '2010-02-03 20:22:42'),
(101, 'addBed', 'Add bed', 201, '2010-01-12 04:31:58'),
(102, 'bedsList', 'Beds list', 201, '2010-01-12 04:31:58'),
(103, 'addBedPool', 'Add bed pool', 201, '2010-01-12 04:33:29'),
(104, 'bedPoolList', 'Bed pool list', 201, '2010-01-12 04:33:29'),
(105, 'addBedEnvelop', 'Add bed envelop', 201, '2010-01-12 04:34:14'),
(106, 'bedEnvelopsList', 'Bed envelops list', 201, '2010-01-12 04:35:48'),
(107, 'bedOccupancy', 'Overall bed occupancy', 100, '2010-02-03 20:41:10'),
(108, 'assignBed', 'Assign bed', 100, '2010-02-03 20:14:45'),
(109, 'bedBooking', 'Bed booking', 100, '2010-02-03 20:14:45'),
(110, 'bedBookingList', 'Bed booking list', 100, '2010-02-06 00:13:11'),
(111, 'addOrder', 'Add order', 100, '2010-02-03 20:14:45'),
(112, 'addOrderTemplate', 'Add order template', 202, '2010-02-03 20:14:45'),
(113, 'doctorOrdersList', 'Doctor orders list', 100, '2010-02-03 20:40:55'),
(121, 'addInsurer', 'Add insurer', 205, '2010-01-10 22:31:07'),
(122, 'addInsurancePlan', 'Add insurance plan', 206, '2010-02-19 19:57:27'),
(123, 'addSponsor', 'Add sponsor', 205, '2010-01-10 23:23:53'),
(124, 'addClaim', 'Add claim request', 200, '2010-02-03 20:15:33'),
(125, 'doctorOrderGroup', 'Add doctor order group', 202, '2010-02-03 20:15:33'),
(126, 'insurerList', 'Insurer list', 205, '2010-01-11 19:33:16'),
(127, 'sponsorList', 'Sponsor list', 205, '2010-02-19 19:10:05'),
(128, 'insurancePlanList', 'Insurance plan list', 206, '2010-02-19 19:57:27'),
(129, 'claimList', 'Claim list', 200, '2010-02-03 20:15:52'),
(131, 'doctorOrderGroupList', 'Doctor order group list', 202, '2010-02-03 20:15:52'),
(132, 'doctorOrderTemplateList', 'Doctor order template list', 202, '2010-02-03 20:16:19'),
(133, 'admissionsList', 'Admissions list', 100, '2010-02-06 00:14:03'),
(134, 'admission', 'View admission details', 100, '2010-02-21 13:51:31'),
(200, 'BILL', 'Billing', NULL, '2010-02-20 15:19:22'),
(201, 'bedManagement', 'Bed Management', 9, '2010-01-10 10:26:43'),
(202, 'orderManagement', 'Order Management', 9, '2010-01-10 10:26:43'),
(203, 'serviceAndPackage', 'Service and Package', 9, '2010-01-10 10:26:43'),
(204, 'userAndRole', 'User and Role', 9, '2010-01-10 10:26:43'),
(205, 'entities', 'Entities', 9, '2010-01-10 10:26:43'),
(206, 'mediclaim', 'Mediclaim', 9, '2010-01-10 10:26:43'),
(207, 'scheduling', 'Scheduling', 9, '2010-01-10 10:26:43'),
(208, 'medicineAndBrand', 'Medicine and Brand', 9, '2010-01-10 10:26:43'),
(209, 'reception', 'Reception', NULL, '2010-02-19 20:37:42'),
(210, 'configuration', 'Configuration', 9, '2010-01-10 10:26:43'),
(213, 'PHARMA', 'Pharmacy', NULL, '2010-02-17 11:51:04'),
(242, 'pharmacy', 'Pharmacy', 213, '2010-01-10 10:26:43'),
(300, 'addSponsor1', 'Add sponsor', 206, '2010-02-19 19:55:58'),
(301, 'addInsurer1', 'Add insurer', 206, '2010-02-19 19:55:58'),
(302, 'insurerList1', 'Insurer list', 206, '2010-02-19 19:55:58'),
(303, 'sponsorList1', 'Sponsor list', 206, '2010-02-19 19:55:58'),
(304, 'addClaim1', 'Add claim request', 206, '2010-02-19 19:55:58'),
(305, 'claimList1', 'Claim list', 206, '2010-02-19 19:55:57'),
(311, 'patientRegistration1', 'Patient registration', 209, '2010-02-19 20:01:24'),
(312, 'patietnList1', 'Patient list', 209, '2010-02-19 20:01:24'),
(313, 'serviceList1', ' Service list ', 209, '2010-02-19 20:01:24'),
(314, 'packageList1', ' Package list ', 209, '2010-02-19 20:01:23'),
(321, 'billing1', 'Billing', 1, '2010-01-10 10:26:43'),
(331, 'admission1', 'IPD Billing', 200, '2010-02-21 13:51:14'),
(341, 'medicineList1', 'Medicine list', 213, '2010-01-10 10:26:43'),
(342, 'materialManagement', 'Material management', NULL, '2010-03-02 20:31:59'),
(343, 'accounting', 'Accounting', NULL, '2010-03-02 20:31:42'),
(344, 'humanResources', 'Human resources', NULL, '2010-03-02 20:31:28'),
(345, 'materialManagement1', 'Material management', 342, '2010-03-02 20:02:33'),
(346, 'accounting1', 'Accounting', 343, '2010-01-10 10:26:42'),
(347, 'humanResources1', 'Human resources', 344, '2010-03-02 20:02:15'),
(348, 'Analysis', 'Analysis', NULL, '2010-02-24 17:57:52'),
(349, 'Kabota', 'Kabota', 348, '2010-03-03 21:33:01'),
(350, 'admissionOrder', 'Admission order', 100, '2010-03-07 21:31:42'),
(351, 'dischargeOrder', 'Discharge order', 100, '2010-03-07 21:31:42'),
(352, 'serviceAssignmentList', 'Service assignment list', 100, '2010-03-08 19:22:39'),
(353, 'gis', 'GIS', 348, '2010-03-18 02:22:03'),
(400, 'LIMS', 'Laboratory', NULL, '2010-03-29 19:47:24'),
(401, 'addTechnique', 'Add technique reagent', 400, '2010-03-29 19:50:04'),
(402, 'techniqueList', 'Technique Reagent List', 400, '2010-03-31 04:19:14'),
(410, 'addLabDetail', 'Add Lab Detail', 400, '2010-03-31 21:10:53'),
(411, 'searchLaboratory', 'Search Laboratory', 400, '2010-04-03 05:22:03'),
(412, 'addCollectionPoint', 'Add Collection Point', 400, '2010-04-05 23:34:39'),
(413, 'collectionPointList', 'Collection point list', 400, '2010-05-10 17:03:37'),
(421, 'requisitionList', 'Requisition list', 400, '2010-03-31 04:47:21'),
(430, 'addTestAttribute', 'Add test attribute', 400, '2010-04-13 04:57:23'),
(431, 'addLabTest', 'Add lab test', 400, '2010-04-05 00:53:05'),
(432, 'testAttributeList', 'Test attribute list', 400, '2010-04-14 03:46:19');

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

--
-- Dumping data for table `um_state`
--

INSERT INTO `um_state` (`COUNTRY_CODE`, `STATE_CODE`, `DESCRIPTION`, `ACTIVE`) VALUES
('208', '1000000', 'Andaman and Nicobar Islands', 1),
('208', '1000001', 'Andhra Pradesh', 1),
('208', '1000002', 'Arunachal Pradesh', 1),
('208', '1000003', 'Assam', 1),
('208', '1000004', 'Bihar', 1),
('208', '1000005', 'Chandigarh', 1),
('208', '1000006', 'Chhattisgarh ', 1),
('208', '1000007', 'Daman and Diu', 1),
('208', '1000008', 'Delhi', 1),
('208', '1000009', 'Dadra and Nagar Haveli', 1),
('208', '1000010', 'Goa', 1),
('208', '1000011', 'Gujarat', 1),
('208', '1000012', 'Himachal Pradesh', 1),
('208', '1000013', 'Haryana', 1),
('208', '1000014', 'Jharkhand', 1),
('208', '1000015', 'Jammu and Kashmir', 1),
('208', '1000016', 'Karnataka', 1),
('208', '1000017', 'Kerala', 1),
('208', '1000018', 'Lakshadweep', 1),
('208', '1000019', 'Maharashtra', 1),
('208', '1000020', 'Meghalaya', 1),
('208', '1000021', 'Manipur', 1),
('208', '1000022', 'Madhya Pradesh', 1),
('208', '1000023', 'Mizoram', 1),
('208', '1000024', 'Nagaland', 1),
('208', '1000025', 'Orissa', 1),
('208', '1000026', 'Punjab', 1),
('208', '1000027', 'Puducherry', 1),
('208', '1000028', 'Rajasthan', 1),
('208', '1000029', 'Sikkim', 1),
('208', '1000030', 'Tamil Nadu', 1),
('208', '1000031', 'Tripura', 1),
('208', '1000032', 'Uttarakhand', 1),
('208', '1000033', 'Uttar Pradesh', 1),
('208', '1000034', 'West Bengal', 1),
('238', '1000035', 'Johor', 1),
('238', '1000036', 'Kedah', 1),
('238', '1000037', 'Kelantan', 1),
('238', '1000038', 'Malacca', 1),
('238', '1000039', 'Negeri Sembilan', 1),
('238', '1000040', 'Pahang', 1),
('238', '1000041', 'Penang', 1),
('238', '1000042', 'Perak', 1),
('238', '1000043', 'Perlis', 1),
('238', '1000044', 'Selangor', 1),
('238', '1000045', 'Terengganu', 1),
('238', '1000046', 'Sabah', 1),
('238', '1000047', 'Sarawak', 1),
('238', '1000048', 'Kuala Lumpur', 1),
('238', '1000049', 'Labuan', 1),
('238', '1000050', 'Putrajaya', 1),
('100', 'AK', 'Alaska', 1),
('100', 'AL', 'Alabama', 1),
('100', 'AR', 'Arkansas', 1),
('100', 'AZ', 'Arizona', 1),
('100', 'CA', 'California', 1),
('100', 'CO', 'Colorado', 1),
('100', 'CT', 'Connecticut', 1),
('100', 'DC', 'D.C.', 1),
('100', 'DE', 'Delaware', 1),
('100', 'FL', 'Florida', 1),
('100', 'GA', 'Georgia', 1),
('100', 'HI', 'Hawaii', 1),
('100', 'IA', 'Iowa', 1),
('100', 'ID', 'Idaho', 1),
('100', 'IL', 'Illinois', 1),
('100', 'IN', 'Indiana', 1),
('100', 'KS', 'Kansas', 1),
('100', 'KY', 'Kentucky', 1),
('100', 'LA', 'Louisiana', 1),
('100', 'MA', 'Massachusetts', 1),
('100', 'MD', 'Maryland', 1),
('100', 'ME', 'Maine', 1),
('100', 'MI', 'Michigan', 1),
('100', 'MN', 'Minnesota', 1),
('100', 'MO', 'Missouri', 1),
('100', 'MS', 'Mississippi', 1),
('100', 'MT', 'Montana', 1),
('100', 'NC', 'North Carolina', 1),
('100', 'ND', 'North Dakota', 1),
('100', 'NE', 'Nebraska', 1),
('100', 'NH', 'New Hampshire', 1),
('100', 'NJ', 'New Jersey', 1),
('100', 'NM', 'New Mexico', 1),
('100', 'NV', 'Nevada', 1),
('100', 'NY', 'New York', 1),
('100', 'OH', 'Ohio', 1),
('100', 'OK', 'Oklahoma', 1),
('100', 'OR', 'Oregon', 1),
('100', 'PA', 'Pennsylvania', 1),
('100', 'RI', 'Rhode Island', 1),
('100', 'SC', 'South Carolina', 1),
('100', 'SD', 'South Dakota', 1),
('100', 'TN', 'Tennessee', 1),
('100', 'TX', 'Texas', 1),
('100', 'UT', 'Utah', 1),
('100', 'VA', 'Virginia', 1),
('100', 'VT', 'Vermont', 1),
('100', 'WA', 'Washington', 1),
('100', 'WI', 'Wisconsin', 1),
('100', 'WV', 'West Virginia', 1),
('100', 'WY', 'Wyoming', 1);

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

--
-- Dumping data for table `um_user_has_role`
--

INSERT INTO `um_user_has_role` (`APP_USER_ID`, `ROLE_ID`, `CREATE_DTM`) VALUES
('ajit', 'ROLE_ADMIN', '2010-01-10 10:32:50'),
('alok', 'ROLE_ADMIN', '2010-05-21 01:18:15'),
('bhavesh', 'ROLE_ADMIN', '2010-03-07 16:07:34'),
('sandeep', 'ROLE_ADMIN', '2010-03-07 15:56:16'),
('vinay', 'ROLE_ADMIN', '2010-03-07 16:02:09');

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

--
-- Dumping data for table `um_user_password_history`
--

INSERT INTO `um_user_password_history` (`APP_USER_ID`, `PASSWORD`, `START_DTM`, `END_DTM`, `CREATE_DTM`, `LAST_UPDATE_DTM`, `VERSION`) VALUES
('ajit', '8a68dc3e925eacf92633be230722a140', '2009-08-18 19:31:49', NULL, '2009-08-18 19:31:49', NULL, 0),
('alok', 'bad220c335d0c1f53548f6acdb17e265', '2010-05-20 00:00:00', NULL, '2010-05-21 01:18:15', NULL, 0),
('bhavesh', '2c23ec98041d2d78c90db61bee5c3652', '2010-03-07 00:00:00', NULL, '2010-03-07 16:07:34', NULL, 0),
('sandeep', '00dcf16d903e5890aaba465b0b1ba51f', '2010-03-07 00:00:00', NULL, '2010-03-07 15:56:16', NULL, 0),
('vinay', '5a8eaa3e637f51ba3f9df03355d7bc08', '2010-03-07 00:00:00', NULL, '2010-03-07 16:02:09', NULL, 0);

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

--
-- Dumping data for table `um_user_status`
--


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

--
-- Dumping data for table `vaccination`
--

INSERT INTO `vaccination` (`VACCINATION_CD`, `VACCINATION_NAME`, `AGE_RANGE`, `SUBSTITUTE_FOR`, `ACTIVE_FLAG`, `USER_ID`, `LAST_MODIFIED_DTM`, `VERSION`) VALUES
('ANTHRAX', 'Anthrax', 'Birth', NULL, 'Y', 'vinay kurudi', '2010-01-05 11:19:33', 0),
('DTAP', 'DTaP', '1-5 years', NULL, 'Y', 'vinay kurudi', '2010-01-05 11:19:33', 0),
('HEPATITIS-A', 'Hepatitis A', '4 weeks ', NULL, 'Y', 'vinay kurudi', '2010-01-05 11:19:33', 0),
('HEPATITIS-B ', 'Hepatitis B ', '> 15 months ', NULL, 'Y', 'vinay kurudi', '2010-01-05 11:19:33', 0),
('POLIO ', 'Polio', 'Birth', NULL, 'Y', 'vinay kurudi', '2010-01-05 11:19:33', 0),
('RABIES ', 'Rabies', '> 15 months ', NULL, 'Y', 'vinay kurudi', '2010-01-05 11:19:33', 0),
('TETANUS ', 'Tetanus/Diptheria/(Pertussis) ', 'Birth', NULL, 'Y', 'vinay kurudi', '2010-01-05 11:19:33', 0);

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

--
-- Dumping data for table `vaccination_schedule`
--

INSERT INTO `vaccination_schedule` (`SCHEDULE_NAME`, `SCHEDULE_DESC`, `AGE_GROUP`, `USER_ID`, `LAST_MODIFIED_DT`, `ACTIVE_FLAG`, `VERSION`) VALUES
('Child Schedule', 'Vaccination for child', '1 - 24 months', 'ajit kumar', '2010-06-19 06:31:56', 'Y', 1),
('New Born', 'Vaccination for new born', '0-30 days', 'ajit kumar', '2010-06-19 06:28:23', 'Y', 0);

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
-- Dumping data for table `vaccination_schedule_details`
--

INSERT INTO `vaccination_schedule_details` (`SCHEDULE_NAME`, `SEQ_NBR`, `PERIOD_IN_DAYS`, `VACCIANTION_CD`, `VACCINATION_TYPE_CD`, `DOSAGE`, `AGE`, `DELETED_FLAG`, `USER_ID`, `LAST_MODIFIED_DT`, `VERSION`) VALUES
('Child Schedule', 1, 1, 'HEPATITIS-A', 'MANDATORY', '1', '4 weeks ', NULL, 'ajit kumar', '2010-06-19 06:31:15', 0),
('Child Schedule', 2, 50, 'DTAP', 'MANDATORY', '1', '1-5 years', NULL, 'ajit kumar', '2010-06-19 06:31:15', 0),
('Child Schedule', 3, 200, 'HEPATITIS-B ', 'OPTIONAL', '1', '> 15 months ', NULL, 'ajit kumar', '2010-06-19 06:31:56', 0),
('New Born', 1, 0, 'ANTHRAX', 'MANDATORY', '1', 'Birth', NULL, 'ajit kumar', '2010-06-19 06:28:23', 0),
('New Born', 2, 2, 'POLIO ', 'MANDATORY', '1', 'Birth', NULL, 'ajit kumar', '2010-06-19 06:28:23', 0);

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
