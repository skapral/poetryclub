/*
 * This file is generated by jOOQ.
 */
package com.github.skapral.poetryclub.db.jooq;


import com.github.skapral.poetryclub.db.jooq.tables.Account;
import com.github.skapral.poetryclub.db.jooq.tables.Community;
import com.github.skapral.poetryclub.db.jooq.tables.Contribution;
import com.github.skapral.poetryclub.db.jooq.tables.Feedback;
import com.github.skapral.poetryclub.db.jooq.tables.FlywaySchemaHistory;
import com.github.skapral.poetryclub.db.jooq.tables.Member;
import com.github.skapral.poetryclub.db.jooq.tables.records.AccountRecord;
import com.github.skapral.poetryclub.db.jooq.tables.records.CommunityRecord;
import com.github.skapral.poetryclub.db.jooq.tables.records.ContributionRecord;
import com.github.skapral.poetryclub.db.jooq.tables.records.FeedbackRecord;
import com.github.skapral.poetryclub.db.jooq.tables.records.FlywaySchemaHistoryRecord;
import com.github.skapral.poetryclub.db.jooq.tables.records.MemberRecord;

import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>public</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<AccountRecord, Long> IDENTITY_ACCOUNT = Identities0.IDENTITY_ACCOUNT;
    public static final Identity<CommunityRecord, Long> IDENTITY_COMMUNITY = Identities0.IDENTITY_COMMUNITY;
    public static final Identity<ContributionRecord, Long> IDENTITY_CONTRIBUTION = Identities0.IDENTITY_CONTRIBUTION;
    public static final Identity<FeedbackRecord, Long> IDENTITY_FEEDBACK = Identities0.IDENTITY_FEEDBACK;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AccountRecord> ACCOUNT_PKEY = UniqueKeys0.ACCOUNT_PKEY;
    public static final UniqueKey<CommunityRecord> COMMUNITY_PKEY = UniqueKeys0.COMMUNITY_PKEY;
    public static final UniqueKey<ContributionRecord> CONTRIBUTION_PKEY = UniqueKeys0.CONTRIBUTION_PKEY;
    public static final UniqueKey<FeedbackRecord> FEEDBACK_PKEY = UniqueKeys0.FEEDBACK_PKEY;
    public static final UniqueKey<FlywaySchemaHistoryRecord> FLYWAY_SCHEMA_HISTORY_PK = UniqueKeys0.FLYWAY_SCHEMA_HISTORY_PK;
    public static final UniqueKey<MemberRecord> MEMBER_PKEY = UniqueKeys0.MEMBER_PKEY;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<ContributionRecord, AccountRecord> CONTRIBUTION__CONTRIBUTION_ACCOUNTID_FKEY = ForeignKeys0.CONTRIBUTION__CONTRIBUTION_ACCOUNTID_FKEY;
    public static final ForeignKey<ContributionRecord, CommunityRecord> CONTRIBUTION__CONTRIBUTION_COMMUNITYID_FKEY = ForeignKeys0.CONTRIBUTION__CONTRIBUTION_COMMUNITYID_FKEY;
    public static final ForeignKey<FeedbackRecord, AccountRecord> FEEDBACK__FEEDBACK_ACCOUNTID_FKEY = ForeignKeys0.FEEDBACK__FEEDBACK_ACCOUNTID_FKEY;
    public static final ForeignKey<FeedbackRecord, ContributionRecord> FEEDBACK__FEEDBACK_CONTRIBUTIONID_FKEY = ForeignKeys0.FEEDBACK__FEEDBACK_CONTRIBUTIONID_FKEY;
    public static final ForeignKey<MemberRecord, AccountRecord> MEMBER__MEMBER_ACCOUNTID_FKEY = ForeignKeys0.MEMBER__MEMBER_ACCOUNTID_FKEY;
    public static final ForeignKey<MemberRecord, CommunityRecord> MEMBER__MEMBER_COMMUNITYID_FKEY = ForeignKeys0.MEMBER__MEMBER_COMMUNITYID_FKEY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<AccountRecord, Long> IDENTITY_ACCOUNT = Internal.createIdentity(Account.ACCOUNT, Account.ACCOUNT.ID);
        public static Identity<CommunityRecord, Long> IDENTITY_COMMUNITY = Internal.createIdentity(Community.COMMUNITY, Community.COMMUNITY.ID);
        public static Identity<ContributionRecord, Long> IDENTITY_CONTRIBUTION = Internal.createIdentity(Contribution.CONTRIBUTION, Contribution.CONTRIBUTION.ID);
        public static Identity<FeedbackRecord, Long> IDENTITY_FEEDBACK = Internal.createIdentity(Feedback.FEEDBACK, Feedback.FEEDBACK.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<AccountRecord> ACCOUNT_PKEY = Internal.createUniqueKey(Account.ACCOUNT, "account_pkey", Account.ACCOUNT.ID);
        public static final UniqueKey<CommunityRecord> COMMUNITY_PKEY = Internal.createUniqueKey(Community.COMMUNITY, "community_pkey", Community.COMMUNITY.ID);
        public static final UniqueKey<ContributionRecord> CONTRIBUTION_PKEY = Internal.createUniqueKey(Contribution.CONTRIBUTION, "contribution_pkey", Contribution.CONTRIBUTION.ID);
        public static final UniqueKey<FeedbackRecord> FEEDBACK_PKEY = Internal.createUniqueKey(Feedback.FEEDBACK, "feedback_pkey", Feedback.FEEDBACK.ID);
        public static final UniqueKey<FlywaySchemaHistoryRecord> FLYWAY_SCHEMA_HISTORY_PK = Internal.createUniqueKey(FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, "flyway_schema_history_pk", FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK);
        public static final UniqueKey<MemberRecord> MEMBER_PKEY = Internal.createUniqueKey(Member.MEMBER, "member_pkey", Member.MEMBER.ACCOUNTID, Member.MEMBER.COMMUNITYID);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<ContributionRecord, AccountRecord> CONTRIBUTION__CONTRIBUTION_ACCOUNTID_FKEY = Internal.createForeignKey(com.github.skapral.poetryclub.db.jooq.Keys.ACCOUNT_PKEY, Contribution.CONTRIBUTION, "contribution__contribution_accountid_fkey", Contribution.CONTRIBUTION.ACCOUNTID);
        public static final ForeignKey<ContributionRecord, CommunityRecord> CONTRIBUTION__CONTRIBUTION_COMMUNITYID_FKEY = Internal.createForeignKey(com.github.skapral.poetryclub.db.jooq.Keys.COMMUNITY_PKEY, Contribution.CONTRIBUTION, "contribution__contribution_communityid_fkey", Contribution.CONTRIBUTION.COMMUNITYID);
        public static final ForeignKey<FeedbackRecord, AccountRecord> FEEDBACK__FEEDBACK_ACCOUNTID_FKEY = Internal.createForeignKey(com.github.skapral.poetryclub.db.jooq.Keys.ACCOUNT_PKEY, Feedback.FEEDBACK, "feedback__feedback_accountid_fkey", Feedback.FEEDBACK.ACCOUNTID);
        public static final ForeignKey<FeedbackRecord, ContributionRecord> FEEDBACK__FEEDBACK_CONTRIBUTIONID_FKEY = Internal.createForeignKey(com.github.skapral.poetryclub.db.jooq.Keys.CONTRIBUTION_PKEY, Feedback.FEEDBACK, "feedback__feedback_contributionid_fkey", Feedback.FEEDBACK.CONTRIBUTIONID);
        public static final ForeignKey<MemberRecord, AccountRecord> MEMBER__MEMBER_ACCOUNTID_FKEY = Internal.createForeignKey(com.github.skapral.poetryclub.db.jooq.Keys.ACCOUNT_PKEY, Member.MEMBER, "member__member_accountid_fkey", Member.MEMBER.ACCOUNTID);
        public static final ForeignKey<MemberRecord, CommunityRecord> MEMBER__MEMBER_COMMUNITYID_FKEY = Internal.createForeignKey(com.github.skapral.poetryclub.db.jooq.Keys.COMMUNITY_PKEY, Member.MEMBER, "member__member_communityid_fkey", Member.MEMBER.COMMUNITYID);
    }
}
