package Commands;

import Server.VKManager;
import com.vk.api.sdk.objects.messages.Message;
import Messenger.Command;

import static java.lang.Math.random;

public class Roulette extends Command {
    private int count = 0;

    public Roulette(String name) {
        super (name);
    }

    @Override
    public void exec(Message message) {
        double bullet = random();
        count++;
        if ((count == 1 && bullet <= 0.16667) || (count == 2 && bullet <= 0.2)
        || (count == 3 && bullet <= 0.25) || (count == 4 && bullet <= 0.33)
        || (count == 5 && bullet <= 0.5) || count == 6) {
            new VKManager().sendMessage("Лох, ты сдох!", message.getUserId());
            count = 0;
        } else
            new VKManager().sendMessage("Ого, ты выжил, поздравляю! Теперь вероятность умереть повысилась! Удачи!", message.getUserId());
    }
}
