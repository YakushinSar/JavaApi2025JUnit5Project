package lesson2;

import org.junit.jupiter.api.Test;

public class ParallelTest {

/*
 оба теста запускаются параллельно а не один за другим и в разных воркерах
 за параллельность отвtчает файл в resources, должен быть true
 junit.jupiter.execution.parallel.enabled = true
 */

    @Test
    void firstTest() throws Exception{
        System.out.println("FirstParallelUnitTest first() start => " + Thread.currentThread().getName());
        Thread.sleep(500);
        System.out.println("FirstParallelUnitTest first() end => " + Thread.currentThread().getName());
    }

    @Test
    void secondTest() throws Exception{
        System.out.println("FirstParallelUnitTest second() start => " + Thread.currentThread().getName());
        Thread.sleep(500);
        System.out.println("FirstParallelUnitTest second() end => " + Thread.currentThread().getName());
    }
}
