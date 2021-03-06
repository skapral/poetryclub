/*
 * This file is generated by jOOQ.
 */
package com.github.skapral.poetryclub.db.jooq.tables;


import com.github.skapral.poetryclub.db.jooq.Indexes;
import com.github.skapral.poetryclub.db.jooq.Keys;
import com.github.skapral.poetryclub.db.jooq.Public;
import com.github.skapral.poetryclub.db.jooq.tables.records.ContributionRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Contribution extends TableImpl<ContributionRecord> {

    private static final long serialVersionUID = -1203008068;

    /**
     * The reference instance of <code>public.contribution</code>
     */
    public static final Contribution CONTRIBUTION = new Contribution();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ContributionRecord> getRecordType() {
        return ContributionRecord.class;
    }

    /**
     * The column <code>public.contribution.id</code>.
     */
    public final TableField<ContributionRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('contribution_id'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.contribution.timestamp</code>.
     */
    public final TableField<ContributionRecord, Timestamp> TIMESTAMP = createField("timestamp", org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>public.contribution.accountid</code>.
     */
    public final TableField<ContributionRecord, Long> ACCOUNTID = createField("accountid", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.contribution.communityid</code>.
     */
    public final TableField<ContributionRecord, Long> COMMUNITYID = createField("communityid", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>public.contribution.uuid</code>.
     */
    public final TableField<ContributionRecord, UUID> UUID = createField("uuid", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.contribution.url</code>.
     */
    public final TableField<ContributionRecord, String> URL = createField("url", org.jooq.impl.SQLDataType.VARCHAR(4096), this, "");

    /**
     * The column <code>public.contribution.status</code>.
     */
    public final TableField<ContributionRecord, String> STATUS = createField("status", org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false).defaultValue(org.jooq.impl.DSL.field("'unapproved'::character varying", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * Create a <code>public.contribution</code> table reference
     */
    public Contribution() {
        this(DSL.name("contribution"), null);
    }

    /**
     * Create an aliased <code>public.contribution</code> table reference
     */
    public Contribution(String alias) {
        this(DSL.name(alias), CONTRIBUTION);
    }

    /**
     * Create an aliased <code>public.contribution</code> table reference
     */
    public Contribution(Name alias) {
        this(alias, CONTRIBUTION);
    }

    private Contribution(Name alias, Table<ContributionRecord> aliased) {
        this(alias, aliased, null);
    }

    private Contribution(Name alias, Table<ContributionRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Contribution(Table<O> child, ForeignKey<O, ContributionRecord> key) {
        super(child, key, CONTRIBUTION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.CONTRIBUTION_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<ContributionRecord, Long> getIdentity() {
        return Keys.IDENTITY_CONTRIBUTION;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ContributionRecord> getPrimaryKey() {
        return Keys.CONTRIBUTION_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ContributionRecord>> getKeys() {
        return Arrays.<UniqueKey<ContributionRecord>>asList(Keys.CONTRIBUTION_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<ContributionRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ContributionRecord, ?>>asList(Keys.CONTRIBUTION__CONTRIBUTION_ACCOUNTID_FKEY, Keys.CONTRIBUTION__CONTRIBUTION_COMMUNITYID_FKEY);
    }

    public Account account() {
        return new Account(this, Keys.CONTRIBUTION__CONTRIBUTION_ACCOUNTID_FKEY);
    }

    public Community community() {
        return new Community(this, Keys.CONTRIBUTION__CONTRIBUTION_COMMUNITYID_FKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Contribution as(String alias) {
        return new Contribution(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Contribution as(Name alias) {
        return new Contribution(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Contribution rename(String name) {
        return new Contribution(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Contribution rename(Name name) {
        return new Contribution(name, null);
    }
}
