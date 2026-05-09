package com.narxoz.rpg.council;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.GuildMediator;
import com.narxoz.rpg.guild.GuildHall;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.QuestLog;
import java.util.List;

public class CouncilEngine {

    public CouncilRunResult runCouncil(List<Hero> party, QuestLog questLog, GuildMediator hall) {
        System.out.println("\n War Council Planning Session ");

        int questsTraversed = 0;
        int messagesRouted = 0;


        QuestIterator it1 = questLog.ordered();
        while (it1.hasNext()) {
            System.out.println("Planning: " + it1.next());
            questsTraversed++;

            hall.dispatch("orders", null, "Prepare for next quest");
            messagesRouted++;
        }


        QuestIterator it2 = questLog.priorityAtLeast(com.narxoz.rpg.quest.QuestPriority.HIGH);
        while (it2.hasNext()) {
            System.out.println("URGENT: " + it2.next());
            questsTraversed++;
            hall.dispatch("urgent", null, "High priority mission!");
            messagesRouted++;
        }

        int membersNotified = hall instanceof GuildHall ?
                ((GuildHall) hall).getNotificationCount() : 0;

        return new CouncilRunResult(questsTraversed, messagesRouted, membersNotified);
    }
}