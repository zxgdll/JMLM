SELECT
  u1.user_id,
  u1.user_idcard,
  u1.user_name,
  u1.user_create_time,
  u1.user_refer_id,
  u2.user_id,
  u2.user_name
FROM user u1
  INNER JOIN user u2 ON u1.user_refer_id = u2.user_id
WHERE u1.User_type = 1;