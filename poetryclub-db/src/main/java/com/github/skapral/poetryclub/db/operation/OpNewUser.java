package com.github.skapral.poetryclub.db.operation;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.db.access.DbaPoetryClub;

import static com.github.skapral.poetryclub.db.jooq.Tables.ACCOUNT;
import static org.jooq.impl.DSL.*;

/**
 * Operation, creating new user
 *
 * @author Kapralov Sergey
 */
public class OpNewUser extends OpJooq {
    /**
     * Ctor.
     * @param login Login of the new user
     */
    public OpNewUser(Scalar<String> login) {
        super(
            new DbaPoetryClub(),
            () -> insertInto(
                ACCOUNT,
                ACCOUNT.LOGIN
            ).select(
                select(
                    val(login.value())
                ).whereNotExists(
                    selectOne()
                        .from(ACCOUNT)
                        .where(ACCOUNT.LOGIN.eq(login.value()))
                )
            )
        );
    }
}
