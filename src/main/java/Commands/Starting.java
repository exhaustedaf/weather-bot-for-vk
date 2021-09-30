package Commands;

import Messenger.Command;
import Server.VKManager;
import com.vk.api.sdk.objects.messages.Message;

public class Starting extends Command {
    public Starting(String name) {
        super (name);
    }

    @Override
    public void exec(Message message) {
        new VKManager().sendMessage("Здравствуйте, я умею показывать погоду в любом городе планеты." +
                " Напишите \"погода <название населённого пункта>\", и я отправлю Вам" +
                "подробное описание погоды в требуемом городе.", message.getUserId());
    }
}
