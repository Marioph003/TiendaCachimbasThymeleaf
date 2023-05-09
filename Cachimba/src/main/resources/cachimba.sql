drop database if exists tienda_cachimbas;
create database tienda_cachimbas;
use tienda_cachimbas;

create table cachimbas (
id_cachimba int primary key not null,
nombre varchar(100),
descripcion varchar(500),
precio float,
marca varchar(50),
stock int
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

INSERT INTO cachimbas VALUES 
(1, 'MIG Razor', 'Cachimba de pie fabricada en acero inoxidable con una altura de 56 cm', 259.99, 'MIG', 10),
(2, 'Starbuzz USA', 'Cachimba de pie fabricada en aluminio anodizado con una altura de 66 cm', 189.99, 'Starbuzz', 15),
(3, 'Khalil Mamoon Shabah', 'Cachimba de pie fabricada en latón y acero inoxidable con una altura de 80 cm', 129.99, 'Khalil Mamoon', 5),
(4, 'Oduman N5', 'Cachimba de pie fabricada en vidrio borosilicato y acero inoxidable con una altura de 50 cm', 179.99, 'Oduman', 8),
(5, 'Wookah Crystal', 'Cachimba de pie fabricada en madera de nogal y vidrio soplado con una altura de 62 cm', 349.99, 'Wookah', 3),
(6, 'Amy Deluxe SS16', 'Cachimba de pie fabricada en acero inoxidable con una altura de 56 cm', 99.99, 'Amy Deluxe', 12),
(7, 'El-Badia X1', 'Cachimba de pie fabricada en aluminio anodizado con una altura de 55 cm', 139.99, 'El-Badia', 6),
(8, 'MIG Nano', 'Cachimba de mesa fabricada en acero inoxidable con una altura de 31 cm', 149.99, 'MIG', 20),
(9, 'HookahJohn Ferris', 'Cachimba de pie fabricada en acero inoxidable con una altura de 65 cm', 249.99, 'HookahJohn', 2),
(10, 'Mya QT', 'Cachimba de mesa fabricada en acero inoxidable con una altura de 30 cm', 64.99, 'Mya Saray', 18);

INSERT INTO complementos VALUES 
(1, 'Kaloud Lotus', 'Sistema de control de calor para tabaco de cachimba', 49.99, 'Control de calor','MIG', 5),
(2, 'Cazoleta HC Black', 'Cazoleta para tabaco de cachimba fabricada en cerámica', 14.99, 'Cazoleta','Starbuzz', 20),
(3, 'Manguera silicona', 'Manguera de silicona para cachimba con boquilla de aluminio', 19.99, 'Manguera','Khalil Mamoon', 15),
(4, 'Cepillo de limpieza', 'Cepillo de limpieza para cachimba con mango de madera', 4.99, 'Limpieza','Oduman', 50),
(5, 'Pinzas para carbón', 'Pinzas para carbón de cachimba fabricadas en acero inoxidable', 9.99, 'Carbón','Wookah', 30),
(6, 'Base de cristal', 'Base de cristal para cachimba de diámetro 20 cm', 29.99, 'Recambio','Amy Deluxe', 8),
(7, 'Boquilla desechable', 'Boquilla desechable para cachimba en paquete de 100 unidades', 9.99,'El-Badia', 'Higiene', 25),
(8, 'Ceniza de coco', 'Ceniza de coco natural para cachimba en paquete de 1 kg', 4.99,'MIG', 'Combustible', 40),
(9, 'Tubo difusor', 'Tubo difusor para cachimba de longitud 12 cm', 7.99, 'Difusor','HookahJohn', 12),
(10, 'Papel de aluminio', 'Papel de aluminio para cachimba en rollo de 5 metros', 6.99, 'Recambio','Mya Saray', 20);

INSERT INTO opinion VALUES 
(1, 1, 'Muy buena cachimba, excelente calidad y diseño.', 4, '2022-04-25'),
(2, 3, 'La cachimba es muy bonita y de buena calidad, pero el precio es algo elevado.', 3, '2022-05-01'),
(3, 6, 'Muy contento con la compra, la cachimba funciona perfectamente y tiene un buen precio.', 5, '2022-04-30'),
(4, 8, 'La cachimba es muy bonita y de buena calidad, pero el precio es algo elevado.', 3, '2022-05-03'),
(5, 9, 'Cachimba muy bonita y bien diseñada, pero el envío tardó más de lo esperado.', 4, '2022-04-28'),
(6, 10, 'Buena calidad y diseño, pero la cachimba es un poco pequeña.', 4, '2022-04-27'),
(7, 2, 'Excelente cachimba, buena calidad y diseño, y el precio es razonable.', 5, '2022-05-02'),
(8, 4, 'La cachimba es de buena calidad, pero el diseño no es muy atractivo.', 3, '2022-05-04'),
(9, 5, 'Buen producto, la cachimba funciona bien y el precio es adecuado.', 4, '2022-04-29'),
(10, 7, 'Cachimba de muy buena calidad, aunque el precio es un poco elevado.', 4, '2022-04-26');

select * from cachimbas;
