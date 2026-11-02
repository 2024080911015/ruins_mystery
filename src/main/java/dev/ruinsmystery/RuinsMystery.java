package dev.ruinsmystery;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.item.CreativeModeTabs;

public class RuinsMystery implements ModInitializer {
	public static final String MOD_ID = "ruinsmystery";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ResourceKey<Block> alterKey=ResourceKey.create(
				Registries.BLOCK,
				id("ancient_altar")
		);
		//给祭坛创建唯一标识符 ruinsmystery:ancient_altar
		AncientAltarBlock altar=new AncientAltarBlock(
				BlockBehaviour.Properties.of()
						.setId(alterKey)
						.strength(3.0f)  //设置方块的破坏硬度
		);
		Registry.register(
				BuiltInRegistries.BLOCK,
				alterKey,
				altar
		);
		//将方块注册到 Minecraft 中


		ResourceKey<Item> altarItemKey = ResourceKey.create(
				Registries.ITEM,
				id("ancient_altar")
		);

		BlockItem altarItem = new BlockItem(
				altar,
				new Item.Properties()
						.useBlockDescriptionPrefix()
						.setId(altarItemKey)
		);

		Registry.register(
				BuiltInRegistries.ITEM,
				altarItemKey,
				altarItem
		);
		//item注册表

		CreativeModeTabEvents.modifyOutputEvent(
				CreativeModeTabs.FUNCTIONAL_BLOCKS
		).register(tab->{
			tab.accept(altarItem);
				}
		);
	}
    //modifyOutputEvent() 是一个方法，它接收一个参数，并返回一个事件对象
	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
