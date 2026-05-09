package com.narxoz.rpg.guild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class GuildHall implements GuildMediator {

    private final Map<String, List<GuildMember>> membersByTopic = new HashMap<>();
    private int notificationCount = 0;

    @Override
    public void register(GuildMember member) {
        addSubscriber("orders", member);
        addSubscriber("supplies", member);
        addSubscriber("scouting", member);
        addSubscriber("healing", member);
        addSubscriber("urgent", member);
        addSubscriber("rewards", member);
        addSubscriber("lore", member);
    }

    @Override
    public void dispatch(String topic, GuildMember from, String payload) {
        List<GuildMember> subscribers = subscribersFor(topic);
        for (GuildMember member : subscribers) {
            if (member != from) {
                member.receive(topic, from, payload);
                notificationCount++;
            }
        }
    }

    public int getNotificationCount() {
        int count = notificationCount;
        notificationCount = 0;
        return count;
    }

    protected void addSubscriber(String topic, GuildMember member) {
        membersByTopic.computeIfAbsent(topic, key -> new ArrayList<>()).add(member);
    }

    protected List<GuildMember> subscribersFor(String topic) {
        return membersByTopic.getOrDefault(topic, List.of());
    }
}