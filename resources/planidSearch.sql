--プランIDで調べるsql

SELECT
	i.name AS innsName,	--宿名(inns)
	r.id,			--予約ID(reservation)
	s.contents,		--プラン内容(stay_plans)
	r.in_date,		--チェックイン日(reservations)
	r.out_date,		--チェックアウト日(reservations)
	m.name AS memberName,	--会員名(members)
	m.postal_code,		--会員郵便番号(members)
	m.address,		--会員住所(members)
	m.tel,			--電話番号(members)
	m.email_address		--メール(members)
FROM
	inns i
INNER JOIN
	stay_plans s
ON
	i.id = s.inn_id
INNER JOIN
	reservations r
ON
	r.plan_id = s.plan_id
INNER JOIN
	members m
ON
	m.id = r.member_id
--WHERE文追加箇所--

-------------------
;