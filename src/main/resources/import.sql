INSERT INTO CATEGORY (CATEGORY_NAME, DESCRIPTION)
VALUES ('Games', 'Games description'),
  ('Multimedia', 'Multimedia  description'),
  ('Productivity', 'Productivity  description'),
  ('Tools', NULL),
  ('Health', 'Health  description'),
  ('Lifestyle', 'Lifestyle  description');
SET @SoftwareDesc = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.';
INSERT INTO SOFTWARE (PACKAGE, CATEGORY_NAME, DESCRIPTION, APP_NAME, PICTURE_128, PICTURE_512, DOWNLOADS_NUM, TIME_UPLOADED)
VALUES ('com', 'Games', @SoftwareDesc, 'Alladin', NULL, NULL, 1, 130),
  ('com', 'Games', @SoftwareDesc, 'Zeus', NULL, NULL, 2, 200),
  ('net', 'Multimedia', @SoftwareDesc, 'Amazon1', NULL, NULL, 3, 400),
  ('net', 'Productivity', @SoftwareDesc, 'JDebugger', NULL, NULL, 4, 1000),
  ('io', 'Tools', @SoftwareDesc, 'Ebay', NULL, NULL, 5, 320),
  ('io', 'Tools', @SoftwareDesc, 'Intellij IDEA', NULL, NULL, 15, 500),
  ('io', 'Health', @SoftwareDesc, 'Facebook', NULL, NULL, 5, 220),
  ('com', 'Lifestyle', @SoftwareDesc, 'Casual', NULL, NULL, 25, 550),
  ('net', 'Multimedia', @SoftwareDesc, 'Google', NULL, NULL, 25, 990),
  ('io', 'Health', @SoftwareDesc, 'LinkedIn', NULL, NULL, 2, 330),
  ('com', 'Tools', @SoftwareDesc, 'Odnoklassniki', NULL, NULL, 20, 100),
  ('io', 'Health', @SoftwareDesc, 'RSS', NULL, NULL, 15, 380),
  ('net', 'Lifestyle', @SoftwareDesc, 'Skype', NULL, NULL, 8, 1500),
  ('io', 'Lifestyle', @SoftwareDesc, '3D glasses', NULL, NULL, 10, 700),
  ('com', 'Tools', @SoftwareDesc, 'Twitter', NULL, NULL, 15, 400),
  ('net', 'Tools', @SoftwareDesc, 'WithoutImage', NULL, NULL, 1, 300);
INSERT INTO USERS (USERNAME, ENABLED, PASSWORD)
VALUES ('admin', 'true', '$2a$04$cXktm6Zp4TARn4W3RKOe4OxqK.JgL.XA0pMYhaOH95Kr3yq066xZi'),
  ('user', 'true', '$2a$04$uUMAaPmzaSRhu2.QfXrFceEWWpjD06vyTVVZJv0FOJp0a4Ctjl1Eq'),
  ('1', 'true', '$2a$04$/Z67djSA5Me14HJPyNosAOMRtEAChckSvj1RN5hI2iNuSwh7bC.fi'),
  ('anonymous', 'true', '$2a$04$J0eVo4zAX1YvN0ab3FHPzerY0WmNyiq7HL8daT5m4/6EfpChfkUUC');
INSERT INTO USER_ROLES (ROLE, USERNAME)
VALUES ('ROLE_DEVELOPER', 'admin'),
  ('ROLE_USER', 'user'),
  ('ROLE_DEVELOPER', '1'),
  ('ROLE_USER', '1'),
  ('ROLE_USER', 'anonymous');
COMMIT;
