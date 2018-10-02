/*
 * This file is generated by jOOQ.
 */
package com.github.skapral.poetryclub.db.jooq.tables;


import com.github.skapral.poetryclub.db.jooq.Indexes;
import com.github.skapral.poetryclub.db.jooq.Keys;
import com.github.skapral.poetryclub.db.jooq.Public;
import com.github.skapral.poetryclub.db.jooq.tables.records.AccountRecord;

import java.util.Arrays;
import java.util.List;

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
public class Account extends TableImpl<AccountRecord> {

    private static final long serialVersionUID = 1425496085;

    /**
     * The reference instance of <code>public.account</code>
     */
    public static final Account ACCOUNT = new Account();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AccountRecord> getRecordType() {
        return AccountRecord.class;
    }

    /**
     * The column <code>public.account.id</code>.
     */
    public final TableField<AccountRecord, Long> ID = createField("id", org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('account_id'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.account.login</code>.
     */
    public final TableField<AccountRecord, String> LOGIN = createField("login", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * Create a <code>public.account</code> table reference
     */
    public Account() {
        this(DSL.name("account"), null);
    }

    /**
     * Create an aliased <code>public.account</code> table reference
     */
    public Account(String alias) {
        this(DSL.name(alias), ACCOUNT);
    }

    /**
     * Create an aliased <code>public.account</code> table reference
     */
    public Account(Name alias) {
        this(alias, ACCOUNT);
    }

    private Account(Name alias, Table<AccountRecord> aliased) {
        this(alias, aliased, null);
    }

    private Account(Name alias, Table<AccountRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Account(Table<O> child, ForeignKey<O, AccountRecord> key) {
        super(child, key, ACCOUNT);
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
        return Arrays.<Index>asList(Indexes.ACCOUNT_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<AccountRecord, Long> getIdentity() {
        return Keys.IDENTITY_ACCOUNT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<AccountRecord> getPrimaryKey() {
        return Keys.ACCOUNT_PKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<AccountRecord>> getKeys() {
        return Arrays.<UniqueKey<AccountRecord>>asList(Keys.ACCOUNT_PKEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account as(String alias) {
        return new Account(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Account as(Name alias) {
        return new Account(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Account rename(String name) {
        return new Account(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Account rename(Name name) {
        return new Account(name, null);
    }
}
