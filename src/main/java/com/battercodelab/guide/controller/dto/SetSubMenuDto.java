package com.battercodelab.guide.controller.dto;

import lombok.Data;

public class SetSubMenuDto {
    @Data
    public static class Request {
        private String mcode;
        private String name;
        private String expl;
    }
}
