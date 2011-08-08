(ns sicp-solution.tasks.chapter1.task1-6
  (:use [clojure.test])
  (:use [clojure.contrib.math :only (abs)]))

(defn new-if [predicate then-clause else-clause]
  (cond
    predicate then-clause
    true else-clause))

(defn square [x] (* x x))

(defn good-enough? [guess x]
  (< (abs (- (square guess) x)) 0.0001))

(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn sqrt-iter [guess x]
  (new-if (good-enough? guess x)
    guess
    (sqrt-iter (improve guess x) x)))

(defn sqrt [number]
  (loop [guess 1.0 x number]
    (if (good-enough? guess x)
      guess
      (recur (improve guess x) x))))

(defn sqrt1 [x]
  (sqrt-iter 1.0 x))


; tests
(deftest new-if-test
  (is (= 5 (new-if (= 2 3) 0 5)))
  (is (= 0 (new-if (= 1 1) 0 5))))

(deftest sqrt-test
  (is (> 0.0001 (abs (- (sqrt 9) 3)))))

(deftest sqrt1-test
  (is (thrown? java.lang.StackOverflowError (sqrt1 9))))
