package hibi.forcedisableeastereggs.mix;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.entity.LivingEntity;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {
    @Inject(
        method = "renderFlipped",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void disableGrummFlip(LivingEntity _1, CallbackInfoReturnable<Boolean> info) {
        info.setReturnValue(false);
    }
}
