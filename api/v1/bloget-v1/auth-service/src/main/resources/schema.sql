
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