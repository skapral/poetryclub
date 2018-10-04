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
import com.github.skapral.poetryclub.itests.assertions.AssertWebdriverScenario;
import com.github.skapral.poetryclub.itests.assertions.webdriver.action.*;
import com.github.skapral.poetryclub.itests.assertions.webdriver.action.complex.*;
import com.github.skapral.poetryclub.itests.assertions.webdriver.hyp.HypAbsent;
import com.github.skapral.poetryclub.itests.assertions.webdriver.hyp.HypPresent;
import com.github.skapral.poetryclub.itests.assertions.webdriver.poi.PoiSubmitButton;
import com.github.skapral.poetryclub.itests.assertions.webdriver.poi.PoiTitle;
import com.github.skapral.poetryclub.itests.assertions.webdriver.poi.agenda.AgendaMessage;
import com.github.skapral.poetryclub.itests.assertions.webdriver.poi.agenda.AgendaUserHasNotContributedAnythingLastMonth;
import com.github.skapral.poetryclub.itests.assertions.webdriver.poi.agenda.AgendaUserHasNotLeftAnyFeedbackLastMonth;
import com.github.skapral.poetryclub.itests.assertions.webdriver.poi.agenda.AgendaYouHaventLeftAnyFeedbackLastMonth;
import com.github.skapral.poetryclub.itests.assertions.webdriver.poi.agenda.AgendaYouHaventMadeAnyContributionsLastMonth;
import com.pragmaticobjects.oo.atom.anno.NotAtom;
import com.pragmaticobjects.oo.tests.TestCase;
import com.pragmaticobjects.oo.tests.junit5.TestsSuite;

import java.time.Duration;

