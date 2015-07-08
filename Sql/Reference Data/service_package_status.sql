INSERT INTO `service_package_status` (`STATUS_CODE`, `DESCRIPTION`) VALUES
('ACTIVE', 'Active'),
('CREATED', 'Created'),
('EXPIRED', 'Expired'),
('PUBLISHED', 'Published'),
('SUSPENDED', 'Suspended');

INSERT INTO `service_group` (
`SERVICE_GROUP_CODE` ,
`GROUP_NAME` ,
`PARENT_GROUP_ID` ,
`CREATE_DTM` ,
`CREATED_BY` ,
`LAST_MODIFIED_DTM` ,
`MODIFIED_BY` ,
`VERSION`
)
VALUES (
'GENERAL', 'General', NULL , NULL , NULL , NULL , NULL , ''
);