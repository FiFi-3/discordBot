package discord.bot.fifi.functions;

import discord.bot.fifi.service.Server;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;

public class MessageEventListener extends ListenerAdapter {
    Server server = new Server();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        super.onMessageReceived(event);
        String userMsg = event.getMessage().getContentRaw();
        //NewsChannel serverNews = event.getGuild().getNewsChannels().get();
        //List<GuildChannel> channel = event.getGuild().getChannels();

    /*    if (userMsg.equalsIgnoreCase("R:restart 1337")) {
            //serverNews.sendMessage("I will restart the server").queue();

            try {
                server.restartServer();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } else if (userMsg.equalsIgnoreCase("R:start 1337")) {
            try {
                server.startServer();
                //serverNews.sendMessage("I will start the server").queue();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }else*/
        if (userMsg.equalsIgnoreCase(".jonas")) {
            event.getMessage().delete().queue();
            event.getChannel().sendMessage("Jonas hat Sand in der Vagina").queue();
        } else if (userMsg.equalsIgnoreCase(".status")) {
            /*for(GuildChannel kanal : channel)///
            System.out.println(kanal.toString());*/

            if (server.getStatus()) {
                event.getChannel().sendMessage(":arrow_forward: Der Server ist **online**!").queue();
            } else {
                event.getChannel().sendMessage(":stop_button: Der Server ist **offline**!").queue();
            }

        } else if (userMsg.equalsIgnoreCase(".news")) {
            try {
                event.getChannel().sendMessage("Das sind die letzten News:\n" + server.getNews()).queue();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (userMsg.equalsIgnoreCase(".help")) {
            event.getChannel().sendMessage(String.format("Befehle für den Raccoon Bot: " +
                                    "```JS" +
                                    "%n %-20s|%-20s" +
                                    "%n %-20s|%-20s" +
                                    "%n %-20s|%-20s" +
                                    "%n```",
                            ".status", "zeigt den Server Status",
                            ".news", "sendet die letzten News von 7 Days to Die",
                            ".jonas", "Easteregg"))
                    .queue();
            //%-20s|%-20s%n

        }


        System.out.println(userMsg);
    }
}
