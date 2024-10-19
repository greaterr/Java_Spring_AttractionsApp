-- CITIES INSERT
INSERT INTO cities (name, population, has_metro)
VALUES ('Ufa', 1000000, FALSE);

INSERT INTO cities (name, population, has_metro)
VALUES ('Beloreck', 15000, FALSE);

INSERT INTO cities (name, population, has_metro)
VALUES ('Starosubkhangulovo', 5000, FALSE);


-- ATTRACTIONS INSERT
INSERT INTO attractions (name, creation_date, description, type, city_id)
VALUES ('Salavat Yulaev', '2024-10-04', 'Bashkirian Hero Salavat Yulayev memorial', 'MEMORIAL', 1);

INSERT INTO attractions (name, creation_date, description, type, city_id)
VALUES ('Agidel', '2024-10-04', 'River in Bashkiria', 'NATURE_PARK', 2);

INSERT INTO attractions (name, creation_date, description, type, city_id)
VALUES ('Shulgan-Tash', '2024-10-04', 'Memorial park', 'ARCHAEOLOGICAL', 3);


-- ACTIVITIES INSERT
INSERT INTO activities (name, description)
VALUES ('Bus', 'Bus excursions');

INSERT INTO activities (name, description)
VALUES ('Horsing', 'Horse riding');

INSERT INTO activities (name, description)
VALUES ('Hotel', 'Stay in hotel');

INSERT INTO activities (name, description)
VALUES ('Photography', 'Make a professional photo');

-- ATTRACTIONS-ACTIVITIES INSERT
INSERT INTO attractions_activities (attraction_id, activity_id)
VALUES (1, 1);

INSERT INTO attractions_activities (attraction_id, activity_id)
VALUES (1, 2);

INSERT INTO attractions_activities (attraction_id, activity_id)
VALUES (1, 3);

INSERT INTO attractions_activities (attraction_id, activity_id)
VALUES (1, 4);

INSERT INTO attractions_activities (attraction_id, activity_id)
VALUES (2, 2);

INSERT INTO attractions_activities (attraction_id, activity_id)
VALUES (2, 3);

INSERT INTO attractions_activities (attraction_id, activity_id)
VALUES (3, 3);

INSERT INTO attractions_activities (attraction_id, activity_id)
VALUES (3, 4);
