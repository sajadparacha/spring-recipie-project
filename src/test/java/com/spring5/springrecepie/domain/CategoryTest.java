package com.spring5.springrecepie.domain;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {
    static Category category;

    /**
     * @BeforeAll will be executed before executing any test case in this class
     * In this case the Category object will be initiated
     */
    @BeforeAll
    public static void setup(){
        category= new Category();
    }
    @Test
    void getId() {

        long idValue=4L;
        category.setId(idValue);
        assertEquals(idValue,category.getId());
    }

    @Test
    void getDescription() {
        String desc="This is my frist unit test case";
        category.setDescription(desc);
        assertEquals(desc,category.getDescription());
    }
}