package hibi.forcedisableeastereggs.mix;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.gui.LogoRenderer;
import net.minecraft.util.random.RandomGenerator;

@Mixin(LogoRenderer.class)
public class LogoRendererMixin {
    @Redirect(
        method = "<init>",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/util/random/RandomGenerator;nextFloat()F"
        )
    )
    float disableMinceraft(RandomGenerator that) {
        return 0.9f;
    }
}
