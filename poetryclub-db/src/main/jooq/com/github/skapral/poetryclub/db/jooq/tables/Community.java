/*
 * This file is generated by jOOQ.
 */
package com.github.skapral.poetryclub.db.jooq.tables;


import com.github.skapral.poetryclub.db.jooq.Indexes;
import com.github.skapral.poetryclub.db.jooq.Keys;
import com.github.skapral.poetryclub.db.jooq.Public;
import com.github.skapral.poetryclub.db.jooq.tables.records.CommunityRecord;

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
public class Community extends TableImpl<CommunityRecord> {

    private static final long serialVersionUID = -1143788427;

    /**
     * The reference instance of <code>public.community</code>
     */
    public static final Community COMMUNITY = new Community();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CommunityRecord> getRecordType() {
        return CommunityRecord.class;
    }

    /**
     * The column <code>public.community.id</code>.
     */
    public final TableField<CommunityRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('community_id'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.community.uuid</code>.
     */
    public final TableField<CommunityRecord, UUID> UUID = createField("uuid", org.jooq.impl.SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.community.name</code>.
     */
    public final TableField<CommunityRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * Create a <code>public.community</code> table reference
     */
    public Community() {
        this(DSL.name("community"), null);
    }

    /**
     * Create an aliased <code>public.community</code> table reference
     */
    public Community(String alias) {
        this(DSL.name(alias), COMMUNITY);
    }

    /**
     * Create an aliased <code>public.community</code> table reference
     */
    public Community(Name alias) {
        this(alias, COMMUNITY);
    }

    private Community(Name alias, Table<CommunityRecord> aliased) {
        this(alias, aliased, null);
    }

    private Community(Name alias, Table<CommunityRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Community(Table<O> child, ForeignKey<O, CommunityRecord> key) {
        super(child, key, COMMUNITY);
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
        return Arrays.<Index>asList(Indexes.COMMUNITY_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<CommunityRecord, Long> getIdentity() {
        return Keys.IDENTITY_COMMUNITY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<CommunityRecord> getPrimaryKey() {
        return Keys.COMMUNITY_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<CommunityRecord>> getKeys() {
        return Arrays.<UniqueKey<CommunityRecord>>asList(Keys.COMMUNITY_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Community as(String alias) {
        return new Community(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Community as(Name alias) {
        return new Community(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Community rename(String name) {
        return new Community(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Community rename(Name name) {
        return new Community(name, null);
    }
}
