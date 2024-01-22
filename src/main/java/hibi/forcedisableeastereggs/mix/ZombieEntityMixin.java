package hibi.forcedisableeastereggs.mix;

import java.time.LocalDate;
import java.time.temporal.TemporalField;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.entity.mob.ZombieEntity;

@Mixin(ZombieEntity.class)
public class ZombieEntityMixin {
    @Redirect(
        method = "initialize",
        at = @At(
            value = "INVOKE",
            target = "Ljava/time/LocalDate;get(Ljava/time/temporal/TemporalField;)I"
        )
    )
    int disableHalloweenDecor(LocalDate that, TemporalField _1) {
        return 0;
    }
}
