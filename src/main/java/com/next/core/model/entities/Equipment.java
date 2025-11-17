package com.next.core.model.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class Equipment {
    @Setter private Armor armor;
    private Item leftHand;
    private Item rightHand;

    public void setLeftHand(Item item) {
        this.leftHand = item;
        if (item.isTwoHanded())
            this.rightHand = item;
    }

    public void setRightHand(Item item) {
        this.rightHand = item;
        if (item.isTwoHanded())
            this.leftHand = item;
    }

    public boolean hasShield() {
        return (rightHand != null && rightHand.getId().equals("item.shield")) ||
                (leftHand != null && leftHand.getId().equals("item.shield"));
    }
}
