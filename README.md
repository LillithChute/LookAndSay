# LookAndSay
The goal of this lab is to design and implement an Iterator design pattern.

## What to do
Package: lookandsay

Define a new RIterator interface that extends Iterator and adds two methods: T prev() and boolean hasPrevious(). Their meanings are self-explanatory. Then define a class LookAndSayIterator that implements RIterator. This iterator will return the numbers as BigInteger objects.

The LookAndSayIterator class should have the following characteristics:

It must offer a constructor that takes two arguments: a starting seed and an end value. The seed is the number at which the sequence must begin. The iterator should stop incrementing once a number greater than end is reached. If the seed is not positive, or is not less than the end or has any zeroes in it, the constructor should throw an IllegalArgumentException.

It must offer a constructor that takes a starting seed as its only argument. The seed is the number at which the sequence must begin. The end value will be a number with 100 9s (the largest 100 digit number).If the seed is not positive, or is not less than the end or has any zeroes in it, the constructor should throw an IllegalArgumentException.

It must offer a constructor that takes no arguments. This should start the sequence with a seed of 1 (e.g. calling next and an end value as a number with 100 9s (the largest 100 digit number).

next() should return the current number in the sequence and advance to the next number.

prev() should return the current number in the sequence and revert to the previous number in the sequence.

hasNext() should return true if the next number to be returned is still lesser than end (specified in the constructors), false otherwise.

hasPrevious() should return true if it is possible to go back one step, false otherwise. How would you determine this?

Note that according to the above specification, the sequence may begin in the middle of a sequence (depending on what the seed is) but be able to go back behind the seed, until it cannot. For example, if the seed is "11" , it is possible to go backward beyond "11" to "1" .
