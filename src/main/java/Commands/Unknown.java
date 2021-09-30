package Commands;

import Messenger.Command;
import Server.VKManager;
import com.vk.api.sdk.objects.messages.Message;

public class Unknown extends Command {

    public Unknown(String name) {
        super(name);
    }

    @Override
    public void exec(Message message) {
        new VKManager().sendMessage("Извините, не могу разобрать, это на эльфийском?", message.getUserId());
    }
}
