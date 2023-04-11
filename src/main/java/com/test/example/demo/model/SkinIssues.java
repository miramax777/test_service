package com.test.example.demo.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public enum SkinIssues {
    HYGIENE,
    MASSAGE,
    GLOW,
    TONING,
    IMPURITIES,
    DARK_CIRCLE,
    EYE_BAG,
    OILY_SKIN,
    TEXTURE,
    FINE_LINES;

    public static List<String> getSkinIssues() {
        return Arrays.stream(SkinIssues.values()).map(SkinIssues::name).collect(Collectors.toList());
    }

    public static List<String> getSpecifiedSkinIssues(final Collection<SkinIssues> skinIssues) {
        return skinIssues.stream().map(Enum::name).toList();
    }
}
