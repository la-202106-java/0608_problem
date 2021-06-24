SELECT
  p.plan_id,
  CASE WHEN r.plan_id IS NULL THEN False ELSE True END as is_full, -- このプランが指定された期間で一日でも満室であればtrue
  p.inn_id,
  p.contents,
  p.fee,
  p.room_max,
  p.img_url,
  p.delete_date as plans_delete_date, -- いらんかも
  p.note as plans_note,
  i.name,
  i.class_code,
  i.postal_code,
  i.address,
  i.in_time,
  i.out_time,
  i.delete_date as inns_delete_date, -- いらんかも
  i.note as inns_note
from stay_plans as p
inner join inns i on p.inn_id = i.id
left outer join
(SELECT
plan_id
FROM
(SELECT
  p.plan_id,
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
and r.reserve_date BETWEEN '2020-09-01' and  '2020-09-03'  -- ここにチェックインの日付とチェックアウト-1の日付を指定する
) as r
WHERE can_reserve_room_count = 0
GROUP BY plan_id
) as r on p.plan_id= r.plan_id