/**
 * Integration tests suite for poetryclub.
 *
 * @author Kapralov Sergey
 */
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
                        new ActAuthenticateAsUser("owner"),
                        new ActCheckPoiText(
                            new PoiTitle(),
                            "Provide your community's name!"
                        )
                    )
                )
            ),
            new TestCase(
                "Non-owner is suggested to join the existing communities",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new ActAuthenticateAsUser("nonowner"),
                        new ActCheckPoiText(
                            new PoiTitle(),
                            "Choose community to join"
                        )
                    )
                )
            ),
            new TestCase(
                "Owner gets membership page after creating community",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new ActAsUser(
                            "owner",
                            new ActCreateCommunity("Test community"),
                            new ActCheckPoiText(
                                new PoiTitle(),
                                "Membership of owner"
                            )
                        )
                    )
                )
            ),
            new TestCase(
                "Non-owner gets membership page after joining community",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new ActAsUser(
                            "owner",
                            new ActCreateCommunity("Test community")
                        ),
                        new ActAsUser(
                            "user",
                            new ActJoinCommunity("Test community"),
                            new ActCheckPoiText(
                                new PoiTitle(),
                                "Membership of user"
                            )
                        )
                    )
                )
            ),
            new TestCase(
                "User joins, but its membership was not yet approved",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new ActAsUser(
                            "owner",
                            new ActCreateCommunity("Test community")
                        ),
                        new ActAsUser(
                            "user",
                            new ActJoinCommunity("Test community")
                        ),
                        new ActAsUser(
                            "user",
                            new ActOpenCommunityAgenda("Test community"),
                            new ActCheckPoiText(
                                new PoiTitle(),
                                "Membership of user in Test community is not yet approved."
                            )
                        )
                    )
                )
            ),
            new TestCase(
                "Owner approves the user's membership",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new ActAsUser(
                            "owner",
                            new ActCreateCommunity("Test community")
                        ),
                        new ActAsUser(
                            "user",
                            new ActJoinCommunity("Test community")
                        ),
                        new ActAsUser(
                            "owner",
                            new ActOpenCommunityAgenda("Test community"),
                            new ActApproveMembership("user")
                        ),
                        new ActAsUser(
                            "user",
                            new ActOpenCommunityAgenda("Test community"),
                            new ActCheckPoiText(
                                new PoiTitle(),
                                "Agenda for user in Test community"
                            )
                        )
                   )
                )
            ),
            new TestCase(
                "Owner declines the user's membership",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new ActAsUser(
                            "owner",
                            new ActCreateCommunity("Test community")
                        ),
                        new ActAsUser(
                            "user",
                            new ActJoinCommunity("Test community")
                        ),
                        new ActAsUser(
                            "owner",
                            new ActOpenCommunityAgenda("Test community"),
                            new ActDeclineMembership("user")
                        ),
                        new ActAsUser(
                            "user",
                            new ActOpenCommunityAgenda("Test community"),
                            new ActCheckPoiText(
                                new PoiTitle(),
                                "Membership of user in Test community is suspended."
                            )
                        )
                    )
                )
            ),
            new TestCase(
                "Agenda shows candidates to ban for admin",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new ActAsUser(
                            "owner",
                            new ActCreateCommunity("Test community")
                        ),
                        new ActAsUser(
                            "user",
                            new ActJoinCommunity("Test community")
                        ),
                        new ActAsUser(
                            "owner",
                            new ActOpenCommunityAgenda("Test community"),
                            new ActApproveMembership("user"),
                            new ActSubmitContribution("http://contribution1")
                        ),
                        new ActWait(Duration.ofDays(32)),
                        new ActAsUser(
                            "owner",
                            new ActOpenCommunityAgenda("Test community"),
                            new ActCheck(
                                new HypPresent(
                                    new AgendaUserHasNotContributedAnythingLastMonth("user")
                                )
                            ),
                            new ActCheck(
                                new HypPresent(
                                    new AgendaUserHasNotLeftAnyFeedbackLastMonth("user", "http://contribution1")
                                )
                            )
                        )
                    )
                )
            ),
            new TestCase(
                "Agenda shows rules violations for member",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new ActAsUser(
                            "owner",
                            new ActCreateCommunity("Test community")
                        ),
                        new ActAsUser(
                            "user",
                            new ActJoinCommunity("Test community")
                        ),
                        new ActAsUser(
                            "owner",
                            new ActOpenCommunityAgenda("Test community"),
                            new ActApproveMembership("user"),
                            new ActSubmitContribution("http://contribution1")
                        ),
                        new ActWait(Duration.ofDays(32)),
                        new ActAsUser(
                            "user",
                            new ActOpenCommunityAgenda("Test community"),
                            new ActCheck(
                                new HypPresent(
                                    new AgendaYouHaventMadeAnyContributionsLastMonth()
                                )
                            ),
                            new ActCheck(
                                new HypPresent(
                                    new AgendaYouHaventLeftAnyFeedbackLastMonth("http://contribution1")
                                )
                            )
                        )
                    )
                )
            ),
            new TestCase(
                "Agenda doesn't show violations for the users who recently joined in",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new ActAsUser(
                            "owner",
                            new ActCreateCommunity("Test community"),
                            new ActOpenCommunityAgenda("Test community"),
                            new ActSubmitContribution("http://contribution1")
                        ),
                        new ActWait(Duration.ofDays(32)),
                        new ActAsUser(
                            "innocent_user",
                            new ActJoinCommunity("Test community")
                        ),
                        new ActAsUser(
                            "owner",
                            new ActOpenCommunityAgenda("Test community"),
                            new ActApproveMembership("innocent_user")
                        ),
                        new ActAsUser(
                            "innocent_user",
                            new ActOpenCommunityAgenda("Test community"),
                            new ActCheck(
                                new HypAbsent(
                                    new AgendaYouHaventMadeAnyContributionsLastMonth()
                                )
                            ),
                            new ActCheck(
                                new HypAbsent(
                                    new AgendaYouHaventLeftAnyFeedbackLastMonth("http://contribution1")
                                )
                            )
                        ),
                        new ActAsUser(
                            "owner",
                            new ActOpenCommunityAgenda("Test community"),
                            new ActCheck(
                                new HypAbsent(
                                    new AgendaUserHasNotContributedAnythingLastMonth("innocent_user")
                                )
                            ),
                            new ActCheck(
                                new HypAbsent(
                                    new AgendaUserHasNotLeftAnyFeedbackLastMonth("innocent_user", "http://contribution1")
                                )
                            )
                        )
                    )
                )
            ),
            new TestCase(
                "Admin forgave the disloyal member",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new ActAsUser(
                            "owner",
                            new ActCreateCommunity("Test community")
                        ),
                        new ActAsUser(
                            "user",
                            new ActJoinCommunity("Test community")
                        ),
                        new ActAsUser(
                            "owner",
                            new ActOpenCommunityAgenda("Test community"),
                            new ActApproveMembership("user")
                        ),
                        new ActWait(Duration.ofDays(32)),
                        new ActAsUser(
                            "owner",
                            new ActOpenCommunityAgenda("Test community"),
                            new ActClick(
                                new PoiSubmitButton(
                                    "Forgive",
                                    new AgendaUserHasNotContributedAnythingLastMonth(
                                        "user"
                                    )
                                )
                            )
                        ),
                        new ActAsUser(
                            "user",
                            new ActOpenCommunityAgenda("Test community"),
                            new ActCheck(
                                new HypAbsent(
                                    new AgendaYouHaventMadeAnyContributionsLastMonth()
                                )
                            )
                        )
                    )
                )
            ),
            new TestCase(
                "Admin banned the disloyal member",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new ActAsUser(
                            "owner",
                            new ActCreateCommunity("Test community")
                        ),
                        new ActAsUser(
                            "user",
                            new ActJoinCommunity("Test community")
                        ),
                        new ActAsUser(
                            "owner",
                            new ActOpenCommunityAgenda("Test community"),
                            new ActApproveMembership("user")
                        ),
                        new ActWait(Duration.ofDays(32)),
                        new ActAsUser(
                            "owner",
                            new ActOpenCommunityAgenda("Test community"),
                            new ActClick(
                                new PoiSubmitButton(
                                    "Ban",
                                    new AgendaUserHasNotContributedAnythingLastMonth(
                                        "user"
                                    )
                                )
                            )
                        ),
                        new ActAsUser(
                            "user",
                            new ActOpenCommunityAgenda("Test community"),
                            new ActCheckPoiText(
                                new PoiTitle(),
                                "Membership of user in Test community is suspended."
                            )
                        )
                    )
                )
            ),
            new TestCase(
                "Agenda page has a link on community summary",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new ActAsUser(
                            "owner",
                            new ActCreateCommunity("Test community"),
                            new ActOpenCommunityAgenda("Test community"),
                            new ActOpenCommunitySummary(),
                            new ActCheckPoiText(
                                new PoiTitle(),
                                "Summary for Test community"
                            )
                        )
                    )
                )
            ),
            new TestCase(
                "Summary page of community displays all members",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new ActAsUser(
                            "owner",
                            new ActCreateCommunity("Test community")
                        ),
                        new ActAsUser(
                            "user1",
                            new ActJoinCommunity("Test community")
                        ),
                        new ActAsUser(
                            "user2",
                            new ActJoinCommunity("Test community")
                        ),
                        new ActAsUser(
                            "owner",
                            new ActOpenCommunityAgenda("Test community"),
                            new ActApproveMembership("user1"),
                            new ActOpenCommunitySummary(),
                            new ActCheckSummaryPageDisplaysMember("owner", "admin"),
                            new ActCheckSummaryPageDisplaysMember("user1", "member"),
                            new ActCheckSummaryPageDisplaysMember("user2", "candidate")
                        )
                    )
                )
            ),
            new TestCase(
                "Summary page of community displays all contributions",
                new AssertAssumingPoetryclubInstance(
                    new AssertWebdriverScenario(
                        new ActAsUser(
                            "owner",
                            new ActCreateCommunity("Test community")
                        ),
                        new ActAsUser(
                            "user1",
                            new ActJoinCommunity("Test community")
                        ),
                        new ActAsUser(
                            "owner",
                            new ActOpenCommunityAgenda("Test community"),
                            new ActApproveMembership("user1"),
                            new ActSubmitContribution("http://test/contrib1")
                        ),
                        new ActAsUser(
                            "user1",
                            new ActOpenCommunityAgenda("Test community"),
                            new ActSubmitContribution("http://test/contrib2"),
                            new ActOpenCommunitySummary(),
                            new ActCheckSummaryPageDisplaysContribution("http://test/contrib1", "owner"),
                            new ActCheckSummaryPageDisplaysContribution("http://test/contrib2", "user1")
                        )
                    )
                )
            )
        );
    }
}
