Insert into ADDRESS (ADDRESS_ID,STREET,CITY,STATE,ZIPCODE,COMPANY,ACTIVE) values (1,'65-30 Kissena Blvd, CEP Hall 2','Queens','NY','11367','Tech Incubator at Queens College',true);
Insert into ADDRESS (ADDRESS_ID,STREET,CITY,STATE,ZIPCODE,COMPANY,ACTIVE) values (2,'11730 Plaza America Drive, 2nd Floor','Reston','VA','20190','Revature LLC',true);

insert into location (id, active, city, name, state) values (1, true, 'Reston', 'Reston HQ', 'Virginia');
insert into location (id, active, city, name, state) values (2, true, 'Tampa', 'Tampa training location', 'Florida');

insert into building (id, active, location, name) values (1, true, 1, 'Reston main');
insert into building (id, active, location, name) values (2, true, 1, 'Reston other builing');
insert into building (id, active, location, name) values (3, true, 2, 'Tampa main');
insert into building (id, active, location, name) values (4, true, 2, 'Tampa other building');

insert into room (id, active, building_id, name) values (1, true, 1, 'Room 304');
insert into room (id, active, building_id, name) values (2, true, 1, 'Room 305');
insert into room (id, active, building_id, name) values (3, true, 3, 'Room 101');

COMMIT;