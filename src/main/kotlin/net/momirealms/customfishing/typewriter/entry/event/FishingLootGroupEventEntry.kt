package net.momirealms.customfishing.typewriter.entry.event

import com.typewritermc.core.books.pages.Colors
import com.typewritermc.core.entries.Query
import com.typewritermc.core.entries.Ref
import com.typewritermc.core.extension.annotations.Entry
import com.typewritermc.core.extension.annotations.EntryListener
import com.typewritermc.core.extension.annotations.Help
import com.typewritermc.core.interaction.context
import com.typewritermc.engine.paper.entry.TriggerableEntry
import com.typewritermc.engine.paper.entry.entries.EventEntry
import com.typewritermc.engine.paper.entry.triggerAllFor
import net.momirealms.customfishing.api.event.FishingResultEvent
import java.util.*

@Entry("customfishing_successful_fishing_loot_group_event", "CustomFishing Fishing Loot Id Event", Colors.BLUE, "material-symbols:bigtop-updates")
class FishingLootGroupEventEntry(
    override val id: String = "",
    override val name: String = "",
    override val triggers: List<Ref<TriggerableEntry>> = emptyList(),
    @Help("The group of the loot")
    val groupId: Optional<String> = Optional.empty(),
) : EventEntry {
}

@EntryListener(FishingLootGroupEventEntry::class)
fun onFishingResultEvent(event: FishingResultEvent, query: Query<FishingLootGroupEventEntry>) {
    if (event.isCancelled) return
    if (event.result != FishingResultEvent.Result.SUCCESS) return
    val groups = event.loot.lootGroup()
    query.findWhere { entry ->
        entry.groupId.map {
            groups?: return@map false
            for (group in groups) {
                if (group == it) {
                   return@map true
                }
            }
            false
        }.orElse(true)
    }.triggerAllFor(event.player, context())
}