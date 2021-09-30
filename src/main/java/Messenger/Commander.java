package Messenger;

import com.vk.api.sdk.objects.messages.Message;

import java.io.IOException;

public class Commander {
    public static void execute(Message message) throws IOException {
        CommandDeterminant.getCommand(CommandManager.getCommands(), message).exec(message);
    }
}