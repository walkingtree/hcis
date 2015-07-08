--
-- Dumping data for table `system_parameter`
--

INSERT INTO `system_parameter` (`ATTR_NAME`, `ATTR_LABEL`, `ATTR_VALUE`, `DATA_TYPE`, `ATTR_WIDTH`, `DATA_PROVIDER`, `ATTR_DESC`) VALUES
('CURRENCY_INDICATOR', 'Currency indicator', 'Rs', 'string', '40', NULL, 'It indicates currency indicator'),
('IS_ROSTER_REQUIRED', 'Roster required', 'Y', 'MVL', '40', 'in.wtc.hcis.bo.common.DataModelManager.getActiveStatus', 'To enable/disable roster functionality'),
('IS_INTEGRATED_IPD', 'Integrate IPD', 'Y', 'MVL', '40', 'in.wtc.hcis.bo.common.DataModelManager.getActiveStatus', 'To enable/disable for IPD functionality in OPD');