/*
 * This file is generated by jOOQ.
 */
package com.github.skapral.poetryclub.db.jooq.tables.records;


import com.github.skapral.poetryclub.db.jooq.tables.Contribution;

import java.sql.Timestamp;
import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.UpdatableRecordImpl;


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
public class ContributionRecord extends UpdatableRecordImpl<ContributionRecord> implements Record7<Long, Timestamp, Long, Long, UUID, String, String> {

    private static final long serialVersionUID = -446994317;

    /**
     * Setter for <code>public.contribution.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.contribution.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.contribution.timestamp</code>.
     */
    public void setTimestamp(Timestamp value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.contribution.timestamp</code>.
     */
    public Timestamp getTimestamp() {
        return (Timestamp) get(1);
    }

    /**
     * Setter for <code>public.contribution.accountid</code>.
     */
    public void setAccountid(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.contribution.accountid</code>.
     */
    public Long getAccountid() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>public.contribution.communityid</code>.
     */
    public void setCommunityid(Long value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.contribution.communityid</code>.
     */
    public Long getCommunityid() {
        return (Long) get(3);
    }

    /**
     * Setter for <code>public.contribution.uuid</code>.
     */
    public void setUuid(UUID value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.contribution.uuid</code>.
     */
    public UUID getUuid() {
        return (UUID) get(4);
    }

    /**
     * Setter for <code>public.contribution.url</code>.
     */
    public void setUrl(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.contribution.url</code>.
     */
    public String getUrl() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.contribution.status</code>.
     */
    public void setStatus(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.contribution.status</code>.
     */
    public String getStatus() {
        return (String) get(6);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Long, Timestamp, Long, Long, UUID, String, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row7<Long, Timestamp, Long, Long, UUID, String, String> valuesRow() {
        return (Row7) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return Contribution.CONTRIBUTION.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field2() {
        return Contribution.CONTRIBUTION.TIMESTAMP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field3() {
        return Contribution.CONTRIBUTION.ACCOUNTID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field4() {
        return Contribution.CONTRIBUTION.COMMUNITYID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UUID> field5() {
        return Contribution.CONTRIBUTION.UUID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Contribution.CONTRIBUTION.URL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return Contribution.CONTRIBUTION.STATUS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp component2() {
        return getTimestamp();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component3() {
        return getAccountid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component4() {
        return getCommunityid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UUID component5() {
        return getUuid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component6() {
        return getUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component7() {
        return getStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value2() {
        return getTimestamp();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value3() {
        return getAccountid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value4() {
        return getCommunityid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UUID value5() {
        return getUuid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getUrl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContributionRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContributionRecord value2(Timestamp value) {
        setTimestamp(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContributionRecord value3(Long value) {
        setAccountid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContributionRecord value4(Long value) {
        setCommunityid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContributionRecord value5(UUID value) {
        setUuid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContributionRecord value6(String value) {
        setUrl(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContributionRecord value7(String value) {
        setStatus(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ContributionRecord values(Long value1, Timestamp value2, Long value3, Long value4, UUID value5, String value6, String value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ContributionRecord
     */
    public ContributionRecord() {
        super(Contribution.CONTRIBUTION);
    }

    /**
     * Create a detached, initialised ContributionRecord
     */
    public ContributionRecord(Long id, Timestamp timestamp, Long accountid, Long communityid, UUID uuid, String url, String status) {
        super(Contribution.CONTRIBUTION);

        set(0, id);
        set(1, timestamp);
        set(2, accountid);
        set(3, communityid);
        set(4, uuid);
        set(5, url);
        set(6, status);
    }
}
