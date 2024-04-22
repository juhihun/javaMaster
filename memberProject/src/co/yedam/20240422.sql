--사원테이블(사원번호, 사원명, 연락처, 이메일, 입사일자, 급여)
create table emp (
emp_no number primary key --emp_seq.nextval
, emp_name varchar2(40) not null
, emp_phone varchar2(12) not null
, email varchar2(30) not null
, hire_date date default sysdate
,salary number
);
--(CreateReadUpdateDelete)
create sequence emp_seq;
insert into emp (emp_no, emp_name, emp_phone, email, salary)
values (emp_seq.nextval, 'kildongHong','01-1234-4567','kolgod@email',2000);
insert into emp (emp_no, emp_name, emp_phone, email, salary)
values (emp_seq.nextval, 'joohuiHyun','01-6335-6555','joohui@email',2200);
select*
from emp
order by emp_no;

update emp
set salary = salary + 500
 , emp_phone = '01-1111-1111'
where emp_name = 'kildongHong';

delete from emp
where emp_no = 2;


create table mem(
            mem_no number primary key,
            mem_name varchar2(40) not null,
            mem_phone varchar2(40) not null,
            birhday date default sysdate,
            gender varchar2(20) );
            
 alter table mem add email varchar2(10);
create sequence mem_seq;
insert into mem (mem_no, mem_name, mem_phone,birhday, gender)
values(mem_seq.nextval, '홍길동','010-1211-2222','98-04-02','남');
insert into mem (mem_no, mem_name, mem_phone,birhday, gender)
values(mem_seq.nextval, '박땡떙','010-2222-3333','99-07-18','여');
insert into mem (mem_no, mem_name, mem_phone,birhday, gender)
values(mem_seq.nextval, '현주희','010-4444-5555','95-09-10','여');

select *
from mem;

select * from mem order by mem_no