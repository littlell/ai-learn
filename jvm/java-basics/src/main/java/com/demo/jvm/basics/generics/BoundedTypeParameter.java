package com.demo.jvm.basics.generics;

public class BoundedTypeParameter {

  public static void main(String[] args) {
    DogRun<Dog> dogDogRun = new DogRun<Dog>();

    dogDogRun.animalSay(new Dog());

    DogRun<Animal> dogAnimalRun = new DogRun<Animal>();
    dogAnimalRun.animalSay(new Animal());
  }

  static class DogRun<T extends Animal> {

    public void animalSay(T t) {
      t.say();
    }

  }


  static class Animal {

    public void say() {
      System.out.println("wow");
    }
  }

  static class Dog extends Animal {

    @Override
    public void say() {
      System.out.println("wang wang wang");
    }
  }

}
