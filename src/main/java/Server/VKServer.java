package Server;

import Messenger.Messenger;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VKServer {
    public static VKCore vkCore;
    static final int RECONNECT_TIME = 10000;

    static {
        try {
            vkCore = new VKCore();
        } catch (ApiException | ClientException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws NullPointerException, ApiException, InterruptedException {
        System.out.println("Сервер запускается...");
        while(true) {
            Thread.sleep(300);
            try {
                Message message = vkCore.getMessage();
                if (message != null) {
                    ExecutorService exec = Executors.newCachedThreadPool();
                    exec.execute(new Messenger(message));
                }
            } catch (ClientException e) {
                System.out.println("Что-то пошло не так...");
                System.out.println("Повторное соединение через " + RECONNECT_TIME / 1000 + " секунд");
                Thread.sleep(RECONNECT_TIME);
            }
        }
    }
}