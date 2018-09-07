create SEQUENCE COMMUNITY_ID;
create SEQUENCE ACCOUNT_ID;
create SEQUENCE CONTRIBUTION_ID;
create SEQUENCE FEEDBACK_ID;
create SEQUENCE REPORT_ID;

CREATE TABLE COMMUNITY (
  ID BIGINT not NULL DEFAULT NEXTVAL('COMMUNITY_ID'),
  UUID UUID not NULL,
  NAME VARCHAR(255),
  PRIMARY KEY ( ID )
);

CREATE TABLE ACCOUNT (
  ID BIGINT not NULL DEFAULT NEXTVAL('ACCOUNT_ID'),
  LOGIN VARCHAR(255),
  PRIMARY KEY ( ID )
);

CREATE TABLE MEMBER (
  ACCOUNTID BIGINT not NULL,
  COMMUNITYID BIGINT not NULL,
  ROLE VARCHAR(255),
  PRIMARY KEY ( ACCOUNTID, COMMUNITYID ),
  FOREIGN KEY ( COMMUNITYID ) REFERENCES COMMUNITY( ID ),
  FOREIGN KEY ( ACCOUNTID ) REFERENCES ACCOUNT( ID )
);

CREATE TABLE CONTRIBUTION (
  ID BIGINT not NULL DEFAULT NEXTVAL('CONTRIBUTION_ID'),
  TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  ACCOUNTID BIGINT not NULL,
  COMMUNITYID BIGINT not NULL,
  UUID UUID not NULL,
  URL VARCHAR(4096),
  STATUS VARCHAR(255) not NULL default 'unapproved',
  PRIMARY KEY ( ID ),
  FOREIGN KEY ( ACCOUNTID ) REFERENCES ACCOUNT( ID ),
  FOREIGN KEY ( COMMUNITYID ) REFERENCES COMMUNITY( ID )
);

CREATE TABLE FEEDBACK (
  ID BIGINT not NULL DEFAULT NEXTVAL('FEEDBACK_ID'),
  TIMESTAMP TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  ACCOUNTID BIGINT not NULL,
  CONTRIBUTIONID BIGINT not NULL,
  UUID UUID not NULL,
  URL VARCHAR(4096),
  STATUS VARCHAR(255) not NULL default 'unapproved',
  PRIMARY KEY ( ID ),
  FOREIGN KEY ( ACCOUNTID ) REFERENCES ACCOUNT( ID ),
  FOREIGN KEY ( CONTRIBUTIONID ) REFERENCES CONTRIBUTION ( ID )
);
