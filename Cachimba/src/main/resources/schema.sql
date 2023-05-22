drop table cachimba_complemento if exists;
drop table complementos if exists;
drop table opinion if exists;
drop table cachimbas if exists;

create table cachimbas (
id int primary key not null auto_increment,
nombre varchar(100),
descripcion varchar(500),
precio float,
marca varchar(50),
stock boolean, 
imagen varchar(500)
);

create table complementos (
id int primary key not null auto_increment,
nombre varchar(100),
descripcion varchar(500),
precio float,
tipo varchar(100),
marca varchar(50),
stock boolean,
imagen varchar(500)
);

create table opinion (
id int primary key auto_increment,
cachimba_id int,
comentario varchar(500),
puntuacion int,
fecha_opinion date,
constraint fk foreign key (cachimba_id) references cachimbas (id) on delete cascade on update cascade
);

create table cachimba_complemento ( 
cachimba_id int, 
complemento_id int, 
primary key (cachimba_id, complemento_id),
constraint fk_cachimba foreign key (cachimba_id) references cachimbas (id) on delete cascade on update cascade,
constraint fk_complemento foreign key (complemento_id) references complementos (id) on delete cascade on update cascade
);

ALTER TABLE opinion ADD CONSTRAINT puntuacion_valida CHECK (puntuacion >= 0 AND puntuacion <= 5);

