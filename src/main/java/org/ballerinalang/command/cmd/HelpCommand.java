/*
 * Copyright (c) 2019, WSO2 Inc. (http://wso2.com) All Rights Reserved.
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

package org.ballerinalang.command.cmd;

import org.ballerinalang.command.BallerinaCliCommands;
import org.ballerinalang.command.util.ErrorUtil;
import picocli.CommandLine;

import java.util.List;

/**
 * This class represents the "help" command and it holds arguments and flags specified by the user.
 */
@CommandLine.Command(name = "help", description = "print usage information")
public class HelpCommand extends Command implements BCommand {

    @CommandLine.Parameters(description = "Command name")
    private List<String> helpCommands;

    private CommandLine parentCmdParser;

    public void execute() {
        if (helpCommands == null) {
            printUsageInfo(BallerinaCliCommands.HELP);
            return;

        }

        int cmdCount = helpCommands.size();

        if (cmdCount > 2) {
             throw ErrorUtil.createUsageExceptionWithHelp("too many arguments");
        }

        String userCommand = helpCommands.get(0);

        if (cmdCount == 2) {
            userCommand += "-" + helpCommands.get(1);
        }

        String commandUsageInfo = getCommandUsageInfo(userCommand);
        getPrintStream().println(commandUsageInfo);
    }

    @Override
    public String getName() {
        return BallerinaCliCommands.HELP;
    }

    @Override
    public void printLongDesc(StringBuilder out) {

    }

    @Override
    public void printUsage(StringBuilder out) {
    }

    @Override
    public void setParentCmdParser(CommandLine parentCmdParser) {
        this.parentCmdParser = parentCmdParser;
    }
}
