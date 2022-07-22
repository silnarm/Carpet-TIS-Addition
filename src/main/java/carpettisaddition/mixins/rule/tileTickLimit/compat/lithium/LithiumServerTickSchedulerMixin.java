package carpettisaddition.mixins.rule.tileTickLimit.compat.lithium;

import carpettisaddition.CarpetTISAdditionSettings;
import carpettisaddition.utils.ModIds;
import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import me.jellysquid.mods.lithium.common.world.scheduler.LithiumServerTickScheduler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Restriction(require = {
		@Condition(value = ModIds.minecraft, versionPredicates = "<1.18"),
		@Condition(ModIds.lithium)
})
@Mixin(value = LithiumServerTickScheduler.class, priority = 998)
public abstract class LithiumServerTickSchedulerMixin
{
	@ModifyConstant(
			method = "selectTicks",
			constant = {
					@Constant(intValue = 65536),  // vanilla value
					@Constant(intValue = 65565),  // value in lithium (<=0.6.0)
			},
			require = 1,
			remap = false
	)
	private static int modifyTileTickLimit(int value)
	{
		return CarpetTISAdditionSettings.tileTickLimit;
	}
}
