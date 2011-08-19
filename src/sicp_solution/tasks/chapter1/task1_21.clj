(ns sicp-solution.tasks.chapter1.task1-21
  (:use [clojure.test]))

(defn square [x] (* x x))

(defn divides?
  [a b]
  (= (rem b a) 0))

(defn find-divisor
  [n test-divisor]
  (loop [n n, test-divisor test-divisor]
    (cond (> (square test-divisor) n) n
          (divides? test-divisor n) test-divisor
          true (recur n (+ test-divisor 1)))))

(defn smallest-divisor
  [n] (find-divisor n 2))

;tests
(deftest smallest-divisor-test
  (is (= (smallest-divisor 1) 1))
  (is (= (smallest-divisor 2) 2))
  (is (= (smallest-divisor 4) 2))
  
  (is (= (smallest-divisor 9) 3))
  (is (= (smallest-divisor 25) 5))
  (is (= (smallest-divisor 17) 17))

  (is (= (smallest-divisor 199) 199))
  (is (= (smallest-divisor 1999) 1999))
  (is (= (smallest-divisor 19999) 7)))