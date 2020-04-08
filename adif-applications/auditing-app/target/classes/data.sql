INSERT INTO auditlogin (email, lastname, name, password, status, username,role_id)
VALUES ('azimlishakir@gmail.com','Azimli','Shakir','$2a$10$6nl2DHzVYNNuznRYU1wYeuEf5OZ/zDy1mTgroVfbmTyAh1UWQHAPS',1,'shakir.azimli');

select * from auditlogin;

insert into roles(id, name) VALUES (1,'ADMIN');

select * from roles;

INSERT INTO auditing.roles(name) VALUES('ADMIN');
INSERT INTO auditing.auditlogin(email, lastname, name, password, status, username, role_id)
VALUES ('test@gmail.com','Test-ov','Test','$2a$10$6nl2DHzVYNNuznRYU1wYeuEf5OZ/zDy1mTgroVfbmTyAh1UWQHAPS',1,'test',1);

delete from auditlogin where id=3;