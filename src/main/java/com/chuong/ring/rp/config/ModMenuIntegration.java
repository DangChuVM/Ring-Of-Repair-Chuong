package com.chuong.ring.rp.config;

import com.chuong.ring.rp.RingOfRepair;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;

public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(parent)
                    .setTitle(Text.translatable("title.ring_of_repair.config"));

            // On save, save the config
            builder.setSavingRunnable(() -> {
                RingOfRepair.CONFIG.save();
            });

            ConfigCategory general = builder.getOrCreateCategory(Text.translatable("category.ring_of_repair.general"));

            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            general.addEntry(entryBuilder.startDoubleField(Text.translatable("option.ring_of_repair.repairIntervalSeconds"), RingOfRepair.CONFIG.repairIntervalSeconds)
                    .setDefaultValue(1.0)
                    .setMin(0.1)
                    .setMax(5.0)
                    .setTooltip(Text.translatable("tooltip.ring_of_repair.repairIntervalSeconds"))
                    .setSaveConsumer(newValue -> RingOfRepair.CONFIG.repairIntervalSeconds = newValue)
                    .build());

            return builder.build();
        };
    }
}
