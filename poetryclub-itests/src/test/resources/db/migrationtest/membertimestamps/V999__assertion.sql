DO $do$
--
-- All members after upgrade from V1 to V2 must have timestamps within the range
-- [timestamp of V2 upgrade start - 1 month, current_timestamp - 1 month]
--
BEGIN
  if (
    (
      select count(ACCOUNTID) FROM MEMBER
      WHERE TIMESTAMP between ((select * from TIMESTAMPS) - interval '1 month')
      and ((select current_timestamp) - interval '1 month')
    ) <> 3
  ) THEN
    raise 'assertion failed';
  END IF;
END
$do$ LANGUAGE plpgsql;