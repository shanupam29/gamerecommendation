create database games;

create user 'game'@'localhost' identified by 'game';

grant all on games.* to 'game'@'localhost';

commit;