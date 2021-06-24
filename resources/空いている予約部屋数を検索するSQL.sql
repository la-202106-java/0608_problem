SELECT
  r.reserve_date, -- 予約されて抑えられている日
  p.inn_id, -- 宿ID
  i.name, -- 宿名
  p.plan_id, -- 宿ごとのプランID
  p.room_max, -- プランごとの最大部屋数
  COALESCE(r.reserved_room_count,0 ) as reserved_room_count, -- 予約されて抑えられている日数
  p.room_max - COALESCE(reserved_room_count, 0) as can_reserve_room_count -- 予約可能部屋数
FROM
(SELECT
  r.plan_id,
  d.reserve_date,
  SUM(d.room) AS reserved_room_count
FROM reservation_details d
INNER JOIN reservations r on r.id = d.reservations_id
GROUP BY r.plan_id, reserve_date
) AS r
RIGHT OUTER JOIN stay_plans p
on p.plan_id = r.plan_id
and r.reserve_date BETWEEN '2020-09-01' and  '2020-09-02'  -- ここにチェックしたい予約日を設定する
--and room_max > r.reserved_room_count -- 部屋が埋まっているのを出したくないならコメントアウト
INNER JOIN inns i on p.inn_id = i.id
ORDER BY reserve_date, inn_id, plan_id