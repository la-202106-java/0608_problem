--���id�Œ��ׂ�sql

SELECT
	i.name,		--�h��(inns)
	i.address,	--�h�̏Z��(inns)
	s.contents,	--�v�������e(stay_plans)
	r.in_date,	--�`�F�b�N�C����(reservations)
	r.out_date,	--�`�F�b�N�A�E�g��(reservations)
	i.in_time,	--�`�F�b�N�C������(inns)
	i.out_time,	--�`�F�b�N�A�E�g����(inns)
	r.room,		--������(reservation)
	s.fee		--����(stay_plans)
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
--WHERE���}���ӏ�--

-------------------
;
