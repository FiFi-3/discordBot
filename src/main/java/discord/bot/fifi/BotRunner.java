package discord.bot.fifi;

import discord.bot.fifi.functions.MessageEventListener;
import discord.bot.fifi.functions.ReadyEventListener;
import discord.bot.fifi.service.Server;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class BotRunner {
    public static void main(String[] args) {
            try {
                    Server server = new Server();
                    final String TOKEN = "MTEyMDM4MjI3MDQ3NDI5NzM3Ng.G9uKu4.7niMbOjWiliyPINcFmyE37KzhuD5nBM3fFTbDw";
                    JDABuilder bot = JDABuilder.createDefault(TOKEN);
                    if (server.getStatus()) {
                            bot.setActivity(Activity.playing("Keksstube - ist online"));
                    } else {
                            bot.setActivity(Activity.playing("Keksstube - ist offline"));
                    }

                    bot.enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MESSAGES)
                            .addEventListeners(new ReadyEventListener(), new MessageEventListener());
                    bot.build();
            } catch (Exception e) {

            }

    }
}
