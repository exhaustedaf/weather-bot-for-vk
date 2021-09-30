package Messenger;

import Commands.Roulette;
import Commands.Starting;
import Commands.Unknown;
import Commands.Weather.Weather;

import java.util.HashSet;

public class CommandManager {
    private static HashSet<Command> commands = new HashSet<>();

    static {
        commands.add(new Unknown("unknown"));
        commands.add(new Weather("погода"));
        commands.add(new Starting("начать"));
        commands.add(new Roulette("рулетка"));
    }

    public static HashSet<Command> getCommands(){
        return commands;
    }
}
