package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.council.CouncilEngine;
import com.narxoz.rpg.council.CouncilRunResult;
import com.narxoz.rpg.guild.*;
import com.narxoz.rpg.quest.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println(" Homework 10 Demo: Iterator + Mediator \n");


        Hero aragorn = new Hero("Aragorn", 100, 25, 15);
        Hero legolas = new Hero("Legolas", 80, 30, 10);
        System.out.println("Party: " + aragorn.getName() + ", " + legolas.getName());


        QuestLog questLog = new QuestLog();
        questLog.add(new Quest("Goblin Camp", QuestPriority.LOW, 50, false));
        questLog.add(new Quest("Dragon Lair", QuestPriority.URGENT, 500, true));
        questLog.add(new Quest("Haunted Ruins", QuestPriority.HIGH, 200, false));
        questLog.add(new Quest("Escort Caravan", QuestPriority.NORMAL, 80, false));
        questLog.add(new Quest("Ancient Temple", QuestPriority.HIGH, 300, true));


        GuildHall hall = new GuildHall();
        Quartermaster qm = new Quartermaster("Thorin", hall);
        Scout scout = new Scout("Eowyn", hall);
        Healer healer = new Healer("Gandalf", hall);
        Captain captain = new Captain("Boromir", hall);
        Loremaster loremaster = new Loremaster("Elrond", hall);

        System.out.println("Guild officers registered.");


        System.out.println("\n Ordered Traversal");
        QuestIterator ordered = questLog.ordered();
        while (ordered.hasNext()) {
            System.out.println(ordered.next());
        }

        System.out.println("\n Reward Sorted");
        QuestIterator sorted = questLog.rewardSorted();
        while (sorted.hasNext()) {
            System.out.println(sorted.next());
        }


        CouncilEngine engine = new CouncilEngine();
        CouncilRunResult result = engine.runCouncil(List.of(aragorn, legolas), questLog, hall);

        System.out.println("\n" + result);
        System.out.println(" Council Session Complete");
    }
}