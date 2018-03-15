DROP SEQUENCE IF EXISTS ADDRESS_ID_SEQUENCE;
CREATE SEQUENCE IF NOT EXISTS ADDRESS_ID_SEQUENCE  MINVALUE 1  INCREMENT BY 1 START WITH 1;
-------------ADDRESS DATA----------------
Insert into CALIBER_ADDRESS (ADDRESS_ID,ADDRESS_STREET,ADDRESS_CITY,ADDRESS_STATE,ADDRESS_ZIPCODE,ADDRESS_COMPANY,ACTIVE) values (ADDRESS_ID_SEQUENCE.NEXTVAL,'65-30 Kissena Blvd, CEP Hall 2','Queens','NY','11367','Tech Incubator at Queens College',1);
Insert into CALIBER_ADDRESS (ADDRESS_ID,ADDRESS_STREET,ADDRESS_CITY,ADDRESS_STATE,ADDRESS_ZIPCODE,ADDRESS_COMPANY,ACTIVE) values (ADDRESS_ID_SEQUENCE.NEXTVAL,'11730 Plaza America Drive, 2nd Floor','Reston','VA','20190','Revature LLC',1);

insert into location (id, active, city, name, state) values (1, 1, 'Reston', 'Reston HQ', 'Virginia');
insert into location (id, active, city, name, state) values (2, 1, 'Tampa', 'Tampa training location', 'Florida');

insert into building (id, active, location, name) values (1, 1, 1, 'Reston main');
insert into building (id, active, location, name) values (2, 1, 1, 'Reston other builing');
insert into building (id, active, location, name) values (3, 1, 2, 'Tampa main');
insert into building (id, active, location, name) values (4, 1, 2, 'Tampa other building');

insert into room (id, active, building, name) values (1, 1, 1, 'Room 304');
insert into room (id, active, building, name) values (2, 1, 1, 'Room 305');
insert into room (id, active, building, name) values (3, 1, 3, 'Room 101');

COMMIT;