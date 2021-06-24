--テーブル内削除

TRUNCATE TABLE reservation_details CASCADE;
TRUNCATE TABLE reservations CASCADE;
TRUNCATE TABLE stay_plans CASCADE;
TRUNCATE TABLE members  CASCADE;
TRUNCATE TABLE inns CASCADE;
TRUNCATE TABLE admin CASCADE;

--シーケンスリセット

SELECT SETVAL ('admin_id_seq', 1, false);
SELECT SETVAL ('inns_id_seq', 1, false);
SELECT SETVAL ('members_id_seq', 1, false);
SELECT SETVAL ('reservations_id_seq', 1, false);
SELECT SETVAL ('stay_plans_plan_id_seq', 1, false);


--会員テーブルサンプル

INSERT INTO members (pass, name, postal_code, address, tel, email_address, birth_date, join_date, quit_date)
VALUES ('apass', '田中一郎', '123-4567', '東京都...', '111-2222-3333', 'aaa@bbb.com', '1982-05-11', '2010-11-01', '2010-12-11');
INSERT INTO members (pass, name, postal_code, address, tel, email_address, birth_date, join_date)
VALUES ('fjasofha', '鈴木次郎', '846-5158', '京都府...', '485-6284-9764', 'diw@dgs.com', '1991-07-11', '2011-02-01');
INSERT INTO members (pass, name, postal_code, address, tel, email_address, birth_date, join_date)
VALUES ('dudber', '佐藤八郎', '658-9584', '北海道...', '761-1739-4628', 'ken@esa.com', '1974-09-07', '2009-08-19');
INSERT INTO members (pass, name, postal_code, address, tel, email_address, birth_date, join_date)
VALUES ('id7dbds', '木村三郎', '168-1684', '鹿児島県...', '684-8536-0376', 'eos@csf.com', '1990-07-15', '2019-07-30');

--管理者テーブルサンプル

INSERT INTO admin (email_address, pass) VALUES ('dhe@sfc.com', 'dfs4f');
INSERT INTO admin (email_address, pass) VALUES ('eic@srb.com', 'so8cd');
INSERT INTO admin (email_address, pass) VALUES ('ocs@lco.com', 'doae5d');
INSERT INTO admin (email_address, pass) VALUES ('leb@sbt.com', 'eu7dne');

--宿テーブルサンプル

INSERT INTO inns (name, class_code, postal_code, address, in_time, out_time)
VALUES ('いこい亭', 3, '413-0000', '静岡県熱海市...', '15:00:00', '11:00:00');
INSERT INTO inns (name, class_code, postal_code, address, in_time, out_time)
VALUES ('東武イン', 2, '321-0000', '栃木県日光市...', '16:00:00', '10:00:00');
INSERT INTO inns (name, class_code, postal_code, address, in_time, out_time)
VALUES ('帝国ホテル', 1, '925-0000', '大阪府...', '15:00:00', '09:00:00');

--宿泊プランテーブルサンプル

INSERT INTO stay_plans (inn_id, contents, fee, room_max, img_url)
VALUES (1, '素泊まり', 77000, 1, 'http://sample1');
INSERT INTO stay_plans (inn_id, contents, fee, room_max, img_url)
VALUES (1, '夕食付き', 89000, 2, 'http://sample2');
INSERT INTO stay_plans (inn_id, contents, fee, room_max, img_url)
VALUES (2, 'シングル', 30000, 1, 'http://sample3');
INSERT INTO stay_plans (inn_id, contents, fee, room_max, img_url)
VALUES (2, 'ダブル', 35000, 2, 'http://sample4');
INSERT INTO stay_plans (inn_id, contents, fee, room_max, img_url)
VALUES (2, 'ツイン', 40000, 3, 'http://sample5');
INSERT INTO stay_plans (inn_id, contents, fee, room_max, img_url)
VALUES (3, 'レギュラールーム', 90000, 5, 'http://sample6');
INSERT INTO stay_plans (inn_id, contents, fee, room_max, img_url)
VALUES (3, 'スイートルーム', 100000, 2, 'http://sample7');
INSERT INTO stay_plans (inn_id, contents, fee, room_max, img_url, delete_date, note)
VALUES (3, 'ロイヤルスイートルーム', 250000, 1, 'http://sample8', '2020-04-01', 'バスルームの故障のため');

--予約テーブルサンプル

INSERT INTO reservations (member_id, plan_id, date, in_date, out_date, room)
VALUES (1, 2, '2019-05-11', '2019-08-01', '2019-08-03', 1);
INSERT INTO reservations (member_id, plan_id, date, in_date, out_date, room, cancel_check, note)
VALUES (1, 2, '2019-05-11', '2019-08-01', '2019-08-03', 1, true, '重複予約してしまったため');
INSERT INTO reservations (member_id, plan_id, date, in_date, out_date, room)
VALUES (3, 4, '2019-07-12', '2019-11-12', '2019-11-13', 1);
INSERT INTO reservations (member_id, plan_id, date, in_date, out_date, room)
VALUES (2, 6, '2020-06-07', '2020-09-01', '2019-09-05', 1);
INSERT INTO reservations (member_id, plan_id, date, in_date, out_date, room)
VALUES (3, 6, '2020-06-07', '2020-09-01', '2019-09-03', 2);
INSERT INTO reservations (member_id, plan_id, date, in_date, out_date, room)
VALUES (4, 6, '2020-06-07', '2020-09-01', '2019-09-03', 2);
INSERT INTO reservations (member_id, plan_id, date, in_date, out_date, room)
VALUES (1, 5, '2020-06-07', '2020-09-01', '2019-09-03', 2);

--予約詳細テーブルサンプル

INSERT INTO reservation_details (reservations_id, reserve_date, room)
VALUES (1, '2019-08-01', 1);
INSERT INTO reservation_details (reservations_id, reserve_date, room)
VALUES (1, '2019-08-02', 1);
INSERT INTO reservation_details (reservations_id, reserve_date, room)
VALUES (3, '2019-11-12', 1);
INSERT INTO reservation_details (reservations_id, reserve_date, room)
VALUES (4, '2020-09-01', 1);
INSERT INTO reservation_details (reservations_id, reserve_date, room)
VALUES (4, '2020-09-02', 1);
INSERT INTO reservation_details (reservations_id, reserve_date, room)
VALUES (4, '2020-09-03', 1);
INSERT INTO reservation_details (reservations_id, reserve_date, room)
VALUES (4, '2020-09-04', 1);
INSERT INTO reservation_details (reservations_id, reserve_date, room)
VALUES (5, '2020-09-01', 2);
INSERT INTO reservation_details (reservations_id, reserve_date, room)
VALUES (5, '2020-09-02', 2);
INSERT INTO reservation_details (reservations_id, reserve_date, room)
VALUES (6, '2020-09-01', 2);
INSERT INTO reservation_details (reservations_id, reserve_date, room)
VALUES (6, '2020-09-02', 2);
INSERT INTO reservation_details (reservations_id, reserve_date, room)
VALUES (7, '2020-09-01', 2);
INSERT INTO reservation_details (reservations_id, reserve_date, room)
VALUES (7, '2020-09-02', 2);
