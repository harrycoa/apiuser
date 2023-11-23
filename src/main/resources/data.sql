
INSERT INTO usuario (id, name, email, password, created, modified, last_login, token, is_active) VALUES
  ('4e23ae29-4afb-4f1c-b8c0-90d2a10b3d7f', 'User1', 'user1@example.com', 'password1@N', '2021-01-24', '2021-01-14', '2021-01-02', 'token-1', false),
  ('5e23ae29-4afb-4f1c-b8c0-90d2a10b3d7g', 'User2', 'user2@example.com', 'password2@I', '2022-08-02', '2022-07-08', '2022-08-02', 'token-2', false),
  ('6e23ae29-4afb-4f1c-b8c0-90d2a10b3d7h', 'User3', 'user3@example.com', 'password3@S', '2022-12-16', '2021-05-16', '2022-10-08', 'token-3', true),
  ('7e23ae29-4afb-4f1c-b8c0-90d2a10b3d7i', 'User4', 'user4@example.com', 'password4@U', '2021-07-22', '2021-12-25', '2022-03-08', 'token-4', true),
  ('8e23ae29-4afb-4f1c-b8c0-90d2a10b3d7j', 'User5', 'user5@example.com', 'password5@M', '2021-03-05', '2022-05-08', '2022-12-06', 'token-5', false);

INSERT INTO telefono (id, number, citycode, countrycode, user_id) VALUES
                                                                      ('1e23ae29-4afb-4f1c-b8c0-90d2a10b3d7k', '1234561', 'city1', 'country1', '4e23ae29-4afb-4f1c-b8c0-90d2a10b3d7f'),
                                                                      ('2e23ae29-4afb-4f1c-b8c0-90d2a10b3d7l', '1234562', 'city2', 'country2', '5e23ae29-4afb-4f1c-b8c0-90d2a10b3d7g'),
                                                                      ('3e23ae29-4afb-4f1c-b8c0-90d2a10b3d7m', '1234563', 'city3', 'country3', '6e23ae29-4afb-4f1c-b8c0-90d2a10b3d7h'),
                                                                      ('4e23ae29-4afb-4f1c-b8c0-90d2a10b3d7n', '1234564', 'city4', 'country4', '7e23ae29-4afb-4f1c-b8c0-90d2a10b3d7i'),
                                                                      ('5e23ae29-4afb-4f1c-b8c0-90d2a10b3d7o', '1234565', 'city5', 'country5', '8e23ae29-4afb-4f1c-b8c0-90d2a10b3d7j');
