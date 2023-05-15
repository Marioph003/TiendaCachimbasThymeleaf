drop database if exists tienda_cachimbas;
create database tienda_cachimbas;
use tienda_cachimbas;

create table cachimbas (
id_cachimba int primary key not null,
nombre varchar(100),
descripcion varchar(500),
precio float,
marca varchar(50),
stock boolean
);

create table complementos (
id_complemento int primary key not null,
nombre varchar(100),
descripcion varchar(500),
precio float,
tipo varchar(100),
marca varchar(50),
stock int
);

create table opinion (
id_opinion int primary key,
id_cachimba int,
comentario varchar(500),
puntuacion int,
fecha_opinion date,
constraint fk foreign key (id_cachimba) references cachimbas (id_cachimba) on delete cascade on update cascade
);

create table cachimba_complemento ( 
id_cachimba int, 
id_complemento int, 
primary key (id_cachimba, id_complemento),
constraint fk_cachimba foreign key (id_cachimba) references cachimbas (id_cachimba) on delete cascade on update cascade,
constraint fk_complemento foreign key (id_complemento) references complementos (id_complemento) on delete cascade on update cascade
);

ALTER TABLE opinion ADD CONSTRAINT puntuacion_valida CHECK (puntuacion >= 0 AND puntuacion <= 5);
select * from cachimbas;
