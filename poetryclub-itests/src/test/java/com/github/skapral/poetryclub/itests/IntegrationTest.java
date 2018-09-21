/*
 * MIT License
 *
 * Copyright (c) 2018 Kapralov Sergey
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.github.skapral.poetryclub.itests;

import com.github.skapral.poetryclub.itests.assertions.AssertAssumingPoetryclubInstance;
import com.github.skapral.poetryclub.itests.assertions.webdriver.*;
import com.pragmaticobjects.oo.atom.anno.NotAtom;
import com.pragmaticobjects.oo.tests.TestCase;
import com.pragmaticobjects.oo.tests.junit5.TestsSuite;

/**
 * Integration tests suite for poetryclub.
 *
 * @author Kapralov Sergey
 */
@NotAtom
public class IntegrationTest extends TestsSuite {
    /**
     * Ctor.
     */
    public IntegrationTest() {
        super(
            new TestCase(
                "Owner is suggested to create the first community, if not yet created",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new AuthenticateAsUser("owner"),
                        new AssertNewCommunityPage()
                    )
                )
            ),
            new TestCase(
                "Non-owner is suggested to join the existing communities",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new AuthenticateAsUser("nonowner"),
                        new AssertJoinCommunityPage()
                    )
                )
            ),
            new TestCase(
                "Owner gets membership page after creating community",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new AuthenticateAsUser("owner"),
                        new CreateCommunity("Test community"),
                        new AssertMembershipPage("owner")
                    )
                )
            ),
            new TestCase(
                "Non-owner gets membership page after joining community",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new AuthenticateAsUser("owner"),
                        new CreateCommunity("Test community"),
                        new AuthenticateAsUser("user"),
                        new JoinCommunity("Test community"),
                        new AssertMembershipPage("user")
                    )
                )
            ),
            new TestCase(
                "User joins, but its membership was not yet approved",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new AuthenticateAsUser("owner"),
                        new CreateCommunity("Test community"),
                        new AuthenticateAsUser("user"),
                        new JoinCommunity("Test community"),
                        new OpenCommunityAgenda("Test community"),
                        new AssertMembershipNotApprovedPage("user", "Test community")
                    )
                )
            ),
            new TestCase(
                "Owner approves the user's membership",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new AuthenticateAsUser("owner"),
                        new CreateCommunity("Test community"),
                        new AuthenticateAsUser("user"),
                        new JoinCommunity("Test community"),
                        new AuthenticateAsUser("owner"),
                        new OpenCommunityAgenda("Test community"),
                        new ApproveMembershipOnAgenda("user"),
                        new AuthenticateAsUser("user"),
                        new OpenCommunityAgenda("Test community"),
                        new AssertAgendaPage("user", "Test community")
                    )
                )
            ),
            new TestCase(
                "Owner declines the user's membership",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new AuthenticateAsUser("owner"),
                        new CreateCommunity("Test community"),
                        new AuthenticateAsUser("user"),
                        new JoinCommunity("Test community"),
                        new AuthenticateAsUser("owner"),
                        new OpenCommunityAgenda("Test community"),
                        new DeclineMembershipOnAgenda("user"),
                        new AuthenticateAsUser("user"),
                        new OpenCommunityAgenda("Test community"),
                        new AssertMemberIsBanned("user", "Test community")
                    )
                )
            ),
            new TestCase(
                "Agenda page has a link on community summary",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new AuthenticateAsUser("owner"),
                        new CreateCommunity("Test community"),
                        new OpenCommunityAgenda("Test community"),
                        new OpenCommunitySummary(),
                        new AssertSummaryPage("Test community")
                    )
                )
            ),
            new TestCase(
                "Summary page of community displays all members",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new AuthenticateAsUser("owner"),
                        new CreateCommunity("Test community"),
                        new AuthenticateAsUser("user1"),
                        new JoinCommunity("Test community"),
                        new AuthenticateAsUser("user2"),
                        new JoinCommunity("Test community"),
                        new AuthenticateAsUser("owner"),
                        new OpenCommunityAgenda("Test community"),
                        new ApproveMembershipOnAgenda("user1"),
                        new OpenCommunitySummary(),
                        new AssertSummaryPageDisplaysMember("owner", "admin"),
                        new AssertSummaryPageDisplaysMember("user1", "member"),
                        new AssertSummaryPageDisplaysMember("user2", "candidate")
                    )
                )
            ),
            new TestCase(
                "Summary page of community displays all contributions",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new AuthenticateAsUser("owner"),
                        new CreateCommunity("Test community"),
                        new AuthenticateAsUser("user1"),
                        new JoinCommunity("Test community"),
                        new AuthenticateAsUser("owner"),
                        new OpenCommunityAgenda("Test community"),
                        new ApproveMembershipOnAgenda("user1"),
                        new SubmitContributionOnAgenda("http://test/contrib1"),
                        new AuthenticateAsUser("user1"),
                        new OpenCommunityAgenda("Test community"),
                        new SubmitContributionOnAgenda("http://test/contrib2"),
                        new OpenCommunitySummary(),
                        new AssertSummaryPageDisplaysContribution("http://test/contrib1", "owner"),
                        new AssertSummaryPageDisplaysContribution("http://test/contrib2", "user1")
                    )
                )
            )
        );
    }
}
