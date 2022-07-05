-- liquibase formatted sql
-- changeset TelegaBase:1

INSERT INTO nsi_animal_kind (id,name) VALUES ('402881248195e17e018195e2adba0000','Собаки');
INSERT INTO nsi_animal_kind (id,name) VALUES ('402881248195e17e018195e2d09e0001','Кошки');

INSERT INTO nsi_breed_animal (id,name) VALUES ('402881248195e17e018195e356ac0002','Дог');
INSERT INTO nsi_breed_animal (id,name) VALUES ('402881248195e17e018195e384fe0003','Бульдог');
INSERT INTO nsi_breed_animal (id,name) VALUES ('402881248195e17e018195e3ab5a0004','Дворняга');

INSERT INTO nsi_commands (id,command,level,description) VALUES ('402881248195e17e018195e462de0005','start',0,0);
INSERT INTO nsi_commands (id,command,level,description) VALUES ('402881248195e17e018195e5747f0006','Информацию о приюте',1,0);
INSERT INTO nsi_commands (id,command,level,description) VALUES ('402881248195e17e018195e67f620007','Расписание',1,0);
INSERT INTO nsi_commands (id,command,level,description) VALUES ('402881248195e17e018195e707c60008','Адрес',1,0);
INSERT INTO nsi_commands (id,command,level,description) VALUES ('402881248195e17e018195e746830009','Схема',1,0);
INSERT INTO nsi_commands (id,command,level,description) VALUES ('402881248195e17e018195e7e6d7000a','Техника безопасности',1,0);
INSERT INTO nsi_commands (id,command,level,description) VALUES ('402881248195e17e018195e89ba3000b','Записать контактные данные',1,0);
INSERT INTO nsi_commands (id,command,level,description) VALUES ('402881248195e17e018195f49cb70010','Выбрать приют',1,0);

INSERT INTO standart_response (id,relation_id,response_name,response_text)
VALUES ('402881248195e17e018195f3057d000d','402881248195e17e018195e5747f0006','Информацию о приюте','Городской муниципальный \n приют г.Невинномысска');
INSERT INTO standart_response (id,relation_id,response_name,response_text)
VALUES ('402881248195e17e018195f3057d00cf','402881248195e17e018195e67f620007','Расписание','Часы работы:\n ПН-ПТ 9:00 - 18:00\n СБ,ВС: выходной');
INSERT INTO standart_response (id,relation_id,response_name,response_text)
VALUES ('403881248195e17e01819f3057d00e1','402881248195e17e018195e707c60008','Адрес','Адрес:\n г. Невинномысск, Менделеева 32');
INSERT INTO standart_response (id,relation_id,response_name,response_text)
VALUES ('403879848195e17e01819f3087c00e1','402881248195e17e018195e7e6d7000a','Техника безопасности','На собак не мяукать, на кошек не гавкать');
INSERT INTO standart_response (id,relation_id,response_name,response_text)
VALUES ('403879848195e171ae761f3087c00e1','402881248195e17e018195e7e6d7000a','402881248195e17e018195e89ba3000b','Введите контактную информация');
INSERT INTO standart_response (id,relation_id,response_name,response_text)
VALUES ('402881248195e17e018195fc10160015','402881248195e17e018195f49cb70010','Собаки','id - 402881248195e17e018195e2adba0000');
INSERT INTO standart_response (id,relation_id,response_name,response_text)
VALUES ('402881248195e17e0181960338480021','402881248195e17e018195f49cb70010','Кошки','id - 402881248195e17e018195e2d09e0001');
INSERT INTO standart_response (id,relation_id,response_name,response_text)
VALUES ('402881248195e17e018195f5a5690011','','','id -         ');
INSERT INTO standart_response (id,relation_id,response_name,response_text)
VALUES ('402881248195e17e01819607305d0024','402881248195e17e018195e2d09e0001','Правила знакомства с кошкой','1. Подойти\n2. Погладить\n3. Позвать по клички\n4. Покормить');
INSERT INTO standart_response (id,relation_id,response_name,response_text)
VALUES ('402881248195e17e01819608cc4a0025','402881248195e17e018195e2d09e0001','Список документов','1. Паспорт\n2. СНИЛС\n3. Справка от психиатра');
INSERT INTO standart_response (id,relation_id,response_name,response_text)
VALUES ('402881248195e17e0181960b8d330026','402881248195e17e018195e2d09e0001','Рекомендации по транспортировке','1. Посадить в клетку\n2. Ехать медленно\n3. Выпустить на месте');
INSERT INTO standart_response (id,relation_id,response_name,response_text)
VALUES ('402881248195e17e0181960d68950027','402881248195e17e018195e2d09e0001','Рекомендаций по обустройству дома для котенка','1. Дать миску\n2. Установить туалет\n3. Уложить на коврик');
INSERT INTO standart_response (id,relation_id,response_name,response_text)
VALUES ('402881248195e17e0181960ea88c0028','402881248195e17e018195e2d09e0001','Рекомендаций по обустройству дома для взрослой кошки','1. Дать миску\n2. Установить туалет\n3. Тыкнуть носом в туалет\n4. Уложить на коврик');
INSERT INTO standart_response (id,relation_id,response_name,response_text)
VALUES ('42881248196111e0181961178910000','402881248195e17e018195e2d09e0001','Рекомендаций по обустройству дома для кошки с ограниченными возможностями (зрение, передвижения)','1. Дать миску\n2. Установить туалет\n3. Тыкнуть носом в туалет\n4. Уложить на коврик\n5.Дата очки и коляску');
INSERT INTO standart_response (id,relation_id,response_name,response_text)
VALUES ('402881248196111e018196128d2e0001','402881248195e17e018195e2d09e0001','Cоветы кинолога','На кошку не гавкать');
INSERT INTO standart_response (id,relation_id,response_name,response_text)
VALUES ('402881248196111e01819614026a0002','402881248195e17e018195e2d09e0001','Список причин отказа','1. Нет паспорта\n2.Нет СНИЛС\n3.Кошка сдохла');