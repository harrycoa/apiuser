INSERT INTO usuario (id, name, email, password, created, modified, last_login, token, is_active) VALUES
                                                                                                     (100, 'User1', 'user1@example.com', 'password1@N', '2021-01-24', '2021-01-14', '2021-01-02', 'token-1', false),
                                                                                                     (101, 'User2', 'user2@example.com', 'password2@I', '2022-08-02', '2022-07-08', '2022-08-02', 'token-2', false),
                                                                                                     (102, 'User3', 'user3@example.com', 'password3@S', '2022-12-16', '2021-05-16', '2022-10-08', 'token-3', true),
                                                                                                     (103, 'User4', 'user4@example.com', 'password4@U', '2021-07-22', '2021-12-25', '2022-03-08', 'token-4', true),
                                                                                                     (104, 'User5', 'user5@example.com', 'password5@M', '2021-03-05', '2022-05-08', '2022-12-06', 'token-5', false);

INSERT INTO telefono (id, number, citycode, countrycode, user_id) VALUES
                                                                      (100, '1234561', 'city1', 'country1', 100),
                                                                      (101, '1234562', 'city2', 'country2', 101),
                                                                      (102, '1234563', 'city3', 'country3', 102),
                                                                      (103, '1234564', 'city4', 'country4', 103),
                                                                      (104, '1234565', 'city5', 'country5', 104);
