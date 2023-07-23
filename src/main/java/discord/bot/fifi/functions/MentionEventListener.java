package discord.bot.fifi.functions;

import net.dv8tion.jda.api.entities.Mentions;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class MentionEventListener implements EventListener {
    @Override
    public void onEvent(GenericEvent event) {
        if (event instanceof Mentions) {
            System.out.println("Someone summond me!");
        }
    }
}

