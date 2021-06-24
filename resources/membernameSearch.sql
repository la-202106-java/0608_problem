--会員idで調べるsql

SELECT
	i.name,		--宿名(inns)
	i.address,	--宿の住所(inns)
	s.contents,	--プラン内容(stay_plans)
	r.in_date,	--チェックイン日(reservations)
	r.out_date,	--チェックアウト日(reservations)
	i.in_time,	--チェックイン時間(inns)
	i.out_time,	--チェックアウト時間(inns)
	r.room,		--部屋数(reservation)
	s.fee		--料金(stay_plans)
FROM
	inns i
INNER JOIN
	stay_plans s
ON
	i.id = s.inn_id
INNER JOIN
	reservations r
ON
	s.plan_id = r.plan_id
--WHERE文挿入箇所--

-------------------
;
