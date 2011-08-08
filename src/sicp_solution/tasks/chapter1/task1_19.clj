(ns sicp-solution.tasks.chapter1.task1-19
  (:use [clojure.test]))

(defn square [num] (* num num))

(defn fib
  [n]
  (loop [a 1, b 0, count n]
    (if (= count 0) b
        (recur (+ a b) a (- count 1)))))

(defn fib-iter
  [n]
  (loop [a 1, b 0, p 0, q 1, count n]
    (cond (= count 0) b
          (even? count)
          (recur a
                 b
                 (+ (square p) (square q))
                 (+ (square q) (* 2 p q))
                 (/ count 2))
          true
          (recur (+ (* b q) (* a q) (* a p))
                 (+ (* b p) (* a q))
                 p
                 q
                 (- count 1)))))

; tests
(deftest fib-iter-test
  (is (= 0 (fib 0) (fib-iter 0)))
  (is (= 1 (fib 1) (fib-iter 1)))
  (is (= 5 (fib 5) (fib-iter 5)))

  (is (= 55 (fib 10) (fib-iter 10)))
  (is (= 610 (fib 15) (fib-iter 15))))