/*
 * Copyright (c) 2021, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ballerinalang.command;

import org.ballerinalang.command.cmd.DistributionCommand;
import org.ballerinalang.command.exceptions.CommandException;
import org.testng.Assert;
import org.testng.annotations.Test;
import picocli.CommandLine;

/**
 * Test cases for distribution command.
 *
 * @since 2.0.0
 */
public class DistributionCommandTest extends CommandTest {
    @Test
    public void distributionCommandHelpTest() {
        DistributionCommand distributionCommand = new DistributionCommand(testStream);
        new CommandLine(distributionCommand).parse("-h");
        distributionCommand.execute();
        Assert.assertTrue(outContent.toString().contains("Manage Ballerina distributions"));
    }

    @Test
    public void distributionCommandInvalidArgTest() {
        try {
            DistributionCommand distributionCommand = new DistributionCommand(testStream);
            new CommandLine(distributionCommand).parse("lists");
            distributionCommand.execute();
        } catch (CommandException e) {
            Assert.assertTrue(e.getMessages().get(0).contains("unknown command 'lists'"));
        }
    }

    @Test
    public void distributionCommandWithMultipleArgsTest() {
        try {
            DistributionCommand distributionCommand = new DistributionCommand(testStream);
            new CommandLine(distributionCommand).parse("arg1", "arg2");
            distributionCommand.execute();
        } catch (CommandException e) {
            Assert.assertTrue(e.getMessages().get(0).contains("too many arguments"));
        }
    }
}
