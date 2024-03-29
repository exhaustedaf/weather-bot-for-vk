package Messenger;

import com.vk.api.sdk.objects.messages.Message;

import java.io.IOException;

public class Messenger implements Runnable {
    private Message message;

    public Messenger(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        try {
            Commander.execute(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
