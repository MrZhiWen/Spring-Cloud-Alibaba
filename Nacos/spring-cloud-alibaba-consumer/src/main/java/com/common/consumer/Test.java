package com.common.consumer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @ClassName : Text.java
 * @Description : 测试类
 * @Author : lizhiwen
 * @Date: 2020-04-22 17:35
 */
public class Test {

    public static void main(String[] args) {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(11, "小牙刷", new BigDecimal(12.05) ));
        items.add(new Item(5, "日本马桶盖", new BigDecimal(999.05) ));
        items.add(new Item(7, "格力空调", new BigDecimal(888.88 )));
        items.add(new Item(17, "肥皂", new BigDecimal(2.00 )));
        items.add(new Item(9, "冰箱", new BigDecimal(4200.00) ));

        items.removeIf(ele -> ele.getId() == 7);

        //通过 foreach 遍历，查看是否已经删除
        items.forEach(a -> System.out.print(a.getId()+ ","));
        items.sort((a , b) -> a.getId() - b.getId());
        System.out.println("");
        items.forEach(a -> System.out.print(a.getId() +","));

        items.forEach( a -> {
            if(a.getName().equals("冰箱")){
                a.setName("");
            }
            a.setId(a.getId()+3);

        });
        System.out.println("");
        items.forEach(a -> System.out.print(a.getId()+a.getName() +","));

        System.out.println("");
//        ArrayList<Integer> res = getNumList(10, () -> (int) (Math.random() * 100));
//        System.out.println(res);
        Collection<String> strings = new ArrayList<>();
        Path path = new File("E:\\log\\testFlie.log").toPath();
        try( Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)){
            lines.onClose(()-> System.out.println("Done!")).forEach(ls -> strings.add(ls));
        } catch (IOException e) {
            e.printStackTrace();
        }
        strings.forEach(a -> System.out.println(a));
        // 获取时间
        LocalDate local = LocalDate.now();
        System.out.println(local);




    }
    //需求，产生指定个数的整数，并放入集合中
    public static ArrayList<Integer> getNumList(int num, Supplier<Integer> sup){
        ArrayList<Integer>list = new ArrayList<>();
        for(int i = 0; i < num; i++){
            Integer e = sup.get();
            list.add(e);
        }
        return list;
    }




    public static class Item{
        private Integer id;
        private String name;
        private BigDecimal pice;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getPice() {
            return pice;
        }

        public void setPice(BigDecimal pice) {
            this.pice = pice;
        }

        public Item(Integer id, String name, BigDecimal pice) {
            this.id = id;
            this.name = name;
            this.pice = pice;
        }
    }
}
