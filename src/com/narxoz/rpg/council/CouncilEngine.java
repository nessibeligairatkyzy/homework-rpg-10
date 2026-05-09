package com.narxoz.rpg.council;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.GuildHall;
import com.narxoz.rpg.guild.GuildMediator;
import com.narxoz.rpg.quest.Quest;
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
            Quest quest = it1.next();
            System.out.println("Planning: " + quest);
            questsTraversed++;

            hall.dispatch("orders", null, "Prepare for " + quest.getTitle());
            hall.dispatch("supplies", null, "Need gear for " + quest.getTitle());
            messagesRouted += 2;
        }


        QuestIterator it2 = questLog.priorityAtLeast(com.narxoz.rpg.quest.QuestPriority.HIGH);
        while (it2.hasNext()) {
            Quest quest = it2.next();
            System.out.println("URGENT PLANNING: " + quest);
            questsTraversed++;

            hall.dispatch("urgent", null, "High priority mission: " + quest.getTitle());
            messagesRouted++;
        }

        int membersNotified = (hall instanceof GuildHall)
                ? ((GuildHall) hall).getNotificationCount()
                : 0;

        System.out.println(" Council Session Finished ");
        return new CouncilRunResult(questsTraversed, messagesRouted, membersNotified);
    }
}