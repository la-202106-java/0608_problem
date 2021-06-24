DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS admin;
DROP TABLE IF EXISTS inns;
DROP TABLE IF EXISTS stay_plans;
DROP TABLE IF EXISTS reservations;

CREATE TABLE members (
	id SERIAL PRIMARY KEY,
	pass VARCHAR(255) NOT NULL,
	name VARCHAR(255) NOT NULL,
	postal_code VARCHAR(255) NOT NULL,
	address VARCHAR(255) NOT NULL,
	tel VARCHAR(255) NOT NULL,
	email_address VARCHAR(255) NOT NULL,
	birth_date DATE NOT NULL,
	join_date DATE NOT NULL,
	quit_date DATE
);

CREATE TABLE admin (
	id SERIAL PRIMARY KEY,
	email_address VARCHAR(255) NOT NULL,
	pass VARCHAR(255) NOT NULL
);

CREATE TABLE inns (
	id BIGSERIAL PRIMARY KEY,
	name VARCHAR(255) NOT NULL,
	class_code INTEGER NOT NULL,
	postal_code VARCHAR(255) NOT NULL,
	address VARCHAR(255) NOT NULL,
	in_time TIME NOT NULL,
	out_time TIME NOT NULL,
	delete_date DATE,
	note TEXT
);

CREATE TABLE stay_plans (
	plan_id SERIAL PRIMARY KEY,
	inn_id INTEGER REFERENCES inns(id),
	contents TEXT NOT NULL,
	fee INTEGER NOT NULL,
	room_max INTEGER NOT NULL,
	img_url TEXT NOT NULL,
	delete_date DATE,
	note TEXT
);

CREATE TABLE reservations (
	id SERIAL PRIMARY KEY,
	member_id INTEGER REFERENCES members(id),
	plan_id INTEGER REFERENCES stay_plans(plan_id),
	date DATE NOT NULL,
	in_date DATE NOT NULL,
	out_date DATE NOT NULL,
	room INTEGER NOT NULL,
	cancel_check BOOLEAN DEFAULT false,
	note TEXT
);


ALTER TABLE members OWNER TO adminuser;
ALTER TABLE admin OWNER TO adminuser;
ALTER TABLE inns OWNER TO adminuser;
ALTER TABLE stay_plans OWNER TO adminuser;
ALTER TABLE reservations OWNER TO adminuser;
