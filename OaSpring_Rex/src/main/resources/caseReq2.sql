DROP SEQUENCE case_seq; 
 
 DROP TABLE CASE_REQ;
 DROP TABLE EMPLOYEE;
 DROP TABLE UNIT;
 
 
 
 CREATE TABLE UNIT (
  UNIT_ID             VARCHAR2(5) NOT NULL,
  UNIT_NAME           VARCHAR2(15) NOT NULL,
  CONSTRAINT UNIT_PK  PRIMARY KEY (UNIT_ID)
 );
 
 INSERT INTO UNIT VALUES ('GOV01','政府服務處');
 INSERT INTO UNIT VALUES ('ENG01','工程服務處');
 INSERT INTO UNIT VALUES ('FDC01','金融服務處');
 
 
 
 CREATE TABLE EMPLOYEE (
  EMP_ID             VARCHAR2(5) NOT NULL,
  UNIT_ID            VARCHAR2(5) NOT NULL,
  EMP_NAME           VARCHAR2(9) NOT NULL,
  CONSTRAINT EMPLOYEE_FK_UNIT_ID FOREIGN KEY (UNIT_ID) REFERENCES UNIT (UNIT_ID),
  CONSTRAINT EMPLOYEE_PK PRIMARY KEY (EMP_ID)
 );
 
 INSERT INTO EMPLOYEE VALUES ('E0001','GOV01','陳小明');
 INSERT INTO EMPLOYEE VALUES ('E0002','ENG01','蔡阿花');
 INSERT INTO EMPLOYEE VALUES ('E0003','FDC01','王大明');
 INSERT INTO EMPLOYEE VALUES ('E0004','GOV01','陳小春');
 INSERT INTO EMPLOYEE VALUES ('E0005','ENG01','蔡仲和');
 INSERT INTO EMPLOYEE VALUES ('E0006','FDC01','王志銘');
 
 
 
 CREATE TABLE CASE_REQ (
--CASE_NO         	 VARCHAR2(5)  NOT NULL,
  CASE_NO      	 VARCHAR2(9)  NOT NULL,
  CASE_TYPE           NUMBER(1)    NOT NULL,
  CASE_LEVEL          VARCHAR2(1)    NOT NULL,
  HOST_EMP_ID    	 VARCHAR2(5)  NOT NULL,
  COHOST_EMP_ID  	 VARCHAR2(5)  NOT NULL,
  STARTDATE           TIMESTAMP	  NOT NULL,
  ENDDATE             TIMESTAMP    NOT NULL,
  WORKITEM            VARCHAR2(50),
  CREATTIME             TIMESTAMP    NOT NULL,
  CONSTRAINT CASE_REQ_FK_HOST_EMP_ID FOREIGN KEY (HOST_EMP_ID) REFERENCES EMPLOYEE (EMP_ID),
  CONSTRAINT CASE_REQ_FK_COHOST_EMP_ID FOREIGN KEY (COHOST_EMP_ID) REFERENCES EMPLOYEE (EMP_ID),
  CONSTRAINT CASE_REQ_PK PRIMARY KEY (CASE_NO)
 );
  
 CREATE SEQUENCE case_seq
 INCREMENT BY 1
 START WITH 1
 NOMAXVALUE
 NOCYCLE
 NOCACHE; 
 
 INSERT INTO CASE_REQ VALUES ( to_char(sysdate, 'YYYY') || lpad(to_char(case_seq.nextval), 5, '0'),
 1,3,'E0001','E0006',TO_TIMESTAMP('2004-01-04','YYYY-MM-DD'),TO_TIMESTAMP('2004-05-03','YYYY-MM-DD'),'工作項目1',TO_TIMESTAMP('2019-03-28 12:22:37','yyyy-mm-dd hh24:mi:ss'));
 
 INSERT INTO CASE_REQ VALUES (  to_char(sysdate, 'YYYY') || lpad(to_char(case_seq.nextval), 5, '0'),
 2,2,'E0002','E0001',TO_TIMESTAMP('2004-04-02','YYYY-MM-DD'),TO_TIMESTAMP('2004-07-08','YYYY-MM-DD'),'工作項目2',TO_TIMESTAMP('2019-04-28 13:24:33','yyyy-mm-dd hh24:mi:ss'));
 
 INSERT INTO CASE_REQ VALUES (  to_char(sysdate, 'YYYY') || lpad(to_char(case_seq.nextval), 5, '0'),
 3,1,'E0003','E0002',TO_TIMESTAMP('2004-05-12','YYYY-MM-DD'),TO_TIMESTAMP('2004-07-19','YYYY-MM-DD'),'工作項目3',TO_TIMESTAMP('2019-05-28 14:27:43','yyyy-mm-dd hh24:mi:ss'));
 
 INSERT INTO CASE_REQ VALUES (  to_char(sysdate, 'YYYY') || lpad(to_char(case_seq.nextval), 5, '0'),
 1,1,'E0004','E0003',TO_TIMESTAMP('2004-06-20','YYYY-MM-DD'),TO_TIMESTAMP('2004-08-23','YYYY-MM-DD'),'工作項目4',TO_TIMESTAMP('2019-06-28 15:29:55','yyyy-mm-dd hh24:mi:ss'));
 
 INSERT INTO CASE_REQ VALUES (  to_char(sysdate, 'YYYY') || lpad(to_char(case_seq.nextval), 5, '0'),
 2,2,'E0005','E0004',TO_TIMESTAMP('2004-08-17','YYYY-MM-DD'),TO_TIMESTAMP('2004-11-03','YYYY-MM-DD'),'工作項目5',TO_TIMESTAMP('2012-07-28 16:22:43','yyyy-mm-dd hh24:mi:ss'));
 
 INSERT INTO CASE_REQ VALUES ( to_char(sysdate, 'YYYY') || lpad(to_char(case_seq.nextval), 5, '0'),
 1,3,'E0001','E0006',TO_TIMESTAMP('2004-01-04','YYYY-MM-DD'),TO_TIMESTAMP('2004-05-03','YYYY-MM-DD'),'工作項目1',TO_TIMESTAMP('2019-03-28 12:22:37','yyyy-mm-dd hh24:mi:ss'));
 
 INSERT INTO CASE_REQ VALUES (  to_char(sysdate, 'YYYY') || lpad(to_char(case_seq.nextval), 5, '0'),
 2,2,'E0002','E0001',TO_TIMESTAMP('2004-04-02','YYYY-MM-DD'),TO_TIMESTAMP('2004-07-08','YYYY-MM-DD'),'工作項目2',TO_TIMESTAMP('2019-04-28 13:24:33','yyyy-mm-dd hh24:mi:ss'));
 
 INSERT INTO CASE_REQ VALUES (  to_char(sysdate, 'YYYY') || lpad(to_char(case_seq.nextval), 5, '0'),
 3,1,'E0003','E0002',TO_TIMESTAMP('2004-05-12','YYYY-MM-DD'),TO_TIMESTAMP('2004-07-19','YYYY-MM-DD'),'工作項目3',TO_TIMESTAMP('2019-05-28 14:27:43','yyyy-mm-dd hh24:mi:ss'));
 
 INSERT INTO CASE_REQ VALUES (  to_char(sysdate, 'YYYY') || lpad(to_char(case_seq.nextval), 5, '0'),
 1,1,'E0004','E0003',TO_TIMESTAMP('2004-06-20','YYYY-MM-DD'),TO_TIMESTAMP('2004-08-23','YYYY-MM-DD'),'工作項目4',TO_TIMESTAMP('2019-06-28 15:29:55','yyyy-mm-dd hh24:mi:ss'));
 
 INSERT INTO CASE_REQ VALUES (  to_char(sysdate, 'YYYY') || lpad(to_char(case_seq.nextval), 5, '0'),
 2,2,'E0005','E0004',TO_TIMESTAMP('2004-08-17','YYYY-MM-DD'),TO_TIMESTAMP('2004-11-03','YYYY-MM-DD'),'工作項目5',TO_TIMESTAMP('2012-07-28 16:22:43','yyyy-mm-dd hh24:mi:ss'));
 
 INSERT INTO CASE_REQ VALUES ( to_char(sysdate, 'YYYY') || lpad(to_char(case_seq.nextval), 5, '0'),
 1,3,'E0001','E0006',TO_TIMESTAMP('2004-01-04','YYYY-MM-DD'),TO_TIMESTAMP('2004-05-03','YYYY-MM-DD'),'工作項目1',TO_TIMESTAMP('2019-03-28 12:22:37','yyyy-mm-dd hh24:mi:ss'));
 
 INSERT INTO CASE_REQ VALUES (  to_char(sysdate, 'YYYY') || lpad(to_char(case_seq.nextval), 5, '0'),
 2,2,'E0002','E0001',TO_TIMESTAMP('2004-04-02','YYYY-MM-DD'),TO_TIMESTAMP('2004-07-08','YYYY-MM-DD'),'工作項目2',TO_TIMESTAMP('2019-04-28 13:24:33','yyyy-mm-dd hh24:mi:ss'));
 
 INSERT INTO CASE_REQ VALUES (  to_char(sysdate, 'YYYY') || lpad(to_char(case_seq.nextval), 5, '0'),
 3,1,'E0003','E0002',TO_TIMESTAMP('2004-05-12','YYYY-MM-DD'),TO_TIMESTAMP('2004-07-19','YYYY-MM-DD'),'工作項目3',TO_TIMESTAMP('2019-05-28 14:27:43','yyyy-mm-dd hh24:mi:ss'));
 
 INSERT INTO CASE_REQ VALUES (  to_char(sysdate, 'YYYY') || lpad(to_char(case_seq.nextval), 5, '0'),
 1,1,'E0004','E0003',TO_TIMESTAMP('2004-06-20','YYYY-MM-DD'),TO_TIMESTAMP('2004-08-23','YYYY-MM-DD'),'工作項目4',TO_TIMESTAMP('2019-06-28 15:29:55','yyyy-mm-dd hh24:mi:ss'));
 
 INSERT INTO CASE_REQ VALUES (  to_char(sysdate, 'YYYY') || lpad(to_char(case_seq.nextval), 5, '0'),
 2,2,'E0005','E0004',TO_TIMESTAMP('2004-08-17','YYYY-MM-DD'),TO_TIMESTAMP('2004-11-03','YYYY-MM-DD'),'工作項目5',TO_TIMESTAMP('2012-07-28 16:22:43','yyyy-mm-dd hh24:mi:ss'));
 commit;