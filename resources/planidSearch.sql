--�v����ID�Œ��ׂ�sql

SELECT
	i.name AS innsName,	--�h��(inns)
	r.id,			--�\��ID(reservation)
	s.contents,		--�v�������e(stay_plans)
	r.in_date,		--�`�F�b�N�C����(reservations)
	r.out_date,		--�`�F�b�N�A�E�g��(reservations)
	m.name AS memberName,	--�����(members)
	m.postal_code,		--����X�֔ԍ�(members)
	m.address,		--����Z��(members)
	m.tel,			--�d�b�ԍ�(members)
	m.email_address		--���[��(members)
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
--WHERE���ǉ��ӏ�--

-------------------
;