-- oAuth2 client
INSERT INTO oauth_client_details (client_id, client_secret, web_server_redirect_uri, scope, access_token_validity, refresh_token_validity, resource_ids, authorized_grant_types, additional_information)
VALUES ('mobile', '{bcrypt}$2a$10$gPhlXZfms0EpNHX0.HHptOhoFD1AoxSr/yUIdTqA8vtjeP4zi0DDu', 'http://localhost:8080/code', 'READ,WRITE', '3600', '10000', 'inventory,payment', 'authorization_code,password,refresh_token,implicit', '{}');

-- oAuth2 roles
INSERT INTO role (id, name)
VALUES (1, 'ROLE_USER'),(2, 'ROLE_MOD'),(3, 'ROLE_ADMIN');

-- oAuth2 users
INSERT INTO users (id, username, password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked)
VALUES (1, 'test_user','{bcrypt}$2a$10$RqdCJE0jAc2J1dYdjMfr3eLIWc.OAXZZwpmxtIJqPSPPRc7J7NrmG', 'email@gmail.com', '1', '1', '1', '1');

INSERT INTO users (id, username, password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked)
VALUES (2, 'test_mod', '{bcrypt}$2a$10$RqdCJE0jAc2J1dYdjMfr3eLIWc.OAXZZwpmxtIJqPSPPRc7J7NrmG','email@gmail.com', '1', '1', '1', '1');

INSERT INTO users (id, username, password, email, enabled, account_non_expired, credentials_non_expired, account_non_locked)
VALUES (3, 'test_admin', '{bcrypt}$2a$10$RqdCJE0jAc2J1dYdjMfr3eLIWc.OAXZZwpmxtIJqPSPPRc7J7NrmG','email@gmail.com', '1', '1', '1', '1');


INSERT INTO role_user (role_id, user_id)
VALUES
(1, 1) /* test-user */,
(2, 2) /* test-mod */,
(3, 3) /* test-admin */;
