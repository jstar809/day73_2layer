CREATE TABLE MEMBER(
	MID VARCHAR(20) PRIMARY KEY,
	MPW VARCHAR(20) NOT NULL,
	NAME VARCHAR(20) NOT NULL,
	ROLE VARCHAR(10) NOT NULL
);

INSERT INTO MEMBER VALUES('admin','1234','������','ADMIN');
select * from member;