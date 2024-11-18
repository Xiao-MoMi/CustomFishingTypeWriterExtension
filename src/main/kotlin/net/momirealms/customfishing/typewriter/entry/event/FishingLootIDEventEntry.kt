package net.momirealms.customfishing.typewriter.entry.event

import com.typewritermc.core.books.pages.Colors
import com.typewritermc.core.entries.Query
import com.typewritermc.core.entries.Ref
import com.typewritermc.core.extension.annotations.*
import com.typewritermc.engine.paper.entry.TriggerableEntry
import com.typewritermc.engine.paper.entry.entries.EventEntry
import com.typewritermc.engine.paper.entry.triggerAllFor
import net.momirealms.customfishing.api.event.FishingResultEvent

@Entry("customfishing_successful_fishing_loot_id_event", "CustomFishing Fishing Loot Group Event", Colors.BLUE, "material-symbols:bigtop-updates")
class FishingLootIDEventEntry(
    override val id: String = "",
    override val name: String = "",
    override val triggers: List<Ref<TriggerableEntry>> = emptyList(),
    @Help("The ids of the loot")
    val lootId: List<String> = emptyList(),
) : EventEntry {
}

@EntryListener(FishingLootIDEventEntry::class)
fun onFishingResultEvent(event: FishingResultEvent, query: Query<FishingLootIDEventEntry>) {
    if (event.isCancelled) return
    if (event.result != FishingResultEvent.Result.SUCCESS) return
    val id = event.loot.id()
    query findWhere { entry ->
        if (entry.lootId.isEmpty()) {
            return@findWhere true
        }
        entry.lootId.contains(id)
    } triggerAllFor event.player
}