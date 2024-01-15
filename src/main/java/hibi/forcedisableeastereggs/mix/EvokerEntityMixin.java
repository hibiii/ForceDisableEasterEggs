package hibi.forcedisableeastereggs.mix;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.mob.EvokerEntity;

@Mixin(EvokerEntity.class)
public class EvokerEntityMixin {
    @Redirect(
        method = "initGoals",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/ai/goal/GoalSelector;add(ILnet/minecraft/entity/ai/goal/Goal;)V",
            ordinal = 5
        )
    )
    void disableColorConversion(GoalSelector that, int _1, Goal _2) {
        /// empty function ///
    }
}
