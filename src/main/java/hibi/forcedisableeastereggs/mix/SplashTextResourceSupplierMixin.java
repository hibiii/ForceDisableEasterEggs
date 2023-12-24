package hibi.forcedisableeastereggs.mix;

import java.util.Calendar;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.resource.SplashTextResourceSupplier;

@Mixin(SplashTextResourceSupplier.class)
public class SplashTextResourceSupplierMixin {
    @Redirect(
        method = "get",
        at = @At(
            value = "INVOKE",
            target = "Ljava/util/Calendar;get(I)I"
        )
    )
    int disableDates(Calendar that, int _1) {
        return Integer.MIN_VALUE;
    }
}
