package com.example.administrator.myall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(BlockJUnit4ClassRunner.class)
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
//    @Before
//    public void before(){
//        LogUtils.i("before-----------");
//        assertEquals(4, 2 + 2);
//    }
//    @After
//    public void after(){
//        LogUtils.i("after-----------");
//        assertEquals(4, 2 + 2);
//    }
}