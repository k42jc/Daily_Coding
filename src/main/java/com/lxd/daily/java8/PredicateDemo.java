package com.lxd.daily.java8;

import com.lxd.daily.App;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

/**
 * java8谓语示例
 */
public class PredicateDemo {

    public static void main(String[] args){
        List<Apple> apples = Arrays.asList(new Apple("red", 100), new Apple("red", 120),
                new Apple("red", 10),
                new Apple("red", 20),
                new Apple("red", 30),
                new Apple("green", 120),
                new Apple("green", 100),
                new Apple("green", 20),
                new Apple("green", 10));
        // 先过滤重量大于100的苹果
        List<Apple> filter = filter(apples, apple -> apple.getWeight() > 100);
        filter.forEach(System.out::println);
        System.out.println();
        // 再过滤颜色不是红色的
        List<Apple> filter1 = filter(filter, apple -> "red".equals(apple.getColor()));
        filter1.forEach(System.out::println);

        Comparator<Apple> comparing = Comparator.comparing(Apple::getWeight);
        BiConsumer<Apple, Apple> appleComparator = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
    }


    /**
     * 使用谓语接口定义参数行为来实现动态条件判定
     * @param tList 需要过滤的目标
     * @param predicate 谓语接口
     * @param <T> 实体类
     * @return 过滤后的结果
     */
    private static <T> List<T> filter(List<T> tList, Predicate<T> predicate) {
        List<T> list = new ArrayList<>();
        if (tList != null) {
            tList.forEach(t -> {
                if (predicate.test(t)) {
                    list.add(t);
                }
            });
        }
        return list;
    }

    static class Apple{
        private String color;
        private Integer weight;

        public Apple(String color, int weight) {
            this.color = color;
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }
}
