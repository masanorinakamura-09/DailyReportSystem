INSERT INTO employee(name,created_at,updated_at,delete_flag) VALUES ("煌木　太郎",CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,0);
INSERT INTO employee(name,created_at,updated_at,delete_flag) VALUES ("田中　太郎",CURRENT_TIMESTAMP,CURRENT_TIMESTAMP,0);
INSERT INTO authentication(code,password,role,employee_id) VALUES ("ktaro","$2a$08$clh9XaYYznpX9WDqySgiCuUu4znpSeu2oJi5l2Q00UJs42Llrbd7S","管理者",1);
INSERT INTO authentication(code,password,role,employee_id) VALUES ("ttaro","$2a$10$F1k.2HZtkRpoSDymdZCTnuI7eVdoKP.Yb8gtiWmVTKejp53Htlm56","一般",2);
INSERT INTO timecard (leavingwork_time, timecard_date, work_time, employee_id ) VALUES ("18:00",'2022-04-11',"09:00",1);
INSERT INTO timecard (leavingwork_time, timecard_date, work_time, employee_id ) VALUES ("21:00",'2021-09-29',"12:00",1);
INSERT INTO timecard (leavingwork_time, timecard_date, work_time, employee_id ) VALUES ("23:00",'2020-11-11',"07:00",2);