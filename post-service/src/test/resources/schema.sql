DROP TABLE IF EXISTS post;
CREATE TABLE IF NOT EXISTS post (
   id INTEGER AUTO_INCREMENT NOT NULL PRIMARY KEY,
   content VARCHAR(2048) NOT NULL,
   tags VARCHAR(2048) NOT NULL,
   owner VARCHAR(100) NOT NULL
);
