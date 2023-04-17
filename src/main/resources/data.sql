INSERT INTO employee(name,created_at,updated_at,delete_flag) VALUES ("煌木　太郎",CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,0);
INSERT INTO employee(name,created_at,updated_at,delete_flag) VALUES ("田中　太郎",CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,0);
INSERT INTO daily_report_system.employee ( id, created_at, delete_flag, name, updated_at ) VALUES (3,'2023-04-17 20:36:41',0,'ataro','2023-04-17 20:36:41');
INSERT INTO daily_report_system.employee ( id, created_at, delete_flag, name, updated_at ) VALUES (4,'2023-04-17 20:37:14',0,'btaro','2023-04-17 20:37:14');
INSERT INTO daily_report_system.employee ( id, created_at, delete_flag, name, updated_at ) VALUES (5,'2023-04-17 20:37:30',0,'ctaro','2023-04-17 20:37:30');
INSERT INTO daily_report_system.employee ( id, created_at, delete_flag, name, updated_at ) VALUES (6,'2023-04-17 20:37:40',0,'dtaro','2023-04-17 20:37:40');


INSERT INTO authentication(code,password,role,employee_id) VALUES ("ktaro","$2a$08$clh9XaYYznpX9WDqySgiCuUu4znpSeu2oJi5l2Q00UJs42Llrbd7S","管理者",1);
INSERT INTO authentication(code,password,role,employee_id) VALUES ("ttaro","$2a$10$F1k.2HZtkRpoSDymdZCTnuI7eVdoKP.Yb8gtiWmVTKejp53Htlm56","一般",2);
INSERT INTO daily_report_system.authentication ( code, password, role, employee_id ) VALUES ('ataro','$2a$10$DsalQIriYr9Cepk6iHtYPedIo.4JRdca5/r4phrTMjlyHMXcRnfOm','一般',3);
INSERT INTO daily_report_system.authentication ( code, password, role, employee_id ) VALUES ('btaro','$2a$10$5AwEBkqWRaBOfgyUkFG6Cumz8SD7cg6oSiZ.Ov5wjlWYS6faUPmwO','一般',4);
INSERT INTO daily_report_system.authentication ( code, password, role, employee_id ) VALUES ('ctaro','$2a$10$.10eiiqbIbPo6rrY8wFGVuDrnn9cpAQcgjNFBMIfi2P9LwgRBtj0S','一般',5);
INSERT INTO daily_report_system.authentication ( code, password, role, employee_id ) VALUES ('dtaro','$2a$10$z39ohpEDXHC2p8uMIiHYKu3WMY4uOd/fN6bh9KxV8u32NKU72zD2C','一般',6);

INSERT INTO timecard (leavingwork_time, timecard_date, work_time, employee_id ) VALUES ("18:00",'2022-04-11',"09:00",1);
INSERT INTO timecard (leavingwork_time, timecard_date, work_time, employee_id ) VALUES ("21:00",'2021-09-29',"12:00",1);
INSERT INTO timecard (leavingwork_time, timecard_date, work_time, employee_id ) VALUES ("23:00",'2020-11-11',"07:00",2);

INSERT INTO daily_report_system.report ( id, content, created_at, report_date, title, updated_at, employee_id ) VALUES (1,'ktaro','2023-04-17 20:46:26','2023-04-17','ktaro','2023-04-17 20:46:26',1);
INSERT INTO daily_report_system.report ( id, content, created_at, report_date, title, updated_at, employee_id ) VALUES (2,'ttaro','2023-04-17 20:46:45','2023-04-17','ttaro','2023-04-17 20:46:45',2);
INSERT INTO daily_report_system.report ( id, content, created_at, report_date, title, updated_at, employee_id ) VALUES (3,'ataro','2023-04-17 20:47:11','2023-04-17','ataro','2023-04-17 20:47:11',3);
INSERT INTO daily_report_system.report ( id, content, created_at, report_date, title, updated_at, employee_id ) VALUES (4,'btaro','2023-04-17 20:47:31','2023-04-17','btaro','2023-04-17 20:47:31',4);
INSERT INTO daily_report_system.report ( id, content, created_at, report_date, title, updated_at, employee_id ) VALUES (5,'ctaro','2023-04-17 20:47:49','2023-04-17','ctaro','2023-04-17 20:47:49',5);
INSERT INTO daily_report_system.report ( id, content, created_at, report_date, title, updated_at, employee_id ) VALUES (6,'dtaro','2023-04-17 20:48:07','2023-04-17','dtaro','2023-04-17 20:48:07',6);
