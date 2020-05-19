
-- oAuth Client
CREATE TABLE IF NOT EXISTS oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

-- Users and their roles
CREATE TABLE IF NOT EXISTS users (
  id SERIAL NOT NULL PRIMARY KEY,
  username VARCHAR(100) NOT NULL,
  password VARCHAR(1024) NOT NULL,
  email VARCHAR(1024) NOT NULL,
  enabled BOOLEAN NOT NULL,
  account_non_expired BOOLEAN NOT NULL,
  credentials_non_expired BOOLEAN NOT NULL,
  account_non_locked BOOLEAN NOT NULL,
  UNIQUE(username)
);

CREATE TABLE IF NOT EXISTS role (
  id SERIAL NOT NULL PRIMARY KEY,
  name VARCHAR(512),
  UNIQUE(name)
);

CREATE TABLE IF NOT EXISTS role_user (
  role_id INT,
  user_id INT,
  FOREIGN KEY (role_id) REFERENCES role (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE INDEX IF NOT EXISTS role_id ON role_user (role_id);
CREATE INDEX IF NOT EXISTS user_id ON role_user (user_id);

-- Token Store
CREATE TABLE IF NOT EXISTS oauth_client_token (
  token_id VARCHAR(256),
  token BYTEA,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_access_token (
  token_id VARCHAR(256),
  token BYTEA,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication BYTEA,
  refresh_token VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_refresh_token (
  token_id VARCHAR(256),
  token BYTEA,
  authentication BYTEA
);

CREATE TABLE IF NOT EXISTS oauth_code (
  code VARCHAR(256),
  authentication BYTEA
);

CREATE TABLE IF NOT EXISTS oauth_approvals (
  userId VARCHAR(256),
  clientId VARCHAR(256),
  scope VARCHAR(256),
  status VARCHAR(10),
  expiresAt TIMESTAMP,
  lastModifiedAt TIMESTAMP
);

-- oAuth2 client
INSERT INTO oauth_client_details (client_id, client_secret, web_server_redirect_uri, scope, access_token_validity, refresh_token_validity, resource_ids, authorized_grant_types, additional_information)
VALUES ('mobile', '{bcrypt}$2a$10$gPhlXZfms0EpNHX0.HHptOhoFD1AoxSr/yUIdTqA8vtjeP4zi0DDu', 'http://localhost:8080/code', 'READ,WRITE', '3600', '10000', 'inventory,payment', 'authorization_code,password,refresh_token,implicit', '{}');

-- oAuth2 roles
INSERT INTO role (id, name)
VALUES (1, 'ROLE_USER'),(2, 'ROLE_MOD'),(3, 'ROLE_ADMIN');

-- oAuth2 users
INSERT INTO users (id, username, password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked)
VALUES (11, 'test_user','{bcrypt}$2a$10$RqdCJE0jAc2J1dYdjMfr3eLIWc.OAXZZwpmxtIJqPSPPRc7J7NrmG', 'email@gmail.com', '1', '1', '1', '1');

INSERT INTO users (id, username, password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked)
VALUES (12, 'test_mod', '{bcrypt}$2a$10$RqdCJE0jAc2J1dYdjMfr3eLIWc.OAXZZwpmxtIJqPSPPRc7J7NrmG','email@gmail.com', '1', '1', '1', '1');

INSERT INTO users (id, username, password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked)
VALUES (13, 'test_admin', '{bcrypt}$2a$10$RqdCJE0jAc2J1dYdjMfr3eLIWc.OAXZZwpmxtIJqPSPPRc7J7NrmG','email@gmail.com', '1', '1', '1', '1');


INSERT INTO role_user (role_id, user_id)
VALUES
(1, 11) /* test-user */,
(2, 12) /* test-mod */,
(1, 12),
(3, 13) /* test-admin */,
(2, 13),
(1, 13);