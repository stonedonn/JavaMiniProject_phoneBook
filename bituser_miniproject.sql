CREATE TABLE PHONE_BOOK(
    id NUMBER(10) PRIMARY KEY,
    name VARCHAR2(20) NOT NULL,
    hp VARCHAR2(30) NOT NULL,
    tel VARCHAR2(30) NOT NULL
);

CREATE SEQUENCE SEQ_PHONEBOOK_PK
    START WITH 1
    INCREMENT BY 1;
    
alter user C##bituser default tablespace users quota unlimited on users;

select * from phone_book;

delete from phone_book
    where id = '21';
    
select * from phone_book;

select * from author;

delete from author
    where author_id = '2';

select CONSTRAINT_NAME, TABLE_NAME, R_CONSTRAINT_NAME
from user_constraints
where CONSTRAINT_NAME = 'FK_AUTHOR_ID';

select * from book;

delete from book
    where book_id = '2';

select * from author;

select * from phone_book;

select * from phone_book;

select * from phone_book;

drop sequence seq_phone_book_pk;

CREATE SEQUENCE SEQ_PHONE_BOOK_PK
    START WITH 1
    INCREMENT BY 1;
    
SELECT * FROM PHONE_BOOK ORDER BY id ASC;