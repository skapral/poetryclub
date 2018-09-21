package com.github.skapral.poetryclub.service.template;

import com.github.skapral.poetryclub.core.scalar.Scalar;
import com.github.skapral.poetryclub.core.scalar.ScalarHash;
import com.github.skapral.poetryclub.db.scalar.ScalarCommunityContributions;
import com.github.skapral.poetryclub.db.scalar.ScalarCommunityMembers;
import com.github.skapral.poetryclub.db.scalar.ScalarCommunityName;
import io.vavr.Tuple2;

import java.util.UUID;

public class TmplSummary extends TmplJtwig2 {
    public TmplSummary(Scalar<UUID> communityId) {
        super(
            "WEB-INF/templates/summary.twig.html",
            new ScalarHash<>(
                new Tuple2<>("community", new ScalarCommunityName(communityId)),
                new Tuple2<>("membership", new ScalarCommunityMembers(communityId)),
                new Tuple2<>("contributions", new ScalarCommunityContributions(communityId))
            )
        );
    }
}
