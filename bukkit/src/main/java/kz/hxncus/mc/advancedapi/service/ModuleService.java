package kz.hxncus.mc.advancedapi.service;

import kz.hxncus.mc.advancedapi.api.module.Module;
import kz.hxncus.mc.advancedapi.api.service.AbstractService;
import org.bukkit.plugin.Plugin;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ModuleService extends AbstractService {
	private static final List<Module> modules = new ArrayList<>();
	
	public ModuleService(final Plugin plugin) {
		super(plugin);
	}
	
	@Override
	public void register() {
		registerModules();
		enableModules();
	}
	
	@Override
	public void unregister() {
		disableModules();
		ModuleService.modules.clear();
	}
	
	private void registerModules() {
	
	}
	
	public void addModule(Module module) {
		ModuleService.modules.add(module);
	}
	
	public Module getModule(String name) {
		for (Module module : ModuleService.modules) {
			if (module.getName().equals(name)) {
				return module;
			}
		}
		return null;
	}
	
	public List<Module> getModules() {
		return Collections.unmodifiableList(ModuleService.modules);
	}
	
	private void sortServices(final boolean isReversed) {
		Comparator<Module> comparator = Comparator.comparingInt(Module::getPriority);
		if (isReversed) {
			ModuleService.modules.sort(comparator.reversed());
		} else {
			ModuleService.modules.sort(comparator);
		}
	}
	
	public void enableModules() {
		this.sortServices(false);
		for (Module module : ModuleService.modules) {
			if (module.isEnabled()) {
				continue;
			}
			module.setEnabled(true);
		}
	}
	
	public void disableModules() {
		this.sortServices(true);
		for (Module module : ModuleService.modules) {
			if (!module.isEnabled()) {
				continue;
			}
			module.setEnabled(false);
		}
	}
	
	@Nullable
	public static <T extends Module> Module getRawModule(Class<T> moduleClass) {
		if (moduleClass.equals(Module.class)) {
			throw new RuntimeException("Module.class");
		}
		
		for (Module module : ModuleService.modules) {
			if (module.getClass().equals(moduleClass)) {
				return module;
			}
		}
		
		return null;
	}
	
	@Nullable
	public static <T extends Module> T getModule(Class<T> moduleClass) {
		Module rawModule = getRawModule(moduleClass);
		return rawModule == null ? null : moduleClass.cast(rawModule);
	}
}
