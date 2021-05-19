--Script to initialize the app data
--insert type Device
INSERT INTO device_type VALUES(1,'Windows Workstation','Windows');
INSERT INTO device_type VALUES(2,'Windows Server','Windows');
INSERT INTO device_type VALUES(3,'Mac','MacOS');
--insert Service
INSERT INTO service VALUES(1,1,'Antivirus in Windows Workstation device','Antivirus',5);
INSERT INTO service VALUES(2,2,'Antivirus in Windows Server device','Antivirus',5);
INSERT INTO service VALUES(3,3,'Antivirus in Mac device','Antivirus',7);
INSERT INTO service VALUES(4,null,'Backup data in any device','Cloudberry',3);
INSERT INTO service VALUES(5,null,'Ticketing system for alterts in any device','PSA',2);
INSERT INTO service VALUES(6,null,'Remote conection in any device','TeamViewer',1);
INSERT INTO service VALUES(7,null,'Cost of each device','DeviceCost',4);
commit;
