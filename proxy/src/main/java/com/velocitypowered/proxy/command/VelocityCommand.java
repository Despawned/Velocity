package com.velocitypowered.proxy.command;

import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.permission.Tristate;
import com.velocitypowered.proxy.VelocityServer;
import net.kyori.text.TextComponent;
import net.kyori.text.event.ClickEvent;
import net.kyori.text.format.TextColor;
import net.kyori.text.format.TextDecoration;
import org.checkerframework.checker.nullness.qual.NonNull;

public class VelocityCommand implements Command {
    @Override
    public void execute(CommandSource source, String[] args) {
        String implVersion = VelocityServer.class.getPackage().getImplementationVersion();
        TextComponent velocity = TextComponent.builder("Velocity ")
                .decoration(TextDecoration.BOLD, true)
                .color(TextColor.DARK_AQUA)
                .append(TextComponent.of(implVersion == null ? "<unknown>" : implVersion).decoration(TextDecoration.BOLD, false))
                .build();
        TextComponent copyright = TextComponent.of("Copyright 2018 Velocity Contributors. Velocity is freely licensed under the terms of the " +
                "MIT License.");
        TextComponent velocityWebsite = TextComponent.builder()
                .content("Visit the ")
                .append(TextComponent.builder("Velocity website")
                        .color(TextColor.GREEN)
                        .clickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.velocitypowered.com"))
                        .build())
                .append(TextComponent.of(" or the ").resetStyle())
                .append(TextComponent.builder("Velocity GitHub")
                        .color(TextColor.GREEN)
                        .clickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/VelocityPowered/Velocity"))
                        .build())
                .build();

        source.sendMessage(velocity);
        source.sendMessage(copyright);
        source.sendMessage(velocityWebsite);
    }

    @Override
    public boolean hasPermission(@NonNull CommandSource source, @NonNull String[] args) {
        return source.getPermissionValue("velocity.command.info") != Tristate.FALSE;
    }
}
