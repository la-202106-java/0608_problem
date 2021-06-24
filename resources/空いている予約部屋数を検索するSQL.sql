SELECT
  p.inn_id, -- 宿ID
  p.plan_id, -- 宿ごとのプランID
  p.room_max, -- プランごとの最大部屋数
  r.reserve_date, -- 予約されて抑えられている日
  r.reserved_room_count -- 予約されて抑えられている日数
FROM
(SELECT
  r.plan_id,
  d.reserve_date,
  SUM(d.room) AS reserved_room_count
FROM reservation_details d
INNER JOIN reservations r on r.id = d.reservations_id
--WHERE reserve_date = '2020-09-01' -- ここにチェックしたい予約日を設定する
GROUP BY r.plan_id, reserve_date
) AS r
RIGHT OUTER JOIN stay_plans p on p.plan_id = r.plan_id
ORDER BY inn_id, plan_id