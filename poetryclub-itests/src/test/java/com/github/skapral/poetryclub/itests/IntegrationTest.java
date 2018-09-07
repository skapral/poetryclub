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
            )
        );
    }
}
