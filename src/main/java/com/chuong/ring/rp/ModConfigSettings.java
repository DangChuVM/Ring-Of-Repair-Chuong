package com.chuong.ring.rp;

import blue.endless.jankson.Comment;

public class ModConfigSettings {

    @Comment("The interval in ticks between repair attempts. 20 ticks = 1 second.")
    public int ringRepairInterval = 90;

}
