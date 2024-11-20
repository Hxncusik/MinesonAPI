package kz.hxncus.mc.advancedapi.bukkit.bossbar;

import kz.hxncus.mc.advancedapi.api.bukkit.bossbar.Animatable;
import kz.hxncus.mc.advancedapi.api.bukkit.bossbar.AnimationType;
import kz.hxncus.mc.advancedapi.bukkit.scheduler.AdvancedScheduler;
import lombok.ToString;

/**
 * The type Animated boss bar.
 * @author Hxncus
 * @since  1.0.1
 */
@ToString
public class AnimatedBossBar extends AdvancedBossBar implements Animatable {
	private final AnimationType animationType;
	private final long delay;
	private final long period;
	
	/**
	 * Instantiates a new Animated boss bar.
	 *
	 * @param animationType the animation type
	 * @param delay         the delay
	 * @param period        the period
	 */
	public AnimatedBossBar(final AnimationType animationType, final long delay, final long period) {
		this.animationType = animationType;
		this.delay = delay;
		this.period = period;
	}
	
	@Override
	public void stopAnimation() {
	
	}
	
	@Override
	public void startAnimation(final AdvancedScheduler advancedScheduler) {
		AdvancedScheduler.timerAsync(this.delay, this.period, () -> {
			switch (this.animationType) {
				case STATIC:
					this.startStaticAnimation();
					break;
				case PROGRESSIVE:
					this.startProgressiveAnimation();
					break;
			}
		});
	}
	
	private void startStaticAnimation() {
	
	}
	
	private void startProgressiveAnimation() {
	
	}
}