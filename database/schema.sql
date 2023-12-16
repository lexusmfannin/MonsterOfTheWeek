BEGIN TRANSACTION;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP TABLE IF EXISTS images;
DROP SEQUENCE IF EXISTS seq_images_id;
DROP TABLE IF EXISTS achievements;
DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS profession;
DROP TABLE IF EXISTS character;
DROP TABLE IF EXISTS monster;
DROP TABLE IF EXISTS graveyard;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS party;
DROP TABLE IF EXISTS partyGroup;
CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE TABLE users (
   user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
   username varchar(50) NOT NULL,
   password_hash varchar(200) NOT NULL,
   role varchar(50) NOT NULL,
   CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE SEQUENCE seq_images_id
   INCREMENT BY 1
   NO MINVALUE
   NO MAXVALUE
   CACHE 1;
CREATE TABLE images (
   image_id int DEFAULT nextval('seq_images_id'::regclass) NOT NULL,
   user_id int,
   mediatype varchar(100) NULL,
   data BYTEA NULL,
   CONSTRAINT PK_images PRIMARY KEY (image_id),
   CONSTRAINT FK_images_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE achievements (
   badge_id serial,
   badge_name varchar(20),
   badge_desc text,
   image_id int,
   CONSTRAINT PK_badge PRIMARY KEY (badge_id),
   CONSTRAINT FK_badge_images FOREIGN KEY (image_id) REFERENCES images (image_id)
);

CREATE TABLE character (
   character_id serial,
   name varchar(25),
   creature varchar(50),
   class_profession varchar(50),
   background text,
   abilities varchar(50),
   created_date date,
   character_strength int NOT NULL,
   character_dexterity int NOT NULL,
   character_constitution int NOT NULL,
   character_intelligence int NOT NULL,
   character_wisdom int NOT NULL,
   character_charisma int NOT NULL,
   user_id int,
   image_id int,
   CONSTRAINT PK_character PRIMARY KEY (character_id),
   CONSTRAINT FK_character_user FOREIGN KEY (user_id) REFERENCES users (user_id),
   CONSTRAINT FK_character_image FOREIGN KEY (image_id) REFERENCES images (image_id)
);

CREATE TABLE monster (
   monster_id serial,
   monster_name varchar(50),
   challenge_rating int,
   special_abilities varchar(50),
   monster_size varchar(20),
   monster_strength int NOT NULL,
   monster_dexterity int NOT NULL,
   monster_constitution int NOT NULL,
   monster_intelligence int NOT NULL,
   monster_wisdom int NOT NULL,
   monster_charisma int NOT NULL,
   image_id int,
   user_id int,
   CONSTRAINT PK_monster PRIMARY KEY (monster_id),
   CONSTRAINT FK_monster_image FOREIGN KEY (image_id) REFERENCES images (image_id),
   CONSTRAINT FK_monster_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE partyGroup (
    party_id serial,  
    party_name varchar(20), 
    CONSTRAINT PK_partyGroup PRIMARY KEY (party_id)
);

CREATE TABLE party (
    party_id int,
    character_id int, 
    CONSTRAINT FK_party_partyGroup FOREIGN KEY (party_id) REFERENCES partyGroup (party_id),
    CONSTRAINT FK_party_character FOREIGN KEY (character_id) REFERENCES character (character_id)
);

CREATE TABLE vote (
   vote_id serial,
   user_id int,
   party_id int,
   monster_id int, 
   vote_date date, 
   CONSTRAINT PK_vote PRIMARY KEY (vote_id),
   CONSTRAINT FK_vote_user FOREIGN KEY (user_id) REFERENCES users (user_id),
   CONSTRAINT FK_vote_partyGroup FOREIGN KEY (party_id) REFERENCES partyGroup (party_id)
--    CONSTRAINT FK_vote_monster FOREIGN KEY (monster_id) REFERENCES monster (monster_id)	
);

CREATE TABLE graveyard (
   graveyard_id serial,
   character_id int,
   character_name varchar(50),
   created_date date,
   user_id int,
   eternity_date date,
   CONSTRAINT PK_graveyard PRIMARY KEY (graveyard_id),
   CONSTRAINT FK_graveyard_character FOREIGN KEY (character_id) REFERENCES character (character_id),
   CONSTRAINT FK_graveyard_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE messages (
	comment_id serial,
	user_id int,
	comment_title varchar(50),
	comment_box text,
	comment_timestamp date,
	CONSTRAINT PK_comment PRIMARY KEY (comment_id),
	CONSTRAINT FK_comment_users FOREIGN KEY (user_id) REFERENCES users (user_id)
);

COMMIT TRANSACTION;