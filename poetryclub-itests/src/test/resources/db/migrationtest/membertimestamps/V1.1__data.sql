insert INTO ACCOUNT (ID, LOGIN) VALUES
  (1, 'owner'),
  (2, 'user1'),
  (3, 'user2');
insert INTO COMMUNITY (ID, NAME, UUID) VALUES
  (1, 'Test community', '00000000-0000-0000-0000-0000-00000000');

insert into MEMBER (ACCOUNTID, COMMUNITYID, ROLE) VALUES
  (1, 1, 'admin'),
  (2, 1, 'member'),
  (3, 1, 'member');

--
-- Persist current timestamp for further reasoning in V2.1 assertion
--
create table TIMESTAMPS (
  TIMESTAMP TIMESTAMP NOT NULL
);

INSERT INTO TIMESTAMPS (TIMESTAMP) VALUES ((select CURRENT_TIMESTAMP));

