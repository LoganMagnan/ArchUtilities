package club.archdev.archutilities.utils.command;

import club.archdev.archutilities.ArchUtilities;

public abstract class BaseCommand {

    public BaseCommand() {
        ArchUtilities.getInstance().getCommandFramework().registerCommands(this, null);
    }

    public abstract void executeAs(CommandArguments command);
}
