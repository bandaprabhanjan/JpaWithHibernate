insert into course(id,name,created_Date,updated_Last_Time) values(10001,'JPA with Hibernate',sysdate(),sysdate());
insert into course(id,name,created_Date,updated_Last_Time) values(10002,'Microservices',sysdate(),sysdate());
insert into course(id,name,created_Date,updated_Last_Time) values(10003,'JavaScript',sysdate(),sysdate());
insert into course(id,name,created_Date,updated_Last_Time) values(10004,'Angular',sysdate(),sysdate());

insert into passport(id,pnumber) values(20001,'A12345');
insert into passport(id,pnumber) values(20002,'B12345');
insert into passport(id,pnumber) values(20003,'C00001');
insert into passport(id,pnumber) values(20004,'D00002');

insert into student(id,name,passport_id) values(30001,'Ankit',20001);
insert into student(id,name,passport_id) values(30002,'Banda',20002);
insert into student(id,name,passport_id) values(30003,'Charan',20003);
insert into student(id,name,passport_id) values(30004,'Dev',20004);


insert into review(id,description,rating,course_id) values(40001,'Awesome Course','5',10002);
insert into review(id,description,rating,course_id) values(40002,'Nice Course','4',10002);
insert into review(id,description,rating,course_id) values(40003,'Good Course','3',10003);
insert into review(id,description,rating,course_id) values(40004,'Average Course','2',10004);

insert into student_course(student_id,course_id) values(30001,10002);
insert into student_course(student_id,course_id) values(30001,10002);
insert into student_course(student_id,course_id) values(30002,10003);
insert into student_course(student_id,course_id) values(30002,10004);







