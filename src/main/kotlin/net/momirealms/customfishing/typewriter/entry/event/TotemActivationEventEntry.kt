package net.momirealms.customfishing.typewriter.entry.event

import com.typewritermc.core.books.pages.Colors
import com.typewritermc.core.entries.Query
import com.typewritermc.core.entries.Ref
import com.typewritermc.core.extension.annotations.Entry
import com.typewritermc.core.extension.annotations.EntryListener
import com.typewritermc.core.interaction.context
import com.typewritermc.engine.paper.entry.TriggerableEntry
import com.typewritermc.engine.paper.entry.entries.EventEntry
import com.typewritermc.engine.paper.entry.triggerAllFor
import net.momirealms.customfishing.api.event.TotemActivateEvent

@Entry("customfishing_totem_activate_event", "CustomFishing Totem Activation Event", Colors.YELLOW, "material-symbols:bigtop-updates")
class TotemActivationEventEntry(
    override val id: String = "",
    override val name: String = "",
    override val triggers: List<Ref<TriggerableEntry>> = emptyList(),
    val totemId: List<String> = emptyList(),
) : EventEntry {
}

@EntryListener(TotemActivationEventEntry::class)
fun onTotemActivateEvent(event: TotemActivateEvent, query: Query<TotemActivationEventEntry>) {
    if (event.isCancelled) return
    query.findWhere { entry ->
        if (entry.totemId.isEmpty()) {
            return@findWhere true
        }
        entry.totemId.contains(event.config.id())
    }.triggerAllFor(event.player, context())
}