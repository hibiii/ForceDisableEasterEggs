package hibi.forcedisableeastereggs.mix;

import java.util.Calendar;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.client.resource.SplashTextResourceSupplier;
import net.minecraft.util.random.RandomGenerator;

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

    @Redirect(
        method = "get",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/util/random/RandomGenerator;nextInt(I)I",
            ordinal = 0
        )
    )
    int disableUsernameSplash(RandomGenerator that, int _1) {
        return Integer.MIN_VALUE;
    }

    @Redirect(
        method = "m_dhjeeeti(Ljava/lang/String;)Z",
        /// Lambda of `prepare`
        at = @At(
            value = "INVOKE",
            target = "Ljava/lang/String;hashCode()I"
        )
    )
    private static int disableDisqualification(String that) {
        return 0;
    }
}
